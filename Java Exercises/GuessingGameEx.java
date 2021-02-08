
package com.cm;
/**
 *
 * @author chris
 */
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;

public class GuessingGameEx {
    public static void main(String[] args) {

    Scanner console = new Scanner(System.in);
    Random r = new Random();
    int MAX = 100; //ceiling for the random number
    int total = 0; //total number of guess
    int plays = 0; //total number of games
    int bestGame = Integer.MAX_VALUE; //used to determine game with lowest amount of guesses
    String answer; //holds user input

    boolean playing = true; //used to determine if the game is ongoing

    while (playing) { //while game is ongoing
        System.out.println("I'm thinking of a number between one and " + MAX + "...");
        int theNumber = r.nextInt(MAX) + 1; //generate random int [1, 100]
        int userGuess = 0; //holds user guess
        int guesses = 0; //total number of guesses this game

   
        while(userGuess != theNumber) { //while number is not guessed
          
            System.out.print("Your guess?\n");
        try{
            userGuess = console.nextInt();
    
           
           
            if (userGuess > theNumber) System.out.println("It's lower."); //user guesses lower than number
            else if (userGuess < theNumber) System.out.println("It's higher.");//user guesses higher than number 
        //    else if (userGuess != )
            guesses++; //increase guesses this round by 1
           
        }   catch (InputMismatchException e){
           System.out.println("Must be an integer.");
             console.nextLine();
         }  
            
        }
         
         
         
   

        if (guesses < bestGame) bestGame = guesses; //if we used less guesses than in our best game this is our new best game
        System.out.println("You got it right in " + guesses + " guess(es)!");
        plays++; //increase number of games by 1
        total += guesses; //increase total guesses by guesses this round

        System.out.println("Again?");
        answer = console.next();
        if (answer.toLowerCase().equals("n")) playing = false; //if input is n (or N) we exit the loop
     
    
    }
      
      
      

    console.close(); //close scanner

    System.out.println("Overall results:"); //print results
    System.out.println("Total games = " + plays);
    System.out.println("Total guesses = " + total);
    System.out.printf("Guesses/game = %.2f%n", (double) total/plays); //cast to double to prevent integer division
    System.out.println("Best game = " + bestGame);


    
   
}
}   

