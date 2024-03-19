package fithub.clientEscriptori.gui;
/**
 * Clase arrel de l'interficie gràfica.
 * Conté tots els elements grafics
 * Executa accion com canviar de pantalla
 *
 * @author Xavi Cano Garcia
 * @version 1.0
 */
public class ControladorGui {
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

    }


    /**
     * Metode que executa un canvi de pantalla.
     *
     * @param frame Frame al que es vol canviar.
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
