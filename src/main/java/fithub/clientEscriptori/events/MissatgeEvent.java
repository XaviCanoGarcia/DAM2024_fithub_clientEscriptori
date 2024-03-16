package fithub.clientEscriptori.events;

import jdk.jfr.Event;

import java.util.EventObject;

public class MissatgeEvent extends EventObject {

    private String missatge;

    public MissatgeEvent(Object source, String msg) {
        super(source);
        this.missatge = msg;
    }

    public String getMissatge() {
        return missatge;
    }
}
