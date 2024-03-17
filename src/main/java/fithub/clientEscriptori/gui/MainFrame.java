package fithub.clientEscriptori.gui;

import javax.swing.*;
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
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setResizable(true);
        this.setVisible(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        currentPanel = new JPanel();
        add(currentPanel);

    }
    /**
     * Metode que canvia el panell del Main framme.
     *
     * @param nouPanell Panell nou que es vol canviar.
     */
    public void canviPanell(JPanel nouPanell) {

        remove(currentPanel);
        currentPanel = nouPanell;
        add(currentPanel);

        validate();
        repaint();
    }
}
