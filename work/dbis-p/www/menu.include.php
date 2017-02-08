<?php
/* $Id: menu.include.php 2008-07-13 aj3 $
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

define('MENU_URI', 0x0001);
define('MENU_CAPTION', 0x0002);
define('MENU_BEGIN_SUBSECTION', 0x0101);
define('MENU_END_SUBSECTION', 0x0102);
define('MENU_SUBSECTION_HEADER', 0x0104);

define('MENU_DIRECTORY_INDEX', 'index.php');  // as defined in httpd.conf
define('MENU_TYPE_EXTENSION', '');
/* :KLUDGE:
 * We really don't want to have the '.php' type extension appended to
 * all MENU_URIs below. Unfortunately, the server targeted by this
 * project (hans-f-kern.de) does not support Apache MultiViews at time
 * of this writing. While it was considered to remove the '.php' suffix
 * from the file name itself, such action would most likely cause many
 * more difficulties than the '.php' suffix in the MENU_URI does.
 * If the server admin ever wanted to get rid of the file type extension
 * in navigation menu links, they would need to define the
 * MENU_TYPE_EXTENSION above as '.php'. Also, they would want to
 * implement automatic removal of the file type extension from the
 * HTTP request URI if present (e. g. with mod_rewrite).
 */



// indentation is as in the menu hierarchy
$site = array(
		MENU_BEGIN_SUBSECTION,
			array(MENU_URI => 'singapore.php', MENU_CAPTION => 'The City of Singapore'),
			array(MENU_URI => 'map.php', MENU_CAPTION => 'Network Map'),
		MENU_END_SUBSECTION,
		array(MENU_URI => NULL, MENU_CAPTION => 'Network Info', MENU_SUBSECTION_HEADER => TRUE),
		MENU_BEGIN_SUBSECTION,
			array(MENU_URI => 'network_totalRange.php', MENU_CAPTION => 'Show general Network Statistics'),
			array(MENU_URI => 'network_lineRange.php', MENU_CAPTION => 'Show total range of all lines'),
			array(MENU_URI => 'network_stopNumber.php', MENU_CAPTION => 'Show the total number of stops of all lines'),
		MENU_END_SUBSECTION,
		array(MENU_URI => NULL, MENU_CAPTION => 'Routes', MENU_SUBSECTION_HEADER => TRUE),
		MENU_BEGIN_SUBSECTION,
			array(MENU_URI => 'routing.php', MENU_CAPTION => 'Find a Connection Between Two Stops'),
		MENU_END_SUBSECTION,
		array(MENU_URI => NULL, MENU_CAPTION => 'Lines', MENU_SUBSECTION_HEADER => TRUE),
		MENU_BEGIN_SUBSECTION,
			array(MENU_URI => 'line_atStop.php', MENU_CAPTION => 'Lines servicing a stop'),
			array(MENU_URI => 'line_map.php', MENU_CAPTION => 'Select a line and show it on a map'),
		MENU_END_SUBSECTION,
		array(MENU_URI => NULL, MENU_CAPTION => 'Stops', MENU_SUBSECTION_HEADER => TRUE),
		MENU_BEGIN_SUBSECTION,
			array(MENU_URI => 'stop_nameQuery.php', MENU_CAPTION => 'Search for a stopname'),
			array(MENU_URI => 'stop_howManyLines.php', MENU_CAPTION => 'Show all stops with multiple lines'),
			array(MENU_URI => 'stop_nearPosition.php', MENU_CAPTION => 'Stops near any location'),
			array(MENU_URI => 'stop_reachable.php', MENU_CAPTION => 'Stops reachable without a change'),
			array(MENU_URI => 'stop_atLine.php', MENU_CAPTION => 'Select a line and show all stops'),
			array(MENU_URI => 'stop_nearPoi.php', MENU_CAPTION => 'Select a POI and show the nearest stops to it'),
		MENU_END_SUBSECTION,
		array(MENU_URI => NULL, MENU_CAPTION => 'Points of Interest', MENU_SUBSECTION_HEADER => TRUE),
		MENU_BEGIN_SUBSECTION,
			array(MENU_URI => 'poi_info.php', MENU_CAPTION => 'Search for names of POI'),
			array(MENU_URI => 'poi_nearStop.php', MENU_CAPTION => 'POIs near a stop'),
			array(MENU_URI => 'poi_map.php', MENU_CAPTION => 'Show all POIs on a map'),
		MENU_END_SUBSECTION,
		array(MENU_URI => NULL, MENU_CAPTION => 'Data', MENU_SUBSECTION_HEADER => TRUE),
		MENU_BEGIN_SUBSECTION,
			array(MENU_URI => 'dataset_info.php', MENU_CAPTION => 'Select a raw dataset'),
		MENU_END_SUBSECTION);



function menuUriTidy ($uri) {
	$uri = str_replace('./', '/', $uri);
	$uri = str_replace('//', '/', $uri);
	return $uri;
}



function menuGetCurrentPageItem () {
	global $site;
	foreach ($site as $item) {
		if (! is_array($item) || $item[MENU_URI] === NULL) {
			continue;
		}
		$itemUri = DBIS_BASE_URI.$item[MENU_URI];
		if (substr($itemUri, -1) == '/') {
			$itemUri .= MENU_DIRECTORY_INDEX;
		}
		if (is_array($item) && menuUriTidy($itemUri) == $_SERVER['SCRIPT_NAME']) {
			return $item;
		}
	}
	return NULL;
}



function printMenu () {
	global $site;
?>

<UL ID="menu">
<?php

// print the menu
$currentItem = menuGetCurrentPageItem();
$indent = DBIS_HTML_INDENT;
foreach ($site as $item) {
	
	// handle menu hierarchy
	if (! is_array($item)) {
		if ($item === MENU_BEGIN_SUBSECTION) {
			echo $indent, "<LI CLASS=\"submenu\"><UL>\n";
			$indent .= DBIS_HTML_INDENT;  // increment indentation level
		}
		else if ($item === MENU_END_SUBSECTION) {
			$indent = substr($indent, 0 -1);  // decrement indentation level
			echo $indent, "</UL></LI>\n";
		}
		continue;
	}
	
	// determine menu item state
	$itemPath = DBIS_BASE_PATH.$item[MENU_URI];
	if (substr($itemPath, -1) == '/') {
		$itemPath .= MENU_DIRECTORY_INDEX;
	}
	$itemReadable = $item[MENU_URI] !== NULL && (is_readable($itemPath));
	$queryInUri = array_key_exists('QUERY_STRING', $_SERVER) && strlen($_SERVER['QUERY_STRING']) > 0;
	
	// print menu item
	echo $indent;
	if ($currentItem === $item && ! $queryInUri) {
		// current page
		echo '<LI ID="current"><STRONG TITLE="the query &quot;', $item[MENU_CAPTION], '&quot; is currently selected">', $item[MENU_CAPTION], '</STRONG>';
	}
	else if ($itemReadable) {
		// link to accessible page
		echo '<LI', ($currentItem === $item) ? ' ID="current"' : '', '><A HREF="', str_replace(MENU_TYPE_EXTENSION, '', $item[MENU_URI]), '">', $item[MENU_CAPTION], '</A>';
	}
	else if (@$item[MENU_SUBSECTION_HEADER]) {
		// subsection header
		echo '<LI CLASS="subsection-header">', $item[MENU_CAPTION];
	}
	else {
		// not accessible page (disabled link)
		echo '<LI CLASS="disabled"><A TITLE="the query &quot;', $item[MENU_CAPTION], '&quot; is disabled"><!-- HREF="', $item[MENU_URI], '"-->', $item[MENU_CAPTION], '</A>';
	}
	echo "</LI>\n";
}

?>
</UL>
<?php
}
?>
