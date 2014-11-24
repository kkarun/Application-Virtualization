/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.vir.client.apps;

import com.app.vir.util.ClientConstants;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author Arun
 */
public class ClientAppHelper {
    
    private PrintWriter outputStream;
    private BufferedReader input;
    private InputStream inputStream = null;
    private String appList = null;
    private String appName = null;
    
    String menuInput;
    String inString;
    
    byte[] aByte = new byte[1];
    int bytesRead;
    
    Process p;
    
    
    public String retrieveAppList(Socket socket) {    
        if (socket == null) {
            return "#";
        }   
        System.out.println("Retrieving App List..");
        String serverResponse = null;
        try {
            // open a new PrintWriter and BufferedReader on the socket
            outputStream = new PrintWriter(socket.getOutputStream(), true);
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            outputStream.println(ClientConstants.CLIENT_GET_APP_LIST_MSG);
            
            while (serverResponse == null) {
                serverResponse = input.readLine();
            };
        } catch (IOException e) {
            System.out.println("IO Exception: " + e.getMessage());
        }
        System.out.println("Applist Retrieved is : " + serverResponse);
        this.appList = serverResponse;
        return serverResponse != null ? serverResponse : "";
    }   
    
    public void getSpecificApp(String appName, Socket server) {
        this.appName = appName;
        System.out.println("Retrieveing App..." + appName);
        String fileOutput = "D:/ClientApps/" + appName;
        try {
            outputStream = new PrintWriter(server.getOutputStream(), true);
            System.out.println("The app to be retrieved : " 
                    + ClientConstants.CLIENT_GET_SECIFIC_APP_MSG + appName);
            
            outputStream.println(ClientConstants.CLIENT_GET_SECIFIC_APP_MSG + appName);
            inputStream = server.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            if (inputStream != null) {
                FileOutputStream fos = null;
                BufferedOutputStream bos = null;
                try {
                    fos = new FileOutputStream(fileOutput);
                    bos = new BufferedOutputStream(fos);
                    bytesRead = inputStream.read(aByte, 0, aByte.length);
                    do {
                        baos.write(aByte);
                        bytesRead = inputStream.read(aByte);
                    } while (bytesRead != -1);
                    bos.write(baos.toByteArray());
                    bos.flush();
                    bos.close();
                } catch (IOException ex) {
                    System.out.println("IO Exception: " + ex.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("IO Exception: " + e.getMessage());
        }
        System.out.println ("The App " + appName + " retrieval complete");
    }
    
    public void executeApp(String appName, int time) {
        //timerNotification(time);
        //startTimer(time);
        String appToBeExecuted = "D:/ClientApps/" + appName;
        System.out.println("The application to be executed is " + appToBeExecuted);
        try {
            p = Runtime.getRuntime().exec(appToBeExecuted);
        } catch (IOException ex) {
            System.out.println("IOException: " + ex.getMessage());
        } 
    }
}
/* EOF */