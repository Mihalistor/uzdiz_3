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
public class IspisHelp extends CommandHandler {

    PrikazPrograma pp = PrikazPrograma.getInstance();

    @Override
    public void handleRequest(String komanda) {
        if (komanda.equals("H")) {
            pp.prikazi("POMOC KOD UPISA KOMANDI");
            pp.prikazi("M x - ispis podataka mjesta x");
            pp.prikazi("S x - ispis podataka senzora x");
            pp.prikazi("A x - ispis podataka aktuatora x");
            pp.prikazi("S - ispis statistike");
            pp.prikazi("SP - spremi podatke (mjesta, uredaja)");
            pp.prikazi("VP - vrati spremljene podatke (mjesta, uredaja)");
            pp.prikazi("C n - izvrsavanje n ciklusa dretve (1-100)");
            pp.prikazi("VF x - izvrsavanje vlastite funkcionalnosti, x predstavlja ID");
            pp.prikazi("PI n - prosjecni % ispravnosti uredaja (0-100)");
            pp.prikazi("H - pomoc, ispis dopustenih komandi i njihov opis");
            pp.prikazi("I - izlaz.");
        } else {
            if(successor != null){
                successor.setCaretaker(caretaker);
                successor.setSp(sp);
                successor.handleRequest(komanda);
            }
        }
    }

}
