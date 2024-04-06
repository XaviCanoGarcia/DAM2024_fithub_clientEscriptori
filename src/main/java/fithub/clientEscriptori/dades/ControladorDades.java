package fithub.clientEscriptori.dades;

import fithub.clientEscriptori.app.ParlarAmbServidor;
import fithub.clientEscriptori.gui.ControladorGui;

import java.net.ConnectException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

/**
 * Aquesta és la classe l'arrel de tota l'estructura de dades de l'aplicació.
 * S'encarrega de fer peticions al servidor i gestionar les dades.
 *
 * @author Xavi Cano
 * @version 1.0
 */
public class ControladorDades {

    DadesAplicacio dades;

    ConversorDades conversorDades;

    public ControladorDades(ControladorGui controladorGui) {
        conversorDades = new ConversorDades();
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
    private Object[] tractarPeticio(Object[] peticio) {

        SimpleDateFormat formatData = new SimpleDateFormat("dd/MM/yyyy");
        if (peticio.length != 3) {
            dades.setErrorMsg("Objecte petició llargada incorrecte");
            return null;
        }
        /*
        if (peticio[0].equals("login")) {
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
        if (peticio[2] instanceof Usuari) {
            peticio[2] = conversorDades.usuari_to_map((Usuari) peticio[2]);
        }

        return peticio;
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
            //Crea els objectes de dades a partir dels HashMaps
            switch (nomDada) {
                case "usuari", "usuariActiu":
                    resposta[1] = conversorDades.map_to_usuari((HashMap<String, String>) respostaRaw[1]);
                    break;
                case "usuariList":
                    resposta[1] = conversorDades.crearLlistaUsuaris((HashMap<String, String>[]) respostaRaw[1]);
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
            case "usuariActiu":
                dades.setUsuariActiu((Usuari) dada);
                break;
            case "usuari":
                dades.setUsuariSeleccionat((Usuari) dada);
                break;
            case "usuariList":
                dades.setLlistaUsuaris((Usuari[]) dada);
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
