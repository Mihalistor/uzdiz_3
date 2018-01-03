/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uzdiz.brumihali.zadaca3.chain;

import uzdiz.brumihali.zadaca3.InicijalizacijaSustava;
import uzdiz.brumihali.zadaca3.PrikazPrograma;

/**
 *
 * @author bruno
 */
public class PostavljanjeIspravnosti extends CommandHandler {

    PrikazPrograma pp = PrikazPrograma.getInstance();

    @Override
    public void handleRequest(String komanda) {
        if (komanda.contains("PI ")) {
            int prosjecnaIspravnost = Integer.parseInt(komanda.substring(komanda.indexOf(" ") + 1, komanda.length()));
            pp.prikazi("KOMANDA POSTAVLJANJE ISPRAVNOSTI");
            if (prosjecnaIspravnost < 0 || prosjecnaIspravnost > 100) {
                pp.prikazi("Prosjecna ispravnost mora biti u intervalu od 0 do 100. Ponovite komandu");
            } else {
                InicijalizacijaSustava.prosjecnaIspravnost = prosjecnaIspravnost;
                pp.prikazi("Prosjecna ispravnst je: " + InicijalizacijaSustava.prosjecnaIspravnost);
            }
        } else {
            if (successor != null) {
                successor.setCaretaker(caretaker);
                successor.setSp(sp);
                successor.handleRequest(komanda);
            }
        }
    }
}
