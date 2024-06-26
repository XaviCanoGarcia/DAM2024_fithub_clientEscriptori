package Test;

import fithub.clientEscriptori.dades.ControladorDades;
import fithub.clientEscriptori.dades.objectes.*;
import fithub.clientEscriptori.gui.ControladorGui;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static fithub.clientEscriptori.dades.Constants.*;

/**
 * Test per comprobar el mètode tractar petició de la classe Controlador de dades
 */
public class TractarPeticio {

    //LOGIN
    @Test
    public void login_correcte() {
        Object[] peticio = {("login"), ("admin@fithub.es"), ("AdminPass37")};
        ControladorDades controladorDades = new ControladorDades(new ControladorGui());
        Object[] rsp = controladorDades.tractarPeticio(peticio);
        boolean status = true;
        if (!rsp[0].equals(peticio[0])) status = false;
        if (!rsp[1].equals(peticio[1])) status = false;
        if (!rsp[2].equals(peticio[2])) status = false;
        assert status;
    }

    @Test
    public void login_format_pass_incorrecte_numero() {
        Object[] peticio = {("login"), ("admin@fithub.es"), ("AdminPass")};
        ControladorDades controladorDades = new ControladorDades(new ControladorGui());
        Object[] rsp = controladorDades.tractarPeticio(peticio);
        boolean status = true;
        if (!(rsp == null)) status = false;
        assert status;
    }

    @Test
    public void login_format_correu_incorrecte_arroba() {
        Object[] peticio = {("login"), ("adminfithub.es"), ("AdminPass37")};
        ControladorDades controladorDades = new ControladorDades(new ControladorGui());
        Object[] rsp = controladorDades.tractarPeticio(peticio);
        boolean status = true;
        if (!(rsp == null)) status = false;
        assert status;
    }

    @Test
    public void login_format_correu_incorrecte_punt() {
        Object[] peticio = {("login"), ("admin@fithubes"), ("AdminPass37")};
        ControladorDades controladorDades = new ControladorDades(new ControladorGui());
        Object[] rsp = controladorDades.tractarPeticio(peticio);
        boolean status = true;
        if (!(rsp == null)) status = false;
        assert status;
    }

    //PETICIO
    @Test
    public void llargada_objecte_peticio_incorrrecte() {
        Object[] peticio = {("login"), ("admin@fithub.es"), ("AdminPass37"), ("")};
        ControladorDades controladorDades = new ControladorDades(new ControladorGui());
        Object[] rsp = controladorDades.tractarPeticio(peticio);
        boolean status = true;
        if (!(rsp == null)) status = false;
        assert status;
    }

    @Test
    public void peticio_amb_sessio_iniciada() {
        Object[] peticio = {("login"), ("admin@fithub.es"), ("AdminPass37")};
        ControladorDades controladorDades = new ControladorDades(new ControladorGui());
        controladorDades.getDades().setSessioID("2024,1");
        Object[] rsp = controladorDades.tractarPeticio(peticio);
        boolean status = true;
        if (!rsp[0].equals(peticio[0])) status = false;
        if (!rsp[1].equals(peticio[1])) status = false;
        if (!rsp[2].equals(peticio[2])) status = false;
        if (!rsp[3].equals("2024,1")) status = false;
        assert status;
    }

    @Test
    public void peticio_amb_sessio_no_iniciada() {
        Object[] peticio = {("login"), ("admin@fithub.es"), ("AdminPass37")};
        ControladorDades controladorDades = new ControladorDades(new ControladorGui());
        controladorDades.getDades().setSessioID("");
        Object[] rsp = controladorDades.tractarPeticio(peticio);
        boolean status = true;
        if (!rsp[0].equals(peticio[0])) status = false;
        if (!rsp[1].equals(peticio[1])) status = false;
        if (!rsp[2].equals(peticio[2])) status = false;
        if (!(rsp.length != 3)) status = false;
        assert status;
    }

    //HASHMAPS
    @Test
    public void usuari_a_hashmap() {
        Usuari usuariAdmin = new Usuari("Xavi", "Cano Garcia", "03/04/1997", "C/Llorach 18", "978056784", "xcano@gmail.com", "pass", "05/09/2020");
        usuariAdmin.setUsuariID(1);
        usuariAdmin.setTipus(1);
        HashMap<String, String> usuariMap = new HashMap<>();
        usuariMap.put(HM_USR_ID, String.valueOf(usuariAdmin.getUsuariID()));
        usuariMap.put(HM_USR_CORREU, usuariAdmin.getCorreu());
        usuariMap.put(HM_USR_NOM, usuariAdmin.getNom());
        usuariMap.put(HM_USR_COGNOMS, usuariAdmin.getCognoms());
        usuariMap.put(HM_USR_ADRECA, usuariAdmin.getAdreca());
        usuariMap.put(HM_USR_DATAN, usuariAdmin.getDataNaixement());
        usuariMap.put(HM_USR_DATAI, usuariAdmin.getDataInscripcio());
        usuariMap.put(HM_USR_TELEFON, usuariAdmin.getTelefon());
        usuariMap.put(HM_USR_TIPUS, String.valueOf(usuariAdmin.getTipus()));
        usuariMap.put(HM_USR_CONTRASENYA, "pass");
        Object[] peticio = {("insert"), ("usuari"), (usuariAdmin)};
        ControladorDades controladorDades = new ControladorDades(new ControladorGui());
        controladorDades.getDades().setSessioID("");
        Object[] rsp = controladorDades.tractarPeticio(peticio);
        HashMap<String, String> hs = (HashMap<String, String>) rsp[2];
        boolean status = usuariMap.size() == hs.size();
        if (status) {
            for (Map.Entry<String, String> entry : usuariMap.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (!hs.containsKey(key) || !hs.get(key).equals(value)) {
                    status = false;
                    break;
                }
            }
        }
        assert status;
    }

    @Test
    public void activitat_a_hashmap() {
        Activitat activitat = new Activitat("tennis", "tennisdesc", 2);
        activitat.setId(1);
        activitat.setTipusActivitat(1);
        HashMap<String, String> activitatMap = new HashMap<>();
        activitatMap.put(HM_ACT_TIPUS, String.valueOf(activitat.getTipusActivitat()));
        activitatMap.put(HM_ACT_NOM, activitat.getNom());
        activitatMap.put(HM_ACT_DESC, activitat.getDescripcio());
        activitatMap.put(HM_ACT_AFORAMENT, String.valueOf(activitat.getAforament()));
        activitatMap.put(HM_ACT_ID, String.valueOf(activitat.getId()));
        Object[] peticio = {("insert"), ("activitat"), (activitat)};
        ControladorDades controladorDades = new ControladorDades(new ControladorGui());
        controladorDades.getDades().setSessioID("");
        Object[] rsp = controladorDades.tractarPeticio(peticio);
        HashMap<String, String> hs = (HashMap<String, String>) rsp[2];
        boolean status = activitatMap.size() == hs.size();
        if (status) {
            for (Map.Entry<String, String> entry : activitatMap.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (!hs.containsKey(key) || !hs.get(key).equals(value)) {
                    status = false;
                    break;
                }
            }
        }
        assert status;
    }

    @Test
    public void installacio_a_hashmap() {
        Installacio installacio1 = new Installacio("Pista tennis", "Pista de tennis descoberta", "exterior");
        installacio1.setId(1);
        installacio1.setTipus("1");
        HashMap<String, String> installacioMap = new HashMap<>();
        installacioMap.put(HM_INS_TIPUS, installacio1.getTipus());
        installacioMap.put(HM_INS_NOM, installacio1.getNom());
        installacioMap.put(HM_INS_DESC, installacio1.getDescripcio());
        installacioMap.put(HM_INS_ID, String.valueOf(installacio1.getId()));
        Object[] peticio = {("insert"), ("installacio"), (installacio1)};
        ControladorDades controladorDades = new ControladorDades(new ControladorGui());
        controladorDades.getDades().setSessioID("");
        Object[] rsp = controladorDades.tractarPeticio(peticio);
        HashMap<String, String> hs = (HashMap<String, String>) rsp[2];
        boolean status = installacioMap.size() == hs.size();
        if (status) {
            for (Map.Entry<String, String> entry : installacioMap.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (!hs.containsKey(key) || !hs.get(key).equals(value)) {
                    status = false;
                    break;
                }
            }
        }
        assert status;
    }

    @Test
    public void servei_a_hashmap() {
        Servei srv1 = new Servei("nom", "15@");
        srv1.setDescripcio("desc");
        srv1.setId(10);
        HashMap<String, String> serveiMap = new HashMap<>();
        serveiMap.put(HM_SRV_ID, String.valueOf(srv1.getId()));
        serveiMap.put(HM_SRV_NOM, srv1.getNom());
        serveiMap.put(HM_SRV_DESC, srv1.getDescripcio());
        serveiMap.put(HM_SRV_PREU, srv1.getPreu());
        Object[] peticio = {("insert"), ("servei"), (srv1)};
        ControladorDades controladorDades = new ControladorDades(new ControladorGui());
        controladorDades.getDades().setSessioID("");
        Object[] rsp = controladorDades.tractarPeticio(peticio);
        HashMap<String, String> hs = (HashMap<String, String>) rsp[2];
        boolean status = serveiMap.size() == hs.size();
        if (status) {
            for (Map.Entry<String, String> entry : serveiMap.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (!hs.containsKey(key) || !hs.get(key).equals(value)) {
                    status = false;
                    break;
                }
            }
        }
        assert status;
    }

    @Test
    public void classeDirigida_a_hashmap() {
        Installacio installacio1 = new Installacio("Pista tennis", "Pista de tennis descoberta", "exterior");
        installacio1.setId(1);
        installacio1.setTipus("1");
        Activitat activitat1 = new Activitat("act1", "act1desc", 0);
        activitat1.setId(2);
        activitat1.setTipusActivitat(1);
        ClasseDirigida cd = new ClasseDirigida("01012020", "0900", "1", activitat1, installacio1);
        HashMap<String, String> classeDirigidaMap = new HashMap<>();
        HashMap<String, String> actMap = activitat1.activitat_to_map(activitat1);
        HashMap<String, String> insMap = installacio1.installacio_to_map(installacio1);

        classeDirigidaMap.put(HM_CDI_DATA, cd.getData());
        classeDirigidaMap.put(HM_CDI_ID, String.valueOf(cd.getId()));
        classeDirigidaMap.put(HM_CDI_HORA, "0900");
        classeDirigidaMap.put(HM_CDI_DURACIO, cd.getDuracio());
        classeDirigidaMap.put(HM_CDI_OCUPACIO, cd.getOcupacio());
        classeDirigidaMap.putAll(actMap);
        classeDirigidaMap.putAll(insMap);


        Object[] peticio = {("insert"), ("classeDirigida"), (cd)};
        ControladorDades controladorDades = new ControladorDades(new ControladorGui());
        controladorDades.getDades().setSessioID("");
        Object[] rsp = controladorDades.tractarPeticio(peticio);
        HashMap<String, String> hs = (HashMap<String, String>) rsp[2];
        boolean status = classeDirigidaMap.size() == hs.size();
        if (status) {
            for (Map.Entry<String, String> entry : classeDirigidaMap.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (!hs.containsKey(key) || !hs.get(key).equals(value)) {
                    status = false;
                    break;
                }
            }
        }
        assert status;
    }


}
