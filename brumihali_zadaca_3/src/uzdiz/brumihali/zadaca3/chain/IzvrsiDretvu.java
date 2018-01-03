/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uzdiz.brumihali.zadaca3.chain;

import java.util.logging.Level;
import java.util.logging.Logger;
import uzdiz.brumihali.zadaca3.Komande;
import uzdiz.brumihali.zadaca3.PrikazPrograma;
import uzdiz.brumihali.zadaca3.ProvjeraMjesta;

/**
 *
 * @author bruno
 */
public class IzvrsiDretvu extends CommandHandler {

    PrikazPrograma pp = PrikazPrograma.getInstance();

    @Override
    public void handleRequest(String komanda) {
        if (komanda.contains("C")) {
            int brojCiklusa = Integer.parseInt(komanda.substring(komanda.indexOf(" ") + 1, komanda.length()));
            pp.prikazi("KOMANDA IZVRSAVANJE DRETVE");
            if (brojCiklusa < 1 || brojCiklusa > 100) {
                pp.prikazi("Broj ciklusa dretve mora biti u intervalu od 1 do 100. Ponovite komandu");
            } else {
                ProvjeraMjesta provjeraMjesta = new ProvjeraMjesta();
                provjeraMjesta.setBrojCiklusa(brojCiklusa);
                provjeraMjesta.start();
                try {
                    provjeraMjesta.join();

                } catch (InterruptedException ex) {
                    Logger.getLogger(Komande.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            if(successor != null){
                successor.setCaretaker(caretaker);
                successor.setSp(sp);
                successor.handleRequest(komanda);
            }
        }
    }

}
