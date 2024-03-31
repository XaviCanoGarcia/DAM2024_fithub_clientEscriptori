package fithub.clientEscriptori.app;
import java.util.Observable;

/**
 * Classe que conté les dades de l'aplicació.
 */
class DadesAplicacio extends Observable {

    private int pestanyaActiva = 0;
    private Usuari usuariActiu;
    private Usuari usuariSeleccionat;
    private Usuari[] llistaUsuaris;

    /**
     * Constructor Dades aplicació
     */
    DadesAplicacio() {
        usuariActiu = new Usuari("", "");
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
}
