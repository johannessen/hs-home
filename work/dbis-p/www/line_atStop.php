<?php
/* $Id: $
 * encoding us-ascii
 * 
 * Copyright (c) 2008 Arne Johannessen, Volker von Nathusius
 * All rights reserved. See LICENSE for details.
 */

require_once('dbis.include.php');



/****** data entry and prep ******/

$isAllValuesPresent = array_key_exists('stopname', $_GET);
$noScript = array_key_exists('noscript', $_GET) && dbisHttpGet('noscript') == 1;

$safeStopName = htmlspecialchars(@dbisHttpGet('stopname'));

if (! $isAllValuesPresent || $noScript) {
	// no particular stop has been requested
	// prepare default Google Maps screen
	
	dbisPrintHeadGoogleMaps('Lines servicing a stop');
?>
<SCRIPT SRC="stop-selection.js" TYPE="text/javascript"></SCRIPT>

<P ID="legendabove"></P>
<DIV ID="selectionmap">
	<FORM ACTION="" METHOD="GET">
		<P><LABEL>Name of stop: <INPUT TYPE="text" NAME="stopname" VALUE="<?php echo $safeStopName; ?>"></LABEL></P>
		<P><INPUT TYPE="submit" VALUE="Go!"><INPUT TYPE="hidden" NAME="noscript" VALUE="1">
	</FORM>
	<P>Normally, an interactive map would appear in this space. However, your browser or JavaScript engine appears to be <STRONG>incompatible with Google Maps,</STRONG> or there is no connection to Google's server. Therefore we offer the simple Web form above as alternative means to run this page's query.</P>
	<P>Note that you have to enter the stop's name using the correct spelling here. You may mix case as you like though.</P>
</DIV>
<DIV ID="out"></DIV>

<?php
}



/****** run database query ******/

if ($isAllValuesPresent) {
	// we did get a request for one particular stop
	// run query for this stop and output results as HTML
	
	// input processing
	dbisQuery("SET @input = '".dbisEscape(dbisHttpGet('stopname'))."';");
	
	// run the query
	dbisQuery("
			SELECT DISTINCT line.name AS linename, line.color AS color
			FROM line, stop, stop_has_line
			WHERE stop.name = @input AND stop.id = stop_has_line.id_stop AND line.id = stop_has_line.id_line;
			");
	
	
	
	/****** print result ******/
	
	// defend against empty results
	if (dbisNumRows() <= 0) {
?>
<P>No MRT lines stop at "<?php echo $safeStopName; ?>", or no such stop could be found.</P>
<?php
		if ($noScript) {
			dbisPrintMenu();
		}
		exit();
	}
	
	// put query results into a table
?>
<TABLE>
	<COL>
<THEAD>
	<TR>
		<TH SCOPE="col" ABBR="Line">These lines stop at <?php echo $safeStopName; ?></TH>
	</TR>
<TBODY>
<?php
	
	$columnClass = array(
			'linename' => 'linename noentities');
	
	while ($row = dbisNextRow()) {
		$row['linename'] = '<SPAN STYLE="background-color: '.$row['color'].'">'.$row['linename'].'</SPAN>';
		unset($row['color']);
		dbisPrintTableRow($row, $columnClass);  // output one table row
	}
?>
</TABLE>
<?php
}


if (! $isAllValuesPresent || $noScript) {
	dbisPrintMenu();
}

?>
