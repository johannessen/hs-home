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



/****** prep ******/

$isAllValuesPresent = array_key_exists('data', $_GET) && @dbisHttpGet('data') == 1;

if (! $isAllValuesPresent) {
	// data has not been requested
	// prepare default Google Maps screen
	dbisPrintHeadGoogleMaps('Points of Interest in Singapore');
?>
<SCRIPT TYPE="text/javascript"><!--

// display no stops or anything, but load POIs
window.onload = function () {
	init("");
	if (googleMapsEnabled) {
		retrieveAndPrintUri("?data=1", "Click on a marker to see more information!");
		map.addMapType(G_PHYSICAL_MAP);
		map.removeControl(zoomControl);
		map.addControl(new GLargeMapControl());
		map.addControl(new GMapTypeControl());
	}
};

// on click, do nothing
mapClickListener = function () {
};

//-->
</SCRIPT>

<DIV ID="selectionmap" CLASS="maponly">
	<P>Normally, an interactive map would appear in this space. However, your browser or JavaScript engine appears to be <STRONG>incompatible with Google Maps,</STRONG> or there is no connection to Google's server. Because the point of this page's query is to display a location map, there is no alternative to be using Google Maps.</P>
	<P>We're sorry for the inconvenience.</P>
	<P>The latest versions of <A HREF="http://www.mozilla.com/firefox/">Firefox</A>, <A HREF="http://www.opera.com/">Opera</A>, <A HREF="http://www.apple.com/safari/">Safari</A> and <A HREF="http://www.microsoft.com/windows/products/winfamily/ie/default.mspx">Internet Explorer</A> have all been tested and found to support the Web standards used by this page's query.</P>
</DIV>
<DIV ID="out"></DIV>
<P ID="legendabove"></P>
<?php
	
	dbisPrintMenu();
	exit();
}



/****** run database query ******/

// run the query
dbisQuery("
		SELECT name, type, utm_e, utm_n
		FROM poi;
		");

// defend against empty results
if (dbisNumRows() <= 0) {
	trigger_error('No POIs could be found in the database', E_USER_ERROR);
}



/****** show result on map ******/

?>
<SCRIPT TYPE="text/javascript">

function createPoiMarker (marker) {
	
	var options = {title: marker.name, icon: marker.icon, clickable: true};
	var position = new GLatLng(marker.latitude, marker.longitude);
	var overlay = new GMarker(position, options);
	map.addOverlay(overlay);
	
	GEvent.addListener(overlay, "click", function() {
		var info = "<P><STRONG>"+marker.name+"<\/STRONG><\/P><P>"+marker.type+"<\/P>";
		map.openInfoWindowHtml(position, info);
	});

}

if (googleMapsEnabled) {
	var markerIcon = new MarkerIcon("pin+shadow");
	
<?php
$calculator = new UtmCalculator;
while ($row = dbisNextRow()) {
	$geographic = $calculator->toGeographic($row['utm_e'], $row['utm_n'], DBIS_UTM_ZONE);
	echo DBIS_HTML_INDENT, 'createPoiMarker({name: "', addcslashes($row['name'], '"/');
	echo '", icon: markerIcon, type: "', addcslashes($row['type'], '"/'), '", ';
	printf("latitude: %08.6f, longitude: %08.6f});\n", $geographic['latitude'], $geographic['longitude']);
}
?>
}

</SCRIPT>
