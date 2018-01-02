/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uzdiz.brumihali.zadaca3.podaci;

import java.util.ArrayList;
import java.util.List;
import uzdiz.brumihali.zadaca3.observer.Observer;
import uzdiz.brumihali.zadaca3.observer.Subject;

/**
 *
 * @author bruno
 */
public class Aktuator implements Cloneable, Observer{
    private Integer IdAktuatora;
    private Integer IdModelaAktuatora;
    private String nazivAktuatora;
    private Integer tipAktuatora;
    private Integer vrstaAktuatora;
    private Float minVrijednostAktuatora;
    private Float maxVrijednostAktuatora;
    private String komentarAktuatora;
    private Float vrijednostAktuatora = 0.0f;
    private Integer statusAktuatora;
    private List<Senzor> popisSenzora = new ArrayList<>();
    private Integer brojGreski = 0;
    private Boolean smjer = true;
    private Boolean state = false;

    public Aktuator(String nazivAktuatora, Integer tipSAktuatora, Integer vrstaAktuatora, Float minVrijednostAktuatora, Float maxVrijednostAktuatora, String komentarAktuatora) {
        this.nazivAktuatora = nazivAktuatora;
        this.tipAktuatora = tipSAktuatora;
        this.vrstaAktuatora = vrstaAktuatora;
        this.minVrijednostAktuatora = minVrijednostAktuatora;
        this.maxVrijednostAktuatora = maxVrijednostAktuatora;
        this.komentarAktuatora = komentarAktuatora;
    }

    public Aktuator() {
    }

    public Aktuator(Aktuator aktuator) {
        this.IdAktuatora = aktuator.getIdAktuatora();
        this.IdModelaAktuatora = aktuator.getIdModelaAktuatora();
        this.nazivAktuatora = aktuator.getNazivAktuatora();
        this.tipAktuatora = aktuator.getTipAktuatora();
        this.vrstaAktuatora = aktuator.getVrstaAktuatora();
        this.minVrijednostAktuatora = aktuator.getMinVrijednostAktuatora();
        this.maxVrijednostAktuatora = aktuator.getMaxVrijednostAktuatora();
        this.komentarAktuatora = aktuator.getKomentarAktuatora();
        this.vrijednostAktuatora = aktuator.getVrijednostAktuatora();
        this.statusAktuatora = aktuator.getStatusAktuatora();
        this.popisSenzora = new ArrayList<>(aktuator.getPopisSenzora());
        this.state = aktuator.getState();
       }

    public String getNazivAktuatora() {
        return nazivAktuatora;
    }

    public void setNazivAktuatora(String nazivAktuatora) {
        this.nazivAktuatora = nazivAktuatora;
    }

    public Integer getTipAktuatora() {
        return tipAktuatora;
    }

    public void setTipAktuatora(Integer tipSAktuatora) {
        this.tipAktuatora = tipSAktuatora;
    }

    public Integer getVrstaAktuatora() {
        return vrstaAktuatora;
    }

    public void setVrstaAktuatora(Integer vrstaAktuatora) {
        this.vrstaAktuatora = vrstaAktuatora;
    }

    public Float getMinVrijednostAktuatora() {
        return minVrijednostAktuatora;
    }

    public void setMinVrijednostAktuatora(Float minVrijednostAktuatora) {
        this.minVrijednostAktuatora = minVrijednostAktuatora;
    }

    public Float getMaxVrijednostAktuatora() {
        return maxVrijednostAktuatora;
    }

    public void setMaxVrijednostAktuatora(Float maxVrijednostAktuatora) {
        this.maxVrijednostAktuatora = maxVrijednostAktuatora;
    }

    public String getKomentarAktuatora() {
        return komentarAktuatora;
    }

    public void setKomentarAktuatora(String komentarAktuatora) {
        this.komentarAktuatora = komentarAktuatora;
    }

    public Float getVrijednostAktuatora() {
        return vrijednostAktuatora;
    }

    public void setVrijednostAktuatora(Float vrijednostAktuatora) {
        this.vrijednostAktuatora = vrijednostAktuatora;
    }

    public Integer getIdAktuatora() {
        return IdAktuatora;
    }

    public void setIdAktuatora(Integer IdAktuatora) {
        this.IdAktuatora = IdAktuatora;
    }

    public Integer getStatusAktuatora() {
        return statusAktuatora;
    }

    public void setStatusAktuatora(Integer statusAktuatora) {
        this.statusAktuatora = statusAktuatora;
    }

    public List<Senzor> getPopisSenzora() {
        return popisSenzora;
    }

    public void setPopisSenzora(List<Senzor> popisSenzora) {
        this.popisSenzora = popisSenzora;
    }

    public Integer getBrojGreski() {
        return brojGreski;
    }

    public void setBrojGreski(Integer brojGreski) {
        this.brojGreski = brojGreski;
    }

    public Boolean getSmjer() {
        return smjer;
    }

    public void setSmjer(Boolean smjer) {
        this.smjer = smjer;
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

    public Integer getIdModelaAktuatora() {
        return IdModelaAktuatora;
    }

    public void setIdModelaAktuatora(Integer IdModelaAktuatora) {
        this.IdModelaAktuatora = IdModelaAktuatora;
    }

    @Override
    public void update(Subject o) {
        state = o.getState();
    }

    public Boolean getState() {
        return state;
    }

}
