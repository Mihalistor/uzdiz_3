/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uzdiz.brumihali.zadaca3.observer;

/**
 *
 * @author bruno
 */
public interface Subject {
    public void addObserver(Observer o);
    public Boolean getState();
    public void setState(Boolean state);
}
