package sk.plajdickova.uloha_7_3;

public class PenazenkaTester {
    public static void main(String[] args) {
        Penazenka a = new Penazenka();
        a.pridajMincu("Eurovka");
        a.pridajMincu("Dvojeurovka");

        Penazenka b = new Penazenka();
        b.pridajMincu("Eurovka");
        b.pridajMincu("Dvojeurovka");

        System.out.println(a.jeObsahovoTotoznaS(b));
        System.out.println("Ocakavana hodnota: true");

        Penazenka c = new Penazenka();
        c.pridajMincu("Centovka");
        c.pridajMincu("Dvojcentovka");
        c.pridajMincu("Centovka");

        Penazenka d = new Penazenka();
        d.pridajMincu("Centovka");
        d.pridajMincu("Dvojcentovka");
        d.pridajMincu("Patcentovka");

        System.out.println(c.jeObsahovoTotoznaS(d));
        System.out.println("Ocakavana hodnota: false");

        Penazenka e = new Penazenka();
        e.pridajMincu("Centovka");
        e.pridajMincu("Dvojcentovka");
        e.pridajMincu("Dvojcentovka");

        Penazenka f = new Penazenka();
        f.pridajMincu("Centovka");
        f.pridajMincu("Dvojcentovka");

        System.out.println(e.jeObsahovoTotoznaS(f));
        System.out.println("Ocakavana hodnota: false");
    }
}
