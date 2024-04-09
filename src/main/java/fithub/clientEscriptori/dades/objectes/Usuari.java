package fithub.clientEscriptori.dades.objectes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import static fithub.clientEscriptori.dades.Constants.*;

/**
 * Classe Usuari que representa un usuari en l'aplicació FitHub.
 * <p>
 * Conté informació com el nom, cognoms, data de naixement, adreça, correu electrònic,
 * telèfon, contrasenya i data d'inscripció de l'usuari.
 *
 * @author Antonio Guerrero, Xavi Cano
 * @version 1.1
 */
public class Usuari implements Serializable {
    //Dades Obligatories
    private String DEFAULT_VALUE = "";
    private int sessioID;
    private String correu;
    private String contrasenya;
    private int tipus;

    //Dades opcionals
    private String nom;
    private String cognoms;
    private String dataNaixement;
    private String adreca;
    private String telefon;
    private String dataInscripcio;

    /**
     * Constructor de la classe Usuari reduït.
     *
     * @param correu      El correu electrònic de l'usuari.
     * @param contrasenya La contrasenya de l'usuari.
     */
    public Usuari(String correu, String contrasenya) {
        this.correu = correu;
        this.contrasenya = contrasenya;
        this.sessioID = -1;

        this.tipus = 2;
        this.nom = DEFAULT_VALUE;
        this.cognoms = DEFAULT_VALUE;
        this.dataNaixement = DEFAULT_VALUE;
        this.adreca = DEFAULT_VALUE;
        this.telefon = DEFAULT_VALUE;
        this.dataInscripcio = DEFAULT_VALUE;
    }

    /**
     * Constructor de la classe Usuari.
     *
     * @param nom            El nom de l'usuari.
     * @param cognoms        Els cognoms de l'usuari.
     * @param dataNaixement  La data de naixement de l'usuari.
     * @param adreca         L'adreça de l'usuari.
     * @param telefon        El número de telèfon de l'usuari.
     * @param correu         El correu electrònic de l'usuari.
     * @param contrasenya    La contrasenya de l'usuari.
     * @param dataInscripcio La data d'inscripció de l'usuari.
     */
    public Usuari(String nom, String cognoms, String dataNaixement, String adreca, String telefon, String correu, String contrasenya, String dataInscripcio) {
        this.sessioID = -1;
        this.tipus = 2;
        this.correu = correu;
        this.contrasenya = contrasenya;
        this.nom = nom;
        this.cognoms = cognoms;
        this.dataNaixement = dataNaixement;
        this.adreca = adreca;
        this.telefon = telefon;
        this.dataInscripcio = dataInscripcio;
    }

    /**
     * Aquest metode converteix un objecte tipu usuari a un hashMap
     *
     * @param usuari Objecte usuari que es vol convertir
     * @return usuariMap HashMap que conti les dades de l'objecte usuari
     */
    public HashMap<String, String> usuari_to_map(Usuari usuari) {
        HashMap<String, String> usuariMap = new HashMap<>();
        usuariMap.put(HM_USR_ID, String.valueOf(usuari.getSessioID()));
        usuariMap.put(HM_USR_CORREU, usuari.getCorreu());
        usuariMap.put(HM_USR_NOM, usuari.getNom());
        usuariMap.put(HM_USR_COGNOMS, usuari.getCognoms());
        usuariMap.put(HM_USR_ADRECA, usuari.getAdreca());
        usuariMap.put(HM_USR_DATAN, usuari.getDataNaixement());
        usuariMap.put(HM_USR_DATAI, usuari.getDataInscripcio());
        usuariMap.put(HM_USR_TELEFON, usuari.getTelefon());
        usuariMap.put(HM_INS_TIPUS, String.valueOf(usuari.getTipus()));

        return usuariMap;
    }

    /**
     * Aquest metode converteix un hashMap a un objecte tipu usuari
     *
     * @param map Objecte hashMap que es vol convertir en usuari
     * @return usuari Objecte usuari obtingut a partir de les dades del hashMap
     */
    public Usuari map_to_usuari(HashMap<String, String> map) {
        Usuari usuari = new Usuari("", "");
        usuari.setSessioID(Integer.parseInt(map.get(HM_USR_ID)));
        usuari.setCorreu(map.get(HM_USR_CORREU));
        usuari.setNom(map.get(HM_USR_NOM));
        usuari.setCognoms(map.get(HM_USR_COGNOMS));
        usuari.setAdreca(map.get(HM_USR_ADRECA));
        usuari.setDataNaixement(map.get(HM_USR_DATAN));
        usuari.setDataInscripcio(map.get(HM_USR_DATAI));
        usuari.setTelefon(map.get(HM_USR_TELEFON));
        usuari.setTipus(Integer.parseInt(map.get(HM_INS_TIPUS)));

        return usuari;
    }

    /**
     * Aquest metode converteix una lista de usuaris a un array de HashMap
     *
     * @param llistaUsuari Llista d'usuaris que es vol convertir
     * @return mapList Llista d'usuaris convertida a mapa
     */
    public ArrayList<HashMap<String, String>> creaLlistaUsuarisMap(Usuari[] llistaUsuari) {
        ArrayList<HashMap<String, String>> mapList = new ArrayList<>();
        int index = 0;
        for (Usuari usr : llistaUsuari) {
            if (usr != null) {
                mapList.add(usuari_to_map(usr));
            }
        }
        return mapList;
    }

    /**
     * Aquest metode llegeix un HashMap i el converteix a llista d'usuaris
     *
     * @param mapList ArrayList que conté la llista d'usuaris
     * @return llistaUsuari Llista d'usuaris extreta del HashMap
     */
    public Usuari[] crearLlistaUsuaris(ArrayList<HashMap<String, String>> mapList) {
        Usuari[] llistraUsuari = new Usuari[mapList.size()];
        int index = 0;
        for (int i = 0; i < mapList.size(); i++) {
            if (mapList.get(i) != null) llistraUsuari[i] = map_to_usuari(mapList.get(i));
        }

        return llistraUsuari;
    }


    /**
     * Obté el nom de l'usuari.
     *
     * @return El nom de l'usuari.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Estableix el nom de l'usuari.
     *
     * @param nom El nom de l'usuari.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Obté els cognoms de l'usuari.
     *
     * @return Els cognoms de l'usuari.
     */
    public String getCognoms() {
        return cognoms;
    }

    /**
     * Estableix els cognoms de l'usuari.
     *
     * @param cognoms Els cognoms de l'usuari.
     */
    public void setCognoms(String cognoms) {
        this.cognoms = cognoms;
    }

    /**
     * Obté la data de naixement de l'usuari.
     *
     * @return La data de naixement de l'usuari.
     */
    public String getDataNaixement() {
        return dataNaixement;
    }

    /**
     * Estableix la data de naixement de l'usuari.
     *
     * @param dataNaixement La data de naixement de l'usuari.
     */
    public void setDataNaixement(String dataNaixement) {
        this.dataNaixement = dataNaixement;
    }

    /**
     * Obté l'adreça de l'usuari.
     *
     * @return L'adreça de l'usuari.
     */
    public String getAdreca() {
        return adreca;
    }

    /**
     * Estableix l'adreça de l'usuari.
     *
     * @param adreca L'adreça de l'usuari.
     */
    public void setAdreca(String adreca) {
        this.adreca = adreca;
    }

    /**
     * Obté el número de telèfon de l'usuari.
     *
     * @return El número de telèfon de l'usuari.
     */
    public String getTelefon() {
        return telefon;
    }

    /**
     * Estableix el número de telèfon de l'usuari.
     *
     * @param telefon El número de telèfon de l'usuari.
     */
    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    /**
     * Obté el correu electrònic de l'usuari.
     *
     * @return El correu electrònic de l'usuari.
     */
    public String getCorreu() {
        return correu;
    }

    /**
     * Estableix el correu electrònic de l'usuari.
     *
     * @param correu El correu electrònic de l'usuari.
     */
    public void setCorreu(String correu) {
        this.correu = correu;
    }

    /**
     * Obté la contrasenya de l'usuari.
     *
     * @return La contrasenya de l'usuari.
     */
    public String getContrasenya() {
        return contrasenya;
    }

    /**
     * Estableix la contrasenya de l'usuari.
     *
     * @param contrasenya La contrasenya de l'usuari.
     */
    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    /**
     * Obté la data d'inscripció de l'usuari.
     *
     * @return La data d'inscripció de l'usuari.
     */
    public String getDataInscripcio() {
        return dataInscripcio;
    }

    /**
     * Estableix la data d'inscripció de l'usuari.
     *
     * @param dataInscripcio La data d'inscripció de l'usuari.
     */
    public void setDataInscripcio(String dataInscripcio) {
        this.dataInscripcio = dataInscripcio;
    }

    public int getSessioID() {
        return sessioID;
    }

    public void setSessioID(int sessioID) {
        this.sessioID = sessioID;
    }

    public int getTipus() {
        return tipus;
    }

    public void setTipus(int tipus) {
        this.tipus = tipus;
    }
}
