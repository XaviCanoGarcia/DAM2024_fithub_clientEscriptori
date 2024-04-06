package fithub.clientEscriptori.gui;

import javax.swing.*;

/**
 * Clase que defineix la finestra de login.
 *
 * @author Xavi Cano Garcia
 * @version 1.0
 */
public class LoginFrame extends JFrame {
    /**
     * Constructor objecte Login frame.
     */
    public LoginFrame() {

        this.setTitle("FITHUB - login");
        this.setSize(400, 400);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
