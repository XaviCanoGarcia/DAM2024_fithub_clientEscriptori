package fithub.clientEscriptori.gui;

import fithub.clientEscriptori.gui.panells.LoginForm;

import javax.swing.*;

public class LoginFrame extends JFrame {

    public LoginFrame (){
        LoginForm loginForm = new LoginForm();
        this.setTitle("FITHUB - login");
        this.setSize(400, 400);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(loginForm.getPanel1());

    }
}
