package com.app.vir.client.resource.monitor;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author JEY
 */
public class Monitor extends Thread {
    Socket server;
    PrintWriter output;
    BufferedReader input;
    
    public Monitor(Socket socket){
        this.server = socket;
    }
    
    public void run(){
//        System.out.println("Starting Resource Monitoring..");
//       try {
//			// open a new PrintWriter and BufferedReader on the socket
//			output = new PrintWriter(server.getOutputStream(), true);
//			input = new BufferedReader(new InputStreamReader(server.getInputStream()));
//			String inString;
//			// read the command from the client
//		        while  ((inString = input.readLine()) == null);
//			System.out.println("Read command " + inString);
//                        
//		}
//		catch (IOException e) {
//			System.out.println("IO Exception: " + e.getMessage());
//		} 
    }
}
