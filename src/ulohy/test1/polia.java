package ulohy.test1;

import java.util.ArrayList;

public class polia {
    public static void main(String[] args) {

        int[] cisla = new int[4];  //vytvorime pole 4 prvkov - index 0,1,2,3
        cisla[cisla.length - 1] = 8; //posledny prvok pola nastavime na hodnotu osem
        System.out.println(cisla[3]); //vypiseme si ho


        String[] farby = {"biely", "modrý", "červený", "želený"};
        String[] karty = {"kráľ", "horník", "dolník", "eso"};
        farby[2] = "ružový";
        System.out.println(farby.length);


       /* ArrayList<BankovyUcet> ucty = new ArrayList<>();
        ucty.add(new BankovyUcet(100));
        ucty.add(new BankovyUcet(200));
        ucty.add(new BankovyUcet(300));
        double celkovyZostatok = 0;
        for (BankovyUcet bankovyUcet : ucty) {
            celkovyZostatok = celkovyZostatok + bankovyUcet.getZostatokNaUcte();
        }
        System.out.println(celkovyZostatok); */


        //  int [][] pole2D = new int[5][5];
        //   System.out.println(pole2D);


    }
}
