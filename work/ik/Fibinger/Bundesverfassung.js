/* 
 * by Arne Johannessen
 * last modified 2009-07-03
 */



// classes definition
var defaultClasses = [
	{lowerLimit: 90, upperLimit: 100, color: '#050'},
	{lowerLimit: 80, upperLimit:  90, color: '#180'},
	{lowerLimit: 70, upperLimit:  80, color: '#3b0'},
	{lowerLimit: 60, upperLimit:  70, color: '#6d0'},
	{lowerLimit: 50, upperLimit:  60, color: '#bf0'},
	{lowerLimit: 40, upperLimit:  50, color: '#ff0'},
	{lowerLimit: 30, upperLimit:  40, color: '#fc0'},
	{lowerLimit: 20, upperLimit:  30, color: '#f90'},
	{lowerLimit: 10, upperLimit:  20, color: '#f60'},
	{lowerLimit:  0, upperLimit:  10, color: '#e40'}
];

var svgDocument;  // reference to the SVG graphic



/**
 * This function is to be called when the onLoad event occurs. It loads
 * the XML data file and uses it to change the map graphic according to
 * the actual data.
 */
function init () {
	
	// obtain a reference to the SVG document
	var objectElement = document.getElementById('svgMap');
	if (objectElement.contentDocument && objectElement.contentDocument.getElementById) {
		svgDocument = objectElement.contentDocument;
	}
	else {
		// this for compatibility with legacy User Agents
		svgDocument = objectElement.getSVGDocument();
	}
	
	// get the XML reference by loading the XML file through an XMLHttpRequest
	loadXmlDocument('XML/data.xml', function (xmlDocument) {
		var data = xmlDocument.documentElement;
		
		// after the XML was loaded successfully, apply its data to the map
		var classes = outputChoropleths(data);
		outputLegend(classes);
		outputMapText(data);
	});
}



/**
 * This function creates the text on the map.
 */
function outputChoropleths (xmlDocument) {
	
	var classes = calculateClasses(xmlDocument);
	
	// iterate through all datasets in the XML document
	for (var i = 0; i < xmlDocument.childNodes.length; i++) {
		var data = xmlDocument.childNodes[i];
		if (! data || ! data.tagName || data.tagName != 'dataset') {
			continue;
		}
		
		// obtain a reference to the map area matching the current dataset
		var area = svgDocument.getElementById(data.getAttribute('id'));
		if (! area) {
			continue;  // ID not found, move on to next dataset
		}
		
		// retrieve classed value from current dataset
		var ratio = data.getAttribute('yayRatio');
		
		// find the matching classification for current dataset
		var classColor = '#cccccc';  // default value (no matching class)
		for (var j = 0; j < classes.length; j++) {
			if ((ratio < classes[j].upperLimit || j == 0) && ratio >= classes[j].lowerLimit) {
				classColor = classes[j].color;  // matching class found
				break;
			}
		}
		
		// set map area color according to classification
		area.style.setProperty('fill', classColor, '');
	}
	
	return classes;  // return classes as calculated above so that they can be re-used for the legend
}



/**
 * This function calculates the classes to be used for the choropleth map.
 * It returns an array with objects containing all relevant information
 * both for the legend and for the representation on the map.
 * 
 * (Note that this function peforms poorly if very many datasets are
 * classified into very few classes. If this was undesired, this function's
 * client would need to detect this by counting the items in each class;
 * at that point, a different set of classes with more appropriate limits
 * can be assigned to the 'defaultClasses' global variable and this
 * function then called again to calculate the classes for the new limits.
 * However, this doesn't appear to be a problem for the datasets on the
 * 'Neue Bundesverfassung' issue.)
 */
function calculateClasses (xmlDocument) {
	
	// prepare fidning minimum and maximum values
	var maximum = 0;
	var minimum = 100;
	
	// iterate through all datasets in the XML document
	for (var i = 0; i < xmlDocument.childNodes.length; i++) {
		var data = xmlDocument.childNodes[i];
		if (! data || ! data.tagName || data.tagName != 'dataset') {
			continue;
		}
		
		// retrieve the value to be classified from current dataset
		var ratio = data.getAttribute('yayRatio');
		
		// determine minimum and maximum values so far
		maximum = Math.max(maximum, ratio);
		minimum = Math.min(minimum, ratio);
	}
	
	// remove any unused classes on the upper and lower ends
	var classes = [];
	for (var i = 0; i < defaultClasses.length; i++) {
		if (defaultClasses[i].upperLimit >= minimum && defaultClasses[i].lowerLimit <= maximum) {
			classes[classes.length] = defaultClasses[i];
		}
	}
	
	// adjust upper-most and lower-most limits
	classes[0].upperLimit = Math.ceil(maximum * 10) / 10;
	classes[classes.length - 1].lowerLimit = Math.floor(minimum * 10) / 10;
	
	return classes;
}



/**
 * This function creates the text on the map.
 */
function outputLegend (classes) {
	
	// 'Klassen' is the SVG container element for the legend boxes
	// 'beschriftung' is the SVG container element for the legend text
	var legendBoxContainer = svgDocument.getElementById('Klassen');
	var legendTextContainer = svgDocument.getElementById('beschriftung');
	if (! legendBoxContainer || ! legendTextContainer) {
		return;
	}
	
	// we create the individual legend items by cloning a prototype
	var boxPrototype = legendBoxContainer.firstChild;
	var textPrototype = legendTextContainer.firstChild;
	
	// iterate through all the classes
	for (var i = 0; i < classes.length; i++) {
		var theClass = classes[i];
		
		// create legend box
		var box = boxPrototype.cloneNode(true);
		box.setAttribute('y', 30 + i * 20);
		box.style.setProperty('fill', theClass.color, '');
		legendBoxContainer.appendChild(box);
		
		// create legend text
		var text = textPrototype.cloneNode(true);
		var below = (i > 0) ? '< ' : '';  // limits shouldn't overlap each other
		text.firstChild.data = theClass.lowerLimit + ' % bis ' + below + theClass.upperLimit + ' %';
		legendTextContainer.appendChild(text);
	}
	
	// we need to remove the prototypes because they are visible by default
	boxPrototype.parentNode.removeChild(boxPrototype);
	textPrototype.parentNode.removeChild(textPrototype);
}



/**
 * This function creates the text on the map.
 */
function outputMapText (xmlDocument) {
	
	// 'mapText' is the SVG container element for the text to be placed on the map itself
	var textContainer = svgDocument.getElementById('mapText');
	if (! textContainer) {
		return;
	}
	
	// we create the individual texts by cloning a prototype
	var textOutlinePrototype = textContainer.firstChild;
	var textPrototype = textOutlinePrototype.nextSibling;
	
	// iterate through all datasets in the XML document
	for (var i = 0; i < xmlDocument.childNodes.length; i++) {
		var data = xmlDocument.childNodes[i];
		if (! data || ! data.tagName || data.tagName != 'dataset') {
			continue;
		}
		
		// retrieve the text and its position from current dataset
		var x = data.getAttribute('centerX');
		var y = data.getAttribute('centerY');
		var textContent = data.getAttribute('id');
		
		// create text outline (used for masking)
		var textOutline = textOutlinePrototype.cloneNode(true);
		textOutline.setAttribute('x', x);
		textOutline.setAttribute('y', y);
		textOutline.firstChild.data = textContent;
		textContainer.appendChild(textOutline);
		
		// create text (after the outline so it is on top of it)
		var text = textPrototype.cloneNode(true);
		text.setAttribute('x', x);
		text.setAttribute('y', y);
		text.firstChild.data = textContent;
		textContainer.appendChild(text);
	}
}



/**
 * This function loads an external XML file from a local or remote URI.
 * The 'xmlDocumentDidLoad' function is called on successful completion.
 */
function loadXmlDocument (xmlDocumentUri, xmlDocumentDidLoad) {
	var httpRequest = new XMLHttpRequest();
	if (window.ActiveXObject) {
		try {  // work around issue with IE not loading local files
			httpRequest = new ActiveXObject("Microsoft.XMLHTTP")
		}
		catch (exception) {
			// silently ignore this condition
		}
	}
	httpRequest.onreadystatechange = function () {
		if (httpRequest.readyState == 4 && (httpRequest.status == 200 || httpRequest.status == 0)) {
			// the document was successfully retrieved
			if (httpRequest.responseXML && httpRequest.responseXML.documentElement) {
				xmlDocumentDidLoad(httpRequest.responseXML);
			}
			else {
				// the XML couldn't be parsed even though the retrieval was successful; this
				// happens in IE when parsing local XML files, so let's try a workaround
				try {
					var xmlParser = new ActiveXObject("Microsoft.XMLDOM");
					xmlParser.async = false;
					xmlParser.loadXML(httpRequest.responseText);
					if (xmlParser.documentElement) {
						xmlDocumentDidLoad(xmlParser);
					}
					else {
						throw 'XML Parse Error';
					}
				}
				catch (exception) {
					alert('Die XML-Datendatei konnte nicht gelesen werden.');
				}
			}
		}
	};
	
	httpRequest.open('GET', xmlDocumentUri);
	httpRequest.send(null);
}



// define the handler for the 'onLoad' event
window.onload = function () {
	setTimeout(init, 400);  // :FIX: iCab sometimes doesn't load the SVG fast enough, so svgDocument is actually an HTMLDocument instance (about:blank) -aj3 2010-09
}
