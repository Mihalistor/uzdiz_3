/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uzdiz.brumihali.zadaca3;

import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import uzdiz.brumihali.zadaca3.memento.Caretaker;
import uzdiz.brumihali.zadaca3.memento.SpremistePodataka;
import uzdiz.brumihali.zadaca3.podaci.Aktuator;
import uzdiz.brumihali.zadaca3.podaci.Mjesto;
import uzdiz.brumihali.zadaca3.podaci.Senzor;

/**
 *
 * @author bruno
 */
public class Komande {

    private List<Mjesto> listaMjesta = new ArrayList<>();
    private static List<Mjesto> spremljenaListaMjesta = new ArrayList<>();
    Caretaker caretaker = new Caretaker();
    SpremistePodataka sp = new SpremistePodataka();

    public Komande(List<Mjesto> listaMjesta) {
        this.listaMjesta = listaMjesta;
    }

    public void izvrsiKomande(String komanda) {
        String naredba;
        int vrijednost = 0;
        if (komanda.contains(" ")) {
            naredba = komanda.substring(0, komanda.indexOf(" "));
            vrijednost = Integer.parseInt(komanda.substring(komanda.indexOf(" ") + 1, komanda.length()));
        } else {
            naredba = komanda;
        }
        if (naredba.equals("M")) {
            ispisMjesta(vrijednost);
        } else if (naredba.equals("S") && vrijednost > 0) {
            ispisSenzora(vrijednost);
        } else if (naredba.equals("A")) {
            ispisAktuatora(vrijednost);
        } else if (naredba.equals("S")) {
            ispisStatistike();
        } else if (naredba.equals("SP")) {
            spremiPodatke(new ArrayList<>(listaMjesta));
        } else if (naredba.equals("VP")) {
            vratiPodatke();
        } else if (naredba.equals("C")) {
            izvrsiDretvu(vrijednost);
        } else if (naredba.equals("VF")) {
            vlastitaFunkcionalnost();
        } else if (naredba.equals("PI")) {
            postaviIspravnost(vrijednost);
        } else if (naredba.equals("H")) {
            help();
        } else if (naredba.equals("I")) {
            izlaz();
        } else {
            System.out.println("KRIVO UPISANA KOMANDA");
        }

    }

    public void ispisMjesta(Integer id) {
        System.out.println("KOMANDA ISPIS MJESTA");
        Boolean postoji = false;
        for (Mjesto mjesto : listaMjesta) {
            if (mjesto.getIdMjesta().equals(id)) {
                postoji = true;
                System.out.println("");
                System.out.println(String.format("|%-104s|", "============================================= ISPIS MJESTA =================================================="));
                System.out.println(String.format("|%-45s|%-15s|%-15s|%-15s|%-15s|", "Naziv mjesta", "ID mjesta", "Tip mjesta", "Broj senzora", "Broj aktuatora"));
                System.out.println(String.format("|%-104s|", "-------------------------------------------------------------------------------------------------------------"));
                System.out.println(String.format("|%-45s|%15s|%15s|%15s|%15s|", mjesto.getNazivMjesta(), mjesto.getIdMjesta(), mjesto.getTipMjesta(), mjesto.getBrojSenzora(), mjesto.getBrojAktuatora()));
                System.out.println(String.format("|%-143s|", "============================================================== ISPIS SENZORA =================================================================="));
                System.out.println(String.format("|%-40s|%-15s|%-15s|%-9s|%-9s|%-16s|%-12s|%-20s|", "Naziv senzora", "ID senzora", "Status senzora", "MIN", "MAX", "Vrijednost", "Broj greski", "Napomena"));
                System.out.println(String.format("|%-143s|", "-----------------------------------------------------------------------------------------------------------------------------------------------"));
                for (Senzor s : mjesto.getSenzori()) {
                    if (s.getBrojGreski() == 3) {
                        System.out.println(String.format("|%-40s|%15s|%15s|%9s|%9s|%16s|%12s|%-20s|", s.getNazivSenzora(), s.getIdSenzora(), s.getStatusSenzora(), s.getMinVrijednostSenzora(), s.getMaxVrijednostSenzora(), s.getVrijednostSenzora(), s.getBrojGreski(), "NEAKTIVAN"));
                    } else {
                        System.out.println(String.format("|%-40s|%15s|%15s|%9s|%9s|%16s|%12s|%-20s|", s.getNazivSenzora(), s.getIdSenzora(), s.getStatusSenzora(), s.getMinVrijednostSenzora(), s.getMaxVrijednostSenzora(), s.getVrijednostSenzora(), s.getBrojGreski(), "AKTIVAN"));
                    }
                    System.out.println(String.format("|%-143s|", "-----------------------------------------------------------------------------------------------------------------------------------------------"));
                }
                System.out.println(String.format("|%-170s|", "============================================================================== ISPIS AKTUATORA ==============================================================================="));
                System.out.println(String.format("|%-40s|%-15s|%-18s|%-9s|%-9s|%-13s|%-30s|%-12s|%-20s|", "Naziv aktuatora", "ID aktuatora", "Status aktuatora", "MIN", "MAX", "Vrijednost", "ID dodjeljenih senzora", "Broj greski", "Napomena"));
                System.out.println(String.format("|%-170s|", "------------------------------------------------------------------------------------------------------------------------------------------------------------------------------"));
                for (Aktuator a : mjesto.getAktuatori()) {
                    String popisSenzora = "";
                    for (Senzor s : a.getPopisSenzora()) {
                        popisSenzora += (s.getIdSenzora() + ", ");
                    }
                    popisSenzora = popisSenzora.substring(0, popisSenzora.lastIndexOf(","));
                    if (a.getBrojGreski() == 3) {
                        System.out.println(String.format("|%-40s|%15s|%18s|%9s|%9s|%13s|%30s|%12s|%-20s|", a.getNazivAktuatora(), a.getIdAktuatora(), a.getStatusAktuatora(), a.getMinVrijednostAktuatora(), a.getMaxVrijednostAktuatora(), a.getVrijednostAktuatora().intValue(), popisSenzora, a.getBrojGreski(), "NEAKTIVAN"));
                    } else {
                        System.out.println(String.format("|%-40s|%15s|%18s|%9s|%9s|%13s|%30s|%12s|%-20s|", a.getNazivAktuatora(), a.getIdAktuatora(), a.getStatusAktuatora(), a.getMinVrijednostAktuatora(), a.getMaxVrijednostAktuatora(), a.getVrijednostAktuatora().intValue(), popisSenzora, a.getBrojGreski(), "AKTIVAN"));
                    }
                    System.out.println(String.format("|%-170s|", "------------------------------------------------------------------------------------------------------------------------------------------------------------------------------"));
                }
            }
        }
        if (!postoji) {
            System.out.println("Ne postoji trazeno mjesto s tim ID-em");
        }
        System.out.println("");
    }

    public void ispisSenzora(Integer id) {
        System.out.println("KOMANDA ISPIS SENZORA");
        Boolean postoji = false;
        for (Mjesto mjesto : listaMjesta) {
            for (Senzor s : mjesto.getSenzori()) {
                if (s.getIdSenzora().equals(id)) {
                    postoji = true;
                    System.out.println("");
                    System.out.println(String.format("|%-143s|", "-----------------------------------------------------------------------------------------------------------------------------------------------"));
                    System.out.println(String.format("|%-143s|", "MJESTO: " + mjesto.getNazivMjesta()));
                    System.out.println(String.format("|%-143s|", "============================================================== ISPIS SENZORA =================================================================="));
                    System.out.println(String.format("|%-40s|%-15s|%-15s|%-9s|%-9s|%-16s|%12s|%-20s|", "Naziv senzora", "ID senzora", "Status senzora", "MIN", "MAX", "Vrijednost", "Broj greski", "Napomena"));
                    System.out.println(String.format("|%-143s|", "-----------------------------------------------------------------------------------------------------------------------------------------------"));
                    if (s.getBrojGreski() == 3) {
                        System.out.println(String.format("|%-40s|%15s|%15s|%9s|%9s|%16s|%12s|%-20s|", s.getNazivSenzora(), s.getIdSenzora(), s.getStatusSenzora(), s.getMinVrijednostSenzora(), s.getMaxVrijednostSenzora(), s.getVrijednostSenzora(), s.getBrojGreski(), "NEAKTIVAN"));
                    } else {
                        System.out.println(String.format("|%-40s|%15s|%15s|%9s|%9s|%16s|%12s|%-20s|", s.getNazivSenzora(), s.getIdSenzora(), s.getStatusSenzora(), s.getMinVrijednostSenzora(), s.getMaxVrijednostSenzora(), s.getVrijednostSenzora(), s.getBrojGreski(), "AKTIVAN"));
                    }
                    System.out.println(String.format("|%-143s|", "-----------------------------------------------------------------------------------------------------------------------------------------------"));
                    break;
                }
            }
        }
        if (!postoji) {
            System.out.println("Ne postoji trazeni senzor s tim ID-em");
        }
        System.out.println("");
    }

    public void ispisAktuatora(Integer id) {
        System.out.println("KOMANDA ISPIS AKTUATORA");
        Boolean postoji = false;
        for (Mjesto mjesto : listaMjesta) {
            for (Aktuator a : mjesto.getAktuatori()) {
                if (a.getIdAktuatora().equals(id)) {
                    postoji = true;
                    String popisSenzora = "";
                    for (Senzor s : a.getPopisSenzora()) {
                        popisSenzora += (s.getIdSenzora() + ", ");
                    }
                    popisSenzora = popisSenzora.substring(0, popisSenzora.lastIndexOf(","));
                    System.out.println("");
                    System.out.println(String.format("|%-170s|", "------------------------------------------------------------------------------------------------------------------------------------------------------------------------------"));
                    System.out.println(String.format("|%-174s|", "MJESTO: " + mjesto.getNazivMjesta()));
                    System.out.println(String.format("|%-170s|", "============================================================================== ISPIS AKTUATORA ==============================================================================="));
                    System.out.println(String.format("|%-40s|%-15s|%-18s|%-9s|%-9s|%-13s|%-30s|%12s|%-20s|", "Naziv aktuatora", "ID aktuatora", "Status aktuatora", "MIN", "MAX", "Vrijednost", "ID dodjeljenih senzora", "Broj greski", "Napomena"));
                    System.out.println(String.format("|%-170s|", "------------------------------------------------------------------------------------------------------------------------------------------------------------------------------"));
                    if (a.getBrojGreski() == 3) {
                        System.out.println(String.format("|%-40s|%15s|%18s|%9s|%9s|%13s|%30s|%12s|%-20s|", a.getNazivAktuatora(), a.getIdAktuatora(), a.getStatusAktuatora(), a.getMinVrijednostAktuatora(), a.getMaxVrijednostAktuatora(), a.getVrijednostAktuatora().intValue(), popisSenzora, a.getBrojGreski(), "NEAKTIVAN"));
                    } else {
                        System.out.println(String.format("|%-40s|%15s|%18s|%9s|%9s|%13s|%30s|%12s|%-20s|", a.getNazivAktuatora(), a.getIdAktuatora(), a.getStatusAktuatora(), a.getMinVrijednostAktuatora(), a.getMaxVrijednostAktuatora(), a.getVrijednostAktuatora().intValue(), popisSenzora, a.getBrojGreski(), "AKTIVAN"));
                    }
                    System.out.println(String.format("|%-170s|", "------------------------------------------------------------------------------------------------------------------------------------------------------------------------------"));
                    break;
                }
            }
        }
        if (!postoji) {
            System.out.println("Ne postoji trazeni aktuator s tim ID-em");
        }
        System.out.println("");
    }

    public void ispisStatistike() {
        System.out.println("KOMANDA ISPIS STATISTIKE");
    }

    public void spremiPodatke(List<Mjesto> lista) {
        System.out.println("KOMANDA SPREMANJE PODATAKA");
        List<Mjesto> l = new ArrayList<>(lista);
        lista.get(0).setIdMjesta(2000);
        System.out.println("id listaMjesta: " + lista.get(0).getIdMjesta());
        System.out.println("id l: " + l.get(0).getIdMjesta());
        sp.setLista(lista);
        caretaker.addMemento(sp.saveToMemento());
        System.out.println("Podaci o mjestima i njihovim uređajima su spremljeni");
    }

    public void vratiPodatke() {
        System.out.println("KOMANDA VRAĆANJE PODATAKA");
        sp.restoreFromMemento(caretaker.getMemento(0));

        /*
        if (spremljenaListaMjesta.size() == 0) {
            System.out.println("Potrebno je prvo spremiti podatke da bi ih vratili");
        } else {
            listaMjesta.clear();
            listaMjesta = new ArrayList<>(spremljenaListaMjesta);
            System.out.println("Podaci o mjestima i njihovim uređajima su vraćeni");
        }
         */
    }

    public void izvrsiDretvu(int brojCiklusa) {
        System.out.println("KOMANDA IZVRŠAVANJE DRETVE");
        if (brojCiklusa < 1 || brojCiklusa > 100) {
            System.out.println("Broj ciklusa dretve mora biti u intervalu od 1 do 100. Ponovite komandu");
        } else {
            ProvjeraMjesta provjeraMjesta = new ProvjeraMjesta();
            provjeraMjesta.setListaMjesta(listaMjesta);
            provjeraMjesta.setBrojCiklusa(brojCiklusa);
            provjeraMjesta.start();
            try {
                provjeraMjesta.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(Komande.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void vlastitaFunkcionalnost() {
        System.out.println("KOMANDA VLASTITA FUNKCIONALNOST KREĆE");

    }

    public void postaviIspravnost(int prosjecnaIspravnost) {
        System.out.println("KOMANDA POSTAVLJANJE ISPRAVNOSTI");
        if (prosjecnaIspravnost < 0 || prosjecnaIspravnost > 100) {
            System.out.println("Prosjecna ispravnost mora biti u intervalu od 0 do 100. Ponovite komandu");
        } else {
            InicijalizacijaSustava.prosjecnaIspravnost = prosjecnaIspravnost;
            System.out.println("Prosjecna ispravnst je: " + InicijalizacijaSustava.prosjecnaIspravnost);
        }
    }

    public void izlaz() {
        System.out.println("KOMANDA IZLAZ IZ PROGRAMA");
        System.out.println("Kraj programa za 1 sekundu");
        try {
            sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Komande.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.exit(0);
    }

    public void help() {
        System.out.println("POMOC KOD UPISA KOMANDI");
        System.out.println("M x - ispis podataka mjesta x\n"
                + "S x - ispis podataka senzora x\n"
                + "A x - ispis podataka aktuatora x\n"
                + "S - ispis statistike\n"
                + "SP - spremi podatke (mjesta, uredaja)\n"
                + "VP - vrati spremljene podatke (mjesta, uredaja)\n"
                + "C n - izvrsavanje n ciklusa dretve (1-100)\n"
                + "VF [argumenti] - izvrsavanje vlastite funkcionalnosti, po potrebni moguci su argumenti\n"
                + "PI n - prosjecni % ispravnosti uredaja (0-100)\n"
                + "H - pomoc, ispis dopustenih komandi i njihov opis\n"
                + "I - izlaz.");
    }

    public List<Mjesto> getListaMjesta() {
        return listaMjesta;
    }

    public void setListaMjesta(List<Mjesto> listaMjesta) {
        this.listaMjesta = listaMjesta;
    }

}
