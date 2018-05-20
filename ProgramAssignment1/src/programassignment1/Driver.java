/*
 * Jessica Jorgenson
 * CSCI 232
 * Program Assignment 1
 */
package programassignment1;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Driver {
    
    public void main(String[] args) {
        Scanner fileInput = getFile();
        String line = fileInput.useDelimiter("\\Z").next();
        String[] strArray = line.split(",");
        int[] intArray = new int[strArray.length];
        for (int i = 0; i < strArray.length; i++) {
            intArray[i] = Integer.parseInt(strArray[i]);
        }
        
        
    }
    
    public static Scanner getFile() {
        try {
            Scanner scan = new Scanner(System.in);
            System.out.print("Enter location of file: ");
            String input = scan.nextLine();
            System.out.println();
            Scanner leFile = new Scanner(new File(input));
            return leFile;
            
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            System.exit(0);
            return null;
            
        }
        
    }
}
