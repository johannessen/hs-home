<?php
/* $Id: maplink.include.php 2008-06-30 aj3 $
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
require_once('utm_calculator.class.php');



function convertCoordinatesToMapLink (&$array, $eastingKey, $northingKey) {
	
	// pseudo-Singleton pattern for the calculator instance
	static $calculator = NULL;
	if ($calculator === NULL) {
		$calculator = new UtmCalculator;
	}
	
	// convert from UTM to geographic
	$geographic = $calculator->toGeographic($array[$eastingKey], $array[$northingKey], DBIS_UTM_ZONE);
	
	// create HTML for the link
	$uri = sprintf('http://maps.google.com/maps?ll=%08.6f,%08.6f&t=h&z=17', $geographic['latitude'], $geographic['longitude']);
	$locationName = array_key_exists('name', $array) ? $array['name'] : 'this location';
	$linkHtml = '<A HREF="'.htmlspecialchars($uri).'" TITLE="Show the area surrounding '.htmlspecialchars($locationName).' on Google Maps" CLASS="maplink"><IMG SRC="images/map.png" ALT="Show map"></A>';
	
	// replace coordinates in the array with the link
	// for now just appending the link at the end of the array is good enough
	unset($array[$eastingKey]);
	unset($array[$northingKey]);
	$array['maplink'] = $linkHtml;
}

?>
