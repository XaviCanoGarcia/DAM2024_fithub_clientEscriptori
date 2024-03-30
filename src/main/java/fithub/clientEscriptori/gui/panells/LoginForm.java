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

    NotificadorMissatge notificador;
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
        notificador = new NotificadorMissatge();

        botoAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //notificador.notificarLogin("login," + textFieldNom.getText() + "," + textFieldPass.getText());
                notificador.notificarLogin("login,admin,pass");

            }
        });
    }

    public void setListener(ControladorAplicacio controladorAplicacio) {
        notificador.afegeixListener(controladorAplicacio);
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
