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
public class ProvjeriSenzore extends IdHandler {

    PrikazPrograma pp = PrikazPrograma.getInstance();

    @Override
    public void handleRequest(int id, String naziv) {
        Boolean postoji = false;
        for (Mjesto m : Komande.listaMjesta) {
            for (Senzor s : m.getSenzori()) {
                if (s.getIdSenzora().equals(id)) {
                    pp.prikazi("TO JE SENZOR: '" + s.getNazivSenzora() + "'");
                    s.setNazivSenzora(naziv);
                    s.setVrijednostSenzora(s.getMaxVrijednostSenzora());
                    pp.prikazi("Novi naziv je: '" + s.getNazivSenzora() + "' te je njegova nova vrijednost: " + s.getVrijednostSenzora());
                    postoji = true;
                }
            }
        }
        if(!postoji){
            if(successor != null){
                successor.handleRequest(id, naziv);
            }
        }
    }

}
