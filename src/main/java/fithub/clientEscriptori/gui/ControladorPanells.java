package fithub.clientEscriptori.gui;

import fithub.clientEscriptori.gui.panells.LoginForm;
import fithub.clientEscriptori.gui.panells.MainAdmin;
import fithub.clientEscriptori.gui.panells.MainUser;

import java.io.ObjectInputStream;
import java.util.Observable;
import java.util.Observer;

/**
 * Clase que conte y selecciona tots els panells.(inprogress)
 *
 * @author Xavi Cano Garcia
 * @version 1.0
 */
public class ControladorPanells implements Observer {
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


    /**
     * Mètode per actualitzar les dades dels elements de la interficie gràfica
     * S'executa quan hi ha un canvi en dadesAplicació
     *
     * @param o   objecte on s'observa un canvi de dades.
     * @param arg dades passades per l'event.
     */
    @Override
    public void update(Observable o, Object arg) {
        Object[] msj = (Object[]) arg;
        System.out.println("updateGui: " + msj[0].toString());
    }
}
