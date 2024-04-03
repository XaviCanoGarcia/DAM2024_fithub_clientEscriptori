package fithub.clientEscriptori.gui.panells;

import fithub.clientEscriptori.app.ControladorAplicacio;
import fithub.clientEscriptori.app.Usuari;
import fithub.clientEscriptori.events.NotificadorGUI;
import fithub.clientEscriptori.events.NotificadorMissatge;
import fithub.clientEscriptori.gui.ControladorGui;

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
    NotificadorMissatge notificadorMsg;
    NotificadorGUI notificadorGui;
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

        notificadorMsg = new NotificadorMissatge();
        notificadorGui = new NotificadorGUI();

        notificadorMsg = new NotificadorMissatge();
        /**
         * LOGOUT, genera un event de tipus login amb la comanda logout
         */
        buttonLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                notificadorMsg.notificarMsg(new Object[]{("logout"), (""), ("")});
            }
        });
        /**
         * ACTUALITZA TAULA, genera un event de tipus dades amb la comanda "selectAll usuari"
         */
        actualitzaTaulaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String param1 = "selectAll";
                String param2 = "usuari";
                Object[] msg = new Object[3];
                msg[0] = param1;
                msg[1] = param2;
                msg[2] = null;
                notificadorMsg.notificarMsg(msg);
            }
        });
        /**
         * NOU USUARI, genera un event de tipus dades amb la comanda "insert usuari"
         */
        nouUsuariButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String param1 = "insert";
                String param2 = "usuari";
                Object[] msg = new Object[3];
                msg[0] = param1;
                msg[1] = param2;
                msg[2] = getUsuariText();
                notificadorMsg.notificarMsg(msg);
            }
        });
        /**
         * MODIFICA USUARI, genera un event de tipus dades amb la comanda "delete usuari nomUsuari"
         */
        guardaUsuariButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String param1 = "update";
                String param2 = "usuari";
                Object[] msg = new Object[3];
                msg[0] = param1;
                msg[1] = param2;
                msg[2] = getUsuariText();
                notificadorMsg.notificarMsg(msg);
            }
        });
        /**
         * SELECCIONA USUARI DE LA TAULA, genera un event de tipus dades amb la comanda "mouse usuariSeleccionat".
         * Aquesta acció la genera l'usuari clicant sobre la taula, l'usuari seleccionat s'actualitza amb la informació de la fila clicada de la taula.
         */
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
                        notificadorMsg.notificarMsg(msg);
                    }
                }
            }
        });
        /**
         * ESBORRA USUARI, genera un event de tipus dades amb la comanda "delete usuari"
         */
        esborraUsuariButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String param1 = "delete";
                String param2 = "usuari";
                Object[] msg = new Object[3];
                msg[0] = param1;
                msg[1] = param2;
                msg[2] = getUsuariText().getCorreu();
                notificadorMsg.notificarMsg(msg);
            }
        });
    }

    public void setListenerMsg(ControladorAplicacio controladorAplicacio) {
        notificadorMsg.afegeixListener(controladorAplicacio);
    }

    public void setListenerGui(ControladorGui controladorGui) {
        notificadorGui.afegeixListener(controladorGui);
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

    /**
     * Mètode que crea un objecte usuari amb les dades de les caixes de text de usuari seleccionat
     *
     * @return usuari Objecte Usuari generat.
     */
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

    /**
     * Metode que omple les caixes de text amb les dades d'un objecte Usuari
     *
     * @param usuari Objecte usuari amb el que es vol omplir les caixes de text.
     */
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
