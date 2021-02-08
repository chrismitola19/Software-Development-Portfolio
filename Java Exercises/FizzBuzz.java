/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cm;

/**
 *
 * @author chris
 */
public class FizzBuzz {
    
    public static void main(String[] args) {
         int n = 100;  //set amount 
  
        // loop for 100 times 
        for (int i=1; i<=n; i++)  { 
            
            // number divisible by 15(divisible by 
            // both 3 & 5), print 'FizzBuzz' in  
            // place of the number 
            if (i%15==0)                                                  
                System.out.print("FizzBuzz"+" ");  
            // number divisible by 5, print 'Buzz'  
            // in place of the number 
            else if (i%5==0)      
                System.out.print("Buzz"+" ");  
  
            // number divisible by 3, print 'Fizz'  
            // in place of the number 
            else if (i%3==0)      
                System.out.print("Fizz"+" ");  
                  
            else // print the numbers 
                System.out.print(i+" ");    //i is a number                        
        } 
        
    } 
    }


