package fithub.clientEscriptori.app;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.util.HashMap;
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

        if (peticio[2] instanceof Usuari) peticio[2] = usuari_to_map((Usuari) peticio[2]);

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

            if (resposta[1] != null) {
                HashMap<String, String> map = (HashMap<String, String>) resposta[1];
                String objectType;
                objectType = map.get("objectType");

                if (objectType.equals("usuari")) {
                    resposta[1] = map_to_usuari((HashMap<String, String>) resposta[1]);
                }
            }

        } catch (ConnectException cx) {
            throw cx;
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

    /**
     * Aquest metode converteix un objecte tipu usuari a un hashMap
     *
     * @param usuari Objecte usuari que es vol convertir
     * @return usuariMap HashMap que conti les dades de l'objecte usuari
     */
    private HashMap<String, String> usuari_to_map(Usuari usuari) {
        HashMap<String, String> usuariMap = new HashMap<>();
        usuariMap.put("objectType", "usuari");
        usuariMap.put("sessioID", String.valueOf(usuari.getSessioID()));
        usuariMap.put("correu", usuari.getCorreu());
        usuariMap.put("nom", usuari.getNom());
        usuariMap.put("cognoms", usuari.getCognoms());
        usuariMap.put("adreca", usuari.getAdreca());
        usuariMap.put("contrasenya", usuari.getContrasenya());
        usuariMap.put("dataNeixament", usuari.getDataNaixement());
        usuariMap.put("dataInscripcio", usuari.getDataInscripcio());
        usuariMap.put("telefon", usuari.getTelefon());
        usuariMap.put("tipusUsuari", usuari.getTipus());

        return usuariMap;
    }

    /**
     * Aquest metode converteix un hashMap a un objecte tipu usuari
     *
     * @param map Objecte hashMap que es vol convertir en usuari
     * @return usuari Objecte usuari obtingut a partir de les dades del hashMap
     */
    private Usuari map_to_usuari(HashMap<String, String> map) {
        Usuari usuari = new Usuari("", "");
        usuari.setSessioID(Integer.parseInt(map.get("sessioID")));
        usuari.setCorreu(map.get("correu"));
        usuari.setNom(map.get("nom"));
        usuari.setCognoms(map.get("cognoms"));
        usuari.setAdreca(map.get("adreca"));
        usuari.setContrasenya(map.get("contrasenya"));
        usuari.setDataNaixement(map.get("dataNeixament"));
        usuari.setDataInscripcio(map.get("dataInscripcio"));
        usuari.setTelefon(map.get("telefon"));
        usuari.setTipus(map.get("tipusUsuari"));

        return usuari;
    }


}
