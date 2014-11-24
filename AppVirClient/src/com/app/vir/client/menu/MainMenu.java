/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.vir.client.menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author JEY
 */
public class MainMenu {
    BufferedReader br;
    String menuInput;
    int AppInput;
    int time;
    public MainMenu(){
        menuInput = null;
        br = new BufferedReader(new InputStreamReader(System.in));
    }
    
    public String mainMenu(){
        System.out.println("Welcome to AppVirtu...");
        do{
              if(menuInput != null){
                System.out.println("Please Enter the Correct option..");
            }
        System.out.println("Options : " + "\n 1. Rent a Software" + "\n 2. Rent resource and software");
        try {
            menuInput = br.readLine();
        } catch (IOException ex) {
            System.out.println("IO Exception: " + ex.getMessage());
        }
        }while(!menuInput.contentEquals("1") && !menuInput.contentEquals("2"));
        return menuInput;
    }
    
    public String chooseApp(String AppList){
        String App[] = AppList.split(",");
        System.out.println("The Application are: ");
        int count = 1;
        for(String appName : App){
            System.out.println(count + "." +appName);
            count++;
        }
        System.out.println(count + "." + "Exit");
        try {
            AppInput = Integer.parseInt(br.readLine());
        } catch (IOException ex) {
            System.out.println("IO Exception: " + ex.getMessage());
        }
        if(AppInput > App.length){
            return "Exit";
        } else {
            return App[AppInput-1];
        }
    }
    
    public int getTheTime(){
        do{
        System.out.println("Please enter the time (mins) you want to use the software: ");
        try {
            time = Integer.parseInt(br.readLine());
        } catch (IOException ex) {
             System.out.println("IO Exception: " + ex.getMessage());
        }
        if(time < 2){
            System.out.println("Please enter the value greater than 10 minutes.");
        }
        }while(time < 2);
        return time;
   }
}
/* EOF */