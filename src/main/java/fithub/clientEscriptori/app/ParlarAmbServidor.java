package fithub.clientEscriptori.app;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase encarregada de enviar y rebre dades amb el servidor.
 *
 * @author Xavi Cano Garcia
 * @version 1.1
 */
public class ParlarAmbServidor {

    //192.168.0.47  Isard server
    //127.0.0.1     Local
    static String ip = "127.0.0.1";
    static int port = 8080;

    String respostaHS = "";
    Object[] resposta;

    /**
     * Obre una connxio amb el servidor, executa la comanda y retorna la resposta.
     *
     * @param peticio Peticio que es vol realitzar al sevidor.
     * @return Retorna la resposta del servidor.
     */
    public Object[] enviarPeticio(Object[] peticio) throws ConnectException {
        Socket clientSocket = null;
        //Handshake
        Scanner inHS = null;
        //Missatge
        ObjectInputStream in = null;
        ObjectOutputStream out = null;

        try {
            // Conectar al servidor
            clientSocket = new Socket(ip, port);
            System.out.println("***COM***           Client connectant al servidor...");

            inHS = new Scanner(clientSocket.getInputStream());
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            in = new ObjectInputStream(clientSocket.getInputStream());


            // Llegir missatge de conexio
            respostaHS = inHS.nextLine();
            System.out.println("***COM***           " + respostaHS);

            // Envia missatge al servidor
            out.writeObject(peticio);

            // Llegeix resposta del servidor
            resposta = (Object[]) in.readObject();

        } catch (ConnectException cx) {
            throw cx;
        } catch (EOFException eq) {

        } catch (IOException ex) {
            Logger.getLogger(ParlarAmbServidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (in != null) in.close();
                if (inHS != null) inHS.close();
                if (out != null) out.close();
                if (clientSocket != null) clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resposta;
    }
}
