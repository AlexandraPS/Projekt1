package sk.plajdickova.patnast1;

public class Veta {

    private String text;

    public Veta(String text) {
        this.text = text;
    }
    void otocPoradie() {
        char [] pole = new char[text.length()];
        otocPoradie(pole, 0);
        text = new String(pole);
    }
    void otocPoradie(char [] pole, int i) {
        if (i <text.length()) {
            pole[i] = text.charAt(text.length() - i -1);
            otocPoradie(pole, i +1);
        }
    }
    /*public void otocPoradie() {
        char[] pole = new char[text.length()];
        for (int i = 0; i < text.length(); i++) {
            pole[i] = text.charAt(text.length() - i - 1);
        }
        text = new String(pole);
    } */

    public String getText() {

        return text;
    }

}
