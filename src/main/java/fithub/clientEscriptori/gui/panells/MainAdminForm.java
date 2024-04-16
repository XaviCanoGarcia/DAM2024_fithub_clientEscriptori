package fithub.clientEscriptori.gui.panells;

import fithub.clientEscriptori.app.ControladorAplicacio;
import fithub.clientEscriptori.dades.objectes.Activitat;
import fithub.clientEscriptori.dades.objectes.Installacio;
import fithub.clientEscriptori.dades.objectes.Usuari;
import fithub.clientEscriptori.events.NotificadorMissatge;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static fithub.clientEscriptori.dades.Constants.*;

public class MainAdminForm {
    NotificadorMissatge notificadorMsg;
    private JPanel panel1;
    private JTable table_usuaris;
    private JTable table_activitats;
    private JTextField txt_act_nom;
    private JTextField txt_act_descripcio;
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
    private JLabel usuariActualSessio;
    private JLabel usuariActualNom;
    private JTextField txt_usr_nom;
    private JTextField txt_usr_cognoms;
    private JTextField txt_act_aforament;
    private JButton novaActivitatButton;
    private JButton guardaActivitatButton;
    private JButton esborraActivitatButton;
    private JButton button4;
    private JTabbedPane tabbedPane1;
    private JPanel panell_usuari;
    private JPanel panell_activitat;
    private JTextArea textAreaLog;

    private JLabel usuariActualTipus;
    private JLabel usuariAcrualCorreu;
    private JPanel panell_installacio;
    private JTable table_installacions;
    private JTextField txt_ins_nom;
    private JTextField txt_ins_tipus;
    private JTextField txt_ins_descripcio;
    private JButton novaInstallacioButton;
    private JButton guardaInstallacioButton;
    private JButton esborraInstallacioButton;

    private int idUsuari = 0;

    private int idActivitat = 0;

    private int idInstallacio = 0;

    //private JTextArea textAreaLog;


    public MainAdminForm() {
        notificadorMsg = new NotificadorMissatge();
        DefaultCaret caret = (DefaultCaret) textAreaLog.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        //textAreaLog.setEditable(false);
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
                Object[] msg3 = new Object[]{(CMD_SELECT_ALL), (INSTALLACIO), (null)};
                notificadorMsg.notificarMsg(msg);
                notificadorMsg.notificarMsg(msg2);
                notificadorMsg.notificarMsg(msg3);
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
                Object[] msg = new Object[]{(CMD_ELIMINA), (USUARI), (getUsuariText().getCorreu())};
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
                Activitat act = getActivitatText();
                act.setId(-1);
                Object[] msg = new Object[]{(CMD_NOU), (ACTIVITAT), (act)};
                notificadorMsg.notificarMsg(msg);
            }
        });
        //GUARDAR ACTIVITAT
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
                Object[] msg = new Object[]{(CMD_ELIMINA), (ACTIVITAT), (getActivitatText().getNom())};
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
                    int row = table_installacions.rowAtPoint(point);
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
        //--------------------------------------------------
        //---------------------INSTAL·LACIONS-------------------
        //--------------------------------------------------
        //NOVA INSTAL·LACIÓ
        novaInstallacioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Installacio ins = getInstallacioText();
                ins.setId(-1);
                Object[] msg = new Object[]{(CMD_NOU), (INSTALLACIO), (ins)};
                notificadorMsg.notificarMsg(msg);
            }
        });
        //GUARDAR INSTAL·LACIÓ
        guardaInstallacioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] msg = new Object[]{(CMD_MODIFICA), (INSTALLACIO), (getInstallacioText())};
                notificadorMsg.notificarMsg(msg);
            }
        });
        //ESBORRA INSTAL·LACIÓ
        esborraInstallacioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] msg = new Object[]{(CMD_ELIMINA), (INSTALLACIO), (getInstallacioText().getNom())};
                notificadorMsg.notificarMsg(msg);
            }
        });
        //TAULA SELECCIO INSTAL·LACIÓ
        table_installacions.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getClickCount() == 1) {
                    Point point = e.getPoint();
                    int row = table_installacions.rowAtPoint(point);
                    //int column = table1.columnAtPoint(point);
                    if (row != -1) {
                        Object[] msg = new Object[3];
                        msg[0] = CMD_MOUSE;
                        msg[1] = INSTALLACIO_SELECT;
                        msg[2] = row;
                        notificadorMsg.notificarMsg(msg);
                    }
                }
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
        usuari.setUsuariID(idUsuari);
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
        activitat.setId(idActivitat);
        activitat.setNom(txt_act_nom.getText());
        activitat.setDescripcio(txt_act_descripcio.getText());
        activitat.setAforament(Integer.valueOf(txt_act_aforament.getText()));
        return activitat;
    }

    /**
     * Mètode que crea un objecte installacio amb les dades de les caixes de text de l'instal·lació seleccionada
     *
     * @return Instal·lació Objecte Activitat generat.
     */
    public Installacio getInstallacioText() {
        Installacio installacio = new Installacio("", "", "");
        installacio.setId(idInstallacio);
        installacio.setNom(txt_ins_nom.getText());
        installacio.setDescripcio(txt_ins_descripcio.getText());
        installacio.setTipus(txt_ins_tipus.getText());
        return installacio;
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

    /**
     * Metode que omple les caixes de text amb les dades d'un objecte Instal·lació
     *
     * @param installacio Objecte instal·lació amb el que es vol omplir les caixes de text.
     */
    public void setInstallacioText(Installacio installacio) {
        txt_ins_nom.setText(installacio.getNom());
        txt_ins_descripcio.setText(installacio.getDescripcio());
        txt_ins_tipus.setText(installacio.getTipus());
    }

    public void setListenerMsg(ControladorAplicacio controladorAplicacio) {
        notificadorMsg.afegeixListener(controladorAplicacio);
    }

    public JPanel getPanel1() {
        return panel1;
    }

    public JLabel getUsuariActualSessio() {
        return usuariActualSessio;
    }

    public JLabel getUsuariActualUsuari() {
        return usuariActualNom;
    }


    public JTable getTable_usuaris() {
        return table_usuaris;
    }

    public JTextArea getTextAreaLog() {
        return textAreaLog;
    }

    public JLabel getUsuariActualTipus() {
        return usuariActualTipus;
    }

    public void setUsuariActualTipus(JLabel usuariActualTipus) {
        this.usuariActualTipus = usuariActualTipus;
    }

    public JLabel getUsuariAcrualCorreu() {
        return usuariAcrualCorreu;
    }

    public void setUsuariAcrualCorreu(JLabel usuariAcrualCorreu) {
        this.usuariAcrualCorreu = usuariAcrualCorreu;
    }


    public JTextField getTxt_ins_nom() {
        return txt_ins_nom;
    }

    public void setTxt_ins_nom(JTextField txt_ins_nom) {
        this.txt_ins_nom = txt_ins_nom;
    }

    public JTextField getTxt_ins_tipus() {
        return txt_ins_tipus;
    }

    public void setTxt_ins_tipus(JTextField txt_ins_tipus) {
        this.txt_ins_tipus = txt_ins_tipus;
    }

    public JTextField getTxt_ins_decripcio() {
        return txt_ins_descripcio;
    }

    public void setTxt_ins_decripcio(JTextField txt_ins_decripcio) {
        this.txt_ins_descripcio = txt_ins_decripcio;
    }

    public JTable getTable_activitats() {
        return table_activitats;
    }

    public void setTable_activitats(JTable table_activitats) {
        this.table_activitats = table_activitats;
    }

    public JTable getTable_installacions() {
        return table_installacions;
    }

    public void setIdUsuari(int idUsuari) {
        this.idUsuari = idUsuari;
    }

    public void setIdActivitat(int idActivitat) {
        this.idActivitat = idActivitat;
    }

    public void setIdInstallacio(int idInstallacio) {
        this.idInstallacio = idInstallacio;
    }
}
