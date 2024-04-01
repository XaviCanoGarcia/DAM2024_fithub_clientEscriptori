package fithub.clientEscriptori.events;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase encarregada de generar notificacions.
 *
 * @author Xavi Cano Garcia
 * @version 1.0
 */
public class NotificadorGUI {
    private final List<GUIListener> listeners = new ArrayList<>();

    /**
     * Afegeig el listener MissatgeListener al notificador.
     *
     * @param listener Listener on es vol notificar.
     */
    public void afegeixListener(GUIListener listener) {
        listeners.add(listener);
    }

    /**
     * Elimina el listener del notificador.
     *
     * @param listener Listener que es vol eliminar.
     */
    public void removerListener(GUIListener listener) {
        listeners.remove(listener);
    }

    /**
     * Notifica un event de tipu generic.
     *
     * @param msg Missatge del event que es notifica.
     */
    public void notificarGui(Object[] msg) {
        GuiEvent event = new GuiEvent(this, msg);
        for (GUIListener listener : listeners) {
            listener.guiEventRebut(event);
        }
    }
}
