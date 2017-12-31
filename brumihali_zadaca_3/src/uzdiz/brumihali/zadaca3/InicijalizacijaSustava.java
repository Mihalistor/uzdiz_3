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

    public void inicijalizirajSve(List<Mjesto> listaMjesta) {
        int statusSenzora = 0;
        int statusAktuatora = 0;
        System.out.println("");
        System.out.println(String.format("%45s","I N I C I J A L I Z A C I J A"));
        for (Mjesto mj : listaMjesta) {
            System.out.println(String.format("|%-61s|", "============================================================="));
            System.out.println(String.format("|%-61s|", "MJESTO: " + mj.getNazivMjesta()));
            System.out.println(String.format("|%-61s|", "-------------------------------------------------------------"));
            System.out.println(String.format("|%-40s|%-20s|", "Naziv uredaja", "Status uredaja"));
            System.out.println(String.format("|%-61s|", "-------------------------------------------------------------"));
            for (int i = 0; i < mj.getSenzori().size(); i++) {
                Senzor senzor = mj.getSenzori().get(i);
                iniPoruka(senzor.getNazivSenzora());
                statusSenzora = odgovorPoruka();              
                if (statusSenzora == 0) {
                    senzor.setBrojGreski(3);
                }
                senzor.setStatusSenzora(statusSenzora);
                System.out.println(String.format("|%-40s|%20d|", "S: " + senzor.getNazivSenzora(), senzor.getStatusSenzora()));
            }
            for (int i = 0; i < mj.getAktuatori().size(); i++) {
                Aktuator aktuator = mj.getAktuatori().get(i);
                iniPoruka(aktuator.getNazivAktuatora());
                statusAktuatora = odgovorPoruka();
                if (statusAktuatora == 0) {
                    aktuator.setBrojGreski(3);
                }
                aktuator.setStatusAktuatora(statusAktuatora);
                System.out.println(String.format("|%-40s|%20d|", "A: " + aktuator.getNazivAktuatora(), aktuator.getStatusAktuatora()));
            }
        }
        System.out.println(String.format("|%-61s|", "============================================================="));
        System.out.println("");
    }

    public void inicijalizirajSenzor(Senzor senzor) {
        iniPoruka(senzor.getNazivSenzora());
        int statusSenzora = odgovorPoruka();
        senzor.setStatusSenzora(statusSenzora);
        System.out.println(String.format("|%-61s|", "-------------------------------------------------------------"));
        System.out.println(String.format("|%-40s|%-20s|", "Naziv uredaja", "Status uredaja"));
        System.out.println(String.format("|%-61s|", "-------------------------------------------------------------"));
        System.out.println(String.format("|%-40s|%20d|", "S: " + senzor.getNazivSenzora(), senzor.getStatusSenzora()));
    }

    public void inicijalizirajAktuator(Aktuator aktuator) {
        iniPoruka(aktuator.getNazivAktuatora());
        int statusAktuatora = odgovorPoruka();
        aktuator.setStatusAktuatora(statusAktuatora);
        System.out.println(String.format("|%-61s|", "-------------------------------------------------------------"));
        System.out.println(String.format("|%-40s|%-20s|", "Naziv uredaja", "Status uredaja"));
        System.out.println(String.format("|%-61s|", "-------------------------------------------------------------"));
        System.out.println(String.format("|%-40s|%20d|", "A: " + aktuator.getNazivAktuatora(), aktuator.getStatusAktuatora()));
    }

    public void iniPoruka(String uredaj) {
        System.out.println(String.format("|%-61s|", "Ini poruka za '" + uredaj + "'"));
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
