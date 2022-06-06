package com.company;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;


public class BinaryTree {

    Node root;
    static final int COUNT = 10;
    public String ad;
    private String soyad;
    public JTextArea textArea;
    public String salaries;

    // Binary Tree constructor
    public BinaryTree(JTextArea textArea, String salaries)
    {
        this.textArea = textArea;
        this.salaries = salaries;
    }


    // Binary Tree'ye ekleme metodu
    Node insert(Node node,String ad,String soyad, int data){
        this.ad = ad;
        this.soyad = soyad;


        if(node == null){

            return new Node(data);

        }
        else {
            if(data <= node.data){
                node.left = insert(node.left,ad,soyad,data);
            }
            else{

                node.right = insert(node.right,ad,soyad,data);
            }

            return node;
        }

    }
    // Binary Tree'den textarea'ya yazdırma metodu
    public void print2DUtil(Node root, int space)
    {
        ArrayList<Integer> datas = new ArrayList<Integer>();
        // Base case
        if (root == null)
            //System.out.println("boş");
            return;

        // Increase distance between levels
        space += COUNT;

        // Process right child first
        print2DUtil(root.right, space);

        salaries += root.data + "\n";

        // Process left child
        print2DUtil(root.left, space);
    }

    // Wrapper over print2DUtil()
    public String print2D(Node root)
    {
        salaries = "";
        // Pass initial space count as 0
        print2DUtil(root, 0);
        return salaries;
    }


    static Node deleteNode(Node root, int k)
    {

        // Base case
        if (root == null)
            return root;

        // Recursive calls for ancestors of
        // node to be deleted
        if (root.data > k)
        {
            root.left = deleteNode(root.left, k);
            return root;
        }
        else if (root.data < k)
        {
            root.right = deleteNode(root.right, k);
            return root;
        }

        // We reach here when root is the node
        // to be deleted.

        // If one of the children is empty
        if (root.left == null)
        {
            Node temp = root.right;
            return temp;
        }
        else if (root.right == null)
        {
            Node temp = root.left;
            return temp;
        }


        else
        {
            Node succParent = root;

            // Find successor
            Node succ = root.right;

            while (succ.left != null)
            {
                succParent = succ;
                succ = succ.left;
            }


            if (succParent != root)
                succParent.left = succ.right;
            else
                succParent.right = succ.right;

            // Copy Successor Data to root
            root.data = succ.data;

            return root;
        }
    }
    int getLeafCount(){
        return getLeafCount(root);
    }

    int getLeafCount(Node node){
        if(node == null){

            return 0;

        }
        else if(node.left == null && node.right == null){

            return 1;

        }

        else{
            return getLeafCount(node.left) + getLeafCount(node.right);
        }

    }
    //Preorder Gezinme
    private void preOrder(Node node){

        if(node == null){
            return;
        }
        System.out.println(node.data + " ");

        preOrder(node.left);

        preOrder(node.right);

    }
    /* private void inOrder(Node node){

        if(node == null){
            return;
        }
        inOrder(node.left);

        System.out.println(node.data + " ");

        inOrder(node.right);

    }*/
    //Postorder Gezinme
    private void postOrder(Node node){

        if(node == null){
            return;
        }

        postOrder(node.left);

        postOrder(node.right);

        System.out.println(node.data + " ");

    }


    void preOrder(){ preOrder(root); }

    //void inOrder(){ inOrder(root); }

    void postOrder(){ postOrder(root); }


    public int getNodeCount(Node node){
        int count = 0;
        if(node != null){
            count = 1;
            count += getNodeCount(node.left);
            count += getNodeCount(node.right);

        }
        return count;
    }
    int getNodeCount(){ return getNodeCount(root); }


    //min node bulma
    int min(Node node){

        Node current = node;

        while(current.left != null){
            current = current.left;
        }

        return current.data;
    }


    //max node bulma
    int max(Node node){
        Node current = node;

        while(current.right != null){

            current = current.right;

        }

        return current.data;
    }

    public static void removeLineFromFile(String file, String lineToRemove) {

        try {

            File inFile = new File(file);

            if (!inFile.isFile()) {
                System.out.println("Parameter is not an existing file");
                return;
            }

            //Construct the new file that will later be renamed to the original filename.
            File tempFile = new File(inFile.getAbsolutePath() + ".tmp");

            BufferedReader br = new BufferedReader(new FileReader(file));
            PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

            String line = null;

            //Read from the original file and write to the new
            //unless content matches data to be removed.
            while ((line = br.readLine()) != null) {

                if (!line.trim().equals(lineToRemove)) {

                    pw.println(line);
                    pw.flush();
                }
            }
            pw.close();
            br.close();

            //Delete the original file
            if (!inFile.delete()) {
                System.out.println("Could not delete file");
                return;
            }

            //Rename the new file to the filename the original file had.
            if (!tempFile.renameTo(inFile))
                System.out.println("Could not rename file");

        }
        catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }


}
