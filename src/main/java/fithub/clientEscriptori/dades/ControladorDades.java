package fithub.clientEscriptori.dades;

import fithub.clientEscriptori.app.ParlarAmbServidor;
import fithub.clientEscriptori.dades.objectes.Activitat;
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
        dades.setEventMsg("Petició que s'envia: " + peticio[0] + ", " + peticio[1] + ", " + peticio[2]);
        respostaRaw = ferPeticio(peticio);
        dades.setEventMsg("Resposta obtinguda: " + respostaRaw[0] + ", " + respostaRaw[1]);
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
    private Object[] tractarPeticio(Object[] peticio) {
        String cmd = (String) peticio[0];
        String param = (String) peticio[1];
        Object dada = peticio[2];

        if (peticio.length != 3) {
            dades.setErrorMsg("Objecte petició llargada incorrecte");
            return null;
        }
        if (dada instanceof Usuari) {
            Usuari usr = (Usuari) dada;
            dada = usr.usuari_to_map(usr);
        }
        if (dada instanceof Activitat) {
            Activitat act = (Activitat) dada;
            dada = act.activitat_to_map(act);
        }
        peticio[2] = dada;
        return peticio;
        /*
        if (peticio[0].equals(CMD_LOGIN)) {
            Usuari usr = (Usuari) peticio[2];
            String correu = usr.getCorreu();
            String pass = usr.getContrasenya();
            String telefon = usr.getTelefon();
            String dataNeixament = usr.getDataNaixement();
            //Supervisa correu
            if (!correu.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")){
                dades.setErrorMsg("Format correu incorrecte");
                return null;
            }
            //Supervisa telefon
            if (!(telefon.length() == 9)){
                dades.setErrorMsg("Format telefon incorrecte");
                return null;
            }
            //Supervisa contrasenya
            if (pass.length() < 8 || !pass.matches(".*\\d.*") || !pass.matches(".*[a-zA-Z].*")){
                dades.setErrorMsg("Format Contrasenya incorrecte");
                return null;
            }
            //Supervisa data neixament
            try {
                Date data = formatData.parse(dataNeixament);
            } catch (ParseException e) {
                dades.setErrorMsg("Format data neixament incorrecte");
                return null;
            }
        }

         */
    }

    /**
     * Tracta la resposta que prové del servidor, adapta el format de les dades
     *
     * @param respostaRaw Resposta obtinguda del servidor
     * @return resposta Resposta tractada amb les dades adaptades
     */
    private Object[] tractarResposta(Object[] respostaRaw) {
        Object[] resposta = new Object[]{(""), ("")};
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
            resposta[0] = respostaRaw[0];
            Usuari usr = new Usuari("", "");
            Activitat act = new Activitat("", "", 0);
            //Crea els objectes de dades a partir dels HashMaps
            switch (nomDada) {
                case USUARI, USUARI_ACTIU:
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
        switch (nomDada) {
            case USUARI_ACTIU:
                dades.setUsuariActiu((Usuari) dada);
                break;
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
        }
    }

    /**
     * Mètode que executa una petició al servidor i retorna la resposta.
     *
     * @param peticio Petició que es passa al servidor.
     * @return resposta Resposta obtinguda del servidor.
     */
    private Object[] ferPeticio(Object[] peticio) {
        Object[] resposta;
        //Peticio
        ParlarAmbServidor srv = new ParlarAmbServidor();
        try {
            resposta = srv.enviarPeticio(peticio);  //Petició al servidor
        } catch (ConnectException cx) {
            dades.setErrorMsg("No hi ha connexió");
            return null;
        }
        return resposta;
    }

    public DadesAplicacio getDades() {
        return dades;
    }


}
