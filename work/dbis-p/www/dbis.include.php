<?php
/* $Id: dbis.include.php 2008-07-13 aj3 $
 * encoding us-ascii
 * 
 * PHP module supporting the database lab at the University of Applied
 * Sciences, Karlsruhe.
 * 
 * Copyright (c) 2008 Arne Johannessen, Volker von Nathusius
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *    * Redistributions of source code must retain the above copyright
 *      notice, this list of conditions and the following disclaimer.
 *    * Redistributions in binary form must reproduce the above copyright
 *      notice, this list of conditions and the following disclaimer in the
 *      documentation or other materials provided with the distribution.
 *    * The names of the authors may not be used to endorse or promote
 *      products derived from this software without specific prior written
 *      permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE AUTHORS "AS IS" AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 * THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */



/* This include file is supposed to be required_once right at the top of the
 * client script, before any output has occurred.
 * It arranges for a connection to the database and offers various utility
 * methods for querying the database as well as printing the navigation menu
 * and masthead. It also takes care of proper error handling in case of PHP
 * errors or connection failures.
 * 
 * This file acts as a facade (single API entry point).
 */



require_once('error.include.php');
require_once('constants.include.php');
require_once('template.include.php');
require_once('database.include.php');



function dbisHttpGet ($name) {
	if(get_magic_quotes_gpc()) {
		return stripslashes($_GET[$name]);
	}
	else {
		return $_GET[$name];
	}
}



/**
 * Prints the first part of the document header.
 */
function dbisPrintHead ($title, $headline = NULL) {
	templatePrintHeadElement($title);
?>
<BODY ID="<?php echo DBIS_CSS_ID; ?>">
<?php
	templatePrintMasthead(($headline === NULL) ? $title : $headline);
}



/**
 * Prints the first part of the document header.
 */
function dbisPrintHeadGoogleMaps ($title) {
	templatePrintHeadElement($title);
?>
<SCRIPT TYPE="text/javascript" SRC="http://maps.google.com/maps?file=api&amp;v=2&amp;key=<?php echo DBIS_GOOGLE_KEY; ?>"></SCRIPT>
<SCRIPT TYPE="text/javascript" SRC="stop-selection.js"></SCRIPT>
<BODY ID="<?php echo DBIS_CSS_ID; ?>" onLoad="load()" onUnload="GUnload()">
<?php
	templatePrintMasthead($title);
}



/**
 * Escapes user-entered text such that it can't do harm when included in an
 * SQL query.
 * 
 * @param mixed $text the (possibly malicious) text entered by the user
 * @param bool $likeOperatorRhs TRUE iff the text is to be used on the right
 *  hand side of a LIKE operator; defaults to FALSE
 * @return string the properly escaped string-equivalent to $text, ready to
 *  be used as part of a properly quoted SQL query
 * @see <http://www.webappsec.org/projects/articles/091007.shtml>
 */
function dbisEscape ($text, $likeOperatorRhs = FALSE) {
	return databaseEscape($text, $likeOperatorRhs);
}



/**
 * Runs an SQL query on the database.
 * 
 * @param string $query the properly escaped query to be sent to the database
 * @return resource the MySQL result
 */
function dbisQuery ($query) {
	return databaseQuery($query);
}



function dbisNumRows ($result = NULL) {
	return databaseNumRows($result);
}



/**
 * Returns the next result row after a query has been run.
 * 
 * @param resource $result the query result
 * @return array an associative array (keys are column headers)
 *  containing the data for the next row, or FALSE if there are
 *  no more rows
 */
function dbisNextRow ($result = NULL) {
	return databaseNextRow($result);
}



/**
 * Prints an array as an HTML table row.
 * 
 * @param array $row the array to be printed (values will be used)
 */
function dbisPrintTableRow ($row, $colClass = NULL, $indent = DBIS_HTML_INDENT) {
	echo $indent, "<TR>\n";
	foreach ($row as $col => $cell) {
		echo $indent, DBIS_HTML_INDENT, '<TD';
		$isEncoded = FALSE;
		if (isset($colClass[$col])) {
			
			// check if encoding is necessary
			$offset = strpos($colClass[$col], 'noentities');
			$isEncoded = $offset !== FALSE;
			if ($isEncoded) {
				$colClass[$col] = trim(substr_replace($colClass[$col], '', $offset, strlen('noentities')));
			}
			
			// set CSS classes
			if (strlen($colClass[$col]) > 0) {
				echo ' CLASS="', $colClass[$col], '"';
			}
		}
		echo '>', $isEncoded ? $cell : htmlspecialchars($cell, ENT_NOQUOTES), "</TD>\n";
	}
	echo $indent, "</TR>\n";
}



/**
 * Prints the document footer and the main menu.
 */
function dbisPrintMenu () {
	templatePrintMenu();
}



function dbisFreeResult ($result = NULL) {
	return databaseFreeResult($result);
}

?>
