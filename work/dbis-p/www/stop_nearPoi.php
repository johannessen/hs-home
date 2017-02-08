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

$isAllValuesPresent = array_key_exists('poi_selection', $_GET);
$unsafePoi = @dbisHttpGet('poi_selection');
$safePoi = htmlspecialchars($unsafePoi);

// print page title and header
if ($isAllValuesPresent) {
	dbisPrintHead('Your Chosen PoI: '.$_GET["poi_selection"]);
}
else {
	dbisPrintHead('PoI information form');
}
?>

<FORM METHOD="GET" ACTION=""><P>
	<LABEL>Select a Point of Interest to show the nearest stops<BR>
	<SELECT NAME="poi_selection" SIZE="1" onChange="submit()">
<?php

// following query fills the selection menu with the necessary data for the later query
dbisQuery("SELECT name FROM poi ORDER BY name;");
while ($row = dbisNextRow()) {
	$name = htmlspecialchars($row['name']);
	echo DBIS_HTML_INDENT, DBIS_HTML_INDENT, '<OPTION VALUE="', $name;
	if ($safePoi == $name) {
		echo '" SELECTED>';
	}
	else {
		echo '">';
	}
	echo $row['name'];
	echo "</OPTION>\n";
}

?>
	</SELECT></LABEL>
	<INPUT TYPE='submit' VALUE='Go!'>
</P></FORM>
<?php

// only proceed with the actual DB query if we have all necessary info
if (! $isAllValuesPresent) {
	dbisPrintMenu();
	exit();
}



/****** database query ******/

// input processing
$poi = dbisEscape($unsafePoi);
dbisQuery("SET @input = '$poi';");

/*
$result = dbisQuery("
SELECT * FROM poi WHERE	poi.name = @input;
");
*/

// run the query
dbisQuery("
		SELECT @input AS poiname, stop.name AS stopname, round(sqrt(POW((stop.utm_n - (SELECT utm_n FROM poi WHERE name = @input)), 2) + POW(stop.utm_e - (SELECT utm_e FROM poi WHERE name = @input), 2))) AS beeline, utm_e, utm_n
		FROM stop
		ORDER BY beeline
		LIMIT 5;
		");

// defend against empty results
if (dbisNumRows() <= 0) {
	trigger_error('POI "'.$safePoi.'" does not exist', E_USER_ERROR);
}



/****** print result ******/

// put query results into a table
?>
<TABLE>
	<COL><COL><COL><COL>
<THEAD>
	<TR>
		<TH SCOPE="col" ABBR="Sight">Name of Sight</TH>
		<TH SCOPE="col" ABBR="Stop">Name of Stop</TH>
		<TH SCOPE="col">Beeline</TH>
		<TH SCOPE="col" ABBR="Map">Show Map</TH>
	</TR>
<TBODY>
<?php

$columnClass = array(
		'beeline' => 'numericvalue noentities',
		'maplink' => 'noentities');

while ($row = dbisNextRow()) {
	$row['beeline'] .= ' <ABBR TITLE="meter">m</ABBR>';
	convertCoordinatesToMapLink($row, 'utm_e', 'utm_n');
	dbisPrintTableRow($row, $columnClass);  // output one table row
}
?>
</TABLE>

<?php
dbisPrintMenu();
?>