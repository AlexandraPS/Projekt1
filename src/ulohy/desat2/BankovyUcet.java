package ulohy.desat2;

/**
 * Trieda predstavuje abstrakciu skutocneho bankoveho uctu a poskytuje zakladnu
 * funkcionalitu pre vklady, vyber a zostatok na ucte.
 *
 */
public class BankovyUcet implements Meratelny {

    private double aktualnyZostatok;

    /**
     * Vytvara novy bankovy ucet s danym pociatocnym zostatkom
     * 
     * @param pociatocnyZostatok pociatocny zostatok
     */
    public BankovyUcet(double pociatocnyZostatok) {
	aktualnyZostatok = pociatocnyZostatok;
    }

    /**
     * Vklada peniaze na bankovy ucet.
     * 
     * @param ciastka suma penazi, ktora sa vlozi na ucet
     */
    public void vloz(double ciastka) {
	aktualnyZostatok = aktualnyZostatok + ciastka;
    }

    /**
     * Vyber z uctu danu sumu penazi.
     * 
     * @param ciastka suma penazi, ktora sa vyberie z uctu
     */
    public void vyber(double ciastka) {
	double novyZostatok = aktualnyZostatok - ciastka;
	aktualnyZostatok = novyZostatok;
    }

    /**
     * Zobrazi aktualny zostatok na standardny vystup.
     */
    public void vypisZostatok() {
	System.out.println("Aktualny zostatok na ucte: " + aktualnyZostatok + " EUR");
    }

    public double getAktualnyZostatok() {
	return aktualnyZostatok;
    }

    @Override
    public String toString() {
	return "BankovyUcet [aktualnyZostatok=" + aktualnyZostatok + "]";
    }

    @Override
    public double getMiera() {
        return aktualnyZostatok;
    }
}
