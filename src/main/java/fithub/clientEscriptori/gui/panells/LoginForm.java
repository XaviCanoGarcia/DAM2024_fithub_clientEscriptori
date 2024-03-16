package fithub.clientEscriptori.gui.panells;

import fithub.clientEscriptori.app.ControladorAccions;
import fithub.clientEscriptori.events.MissatgeListener;
import fithub.clientEscriptori.events.NotificadorMissatge;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm {

    NotificadorMissatge notificador;

    public LoginForm() {
        notificador = new NotificadorMissatge();
        botoAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                notificador.notificar("login,"+textFieldNom.getText()+","+textFieldPass.getText());

                /*
                */
            }
        });
    }

    public void setListener(ControladorAccions controladorAccions){
        notificador.agregarListener((MissatgeListener) controladorAccions);
    }

    public JPanel getPanel1() {
        return panel1;
    }

    private JPanel panel1;

    public JTextField getTextFieldPass() {
        return textFieldPass;
    }

    public JTextField getTextFieldNom() {
        return textFieldNom;
    }

    private JButton botoAceptar;
    private JTextField textFieldPass;
    private JTextField textFieldNom;
    private JLabel titol;
    private JLabel titolLogin;
    private JLabel titolNom;
    private JLabel titolPass;
}
