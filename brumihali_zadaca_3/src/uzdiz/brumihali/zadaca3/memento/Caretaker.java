/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uzdiz.brumihali.zadaca3.memento;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bruno
 */
public class Caretaker {

    private List<Object> savedLists = new ArrayList<Object>();

    public void addMemento(Object m) {
        savedLists.add(m);
    }

    public Object getMemento() {
        return savedLists.get(savedLists.size()-1);
    }
}
