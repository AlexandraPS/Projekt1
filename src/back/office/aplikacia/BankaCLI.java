package back.office.aplikacia;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BankaCLI {
    private Banka banka;
    private final Scanner scanner = new Scanner(System.in);

    public BankaCLI(Banka banka) {
        this.banka = banka;
        System.out.println("+------------------------------------------------------------------+");
        System.out.println("|                      Vitajte v BO Aplikacii                      |");
        System.out.println("|                                                                  |");
        System.out.println("|                           ZUNO Bank AG                           |");
        System.out.println("|                                                                  |");
        System.out.println("+------------------------------------------------------------------+");

        int input;
        loop:
        while (true) {
            System.out.println("Vyberte jednu z moznosti:");
            System.out.println("1) Zalozit ucet");
            System.out.println("2) Log In");
            System.out.println("0) Koniec");
            input = Integer.parseInt(scanner.nextLine());
            switch (input) {
                case 1:
                    zalozitUcet();
                    break;
                case 2:
                    LogIn();
                    break;
                case 0:
                    banka.ulozKlientov();
                    break loop;
                default:
                    System.out.println("Zadali ste nespravne cislo, skuste znovu!");
            }
        }
        System.out.println("Dovidenia!");
    }
    //neoveruje, ci uz v banke klient existuje,pri zalozeni uctu moze potom vytvarat toho isteho klienta viac x?
    private void zalozitUcet() { //po zalozeni uctu nieje klientovi priradeny PIN
        System.out.println("Zadajte meno:");
        String meno = scanner.nextLine();
        System.out.println("Zadajte priezvisko:");
        String priezvisko = scanner.nextLine();
        System.out.println("Zadajte rodne cislo:");
        String rodneCislo = scanner.nextLine();
        Klient klient = new Klient(meno, priezvisko, rodneCislo);

        System.out.println("Zadajte typ uctu: (bezny/sporiaci)");
        String typ = scanner.nextLine();
        String id = vygenerujId();

        double vklad = overVklad(typ);
        klient.setUcet(typ, id);
        vklad = (vklad > 10_000) ? vklad * 1.05 : vklad * 1.02;
        klient.getUcet(typ).vloz(vklad);

        System.out.println("Vklad na ucet bol uspesne zrealizovany.");
        System.out.printf("Aktualny zostatok na ucte: %.2f€\n", klient.getUcet(typ).getZostatok());
        banka.getKlienti().add(klient);
    }

    private String vygenerujId() {
        StringBuilder id;
        do {
            id = new StringBuilder("09000");
            for (int i = 0; i < 10; i++) {
                id.append((int) (Math.random() * 10));
            }
        } while (banka.pouzivaId(id.toString()));
        return id.toString();
    }

    private double overVklad(String typ) {
        System.out.println("Zadajte pociatocny vklad:");
        double minimum = ("bezny".equals(typ)) ? 100 : 50;
        double vklad;
        while (true) {
            vklad = Double.parseDouble(scanner.nextLine());
            if (vklad < minimum)
                System.out.printf("Vklad %.2f€ je menej ako minimalny mozny vklad %.2f€.\n", vklad, minimum);
            else
                break;
        }
        return vklad;
    }

    private void spravujKlienta(Klient klient) {
        int input;
        loop:
        while (true) {
            System.out.printf("Ste prihlaseny ako klient: %s\n", klient);
            System.out.println("Vyberte jednu z moznosti:");
            System.out.println("1) Otvorit dalsi ucet");
            System.out.println("2) Vklad na ucet");
            System.out.println("3) Vyber z uctu");
            System.out.println("4) Zmen PIN");
            System.out.println("5) Zrusit ucet");
            System.out.println("0) Odhlasit sa");
            input = Integer.parseInt(scanner.nextLine());
            switch (input) {
                case 1:
                    vytvorUcet(klient);
                    break;
                case 2:
                    transakcia(klient, "vklad");
                    break;
                case 3:
                    transakcia(klient, "vyber");
                    break;
                case 4:
                    zmenPin(klient);
                    break;
                case 5:
                    zrusUcet(klient);
                    break loop;
                case 0:
                    break loop;
            }
        }
        System.out.println("Boli ste uspesne odhlaseny!");
    }

    private void vytvorUcet(Klient klient) {
        if (klient.getPocetUctov() == 2) {
            System.out.println("Uz u nas mate vsetky typy uctov.");
            return;
        }
        BankovyUcet ucet = klient.getUcet("bezny");
        if (ucet == null) {
            System.out.println("Mate u nas sporiaci ucet, prajete si vytvorit aj bezny ucet? Y/N");
            String odpoved = scanner.nextLine();
            if ("Y".equalsIgnoreCase(odpoved)) {
                klient.setUcet("bezny", vygenerujId());
                System.out.println("Vas bezny ucet bol uspesne vytvoreny.");
                klient.setPocetUctov(klient.getPocetUctov() + 1);
            }
        } else {
            System.out.println("Mate u nas bezny ucet, prajete is vytvorit aj sporiaci? Y/N");
            String odpoved = scanner.nextLine();
            if ("Y".equalsIgnoreCase(odpoved)) {
                klient.setUcet("sporiaci", vygenerujId());
                System.out.println("Vas sporiaci ucet bol uspesne vytvoreny.");
                klient.setPocetUctov(klient.getPocetUctov() + 1);
            }
        }
    }

    private void transakcia(Klient klient, String typ) {
        BankovyUcet ucet = getUcet(klient);
        if (ucet == null) {
            System.out.println("Nemate u nas zadany ucet");
            return;
        }
        System.out.println("Zadajte sumu:");
        double suma = Double.parseDouble(scanner.nextLine());
        if ("vklad".equals(typ)) {
            ucet.vloz(suma);
            System.out.printf("%.2f€ bolo vlozenych na Vas ucet. Novy zostatok: %.2f€\n", suma, ucet.getZostatok());
        } else {
            if (suma > ucet.getZostatok()) {
                System.out.println("Vyber zlyhal, nemate dostatok financii");
            } else {
                ucet.vyber(suma);
                System.out.printf("%.2f€ bolo vybratych z Vasho uctu. Novy zostatok: %.2f€\n", suma, ucet.getZostatok());
            }
        }
    }

    private BankovyUcet getUcet(Klient klient) {
        System.out.println("Vyberte ucet:");
        System.out.println("1) bezny ucet");
        System.out.println("2) sporiaci ucet");
        int input = Integer.parseInt(scanner.nextLine());
        return klient.getUcet((input == 1) ? "bezny" : "sporiaci");
    }

    private void zrusUcet(Klient klient) {
        BankovyUcet ucet = getUcet(klient);
        if (ucet == null) {
            System.out.println("Nemate u nas zadany ucet.");
            return;
        }
        System.out.println("Naozaj si prajete zrusit ucet? Y/N");
        String odpoved = scanner.nextLine();
        if ("Y".equalsIgnoreCase(odpoved)) {
            if (klient.getPocetUctov() == 1) {
                banka.getKlienti().remove(klient);
            } else {
                klient.zrusUcet(ucet.getTyp());
                klient.setPocetUctov(klient.getPocetUctov() - 1);
            }
            System.out.println("Vas ucet bol zruseny.");
        }
    }

    private void zmenPin(Klient klient) {
        System.out.println("Zadaj novy PIN");
        String pin1 = scanner.nextLine();
        System.out.println("Zopakuj novy PIN");
        String pin2 = scanner.nextLine();
        if (!pin1.equals(pin2)) {
            System.out.println("Zmena hesla zlyhala, hesla sa nezhoduju.");
        } else {
            klient.setPin(pin1);
            System.out.println("PIN bol uspesne zmeneny.");
        }
    }

    private void LogIn() {
        System.out.println("Zadajte cislo uctu:");
        String cisloUctu = scanner.nextLine();
        System.out.println("Zadajte PIN:");
        String pin = scanner.nextLine();
        Klient hladanyKlient = null;
        for (Klient klient : banka.getKlienti()) {
            if (klient.getUcet("bezny") != null) {
                if (klient.getUcet("bezny").getID().equals(cisloUctu) && klient.getPin().equals(pin))
                    hladanyKlient = klient;
            }
            if (klient.getUcet("sporiaci") != null) {
                if (klient.getUcet("sporiaci").getID().equals(cisloUctu) && klient.getPin().equals(pin))
                    hladanyKlient = klient;
            }
        }
        if (hladanyKlient == null)
            System.out.println("Chyba pri zadavani cisla uctu alebo PINu!");
        else
            spravujKlienta(hladanyKlient);
    }



}
