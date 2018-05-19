package kafka.client.ui;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.util.Vector;

public class BrowserExplorerContainer extends JPanel {
    public static BrowserExplorerContainer SINGLETON = new BrowserExplorerContainer();
    private BrowserExplorerContainer(){}

    public JTree tree;
    protected DefaultMutableTreeNode treeRoot = null;
    private String name = "Clusters";

    public static JScrollPane browserJScrollPane = null;

    public void initUI() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        System.setProperty("northPanel_color", "0Xfafcff");
        setBackground(Color.getColor("northPanel_color"));

        browserJScrollPane = new JScrollPane();
        browserJScrollPane.setBackground(Color.getColor("northPanel_color"));

        tree = new JTree();
        tree.setShowsRootHandles(true);
        DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
        treeRoot = new DefaultMutableTreeNode(name);
        model.setRoot(treeRoot);

        browserJScrollPane.setViewportView(tree);
        add(browserJScrollPane);
    }


    public void addNodeInJtree(String nodeToAdd) {

        DefaultMutableTreeNode child = new DefaultMutableTreeNode(nodeToAdd);

        Vector <String>v1 = getListOfTopics();
        for(String topic : v1){
            child.add(new DefaultMutableTreeNode(topic));
        }

        DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) tree.getModel().getRoot();
        model.insertNodeInto(child, root, root.getChildCount());
        tree.scrollPathToVisible(new TreePath(child.getPath()));
    }


    public Vector getListOfTopics(){
        Vector <String>vl = new Vector();
        vl.add("Topic1");
        vl.add("Topic2");
        vl.add("Topic3");
        vl.add("Topic4");

        return vl;
    }
}