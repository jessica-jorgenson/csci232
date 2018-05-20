/*
 * Jessica Jorgenson
 * CSCI 232
 */
package programassignment1;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author jorge
 */
public class PrintTreeArray {
    
    public ArrayList<ArrayList<Node>> treelist = new ArrayList<>();
    public int levels = treelist.size();
    
    public void makeLevel(int level){
        int numInLevel = (int) Math.pow(2, level);
        if(level > levels){
            treelist.add(new ArrayList<>(numInLevel));
            ArrayList<Node> newList = treelist.get(-1);
            for(int i = 0; i < newList.size(); i++){
                newList.set(i, null);
            }
        }
    }
    
    public void addNode(Node node){
        ArrayList map = node.mapTo;
        int[] tempArray;
        int levelOn = node.mapTo.size();
        ArrayList<Node> currentLevel = treelist.get(levelOn);
        
        int[] nums = java.util.stream.IntStream.rangeClosed(0, currentLevel.size()).toArray();
        // From: https://alvinalexander.com/source-code/how-to-populate-initialize-java-int-array-range
        
        int mapIter = 0;
        while(nums.length > 1){
            int leftOrRight = (int) map.get(mapIter);
            if(leftOrRight == -1){
                tempArray = Arrays.copyOfRange(nums, 0, nums.length/2);
            }else{
                tempArray = Arrays.copyOfRange(nums, nums.length/2, nums.length);
            }
            nums = tempArray;
            mapIter++;
        }
        int index = nums[0];
        currentLevel.set(index, node);
    }
    
}
