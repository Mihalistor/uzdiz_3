/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uzdiz.brumihali.zadaca3.observer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author bruno
 */
public class SubjectImpl implements Subject{

    private List observers = new ArrayList<>();
    private Boolean state = false ;
    
    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public Boolean getState() {
       return state;
    }

    @Override
    public void setState(Boolean state) {
        this.state = state;
        notifyObservers();
    }
    
    public void notifyObservers(){
        Iterator i = observers.iterator();
        while(i.hasNext()){
            Observer o = (Observer) i.next();
            o.update(this);
        }
    }

}
