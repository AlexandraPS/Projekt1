package ulohy.test1;

public class BankovyUcetTester {
    public static void main(String[] args) {
        Banka banka = new Banka();

        final int UCET_ID1 = 0;
        final int UCET_ID2 = 1;
        final int UCET_ID3 = 2;
        final int UCET_ID4 = 3;

        banka.pridajUcet(UCET_ID1,1000);
        banka.pridajUcet(UCET_ID2,2000);
        banka.pridajUcet(UCET_ID3,3000);
        banka.pridajUcet(UCET_ID4,10000);

        banka.vloz(UCET_ID1,200);
        banka.vyber(UCET_ID2,500);
        banka.vloz(UCET_ID3,1000);
        banka.vyber(UCET_ID4,7000);

        System.out.print("Zostatok na účte s ID " + UCET_ID1);
        System.out.println(" je "+ banka.getAktualnyZostatok(UCET_ID1) + " Eur");
        System.out.println(" Očakávaná hodnota: 1200 Eur");

        System.out.print("Zostatok na ucte s ID " + UCET_ID2);
        System.out.println(" je " + banka.getAktualnyZostatok(UCET_ID2) + " EUR.");
        System.out.println("Ocakavana hodnota: 1500");

        System.out.print("Zostatok na ucte s ID " + UCET_ID3);
        System.out.println(" je " + banka.getAktualnyZostatok(UCET_ID3) + " EUR.");
        System.out.println("Ocakavana hodnota: 4000");

        System.out.print("Zostatok na ucte s ID " + UCET_ID4);
        System.out.println(" je " + banka.getAktualnyZostatok(UCET_ID4) + " EUR.");
        System.out.println("Ocakavana hodnota: 3000");

    }
}
