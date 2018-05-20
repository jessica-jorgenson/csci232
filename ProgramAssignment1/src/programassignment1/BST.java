/*
 * Jessica Jorgenson
 * CSCI 232
 * Program Assignment 1
 */
package programassignment1;
import java.util.*;

public class BST {

    public Node root;
    public int levels = 0;
    public ArrayList byLevel = new ArrayList();
    public PrintTreeArray theTree = new PrintTreeArray();

    public void insert(int integer) {
        Node currentNode = new Node(integer);
        if (levels == 0) {
            root = currentNode;
            root.parent = null;
        } else {
            Node check = root;
            int currLevel = 0;
            boolean inserted = false;
            while (!inserted) {
                
                if (currentNode.value < check.value) {
                    if (check.hasLeftChild()) {
                        check = check.left;
                        currLevel++;
                    }else{
                        check.left = currentNode;
                        currentNode.isLeftChild = true;
                        currentNode.parent = check;
                        inserted = true;
                        totalLevels(currLevel, currentNode);
                    }
                    currentNode.mapTo.add(-1);
                } else {
                    if (check.hasRightChild()) {
                        check = check.right;
                        currLevel++;
                    }else{
                        check.right = currentNode;
                        currentNode.isRightChild = true;
                        currentNode.parent = check;
                        inserted = true;
                        totalLevels(currLevel, currentNode);
                    }
                    currentNode.mapTo.add(1);
                }
                
            }
        }
    }
    
    public void totalLevels(int levCheck, Node currNode){
        if(levCheck > levels){
            theTree.addToLevel(levCheck, currNode);
            levels = levCheck;
        }
        else {
            theTree.addToLevel(currNode.mapTo.size(), currNode);
        }
    }
}
