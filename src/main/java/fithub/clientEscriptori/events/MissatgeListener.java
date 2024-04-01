package fithub.clientEscriptori.events;
/**
 * Interficie que defineix els metodes listener.
 *
 * @author Xavi Cano Garcia
 * @version 1.0
 */
public interface MissatgeListener {
    /**
     * Metode listener event dades.
     *
     * @param event Event que s'escolta.
     */
    void dadesEventRebut(MissatgeEvent event);


    /**
     * Metode listener event login.
     *
     * @param event Event que s'escolta.
     */
    void loginEventRebut(LoginEvent event);
}
