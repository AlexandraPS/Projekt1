package ulohy.uloha_7_3;

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

    public int getPocetMinci() {
        return mince.size();
    }
    public String getMinca(int poradieVpenazenke) {

        return mince.get(poradieVpenazenke);
    }


    public boolean jeObsahovoTotoznaS(Penazenka inaPenazenka) {
        if (inaPenazenka == null) {
            return false;       //ak nam if vracia return, nemusim dat dalsi if ako "else if"
        }
        if (this.getPocetMinci() != inaPenazenka.getPocetMinci()) {
            return false;
        }
        for (int i = 0; i < getPocetMinci() ; i++) {
            String minca = this.getMinca(i);
            String mincaIna = inaPenazenka.getMinca(i);
            if (!minca.equals(mincaIna)) {
                return false;
            }
        }
        return true;
    }
}


