package fithub.clientEscriptori.gui;

import javax.swing.*;

/**
 * Clase que defineix la finestra de canvi d'informació de l'usuari.
 *
 * @author Xavi Cano Garcia
 * @version 1.0
 */
public class InfoUsuariFrame extends JFrame {

    JPanel currentPanel;

    public InfoUsuariFrame() {
        this.setTitle("FITHUB - Informació d'usuari");
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        currentPanel = new JPanel();
        add(currentPanel);
    }
}
