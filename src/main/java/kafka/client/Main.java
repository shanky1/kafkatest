package kafka.client;

import kafka.client.ui.about.AboutDialog;
import kafka.client.ui.cluster.ClusterDialog;

import javax.swing.*;

public class Main {

    private void initView(){
        try {
            try {
                if(Global.OS.contains("nux")|| Global.OS.contains("nix")) {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
                } else if(Global.OS.contains("mac")){
                    UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
                } else if(Global.OS.contains("win")){
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                }

                if (Client.SINGLETON != null) SwingUtilities.updateComponentTreeUI(Client.SINGLETON);
            } catch (Exception exception) {
                exception.printStackTrace();
            }

            ToolTipManager.sharedInstance().setInitialDelay(0);
            SystemTray.SINGLETON.addSystemTraySupport();
            SystemTray.SINGLETON.addWindowListenerSupport();

            SwingUtilities.invokeLater(Client.SINGLETON::initCoreUI);
            SwingUtilities.invokeLater(Client.SINGLETON::initOtherUIComponents);
            SwingUtilities.invokeLater(ClusterDialog.SINGLETON::initUI);
            SwingUtilities.invokeLater(AboutDialog.SINGLETON::initUI);
            SwingUtilities.invokeLater(AboutDialog.SINGLETON::createAboutDialog);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Main main = new Main();
        main.initView();
    }
}
