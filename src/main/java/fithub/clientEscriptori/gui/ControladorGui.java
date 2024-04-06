package fithub.clientEscriptori.gui;

import fithub.clientEscriptori.dades.Usuari;

import java.util.Observable;
import java.util.Observer;

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
        if (nomDada.equals("usuariActiu")) {
            Usuari usrActiu = (Usuari) dada;
            String usrTipus = usrActiu.getTipus();
            int sessioID = Integer.valueOf(usrActiu.getSessioID());
            if (sessioID != -1) {
                if (usrTipus.equals("admin")) {
                    canviaPantalla("main", "admin");
                } else if (usrTipus.equals("client")) {
                    canviaPantalla("main", "client");
                }
            } else {
                canviaPantalla("login", "admin");
            }

        }

        controladorPanells.update(arg);
    }

    /**
     * Metode que executa un canvi de pantalla.
     *
     * @param frame    Frame al que es vol canviar.
     * @param userType Tipus de usuari que es fara servir per triar el panell.
     */
    public void canviaPantalla(String frame, String userType) {
        if (frame.equals("main")) {
            loginFrame.setVisible(false);
            mainFrame.setVisible(true);
            if (userType.equals("client")) {
                mainFrame.setTitle("FITHUB - Client");
                mainFrame.canviPanell(controladorPanells.mainUser.getPanel1());
            }
            if (userType.equals("admin")) {
                mainFrame.setTitle("FITHUB - Admin");
                mainFrame.canviPanell(controladorPanells.mainAdmin.getPanel1());
            }
        } else if (frame.equals("login")) {
            loginFrame.setVisible(true);
            mainFrame.setVisible(false);
        }
    }

    public ControladorPanells getControladorPanells() {
        return controladorPanells;
    }


}
