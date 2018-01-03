/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uzdiz.brumihali.zadaca3.chain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import uzdiz.brumihali.zadaca3.PrikazPrograma;

/**
 *
 * @author bruno
 */
public class VlastitaFunkcionalnost extends CommandHandler {

    PrikazPrograma pp = PrikazPrograma.getInstance();
    
    @Override
    public void handleRequest(String komanda) {
        Pattern p = Pattern.compile("^VF [0-9]{1,4} [a-zA-Z1-9]{1,}$");
        Matcher m = p.matcher(komanda);
        if (m.matches()) {
            pp.prikazi("KOMANDA VLASTITA FUNKCIONALNOST");
            String[] zapis = komanda.split(" ");
            int id  = Integer.parseInt(zapis[1]);
            String naziv = zapis[2];
            IdHandler ih = postaviChain();
            ih.handleRequest(id,naziv);

        } else {
            if (successor != null) {
                successor.setCaretaker(caretaker);
                successor.setSp(sp);
                successor.handleRequest(komanda);
            }
        }
    }
    
    public static IdHandler postaviChain(){
        IdHandler provjeriMjesta = new ProvjeriMjesta();
        IdHandler provjeriSenzore = new ProvjeriSenzore();
        IdHandler provjeriAktuatore = new ProvjeriAktuatore();

        provjeriMjesta.setSuccessor(provjeriSenzore);
        provjeriSenzore.setSuccessor(provjeriAktuatore);
        
        return provjeriMjesta;
    }
}
