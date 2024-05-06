package fithub.clientEscriptori.gui;

import fithub.clientEscriptori.dades.objectes.Usuari;

import java.util.Observable;
import java.util.Observer;

import static fithub.clientEscriptori.dades.Constants.*;

/**
 * Clase arrel de l'interficie gràfica.
 * Conté tots els elements grafics
 * Executa accion com canviar de pantalla
 *
 * @author Xavi Cano Garcia
 * @version 1.0
 */
public class ControladorGui implements Observer {
    LoginFrame loginFrame;
    MainFrame mainFrame;
    ControladorPanells controladorPanells;

    /**
     * Constructor objecte ControladorGui.
     */
    public ControladorGui() {
        controladorPanells = new ControladorPanells();

        mainFrame = new MainFrame();
        loginFrame = new LoginFrame();
        loginFrame.add(controladorPanells.loginForm.getPanel1());
        //controladorPanells.getMainUser().setListener(this);
        mainFrame.setJMenuBar(controladorPanells.getMainAdminForm().getMenuBar());

    }


    /**
     * Actualitza finestres i notifica al controladorPanells
     *
     * @param o   Objecte que s'observa si hi ha canvi de dades
     * @param arg Nom i objecte dada que s'ha modificat
     */
    @Override
    public void update(Observable o, Object arg) {
        Object[] data = (Object[]) arg;
        String nomDada = (String) data[0];
        Object dada = data[1];
        //Canvi de frame si hi sessio activa
        if (nomDada.equals(SESSIO_ID) || nomDada.equals(CMD_LOGOUT)) {
            String sessioID = (String) dada;
            if (sessioID.contains(",")) {

            } else {
                canviaPantalla(LOGIN_FRAME, LOGIN_FORM);
            }
        }
        if (nomDada.equals(USUARI_ACTIU)) {
            Usuari usr = (Usuari) dada;
            if ((usr.getUsuariID() == -1)) return;
            if (((Usuari) dada).getTipus() == 1) {
                canviaPantalla(MAIN_FRAME, MAIN_ADMIN_FORM);
            } else if (((Usuari) dada).getTipus() == 2) {
                canviaPantalla(MAIN_FRAME, MAIN_CLIENT_FORM);
            }
        }
        if (nomDada.equals(INFO_USUARI)) {
            InfoUsuariFrame infoUsuariFrame = new InfoUsuariFrame();
            infoUsuariFrame.setContentPane(controladorPanells.getUserInfoForm().getPanel1());
        }
        controladorPanells.update(arg);
    }

    /**
     * Metode que executa un canvi de pantalla.
     *
     * @param frame Finestra a la qual es vol canviar.
     * @param panel Panell al que es vol canviar.
     */
    public void canviaPantalla(String frame, String panel) {
        //Selecció de finestra
        switch (frame) {
            case MAIN_FRAME:
                loginFrame.setVisible(false);
                mainFrame.setVisible(true);
                break;
            case LOGIN_FRAME:
                mainFrame.setVisible(false);
                loginFrame.setVisible(true);
                break;
        }
        switch (panel) {
            case LOGIN_FORM:
                loginFrame.setContentPane(controladorPanells.loginForm.getPanel1());
                break;
            case MAIN_ADMIN_FORM:
                mainFrame.setContentPane(controladorPanells.mainAdminForm.getPanel1());
                break;
            case MAIN_CLIENT_FORM:
                mainFrame.setContentPane(controladorPanells.mainUser.getPanel1());
        }

    }

    public ControladorPanells getControladorPanells() {
        return controladorPanells;
    }


}
