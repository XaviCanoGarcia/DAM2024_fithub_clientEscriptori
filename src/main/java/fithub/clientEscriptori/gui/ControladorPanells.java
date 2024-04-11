package fithub.clientEscriptori.gui;

import fithub.clientEscriptori.dades.objectes.Activitat;
import fithub.clientEscriptori.dades.objectes.Installacio;
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
        mainAdminForm.getTextAreaLog().setLineWrap(false);
        mainAdminForm.getTextAreaLog().setWrapStyleWord(false);
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
        if (nomDada.equals(EVENT) || nomDada.equals(DADA_CONSOLA_LOG)) {
            //String consoleText = insertaSaltDeLinia((String) dada, 120);
            //mainAdminForm.getTextAreaLog().append("**EVENT**    ---- " + (String) dada + "\n");
        }
        if (nomDada.equals(SESSIO_ID)) {
            mainAdminForm.getUsuariActualSessio().setText("sessioId: " + dada);

        }
        //--------------------------------------------------
        //---------------------USUARIS----------------------
        //--------------------------------------------------
        //Actualitza elements grafics d'usuariActiu
        if (nomDada.equals(USUARI_ACTIU)) {
            Usuari usuari = (Usuari) dada;
            String txt = "null";
            if (usuari.getTipus() == 1) {
                txt = "Usuari Administrador";
            } else if (usuari.getTipus() == 2) {
                txt = "Usuari Client";
            }
            mainAdminForm.getUsuariActualTipus().setText(txt);
            mainAdminForm.getUsuariActualUsuari().setText(usuari.getNom() + " " + usuari.getCognoms());
            mainAdminForm.getUsuariAcrualCorreu().setText(usuari.getCorreu());
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
            mainAdminForm.setIdUsuari(((Usuari) dada).getUsuariID());
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
        //--------------------------------------------------
        //---------------------INSTAL·LACIONS---------------
        //--------------------------------------------------
        //Actualitza elements grafics llistaInstal·lacions
        if (nomDada.equals(INSTALLACIO_LLISTA)) {
            String[] columnNamesInstallacio = INSTALLACIO_COLUMNES;
            Object[][] dadesTaulaInstallacio = llistaInstallacionsTaula((Installacio[]) dada);
            DefaultTableModel modelInstallacio = new DefaultTableModel(dadesTaulaInstallacio, columnNamesInstallacio);
            mainAdminForm.getTable_installacions().setModel(modelInstallacio);
            return;
        }
        //Actualitza elements grafics activitatSeleccionada
        if (nomDada.equals(INSTALLACIO_SELECT)) {
            mainAdminForm.setInstallacioText((Installacio) dada);
            return;
        }
    }

    /**
     * Inserta caràcters especials de salt de linia i tabulador cada x caracters.
     * Aquest mètod es fa servir per tractar les linies de text de la consola.
     *
     * @param text         Text on es vol insertar salts de linia
     * @param numCaracters Numero de caràcters de la linia
     * @return String amb els caràcter especials intercalats
     */
    public String insertaSaltDeLinia(String text, int numCaracters) {
        StringBuilder resultado = new StringBuilder();
        for (int i = 0; i < text.length(); i += numCaracters) {
            int fin = Math.min(i + numCaracters, text.length());
            resultado.append(text.substring(i, fin)).append("\n\t");
        }
        return resultado.toString();
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

    /**
     * Mètode que tansforma una llista de instal·lacions en un Object[][] per poder omplir la taula
     *
     * @param llista Llista d'instal·lacions
     * @return taula Array objecte dos dimensions
     */
    private Object[][] llistaInstallacionsTaula(Installacio[] llista) {
        Object[][] taula = new Object[50][3];
        int i = 0;
        if (llista == null) return taula;
        for (Installacio installacio : llista) {
            if (installacio != null) {
                taula[i][0] = installacio.getNom();
                taula[i][1] = installacio.getDescripcio();
                taula[i][2] = installacio.getTipus();
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
