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
import java.util.Random;

public class DogGenetics {
    public static void main(String[] args) {
        
    
     
Scanner sc = new Scanner(System.in);
System.out.println("What is your dog's name?");
// Entering name of dog
String name = sc.nextLine();  
  
System.out.println("Well then, I have this highly reliable report on " + name + "'s prestigious background right here.");
  
System.out.println("Sir " + name + " is: ");
  
//Generating random numbers
Random ran = new Random(); //establish randomizer 
int sum = 0;
int a = 0;
int b = 0;
int c = 0;
int d = 0;
int e = 0;
while(sum != 100)
{
a = ran.nextInt(100);
  
b = ran.nextInt(100-a);
  
c = ran.nextInt(100-b);

d = ran.nextInt(100-c);
  
e = ran.nextInt(100-d);
sum = a + b+ c + d + e;
}
  
System.out.println(a + "% St. Bernard");
System.out.println(b + "% Chihuahua");
System.out.println(c + "% Dramatic RedNosed Asian Pug");
System.out.println(d + "% Common Cur");
System.out.println(e + "% King Doberman");
  
System.out.println("\n\n Wow, that's QUITE the dog!");   // \n\n creates a couple spaces 
    }
} 
