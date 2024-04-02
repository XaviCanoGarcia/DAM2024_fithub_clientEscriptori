package fithub.clientEscriptori.gui;

import fithub.clientEscriptori.app.Usuari;
import fithub.clientEscriptori.gui.panells.LoginForm;
import fithub.clientEscriptori.gui.panells.MainAdmin;
import fithub.clientEscriptori.gui.panells.MainUser;
import fithub.clientEscriptori.gui.panells.MissatgeError;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.ObjectInputStream;
import java.util.Observable;
import java.util.Observer;

/**
 * Clase que conte y selecciona tots els panells.(inprogress)
 *
 * @author Xavi Cano Garcia
 * @version 1.0
 */
public class ControladorPanells implements Observer {
    LoginForm loginForm;

    MainUser mainUser;

    MainAdmin mainAdmin;

    MissatgeError missatgeError;

    JFrame errorFrame;

    /**
     * Constructor objecte Controlador de panells.
     */
    public ControladorPanells() {

        loginForm = new LoginForm();
        mainAdmin = new MainAdmin();
        mainUser = new MainUser();
        missatgeError = new MissatgeError();


    }


    public LoginForm getLoginForm() {
        return loginForm;
    }

    public MainUser getMainUser() {
        return mainUser;
    }

    public MainAdmin getMainAdmin() {
        return mainAdmin;
    }


    /**
     * Mètode per actualitzar les dades dels elements de la interficie gràfica
     * S'executa quan hi ha un canvi en dadesAplicació
     *
     * @param o   objecte on s'observa un canvi de dades.
     * @param arg dades passades per l'event.
     */
    @Override
    public void update(Observable o, Object arg) {
        Object[] msj = (Object[]) arg;
        System.out.println("***GUI***    ----Actualiza: " + msj[0].toString());

        //Actualitza elements grafics d'usuariActiu
        if (msj[0].equals("usuariActiu")) {
            Usuari usuari = (Usuari) msj[1];
            mainAdmin.getCorreuUsuariActual().setText("Tipus usuari " + usuari.getTipus() + " " + usuari.getCorreu());
            mainAdmin.getNomUsuariActual().setText(usuari.getNom() + " " + usuari.getCognoms());
            mainAdmin.getSessioInfo().setText("Sessio id: " + usuari.getSessioID());
        }
        //Actualitza elements grafics llistaUsuaris
        if (msj[0].equals("llistaUsuaris")) {
            String[] columnNames = {"Nom", "Cognom", "Data Neixament", "Adreça", "Telèfon", "Correu", "Contrasenya", "Data Inscripció"};
            Object[][] dadesTaula = llistaUsuarisTaula((Usuari[]) msj[1]);
            DefaultTableModel model = new DefaultTableModel(dadesTaula, columnNames);
            mainAdmin.getTable1().setModel(model);
        }
        //Actualitza elements grafics usuariSeleccionat
        if (msj[0].equals("usuariSeleccionat")) {
            mainAdmin.setUsuariText((Usuari) msj[1]);

        }
        /*if(msj[0].equals("error")&&!(msj[1].equals(""))){
            errorFrame = new JFrame();
            errorFrame.setTitle("FITHUB - Error");
            errorFrame.setLocationRelativeTo(null);
            errorFrame.setResizable(false);
            missatgeError.getErrorMessage().setText("(String)msj[1]");
            errorFrame.setVisible(true);
            errorFrame.add(missatgeError.getPanel1());
        }*/
    }

    /**
     * Mètode que tansforma una llista de usuaris en un Object[][] per poder omplir la taula
     *
     * @param llista Llista d'usuaris
     * @return taula Array objecte dos dimensions
     */
    private Object[][] llistaUsuarisTaula(Usuari[] llista) {
        Object[][] taula = new Object[50][8];
        int i = 0;
        if (llista == null) return taula;
        for (Usuari usuari : llista) {
            if (usuari != null) {
                taula[i][0] = usuari.getNom();
                taula[i][1] = usuari.getCognoms();
                taula[i][2] = usuari.getDataNaixement();
                taula[i][3] = usuari.getAdreca();
                taula[i][4] = usuari.getTelefon();
                taula[i][5] = usuari.getCorreu();
                taula[i][6] = usuari.getContrasenya();
                taula[i][7] = usuari.getDataInscripcio();
                i++;
            }
        }
        return taula;
    }
}
