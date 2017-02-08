/* 
 * $Id: Aufgabe7_3.java $ 
 * Programmiersprachen I
 * 
 * Encoding: us-ascii
 * 
 * Created by Arne Johannessen on 2005-12-04.
 */

package de.thaw.ps1.blatt7;

import de.thaw.ps1.blatt2.Aufgabe2_2;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Iterator;




/**
 * Blatt 7, Aufgabe 3: Der wilde Westen.
 * @see <a href="http://www.christophweser.de/java/aufgaben/WS2005/Blatt7.pdf">7. &Uuml;bungsblatt PS1</a>
 * @author Arne Johannessen
 */
public class Aufgabe7_3 {
	
	/** Driver method. Calls {@link WilderWesten#main()}. */
	public static void main (String[] args) {
		WilderWesten.main();
	}
	
}




/** Modelliert den Wilden Westen (<EM>stark</EM> vereinfacht). */
class WilderWesten {
	
	
	/** Die Bev&ouml;lkerung des Wilden Westens. */
	private static Collection population = null;
	
	
	// static constructor section
	static {
		WilderWesten.population = new LinkedHashSet();
	}
	
	
	/**
	 * Gibt die Bev&ouml;lkerungsgr&ouml;&szlig;e zur&uuml;ck.
	 * @return Anzahl der Elemente in {@link #population population}
	 */
	static int populationSize () {
		return WilderWesten.population.size();
	}
	
	/**
	 * Erstellt eine Reihe Menschen im Wilden Westen.  Es wird je einen Indianer
	 * und Gauner, eine jeweils zuf&auml;llige Menge Gauner und Normalos sowie
	 * einen Chefgauner erzeugt und f&uuml;r die <Q>Vorstellung</Q> gesorgt,
	 * d. h. jeder Mensch informiert textuell &uuml;ber sich selbst. Alle
	 * Menschen erhalten zuf&auml;llige Eigenschaften.
	 */
	static void main () {
		WilderWesten.population.add(new Indianer(Normalo.randomName()));
		WilderWesten.population.add(new Ranger(Normalo.randomName()));
		for (int index = (int)Math.floor(Math.random() * 4.0) + 2; index > 0; index--) {  // completely arbitrary
			WilderWesten.population.add(new Gauner(Normalo.randomName()));
		}
		for (int index = (int)Math.floor(Math.random() * 3.0) + 2; index > 0; index--) {  // completely arbitrary
			WilderWesten.population.add(new Normalo(Normalo.randomName()));
		}
		WilderWesten.population.add(Chefgauner.instance("Santa Maria"));
		
		Iterator iterator = WilderWesten.population.iterator();
		while (iterator.hasNext()) {
			((Normalo)iterator.next()).infoUeberMich();
		}
	}
	
}




/** Modelliert einen <Q>Normalo</Q>-Menschen. */
class Normalo extends BloodTypeCarrier {
	
	
	/** Name des Menschen. */
	private String name = null;
	
	
	/**
	 * Erzeugt einen normalen Menschen (<Q>Normalo</Q>) mit Namen.
	 * @param name der Name des Menschen
	 */
	Normalo (String name) {
		this.name = name;
		super.blutgruppe = BloodType.random();
	}
	
	
	/**
	 * Erzeugt einen normalen Menschen (<Q>Normalo</Q>) mit Namen und Blutgruppe.
	 * @param name der Name des Menschen
	 * @param blutgruppe die Blutgruppe des Menschen
	 */
	Normalo (String name, BloodType blutgruppe) {
		this.name = name;
		super.blutgruppe = blutgruppe;
	}
	
	
	/**
	 * Gibt Informationen &uuml;ber diesen Menschen auf die Standardausgabe aus.
	 * Dies ist quasi eine <Q>Vorstellung<Q> dieses Meschens. Diese Methode ruft
	 * haupts&auml;chlich {@link #toString() toString()} auf.
	 */
	void infoUeberMich () {
		System.out.println(this.toString());
		System.out.println();
	}
	
	
	/**
	 * Gibt die Bev&ouml;lkerungsgr&ouml;&szlig;e zur&uuml;ck.
	 * @return Anzahl der Elemente in {@link WilderWesten#population population}
	 * im WildenWesten
	 */
	int wilderWestenBevoelkerung () {
		return WilderWesten.populationSize();
	}
	
	
	/**
	 * Zugriffsmethode
	 * @return den Namen dieses Menschen
	 * @see #name
	 */
	String name () {
		return this.name;
	}
	
	
	/**
	 * Gibt eine <Q>Vorstellung</Q> dieses Menschens zur&uuml;ck, die aus allen
	 * relevanten allgemeinen Informationen &uuml;ber ihn besteht.
	 * @return einen mehrzeiligen String mit Informationen &uuml;ber diesen
	 * Menschen
	 */
	public String toString () {
		StringBuffer buffer;
		String namePrefix;
		
		namePrefix = "["+this.name+":] ";
		buffer = new StringBuffer();
		buffer.append(namePrefix);
		buffer.append("Mein Name lautet ");
		buffer.append(this.name);
		buffer.append(".\n");
		buffer.append(namePrefix);
		buffer.append("Ich bin ");
		buffer.append((this instanceof Chefgauner) ? "der " : "ein ");
		buffer.append(this.getClass().getName());
		buffer.append(".\n");
		buffer.append(namePrefix);
		buffer.append("Meine Blutgruppe ist ");
		buffer.append(this.blutgruppe);
		buffer.append(".\n");
		buffer.append(namePrefix);
		buffer.append("Ich wei\u00df, dass im Wilden Westen ");
		buffer.append(this.wilderWestenBevoelkerung());
		buffer.append(" Menschen leben.");
		return buffer.toString();
	}
	
	
	/**
	 * Gibt einen zuf&auml;lligen Namen zur&uuml;ck. Der Name ist
	 * zuf&auml;lligerweise mehr oder weniger aussprechbar. Es ist nicht
	 * empfohlen, die von dieser Methode generierten Namen zur Benennung von
	 * reellen (physischen) Nachkommen zu verwenden.
	 * @return einen kurzen aussprechbaren String
	 */
	static String randomName () {
		StringBuffer buffer = null;
		int firstVowel;
		
		buffer = new StringBuffer((int)Math.floor(Math.random() * 6.0) + 3);
		firstVowel = (int)Math.round(Math.random());
		buffer.append(Character.toTitleCase(Normalo.randomCharacter(firstVowel == 0)));
		for (int index = 1; index < buffer.capacity(); index++) {
			buffer.append(Normalo.randomCharacter(firstVowel == index % 2));
		}
		return buffer.toString();
	}
	
	
	/**
	 * Erzeugt einen zuf&auml;lligen Buchstaben.
	 * @param isVowel bestimmt, ob der zu erzeugende Buchstabe ein Vokal (nach
	 * {@link #isVowel(char) isVowel(char)} ist
	 * @return einen Vokal, falls <code>isVowel == true</code>; andernfalls
	 * einen Konsonanten
	 */
	private static char randomCharacter (boolean isVowel) {
		char character;
		
		do {
			character = (char)((int)Math.floor(Math.random() * 26.0) + (int)'a');
		} while (isVowel(character) != isVowel);
		return character;
	}
	
	
	/**
	 * Pr&uuml;ft, ob ein Buchstabe ein Vokal ist.
	 * @param character der zu pr&uuml;fende Buchstabe
	 * @return <code>true</code>, falls <code>character</code> ein Vokal ist
	 */
	private static boolean isVowel (char character) {
		switch (character) {
			case 'a':
			case 'e':
			case 'i':
			case 'o':
			case 'u':
				return true;
			
			default:
				return false;
		}
	}
	
}




/** Modelliert einen Indianer. */
class Indianer extends Normalo {
	
	
	/**
	 * Gibt an, ob dieser Indianer toll Spuren lesen kann.
	 */
	private boolean kannTollSpurenLesen;
	
	
	/**
	 * Standard-Konstruktor. Erstellt einen Indianer, der toll Spuren lesen
	 * kann.
	 * @param name der Name des Indianers
	 * @see Normalo#Normalo(String)
	 */
	Indianer (String name) {
		super(name);
		this.kannTollSpurenLesen = true;
	}
	
	
	/**
	 * Erstellt einen Indianer, der toll Spuren lesen kann.
	 * @param name der Name des Indianers
	 * @param blutgruppe die Blutgruppe des Indianers
	 * @see Normalo#Normalo(String,BloodType)
	 */
	Indianer (String name, BloodType blutgruppe) {
		super(name, blutgruppe);
		this.kannTollSpurenLesen = true;
	}
	
	
	/**
	 * Erstellt einen Indianer.
	 * @param name der Name des Indianers
	 * @param blutgruppe die Blutgruppe des Indianers
	 * @param kannTollSpurenLesen gibt an, ob der Indianer toll Spuren lesen kann
	 */
	Indianer (String name, BloodType blutgruppe, boolean kannTollSpurenLesen) {
		super(name, blutgruppe);
		this.kannTollSpurenLesen = kannTollSpurenLesen;
	}
	
	
	/**
	 * Zugriffsmethode.
	 * @return <code>true</code>, falls dieser Indianer toll Spuren lesen kann
	 */
	boolean kannTollSpurenLesen () {
		return this.kannTollSpurenLesen;
	}
	
	
	/**
	 * Zugriffsmethode.
	 * @param kannTollSpurenLesen gibt an, ob der Indianer toll Spuren lesen kann
	 */
	void setKannTollSpurenLesen (boolean kannTollSpurenLesen) {
		this.kannTollSpurenLesen = kannTollSpurenLesen;
	}
	
	
	/**
	 * Gibt allgemeine Informationen &uuml;ber diesen Menschen und
	 * zus&auml;tzliche Informationen &uuml;ber diesen Indianer zur&uuml;ck.
	 * @return einen mehrzeiligen String mit Informationen &uuml;ber diesen
	 * Menschen
	 * @see Normalo#toString()
	 */
	public String toString () {
		StringBuffer buffer;
		
		buffer = new StringBuffer(super.toString());
		buffer.append("\n[");
		buffer.append(super.name());
		buffer.append(":] Ich kann Spuren ");
		if (! this.kannTollSpurenLesen) {
			buffer.append("nicht ");
		}
		buffer.append("toll lesen.");
		return buffer.toString();
	}
	
}




/** Modelliert einen Ranger. */
class Ranger extends Normalo {
	
	
	/**
	 * Gibt an, ob dieser Ranger toll schie&szlig;en kann.
	 */
	boolean kannTollSchiessen; 
	
	
	/**
	 * Standard-Konstruktor. Erstellt einen Ranger, der toll schie&szlig;en kann.
	 * @param name der Name des Rangers
	 * @see Normalo#Normalo(String)
	 */
	Ranger (String name) {
		super(name);
		this.kannTollSchiessen = true;
	}
	
	
	/**
	 * Erstellt einen Ranger, der toll schie&szlig;en kann.
	 * @param name der Name des Rangers
	 * @param blutgruppe die Blutgruppe des Rangers
	 * @see Normalo#Normalo(String,BloodType)
	 */
	Ranger (String name, BloodType blutgruppe) {
		super(name, blutgruppe);
		this.kannTollSchiessen = true;
	}
	
	
	/**
	 * Erstellt einen Ranger.
	 * @param name der Name des Rangers
	 * @param blutgruppe die Blutgruppe des Rangers
	 * @param kannTollSchiessen gibt an, ob der Ranger toll schie&szlig;en kann
	 */
	Ranger (String name, BloodType blutgruppe, boolean kannTollSchiessen) {
		super(name, blutgruppe);
		this.kannTollSchiessen = kannTollSchiessen;
	}
	
	
	/**
	 * Zugriffsmethode.
	 * @return <code>true</code>, falls dieser Ranger toll schie&szlig;en kann
	 */
	boolean kannTollSchiessen () {
		return this.kannTollSchiessen;
	}
	
	
	/**
	 * Zugriffsmethode.
	 * @param kannTollSchiessen gibt an, ob der Ranger toll schie&szlig;en kann
	 */
	void setKannTollSchiessen (boolean kannTollSchiessen) {
		this.kannTollSchiessen = kannTollSchiessen;
	}
	
	
	/**
	 * Gibt allgemeine Informationen &uuml;ber diesen Menschen und
	 * zus&auml;tzliche Informationen &uuml;ber diesen Ranger zur&uuml;ck.
	 * @return einen mehrzeiligen String mit Informationen &uuml;ber diesen
	 * Menschen
	 * @see Normalo#toString()
	 */
	public String toString () {
		StringBuffer buffer;
		
		buffer = new StringBuffer(super.toString());
		buffer.append("\n[");
		buffer.append(super.name());
		buffer.append(":] Ich kann");
		if (! this.kannTollSchiessen) {
			buffer.append("nicht");
		}
		buffer.append(" toll schie\u00dfen.");
		return buffer.toString();
	}
	
}




/** Modelliert einen Gauner. */
class Gauner extends Normalo {
	
	
	/**
	 * Gibt an, wie viele tolle Verbrechen dieser Gauner begangen hat.
	 */
	int anzahlBegangenerVerbrechen;
	
	
	/**
	 * Standard-Konstruktor. Erstellt einen Gauner, der eine zuf&auml;llige Zahl
	 * Verbrechen begangen hat.
	 * @param name der Name des Gauers
	 * @see Normalo#Normalo(String)
	 */
	Gauner (String name) {
		super(name);
		this.anzahlBegangenerVerbrechen = (int)Math.round(Math.random() * Chefgauner.UNTERGRENZE_BEGANGENER_VERBRECHEN) + 2;
	}
	
	
	/**
	 * Erstellt einen Gauner, der eine zuf&auml;llige Zahl Verbrechen begangen hat.
	 * @param name der Name des Gauners
	 * @param blutgruppe die Blutgruppe des Gauners
	 * @see Normalo#Normalo(String,BloodType)
	 */
	Gauner (String name, BloodType blutgruppe) {
		super(name, blutgruppe);
		this.anzahlBegangenerVerbrechen = (int)Math.round(Math.random() * Chefgauner.UNTERGRENZE_BEGANGENER_VERBRECHEN) + 2;
	}
	
	
	/**
	 * Erstellt einen Gauner.
	 * @param name der Name des Gauners
	 * @param blutgruppe die Blutgruppe des Gauners
	 * @param anzahlBegangenerVerbrechen die Zahl der Verbrechen, die der Gauner
	 * begangen hat
	 */
	Gauner (String name, BloodType blutgruppe, int anzahlBegangenerVerbrechen) {
		super(name, blutgruppe);
		this.anzahlBegangenerVerbrechen = anzahlBegangenerVerbrechen;
	}
	
	
	/**
	 * Zugriffsmethode.
	 * @return die Zahl der Verbrechen, die dieser Gauner begangen hat
	 */
	int anzahlBegangenerVerbrechen () {
		return this.anzahlBegangenerVerbrechen;
	}
	
	
	/**
	 * Zugriffsmethode.
	 * @param anzahlBegangenerVerbrechen die Zahl der Verbrechen, die der Gauner
	 * begangen hat
	 */
	void setAnzahlBegangenerVerbrechen (int anzahlBegangenerVerbrechen) {
		this.anzahlBegangenerVerbrechen = anzahlBegangenerVerbrechen;
	}
	
	
	/**
	 * Gibt allgemeine Informationen &uuml;ber diesen Menschen und
	 * zus&auml;tzliche Informationen &uuml;ber diesen Gauner zur&uuml;ck.
	 * @return einen mehrzeiligen String mit Informationen &uuml;ber diesen
	 * Menschen
	 * @see Normalo#toString()
	 */
	public String toString () {
		StringBuffer buffer;
		
		buffer = new StringBuffer(super.toString());
		buffer.append("\n[");
		buffer.append(super.name());
		buffer.append(":] Ich habe schon ");
		buffer.append(Aufgabe2_2.numberToString(this.anzahlBegangenerVerbrechen, Aufgabe2_2.CARDINAL_NUMBER, null));
		buffer.append(" tolle Verbrechen begangen.");
		return buffer.toString();
	}
	
}




/**
 * Modelliert den Chefgauner. Diese Klasse kann nur einmal instanziiert
 * werden.
 */
class Chefgauner extends Gauner {
	
	
	/**
	 * Gibt die Mindestzahl von Verbrechen, die der <EM>Chef</EM>gauner begangen
	 * haben muss, an.
	 */
	static final int UNTERGRENZE_BEGANGENER_VERBRECHEN = 197;  // completely arbitrary
	
	
	/** Gibt an, ob der Chefgauner schon einmal verurteilt wurde. */
	boolean wurdeSchonEinmalVerurteilt;
	
	
	/** Die Instanz lt. dem <STRONG>Singleton-Pattern</STRONG> des Chefgauners. */
	static Chefgauner instance = null;
	
	
	/**
	 * Privater Default-Konstruktor. Da es sich um eine Singleton-Klasse handelt, darf
	 * der Konstruktor nicht von der Anwendung aufgerufen werden.
	 * @param name der Name des Gauners
	 * @see Normalo#Normalo(String)
	 */
	private Chefgauner (String name) {
		super(name);
		super.anzahlBegangenerVerbrechen += Chefgauner.UNTERGRENZE_BEGANGENER_VERBRECHEN;
		this.wurdeSchonEinmalVerurteilt = false;
	}
	
	
	/**
	 * Privater Konstruktor. Da es sich um eine Singleton-Klasse handelt, darf
	 * der Konstruktor nicht von der Anwendung aufgerufen werden.
	 * @param name der Name des Gauners
	 * @param blutgruppe die Blutgruppe des Gauners
	 * @see Normalo#Normalo(String,BloodType)
	 */
	private Chefgauner (String name, BloodType blutgruppe) {
		super(name, blutgruppe);
		super.anzahlBegangenerVerbrechen += Chefgauner.UNTERGRENZE_BEGANGENER_VERBRECHEN;
		this.wurdeSchonEinmalVerurteilt = false;
	}
	
	
	/**
	 * Privater Konstruktor. Da es sich um eine Singleton-Klasse handelt, darf
	 * der Konstruktor nicht von der Anwendung aufgerufen werden.
	 * @param name der Name des Gauners
	 * @param blutgruppe die Blutgruppe des Gauners
	 * @param anzahlBegangenerVerbrechen die Zahl der Verbrechen, die der Gauner
	 * begangen hat
	 * @see Gauner#Gauner(String,BloodType,int)
	 */
	private Chefgauner (String name, BloodType blutgruppe, int anzahlBegangenerVerbrechen) {
		super(name, blutgruppe, anzahlBegangenerVerbrechen);
		super.anzahlBegangenerVerbrechen += Chefgauner.UNTERGRENZE_BEGANGENER_VERBRECHEN;
		this.wurdeSchonEinmalVerurteilt = false;
	}
	
	
	/**
	 * Privater Konstruktor. Da es sich um eine Singleton-Klasse handelt, darf
	 * der Konstruktor nicht von der Anwendung aufgerufen werden.
	 * @param name der Name des Gauners
	 * @param blutgruppe die Blutgruppe des Gauners
	 * @param anzahlBegangenerVerbrechen die Zahl der Verbrechen, die der Gauner
	 * begangen hat
	 * @param wurdeSchonEinmalVerurteilt gibt an, ob der Chefgauner schon einmal
	 * verurteilt wurde
	 */
	private Chefgauner (String name, BloodType blutgruppe, int anzahlBegangenerVerbrechen, boolean wurdeSchonEinmalVerurteilt) {
		super(name, blutgruppe, anzahlBegangenerVerbrechen);
		super.anzahlBegangenerVerbrechen += Chefgauner.UNTERGRENZE_BEGANGENER_VERBRECHEN;
		this.wurdeSchonEinmalVerurteilt = wurdeSchonEinmalVerurteilt;
	}
	
	
	/**
	 * Singleton-Zugriffsmethode. Kann die Singleton-Instanz nicht erstellen,
	 * weil kein Parameter f&uuml;r den (ben&ouml;tigten) Namen existieren.
	 * @return die Singleton-Instanz dieser Klasse oder <code>null</code>, falls
	 * die Instanz noch nicht existiert; statt dieser Methode muss dazu
	 * {@link #instance(String) instance(String)} aufgerufen werden
	 */
	static final Chefgauner instance () {
		return Chefgauner.instance;
	}
	
	
	/**
	 * Singleton-Konstruktor. Erstellt die (einzige) Instanz, falls dies noch
	 * nicht geschehen ist. Nachdem die Instanz erstellt wurde, wird sie von
	 * dieser Methode nicht mehr ver&auml;ndert; eventuell &uuml;bergebene
	 * Parameter werden still ignoriert.
	 * @param name der Name des Gauners
	 * @return die Singleton-Instanz dieser Klasse
	 */
	static final Chefgauner instance (String name) {
		if (Chefgauner.instance == null) {
			Chefgauner.instance = new Chefgauner(name);
		}
		return Chefgauner.instance;
	}
	
	
	/**
	 * Singleton-Konstruktor. Erstellt die (einzige) Instanz, falls dies noch
	 * nicht geschehen ist. Nachdem die Instanz erstellt wurde, wird sie von
	 * dieser Methode nicht mehr ver&auml;ndert; eventuell &uuml;bergebene
	 * Parameter werden still ignoriert.
	 * @param name der Name des Gauners
	 * @param blutgruppe die Blutgruppe des Gauners
	 * @return die Singleton-Instanz dieser Klasse
	 * benutzt werden
	 */
	static final Chefgauner instance (String name, BloodType blutgruppe) {
		if (Chefgauner.instance == null) {
			Chefgauner.instance = new Chefgauner(name, blutgruppe);
		}
		return Chefgauner.instance;
	}
	
	
	/**
	 * Singleton-Konstruktor. Erstellt die (einzige) Instanz, falls dies noch
	 * nicht geschehen ist. Nachdem die Instanz erstellt wurde, wird sie von
	 * dieser Methode nicht mehr ver&auml;ndert; eventuell &uuml;bergebene
	 * Parameter werden still ignoriert.
	 * @param name der Name des Gauners
	 * @param blutgruppe die Blutgruppe des Gauners
	 * @param anzahlBegangenerVerbrechen die Zahl der Verbrechen, die der Gauner
	 * begangen hat
	 * @return die Singleton-Instanz dieser Klasse
	 */
	static final Chefgauner instance (String name, BloodType blutgruppe, int anzahlBegangenerVerbrechen) {
		if (Chefgauner.instance == null) {
			Chefgauner.instance = new Chefgauner(name, blutgruppe, anzahlBegangenerVerbrechen);
		}
		return Chefgauner.instance;
	}
	
	
	/**
	 * Singleton-Konstruktor. Erstellt die (einzige) Instanz, falls dies noch
	 * nicht geschehen ist. Nachdem die Instanz erstellt wurde, wird sie von
	 * dieser Methode nicht mehr ver&auml;ndert; eventuell &uuml;bergebene
	 * Parameter werden still ignoriert.
	 * @param name der Name des Gauners
	 * @param blutgruppe die Blutgruppe des Gauners
	 * @param anzahlBegangenerVerbrechen die Zahl der Verbrechen, die der Gauner
	 * begangen hat
	 * @param wurdeSchonEinmalVerurteilt gibt an, ob der Chefgauner schon einmal
	 * verurteilt wurde
	 * @return die Singleton-Instanz dieser Klasse
	 */
	static final Chefgauner instance (String name, BloodType blutgruppe, int anzahlBegangenerVerbrechen, boolean wurdeSchonEinmalVerurteilt) {
		if (Chefgauner.instance == null) {
			Chefgauner.instance = new Chefgauner(name, blutgruppe, anzahlBegangenerVerbrechen, wurdeSchonEinmalVerurteilt);
		}
		return Chefgauner.instance;
	}
	
	
	/**
	 * Zugriffsmethode.
	 * @return <code>true</code>, falls der Chefgauner schon einmal verurteilt
	 * wurde
	 */
	boolean wurdeSchonEinmalVerurteilt () {
		return this.wurdeSchonEinmalVerurteilt;
	}
	
	
	/**
	 * Zugriffsmethode.
	 * @param wurdeSchonEinmalVerurteilt gibt an, ob der Chefgauner schon einmal
	 * verurteilt wurde
	 */
	void setWurdeSchonEinmalVerurteilt (boolean wurdeSchonEinmalVerurteilt) {
		this.wurdeSchonEinmalVerurteilt = wurdeSchonEinmalVerurteilt;
	}
	
	
	/**
	 * Gibt allgemeine Informationen &uuml;ber diesen Menschen und
	 * zus&auml;tzliche Informationen &uuml;ber den Chefgauner zur&uuml;ck.
	 * @return einen mehrzeiligen String mit Informationen &uuml;ber diesen
	 * Menschen
	 * @see Normalo#toString()
	 */
	public String toString () {
		StringBuffer buffer;
		
		buffer = new StringBuffer(super.toString());
		buffer.append("\n[");
		buffer.append(super.name());
		buffer.append(":] Ich wurde ");
		buffer.append((this.wurdeSchonEinmalVerurteilt) ? "schon einmal" : "bisher noch nicht");
		buffer.append(" verurteilt.");
		return buffer.toString();
	}
	
}




/** Modelliert Blutgruppen. */
class BloodType {
	
	/** Definition einer Blutgruppengenart. */
	static final int NONE = 00;
	static final int A = 01;
	static final int B = 02;
	static final int D = 04;  // Rhesus
	static final int A1 = 010;
	static final int A2 = 020;
	private static final int ALL_GENES = BloodType.A | BloodType.B | BloodType.D | BloodType.A1 | BloodType.A2;
	
	/** Definition einer auf kombinierten Genen basierenden Blutgruppe. */
	static final int AB = BloodType.A | BloodType.B;
	static final int NULL_NEGATIVE = BloodType.NONE;
	static final int A_NEGATIVE = BloodType.A;
	static final int B_NEGATIVE = BloodType.B;
	static final int AB_NEGATIVE = BloodType.AB;
	static final int NULL_POSITIVE = BloodType.NONE | BloodType.D;
	static final int A_POSITIVE = BloodType.A | BloodType.D;
	static final int B_POSITIVE = BloodType.B | BloodType.D;
	static final int AB_POSITIVE = BloodType.AB | BloodType.D;
	
	
	/**
	 * Stellt eine Liste aller potentiell existierenden Blutgruppen dar. Da
	 * mehrere Menschen mit derselben Blutgruppe nicht jeweils eine eigene
	 * Instanz der Blutgruppe ben&ouml;tigen, werden alle existierenden
	 * Blutgruppen hier zur Wiederverwendung gesammelt.
	 */
	static BloodType[] instance = new BloodType[BloodType.ALL_GENES];
	
	
	/** Diese Blutgruppe. */
	private int bloodType = BloodType.NONE;
	
	
	/**
	 * Privater Konstruktor. Da es sich um eine erweiterte Singleton-Klasse
	 * handelt, darf der Konstruktor nicht von der Anwendung aufgerufen werden.
	 * @param bloodType die Blutgruppe der neuen Instanz
	 */
	private BloodType (int bloodType) {
		// private: extended Singleton Pattern (in place of Fly-Weight Pattern)
		this.bloodType = bloodType;
	}
	
	
	/**
	 * Singleton-Konstruktor. Erstellt eine (indizierte) Instanz, falls diese
	 * noch nicht erstellt wurde.
	 * @param bloodType die Blutgruppe der neuen Instanz
	 * @return die Blutgruppen-Instanz der spezifizierten Blutgruppe
	 * @throws IllegalArgumentException falls die &uuml;bergebene Blutgruppe
	 * nicht existieren kann
	 */
	static final BloodType instance (int bloodType) throws IllegalArgumentException {
		BloodType.validate(bloodType);
		if (instance[bloodType] == null) {
			instance[bloodType] = new BloodType(bloodType);
		}
		return instance[bloodType];
	}
	
	
	/**
	 * Zugriffsmethode.
	 * @return diese Blutgruppe
	 * @see #bloodType
	 */
	int bloodType () {
		return this.bloodType;
	}
	
	
	/**
	 * Zugriffsmethode.
	 * @param bloodType die Blutgruppe der neuen Instanz
	 * @throws IllegalArgumentException falls die &uuml;bergebene Blutgruppe
	 * nicht existieren kann
	 * @see #bloodType
	 */
	private void setBloodType (int bloodType) throws IllegalArgumentException {
		this.bloodType = BloodType.validate(bloodType);
	}
	
	
	/**
	 * &Uuml;berpr&uuml;ft, ob die angegebene Blutgruppe existieren kann.
	 * Beispielsweise w&uuml;rde eine Angabe, aus der Zugeh&ouml;rigkeit zur
	 * Sub-Gruppe A1, aber nicht zur Haupt-Gruppe A hervorgeht, als
	 * ung&uuml;ltig abgelehnt.
	 * @param bloodType die zu testende Blutgruppe
	 * @return int die Blutgruppe, falls der Test erfolgreich war
	 * @throws IllegalArgumentException, falls der Test nicht erfolgreich war
	 */
	private static int validate (int bloodType) throws IllegalArgumentException {
		if ((bloodType & BloodType.ALL_GENES) != bloodType) {
			throw new IllegalArgumentException("illegal blood group bits are being used");
		}
		if ((bloodType & BloodType.A1) > 0 && (bloodType & BloodType.A2) > 0 ||
				(bloodType & (BloodType.A1 | BloodType.A2)) > 0 && (bloodType & BloodType.A) == 0) {
			throw new IllegalArgumentException("impossible bit combination for blood group");
		}
		return bloodType;
	}
	
	
	/**
	 * Erstellt eine medizinisch gesehen m&ouml;gliche, zuf&auml;llige
	 * neue Blutgruppe anhand zweier Eltern-Blutgruppen.
	 * @param parent1 Elternteil-Blutsgruppe
	 * @param parent2 Elternteil-Blutsgruppe
	 * @return eine neue <Q>nat&uuml;rliche</Q> Blutsgruppe
	 */
	static BloodType random (BloodTypeCarrier parent1, BloodTypeCarrier parent2) {
		return BloodType.random(parent1.bloodType().bloodType(), parent2.bloodType().bloodType());
	}
	
	
	/**
	 * Erstellt eine zuf&auml;llige neue Blutgruppe.
	 * @return eine neue Blutsgruppe
	 */
	static BloodType random () {
		return BloodType.random(BloodType.ALL_GENES, BloodType.ALL_GENES);
	}
	
	
	/**
	 * Erstellt eine medizinisch gesehen m&ouml;gliche, zuf&auml;llige
	 * neue Blutgruppe anhand zweier Eltern-Blutgruppen.
	 * @param parent1BloodType Elternteil-Blutsgruppe
	 * @param parent2BloodType Elternteil-Blutsgruppe
	 * @return eine neue <Q>nat&uuml;rliche</Q> Blutsgruppe
	 */
	private static BloodType random (int parent1BloodType, int parent2BloodType) {
		int bloodType;
		
		bloodType = BloodType.A & BloodType.geneRandomness();
		if (bloodType > 0) {
			// gene A is present, so set a random sub-group
			if (BloodType.geneRandomness() > 0) {
				bloodType |= BloodType.A1;
			}
			else {
				bloodType |= BloodType.A2;
			}
		}
		bloodType |= BloodType.B & BloodType.geneRandomness();
		bloodType |= BloodType.D & BloodType.geneRandomness();
		return BloodType.instance(bloodType & (parent1BloodType | parent2BloodType));
	}
	
	
	/**
	 * Erstellt einen Zuf&auml;lligkeitsfaktor f&uuml;r neue Blutgruppen. Dieser
	 * Faktor ist &auml;u&szlig;erst unrealistisch: Er nimmt an, dass die
	 * Wahrscheinlichkeit f&uuml;r ein bestimmtes Gen immer <code>0.5</code> ist.
	 * @return ein zuf&auml;lliger Faktor
	 */
	private static int geneRandomness () {
		return (int)Math.round(Math.random()) * BloodType.ALL_GENES;
	}
	
	
	/**
	 * Beschreibt diese Blutgruppe textuell.
	 * @return eine Text-Repr&auml;sentation dieser Blutgruppe.
	 */
	public String toString () {
		StringBuffer buffer;
		
		buffer = new StringBuffer();
		if ((this.bloodType & BloodType.A) > 0) {
			buffer.append('A');
		}
		if ((this.bloodType & BloodType.B) > 0) {
			buffer.append('B');
		}
		if (buffer.length() == 0) {
			buffer.append('0');
		}
		if ((this.bloodType & BloodType.D) > 0) {
			buffer.append(" positiv");
		}
		else {
			buffer.append(" negativ");
		}
		return buffer.toString();
	}
	
}



	
/**
 * Nicht instanziierbare Klasse eines Wesens, das eine Blutgruppe besitzt.
 * Diese Klasse h&auml;tte sehr gut auch ein <code>interface</code> werden
 * k&ouml;nnen, aber zur Vermeidung von Redundanzen bot sich hier eine 
 * <code>abstract class</code> an.
 */
abstract class BloodTypeCarrier {
	
	
	/** Die Blutgruppe dieses Wesens. */
	protected BloodType blutgruppe;
	
	
	/**
	 * Zugriffsmethode.
	 * @return die Blutgruppe dieses Wesens
	 * @see #blutgruppe
	 */
	protected BloodType bloodType () {
		return this.blutgruppe;
	}
	
}
