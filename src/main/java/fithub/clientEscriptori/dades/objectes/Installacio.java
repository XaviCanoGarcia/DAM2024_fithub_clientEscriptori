package fithub.clientEscriptori.dades.objectes;

import java.util.ArrayList;
import java.util.HashMap;

import static fithub.clientEscriptori.dades.Constants.*;
import static fithub.clientEscriptori.dades.Constants.HM_ACT_DESC;

/**
 * Classe que representa l'objecte de tipus instal·lació.
 */
public class Installacio {
    private int id;
    private String nom;
    private String descripcio;
    private String tipus;
    //private String imatge;PENDENT


    /**
     * Constructor de la classe Installacio.
     *
     * @param nom        Nom de la instal·lació
     * @param descripcio Descripció de la instal·lació
     * @param tipus      Tipus de la instal·lació
     */
    public Installacio(String nom, String descripcio, String tipus) {
        this.id = -1;
        this.nom = nom;
        this.descripcio = descripcio;
        this.tipus = tipus;
    }

    /**
     * Aquest metode converteix un objecte tipu installacio a un hashMap
     *
     * @param installacio Objecte installacio que es vol convertir
     * @return installacioMap HashMap que conti les dades de l'objecte installacio
     */
    public HashMap<String, String> installacio_to_map(Installacio installacio) {
        HashMap<String, String> installacioMap = new HashMap<>();
        installacioMap.put(HM_INS_TIPUS, installacio.getTipus());
        installacioMap.put(HM_INS_NOM, installacio.getNom());
        installacioMap.put(HM_INS_DESC, installacio.getDescripcio());
        installacioMap.put(HM_INS_ID, String.valueOf(installacio.getId()));


        return installacioMap;
    }

    /**
     * Aquest metode converteix un hashMap a un objecte tipu installacio
     *
     * @param map Objecte hashMap que es vol convertir en installacio
     * @return installacio Objecte installacio obtingut a partir de les dades del hashMap
     */
    public Installacio map_to_installacio(HashMap<String, String> map) {
        Installacio installacio = new Installacio("", "", "");
        installacio.setNom(map.get(HM_INS_NOM));
        installacio.setTipus(map.get(HM_INS_TIPUS));
        installacio.setDescripcio(map.get(HM_INS_DESC));
        installacio.setId(Integer.valueOf(map.get(HM_INS_ID)));

        return installacio;
    }

    /**
     * Aquest metode converteix una lista de installacios a un array de HashMap
     *
     * @param llistaInstallacio Llista d'installacios que es vol convertir
     * @return mapList Llista d'installacios convertida a mapa
     */
    public ArrayList<HashMap<String, String>> creaLlistaInstallacioMap(Installacio[] llistaInstallacio) {
        ArrayList<HashMap<String, String>> mapList = new ArrayList<HashMap<String, String>>();
        int index = 0;
        for (Installacio ins : llistaInstallacio) {
            if (ins != null) {
                mapList.add(installacio_to_map(ins));
            }
        }
        return mapList;
    }

    /**
     * Aquest metode llegeix un HashMap i el converteix a llista d'installacios
     *
     * @param mapList ArrayList que conté la llista d'installacios
     * @return llistainstallacio Llista d'installacios extreta del HashMap
     */
    public Installacio[] crearLlistaInstallacio(ArrayList<HashMap<String, String>> mapList) {
        Installacio[] llistraInstallacio = new Installacio[mapList.size()];
        int index = 0;
        for (int i = 0; i < mapList.size(); i++) {
            if (mapList.get(i) != null) llistraInstallacio[i] = map_to_installacio(mapList.get(i));
        }

        return llistraInstallacio;
    }

    // Getters i setters

    /**
     * Obté l'identificador de la instal·lació.
     *
     * @return L'identificador de la instal·lació
     */
    public int getId() {
        return id;
    }

    /**
     * Estableix l'identificador de la instal·lació.
     *
     * @param id L'identificador de la instal·lació a establir
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obté el nom de la instal·lació.
     *
     * @return El nom de la instal·lació
     */
    public String getNom() {
        return nom;
    }

    /**
     * Estableix el nom de la instal·lació.
     *
     * @param nom El nom de la instal·lació
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Obté la descripció de la instal·lació.
     *
     * @return La descripció de la instal·lació
     */
    public String getDescripcio() {
        return descripcio;
    }

    /**
     * Estableix la descripció de la instal·lació.
     *
     * @param descripcio La descripció de la instal·lació
     */
    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    /**
     * Obté el tipus de la instal·lació.
     *
     * @return El tipus de la instal·lació
     */
    public String getTipus() {
        return tipus;
    }

    /**
     * Estableix el tipus de la instal·lació.
     *
     * @param tipus El tipus de la instal·lació
     */
    public void setTipus(String tipus) {
        this.tipus = tipus;
    }
}
