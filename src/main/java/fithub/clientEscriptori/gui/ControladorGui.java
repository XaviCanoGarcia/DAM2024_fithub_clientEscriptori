package fithub.clientEscriptori.gui;


import javax.swing.*;

public class ControladorGui {
    LoginFrame loginFrame;
    MainFrame mainFrame = new MainFrame();


    public ControladorGui() {
        //CAUTION CREAR PANELL ABANS DEL Controlador d'accions
        ControladorPanells controladorPanells = new ControladorPanells();
        //ControladorAccions controladorAccions = new ControladorAccions(controladorPanells);


        loginFrame = new LoginFrame();
        loginFrame.add(controladorPanells.loginForm.getPanel1());

    }


}
