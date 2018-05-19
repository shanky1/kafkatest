package kafka.client.ui;

import kafka.client.ui.about.AboutDialog;
import kafka.client.ui.cluster.ClusterDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class MainMenuBar extends JMenuBar {

    public static MainMenuBar SINGLETON = new MainMenuBar();
    private MainMenuBar() { }

    public void initAndCreateMainMenuBar() {

        JMenu file = new JMenu("File");
        file.setMnemonic(KeyEvent.VK_F);
        JMenuItem eMenuItem = new JMenuItem("Add New Connection");
        eMenuItem.addActionListener(addConnectionListener);
        file.add(eMenuItem);
        add(file);

        JMenu edit = new JMenu("Edit");
        edit.setMnemonic(KeyEvent.VK_E);
        JMenuItem eCutItem = new JMenuItem("Cut");
        JMenuItem eCopyItem = new JMenuItem("Copy");
        JMenuItem ePasteItem = new JMenuItem("Paste");
        edit.add(eCutItem);
        edit.add(eCopyItem);
        edit.add(ePasteItem);
        add(edit);

        JMenu help = new JMenu("Help");
        help.setMnemonic(KeyEvent.VK_H);
        JMenuItem eAboutItem = new JMenuItem("About");
        eAboutItem.addActionListener(aboutListener);
        help.add(eAboutItem);
        add(help);
    }

    ActionListener addConnectionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            ClusterDialog.SINGLETON.setVisible(true);
        }
    };

    ActionListener aboutListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            AboutDialog.SINGLETON.setVisible(true);
        }
    };
}

