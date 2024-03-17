package fithub.clientEscriptori.app;

import fithub.clientEscriptori.events.LoginEvent;
import fithub.clientEscriptori.events.MissatgeEvent;
import fithub.clientEscriptori.events.MissatgeListener;
import fithub.clientEscriptori.gui.ControladorGui;
import fithub.clientEscriptori.gui.ControladorPanells;

public class ControladorAccions implements MissatgeListener {
    ControladorGui controladorGui;

    ControladorPanells panells;

    ParlarAmbServidor servidor;

    //Dades Usuari i sessio
    int currentSessionId = -1;
    String nomUsuari = "";
    String userType = "";
    String userTrueName = "";


    public ControladorAccions() {
        controladorGui = new ControladorGui();
        servidor = new ParlarAmbServidor();
        panells = controladorGui.getControladorPanells();
        //receptor = new Receptor();
        panells.getLoginForm().setListener(this);
        panells.getMainAdmin().setListener(this);
        panells.getMainUser().setListener(this);


    }

    public String accioLogin(String nom, String pass) {
        nomUsuari = nom;
        System.out.println("***login-event***    User: " + nom + "  Pass: " + pass + "   ***");
        servidor.enviarPeticio("login," + nomUsuari + "," + pass);
        return servidor.resposta;
    }


    @Override
    public void missatgeRebut(MissatgeEvent event) {
        System.out.println("***Msg-event***    " + event.getMissatge() + "   ***");


    }

    @Override
    public void loginMsgRebut(LoginEvent event) {

        String[] msgList = event.getMissatge().split(",");

        //Acció LOGIN
        if (currentSessionId == -1) {
            System.out.println("***login-event***    " + event.getMissatge() + "   ***");

            String rsp;
            if (msgList[0].equals("login") && msgList.length == 3) {
                rsp = accioLogin(msgList[1], msgList[2]);               //Petició login i resposta del servidor
                System.out.println("***login-event***  resposta-server   " + rsp + "   ***");
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
                System.out.println("***login-event***    LOGOUT   ***");
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


