import java.util.Scanner;

public class ZobrazovacFaktorov {
    public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
        System.out.println("Zadajte celé kladné číslo: ");
        int cislo = sc.nextInt();
        FaktorGenerator Generator = new FaktorGenerator(cislo);
        while (Generator.existujeDalsiFaktor()) {
            System.out.print(Generator.dalsiFaktor() + " ");
        }
    }
}
