/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.vir.client.connection;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author Jey
 */
public class ConnectionClient {

    private Socket socket = null;

    private static String hostName = null;
    
    private static int port = 0;
    
    private boolean isConnected = false;
    
    public ConnectionClient(String hostName, int port) {
        this.hostName = hostName;
        this.port = port;
    }

    public void connectToServer() {
        try {
            System.out.println("Trying to establish connection with server....");
            socket = new Socket(hostName, port);
            isConnected = true;
            System.out.println("connection Established Successfully...");
        } catch (UnknownHostException e) {
            System.out.println("Unknown host: " + hostName);
        } catch (ConnectException e) {
            System.out.println("Connection refused by host: " + hostName);
        } catch (IOException e) {
            System.out.println("IO Exception: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
        }
    }

    public void closeClientConnection() {
        try {
            socket.close();
        } catch (IOException e) {
            System.out.println("Couldn't close socket");
        }
    }

    public Socket getServerConection() {
        return socket;
    }

    public boolean isConnectedToServer() {
        int i = 0;
        do {
            if (socket == null || !socket.isConnected()) {
                connectToServer();
                if (isConnected)
                    break;
            }
        } while (i++ < 3);
        return isConnected;
    }
}
/* EOF */
