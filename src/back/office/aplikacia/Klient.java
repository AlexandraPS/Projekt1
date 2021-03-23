package back.office.aplikacia;

public class Klient {
    private  String meno;

    public void setMeno(String meno) {
        this.meno = meno;
    }

    public void setPriezvisko(String priezvisko) {
        this.priezvisko = priezvisko;
    }

    private  String priezvisko;
    private final String rodneCislo;
    private String pin;
    private int pocetUctov;
    private BankovyUcet beznyUcet;
    private BankovyUcet sporiaciUcet;

    public Klient(String meno, String priezvisko, String rodneCislo) {
        this.meno = meno;
        this.priezvisko = priezvisko;
        this.rodneCislo = rodneCislo;
        pin = vygenerujPin();
        pocetUctov = 1;
    }

    private static String vygenerujPin() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            builder.append((int) (Math.random() * 10));
        }
        return builder.toString();
    }

    public int getPocetUctov() {
        return pocetUctov;
    }

    public void setPocetUctov(int pocetUctov) {
        this.pocetUctov = pocetUctov;
    }

    public String getMeno() {
        return meno;
    }

    public String getPriezvisko() {
        return priezvisko;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public BankovyUcet getUcet(String typ) {
        return ("bezny".equals(typ)) ? beznyUcet : sporiaciUcet;
    }

    public void setUcet(String typ, String id) {
        if ("bezny".equals(typ))
            this.beznyUcet = new BeznyUcet(id);
        else
            this.sporiaciUcet = new SporiaciUcet(id);
    }

    public void zrusUcet(String typ) {
        if ("bezny".equals(typ)) beznyUcet = null;
        else                     sporiaciUcet = null;
    }

    @Override
    public String toString() {
        if (beznyUcet == null)
            return String.format("Meno: %s %s, Rodne cislo: %s\nSporiaci ucet: %s Zostatok: %.2f€\n",
                    meno, priezvisko, rodneCislo, sporiaciUcet.getID(), sporiaciUcet.getZostatok());
        else if (sporiaciUcet == null)
            return String.format("Meno: %s %s, Rodne cislo: %s\nBezny ucet: %s Zostatok: %.2f€\n",
                    meno, priezvisko, rodneCislo, beznyUcet.getID(), beznyUcet.getZostatok());
        else
            return String.format("""
                Meno: %s %s, Rodne cislo: %s
                Bezny ucet: %s Zostatok: %.2f€
                Sporiaci ucet: %s Zostatok: %.2f€
                """, meno, priezvisko, rodneCislo, beznyUcet.getID(),
                    beznyUcet.getZostatok(), sporiaciUcet.getID(), sporiaciUcet.getZostatok());
    }

    public String getRodneCislo() {
        return rodneCislo;
    }
}
