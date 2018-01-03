/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uzdiz.brumihali.zadaca3.chain;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import uzdiz.brumihali.zadaca3.Komande;
import uzdiz.brumihali.zadaca3.PrikazPrograma;

/**
 *
 * @author bruno
 */
public class Izlaz extends CommandHandler {

    PrikazPrograma pp = PrikazPrograma.getInstance();

    @Override
    public void handleRequest(String komanda) {
        if (komanda.equals("I")) {
            pp.prikazi("KOMANDA IZLAZ IZ PROGRAMA");
            pp.prikazi("Kraj programa za 1 sekundu");
            try {
                sleep(1000);

            } catch (InterruptedException ex) {
                Logger.getLogger(Komande.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            System.exit(0);
        } else {
            if (successor != null) {
                successor.setCaretaker(caretaker);
                successor.setSp(sp);
                successor.handleRequest(komanda);
            }
        }
    }
}
