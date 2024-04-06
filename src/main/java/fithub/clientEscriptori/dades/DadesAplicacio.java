package fithub.clientEscriptori.dades;

import java.util.Observable;

/**
 * Classe que conté les dades de l'aplicació.
 *
 * @author Xavi Cano
 * @version 1.0
 */
public class DadesAplicacio extends Observable {

    private int pestanyaActiva = 0;
    private Usuari usuariActiu;
    private Usuari usuariSeleccionat;
    private Usuari[] llistaUsuaris;
    private String errorMsg;

    private String eventMsg;

    /**
     * Constructor Dades aplicació
     */
    public DadesAplicacio() {
        usuariActiu = new Usuari("", "");
        usuariActiu.setTipus("admin");
        usuariSeleccionat = new Usuari("", "");
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
    }

    void inicialitzaDades() {
        setPestanyaActiva(0);
        setUsuariActiu(new Usuari("", ""));
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
            notificaCanviDades("pestanyaActiva", this.pestanyaActiva);
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
            notificaCanviDades("usuariActiu", this.usuariActiu);
        }
    }

    public Usuari getUsuariSeleccionat() {
        return usuariSeleccionat;
    }

    public void setUsuariSeleccionat(Usuari usuariSeleccionat) {
        if (this.usuariSeleccionat != usuariSeleccionat) {
            this.usuariSeleccionat = usuariSeleccionat;
            setChanged();
            notificaCanviDades("usuariSeleccionat", this.usuariSeleccionat);
        }
    }

    public Usuari[] getLlistaUsuaris() {
        return llistaUsuaris;
    }

    public void setLlistaUsuaris(Usuari[] llistaUsuaris) {
        if (this.llistaUsuaris != llistaUsuaris) {
            this.llistaUsuaris = llistaUsuaris;
            setChanged();
            notificaCanviDades("llistaUsuaris", this.llistaUsuaris);
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
