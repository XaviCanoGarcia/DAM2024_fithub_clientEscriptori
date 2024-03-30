package fithub.clientEscriptori.events;

import java.util.ArrayList;
import java.util.List;
/**
 * Clase encarregada de generar notificacions.
 *
 * @author Xavi Cano Garcia
 * @version 1.0
 */
public class NotificadorMissatge {
    private final List<MissatgeListener> listeners = new ArrayList<>();
    /**
     * Afegeig el listener MissatgeListener al notificador.
     *
     * @param listener Listener on es vol notificar.
     */
    public void afegeixListener(MissatgeListener listener) {
        listeners.add(listener);
    }
    /**
     * Elimina el listener del notificador.
     *
     * @param listener Listener que es vol eliminar.
     */
    public void removerListener(MissatgeEvent listener) {
        listeners.remove(listener);
    }

    /**
     * Notifica un event de tipu generic.
     *
     * @param msg Missatge del event que es notifica.
     */
    public void notificarMsg(Object[] msg) {
        MissatgeEvent event = new MissatgeEvent(this, msg);
        for (MissatgeListener listener : listeners) {
            listener.dadesEventRebut(event);
        }
    }

    /**
     * Notifica un event de tipu login.
     *
     * @param msg Missatge del event que es notifica.
     */
    public void notificarLogin(String msg) {
        LoginEvent event = new LoginEvent(this, msg);
        for (MissatgeListener listener : listeners) {
            listener.loginEventRebut(event);
        }
    }
}
