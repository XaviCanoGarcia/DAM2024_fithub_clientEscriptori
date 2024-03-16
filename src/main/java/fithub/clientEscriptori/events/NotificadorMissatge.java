package fithub.clientEscriptori.events;

import java.util.ArrayList;
import java.util.List;

public class NotificadorMissatge {
    private List<MissatgeListener> listeners = new ArrayList<>();

    public void agregarListener(MissatgeListener listener) {
        listeners.add(listener);
    }

    public void removerListener(MissatgeEvent listener) {
        listeners.remove(listener);
    }

    public void notificar(String msg) {
        MissatgeEvent event = new MissatgeEvent(this, msg);
        for (MissatgeListener listener : listeners) {
            listener.mensajeRecibido(event);
        }
    }
}
