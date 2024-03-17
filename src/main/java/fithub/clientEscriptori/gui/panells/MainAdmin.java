package fithub.clientEscriptori.gui.panells;

import fithub.clientEscriptori.app.ControladorAccions;
import fithub.clientEscriptori.events.NotificadorMissatge;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public void setListener(ControladorAccions controladorAccions) {
        notificador.agregarListener(controladorAccions);
    }

    public JPanel getPanel1() {
        return panel1;
    }
}
