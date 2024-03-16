package fithub.clientEscriptori.gui;


import javax.swing.*;

public class ControladorGui {
    LoginFrame loginFrame;
    MainFrame mainFrame = new MainFrame();

    public ControladorPanells getControladorPanells() {
        return controladorPanells;
    }

    ControladorPanells controladorPanells;


    public ControladorGui() {
        controladorPanells = new ControladorPanells();


        loginFrame = new LoginFrame();
        loginFrame.add(controladorPanells.loginForm.getPanel1());

    }

    public void canviaPantalla (String frame, String userType){
        if(frame.equals("main")){
            loginFrame.setVisible(false);
            mainFrame.setVisible(true);
            if(userType.equals("user")){
                mainFrame.setTitle("FITHUB - User");
                mainFrame.add(controladorPanells.mainUser.getPanel1());
            }
            if(userType.equals("admin")){
                mainFrame.setTitle("FITHUB - Admin");
                mainFrame.add(controladorPanells.mainAdmin.getPanel1());
            }
        }
    }


}
