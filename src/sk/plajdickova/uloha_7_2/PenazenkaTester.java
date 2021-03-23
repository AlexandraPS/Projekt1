package sk.plajdickova.uloha_7_2;

public class PenazenkaTester {
    public static void main(String[] args) {
        Penazenka penazenka = new Penazenka();
        penazenka.pridajMincu("Centovka");
        penazenka.pridajMincu("Eurovka");
        penazenka.pridajMincu("Dvojeurovka");

        System.out.println(penazenka);
        System.out.println("Ocakavana hodnota: Penazenka[Centovka, Eurovka, Dvojeurovka]");
    }
}
