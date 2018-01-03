/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uzdiz.brumihali.zadaca3.chain;

import java.util.ArrayList;
import uzdiz.brumihali.zadaca3.Komande;
import uzdiz.brumihali.zadaca3.PrikazPrograma;

/**
 *
 * @author bruno
 */
public class VracanjePodataka extends CommandHandler {

    PrikazPrograma pp = PrikazPrograma.getInstance();

    @Override
    public void handleRequest(String komanda) {
        if (komanda.equals("VP")) {
            pp.prikazi("KOMANDA VRACANJE PODATAKA");
            Komande.listaMjesta.clear();
            Komande.listaMjesta = new ArrayList<>(sp.restoreFromMemento(caretaker.getMemento()));
            pp.prikazi("Podaci o mjestima i njihovim uredajima su vraceni");
        } else {
            if (successor != null) {
                successor.setCaretaker(caretaker);
                successor.setSp(sp);
                successor.handleRequest(komanda);
            }
        }
    }
}
