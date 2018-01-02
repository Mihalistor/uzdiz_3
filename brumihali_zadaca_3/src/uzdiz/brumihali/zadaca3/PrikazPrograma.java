/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uzdiz.brumihali.zadaca3;

import java.util.Scanner;

/**
 *
 * @author bruno
 */
public class PrikazPrograma {

    public static final String ANSI_ESC = "\033[";

    private static PrikazPrograma instance = new PrikazPrograma();
    int pozicijaKomanda = 1;
    int trenutnaPozicija = 1;
    int brojRedaka = 0;
    int brojStupaca = 0;
    int brojRedakaKomanda = 0;
    int helper = 0;

    private PrikazPrograma() {
    }

    public static PrikazPrograma getInstance() {
        if (instance == null) {
            instance = new PrikazPrograma();
        }
        return instance;
    }

    public void postavi(int retci, int stupci, int retciUpis) {
        System.out.print(ANSI_ESC + "2J");
        brojRedaka = retci;
        brojStupaca = stupci;
        brojRedakaKomanda = retciUpis;
        pozicijaKomanda = retci - retciUpis;
        helper = retci - retciUpis;
    }

    public void postavi(int x, int y) {
        System.out.print(ANSI_ESC + x + ";" + y + "f");
    }

    public void postaviNaKomande() {       
        System.out.print(ANSI_ESC + pozicijaKomanda + ";" + 0 + "f");
        pozicijaKomanda++;
        if (pozicijaKomanda == (brojRedaka + 2)) {
            pozicijaKomanda = helper;
            obrisiKomande();
        }
    }

    public void obrisiKomande() {
        int broj = brojRedakaKomanda;
        int x = helper;
        while (broj >= 0) {
            postavi(x, 0);
            System.out.print(ANSI_ESC + "37m");
            System.out.print("                            ");
            x++;
            broj--;
        }
        postavi(helper,0);
        pozicijaKomanda = helper;
    }

    public void prikazi(String tekst) {
        if (trenutnaPozicija >= helper) {
            String komanda = "";
            while (!komanda.equalsIgnoreCase("n")) {
                System.out.print(" -> ");
                Scanner userInput = new Scanner(System.in);
                komanda = userInput.nextLine();
            }
            System.out.print("\033[H\033[2J");
            System.out.flush();
            trenutnaPozicija = 1;
            pozicijaKomanda = helper;
        }
        postavi(trenutnaPozicija, 1);
        System.out.print(ANSI_ESC + "37m");
        if (tekst.length() > brojStupaca) {
            tekst = tekst.substring(0, brojStupaca - 1);
        }
        System.out.print(tekst);
        trenutnaPozicija++;
    }
}
