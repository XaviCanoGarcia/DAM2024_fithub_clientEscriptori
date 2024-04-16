package Test;

import fithub.clientEscriptori.dades.ControladorDades;
import fithub.clientEscriptori.gui.ControladorGui;
import org.junit.jupiter.api.Test;

public class FerPeticio {

    @Test
    public void peticio_sense_servidor() {
        Object[] peticio = {("login"), ("admin@fithub.es"), ("AdminPass37"), ("2024,1")};
        ControladorDades controladorDades = new ControladorDades(new ControladorGui());
        Object[] rsp = controladorDades.ferPeticio(peticio);
        boolean status = true;
        if (rsp != null) status = false;
        assert status;
    }

}
