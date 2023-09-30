<?php
/* $Id: error.include.php 2008-06-30 aj3 $
 * encoding us-ascii
 * 
 * PHP module supporting the database lab at the University of Applied
 * Sciences, Karlsruhe. Handles fatal errors in a user-friendly way.
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



require_once('constants.include.php');



function httpErrorDescription ($httpStatus) {
	// see http://www.w3.org/Protocols/rfc2616/rfc2616-sec10#sec10
	switch ((int)$httpStatus) {
		case 400:
			return 'Your browser sent a request that this server could not understand.';
		case 403:
			return 'You don&#8217;t have permission to access '.$_SERVER['REQUEST_URI'].' on this server.';
		case 404:
			return 'The requested URI '.$_SERVER['REQUEST_URI'].' was not found on this server.';
		case 410:
			return 'The requested resource '.$_SERVER['REQUEST_URI'].' is no longer available on this server and there is no forwarding address. Please remove all references to this resource.';
		
		case 500:
			return "The server encountered an internal error or misconfiguration and was unable to complete your request.\nPlease contact the server administrator, ".$_SERVER['SERVER_ADMIN']." and inform them of the time the error occurred, and anything you might have done that may have caused the error.";
		case 501:
			return $_SERVER['REQUEST_METHOD'].' to '.$_SERVER['REQUEST_URI'].' not supported.';
		case 503:
			return 'The server is temporarily unable to service your request due to maintenance downtime. Please try again later.';
		
		default:
			return 'An unknown error occured while processing the request for the URL '.$_SERVER['REQUEST_URI'].' on this server.';
	}
}



function errorTypeName ($type) {
	static $typeNames = array (
			2 => "PHP Warning",
			8 => "PHP Notice",
			256 => "PHP User Error",
			512 => "PHP User Warning",
			1024 => "PHP User Notice");
	if (array_key_exists($type, $typeNames)) {
		return $typeNames[$type];
	}
	else {
		return 'PHP Error #'.$type;
	}
}



function errorFileName ($filePath) {
	if (strpos($filePath, DBIS_BASE_PATH) === 0) {
		$filePath = substr($filePath, strlen(DBIS_BASE_PATH));
		while ($filePath[0] == '/') {
			$filePath = substr($filePath, 1);
		}
	}
	return $filePath;
}



function printErrorPageAndDie ($httpStatus = '500 Internal Server Error', $debugInfo = FALSE) {
	ob_end_clean();
	
	header('HTTP/1.1 '.$httpStatus);
	header('Content-Type: text/html; charset=utf-8');
	
	$stacktrace = debug_backtrace();
	foreach ($stacktrace as $level) {
		if ($level['function'] == 'errorHandler') {
			list($type, $message, $file, $line) = $level['args'];
			break;
		}
	}
	
	require_once('template.include.php');
	templatePrintHeadElement($httpStatus);
?>
<STYLE TYPE="text/css">
H3 {
	font-weight: normal;
	font-size: 1em;
	margin: 0;
}
.debug {
	background: inherit;
	color: #666;
}
#stacktrace, H3 {
	margin-bottom: 0;
}
#stacktrace+UL, H3+P {
	margin-top: 0;
}
</STYLE>
<BODY ID="<?php echo DBIS_CSS_ID; ?>">
<?php templatePrintMasthead($httpStatus); ?>
<P><?php echo httpErrorDescription($httpStatus); ?></P>

<H3>Error Message:</H3>
<P><STRONG><?php echo $message; ?></STRONG><?php if ($debugInfo) { echo ' <SPAN CLASS="debug">(', errorTypeName($type), ')</SPAN>'; } ?></P>

<H3>Exact time this error occurred on:</H3>
<P><?php echo date('Y-m-d H:i:s (T)'); ?></P>
<?php
	if ($debugInfo) {
?>
<DIV CLASS="debug">
	<H3>Location:</H3>
	<P><?php echo $file; ?>, line <?php echo $line; ?></P>
	<H3 ID="stacktrace">Stack Trace:</H3>
	<UL>
<?php
		array_shift($stacktrace);
		foreach ($stacktrace as $level) {
			if ($level['function'] == 'errorHandler') {
				continue;
			}
			echo DBIS_HTML_INDENT, DBIS_HTML_INDENT, '<LI>';
			echo array_key_exists('class', $level) ? ($level['class'].'.') : '';
			echo $level['function'], ' (', errorFileName($level['file']), ':', $level['line'], ")</LI>\n";
		}
		echo DBIS_HTML_INDENT, DBIS_HTML_INDENT, '<LI>&lt;init> (', errorFileName($_SERVER['SCRIPT_FILENAME']), ")</LI>\n";
		echo DBIS_HTML_INDENT, "</UL>\n</DIV>\n";
	}
	
	templatePrintMenu();
	exit();
}



function errorHandler ($type, $message, $file, $line) {
	
	// gather information
	$isFatal = ((E_ERROR | E_CORE_ERROR | E_COMPILE_ERROR | E_USER_ERROR) & $type) > 0;
	$error_reporting = ini_get('error_reporting');
	
	// we ignore errors suppressed by the error-control operator (@)
	if ($error_reporting == 0 && ! $isFatal) {
		return;
	}
	
	// log the error using the server API's logging functionality
	$log = errorTypeName($type).': '.$message.' in '.$file.' on line '.$line;
	error_log($log);
	
	// we print error pages as configured, plus for any fatal errors
	$isReportConfigured = ($error_reporting & $type) > 0;
	if ($isReportConfigured || $isFatal) {
		printErrorPageAndDie('500 Internal Server Error', $isReportConfigured);
	}
}



// start output buffering to facilitate full error pages
ob_start();
header('Content-Type: text/html; charset=utf-8');

// set up our own custom error handler
set_error_handler('errorHandler');
ini_set('display_errors', 0);

//$h=hh;
//trigger_error('DB connection failed', E_USER_ERROR);

?>
