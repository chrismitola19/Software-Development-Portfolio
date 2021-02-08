
package com.cm;

/**
 *
 * @author chris
 */
import java.util.Random;
import java.util.Scanner;

public class LuckySevens {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Random r = new Random();          
    
 // Request the input

// Initialize variables
int maxDollars = 0;
int countAtMax = 0;
int count = 0;
        System.out.println("How many dollars would you like to play with");
        String intput = s.nextLine();
        int dollars = Integer.parseInt(intput);
        
// Loop until the money is gone
while (dollars > 0);
    // Roll the dice
    double d = Math.random();  // 1-6
    double d2 = Math.random();  // 1-6
    // Calculate the winnings or losses
    count += 1;
    if (d + d2 == 7){
        dollars += 4;
    }
        else{
        dollars -= 1;
    }
    // If this is a new maximum, remember it
    if (dollars > maxDollars){
        maxDollars = dollars;
        countAtMax = count;
    }
// Display the results
    
System.out.println("You are broke after " + count  + " rolls."); 
 
    System.out.println("You should have quit after " + countAtMax);
 
     System.out.println(" rolls when you had $" + maxDollars + ".");
}//end main
}

    

