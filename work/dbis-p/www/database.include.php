<?php
/* $Id: dbis.include.php 2008-06-30 aj3 $
 * encoding us-ascii
 * 
 * PHP module supporting the database lab at the University of Applied
 * Sciences, Karlsruhe. Connects to the database and provides query tools.
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



require_once('error.include.php');
require_once('constants.include.php');



$connectionUri = 'mysql:host=' . DBIS_HOST . ';dbname=' . DBIS_DATABASE;
$dbisPdo = new PDO($connectionUri, DBIS_USER, DBIS_PASSWORD)
		or trigger_error('Failed to establish a connection to the database server', E_USER_ERROR);
$dbisLastResult = NULL;

$dbisPdo->query("SET CHARACTER SET 'utf8';");

// at this point the database link is properly established



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
function databaseEscape ($text, $likeOperatorRhs = FALSE) {
	if ($likeOperatorRhs) {
		return addcslashes(preg_replace("/[^a-zA-Z0-9 ]+/", "", $text), "%_");
	}
	else {
		return preg_replace("/[^a-zA-Z0-9 _]+/", "", $text);
	}
}



/**
 * Runs an SQL query on the database.
 * 
 * @param string $query the properly escaped query to be sent to the database
 * @return resource the MySQL result
 */
function databaseQuery ($query) {
	global $dbisPdo;
	global $dbisLastResult;
	$dbisLastResult = $dbisPdo->query($query)
			or trigger_error('Execution of query "'.htmlspecialchars($query, ENT_NOQUOTES).'" failed', E_USER_ERROR);
	return $dbisLastResult;
}



/**
 * Returns the next result row after a query has been run.
 * 
 * @param resource $result the query result
 * @return array an associative array (keys are column headers)
 *  containing the data for the next row, or FALSE if there are
 *  no more rows
 */
function databaseNextRow ($result = NULL) {
	global $dbisLastResult;
	if ($result === NULL) {
		if ($dbisLastResult === NULL) {
			trigger_error('query result is NULL', E_USER_ERROR);  // :TODO: proper error handling
		}
		$result = $dbisLastResult;
	}
	return $result->fetch(PDO::FETCH_ASSOC);
}



function databaseNumRows ($result = NULL) {
	global $dbisLastResult;
	if ($result === NULL) {
		if ($dbisLastResult === NULL) {
			trigger_error('query result is NULL', E_USER_ERROR);  // :TODO: proper error handling
		}
		$result = $dbisLastResult;
	}
	return $result->rowCount($result);
}



function databaseFreeResult ($result = NULL) {
}

?>
