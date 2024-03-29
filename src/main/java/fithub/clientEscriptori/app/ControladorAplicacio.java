package fithub.clientEscriptori.app;

import fithub.clientEscriptori.events.LoginEvent;
import fithub.clientEscriptori.events.MissatgeEvent;
import fithub.clientEscriptori.events.MissatgeListener;
import fithub.clientEscriptori.gui.ControladorGui;
import fithub.clientEscriptori.gui.ControladorPanells;
import fithub.clientEscriptori.peticions.ParlarAmbServidor;
import fithub.clientEscriptori.peticions.PeticioLogin;

/**
 * Clase arrel de l'aplicacio conté tots els elements.
 * Aquesta classe escolta els events generats per l'aplicació
 *
 * @author Xavi Cano Garcia
 * @version 1.0
 */
public class ControladorAplicacio implements MissatgeListener {
    ControladorGui controladorGui;

    ControladorPanells panells;

    ParlarAmbServidor servidor;

    //Dades Usuari i sessio

    Usuari usuari;


    /**
     * Constructor objecte ControladorAplicació.
     */
    public ControladorAplicacio() {
        controladorGui = new ControladorGui();
        servidor = new ParlarAmbServidor();
        panells = controladorGui.getControladorPanells();
        panells.getLoginForm().setListener(this);
        panells.getMainAdmin().setListener(this);
        panells.getMainUser().setListener(this);
    }

    /**
     * Executa l'acció al produir-se un event de tipus generic.
     *
     * @param event Event de tipus genèric escoltat.
     */
    @Override
    public void missatgeRebut(MissatgeEvent event) {
        System.out.println("***Msg-event***    ----" + event.getMissatge() + "");
    }


    /**
     * Executa l'acció login/logout al produir-se un event de tipus login.
     *
     * @param event Event de tipus login escoltat.
     */
    @Override
    public void loginMsgRebut(LoginEvent event) {
        System.out.println("***login-event***   Generat per l'usuari----" + event.getMissatge() + "");
        String[] msgList = event.getMissatge().split(",");

        if (msgList[0].equals("login") && msgList.length == 3) {
            usuari = new PeticioLogin().login(event);
            controladorGui.canviaPantalla("main", usuari.getTipus());
        }
        if (msgList[0].equals("logout") && msgList.length == 1) {
            usuari = new PeticioLogin().logout();
            controladorGui.canviaPantalla("login", usuari.getTipus());
        }
    }
}


