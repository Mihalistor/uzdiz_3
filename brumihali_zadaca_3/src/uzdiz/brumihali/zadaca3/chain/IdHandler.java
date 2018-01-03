/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uzdiz.brumihali.zadaca3.chain;

/**
 *
 * @author bruno
 */
public abstract class IdHandler {
    
    IdHandler successor;

    public void setSuccessor(IdHandler successor) {
        this.successor = successor;
    }
    
    public abstract void handleRequest(int id, String naziv);
    
}
