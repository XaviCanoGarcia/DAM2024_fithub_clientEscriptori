package fithub.clientEscriptori.gui.panells;


import fithub.clientEscriptori.app.ParlarAmbServidor;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm {

    public LoginForm() {
        botoAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomUsuari = textFieldNom.getText();
                String contrasenya = textFieldPass.getText();
                System.out.print("login,"+nomUsuari+","+contrasenya);
                //servidor.enviarPeticio("login,"+nomUsuari+","+contrasenya);
            }
        });
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
