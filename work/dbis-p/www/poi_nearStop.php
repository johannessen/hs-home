<?php
/* $Id: $
 * encoding us-ascii
 * 
 * Copyright (c) 2008 Arne Johannessen, Volker von Nathusius
 * All rights reserved. See LICENSE for details.
 */

require_once('dbis.include.php');
require_once('maplink.include.php');



/****** data entry and prep ******/

$isAllValuesPresent = array_key_exists('stopname', $_GET);
$noScript = array_key_exists('noscript', $_GET) && dbisHttpGet('noscript') == 1;

$safeStopName = htmlspecialchars(@dbisHttpGet('stopname'));

if (! $isAllValuesPresent || $noScript) {
	// no particular stop has been requested
	// prepare default Google Maps screen
	
	dbisPrintHeadGoogleMaps('Points of Interest in the Vicinity of a Stop');
?>
<SCRIPT SRC="stop-selection.js" TYPE="text/javascript"></SCRIPT>

<P ID="legendabove"></P>
<DIV ID="selectionmap">
	<FORM ACTION="" METHOD="GET">
		<P><LABEL>Name of stop: <INPUT TYPE="text" NAME="stopname" VALUE="<?php echo $safeStopName; ?>"></LABEL></P>
		<P><INPUT TYPE="submit" VALUE="Go!"><INPUT TYPE="hidden" NAME="noscript" VALUE="1">
	</FORM>
	<P>Normally, an interactive map would appear in this space. However, your browser or JavaScript engine appears to be <STRONG>incompatible with Google Maps,</STRONG> or there is no connection to Google's server. Therefore we offer the simple Web form above as alternative means to run this page's query.</P>
	<P>Note that you have to enter the stop's name using the correct spelling here. You may mix case as you like though.</P>
</DIV>
<P CLASS="tip">Note: Most points of interest are downtown.</P>
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
	dbisQuery("SET @stop_utm_n = (SELECT utm_n FROM stop WHERE name = @input);");
	dbisQuery("SET @stop_utm_e = (SELECT utm_e FROM stop WHERE name = @input);");
	dbisQuery("SET @distance_limit = 1000;");
	dbisQuery("
			SELECT poi.name AS name, poi.type AS type, round(sqrt(POW((poi.utm_n - @stop_utm_n), 2) + POW(poi.utm_e - @stop_utm_e, 2))) AS distance, poi.utm_e AS utm_e, poi.utm_n AS utm_n
			FROM poi
			WHERE round(sqrt(POW((poi.utm_n - @stop_utm_n), 2) + POW(poi.utm_e - @stop_utm_e, 2))) < @distance_limit
			ORDER BY distance ASC;
			");
	
	
	
	/****** print result ******/
	
	// defend against empty results
	if (dbisNumRows() <= 0) {
?>
<P>There are no Points of Interest nearby <?php echo $safeStopName; ?>.</P>
<?php
		if ($noScript) {
			dbisPrintMenu();
		}
		exit();
	}
	
	// put query results into a table
?>
<TABLE>
	<COL><COL><COL><COL>
<THEAD>
	<TR>
		<TH SCOPE="col" ABBR="Point">Points of Interest near <?php echo $safeStopName; ?></TH>
		<TH SCOPE="col">Kind</TH>
		<TH SCOPE="col">Distance</TH>
		<TH SCOPE="col">Map</TH>
	</TR>
<TBODY>
<?php
	
	$columnClass = array(
			'distance' => 'numericvalue noentities',
			'maplink' => 'noentities');
	
	while ($row = dbisNextRow()) {
		$row['distance'] .= ' <ABBR TITLE="meter">m</ABBR>';
		convertCoordinatesToMapLink($row, 'utm_e', 'utm_n');
		dbisPrintTableRow($row, $columnClass);  // output one table row
	}
?>
</TABLE>
<?php
}


if (! $isAllValuesPresent || $noScript) {
	dbisPrintMenu();
}

?>
