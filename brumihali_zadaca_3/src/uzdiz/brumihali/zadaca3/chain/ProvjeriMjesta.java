/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uzdiz.brumihali.zadaca3.chain;

import uzdiz.brumihali.zadaca3.Komande;
import uzdiz.brumihali.zadaca3.PrikazPrograma;
import uzdiz.brumihali.zadaca3.podaci.Mjesto;

/**
 *
 * @author bruno
 */
public class ProvjeriMjesta extends IdHandler{

    PrikazPrograma pp = PrikazPrograma.getInstance();
    
    @Override
    public void handleRequest(int id, String naziv) {
        Boolean postoji = false;
        for(Mjesto m : Komande.listaMjesta){
            if (m.getIdMjesta().equals(id)){
                pp.prikazi("TO JE MJESTO: '" + m.getNazivMjesta() + "'");
                m.setNazivMjesta(naziv);
                pp.prikazi("Novi naziv je: '" + m.getNazivMjesta() + "'");
                postoji = true;
                break;
            }
        }
        if(!postoji){
            if(successor != null){
                successor.handleRequest(id, naziv);
            }
        }
    }
    
}
