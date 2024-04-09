package fithub.clientEscriptori.gui;

import fithub.clientEscriptori.dades.Constants;
import fithub.clientEscriptori.dades.objectes.Usuari;

import javax.swing.*;
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

    }

    @Override
    public void update(Observable o, Object arg) {
        Object[] data = (Object[]) arg;
        String nomDada = (String) data[0];
        Object dada = data[1];
        if (nomDada.equals(USUARI_ACTIU)) {
            Usuari usrActiu = (Usuari) dada;
            int usrTipus = usrActiu.getTipus();
            int sessioID = Integer.valueOf(usrActiu.getSessioID());
            if (sessioID != -1) {
                if (usrTipus==1) {
                    canviaPantalla(MAIN_FRAME, MAIN_ADMIN_FORM);
                } else if (usrTipus==2) {
                    canviaPantalla(MAIN_FRAME, USUARI_CLIENT);
                }
            } else {
                canviaPantalla(CMD_LOGIN, LOGIN_FORM);
            }
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
                loginFrame.setVisible(true);
                mainFrame.setVisible(false);
                break;
        }
        switch (panel) {
            case LOGIN_FORM:
                loginFrame.setContentPane(controladorPanells.loginForm.getPanel1());
                break;
            case MAIN_ADMIN_FORM:
                mainFrame.setContentPane(controladorPanells.mainAdminForm.getPanel1());
                break;
        }

    }

    public ControladorPanells getControladorPanells() {
        return controladorPanells;
    }


}
