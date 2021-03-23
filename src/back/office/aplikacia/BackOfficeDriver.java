package back.office.aplikacia;

public class BackOfficeDriver {



    public static void main(String[] args) {
        final Banka banka = new Banka();
        new BankaGUI(banka);
    }
}
