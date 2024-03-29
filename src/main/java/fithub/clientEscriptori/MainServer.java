/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fithub.clientEscriptori;


import fithub.clientEscriptori.app.Usuari;

import java.io.*;
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
            if (msg[0].equals("login") && msg[1].equals("admin") && msg[2].equals("pass")) {
                rsp[0] = true;
                usuari.setTipus("admin");
                rsp[1] = usuari;
            } else if (msg[0].equals("login") && msg[1].equals("client") && msg[2].equals("pass")) {
                rsp[0] = true;
                usuari.setTipus("client");
                rsp[1] = usuari;
            } else {
                rsp[0] = false;
                rsp[1] = null;
            }
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
