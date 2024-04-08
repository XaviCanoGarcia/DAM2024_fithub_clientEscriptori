package fithub.clientEscriptori.gui.panells;

import fithub.clientEscriptori.app.ControladorAplicacio;
import fithub.clientEscriptori.dades.objectes.Activitat;
import fithub.clientEscriptori.dades.objectes.Usuari;
import fithub.clientEscriptori.events.NotificadorMissatge;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static fithub.clientEscriptori.dades.Constants.*;

public class MainAdminForm {
    NotificadorMissatge notificadorMsg;
    private JPanel panel1;
    private JTabbedPane tabbedPane1;
    private JTable table_usuaris;
    private JTable table_activitats;
    private JTextField txt_act_nom;
    private JTextField txt_act_descripcio;
    private JPanel panell_activitat;
    private JPanel panell_usuari;
    private JTextField txt_usr_correu;
    private JTextField txt_usr_contrasenya;
    private JTextField txt_usr_adreca;
    private JTextField txt_usr_telefon;
    private JTextField txt_usr_dataN;
    private JTextField txt_usr_dataI;
    private JButton nou_usuaributton;
    private JButton guardausuaributton;
    private JButton esborrausuaributton;
    private JButton actualitzaButton;
    private JButton logoutbutton;
    private JLabel usuariActualNom;
    private JLabel usuariActualCorreu;
    private JTextField txt_usr_nom;
    private JTextField txt_usr_cognoms;
    private JTextField txt_act_aforament;
    private JButton novaActivitatButton;
    private JButton guardaActivitatButton;
    private JButton esborraActivitatButton;
    private JButton button4;


    public MainAdminForm() {
        notificadorMsg = new NotificadorMissatge();
        //LOGOUT
        logoutbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] msg = new Object[]{(CMD_LOGOUT), (""), ("")};
                notificadorMsg.notificarMsg(msg);
            }
        });
        //ACTUALITZA TAULA
        actualitzaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] msg = new Object[]{(CMD_SELECT_ALL), (USUARI), (null)};
                Object[] msg2 = new Object[]{(CMD_SELECT_ALL), (ACTIVITAT), (null)};
                notificadorMsg.notificarMsg(msg);
                notificadorMsg.notificarMsg(msg2);
            }
        });
        //--------------------------------------------------
        //---------------------USUARI-----------------------
        //--------------------------------------------------
        //NOU USUARI
        nou_usuaributton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] msg = new Object[]{(CMD_NOU), (USUARI), (getUsuariText())};
                notificadorMsg.notificarMsg(msg);
            }
        });
        //GUARDA USUARI
        guardausuaributton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] msg = new Object[]{(CMD_MODIFICA), (USUARI), (getUsuariText())};
                notificadorMsg.notificarMsg(msg);
            }
        });
        //ESBORRA USUARI
        esborrausuaributton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] msg = new Object[]{(CMD_ELIMINA), (USUARI), (getUsuariText())};
                notificadorMsg.notificarMsg(msg);
            }
        });
        //TAULA SELECCIO USUARI
        table_usuaris.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getClickCount() == 1) {
                    Point point = e.getPoint();
                    int row = table_usuaris.rowAtPoint(point);
                    //int column = table1.columnAtPoint(point);
                    if (row != -1) {
                        Object[] msg = new Object[3];
                        msg[0] = CMD_MOUSE;
                        msg[1] = USUARI_SELECT;
                        msg[2] = row;
                        notificadorMsg.notificarMsg(msg);
                    }
                }
            }
        });
        //--------------------------------------------------
        //---------------------ACTIVITATS-------------------
        //--------------------------------------------------
        //NOVA ACTIVITAT
        novaActivitatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] msg = new Object[]{(CMD_NOU), (ACTIVITAT), (getActivitatText())};
                notificadorMsg.notificarMsg(msg);
            }
        });
        //GUARDAR ACYIVITAT
        guardaActivitatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] msg = new Object[]{(CMD_MODIFICA), (ACTIVITAT), (getActivitatText())};
                notificadorMsg.notificarMsg(msg);
            }
        });
        //ESBORRA ACTIVITAT
        esborraActivitatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] msg = new Object[]{(CMD_ELIMINA), (ACTIVITAT), (getActivitatText())};
                notificadorMsg.notificarMsg(msg);
            }
        });
        //TAULA SELECCIO ACTIVITAT
        table_activitats.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getClickCount() == 1) {
                    Point point = e.getPoint();
                    int row = table_activitats.rowAtPoint(point);
                    //int column = table1.columnAtPoint(point);
                    if (row != -1) {
                        Object[] msg = new Object[3];
                        msg[0] = CMD_MOUSE;
                        msg[1] = ACTIVITAT_SELECT;
                        msg[2] = row;
                        notificadorMsg.notificarMsg(msg);
                    }
                }
            }
        });
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    /**
     * Mètode que crea un objecte usuari amb les dades de les caixes de text de usuari seleccionat
     *
     * @return usuari Objecte Usuari generat.
     */
    public Usuari getUsuariText() {
        Usuari usuari = new Usuari("", "");
        usuari.setNom(txt_usr_nom.getText());
        usuari.setCognoms(txt_usr_cognoms.getText());
        usuari.setDataNaixement(txt_usr_dataN.getText());
        usuari.setAdreca(txt_usr_adreca.getText());
        usuari.setTelefon(txt_usr_telefon.getText());
        usuari.setCorreu(txt_usr_correu.getText());
        usuari.setContrasenya(txt_usr_contrasenya.getText());
        usuari.setDataInscripcio(txt_usr_dataI.getText());
        return usuari;
    }

    /**
     * Metode que omple les caixes de text amb les dades d'un objecte Usuari
     *
     * @param usuari Objecte usuari amb el que es vol omplir les caixes de text.
     */
    public void setUsuariText(Usuari usuari) {
        txt_usr_nom.setText(usuari.getNom());
        txt_usr_cognoms.setText(usuari.getCognoms());
        txt_usr_dataN.setText(usuari.getDataNaixement());
        txt_usr_adreca.setText(usuari.getAdreca());
        txt_usr_telefon.setText(usuari.getTelefon());
        txt_usr_correu.setText(usuari.getCorreu());
        txt_usr_contrasenya.setText(usuari.getContrasenya());
        txt_usr_dataI.setText(usuari.getDataInscripcio());
    }

    /**
     * Mètode que crea un objecte activitat amb les dades de les caixes de text de activitat seleccionada
     *
     * @return usuari Objecte Activitat generat.
     */
    public Activitat getActivitatText() {
        Activitat activitat = new Activitat("", "", 0);
        activitat.setNom(txt_act_nom.getText());
        activitat.setDescripcio(txt_act_descripcio.getText());
        activitat.setAforament(Integer.valueOf(txt_act_aforament.getText()));
        return activitat;
    }

    /**
     * Metode que omple les caixes de text amb les dades d'un objecte Activitat
     *
     * @param activitat Objecte activitat amb el que es vol omplir les caixes de text.
     */
    public void setActivitatText(Activitat activitat) {
        txt_act_nom.setText(activitat.getNom());
        txt_act_descripcio.setText(activitat.getDescripcio());
        txt_act_aforament.setText(String.valueOf(activitat.getAforament()));
    }

    public void setListenerMsg(ControladorAplicacio controladorAplicacio) {
        notificadorMsg.afegeixListener(controladorAplicacio);
    }

    public JPanel getPanel1() {
        return panel1;
    }

    public JLabel getUsuariActualNom() {
        return usuariActualNom;
    }

    public JLabel getUsuariActualCorreu() {
        return usuariActualCorreu;
    }

    public JTable getTable_activitats() {
        return table_activitats;
    }

    public JTable getTable_usuaris() {
        return table_usuaris;
    }
}
