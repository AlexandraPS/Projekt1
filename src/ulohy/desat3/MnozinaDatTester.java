package ulohy.desat3;

public class MnozinaDatTester {
    public static void main(String[] args)
    {
        MnozinaDat retazce = new MnozinaDat();

        retazce.pridaj("Igor");
        retazce.pridaj("Peter");
        retazce.pridaj("Vojto");
        retazce.pridaj("Andrejka");
        retazce.pridaj("Evicka");

        Comparable maxRetazec = retazce.getMaximum();
        Comparable minRetazec = retazce.getMinimum();

        System.out.println("Max retazec: " + maxRetazec);
        System.out.println("Ocakavana hodnota: Vojto");
        System.out.println("Min retazec: " + minRetazec);
        System.out.println("Ocakavana hodnota: Andrejka");
    }
}
