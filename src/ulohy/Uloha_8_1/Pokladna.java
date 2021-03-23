package ulohy.Uloha_8_1;

public class Pokladna {
    private double zostatok = 0;

    public void nacitajTovar(double cena) {
         zostatok -= cena;
        System.out.println("Nový zostatok: " + zostatok);
    }

    public double datVydavok() {

        return zostatok;
    }

    public void prijmiHotovost(int pocet, Minca minca) {
        zostatok += pocet*minca.getHodnota();
        System.out.println("Nový zostatok: " + zostatok);
    }
}
