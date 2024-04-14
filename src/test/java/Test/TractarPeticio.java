package Test;

import fithub.clientEscriptori.dades.ControladorDades;
import fithub.clientEscriptori.dades.objectes.Usuari;
import fithub.clientEscriptori.gui.ControladorGui;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static fithub.clientEscriptori.dades.Constants.*;


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
    public void login_format_pass_incorrecte_Majus() {
        Object[] peticio = {("login"), ("admin@fithub.es"), ("adminpass37")};
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


}
