package fithub.clientEscriptori.dades.objectes;

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
