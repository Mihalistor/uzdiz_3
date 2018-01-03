/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uzdiz.brumihali.zadaca3.chain;

import uzdiz.brumihali.zadaca3.Komande;
import uzdiz.brumihali.zadaca3.PrikazPrograma;
import uzdiz.brumihali.zadaca3.podaci.Mjesto;
import uzdiz.brumihali.zadaca3.podaci.Senzor;

/**
 *
 * @author bruno
 */
public class IspisSenzora extends CommandHandler {

    PrikazPrograma pp = PrikazPrograma.getInstance();

    @Override
    public void handleRequest(String komanda) {
        if (komanda.contains("S ")) {
            int id = Integer.parseInt(komanda.substring(komanda.indexOf(" ") + 1, komanda.length()));
            pp.prikazi("KOMANDA ISPIS SENZORA");
            Boolean postoji = false;
            for (Mjesto mjesto : Komande.listaMjesta) {
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
        } else {
            if (successor != null) {
                successor.setCaretaker(caretaker);
                successor.setSp(sp);
                successor.handleRequest(komanda);
            }
        }
    }

}
