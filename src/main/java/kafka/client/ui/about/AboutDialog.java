package kafka.client.ui.about;

import kafka.client.Client;


import javax.swing.*;
import java.awt.*;

public class AboutDialog extends JDialog {
    public static AboutDialog SINGLETON = new AboutDialog();

    public JPanel aboutMainPanel = null;

    public AboutDialog() {
        super(Client.SINGLETON, true);
    }

    public void initUI() {
        aboutMainPanel = new JPanel();
        aboutMainPanel.setLayout(new BoxLayout(aboutMainPanel, BoxLayout.Y_AXIS));

        final Toolkit toolkit = Toolkit.getDefaultToolkit();
        final Dimension screenSize = toolkit.getScreenSize();
        final int x = (screenSize.width - getWidth()) / 4;
        final int y = (screenSize.height - getHeight()) / 4;
        setLocation(x, y);
        setSize(550, 500);

        add(aboutMainPanel, BorderLayout.CENTER);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/kafka/images/kafka.png")));
        setTitle("About Kafka Tester");
    }

    public void createAboutDialog() {
        About.SINGLETON.initAboutTab();
        aboutMainPanel.add(About.SINGLETON);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        AboutDialog sd = new AboutDialog();
        sd.initUI();
        SwingUtilities.invokeLater(sd::createAboutDialog);
        sd.setSize(550, 500);
        sd.setVisible(true);
    }
}