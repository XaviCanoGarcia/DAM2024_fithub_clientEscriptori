package fithub.clientEscriptori.dades.objectes;

import java.util.ArrayList;
import java.util.HashMap;

import static fithub.clientEscriptori.dades.Constants.*;

/**
 * Classe que representa una classe dirigida en l'aplicació FitHub.
 *
 * @author Xavi Cano
 * @version 1.1
 */
public class ClasseDirigida {

    private int id;
    private String data;
    private String horaInici;
    private String duracio;
    private String ocupacio;
    private Activitat activitat;
    private Installacio installacio;

    /**
     * Classe que representa una classe dirigida amb una data i hora concretes.
     * <p>
     * Conte la informacio de la data, hora, l'activitat i la instal·lació on es realitzarà.
     *
     * @author Xavi Cano
     * @version 1.0
     */
    public ClasseDirigida(String data, String horaInici, String duracio, Activitat activitat, Installacio installacio) {
        this.id = -1;
        this.data = data;
        this.horaInici = horaInici;
        this.duracio = duracio;
        this.ocupacio = "0/0";
        this.activitat = activitat;
        this.installacio = installacio;
    }

    /**
     * Aquest mètode transforma un objecte classe dirigida en un hashMap
     *
     * @param classeDirigida Objecte de classe dirigida que es vol convertir
     * @return classeDirigidaMap Hashmap amb la informacio de la classe dirigida.
     */
    public HashMap<String, String> classeDirigida_a_hashMap(ClasseDirigida classeDirigida) {
        HashMap<String, String> classeDirigidaMap = new HashMap<>();
        Activitat act = new Activitat("", "", 0);
        HashMap<String, String> activitatMap = new HashMap<>();
        Installacio ins = new Installacio("", "", "");
        HashMap<String, String> installacioMap = new HashMap<>();
        activitatMap = act.activitat_to_map(classeDirigida.getActivitat());
        classeDirigidaMap.putAll(activitatMap);
        installacioMap = ins.installacio_to_map(classeDirigida.getInstallacio());
        classeDirigidaMap.putAll(installacioMap);
        classeDirigidaMap.put(HM_CDI_ID, String.valueOf(classeDirigida.getId()));
        classeDirigidaMap.put(HM_CDI_DATA, classeDirigida.getData());
        classeDirigidaMap.put(HM_CDI_HORA, classeDirigida.getHoraInici().replace(":", ""));
        classeDirigidaMap.put(HM_CDI_DURACIO, classeDirigida.getDuracio());
        classeDirigidaMap.put(HM_CDI_OCUPACIO, classeDirigida.getOcupacio());

        return classeDirigidaMap;
    }

    /**
     * Aquest mètode transforma un hashmap a un objecte classe dirigida
     *
     * @param map Hashmap que conté la informacio d'un objecte classe dirigida.
     * @return classeDirigida Objecte classe dirigida extret del hashMap.
     */
    public ClasseDirigida map_a_classeDirigida(HashMap<String, String> map) {
        Activitat act = new Activitat("", "", 0);
        Installacio ins = new Installacio("", "", "");
        ClasseDirigida classeDirigida = new ClasseDirigida("12052024", "9:00", "1", act, ins);
        classeDirigida.setActivitat(act.map_to_activitat(map));
        classeDirigida.setInstallacio(ins.map_to_installacio(map));
        classeDirigida.setId(Integer.valueOf(map.get(HM_CDI_ID)));
        classeDirigida.setData(map.get(HM_CDI_DATA));
        classeDirigida.setHoraInici(map.get(HM_CDI_HORA));
        classeDirigida.setDuracio(map.get(HM_CDI_DURACIO));
        classeDirigida.setOcupacio(map.get(HM_CDI_OCUPACIO));

        return classeDirigida;
    }

    /**
     * Aquest metode converteix una lista de classes dirigides a un array de HashMap
     *
     * @param llistaClasseDirigida Llista d'usuaris que es vol convertir
     * @return mapList Llista de classes dirigides convertida a mapa
     */
    public ArrayList<HashMap<String, String>> creaLlistaClasseDirigidaMap(ClasseDirigida[] llistaClasseDirigida) {
        ArrayList<HashMap<String, String>> mapList = new ArrayList<>();
        int index = 0;
        for (ClasseDirigida cd : llistaClasseDirigida) {
            if (cd != null) {
                mapList.add(classeDirigida_a_hashMap(cd));
            }
        }
        return mapList;
    }


    /**
     * Aquest metode llegeix un HashMap i el converteix a una llista de classes dirigides
     *
     * @param mapList ArrayList que conté la llista de classes dirigides
     * @return llistaClasseDirigida llista de classes dirigides extretes del hashmap
     */
    public ClasseDirigida[] crearLlistaClasseDirigida(ArrayList<HashMap<String, String>> mapList) {
        ClasseDirigida[] llistaClasseDirigida = new ClasseDirigida[mapList.size()];
        int index = 0;
        for (int i = 0; i < mapList.size(); i++) {
            if (mapList.get(i) != null) llistaClasseDirigida[i] = map_a_classeDirigida(mapList.get(i));
        }

        return llistaClasseDirigida;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHoraInici() {
        String horaRaw = horaInici;
        String horaFormat = "";
        if (!horaRaw.equals("")) horaFormat = horaRaw.substring(0, 2) + ":" + horaRaw.substring(2);
        return horaFormat;
    }

    public void setHoraInici(String horaInici) {
        this.horaInici = horaInici.replace(":", "");
    }

    public String getDuracio() {
        return duracio;
    }

    public void setDuracio(String duracio) {
        this.duracio = duracio;
    }

    public String getOcupacio() {
        return ocupacio;
    }

    public void setOcupacio(String ocupacio) {
        this.ocupacio = ocupacio;
    }

    public Activitat getActivitat() {
        return activitat;
    }

    public void setActivitat(Activitat activitat) {
        this.activitat = activitat;
    }

    public Installacio getInstallacio() {
        return installacio;
    }

    public void setInstallacio(Installacio installacio) {
        this.installacio = installacio;
    }
}
