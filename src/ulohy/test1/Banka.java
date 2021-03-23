package ulohy.test1;

import java.util.ArrayList;

public class Banka {
    private ArrayList<BankovyUcet> bankoveUcty;



    public double getAktualnyZostatok(int cisloUctu) {
        for (BankovyUcet ucet:bankoveUcty) {
            if (ucet.getCisloUctu() == cisloUctu) {
                return ucet.getAktualnyZostatok();
            }

        }
        return 0;
    }

    public Banka() {
        bankoveUcty = new ArrayList<>();
    }

    public void pridajUcet(BankovyUcet ucet) {
        bankoveUcty.add(ucet);
    }
    public void pridajUcet(int cisloUctu, double aktualnyZostatok) {
        BankovyUcet ucet = new BankovyUcet(cisloUctu,aktualnyZostatok);
        bankoveUcty.add(ucet);
    }

    public double getCelkovyZostatok() {
        double celkovyZostatok = 0;
        for (BankovyUcet b : bankoveUcty) {
            celkovyZostatok += b.getAktualnyZostatok();
        }
        return celkovyZostatok;
    }

    public int vratPocetUctovSoZostatkomVacsimAko(double ciastka) {
        int pocetUctov = 0;
        for (BankovyUcet ucet : bankoveUcty) {
            if (ucet.getAktualnyZostatok() > ciastka) {
                pocetUctov++;
            }
        }
        return pocetUctov;
    }

    public BankovyUcet najdiUcet(int cisloUctu) {
        for (BankovyUcet ucet : bankoveUcty) {
            if (ucet.getCisloUctu() == cisloUctu) {
                return ucet;
            }
        }
        return null;  //nenasiel sa ziadny ucet so zadanym cislom uctu
    }

    public BankovyUcet getMaximum() {

        if (bankoveUcty.size() == 0) {
            return null;   //v banke niesu ziadne ucty
        }
        BankovyUcet maxZostatok = bankoveUcty.get(0);
        for (int i = 1; i < bankoveUcty.size(); i++) {
            BankovyUcet ucet = bankoveUcty.get(i);
            if (ucet.getAktualnyZostatok() > maxZostatok.getAktualnyZostatok()) {
                maxZostatok = ucet;    //ucet s maximalnym zostatkom
            }
        }
        return maxZostatok;
    }

    public void vloz(int cisloUctu, double ciastka) {
        for (BankovyUcet ucet : bankoveUcty) {
            if (ucet.getCisloUctu() == cisloUctu) {
                ucet.setZostatokNaUcte(ucet.getAktualnyZostatok() + ciastka);
                break;
            }
        }
    }

    public void vyber(int cisloUctu, int ciastka) {
        for (BankovyUcet ucet:bankoveUcty) {
            if (ucet.getCisloUctu() ==cisloUctu) {
                ucet.setZostatokNaUcte(ucet.getAktualnyZostatok() - ciastka);
                break;
            }
        }
    }
}
