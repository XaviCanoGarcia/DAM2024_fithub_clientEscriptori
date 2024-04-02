package fithub.clientEscriptori.gui.panells;

import fithub.clientEscriptori.app.ControladorAplicacio;
import fithub.clientEscriptori.events.NotificadorMissatge;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Clase que defineix formulari main usuari.
 *
 * @author Xavi Cano Garcia
 * @version 1.0
 */
public class MainUser {
    NotificadorMissatge notificador;
    private JPanel panel1;
    private JLabel titol;
    private JButton buttonLogout;

    public MainUser() {
        notificador = new NotificadorMissatge();
        buttonLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                notificador.notificarMsg(new Object[]{("logout"), (""), ("")});
            }
        });
    }

    public void setListener(ControladorAplicacio controladorAplicacio) {
        notificador.afegeixListener(controladorAplicacio);
    }

    public JPanel getPanel1() {
        return panel1;
    }
}
