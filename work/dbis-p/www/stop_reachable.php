<?php
/* $Id: $
 * encoding us-ascii
 * 
 * Copyright (c) 2008 Arne Johannessen, Volker von Nathusius
 * All rights reserved. See LICENSE for details.
 */

require_once('dbis.include.php');
require_once('maplink.include.php');
require_once('constants.include.php');



/****** data entry and prep ******/

$isAllValuesPresent = array_key_exists('stopname', $_GET);
$noScript = array_key_exists('noscript', $_GET) && dbisHttpGet('noscript') == 1;

$safeStopName = htmlspecialchars(@dbisHttpGet('stopname'));

if (! $isAllValuesPresent || $noScript) {
	// no particular stop has been requested
	// prepare default Google Maps screen
	dbisPrintHeadGoogleMaps('Stops reachable without a change');
?>

<P ID="legendabove"></P>
<DIV ID="selectionmap">
	<FORM ACTION="" METHOD="GET">
		<P><LABEL>Name of stop: <INPUT TYPE="text" NAME="stopname" VALUE="<?php echo $safeStopName; ?>"></LABEL></P>
		<P><INPUT TYPE="submit" VALUE="Go!"><INPUT TYPE="hidden" NAME="noscript" VALUE="1">
	</FORM>
	<P>Normally, an interactive map would appear in this space. However, your browser or JavaScript engine appears to be <STRONG>incompatible with Google Maps,</STRONG> or there is no connection to Google's server. Therefore we offer the simple Web form above as alternative means to run this page's query.</P>
	<P>Note that you have to enter the stop's name using the correct spelling here. You may mix case as you like though.</P>
</DIV>
<DIV ID="out"></DIV>

<?php
}



/****** run database query ******/

if ($isAllValuesPresent) {
	// we did get a request for one particular stop
	// run query for this stop and output results as HTML
	
	// input processing
	dbisQuery("SET @input = '".dbisEscape(dbisHttpGet('stopname'))."';");
	
	// fast fail if the user manually gave us a nonexistent name
	if (dbisNumRows(dbisQuery("SELECT * FROM stop WHERE name = @input;")) <= 0) {
?>
<P>No stop named "<?php echo $safeStopName; ?>" could be found.</P>
<?php
		if ($noScript) {
			dbisPrintMenu();
		}
		exit();
	}
	
	// run the query
	dbisQuery("SET @sid = (SELECT id FROM stop WHERE name = @input);");
	dbisQuery("
			SELECT DISTINCT stop.name AS name, line.name AS linename, line.color AS color, stop.utm_e AS utm_e, stop.utm_n AS utm_n
			FROM stop_has_line, stop, line
			WHERE stop_has_line.id_stop = stop.id AND @sid <> stop.id AND stop_has_line.id_line = line.id AND stop_has_line.id_line IN (
				SELECT l.id
				FROM line l, stop_has_line shl
				WHERE l.id = shl.id_line AND shl.id_stop = @sid
				)
			ORDER BY stop.name ASC;
			");
	
	
	
	/****** print result ******/
	
	// defend against empty results
	if (dbisNumRows() <= 0) {
?>
<P>No MRT stops are reachable without a change starting your journey from the selected station.</P>
<P>(This is probably an indication of a server-side data error. Contact the service provider for assistance.)</P>
<?php
		exit();
	}
	
	// put query results into a table
?>
<TABLE>
	<COL><COL><COL>
<THEAD>
	<TR>
		<TH SCOPE="col" ABBR="Stop">Directly reachable from <?php echo $safeStopName; ?></TH>
		<TH SCOPE="col" ABBR="Line">With Line</TH>
		<TH SCOPE="col">Map</TH>
	</TR>
<TBODY>
<?php
	
	$columnClass = array(
			'linename' => 'linename noentities',
			'maplink' => 'noentities');
	$resultStops = array();
	
	while ($row = dbisNextRow()) {
		$resultStops[] = $row;
		
		$row['linename'] = '<SPAN STYLE="background-color: '.htmlspecialchars($row['color']).'">'.htmlspecialchars($row['linename']).'</SPAN>';
		unset($row['color']);
		convertCoordinatesToMapLink($row, 'utm_e', 'utm_n');
		dbisPrintTableRow($row, $columnClass);  // output one table row
	}
?>
</TABLE>
<?php
	
	
	
	/****** show result on map ******/
	
	if (! $noScript) {
?>

<SCRIPT TYPE="text/javascript">
if (googleMapsEnabled) {
	
	var markerData = [];
<?php
		require_once('utm_calculator.class.php');
		$calculator = new UtmCalculator;
		
		$i = 0;
		foreach ($resultStops as $stop) {
			$geographic = $calculator->toGeographic($stop['utm_e'], $stop['utm_n'], DBIS_UTM_ZONE);
			echo DBIS_HTML_INDENT, 'markerData[', $i, '] = {name: "', htmlspecialchars($stop['name']), '", ';
			printf("latitude: %08.6f, longitude: %08.6f};\n", $geographic['latitude'], $geographic['longitude']);
			$i++;
		}
?>
	
	displayResultData(markerData);
}
</SCRIPT>
<?php
	}
}


if (! $isAllValuesPresent || $noScript) {
dbisPrintMenu();
}

?>
