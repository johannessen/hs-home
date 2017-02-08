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

$isAllValuesPresent = array_key_exists('poi', $_GET);
$unsafePoi = @dbisHttpGet('poi');
$safePoi = htmlspecialchars($unsafePoi);

// print page title and header
if ($isAllValuesPresent) {
	dbisPrintHead('Points of interest matching "'.$safePoi.'"');
}
else {
	dbisPrintHead('Search points of interest by text');
}
?>

<FORM METHOD="GET" ACTION="">
	<LABEL>Type in the Point of Interest name or type you want to show</LABEL><BR>
	<INPUT TYPE="text" NAME="poi" VALUE="<?php echo $safePoi; ?>">
	<INPUT TYPE="submit" VALUE="Go!">
</FORM>

<?php

// only proceed with the actual DB query if we have all necessary info
if (! $isAllValuesPresent) {
	dbisPrintMenu();
	exit();
}



/****** database query ******/

// input processing
$poi = dbisEscape($unsafePoi, TRUE);
dbisQuery("SET @input = '%$poi%';");

// run the query
dbisQuery("
		SELECT name, type, utm_e, utm_n
		FROM poi
		WHERE type LIKE @input OR name LIKE @input
		ORDER BY name ASC;
		");



/****** print result ******/

// defend against empty results
if (dbisNumRows() <= 0) {
?>
<P>Sorry, no point of interest matches your query.</P>
<?php
	dbisPrintMenu();
	exit();
}

// make query results into a table
?>
<TABLE>
	<COL><COL><COL><COL>
<THEAD>
	<TR>
		<TH SCOPE="col" ABBR="Sight">Name of Sight</TH>
		<TH SCOPE="col">Type</TH>
		<TH SCOPE="col" ABBR="Map">Show Map</TH>
	</TR>
<TBODY>
<?php

$columnClass = array(
		'maplink' => 'noentities');

while ($row = dbisNextRow()) {
	convertCoordinatesToMapLink($row, 'utm_e', 'utm_n');
	dbisPrintTableRow($row, $columnClass);  // output one table row
}
?>
</TABLE>
<?php

dbisPrintMenu();
?>