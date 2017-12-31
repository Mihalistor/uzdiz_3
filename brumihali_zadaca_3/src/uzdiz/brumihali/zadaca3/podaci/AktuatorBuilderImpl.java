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
public class AktuatorBuilderImpl implements AktuatorBuilder{
    
    private Aktuator aktuator;

    public AktuatorBuilderImpl() {
        aktuator = new Aktuator();
    }
    
    @Override
    public Aktuator build() {
        return aktuator;
    }

    @Override
    public AktuatorBuilder setIdAktuatora(int id) {
        aktuator.setIdAktuatora(id);
        return this;
    }

    @Override
    public AktuatorBuilder setNazivAktuatora(String naziv) {
        aktuator.setNazivAktuatora(naziv);
        return this;
    }

    @Override
    public AktuatorBuilder setTipAktuatora(int tip) {
        aktuator.setTipAktuatora(tip);
        return this;
    }

    @Override
    public AktuatorBuilder setVrstaAktuatora(int vrsta) {
        aktuator.setVrstaAktuatora(vrsta);
        return this;
    }

    @Override
    public AktuatorBuilder setMinAktuatora(float minVrijednost) {
        aktuator.setMinVrijednostAktuatora(minVrijednost);
        return this;
    }

    @Override
    public AktuatorBuilder setMaxAktuatora(float maxVrijednost) {
        aktuator.setMaxVrijednostAktuatora(maxVrijednost);
        return this;
    }

    @Override
    public AktuatorBuilder setKomentarAktuatora(String komentar) {
        aktuator.setKomentarAktuatora(komentar);
        return this;
    }

    @Override
    public AktuatorBuilder setVrijednostAktuatora(float vrijednost) {
        aktuator.setVrijednostAktuatora(vrijednost);
        return this;
    }

    @Override
    public AktuatorBuilder setStatusAktuatora(int status) {
        aktuator.setStatusAktuatora(status);
        return this;
    }

    @Override
    public AktuatorBuilder setBrojGreskiAktuatora(int brojGreski) {
        aktuator.setBrojGreski(brojGreski);
        return this;
    }

    @Override
    public AktuatorBuilder setIdModelaAktuatora(int idModela) {
        aktuator.setIdModelaAktuatora(idModela);
        return this;
    }
    
}
