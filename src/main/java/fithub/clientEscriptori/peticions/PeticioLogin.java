package fithub.clientEscriptori.peticions;

import fithub.clientEscriptori.app.Usuari;
import fithub.clientEscriptori.events.LoginEvent;

public class PeticioLogin {

    private Usuari usuari = new Usuari("", "");
    private Object[] peticio = new Object[3];
    private Object[] resposta = new Object[2];

    public Usuari login(LoginEvent event) {
        System.out.println("***login-event***   Generat per l'usuari----" + event.getMissatge() + "");
        Object[] msgList = event.getMissatge().split(",");
        //Acció LOGIN
        if (usuari.getSessioID() == -1) {
            if (msgList[0].equals("login") && msgList.length == 3) {
                resposta = accioLogin(msgList[1].toString(), msgList[2].toString());
                boolean status = (boolean) resposta[0];
                System.out.println("***login-event***   Resposta-server----" + status + resposta + "");
                if (status && resposta.length == 2) {
                    usuari = (Usuari) resposta[1];

                }
            }

        }
        return usuari;
    }

    public Object[] accioLogin(String nom, String pass) {
        usuari = new Usuari(nom, pass);
        System.out.println("***login-event***   Login que s'envia al servidor------User: " + nom + "  Pass: " + pass + "   ***");
        peticio[0] = "login";
        peticio[1] = nom;
        peticio[2] = pass;
        return new ParlarAmbServidor().enviarPeticio(peticio);
    }

    public Usuari logout() {
        return new Usuari("", "");
    }
}
