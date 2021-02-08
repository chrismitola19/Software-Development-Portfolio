package com.cm;

/**
 *
 * @author Chris Mitola
 */
import java.util.InputMismatchException;
import java.util.Random; //tell Java to use Random
import java.util.Scanner; //tell Java to use Scanner 

public class RockPaperScissors {
    
    public static void main(String[] args) { //start main method
        Scanner inputScanner = new Scanner(System.in); //object for getting user input
        Random inputRandom = new Random(); //object for generating random numbers 
        
        //declare variables and objects
        int r = 0; //r = rock
        int p = 1; //p = paper
        int s = 2; //s = scissors 
        
        int ties = 0, userWin = 0, compWin = 0;
        int u; //user's choice 
        int c; //comp's choice
        
                
        boolean playing = true;//used to determin if the game is ongoing 
        
        //declare variables
        int currentRound = 0;//current round 
        int rounds= 0; //total rounds
        
        while (playing) {  //start of game loop
        
        //Must Initialize to use in loop
        currentRound = 0;//current round 
        rounds= 0; //total rounds
        try{ 
            System.out.print("How many rounds do you want to play: ");
            rounds = inputScanner.nextInt();//pick # of rounds
            if(rounds < 1 || rounds > 10) {             //user must choose 1-10 rounds...
                System.out.println("Invalid rounds.");  //... or system will print this...
                  System.exit(1);                       //...and program prints error message and quits...
            }                                           //... the (1) prints "Command execution failed while...
                                                        //... a (0) would exit but print "BUILD SUCCESS"  
             }   catch (InputMismatchException e){
           System.out.println("Invalid Input");
           System.exit(1);
         
         }  
        
        while (currentRound < rounds) { //loops each round out of total rounds 
         try{
          //shows user their choices
        System.out.println("Pick choice: Rock = 1, Paper = 2, Scissors = 3: ");
        u = inputScanner.nextInt(); //get user choice
        c = inputRandom.nextInt(3); //comp number will be random between 0-2 
        
       //true/false statement
        boolean valid = false;  
        while (!valid) {  //!valid = not false
            if (u >= 1 && u <= 3) { //if user chooses 1-3...
                valid = true;                                          
            } else {  //...if user chooses anything other than 1-3...
                System.out.print("Invalid Response: 1 = rock, 2 = paper 3 = scissors: ");   //...print invalid response
                u = inputScanner.nextInt(); //user chooses
            }
        }
        
 
        //show results 
        System.out.println("Round" + (currentRound + 1) + ": ");
        System.out.println("Computer picks: " + c + "," +  " User picks: " + u); 
        
        //if/else statement to show round results 
        if (u==c){
            System.out.println("Tie!");
            ties++; //adds 1 tie
        } else if ((u == r && c == s) || (u == s && c == p) || (u == p && c == r)){
            System.out.println("User Wins!");
            userWin++; //adds 1 win for user
        } else {
            System.out.println("Computer Wins!");
            compWin++; //adds 1 win for comp
        } //end of if/else
            System.out.println();
            currentRound++;
         
             }   catch (InputMismatchException e){
           System.out.println("Must 1, 2, or 3.");
           inputScanner.nextLine();
         
         }  
        }
         
        
            //Diplays final results 
            System.out.println("Match finished!");
            System.out.println("User Won: " + userWin);
            System.out.println("Computer Won: " + compWin);
            System.out.println("Ties: " + ties);
            
            //if/else statement to show overall winner
            if (userWin > compWin){
                System.out.println("User Wins! Take that Bots!!!");
            } else if (compWin > userWin){
                System.out.println("Computer Wins! Man is no match against Machine!!!");
            } else{
                System.out.println("DRAW!");
            }
            
            System.out.println("Do you want to play again? (y/n): ");
            String answer = inputScanner.next();
            playing = answer.equalsIgnoreCase("y");//if input is y (or Y) we play again, if anything else we exit
        
        }
            System.out.println("Thanks for playing!");  
   }//end main method
      
}