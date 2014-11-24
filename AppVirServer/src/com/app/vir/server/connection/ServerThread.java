/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.vir.server.connection;

/**
 * @author JEY
 */
import com.app.vir.server.app.support.CommandParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/*
 * Individual ServerThread listens for the client to tell it what command to parseCommandsFromClients, then
 * runs that command and sends the output of that command to the client
 *
 */
public class ServerThread extends Thread {

    Socket client = null;
    PrintWriter output;
    BufferedReader input;

    public ServerThread(Socket client) {
        this.client = client;
    }

    public void run() {
        System.out.print("Accepted client connection. ");
        try {
            // open a new PrintWriter and BufferedReader on the socket
            System.out.println("The Client: " + client.getRemoteSocketAddress().toString() + " is connected.");
            output = new PrintWriter(client.getOutputStream(), true);
            input = new BufferedReader(new InputStreamReader(client.getInputStream()));
            System.out.print("Reader and writer created. ");
            String inString;
            // read the command from the client
            do {
                while ((inString = input.readLine()) == null);
                System.out.println("Read command " + inString);
                CommandParser cmdParser = new CommandParser(client);
                String result = cmdParser.parseCommandsFromClients(inString);
                output.println(result);
            } while (true);//change it

        } catch (IOException e) {
            System.out.println("IO Exception: " + e.getMessage());
        } finally {
            // close the connection to the client
            try {
                client.close();
            } catch (IOException e) {
                System.out.println("IO Exception: " + e.getMessage());
            }
            System.out.println("Output closed.");
        }
    }
}
/* EOF */
