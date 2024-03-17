package fithub.clientEscriptori.events;

import java.util.EventObject;
/**
 * Clase que defineix un event de tipus login.
 *
 * @author Xavi Cano Garcia
 * @version 1.0
 */
public class LoginEvent extends EventObject {


    private final String missatge;
    /**
     * Constructor objecte event de login.
     * @param source Objecte que notificara el missatge
     * @param msg Missatge que es notificarà
     */
    public LoginEvent(Object source, String msg) {
        super(source);
        this.missatge = msg;
    }

    public String getMissatge() {
        return missatge;
    }

}
