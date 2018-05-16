/*
 * Jessica Jorgenson
 * CSCI 232
 * This program receives a file designated by the user, and then prints every
 * word as its own line.
 */

package homework1;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Driver {

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter file (or location + file): ");
            String input = scanner.next();
            System.out.println();
            File file = new File(input);
            Scanner fileInput = new Scanner(file);

            while (fileInput.hasNextLine()) {
                String theLine = fileInput.nextLine();
                String wordsOnLine[] = theLine.split(" ");
                for (String word : wordsOnLine) {
                    System.out.println(word);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");

        }

    }
}
