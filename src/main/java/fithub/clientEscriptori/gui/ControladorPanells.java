package fithub.clientEscriptori.gui;

import fithub.clientEscriptori.gui.panells.LoginForm;
import fithub.clientEscriptori.gui.panells.MainAdmin;
import fithub.clientEscriptori.gui.panells.MainUser;

public class ControladorPanells {
    LoginForm loginForm;

    MainUser mainUser;

    MainAdmin mainAdmin;

    public LoginForm getLoginForm() {
        return loginForm;
    }

    public ControladorPanells() {

        loginForm = new LoginForm();
        mainAdmin = new MainAdmin();
        mainUser = new MainUser();
    }
}
