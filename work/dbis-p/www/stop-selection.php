<?php

require_once('constants.include.php');
require_once('dbis.include.php');
require_once('utm_calculator.class.php');
$calculator = new UtmCalculator;

// ensure XML output
header('Content-Type: text/xml; charset=utf-8');
echo "<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"yes\"?>\n";



/****** data input and prep ******/

// only proceed with the actual DB query if we have all necessary parameters
$isAllValuesPresent = array_key_exists('latitude', $_GET) && array_key_exists('longitude', $_GET);

// input processing
if ($isAllValuesPresent) {
	// we have a lat/lon position as input value, so convert it to UTM for the DB query
	$utm = $calculator->toUtm((double)$_GET['latitude'], (double)$_GET['longitude']);
	dbisQuery("
			SET @easting = '".$utm['easting']."';
			");
	dbisQuery("
			SET @northing = '".$utm['northing']."';
			");
	$range = (int)@$_GET['range'];
}



/****** run the query ******/

if ($isAllValuesPresent) {
	if ($range > 0) {
		// stops within range only
		dbisQuery("
				SELECT *
				FROM (
					SELECT stop.utm_e AS easting, stop.utm_n AS northing, stop.name AS stopname, round(sqrt(POW((stop.utm_n - @northing), 2) + POW(stop.utm_e - @easting, 2))) AS beeline
					FROM stop
					ORDER BY beeline
					) AS beeline_all_stops
				WHERE beeline <= '$range';
				");
	}
	else {
		// the one nearest stop only
		dbisQuery("
				SELECT utm_e AS easting, utm_n AS northing, name AS stopname, round(sqrt(POW((utm_n - @northing), 2) + POW(utm_e - @easting, 2))) AS beeline
				FROM stop
				ORDER BY beeline
				LIMIT 1;
				");
	}
}
else {
	// all stops
	dbisQuery("
			SELECT utm_e AS easting, utm_n AS northing, name AS stopname
			FROM stop
			ORDER BY name;
			");
}



/****** output result ******/

?>
<resultset>
<?php
while ($row = dbisNextRow()) {
	
	// get geographic position of stop
	$geographic = $calculator->toGeographic($row['easting'], $row['northing'], DBIS_UTM_ZONE);
	
	// output stop as XML
	echo DBIS_HTML_INDENT;
	printf('<stop latitude="%08.6f" longitude="%08.6f"', $geographic['latitude'], $geographic['longitude']);
	if (array_key_exists('beeline', $row)) {
		echo ' beeline="', $row['beeline'], '"';
	}
	echo '>', htmlspecialchars($row['stopname'], ENT_NOQUOTES), "</stop>\n";
}
?>
</resultset>
