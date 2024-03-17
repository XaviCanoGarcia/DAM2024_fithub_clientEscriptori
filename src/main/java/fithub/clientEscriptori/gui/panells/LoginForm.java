package fithub.clientEscriptori.gui.panells;

import fithub.clientEscriptori.app.ControladorAccions;
import fithub.clientEscriptori.events.NotificadorMissatge;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm {

    NotificadorMissatge notificador;
    private JPanel panel1;
    private JButton botoAceptar;
    private JTextField textFieldPass;
    private JTextField textFieldNom;
    private JLabel titol;
    private JLabel titolLogin;
    private JLabel titolNom;
    private JLabel titolPass;

    public LoginForm() {
        notificador = new NotificadorMissatge();
        botoAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                notificador.notificarLogin("login," + textFieldNom.getText() + "," + textFieldPass.getText());

            }
        });
    }

    public void setListener(ControladorAccions controladorAccions) {
        notificador.agregarListener(controladorAccions);
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
