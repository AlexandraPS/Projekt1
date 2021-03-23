package ulohy.uloha_9_2;

public class Kviz {
    private int pocetBodov;
    private String znamka;

    public Kviz(int pocetBodov, String znamka) {
        this.pocetBodov = pocetBodov;
    }

    public String getZnamka() {
        if (pocetBodov >= 90) {
            znamka = "A";
        }
        return znamka;
    }

}
