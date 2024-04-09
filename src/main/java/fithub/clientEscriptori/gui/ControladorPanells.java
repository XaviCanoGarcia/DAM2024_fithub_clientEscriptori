package fithub.clientEscriptori.gui;

import fithub.clientEscriptori.dades.objectes.Activitat;
import fithub.clientEscriptori.dades.objectes.Usuari;

import fithub.clientEscriptori.gui.panells.MainAdminForm;
import fithub.clientEscriptori.gui.panells.login.LoginForm;

import fithub.clientEscriptori.gui.panells.MainUser;

import javax.swing.table.DefaultTableModel;

import static fithub.clientEscriptori.dades.Constants.*;


/**
 * Clase que conté y selecciona tots els panells.
 *
 * @author Xavi Cano Garcia
 * @version 1.0
 */
public class ControladorPanells {
    LoginForm loginForm;
    MainUser mainUser;
    MainAdminForm mainAdminForm;

    /**
     * Constructor objecte Controlador de panells.
     */
    public ControladorPanells() {
        loginForm = new LoginForm();
        mainUser = new MainUser();
        mainAdminForm = new MainAdminForm();
    }

    /**
     * Mètode per actualitzar les dades dels elements de la interficie gràfica
     * S'executa quan hi ha un canvi en dadesAplicació
     *
     * @param data dades passades per l'event.
     */
    public void update(Object data) {
        Object[] msj = (Object[]) data;
        String nomDada = (String) msj[0];
        Object dada = msj[1];
        //--------------------------------------------------
        //---------------------USUARIS----------------------
        //--------------------------------------------------
        //Actualitza elements grafics d'usuariActiu
        if (nomDada.equals(USUARI_ACTIU)) {
            Usuari usuari = (Usuari) dada;
            mainAdminForm.getUsuariActualCorreu().setText("Id: " + usuari.getSessioID() + ", " + usuari.getTipus() + ", " + usuari.getCorreu());
            mainAdminForm.getUsuariActualNom().setText(usuari.getNom() + " " + usuari.getCognoms());
            loginForm.getTextFieldNom().setText("");
            loginForm.getTextFieldPass().setText("");
            return;
        }
        //Actualitza elements grafics llistaUsuaris
        if (nomDada.equals(USUARI_LLISTA)) {
            String[] columnNames = USUARI_COLUMNES;
            Object[][] dadesTaula = llistaUsuarisTaula((Usuari[]) dada);
            DefaultTableModel model = new DefaultTableModel(dadesTaula, columnNames);
            mainAdminForm.getTable_usuaris().setModel(model);
            return;
        }
        //Actualitza elements grafics usuariSeleccionat
        if (nomDada.equals(USUARI_SELECT)) {
            mainAdminForm.setUsuariText((Usuari) dada);
            return;
        }
        //--------------------------------------------------
        //---------------------ACTIVITATS-------------------
        //--------------------------------------------------
        //Actualitza elements grafics llistaActivitats
        if (nomDada.equals(ACTIVITAT_LLISTA)) {
            String[] columnNamesActivitats = ACTIVITAT_COLUMNES;
            Object[][] dadesTaulaActivitats = llistaActivitatsTaula((Activitat[]) dada);
            DefaultTableModel modelActivitats = new DefaultTableModel(dadesTaulaActivitats, columnNamesActivitats);
            mainAdminForm.getTable_activitats().setModel(modelActivitats);
            return;
        }
        //Actualitza elements grafics activitatSeleccionada
        if (nomDada.equals(ACTIVITAT_SELECT)) {
            mainAdminForm.setActivitatText((Activitat) dada);
            return;
        }


    }


    /**
     * Mètode que tansforma una llista d'usuaris en un Object[][] per poder omplir la taula
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

    /**
     * Mètode que tansforma una llista de activitats en un Object[][] per poder omplir la taula
     *
     * @param llista Llista d'activitats
     * @return taula Array objecte dos dimensions
     */
    private Object[][] llistaActivitatsTaula(Activitat[] llista) {
        Object[][] taula = new Object[50][3];
        int i = 0;
        if (llista == null) return taula;
        for (Activitat activitat : llista) {
            if (activitat != null) {
                taula[i][0] = activitat.getNom();
                taula[i][1] = activitat.getDescripcio();
                taula[i][2] = activitat.getAforament();
                i++;
            }
        }
        return taula;
    }

    public LoginForm getLoginForm() {
        return loginForm;
    }

    public MainUser getMainUser() {
        return mainUser;
    }

    public MainAdminForm getMainAdminForm() {
        return mainAdminForm;
    }


}
