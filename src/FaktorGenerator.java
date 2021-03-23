public class FaktorGenerator {
    private int cislo;

    public FaktorGenerator(int cislo) {
        this.cislo = cislo;
    }

    public boolean existujeDalsiFaktor() {
        return cislo != 1;


    }

    public int dalsiFaktor() {
        int faktor = cislo;
        for (int i = 2; i < cislo; i++) {
            if (cislo % i == 0) {
                faktor = i;
                break;
            }

        }
        cislo = cislo / faktor;
        return faktor;
    }


}
