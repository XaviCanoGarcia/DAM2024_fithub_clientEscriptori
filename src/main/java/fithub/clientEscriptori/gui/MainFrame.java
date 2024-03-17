package fithub.clientEscriptori.gui;

import javax.swing.*;

public class MainFrame extends JFrame {

    JPanel currentPanel;

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

    public void canviPanell(JPanel nouPanell) {

        remove(currentPanel);
        currentPanel = nouPanell;
        add(currentPanel);

        validate();
        repaint();
    }
}
