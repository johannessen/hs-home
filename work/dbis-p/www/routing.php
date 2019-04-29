<?php
/* $Id: routing.php 2008-07-17 aj3 $
 * encoding us-ascii
 * 
 * Copyright (c) 2008 Arne Johannessen, Volker von Nathusius
 * All rights reserved. See LICENSE for details.
 */

require_once('dbis.include.php');



/****** data entry and prep ******/

if (array_key_exists('stopname', $_GET)) {
	// script for pop-up map click handling only
?>
<SCRIPT TYPE="text/javascript">
hideModalMapApply("<?php echo htmlspecialchars(dbisHttpGet('stopname')) ?>");
</SCRIPT>
<?php
	exit();
}

$isAllValuesPresent = array_key_exists('from', $_GET) && array_key_exists('to', $_GET);
$safeChangeTime = array_key_exists('change-minutes', $_GET) ? (int)dbisHttpGet('change-minutes') : 5;
$safeFilter = (array_key_exists('filter', $_GET) || ! (array_key_exists('change-minutes', $_GET) && array_key_exists('from', $_GET) && array_key_exists('to', $_GET))) && @dbisHttpGet('filter') != '0';

$safeFromStopName = "";
$safeToStopName = "";
if ($isAllValuesPresent) {
	
	// input processing
	$unsafeFromStopName = dbisHttpGet('from');
	$safeFromStopName = htmlspecialchars($unsafeFromStopName, ENT_NOQUOTES);
	$unsafeToStopName = dbisHttpGet('to');
	$safeToStopName = htmlspecialchars($unsafeToStopName, ENT_NOQUOTES);
	
	$fromStopName = dbisEscape($unsafeFromStopName);
	dbisQuery("SELECT id FROM stop WHERE name = '$fromStopName';");
	if ($row = dbisNextRow()) {
		$fromStopId = $row['id'];
	}
	$toStopName = dbisEscape($unsafeToStopName);
	dbisQuery("SELECT id FROM stop WHERE name = '$toStopName';");
	if ($row = dbisNextRow()) {
		$toStopId = $row['id'];
	}
	
	// print page title and header
	dbisPrintHeadGoogleMaps('Connection between '.$safeFromStopName.' and '.$safeToStopName);
}
else {
	// print page title and header
	dbisPrintHeadGoogleMaps('Find a connection between two stops');
}
?>
<SCRIPT SRC="stop-selection.js" TYPE="text/javascript"></SCRIPT>
<DIV ID="mapdialog"><!-- DOM anchor for modal map dialog --></DIV>

<FORM METHOD="GET" ACTION="">
	<P>Find me a route:</P>
	<P>
		<LABEL>From stop <INPUT TYPE="text" NAME="from" ID="from" VALUE="<?php echo @$safeFromStopName; ?>"></LABEL><BUTTON TYPE="button" DISABLED onClick="showModalMap(&quot;from&quot;)"><IMG SRC="images/map.png" ALT="Show map"></BUTTON>
		<BR><LABEL>To stop <INPUT TYPE="text" NAME="to" ID="to" VALUE="<?php echo @$safeToStopName; ?>"></LABEL><BUTTON TYPE="button" DISABLED onClick="showModalMap(&quot;to&quot;)"><IMG SRC="images/map.png" ALT="Show map"></BUTTON>
		<BR><LABEL>Changing lines takes me <INPUT TYPE="text" NAME="change-minutes" VALUE="<?php echo $safeChangeTime; ?>" CLASS="numericvalue"> minutes.</LABEL>
		<BR><LABEL><INPUT TYPE="checkbox" NAME="filter" VALUE="1"<?php echo $safeFilter ? ' CHECKED' : '' ?>> I prefer fast connections.</LABEL>
	</P>
	<P><INPUT TYPE="submit" VALUE="Go!"></P>
</FORM>

<?php

// only proceed with the actual route calculation if we have all necessary info
if (! $isAllValuesPresent) {
	dbisPrintMenu();
	exit();
}

// defend against nonexistent stops
if (! isset($fromStopId) || ! isset($toStopId)) {
	header('HTTP/1.1 404 Not Found');
?>
<P>No route could be found from <?php echo $safeFromStopName; ?> to <?php echo $safeToStopName; ?>.</P>
<P>Check that both stops exist and are spelled correctly. Use the <A HREF="stop_nameQuery.php?stop=<?php echo isset($fromStopId) ? $safeToStopName : $safeFromStopName; ?>">search for a stopname</A> service if you are unsure about the spelling.</P>
<?php
	dbisPrintMenu();
	exit();
}





/****** routing ******/

/* Database queries are very slow compared to an al-PHP solution.
 * Therefore we make an effort to minimise the count of database
 * queries by retrieving an entire table of results from the
 * database and caching it in a PHP array. This has brought a nearly
 * 1000-fold (!) speed increase.
 * This function is responsible for creating these cache arrays.
 */
function createCache ($cacheType) {
	$cache = array();
	switch ($cacheType) {
		case 'stop_has_line':
			dbisQuery("
					SELECT id_stop, id_line, distance, duration
					FROM stop_has_line
					ORDER BY id_line ASC, `order` ASC;
					");
			while ($row = dbisNextRow()) {
				$cache[$row['id_line']][] = array('stop' => $row['id_stop'], 'distance' => $row['distance'], 'duration' => $row['duration']);
			}
			break;
		
		case 'interchanges':
			dbisQuery("
					SELECT DISTINCT shl2.id_line AS line_id, shl2.id_stop AS stop_id, shl1.id_line AS line_id1
					FROM stop_has_line shl1
					INNER JOIN stop_has_line shl2 ON shl1.id_stop = shl2.id_stop
					WHERE shl1.id_line <> shl2.id_line;
					");
			while ($row = dbisNextRow()) {
				$cache[$row['line_id1']][] = array('line' => $row['line_id'], 'stop' => $row['stop_id']);
			}
			break;
		
		case 'stop':
			dbisQuery("
					SELECT id, name
					FROM stop;
					");
			while ($row = dbisNextRow()) {
				$cache[$row['id']] = $row['name'];
			}
			break;
		
		case 'line':
			dbisQuery("
					SELECT id, name, color
					FROM line;
					");
			while ($row = dbisNextRow()) {
				$cache[$row['id']] = array('name' => $row['name'], 'color' => $row['color']);
			}
			break;
		
		default:
			trigger_error('Unknown cache type "'.$cacheType.'"', E_USER_ERROR);
	}
	dbisFreeResult();
	return $cache;
}


/* Determines the quickest route between two stops
 * travelling on one particular line. */
function quickestConnection ($fromStopId, $toStopId, $lineId) {
	global $linesServiceStops;
	
	// get all instances of the from and to stop IDs (normally just one each)
	$froms = array();
	$tos = array();
	foreach ($linesServiceStops[$lineId] as $key => $item) {
		if ($item['stop'] == $fromStopId) {
			$froms[] = $key;
		}
		if ($item['stop'] == $toStopId) {
			$tos[] = $key;
		}
	}
	
	if (count($froms) == 0 || count($tos) == 0) {
		// stops 1 and 2 are not both on the given line
		return NULL;
	}
	
	// calculate duration and distance for each pair, searching for quickest
	// note that normally there is exactly one pair, thus O(n^2) : n=1 => O(1) in the average case
	$quickest = array('duration' => NULL, 'distance' => NULL);
	foreach ($froms as $from) {
		foreach ($tos as $to) {
			$duration = abs($linesServiceStops[$lineId][$to]['duration'] - $linesServiceStops[$lineId][$from]['duration']);
			if ($quickest['duration'] === NULL || $duration < $quickest['duration']) {
				$quickest['duration'] = $duration;
				$quickest['distance'] = abs($linesServiceStops[$lineId][$to]['distance'] - $linesServiceStops[$lineId][$from]['distance']);
			}
		}
	}
	return $quickest;
}


/* Detemines whether the given line services the given stop.
 */
function lineServicesStop ($lineId, $stopId) {
	global $linesServiceStops;
	foreach ($linesServiceStops[$lineId] as $item) {
		if ($item['stop'] == $stopId) {
			return TRUE;
		}
	}
	return FALSE;
}


/* Detemines if this route has already had a change
 * at a given interchange station.
 */
function isChangeInStack ($stop, $line, $changeStack) {
	foreach ($changeStack as $stackItem) {
		if ($stop == $stackItem['from']) {
			return TRUE;
		}
	}
	return FALSE;
}


/* Main workhorse function, contains the routing algorithm.
 * Recursively traverses sensible routes and writes possible
 * results into a global array.
 */
function findRouteLeg ($currentStopId, $destinationStopId, $currentLineId) {
	global $changeStack, $routeList, $interchangeStops;
	$changeStack[] = array('from' => $currentStopId, 'line' => $currentLineId);  // push
	
	// is our destination on this line?
	if (lineServicesStop($currentLineId, $destinationStopId)) {
		// yes: we've found a route, so let's log it
		$routeList[] = array('legs' => $changeStack);
	}
	
	// try to change lines:
	// iterate through all interchange stations on the current line
	foreach ($interchangeStops[$currentLineId] as $interchange) {
		
		// did we already change here for this connecting line?
		if (isChangeInStack($interchange['stop'], $interchange['line'], $changeStack)) {
			// yes: no recursion for that line now, avoiding infinite loop
			continue;
		}
		
		// no changing at current or destination stop
		if ($interchange['stop'] == $currentStopId || $interchange['stop'] == $destinationStopId) {
			continue;
		}
		
		// recurse, searching route from interchange station
		findRouteLeg($interchange['stop'], $destinationStopId, $interchange['line']);
	}
	
	array_pop($changeStack);
}


/* Routing initialiser. Call this function to
 * find routes between two stops.
 */
function findRoutes ($beginStopId, $destinationStopId) {
	
	// get access to script-wide configuration
	global $safeChangeTime;
	
	// prepare global routing state variables
	global $changeStack, $routeList;
	$changeStack = array();  // stack of line changes since start point
	$routeList = array();  // resulting list of routes from start to destination
	
	// prepare caching of database output
	global $linesServiceStops, $interchangeStops;
	$linesServiceStops = createCache('stop_has_line');
	$interchangeStops = createCache('interchanges');
	
	// retrieve list of lines servicing start point and iterate
	$result = dbisQuery("
			SELECT DISTINCT id_line
			FROM stop_has_line
			WHERE id_stop = '$beginStopId';
			");
	while ($row = dbisNextRow($result)) {
		findRouteLeg($beginStopId, $destinationStopId, $row['id_line']);
	}
	
	// prepare filtering and output
	foreach ($routeList as &$route) {
		// determine all journey times
		$journeyTime = 0;
		for ($i = 0; $i < count($route['legs']); $i++) {
			if ($i < count($route['legs']) - 1) {
				$route['legs'][$i]['to'] = $route['legs'][$i + 1]['from'];
			}
			else {
				$route['legs'][$i]['to'] = $destinationStopId;
			}
			$connection = quickestConnection($route['legs'][$i]['from'], $route['legs'][$i]['to'], $route['legs'][$i]['line']);
			$route['legs'][$i]['duration'] = $connection['duration'];
			$route['legs'][$i]['distance'] = $connection['distance'];
			$journeyTime += $connection['duration'];
		}
		$journeyTime += $safeChangeTime * (count($route['legs']) - 1);
		$route['duration'] = $journeyTime;
	}
	
	// sort the routes using the sortRoutes() comparison function
	usort($routeList, 'sortRoutes');
	
	// filter any duplicates (same total travel time and change count)
	for ($i = count($routeList) - 1; $i > 0; $i--) {
		if ($routeList[$i]['duration'] == $routeList[$i - 1]['duration'] && count($routeList[$i]['legs']) == count($routeList[$i - 1]['legs'])) {
			unset($routeList[$i]);
		}
	}
	
	return $routeList;
}


/* Comparison function for sorting routes with usort(). */
function sortRoutes ($a, $b) {
	$comparison = $a['duration'] - $b['duration'];
	if ($comparison == 0) {
		$comparison = count($a['legs']) - count($b['legs']);
	}
	return $comparison;
}


/* Finds the quickest among a selection of routes with at
 * most a given number of changes.
 */
function quickestRoute ($routes, $maxChanges) {
	$quickestRoute = NULL;
	foreach ($routes as $routeKey => $route) {
		if (($quickestRoute === NULL || $route['duration'] < $routes[$quickestRoute]['duration'])
				&& ($maxChanges === NULL || count($route['legs']) <= $maxChanges)) {
			$quickestRoute = $routeKey;
		}
	}
	return $quickestRoute;
}


/* Filters routes that are not sensible. */
function filterRoutes ($routes) {
	// find quickest routes with as few changes as possible
	$recommendedRoutes = array();
	$changes = NULL;
	while (($quickestRoute = quickestRoute($routes, $changes)) !== NULL) {
		$recommendedRoutes[] = $routes[$quickestRoute];
		$changes = count($routes[$quickestRoute]['legs']) - 1;
	}
	return $recommendedRoutes;
}


/* Output elapsed time since a given time as SGML comment. */
function printProfilingEstimate ($time0) {
	$time = @gettimeofday();
	printf("<!-- query processed in %04.2f ms -->\n", (@$time['sec'] - @$time0['sec']) * 1000 + (@$time['usec'] - @$time0['usec']) / 1000);
}


// begin route search
$startTime = @gettimeofday();
$routes = findRoutes($fromStopId, $toStopId);
printProfilingEstimate($startTime);





/****** print result ******/

if ($safeFilter) {
	$routes = filterRoutes($routes);
}

// convert IDs to names and clean up result for output
$stops = createCache('stop');
$lines = createCache('line');
$resultRoutes = array();
foreach ($routes as $route) {
	$newRoute = array();
	foreach ($route['legs'] as $leg) {
		$newLeg = array();
		$newLeg['from'] = $stops[$leg['from']];
		$newLeg['to'] = $stops[$leg['to']];
		$newLeg['line'] = '<SPAN STYLE="background-color: '.htmlspecialchars($lines[$leg['line']]['color']).'">'.htmlspecialchars($lines[$leg['line']]['name']).'</SPAN>';
		$newLeg['duration'] = $leg['duration'].' <ABBR TITLE="minutes">min</ABBR>';
		$newLeg['distance'] = sprintf('%03.1f <ABBR TITLE="kilometer">km</ABBR>', $leg['distance'] / 1000);
		$newRoute['legs'][] = $newLeg;
	}
	$newRoute['duration'] = $route['duration'];
	$resultRoutes[] = $newRoute;
}

// defend against empty results
if (count($routes) == 0) {
	header('HTTP/1.1 500 Internal Server Error');
?>
<P>No route could be found from <?php echo $safeFromStopName; ?> to <?php echo $safeToStopName; ?>.</P>
<P>(This is probably an indication of a server-side data error. Contact the service provider for assistance.)</P>
<?php
	dbisPrintMenu();
	exit();
}

// make query results into a list of tables
?>
<UL>
<?php

$columnClass = array(
		'distance' => 'numericvalue noentities',
		'duration' => 'numericvalue noentities',
		'line' => 'linename noentities');
$indent = DBIS_HTML_INDENT.DBIS_HTML_INDENT.DBIS_HTML_INDENT;

foreach ($resultRoutes as &$route) {
?>
	<LI>
		<TABLE>
			<COL><COL><COL><COL><COL>
		<THEAD>
			<TR>
				<TH SCOPE="col">From</TH>
				<TH SCOPE="col">To</TH>
				<TH SCOPE="col" ABBR="on">On Line</TH>
				<TH SCOPE="col" ABBR="for">Travel Time</TH>
				<TH SCOPE="col">Distance</TH>
			</TR>
		<TBODY>
<?php
	
	foreach ($route['legs'] as $leg) {
		dbisPrintTableRow($leg, $columnClass, $indent);
	}
	
?>
		</TABLE>
		<P><?php echo 'Total journey time ', $route['duration'], ' minute', (count($route['duration']) == 2 ) ? ', ' : 's, ', count($route['legs']) - 1, ' change', (count($route['legs']) == 2 ) ? '.' : 's.'; ?></P>
	</LI>
<?php
}
?>
</UL>

<?php

dbisPrintMenu();
?>
