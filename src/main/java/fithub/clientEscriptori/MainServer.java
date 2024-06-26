/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fithub.clientEscriptori;

import fithub.clientEscriptori.dades.objectes.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;

import static fithub.clientEscriptori.dades.Constants.*;

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

    private static Object[] simulacioServer(Object[] msg) {
        Object[] rsp = new Object[2];
        Usuari usuariAdmin = new Usuari("Xavi", "Cano Garcia", "03/04/1997", "C/Llorach 18", "978056784", "xcano@gmail.com", "pass", "05/09/2020");
        usuariAdmin.setUsuariID(1);
        usuariAdmin.setTipus(1);
        Usuari usr = new Usuari("", "");
        Usuari usuari1 = new Usuari("Josep", "Lopez", "03/04/1997", "C/Terssol 18", "978056784", "josepLopez@gmail.com", "pass", "05/09/2020");
        usuari1.setUsuariID(2);
        Usuari usuari2 = new Usuari("Maria", "Bonet", "13/12/2000", "C/Major 12", "97800987", "MariaBonet@gmail.com", "pass", "15/07/2020");
        Usuari usuari3 = new Usuari("Albert", "Guspi", "18/02/1993", "C/Vell 1", "979807654", "AlbertGuspi@gmail.com", "pass", "14/02/2019");
        Usuari[] llistaUsuari = new Usuari[10];
        llistaUsuari[0] = usuari1;
        llistaUsuari[1] = usuari2;
        llistaUsuari[2] = usuari3;
        Activitat[] llistaActivitat = new Activitat[4];
        Activitat activitat = new Activitat("tennis", "tennisdesc", 2);
        activitat.setId(1);
        Activitat activitat2 = new Activitat("basquet", "bascdesc", 10);
        activitat2.setId(2);
        Activitat activitat3 = new Activitat("futbol", "futsdesc", 22);
        activitat3.setId(3);
        activitat.setTipusActivitat(1);
        activitat3.setTipusActivitat(1);
        activitat2.setTipusActivitat(1);
        Installacio[] llistaInstallacio = new Installacio[4];
        Installacio installacio1 = new Installacio("Pista tennis", "Pista de tennis descoberta", "exterior");
        installacio1.setId(1);
        Installacio installacio2 = new Installacio("Piscina", "Piscina coberta", "interior");
        installacio2.setId(2);
        Installacio installacio3 = new Installacio("Pista basquet", "Pista basquet", "interior");
        installacio3.setId(3);
        llistaInstallacio[0] = installacio1;
        llistaInstallacio[1] = installacio2;
        llistaInstallacio[2] = installacio3;
        llistaActivitat[0] = activitat;
        llistaActivitat[1] = activitat2;
        llistaActivitat[2] = activitat3;
        ClasseDirigida cd = new ClasseDirigida("28042024", "0900", "1", activitat, installacio1);
        cd.setId(1);
        cd.setOcupacio("1");
        ClasseDirigida cd2 = new ClasseDirigida("28042024", "1000", "2", activitat2, installacio3);
        cd2.setId(2);
        cd2.setOcupacio("2");
        ClasseDirigida[] llistaClasseDirigida = new ClasseDirigida[4];
        llistaClasseDirigida[0] = cd;
        llistaClasseDirigida[1] = cd2;
        Servei srv = new Servei("Monitor", "15€");
        srv.setDescripcio("Monitor per a les classes de gimnas");
        Servei srv2 = new Servei("Dietista", "25€");
        srv2.setDescripcio("Dietista personal");
        Servei srv3 = new Servei("Entrenador", "20€");
        srv3.setDescripcio("Entrenador personal");
        Servei[] llistaServeis = new Servei[4];
        llistaServeis[0] = srv;
        llistaServeis[1] = srv2;
        llistaServeis[2] = srv3;


        if (msg[1].equals(USUARI)) {
            switch ((String) msg[0]) {
                case CMD_NOU:
                    llistaUsuari[3] = usr.map_to_usuari((HashMap<String, String>) msg[2]);
                    rsp[0] = USUARI_LLISTA;
                    rsp[1] = usr.creaLlistaUsuarisMap(llistaUsuari);
                    break;
                case CMD_ELIMINA:
                    break;
                case CMD_MODIFICA:
                    rsp[0] = USUARI;
                    rsp[1] = usr.usuari_to_map(usuari2);
                    break;
                case CMD_SELECT:
                    rsp[0] = USUARI;
                    rsp[1] = usr.usuari_to_map(usuari2);
                    break;
                case CMD_SELECT_ALL:
                    rsp[0] = USUARI_LLISTA;
                    rsp[1] = usr.creaLlistaUsuarisMap(llistaUsuari);
                    break;
            }
            return rsp;
        }
        if (msg[1].equals(ACTIVITAT)) {
            switch ((String) msg[0]) {
                case CMD_NOU:
                    llistaActivitat[3] = activitat.map_to_activitat((HashMap<String, String>) msg[2]);
                    rsp[0] = ACTIVITAT_LLISTA;
                    rsp[1] = activitat.creaLlistaactivitatsMap(llistaActivitat);
                    break;
                case CMD_ELIMINA:
                    break;
                case CMD_MODIFICA:
                    rsp[0] = ACTIVITAT;
                    rsp[1] = activitat.activitat_to_map(activitat2);
                    break;
                case CMD_SELECT:
                    rsp[0] = ACTIVITAT;
                    rsp[1] = activitat.activitat_to_map(activitat2);
                    break;
                case CMD_SELECT_ALL:
                    rsp[0] = ACTIVITAT_LLISTA;
                    rsp[1] = activitat.creaLlistaactivitatsMap(llistaActivitat);
                    break;
            }
            return rsp;
        }
        if (msg[1].equals(INSTALLACIO)) {
            switch ((String) msg[0]) {
                case CMD_NOU:
                    llistaActivitat[3] = activitat.map_to_activitat((HashMap<String, String>) msg[2]);
                    rsp[0] = INSTALLACIO_LLISTA;
                    rsp[1] = installacio1.creaLlistaInstallacioMap(llistaInstallacio);
                    break;
                case CMD_ELIMINA:
                    break;
                case CMD_MODIFICA:
                    rsp[0] = INSTALLACIO;
                    rsp[1] = installacio1.installacio_to_map(installacio2);
                    break;
                case CMD_SELECT:
                    rsp[0] = INSTALLACIO;
                    rsp[1] = installacio1.installacio_to_map(installacio2);
                    break;
                case CMD_SELECT_ALL:
                    rsp[0] = INSTALLACIO_LLISTA;
                    rsp[1] = installacio1.creaLlistaInstallacioMap(llistaInstallacio);
                    break;
            }
            return rsp;
        }
        if (msg[1].equals(CLASSE_DIRIGIDA)) {
            switch (((String) msg[0])) {
                case CMD_SELECT_ALL:
                    rsp[0] = CLASSE_DIRIGIDA_LLISTA;
                    rsp[1] = cd.creaLlistaClasseDirigidaMap(llistaClasseDirigida);
                    break;
            }
        }
        if (msg[1].equals(SERVEI)) {
            switch ((String) msg[0]) {
                case CMD_NOU:
                    llistaServeis[3] = srv.map_to_servei((HashMap<String, String>) msg[2]);
                    rsp[0] = SERVEI_LLISTA;
                    rsp[1] = srv.creaLlistaserveisMap(llistaServeis);
                    break;
                case CMD_ELIMINA:
                    break;
                case CMD_SELECT_ALL:
                    rsp[0] = SERVEI_LLISTA;
                    rsp[1] = srv.creaLlistaserveisMap(llistaServeis);
                    break;
            }
            return rsp;
        }
        //Login
        if (msg[0].equals(CMD_LOGIN) && msg[1].equals("admin@fithub.es") && msg[2].equals("Adminpass40")) {
            rsp[0] = "2024,1";
            rsp[1] = usr.usuari_to_map(usuariAdmin);
        } else if (msg[0].equals(CMD_LOGIN) && msg[1].equals("client@fithub.es") && msg[2].equals("Clientpass40")) {
            rsp[0] = "2024,2";
            usr.setTipus(1);
            rsp[1] = usr.usuari_to_map(usuari1);
        } else if (msg[0].equals(CMD_LOGOUT)) {
            rsp[0] = CMD_LOGOUT;
            rsp[1] = "true";
        }
        return rsp;
    }

    @Override
    public void run() {
        //Missatge d'intercanvi amb el client
        Object[] msg = new Object[3];
        Object[] rsp = new Object[2];
        Usuari usuari = new Usuari("admin@fithub.com", "pass");
        usuari.setUsuariID(1);

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
