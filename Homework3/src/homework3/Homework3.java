/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homework3;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jorge
 */
public class Homework3 implements Runnable{
    
    int seconds;
    int count;
    String spacing = "";
    String category;
    
    public Homework3(int sec){
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
                Logger.getLogger(Homework3.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public static void main(String[] args) {
        Homework3 first = new Homework3(1);
        Homework3 second = new Homework3(3);
        Thread t1 = new Thread(first);
        Thread t2 = new Thread(second);
        
        t1.start();
        t2.start();
    }
}
