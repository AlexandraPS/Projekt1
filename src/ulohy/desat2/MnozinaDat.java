package ulohy.desat2;

/**
 * Mnozina dat dokaze zbierat statistiky o lubovolnych objektoch, ktore zbierame
 * pomocou externeho meraca.
 */
public class MnozinaDat {

    private Object maximum;
    private Object minimum;
    private int pocetPrvkov;
    private double sucet;
    private Merac merac;
    private Filter filter;


    public MnozinaDat() {
        this.merac = new Merac() {
            @Override
            public double zmeraj(Object objekt) {
                Meratelny meratelny = (Meratelny) objekt; //pretypovali sme objekt na meratelny
                return meratelny.getMiera();
            }
        };
        this.filter = new Filter() {
            @Override
            public boolean akceptuje(Object objekt) {
                return true;
            }
        };
    }

    public MnozinaDat(Merac merac) {
        this.merac = merac;
        this.filter = new Filter() {
            @Override
            public boolean akceptuje(Object objekt) {
                return true;
            }
        };
    }


    public MnozinaDat(Merac merac, Filter filter) {
        this.merac = merac;
        this.filter = filter;
    }


    public void pridaj(Object prvok) {

        if (!filter.akceptuje(prvok)) {
            return;      //funkcia je typu void, nevracia nic, returnom funkcia konci
        }

        if (pocetPrvkov == 0 || merac.zmeraj(prvok) > merac.zmeraj(maximum)) {
            maximum = prvok;
        }

        if (pocetPrvkov == 0 || merac.zmeraj(prvok) < merac.zmeraj(minimum)) {
            minimum = prvok;
        }

        pocetPrvkov++;
        sucet += merac.zmeraj(prvok);
    }

    public Object getMaximum() {
        return maximum;
    }

    public Object getMinimum() {
        return minimum;
    }

    public double getPriemer() {
        if (pocetPrvkov == 0) {
            return 0;
        } else {
            return sucet / pocetPrvkov;
        }
    }

}
