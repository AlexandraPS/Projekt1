package sk.plajdickova.desat2;

/**
 * Program testuje pouzitie rozhrania Merac a Filter.
 */
public class MnozinaDatTester {
    public static void main(String[] args) {
        class MeracBankovychUctov implements Merac {
            public double zmeraj(Object objekt) {
                BankovyUcet ucet = (BankovyUcet) objekt;
                return ucet.getAktualnyZostatok();
            }
        }

        class FilterBankovychUctov implements Filter {
            public boolean akceptuje(Object objekt) {
                BankovyUcet bankovyUcet = (BankovyUcet) objekt;
                return bankovyUcet.getAktualnyZostatok() >= 1000;
            }
        }

        Merac merac = new MeracBankovychUctov();
        Filter filter = new FilterBankovychUctov();
        MnozinaDat bankoveUcty = new MnozinaDat(merac, filter);

        bankoveUcty.pridaj(new BankovyUcet(1));
        bankoveUcty.pridaj(new BankovyUcet(100));
        bankoveUcty.pridaj(new BankovyUcet(2000));
        bankoveUcty.pridaj(new BankovyUcet(950));
        bankoveUcty.pridaj(new BankovyUcet(4000));

        System.out.println("Priemerny zostatok na ucte: " + bankoveUcty.getPriemer());
        System.out.println("Ocakavana hodnota: 3000");

        BankovyUcet ucetMax = (BankovyUcet) bankoveUcty.getMaximum();
        double zostatok = ucetMax.getAktualnyZostatok();
        System.out.println("Najvyssi zostatok na ucte je: " + zostatok);
        System.out.println("Ocakavana hodnota: 4000");
    }
}