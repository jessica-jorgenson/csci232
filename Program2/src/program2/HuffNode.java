/*
 * Jessica Jorgenson
 * CSCI 232
 */
package program2;

/**
 *
 * @author jorge
 */
public class HuffNode implements Comparable<HuffNode> {
    public String theChar;
    public int freq;
    public String binCode = "";
    public HuffNode left, right, parent;
    public boolean isLeftChild, isRightChild;
    
    public HuffNode(int leFreq, HuffNode leLeft, HuffNode leRight){
        freq = leFreq;
        left = leLeft;
        right = leRight;
        theChar = null;
    }
    
    public HuffNode(String leChar, int leFreq, HuffNode leLeft, HuffNode leRight){
        theChar = leChar;
        freq = leFreq;
        left = leLeft;
        right = leRight;
    }
    
    public boolean isLeaf(){
        return left == null && right == null;
    }
    
    public void makeCode(HuffNode root){
        HuffNode current = this;
        
        while(current != root){
            if(current.isLeftChild){
                binCode = "0" + binCode;
            }else if(current.isRightChild){
                binCode = "1" + binCode;
            }
            current = current.parent;
        }  
        
    }
    
    public void setAsLeft(){
        isLeftChild = true;
        isRightChild = false;
    }
    
    public void setAsRight(){
        isRightChild = true;
        isLeftChild = false;
    }

    @Override
    public int compareTo(HuffNode o) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return 0;
    }
}
