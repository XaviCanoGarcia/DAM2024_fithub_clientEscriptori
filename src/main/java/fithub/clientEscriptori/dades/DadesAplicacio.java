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

    private int pestanyaActiva = 1;
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
        usuariActiu.setTipus(USUARI_ADMIN);
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
        if (!nomDada.equals("event")) System.out.println("**DATA**    ---- Dada modificada:" + nomDada);
    }

    void inicialitzaDades() {
        setPestanyaActiva(0);
        setUsuariActiu(new Usuari("a", "a"));
        setUsuariSeleccionat(new Usuari("", ""));
        setLlistaUsuaris(new Usuari[]{(new Usuari("", "")), (new Usuari("", ""))});

        errorMsg = "";
        eventMsg = "";
    }

    public int getPestanyaActiva() {
        return pestanyaActiva;
    }

    public void setPestanyaActiva(int pestanyaActiva) {
        if (this.pestanyaActiva != pestanyaActiva) {
            this.pestanyaActiva = pestanyaActiva;
            setChanged();
            notificaCanviDades(PESTANYA, this.pestanyaActiva);
        }
    }

    public Usuari getUsuariActiu() {
        return usuariActiu;
    }

    public void setUsuariActiu(Usuari usuariActiu) {
        if (this.usuariActiu != usuariActiu) {
            if (usuariActiu == null) ;
            this.usuariActiu = usuariActiu;
            setChanged();
            notificaCanviDades(USUARI_ACTIU, this.usuariActiu);
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
        }
    }

    public String getErrorMsg() {
        return errorMsg;
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
            notificaCanviDades("event", this.eventMsg);
            System.out.println("**EVENT**    ---- " + eventMsg);
            this.eventMsg = "";
        }
    }
}
