<?php
/* $Id: $
 * encoding us-ascii
 * 
 * Copyright (c) 2008 Arne Johannessen, Volker von Nathusius
 * All rights reserved. See LICENSE for details.
 */

require_once('dbis.include.php');
require_once('maplink.include.php');
require_once('utm_calculator.class.php');
require_once('constants.include.php');



/****** data entry and prep ******/

$isAllValuesPresent = array_key_exists('latitude', $_GET) && array_key_exists('longitude', $_GET);

if (! $isAllValuesPresent) {
	// no particular location has been requested
	// prepare default Google Maps screen
	dbisPrintHeadGoogleMaps('Stops near an arbitrary location');
?>
<SCRIPT TYPE="text/javascript"><!--
var clickOverlay;

// display no stops to begin with
window.onload = function () {
	init("Click on the map to choose a location!");
};

// on click, just run the SQL query instead of the stop-selection routine
mapClickListener = function (overlay, point) {
	if (point) {
		var resultCaption = "<P>Stops near your location: <IMG SRC=\"images/pin.png\" WIDTH=\"22\" HEIGHT=\"20\" ALT=\"\"><\/P>";
		retrieveAndPrintUri("?latitude="+point.lat()+"&longitude="+point.lng(), resultCaption);
		
		// show a marker to indicate click position
		if (clickOverlay) {
			map.removeOverlay(clickOverlay);
		}
		clickOverlay = new GMarker(point, new MarkerOptions(undefined, new MarkerIcon("pin"), 5));
		map.addOverlay(clickOverlay);
	}
};

//-->
</SCRIPT>

<P ID="legendabove"></P>
<DIV ID="selectionmap">
	<P>Normally, an interactive map would appear in this space. However, your browser or JavaScript engine appears to be <STRONG>incompatible with Google Maps,</STRONG> or there is no connection to Google's server. Because this page's query requires you to select a random location by clicking onto a map, there is no alternative to be using Google Maps.</P>
	<P>We're sorry for the inconvenience.</P>
	<P>The latest versions of <A HREF="http://www.mozilla.com/firefox/">Firefox</A>, <A HREF="http://www.opera.com/">Opera</A>, <A HREF="http://www.apple.com/safari/">Safari</A> and <A HREF="http://www.microsoft.com/windows/products/winfamily/ie/default.mspx">Internet Explorer</A> have all been tested and found to support the Web standards used by this page's query.</P>
</DIV>
<DIV ID="out"></DIV>
<?php
	
	dbisPrintMenu();
	exit();
}



/****** run database query ******/

// if this point is reached, then we did get a request for a particular location
// run query for this stop and output results as HTML

// input processing
// we have a lat/lon position as input value, so convert it to UTM for the DB query
$calculator = new UtmCalculator;
$utm = $calculator->toUtm((double)dbisHttpGet('latitude'), (double)dbisHttpGet('longitude'));
dbisQuery("
		SET @easting = '".$utm['easting']."';
		");
dbisQuery("
		SET @northing = '".$utm['northing']."';
		");
dbisQuery("
		SET @range = 2500;
		");

// run the query
dbisQuery("
		SELECT *
		FROM (
			SELECT stop.utm_e AS easting, stop.utm_n AS northing, stop.name AS name, round(sqrt(POW((stop.utm_n - @northing), 2) + POW(stop.utm_e - @easting, 2))) AS beeline
			FROM stop
			ORDER BY beeline
			) AS beeline_all_stops
		WHERE beeline <= @range;
		");



/****** print result ******/

// defend against empty results
if (dbisNumRows() <= 0) {
?>
<P>There are no MRT stops nearby to where you clicked.</P>
<SCRIPT TYPE="text/javascript">
if (googleMapsEnabled) {
	clearResultData();
}
</SCRIPT>
<?php
	exit();
}

// put query results into a table
?>
<TABLE>
	<COL><COL><COL>
<THEAD>
	<TR>
		<TH SCOPE="col" ABBR="Stop">Name of stop</TH>
		<TH SCOPE="col">Distance</TH>
		<TH SCOPE="col" ABBR="Map">Show map</TH>
	</TR>
<TBODY>
<?php

$columnClass = array(
		'beeline' => 'numericvalue noentities',
		'maplink' => 'noentities');
$resultStops = array();

while ($row = dbisNextRow()) {
	$resultStops[] = $row;
	
	$row['beeline'] .= ' <ABBR TITLE="meter">m</ABBR>';
	convertCoordinatesToMapLink($row, 'easting', 'northing');
	dbisPrintTableRow($row, $columnClass);  // output one table row
}
?>
</TABLE>
<?php



/****** show result on map ******/

?>

<SCRIPT TYPE="text/javascript">
if (googleMapsEnabled) {
	
	var markerData = [];
<?php
foreach ($resultStops as $stop) {
	$geographic = $calculator->toGeographic($stop['easting'], $stop['northing'], DBIS_UTM_ZONE);
	echo DBIS_HTML_INDENT, 'markerData.push({name: "', htmlspecialchars($stop['name']), '", ';
	printf("latitude: %08.6f, longitude: %08.6f});\n", $geographic['latitude'], $geographic['longitude']);
}
?>
	
	displayResultData(markerData);
}
</SCRIPT>
