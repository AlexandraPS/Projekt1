package back.office.aplikacia;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.regex.Pattern;

/**
 * Trieda vytvara graficku verziu aplikacie
 */
public class BankaGUI extends JFrame {

    private static final int UVOD = 0;
    private static final int ZALOZENIE_UCTU = 1;
    private static final int PRIHLASENIE = 2;
    private static final int BEZNY_UCET = 3;
    private static final int SPORIACI_UCET = 4;
    private static final int PRIHLASENIE_PIN = 5;
    private static final int PRIHLASENIE_OK = 6;
    private static final int ZALOZENIE_MENO = 7;
    private static final int ZALOZENIE_PRIEZVISKO = 8;
    private static final int ZALOZENIE_SPORIACI = 9;
    private static final int ZALOZENIE_UCET = 10;
    private static final int ZALOZENIE_BEZNY = 11;
    private static final int ZRUSENIE_UCTU = 12;
    private static final int ZMENA_PIN = 13;
    private static final int VKLAD = 14;
    private static final int VKLAD_BEZNY = 15;
    private static final int VKLAD_SPORIACI = 16;
    private static final int VYBER = 17;
    private static final int VYBER_BEZNY = 18;
    private static final int VYBER_SPORIACI = 19;
    private Klient prihlasenyKlient;
    private Banka banka;
    private JTextArea textArea;
    private int stav = UVOD;

    /**
     * Vytvara konkretnu graficku aplikaciu
     * @param banka konkretna banka
     */
    public BankaGUI(Banka banka) {
        this.banka = banka;
        setTitle("ZUNO BANK AG");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                banka.ulozKlientov();
                super.windowClosing(e);
            }
        });
        //nastavujeme Layout
        setLayout(new GridLayout(2, 1));
        textArea = new JTextArea();
        textArea.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLUE, 2),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        textArea.setEditable(false);

        textArea.setFont(new Font("Arial", Font.BOLD, 15));
        add(textArea);
        JPanel panel = new JPanel();
        JTextField vstup = new JTextField();
        panel.add(vstup);
        JPanel klavesnica = new JPanel(new GridLayout(1, 7));
        panel.setLayout(new GridLayout(2, 1));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton acko = new JButton("A");
        acko.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (stav == UVOD) {
                    stav = ZALOZENIE_UCTU;
                } else if (stav == ZALOZENIE_UCTU) {
                    String rodneCislo = vstup.getText();

                    prihlasenyKlient = banka.vyhladajKlienta(rodneCislo);
                    if (prihlasenyKlient != null) {
                        stav = PRIHLASENIE_PIN;
                    } else if (rodneCislo.length() == 10) {
                        prihlasenyKlient = new Klient("", "", rodneCislo);
                        stav = ZALOZENIE_MENO;
                    } else {
                        JOptionPane.showMessageDialog(BankaGUI.this,
                                "Zadaj rodne cislo, ktore ma 10 znakov");
                    }
                } else if (stav == PRIHLASENIE) {
                    prihlasenyKlient = banka.vyhladajKlienta(vstup.getText());
                    if (prihlasenyKlient != null) {
                        stav = PRIHLASENIE_PIN;
                    } else JOptionPane.showMessageDialog(BankaGUI.this,
                            "Klient so zadanym rodnym cislom sa nenasiel. Prosim zadajte este raz rodne cislo");


                } else if (stav == PRIHLASENIE_PIN) {
                    if (prihlasenyKlient.getPin().equals(vstup.getText())) {
                        stav = PRIHLASENIE_OK;
                    }

                } else if (stav == ZALOZENIE_MENO) {
                    String meno = vstup.getText();

                    if (Pattern.compile("[A-Z][a-z]+").matcher(meno).matches()) {
                        prihlasenyKlient.setMeno(meno);
                        stav = ZALOZENIE_PRIEZVISKO;
                    } else JOptionPane.showMessageDialog(BankaGUI.this,
                            "Polozka meno musi zacinat velkym pismenom a mat minimalne dva znaky");

                } else if (stav == ZALOZENIE_PRIEZVISKO) {
                    String priezvisko = vstup.getText();

                    if (Pattern.compile("[A-Z][a-z]+").matcher(priezvisko).matches()) {
                        prihlasenyKlient.setPriezvisko(priezvisko);
                        banka.pridajKlienta(prihlasenyKlient);
                        stav = ZALOZENIE_UCET;
                    } else JOptionPane.showMessageDialog(BankaGUI.this,
                            "Polozka priezvisko musi zacinat velkym pismenom a mat minimalne dva znaky");

                } else if (stav == ZALOZENIE_UCET) {
                    if (prihlasenyKlient.getUcet("bezny") == null) {
                        stav = ZALOZENIE_BEZNY;
                    } else JOptionPane.showMessageDialog(BankaGUI.this, "Dany typ uctu uz bol vytvoreny");
                } else if (stav == ZALOZENIE_BEZNY) {
                    try {
                        double suma = Double.parseDouble(vstup.getText());
                        if (suma >= 100) {
                            if (suma >= 1e4) {
                                suma *= 1.05; //*105e-2;
                            } else suma *= 1.02;
                            prihlasenyKlient.setUcet("bezny", vygenerujId());
                            prihlasenyKlient.getUcet("bezny").vloz(suma);
                            JOptionPane.showMessageDialog(BankaGUI.this, "Bezny ucet bol zalozeny, Vas aktualny zostatok na ucte je: "
                                    + suma + " eur\nBol vygenerovany PIN k Vasmu uctu: " + prihlasenyKlient.getPin());
                            stav = PRIHLASENIE_OK;
                        } else
                            JOptionPane.showMessageDialog(BankaGUI.this, "Zadana suma je mensia ako 100 eur. Zopakujte vklad");
                    } catch (NumberFormatException e1) {
                        JOptionPane.showMessageDialog(BankaGUI.this, "Zadajte spravny format vkladu");
                    }

                } else if (stav == ZALOZENIE_SPORIACI) {
                    try {
                        double suma = Double.parseDouble(vstup.getText());
                        if (suma >= 50) {
                            if (suma >= 1e4) {
                                suma *= 1.05; //*105e-2;
                            } else suma *= 1.02;
                            prihlasenyKlient.setUcet("sporiaci", vygenerujId());
                            prihlasenyKlient.getUcet("sporiaci").vloz(suma);
                            JOptionPane.showMessageDialog(BankaGUI.this, "Sporiaci ucet bol zalozeny, Vas aktualny zostatok na ucte je: "
                                    + suma + " eur\nBol vygenerovany PIN k Vasmu uctu: " + prihlasenyKlient.getPin());
                            stav = PRIHLASENIE_OK;
                        } else
                            JOptionPane.showMessageDialog(BankaGUI.this, "Zadana suma je mensia ako 50 eur. Zopakujte vklad");
                    } catch (NumberFormatException e1) {
                        JOptionPane.showMessageDialog(BankaGUI.this, "Zadajte spravny format vkladu");
                    }
                } else if (stav == ZRUSENIE_UCTU) {
                    prihlasenyKlient.zrusUcet("bezny");
                    stav = PRIHLASENIE_OK;
                    JOptionPane.showMessageDialog(BankaGUI.this, "Bezny ucet bol zruseny");
                } else if (stav == ZMENA_PIN) {
                    String pin = vstup.getText();
                    if (Pattern.compile("[0-9]{4}").matcher(pin).matches()) {
                        prihlasenyKlient.setPin(pin);
                        stav = PRIHLASENIE_OK;
                        JOptionPane.showMessageDialog(BankaGUI.this, "Vas PIN bol uspesne zmeneny");
                    } else
                        JOptionPane.showMessageDialog(BankaGUI.this, "Pin sa nepodarilo zmenit. Zadajte prosim spravny ciselny format");

                } else if (stav == PRIHLASENIE_OK) {
                    stav = ZALOZENIE_UCET;
                } else if (stav == VKLAD) {
                    if (prihlasenyKlient.getUcet("bezny") != null) {
                        stav = VKLAD_BEZNY;
                    } else JOptionPane.showMessageDialog(BankaGUI.this, "Bezny ucet nie je zalozeny");

                } else if (stav == VKLAD_BEZNY) {
                    try {
                        double suma = Double.parseDouble(vstup.getText());
                        if (suma > 0) {
                            BankovyUcet ucet = prihlasenyKlient.getUcet("bezny");
                            ucet.vloz(suma);
                            JOptionPane.showMessageDialog(BankaGUI.this, "Vklad na bezny ucet bol uspesne zrealizovany: "
                                    + suma + "Vas aktualny zostatok na beznom ucte je: " + ucet.getZostatok());
                            stav = PRIHLASENIE_OK;
                        } else
                            JOptionPane.showMessageDialog(BankaGUI.this, "Zadana suma musi byt vacsia ako 0 eur. Zopakujte vklad");
                    } catch (NumberFormatException e1) {
                        JOptionPane.showMessageDialog(BankaGUI.this, "Zadajte spravny format vkladu");
                    }
                } else if (stav == VKLAD_SPORIACI) {
                    try {
                        double suma = Double.parseDouble(vstup.getText());
                        if (suma > 0) {
                            BankovyUcet ucet = prihlasenyKlient.getUcet("sporiaci");
                                ucet.vloz(suma);
                                JOptionPane.showMessageDialog(BankaGUI.this, "Vklad na sporiaci ucet bol uspesne zrealizovany: "
                                        + suma + "Vas aktualny zostatok na sporiacom ucte je: " + ucet.getZostatok());
                                stav = PRIHLASENIE_OK;
                        } else
                            JOptionPane.showMessageDialog(BankaGUI.this, "Zadana suma musi byt vacsia ako 0 eur. Zopakujte vklad");
                    } catch (NumberFormatException e1) {
                        JOptionPane.showMessageDialog(BankaGUI.this, "Zadajte spravny format vkladu");
                    }
                } else if (stav == VYBER) {
                    if (prihlasenyKlient.getUcet("bezny") != null) {
                        stav = VYBER_BEZNY;
                    } else JOptionPane.showMessageDialog(BankaGUI.this, "Bezny ucet nieje zalozeny");


                } else if (stav == VYBER_BEZNY) {
                    try {
                        double suma = Double.parseDouble(vstup.getText());
                        if (suma > 0) {
                            BankovyUcet ucet = prihlasenyKlient.getUcet("bezny");
                            if (ucet.getZostatok() >= suma) {
                                ucet.vyber(suma);
                                JOptionPane.showMessageDialog(BankaGUI.this, "Vyber z bezneho ucetu bol uspesne zrealizovany: "
                                        + suma + "Eur\nVas aktualny zostatok na beznom ucte je: " + ucet.getZostatok());
                                stav = PRIHLASENIE_OK;
                            } else
                                JOptionPane.showMessageDialog(BankaGUI.this, "Zvolenu sumu nieje mozne vybrat z uctu");
                        } else
                            JOptionPane.showMessageDialog(BankaGUI.this, "Zadana suma musi byt vacsia ako 0 eur. Zopakujte vklad");
                    } catch (NumberFormatException e1) {
                        JOptionPane.showMessageDialog(BankaGUI.this, "Zadajte spravny format vkladu");
                    }
                } else if (stav == VYBER_SPORIACI) {
                    try {
                        double suma = Double.parseDouble(vstup.getText());
                        if (suma > 0) {
                            BankovyUcet ucet = prihlasenyKlient.getUcet("sporiaci");
                            if (ucet.getZostatok() >= suma) {
                                ucet.vyber(suma);
                                JOptionPane.showMessageDialog(BankaGUI.this, "Vyber zo sporiaceho uctu bol uspesne zrealizovany: "
                                        + suma + "Eur\nVas aktualny zostatok na sporiacom ucte je: " + ucet.getZostatok());
                                stav = PRIHLASENIE_OK;
                            } else
                                JOptionPane.showMessageDialog(BankaGUI.this, "Zvolenu sumu nieje mozne vybrat z uctu");
                        } else
                            JOptionPane.showMessageDialog(BankaGUI.this, "Zadana suma musi byt vacsia ako 0 eur. Zopakujte vklad");
                    } catch (NumberFormatException e1) {
                        JOptionPane.showMessageDialog(BankaGUI.this, "Zadajte spravny format vkladu");
                    }
                }

                vstup.setText("");
                nastavText();
            }
        });
        klavesnica.add(acko);
        JButton becko = new JButton("B");
        becko.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (stav == UVOD) {
                    stav = PRIHLASENIE;
                } else if (stav == ZALOZENIE_UCTU) {
                    stav = ZALOZENIE_SPORIACI;
                } else if (stav == ZALOZENIE_UCET) {
                    if (prihlasenyKlient.getUcet("sporiaci") == null) {
                        stav = ZALOZENIE_SPORIACI;
                    } else JOptionPane.showMessageDialog(BankaGUI.this, "Dany typ uctu uz bol vytvoreny");
                } else if (stav == ZRUSENIE_UCTU) {
                    prihlasenyKlient.zrusUcet("sporiaci");
                    stav = PRIHLASENIE_OK;
                    JOptionPane.showMessageDialog(BankaGUI.this, "Sporiaci ucet bol zruseny");
                } else if (stav == PRIHLASENIE_OK) {
                    stav = VKLAD;
                } else if (stav == VKLAD) {
                    if (prihlasenyKlient.getUcet("sporiaci") != null) {
                        stav = VKLAD_SPORIACI;
                    } else JOptionPane.showMessageDialog(BankaGUI.this, "Sporiaci ucet nebol vytvoreny");

                } else if (stav == VYBER) {
                    if (prihlasenyKlient.getUcet("sporiaci") != null) {
                        stav = VYBER_SPORIACI;
                    } else JOptionPane.showMessageDialog(BankaGUI.this, "Sporiaci ucet nebol vytvoreny");

                }
                nastavText();
            }
        });
        klavesnica.add(becko);
        JButton cecko = new JButton("C");
        cecko.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (stav == PRIHLASENIE_OK) {
                    stav = VYBER;
                }
                nastavText();
            }
        });
        klavesnica.add(cecko);
        JButton decko = new JButton("D");
        decko.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (stav == PRIHLASENIE_OK) {
                    stav = ZMENA_PIN;
                }
                nastavText();
            }
        });
        klavesnica.add(decko);
        JButton ecko = new JButton("E");
        ecko.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (stav == PRIHLASENIE_OK) {
                    stav = ZRUSENIE_UCTU;
                }
                nastavText();
            }
        });
        klavesnica.add(ecko);

        JButton ef = new JButton("F");
        ef.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (stav == PRIHLASENIE_OK) {
                    stav = UVOD;
                    prihlasenyKlient = null;
                }
                vstup.setText("");
                nastavText();
            }
        });
        klavesnica.add(ef);
        panel.add(klavesnica);
        add(panel);


        setVisible(true);
        nastavText();
    }

    /**
     * Metoda, ktora generuje unikate ID cislo reprezentujuce cislo uctu
     * @return ID unikatne cislo uctu
     */
    private String vygenerujId() {
        StringBuilder id;
        do {
            id = new StringBuilder("09000");
            for (int i = 0; i < 10; i++) {
                id.append((int) (Math.random() * 10));
            }
        } while (banka.pouzivaId(id.toString()));
        return id.toString();
    }

    /**
     * Metoda nastavuje informacny text pre pouzivatela, ktory mu ponuka moznosti volieb prace s programom
     */
    private void nastavText() {
        if (stav == UVOD) {
            textArea.setText("Dobry den, zvolte jednu z nasledovnych moznosti:\n\nA Zalozit ucet\t\t\tB Prihlasit sa");
        } else if (stav == ZALOZENIE_UCTU) {
            textArea.setText("Zadaj rodne cislo\na stlacte A pre potvrdenie volby ");

        } else if (stav == PRIHLASENIE) {
            textArea.setText("Zadaj rodne cislo a stlac A pre potvrdenie volby");
        } else if (stav == PRIHLASENIE_PIN) {
            textArea.setText("Zadajte svoj PIN a stlacte A pre potvrdenie volby");
        } else if (stav == PRIHLASENIE_OK) {
            textArea.setText("Prihlasenie prebehlo uspesne, prosim vyberte jednu z moznosti:\n" +
                    "A) Otvorit dalsi ucet\n" +
                    "B) Vklad na ucet\n" +
                    "C) Vyber z uctu\n" +
                    "D) Zmen PIN\n" +
                    "E) Zrusit ucet\n" +
                    "F) Odhlasit sa");
        } else if (stav == ZALOZENIE_MENO) {
            textArea.setText("Zadajte MENO \na stlacte A pre potvrdenie volby ");
        } else if (stav == ZALOZENIE_PRIEZVISKO) {
            textArea.setText("Zadajte PRIEZVISKO \na stlacte A pre potvrdenie volby ");
        } else if (stav == ZALOZENIE_UCET) {
            textArea.setText("A: Bezny ucet  B: Sporiaci ucet");
        } else if (stav == ZALOZENIE_BEZNY) {
            textArea.setText("Prosím vložte sumu minimálne 100 eur\na stlacte A pre potvrdenie volby ");
        } else if (stav == ZALOZENIE_SPORIACI) {
            textArea.setText("Prosím vložte sumu minimálne 50 eur\na stlacte A pre potvrdenie volby ");
        } else if (stav == ZRUSENIE_UCTU) {
            textArea.setText("Zvolte A pre zrusenie Bezneho uctu alebo B pre zrusenie Sporiaceho uctu");
        } else if (stav == ZMENA_PIN) {
            textArea.setText("Zadajte novy PIN\na stlacte A pre potvrdenie volby");
        } else if (stav == VKLAD) {
            textArea.setText("Zvolte A na vklad na bezny ucet\nzvolte B pre vklad na sporiaci ucet");
        } else if (stav == VYBER) {
            textArea.setText("Zvolte A pre vyber z bezneho uctu\nzvolte B pre vyber zo sporiaceho uctu");
        } else if (stav == VKLAD_BEZNY) {
            textArea.setText("Zvolte sumu vkladu na bezny ucet\na stlacte A pre potvrdenie volby");
        } else if (stav == VKLAD_SPORIACI) {
            textArea.setText("Zvolte sumu vkladu na sporiaci ucet\na stlacte A pre potvrdenie volby");
        } else if (stav == VYBER_BEZNY) {
            textArea.setText("Zvolte sumu vyberu z bezneho uctu\na stlacte A pre potvrdenie volby");
        } else if (stav == VYBER_SPORIACI) {
            textArea.setText("Zvolte sumu vyberu zo sporiaceho uctu\na stlacte A pre potvrdenie volby");
        }

    }

}
