/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uzdiz.brumihali.zadaca3.podaci;

import java.util.HashMap;
import java.util.List;
/**
 *
 * @author bruno
 */
public interface MjestoBuilder {
    Mjesto build();
    MjestoBuilder setNazivMjesta(final String nazivMjesta);
    MjestoBuilder setTipMjesta(final Integer tipMjesta);
    MjestoBuilder setBrojSenzoraMjesta(final Integer brojSenzoraMjesta);
    MjestoBuilder setBrojAktuatoraMjesta(final Integer brojAktuatoraMjesta);
    MjestoBuilder setSenzori(final List<Senzor> senzori);
    MjestoBuilder setAktuatori(final List<Aktuator> aktuatori);
    MjestoBuilder setIdMjesta(final Integer idMjesta);
}
