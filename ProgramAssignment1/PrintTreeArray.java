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
    boolean firstTime = true;
    Node theRoot;

    public void makeLevel(int level) {
        int numInLevel = (int) Math.pow(2, level);
        if (level == 0) {
            treelist.add(new ArrayList<>(1));
        } else {
            treelist.add(new ArrayList<>(numInLevel));
        }
        ArrayList<Node> newList = treelist.get(treelist.size() - 1);
        for (int i = 0; i < newList.size(); i++) {
            newList.add(null);
        }
        levels = treelist.size();
    }

    public void addNode(Node node) {
        ArrayList<Integer> map = node.mapTo;
        if (firstTime) {
            ArrayList<Node> firstLev = treelist.get(0);
            firstLev.add(node);
            theRoot = node;
            firstTime = false;
        } else {
            node.makeMap(theRoot);
            int[] tempArray;
            int levelOn = node.mapTo.size();
            ArrayList<Node> currentLevel = treelist.get(levelOn);

            int[] nums = java.util.stream.IntStream.rangeClosed(0, (int) Math.pow(2, levelOn) - 1).toArray();
            // Line above from: https://alvinalexander.com/source-code/how-to-populate-initialize-java-int-array-range

            int mapIter = 0;
            while (nums.length > 1) {
                int leftOrRight = (int) map.get(mapIter);
                if (leftOrRight == -1) {
                    tempArray = Arrays.copyOfRange(nums, 0, nums.length / 2);
                } else {
                    tempArray = Arrays.copyOfRange(nums, nums.length / 2, nums.length);
                }
                nums = tempArray;
                mapIter++;
            }
            int index = nums[0];
            if (currentLevel.size() <= index) {
                currentLevel.add(node);
            } else {
                currentLevel.set(index, node);
            }
        }
    }

    public void ridNode(Node delNode) {
        int delNodeLevel = 0;
        int delNodeLoc = 0;
        for (int i = 0; i < levels; i++) {
            ArrayList<Node> level = treelist.get(i);
            for (int j = 0; j < level.size(); j++) {
                if (treelist.get(i).get(j) == delNode) {
                    delNodeLevel = i;
                    delNodeLoc = j;
                }
            }
        }
        treelist.get(delNodeLevel).set(delNodeLoc, null);
    }

    public void ridNode(Node delNode, Node succNode) {
        int delNodeLevel = 0;
        int delNodeLoc = 0;
        int succNodeLevel = 0;
        int succNodeLoc = 0;
        for (int i = 0; i < levels; i++) {
            ArrayList<Node> level = treelist.get(i);
            for (int j = 0; j < level.size(); j++) {
                if (treelist.get(i).get(j) == delNode) {
                    delNodeLevel = i;
                    delNodeLoc = j;
                } else if (treelist.get(i).get(j) == succNode) {
                    succNodeLevel = i;
                    succNodeLoc = j;
                }
            }
        }
        treelist.get(delNodeLevel).set(delNodeLoc, succNode);
        treelist.get(succNodeLevel).set(succNodeLoc, null);
    }

    public void twoDtoString(){
      String result = "";
      for(int i = 0; i < treelist.size(); i++){
          for(int j = 0; j < treelist.get(i).size(); j++){
              result += treelist.get(i).get(j).value + " ";
          }
          // System.out.println();
          result += "\n";
      }
      System.out.println(result);
  }
}
