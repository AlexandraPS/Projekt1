package back.office.aplikacia;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Banka {
    private final List<Klient> klienti = nacitajKlientov();

    public void pridajKlienta(Klient klient) {
        klienti.add(klient);
    }

    public Klient vyhladajKlienta(String rodneCislo) {
        for (Klient klient : klienti) {
            if (klient.getRodneCislo().equals(rodneCislo)) {
                return klient;
            }
        }
        return null;
    }

    public List<Klient> getKlienti() {
        return klienti;
    }

    public boolean pouzivaId(String id) {
        for (Klient klient : klienti) {
            if (klient.getUcet("bezny") != null) {
                if (klient.getUcet("bezny").getID().equals(id))
                    return true;
            }
            if (klient.getUcet("sporiaci") != null) {
                if (klient.getUcet("sporiaci").getID().equals(id))
                    return true;
            }
        }
        return false;
    }

    private List<Klient> nacitajKlientov() {
        String subor = "klienti.txt";
        List<Klient> klienti = new ArrayList<>();
        try (var reader = new BufferedReader(new FileReader(subor))) {
            while (reader.ready()) {
                String line = reader.readLine();
                String[] parts = line.split(", ");
                String meno = parts[0];
                String priezvisko = parts[1];
                int pocetUctov = Integer.parseInt(parts[2]);
                String rodneCislo = parts[3];
                String pin = parts[4];
                Klient klient = new Klient(meno, priezvisko, rodneCislo);
                klient.setPin(pin);
                klient.setPocetUctov(pocetUctov);
                String typ = "bezny".equals(parts[5]) ? "bezny" : "sporiaci";
                klient.setUcet(typ, parts[6]);
                klient.getUcet(typ).vloz(Double.parseDouble(parts[7]));
                if (parts.length == 11) {
                    typ = "bezny".equals(parts[8]) ? "bezny" : "sporiaci";
                    klient.setUcet(typ, parts[9]);
                    klient.getUcet(typ).vloz(Double.parseDouble(parts[10]));
                }
                klienti.add(klient);
            }
        } catch (IOException e) {
            System.out.println("Vyskytla sa I/O chyba!");
        }
        return klienti;
    }
    public void ulozKlientov() {
        String klienti = "klienti.txt";
        try (var writer = new PrintWriter(klienti)) {
            for (Klient klient : this.getKlienti()) {
                List<String> parts = new ArrayList<>();
                parts.add(klient.getMeno());
                parts.add(klient.getPriezvisko());
                parts.add(String.valueOf(klient.getPocetUctov()));
                parts.add(klient.getRodneCislo());
                parts.add(klient.getPin());
                BankovyUcet ucet = klient.getUcet("bezny");
                if (ucet != null) {
                    parts.add("bezny");
                    parts.add(ucet.getID());
                    parts.add(String.valueOf(ucet.getZostatok()));
                }
                ucet = klient.getUcet("sporiaci");
                if (ucet != null) {
                    parts.add("sporiaci");
                    parts.add(ucet.getID());
                    parts.add(String.valueOf(ucet.getZostatok()));
                }
                writer.println(String.join(", ", parts));
            }
        } catch (IOException e) {
            System.out.println("Vyskytla sa I/O chyba!");
        }
    }
}
