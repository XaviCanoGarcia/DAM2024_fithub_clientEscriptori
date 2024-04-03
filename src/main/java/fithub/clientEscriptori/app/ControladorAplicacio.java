package fithub.clientEscriptori.app;

import fithub.clientEscriptori.events.MissatgeEvent;
import fithub.clientEscriptori.events.MissatgeListener;
import fithub.clientEscriptori.gui.ControladorGui;

import java.net.ConnectException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Clase arrel de l'aplicacio conté tots els elements.
 * Aquesta classe escolta els events generats per l'aplicació
 *
 * @author Xavi Cano Garcia
 * @version 1.0
 */
public class ControladorAplicacio implements MissatgeListener {
    ControladorGui controladorGui;
    DadesAplicacio dades;
    /**
     * Constructor objecte ControladorAplicació.
     */
    public ControladorAplicacio() {
        controladorGui = new ControladorGui();
        controladorGui.getControladorPanells().getLoginForm().setListenerMsg(this);
        controladorGui.getControladorPanells().getMainAdmin().setListenerMsg(this);
        //controladorGui.getControladorPanells().getMainUser().setListener(this);
        dades = new DadesAplicacio();
        dades.addObserver(controladorGui.getControladorPanells());
        dades.inicialitzaDades();
    }

    /**
     * Executa l'acció al produir-se un event de tipus dades.
     * Fa peticions al servidor i actualitza les dades amb la resposta
     *
     * @param event Event de dades escoltat.
     */
    @Override
    public void dadesEventRebut(MissatgeEvent event) {
        Object[] peticio = event.getMissatge();
        String peticioStatus;

        //Acció fer la petició al servidor
        System.out.println("***DATA**    ----Petició: " + peticio[0] + " " + peticio[1] + " " + peticio[2]);
        peticioStatus = comprobarPeticio(peticio);
        if (peticioStatus.equals("")) {
            ferPeticio(peticio);
        } else {
            dades.setErrorMsg(peticioStatus);
            return;
        }


        //Acció login/logout
        if (peticio[0].equals("login") && dades.getUsuariActiu().getSessioID() != -1) {
            controladorGui.canviaPantalla("main", dades.getUsuariActiu().getTipus());
        } else if (peticio[0].equals("logout")) {
            controladorGui.canviaPantalla("login", "");
            dades.inicialitzaDades();
        }
        //Seleccio amb el mouse un usuari de la taula
        if (peticio[0].equals("mouse") && peticio[1].equals("usuariSeleccionat")) {
            if ((int) peticio[2] < dades.getLlistaUsuaris().length) {
                if (dades.getLlistaUsuaris()[(int) peticio[2]] != null) {
                    dades.setUsuariSeleccionat((Usuari) dades.getLlistaUsuaris()[(int) peticio[2]]);
                }
            }
        }
    }

    private String comprobarPeticio(Object[] peticio) {
        SimpleDateFormat formatData = new SimpleDateFormat("dd/MM/yyyy");

        if (peticio.length != 3) {
            dades.setErrorMsg("Objecte petició llargada incorrecte");
            return "";
        }
        if (peticio[0].equals("login")) {
            Usuari usr = (Usuari) peticio[2];
            String correu = usr.getCorreu();
            String pass = usr.getContrasenya();
            String telefon = usr.getTelefon();
            String dataNeixament = usr.getDataNaixement();

            if (!correu.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")) return "Format correu incorrecte";
            if (!(telefon.length() == 9)) return "Format telefon incorrecte";
            if (pass.length() < 8 || !pass.matches(".*\\d.*") || !pass.matches(".*[a-zA-Z].*"))
                return "Format contrasenya incorrecte";
            try {
                Date data = formatData.parse(dataNeixament);
                return "";
            } catch (ParseException e) {
                return "Format data neixament incorrecte";
            }
        }
        return "";
    }

    /**
     * Mètode que executa una petioció al servidor i guarda la resposta a les dades aplicació.
     *
     * @param peticio Petició que es passa al servidor.
     */
    private void ferPeticio(Object[] peticio) {
        Object[] resposta = new Object[2];


        //Peticio
        ParlarAmbServidor srv = new ParlarAmbServidor();
        try {
            resposta = srv.enviarPeticio(peticio);  //Petició al servidor
        } catch (ConnectException cx) {
            dades.setErrorMsg("No hi ha connexió");
            return;
        }
        if ((resposta[0] == null)) {
            System.out.println("***DATA**    ----Petició sense resposta");
            dades.setErrorMsg("Petició sense resposta");
            return;
        }
        if ((boolean) resposta[0] == false) {
            System.out.println("***DATA**    ----Petició incorecta");
            dades.setErrorMsg("Petició incorrecta");
            return;
        }
        //S'interpreta la resposta
        if (resposta[1] instanceof Usuari) {
            if (peticio[0].equals("login")) {
                dades.setUsuariActiu((Usuari) resposta[1]);
            } else {
                dades.setUsuariSeleccionat((Usuari) resposta[1]);
            }
        } else if (resposta[1] instanceof Usuari[]) {
            dades.setLlistaUsuaris((Usuari[]) resposta[1]);
        }

    }
}


