package fithub.clientEscriptori.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Clase que defineix la finestra principal.
 *
 * @author Xavi Cano Garcia
 * @version 1.0
 */
public class MainFrame extends JFrame {

    JPanel currentPanel;

    /**
     * Constructor objecte Main frame.
     */
    public MainFrame() {
        this.setTitle("FITHUB - Main");
        this.setSize(1237, 960);
        this.setLocationRelativeTo(null);
        this.setResizable(true);
        this.setVisible(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        currentPanel = new JPanel();
        add(currentPanel);
    }

}
