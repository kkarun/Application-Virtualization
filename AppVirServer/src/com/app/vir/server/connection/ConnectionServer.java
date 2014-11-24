/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.vir.server.connection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Jey
 */
public class ConnectionServer {

    private ServerSocket socket = null;

    private int port = 0;

    public void startServer(int port) {
        this.port = port;
        try {
            socket = new ServerSocket(port);
            System.out.println("Server listening on port " + port);
        } catch (IOException ioe) {
            System.out.println("IO Exception: " + ioe.getMessage());
        }
    }

    public void closeServerSocket() {
        if (socket != null) {
            try {
                socket.close();
                System.out.println("Server on port " + port + " closed");
            } catch (IOException ex) {
                System.out.println("Server on port " + port + " couldnot be closed, " + ex.getMessage());
            }
        }
    }

    public void listenToClients() {
        Runnable run = new Runnable() {
            public void run() {
                try {
                    while (true) {
                        // accept a new connection
                        Socket client = socket.accept();
                        Thread serverThread = new Thread(new ServerThread(client));
                        serverThread.start();
                    }
                } catch (IOException ioe) {
                    System.out.println("IO Exception: " + ioe.getMessage());
                }
            }
        }; // Create runnable
        new Thread(run).start(); // Create thread and start the thread.
    }
}
/* EOF */
