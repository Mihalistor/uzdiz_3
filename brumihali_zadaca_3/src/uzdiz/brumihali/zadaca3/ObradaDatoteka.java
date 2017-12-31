/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uzdiz.brumihali.zadaca3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import uzdiz.brumihali.zadaca3.podaci.Aktuator;
import uzdiz.brumihali.zadaca3.podaci.AktuatorBuilder;
import uzdiz.brumihali.zadaca3.podaci.AktuatorBuilderImpl;
import uzdiz.brumihali.zadaca3.podaci.Mjesto;
import uzdiz.brumihali.zadaca3.podaci.MjestoBuilder;
import uzdiz.brumihali.zadaca3.podaci.MjestoBuilderImpl;
import uzdiz.brumihali.zadaca3.podaci.Senzor;
import uzdiz.brumihali.zadaca3.podaci.SenzorBuilder;
import uzdiz.brumihali.zadaca3.podaci.SenzorBuilderImpl;

/**
 *
 * @author bruno
 */
public class ObradaDatoteka {

    List<Mjesto> listaMjesta = new ArrayList<>();
    List<Senzor> listaSenzora = new ArrayList<>();
    List<Aktuator> listaAktuatora = new ArrayList<>();
    String linija = "";
    String delimiter = ";";
    List<Aktuator> pomocnaAktuatori = new ArrayList<>();
    ProvjeraMjesta provjeraMjesta = new ProvjeraMjesta();

    public List<Mjesto> dohvatiMjesta(String nazivDatoteke) {
        try (BufferedReader br = new BufferedReader(new FileReader(nazivDatoteke))) {
            linija = br.readLine();
            int duljina = 5;
            List<Integer> listaIdMjesta = new ArrayList<>();
            while ((linija = br.readLine()) != null) {
                if (linija.split(delimiter).length == duljina) {
                    String[] zapis = linija.split(delimiter);
                    if (Integer.parseInt(zapis[2]) != 1 && Integer.parseInt(zapis[2]) != 0) {
                        System.out.println("CSV Mjesto nije ispravno - ne odgovara tip (0 ili 1)");
                    } else if (listaIdMjesta.contains(Integer.parseInt(zapis[0]))) {
                        System.out.println("Mjesto s ID: '" + zapis[0] + "' vec postoji");
                    } else {
                        MjestoBuilder mb = new MjestoBuilderImpl();
                        mb.setIdMjesta(Integer.parseInt(zapis[0]));
                        mb.setNazivMjesta(zapis[1]);
                        mb.setTipMjesta(Integer.parseInt(zapis[2]));
                        mb.setBrojSenzoraMjesta(Integer.parseInt(zapis[3]));
                        mb.setBrojAktuatoraMjesta(Integer.parseInt(zapis[4]));
                        listaMjesta.add(mb.build());
                        listaIdMjesta.add(Integer.parseInt(zapis[0]));
                    }
                } else {
                    System.out.println("CSV Mjesto nije ispravno");
                }
            }
        } catch (IOException ex) {
            System.out.println("GRESKA KOD CITANJA DATOTEKE MJESTA");
        }
        return listaMjesta;
    }

    public List<Senzor> dohvatiSenzore(String nazivDatoteke) {
        try (BufferedReader br = new BufferedReader(new FileReader(nazivDatoteke))) {
            linija = br.readLine();
            int duljina = 7;
            List<Integer> listaIdModelaSenzora = new ArrayList<>();
            while ((linija = br.readLine()) != null) {
                if ((linija.substring(linija.length() - 1)).equals(";")) {
                    linija += " ";
                }
                if (linija.split(delimiter).length == duljina) {
                    String[] zapis = linija.split(delimiter);
                    if (listaIdModelaSenzora.contains(Integer.parseInt(zapis[0]))) {
                        System.out.println("Senzor s ID: '" + zapis[0] + "' vec postoji");
                    } else if (Integer.parseInt(zapis[2]) != 0 && Integer.parseInt(zapis[2]) != 1 && Integer.parseInt(zapis[2]) != 2 || Integer.parseInt(zapis[3]) != 0 && Integer.parseInt(zapis[3]) != 1 && Integer.parseInt(zapis[3]) != 2 && Integer.parseInt(zapis[3]) != 3) {
                        System.out.println("CSV za Senzor nije ispravan");
                    } else {
                        SenzorBuilder sb = new SenzorBuilderImpl();
                        sb.setIdModelaSenzora(Integer.parseInt(zapis[0]));
                        sb.setNazivSenzora(zapis[1]);
                        sb.setTipSenzora(Integer.parseInt(zapis[2]));
                        sb.setVrstaSenzora(Integer.parseInt(zapis[3]));
                        sb.setMinSenzora(Float.parseFloat(zapis[4]));
                        sb.setMaxSenzora(Float.parseFloat(zapis[5]));
                        sb.setKomentarSenzora(zapis[6]);
                        listaIdModelaSenzora.add(Integer.parseInt(zapis[0]));
                        Senzor s = sb.build();
                        listaSenzora.add(new Senzor(s));
                    }
                } else {
                    String[] zapis = linija.split(delimiter);
                    System.out.println("CSV Senzor '" + zapis[1] + "' s ID: " + zapis[0] + " nije ispravan te se odbacuje");
                }

            }
        } catch (IOException ex) {
            System.out.println("GRESKA KOD CITANJA DATOTEKE SENZORA");
        }
        return listaSenzora;
    }

    public List<Aktuator> dohvatiAktuatore(String nazivDatoteke) {
        try (BufferedReader br = new BufferedReader(new FileReader(nazivDatoteke))) {
            linija = br.readLine();
            int duljina = 7;
            List<Integer> listaIdModelaAktuatora = new ArrayList<>();
            while ((linija = br.readLine()) != null) {
                if ((linija.substring(linija.length() - 1)).equals(";")) {
                    linija += " ";
                }
                if (linija.split(delimiter).length == duljina) {
                    String[] zapis = linija.split(delimiter);
                    if (listaIdModelaAktuatora.contains(Integer.parseInt(zapis[0]))) {
                        System.out.println("Aktuator s ID: '" + zapis[0] + "' vec postoji");
                    } else if (Integer.parseInt(zapis[2]) != 0 && Integer.parseInt(zapis[2]) != 1 && Integer.parseInt(zapis[2]) != 2 || Integer.parseInt(zapis[3]) != 0 && Integer.parseInt(zapis[3]) != 1 && Integer.parseInt(zapis[3]) != 2 && Integer.parseInt(zapis[3]) != 3) {
                        System.out.println("CSV za Aktuator nije ispravan");
                    } else {
                        AktuatorBuilder ab = new AktuatorBuilderImpl();
                        ab.setIdModelaAktuatora(Integer.parseInt(zapis[0]));
                        ab.setNazivAktuatora(zapis[1]);
                        ab.setTipAktuatora(Integer.parseInt(zapis[2]));
                        ab.setVrstaAktuatora(Integer.parseInt(zapis[3]));
                        ab.setMinAktuatora(Float.parseFloat(zapis[4]));
                        ab.setMaxAktuatora(Float.parseFloat(zapis[5]));
                        ab.setKomentarAktuatora(zapis[6]);
                        listaIdModelaAktuatora.add(Integer.parseInt(zapis[0]));
                        Aktuator a = ab.build();
                        listaAktuatora.add(new Aktuator(a));
                    }
                } else {
                    String[] zapis = linija.split(delimiter);
                    System.out.println("CSV Aktuator '" + zapis[1] + "' s ID: " + zapis[0] + " nije ispravan te se odbacuje");
                }
            }
        } catch (IOException ex) {
            System.out.println("GRESKA KOD CITANJA DATOTEKE AKTUATORA");
        }
        return listaAktuatora;
    }

    void dodjeliUredaje(String nazivDatoteke) {
        try (BufferedReader br = new BufferedReader(new FileReader(nazivDatoteke))) {
            linija = br.readLine();
            linija = br.readLine();
            linija = br.readLine();
            while ((linija = br.readLine()) != null) {
                if (linija.split(delimiter).length == 5) {
                    String[] zapis = linija.split(delimiter);
                    int index = 0;
                    for (int i = 0; i < listaMjesta.size(); i++) {
                        if (listaMjesta.get(i).getIdMjesta().equals(Integer.parseInt(zapis[1]))) {
                            index = i;
                            break;
                        }
                    }
                    if (zapis[0].equals("0") && zapis[2].equals("0")) {
                        if (listaMjesta.get(index).getSenzori().size() < listaMjesta.get(index).getBrojSenzora()) {
                            Boolean postoji = false;
                            for (Senzor senzor : listaSenzora) {
                                if (senzor.getIdModelaSenzora().equals(Integer.parseInt(zapis[3]))) {
                                    postoji = true;
                                    if (senzor.getTipSenzora().equals(listaMjesta.get(index).getTipMjesta()) || senzor.getTipSenzora().equals(2)) {
                                        Senzor senzorek = new Senzor(senzor);
                                        senzorek.setIdSenzora(Integer.parseInt(zapis[4]));
                                        List<Senzor> novaLista = new ArrayList<>(listaMjesta.get(index).getSenzori());
                                        novaLista.add(senzorek);
                                        listaMjesta.get(index).setSenzori(novaLista);
                                        provjeraMjesta.getListaIdUredaja().add(Integer.parseInt(zapis[4]));                                        
                                        break;
                                    } else {
                                        System.out.println("Senzor '" + senzor.getNazivSenzora() + "' s tipom: " + senzor.getTipSenzora() + " ne odgovara mjestu '" + listaMjesta.get(index).getNazivMjesta() + "' koji ima tip: " + listaMjesta.get(index).getTipMjesta());
                                    }
                                }
                            }
                            if (!postoji) {
                                System.out.println("Senzor s ID modela: " + zapis[3] + " ne postoji te zbog toga nije dodan mjestu");
                            }
                        } else {
                            System.out.println("Senzor s ID modela: " + zapis[3] + " i ID: " + zapis[4] + " nije dodan mjestu jer vise nema mjesta na toj lokaciji");
                        }

                    } else if (zapis[0].equals("0") && zapis[2].equals("1")) {
                        if (listaMjesta.get(index).getAktuatori().size() < listaMjesta.get(index).getBrojAktuatora()) {
                            Boolean postoji = false;
                            for (Aktuator aktuator : listaAktuatora) {
                                if (aktuator.getIdModelaAktuatora().equals(Integer.parseInt(zapis[3]))) {
                                    postoji = true;
                                    if (aktuator.getTipAktuatora().equals(listaMjesta.get(index).getTipMjesta()) || aktuator.getTipAktuatora().equals(2)) {
                                        Aktuator aktuatorek = new Aktuator(aktuator);
                                        aktuatorek.setIdAktuatora(Integer.parseInt(zapis[4]));
                                        provjeraMjesta.getListaIdUredaja().add(Integer.parseInt(zapis[4]));
                                        List<Aktuator> novaLista = new ArrayList<>(listaMjesta.get(index).getAktuatori());
                                        novaLista.add(aktuatorek);
                                        listaMjesta.get(index).setAktuatori(novaLista);
                                        break;
                                    } else {
                                        System.out.println("Aktuator '" + aktuator.getNazivAktuatora() + "' s tipom: " + aktuator.getTipAktuatora() + " ne odgovara mjestu '" + listaMjesta.get(index).getNazivMjesta() + "' koji ima tip: " + listaMjesta.get(index).getTipMjesta());
                                    }
                                }
                            }
                            if (!postoji) {
                                System.out.println("Aktuator s ID modela: " + zapis[3] + " ne postoji te zbog toga nije dodan mjestu");
                            }
                        } else {
                            System.out.println("Aktuator s ID modela: " + zapis[3] + " i ID: " + zapis[4] + " nije dodan mjestu jer vise nema mjesta na toj lokaciji");
                        }
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println("GRESKA KOD CITANJA DATOTEKE RASPOREDA");
        }
    }

    void dodjeliSenzoreAktuatorima(String nazivDatoteke) {
        try (BufferedReader br = new BufferedReader(new FileReader(nazivDatoteke))) {
            linija = br.readLine();
            linija = br.readLine();
            linija = br.readLine();
            while ((linija = br.readLine()) != null) {
                if (linija.split(delimiter).length == 3) {
                    String[] zapis = linija.split(delimiter);
                    String[] senzori = zapis[2].split(",");
                    Boolean postojiAktuator = false;
                    Boolean postojiSenzor = false;
                    //1;1002;1000,1001
                    for (Mjesto mjesto : listaMjesta) {
                        for (Aktuator aktuator : mjesto.getAktuatori()) {
                            if (aktuator.getIdAktuatora() == Integer.parseInt(zapis[1])) {
                                postojiAktuator = true;
                                for (String idSenzora : senzori) { //1000,1001
                                    for (Senzor senzor : mjesto.getSenzori()) {
                                        if (senzor.getIdSenzora() == Integer.parseInt(idSenzora)) {
                                            postojiSenzor = true;
                                            List<Senzor> novi = new ArrayList<>(aktuator.getPopisSenzora());
                                            senzor.addObserver(aktuator);
                                            novi.add(senzor);                                           
                                            aktuator.setPopisSenzora(novi);
                                            break;
                                        } else {
                                            postojiSenzor = false;
                                        }
                                    }
                                    if (!postojiSenzor) {
                                        System.out.println("Nije moguce dodjeliti senzor koji ima ID: " + idSenzora + " aktuatoru. Senzor se ne nalazi u mjestu");
                                    }
                                }
                            }
                        }
                    }
                    if (!postojiAktuator) {
                        System.out.println("Nije moguce dodjeliti senzore aktuatoru koji ima ID: " + zapis[1] + ". Aktuator se ne nalazi u mjestu");
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println("GRESKA KOD CITANJA DATOTEKE RASPOREDA");
        }
    }
}
