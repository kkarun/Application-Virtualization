/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.vir.util;

import static com.app.vir.util.ClientConstants.LINUX;
import static com.app.vir.util.ClientConstants.WINDOWS;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author Arun
 */
public class HostDetailsUtil {

    private static String machineName = null;

    public static String getMachineName() {
        System.out.println(System.getProperty("os.name"));
        return machineName == null ? machineName = System.getProperty("os.name")
                : machineName;
    }

    public static String getMachineIp() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

	// Should Return true for all the distro's of Linux
    // need to be tested
    public static boolean isLinux() {
        getMachineName();
        return machineName != null ? (machineName.startsWith(LINUX) ? true
                : false) : false;
    }

    public static boolean isWindows() {
        getMachineName();
        return machineName != null ? (machineName.startsWith(WINDOWS) ? true
                : false) : false;
    }

    public static boolean isMac() {
        // TODO Implementation needed
        return false;
    }

    public static boolean isSolaris() {
        // TODO Implementation needed
        return false;
    }
}
