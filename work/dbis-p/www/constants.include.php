<?php
/* $Id: constants.include.php 2008-06-30 aj3 $
 * encoding us-ascii
 * 
 * by Arne Johannessen, Volker von Nathusius
 */



// database access
define('DBIS_HOST', 'localhost');
define('DBIS_USER', 'singapore-dbis');
define('DBIS_PASSWORD', '');
define('DBIS_DATABASE', 'singapore');

// Google Maps API key
//define('DBIS_GOOGLE_KEY', 'ABQIAAAAGghKHlLH5E9h0quV7JoykxRi_j0U6kJrkFvY4-OX2XYmEAa76BTloRz-qJnP3nxSvhaZXKAuLoArdw');  // 127.0.0.1
//define('DBIS_GOOGLE_KEY', 'ABQIAAAAGghKHlLH5E9h0quV7JoykxT2yXp_ZAY8_ufC3CFXhHIE1NvwkxSvbGm5v_7noP-U0CfeQtHURJ3ihQ');  // localhost
define('DBIS_GOOGLE_KEY', 'ABQIAAAAGghKHlLH5E9h0quV7JoykxRcPJHppFiQ-MBXrOI2zKSkX9lF8BTRHjSDT6ezMqba0t6Uk784aX30IQ');  // johannessen.de

// menu automation
define('DBIS_BASE_URI', '/~joar0011/work/dbis-p/www/');
define('DBIS_BASE_PATH', '/srv/arne/~joar0011/work/dbis-p/www/');

// :TODO: verify that windows backslashes work here

// site ID for user CSS selection
define('DBIS_CSS_ID', 'www-hans-f-kern-de-singapore-ss-2008');

// the UTM zone the coordinates in the database lie within
define('DBIS_UTM_ZONE', '48N');

// the indentation used for HTML output
define('DBIS_HTML_INDENT', "\t");

// this setting merely influences the reporting of debugging information (stacktrace, file path)
ini_set('error_reporting', E_ALL);

?>
