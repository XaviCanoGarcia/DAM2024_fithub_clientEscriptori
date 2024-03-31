package fithub.clientEscriptori.gui.panells;

import fithub.clientEscriptori.app.ControladorAplicacio;
import fithub.clientEscriptori.app.Usuari;
import fithub.clientEscriptori.events.NotificadorMissatge;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Clase que defineix formulari main administrador.
 *
 * @author Xavi Cano Garcia
 * @version 1.0
 */
public class MainAdmin {
    NotificadorMissatge notificador;
    private JPanel panel1;
    private JSplitPane JSplitHorizontal;
    private JSplitPane JSplitVertical;
    private JPanel capcalera;
    private JPanel titolPantalla;
    private JPanel pestanyes;
    private JPanel contingut;
    private JButton pestanyaUsuaris;
    private JButton pestanyaActivitats;
    private JButton pestanyaServeis;
    private JButton pestanyaOpcions;
    private JButton buttonLogout;
    private JButton nouUsuariButton;
    private JLabel titolPantallaLabel;
    private JTable table1;
    private JPanel titolUsuariSeleccionat;
    private JPanel infoUsuariSeleccionat;
    private JTextField textSessioID;
    private JTextField textTipusUsuari;
    private JLabel titolUsuariSeleccionatLabel;
    private JLabel titolSessioIDLabel;
    private JLabel titolTipusUsuariLabel;
    private JPanel infoUsuariActual;
    private JPanel titolpanell;
    private JTextField textCorreu;
    private JTextField textContrasenya;
    private JTextField textCognom;
    private JTextField textTelefon;
    private JTextField textDataInscripcio;
    private JTextField textDataNeixement;
    private JTextField textAdreca;
    private JTextField textNom;
    private JButton esborraUsuariButton;
    private JButton guardaUsuariButton;
    private JLabel titolCorreu;
    private JLabel titolContrasenya;
    private JLabel titolNom;
    private JLabel titolCognom;
    private JLabel titolAdreca;
    private JLabel titolTelefon;
    private JLabel titolDAtaNeixement;
    private JLabel titolDataInscripcio;
    private JLabel correuUsuariActual;
    private JLabel nomUsuariActual;
    private JLabel sessioInfo;
    private JButton actualitzaTaulaButton;

    public MainAdmin() {

        notificador = new NotificadorMissatge();
        //LOGOUT
        buttonLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                notificador.notificarLogin("logout");
            }
        });
        //ACTUALIZA TAULA
        actualitzaTaulaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String param1 = "selectAll";
                String param2 = "usuari";
                Object[] msg = new Object[3];
                msg[0] = param1;
                msg[1] = param2;
                msg[2] = null;
                notificador.notificarMsg(msg);
            }
        });
        //NOU USUARI
        nouUsuariButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String param1 = "insert";
                String param2 = "usuari";
                Object[] msg = new Object[3];
                msg[0] = param1;
                msg[1] = param2;
                msg[2] = getUsuariText();
                notificador.notificarMsg(msg);
            }
        });
        //MODIFICA USUARI
        guardaUsuariButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String param1 = "update";
                String param2 = "usuari";
                Object[] msg = new Object[3];
                msg[0] = param1;
                msg[1] = param2;
                msg[2] = getUsuariText();
                notificador.notificarMsg(msg);
            }
        });
        //SELECCIONA USUARI DE LA TAULA
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getClickCount() == 1) {
                    Point point = e.getPoint();
                    int row = table1.rowAtPoint(point);
                    //int column = table1.columnAtPoint(point);
                    if (row != -1) {
                        String param1 = "mouse";
                        String param2 = "usuariSeleccionat";
                        Object[] msg = new Object[3];
                        msg[0] = param1;
                        msg[1] = param2;
                        msg[2] = row;
                        notificador.notificarMsg(msg);
                    }
                }
            }
        });
    }

    public void setListener(ControladorAplicacio controladorAplicacio) {
        notificador.afegeixListener(controladorAplicacio);
    }

    public JPanel getPanel1() {
        return panel1;
    }

    public JLabel getCorreuUsuariActual() {
        return correuUsuariActual;
    }

    public JLabel getNomUsuariActual() {
        return nomUsuariActual;
    }

    public JLabel getSessioInfo() {
        return sessioInfo;
    }

    public JTable getTable1() {
        return table1;
    }

    public void setTable1(JTable table1) {
        this.table1 = table1;
    }

    public Usuari getUsuariText() {
        Usuari usuari = new Usuari(
                textNom.getText(),
                textCognom.getText(),
                textDataNeixement.getText(),
                textAdreca.getText(),
                textTelefon.getText(),
                textCorreu.getText(),
                textContrasenya.getText(),
                textDataInscripcio.getText());
        return usuari;

    }

    public void setUsuariText(Usuari usuari) {
        textNom.setText(usuari.getNom());
        textCognom.setText(usuari.getCognoms());
        textDataNeixement.setText(usuari.getDataNaixement());
        textAdreca.setText(usuari.getAdreca());
        textTelefon.setText(usuari.getTelefon());
        textCorreu.setText(usuari.getCorreu());
        textContrasenya.setText(usuari.getContrasenya());
        textDataInscripcio.setText(usuari.getDataInscripcio());
    }


}
