package fithub.clientEscriptori.app;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase encarregada de enviar y rebre dades amb el servidor.
 *
 * @author Xavi Cano Garcia
 * @version 1.0
 */
public class ParlarAmbServidor {

    //192.168.0.47  Isard server
    //127.0.0.1     Local
    static String ip = "127.0.0.1";
    static int port = 8080;

    String resposta = "";

    /**
     * Obre una connxio amb el servidor, executa la comanda y retorna la resposta.
     *
     * @param missatge Peticio que es vol realitzar al sevidor.
     */
    public void enviarPeticio(String missatge) {
        Socket clientSocket = null;
        Scanner in = null;
        PrintWriter out = null;
        Scanner sc = new Scanner(System.in);
        String resultat;

        try {
            // Conectar al servidor
            clientSocket = new Socket(ip, port);
            System.out.println("***COM***           Client connectant al servidor...");

            in = new Scanner(clientSocket.getInputStream());
            out = new PrintWriter(clientSocket.getOutputStream(), true);

            // Llegir missatge de conexio
            resultat = in.nextLine();
            System.out.println("***COM***           "+resultat);

            // Envia missatge al servidor
            out.println(missatge);

            // Llegeix resposta del servidor
            while (in.hasNextLine()) {
                this.resposta = in.nextLine();
                //System.out.println(resultat);
            }

        } catch (IOException ex) {
            Logger.getLogger(ParlarAmbServidor.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            try {
                // Cerrar recursos y el socket
                if (sc != null) sc.close();
                if (in != null) in.close();
                if (out != null) out.close();
                if (clientSocket != null) clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
