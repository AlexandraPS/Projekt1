public class FaktorGeneratorTest {
    public static void main(String[] args) {
        long vstupneCislo = 3000000000000000000L;
        long cas = System.currentTimeMillis();
        while (true) {
            long delitel = 0;
            for (long i = 2; i < vstupneCislo; i++) {
                if (vstupneCislo % i == 0) {
                    System.out.println(i);
                    delitel = i;
                    break;
                }
            }
            if (delitel > 0) {
                vstupneCislo = vstupneCislo / delitel;
            } else {
                System.out.println(vstupneCislo);
                break;
            }
        }
        long koniec = System.currentTimeMillis();
        System.out.println("čas je " + (koniec - cas));
    }

}
