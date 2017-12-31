/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uzdiz.brumihali.zadaca3;

import java.util.Calendar;
import java.util.List;
import java.util.Scanner;
import uzdiz.brumihali.zadaca3.observer.Observer;
import uzdiz.brumihali.zadaca3.observer.ObserverImpl;
import uzdiz.brumihali.zadaca3.observer.Subject;
import uzdiz.brumihali.zadaca3.observer.SubjectImpl;
import uzdiz.brumihali.zadaca3.podaci.Aktuator;
import uzdiz.brumihali.zadaca3.podaci.Mjesto;
import uzdiz.brumihali.zadaca3.podaci.Senzor;

/**
 *
 * @author bruno
 */
public class KonfiguracijaSustava {

    public static void main(String[] args) {
        GeneratorBrojeva generatorBrojeva = GeneratorBrojeva.getInstance();
        int brojRedaka = -1;
        int brojStupaca = -1;
        int brojRedakaUpis = -1;
        int prosjecnaIspravnost = -1;
        int sjemeGenerator = -1;
        String nazivMjesta = null;
        String nazivSenzora = null;
        String nazivAktuatora = null;
        String nazivRasporeda = null;
        int trajanjeCiklusa = -1;

        try {
            if (args.length == 1) {
                if (args[0].equals("--help")) {
                    prikaziHelp();
                } else {
                    System.out.println("POGRESNI PARAMETRI");
                }
            } else {
                for (int i = 0; i < args.length; i++) {
                    if (args[i].equals("-br")) {
                        brojRedaka = Integer.parseInt(args[i + 1]);
                    } else if (args[i].equals("-bs")) {
                        brojStupaca = Integer.parseInt(args[i + 1]);
                    } else if (args[i].equals("-brk")) {
                        brojRedakaUpis = Integer.parseInt(args[i + 1]);
                    } else if (args[i].equals("-pi")) {
                        prosjecnaIspravnost = Integer.parseInt(args[i + 1]);
                    } else if (args[i].equals("-g")) {
                        sjemeGenerator = Integer.parseInt(args[i + 1]);
                    } else if (args[i].equals("-m")) {
                        nazivMjesta = args[i + 1];
                    } else if (args[i].equals("-s")) {
                        nazivSenzora = args[i + 1];
                    } else if (args[i].equals("-a")) {
                        nazivAktuatora = args[i + 1];
                    } else if (args[i].equals("-r")) {
                        nazivRasporeda = args[i + 1];
                    } else if (args[i].equals("-tcd")) {
                        trajanjeCiklusa = Integer.parseInt(args[i + 1]);
                    }
                }
                if (nazivMjesta == null || nazivSenzora == null || nazivAktuatora == null || nazivRasporeda == null) {
                    System.out.println("POPUNITE OBAVEZNE PARAMETRE");
                    System.exit(0);
                } else {
                    if (sjemeGenerator == 0 || sjemeGenerator < 100 || sjemeGenerator > 65535) {
                        Calendar now = Calendar.getInstance();
                        int second = now.get(Calendar.SECOND);
                        int millis = now.get(Calendar.MILLISECOND);
                        sjemeGenerator = second * 1000 + millis;
                    }
                    GeneratorBrojeva.sjeme = sjemeGenerator;

                    if (brojRedaka == -1) {
                        brojRedaka = 24;
                    } else if (brojRedaka < 24 || brojRedaka > 40) {
                        brojRedaka = generatorBrojeva.dajSlucaniBroj(24, 40);
                    }

                    if (brojStupaca == -1) {
                        brojStupaca = 80;
                    } else if (brojStupaca < 80 || brojStupaca > 160) {
                        brojStupaca = generatorBrojeva.dajSlucaniBroj(80, 160);
                    }

                    if (brojRedakaUpis == -1) {
                        brojRedakaUpis = 2;
                    } else if (brojRedakaUpis < 2 || brojRedakaUpis > 5) {
                        brojRedakaUpis = generatorBrojeva.dajSlucaniBroj(2, 5);
                    }

                    if (prosjecnaIspravnost == -1) {
                        prosjecnaIspravnost = 50;
                    } else if (prosjecnaIspravnost < 0 || prosjecnaIspravnost > 100) {
                        prosjecnaIspravnost = generatorBrojeva.dajSlucaniBroj(0, 100);
                    }
                    InicijalizacijaSustava.prosjecnaIspravnost = prosjecnaIspravnost;

                    if (trajanjeCiklusa < 0) {
                        trajanjeCiklusa = generatorBrojeva.dajSlucaniBroj(1, 17);
                    }
                    ProvjeraMjesta.trajanjeCiklusa = trajanjeCiklusa;

                    System.out.println("broj redaka: " + brojRedaka);
                    System.out.println("broj stupaca: " + brojStupaca);
                    System.out.println("broj redaka upis: " + brojRedakaUpis);
                    System.out.println("prosjecna ispravnost: " + prosjecnaIspravnost);
                    System.out.println("sjeme: " + sjemeGenerator);
                    System.out.println("mjesta: " + nazivMjesta);
                    System.out.println("senzor: " + nazivSenzora);
                    System.out.println("aktuator: " + nazivAktuatora);
                    System.out.println("raspored: " + nazivRasporeda);
                    System.out.println("trajanje: " + trajanjeCiklusa);

                    KonfiguracijaSustava konfiguracijaSustava = new KonfiguracijaSustava();
                    konfiguracijaSustava.konfiguriraj(nazivMjesta, nazivSenzora, nazivAktuatora, nazivRasporeda);
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("GRESKA KOD PARAMETARA");
        }
    }

    private void konfiguriraj(String nazivMjesta, String nazivSenzora, String nazivAktuatora, String nazivRasporeda) {
        ObradaDatoteka obradaDatoteka = new ObradaDatoteka();
        List<Mjesto> listaMjesta = obradaDatoteka.dohvatiMjesta(nazivMjesta);
        List<Senzor> listaSenzora = obradaDatoteka.dohvatiSenzore(nazivSenzora);
        List<Aktuator> listaAktuatora = obradaDatoteka.dohvatiAktuatore(nazivAktuatora);
        obradaDatoteka.dodjeliUredaje(nazivRasporeda);
        obradaDatoteka.dodjeliSenzoreAktuatorima(nazivRasporeda);

        InicijalizacijaSustava inicijalizacijaSustava = new InicijalizacijaSustava();
        inicijalizacijaSustava.inicijalizirajSve(listaMjesta);

        Komande komande = new Komande(listaMjesta);
 /*       String komanda0 = "M 2";
        String komanda1 = "S 1023";
        String komanda2 = "A 1031";
        String komanda3 = "S";
        String komanda4 = "SP";
        String komanda5 = "VP";
        String komanda6 = "C 100";
        String komanda7 = "VF";
        String komanda8 = "PI 50";
        String komanda9 = "I";
        String komanda10 = "H"; */

        Scanner userInput = new Scanner(System.in);
        String komanda = "";
        while (true) {
            System.out.println("");
            System.out.print("UPISITE KOMANDU: ");
            komanda = userInput.nextLine();
            komande.izvrsiKomande(komanda);
        }
    }

    private static void prikaziHelp() {
        System.out.println("H E L P");
        System.out.println("-br  --> broj redaka na ekranu (24-40)");
        System.out.println("-bs  --> broj stupaca na ekranu (80-160)");
        System.out.println("-brk  --> broj redaka na ekranu za unos komandi (2-5)");
        System.out.println("-pi  --> prosjecni % ispravnosti uredaja (0-100)");
        System.out.println("-g  --> sjeme za generator slucajnog broja (100-65535)");
        System.out.println("-m  --> naziv datoteke mjesta");
        System.out.println("-s  --> naziv datoteke senzora");
        System.out.println("-a  --> naziv datoteke aktuatora");
        System.out.println("-r  --> naziv datoteke rasporeda");
        System.out.println("-tcd  --> trajanje ciklusa dretve u sek (1-17)");
    }

}
