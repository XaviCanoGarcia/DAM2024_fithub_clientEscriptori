package fithub.clientEscriptori.gui.panells.main;

import fithub.clientEscriptori.app.ControladorAplicacio;
import fithub.clientEscriptori.events.NotificadorMissatge;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static fithub.clientEscriptori.dades.Constants.CMD_INFO_USUARI;
import static fithub.clientEscriptori.dades.Constants.CMD_LOGOUT;

/**
 * Clase que defineix formulari main usuari.
 *
 * @author Xavi Cano Garcia
 * @version 1.0
 */
public class MainUser {
    NotificadorMissatge notificadorMsg;
    private JPanel panel1;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem menuLogout;
    private JMenuItem menuInfo;
    private int idUsuari = 0;

    public MainUser() {
        //menu
        menuBar = new JMenuBar();
        menu = new JMenu(("Opcions"));
        menuLogout = new JMenuItem("Logout");
        menuInfo = new JMenuItem("Informacio d'usuari");
        menu.add(menuInfo);
        menu.add(menuLogout);
        menuBar.add(menu);

        //LOGOUT
        menuLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] msg = new Object[]{(CMD_LOGOUT), (String.valueOf(idUsuari)), ("")};
                notificadorMsg.notificarMsg(msg);
            }
        });
        //INFORMACIO DE L'USUARI
        menuInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] msg = new Object[]{(CMD_INFO_USUARI), (""), ("")};
                notificadorMsg.notificarMsg(msg);
            }
        });

    }


    public void setListener(ControladorAplicacio controladorAplicacio) {
        notificadorMsg.afegeixListener(controladorAplicacio);
    }

    public JPanel getPanel1() {
        return panel1;
    }

    public void setIdUsuari(int idUsuari) {
        this.idUsuari = idUsuari;
    }
}
