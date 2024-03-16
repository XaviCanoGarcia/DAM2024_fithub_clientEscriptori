package fithub.clientEscriptori.gui;

import fithub.clientEscriptori.gui.panells.LoginForm;

public class ControladorPanells {
    LoginForm loginForm;

    public LoginForm getLoginForm() {
        return loginForm;
    }

    ControladorPanells() {

        loginForm = new LoginForm();
    }
}
