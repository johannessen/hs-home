/* $Id: MutableLinearList.java,v 1.1 2007-12-10 09:45:11 arne Exp $
 * by Arne Johannessen
 * Faculty of Geomatics, Hochschule Karlsruhe - Technik und Wirtschaft
 */


public class MutableLinearList extends LinearList {
	
	public void addInFront (int value) {
		LinearList firstElement = new LinearList();
		firstElement.head = value;
		firstElement.tail = this;
	}
	
	public int removeFromFront () {
		this.head = this.tail.head;
		this.tail = this.tail.tail;
	}
	
	public int firstElement () {
		return this.head;
	}
	
	public int size () {
		if (this.tail == null) {
			return 1;  // Restliste leer: ich bin das einzige Element
		}
		MutableLinearList tail = (MutableLinearList)this.tail;  // Type-Cast ermoeglicht Zugriff auf size()
		return tail.size() + 1;
	}
	
}
