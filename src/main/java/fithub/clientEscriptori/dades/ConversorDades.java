package fithub.clientEscriptori.dades;

import java.util.HashMap;

/**
 * Aquesta classe conté els mètodes necessaris per poder convertir i llegir les dades a objectes HashMap
 *
 * @author Xavi Cano
 * @version 1.0
 */

public class ConversorDades {

    /**
     * Aquest metode converteix un objecte tipu usuari a un hashMap
     *
     * @param usuari Objecte usuari que es vol convertir
     * @return usuariMap HashMap que conti les dades de l'objecte usuari
     */
    public HashMap<String, String> usuari_to_map(Usuari usuari) {
        HashMap<String, String> usuariMap = new HashMap<>();
        usuariMap.put("objectType", "usuari");
        usuariMap.put("sessioID", String.valueOf(usuari.getSessioID()));
        usuariMap.put("correu", usuari.getCorreu());
        usuariMap.put("nom", usuari.getNom());
        usuariMap.put("cognoms", usuari.getCognoms());
        usuariMap.put("adreca", usuari.getAdreca());
        usuariMap.put("contrasenya", usuari.getContrasenya());
        usuariMap.put("dataNeixament", usuari.getDataNaixement());
        usuariMap.put("dataInscripcio", usuari.getDataInscripcio());
        usuariMap.put("telefon", usuari.getTelefon());
        usuariMap.put("tipusUsuari", usuari.getTipus());

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
        usuari.setSessioID(Integer.parseInt(map.get("sessioID")));
        usuari.setCorreu(map.get("correu"));
        usuari.setNom(map.get("nom"));
        usuari.setCognoms(map.get("cognoms"));
        usuari.setAdreca(map.get("adreca"));
        usuari.setContrasenya(map.get("contrasenya"));
        usuari.setDataNaixement(map.get("dataNeixament"));
        usuari.setDataInscripcio(map.get("dataInscripcio"));
        usuari.setTelefon(map.get("telefon"));
        usuari.setTipus(map.get("tipusUsuari"));

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

}
