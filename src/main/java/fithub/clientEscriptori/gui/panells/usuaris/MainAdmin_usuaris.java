package fithub.clientEscriptori.gui.panells.usuaris;

import fithub.clientEscriptori.app.ControladorAplicacio;
import fithub.clientEscriptori.dades.objectes.Usuari;
import fithub.clientEscriptori.events.NotificadorMissatge;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static fithub.clientEscriptori.dades.Constants.*;

/**
 * Clase que defineix formulari main administrador.
 *
 * @author Xavi Cano Garcia
 * @version 1.0
 */
public class MainAdmin_usuaris {
    NotificadorMissatge notificadorMsg;
    private JPanel panel1;
    private JButton pestanyaUsuaris;
    private JButton pestanyaActivitats;
    private JButton buttonLogout;
    private JButton nouUsuariButton;
    private JTable table1;
    private JPanel titolActivitatSeleccionada;
    private JPanel infoUsuariSeleccionat;
    private JLabel titolActivitatSeleccionadaLabel;
    private JTextField textNom;
    private JTextField textContrasenya;
    private JTextField textCognom;
    private JTextField textTelefon;
    private JTextField textDataInscripcio;
    private JTextField textDataNeixement;
    private JTextField textAdreca;
    private JTextField textAforament;
    private JButton esborraUsuariButton;
    private JButton guardaUsuariButton;
    private JLabel titolNom;
    private JLabel titolDescripcio;
    private JLabel titolAforament;
    private JLabel correuUsuariActual;
    private JLabel nomUsuariActual;
    private JLabel sessioInfo;
    private JButton actualitzaTaulaButton;
    private JPanel admin_activitats;
    private JPanel admin_usuaris;
    private JTextField textDescripcio;

    public MainAdmin_usuaris() {

        notificadorMsg = new NotificadorMissatge();


    }

    /**
     * Mètode que crea un objecte usuari amb les dades de les caixes de text de usuari seleccionat
     *
     * @return usuari Objecte Usuari generat.
     */
    public Usuari getUsuariText() {
        Usuari usuari = new Usuari(
                textAforament.getText(),
                textCognom.getText(),
                textDataNeixement.getText(),
                textAdreca.getText(),
                textTelefon.getText(),
                textNom.getText(),
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
        textAforament.setText(usuari.getNom());
        textCognom.setText(usuari.getCognoms());
        textDataNeixement.setText(usuari.getDataNaixement());
        textAdreca.setText(usuari.getAdreca());
        textTelefon.setText(usuari.getTelefon());
        textNom.setText(usuari.getCorreu());
        textContrasenya.setText(usuari.getContrasenya());
        textDataInscripcio.setText(usuari.getDataInscripcio());
    }

    public void setListenerMsg(ControladorAplicacio controladorAplicacio) {
        notificadorMsg.afegeixListener(controladorAplicacio);
    }

    public JPanel getPanel1() {
        return panel1;
    }


    public JLabel getSessioInfo() {
        return sessioInfo;
    }

    public JTable getTable1() {
        return table1;
    }


}
