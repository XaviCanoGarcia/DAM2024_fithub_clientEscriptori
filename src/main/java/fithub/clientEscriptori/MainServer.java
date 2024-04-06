/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fithub.clientEscriptori;


import fithub.clientEscriptori.dades.ConversorDades;
import fithub.clientEscriptori.dades.Usuari;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class MainServer {

    static int puerto = 8080;
    static String ip = "127.0.0.1";

    public static void main(String[] args) {
        try {
            InetAddress direccionIP = InetAddress.getByName(ip); // Cambia a la IP que deseas usar
            InetSocketAddress direccion = new InetSocketAddress(direccionIP, puerto);
            ServerSocket server = new ServerSocket();
            server.bind(direccion);
            int i = 0;

            while (true) {

                System.out.println("Esperant client...");
                //Accepta conexio del client
                Socket socket = server.accept();
                System.out.println("***server-event***       Client connectat " + i);
                i++;
                new ThreadClient(socket).start();  // Inicia un fil per a la conexio del client
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

class ThreadClient extends Thread {
    public static final ConversorDades conversorDades = new ConversorDades();
    private final Socket clientSocket;
    //Handshake
    private final Scanner inHS;
    private final PrintWriter outHS;
    //Missatge
    private final ObjectInputStream in;
    private final ObjectOutputStream out;

    public ThreadClient(Socket client) throws IOException {
        this.clientSocket = client;
        this.inHS = new Scanner(client.getInputStream());
        this.outHS = new PrintWriter(client.getOutputStream(), true);
        this.out = new ObjectOutputStream(client.getOutputStream());
        this.in = new ObjectInputStream(client.getInputStream());

    }

    private static Object[] simulacioServer(Object[] msg) {
        Object[] rsp = new Object[2];
        Usuari usuariAdmin = new Usuari("Xavi", "Cano Garcia", "03/04/1997", "C/Llorach 18", "978056784", "xcano@gmail.com", "pass", "05/09/2020");
        usuariAdmin.setSessioID(1);
        usuariAdmin.setTipus("admin");
        Usuari usuari = new Usuari("", "");
        Usuari usuari1 = new Usuari("Josep", "Lopez", "03/04/1997", "C/Terssol 18", "978056784", "josepLopez@gmail.com", "pass", "05/09/2020");
        usuari1.setSessioID(2);
        Usuari usuari2 = new Usuari("Maria", "Bonet", "13/12/2000", "C/Major 12", "97800987", "MariaBonet@gmail.com", "pass", "15/07/2020");
        Usuari usuari3 = new Usuari("Albert", "Guspi", "18/02/1993", "C/Vell 1", "979807654", "AlbertGuspi@gmail.com", "pass", "14/02/2019");
        Usuari[] llistaUsuari = new Usuari[10];
        llistaUsuari[0] = usuari1;
        llistaUsuari[1] = usuari2;
        llistaUsuari[2] = usuari3;

        if (msg[1].equals("usuari")) {
            switch ((String) msg[0]) {
                case "insert":
                    llistaUsuari[3] = (Usuari) msg[2];
                    rsp[0] = true;
                    rsp[1] = llistaUsuari;
                    break;
                case "delete":
                    break;
                case "modifica":
                    rsp[0] = "usuari";
                    rsp[1] = usuari2;
                    break;
                case "select":
                    rsp[0] = "usuari";
                    rsp[1] = usuari1;
                    break;
                case "selectAll":
                    rsp[0] = "usuariList";
                    rsp[1] = conversorDades.creaLlistaUsuarisMap(llistaUsuari);
                    ;
                    break;
            }
        }
        //Login
        if (msg[0].equals("login") && msg[1].equals("admin") && msg[2].equals("pass")) {
            rsp[0] = "usuariActiu";
            rsp[1] = conversorDades.usuari_to_map(usuariAdmin);
        } else if (msg[0].equals("login") && msg[1].equals("client") && msg[2].equals("pass")) {
            rsp[0] = "usuariActiu";
            usuari.setTipus("client");
            rsp[1] = conversorDades.usuari_to_map(usuari1);
        }
        return rsp;
    }

    @Override
    public void run() {
        //Missatge d'intercanvi amb el client
        Object[] msg = new Object[3];
        Object[] rsp = new Object[2];
        Usuari usuari = new Usuari("admin@fithub.com", "pass");
        usuari.setSessioID(1);

        // Envia missatge de conectat al client
        outHS.println("Client connectat");
        //Llegeix missatge i envia resposta
        try {
            msg = (Object[]) in.readObject();   //Llegeix missatge
            rsp = simulacioServer(msg);
            out.writeObject((Object[]) rsp);    //Envia resposta

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (in != null) in.close();
                if (out != null) out.close();
                if (inHS != null) inHS.close();
                if (outHS != null) outHS.close();
                if (clientSocket != null) clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
