package fithub.clientEscriptori.dades;


/**
 * Aquesta classe emmagatzema variables constants
 */
public class Constants {

    //Nom finestres
    public static final String MAIN_FRAME = "main";
    public static final String LOGIN_FRAME = "login";
    //Nom Panells
    public static final String LOGIN_FORM = "loginForm";
    public static final String MAIN_ADMIN_FORM = "mainAdminForm";

    //Tipus usuari
    public static final String USUARI_ADMIN = "1";
    public static final String USUARI_CLIENT = "client";

    //Comandes y parametres de les peticions
    public static final String CMD_NOU = "insert";
    public static final String CMD_LOGIN = "login";
    public static final String CMD_LOGOUT = "logout";
    public static final String CMD_SELECT = "select";
    public static final String CMD_SELECT_ALL = "selectAll";
    public static final String CMD_MODIFICA = "update";
    public static final String CMD_ELIMINA = "delete";
    public static final String CMD_MOUSE = "mouse";
    public static final String CMD_INFO_USUARI = "infoUsuari";

    //Nom tipus Objecte dades
    public static final String SESSIO_ID = "sessioID";
    public static final String EVENT = "event";
    public static final String INFO_USUARI = "canviInfoUsuari";
    public static final String CONTRASSENYA = "pass";
    public static final String DADA_CONSOLA_LOG = "dadaConsolaLog";
    public static final String USUARI = "usuari";
    public static final String USUARI_LLISTA = "usuariLlista";
    public static final String USUARI_ACTIU = "usuariActual";
    public static final String USUARI_SELECT = "usuariSeleccionat";
    public static final String[] USUARI_COLUMNES = {"Nom", "Cognom", "Data Neixament", "Adreça", "Telèfon", "Correu", "Contrasenya", "Data Inscripció"};

    public static final String ACTIVITAT = "activitat";
    public static final String ACTIVITAT_LLISTA = "activitatLlista";
    public static final String ACTIVITAT_SELECT = "activitatSeleccionada";
    public static final String[] ACTIVITAT_COLUMNES = {"Nom", "Decripció", "Aforament"};

    public static final String INSTALLACIO = "installacio";
    public static final String INSTALLACIO_LLISTA = "installacioLlista";
    public static final String INSTALLACIO_SELECT = "installacioSeleccionada";
    public static final String[] INSTALLACIO_COLUMNES = {"Nom", "Descripció", "Tipus"};

    public static final String CLASSE_DIRIGIDA = "classeDirigida";
    public static final String CLASSE_DIRIGIDA_LLISTA = "classeDirigidaLlista";
    public static final String CLASSE_DIRIGIDA_SELECT = "classeDirigidaSeleccionada";
    public static final String[] CLASSE_DIRIGIDA_COLUMNES = {"Data", "Hora", "Activitat", "Instal·lació"};

    public static final String SERVEI = "servei";
    public static final String SERVEI_LLISTA = "serveiLlista";
    public static final String SERVEI_SELECT = "serveiSelect";
    public static final String[] SERVEI_COLUMNES = {"Nom", "Descripció", "Preu"};

    //HashMapsIndex
    public static final String HM_USR_NOM = "nomUsuari";
    public static final String HM_USR_COGNOMS = "cognomsUsuari";
    public static final String HM_USR_CORREU = "correuUsuari";
    public static final String HM_USR_DATAN = "dataNaixement";
    public static final String HM_USR_DATAI = "dataInscripcio";
    public static final String HM_USR_ADRECA = "adreca";
    public static final String HM_USR_TELEFON = "telefon";
    public static final String HM_USR_TIPUS = "tipusUsuari";
    public static final String HM_USR_ID = "IDusuari";
    public static final String HM_USR_CONTRASENYA = "passUsuari";


    public static final String HM_ACT_ID = "IDactivitat";
    public static final String HM_ACT_NOM = "nomActivitat";
    public static final String HM_ACT_DESC = "descripcioActivitat";
    public static final String HM_ACT_TIPUS = "tipusActivitat";
    public static final String HM_ACT_AFORAMENT = "aforamentActivitat";

    public static final String HM_INS_ID = "IDinstallacio";
    public static final String HM_INS_NOM = "nomInstallacio";
    public static final String HM_INS_DESC = "descripcioInstallacio";
    public static final String HM_INS_TIPUS = "tipusInstallacio";

    public static final String HM_CDI_ID = "IDclasse";
    public static final String HM_CDI_DATA = "dataClasseDirigida";
    public static final String HM_CDI_HORA = "horaInici";
    public static final String HM_CDI_DURACIO = "duracio";
    public static final String HM_CDI_OCUPACIO = "ocupacio";

    public static final String HM_SRV_ID = "IDservei";
    public static final String HM_SRV_NOM = "nomServei";
    public static final String HM_SRV_DESC = "descripcioServei";
    public static final String HM_SRV_PREU = "preu";


}
