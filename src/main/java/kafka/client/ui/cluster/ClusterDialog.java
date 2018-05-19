package kafka.client.ui.cluster;

import kafka.client.ui.BrowserExplorerContainer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClusterDialog extends JDialog {
    public JPanel clusterMainPanel = null;
    public JPanel emptyPanel = null;
    public JPanel textPanel = null;
    public JPanel buttonPanel = null;

    private JButton addButton;
    private JButton cancelButton;

    private JLabel label = null;
    private JTextField jTextField = null;

    public static ClusterDialog SINGLETON = new ClusterDialog();
    private ClusterDialog(){

    }

    public void initUI() {
        clusterMainPanel = new JPanel();
        clusterMainPanel.setLayout(new BoxLayout(clusterMainPanel, BoxLayout.Y_AXIS));

        final Toolkit toolkit = Toolkit.getDefaultToolkit();
        final Dimension screenSize = toolkit.getScreenSize();
        final int x = (screenSize.width - getWidth()) / 4;
        final int y = (screenSize.height - getHeight()) / 4;
        setLocation(x, y);
        setSize(400, 300);

        add(clusterMainPanel, BorderLayout.CENTER);

        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/kafka/images/kafka.png")));
        setTitle("Add Cluster");

        emptyPanel = new JPanel();

        textPanel = new JPanel();
        label = new JLabel("Host :");
        jTextField = new JTextField();
        jTextField.setColumns(20);
        textPanel.add(label);
        textPanel.add(jTextField);

        buttonPanel = new JPanel();

        addButton = new JButton("Add");
        addButton.addActionListener(addButtonListener);

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(cancelButtonListener);

        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(addButton);
        buttonPanel.add(cancelButton);
        buttonPanel.setMaximumSize(new Dimension(400, 50));

        clusterMainPanel.add(emptyPanel, BorderLayout.NORTH);
        clusterMainPanel.add(textPanel, BorderLayout.CENTER);
        clusterMainPanel.add(buttonPanel, BorderLayout.SOUTH);
    }


    ActionListener addButtonListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String addedHost = jTextField.getText();
            if(addedHost != null || !addedHost.equalsIgnoreCase("")){
                BrowserExplorerContainer.SINGLETON.addNodeInJtree(addedHost);
            }
            clearJtextBox();
            setVisible(false);
        }
    };


    ActionListener cancelButtonListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
        }
    };


    public void clearJtextBox(){
        jTextField.setText("");
    }
}
