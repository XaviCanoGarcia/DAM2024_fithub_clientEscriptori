package fithub.clientEscriptori.events;

import java.util.EventObject;

/**
 * Clase que defineix un event de tipus generic.
 *
 * @author Xavi Cano Garcia
 * @version 1.0
 */
public class MissatgeEvent extends EventObject {

    private final Object[] missatge;

    /**
     * Constructor objecte event generic.
     *
     * @param source Objecte que notificara el missatge
     * @param msg    Missatge que es notificarà
     */
    public MissatgeEvent(Object source, Object[] msg) {
        super(source);
        this.missatge = msg;
    }

    public Object[] getMissatge() {
        return missatge;
    }
}
