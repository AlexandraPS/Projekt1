import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Test {

    public static void main(String[] args) throws FileNotFoundException {

        System.out.println("Zadaj názov súboru: ");

        Scanner sc = new Scanner(System.in);
        String nazovSuboru = sc.next();
        File file = new File(nazovSuboru);
        Scanner citac = new Scanner(new FileInputStream(file));

        while (citac.hasNextLine()) {
            System.out.println(citac.nextLine());
        }

    }

}
