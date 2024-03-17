package fithub.clientEscriptori.gui;

public class ControladorGui {
    LoginFrame loginFrame;
    MainFrame mainFrame;

    ControladorPanells controladorPanells;

    public ControladorGui() {
        controladorPanells = new ControladorPanells();

        mainFrame = new MainFrame();
        loginFrame = new LoginFrame();
        loginFrame.add(controladorPanells.loginForm.getPanel1());

    }

    public void canviaPantalla(String frame, String userType) {
        if (frame.equals("main")) {
            loginFrame.setVisible(false);
            mainFrame.setVisible(true);
            if (userType.equals("user")) {
                mainFrame.setTitle("FITHUB - User");
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
