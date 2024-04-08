package fithub.clientEscriptori.dades.objectes;


import java.util.HashMap;

/**
 * Classe que representa l'objecte Activitat
 *
 * @author Antonio
 * @version 1.0
 */
public class Activitat {
    private int id;
    private String nom;
    private String descripcio;
    private int aforament;

    /**
     * Constructor de la classe Activitat.
     *
     * @param nom        Nom de l'activitat
     * @param descripcio Descripció de l'activitat
     * @param aforament  Aforament de l'activitat
     */
    public Activitat(String nom, String descripcio, int aforament) {
        this.id = -1;
        this.nom = nom;
        this.descripcio = descripcio;
        this.aforament = aforament;
    }


    /**
     * Aquest metode converteix un objecte tipu activitat a un hashMap
     *
     * @param activitat Objecte activitat que es vol convertir
     * @return activitatMap HashMap que conti les dades de l'objecte activitat
     */
    public HashMap<String, String> activitat_to_map(Activitat activitat) {
        HashMap<String, String> activitatMap = new HashMap<>();
        activitatMap.put("id", String.valueOf(activitat.getId()));
        activitatMap.put("nom", activitat.getNom());
        activitatMap.put("descripcio", activitat.getDescripcio());
        activitatMap.put("aforament", String.valueOf(activitat.getAforament()));

        return activitatMap;
    }

    /**
     * Aquest metode converteix un hashMap a un objecte tipu activitat
     *
     * @param map Objecte hashMap que es vol convertir en activitat
     * @return activitat Objecte activitat obtingut a partir de les dades del hashMap
     */
    public Activitat map_to_activitat(HashMap<String, String> map) {
        Activitat activitat = new Activitat("", "", 0);
        activitat.setNom(map.get("nom"));
        activitat.setId(Integer.parseInt(map.get("id")));
        activitat.setAforament(Integer.parseInt(map.get("aforament")));
        activitat.setDescripcio(map.get("descripcio"));

        return activitat;
    }

    /**
     * Aquest metode converteix una lista de activitats a un array de HashMap
     *
     * @param llistaactivitat Llista d'activitats que es vol convertir
     * @return mapList Llista d'activitats convertida a mapa
     */
    public HashMap<String, String>[] creaLlistaactivitatsMap(Activitat[] llistaactivitat) {
        HashMap<String, String>[] mapList = new HashMap[llistaactivitat.length];
        int index = 0;
        for (Activitat usr : llistaactivitat) {
            if (usr != null) {
                mapList[index++] = (activitat_to_map(usr));
            }
        }
        return mapList;
    }

    /**
     * Aquest metode llegeix un HashMap i el converteix a llista d'activitats
     *
     * @param map HashMap que conté la llista d'activitats
     * @return llistaactivitat Llista d'activitats extreta del HashMap
     */
    public Activitat[] crearLlistaActivitats(HashMap<String, String>[] map) {
        Activitat[] llistraActivitat = new Activitat[map.length];
        int index = 0;
        for (int i = 0; i < map.length; i++) {
            if (map[i] != null) llistraActivitat[i] = map_to_activitat(map[i]);
        }

        return llistraActivitat;
    }


// Getters i setters

    /**
     * Obté el nom de l'activitat.
     *
     * @return El nom de l'activitat
     */
    public String getNom() {
        return nom;
    }

    /**
     * Estableix el nom de l'activitat.
     *
     * @param nom El nou nom de l'activitat
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Obté la descripció de l'activitat.
     *
     * @return La descripció de l'activitat
     */
    public String getDescripcio() {
        return descripcio;
    }

    /**
     * Estableix la descripció de l'activitat.
     *
     * @param descripcio La nova descripció de l'activitat
     */
    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    /**
     * Obté l'aforament de l'activitat.
     *
     * @return L'aforament de l'activitat
     */
    public int getAforament() {
        return aforament;
    }

    /**
     * Estableix l'aforament de l'activitat.
     *
     * @param aforament El nou aforament de l'activitat
     */
    public void setAforament(int aforament) {
        this.aforament = aforament;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}



