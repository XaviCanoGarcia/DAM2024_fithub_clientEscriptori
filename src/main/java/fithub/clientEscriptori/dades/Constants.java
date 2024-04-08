package fithub.clientEscriptori.dades;

public class Constants {

    //Nom finestres
    public static final String MAIN_FRAME = "main";
    public static final String LOGIN_FRAME = "login";
    //Nom Panells
    public static final String LOGIN_FORM = "loginForm";
    public static final String MAIN_ADMIN_FORM = "mainAdminForm";
    //Tipus usuari
    public static final String USUARI_ADMIN = "admin";
    public static final String USUARI_CLIENT = "client";
    //Nom tipus Objecte dades
    public static final String USUARI = "usuari";
    public static final String ACTIVITAT = "activitat";
    public static final String[] USUARI_COLUMNES = {"Nom", "Cognom", "Data Neixament", "Adreça", "Telèfon", "Correu", "Contrasenya", "Data Inscripció"};
    public static final String[] ACTIVITAT_COLUMNES = {"Nom", "Decripció", "Aforament"};
    public static final String USUARI_ACTIU = "usuariActiu";
    public static final String USUARI_SELECT = "usuariSeleccionat";
    public static final String ACTIVITAT_SELECT = "activitatSeleccionada";
    public static final String USUARI_LLISTA = "usuariList";
    public static final String ACTIVITAT_LLISTA = "activitatList";
    public static final String PESTANYA = "pestanya";
    //Pestanyes
    public static final int PESTANYA_USUARI = 0;
    public static final int PESTANYA_ACTIVITATS = 1;
    public static final int PESTANYA_INSTALLACIONS = 2;
    public static final int PESTANYA_SERVEIS = 3;
    public static final int PESTANYA_RESERVES = 4;
    public static final int PESTANYA_OPCIONS = 9;
    //Comandes y parametres de les peticions
    public static final String CMD_NOU = "insert";
    public static final String CMD_LOGIN = "login";
    public static final String CMD_LOGOUT = "logout";
    public static final String CMD_SELECT = "select";
    public static final String CMD_SELECT_ALL = "selectAll";
    public static final String CMD_MODIFICA = "update";
    public static final String CMD_ELIMINA = "delecte";
    public static final String CMD_MOUSE = "mouse";
}
