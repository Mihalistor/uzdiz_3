/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uzdiz.brumihali.zadaca3.chain;

import uzdiz.brumihali.zadaca3.memento.Caretaker;
import uzdiz.brumihali.zadaca3.memento.SpremistePodataka;

/**
 *
 * @author bruno
 */
public abstract class CommandHandler {
   
    CommandHandler successor;
    protected Caretaker caretaker;
    protected SpremistePodataka sp;

    public void setSuccessor(CommandHandler successor) {
        this.successor = successor;
    }
    
    public abstract void handleRequest(String komanda);

    public Caretaker getCaretaker() {
        return caretaker;
    }

    public void setCaretaker(Caretaker caretaker) {
        this.caretaker = caretaker;
    }

    public SpremistePodataka getSp() {
        return sp;
    }

    public void setSp(SpremistePodataka sp) {
        this.sp = sp;
    }
}
