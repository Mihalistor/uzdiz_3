/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uzdiz.brumihali.zadaca3.chain;

import uzdiz.brumihali.zadaca3.Komande;
import uzdiz.brumihali.zadaca3.PrikazPrograma;
import uzdiz.brumihali.zadaca3.podaci.Aktuator;
import uzdiz.brumihali.zadaca3.podaci.Mjesto;
import uzdiz.brumihali.zadaca3.podaci.Senzor;

/**
 *
 * @author bruno
 */
public class IspisMjesta extends CommandHandler {

    PrikazPrograma pp = PrikazPrograma.getInstance();

    @Override
    public void handleRequest(String komanda) {
        if (komanda.contains("M ")) {
            int id  = Integer.parseInt(komanda.substring(komanda.indexOf(" ") + 1, komanda.length()));
            pp.prikazi("KOMANDA ISPIS MJESTA");
            Boolean postoji = false;
            for (Mjesto mjesto : Komande.listaMjesta) {
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
                    pp.prikazi(String.format("|%1s|", "================================================================= ISPIS AKTUATORA ======================================================================"));
                    pp.prikazi(String.format("|%-40s|%-10s|%-10s|%-9s|%-9s|%-13s|%-30s|%12s|%-11s|", "Naziv aktuatora", "ID", "Status", "MIN", "MAX", "Vrijednost", "ID dodjeljenih senzora", "Broj greski", "Napomena"));
                    pp.prikazi(String.format("|%-150s|", "--------------------------------------------------------------------------------------------------------------------------------------------------------"));
                    for (Aktuator a : mjesto.getAktuatori()) {
                        String popisSenzora = "";
                        for (Senzor s : a.getPopisSenzora()) {
                            popisSenzora += (s.getIdSenzora() + ", ");
                        }
                        popisSenzora = popisSenzora.substring(0, popisSenzora.lastIndexOf(","));
                        if (a.getBrojGreski() == 3) {
                            pp.prikazi(String.format("|%-40s|%10s|%10s|%9s|%9s|%13s|%30s|%12s|%-11s|", a.getNazivAktuatora(), a.getIdAktuatora(), a.getStatusAktuatora(), a.getMinVrijednostAktuatora(), a.getMaxVrijednostAktuatora(), a.getVrijednostAktuatora().intValue(), popisSenzora, a.getBrojGreski(), "NEAKTIVAN"));
                        } else {
                            pp.prikazi(String.format("|%-40s|%10s|%10s|%9s|%9s|%13s|%30s|%12s|%-11s|", a.getNazivAktuatora(), a.getIdAktuatora(), a.getStatusAktuatora(), a.getMinVrijednostAktuatora(), a.getMaxVrijednostAktuatora(), a.getVrijednostAktuatora().intValue(), popisSenzora, a.getBrojGreski(), "AKTIVAN"));
                        }
                        pp.prikazi(String.format("|%-150s|", "--------------------------------------------------------------------------------------------------------------------------------------------------------"));
                    }
                }
            }
            if (!postoji) {
                pp.prikazi("Ne postoji trazeno mjesto s tim ID-em");
            }
            pp.prikazi("");
        } else {
            if(successor != null){
                successor.setCaretaker(caretaker);
                successor.setSp(sp);
                successor.handleRequest(komanda);
            }
        }
    }

}
