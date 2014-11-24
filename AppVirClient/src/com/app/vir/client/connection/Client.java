/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.vir.client.connection;

/**
 * @author JEY
 */
import com.app.vir.client.menu.*;
import com.app.vir.client.resource.monitor.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;


public class Client {

    String hostName = null;
    Socket socket = null;
    PrintWriter out = null;
    BufferedReader input = null;
    String menuInput = null;
    MainMenu menu = null;
    String appName = null;
    AppManager appManager = null;

    /*public static void main(String[] args) {
        Client client = new Client();
        client.establishConnection();
        client.startResourceMonitor();
        client.menu();
        client.retrieveApp();
        client.deleteApp();
    }*/

   

   

    private void startResourceMonitor() {
        Thread resouceMonitor = new Thread(new Monitor(socket));
        resouceMonitor.start();
    }

    private void menu() {
        menu = new MainMenu();
        menuInput = menu.mainMenu();
    }

    private void retrieveApp() {
        int time = 0;
        appManager = new AppManager(socket, menuInput);
        String result = appManager.retrieveAppList();
        appName = menu.chooseApp(result);
        if (!appName.equalsIgnoreCase("Exit")) {
            time = menu.getTheTime();
            appManager.retrieveApp(appName);
        } else {
            this.menu();
        }
        appManager.executeApp(time);
    }

    private void deleteApp() {
        appManager.deleteApp();
        this.menu();
    }
}
/* EOF */