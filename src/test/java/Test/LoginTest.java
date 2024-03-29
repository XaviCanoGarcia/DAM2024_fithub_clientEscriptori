package Test;

import fithub.clientEscriptori.app.ControladorAplicacio;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Clase de Test del login.
 *
 * @author Xavi Cano Garcia
 * @version 1.0
 */
public class LoginTest {
    /**
     * Test login d'administrador correcte
     */
    @Test
    public void loginAdminCorrecte() {
        String nom = "admin";
        String pass = "pass";
        String out = "1,admin,Xavi Cano";
        ControladorAplicacio controladorAplicacio = new ControladorAplicacio();
        String res = controladorAplicacio.accioLogin(nom, pass);
        assertEquals(out, res);
    }

    /**
     * Test login de client correcte
     */
    @Test
    public void loginClientCorrecte() {
        String nom = "user";
        String pass = "pass";
        String out = "1,client,Xavi Cano";
        ControladorAplicacio controladorAplicacio = new ControladorAplicacio();
        String res = controladorAplicacio.accioLogin(nom, pass);
        assertEquals(out, res);
    }

    /**
     * Test login incorrecte
     */
    @Test
    public void loginIncorrecte() {
        String nom = "josep";
        String pass = "pasaas";
        String out = "-1";
        ControladorAplicacio controladorAplicacio = new ControladorAplicacio();
        String res = controladorAplicacio.accioLogin(nom, pass);
        assertEquals(out, res);
    }

    /**
     * Test login sense dades
     */
    @Test
    public void loginBuit() {
        String nom = "";
        String pass = "";
        String out = "-1";
        ControladorAplicacio controladorAplicacio = new ControladorAplicacio();
        String res = controladorAplicacio.accioLogin(nom, pass);
        assertEquals(out, res);
    }
}
