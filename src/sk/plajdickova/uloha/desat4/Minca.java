package sk.plajdickova.uloha.desat4;

public class Minca implements Comparable<Minca> {
    private final String nazov;
    private final double hodnota;

    public Minca(String nazov, double hodnota) {
        this.hodnota = hodnota;
        this.nazov = nazov;
    }

    public Minca(double hodnota, String nazov) {
        this.hodnota = hodnota;
        this.nazov = nazov;
    }

    public double getHodnota() {
        return hodnota;
    }

    public String getNazov() {
        return nazov;
    }

    @Override
    public int compareTo(Minca o) {
     /*  if (this.getHodnota() < o.getHodnota()) {
            return -1;
        } else if (this.getHodnota() > o.getHodnota()) {
            return 1;
        } else {
            return 0;
        } */

        //return (int) (this.getHodnota() * 100 - o.getHodnota() * 100);
        return Double.compare(this.getHodnota(),o.getHodnota());
    }
}
