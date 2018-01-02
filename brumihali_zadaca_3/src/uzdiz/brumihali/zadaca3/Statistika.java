/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uzdiz.brumihali.zadaca3;

/**
 *
 * @author bruno
 */
public class Statistika {
    
    private static Statistika instance = new Statistika();
    
    private int brojIspravnihSenzora = 0;
    private int brojIspravnihAktuatora = 0;
    private int brojNeispravnihSenzora = 0;
    private int brojNeispravnihAktuatora = 0;
    private int brojIspravnihMjesta = 0;
    private int brojNeispravnihMjesta = 0;
    private int brojDodjeljenihSenzora = 0;
    private int brojNeDodjeljenihSenzora = 0;
    private int brojDodjeljenihAktuatora = 0;
    private int brojNeDodjeljenihAktuatora = 0;
    private int brojDodjeljenihSenzoraAktuatorima = 0;
    private int brojNeDodjeljenihSenzoraAktuatorima = 0;       
    private int zamjenaSenzora = 0;
    private int zamjenaAktuatora = 0;
    private int vrijednostiSenzora = 0;
    private int vrijednostiAktuatora = 0;
    private int brojIniPoruka = 0;
    private int brojGeneriranihBrojeva = 0;
    private int brojIzvrsenihKomandi = 0;
    private String vrijemePokretanja = "";
    
    
    private Statistika(){
    }
    
    public static Statistika getInstance(){
        if (instance == null) {
            instance = new Statistika();
        }
        return instance;
    }

    public int getZamjenaSenzora() {
        return zamjenaSenzora;
    }

    public void setZamjenaSenzora(int zamjenaSenzora) {
        this.zamjenaSenzora = zamjenaSenzora;
    }

    public int getZamjenaAktuatora() {
        return zamjenaAktuatora;
    }

    public void setZamjenaAktuatora(int zamjenaAktuatora) {
        this.zamjenaAktuatora = zamjenaAktuatora;
    }

    public int getVrijednostiSenzora() {
        return vrijednostiSenzora;
    }

    public void setVrijednostiSenzora(int vrijednostiSenzora) {
        this.vrijednostiSenzora = vrijednostiSenzora;
    }

    public int getVrijednostiAktuatora() {
        return vrijednostiAktuatora;
    }

    public void setVrijednostiAktuatora(int vrijednostiAktuatora) {
        this.vrijednostiAktuatora = vrijednostiAktuatora;
    }

    public int getBrojIniPoruka() {
        return brojIniPoruka;
    }

    public void setBrojIniPoruka(int brojIniPoruka) {
        this.brojIniPoruka = brojIniPoruka;
    }

    public int getBrojGeneriranihBrojeva() {
        return brojGeneriranihBrojeva;
    }

    public void setBrojGeneriranihBrojeva(int brojGeneriranihBrojeva) {
        this.brojGeneriranihBrojeva = brojGeneriranihBrojeva;
    }

    public int getBrojDodjeljenihSenzora() {
        return brojDodjeljenihSenzora;
    }

    public void setBrojDodjeljenihSenzora(int brojDodjeljenihSenzora) {
        this.brojDodjeljenihSenzora = brojDodjeljenihSenzora;
    }

    public int getBrojNeispravnihSenzora() {
        return brojNeispravnihSenzora;
    }

    public void setBrojNeispravnihSenzora(int brojNeispravnihSenzora) {
        this.brojNeispravnihSenzora = brojNeispravnihSenzora;
    }

    public int getBrojNeispravnihAktuatora() {
        return brojNeispravnihAktuatora;
    }

    public void setBrojNeispravnihAktuatora(int brojNeispravnihAktuatora) {
        this.brojNeispravnihAktuatora = brojNeispravnihAktuatora;
    }

    public int getBrojIspravnihSenzora() {
        return brojIspravnihSenzora;
    }

    public void setBrojIspravnihSenzora(int brojIspravnihSenzora) {
        this.brojIspravnihSenzora = brojIspravnihSenzora;
    }

    public int getBrojIspravnihAktuatora() {
        return brojIspravnihAktuatora;
    }

    public void setBrojIspravnihAktuatora(int brojIspravnihAktuatora) {
        this.brojIspravnihAktuatora = brojIspravnihAktuatora;
    }

    public int getBrojIspravnihMjesta() {
        return brojIspravnihMjesta;
    }

    public void setBrojIspravnihMjesta(int brojIspravnihMjesta) {
        this.brojIspravnihMjesta = brojIspravnihMjesta;
    }

    public int getBrojNeispravnihMjesta() {
        return brojNeispravnihMjesta;
    }

    public void setBrojNeispravnihMjesta(int brojNeispravnihMjesta) {
        this.brojNeispravnihMjesta = brojNeispravnihMjesta;
    }

    public int getBrojDodjeljenihAktuatora() {
        return brojDodjeljenihAktuatora;
    }

    public void setBrojDodjeljenihAktuatora(int brojDodjeljenihAktuatora) {
        this.brojDodjeljenihAktuatora = brojDodjeljenihAktuatora;
    }

    public int getBrojNeDodjeljenihSenzora() {
        return brojNeDodjeljenihSenzora;
    }

    public void setBrojNeDodjeljenihSenzora(int brojNeDodjeljenihSenzora) {
        this.brojNeDodjeljenihSenzora = brojNeDodjeljenihSenzora;
    }

    public int getBrojNeDodjeljenihAktuatora() {
        return brojNeDodjeljenihAktuatora;
    }

    public void setBrojNeDodjeljenihAktuatora(int brojNeDodjeljenihAktuatora) {
        this.brojNeDodjeljenihAktuatora = brojNeDodjeljenihAktuatora;
    }

    public int getBrojDodjeljenihSenzoraAktuatorima() {
        return brojDodjeljenihSenzoraAktuatorima;
    }

    public void setBrojDodjeljenihSenzoraAktuatorima(int brojDodjeljenihSenzoraAktuatorima) {
        this.brojDodjeljenihSenzoraAktuatorima = brojDodjeljenihSenzoraAktuatorima;
    }

    public int getBrojNeDodjeljenihSenzoraAktuatorima() {
        return brojNeDodjeljenihSenzoraAktuatorima;
    }

    public void setBrojNeDodjeljenihSenzoraAktuatorima(int brojNeDodjeljenihSenzoraAktuatorima) {
        this.brojNeDodjeljenihSenzoraAktuatorima = brojNeDodjeljenihSenzoraAktuatorima;
    }

    public int getBrojIzvrsenihKomandi() {
        return brojIzvrsenihKomandi;
    }

    public void setBrojIzvrsenihKomandi(int brojIzvrsenihKomandi) {
        this.brojIzvrsenihKomandi = brojIzvrsenihKomandi;
    }

    public String getVrijemePokretanja() {
        return vrijemePokretanja;
    }

    public void setVrijemePokretanja(String vrijemePokretanja) {
        this.vrijemePokretanja = vrijemePokretanja;
    }
    
    
}
