package sk.plajdickova.uloha_7_2;

import java.util.ArrayList;

public class Penazenka {
    private ArrayList<String> mince;

    public Penazenka() {
        mince = new ArrayList<>();
    }

    public void pridajMincu(String nazovMince) {
        mince.add(nazovMince);
    }

    public String toString() {

        return "Penazenka" + mince.toString();
    }
}
