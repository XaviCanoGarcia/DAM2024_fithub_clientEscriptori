package fithub.clientEscriptori.app;

import fithub.clientEscriptori.events.GuiEvent;
import fithub.clientEscriptori.events.LoginEvent;
import fithub.clientEscriptori.events.MissatgeEvent;
import fithub.clientEscriptori.events.MissatgeListener;
import fithub.clientEscriptori.gui.ControladorGui;
import fithub.clientEscriptori.com.ParlarAmbServidor;
import fithub.clientEscriptori.com.PeticioLogin;

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
        Object[] resposta;
        System.out.println("***DATA**    ----Petició: " + peticio[0] + " " + peticio[1] + " " + peticio[2]);
        //DADES USUARI
        if (peticio[1].equals("usuari") && peticio.length == 3) {
            resposta = new ParlarAmbServidor().enviarPeticio(peticio);  //Petició al servidor
            if (!(resposta[0] == null)) {
                if ((boolean) resposta[0] == true) {
                    if (resposta[1] instanceof Usuari) {
                        dades.setUsuariSeleccionat((Usuari) resposta[1]);
                    } else if (resposta[1] instanceof Usuari[]) {
                        dades.setLlistaUsuaris((Usuari[]) resposta[1]);
                    }
                } else {
                    System.out.println("***DATA**    ----Petició incorecta");
                }
            } else {
                System.out.println("***DATA**    ----Petició sense resposta");
            }
        }//Seleccio amb el mouse un usuari de la taula
        if (peticio[0].equals("mouse") && peticio[1].equals("usuariSeleccionat")) {
            if ((int) peticio[2] < dades.getLlistaUsuaris().length) {
                if (dades.getLlistaUsuaris()[(int) peticio[2]] != null) {
                    dades.setUsuariSeleccionat((Usuari) dades.getLlistaUsuaris()[(int) peticio[2]]);
                }
            }

        }

    }

    /**
     * Executa l'acció login/logout al produir-se un event de tipus login.
     *
     * @param event Event de tipus login escoltat.
     */
    public void loginEventRebut(LoginEvent event) {
        String[] msgList = event.getMissatge().split(",");
        if (msgList[0].equals("login") && msgList.length == 3) {
            dades.setUsuariActiu(new PeticioLogin().login(event));
            controladorGui.canviaPantalla("main", dades.getUsuariActiu().getTipus());
        }
        if (msgList[0].equals("logout") && msgList.length == 1) {
            dades.setUsuariActiu(new PeticioLogin().logout());
            controladorGui.canviaPantalla("login", dades.getUsuariActiu().getTipus());
        }
    }
}


