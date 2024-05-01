package fithub.clientEscriptori.gui;

import fithub.clientEscriptori.dades.objectes.Activitat;
import fithub.clientEscriptori.dades.objectes.ClasseDirigida;
import fithub.clientEscriptori.dades.objectes.Installacio;
import fithub.clientEscriptori.dades.objectes.Usuari;

import fithub.clientEscriptori.gui.panells.main.MainAdminForm;
import fithub.clientEscriptori.gui.panells.login.LoginForm;

import fithub.clientEscriptori.gui.panells.main.MainUser;
import fithub.clientEscriptori.gui.panells.main.UserInfoForm;

import javax.swing.table.DefaultTableModel;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

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
    UserInfoForm userInfoForm;

    /**
     * Constructor objecte Controlador de panells.
     */
    public ControladorPanells() {
        loginForm = new LoginForm();
        mainUser = new MainUser();
        userInfoForm = new UserInfoForm();
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
        //Actualitza la consola
        if (nomDada.equals(EVENT) || nomDada.equals(DADA_CONSOLA_LOG)) {
            Instant timestamp = Instant.now();
            timestamp = Instant.parse(timestamp.toString());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS")
                    .withZone(ZoneId.of("UTC"));
            String formattedTimestamp = formatter.format(timestamp);
            mainAdminForm.getTextAreaLog().append(formattedTimestamp + " - **EVENT**    ---- " + (String) dada + "\n");
        }
        //Actualitza la id de sessio
        if (nomDada.equals(SESSIO_ID)) {
            mainAdminForm.getUsuariActualSessio().setText("sessioId: " + dada);

        }
        //Actualitza elements gràfics de la finestra informació de l'usuari
        if (nomDada.equals(INFO_USUARI)) {
            userInfoForm.setUsuariText((Usuari) dada);
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

        }
        //Actualitza elements grafics activitatSeleccionada
        if (nomDada.equals(ACTIVITAT_SELECT)) {
            mainAdminForm.setActivitatText((Activitat) dada);
            mainAdminForm.setIdActivitat(((Activitat) dada).getId());
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

        }
        //Actualitza elements grafics activitatSeleccionada
        if (nomDada.equals(INSTALLACIO_SELECT)) {
            mainAdminForm.setInstallacioText((Installacio) dada);
            mainAdminForm.setIdInstallacio(((Installacio) dada).getId());
            return;
        }
        //--------------------------------------------------
        //---------------------RESERVES---------------------
        //--------------------------------------------------
        //Actualitza COMBOS
        if (nomDada.equals(ACTIVITAT_LLISTA)) {
            mainAdminForm.getActivitatComboBox().removeAllItems();
            HashMap<String, Integer> hs = new HashMap<>();
            for (Activitat act : (Activitat[]) dada) {
                mainAdminForm.getActivitatComboBox().addItem(act.getNom());
                hs.put(act.getNom(), act.getId());
            }
            mainAdminForm.setIdActivitatComboList(hs);

        }
        if (nomDada.equals(INSTALLACIO_LLISTA)) {
            mainAdminForm.getUbicacioComboBox().removeAllItems();
            HashMap<String, Integer> hs = new HashMap<>();
            for (Installacio ins : (Installacio[]) dada) {
                mainAdminForm.getUbicacioComboBox().addItem(ins.getNom());
                hs.put(ins.getNom(), ins.getId());
            }
            mainAdminForm.setIdInstallacioComboList(hs);

        }
        //Actualitza elements grafics llistaClasseDirigida
        if (nomDada.equals(CLASSE_DIRIGIDA_LLISTA)) {
            String[] columnNamesClasseDirigida = CLASSE_DIRIGIDA_COLUMNES;
            Object[][] dadesTaulaClasseDirigida = llistaClasseDirigidaTaula((ClasseDirigida[]) dada);
            DefaultTableModel modelClasseDirigida = new DefaultTableModel(dadesTaulaClasseDirigida, columnNamesClasseDirigida);
            mainAdminForm.getTableClasseDirigida().setModel(modelClasseDirigida);
            return;
        }
        //Actualitza elements gràfics de la ClasseDirigida seleccionada
        if (nomDada.equals(CLASSE_DIRIGIDA)) {
            ClasseDirigida cd = (ClasseDirigida) dada;
            mainAdminForm.getHoraComboBox().setSelectedItem(cd.getHoraInici());
            mainAdminForm.getActivitatComboBox().setSelectedItem(cd.getActivitat().getNom());
            mainAdminForm.getUbicacioComboBox().setSelectedItem(cd.getInstallacio().getNom());
            mainAdminForm.setIdClasseDirigida(cd.getId());
            return;
        }
    }

    /**
     * Mètode que tansforma una llista de classeDirigida en un Object[][] per poder omplir la taula
     *
     * @param llista Llista d'usuaris
     * @return taula Array objecte dos dimensions
     */
    private Object[][] llistaClasseDirigidaTaula(ClasseDirigida[] llista) {
        Object[][] taula = new Object[50][8];
        int i = 0;
        if (llista == null) return taula;
        for (ClasseDirigida cd : llista) {
            if (cd != null) {
                taula[i][0] = cd.getData();
                taula[i][1] = cd.getHoraInici();
                taula[i][2] = cd.getActivitat().getNom();
                taula[i][3] = cd.getInstallacio().getNom();
                i++;
            }
        }
        return taula;
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

    public UserInfoForm getUserInfoForm() {
        return userInfoForm;
    }

    public MainAdminForm getMainAdminForm() {
        return mainAdminForm;
    }


}
