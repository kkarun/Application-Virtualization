/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.vir.server.app.support;

import com.app.vir.util.ClientConstants;
import java.net.Socket;

/**
 * @author JEY
 */
public class CommandParser {

    AppManager appManager;

    public CommandParser(Socket client) {
        appManager = new AppManager(client);
    }

    public String parseCommandsFromClients(String commandString) {
        String result = "";
        if (commandString.equals(ClientConstants.CLIENT_GET_APP_LIST_MSG)) {
            result = appManager.getAppList();
        } else if (commandString.startsWith(ClientConstants.CLIENT_GET_SECIFIC_APP_MSG)) {
            String appName[] = commandString.split("#");
            appManager.sendApp(appName[1]);
        }
        return result;
    }
}
/* EOF */