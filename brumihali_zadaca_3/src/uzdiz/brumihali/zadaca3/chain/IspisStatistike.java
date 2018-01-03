/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uzdiz.brumihali.zadaca3.chain;

import uzdiz.brumihali.zadaca3.PrikazPrograma;
import uzdiz.brumihali.zadaca3.Statistika;

/**
 *
 * @author bruno
 */
public class IspisStatistike extends CommandHandler {

    PrikazPrograma pp = PrikazPrograma.getInstance();
    Statistika statistika = Statistika.getInstance();

    @Override
    public void handleRequest(String komanda) {
        if (komanda.equals("S")) {
            pp.prikazi("KOMANDA ISPIS STATISTIKE");
            pp.prikazi("1. Broj ispravnih mjesta iz CSV datoteke: " + statistika.getBrojIspravnihMjesta());
            pp.prikazi("2. Broj neispravnih mjesta iz CSV datoteke: " + statistika.getBrojNeispravnihMjesta());
            pp.prikazi("3. Broj ispravnih senzora iz CSV datoteke: " + statistika.getBrojIspravnihSenzora());
            pp.prikazi("4. Broj neispravnih senzora iz CSV datoteke: " + statistika.getBrojNeispravnihSenzora());
            pp.prikazi("5. Broj ispravnih aktuatora iz CSV datoteke: " + statistika.getBrojIspravnihAktuatora());
            pp.prikazi("6. Broj neispravnih aktuatora iz CSV datoteke: " + statistika.getBrojNeispravnihAktuatora());
            pp.prikazi("7. Broj ispravnih dodjela senzora mjestima: " + statistika.getBrojDodjeljenihSenzora());
            pp.prikazi("8. Broj neispravnih dodjela senzora mjestima: " + statistika.getBrojNeDodjeljenihSenzora());
            pp.prikazi("9. Broj ispravnih dodjela akutuatora mjestima: " + statistika.getBrojDodjeljenihAktuatora());
            pp.prikazi("10. Broj neispravnih dodjela akutuatora mjestima: " + statistika.getBrojNeDodjeljenihAktuatora());
            pp.prikazi("11. Broj ispravnih dodjela senzora aktuatorima: " + statistika.getBrojDodjeljenihSenzoraAktuatorima());
            pp.prikazi("12. Broj neispravnih dodjela senzora aktuatorima: " + statistika.getBrojNeDodjeljenihSenzoraAktuatorima());
            pp.prikazi("13. Broj zamjena senzora: " + statistika.getZamjenaSenzora());
            pp.prikazi("14. Broj zamjena aktuatora: " + statistika.getZamjenaAktuatora());
            pp.prikazi("15. Broj ucitanih vrijednosti senzora: " + statistika.getVrijednostiSenzora());
            pp.prikazi("16. Broj ucitanih vrijednosti aktuatora: " + statistika.getVrijednostiAktuatora());
            pp.prikazi("17. Broj poslanih inicijalizacijskih poruka: " + statistika.getBrojIniPoruka());
            pp.prikazi("18. Broj generiranih brojeva: " + statistika.getBrojGeneriranihBrojeva());
            pp.prikazi("19. Broj izvrsenih komandi: " + statistika.getBrojIzvrsenihKomandi());
            pp.prikazi("20. Vrijeme pokretanja programa: " + statistika.getVrijemePokretanja());
        } else {
            if(successor != null){
                successor.setCaretaker(caretaker);
                successor.setSp(sp);
                successor.handleRequest(komanda);
            }
        }
    }

}
