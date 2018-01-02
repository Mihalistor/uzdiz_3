/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uzdiz.brumihali.zadaca3;

import java.util.List;
import uzdiz.brumihali.zadaca3.podaci.Aktuator;
import uzdiz.brumihali.zadaca3.podaci.Mjesto;
import uzdiz.brumihali.zadaca3.podaci.Senzor;

/**
 *
 * @author bruno
 */
public class InicijalizacijaSustava {

    GeneratorBrojeva generatorBrojeva = GeneratorBrojeva.getInstance();
    public static int prosjecnaIspravnost;
    Statistika statistika = Statistika.getInstance();
    PrikazPrograma pp = PrikazPrograma.getInstance();
    
    public void inicijalizirajSve(List<Mjesto> listaMjesta) {
        int statusSenzora = 0;
        int statusAktuatora = 0;
        pp.prikazi("");
        pp.prikazi(String.format("%45s","I N I C I J A L I Z A C I J A"));
        for (Mjesto mj : listaMjesta) {
            pp.prikazi(String.format("|%-61s|", "============================================================="));
            pp.prikazi(String.format("|%-61s|", "MJESTO: " + mj.getNazivMjesta()));
            pp.prikazi(String.format("|%-61s|", "-------------------------------------------------------------"));
            pp.prikazi(String.format("|%-40s|%-20s|", "Naziv uredaja", "Status uredaja"));
            pp.prikazi(String.format("|%-61s|", "-------------------------------------------------------------"));
            for (int i = 0; i < mj.getSenzori().size(); i++) {
                Senzor senzor = mj.getSenzori().get(i);
                iniPoruka(senzor.getNazivSenzora());
                statusSenzora = odgovorPoruka();              
                if (statusSenzora == 0) {
                    senzor.setBrojGreski(3);
                }
                senzor.setStatusSenzora(statusSenzora);
                pp.prikazi(String.format("|%-40s|%20d|", "S: " + senzor.getNazivSenzora(), senzor.getStatusSenzora()));
            }
            for (int i = 0; i < mj.getAktuatori().size(); i++) {
                Aktuator aktuator = mj.getAktuatori().get(i);
                iniPoruka(aktuator.getNazivAktuatora());
                statusAktuatora = odgovorPoruka();
                if (statusAktuatora == 0) {
                    aktuator.setBrojGreski(3);
                }
                aktuator.setStatusAktuatora(statusAktuatora);
                pp.prikazi(String.format("|%-40s|%20d|", "A: " + aktuator.getNazivAktuatora(), aktuator.getStatusAktuatora()));
            }
        }
        pp.prikazi(String.format("|%-61s|", "============================================================="));
        pp.prikazi("");
    }

    public void inicijalizirajSenzor(Senzor senzor) {
        iniPoruka(senzor.getNazivSenzora());
        int statusSenzora = odgovorPoruka();
        senzor.setStatusSenzora(statusSenzora);
        pp.prikazi(String.format("|%-61s|", "-------------------------------------------------------------"));
        pp.prikazi(String.format("|%-40s|%-20s|", "Naziv uredaja", "Status uredaja"));
        pp.prikazi(String.format("|%-61s|", "-------------------------------------------------------------"));
        pp.prikazi(String.format("|%-40s|%20d|", "S: " + senzor.getNazivSenzora(), senzor.getStatusSenzora()));
    }

    public void inicijalizirajAktuator(Aktuator aktuator) {
        iniPoruka(aktuator.getNazivAktuatora());
        int statusAktuatora = odgovorPoruka();
        aktuator.setStatusAktuatora(statusAktuatora);
        pp.prikazi(String.format("|%-61s|", "-------------------------------------------------------------"));
        pp.prikazi(String.format("|%-40s|%-20s|", "Naziv uredaja", "Status uredaja"));
        pp.prikazi(String.format("|%-61s|", "-------------------------------------------------------------"));
        pp.prikazi(String.format("|%-40s|%20d|", "A: " + aktuator.getNazivAktuatora(), aktuator.getStatusAktuatora()));
    }

    public void iniPoruka(String uredaj) {
        pp.prikazi(String.format("|%-61s|", "Ini poruka za '" + uredaj + "'"));
        statistika.setBrojIniPoruka(statistika.getBrojIniPoruka()+1);
    }

    public int odgovorPoruka() {
        int slucajniBroj = generatorBrojeva.dajSlucaniBroj(1, 100);
        if (slucajniBroj <= prosjecnaIspravnost) {
            return 1;
        } else {
            return 0;
        }
    }
}
