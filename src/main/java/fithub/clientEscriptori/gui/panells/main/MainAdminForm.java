package fithub.clientEscriptori.gui.panells.main;

import fithub.clientEscriptori.app.ControladorAplicacio;
import fithub.clientEscriptori.dades.objectes.*;
import fithub.clientEscriptori.events.NotificadorMissatge;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import static fithub.clientEscriptori.dades.Constants.*;

/**
 * Clase que defineix formulari main administrador.
 *
 * @author Xavi Cano Garcia
 * @version 1.0
 */
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
    private JPanel panell_classeDirigida;
    private JTable tableClasseDirigida;
    private JComboBox dataComboBox;
    private JComboBox activitatComboBox;
    private JComboBox horaComboBox;
    private JComboBox ubicacioComboBox;
    private JButton novaReservaButton;
    private JButton reservaGuardaButton;
    private JButton reservaEsborraButton;
    private JTextField txt_rsv_data;
    private JButton demanaDiaButton;
    private JPanel panell_serveis;
    private JTable tableServeis;
    private JTextField txt_srv_nom;
    private JTextField txt_srv_desc;
    private JButton nou_serveibutton;
    private JButton esborra_serveiButton;
    private JTextField txt_srv_preu;
    private JTextField txt_rsv_duracio;
    private JLabel fithub_logo;

    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem menuLogout;
    private JMenuItem menuInfo;

    private int idUsuari = 0;
    private int idActivitat = 0;
    private int idInstallacio = 0;
    private int idClasseDirigida = 0;
    private HashMap<String, Integer> idActivitatComboList;
    private HashMap<String, Integer> idInstallacioComboList;
    private int idActivitatCombo = 0;
    private int idInstallacioCombo = 0;
    private int idServei = 0;


    public MainAdminForm() {
        notificadorMsg = new NotificadorMissatge();
        DefaultCaret caret = (DefaultCaret) textAreaLog.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        textAreaLog.setEditable(false);
        actualitzaButton.setEnabled(false);
        actualitzaButton.setVisible(false);

        //menu
        menuBar = new JMenuBar();
        menu = new JMenu(("Opcions"));
        menuLogout = new JMenuItem("Logout");
        menuInfo = new JMenuItem("Informacio d'usuari");
        menu.add(menuInfo);
        menu.add(menuLogout);
        menuBar.add(menu);
        //FITHUB LOGO
        try {
            File file = new File("src/main/resources/fithub logo.png");
            Image img = ImageIO.read(file);
            Image scaledImg = img.getScaledInstance(237, 75, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(scaledImg);
            fithub_logo.setIcon(icon);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        //combo hora
        String[] horari = {("09:00"), ("10:00"), ("11:00"), ("12:00"), ("13:00"), ("14:00"), ("15:00"), ("16:00"), ("17:00"), ("18:00"), ("19:00"), ("20:00")};

        for (String hora : horari) {
            getHoraComboBox().addItem(hora);
        }

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
        //ACTUALITZA TAULES
        actualitzaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] msg = new Object[]{(CMD_SELECT_ALL), (USUARI), (null)};
                Object[] msg2 = new Object[]{(CMD_SELECT_ALL), (ACTIVITAT), (null)};
                Object[] msg3 = new Object[]{(CMD_SELECT_ALL), (INSTALLACIO), (null)};
                Object[] msg4 = new Object[]{(CMD_SELECT_ALL), (SERVEI), (null)};
                notificadorMsg.notificarMsg(msg);
                notificadorMsg.notificarMsg(msg2);
                notificadorMsg.notificarMsg(msg3);
                notificadorMsg.notificarMsg(msg4);

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
        //--------------------------------------------------
        //---------------------RESERVES---------------------
        //--------------------------------------------------
        //DEMANA CLASSE PROGRAMADA
        demanaDiaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String data = txt_rsv_data.getText();
                if (!(data.matches("\\d{8}"))) {
                    txt_rsv_data.setText("ddmmyyyy");
                    return;
                }
                Object[] msg = new Object[]{(CMD_SELECT_ALL), (CLASSE_DIRIGIDA), (data)};
                notificadorMsg.notificarMsg(msg);
            }
        });
        //NOVA CLASSE PROGRAMADA
        novaReservaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] msg = new Object[]{(CMD_NOU), (CLASSE_DIRIGIDA), (getClasseDirigidaText())};
                notificadorMsg.notificarMsg(msg);
            }
        });
        //GUARDA CLASSE PROGRAMADA
        reservaGuardaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] msg = new Object[]{(CMD_MODIFICA), (CLASSE_DIRIGIDA), (getClasseDirigidaText())};
                notificadorMsg.notificarMsg(msg);
            }
        });
        //ESBORRA CLASSE PROGRAMADA
        reservaEsborraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClasseDirigida cd = getClasseDirigidaText();
                cd.setId(-1);
                Object[] msg = new Object[]{(CMD_ELIMINA), (CLASSE_DIRIGIDA), (idClasseDirigida)};
                notificadorMsg.notificarMsg(msg);
            }
        });
        //TAULA CLASSE DIRIGIDA
        tableClasseDirigida.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getClickCount() == 1) {
                    Point point = e.getPoint();
                    int row = tableClasseDirigida.rowAtPoint(point);

                    if (row != -1) {
                        Object[] msg = new Object[3];
                        msg[0] = CMD_MOUSE;
                        msg[1] = CLASSE_DIRIGIDA_SELECT;
                        msg[2] = row;
                        notificadorMsg.notificarMsg(msg);
                    }
                }
            }
        });
        //--------------------------------------------------
        //---------------------SERVEIS---------------------
        //--------------------------------------------------
        //NOU SERVEI
        nou_serveibutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Servei srv = getServeiText();
                srv.setId(-1);
                Object[] msg = new Object[]{(CMD_NOU), (SERVEI), (srv)};
                notificadorMsg.notificarMsg(msg);
            }
        });
        //ESBORRA SERVEI
        esborra_serveiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Servei srv = getServeiText();
                srv.setId(-1);
                Object[] msg = new Object[]{(CMD_ELIMINA), (SERVEI), (srv.getNom())};
                notificadorMsg.notificarMsg(msg);
            }
        });
        //TAULA SERVEI
        tableServeis.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getClickCount() == 1) {
                    Point point = e.getPoint();
                    int row = tableServeis.rowAtPoint(point);

                    if (row != -1) {
                        Object[] msg = new Object[3];
                        msg[0] = CMD_MOUSE;
                        msg[1] = SERVEI_SELECT;
                        msg[2] = row;
                        notificadorMsg.notificarMsg(msg);
                    }
                }
            }
        });
    }

    /**
     * Mètode que crea un objecte peticio classe dirigida, l'activitat i la instalL'ació son només les id.
     *
     * @return peticioClasseDirigida objecte peticio de classe dirigida.
     */
    public ClasseDirigida getClasseDirigidaText() {
        ClasseDirigida cd = new ClasseDirigida("00000000", "09:00", "1", (new Activitat("", "", 1)), (new Installacio("", "", "")));
        idActivitatCombo = idActivitatComboList.get(activitatComboBox.getSelectedItem());
        idInstallacioCombo = idInstallacioComboList.get(ubicacioComboBox.getSelectedItem());
        cd.getActivitat().setId(idActivitatComboList.get(activitatComboBox.getSelectedItem()));
        cd.getInstallacio().setId(idInstallacioComboList.get(ubicacioComboBox.getSelectedItem()));
        cd.setData(txt_rsv_data.getText());
        cd.setId(idClasseDirigida);
        cd.setHoraInici((String) horaComboBox.getSelectedItem());
        cd.setDuracio(txt_rsv_duracio.getText());
        cd.setOcupacio("0");
        Object classeDirigidaPeticio = new Object() {
        };

        return cd;
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
     * Mètode que crea un objecte reserves amb les dades de les caixes de text del servei seleccionat
     *
     * @return Objecte servei generat.
     */
    public Servei getServeiText() {
        Servei srv = new Servei("", "");
        srv.setId(idServei);
        srv.setNom(txt_srv_nom.getText());
        srv.setDescripcio(txt_srv_desc.getText());
        srv.setPreu(txt_srv_preu.getText());
        return srv;
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

    public JLabel getUsuariAcrualCorreu() {
        return usuariAcrualCorreu;
    }


    public JTable getTable_activitats() {
        return table_activitats;
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

    public JMenuBar getMenuBar() {
        return menuBar;
    }

    public JComboBox getDataComboBox() {
        return dataComboBox;
    }

    public void setDataComboBox(JComboBox dataComboBox) {
        this.dataComboBox = dataComboBox;
    }

    public JComboBox getActivitatComboBox() {
        return activitatComboBox;
    }

    public void setActivitatComboBox(JComboBox activitatComboBox) {
        this.activitatComboBox = activitatComboBox;
    }

    public JComboBox getHoraComboBox() {
        return horaComboBox;
    }

    public void setHoraComboBox(JComboBox horaComboBox) {
        this.horaComboBox = horaComboBox;
    }

    public JComboBox getUbicacioComboBox() {
        return ubicacioComboBox;
    }

    public void setUbicacioComboBox(JComboBox ubicacioComboBox) {
        this.ubicacioComboBox = ubicacioComboBox;
    }

    public JTextField getTxt_rsv_data() {
        return txt_rsv_data;
    }

    public void setTxt_rsv_data(JTextField txt_rsv_data) {
        this.txt_rsv_data = txt_rsv_data;
    }

    public JTable getTableClasseDirigida() {
        return tableClasseDirigida;
    }

    public HashMap<String, Integer> getIdActivitatComboList() {
        return idActivitatComboList;
    }

    public void setIdActivitatComboList(HashMap<String, Integer> idActivitatComboList) {
        this.idActivitatComboList = idActivitatComboList;
    }

    public HashMap<String, Integer> getIdInstallacioComboList() {
        return idInstallacioComboList;
    }

    public void setIdInstallacioComboList(HashMap<String, Integer> idInstallacioComboList) {
        this.idInstallacioComboList = idInstallacioComboList;
    }

    public int getIdClasseDirigida() {
        return idClasseDirigida;
    }

    public void setIdClasseDirigida(int idClasseDirigida) {
        this.idClasseDirigida = idClasseDirigida;
    }

    public JTable getTableServeis() {
        return tableServeis;
    }

    public void setTableServeis(JTable tableServeis) {
        this.tableServeis = tableServeis;
    }

    public JTextField getTxt_srv_nom() {
        return txt_srv_nom;
    }

    public void setTxt_srv_nom(JTextField txt_srv_nom) {
        this.txt_srv_nom = txt_srv_nom;
    }

    public JTextField getTxt_srv_desc() {
        return txt_srv_desc;
    }

    public void setTxt_srv_desc(JTextField txt_srv_desc) {
        this.txt_srv_desc = txt_srv_desc;
    }

    public JTextField getTxt_srv_preu() {
        return txt_srv_preu;
    }

    public void setTxt_srv_preu(JTextField txt_srv_preu) {
        this.txt_srv_preu = txt_srv_preu;
    }

    public int getIdServei() {
        return idServei;
    }

    public void setIdServei(int idServei) {
        this.idServei = idServei;
    }

    public JTextField getTxt_rsv_duracio() {
        return txt_rsv_duracio;
    }

    public void setTxt_rsv_duracio(JTextField txt_rsv_duracio) {
        this.txt_rsv_duracio = txt_rsv_duracio;
    }
}
