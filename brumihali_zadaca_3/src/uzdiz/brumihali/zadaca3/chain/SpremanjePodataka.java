/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uzdiz.brumihali.zadaca3.chain;

import java.util.ArrayList;
import java.util.List;
import uzdiz.brumihali.zadaca3.Komande;
import uzdiz.brumihali.zadaca3.PrikazPrograma;
import uzdiz.brumihali.zadaca3.podaci.Aktuator;
import uzdiz.brumihali.zadaca3.podaci.Mjesto;
import uzdiz.brumihali.zadaca3.podaci.Senzor;

/**
 *
 * @author bruno
 */
public class SpremanjePodataka extends CommandHandler {

    PrikazPrograma pp = PrikazPrograma.getInstance();

    @Override
    public void handleRequest(String komanda) {
        if (komanda.equals("SP")) {
            pp.prikazi("KOMANDA SPREMANJE PODATAKA");
            List<Mjesto> l = new ArrayList<>();
            for (Mjesto mjesto : Komande.listaMjesta) {
                Mjesto m = new Mjesto(mjesto);
                m.getSenzori().clear();
                m.getAktuatori().clear();
                for (Senzor senzor : mjesto.getSenzori()) {
                    Senzor s = new Senzor(senzor);
                    s.getObservers().clear();
                    m.getSenzori().add(s);
                }
                for (Aktuator aktuator : mjesto.getAktuatori()) {
                    Aktuator a = new Aktuator(aktuator);
                    a.getPopisSenzora().clear();
                    m.getAktuatori().add(a);
                }

                for (Senzor senzor2 : m.getSenzori()) {
                    for (Senzor senzor3 : mjesto.getSenzori()) {
                        if (senzor3.getIdSenzora().equals(senzor2.getIdSenzora())) {
                            for (Object o : senzor3.getObservers()) {
                                Aktuator a = (Aktuator) o;
                                for (Aktuator ak : m.getAktuatori()) {
                                    if (a.getIdAktuatora().equals(ak.getIdAktuatora())) {
                                        senzor2.addObserver(ak);
                                        ak.getPopisSenzora().add(senzor2);
                                        break;
                                    }
                                }
                            }
                            break;
                        }
                    }
                }
                l.add(m);
            }
            sp.setLista(l);
            caretaker.addMemento(sp.saveToMemento());
            pp.prikazi("Podaci o mjestima i njihovim uredajima su spremljeni");
        } else {
            if (successor != null) {
                successor.setCaretaker(caretaker);
                successor.setSp(sp);
                successor.handleRequest(komanda);
            }
        }
    }
}
