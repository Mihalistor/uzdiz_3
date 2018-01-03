/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uzdiz.brumihali.zadaca3;

import java.util.ArrayList;
import java.util.List;
import uzdiz.brumihali.zadaca3.chain.CommandHandler;
import uzdiz.brumihali.zadaca3.chain.IspisAktuatora;
import uzdiz.brumihali.zadaca3.chain.IspisHelp;
import uzdiz.brumihali.zadaca3.chain.IspisMjesta;
import uzdiz.brumihali.zadaca3.chain.IspisSenzora;
import uzdiz.brumihali.zadaca3.chain.IspisStatistike;
import uzdiz.brumihali.zadaca3.chain.Izlaz;
import uzdiz.brumihali.zadaca3.chain.IzvrsiDretvu;
import uzdiz.brumihali.zadaca3.chain.PostavljanjeIspravnosti;
import uzdiz.brumihali.zadaca3.chain.SpremanjePodataka;
import uzdiz.brumihali.zadaca3.chain.VlastitaFunkcionalnost;
import uzdiz.brumihali.zadaca3.chain.VracanjePodataka;
import uzdiz.brumihali.zadaca3.memento.Caretaker;
import uzdiz.brumihali.zadaca3.memento.SpremistePodataka;
import uzdiz.brumihali.zadaca3.podaci.Mjesto;

/**
 *
 * @author bruno
 */
public class Komande {

    public static List<Mjesto> listaMjesta = new ArrayList<>();
    Statistika statistika = Statistika.getInstance();
    Caretaker caretaker = new Caretaker();
    SpremistePodataka sp = new SpremistePodataka();
    PrikazPrograma pp = PrikazPrograma.getInstance();


    public Komande(List<Mjesto> listaMjesta) {
        this.listaMjesta = listaMjesta;
    }
    
    public static CommandHandler postaviChain(){
        CommandHandler ispisMjesta = new IspisMjesta();
        CommandHandler ispisSenzora = new IspisSenzora();
        CommandHandler ispisAktuatora = new IspisAktuatora();
        CommandHandler ispisStatistika = new IspisStatistike();
        CommandHandler spremanjePodataka = new SpremanjePodataka();
        CommandHandler vracanjePodataka = new VracanjePodataka();
        CommandHandler izvrsiDretvu = new IzvrsiDretvu();
        CommandHandler vlastitaFunkcionalnost = new VlastitaFunkcionalnost();
        CommandHandler postavljanjeIspravnosti = new PostavljanjeIspravnosti();
        CommandHandler ispisHelp = new IspisHelp();
        CommandHandler izlaz = new Izlaz();

        ispisMjesta.setSuccessor(ispisSenzora);
        ispisSenzora.setSuccessor(ispisAktuatora);
        ispisAktuatora.setSuccessor(ispisStatistika);
        ispisStatistika.setSuccessor(spremanjePodataka);
        spremanjePodataka.setSuccessor(vracanjePodataka);
        vracanjePodataka.setSuccessor(izvrsiDretvu);
        izvrsiDretvu.setSuccessor(vlastitaFunkcionalnost);
        vlastitaFunkcionalnost.setSuccessor(postavljanjeIspravnosti);
        postavljanjeIspravnosti.setSuccessor(ispisHelp);
        ispisHelp.setSuccessor(izlaz);      
        return ispisMjesta;
    }

    public void izvrsiKomande(String komanda) {     
        CommandHandler ch = postaviChain();
        ch.setCaretaker(caretaker);
        ch.setSp(sp);
        ch.handleRequest(komanda);
        
        statistika.setBrojIzvrsenihKomandi(statistika.getBrojIzvrsenihKomandi() + 1);
    }
}
