package fithub.clientEscriptori.gui.panells;

import fithub.clientEscriptori.app.ControladorAplicacio;
import fithub.clientEscriptori.events.NotificadorMissatge;

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
    private JPanel panel1;
    private JButton botoAceptar;
    private JTextField textFieldPass;
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

                notificadorMsg.notificarMsg(new Object[]{("login"), (textFieldCorreu.getText()), (textFieldPass.getText())});
                /*Usuari usr = new Usuari("Josep", "Lopez", "03/04/1997", "C/Terssol 18", "978056784", "josepLopez@gmail.com", "pass", "05/09/2020");
                usr.setSessioID(1);
                usr.setTipus("admin");
                notificadorMsg.notificarMsg(new Object[]{("login"), ("admin"), (usr)});*/
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

    public void setTextFieldPass(JTextField textFieldPass) {
        this.textFieldPass = textFieldPass;
    }

    public void setTextFieldPass(String text) {
        this.textFieldPass.setText(text);
    }
}
