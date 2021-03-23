package back.office.aplikacia;

public class BeznyUcet extends BankovyUcet {

    public BeznyUcet(String ID) {
        super(ID, "bezny");
    }

    @Override
    public String toString() {
        return String.format("Bezny ucet: %s Aktualny zostatok: %.2f€\n", getID(), getZostatok());
    }
}
