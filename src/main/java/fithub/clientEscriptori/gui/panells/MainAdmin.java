package fithub.clientEscriptori.gui.panells;

import fithub.clientEscriptori.app.ControladorAplicacio;
import fithub.clientEscriptori.events.NotificadorMissatge;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Clase que defineix formulari main administrador.
 *
 * @author Xavi Cano Garcia
 * @version 1.0
 */
public class MainAdmin {
    NotificadorMissatge notificador;
    private JPanel panel1;
    private JLabel titol;
    private JButton buttonLogout;

    public MainAdmin() {

        notificador = new NotificadorMissatge();
        buttonLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                notificador.notificarLogin("logout");
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
