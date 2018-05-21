/*
 * Jessica Jorgenson
 * CSCI 232
 */
package programassignment1;

import java.util.*;

public class Node {

    int value;
    Node left;
    Node right;
    Node parent;
    ArrayList<Integer> mapTo = new ArrayList();
    boolean isRightChild;
    boolean isLeftChild;
    boolean leaf;

    public Node(int inputData) {
        value = inputData;
        left = null;
        right = null;
        leaf = true;

    }

    public boolean hasLeftChild() {
        return left != null;
    }

    public boolean hasRightChild() {
        return right != null;
    }

    public boolean isLeaf() {
        return leaf;
    }

    public void setRight(Node node) {
        right = node;
        if (node != null) {
            leaf = false;
        }
    }

    public void setLeft(Node node) {
        left = node;
        if (node != null) {
            leaf = false;
        }
    }
    
    public void setParent(Node node){
        parent = node;
    }
    
    public void makeMap(Node root){
        mapTo.clear();
        Node current = this;
        while(current != root){
            if(current.isLeftChild){
                mapTo.add(0, -1);
            }else if(current.isRightChild){
                mapTo.add(0, 1);
            }
            current = current.parent;
        }        
    }

}
