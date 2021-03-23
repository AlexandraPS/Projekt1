package sk.plajdickova.uloha.desat4;
/**
 Program testuje pouzitie objekty triedy Minca implementujuce rozhranie Comparable.
 */
public class MincaTester
{
    public static void main(String[] args)
    {
        Minca eurovka = new Minca("Eurovka", 1.0);
        Minca dvojeurovka = new Minca("Dvojeurovka", 2.0);

        int vysledokPorovnania = eurovka.compareTo(dvojeurovka);

        if (vysledokPorovnania < 0)
        {
            System.out.println("Hodnota eurovej minci je mensia ako hodnota dvojeurovej minci");
        }
        else if (vysledokPorovnania > 0)
        {
            System.out.println("Hodnota  eurovej minci je vacsia ako hodnota dvojeurovej minci");
        }
        else
        {
            System.out.println("Hodnota eurovej minci je rovna hodnote dvojeurovej minci");
        }
    }
}