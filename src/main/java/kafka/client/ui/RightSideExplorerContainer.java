package kafka.client.ui;

import javax.swing.*;

public class RightSideExplorerContainer extends JPanel {

    public static RightSideExplorerContainer SINGLETON = new RightSideExplorerContainer();

    private RightSideExplorerContainer(){
        System.out.println("Loading RightSideExplorerContainer");
    }
}