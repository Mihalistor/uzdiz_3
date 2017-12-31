/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uzdiz.brumihali.zadaca3.podaci;

/**
 *
 * @author bruno
 */
public class SenzorBuilderImpl implements SenzorBuilder {

    private Senzor senzor;

    public SenzorBuilderImpl() {
        senzor = new Senzor();
    }

    @Override
    public Senzor build() {
        return senzor;
    }

    @Override
    public SenzorBuilder setIdSenzora(int id) {
        senzor.setIdSenzora(id);
        return this;
    }

    @Override
    public SenzorBuilder setNazivSenzora(String naziv) {
        senzor.setNazivSenzora(naziv);
        return this;
    }

    @Override
    public SenzorBuilder setTipSenzora(int tip) {
        senzor.setTipSenzora(tip);
        return this;
    }

    @Override
    public SenzorBuilder setVrstaSenzora(int vrsta) {
        senzor.setVrstaSenzora(vrsta);
        return this;
    }

    @Override
    public SenzorBuilder setMinSenzora(float minVrijednost) {
        senzor.setMinVrijednostSenzora(minVrijednost);
        return this;
    }

    @Override
    public SenzorBuilder setMaxSenzora(float maxVrijednost) {
        senzor.setMaxVrijednostSenzora(maxVrijednost);
        return this;
    }

    @Override
    public SenzorBuilder setKomentarSenzora(String komentar) {
        senzor.setKomentarSenzora(komentar);
        return this;
    }

    @Override
    public SenzorBuilder setVrijednostSenzora(float vrijednost) {
        senzor.setVrijednostSenzora(vrijednost);
        return this;
    }

    @Override
    public SenzorBuilder setStatusSenzora(int status) {
        senzor.setStatusSenzora(status);
        return this;
    }

    @Override
    public SenzorBuilder setBrojGreskiSenzora(int brojGreski) {
        senzor.setBrojGreski(brojGreski);
        return this;
    }

    @Override
    public SenzorBuilder setIdModelaSenzora(int idModela) {
        senzor.setIdModelaSenzora(idModela);
        return this;
    }

}
