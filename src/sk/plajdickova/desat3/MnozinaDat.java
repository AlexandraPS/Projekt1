package sk.plajdickova.desat3;

public class MnozinaDat {

    private Comparable maximum;  //defaultne hodnota null
    private Comparable minimum;

    public MnozinaDat () {

    }
    public void pridaj(Comparable prvok) {
        if (maximum == null || maximum.compareTo(prvok) < 0) {
            maximum = prvok;
        }
        if (minimum == null || minimum.compareTo(prvok) > 0) {
            minimum = prvok;
        }
    }
    public Comparable getMaximum () {
        return maximum;
    }
    public Comparable getMinimum () {
        return minimum;
    }
}
