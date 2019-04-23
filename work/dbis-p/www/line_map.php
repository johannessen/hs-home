<?php
/* $Id: $
 * encoding us-ascii
 * 
 * Copyright (c) 2008 Arne Johannessen, Volker von Nathusius
 * All rights reserved. See LICENSE for details.
 */

require_once('dbis.include.php');
require_once('utm_calculator.class.php');
require_once('constants.include.php');



/****** data entry and prep ******/

$isAllValuesPresent = array_key_exists('id', $_GET);

if (! $isAllValuesPresent) {
	// no particular location has been requested
	// prepare default Google Maps screen
	dbisPrintHeadGoogleMaps('Line Map');
?>
<SCRIPT TYPE="text/javascript"><!--

// display no stops, disallow view change
window.onload = function () {
	init("");
	map.removeControl(zoomControl);
	map.disableDoubleClickZoom();
	map.disableDragging();
};

// on click, do nothing
mapClickListener = function () {
};

// handle the form
function lineSelected (button) {
	if (googleMapsEnabled) {
		var cuddleIe7 = "&nbsp;";
		retrieveAndPrintUri("?id="+button.value, cuddleIe7);
	}
}

//-->
</SCRIPT>

<DIV ID="selectionmap">
	<P>Normally, an interactive map would appear in this space. However, your browser or JavaScript engine appears to be <STRONG>incompatible with Google Maps,</STRONG> or there is no connection to Google's server. Because the point of this page's query is to display polylines graphically, there is no alternative to be using Google Maps.</P>
	<P>We're sorry for the inconvenience.</P>
	<P>The latest versions of <A HREF="http://www.mozilla.com/firefox/">Firefox</A>, <A HREF="http://www.opera.com/">Opera</A>, <A HREF="http://www.apple.com/safari/">Safari</A> and <A HREF="http://www.microsoft.com/windows/products/winfamily/ie/default.mspx">Internet Explorer</A> have all been tested and found to support the Web standards used by this page's query.</P>
</DIV>

<FORM ACTION="//none.invalid/">
	<P>Select the line you want to see:</P>
	<P>
<?php

// following query fills the selection list with the necessary data for the later query
dbisQuery("SELECT id, name, color FROM line ORDER BY name;");
while ($row = dbisNextRow()) {
	$id = (int)$row['id'];
	echo DBIS_HTML_INDENT, DBIS_HTML_INDENT;
	echo '<INPUT TYPE="radio" NAME="line_selection" ID="line-', $id, '" VALUE="', $id, '" onClick="lineSelected(this)"> <LABEL FOR="line-', $id;
	echo '" CLASS="linename" STYLE="background-color: ', $row['color'], '">', htmlspecialchars($row['name']), "</LABEL><BR>\n";
}

?>
	</P>
</FORM>

<DIV ID="legendabove"></DIV>
<DIV ID="out"></DIV>
<?php
	
	dbisPrintMenu();
	exit();
}



/****** run database query ******/

// if this point is reached, then we did get a request for a particular line
// run query for this line and output result script as HTML

// input processing
$safeId = (int)@dbisHttpGet('id');
dbisQuery("SET @input = '$safeId';");
$colorResult = dbisNextRow(dbisQuery("
		SELECT color
		FROM line
		WHERE id = @input;
		"));

// defend against empty results
if (dbisNumRows() <= 0) {
	trigger_error('Line ID "'.$safeId.'" does not exist', E_USER_ERROR);
}

// run the query
dbisQuery("
		SELECT stop.utm_e AS utm_e, stop.utm_n AS utm_n
		FROM stop
		INNER JOIN stop_has_line ON stop.id = stop_has_line.id_stop
		WHERE stop_has_line.id_line = @input
		ORDER BY stop_has_line.`order`;
		");



/****** show result on map ******/

?>

<SCRIPT TYPE="text/javascript">
var lineData = [];
<?php
$calculator = new UtmCalculator;
while ($row = dbisNextRow()) {
	$geographic = $calculator->toGeographic($row['utm_e'], $row['utm_n'], DBIS_UTM_ZONE);
	printf("lineData.push(new GLatLng(%08.6f, %08.6f));\n", $geographic['latitude'], $geographic['longitude']);
}
?>

map.clearOverlays();
map.addOverlay(new GPolyline(lineData, "#ffffff", 10, .75));  // outline
map.addOverlay(new GPolyline(lineData, "<?php echo $colorResult['color']; ?>", 6, 1));  // line
// :BUG: named colors degrade gracefully to blue if neither SVG nor VML is supported
// for workaround see <http://www.oreillynet.com/pub/a/javascript/excerpt/JSDHTMLCkbk_chap5/index5.html>
</SCRIPT>
