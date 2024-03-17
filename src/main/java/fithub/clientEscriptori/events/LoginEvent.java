package fithub.clientEscriptori.events;

import java.util.EventObject;

public class LoginEvent extends EventObject {


    private final String missatge;

    public LoginEvent(Object source, String msg) {
        super(source);
        this.missatge = msg;
    }

    public String getMissatge() {
        return missatge;
    }

}
