package kafka.client;

import kafka.client.ui.ExitDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SystemTray {

    public static SystemTray SINGLETON = new SystemTray();
    private SystemTray(){}

    private java.awt.SystemTray tray;
    private TrayIcon trayIcon;
    private ExitDialog exitPromt;


    public void addSystemTraySupport() {
        try {
            if (java.awt.SystemTray.isSupported()) {
                tray = java.awt.SystemTray.getSystemTray();
                Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/kafka/images/kafka.png"));

                PopupMenu popup = new PopupMenu();
                MenuItem defaultItem = new MenuItem("Quit");
                ActionListener exitListenerfromtray = new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        exitPromt = new ExitDialog();
                    }
                };
                defaultItem.addActionListener(exitListenerfromtray);
                popup.add(defaultItem);

                defaultItem = new MenuItem("Open");
                defaultItem.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        Client.SINGLETON.setVisible(true);
                        Client.SINGLETON.setExtendedState(JFrame.NORMAL);
                    }
                });
                popup.add(defaultItem);

                trayIcon = new TrayIcon(image, "KafkaTester", popup);
                trayIcon.setImageAutoSize(true);
                tray.add(trayIcon);

                trayIcon.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if (e.getClickCount() >= 2) {
                            Client.SINGLETON.setVisible(true);
                            Client.SINGLETON.setExtendedState(JFrame.NORMAL);
                        }
                    }
                });
            } else {
                System.out.println("System tray not supported");
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /*
    * Depend on Windows Listener, kafkaTester goes to system try
    * */
    public void addWindowListenerSupport() {
        Client.SINGLETON.addWindowStateListener(new WindowStateListener() {
            public void windowStateChanged(WindowEvent e) {

                if (e.getNewState() ==  Client.SINGLETON.NORMAL) {                          // normal open the KafkaTester
                    try {
                        Client.SINGLETON.setVisible(true);
                    } catch (Exception ex) {
                        System.out.println("2. Unable to remove to system tray");
                    }
                }

                if (e.getNewState() == Client.SINGLETON.ICONIFIED) {                        // minimize the KafkaTester
                    try {

                    } catch (Exception ex) {
                        System.out.println("1. Unable to add to system tray");
                    }
                }

                if (e.getNewState() == 7) {
                    try {
                    } catch (Exception ex) {
                        System.out.println("2. Unable to add to system tray");
                    }
                }

                if (e.getNewState() ==  Client.SINGLETON.MAXIMIZED_BOTH) {                  // maximize the KafkaTester
                    try {
                        Client.SINGLETON.setVisible(true);
                    } catch (Exception ex) {
                        System.out.println("1. Unable to remove to system tray");
                    }
                }

            }
        });
    }

    /* Closing kafkaTester windows action */
    public WindowAdapter windowAdap = new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
            String ObjButtons[] = {"Quit", "No, minimize to system tray", "Cancel"};
            int PromptResult = JOptionPane.showOptionDialog(Client.SINGLETON, "Are you sure you want to quit?", "Quit?", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, ObjButtons, ObjButtons[1]);

            if (PromptResult == JOptionPane.YES_OPTION) {
                System.out.println("user clicked Yes");
                System.exit(0);
            } else if (PromptResult == JOptionPane.NO_OPTION) {
                Client.SINGLETON.setVisible(false);
            } else if (PromptResult == JOptionPane.CANCEL_OPTION) {
                //Do Nothing
            }
        }
    };
}
