package ulohy.dvanastTri;

import java.awt.*;

public class Auto extends DopravnyProstriedok{
    public Auto (int x,int y){
        super.setPozicia(x,y);
    }
    public Auto () {

    }
    @Override
    public void zobraz(Graphics2D g2) {
    g2.drawOval(getX(),getY() + 60,20,20);
    g2.drawRect(getX() +20,getY(),80,20);
    g2.drawRect(getX(),getY() + 20, 120, 40);
    g2.drawOval(getX()+ 100,getY() + 60,20,20);
    }

    @Override
    public int getSirka() {
        return 120;
    }

    @Override
    public int getVyska() {
        return 80;
    }
}
