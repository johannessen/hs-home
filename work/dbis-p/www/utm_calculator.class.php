<?php
/* $Id: utm_calculator.class.php 2008-05-23 aj3 $
 * encoding utf-8
 * 
 * PHP class for converting UTM to geographic coordinates and vice-versa.
 * 
 * Copyright (c) 2004, 2008 Arne Johannessen
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *    * Redistributions of source code must retain the above copyright
 *      notice, this list of conditions and the following disclaimer.
 *    * Redistributions in binary form must reproduce the above copyright
 *      notice, this list of conditions and the following disclaimer in the
 *      documentation or other materials provided with the distribution.
 *    * The name of Arne Johannessen may not be used to endorse or promote
 *      products derived from this software without specific prior written
 *      permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY ARNE JOHANNESSEN "AS IS" AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL ARNE JOHANNESSEN BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 * THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */



/**
 * Provides utility methods for calculations within the Universal Transverse
 * Mercator (UTM) system. This initial version focuses on conversion routines
 * from UTM coordinates (easting, northing, and zone) to the corresponding
 * geographic coordinates (latitude and longitude) and vice-versa.
 * 
 * Example code: <code><pre>
 * 
 * require_once('utm_calculator.class.php');
 * $calculator = new UtmCalculator;
 * 
 * $position = $calculator->toGeographic(456452, 5429140, '32U');
 * echo 'LAT ', $position['latitude'], ' LON ', $position['longitude'];
 * 
 * </pre></code><code><pre>
 * 
 * require_once('utm_calculator.class.php');
 * $calculator = new UtmCalculator;
 * 
 * $latitude = $calculator->toDecimalDegrees(array(
 *         'degrees' => 49, 'minutes' => 0, 'seconds' => 49));
 * $longitude = $calculator->toDecimalDegrees(array(
 *         'degrees' => 8, 'minutes' => 24, 'seconds' => 16));
 * $position = $calculator->toUtm($latitude, $longitude);
 * echo 'E ', $position['easting'], ' N ', $position['northing'];
 * echo ' ', $position['zone'];
 * 
 * </pre></code>
 */
class UtmCalculator {
	
	
	/**
	 * The semi-major axis of the ellipsoid used for all calculations, in
	 * metres. (For calculations on a sphere, this would mean its radius.)
	 * Change this value to use ellipsoids that are not WGS84-compatible.
	 */
	var $ellipsoidSemiMajorAxis;
	
	
	/**
	 * Mathematical square of the ellipsoid's primary eccentricity. (For
	 * calulations on a sphere, this value would be zero.) Change this value
	 * to use ellipsoids that are not WGS84-compatible.
	 */
	var $ellipsoidEccentricitySquared;
	
	
	/**
	 * Scale of the projection's central meridian. Change this value to use
	 * this class for other transverse mercator (TM) projections.
	 */
	var $scaleFactor;
	
	
	/** False easting of the projection. */
	var $falseEasting;
	
	
	/**
	 * False northing of the projection. Note that this variable is changed
	 * by some methods as a side-effect!
	 */
	var $falseNorthing;
	
	
	
	/**
	 * This constructor initialises all instances for the WGS84 ellispoid
	 * and a northern-hemisphere UTM projection.
	 */
	function __construct () {
		// values for WGS84 as per NIMA TR 8350.2, 3rd ed., p. 3-2
		$this->ellipsoidSemiMajorAxis = 6378137;
		$flattening = 1 / 298.257223563;
		$this->ellipsoidEccentricitySquared = 2 * $flattening - $flattening*$flattening;
		
		// UTM projection properties as per DMA TM 8358.2, p. 2-2
		$this->scaleFactor = .9996;
		$this->falseEasting = 500000;
		$this->falseNorthing = 0;
	}
	
	
	
	/**
	 * Sets this object's false northing appropriate in the UTM projection
	 * for the given hemisphere. For north latitudes and the equator this
	 * is 0 m, for south latitudes 10,000,000 m. The idea is that a UTM
	 * northing never has more than 7 digits.
	 * 
	 * @param boolean $isNorthern TRUE if the coordinates are on the equator
	 *  or at north latitude
	 */
	function _setNorthernHemisphere ($isNorthern) {
		if ($isNorthern) {
			$this->falseNorthing = 0;
		}
		else {
			$this->falseNorthing = 10000000;
		}
	}
	
	
	
	/**
	 * Normalises an angle in degrees. The result's difference from the
	 * specified offset (0 degrees by default) will be at most 180 degrees
	 * in either direction. Note that this results in two possible return
	 * values for the same physical angle (-180 degrees and +180 degrees).
	 * 
	 * @param number $longitude the angle to be normalised
	 * @param number $offset offset to normalise around
	 */
	function _normaliseAngle ($longitude, $offset = 0) {
		while ($longitude > 180 + $offset) {
			$longitude -= 360;
		}
		while ($longitude < -180 + $offset) {
			$longitude += 360;
		}
		return $longitude;
	}
	
	
	
	/**
	 * Determines the central meridian of a given UTM zone.
	 * 
	 * @param number $zone UTM longitude zone designation
	 * @return int the longitude of the zone's central meridian
	 * @throws E_USER_ERROR if $zone < 1 or $zone > 60
	 */
	function _centralMeridian ($zone) {
		$zone = (int)$zone;
		if ($zone < 1 || $zone > 60) {
			trigger_error('illegal UTM longitude zone '.$zone, E_USER_ERROR);
		}
		return $zone * 6 - 183;
	}
	
	
	
	/**
	 * Determines the UTM zone designation that matches the given latitude
	 * and longitude. This method correctly implements, and applies by
	 * default, the Norwegian irregularities to the UTM system (Western
	 * Norway and Svalbard).
	 * 
	 * @param number $latitude the latitude of the position whose UTM zone
	 *  is to be determined
	 * @param number $longitude the longitude of the position whose UTM zone
	 *  is to be determined
	 * @param boolean $applyExceptions FALSE if the Norwegian UTM exceptions
	 *  should be ignored
	 * @throws E_USER_WARNING if $latitude is outside the range [-80,84] for
	 *  which the UTM projection is defined
	 */
	function _zone ($latitude, $longitude, $applyExceptions = TRUE) {
		if ($latitude > 84 || $latitude < -80) {
			trigger_error('UPS coordinate system not implemented', E_USER_WARNING);
		}
		$longitude = $this->_normaliseAngle($longitude);
		$longitudeZone = (int)(($longitude + 180) / 6) + 1;
		$latitudeZoneNumber = (int)(($latitude + 80) / 8);
		if ($latitudeZoneNumber >= 6) {  // ord('I') - ord('C')
			$latitudeZoneNumber++; // there is no zone I
		}
		if ($latitudeZoneNumber >= 12) {  // ord('O') - ord('C')
			$latitudeZoneNumber++; // there is no zone O
		}
		$latitudeZone = chr($latitudeZoneNumber + 0x43);  // ord('C')
		$zone = $longitudeZone.$latitudeZone;
		
		// exceptions to UTM; cf. USGS PP 1395, p. 62
		if ($applyExceptions) {
			if ($latitudeZone == 'V' && $longitude > 3) {
				$zone = '32V';
			}
			if ($latitudeZone == 'X' && $longitude >= 6 && $longitude <= 36) {
				$zone = ((int)(($longitude + 183) / 12) * 2 + 1).'X';
			}
		}
		
		return $zone;
	}
	
	
	
	/**
	 * Determines the UTM hemisphere based on the UTM zone designation.
	 * 
	 * There are two systems commonly used for UTM zone designations: The
	 * one uses the UTM latitude zones (letters C to X, as defined for the
	 * military grid), the other just uses N or S as hemisphere designator.
	 * Unfortunately the letter S means a southern hemisphere in the latter,
	 * but a northern hemisphere in the former. Therefore this method uses
	 * heuristics based on the actual latitude or easting of a coordinate to
	 * decide which of the systems is to be used in each specific case.
	 * 
	 * If actual coordinates are unavailable, simply pass NULL for both
	 * $latitude and $northing, and pass TRUE for $gridReference if you want
	 * your $latitudeZone to be interpreted as a military-style latitude
	 * grid reference; otherwise, pass FALSE for $gridReference, in which
	 * case it will be interpreted as an "either N or S" hemisphere
	 * designation. In other words, for $gridReference == TRUE,
	 * the zone S will result in the northern hemisphere, while
	 * for $gridReference == FALSE, zone S will be assumed to be in
	 * the southern hemisphere.
	 * 
	 * Note that this method changes the value of the $falseNorthing
	 * instance variable as a side-effect.
	 * 
	 * @param string $latitudeZone the UTM zone to be interpreted (the
	 *  letter can either be a latitude grid zone or a hemisphere)
	 * @param number $latitude a latitude to use for heuristics if the zone
	 *  letter is an S
	 * @param number $northing a northing to use for heuristics if the zone
	 *  letter is an S
	 * @param boolean $gridReference whether the zone letter is to be
	 *  interpreted as a latitude grid reference if heuristics cannot be
	 *  used
	 * @throws E_USER_ERROR if $latitudeZone does not contain a valid UTM
	 *  zone designation (the longitude zone being optional)
	 */
	function _setHemisphere ($latitudeZone, $latitude = NULL, $northing = NULL, $gridReference = TRUE) {
		if (! preg_match('/^[0-9]{0,2}([C-HJ-NP-X])$/i', $latitudeZone, $match)) {
			trigger_error('unable to determine UTM hemisphere from "'.$latitudeZone.'"', E_USER_ERROR);
		}
		$latitudeZone = strtoupper($match[1]);
		
		if ($latitudeZone <= 'M') {
			// the latitude zone is definitely meant to signify southern hemisphere
			$this->_setNorthernHemisphere(FALSE);
		}
		else if ($latitudeZone != 'S') {
			// the latitude zone is definitely meant to signify northern hemisphere
			$this->_setNorthernHemisphere(TRUE);
		}
		else {
			// an S may be meant both as hemisphere designator or as latitude zone
			if (is_numeric($latitude) || is_numeric($northing)) {
				// let's do some heuristics here
				$this->_setNorthernHemisphere(is_numeric($latitude) && $latitude >= 16
						|| is_numeric($northing) && $northing > 3000000 && $northing < 5000000);
			}
			else {
				// no information available; give up
				trigger_error('UTM latitude zone ambiguous', E_USER_NOTICE);
				$this->_setNorthernHemisphere($gridReference);
			}
		}
	}
	
	
	
	/**
	 * Projects a given geographic position onto a transverse mercator grid.
	 * Projection paramters and ellipsoid are to be set as instance
	 * variables. The formulas used are those given in Snyder's USGS
	 * Professional Paper 1395, pages 48 ff.
	 * 
	 * Interpreting Snyder, accuracy is understood to be in the order of
	 * millimetres within the defined zone and decimetres in the adjacent
	 * zones.
	 * 
	 * @param number $latitude the latitude of the position to be projected
	 * @param number $longitude the longitude of the position to be
	 *  projected
	 * @return a key-value-coded array containing the TM projected position:
	 *      'easting' is the abscissa in the TM projection
	 *      'northing' is the ordinate in the TM projection
	 */
	function _convertGeographicToTm ($latitude, $longitude) {
		// forward formulas
		// local variable names follow formula symbols in USGS PP 1395
		$φ = $latitude * M_PI / 180;
		$λ = $longitude * M_PI / 180;
		$k₀ = $this->scaleFactor;
		$a = $this->ellipsoidSemiMajorAxis;
		$e² = $this->ellipsoidEccentricitySquared;
		
		$e′² = $e² / (1 - $e²);  // (8-12)
		$N = $a / sqrt(1 - $e² * pow(sin($φ), 2));  // (4-20)
		$T = pow(tan($φ), 2);  // (8-13)
		$C = $e′² * pow(cos($φ), 2);  // (8-14)
		$A = $λ * cos($φ);  // (8-15) : λ₀ == 0
		$M = $a * ((1 - $e² / 4 - $e²*$e² * 3 / 64 - $e²*$e²*$e² * 5 / 256) * $φ
				- ($e² * 3 / 8 + $e²*$e² * 3 / 32 + $e²*$e²*$e² * 45 / 1024) * sin(2 * $φ)
				+ ($e²*$e² * 15 / 256 + $e²*$e²*$e² * 45 / 1024) * sin(4 * $φ)
				- ($e²*$e²*$e² * 35 / 3072) * sin(6 * $φ));  // (3-21)
		
		$x = $k₀ * $N * ($A + (1 - $T + $C) * $A*$A*$A / 6 + (5 - 18 * $T + $T*$T + 72 * $C - 58 * $e′²) * $A*$A*$A*$A*$A / 120);  // (8-9)
		$y = $k₀ * ($M + $N * tan($φ) * ($A*$A / 2
				+ (5 - $T + 9 * $C + 4 * $C*$C) * $A*$A*$A*$A / 24
				+ (61 - 58 * $T + $T*$T + 600 * $C - 330 * $e′²) * $A*$A*$A*$A*$A*$A / 720));  // (8-10) : M₀ == 0 <= φ₀ == 0
		
		return array('easting' => $x + $this->falseEasting, 'northing' => $y + $this->falseNorthing);
	}
	
	
	
	/**
	 * Converts a given geographic position to an UTM position. This method
	 * achieves sub-metre accuracy.
	 * 
	 * If a specific zone is given, that zone will be used for the UTM
	 * projection, even if it is way outside the defined range. Note that
	 * this may result in an error when resulting in invalid projection
	 * parameters. If no zone is specified, it is automatically determined
	 * to fit the given position.
	 * 
	 * Note that this method changes the value of the $falseNorthing
	 * instance variable as a side-effect.
	 * 
	 * @param number $latitude the latitude of the position to be converted
	 * @param number $longitude the longitude of the position to be
	 *  converted
	 * @param string $zone the desired UTM zone, or NULL if the zone is to
	 *  be automatically determined
	 * @return a key-value-coded array containing the UTM position:
	 *      'easting' is the UTM easting
	 *      'northing' is the UTM northing
	 *      'zone' is the UTM zone with latitude grid reference letter
	 * @throws E_USER_ERROR if $latitude is outside the range [-90,90] for
	 *  which it is defined
	 * @throws E_USER_WARNING if $latitude is outside the range [-80,84] for
	 *  which the UTM projection is defined
	 * @throws E_USER_NOTICE if $zone is not the correct UTM zone as defined
	 *  for $latitude and $longitude
	 * @throws E_USER_WARNING if $zone does not contain a valid UTM zone
	 *  designation
	 */
	function toUtm ($latitude, $longitude, $zone = NULL) {
		$latitude = (float)$latitude;
		if ($latitude > 90 || $latitude < -90) {
			trigger_error('illegal geographic position: latitude exceeds 90 degrees', E_USER_ERROR);
		}
		if ($latitude > 84 || $latitude < -80) {
			trigger_error('UPS coordinate system not implemented', E_USER_WARNING);
		}
		$longitude = $this->_normaliseAngle((float)$longitude);
		$isZoneDetermined = FALSE;
		if ($zone !== NULL) {
			// we have a request for a specific zone; try to deal with it
			if (preg_match('/^([1-9]|[1-5][0-9]|60)([C-HJ-NP-X])$/i', $zone, $requestedZone)) {
				$isZoneDetermined = TRUE;
				$this->_setHemisphere($requestedZone[2], $latitude);
				// report any problems to the client
				$longitude = $this->_normaliseAngle($longitude, $this->_centralMeridian($zone));
				$recommendedZone = $this->_zone($latitude, $longitude);
				if ($requestedZone[0] != $recommendedZone) {
					trigger_error('position outside of defined UTM zone '.$recommendedZone, E_USER_NOTICE);
				}
			}
			else {
				trigger_error('requested UTM zone could not be interpreted; determining zone automatically', E_USER_WARNING);
			}
		}
		if (! $isZoneDetermined) {
			$zone = $this->_zone($latitude, $longitude);
			$this->_setHemisphere($zone, $latitude);
		}
		$centralMeridian = $this->_centralMeridian($zone);
		$position = $this->_convertGeographicToTm($latitude, $longitude - $centralMeridian);
		$position['zone'] = $zone;
		return $position;
	}
	
	
	
	/**
	 * Calculates actual position from a projected point
	 * (projection-inverse). Projection paramters and ellipsoid are to be
	 * set as instance variables. The formulas used are those given in
	 * Snyder's USGS Professional Paper 1395, pages 48 ff.
	 * 
	 * Interpreting Snyder, accuracy is understood to be in the order of a
	 * metre within the defined zone or better.
	 * 
	 * @param number $easting the abscissa of the projected position
	 * @param number $northing the ordinate of the projected position
	 * @return a key-value-coded array containing the original position:
	 *      'latitude' is the position's geographical latitude
	 *      'longitude' is the position's geographical longitude
	 */
	function _convertTmToGeographic ($easting, $northing) {
		// inverse formulas
		// local variable names follow formula symbols in USGS PP 1395
		$x = $easting - $this->falseEasting;
		$y = $northing - $this->falseNorthing;
		$k₀ = $this->scaleFactor;
		$a = $this->ellipsoidSemiMajorAxis;
		$e² = $this->ellipsoidEccentricitySquared;
		
		$M = $y / $k₀;  // (8-20) : M₀ == 0 <= φ₀ == 0
		$µ = $M / ($a * (1 - $e² / 4 - $e²*$e² * 3 / 64 - $e²*$e²*$e² * 5 / 256));  // (7-19)
		$e₁ = (1 - sqrt(1 - $e²)) / (1 + sqrt(1 - $e²));  // (3-24)
		
		$φ₁ = $µ
				+ ($e₁ * 3 / 2 - $e₁*$e₁*$e₁ * 27 / 32) * sin(2 * $µ)
				+ ($e₁*$e₁ * 21 / 16 - $e₁*$e₁*$e₁*$e₁ * 55 / 32) * sin(4 * $µ)
				+ ($e₁*$e₁*$e₁ * 151 / 96) * sin(6 * $µ)
				+ ($e₁*$e₁*$e₁*$e₁ * 1097 / 512) * sin(8 * $µ);  // (3-26)
		
		$e′² = $e² / (1 - $e²);  // (8-12)
		$C₁ = $e′² * pow(cos($φ₁), 2);  // (8-21)
		$T₁ = pow(tan($φ₁), 2);  // (8-22)
		$N₁ = $a / sqrt(1 - $e² * pow(sin($φ₁), 2));  // (8-23)
		$R₁ = $a * (1 - $e²) / pow(1 - $e² * pow(sin($φ₁), 2), 1.5);  // (8-24)
		$D = $x / ($N₁ * $k₀);  // (8-25)
		
		$φ = $φ₁ - ($N₁ * tan($φ₁) / $R₁) * ($D*$D / 2
				- (5 + 3 * $T₁ + 10 * $C₁ - 4 * $C₁*$C₁ - 9 * $e′²) * $D*$D*$D*$D / 24
				+ (61 + 90 * $T₁ + 298 * $C₁ + 45 * $T₁*$T₁ - 252 * $e′² - 3 * $C₁*$C₁) * $D*$D*$D*$D*$D*$D / 720);  // (8-17)
		$λ = ($D - (1 + 2 * $T₁ + $C₁) * $D*$D*$D / 6
				+ (5 - 2 * $C₁ + 28 * $T₁ - 3 * $C₁*$C₁ + 8 * $e′² + 24 * $T₁*$T₁) * $D*$D*$D*$D*$D / 120) / cos($φ₁);  // (8-18) : λ₀ == 0
		
		return array('latitude' => $φ * 180 / M_PI, 'longitude' => $λ * 180 / M_PI);
	}
	
	
	
	/**
	 * Converts a given UTM position to a geographic position. This method
	 * achieves metre accuracy (approximately).
	 * 
	 * Note that this method changes the value of the $falseNorthing
	 * instance variable as a side-effect.
	 * 
	 * @param number $easting the position's UTM easting
	 * @param number $northing the position's UTM northing
	 * @param string $zone the position's UTM zone
	 * @return a key-value-coded array containing the geographic position:
	 *      'latitude' is the position's geographical latitude
	 *      'longitude' is the position's geographical longitude
	 * @throws E_USER_ERROR if conversion results in a latitude outside of
	 *  the defined range [-90,90]
	 */
	function toGeographic ($easting, $northing, $zone) {
		$this->_setHemisphere($zone, NULL, (float)$northing);
		$position = $this->_convertTmToGeographic((float)$easting, (float)$northing);
		if ($position['latitude'] > 90 || $position['latitude'] < -90) {
			trigger_error('illegal UTM position: latitude exceeds 90 degrees', E_USER_ERROR);
		}
		$position['longitude'] = $this->_normaliseAngle($position['longitude']) + $this->_centralMeridian($zone);
		return $position;
	}
	
	
	
	/**
	 * Converts a given decimal degree value to degrees, minutes, seconds.
	 * 
	 * @param number $decimalDegrees the decimal degree value
	 * @return a key-value-coded array containing the DMS value:
	 *      'sign' is the sign of the $decimalDegrees value
	 *      'degrees' is the unsigned integer degrees part
	 *      'minutes' is the unsigned integer minutes part
	 *      'seconds' is the unsigned double seconds part
	 */
	function toDms ($decimalDegrees) {
		$dms = array();
		$dms['sign'] = ($decimalDegrees < 0) ? -1 : (($decimalDegrees > 0) ? 1 : 0);
		$decimalDegrees = abs($decimalDegrees);
		$dms['degrees'] = (int)$decimalDegrees;
		$decimalDegrees = ($decimalDegrees - $dms['degrees']) * 60;
		$dms['minutes'] = (int)$decimalDegrees;
		$decimalDegrees = ($decimalDegrees - $dms['minutes']) * 60;
		$dms['seconds'] = $decimalDegrees;
		return $dms;
	}
	
	
	
	/**
	 * Converts a given decimal degree value to degrees and decimal minutes.
	 * 
	 * @param number $decimalDegrees the decimal degree value
	 * @return a key-value-coded array containing the degrees-minutes value:
	 *      'sign' is the sign of the $decimalDegrees value
	 *      'degrees' is the unsigned integer degrees part
	 *      'minutes' is the unsigned double minutes part
	 */
	function toDegreesMinutes ($decimalDegrees) {
		$degreesMinutes = array();
		$degreesMinutes['sign'] = ($decimalDegrees < 0) ? -1 : (($decimalDegrees > 0) ? 1 : 0);
		$decimalDegrees = abs($decimalDegrees);
		$degreesMinutes['degrees'] = (int)$decimalDegrees;
		$decimalDegrees = ($decimalDegrees - $degreesMinutes['degrees']) * 60;
		$degreesMinutes['minutes'] = $decimalDegrees;
		return $degreesMinutes;
	}
	
	
	
	/**
	 * Converts a given degrees, minutes, seconds value to decimal degrees.
	 * The 'seconds' part may be missing.
	 * 
	 * @param a key-value-coded array containing the DMS value:
	 *      'sign' is the sign that the result should have
	 *      'degrees' is the decimal degrees part
	 *      'minutes' is the decimal minutes part
	 *      'seconds' is the decimal seconds part
	 * @return number $decimalDegrees the decimal degree value
	 * @throws E_USER_ERROR if the 'degrees' or 'minutes' keys are missing
	 *  from the $dms array
	 */
	function toDecimalDegrees ($dms) {
		if (! array_key_exists('degrees', $dms) || ! array_key_exists('minutes', $dms)) {
			trigger_error('require degrees and minutes in DMS value', E_USER_ERROR);
		}
		$decimalDegrees = $dms['degrees'] + $dms['minutes'] / 60 + @$dms['seconds'] / 3600;
		if (@$dms['sign'] < 0) {
			$decimalDegrees *= -1;
		}
		return $decimalDegrees;
	}
	
}

?>
