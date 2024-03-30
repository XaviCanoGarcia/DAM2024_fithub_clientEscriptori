package fithub.clientEscriptori.app;

import java.io.Serializable;

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
    private String tipus;

    //Dades opcionals
    private String nom;
    private String cognoms;
    private String dataNaixement;
    private String adreca;
    private String telefon;
    private String dataInscripcio;

    /**
     * Constructor de la classe Usuari reduit.
     *
     * @param correu      El correu electrònic de l'usuari.
     * @param contrasenya La contrasenya de l'usuari.
     */
    public Usuari(String correu, String contrasenya) {
        this.correu = correu;
        this.contrasenya = contrasenya;
        this.sessioID = -1;

        this.tipus = DEFAULT_VALUE;
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
        this.tipus = DEFAULT_VALUE;
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

    public String getTipus() {
        return tipus;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }
}