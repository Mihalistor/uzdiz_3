/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uzdiz.brumihali.zadaca3;

import java.util.Random;

/**
 *
 * @author bruno
 */
public class GeneratorBrojeva {
    private static GeneratorBrojeva instance = new GeneratorBrojeva();
    //IspisDatoteka ispisDatoteka = new IspisDatoteka();
    public static int sjeme;
    Random generator = new Random(sjeme);

    private GeneratorBrojeva() {
    }
    
    public static GeneratorBrojeva getInstance(){
        if (instance == null) {
            instance = new GeneratorBrojeva();
        }
        return instance;
    }
    
    public int dajSlucaniBroj(int odBroja, int doBroja){
        int slucajniBroj = odBroja + generator.nextInt((doBroja - odBroja) + 1);
        //System.out.println("Generiran slucajni INT Broj: " + slucajniBroj);
        //ispisDatoteka.zapisiPoruku("Generiran slucajni INT broj " + Integer.toString(slucajniBroj));
        return slucajniBroj;
    }

    public float dajSlucaniBroj(float odBroja, float doBroja) {
        float slucajniBroj = odBroja + generator.nextFloat() * (doBroja - odBroja);
        //System.out.println("Generiran slucajni FLOAT Broj: " + slucajniBroj);
        //ispisDatoteka.zapisiPoruku("Generiran slucajni FLOAT broj " + Float.toString(slucajniBroj));
        return slucajniBroj;
    }
}
