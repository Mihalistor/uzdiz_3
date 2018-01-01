/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uzdiz.brumihali.zadaca3.memento;

import java.util.ArrayList;
import java.util.List;
import uzdiz.brumihali.zadaca3.podaci.Mjesto;

/**
 *
 * @author bruno
 */
public class SpremistePodataka {

    private List<Mjesto> listaMjesta = new ArrayList<>();

    public void setLista(List<Mjesto> lista) {
        System.out.println("Postavljam listu");
        this.listaMjesta = lista;
    }

    public Object saveToMemento() {
        System.out.println("spremam u listu");
        return new Memento(listaMjesta);
    }

    public void restoreFromMemento(Object m) {
        if (m instanceof Memento) {
            Memento memento = (Memento) m;
            listaMjesta = memento.getSavedList();
            System.out.println("Lista dohvacena iz spremnika");
        }
    }

    private static class Memento {

        private List<Mjesto> listaMjesta = new ArrayList<>();

        public Memento(List<Mjesto> listToSave) {
            listaMjesta = listToSave;
        }

        public List<Mjesto> getSavedList() {
            return listaMjesta;
        }
    }

}
