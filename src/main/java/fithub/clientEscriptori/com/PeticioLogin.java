package fithub.clientEscriptori.com;

import fithub.clientEscriptori.app.Usuari;
import fithub.clientEscriptori.events.LoginEvent;

/**
 * Classe que s'encarrega tant de fer la petició de login com la de logout.
 * Genera la petició al servidor i actualitza les dades
 */
public class PeticioLogin {

    private Usuari usuari = new Usuari("", "");
    private Object[] peticio = new Object[3];
    private Object[] resposta = new Object[2];

    /**
     * Aquet metode realitza l'acció de login, la informacio d'autenticació se li passa a traves d'un objecte de tipus event.
     *
     * @param event Event de tipus login amb les dades d'autenticació.
     * @return usuari Respasto amb la informació de usuari actiu del servidor.
     */
    public Usuari login(LoginEvent event) {
        Object[] msgList = event.getMissatge().split(",");
        //Acció LOGIN
        if (usuari.getSessioID() == -1) {
            if (msgList[0].equals("login") && msgList.length == 3) {
                resposta = accioLogin(msgList[1].toString(), msgList[2].toString());
                boolean status = (boolean) resposta[0];
                System.out.println("**LOGIN**    ----Resposta-server: " + status);
                if (status && resposta.length == 2) {
                    usuari = (Usuari) resposta[1];
                }
            }

        }
        return usuari;
    }

    /**
     * Petició de login al servidor.
     *
     * @param nom  Nom d'usuari amb el que es vol fer login.
     * @param pass Contrasenya amb la que es vol fer login.
     * @return resposta Objecte resposta que retorna el servidor.
     */
    public Object[] accioLogin(String nom, String pass) {
        usuari = new Usuari(nom, pass);
        System.out.println("**LOGIN**    ----User: " + nom + "  Pass: " + pass + "   ***");
        peticio[0] = "login";
        peticio[1] = nom;
        peticio[2] = pass;
        return new ParlarAmbServidor().enviarPeticio(peticio);
    }

    /**
     * Aquet metode realitza l'acció de logout.
     *
     * @return usuari Usuari amb valor per defecte.
     */
    public Usuari logout() {
        return new Usuari("", "");
    }
}
