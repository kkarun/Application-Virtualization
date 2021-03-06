/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.vir.client.ui;

import com.app.vir.client.apps.ClientAppHelper;
import com.app.vir.client.connection.ConnectionClient;
import com.app.vir.util.ClientConstants;
import com.app.vir.util.PropertiesUtil;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import javax.swing.DefaultListModel;

/**
 * @author Arun
 */
public class AppVirClientJFrame extends javax.swing.JFrame {

    private List<String> appList = null;
    private String serverIp;
    private String serverPort;
    private Socket clientSocket;

    //private boolean isServerConnected = false; 
    /**
     * Creates new form AppVirClientUI
     */
    public AppVirClientJFrame() {
        initComponents();

        if (!isClientConfigured()) {
            System.out.println("Client not configured");
            configureClient();
        }
        // Check connection status, if no connection exists create a new connection
        setServerConnectionStatus();
        //Retrieve the application list from server
        System.out.println("Going to retrieve App List from server");
        appList = getAppListFromServer();
        // populate the applications from the server
        System.out.println("AppList retrieved is : " + appList);
        populateAppListToClient(appList);
    }

    private void loadConfigProps() {
        Properties props = PropertiesUtil.getProperty(ClientConstants.CONNECTION_PROP_FILE_NAME);
        serverIp = props.getProperty(ClientConstants.SERVER_IP_PROP);
        System.out.println("serverIP Read : " + serverIp);
        serverPort = props.getProperty(ClientConstants.SERVER_PORT_PROP);
        System.out.println("serverPort Read : " + serverPort);
    }

    private boolean isClientConfigured() {
        loadConfigProps();
        if (serverIp != null && serverPort != null && !serverIp.isEmpty() && !serverPort.isEmpty()) {
            System.out.println("All configured, Client can launch");
            return true;
        }
        System.out.println("Client needs to be configured with server ip, port [default port 8900]");
        return false;
    }

    private void configureClient() {
        new AppVirClientConfigJFrame().setVisible(true);
        /* Create and display the form */
        /* java.awt.EventQueue.invokeLater(new Runnable() {
         public void run() {
         new AppVirClientConfigJFrame().setVisible(true);         
         }
         }); */
    }

    private void setServerConnectionStatus() {
        ConnectionClient cc = new ConnectionClient(serverIp, Integer.parseInt(serverPort));
        cc.connectToServer();
        clientSocket = cc.getServerConection();
        if (cc.isConnectedToServer()) {
            jServerStatusLabel.setText("Server Status : Connected");
        } else {
            jServerStatusLabel.setText("Server Status : Not Connected");
        }
    }

    private List<String> getAppListFromServer() {
        ClientAppHelper appHelper = new ClientAppHelper();
        String appListStr = appHelper.retrieveAppList(clientSocket);
        List<String> appList = new ArrayList(Arrays.asList(appListStr.split("#")));
        return appList;
    }

    private void populateAppListToClient(List<String> apps) {
        System.out.println("Apps going to get set to JList : " + apps);

        DefaultListModel model = new DefaultListModel();
        jVirtualAppList.setModel(model);
        if (clientSocket != null && clientSocket.isConnected()) {
            for (String fileName : apps) {
                System.out.println(fileName + " getting added to the client list");
                model.addElement(fileName);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem2 = new javax.swing.JMenuItem();
        jAppVirClientPanel = new javax.swing.JPanel();
        jApplistScrollPane1 = new javax.swing.JScrollPane();
        jVirtualAppList = new javax.swing.JList();
        jAppSelectionButton = new javax.swing.JButton();
        jAppListLabel = new javax.swing.JLabel();
        jServerStatusLabel = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMainMenu = new javax.swing.JMenu();
        jRefreshAppMenuItem = new javax.swing.JMenuItem();
        jExitClientMenuItem = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jConfigureClientMenuItem = new javax.swing.JMenuItem();
        jReconnectServerMenuItem = new javax.swing.JMenuItem();
        jExitMenu = new javax.swing.JMenu();
        jAboutAppMenuItem = new javax.swing.JMenuItem();

        jMenuItem2.setText("jMenuItem2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Application Virtualization Client");
        setName("AppVirMainJFrame"); // NOI18N

        jAppVirClientPanel.setToolTipText("Application Virtualization Clinet UI");

        jVirtualAppList.setVisibleRowCount(15);
        jApplistScrollPane1.setViewportView(jVirtualAppList);

        jAppSelectionButton.setText("Select an App and Click me to Run");
        jAppSelectionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAppSelectionButtonActionPerformed(evt);
            }
        });

        jAppListLabel.setBackground(new java.awt.Color(153, 153, 255));
        jAppListLabel.setText("Application List [Available from Server]");
        jAppListLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jServerStatusLabel.setText("Server Status : IDLE");
        jServerStatusLabel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        javax.swing.GroupLayout jAppVirClientPanelLayout = new javax.swing.GroupLayout(jAppVirClientPanel);
        jAppVirClientPanel.setLayout(jAppVirClientPanelLayout);
        jAppVirClientPanelLayout.setHorizontalGroup(
            jAppVirClientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jAppVirClientPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jAppVirClientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jServerStatusLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jAppListLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jApplistScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
                    .addComponent(jAppSelectionButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jAppVirClientPanelLayout.setVerticalGroup(
            jAppVirClientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jAppVirClientPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jServerStatusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jAppListLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jApplistScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jAppSelectionButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMainMenu.setText("Virtualization Client");

        jRefreshAppMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        jRefreshAppMenuItem.setText("Refresh Apps");
        jRefreshAppMenuItem.setToolTipText("Refresh the Application List Avaialble");
        jRefreshAppMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRefreshAppMenuItemActionPerformed(evt);
            }
        });
        jMainMenu.add(jRefreshAppMenuItem);

        jExitClientMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        jExitClientMenuItem.setText("Exit Client");
        jExitClientMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jExitClientMenuItemActionPerformed(evt);
            }
        });
        jMainMenu.add(jExitClientMenuItem);

        jMenuBar1.add(jMainMenu);

        jMenu1.setText("Configuration");

        jConfigureClientMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        jConfigureClientMenuItem.setText("Configure Client");
        jConfigureClientMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jConfigureClientMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(jConfigureClientMenuItem);

        jReconnectServerMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jReconnectServerMenuItem.setText("Reconnect Server");
        jReconnectServerMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jReconnectServerMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(jReconnectServerMenuItem);

        jMenuBar1.add(jMenu1);

        jExitMenu.setText("About");

        jAboutAppMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        jAboutAppMenuItem.setText("Help");
        jAboutAppMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAboutAppMenuItemActionPerformed(evt);
            }
        });
        jExitMenu.add(jAboutAppMenuItem);

        jMenuBar1.add(jExitMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jAppVirClientPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jAppVirClientPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jRefreshAppMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRefreshAppMenuItemActionPerformed
        if (clientSocket != null && clientSocket.isConnected()) {
            appList = getAppListFromServer();
            populateAppListToClient(appList);
            jServerStatusLabel.setText("Server Status : Connected");
        } else {
            jServerStatusLabel.setText("Server Status : Not Connected");
        }
    }//GEN-LAST:event_jRefreshAppMenuItemActionPerformed

    private void jExitClientMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jExitClientMenuItemActionPerformed
        System.exit(1);
    }//GEN-LAST:event_jExitClientMenuItemActionPerformed

    private void jAppSelectionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAppSelectionButtonActionPerformed
        String appSelected = (String) jVirtualAppList.getSelectedValue();
        System.out.println("AppSelected is : " + appSelected);
        
        ClientAppHelper appHelper = new ClientAppHelper();
        appHelper.getSpecificApp(appSelected, clientSocket);
   
        appHelper.executeApp(appSelected, 1000);
    }//GEN-LAST:event_jAppSelectionButtonActionPerformed

    private void jAboutAppMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAboutAppMenuItemActionPerformed
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AppVirAboutJFrame().setVisible(true);
            }
        });
    }//GEN-LAST:event_jAboutAppMenuItemActionPerformed

    private void jConfigureClientMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jConfigureClientMenuItemActionPerformed
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AppVirClientConfigJFrame().setVisible(true);
            }
        });
    }//GEN-LAST:event_jConfigureClientMenuItemActionPerformed

    private void jReconnectServerMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jReconnectServerMenuItemActionPerformed
        loadConfigProps();
        setServerConnectionStatus();
        System.out.println("Going to retrieve App List from server");
        appList = getAppListFromServer();
        // populate the applications from the server
        System.out.println("AppList retrieved is : " + appList);
        populateAppListToClient(appList);
    }//GEN-LAST:event_jReconnectServerMenuItemActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AppVirClientJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AppVirClientJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AppVirClientJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AppVirClientJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AppVirClientJFrame avcjf = new AppVirClientJFrame();
                avcjf.setVisible(true);
                avcjf.setLocationRelativeTo(null);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem jAboutAppMenuItem;
    private javax.swing.JLabel jAppListLabel;
    private javax.swing.JButton jAppSelectionButton;
    private javax.swing.JPanel jAppVirClientPanel;
    private javax.swing.JScrollPane jApplistScrollPane1;
    private javax.swing.JMenuItem jConfigureClientMenuItem;
    private javax.swing.JMenuItem jExitClientMenuItem;
    private javax.swing.JMenu jExitMenu;
    private javax.swing.JMenu jMainMenu;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jReconnectServerMenuItem;
    private javax.swing.JMenuItem jRefreshAppMenuItem;
    private javax.swing.JLabel jServerStatusLabel;
    private javax.swing.JList jVirtualAppList;
    // End of variables declaration//GEN-END:variables
}
/* EOF */