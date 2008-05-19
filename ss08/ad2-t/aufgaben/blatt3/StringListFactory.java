/* $Id: StringListFactory.java,v 1.1 2008-05-19 04:44:47 aj3 Exp $
 * by Arne Johannessen
 * Faculty of Geomatics, Hochschule Karlsruhe - Technik und Wirtschaft
 */


import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;


/**
 * @author <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/">Arne Johannessen</A>
 * @version $Revision: 1.1 $
 */
class StringListFactory {
	
	
	// ein paar Kommentare kommen noch spaeter...
	
	
	LinkedList getColourList () {
		return COLOURS;
	}
	
	
	LinkedList createStringList () {
		return createStringList(12, 2);
	}
	
	
	
//	@SuppressWarnings("unchecked")  // uncomment this line in Java 1.5
	LinkedList createStringList (final int size, final int length) {
		final LinkedList list = new LinkedList();
		for (int i = 0; i < size; i++) {
			list.add(randomString(length));
		}
		lastList = list;
		return list;
	}
	
	
	
	String pickRandomString (final List list) {
		return (String)list.get((int)(Math.random() * list.size()));
	}
	
	
	
	String pickRandomString () {
		if (lastList == null) {
			throw new IllegalStateException("erst createStringList(int,int) aufrufen!");
		}
		return pickRandomString(lastList);
	}
	
	
	
	private String randomString (final int length) {
		StringBuffer buffer = new StringBuffer(length);
		for (int i = 0; i < length; i++) {
			buffer.append((char)(Math.random() * 26 + 0x61));
		}
		return buffer.toString();
	}
	
	
//	@SuppressWarnings("unchecked")  // uncomment this line in Java 1.5
	StringListFactory () {
		String[] colours = new String[] {"red", "green", "blue", "cyan", "magenta", "yellow", "black", "grey", "white"};
		COLOURS = new LinkedList();
		COLOURS.addAll(Arrays.asList(colours));
	}
	
	private final LinkedList COLOURS;
	
	private LinkedList lastList = null;
	
}
