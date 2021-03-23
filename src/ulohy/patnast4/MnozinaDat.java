package ulohy.patnast4;

import java.util.ArrayList;

public class MnozinaDat {

    private ArrayList<Integer> data = new ArrayList<>();

    public void pridaj(int cislo) {
        data.add(cislo);
    }

    public int sucet() {
        return sucet(0);
    }

    private int sucet(int i) {
        if (i < data.size()) {
            return data.get(i) + sucet(i + 1);
        } else {
            return 0;
        }
    }
}
