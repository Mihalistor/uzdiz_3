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
public interface AktuatorBuilder {
    Aktuator build();
    AktuatorBuilder setIdAktuatora(final int id);
    AktuatorBuilder setIdModelaAktuatora(final int idModela);
    AktuatorBuilder setNazivAktuatora(final String naziv);
    AktuatorBuilder setTipAktuatora(final int tip); 
    AktuatorBuilder setVrstaAktuatora(final int vrsta);
    AktuatorBuilder setMinAktuatora(final float minVrijednost);
    AktuatorBuilder setMaxAktuatora(final float maxVrijednost);
    AktuatorBuilder setKomentarAktuatora(final String komentar);
    AktuatorBuilder setVrijednostAktuatora(final float vrijednost);
    AktuatorBuilder setStatusAktuatora(final int status);
    AktuatorBuilder setBrojGreskiAktuatora(final int brojGreski);
    
}
