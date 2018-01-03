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

/**
 *
 * @author bruno
 */
public class ProvjeriAktuatore extends IdHandler{

    PrikazPrograma pp = PrikazPrograma.getInstance();
    
    @Override
    public void handleRequest(int id, String naziv) {
        Boolean postoji = false;
        for (Mjesto m : Komande.listaMjesta) {
            for (Aktuator a : m.getAktuatori()) {
                if (a.getIdAktuatora().equals(id)) {
                    pp.prikazi("TO JE AKTUATOR: '" + a.getNazivAktuatora() + "'");
                    a.setNazivAktuatora(naziv);
                    a.setVrijednostAktuatora(a.getMinVrijednostAktuatora());
                    pp.prikazi("Novi naziv je: '" + a.getNazivAktuatora() + "' te je njegova nova vrijednost: " + a.getVrijednostAktuatora());
                    postoji = true;
                }
            }
        }
        if(!postoji){
            if(successor != null){
                successor.handleRequest(id, naziv);
            } else {
                pp.prikazi("Ne postoji mjesto ili uredaj s tim ID-em");
            }
        }
    }
}
