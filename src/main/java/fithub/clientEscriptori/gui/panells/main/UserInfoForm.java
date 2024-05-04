package fithub.clientEscriptori.gui.panells.main;

import fithub.clientEscriptori.app.ControladorAplicacio;
import fithub.clientEscriptori.dades.objectes.Usuari;
import fithub.clientEscriptori.events.NotificadorMissatge;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static fithub.clientEscriptori.dades.Constants.*;

public class UserInfoForm {
    NotificadorMissatge notificadorMsg;
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JTextField usuariActiuId;
    private JTextField usuariActiuCorreu;
    private JTextField usuariActiuTipus;
    private JTextField usuariActiuNom;
    private JTextField usuariActiuCognoms;
    private JTextField usuariActiuAdreca;
    private JTextField usuariActiuTelefon;
    private JTextField usuariActiuDataN;
    private JTextField usuariActiuDataI;
    private JButton usuariActiuAcceptaButton;
    private JTextField canviContrassenyaText1;
    private JTextField canviContrassenyaText2;
    private JButton canviContrassenyaAcceptaButton;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JLabel errorText;


    public UserInfoForm() {
        notificadorMsg = new NotificadorMissatge();
        errorText.setVisible(false);
        //BOTO INFO USUARI
        usuariActiuAcceptaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Usuari usr = new Usuari("", "");
                usr = getUsuariText();
                Object[] msg = new Object[]{(CMD_MODIFICA), (USUARI_ACTIU), (usr)};
                notificadorMsg.notificarMsg(msg);
            }
        });
        //BOTO CANVI CONTRASSENYA
        canviContrassenyaAcceptaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pass = "";
                String t1 = String.valueOf(passwordField1.getPassword());
                String t2 = String.valueOf(passwordField2.getPassword());
                if (t1.equals(t2)) {
                    pass = passwordField1.getText();
                    errorText.setForeground(Color.DARK_GRAY);
                    errorText.setText("Contrassenya repetida correctament");
                    errorText.setVisible(true);
                } else {
                    errorText.setForeground(Color.RED);
                    errorText.setText("Contrassenya repetida incorretament");
                    errorText.setVisible(true);
                    return;
                }
                Usuari usr = new Usuari(usuariActiuCorreu.getText(), t1);
                usr.setUsuariID(Integer.parseInt(usuariActiuId.getText()));
                Object[] msg = new Object[]{(CMD_MODIFICA), (CONTRASSENYA), (usr)};
                notificadorMsg.notificarMsg(msg);
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
        usuari.setUsuariID(Integer.valueOf(usuariActiuId.getText()));
        usuari.setTipus(Integer.valueOf(usuariActiuTipus.getText()));
        usuari.setNom(usuariActiuNom.getText());
        usuari.setCognoms(usuariActiuCognoms.getText());
        usuari.setDataNaixement(usuariActiuDataN.getText());
        usuari.setAdreca(usuariActiuAdreca.getText());
        usuari.setTelefon(usuariActiuTelefon.getText());
        usuari.setCorreu(usuariActiuCorreu.getText());
        //usuari.setContrasenya(txt_usr_contrasenya.getText());
        usuari.setDataInscripcio(usuariActiuDataI.getText());
        return usuari;
    }

    /**
     * Metode que omple les caixes de text amb les dades d'un objecte Usuari
     *
     * @param usuari Objecte usuari amb el que es vol omplir les caixes de text.
     */
    public void setUsuariText(Usuari usuari) {
        usuariActiuTipus.setText(String.valueOf(usuari.getTipus()));
        usuariActiuId.setText(String.valueOf(usuari.getUsuariID()));
        usuariActiuNom.setText(usuari.getNom());
        usuariActiuCognoms.setText(usuari.getCognoms());
        usuariActiuDataN.setText(usuari.getDataNaixement());
        usuariActiuAdreca.setText(usuari.getAdreca());
        usuariActiuTelefon.setText(usuari.getTelefon());
        usuariActiuCorreu.setText(usuari.getCorreu());
        //txt_usr_contrasenya.setText(usuari.getContrasenya());
        usuariActiuDataI.setText(usuari.getDataInscripcio());
    }


    public JPanel getPanel1() {
        return panel1;
    }

    public void setListenerMsg(ControladorAplicacio controladorAplicacio) {
        notificadorMsg.afegeixListener(controladorAplicacio);
    }

    public JTextField getUsuariActiuId() {
        return usuariActiuId;
    }

    public void setUsuariActiuId(JTextField usuariActiuId) {
        this.usuariActiuId = usuariActiuId;
    }

    public JTextField getUsuariActiuCorreu() {
        return usuariActiuCorreu;
    }

    public void setUsuariActiuCorreu(JTextField usuariActiuCorreu) {
        this.usuariActiuCorreu = usuariActiuCorreu;
    }

    public JTextField getUsuariActiuTipus() {
        return usuariActiuTipus;
    }

    public void setUsuariActiuTipus(JTextField usuariActiuTipus) {
        this.usuariActiuTipus = usuariActiuTipus;
    }

    public JTextField getUsuariActiuNom() {
        return usuariActiuNom;
    }

    public void setUsuariActiuNom(JTextField usuariActiuNom) {
        this.usuariActiuNom = usuariActiuNom;
    }

    public JTextField getUsuariActiuCognoms() {
        return usuariActiuCognoms;
    }

    public void setUsuariActiuCognoms(JTextField usuariActiuCognoms) {
        this.usuariActiuCognoms = usuariActiuCognoms;
    }

    public JTextField getUsuariActiuAdreca() {
        return usuariActiuAdreca;
    }

    public void setUsuariActiuAdreca(JTextField usuariActiuAdreca) {
        this.usuariActiuAdreca = usuariActiuAdreca;
    }

    public JTextField getUsuariActiuTelefon() {
        return usuariActiuTelefon;
    }

    public void setUsuariActiuTelefon(JTextField usuariActiuTelefon) {
        this.usuariActiuTelefon = usuariActiuTelefon;
    }

    public JTextField getUsuariActiuDataN() {
        return usuariActiuDataN;
    }

    public void setUsuariActiuDataN(JTextField usuariActiuDataN) {
        this.usuariActiuDataN = usuariActiuDataN;
    }

    public JTextField getUsuariActiuDataI() {
        return usuariActiuDataI;
    }

    public void setUsuariActiuDataI(JTextField usuariActiuDataI) {
        this.usuariActiuDataI = usuariActiuDataI;
    }

    public JTextField getCanviContrassenyaText1() {
        return canviContrassenyaText1;
    }

    public void setCanviContrassenyaText1(JTextField canviContrassenyaText1) {
        this.canviContrassenyaText1 = canviContrassenyaText1;
    }

    public JTextField getCanviContrassenyaText2() {
        return canviContrassenyaText2;
    }

    public void setCanviContrassenyaText2(JTextField canviContrassenyaText2) {
        this.canviContrassenyaText2 = canviContrassenyaText2;
    }
}
