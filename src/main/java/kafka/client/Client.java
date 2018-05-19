package kafka.client;

import kafka.client.ui.*;

import javax.swing.*;
import java.awt.*;

public class Client extends JFrame {

    public static Client SINGLETON = new Client();

    private Client() {
        Global.WIDTH = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
        Global.HEIGHT = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
    }

    protected void initCoreUI() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/kafka/images/kafka.png")));

        MainMenuBar.SINGLETON.initAndCreateMainMenuBar();
        add(MainMenuBar.SINGLETON, BorderLayout.PAGE_START);

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(SystemTray.SINGLETON.windowAdap);

        setSize(Global.WIDTH, Global.HEIGHT);
        setPreferredSize(new Dimension(Global.WIDTH, Global.HEIGHT));

        setTitle("Kafka Tester (Beta)");
        setVisible(true);
    }

    protected void initOtherUIComponents() {
        TopLevelContainer.SINGLETON.initUI();
        add(TopLevelContainer.SINGLETON, BorderLayout.CENTER);
        pack();
    }
}