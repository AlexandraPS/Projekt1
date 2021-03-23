package sk.plajdickova.jedenastJedna;

public class SporiaciUcet {
    private double aktualnyZostatok;
    private final double urok;
    private double minZostatok;

    public SporiaciUcet(double urok) {
        this.urok = urok / 100;
    }


    public void vloz(double ciastka) {
        aktualnyZostatok += ciastka;
        if (minZostatok == 0) {
            minZostatok = aktualnyZostatok;
        }
    }

    public void vyber(double ciastka) {
        aktualnyZostatok -= ciastka;
        if (aktualnyZostatok < minZostatok) {
            minZostatok = aktualnyZostatok;
        }

    }

    public double getAktualnyZostatok() {
        return aktualnyZostatok;
    }

    public void zapisUrok() {
        aktualnyZostatok += urok * minZostatok;
    }
}
