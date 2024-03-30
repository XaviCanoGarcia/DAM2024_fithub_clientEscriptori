package fithub.clientEscriptori.gui;

import fithub.clientEscriptori.app.Usuari;
import fithub.clientEscriptori.gui.panells.LoginForm;
import fithub.clientEscriptori.gui.panells.MainAdmin;
import fithub.clientEscriptori.gui.panells.MainUser;

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

    /**
     * Constructor objecte Controlador de panells.
     */
    public ControladorPanells() {

        loginForm = new LoginForm();
        mainAdmin = new MainAdmin();
        mainUser = new MainUser();
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
        System.out.println("updateGui: " + msj[0].toString());
        if (msj[0].equals("usuariActiu")) {
            Usuari usuari = (Usuari) msj[1];
            mainAdmin.getCorreuUsuariActual().setText("Tipus usuari " + usuari.getTipus() + " " + usuari.getCorreu());
            mainAdmin.getNomUsuariActual().setText(usuari.getNom() + " " + usuari.getCognoms());
            mainAdmin.getSessioInfo().setText("Sessio id: " + usuari.getSessioID());
        }
        if (msj[0].equals("llistaUsuaris")) {
            String[] columnNames = {"Nom", "Cognom", "Data Neixament", "Adreça", "Telèfon", "Correu", "Contrasenya", "Data Inscripció"};
            Object[][] dadesTaula = llistaUsuarisTaula((Usuari[]) msj[1]);
            DefaultTableModel model = new DefaultTableModel(dadesTaula, columnNames);
            mainAdmin.getTable1().setModel(model);
        }

    }

    private Object[][] llistaUsuarisTaula(Usuari[] llista) {
        Object[][] taula = new Object[50][8];
        int i = 0;
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
