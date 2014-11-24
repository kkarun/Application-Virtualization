/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.vir.server.app.support;

import com.app.vir.server.ui.AppVirServerJFrame;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JEY
 */
public class AppManager {

    File[] files;
    Socket client;
    BufferedOutputStream outToClient;
    String fileToSend;

    public AppManager(Socket client) {
        files = null;
        this.client = client;
        outToClient = null;
        fileToSend = null;
    }

    public String getAppList() {
        return AppVirServerJFrame.getAppListString() != null ? 
                AppVirServerJFrame.getAppListString() : "#";
    }

    // TODO Requires change in logic
    public void sendApp(String appName) {
        System.out.println("Server to send : " + appName);
        files = new File("D:/VirtualApps/").listFiles();
        for (File file : files) {
            if (file.isFile()) {
                if (file.getName().contains(appName)) {
                    try {
                        fileToSend = file.getCanonicalPath();
                    } catch (IOException ex) {
                        System.out.println("Couldn't get the canonical path for the file :" + appName);
                    }
                }
            }
        }
        if (fileToSend == null) {
            return;
        }
        System.out.println("Server to send [Full path] : " + fileToSend);
        try {
            outToClient = new BufferedOutputStream(client.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(AppManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (outToClient != null) {
            File myFile = new File(fileToSend);
            byte[] mybytearray = new byte[(int) myFile.length()];

            FileInputStream fileInputStream = null;

            try {
                fileInputStream = new FileInputStream(myFile);
            } catch (FileNotFoundException ex) {
                // Do exception handling
            }
            BufferedInputStream bis = new BufferedInputStream(fileInputStream);

            try {
                bis.read(mybytearray, 0, mybytearray.length);
                outToClient.write(mybytearray, 0, mybytearray.length);
                outToClient.flush();
                outToClient.close();
                // File sent, exit the main method
                return;
            } catch (IOException ex) {
                // Do exception handling
            }
        }
    }
}
/* EOF */
