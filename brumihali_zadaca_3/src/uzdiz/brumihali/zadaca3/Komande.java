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
    Statistika statistika = Statistika.getInstance();
    Caretaker caretaker = new Caretaker();
    SpremistePodataka sp = new SpremistePodataka();
    PrikazPrograma pp = PrikazPrograma.getInstance();

    public Komande(List<Mjesto> listaMjesta) {
        this.listaMjesta = listaMjesta;
    }

    public void izvrsiKomande(String komanda) {
        statistika.setBrojIzvrsenihKomandi(statistika.getBrojIzvrsenihKomandi() + 1);
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
            pp.prikazi("KRIVO UPISANA KOMANDA");
        }

    }

    public void ispisMjesta(Integer id) {
        pp.prikazi("KOMANDA ISPIS MJESTA");
        Boolean postoji = false;
        for (Mjesto mjesto : listaMjesta) {
            if (mjesto.getIdMjesta().equals(id)) {
                postoji = true;
                pp.prikazi("");
                pp.prikazi(String.format("|%-104s|", "============================================= ISPIS MJESTA =================================================="));
                pp.prikazi(String.format("|%-45s|%-15s|%-15s|%-15s|%-15s|", "Naziv mjesta", "ID mjesta", "Tip mjesta", "Broj senzora", "Broj aktuatora"));
                pp.prikazi(String.format("|%-104s|", "-------------------------------------------------------------------------------------------------------------"));
                pp.prikazi(String.format("|%-45s|%15s|%15s|%15s|%15s|", mjesto.getNazivMjesta(), mjesto.getIdMjesta(), mjesto.getTipMjesta(), mjesto.getBrojSenzora(), mjesto.getBrojAktuatora()));
                pp.prikazi(String.format("|%-143s|", "============================================================== ISPIS SENZORA =================================================================="));
                pp.prikazi(String.format("|%-40s|%-15s|%-15s|%-9s|%-9s|%-16s|%-12s|%-20s|", "Naziv senzora", "ID senzora", "Status senzora", "MIN", "MAX", "Vrijednost", "Broj greski", "Napomena"));
                pp.prikazi(String.format("|%-143s|", "-----------------------------------------------------------------------------------------------------------------------------------------------"));
                for (Senzor s : mjesto.getSenzori()) {
                    if (s.getBrojGreski() == 3) {
                        pp.prikazi(String.format("|%-40s|%15s|%15s|%9s|%9s|%16s|%12s|%-20s|", s.getNazivSenzora(), s.getIdSenzora(), s.getStatusSenzora(), s.getMinVrijednostSenzora(), s.getMaxVrijednostSenzora(), s.getVrijednostSenzora(), s.getBrojGreski(), "NEAKTIVAN"));
                    } else {
                        pp.prikazi(String.format("|%-40s|%15s|%15s|%9s|%9s|%16s|%12s|%-20s|", s.getNazivSenzora(), s.getIdSenzora(), s.getStatusSenzora(), s.getMinVrijednostSenzora(), s.getMaxVrijednostSenzora(), s.getVrijednostSenzora(), s.getBrojGreski(), "AKTIVAN"));
                    }
                    pp.prikazi(String.format("|%-143s|", "-----------------------------------------------------------------------------------------------------------------------------------------------"));
                }
                pp.prikazi(String.format("|%-170s|", "============================================================================== ISPIS AKTUATORA ==============================================================================="));
                pp.prikazi(String.format("|%-40s|%-15s|%-18s|%-9s|%-9s|%-13s|%-30s|%-12s|%-20s|", "Naziv aktuatora", "ID aktuatora", "Status aktuatora", "MIN", "MAX", "Vrijednost", "ID dodjeljenih senzora", "Broj greski", "Napomena"));
                pp.prikazi(String.format("|%-170s|", "------------------------------------------------------------------------------------------------------------------------------------------------------------------------------"));
                for (Aktuator a : mjesto.getAktuatori()) {
                    String popisSenzora = "";
                    for (Senzor s : a.getPopisSenzora()) {
                        popisSenzora += (s.getIdSenzora() + ", ");
                    }
                    popisSenzora = popisSenzora.substring(0, popisSenzora.lastIndexOf(","));
                    if (a.getBrojGreski() == 3) {
                        pp.prikazi(String.format("|%-40s|%15s|%18s|%9s|%9s|%13s|%30s|%12s|%-20s|", a.getNazivAktuatora(), a.getIdAktuatora(), a.getStatusAktuatora(), a.getMinVrijednostAktuatora(), a.getMaxVrijednostAktuatora(), a.getVrijednostAktuatora().intValue(), popisSenzora, a.getBrojGreski(), "NEAKTIVAN"));
                    } else {
                        pp.prikazi(String.format("|%-40s|%15s|%18s|%9s|%9s|%13s|%30s|%12s|%-20s|", a.getNazivAktuatora(), a.getIdAktuatora(), a.getStatusAktuatora(), a.getMinVrijednostAktuatora(), a.getMaxVrijednostAktuatora(), a.getVrijednostAktuatora().intValue(), popisSenzora, a.getBrojGreski(), "AKTIVAN"));
                    }
                    pp.prikazi(String.format("|%-170s|", "------------------------------------------------------------------------------------------------------------------------------------------------------------------------------"));
                }
            }
        }
        if (!postoji) {
            pp.prikazi("Ne postoji trazeno mjesto s tim ID-em");
        }
        pp.prikazi("");
    }

    public void ispisSenzora(Integer id) {
        pp.prikazi("KOMANDA ISPIS SENZORA");
        Boolean postoji = false;
        for (Mjesto mjesto : listaMjesta) {
            for (Senzor s : mjesto.getSenzori()) {
                if (s.getIdSenzora().equals(id)) {
                    postoji = true;
                    pp.prikazi("");
                    pp.prikazi(String.format("|%-143s|", "-----------------------------------------------------------------------------------------------------------------------------------------------"));
                    pp.prikazi(String.format("|%-143s|", "MJESTO: " + mjesto.getNazivMjesta()));
                    pp.prikazi(String.format("|%-143s|", "============================================================== ISPIS SENZORA =================================================================="));
                    pp.prikazi(String.format("|%-40s|%-15s|%-15s|%-9s|%-9s|%-16s|%12s|%-20s|", "Naziv senzora", "ID senzora", "Status senzora", "MIN", "MAX", "Vrijednost", "Broj greski", "Napomena"));
                    pp.prikazi(String.format("|%-143s|", "-----------------------------------------------------------------------------------------------------------------------------------------------"));
                    if (s.getBrojGreski() == 3) {
                        pp.prikazi(String.format("|%-40s|%15s|%15s|%9s|%9s|%16s|%12s|%-20s|", s.getNazivSenzora(), s.getIdSenzora(), s.getStatusSenzora(), s.getMinVrijednostSenzora(), s.getMaxVrijednostSenzora(), s.getVrijednostSenzora(), s.getBrojGreski(), "NEAKTIVAN"));
                    } else {
                        pp.prikazi(String.format("|%-40s|%15s|%15s|%9s|%9s|%16s|%12s|%-20s|", s.getNazivSenzora(), s.getIdSenzora(), s.getStatusSenzora(), s.getMinVrijednostSenzora(), s.getMaxVrijednostSenzora(), s.getVrijednostSenzora(), s.getBrojGreski(), "AKTIVAN"));
                    }
                    pp.prikazi(String.format("|%-143s|", "-----------------------------------------------------------------------------------------------------------------------------------------------"));
                    break;
                }
            }
        }
        if (!postoji) {
            pp.prikazi("Ne postoji trazeni senzor s tim ID-em");
        }
        pp.prikazi("");
    }

    public void ispisAktuatora(Integer id) {
        pp.prikazi("KOMANDA ISPIS AKTUATORA");
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
                    pp.prikazi("");
                    pp.prikazi(String.format("|%-170s|", "------------------------------------------------------------------------------------------------------------------------------------------------------------------------------"));
                    pp.prikazi(String.format("|%-174s|", "MJESTO: " + mjesto.getNazivMjesta()));
                    pp.prikazi(String.format("|%-170s|", "============================================================================== ISPIS AKTUATORA ==============================================================================="));
                    pp.prikazi(String.format("|%-40s|%-15s|%-18s|%-9s|%-9s|%-13s|%-30s|%12s|%-20s|", "Naziv aktuatora", "ID aktuatora", "Status aktuatora", "MIN", "MAX", "Vrijednost", "ID dodjeljenih senzora", "Broj greski", "Napomena"));
                    pp.prikazi(String.format("|%-170s|", "------------------------------------------------------------------------------------------------------------------------------------------------------------------------------"));
                    if (a.getBrojGreski() == 3) {
                        pp.prikazi(String.format("|%-40s|%15s|%18s|%9s|%9s|%13s|%30s|%12s|%-20s|", a.getNazivAktuatora(), a.getIdAktuatora(), a.getStatusAktuatora(), a.getMinVrijednostAktuatora(), a.getMaxVrijednostAktuatora(), a.getVrijednostAktuatora().intValue(), popisSenzora, a.getBrojGreski(), "NEAKTIVAN"));
                    } else {
                        pp.prikazi(String.format("|%-40s|%15s|%18s|%9s|%9s|%13s|%30s|%12s|%-20s|", a.getNazivAktuatora(), a.getIdAktuatora(), a.getStatusAktuatora(), a.getMinVrijednostAktuatora(), a.getMaxVrijednostAktuatora(), a.getVrijednostAktuatora().intValue(), popisSenzora, a.getBrojGreski(), "AKTIVAN"));
                    }
                    pp.prikazi(String.format("|%-170s|", "------------------------------------------------------------------------------------------------------------------------------------------------------------------------------"));
                    break;
                }
            }
        }
        if (!postoji) {
            pp.prikazi("Ne postoji trazeni aktuator s tim ID-em");
        }
        pp.prikazi("");
    }

    public void ispisStatistike() {
        pp.prikazi("KOMANDA ISPIS STATISTIKE");
        pp.prikazi("1. Broj ispravnih mjesta iz CSV datoteke: " + statistika.getBrojIspravnihMjesta());
        pp.prikazi("2. Broj neispravnih mjesta iz CSV datoteke: " + statistika.getBrojNeispravnihMjesta());
        pp.prikazi("3. Broj ispravnih senzora iz CSV datoteke: " + statistika.getBrojIspravnihSenzora());
        pp.prikazi("4. Broj neispravnih senzora iz CSV datoteke: " + statistika.getBrojNeispravnihSenzora());
        pp.prikazi("5. Broj ispravnih aktuatora iz CSV datoteke: " + statistika.getBrojIspravnihAktuatora());
        pp.prikazi("6. Broj neispravnih aktuatora iz CSV datoteke: " + statistika.getBrojNeispravnihAktuatora());
        pp.prikazi("7. Broj ispravnih dodjela senzora mjestima: " + statistika.getBrojDodjeljenihSenzora());
        pp.prikazi("8. Broj neispravnih dodjela senzora mjestima: " + statistika.getBrojNeDodjeljenihSenzora());
        pp.prikazi("9. Broj ispravnih dodjela akutuatora mjestima: " + statistika.getBrojDodjeljenihAktuatora());
        pp.prikazi("10. Broj neispravnih dodjela akutuatora mjestima: " + statistika.getBrojNeDodjeljenihAktuatora());
        pp.prikazi("11. Broj ispravnih dodjela senzora aktuatorima: " + statistika.getBrojDodjeljenihSenzoraAktuatorima());
        pp.prikazi("12. Broj neispravnih dodjela senzora aktuatorima: " + statistika.getBrojNeDodjeljenihSenzoraAktuatorima());
        pp.prikazi("13. Broj zamjena senzora: " + statistika.getZamjenaSenzora());
        pp.prikazi("14. Broj zamjena aktuatora: " + statistika.getZamjenaAktuatora());
        pp.prikazi("15. Broj ucitanih vrijednosti senzora: " + statistika.getVrijednostiSenzora());
        pp.prikazi("16. Broj ucitanih vrijednosti aktuatora: " + statistika.getVrijednostiAktuatora());
        pp.prikazi("17. Broj poslanih inicijalizacijskih poruka: " + statistika.getBrojIniPoruka());
        pp.prikazi("18. Broj generiranih brojeva: " + statistika.getBrojGeneriranihBrojeva());
        pp.prikazi("19. Broj izvrsenih komandi: " + statistika.getBrojIzvrsenihKomandi());
        pp.prikazi("20. Vrijeme pokretanja programa: " + statistika.getVrijemePokretanja());
    }

    public void spremiPodatke(List<Mjesto> lista) {
        pp.prikazi("KOMANDA SPREMANJE PODATAKA");
        List<Mjesto> l = new ArrayList<>();
        for (Mjesto mjesto : lista) {
            Mjesto m = new Mjesto(mjesto);
            m.getSenzori().clear();
            m.getAktuatori().clear();
            for (Senzor senzor : mjesto.getSenzori()) {
                Senzor s = new Senzor(senzor);
                s.getObservers().clear();
                m.getSenzori().add(s);
            }
            for (Aktuator aktuator : mjesto.getAktuatori()) {
                Aktuator a = new Aktuator(aktuator);
                a.getPopisSenzora().clear();
                m.getAktuatori().add(a);
            }

            for (Senzor senzor2 : m.getSenzori()) {
                for (Senzor senzor3 : mjesto.getSenzori()) {
                    if (senzor3.getIdSenzora().equals(senzor2.getIdSenzora())) {
                        for (Object o : senzor3.getObservers()) {
                            Aktuator a = (Aktuator) o;
                            for (Aktuator ak : m.getAktuatori()) {
                                if (a.getIdAktuatora().equals(ak.getIdAktuatora())) {
                                    senzor2.addObserver(ak);
                                    ak.getPopisSenzora().add(senzor2);
                                    break;
                                }
                            }
                        }
                        break;
                    }
                }
            }
            l.add(m);
        }

        sp.setLista(l);

        caretaker.addMemento(sp.saveToMemento());
        pp.prikazi("Podaci o mjestima i njihovim uređajima su spremljeni");
    }

    public void vratiPodatke() {
        pp.prikazi("KOMANDA VRACANJE PODATAKA");
        listaMjesta = sp.restoreFromMemento(caretaker.getMemento());
        pp.prikazi("Podaci o mjestima i njihovim uredajima su vraceni");
    }

    public void izvrsiDretvu(int brojCiklusa) {
        pp.prikazi("KOMANDA IZVRSAVANJE DRETVE");
        if (brojCiklusa < 1 || brojCiklusa > 100) {
            pp.prikazi("Broj ciklusa dretve mora biti u intervalu od 1 do 100. Ponovite komandu");
        } else {
            ProvjeraMjesta provjeraMjesta = new ProvjeraMjesta();
            provjeraMjesta.setListaMjesta(listaMjesta);
            provjeraMjesta.setBrojCiklusa(brojCiklusa);
            provjeraMjesta.start();
            try {
                provjeraMjesta.join();

            } catch (InterruptedException ex) {
                Logger.getLogger(Komande.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void vlastitaFunkcionalnost() {
        pp.prikazi("KOMANDA VLASTITA FUNKCIONALNOST KRECE");

    }

    public void postaviIspravnost(int prosjecnaIspravnost) {
        pp.prikazi("KOMANDA POSTAVLJANJE ISPRAVNOSTI");
        if (prosjecnaIspravnost < 0 || prosjecnaIspravnost > 100) {
            pp.prikazi("Prosjecna ispravnost mora biti u intervalu od 0 do 100. Ponovite komandu");
        } else {
            InicijalizacijaSustava.prosjecnaIspravnost = prosjecnaIspravnost;
            pp.prikazi("Prosjecna ispravnst je: " + InicijalizacijaSustava.prosjecnaIspravnost);
        }
    }

    public void izlaz() {
        pp.prikazi("KOMANDA IZLAZ IZ PROGRAMA");
        pp.prikazi("Kraj programa za 1 sekundu");
        try {
            sleep(1000);

        } catch (InterruptedException ex) {
            Logger.getLogger(Komande.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        System.exit(0);
    }

    public void help() {
        pp.prikazi("POMOC KOD UPISA KOMANDI");
        pp.prikazi("M x - ispis podataka mjesta x");
        pp.prikazi("S x - ispis podataka senzora x");
        pp.prikazi("A x - ispis podataka aktuatora x");
        pp.prikazi("S - ispis statistike");
        pp.prikazi("SP - spremi podatke (mjesta, uredaja)");
        pp.prikazi("VP - vrati spremljene podatke (mjesta, uredaja)");
        pp.prikazi("C n - izvrsavanje n ciklusa dretve (1-100)");
        pp.prikazi("VF [argumenti] - izvrsavanje vlastite funkcionalnosti, po potrebni moguci su argumenti");
        pp.prikazi("PI n - prosjecni % ispravnosti uredaja (0-100)");
        pp.prikazi("H - pomoc, ispis dopustenih komandi i njihov opis");
        pp.prikazi("I - izlaz.");
    }

    public List<Mjesto> getListaMjesta() {
        return listaMjesta;
    }

    public void setListaMjesta(List<Mjesto> listaMjesta) {
        this.listaMjesta = listaMjesta;
    }

}
