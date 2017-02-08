public class Programm
{
    private int [] zahlen = { 1, 8, 4, 6, 2, 20, 30, 3, 17, 15, 19, 9, 12, 13, 0 };

    public Programm()
    {
    }

    private void sortieren()
    {
        //der BubbleSort
        for (int i = 0; i < zahlen.length; i++)
        {
            for (int j = zahlen.length-1; j > i; j--)
            {
                if (zahlen[j] < zahlen[i])
                {
                    int temp = zahlen[j];
                    zahlen[j] = zahlen[i];
                    zahlen[i] = temp;
                }
            }
        }

        //Ausgabe!
        for (int i = zahlen.length; --i > 0; )
        {
            System.out.print(zahlen[i] + " > ");
        }
        System.out.println(zahlen[0]);
    }

    public static void main(String[] args)
    {
        Programm blatt = new Programm();
        blatt.sortieren();
    }

}
