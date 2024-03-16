package fithub.clientEscriptori.app;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ParlarAmbServidor {

    static int port = 8080;
    static String ip = "127.0.0.1";

    public ParlarAmbServidor() {

    }

    public String enviarPeticio(String missatge){
        Socket clientSocket = null;
        Scanner in = null;
        PrintWriter out = null;
        Scanner sc = new Scanner(System.in);
        String resultat = "";

        try {
            // Conectar al servidor
            clientSocket = new Socket(ip, port);
            System.out.println("Client connectant al servidor...");

            in = new Scanner(clientSocket.getInputStream());
            out = new PrintWriter(clientSocket.getOutputStream(), true);

            // Llegir missatge de conexio
            resultat = in.nextLine();
            System.out.println(resultat);

            // Envia missatge al servidor
            out.println(missatge);

            // Llegeix resposta del servidor
            while (in.hasNextLine()) {
                resultat = in.nextLine();
                System.out.println(resultat);
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
        return resultat;
    }
}
