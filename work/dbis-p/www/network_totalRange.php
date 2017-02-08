<?php
/* $Id: $
 * encoding utf-8
 * 
 * Copyright (c) 2008 Arne Johannessen, Volker von Nathusius
 * All rights reserved. See LICENSE for details.
 */

require_once('dbis.include.php');

dbisPrintHead('General Network Statistics');



/****** database query ******/

dbisQuery("
		SELECT *, (stop_count / line_count) AS stops_per_line, (interchanges_per_line * line_count / stop_count) AS interchanges_percent
		FROM (
			SELECT (
				SELECT SUM(distance)
				FROM (
					-- total track length
					SELECT MAX(stop_has_line.distance) AS distance
					FROM stop_has_line
					INNER JOIN line ON line.id = stop_has_line.id_line
					GROUP BY stop_has_line.id_line
					) AS line_range
				) AS total_range, (
					-- number of lines
					SELECT COUNT(*)
					FROM line
				) AS line_count, (
					-- number of stops
					SELECT COUNT(*)
					FROM stop
				) AS stop_count, (
					-- average number of interchanges per line
					SELECT AVG(line_count)
					FROM (
						SELECT COUNT(line_id) AS line_count
						FROM (
							-- full table of interchange stops
							SELECT DISTINCT shl1.id_line AS line_id, shl1.id_stop
							FROM stop_has_line shl1
							INNER JOIN stop_has_line shl2 ON shl1.id_stop = shl2.id_stop
							WHERE shl1.id_line <> shl2.id_line
							) AS interchange_stops
						GROUP BY line_id
						) AS line_interchange_count
				) AS interchanges_per_line
			) AS results;
		");

// defend against empty results 
if (dbisNumRows() <= 0) {
	trigger_error('The database appears to be corrupted', E_USER_ERROR);
}



/****** print result ******/

$results = dbisNextRow();

// make query results into a  text
?>
<P>The total length of the whole network of Singapore is <?php echo $results['total_range']; ?> <ABBR TITLE="meter">m</ABBR>.</P>
<P>The total count of stops in the entire network is <?php echo $results['stop_count']; ?> and the total number of lines is <?php echo $results['line_count']; ?>. That makes for a computed average of <?php echo round($results['stops_per_line'], 1); ?> stops per line.</P>
<P>An average of <?php echo round($results['interchanges_per_line'], 2); ?> stops per line are interchange stops; over the entire network, <?php echo round($results['interchanges_percent'] * 100, 1); ?> % of stops are interchange stops.</P>
<?php

dbisPrintMenu();
?>