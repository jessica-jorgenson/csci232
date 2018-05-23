/*
 * Jessica Jorgenson
 * CSCI 232
 * This program provides the solution to part 1 
 * of the programming section of the hoeework
 */
package homework3;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jorge
 */
public class Homework3PartOne implements Runnable{
    
    int seconds;
    int count;
    String spacing = "";
    String category;
    
    public Homework3PartOne(int sec){
        seconds = sec;
        category = Integer.toString(seconds);
    }
    

    @Override
    public void run() {
        while(true){
            try {
                count++;
                if("3".equals(category)){
                    spacing = "    ";
                }else{
                    spacing = "";
                }
                Thread.sleep(seconds * 1000);
                System.out.println(spacing + count);
            } catch (InterruptedException ex) {
                Logger.getLogger(Homework3PartOne.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public static void main(String[] args) {
        Homework3PartOne first = new Homework3PartOne(1);
        Homework3PartOne second = new Homework3PartOne(3);
        Thread t1 = new Thread(first);
        Thread t2 = new Thread(second);
        
        t1.start();
        t2.start();
    }
}
