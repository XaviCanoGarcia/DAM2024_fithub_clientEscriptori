package fithub.clientEscriptori.dades;

import fithub.clientEscriptori.app.ParlarAmbServidor;
import fithub.clientEscriptori.dades.objectes.Activitat;
import fithub.clientEscriptori.dades.objectes.ClasseDirigida;
import fithub.clientEscriptori.dades.objectes.Installacio;
import fithub.clientEscriptori.dades.objectes.Usuari;
import fithub.clientEscriptori.gui.ControladorGui;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.HashMap;

import static fithub.clientEscriptori.dades.Constants.*;

/**
 * Aquesta és la classe l'arrel de tota l'estructura de dades de l'aplicació.
 * S'encarrega de fer peticions al servidor i gestionar les dades.
 *
 * @author Xavi Cano
 * @version 1.0
 */
public class ControladorDades {

    DadesAplicacio dades;

    public ControladorDades(ControladorGui controladorGui) {
        dades = new DadesAplicacio();
        dades.addObserver(controladorGui);
        dades.inicialitzaDades();
    }

    /**
     * Crea una peticio al sevidor, i actualitza el model de dades amb la resposta
     *
     * @param peticioUsr Peticio que l'usuari vol fer al servidor
     */
    public void crearPeticio(Object[] peticioUsr) {
        Object[] peticio;
        Object[] respostaRaw;
        Object[] resposta;

        peticio = tractarPeticio(peticioUsr);
        if(peticio==null)return;
        respostaRaw = ferPeticio(peticio);

        resposta = tractarResposta(respostaRaw);
        actualitzaDades(resposta);

    }

    /**
     * Executa l'accio de logout
     */
    public void accioLogout() {
        inicialitzaDades();
        dades.setEventMsg("logout");
    }

    /**
     * Inicialitza les dades de l'aplicacio, torna el seu valor a un estat per defecte
     */
    private void inicialitzaDades() {
        dades.inicialitzaDades();
    }

    /**
     * Tracta les dades de la petició que es fara al servidor.
     * Comprova que es compleixin restriccions en les dades com la contrassenya o el correu.
     * Adapta els objectes de dades a HashMap per poder passar-los al servidor
     *
     * @param peticio Petició que es fara al server.
     * @return peticio retorna la petició amb les dades adaptades.
     */
    public Object[] tractarPeticio(Object[] peticio) {
        String cmd = (String) peticio[0];
        String param = (String) peticio[1];
        Object dada = peticio[2];
        Object[] peticioTractada = new Object[4];
        //Supervisa la llargada de l'objecte petició
        if (peticio.length != 3) {
            dades.setErrorMsg("Objecte petició llargada incorrecte");
            return null;
        }
        //Supervisa les dades de login introduides per l'usuari
        if (peticio[0].equals(CMD_LOGIN)) {
            String correu = (String) peticio[1];
            String pass = (String) peticio[2];
            //Supervisa correu
            if (!correu.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")) {
                dades.setErrorMsg("Format correu incorrecte");
                return null;
            }
            //Supervisa contrasenya
            if (pass.length() < 8 || !pass.matches(".*\\d.*") || !pass.matches(".*[a-zA-Z].*")) {
                dades.setErrorMsg("Format Contrasenya incorrecte");
                return null;
            }
        }
        //Assigna sessioID a la peticio
        if (dades.getSessioID().equals("")) {
            peticioTractada[3] = null;
            dades.setErrorMsg("Sessio no iniciada");
        } else {
            peticioTractada[3] = dades.getSessioID();
        }
        //Convertir objectes a HashMaps
        if (dada instanceof Usuari) {
            Usuari usr = (Usuari) dada;
            dada = usr.usuari_to_map(usr);
        }
        if (dada instanceof Activitat) {
            Activitat act = (Activitat) dada;
            dada = act.activitat_to_map(act);
        }
        if (dada instanceof Installacio) {
            Installacio ins = (Installacio) dada;
            dada = ins.installacio_to_map(ins);
        }
        if (dada instanceof ClasseDirigida) {
            ClasseDirigida cd = (ClasseDirigida) dada;
            dada = cd.classeDirigida_a_hashMap(cd);
        }

        peticioTractada[0] = peticio[0];
        peticioTractada[1] = peticio[1];
        peticioTractada[2] = dada;

        dades.setEventMsg("Petició que s'envia al servidor: " + peticioTractada[0] + ", " + peticioTractada[1] + ", " + peticioTractada[2] + ", " + peticioTractada[3]);

        return peticioTractada;
    }

    /**
     * Tracta la resposta que prové del servidor, adapta el format de les dades
     *
     * @param respostaRaw Resposta obtinguda del servidor
     * @return resposta Resposta tractada amb les dades adaptades
     */
    public Object[] tractarResposta(Object[] respostaRaw) {
        Object[] resposta = respostaRaw;
        //Respostes incorrectes
        if (respostaRaw == null || respostaRaw[0] == null) {
            dades.setErrorMsg("Reseposta null del servidor");
            return null;
        }
        if (respostaRaw.length != 2) {
            dades.setErrorMsg("Llargada objecte resposta incorrecte");
            return null;
        }

        //Resposta Correcte
        if (!respostaRaw[0].equals("") && respostaRaw[1] != null) {
            String nomDada = (String) respostaRaw[0];
            Object dada = respostaRaw[1];
            Usuari usr = new Usuari("", "");
            Activitat act = new Activitat("", "", 0);
            Installacio ins = new Installacio("", "", "");
            ClasseDirigida cd = new ClasseDirigida("", "", "1", act, ins);
            //Identifica resposta de login, comprova el tipus d'usuari
            if (nomDada.contains(",")) {
                if (nomDada.split(",")[1].equals("1") || nomDada.split(",")[1].equals("2")) {
                    resposta[1] = usr.map_to_usuari((HashMap<String, String>) respostaRaw[1]);
                }
            }
            //Crea els objectes de dades a partir dels HashMaps
            switch (nomDada) {
                case USUARI:
                    resposta[1] = usr.map_to_usuari((HashMap<String, String>) respostaRaw[1]);
                    break;
                case USUARI_LLISTA:
                    resposta[1] = usr.crearLlistaUsuaris((ArrayList<HashMap<String, String>>) respostaRaw[1]);
                    break;
                case ACTIVITAT:
                    resposta[1] = act.map_to_activitat((HashMap<String, String>) respostaRaw[1]);
                    break;
                case ACTIVITAT_LLISTA:
                    resposta[1] = act.crearLlistaActivitats((ArrayList<HashMap<String, String>>) respostaRaw[1]);
                    break;
                case INSTALLACIO:
                    resposta[1] = ins.map_to_installacio((HashMap<String, String>) respostaRaw[1]);
                    break;
                case INSTALLACIO_LLISTA:
                    resposta[1] = ins.crearLlistaInstallacio((ArrayList<HashMap<String, String>>) respostaRaw[1]);
                    break;
                case CLASSE_DIRIGIDA:
                    resposta[1] = cd.map_a_classeDirigida((HashMap<String, String>) respostaRaw[1]);
                    break;
                case CLASSE_DIRIGIDA_LLISTA:
                    resposta[1] = cd.crearLlistaClasseDirigida(((ArrayList<HashMap<String, String>>) respostaRaw[1]));
                    break;
            }
        }
        return resposta;
    }

    /**
     * Aquest mètode actualitza el model de dades amb la resposta obtinguda del servidor
     *
     * @param resposta Dada de la resposta obtinguda del servidor
     */
    private void actualitzaDades(Object[] resposta) {
        if (resposta == null || resposta[0].equals("") || resposta[1] == null) return;
        String nomDada = (String) resposta[0];
        Object dada = resposta[1];
        if (nomDada.contains(",")) {
            dades.setSessioID(nomDada);
            dades.setUsuariActiu((Usuari) dada);
            return;
        }
        switch (nomDada) {
            case USUARI:
                dades.setUsuariSeleccionat((Usuari) dada);
                break;
            case USUARI_LLISTA:
                dades.setLlistaUsuaris((Usuari[]) dada);
                break;
            case ACTIVITAT:
                dades.setActivitatSeleccionada((Activitat) dada);
                break;
            case ACTIVITAT_LLISTA:
                dades.setLlistaActivitats((Activitat[]) dada);
                break;
            case INSTALLACIO:
                dades.setInstallacioSeleccionada((Installacio) dada);
                break;
            case INSTALLACIO_LLISTA:
                dades.setLlistaInstallacio((Installacio[]) dada);
                break;
            case CLASSE_DIRIGIDA:
                dades.setClasseDirigidaSeleccionada((ClasseDirigida) dada);
                break;
            case CLASSE_DIRIGIDA_LLISTA:
                dades.setLlistaClasseDirigida((ClasseDirigida[]) dada);
                break;
            case CMD_LOGOUT:
                accioLogout();
                break;
        }
    }

    /**
     * Mètode que executa una petició al servidor i retorna la resposta.
     *
     * @param peticio Petició que es passa al servidor.
     * @return resposta Resposta obtinguda del servidor.
     */
    public Object[] ferPeticio(Object[] peticio) {
        Object[] resposta;
        //Peticio
        ParlarAmbServidor srv = new ParlarAmbServidor();
        try {
            resposta = srv.enviarPeticio(peticio);  //Petició al servidor

        } catch (ConnectException cx) {
            dades.setErrorMsg("No hi ha connexió");
            return null;
        }
        dades.setEventMsg("Resposta obtinguda: " + resposta[0] + ", " + resposta[1]);
        return resposta;
    }

    public DadesAplicacio getDades() {
        return dades;
    }


}
