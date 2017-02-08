<?php
/* $Id: $
 * encoding utf-8
 * 
 * Copyright (c) 2008 Arne Johannessen, Volker von Nathusius
 * All rights reserved. See LICENSE for details.
 */

require_once('dbis.include.php');
require_once('utm_calculator.class.php');
$calculator = new UtmCalculator;

dbisPrintHead('Information about the city of Singapore');



/****** database query ******/

$northingResult = dbisQuery("
		SELECT AVG(utm_e) AS average_e, AVG(utm_n) AS average_n
		FROM (
			SELECT utm_e, utm_n
			FROM stop
			UNION
			SELECT utm_e, utm_n
			FROM poi
			) AS all_positions;
		");

$lengthResult = dbisQuery("
		SELECT SUM(distance) AS total_range
		FROM (
			SELECT MAX(stop_has_line.distance) AS distance
			FROM stop_has_line
			INNER JOIN line ON line.id = stop_has_line.id_line
			GROUP BY stop_has_line.id_line
			) AS line_range;
		");

// defend against empty results 
if (dbisNumRows($northingResult) <= 0 || dbisNumRows($lengthResult) <= 0) {
	trigger_error('The database appears to be corrupted', E_USER_ERROR);
}


/****** print result ******/

$northingRow = dbisNextRow($northingResult);
$geographic = $calculator->toGeographic($northingRow['average_e'], $northingRow['average_n'], DBIS_UTM_ZONE);
$kmPerDegreeOfLatitude = $calculator->ellipsoidSemiMajorAxis / 1000 * (M_PI / 2 / 90);
$kmNorthOfEquator = $geographic['latitude'] * $kmPerDegreeOfLatitude;

$lengthRow = dbisNextRow($lengthResult);
$kmCurrentNetwork = $lengthRow['total_range'] / 1000;

// make query results into a  text
?>

<P><IMG SRC="images/CityView.jpg" CLASS="fullwidth" ALT=""></P>
<P>The <STRONG>Republic of Singapore (Mandarin: 新加坡, Xīnjiāpō)</STRONG> is an island nation located at the southern tip of the Malay Peninsula. It lies <EM><?php echo round($kmNorthOfEquator); ?> kilometres north of the equator,</EM> south of the Malaysian state of Johor and north of Indonesia’s Riau Islands. At 707.1 km² it is one of the few remaining city-states in the world and the smallest country in Southeast Asia.</P>
<P>Transportation within Singapore is mainly land-based. Almost all parts of Singapore are accessible by road, including islands such as Sentosa and Jurong Island. The other major form of transportation within Singapore is rail: the Mass Rapid Transit MRT which runs the length and width of Singapore, and the Light Rail Transit LRT which runs within a few neighbourhoods. The main island of Singapore is connected to the other islands by ferryboat services.</P>
<P>The public transport system is regulated by the Land Transport Authority (LTA), whose policies are meant to encourage the use of public transport in Singapore. The key aims are to provide an incentive to reside away from the Central district, as well as to reduce air pollution. There is also a system of bus routes throughout the island, most of which have air conditioning due to Singapore’s tropical climate. Buses without air conditioning are gradually being phased out.</P>
<P>The initial section of the MRT, between Choa Chu Kang and Toa Payoh, opened in 1987 establishing itself as the second-oldest metro system in Southeast Asia. The network has since grown rapidly as a result of Singapores aim of developing a comprehensive rail network as the main backbone of the public transport system in Singapore with an average daily ridership of 1.435 million, about half of the bus network’s 2.853 million in the same period.</P>
<P>The MRT system had relied on its two main lines, namely the North South and East West Lines, for more than a decade until the opening of the North East Line in 2003. Government, encouraged by LTA, intents to greatly expand on the existing system. The plans allow for the long-term replacement of the bus network by rail-based transportation as the primary mode of public transportation. It called for the expansion of the 67 kilometres of track in 1995 to over 160 in 10 to 15 years, and envisaged further expansion in the longer term.</P>
<P>By 2030, the government envisages a rail network of 540 kilometres — more extensive than Londons 408-kilometre Tube system. It is anticipated that by 2020, the <EM>rail network</EM> will have grown to 278 kilometres from the <EM>current <?php echo round($kmCurrentNetwork); ?> kilometres,</EM> with ridership expected to rise from the current 1.4 million to 4.6 million daily. The network density will also rise from the current 31 to 51 kilometres per million population, putting it on par with the networks in London and New York, while exceeding those in Hong Kong and Tokyo.</P>

<HR>
<P CLASS="license"><IMG SRC="http://creativecommons.org/images/public/somerights20.png" ALT=""> The <A HREF="images/CityView.jpg" TYPE="image/png">photograph above</A> was retrieved on 3 July 2008 <A REL="copyright" HREF="http://commons.wikimedia.org/wiki/Image:Marina_bay_new_IR.jpg">from Wikimedia Commons</A>; copyright © 2006 Calvin Teo. It is licensed under the <A REL="license" HREF="http://creativecommons.org/licenses/by-sa/2.5/">Creative Commons Attribution-Share Alike 2.5 License</A>.</P>
<P CLASS="license">The text on this page uses material from the Wikipedia articles <A REL="copyright" HREF="http://en.wikipedia.org/w/index.php?title=Singapore&amp;oldid=222714482">Singapore</A> and <A REL="copyright" HREF="http://en.wikipedia.org/w/index.php?title=Mass_Rapid_Transit_%28Singapore%29&amp;oldid=222225269">Mass Rapid Transit (Singapore)</A>; copyright © 2001–2008 authors of <A HREF="http://en.wikipedia.org/w/index.php?title=Singapore&amp;action=history">Singapore</A> and <A REL="copyright" HREF="http://en.wikipedia.org/w/index.php?title=Mass_Rapid_Transit_%28Singapore%29&amp;action=history">Mass Rapid Transit (Singapore)</A> in Wikipedia, © 2008 Volker von Nathusius, Arne Johannessen. Permission is granted to copy, distribute and/or modify this text under the terms of the <A REL="license" HREF="http://www.gnu.org/copyleft/fdl.html">GNU Free Documentation License</A>, version 1.2 published by the <A HREF="http://www.fsf.org/">Free Software Foundation</A>; with no Invariant Sections, no Front-Cover Texts, and no Back-Cover Texts. A <A HREF="singapore-text.LICENSE.txt" TYPE="text/plain">copy of the license</A> is available.</P>

<?php
dbisPrintMenu();
?>
