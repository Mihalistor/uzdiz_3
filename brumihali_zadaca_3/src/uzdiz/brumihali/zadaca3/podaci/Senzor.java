/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uzdiz.brumihali.zadaca3.podaci;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import uzdiz.brumihali.zadaca3.observer.Observer;
import uzdiz.brumihali.zadaca3.observer.Subject;

/**
 *
 * @author bruno
 */
public class Senzor implements Cloneable, Subject {

    private Integer IdSenzora;
    private Integer IdModelaSenzora;
    private String nazivSenzora;
    private Integer tipSenzora;
    private Integer vrstaSenzora;
    private Float minVrijednostSenzora;
    private Float maxVrijednostSenzora;
    private String komentarSenzora;
    private Float vrijednostSenzora;
    private Integer statusSenzora;
    private Integer brojGreski = 0;
    private List observers = new ArrayList<>();
    private Boolean state = false;

    public Senzor(String nazivSenzora, Integer tipSenzora, Integer vrstaSenzora, Float minVrijednostSenzora, Float maxVrijednostSenzora, String komentarSenzora) {
        this.nazivSenzora = nazivSenzora;
        this.tipSenzora = tipSenzora;
        this.vrstaSenzora = vrstaSenzora;
        this.minVrijednostSenzora = minVrijednostSenzora;
        this.maxVrijednostSenzora = maxVrijednostSenzora;
        this.komentarSenzora = komentarSenzora;
    }

    public Senzor() {
    }

    public Senzor(Senzor senzor) {
        this.IdSenzora = senzor.getIdSenzora();
        this.IdModelaSenzora = senzor.getIdModelaSenzora();
        this.nazivSenzora = senzor.getNazivSenzora();
        this.tipSenzora = senzor.getTipSenzora();
        this.vrstaSenzora = senzor.getVrstaSenzora();
        this.minVrijednostSenzora = senzor.getMinVrijednostSenzora();
        this.maxVrijednostSenzora = senzor.getMaxVrijednostSenzora();
        this.komentarSenzora = senzor.getKomentarSenzora();
        this.vrijednostSenzora = senzor.getVrijednostSenzora();
        this.statusSenzora = senzor.getStatusSenzora();
        this.observers = new ArrayList<>(senzor.getObservers());
        this.state = senzor.getState();
    }

    public String getNazivSenzora() {
        return nazivSenzora;
    }

    public void setNazivSenzora(String nazivSenzora) {
        this.nazivSenzora = nazivSenzora;
    }

    public Integer getTipSenzora() {
        return tipSenzora;
    }

    public void setTipSenzora(Integer tipSenzora) {
        this.tipSenzora = tipSenzora;
    }

    public Integer getVrstaSenzora() {
        return vrstaSenzora;
    }

    public void setVrstaSenzora(Integer vrstaSenzora) {
        this.vrstaSenzora = vrstaSenzora;
    }

    public Float getMinVrijednostSenzora() {
        return minVrijednostSenzora;
    }

    public void setMinVrijednostSenzora(Float minVrijednostSenzora) {
        this.minVrijednostSenzora = minVrijednostSenzora;
    }

    public Float getMaxVrijednostSenzora() {
        return maxVrijednostSenzora;
    }

    public void setMaxVrijednostSenzora(Float maxVrijednostSenzora) {
        this.maxVrijednostSenzora = maxVrijednostSenzora;
    }

    public String getKomentarSenzora() {
        return komentarSenzora;
    }

    public void setKomentarSenzora(String komentarSenzora) {
        this.komentarSenzora = komentarSenzora;
    }

    public Float getVrijednostSenzora() {
        return vrijednostSenzora;
    }

    public void setVrijednostSenzora(Float vrijednostSenzora) {
        this.vrijednostSenzora = vrijednostSenzora;
    }

    public Integer getIdSenzora() {
        return IdSenzora;
    }

    public void setIdSenzora(Integer IdSenzora) {
        this.IdSenzora = IdSenzora;
    }

    public Integer getStatusSenzora() {
        return statusSenzora;
    }

    public void setStatusSenzora(Integer statusSenzora) {
        this.statusSenzora = statusSenzora;
    }

    public Integer getBrojGreski() {
        return brojGreski;
    }

    public void setBrojGreski(Integer brojGreski) {
        this.brojGreski = brojGreski;
    }

    public Object clone() {
        Object clone = null;
        try {
            clone = super.clone();

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return clone;
    }

    public Integer getIdModelaSenzora() {
        return IdModelaSenzora;
    }

    public void setIdModelaSenzora(Integer IdModelaSenzora) {
        this.IdModelaSenzora = IdModelaSenzora;
    }
    
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

    public List getObservers() {
        return observers;
    }

}
