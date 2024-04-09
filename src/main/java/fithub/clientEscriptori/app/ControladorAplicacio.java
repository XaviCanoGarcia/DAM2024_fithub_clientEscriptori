package fithub.clientEscriptori.app;

import fithub.clientEscriptori.dades.Constants;
import fithub.clientEscriptori.dades.ControladorDades;
import fithub.clientEscriptori.events.MissatgeEvent;
import fithub.clientEscriptori.events.MissatgeListener;
import fithub.clientEscriptori.gui.ControladorGui;

import static fithub.clientEscriptori.dades.Constants.*;


/**
 * Clase arrel de l'aplicacio conté tots els elements.
 * Aquesta classe escolta els events generats per l'aplicació
 *
 * @author Xavi Cano Garcia
 * @version 1.0
 */
public class ControladorAplicacio implements MissatgeListener {
    ControladorGui controladorGui;
    ControladorDades controladorDades;

    /**
     * Constructor objecte ControladorAplicació.
     */
    public ControladorAplicacio() {
        controladorGui = new ControladorGui();
        controladorGui.getControladorPanells().getLoginForm().setListenerMsg(this);
        controladorGui.getControladorPanells().getMainAdminForm().setListenerMsg(this);
        //controladorGui.getControladorPanells().getMainUser().setListener(this);

        controladorDades = new ControladorDades(controladorGui);

    }

    /**
     * Executa l'acció al produir-se un event de tipus dades.
     * Fa peticions al servidor i actualitza les dades amb la resposta
     *
     * @param event Event de dades escoltat.
     */
    @Override
    public void dadesEventRebut(MissatgeEvent event) {
        Object[] peticio = event.getMissatge();
        String cmd = (String) peticio[0];
        String param = (String) peticio[1];
        Object dada = peticio[2];
        controladorDades.getDades().setEventMsg("Generat per l'usuari: " + peticio[0] + " " + peticio[1]);

        //Accio Logout
        if (cmd.equals(CMD_LOGOUT)) {
            controladorDades.accioLogout();
            return;
        }
        //Seleccio amb el mouse un usuari de la taula
        if (cmd.equals(CMD_MOUSE) && peticio[1].equals(USUARI_SELECT)) {
            int numUsuariTaulaSeleccionat = (int) peticio[2];
            if (numUsuariTaulaSeleccionat < controladorDades.getDades().getLlistaUsuaris().length) {
                if (controladorDades.getDades().getLlistaUsuaris()[numUsuariTaulaSeleccionat] != null) {
                    controladorDades.getDades().setUsuariSeleccionat(controladorDades.getDades().getLlistaUsuaris()[numUsuariTaulaSeleccionat]);

                }
            }
            return;
        }
        //Seleccio amb el mouse d'una activitat de la taula
        if (cmd.equals(CMD_MOUSE) && peticio[1].equals(ACTIVITAT_SELECT)) {
            int numTaulaSeleccionat = (int) peticio[2];
            if (numTaulaSeleccionat < controladorDades.getDades().getLlistaActivitats().length) {
                if (controladorDades.getDades().getLlistaActivitats()[numTaulaSeleccionat] != null) {
                    controladorDades.getDades().setActivitatSeleccionada(controladorDades.getDades().getLlistaActivitats()[numTaulaSeleccionat]);

                }
            }
            return;
        }
        //Petició al servidor
        controladorDades.crearPeticio(peticio);
    }
}


