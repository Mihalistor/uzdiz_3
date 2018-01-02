/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uzdiz.brumihali.zadaca3.podaci;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author bruno
 */
public class Mjesto implements Comparable<Mjesto> {

    private Integer idMjesta;
    private String nazivMjesta;
    private Integer tipMjesta;
    private Integer brojSenzora;
    private Integer brojAktuatora;
    private List<Senzor> senzori = new ArrayList<>();
    private List<Aktuator> aktuatori = new ArrayList<>();
    
    public Mjesto(){
    }
    
    public Mjesto(Mjesto mjesto){
        this.idMjesta = mjesto.getIdMjesta();
        this.nazivMjesta = mjesto.getNazivMjesta();
        this.brojSenzora = mjesto.getBrojSenzora();
        this.brojAktuatora = mjesto.getBrojAktuatora();
        this.senzori = new ArrayList<>(mjesto.getSenzori());
        this.aktuatori = new ArrayList<>(mjesto.getAktuatori());
    }
    public String getNazivMjesta() {
        return nazivMjesta;
    }

    public void setNazivMjesta(String nazivMjesta) {
        this.nazivMjesta = nazivMjesta;
    }

    public Integer getTipMjesta() {
        return tipMjesta;
    }

    public void setTipMjesta(Integer tipMjesta) {
        this.tipMjesta = tipMjesta;
    }

    public Integer getBrojSenzora() {
        return brojSenzora;
    }

    public void setBrojSenzora(Integer brojSenzora) {
        this.brojSenzora = brojSenzora;
    }

    public Integer getBrojAktuatora() {
        return brojAktuatora;
    }

    public void setBrojAktuatora(Integer brojAktuatora) {
        this.brojAktuatora = brojAktuatora;
    }

    public List<Senzor> getSenzori() {
        return senzori;
    }

    public void setSenzori(List<Senzor> senzori) {
        this.senzori = senzori;
    }

    public List<Aktuator> getAktuatori() {
        return aktuatori;
    }

    public void setAktuatori(List<Aktuator> aktuatori) {
        this.aktuatori = aktuatori;
    }

    public Integer getIdMjesta() {
        return idMjesta;
    }

    public void setIdMjesta(Integer idMjesta) {
        this.idMjesta = idMjesta;
    }

    @Override
    public int compareTo(Mjesto o) {
        return Integer.valueOf(idMjesta).compareTo(o.idMjesta);
    }
}
