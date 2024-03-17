package fithub.clientEscriptori.events;

import java.util.EventObject;

public class MissatgeEvent extends EventObject {

    private final String missatge;

    public MissatgeEvent(Object source, String msg) {
        super(source);
        this.missatge = msg;
    }

    public String getMissatge() {
        return missatge;
    }
}
