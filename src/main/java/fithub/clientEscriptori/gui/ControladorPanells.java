package fithub.clientEscriptori.gui;

import fithub.clientEscriptori.gui.panells.LoginForm;
import fithub.clientEscriptori.gui.panells.MainAdmin;
import fithub.clientEscriptori.gui.panells.MainUser;
/**
 * Clase que conte y selecciona tots els panells.(inprogress)
 *
 * @author Xavi Cano Garcia
 * @version 1.0
 */
public class ControladorPanells {
    LoginForm loginForm;

    MainUser mainUser;

    MainAdmin mainAdmin;
    /**
     * Constructor objecte Controlador de panells.
     */
    public ControladorPanells() {

        loginForm = new LoginForm();
        mainAdmin = new MainAdmin();
        mainUser = new MainUser();
    }


    public LoginForm getLoginForm() {
        return loginForm;
    }

    public MainUser getMainUser() {
        return mainUser;
    }

    public MainAdmin getMainAdmin() {
        return mainAdmin;
    }
}
