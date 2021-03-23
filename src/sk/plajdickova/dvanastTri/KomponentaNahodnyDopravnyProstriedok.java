package sk.plajdickova.dvanastTri;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class KomponentaNahodnyDopravnyProstriedok extends JComponent {
    private ArrayList<DopravnyProstriedok> dopravneProstriedky = new ArrayList<>();

    public void pridaj(DopravnyProstriedok d) {
        dopravneProstriedky.add(d);
    }
    @Override
    public void paint(Graphics g) {
        for (DopravnyProstriedok d:dopravneProstriedky) {
            d.zobraz((Graphics2D) g);
        }
    }
}
