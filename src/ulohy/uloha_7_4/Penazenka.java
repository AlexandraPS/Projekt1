package ulohy.uloha_7_4;

import java.util.ArrayList;
import java.util.Comparator;

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

    public int getPocetMinci() {
        return mince.size();
    }

    public String getMinca(int poradieVpenazenke) {

        return mince.get(poradieVpenazenke);
    }

    public void zoradMince() {
        mince.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {  //vracia nam 0(su rovnake),1 (objekt 2 je za objektom 1),
                return o1.compareTo(o2);                // -1 (objekt 1 sa nachadza za objektom 2)
            }
        });
    }

    public boolean jeObsahovoTotoznaS(Penazenka inaPenazenka) {
        if (inaPenazenka == null) {
            return false;       //ak nam if vracia return, nemusim dat dalsi if ako "else if"
        }
        if (this.getPocetMinci() != inaPenazenka.getPocetMinci()) {
            return false;
        }
        this.zoradMince();
        inaPenazenka.zoradMince();
        for (int i = 0; i < getPocetMinci(); i++) {
            String minca = this.getMinca(i);
            String mincaIna = inaPenazenka.getMinca(i);
            if (!minca.equals(mincaIna)) {
                return false;
            }
        }
        return true;
    }
}


