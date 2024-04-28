package fithub.clientEscriptori.dades;

import fithub.clientEscriptori.dades.objectes.Activitat;
import fithub.clientEscriptori.dades.objectes.ClasseDirigida;
import fithub.clientEscriptori.dades.objectes.Installacio;
import fithub.clientEscriptori.dades.objectes.Usuari;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Observable;

import static fithub.clientEscriptori.dades.Constants.*;

/**
 * Classe que conté les dades de l'aplicació.
 *
 * @author Xavi Cano
 * @version 1.0
 */
public class DadesAplicacio extends Observable {

    private String sessioID = "";
    private Usuari usuariActiu;
    private boolean canviInfoUsuariActiu;
    private Usuari usuariSeleccionat;
    private Usuari[] llistaUsuaris;
    private Activitat activitatSeleccionada;
    private Activitat[] llistaActivitats;
    private Installacio installacioSeleccionada;
    private Installacio[] llistaInstallacio;
    private ClasseDirigida classeDirigidaSeleccionada;
    private ClasseDirigida[] llistaClasseDirigida;

    private String errorMsg;
    private String eventMsg;

    /**
     * Constructor Dades aplicació
     */
    public DadesAplicacio() {
        usuariActiu = new Usuari("", "");
        usuariActiu.setTipus(1);
        canviInfoUsuariActiu = false;
        usuariSeleccionat = new Usuari("", "");
        activitatSeleccionada = new Activitat("", "", 0);
        installacioSeleccionada = new Installacio("", "", "");
    }

    /**
     * Notifica que hi ha un canvi en les dades, l'event conté el nom de la dada i la dada que s'ha modificat
     *
     * @param nomDada Nom de la dada que s'ha modificat.
     * @param dada    Objecte amb la dada que sha modificat
     */
    void notificaCanviDades(String nomDada, Object dada) {
        Object[] updatedData = new Object[2];
        updatedData[0] = nomDada;
        updatedData[1] = dada;
        notifyObservers(updatedData);
        if (!nomDada.equals(EVENT)) {
            Instant timestamp = Instant.now();
            timestamp = Instant.parse(timestamp.toString());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS")
                    .withZone(ZoneId.of("UTC"));
            String formattedTimestamp = formatter.format(timestamp);
            System.out.println(formattedTimestamp + " - **DATA**     ---- Dada modificada: " + nomDada);
        }
    }

    /**
     * Inicialitza el model de dades a valors per defecte
     */
    void inicialitzaDades() {
        setSessioID("");
        setUsuariActiu(new Usuari("a", "a"));
        setUsuariSeleccionat(new Usuari("", ""));
        setLlistaUsuaris(new Usuari[]{(new Usuari("", "")), (new Usuari("", ""))});
        setActivitatSeleccionada(new Activitat("", "", 0));
        setLlistaActivitats(new Activitat[]{(new Activitat("", "", 0)), new Activitat("", "", 0)});
        ClasseDirigida cd = new ClasseDirigida("", "", 1, (new Activitat("", "", 0)), (new Installacio("", "", "")));
        ClasseDirigida cd2 = new ClasseDirigida("", "", 1, (new Activitat("", "", 0)), (new Installacio("", "", "")));
        setClasseDirigidaSeleccionada(cd);
        setLlistaClasseDirigida(new ClasseDirigida[]{(cd), (cd2)});
        errorMsg = "";
        eventMsg = "";
    }

    public void setSessioID(String sessioID) {
        if (this.sessioID != sessioID) {
            this.sessioID = sessioID;
            setChanged();
            notificaCanviDades(SESSIO_ID, this.sessioID);
            this.sessioID = sessioID;
            setChanged();
            notificaCanviDades(DADA_CONSOLA_LOG, "Dada modificada: " + SESSIO_ID);
        }
    }

    public Usuari[] getLlistaUsuaris() {
        return llistaUsuaris;
    }

    public void setLlistaUsuaris(Usuari[] llistaUsuaris) {
        if (this.llistaUsuaris != llistaUsuaris) {
            this.llistaUsuaris = llistaUsuaris;
            setChanged();
            notificaCanviDades(USUARI_LLISTA, this.llistaUsuaris);
            setChanged();
            notificaCanviDades(DADA_CONSOLA_LOG, "Dada modificada: " + USUARI_LLISTA);
        }
    }

    public Activitat[] getLlistaActivitats() {
        return llistaActivitats;
    }

    public void setLlistaActivitats(Activitat[] llistaActivitats) {
        if (this.llistaActivitats != llistaActivitats) {
            this.llistaActivitats = llistaActivitats;
            setChanged();
            notificaCanviDades(ACTIVITAT_LLISTA, this.llistaActivitats);
            setChanged();
            notificaCanviDades(DADA_CONSOLA_LOG, "Dada modificada: " + ACTIVITAT_LLISTA);
        }
    }

    public void setErrorMsg(String errorMsg) {
        if (this.errorMsg != errorMsg) {
            this.errorMsg = errorMsg;
            setChanged();
            notificaCanviDades("error", this.errorMsg);
            System.out.println("**ERROR**    ---- " + errorMsg);
            this.errorMsg = "";
        }
    }

    public void setEventMsg(String eventMsg) {
        if (this.eventMsg != eventMsg) {
            this.eventMsg = eventMsg;
            setChanged();
            notificaCanviDades(EVENT, this.eventMsg);
            System.out.println("**EVENT**    ---- " + eventMsg);
            this.eventMsg = "";
        }
    }

    public void setCanviInfoUsuariActiu(boolean canviInfoUsuariActiu) {
        if (this.canviInfoUsuariActiu != canviInfoUsuariActiu) {
            setChanged();
            notificaCanviDades(INFO_USUARI, this.usuariActiu);
        }
    }

    public String getSessioID() {
        return sessioID;
    }

    public void setUsuariActiu(Usuari usuariActiu) {
        if (this.usuariActiu != usuariActiu) {
            if (usuariActiu == null) ;
            this.usuariActiu = usuariActiu;
            setChanged();
            notificaCanviDades(USUARI_ACTIU, this.usuariActiu);
            setChanged();
            notificaCanviDades(DADA_CONSOLA_LOG, "Dada modificada: " + USUARI_ACTIU);
        }
    }

    public void setUsuariSeleccionat(Usuari usuariSeleccionat) {
        if (this.usuariSeleccionat != usuariSeleccionat) {
            this.usuariSeleccionat = usuariSeleccionat;
            setChanged();
            notificaCanviDades(USUARI_SELECT, this.usuariSeleccionat);
            setChanged();
            notificaCanviDades(DADA_CONSOLA_LOG, "Dada modificada: " + USUARI_SELECT);
        }
    }

    public void setActivitatSeleccionada(Activitat activitatSeleccionada) {
        if (this.activitatSeleccionada != activitatSeleccionada) {
            if (activitatSeleccionada == null) ;
            this.activitatSeleccionada = activitatSeleccionada;
            setChanged();
            notificaCanviDades(ACTIVITAT_SELECT, this.activitatSeleccionada);
            setChanged();
            notificaCanviDades(DADA_CONSOLA_LOG, "Dada modificada: " + ACTIVITAT_SELECT);

        }
    }

    public void setInstallacioSeleccionada(Installacio installacioSeleccionada) {
        if (this.installacioSeleccionada != installacioSeleccionada) {
            if (installacioSeleccionada == null) ;
            this.installacioSeleccionada = installacioSeleccionada;
            setChanged();
            notificaCanviDades(INSTALLACIO_SELECT, this.installacioSeleccionada);
            setChanged();
            notificaCanviDades(DADA_CONSOLA_LOG, "Dada modificada: " + INSTALLACIO_SELECT);
        }
    }

    public Installacio[] getLlistaInstallacio() {
        return llistaInstallacio;
    }

    public void setLlistaInstallacio(Installacio[] llistaInstallacio) {
        if (this.llistaInstallacio != llistaInstallacio) {
            this.llistaInstallacio = llistaInstallacio;
            setChanged();
            notificaCanviDades(INSTALLACIO_LLISTA, this.llistaInstallacio);
            setChanged();
            notificaCanviDades(DADA_CONSOLA_LOG, "Dada modificada: " + INSTALLACIO_LLISTA);
        }
    }

    public ClasseDirigida getClasseDirigidaSeleccionada() {
        return classeDirigidaSeleccionada;
    }

    public void setClasseDirigidaSeleccionada(ClasseDirigida classeDirigidaSeleccionada) {
        if (this.classeDirigidaSeleccionada != classeDirigidaSeleccionada) {
            this.classeDirigidaSeleccionada = classeDirigidaSeleccionada;
            setChanged();
            notificaCanviDades(CLASSE_DIRIGIDA, this.classeDirigidaSeleccionada);
            setChanged();
            notificaCanviDades(DADA_CONSOLA_LOG, "Dada modificada: " + CLASSE_DIRIGIDA);

        }
    }

    public ClasseDirigida[] getLlistaClasseDirigida() {
        return llistaClasseDirigida;
    }

    public void setLlistaClasseDirigida(ClasseDirigida[] llistaClasseDirigida) {
        if (this.llistaClasseDirigida != llistaClasseDirigida) {
            this.llistaClasseDirigida = llistaClasseDirigida;
            setChanged();
            notificaCanviDades(CLASSE_DIRIGIDA_LLISTA, this.llistaClasseDirigida);
            setChanged();
            notificaCanviDades(DADA_CONSOLA_LOG, "Dada modificada: " + CLASSE_DIRIGIDA_LLISTA);

        }
    }
}
