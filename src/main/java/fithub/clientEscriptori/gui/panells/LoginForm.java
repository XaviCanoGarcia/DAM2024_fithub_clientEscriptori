package fithub.clientEscriptori.gui.panells;

import fithub.clientEscriptori.app.ControladorAplicacio;
import fithub.clientEscriptori.events.NotificadorGUI;
import fithub.clientEscriptori.events.NotificadorMissatge;
import fithub.clientEscriptori.gui.ControladorGui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase que defineix formulari login.
 *
 * @author Xavi Cano Garcia
 * @version 1.0
 */
public class LoginForm {

    NotificadorMissatge notificadorMsg;
    NotificadorGUI notificadorGui;
    private JPanel panel1;
    private JButton botoAceptar;
    private JTextField textFieldPass;

    public void setTextFieldPass(JTextField textFieldPass) {
        this.textFieldPass = textFieldPass;
    }

    public void setTextFieldNom(JTextField textFieldNom) {
        this.textFieldNom = textFieldNom;
    }

    private JTextField textFieldNom;
    private JLabel titol;
    private JLabel titolLogin;
    private JLabel titolNom;
    private JLabel titolPass;

    public JButton getBotoAceptar() {
        return botoAceptar;
    }

    public LoginForm() {
        notificadorMsg = new NotificadorMissatge();
        notificadorGui = new NotificadorGUI();
        botoAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //notificador.notificarLogin("login," + textFieldNom.getText() + "," + textFieldPass.getText());
                notificadorMsg.notificarMsg(new Object[]{("login"), ("admin"), ("pass")});
            }
        });
    }

    public void setListenerMsg(ControladorAplicacio controladorAplicacio) {
        notificadorMsg.afegeixListener(controladorAplicacio);
    }

    public void setListenerGui(ControladorGui controladorGui) {
        notificadorGui.afegeixListener(controladorGui);
    }

    public JPanel getPanel1() {
        return panel1;
    }

    public JTextField getTextFieldPass() {
        return textFieldPass;
    }

    public void setTextFieldPass(String text) {
        this.textFieldPass.setText(text);
    }

    public JTextField getTextFieldNom() {
        return textFieldNom;
    }

    public void setTextFieldNom(String text) {
        this.textFieldNom.setText(text);
    }
}
