/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.vir.client.menu;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author JEY
 */
public class AppManager {

    Socket server;
    PrintWriter output;
    BufferedReader input;
    String menuInput;
    String inString;
    InputStream is = null;
    byte[] aByte = new byte[1];
    int bytesRead;
    String appName;
    Process p;

    public AppManager(Socket server, String menuInput) {
        this.server = server;
        this.menuInput = menuInput;
    }

    public String retrieveAppList() {
        System.out.println("Retrieving App List..");
        try {
            // open a new PrintWriter and BufferedReader on the socket
            output = new PrintWriter(server.getOutputStream(), true);
            input = new BufferedReader(new InputStreamReader(server.getInputStream()));
            output.println("GetApp:" + menuInput);
            while (inString == null) {
                inString = input.readLine();
            };
        } catch (IOException e) {
            System.out.println("IO Exception: " + e.getMessage());
        }
        return inString;
    }

    public void retrieveApp(String appName) {
        this.appName = appName;
        System.out.println("Retrieveing App..." + appName);
        String fileOutput = "D:/" + appName;
        try {
            output = new PrintWriter(server.getOutputStream(), true);
            output.println("RetrieveApp:" + appName);
            is = server.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            if (is != null) {

                FileOutputStream fos = null;
                BufferedOutputStream bos = null;
                try {
                    fos = new FileOutputStream(fileOutput);
                    bos = new BufferedOutputStream(fos);
                    bytesRead = is.read(aByte, 0, aByte.length);

                    do {
                        baos.write(aByte);
                        bytesRead = is.read(aByte);
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
    }

    public void executeApp(int time) {
        timerNotification(time);
        startTimer(time);
        try {
            p = Runtime.getRuntime().exec(
                    "\"d:/" + appName + "\"");
        } catch (IOException ex) {
            System.out.println("IOException: " + ex.getMessage());
        } 
    }

    private void timerNotification(int time) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                System.out.println("Your program will end in 5 minutes please save your file and continue.");
            }
        }, (time - 1) * 60 * 1000);
    }

    private void startTimer(int time) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                try {
                    System.out.println("Your program will end now.");
                    p.destroy();
                    String app = appName.substring(0, appName.indexOf('.'));
                    Runtime.getRuntime().exec("taskkill /F /IM " + (app) + ".exe");
                } catch (IOException ex) {
                    System.out.println("IO Exception : " + ex.getMessage());
                }
            }
        }, (time) * 60 * 1000);
    }

    public void deleteApp() {
        File file = new File("D:\\" + appName);
        if (file.delete()) {
            System.out.println(file.getName() + " is deleted!");
        } else {
            System.out.println("Delete operation is failed.");
        }
    }
}
