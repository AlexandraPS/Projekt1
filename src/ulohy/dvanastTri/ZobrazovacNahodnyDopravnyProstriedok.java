package ulohy.dvanastTri;

import javax.swing.*;

/**
 * Program zobrazuje auta a nakladne auta na nahodnych poziciach.
 */
public class ZobrazovacNahodnyDopravnyProstriedok {
    public static void main(String[] args) {
        JFrame okno = new JFrame();
        final int SIRKA_OKNA = 600;
        final int VYSKA_OKNA = 600;

        okno.setSize(SIRKA_OKNA, VYSKA_OKNA);
        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        KomponentaNahodnyDopravnyProstriedok komponenta = new KomponentaNahodnyDopravnyProstriedok();
        for (int i = 0; i < 10; i++) {
            komponenta.pridaj(DopravnyProstriedok.getNahodnyDopravnyProstriedok());
        }

        okno.add(komponenta);
        okno.setVisible(true);
    }
}

