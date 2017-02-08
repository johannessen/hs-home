<?php
/* $Id: $
 * encoding us-ascii
 * 
 * Copyright (c) 2008 Arne Johannessen, Volker von Nathusius
 * All rights reserved. See LICENSE for details.
 */

require_once('dbis.include.php');

dbisPrintHead('Total number of stops of each line');



/****** database query ******/

dbisQuery("
		SELECT line.name AS linename, COUNT(*) AS number, line.color AS linecolor
		FROM stop_has_line, line
		WHERE line.id = stop_has_line.id_line
		GROUP BY id_line
		ORDER BY count(*) DESC;
		");

// defend against empty results
if (dbisNumRows() <= 0) {
	trigger_error('No stops with lines could be found in the database', E_USER_ERROR);
}



/****** print result ******/

// make query results into a table
?>
<TABLE>
	<COL><COL>
<THEAD>
	<TR>
		<TH SCOPE="col" ABBR="Line">Name of Line</TH>
		<TH SCOPE="col" ABBR="Stops">Number of Stops</TH>
	</TR>
<TBODY>
<?php

$columnClass = array(
		'linename' => 'linename noentities',
		'number' => 'numericvalue noentities');

while ($row = dbisNextRow()) {
	$row['linename'] = '<SPAN STYLE="background-color: '.htmlspecialchars($row['linecolor']).'">'.htmlspecialchars($row['linename']).'</SPAN>';
	unset($row['linecolor']);
	dbisPrintTableRow($row, $columnClass);  // output one table row
}
?>
</TABLE>
<?php

dbisPrintMenu();
?>