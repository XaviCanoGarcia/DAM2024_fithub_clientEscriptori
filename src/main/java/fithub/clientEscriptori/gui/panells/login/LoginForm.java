package fithub.clientEscriptori.gui.panells.login;

import fithub.clientEscriptori.app.ControladorAplicacio;
import fithub.clientEscriptori.events.NotificadorMissatge;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static fithub.clientEscriptori.dades.Constants.CMD_LOGIN;

/**
 * Clase que defineix formulari login.
 *
 * @author Xavi Cano Garcia
 * @version 1.0
 */
public class LoginForm {

    NotificadorMissatge notificadorMsg;
    private JPanel panel1;
    private JButton botoAceptar;
    private JPasswordField textFieldPass;
    private JTextField textFieldCorreu;
    private JLabel titol;
    private JLabel titolLogin;
    private JLabel titolNom;
    private JLabel titolPass;

    public LoginForm() {
        notificadorMsg = new NotificadorMissatge();
        botoAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //char[] caracters = textFieldPass.getPassword();
                //String passStr = new String(caracters);
                //notificadorMsg.notificarMsg(new Object[]{(CMD_LOGIN), (textFieldCorreu.getText()), (passStr)});
                notificadorMsg.notificarMsg(new Object[]{("login"), ("admin@fithub.es"), ("Adminpass38")});
            }
        });
    }

    public JButton getBotoAceptar() {
        return botoAceptar;
    }

    public JTextField getTextFieldNom() {
        return textFieldCorreu;
    }

    public void setTextFieldNom(JTextField textFieldNom) {
        this.textFieldCorreu = textFieldNom;
    }

    public void setTextFieldNom(String text) {
        this.textFieldCorreu.setText(text);
    }

    public void setListenerMsg(ControladorAplicacio controladorAplicacio) {
        notificadorMsg.afegeixListener(controladorAplicacio);
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
}
