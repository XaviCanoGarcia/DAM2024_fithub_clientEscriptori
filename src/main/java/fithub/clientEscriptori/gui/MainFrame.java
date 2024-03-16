package fithub.clientEscriptori.gui;

import fithub.clientEscriptori.gui.panells.LoginForm;

import javax.swing.*;

public class MainFrame extends JFrame  {
    public MainFrame (){
        this.setTitle("FITHUB - Main");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setResizable(true);
        this.setVisible(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }
}
