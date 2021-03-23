package back.office.aplikacia;

public abstract class BankovyUcet {
    private final String ID;
    private double zostatok;
    private final String typ;

    public BankovyUcet(String ID, String typ) {
        this.ID = ID;
        this.typ = typ;
    }

    public String getID() {
        return ID;
    }

    public double getZostatok() {
        return zostatok;
    }

    public void vloz(double ciastka) {
        zostatok += ciastka;
    }

    public String getTyp() {
        return typ;
    }

    public void vyber(double ciastka) {
        if (ciastka < 0)
            System.out.printf("Nemozno vlozit %.2f€, hodnota je zaporna!\n", ciastka);
        else if (ciastka > zostatok)
            System.out.printf("Nemozno vybrat %.2f€, zostatok je len %.2f€\n", ciastka, zostatok);
        else
            zostatok -= ciastka;
    }
}
