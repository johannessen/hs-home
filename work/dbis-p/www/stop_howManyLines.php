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

// print page title and header
dbisPrintHead('List of interchange stops');

?>
<FORM METHOD="GET" ACTION="">
	<P>The list below only lists stops serviced by more than one line per default.
	<BR><LABEL>
<?php 
echo DBIS_HTML_INDENT, DBIS_HTML_INDENT, DBIS_HTML_INDENT;
echo 'If you want to show all, check this field:&nbsp;<INPUT TYPE="checkbox" NAME="showAll" onChange="submit()" ';
if(array_key_exists('showAll', $_GET)) {
	echo "CHECKED>";
}
else {
	echo ">";
}
?>
	</LABEL>
	<INPUT TYPE="submit" VALUE="Go!">
</FORM>

<?php



/****** database query ******/

if(array_key_exists('showAll', $_GET)) {
	dbisQuery("
			SELECT DISTINCT stop.name AS name, line_count, line.name AS line_name, line.color AS line_color, stop.utm_e AS utm_e, stop.utm_n AS utm_n
			FROM stop_has_line, line, stop, (
				SELECT shl.id_stop AS stop_id, COUNT(*) AS line_count
				FROM line, (
					SELECT DISTINCT id_stop, id_line
					FROM stop_has_line
					) AS shl
				WHERE line.id = shl.id_line
				GROUP BY shl.id_stop
				ORDER BY COUNT(*) DESC
				) AS lines_per_station
			WHERE stop.id = stop_id AND id_stop = stop_id AND id_line = line.id
			ORDER BY line_count DESC, name ASC;
			");
}
else {
	
	dbisQuery("
			SELECT DISTINCT stop.name AS name, line_count, line.name AS line_name, line.color AS line_color, stop.utm_e AS utm_e, stop.utm_n AS utm_n
			FROM stop_has_line, line, stop, (
				SELECT shl.id_stop AS stop_id, COUNT(*) AS line_count
				FROM line, (
					SELECT DISTINCT id_stop, id_line
					FROM stop_has_line
					) AS shl
				WHERE line.id = shl.id_line
				GROUP BY shl.id_stop
				ORDER BY COUNT(*) DESC
				) AS lines_per_station
			WHERE stop.id = stop_id AND id_stop = stop_id AND id_line = line.id AND line_count > 1
			ORDER BY line_count DESC, name ASC;
			");
}

// defend against empty results
if (dbisNumRows() <= 0) {
	trigger_error('No interchange stops could be found in the database', E_USER_ERROR);
}



/****** print result ******/

// make query results into a table
?>
<TABLE>
	<COL><COL><COL>
<THEAD>
	<TR>
		<TH SCOPE="col" ABBR="Stop">Name of Stop</TH>
		<TH SCOPE="col" ABBR="Count">Number of Lines</TH>
		<TH SCOPE="col" ABBR="Line">Name of Line</TH>
		<TH SCOPE="col">Map</TH>
	</TR>
<TBODY>
<?php

$columnClass = array(
		'line_count' => 'numericvalue',
		'line_name' => 'linename noentities',
		'maplink' => 'noentities');

while ($row = dbisNextRow()) {
	$row['line_name'] = '<SPAN STYLE="background-color: '.htmlspecialchars($row['line_color']).'">'.htmlspecialchars($row['line_name']).'</SPAN>';
	unset($row['line_color']);
	convertCoordinatesToMapLink($row, 'utm_e', 'utm_n');
	
	// :TODO: HIER wäre es sehr schick, wenn wir noch die Tabelle so modifizieren, dass nicht in jeder Zeile wieder dasselbe Steht!
	
	dbisPrintTableRow($row, $columnClass);  // output one table row
}
?>
</TABLE>
<?php

dbisPrintMenu();
?>
