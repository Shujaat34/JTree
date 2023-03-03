import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.io.File;

public class TreeViewExplorer extends JFrame {

    public static void main(String[] args) {
        new TreeViewExplorer().setVisible(true);
    }

    private JTree tree;

    /**
     *
     */
    public TreeViewExplorer() {
        this.initialize();
        this.build();
//        this.configure();
        this.addNodeToTree();
        setSize(400, 600);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        setTitle("Java Directory Explorer");
        setVisible(true);
    }

    /**
     *
     */
    public void initialize() {
        this.tree = new JTree();
    }

    /**
     *
     */
    public void build() {
        this.add(this.tree);
    }

    /**
     *
     */
    public void configure() {

        File fileRoot = new File("D:/");

        DefaultMutableTreeNode root = new DefaultMutableTreeNode(fileRoot);
        DefaultTreeModel model = new DefaultTreeModel(root);

        File[] subItems = fileRoot.listFiles();
        if (subItems != null && subItems.length > 0) {
            for (File file : subItems) {
                DefaultMutableTreeNode subNode = new DefaultMutableTreeNode(file);
                root.add(subNode);
                if (file.isDirectory()) {
                    File[] directory = file.listFiles();
                    if (directory != null && directory.length > 0) {
                        for (File subFile : directory) {
                            subNode.add(new DefaultMutableTreeNode(subFile));
                        }
                    }

                }

            }
        }

        this.tree.setModel(model);
    }


    public void addNodeToTree() {

        File fileRoot = new File("D:/");
        //Root Node
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(fileRoot);
        //
        DefaultTreeModel model = new DefaultTreeModel(root);
        addNode(root, fileRoot);
        this.tree.setModel(model);
    }

    private void addNode(DefaultMutableTreeNode rootNode, File fileRoot) {

        if (fileRoot.listFiles() != null)
            for (File file : fileRoot.listFiles()) {
                DefaultMutableTreeNode subNode = new DefaultMutableTreeNode(file);
                rootNode.add(subNode);
                if (file.isDirectory()) {
                    //Calling Recursively
                    addNode(subNode, file);
                }
            }
    }


}
