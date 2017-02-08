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

$isAllValuesPresent = array_key_exists('line_selection', $_GET);
$unsafeLine = @dbisHttpGet('line_selection');
$safeLine = htmlspecialchars($unsafeLine);

// print page title and header
if ($isAllValuesPresent) {
	dbisPrintHead('Your chosen line: '.$_GET["line_selection"]);
}
else {
	dbisPrintHead('line selection form');
}
?>

<FORM METHOD="GET" ACTION=""><P>
	<LABEL>Select the line you want to show<BR>
	<SELECT NAME="line_selection" SIZE="1" onChange="submit()">
<?php

// following query fills the selection menu with the necessary data for the later query
dbisQuery("SELECT name FROM line ORDER BY name;");
while ($row = dbisNextRow()) {
	$name = htmlspecialchars($row['name']);
	echo DBIS_HTML_INDENT, DBIS_HTML_INDENT, '<OPTION VALUE="', $name;
	if ($safeLine == $name) {
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
$line = dbisEscape($unsafeLine);
dbisQuery("SET @input = '$line';");

// run the query
dbisQuery("
		SELECT stop_has_line.`order` + 1 as `order`, stop.name AS stopname, line.name AS linename, line.color as linecolor, stop_has_line.distance as distance, stop_has_line.duration as duration, stop.utm_e AS utm_e, stop.utm_n AS utm_n
		FROM stop_has_line, stop, line
		WHERE line.id=stop_has_line.id_line AND stop.id=stop_has_line.id_stop AND line.id = (
			SELECT id
			FROM line
			WHERE name = @input OR id = @input
			)
		ORDER BY stop_has_line.`order`;
		");

// defend against empty results
if (dbisNumRows() <= 0) {
	trigger_error('Line "'.$safeLine.'" does not exist', E_USER_ERROR);
}



/****** print result ******/

// make query results into a table
?>
<TABLE>
	<COL><COL><COL><COL><COL><COL>
<THEAD>
	<TR>
		<TH></TH>
		<TH SCOPE="col" ABBR="Stop">Name of Stop</TH>
		<TH SCOPE="col" ABBR="Line">Name of Line</TH>
		<TH SCOPE="col">Distance</TH>
		<TH SCOPE="col">Duration</TH>
		<TH SCOPE="col">Map</TH>
	</TR>
<TBODY>
<?php

$columnClass = array(
		'order' => 'numericvalue',
		'distance' => 'numericvalue noentities',
		'duration' => 'numericvalue noentities',
		'linename' => 'linename noentities',
		'maplink' => 'noentities');

while ($row = dbisNextRow()) {
	$row['linename'] = '<SPAN STYLE="background-color: '.htmlspecialchars($row['linecolor']).'">'.htmlspecialchars($row['linename']).'</SPAN>';
	unset($row['linecolor']);
	$row['distance'] .= ' <ABBR TITLE="meter">m</ABBR>';
	$row['duration'] .= ' <ABBR TITLE="minutes">min</ABBR>';
	convertCoordinatesToMapLink($row, 'utm_e', 'utm_n');
	dbisPrintTableRow($row, $columnClass);  // output one table row
}
?>
</TABLE>

<?php
dbisPrintMenu();
?>