<?php
/* $Id: template.include.php 2008-07-13 aj3 $
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



require_once('constants.include.php');
require_once('menu.include.php');



function templatePrintHeadElement ($title) {
	header('Content-Style-Type: text/css');
	header('Content-Script-Type: text/javascript');
?>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<HTML LANG="en">
<TITLE><?php echo $title; ?> &lt; SINIS</TITLE>
<STYLE TYPE="text/css">
	@import url("singapore.css");
</STYLE>
<SCRIPT TYPE="text/javascript" SRC="singapore.js" DEFER></SCRIPT>
<?php
}



function templatePrintMasthead ($title) {
?>

<DIV ID="skiplink"><A HREF="#menuheader">Skip to Navigation</A></DIV>

<H1><ABBR>SINIS</ABBR> Singapore Network Information System</H1>
<H2><?php echo $title; ?></H2>

<?php
}



/**
 * Prints the document footer and the main menu.
 */
function templatePrintMenu () {
?>

<H2 ID="menuheader">Navigation Menu</H2>
<?php
printMenu();
?>
<UL ID="sitemenu">
	<LI><A HREF="./">About us</A></LI>
</UL>
<?php
}

?>
