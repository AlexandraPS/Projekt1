package sk.plajdickova.dvanastTri;

import java.awt.*;
import java.util.Random;

/**
 * Trida reprezentuje dopravny prostriedok.
 */
public abstract class DopravnyProstriedok {
    private static Random r = new Random();
    private int x;
    private int y;

    /**
     * Vytvara objekt dopravny prostriedok.
     */
    public DopravnyProstriedok() {
        x = 0;
        y = 0;
    }

    /**
     * Zobrazuje konkretny dopravny prostriedok.
     *
     * @g2 graficky kontext
     */
    public abstract void zobraz(Graphics2D g2);

    /**
     * Nastavuje poziciu dopravneho prostriedku.
     *
     * @param x x-ova surdnica laveho horneho rohu
     * @param y y-ova suradnica laveho horneho rohu
     */
    public void setPozicia(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Vracia x-ovu sradnicu laveho horneho rohu.
     *
     * @return x-ovu suradnicu laveho horneho rohu
     */
    public int getX() {
        return this.x;
    }

    /**
     * Vracia y-ovu suradnicu laveho horneho rohu.
     *
     * @return y-ovu suradnicu laveho horneho rohu
     */
    public int getY() {
        return this.y;
    }

    public abstract int getSirka();

    public abstract int getVyska();

    public static DopravnyProstriedok getNahodnyDopravnyProstriedok() {

        DopravnyProstriedok d;
        if (r.nextBoolean()) {
            d = new Auto();
        } else {
            d = new NakladneAuto();
        }
        d.setPozicia(r.nextInt(600 - d.getSirka()), r.nextInt(600 - d.getVyska())); //ohranicime si lokaciu suradnic
        return d;
    }
}