package sk.plajdickova.jedenastDva;

public class SporiaciUcet extends BankovyUcet {

    private final double urok;
    protected double minZostatok;

    @Override
    public void vloz(double ciastka) {
        super.vloz(ciastka);
        if (minZostatok == 0) {
            minZostatok = aktualnyZostatok;
        }
    }

    @Override
    public void vyber(double ciastka) {
        super.vyber(ciastka);
        if (aktualnyZostatok < minZostatok) {
            minZostatok = aktualnyZostatok;
        }
    }

    public SporiaciUcet(double urok) {
        this.urok = urok / 100;
    }

    public void zapisUrok() {
        aktualnyZostatok += urok * minZostatok;
    }

    public void vlozNaUcet(double ciastka, BankovyUcet ucet) {
        this.vyber(ciastka);
        ucet.vloz(ciastka);
    }
}
