package sk.plajdickova.Uloha_8_1;

public class Minca {

    private final String nazov;
    private final double hodnota;

    public Minca(String nazov, double hodnota) {
        this.hodnota = hodnota;
        this.nazov = nazov;
    }

    public double getHodnota() {
        return hodnota;
    }

    public String getNazov() {
        return nazov;
    }
}
