<?php
/* $Id: $
 * encoding us-ascii
 * 
 * Copyright (c) 2008 Arne Johannessen, Volker von Nathusius
 * All rights reserved. See LICENSE for details.
 */

require_once('dbis.include.php');

dbisPrintHead('Total length of each line');



/****** database query ******/

dbisQuery("
		SELECT line.name AS linename, MAX(stop_has_line.distance) AS distance, line.color AS linecolor
		FROM stop_has_line, line
		WHERE line.id = stop_has_line.id_line
		GROUP BY line.name
		ORDER BY distance DESC;
		");

// defend against empty results
if (dbisNumRows() <= 0) {
	trigger_error('No lines could be found in the database', E_USER_ERROR);
}



/****** print result ******/

// make query results into a table
?>
<TABLE>
	<COL><COL>
<THEAD>
	<TR>
		<TH SCOPE="col" ABBR="Line">Name of Line</TH>
		<TH SCOPE="col" ABBR="Length">Total Length</TH>
	</TR>
<TBODY>
<?php

$columnClass = array(
		'linename' => 'linename noentities',
		'distance' => 'numericvalue noentities');

while ($row = dbisNextRow()) {
	$row['linename'] = '<SPAN STYLE="background-color: '.htmlspecialchars($row['linecolor']).'">'.htmlspecialchars($row['linename']).'</SPAN>';
	unset($row['linecolor']);
	$row['distance'] .= ' <ABBR TITLE="meter">m</ABBR>';
	dbisPrintTableRow($row, $columnClass);  // output one table row
}
?>
</TABLE>
<?php

dbisPrintMenu();
?>