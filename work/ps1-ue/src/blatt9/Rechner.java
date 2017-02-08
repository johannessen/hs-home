public class Rechner
{
	/**
	 * this funny little method calculates the length of a hypotenuse.
	 */
	public static double calculateHypotenuse(int firstValue, int secondValue)
	{
		double result = Math.sqrt((firstValue * firstValue) + (secondValue * secondValue));

		return result;
	}

	public static void main (String args [])
	{
		double ergebnis = 0.0;
		int a = Integer.parseInt(args[0]);
		int b = Integer.parseInt(args[0]);

		ergebnis = calculateHypotenuse(a,b);

		System.out.println("Ergebnis: "+ergebnis);
	}

}