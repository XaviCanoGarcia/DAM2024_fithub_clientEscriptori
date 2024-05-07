package fithub.clientEscriptori.dades.objectes;

import java.util.ArrayList;
import java.util.HashMap;

import static fithub.clientEscriptori.dades.Constants.*;

/**
 * Classe que representa un servei en l'aplicació FitHub.
 *
 * @author Xavi Cano
 * @version 1.1
 */
public class Servei {

    private int id;
    private String nom;
    private String descripcio;
    private String preu;

    public Servei(String nom, String preu) {
        this.id = -1;
        this.nom = nom;
        this.descripcio = "";
        this.preu = preu;
    }

    /**
     * Aquest metode converteix un objecte tipu servei a un hashMap
     *
     * @param servei Objecte servei que es vol convertir
     * @return serveiMap HashMap que conti les dades de l'objecte servei
     */
    public HashMap<String, String> servei_to_map(Servei servei) {
        HashMap<String, String> serveiMap = new HashMap<>();

        serveiMap.put(HM_SRV_NOM, servei.getNom());
        serveiMap.put(HM_SRV_DESC, servei.getDescripcio());
        serveiMap.put(HM_SRV_ID, String.valueOf(servei.getId()));
        serveiMap.put(HM_SRV_PREU, servei.getPreu());

        return serveiMap;
    }

    /**
     * Aquest metode converteix un hashMap a un objecte tipu servei
     *
     * @param map Objecte hashMap que es vol convertir en servei
     * @return servei Objecte servei obtingut a partir de les dades del hashMap
     */
    public Servei map_to_servei(HashMap<String, String> map) {
        Servei servei = new Servei("", "");
        servei.setNom(map.get(HM_SRV_NOM));
        servei.setDescripcio(map.get(HM_SRV_DESC));
        servei.setId(Integer.parseInt(map.get(HM_SRV_ID)));
        servei.setPreu(map.get(HM_SRV_PREU));

        return servei;
    }

    /**
     * Aquest metode converteix una lista de serveis a un array de HashMap
     *
     * @param llistaservei Llista d'serveis que es vol convertir
     * @return mapList Llista d'serveis convertida a mapa
     */
    public ArrayList<HashMap<String, String>> creaLlistaserveisMap(Servei[] llistaservei) {
        ArrayList<HashMap<String, String>> mapList = new ArrayList<HashMap<String, String>>();
        int index = 0;
        for (Servei usr : llistaservei) {
            if (usr != null) {
                mapList.add(servei_to_map(usr));
            }
        }
        return mapList;
    }

    /**
     * Aquest metode llegeix un HashMap i el converteix a llista d'serveis
     *
     * @param mapList ArrayList que conté la llista d'serveis
     * @return llistaservei Llista d'serveis extreta del HashMap
     */
    public Servei[] crearLlistaServeis(ArrayList<HashMap<String, String>> mapList) {
        Servei[] llistraServei = new Servei[mapList.size()];
        int index = 0;
        for (int i = 0; i < mapList.size(); i++) {
            if (mapList.get(i) != null) llistraServei[i] = map_to_servei(mapList.get(i));
        }

        return llistraServei;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public String getPreu() {
        return preu;
    }

    public void setPreu(String preu) {
        this.preu = preu;
    }
}
