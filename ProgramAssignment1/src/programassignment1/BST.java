/*
 * Jessica Jorgenson
 * CSCI 232
 * Program Assignment 1
 */
package programassignment1;

import java.util.*;

public class BST {

    public Node root = null;
    public int levels = 0;
    public ArrayList byLevel = new ArrayList();
    public PrintTreeArray theTree = new PrintTreeArray();

    public ArrayList<Integer> inOrderList = new ArrayList();
    public ArrayList<Integer> preOrderList = new ArrayList();
    public ArrayList<Integer> postOrderList = new ArrayList();

    public void search(int id) {
        Node searcher = root;
        while (searcher != null) {
            if (searcher.value == id) {
                System.out.println("Number found in tree");
                return;
            } else if (searcher.value < id) {
                searcher = searcher.left;
            } else {
                searcher = searcher.right;
            }
        }
        System.out.println("Number not found.");
    }

    public void insert(int integer) {
        Node currentNode = new Node(integer);
        if (root == null) {
            root = currentNode;
            root.parent = null;
            theTree.makeLevel(0);
            theTree.addNode(root);
        } else {
            Node check = root;
            int currLevel = 0;
            boolean inserted = false;

            while (!inserted) {

                if (currentNode.value < check.value) {

                    if (check.hasLeftChild()) {

                        check = check.left;

                        currLevel += 1;

                    } else {

                        check.setLeft(currentNode);
                        currentNode.isLeftChild = true;
                        currentNode.parent = check;
                        inserted = true;
                        currLevel += 1;
                        totalLevels(currLevel, currentNode);
                    }
                } else {

                    if (check.hasRightChild()) {

                        check = check.right;
                        currLevel += 1;

                    } else {
                        check.setRight(currentNode);
                        currentNode.isRightChild = true;
                        currentNode.parent = check;
                        inserted = true;
                        currLevel += 1;
                        totalLevels(currLevel, currentNode);
                    }
                }

            }
        }
        currentNode.makeMap(root);
        System.out.println("Node " + integer + " was inserted into the tree.");
    }

    public void delete(int numToDel) {
        Node theParent = root, currentNode = root;
        Node successor = null;
        boolean isLeftChild = false;
        while (currentNode.value != numToDel) {
            theParent = currentNode;

            if (currentNode.value > numToDel) {
                isLeftChild = true;
                currentNode = currentNode.left;
            } else {
                isLeftChild = false;
                currentNode = currentNode.right;
            }

            if (currentNode == null) {
                System.out.println("Number not found.");
            }
        }

        //Case 1
        if (currentNode.isLeaf()) {
            theTree.ridNode(currentNode);
            if (currentNode == root) {
                root = null;
            }

            if (isLeftChild) {
                theParent.setLeft(null);
            } else {
                theParent.setRight(null);
            }

            //Case 2
        } else if (currentNode.right == null) {
            theTree.ridNode(currentNode);
            if (currentNode == root) {
                root = currentNode.left;
            } else if (isLeftChild) {
                theParent.setLeft(currentNode.left);
            } else {
                theParent.setRight(currentNode.left);
            }
            currentNode.left.setParent(theParent);
            //theParent.makeMap(root);
            currentNode.left.makeMap(root);

            //Case 3
        } else if (currentNode.left == null) {
            theTree.ridNode(currentNode);
            if (currentNode == root) {
                root = currentNode.right;
            } else if (isLeftChild) {
                theParent.setLeft(currentNode.right);
            } else {
                theParent.setRight(currentNode.right);
            }
            currentNode.right.setParent(theParent);
            //theParent.makeMap(root);
            currentNode.makeMap(root);
        } //Successor
        else if (currentNode.left != null && currentNode.right != null) {
            successor = getSuccessor(currentNode);
            theTree.ridNode(currentNode, successor);
            if (currentNode == root) {
                root = successor;
            } else if (isLeftChild) {
                theParent.setLeft(successor);
            } else {
                theParent.setRight(successor);
            }
            successor.setLeft(currentNode.left);
            currentNode.left.setParent(successor);
        }
        System.out.println("Node " + numToDel + " was deleted from the tree.");

    }

    public Node getSuccessor(Node nodeToDel) {
        Node successor = null;
        Node successorParent = null;
        Node currentNode = nodeToDel.right;
        while (currentNode != null) {
            successorParent = successor;
            successor = currentNode;
            currentNode = currentNode.left;
        }
        if (successor != nodeToDel.right) {
            successorParent.setLeft(successor.right);
            successor.right.setParent(successorParent);
            successor.setRight(nodeToDel.right);
            nodeToDel.right.setParent(successor);
        }
        return successor;

    }

    public void makeOrders() {
        inOrderList.clear();
        postOrderList.clear();
        preOrderList.clear();
        inOrder(root);
        postOrder(root);
        preOrder(root);
    }

    public void printOrders() {

        String inOrderStr = "In-Order: ";
        for (Integer i : inOrderList) {
            inOrderStr += i + " ";
        }
        String postOrderStr = "Post-Order: ";
        for (Integer i : postOrderList) {
            postOrderStr += i + " ";
        }
        String preOrderStr = "Pre-Order: ";
        for (Integer i : preOrderList) {
            preOrderStr += i + " ";
        }
        System.out.println(inOrderStr + "\n");
        System.out.println(postOrderStr + "\n");
        System.out.println(preOrderStr + "\n");
    }

    public void inOrder(Node node) {
        if (node.left != null) {
            inOrder(node.left);
        }
        inOrderList.add(node.value);
        if (node.right != null) {
            inOrder(node.right);
        }
    }

    public void postOrder(Node node) {
        if (node.left != null) {
            postOrder(node.left);
        }
        if (node.right != null) {
            postOrder(node.right);
        }
        postOrderList.add(node.value);
    }

    public void preOrder(Node node) {
        preOrderList.add(node.value);
        if (node.left != null) {
            preOrder(node.left);
        }
        if (node.right != null) {
            preOrder(node.right);
        }
    }

    public void totalLevels(int levCheck, Node currNode) {
        if (levCheck > levels) {
            theTree.makeLevel(levCheck);
            theTree.addNode(currNode);
            levels = levCheck;
        } else {
            theTree.addNode(currNode);
        }
    }

    public void workingPrintTree() {
        if (root == null) {
            System.out.println("Tree is empty");
        }

        Queue<Node> treeQueue = new ArrayDeque<>();

        treeQueue.add(root);
        do {
            int size = treeQueue.size();
            for (int i = 0; i < size; i++) {
                Node node = treeQueue.poll();
                System.out.print("-" + node.value + "-");
                if (node.left != null) {
                    treeQueue.add(node.left);
                }
                if (node.right != null) {
                    treeQueue.add(node.right);
                }
            }
            System.out.println();
        } while (treeQueue.size() > 0);
    }
}
}
