package sk.plajdickova.jedenastDva;

public class BankovyUcet {

    protected double aktualnyZostatok;  //pouzitim protected umoznujeme pouzitie podtriedam


    public double getAktualnyZostatok() {
        return aktualnyZostatok;
    }
    public void vloz(double ciastka) {
        aktualnyZostatok += ciastka;
    }
    public void vyber(double ciastka) {
        aktualnyZostatok -= ciastka;
    }
}
