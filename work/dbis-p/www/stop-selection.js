/* $Id: stop-selection.js 2008-07-08 aj3 $
 * encoding us-ascii
 * 
 * Interactive adapter of Google Maps to the database lab at the
 * University of Applied Sciences, Karlsruhe.
 * 
 * Copyright (c) 2008 Arne Johannessen, Volker von Nathusius
 * All rights reserved. See LICENSE for details.
 */





/* ===== General ===== */

/* define some document-global variables */

var googleMapsEnabled = GBrowserIsCompatible();

var selectionOverlay;

var dataOverlays = [];

var map;

var zoomControl;



/* Default map view setup. Change this for using a different city. */
function resetMap () {
	var origin = new GLatLng(1.356, 103.847);
	map.setCenter(origin, 11, G_PHYSICAL_MAP);
}





/* ===== Stop Selection Support ===== */

/* Standard window.onload handler. */
function load () {
	
	init("Click on the map to select a stop marked thus: <IMG SRC=\"images/stop.png\" ALT=\"\" CLASS=\"stoplegend\">");
	
	// add an overlay showing all stops
	GDownloadUrl("stop-selection.php", function(data, responseCode) {
		addStopsOverlay(map, data, responseCode);
	});
	
}



/* Alternative window.onload handler,
 * avoiding immediate display of all stops.
 */
function init (legendabove) {
	
	if (! googleMapsEnabled) {
		return;
	}
	
	if (document.getElementById("mapdialog")) {
		initModalMap();
	}
	
	// break out of "noscript" environment if necessary
	if (googleMapsEnabled && top.location.href.search("&noscript=1") > 0) {
		top.location.href = "?";
	}
	
	// create map
	document.getElementById("selectionmap").innerHTML += "<P ID=\"standbymsg\"><EM>Loading Google Maps... <IMG SRC=\"images/chasing-arrows.gif\" ALT=\"\"></EM></P>";
	map = new GMap2(document.getElementById("selectionmap"));
	zoomControl = new GSmallZoomControl();
	map.addControl(zoomControl);
	map.addControl(new GScaleControl());
	resetMap();
	
	// set up map for stop selection
	document.getElementById("legendabove").innerHTML = legendabove;
	var clickListener = GEvent.addListener(map, "click", mapClickListener);
}



/* event handler for parsing a list of stops and displaying these as markers on the map
 */
function addStopsOverlay (map, data, responseCode) {
	
	// only proceed if the HTTP status code says "OK"
	if (responseCode != 200) {
		console.info("Unexpected HTTP status code "+responseCode+".");
		document.getElementById("out").innerHTML = "An unknown error occurred.";
		return;
	}
	
	// parse the XML
	var xml = GXml.parse(data);
	var markers = xml.documentElement.getElementsByTagName("stop");
	
	// only proceed if the XML list did contain some stops
	if (markers.length == 0) {
		document.getElementById("out").innerHTML = "<P>There are no stops. (DB corrupted?)</P>";
		return;
	}
	
	// display one marker for each stop
	var stopIcon = new MarkerIcon("stop");
	for (var i = 0; i < markers.length; i++) {
		
		// get this stop's properties
		var stopName = GXml.value(markers[i]);
		var stopPosition = new GLatLng(parseFloat(markers[i].getAttribute("latitude")), parseFloat(markers[i].getAttribute("longitude")));
		
		// display stop as marker on the map
		var options = {title: stopName, icon: stopIcon, clickable: false};
		var stopMarker = new GMarker(stopPosition, options);
		map.addOverlay(stopMarker);
	}
}



/* event handler for a single click on the map
 */
var mapClickListener = function (overlay, point) {
	if (point) {
		// make XMLHttpRequest for nearest stop in the vicinity of the click position
		var uri = "stop-selection.php?latitude="+point.lat()+"&longitude="+point.lng()+"&range=1500";
		GDownloadUrl(uri, function (data, responseCode) {
			receivedNearestStop(point, data, responseCode);
		});
	}
};



/* event handler for displaying the nearest stop as marker on the map
 */
function receivedNearestStop (point, data, responseCode) {
	
	// clean up any existing markers first
	if (selectionOverlay != null) {
		map.removeOverlay(selectionOverlay);
	}
	
	// only proceed if the HTTP status code says "OK"
	if (responseCode != 200) {
		console.info("Unexpected HTTP status code "+responseCode+".");
		document.getElementById("out").innerHTML = "An unknown error occurred.";
		return;
	}
	
	// parse the XML
	var xml = GXml.parse(data);
	var markers = xml.documentElement.getElementsByTagName("stop");
	
	// only proceed if the XML list did contain some stops
	if (markers.length == 0) {
		document.getElementById("out").innerHTML = "<P>There are no MRT stops nearby to where you clicked.</P>";
		return;
	}
	
	var resultCaption = "<P>Your selection: <STRONG CLASS=\"stoplegend\"><IMG SRC=\"images/stop.selected.png\" ALT=\"\"> "+GXml.value(markers[0])+"</STRONG></P>";
	retrieveAndPrintUri("?stopname="+encodeURIComponent(GXml.value(markers[0])), resultCaption);
	
	var i = 0;
	
	// get this stop's properties
	var stopName = GXml.value(markers[i]);
	var stopPosition = new GLatLng(parseFloat(markers[i].getAttribute("latitude")), parseFloat(markers[i].getAttribute("longitude")));
	
	// display stop as marker on the map
	selectionOverlay = new GMarker(stopPosition, new MarkerOptions(stopName, new MarkerIcon("stop.selected"), 0));
	map.addOverlay(selectionOverlay);
	
	// define event listeners for clicks on this stop
	GEvent.addListener(selectionOverlay, "click", mapClickListener);
	
}





/* ===== Results Dispay Support ===== */

/* Retrieve a given URI, display the result in the "out" container
 * and execute all included SCRIPT elements (if any).
 */
function retrieveAndPrintUri (uri, resultCaption) {
	if (! document.getElementById("out")) {
		console.info("HTML output element ID \"out\" not found.");
		return;
	}
	
	// print an HTML form to submit the current selection
	// since the XML list is supposed to be sorted we take its first element as "current" (nearest) selection
	GDownloadUrl(uri, function(data, responseCode) {
		if (responseCode == 200) {  // OK
			document.getElementById("out").innerHTML = resultCaption+"\n"+data;
			var embeddedScripts = document.getElementById("out").getElementsByTagName("SCRIPT");
			for (var i = 0; i < embeddedScripts.length; i++) {
				eval(embeddedScripts[i].innerHTML);
			}
		}
		else {  // unexpected HTTP status
			console.info("HTTP status code "+responseCode+" on GET "+uri);
			document.getElementById("out").innerHTML = "<P>An unknown error occurred.</P>";
		}
	});
	
}



/* Displays the given list of markers as query result
 * on the existing map in a conspicuous way.
 */
function displayResultData (markerData) {
	
	clearResultData();
	
	var markerIcon = new MarkerIcon("stop.marker");
	for (var i = 0; i < markerData.length; i++) {
		var markerOptions = new MarkerOptions(markerData[i].name, markerIcon, -1);
		var markerPosition = new GLatLng(markerData[i].latitude, markerData[i].longitude);
		var markerOverlay = new GMarker(markerPosition, markerOptions);
		map.addOverlay(markerOverlay);
		dataOverlays[dataOverlays.length] = markerOverlay;
	}
	
	// add legend icon to HTML
	var tableRows = document.getElementsByTagName("TBODY")[0].getElementsByTagName("TR");
	for (var i = 0; i < tableRows.length; i++) {
		var firstTdElement = tableRows[i].getElementsByTagName("TD")[0];
		firstTdElement.innerHTML = "<IMG SRC=\"images/stop.marker.png\" ALT=\"\" CLASS=\"stoplegend\"> "+firstTdElement.innerHTML;
	}
}



/* Remove the result display from displayResultData(), if any.
 */
function clearResultData () {
	// clear old data overlays, if any
	for (var i = 0; i < dataOverlays.length; i++) {
		map.removeOverlay(dataOverlays[i]);
	}
	dataOverlays = [];
}





/* ===== Modal Map Dialog support ===== */

/* Initialise map dialog. To be called from window.onload.
 */
function initModalMap () {
	// enable the buttons supposed to show the modal map dialog
	var buttons = document.getElementsByTagName("BUTTON");
	for (var i = 0; i < buttons.length; i++) {
		buttons[i].disabled = false;
	}
	
	// insert DOM structure for modal map dialog
	document.getElementById("mapdialog").innerHTML = "<DIV ID=\"dialogcontent\"><DIV ID=\"legendabove\"></DIV><DIV ID=\"selectionmap\"></DIV><DIV ID=\"cancelbutton\"><INPUT TYPE=\"button\" onClick=\"hideModalMapCancel()\" VALUE=\"Cancel\"> <INPUT TYPE=\"button\" onClick=\"resetMap()\" VALUE=\"Reset Map\"></DIV><DIV ID=\"out\"></DIV></DIV>";
}



/* Shows the map dialog and stores a named form element as target
 * for the stop to be selected.
 */
function showModalMap (target) {
	document.getElementById("mapdialog").style.visibility = "visible";
	document.mapTarget = target;
	map.removeOverlay(selectionOverlay);
	document.getElementById("out").innerHTML = "";
}



/* Closes the map dialog without making changes to the HTML form.
 */
function hideModalMapCancel () {
	document.getElementById('mapdialog').style.visibility = 'hidden';
}



/* Closes the map dialog, applying the selected stop to the HTML form target.
 */
function hideModalMapApply (stopname) {
	document.getElementById(document.mapTarget).value = stopname;
	document.getElementById("mapdialog").style.visibility = "hidden";
	document.getElementById(document.mapTarget).focus();
	document.getElementById(document.mapTarget).select();
}





/* ===== Prototypes ===== */

/* MarkerOptions "class" (actually, prototype function).
 * Objects are usable with the informal GMarkerOptions interface
 * in Google Maps.
 * 
 * Usage:
 * map.addOverlay(new GMarker(position, new MarkerOptions(stopName, new MarkerIcon("stop"), 0)));
 */
function MarkerOptions (title, icon, zIndex) {
	this.title = title;
	this.icon = icon;
	this.clickable = false;
	this.zIndex = zIndex;
	this.zIndexProcess = function (marker, b) {
		return this.zIndex;
	};
}



/* MarkerIcon "class" (actually, prototype function).
 * Objects represent icons to be used as markers in Google Maps.
 * 
 * Usage:
 * var options = {icon: new MarkerIcon("stop")};
 * map.addOverlay(new GMarker(position, options));
 */
function MarkerIcon (name) {
	switch (name) {
		case "stop.marker":
			this.iconSize = new GSize(14, 14);
			this.image = 'images/stop.marker.png';
			this.iconAnchor = new GPoint(6, 6);
			break;
		
		case "stop":
			this.iconSize = new GSize(14, 14);
			this.image = 'images/stop.png';
			this.iconAnchor = new GPoint(6, 6);
			break;
		
		case "stop.selected":
			this.iconSize = new GSize(14, 14);
			this.image = 'images/stop.selected.png';
			this.iconAnchor = new GPoint(6, 6);
			break;
		
		case "pin+shadow":
			this.iconSize = new GSize(12, 20);
			this.image = 'images/pin.flat.png';
			this.shadowSize = new GSize(22, 20);
			this.shadow = 'images/pin.shadow.png';
			this.iconAnchor = new GPoint(6, 20);
			break;
		
		default:
			console.info("Unknown icon name \""+name+"\".");
		case "pin":
			this.iconSize = new GSize(22, 20);
			this.image = 'images/pin.png';
			this.iconAnchor = new GPoint(6, 20);
			break;
	}
}

// define "superclass" (actually, the prototype) of MarkerIcon
MarkerIcon.prototype = new GIcon();
