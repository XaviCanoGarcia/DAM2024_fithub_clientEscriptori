package fithub.clientEscriptori.dades.objectes;

import java.io.Serializable;
import java.util.HashMap;

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
        usuariMap.put("objectType", "usuari");
        usuariMap.put("usuariID", String.valueOf(usuari.getSessioID()));
        usuariMap.put("correuUsuari", usuari.getCorreu());
        usuariMap.put("nomUsuari", usuari.getNom());
        usuariMap.put("cognomsUsuari", usuari.getCognoms());
        usuariMap.put("adreca", usuari.getAdreca());
        usuariMap.put("passUsuari", usuari.getContrasenya());
        usuariMap.put("dataNaixement", usuari.getDataNaixement());
        usuariMap.put("dataInscripcio", usuari.getDataInscripcio());
        usuariMap.put("telefon", usuari.getTelefon());
        usuariMap.put("tipusUsuari", String.valueOf(usuari.getTipus()));

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
        usuari.setSessioID(Integer.parseInt(map.get("usuariID")));
        usuari.setCorreu(map.get("correuUsuari"));
        usuari.setNom(map.get("nomUsuari"));
        usuari.setCognoms(map.get("cognomsUsuari"));
        usuari.setAdreca(map.get("adreca"));
        usuari.setContrasenya(map.get("passUsuari"));
        usuari.setDataNaixement(map.get("dataNaixement"));
        usuari.setDataInscripcio(map.get("dataInscripcio"));
        usuari.setTelefon(map.get("telefon"));
        usuari.setTipus(Integer.parseInt(map.get("tipusUsuari")));

        return usuari;
    }

    /**
     * Aquest metode converteix una lista de usuaris a un array de HashMap
     *
     * @param llistaUsuari Llista d'usuaris que es vol convertir
     * @return mapList Llista d'usuaris convertida a mapa
     */
    public HashMap<String, String>[] creaLlistaUsuarisMap(Usuari[] llistaUsuari) {
        HashMap<String, String>[] mapList = new HashMap[llistaUsuari.length];
        int index = 0;
        for (Usuari usr : llistaUsuari) {
            if (usr != null) {
                mapList[index++] = (usuari_to_map(usr));
            }
        }
        return mapList;
    }

    /**
     * Aquest metode llegeix un HashMap i el converteix a llista d'usuaris
     *
     * @param map HashMap que conté la llista d'usuaris
     * @return llistaUsuari Llista d'usuaris extreta del HashMap
     */
    public Usuari[] crearLlistaUsuaris(HashMap<String, String>[] map) {
        Usuari[] llistraUsuari = new Usuari[map.length];
        int index = 0;
        for (int i = 0; i < map.length; i++) {
            if (map[i] != null) llistraUsuari[i] = map_to_usuari(map[i]);
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
