package back.office.aplikacia;

public class SporiaciUcet extends BankovyUcet {
    private double urok;

    public SporiaciUcet(String ID) {
        super(ID, "sporiaci");
    }

    @Override
    public String toString() {
        return String.format("Sporiaci ucet: %s Aktualny zostatok: %.2f€\n",getID(), getZostatok());
    }

    public void setUrok(double urok) {
        this.urok = urok;
    }

    private void vypocitajUrok() {
        if (getZostatok() < 10_000)
            urok = 0.02;
        else if (getZostatok() < 100_000)
            urok = 0.05;
        else
            urok = 0.01;
    }
}
