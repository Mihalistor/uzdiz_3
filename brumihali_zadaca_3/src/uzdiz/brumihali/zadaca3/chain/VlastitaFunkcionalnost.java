/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uzdiz.brumihali.zadaca3.chain;

import uzdiz.brumihali.zadaca3.PrikazPrograma;

/**
 *
 * @author bruno
 */
public class VlastitaFunkcionalnost extends CommandHandler {

    PrikazPrograma pp = PrikazPrograma.getInstance();
    
    @Override
    public void handleRequest(String komanda) {
        if (komanda.contains("VF")) {
            pp.prikazi("KOMANDA VLASTITA FUNKCIONALNOST");

        } else {
            if (successor != null) {
                successor.setCaretaker(caretaker);
                successor.setSp(sp);
                successor.handleRequest(komanda);
            }
        }
    }
}
