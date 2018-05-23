/*
 * Jessica Jorgenson
 * CSCI 232
 * This program provides the solution to part 2 
 * of the programming section of the hoeework
 */
package homework3;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jorge
 */
public class Homework3PartTwo implements Runnable {

    static ArrayList<Integer> ints = new ArrayList<Integer>();
    static int toMatch = 1;
    int id;
    int random;
    Homework3PartTwo next;

    public Homework3PartTwo(int theId) {
        id = theId;
    }

    public void setNext(Homework3PartTwo nextGuy) {
        next = nextGuy;
    }

    @Override
    public void run() {
        while (toMatch != id) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Homework3PartTwo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            random = (int) (Math.random() * 50 + 1);
            ints.add(random);
            System.out.println("Thread " + id + " inserted " + random);
            toMatch = next.id;


}

public static void main(String[] args) throws InterruptedException {
        Homework3PartTwo first = new Homework3PartTwo(1);
        Homework3PartTwo second = new Homework3PartTwo(2);
        Homework3PartTwo third = new Homework3PartTwo(3);
        Homework3PartTwo fourth = new Homework3PartTwo(4);

        first.setNext(second);
        second.setNext(third);
        third.setNext(fourth);
        fourth.setNext(first);

        Thread firstThread = new Thread(first);
        Thread secondThread = new Thread(second);
        Thread thirdThread = new Thread(third);
        Thread fourthThread = new Thread(fourth);

        firstThread.start();
        secondThread.start();
        thirdThread.start();
        fourthThread.start();

    }
}
