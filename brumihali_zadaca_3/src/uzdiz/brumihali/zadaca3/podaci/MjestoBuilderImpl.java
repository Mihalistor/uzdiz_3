/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uzdiz.brumihali.zadaca3.podaci;

import java.util.List;

/**
 *
 * @author bruno
 */
public class MjestoBuilderImpl implements MjestoBuilder{
    
    private Mjesto mjesto;

    public MjestoBuilderImpl(){
        mjesto = new Mjesto();
    }
    
    @Override
    public Mjesto build() {
        return mjesto;
    }

    @Override
    public MjestoBuilder setNazivMjesta(String nazivMjesta) {
        mjesto.setNazivMjesta(nazivMjesta);
        return this;
    }

    @Override
    public MjestoBuilder setTipMjesta(Integer tipMjesta) {
        mjesto.setTipMjesta(tipMjesta);
        return this;
    }

    @Override
    public MjestoBuilder setBrojSenzoraMjesta(Integer brojSenzoraMjesta) {
        mjesto.setBrojSenzora(brojSenzoraMjesta);
        return this;
    }

    @Override
    public MjestoBuilder setBrojAktuatoraMjesta(Integer brojAktuatoraMjesta) {
        mjesto.setBrojAktuatora(brojAktuatoraMjesta);
        return this;
    }

    @Override
    public MjestoBuilder setSenzori(List<Senzor> senzori) {
        mjesto.setSenzori(senzori);
        return this;
    }

    @Override
    public MjestoBuilder setAktuatori(List<Aktuator> aktuatori) {
        mjesto.setAktuatori(aktuatori);
        return this;
    }

    @Override
    public MjestoBuilder setIdMjesta(Integer idMjesta) {
        mjesto.setIdMjesta(idMjesta);
        return this;
    }
    

}
