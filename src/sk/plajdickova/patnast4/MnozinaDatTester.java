package sk.plajdickova.patnast4;

public class MnozinaDatTester {
    public static void main(String[] args) {
        MnozinaDat d = new MnozinaDat();
        d.pridaj(10);
        d.pridaj(15);
        d.pridaj(20);
        d.pridaj(20);
        System.out.println(d.sucet());

    }
}
