package ulohy.Uloha_8_1;

public class PokladnaTester {
    public static void main(String[] args) {
        final double HODNOTA_1_EURA = 1.0;
        final double HODNOTA_50_CENTOV = 0.5;
        final double HODNOTA_20_CENTOV = 0.2;
        final double HODNOTA_10_CENTOV = 0.1;
        final double HODNOTA_5_CENTOV = 0.05;
        final double HODNOTA_2_CENTOV = 0.02;
        final double HODNOTA_1_CENTU = 0.01;

        // vytvorime si jednotlive mince
        Minca eurovaMinca = new Minca("Eurovka", HODNOTA_1_EURA);
        Minca patdesiatcentovaMinca = new Minca("Patdesiatcentovka", HODNOTA_50_CENTOV);
        Minca dvadsatcentovaMinca = new Minca("Dvadsatcentovka", HODNOTA_20_CENTOV);

        Pokladna pokladna = new Pokladna();
        pokladna.nacitajTovar(2.59);
        pokladna.nacitajTovar(6.99);
        pokladna.prijmiHotovost(9, eurovaMinca);
        pokladna.prijmiHotovost(1, patdesiatcentovaMinca);
        pokladna.prijmiHotovost(1, dvadsatcentovaMinca);
        double vydavok = pokladna.datVydavok();
        double zaokruhlenyVydavok = Math.round(vydavok * 100) / 100.0;
        // System.out.printf("\tVýdavok: %.2f\n",vydavok);  ak chcem bodku, zapis je "%..2f"
        System.out.println("Vydavok: " + zaokruhlenyVydavok);
        System.out.println("Ocakavana hodnota: 12 centov");
    }

}
