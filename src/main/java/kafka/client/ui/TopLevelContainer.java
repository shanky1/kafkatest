package kafka.client.ui;

import javax.swing.*;
import java.awt.*;


public class TopLevelContainer extends JPanel {
    public static TopLevelContainer SINGLETON = new TopLevelContainer();
    private TopLevelContainer(){}

    private JTabbedPane mainTabbedPane;
    private JSplitPane BrowserExplorerAndRightSideExplorerSplitPane;

    public void initUI() {
        mainTabbedPane = new JTabbedPane();
        BrowserExplorerAndRightSideExplorerSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true);
        BrowserExplorerAndRightSideExplorerSplitPane.setDividerSize(6);

        setLayout(new BorderLayout());

        BrowserExplorerContainer.SINGLETON.initUI();

        BrowserExplorerAndRightSideExplorerSplitPane.setLeftComponent(BrowserExplorerContainer.SINGLETON);
        BrowserExplorerAndRightSideExplorerSplitPane.setRightComponent(RightSideExplorerContainer.SINGLETON);

        mainTabbedPane.add("Browser", BrowserExplorerAndRightSideExplorerSplitPane);
        add(mainTabbedPane, BorderLayout.CENTER);

        SwingUtilities.invokeLater(this::reSize);
    }


    private void reSize() {
        BrowserExplorerAndRightSideExplorerSplitPane.setDividerLocation(.20);
        BrowserExplorerAndRightSideExplorerSplitPane.resetToPreferredSizes();
    }
}