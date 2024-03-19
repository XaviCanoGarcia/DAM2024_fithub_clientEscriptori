package fithub.clientEscriptori.app;

import fithub.clientEscriptori.events.LoginEvent;
import fithub.clientEscriptori.events.MissatgeEvent;
import fithub.clientEscriptori.events.MissatgeListener;
import fithub.clientEscriptori.gui.ControladorGui;
import fithub.clientEscriptori.gui.ControladorPanells;

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
    int currentSessionId = -1;
    String nomUsuari = "";
    String userType = "";
    String userTrueName = "";


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
     * Metode que executa l'accio de login y retorna la resposta del servidor.
     *
     * @param nom  Nom introduit per l'usuari.
     * @param pass Contrasenya introduida per l'usuari.
     * @return La resposta del servidor
     */
    public String accioLogin(String nom, String pass) {
        nomUsuari = nom;
        System.out.println("***login-event***   Login que s'envia al servidor------User: " + nom + "  Pass: " + pass + "   ***");
        return servidor.enviarPeticio("login," + nomUsuari + "," + pass);
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
        //Acció LOGIN
        if (currentSessionId == -1) {
            String rsp;
            if (msgList[0].equals("login") && msgList.length == 3) {
                rsp = accioLogin(msgList[1], msgList[2]);               //Petició login i resposta del servidor
                System.out.println("***login-event***   Resposta-server----" + rsp + "");
                String[] rspList = rsp.split(",");
                if (!(rspList[0].equals("-1")) && rspList.length == 3) {
                    currentSessionId = Integer.parseInt(rspList[0]);
                    userType = rspList[1];
                    userTrueName = rspList[2];
                }
            }
            if (!(currentSessionId == -1)) {
                controladorGui.canviaPantalla("main", userType);
            }

        } else {
            //Accio logout
            if (msgList[0].equals("logout")) {
                System.out.println("***login-event***       LOGOUT");
                currentSessionId = -1;
                nomUsuari = "";
                userType = "";
                userTrueName = "";
                controladorGui.getControladorPanells().getLoginForm().setTextFieldNom("");
                controladorGui.getControladorPanells().getLoginForm().setTextFieldPass("");
                controladorGui.canviaPantalla("login", "none");
            }
        }
    }
}


