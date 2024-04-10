package fithub.clientEscriptori.dades;

import fithub.clientEscriptori.dades.objectes.Activitat;
import fithub.clientEscriptori.dades.objectes.Usuari;

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
    private Usuari usuariSeleccionat;
    private Usuari[] llistaUsuaris;
    private Activitat activitatSeleccionada;
    private Activitat[] llistaActivitats;
    private String errorMsg;

    private String eventMsg;

    /**
     * Constructor Dades aplicació
     */
    public DadesAplicacio() {
        usuariActiu = new Usuari("", "");
        usuariActiu.setTipus(1);
        usuariSeleccionat = new Usuari("", "");
        activitatSeleccionada = new Activitat("", "", 0);
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
        if (!nomDada.equals(EVENT)) System.out.println("**DATA**     ---- Dada modificada: " + nomDada);
    }

    void inicialitzaDades() {
        setSessioID("");
        setUsuariActiu(new Usuari("a", "a"));
        setUsuariSeleccionat(new Usuari("", ""));
        setLlistaUsuaris(new Usuari[]{(new Usuari("", "")), (new Usuari("", ""))});
        setActivitatSeleccionada(new Activitat("", "", 0));
        setLlistaActivitats(new Activitat[]{(new Activitat("", "", 0)), new Activitat("", "", 0)});
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

    public Usuari getUsuariActiu() {
        return usuariActiu;
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

    public Usuari getUsuariSeleccionat() {
        return usuariSeleccionat;
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

    public Activitat getActivitatSeleccionada() {
        return activitatSeleccionada;
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

    public String getErrorMsg() {
        return errorMsg;
    }
}
