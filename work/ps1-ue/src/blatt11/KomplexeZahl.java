public class KomplexeZahl
{
	private double a;
	private double b;

	public KomplexeZahl(double a, double b)
	{
		this.a = a;
		this.b = b;
	}

	public void setA(double newA)
	{
		this.a = newA;
	}

	public double getA()
	{
		return a;
	}

	public void setB(double newB)
	{
		this.b = newB;
	}

	public double getB()
	{
		return b;
	}

	public String toString()
	{
		return a+" + ("+b+")i";
	}
}



