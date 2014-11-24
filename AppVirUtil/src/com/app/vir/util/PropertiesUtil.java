/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.vir.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Arun
 */
public class PropertiesUtil {
    // TODO Move all the property access code here

    public static Properties getProperty(String fileName) {
        Properties props = new Properties();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(fileName);
        } catch (FileNotFoundException ex) {
            System.out.println("The  file " + fileName + " not exists");
        }

        try {
            props.load(fis);
        } catch (IOException ex) {
            System.out.println("The properties file not be loaded");
        }
        return props;
    }

    public static boolean saveProperties(String fileName, Properties properties) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(fileName);
            properties.store(fos, null);
        } catch (FileNotFoundException ex) {
            System.out.println("The server configuration properties file not exists");
        } catch (IOException ioe) {
            System.out.println("The property file configuration wrintng error");
        } finally {
            try {
                fos.close();
            } catch (IOException ex) {
                //Logger.getLogger(AppVirClientConfigJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
}
/* EOF */
