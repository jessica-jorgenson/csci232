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
    ArrayList mapTo = new ArrayList();
    boolean isRightChild;
    boolean isLeftChild;

    public Node(int inputData) {
        value = inputData;
        left = null;
        right = null;
    }

    public boolean hasLeftChild() {
        return left != null;
    }

    public boolean hasRightChild() {
        return right != null;
    }

}
