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
public class IspisAktuatora extends CommandHandler {

    PrikazPrograma pp = PrikazPrograma.getInstance();

    @Override
    public void handleRequest(String komanda) {
        if (komanda.contains("A ")) {
            int id = Integer.parseInt(komanda.substring(komanda.indexOf(" ") + 1, komanda.length()));
            pp.prikazi("KOMANDA ISPIS AKTUATORA");
            Boolean postoji = false;
            for (Mjesto mjesto : Komande.listaMjesta) {
                for (Aktuator a : mjesto.getAktuatori()) {
                    if (a.getIdAktuatora().equals(id)) {
                        postoji = true;
                        String popisSenzora = "";
                        for (Senzor s : a.getPopisSenzora()) {
                            popisSenzora += (s.getIdSenzora() + ", ");
                        }
                        popisSenzora = popisSenzora.substring(0, popisSenzora.lastIndexOf(","));
                        pp.prikazi("");
                        pp.prikazi(String.format("|%1s|", "--------------------------------------------------------------------------------------------------------------------------------------------------------"));
                        pp.prikazi(String.format("|%-152s|", "MJESTO: " + mjesto.getNazivMjesta()));
                        pp.prikazi(String.format("|%1s|", "================================================================= ISPIS AKTUATORA ======================================================================"));
                        pp.prikazi(String.format("|%-40s|%-10s|%-10s|%-9s|%-9s|%-13s|%-30s|%12s|%-11s|", "Naziv aktuatora", "ID", "Status", "MIN", "MAX", "Vrijednost", "ID dodjeljenih senzora", "Broj greski", "Napomena"));
                        pp.prikazi(String.format("|%-150s|", "--------------------------------------------------------------------------------------------------------------------------------------------------------"));
                        if (a.getBrojGreski() == 3) {
                            pp.prikazi(String.format("|%-40s|%10s|%10s|%9s|%9s|%13s|%30s|%12s|%-11s|", a.getNazivAktuatora(), a.getIdAktuatora(), a.getStatusAktuatora(), a.getMinVrijednostAktuatora(), a.getMaxVrijednostAktuatora(), a.getVrijednostAktuatora().intValue(), popisSenzora, a.getBrojGreski(), "NEAKTIVAN"));
                        } else {
                            pp.prikazi(String.format("|%-40s|%10s|%10s|%9s|%9s|%13s|%30s|%12s|%-11s|", a.getNazivAktuatora(), a.getIdAktuatora(), a.getStatusAktuatora(), a.getMinVrijednostAktuatora(), a.getMaxVrijednostAktuatora(), a.getVrijednostAktuatora().intValue(), popisSenzora, a.getBrojGreski(), "AKTIVAN"));
                        }
                        pp.prikazi(String.format("|%-150s|", "--------------------------------------------------------------------------------------------------------------------------------------------------------"));
                        break;
                    }
                }
            }
            if (!postoji) {
                pp.prikazi("Ne postoji trazeni aktuator s tim ID-em");
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
