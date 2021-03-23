package ulohy.jedenastDva;

public class TerminovanyUcet extends SporiaciUcet {

    private int viazanost;

    public TerminovanyUcet(double urok, int viazanost) {
        super(urok);
        this.viazanost = viazanost;
    }

    @Override
    public void vyber(double ciastka) {
        if (viazanost > 0) {
            aktualnyZostatok -= ciastka + ciastka / 25;
        } else {
            aktualnyZostatok -= ciastka;
        }
        if (aktualnyZostatok < minZostatok) {
            minZostatok = aktualnyZostatok;
        }
    }

    public void zapisUrok() {
        super.zapisUrok();
        this.viazanost--;
    }
}
