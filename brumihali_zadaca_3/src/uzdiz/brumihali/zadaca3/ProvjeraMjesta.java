/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uzdiz.brumihali.zadaca3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import uzdiz.brumihali.zadaca3.podaci.Aktuator;
import uzdiz.brumihali.zadaca3.podaci.Mjesto;
import uzdiz.brumihali.zadaca3.podaci.Senzor;
import uzdiz.brumihali.zadaca3.iterator.Container;
import uzdiz.brumihali.zadaca3.iterator.Iterator;

/**
 *
 * @author bruno
 */
public class ProvjeraMjesta extends Thread implements Container {

    private List<Mjesto> listaMjesta = new ArrayList<>();
    private List<Senzor> listaSenzora = new ArrayList<>();
    private List<Aktuator> listaAktuatora = new ArrayList<>();
    public static int trajanjeCiklusa = 0;
    private int brojCiklusa = 0;
    private static long trajanjeObrade = 0;
    long vrijemePocetka = 0;
    long vrijemeZavrsetka = 0;
    private static List<Integer> listaIdUredaja = new ArrayList<>();

    GeneratorBrojeva generatorBrojeva = GeneratorBrojeva.getInstance();
    InicijalizacijaSustava inicijalizacijaSustava = new InicijalizacijaSustava();
    Statistika statistika = Statistika.getInstance();
    PrikazPrograma pp = PrikazPrograma.getInstance();

    @Override
    public void interrupt() {
        super.interrupt(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void run() {
        int i = 1;
        while (brojCiklusa >= 1) {
            vrijemePocetka = System.currentTimeMillis();
            pp.prikazi("Provjera mjesta " + i);
            for (Iterator iter = getIterator(); iter.hasNext();) {
                Mjesto mjesto = (Mjesto) iter.next();
                pp.prikazi("");
                pp.prikazi(String.format("|%-109s|", "-------------------------------------------------------------------------------------------------------------"));
                pp.prikazi(String.format("|%-109s|", "MJESTO: " + mjesto.getNazivMjesta()));
                provjeriStatus(mjesto);
                ocitajIspisiSenzore(mjesto);
                ocitajIspisiAktuatore(mjesto);
            }
            try {
                pp.prikazi("");
                pp.prikazi("");
                vrijemeZavrsetka = System.currentTimeMillis();
                trajanjeObrade = vrijemeZavrsetka - vrijemePocetka;
                long vrijeme = trajanjeCiklusa * 1000 - trajanjeObrade;
                if (vrijeme < 0) {
                    vrijeme = 0;
                }
                sleep(vrijeme);
            } catch (InterruptedException ex) {
                Logger.getLogger(ProvjeraMjesta.class.getName()).log(Level.SEVERE, null, ex);
            }
            brojCiklusa--;
            i++;
        }
    }

    public void provjeriStatus(Mjesto mjesto) {
        pp.prikazi(String.format("|%-109s|", "======================================== PROVJERA STATUSA UREDAJA ==========================================="));
        pp.prikazi(String.format("|%-40s|%-15s|%-15s|%-15s|%-20s|", "Naziv uredaja", "ID uredaja", "Status uredaja", "Broj greski", "Napomena"));
        pp.prikazi(String.format("|%-109s|", "-------------------------------------------------------------------------------------------------------------"));
        for (int i = mjesto.getSenzori().size() - 1; i >= 0; i--) {
            Senzor s = mjesto.getSenzori().get(i);
            if (s.getBrojGreski() != 3) {
                int greske = 0;
                int status = inicijalizacijaSustava.odgovorPoruka();
                if (status == 1) {
                    s.setState(true);
                }
                s.setStatusSenzora(status);
                if (status == 0) {
                    greske = s.getBrojGreski() + 1;
                    s.setBrojGreski(greske);
                } else {
                    s.setBrojGreski(0);
                }
                pp.prikazi(String.format("|%-40s|%15s|%15s|%15s|%-20s|", "S: " + s.getNazivSenzora(), s.getIdSenzora(), s.getStatusSenzora(), s.getBrojGreski(), "AKTIVAN"));
                if (greske == 3) {
                    pp.prikazi("");
                    pp.prikazi(" - - - - - - - - - - - - ZAMJENA SENZORA - - - - - - - - - - -");
                    Senzor noviSenzor = (Senzor) s.clone();
                    noviSenzor.setVrijednostSenzora(0.0f);
                    noviSenzor.setBrojGreski(0);
                    Collections.sort(listaIdUredaja, Collections.reverseOrder());
                    noviSenzor.setIdSenzora(listaIdUredaja.get(0) + 1);
                    for(Aktuator akt : mjesto.getAktuatori()){
                        if(akt.getPopisSenzora().contains(s)){
                            akt.getPopisSenzora().remove(s);
                            akt.getPopisSenzora().add(noviSenzor);                           
                        }
                    }                  
                    listaIdUredaja.add(listaIdUredaja.get(0) + 1);
                    inicijalizacijaSustava.inicijalizirajSenzor(noviSenzor);
                    mjesto.getSenzori().add(new Senzor(noviSenzor)); 
                    statistika.setZamjenaSenzora(statistika.getZamjenaSenzora()+1);
                    pp.prikazi(String.format("|%-61s|", "Senzor ID: " + s.getIdSenzora() + " - 3 greske - novi ID: " + noviSenzor.getIdSenzora()));
                    pp.prikazi(" - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
                    pp.prikazi("");
                    pp.prikazi(String.format("|%-40s|%15s|%15s|%15s|%-20s|", "S: " + noviSenzor.getNazivSenzora(), noviSenzor.getIdSenzora(), noviSenzor.getStatusSenzora(), noviSenzor.getBrojGreski(), "AKTIVAN"));
                }
            } else {
                pp.prikazi(String.format("|%-40s|%15s|%15s|%15s|%-20s|", "S: " + s.getNazivSenzora(), s.getIdSenzora(), s.getStatusSenzora(), s.getBrojGreski(), "NEAKTIVAN"));
            }
        }

        for (int i = mjesto.getAktuatori().size() - 1; i >= 0; i--) {
            Aktuator a = mjesto.getAktuatori().get(i);
            if (a.getBrojGreski() != 3) {
                int greske = 0;
                int status = inicijalizacijaSustava.odgovorPoruka();
                a.setStatusAktuatora(status);
                if (status == 0) {
                    greske = a.getBrojGreski() + 1;
                    a.setBrojGreski(greske);
                } else {
                    a.setBrojGreski(0);
                }
                pp.prikazi(String.format("|%-40s|%15s|%15s|%15s|%-20s|", "A: " + a.getNazivAktuatora(), a.getIdAktuatora(), a.getStatusAktuatora(), a.getBrojGreski(), "AKTIVAN"));
                if (greske == 3) {
                    pp.prikazi("");
                    pp.prikazi(" - - - - - - - - - - - - ZAMJENA AKTUATORA - - - - - - - - - - ");
                    Aktuator noviAktuator = (Aktuator) a.clone();
                    noviAktuator.setBrojGreski(0);
                    noviAktuator.setVrijednostAktuatora(0.0f);
                    Collections.sort(listaIdUredaja, Collections.reverseOrder());
                    noviAktuator.setIdAktuatora(listaIdUredaja.get(0) + 1);
                    listaIdUredaja.add(listaIdUredaja.get(0) + 1);
                    inicijalizacijaSustava.inicijalizirajAktuator(noviAktuator);
                    mjesto.getAktuatori().add(new Aktuator(noviAktuator));
                    statistika.setZamjenaAktuatora(statistika.getZamjenaAktuatora()+1);
                    pp.prikazi(String.format("|%-61s|", "Aktuator ID: " + a.getIdAktuatora() + " - 3 greske - novi ID: " + noviAktuator.getIdAktuatora()));
                    pp.prikazi(" - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ");
                    pp.prikazi("");
                    pp.prikazi(String.format("|%-40s|%15s|%15s|%15s|%-20s|", "A: " + noviAktuator.getNazivAktuatora(), noviAktuator.getIdAktuatora(), noviAktuator.getStatusAktuatora(), noviAktuator.getBrojGreski(), "AKTIVAN"));
                }
            } else {
                pp.prikazi(String.format("|%-40s|%15s|%15s|%15s|%-20s|", "A: " + a.getNazivAktuatora(), a.getIdAktuatora(), a.getStatusAktuatora(), a.getBrojGreski(), "NEAKTIVAN"));
            }
        }
        pp.prikazi(String.format("|%-88s|", "============================================================================================================="));
    }

    public void ocitajIspisiSenzore(Mjesto mjesto) {
        pp.prikazi(String.format("|%-109s|", "============================================ OCITANJE SENZORA ==============================================="));
        pp.prikazi(String.format("|%-40s|%-15s|%-15s|%-9s|%-9s|%-16s|", "Naziv senzora", "ID senzora", "Status senzora", "MIN", "MAX", "Vrijednost"));
        pp.prikazi(String.format("|%-109s|", "-------------------------------------------------------------------------------------------------------------"));
        for (Senzor s : mjesto.getSenzori()) {
            if (s.getStatusSenzora() == 1) {
                if (s.getVrstaSenzora() == 0) {
                    int min = s.getMinVrijednostSenzora().intValue();
                    int max = s.getMaxVrijednostSenzora().intValue();
                    int vrijednost = generatorBrojeva.dajSlucaniBroj(min, max);
                    float pom = (float) vrijednost;
                    s.setVrijednostSenzora(pom);
                } else if (s.getVrstaSenzora() == 1) {
                    Float min = s.getMinVrijednostSenzora();
                    Float max = s.getMaxVrijednostSenzora();
                    float vrijednost = generatorBrojeva.dajSlucaniBroj(min, max);
                    s.setVrijednostSenzora(Float.parseFloat(String.format("%.1f", vrijednost).replace(",", ".")));
                } else if (s.getVrstaSenzora() == 2) {
                    Float min = s.getMinVrijednostSenzora();
                    Float max = s.getMaxVrijednostSenzora();
                    float vrijednost = generatorBrojeva.dajSlucaniBroj(min, max);
                    s.setVrijednostSenzora(Float.parseFloat(String.format("%.5f", vrijednost).replace(",", ".")));
                } else if (s.getVrstaSenzora() == 3) {
                    int min = s.getMinVrijednostSenzora().intValue();
                    int max = s.getMaxVrijednostSenzora().intValue();
                    int vrijednost = generatorBrojeva.dajSlucaniBroj(min, max);
                    float pom = (float) vrijednost;
                    s.setVrijednostSenzora(pom);
                }
                if (s.getVrstaSenzora() == 0 || s.getVrstaSenzora() == 3) {
                    pp.prikazi(String.format("|%-40s|%15s|%15s|%9s|%9s|%16s|", s.getNazivSenzora(), s.getIdSenzora(), s.getStatusSenzora(), s.getMinVrijednostSenzora(), s.getMaxVrijednostSenzora(), s.getVrijednostSenzora().intValue()));
                } else {
                    pp.prikazi(String.format("|%-40s|%15s|%15s|%9s|%9s|%16s|", s.getNazivSenzora(), s.getIdSenzora(), s.getStatusSenzora(), s.getMinVrijednostSenzora(), s.getMaxVrijednostSenzora(), s.getVrijednostSenzora()));
                }
                statistika.setVrijednostiSenzora(statistika.getVrijednostiSenzora()+1);
            }
        }
        pp.prikazi(String.format("|%-109s|", "============================================================================================================="));
    }

    public void ocitajIspisiAktuatore(Mjesto mjesto) {
        pp.prikazi(String.format("|%-109s|", "=========================================================== OCITANJE AKTUATORA ============================================================="));
        pp.prikazi(String.format("|%-40s|%-15s|%-18s|%-9s|%-9s|%-13s|%-30s|", "Naziv aktuatora", "ID aktuatora", "Status aktuatora", "MIN", "MAX", "Vrijednost", "ID dodjeljenih senzora"));
        pp.prikazi(String.format("|%-109s|", "--------------------------------------------------------------------------------------------------------------------------------------------"));
        for (Aktuator a : mjesto.getAktuatori()) {
            String listaDodjeljenihSenzora = "";
            for (Senzor s : a.getPopisSenzora()) {
                listaDodjeljenihSenzora += s.getIdSenzora() + ", ";
            }
            if (listaDodjeljenihSenzora != "") {
                listaDodjeljenihSenzora = listaDodjeljenihSenzora.substring(0, listaDodjeljenihSenzora.lastIndexOf(","));
            }
            if (a.getStatusAktuatora() == 1) {
                if (a.getState()) {
                    if (a.getVrstaAktuatora() == 0 || a.getVrstaAktuatora() == 3) {
                        pp.prikazi(String.format("|%-40s|%15s|%18s|%9s|%9s|%13s|%30s|", a.getNazivAktuatora(), a.getIdAktuatora(), a.getStatusAktuatora(), a.getMinVrijednostAktuatora(), a.getMaxVrijednostAktuatora(), a.getVrijednostAktuatora().intValue(), listaDodjeljenihSenzora));
                    } else {
                        pp.prikazi(String.format("|%-40s|%15s|%18s|%9s|%9s|%13s|%30s|", a.getNazivAktuatora(), a.getIdAktuatora(), a.getStatusAktuatora(), a.getMinVrijednostAktuatora(), a.getMaxVrijednostAktuatora(), a.getVrijednostAktuatora(), listaDodjeljenihSenzora));
                    }
                    Float staraVrijednost = a.getVrijednostAktuatora();
                    Boolean smjer = a.getSmjer();
                    int vrijednost;
                    float vrijednost2;
                    if (a.getVrstaAktuatora() == 0) {
                        int min = a.getMinVrijednostAktuatora().intValue();
                        int max = a.getMaxVrijednostAktuatora().intValue();
                        if (smjer) {
                            vrijednost = generatorBrojeva.dajSlucaniBroj(staraVrijednost.intValue() + 1, max);
                            if (vrijednost == max) {
                                a.setSmjer(false);
                            }
                        } else {
                            vrijednost = generatorBrojeva.dajSlucaniBroj(min, staraVrijednost.intValue());
                            if (vrijednost == min) {
                                a.setSmjer(true);
                            }
                        }
                        float pom = (float) vrijednost;
                        a.setVrijednostAktuatora(pom);

                    } else if (a.getVrstaAktuatora() == 1) {
                        float min = a.getMinVrijednostAktuatora();
                        float max = a.getMaxVrijednostAktuatora();
                        if (smjer) {
                            vrijednost2 = generatorBrojeva.dajSlucaniBroj(staraVrijednost, max);
                            if (vrijednost2 == max) {
                                a.setSmjer(false);
                            }
                        } else {
                            vrijednost2 = generatorBrojeva.dajSlucaniBroj(min, staraVrijednost);
                            if (vrijednost2 == min) {
                                a.setSmjer(true);
                            }
                        }
                        a.setVrijednostAktuatora(Float.parseFloat(String.format("%.1f", vrijednost2).replace(",", ".")));

                    } else if (a.getVrstaAktuatora() == 2) {
                        float min = a.getMinVrijednostAktuatora();
                        float max = a.getMaxVrijednostAktuatora();
                        if (smjer) {
                            vrijednost2 = generatorBrojeva.dajSlucaniBroj(staraVrijednost, max);
                            if (vrijednost2 == max) {
                                a.setSmjer(false);
                            }
                        } else {
                            vrijednost2 = generatorBrojeva.dajSlucaniBroj(min, staraVrijednost);
                            if (vrijednost2 == min) {
                                a.setSmjer(true);
                            }
                        }
                        a.setVrijednostAktuatora(Float.parseFloat(String.format("%.5f", vrijednost2).replace(",", ".")));

                    } else if (a.getVrstaAktuatora() == 3) {
                        if (staraVrijednost.intValue() == 0) {
                            vrijednost = 1;
                        } else {
                            vrijednost = 0;
                        }
                        float pom = (float) vrijednost;
                        a.setVrijednostAktuatora(pom);
                    }
                } else {
                    if (a.getPopisSenzora().size() == 0) {
                        if (a.getVrstaAktuatora() == 0 || a.getVrstaAktuatora() == 3) {
                            pp.prikazi(String.format("|%-40s|%15s|%18s|%9s|%9s|%13s|%30s|", a.getNazivAktuatora(), a.getIdAktuatora(), a.getStatusAktuatora(), a.getMinVrijednostAktuatora(), a.getMaxVrijednostAktuatora(), a.getVrijednostAktuatora().intValue(), ""));
                        } else {
                            pp.prikazi(String.format("|%-40s|%15s|%18s|%9s|%9s|%13s|%30s|", a.getNazivAktuatora(), a.getIdAktuatora(), a.getStatusAktuatora(), a.getMinVrijednostAktuatora(), a.getMaxVrijednostAktuatora(), a.getVrijednostAktuatora(), ""));
                        }
                    } else {
                        pp.prikazi(String.format("|%-140s|", "AKTUATOR : " + a.getNazivAktuatora() + " S ID: " + a.getIdAktuatora() + " NIJE PRIKAZAN JER SE NITI JEDAN SENZOR NIJE PROMIJENIO (" + listaDodjeljenihSenzora.toString() + ")"));
                    }
                }
            }
        }
        statistika.setVrijednostiAktuatora(statistika.getVrijednostiAktuatora()+1);
        for (Senzor s : mjesto.getSenzori()) {
            s.setState(false);
        }
        pp.prikazi(String.format("|%-109s|", "============================================================================================================================================"));

    }

    @Override
    public synchronized void start() {
        super.start(); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Senzor> getListaSenzora() {
        return listaSenzora;
    }

    public void setListaSenzora(List<Senzor> listaSenzora) {
        this.listaSenzora = listaSenzora;
    }

    public List<Aktuator> getListaAktuatora() {
        return listaAktuatora;
    }

    public void setListaAktuatora(List<Aktuator> listaAktuatora) {
        this.listaAktuatora = listaAktuatora;
    }

    public int getTrajanjeCiklusa() {
        return trajanjeCiklusa;
    }

    public void setTrajanjeCiklusa(int trajanjeCiklusa) {
        this.trajanjeCiklusa = trajanjeCiklusa;
    }

    public int getBrojCiklusa() {
        return brojCiklusa;
    }

    public void setBrojCiklusa(int brojCiklusa) {
        this.brojCiklusa = brojCiklusa;
    }

    public List<Mjesto> getListaMjesta() {
        return listaMjesta;
    }

    public void setListaMjesta(List<Mjesto> listaMjesta) {
        this.listaMjesta = listaMjesta;
    }

    public List<Integer> getListaIdUredaja() {
        return listaIdUredaja;
    }

    public void setListaIdUredaja(List<Integer> listaIdUredaja) {
        this.listaIdUredaja = listaIdUredaja;
    }

    @Override
    public Iterator getIterator() {
        return new iteratorMjesta();
    }

    private class iteratorMjesta implements Iterator {

        int index;

        @Override
        public boolean hasNext() {
            if (index < listaMjesta.size()) {
                return true;
            }
            return false;
        }

        @Override
        public Object next() {
            if (this.hasNext()) {
                return listaMjesta.get(index++);
            }
            return null;
        }

    }

}
