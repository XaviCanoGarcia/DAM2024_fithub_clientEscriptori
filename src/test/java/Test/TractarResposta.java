package Test;

import fithub.clientEscriptori.dades.ControladorDades;
import fithub.clientEscriptori.dades.objectes.*;
import fithub.clientEscriptori.gui.ControladorGui;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static fithub.clientEscriptori.dades.Constants.*;

public class TractarResposta {

    //Respostes incorrectes
    @Test
    public void resposta_null() {
        Object[] resposta = null;
        ControladorDades controladorDades = new ControladorDades(new ControladorGui());
        Object[] rsp = controladorDades.tractarResposta(resposta);
        boolean status = true;
        if (rsp != null) status = false;
        assert status;
    }

    @Test
    public void resposta_mida_incorrecte() {
        Object[] resposta = {(""), (""), ("")};
        ControladorDades controladorDades = new ControladorDades(new ControladorGui());
        Object[] rsp = controladorDades.tractarResposta(resposta);
        boolean status = true;
        if (rsp != null) status = false;
        assert status;
    }

    //Respostes correctes
    @Test
    public void resposta_usuari_login() {
        Usuari usuariAdmin = new Usuari("Xavi", "Cano Garcia", "03/04/1997", "C/Llorach 18", "978056784", "xcano@gmail.com", "pass", "05/09/2020");
        usuariAdmin.setUsuariID(1);
        usuariAdmin.setTipus(1);
        HashMap<String, String> usuariMap = usuariAdmin.usuari_to_map(usuariAdmin);
        Object[] resposta = {("2024,1"), (usuariMap)};
        usuariAdmin.setContrasenya("");
        ControladorDades controladorDades = new ControladorDades(new ControladorGui());
        Object[] rsp = controladorDades.tractarResposta(resposta);
        boolean status = true;
        if (!(rsp[0].equals("2024,1"))) status = false;
        if (!(rsp[1] instanceof Usuari)) status = false;
        assert status;
    }

    //Resposta en format hashmap, conversio a objectes de dades
    @Test
    public void map_a_usuari() {
        Usuari usuariAdmin = new Usuari("Xavi", "Cano Garcia", "03/04/1997", "C/Llorach 18", "978056784", "xcano@gmail.com", "pass", "05/09/2020");
        usuariAdmin.setUsuariID(1);
        usuariAdmin.setTipus(1);
        HashMap<String, String> usuariMap = usuariAdmin.usuari_to_map(usuariAdmin);
        Object[] resposta = {("usuari"), (usuariMap)};
        ControladorDades controladorDades = new ControladorDades(new ControladorGui());
        Object[] rsp = controladorDades.tractarResposta(resposta);
        boolean status = true;
        if (!(rsp[1] instanceof Usuari)) status = false;
        assert status;
    }

    @Test
    public void map_a_activitat() {
        Activitat activitat = new Activitat("tennis", "tennisdesc", 2);
        HashMap<String, String> activitatMap = activitat.activitat_to_map(activitat);
        Object[] resposta = {("activitat"), (activitatMap)};
        ControladorDades controladorDades = new ControladorDades(new ControladorGui());
        Object[] rsp = controladorDades.tractarResposta(resposta);
        boolean status = true;
        if (!(rsp[1] instanceof Activitat)) status = false;
        assert status;
    }

    @Test
    public void map_a_installacio() {
        Installacio installacio1 = new Installacio("Pista tennis", "Pista de tennis descoberta", "exterior");
        HashMap<String, String> installacioMap = installacio1.installacio_to_map(installacio1);
        Object[] resposta = {("installacio"), (installacioMap)};
        ControladorDades controladorDades = new ControladorDades(new ControladorGui());
        Object[] rsp = controladorDades.tractarResposta(resposta);
        boolean status = true;
        if (!(rsp[1] instanceof Installacio)) status = false;
        assert status;
    }

    @Test
    public void map_a_servei() {
        Servei servei = new Servei("servei1", "15€");
        HashMap<String, String> serveiMap = servei.servei_to_map(servei);
        Object[] resposta = {("servei"), (serveiMap)};
        ControladorDades controladorDades = new ControladorDades(new ControladorGui());
        Object[] rsp = controladorDades.tractarResposta(resposta);
        boolean status = true;
        if (!(rsp[1] instanceof Servei)) status = false;
        assert status;
    }

    @Test
    public void map_a_classeDirigida() {
        Installacio installacio1 = new Installacio("Pista tennis", "Pista de tennis descoberta", "exterior");
        Activitat activitat = new Activitat("tennis", "tennisdesc", 2);
        activitat.setId(1);
        activitat.setTipusActivitat(1);
        ClasseDirigida cd = new ClasseDirigida("03042020", "0900", "1", activitat, installacio1);
        HashMap<String, String> classeDirigidaMap = cd.classeDirigida_a_hashMap(cd);
        Object[] resposta = {("classeDirigida"), (classeDirigidaMap)};
        ControladorDades controladorDades = new ControladorDades(new ControladorGui());
        Object[] rsp = controladorDades.tractarResposta(resposta);
        boolean status = true;
        if (!(rsp[1] instanceof ClasseDirigida)) status = false;
        assert status;
    }

    @Test
    public void arraymap_a_llistainstallacio() {
        Installacio[] llistaInstallacio = new Installacio[4];
        Installacio installacio1 = new Installacio("Pista tennis", "Pista de tennis descoberta", "exterior");
        Installacio installacio2 = new Installacio("Piscina", "Piscina coberta", "interior");
        llistaInstallacio[0] = installacio1;
        llistaInstallacio[1] = installacio2;
        Object[] resposta = {(INSTALLACIO_LLISTA), (installacio1.creaLlistaInstallacioMap(llistaInstallacio))};
        ControladorDades controladorDades = new ControladorDades(new ControladorGui());
        Object[] rsp = controladorDades.tractarResposta(resposta);
        boolean status = true;
        if (!(rsp[1] instanceof Installacio[])) status = false;
        assert status;
    }

    @Test
    public void arraymap_a_llistaactivitat() {
        Activitat[] llistaActivitat = new Activitat[4];
        Activitat activitat = new Activitat("tennis", "tennisdesc", 2);
        Activitat activitat2 = new Activitat("basquet", "bascdesc", 10);
        Activitat activitat3 = new Activitat("futbol", "futsdesc", 22);
        activitat.setTipusActivitat(1);
        activitat3.setTipusActivitat(1);
        activitat2.setTipusActivitat(1);
        llistaActivitat[0] = activitat;
        llistaActivitat[1] = activitat2;
        llistaActivitat[2] = activitat3;

        Object[] resposta = {(ACTIVITAT_LLISTA), (activitat.creaLlistaactivitatsMap(llistaActivitat))};
        ControladorDades controladorDades = new ControladorDades(new ControladorGui());
        Object[] rsp = controladorDades.tractarResposta(resposta);
        boolean status = true;
        if (!(rsp[1] instanceof Activitat[])) status = false;
        assert status;
    }

    @Test
    public void arraymap_a_llistaservei() {
        Servei srv = new Servei("Monitor", "15€");
        srv.setDescripcio("Monitor per a les classes de gimnas");
        Servei srv2 = new Servei("Dietista", "25€");
        srv2.setDescripcio("Dietista personal");
        Servei srv3 = new Servei("Entrenador", "20€");
        srv3.setDescripcio("Entrenador personal");
        Servei[] llistaServeis = new Servei[4];
        llistaServeis[0] = srv;
        llistaServeis[1] = srv2;
        llistaServeis[2] = srv3;

        Object[] resposta = {(SERVEI_LLISTA), srv.creaLlistaserveisMap(llistaServeis)};
        ControladorDades controladorDades = new ControladorDades(new ControladorGui());
        Object[] rsp = controladorDades.tractarResposta(resposta);
        boolean status = true;
        if (!(rsp[1] instanceof Servei[])) status = false;
        assert status;
    }

    @Test
    public void arraymap_a_llistaclasseDirigida() {
        Installacio installacio3 = new Installacio("Pista basquet", "Pista basquet", "interior");
        installacio3.setId(3);
        Installacio installacio1 = new Installacio("Pista tennis", "Pista de tennis descoberta", "exterior");
        installacio1.setId(1);
        Activitat activitat = new Activitat("tennis", "tennisdesc", 2);
        activitat.setId(1);
        Activitat activitat2 = new Activitat("basquet", "bascdesc", 10);
        activitat2.setId(2);
        ClasseDirigida cd = new ClasseDirigida("28042024", "0900", "1", activitat, installacio1);
        cd.setId(1);
        cd.setOcupacio("1");
        ClasseDirigida cd2 = new ClasseDirigida("28042024", "1000", "2", activitat2, installacio3);
        cd2.setId(2);
        cd2.setOcupacio("2");
        ClasseDirigida[] llistaClasseDirigida = new ClasseDirigida[4];
        llistaClasseDirigida[0] = cd;
        llistaClasseDirigida[1] = cd2;

        Object[] resposta = {(CLASSE_DIRIGIDA_LLISTA), (cd.creaLlistaClasseDirigidaMap(llistaClasseDirigida))};
        ControladorDades controladorDades = new ControladorDades(new ControladorGui());
        Object[] rsp = controladorDades.tractarResposta(resposta);
        boolean status = true;
        if (!(rsp[1] instanceof ClasseDirigida[])) status = false;
        assert status;
    }

    @Test
    public void arraymap_a_llistausuari() {
        Usuari usuari1 = new Usuari("Josep", "Lopez", "03/04/1997", "C/Terssol 18", "978056784", "josepLopez@gmail.com", "pass", "05/09/2020");
        usuari1.setUsuariID(2);
        Usuari usuari2 = new Usuari("Maria", "Bonet", "13/12/2000", "C/Major 12", "97800987", "MariaBonet@gmail.com", "pass", "15/07/2020");
        Usuari usuari3 = new Usuari("Albert", "Guspi", "18/02/1993", "C/Vell 1", "979807654", "AlbertGuspi@gmail.com", "pass", "14/02/2019");
        Usuari[] llistaUsuari = new Usuari[10];
        llistaUsuari[0] = usuari1;
        llistaUsuari[1] = usuari2;
        llistaUsuari[2] = usuari3;

        Object[] resposta = {(USUARI_LLISTA), (usuari1.creaLlistaUsuarisMap(llistaUsuari))};
        ControladorDades controladorDades = new ControladorDades(new ControladorGui());
        Object[] rsp = controladorDades.tractarResposta(resposta);
        boolean status = true;
        if (!(rsp[1] instanceof Usuari[])) status = false;
        assert status;
    }

}
