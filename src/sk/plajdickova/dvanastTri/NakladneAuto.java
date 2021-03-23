package sk.plajdickova.dvanastTri;

import java.awt.*;

public class NakladneAuto extends DopravnyProstriedok {
    public NakladneAuto(int x, int y) {
        super.setPozicia(x, y);
    }
    public NakladneAuto() {

    }

    @Override
    public void zobraz(Graphics2D g2) {
        g2.drawRect(getX(),getY() + 40,40,40);
        g2.drawRect(getX() + 40,getY(),200,80);
        g2.drawOval(getX(),getY() + 80,20,20);
        g2.drawOval(getX() + 220,getY() +80, 20,20);
    }

    @Override
    public int getSirka() {
        return 240;
    }

    @Override
    public int getVyska() {
        return 100;
    }
}
