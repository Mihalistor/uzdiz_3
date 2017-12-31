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
public interface SenzorBuilder {
    Senzor build();
    SenzorBuilder setIdSenzora(final int id);
    SenzorBuilder setIdModelaSenzora(final int idModela);
    SenzorBuilder setNazivSenzora(final String naziv);
    SenzorBuilder setTipSenzora(final int tip); 
    SenzorBuilder setVrstaSenzora(final int vrsta);
    SenzorBuilder setMinSenzora(final float minVrijednost);
    SenzorBuilder setMaxSenzora(final float maxVrijednost);
    SenzorBuilder setKomentarSenzora(final String komentar);
    SenzorBuilder setVrijednostSenzora(final float vrijednost);
    SenzorBuilder setStatusSenzora(final int status);
    SenzorBuilder setBrojGreskiSenzora(final int brojGreski);
    
    
}
