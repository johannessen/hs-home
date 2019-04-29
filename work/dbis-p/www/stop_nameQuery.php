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

$isAllValuesPresent = array_key_exists('stop', $_GET);
$unsafeStop = @dbisHttpGet('stop');
$safeStop = htmlspecialchars($unsafeStop);

// print page title and header
if ($isAllValuesPresent) {
	dbisPrintHead('Search for a stop by first letters "'.$safeStop.'"');
}
else {
	dbisPrintHead('Search for a stop by first letters');
}
?>

<FORM METHOD="GET" ACTION="">
	<LABEL>Type in the beginning letters of the searched stop:</LABEL><BR>
	<INPUT TYPE="text" NAME="stop" VALUE="<?php echo $safeStop; ?>">
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
$stop = dbisEscape($unsafeStop);
dbisQuery("SET @input = '$stop' '%';");

// run the query
dbisQuery("
		SELECT DISTINCT s.name AS name, l.name as linename, l.color as linecolor, s.utm_e AS utm_e, s.utm_n AS utm_n
		FROM stop s, line l, stop_has_line shl
		WHERE s.name LIKE @input AND l.id = shl.id_line AND s.id = shl.id_stop
		ORDER BY s.name;
		");



/****** print result ******/

// defend against empty results
if (dbisNumRows() <= 0) {
?>
<P>Sorry, no stops begin with the letters "<?php echo $safeStop; ?>".</P>
<?php
	dbisPrintMenu();
	exit();
}

// make query results into a table
?>
<TABLE>
	<COL><COL><COL>
<THEAD>
	<TR>
		<TH SCOPE="col" ABBR="Stop">Name of Stop</TH>
		<TH SCOPE="col" ABBR="Line">Name of Line</TH>
		<TH SCOPE="col">Map</TH>
	</TR>
<TBODY>
<?php

$columnClass = array(
		'linename' => 'linename noentities',
		'maplink' => 'noentities');

while ($row = dbisNextRow()) {
	$row['linename'] = '<SPAN STYLE="background-color: '.$row['linecolor'].'">'.$row['linename'].'</SPAN>';
	unset($row['linecolor']);
	convertCoordinatesToMapLink($row, 'utm_e', 'utm_n');
	dbisPrintTableRow($row, $columnClass);  // output one table row
}
?>
</TABLE>
<?php

dbisPrintMenu();
?>
