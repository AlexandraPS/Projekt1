package ulohy.Uloha_8_2;

public class Pokladna {
    private double zostatok = 0;

    public void nacitajTovar(double cena) {
        zostatok -= cena;
    }

    public double datVydavok() {

        return zostatok;
    }

    public void prijmiHotovost(int pocet,Minca minca) {
        zostatok += pocet * minca.getHodnota();

    }

    public int dajVydavok(Minca minca) {
        double upravenyZostatok = Math.round(zostatok * 100) / 100.0;
        int pocet = (int) (upravenyZostatok / minca.getHodnota());
        zostatok -= pocet * minca.getHodnota();
        return pocet;
    }
}
