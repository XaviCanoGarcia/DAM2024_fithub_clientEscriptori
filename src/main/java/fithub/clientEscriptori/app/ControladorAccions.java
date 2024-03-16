package fithub.clientEscriptori.app;

import fithub.clientEscriptori.gui.ControladorPanells;

public class ControladorAccions {
    ControladorPanells panells;

    ParlarAmbServidor servidor;

    String nomUsuari = "";
    String contrasenya = "";
    public ControladorAccions(ControladorPanells controladorPanells){
        panells = controladorPanells;
        servidor = new ParlarAmbServidor();
        //Accions Login


    }
    public void accioLogin(){
        nomUsuari = panells.getLoginForm().getTextFieldNom().getText();
        contrasenya = panells.getLoginForm().getTextFieldPass().getText();
        System.out.print("login,"+nomUsuari+","+contrasenya);
        servidor.enviarPeticio("login,"+nomUsuari+","+contrasenya);
    }
}
