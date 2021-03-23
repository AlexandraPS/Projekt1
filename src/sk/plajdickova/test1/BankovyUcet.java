package sk.plajdickova.test1;

public class BankovyUcet {
    private int cisloUctu;
    private double aktualnyZostatok;

    public BankovyUcet(int cisloUctu,double aktualnyZostatok) {
        this.cisloUctu=cisloUctu;
        this.aktualnyZostatok=aktualnyZostatok;
    }

    public void setCisloUctu(int cisloUctu) {
        this.cisloUctu = cisloUctu;
    }

    public void setZostatokNaUcte(double aktualnyZostatok) {
        this.aktualnyZostatok = aktualnyZostatok;
    }

    public int getCisloUctu() {
        return cisloUctu;
    }

    public double getAktualnyZostatok() {
        return aktualnyZostatok;
    }



}
