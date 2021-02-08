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
import java.util.Scanner;

public class AskUserHowManyTimesEx {
     public static void main(String[] args) {
        
         Scanner myScanner = new Scanner(System.in);
         
         int count = 0;
         int sum = 0;
         int num;
         
         System.out.println("Enter a number: ");
         num = myScanner.nextInt();
         
         
         for (count = 0; count < num; count++){
             sum += num;
             System.out.println("Your Results are: " + sum);
         }
         
         
    }
    
}
