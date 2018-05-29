/*
 * Jessica Jorgenson
 * CSCI 232
 * Program 2
 */
package program2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

import java.util.Scanner;

/**
 *
 * @author jorge
 */
public class Program2 {

    /**
     * @param args the command line arguments
     */
    static ArrayList<HuffNode> nodeList = new ArrayList<>(); // List of all nodes
    static HuffNode root; // Holds root of nodes
    static HashMap<String, String> binCodes = new HashMap<>(); // Dictionary of characters and their codes
    static String binMapStr;
    static String freqMapStr;
    static HashMap<String, Integer> freqMap = new HashMap<>();
    static String theString; // The input
    static String encoded = ""; // The binary message
    static String output = ""; // Binary to English string

    public static void main(String[] args) throws IOException {
        //getString();
        //System.out.println("Huffman Tree with: " + theString);
        //HashMap<String, Integer> freqMap = new HashMap<>();
        for (int i = 0; i < theString.length(); i++) {
            if (freqMap.containsKey(Character.toString(theString.charAt(i))) == false) {
                int count = theString.length() - theString.replace(Character.toString(theString.charAt(i)), "").length();
                freqMap.put(Character.toString(theString.charAt(i)), count);
            }
        }

        //System.out.println("Frequency Table");
        freqMapStr = printMaps(freqMap);

        buildTree(freqMap);

        //System.out.println("Binary Table");
        binMapStr = printMaps(binCodes);

        encoded = encode();
        decode();

    }

    public static void buildTree(HashMap theMap) {
        PriorityQueue<HuffNode> pq = new PriorityQueue<>();
        for (Object letter : theMap.keySet()) {
            String theLetter = letter.toString();
            int theValue = (int) theMap.get(theLetter);
            HuffNode node = new HuffNode(theLetter, theValue, null, null);
            nodeList.add(node);
            pq.add(node);
        }

        HuffNode treeRoot = null;
        while (pq.size() > 1) {
            HuffNode firstLeast = pq.poll();
            HuffNode secondLeast = pq.poll();
            HuffNode combo = new HuffNode(firstLeast.theChar + " + " + secondLeast.theChar, firstLeast.freq + secondLeast.freq, firstLeast, secondLeast);
            nodeList.add(combo);
            firstLeast.parent = combo;
            firstLeast.setAsLeft();
            secondLeast.parent = combo;
            secondLeast.setAsRight();

            pq.add(combo);
            treeRoot = combo;
        }
        root = treeRoot;
        makeCodes();
    }

    public static void getString(boolean useFile, boolean lower, String input) {
        if (useFile) {
            try {
                Scanner scanner = new Scanner(System.in);
                File file = new File(input);
                Scanner fileInput = new Scanner(file);
                if (lower) {
                    theString = fileInput.nextLine().toLowerCase();
                } else {
                    theString = fileInput.nextLine();
                }

            } catch (FileNotFoundException e) {
                System.out.println("File not found.");
            }
        } else {
            if (lower) {
                theString = input.toLowerCase();
            } else {
                theString = input;
            }

        }

    }

    public static void makeCodes() {
        for (int i = 0; i < nodeList.size(); i++) {
            HuffNode current = nodeList.get(i);
            current.makeCode(root);
            if (current.theChar != null && current.theChar.length() == 1) {
                binCodes.put(current.theChar, current.binCode);

            }
        }
    }

    public static String printMaps(HashMap theMap) {
        String mapString = "<html>";
        for (Object letter : theMap.keySet()) {
            String theLetter = letter.toString();
            String theValue = theMap.get(theLetter).toString();
            mapString += theLetter + ": " + theValue + "<br>";
        }
        return mapString + "</html>";
    }

    public static String encode() {
        for (int i = 0; i < theString.length(); i++) {
            encoded += binCodes.get(Character.toString(theString.charAt(i)));
        }
        return encoded;
    }

    public static void decode() throws IOException {
        int count;
        String stepBy;
        while (encoded.length() > 0) {
            HuffNode current = root;
            count = 0;
            while (current.isLeaf() == false) {
                stepBy = Character.toString(encoded.charAt(count));
                if ("0".equals(stepBy)) {
                    current = current.left;
                } else {
                    current = current.right;
                }
                count++;
            }
            output += current.theChar;
            encoded = encoded.substring(count);
        }
        try (BufferedWriter out = new BufferedWriter(new FileWriter("output.txt"))) {
            out.write(output);
        }

    }
}