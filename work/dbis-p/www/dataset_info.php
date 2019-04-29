<?php
/* $Id: $
 * encoding us-ascii
 * 
 * Copyright (c) 2008 Arne Johannessen, Volker von Nathusius
 * All rights reserved. See LICENSE for details.
 */

require_once('dbis.include.php');



/****** data entry and prep ******/

$isAllValuesPresent = array_key_exists('dataset', $_GET);

$unsafeDataset = @dbisHttpGet('dataset');
$safeDataset = htmlspecialchars($unsafeDataset);

if ($isAllValuesPresent) {
	// prevent SQL injections: allow only predefined table names
	$legalDatasetValues = array('line', 'stop', 'poi', 'stop_has_line');
	if (! in_array($unsafeDataset, $legalDatasetValues)) {
		trigger_error('"'.$safeDataset.'" is not a legal dataset name', E_USER_ERROR);
	}
	
	// print page title and header
	dbisPrintHead('Dataset '.$safeDataset);
}
else {
	// print page title and header
	dbisPrintHead('Dataset selection form');
}
?>

<FORM METHOD="GET" ACTION="">
	<P>Select the raw dataset you want</P>
	<P>
		<LABEL><INPUT type="radio" 
			<?php if ('line' == $unsafeDataset) { echo "CHECKED "; } ?> NAME="dataset" VALUE="line"> Lines</LABEL><BR>
		<LABEL><INPUT type="radio" 
			<?php if ('stop' == $unsafeDataset) { echo "CHECKED "; } ?> NAME="dataset" VALUE="stop"> Stops</LABEL><BR>
		<LABEL><INPUT type="radio"
			<?php if ('poi' == $unsafeDataset) { echo "CHECKED "; } ?> NAME="dataset" VALUE="poi"> Points of Interest</LABEL><BR>
		<LABEL><INPUT type="radio"
			<?php if ('stop_has_line' == $unsafeDataset) { echo "CHECKED "; } ?> NAME="dataset" VALUE="stop_has_line"> Stop-Line-Korrelation</LABEL><BR>
		<INPUT TYPE="submit" VALUE="Show raw dataset">
	</P>
</FORM>

<?php

// only proceed with the actual DB query if we have all necessary info
if (! $isAllValuesPresent) {
	dbisPrintMenu();
	exit();
}



/****** database query ******/

$dataset = dbisEscape($unsafeDataset);
dbisQuery("
		SELECT * FROM `$dataset`;
		");



/****** print result ******/

// put query results into a table
?>
<TABLE>
<?php

$columnClass = array(
		'id' => 'numericvalue',
		'utm_e' => 'numericvalue',
		'utm_n' => 'numericvalue',
		'id_line' => 'numericvalue',
		'id_stop' => 'numericvalue',
		'duration' => 'numericvalue',
		'distance' => 'numericvalue',
		'order' => 'numericvalue');

while ($row = dbisNextRow()) {
	dbisPrintTableRow($row, $columnClass);  // output one table row
}

?>
</TABLE>
<?php

dbisPrintMenu();
?>
