package fithub.clientEscriptori.app;

import fithub.clientEscriptori.events.MissatgeEvent;
import fithub.clientEscriptori.events.MissatgeListener;
import fithub.clientEscriptori.gui.ControladorGui;
import fithub.clientEscriptori.gui.ControladorPanells;

public class ControladorAccions implements MissatgeListener  {
    ControladorGui controladorGui;

    ControladorPanells panells;

    ParlarAmbServidor servidor;

    //Receptor receptor;

    String nomUsuari = "";
    String contrasenya = "";

    public ControladorAccions() {
        controladorGui = new ControladorGui();
        servidor = new ParlarAmbServidor();
        panells = controladorGui.getControladorPanells();
        //receptor = new Receptor();
        panells.getLoginForm().setListener(this);


    }

    public String accioLogin(String nom, String pass) {
        nomUsuari = nom;
        contrasenya = pass;
        System.out.print("login," + nomUsuari + "," + contrasenya);
        servidor.enviarPeticio("login," + nomUsuari + "," + contrasenya);
        return servidor.resposta;
    }

    @Override
    public void mensajeRecibido(MissatgeEvent event) {
        System.out.print(event.getMissatge());
        String[] msgList = event.getMissatge().split(",");
        String rsp = "";
        if(msgList[0].equals("login") && msgList.length==3){
            rsp = accioLogin(msgList[1], msgList[2]);
        }
        String[] rspList = rsp.split(",");
        if(rspList[1].equals("admin")){
            controladorGui.canviaPantalla("main","admin");
        }else if(rspList[1].equals("user")){
            controladorGui.canviaPantalla("main","user");
        }
    }
}


