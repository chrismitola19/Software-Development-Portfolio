
package com.cm.mp3library.ui;

import java.util.Scanner;

/**
 *
 * @author chris
 */
public class UserIOConsoleImpl implements UserIO {
    
    Scanner myScanner = new Scanner(System.in);

    @Override
    public void print(String prompt) {
        System.out.println(prompt);
    }
    
  //double input
    @Override
    public double readDouble(String prompt) {
        System.out.println(prompt);
        String input = myScanner.nextLine();
        double doubleVal = Double.parseDouble(input);

        return doubleVal;
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        System.out.println(prompt);
        String stringNumber = myScanner.nextLine();
        double number = Double.parseDouble(stringNumber);

        while (number < min || number > max) {

            if (number < min || number > max) { //if number is less than min or greater than max...
                System.out.println("Please input the correct number");
                stringNumber = myScanner.nextLine();
                number = Double.parseDouble(stringNumber);
            }
        }
        return number;
    }
//float input
    @Override
    public float readFloat(String prompt) {
        System.out.println(prompt);
        String intput = myScanner.nextLine();
        float floatVal = Float.parseFloat(intput);

        return floatVal;
    }

    @Override
    public float readFloat(String prompt, float min, float max) {

        System.out.println(prompt);
        String stringNumber = myScanner.nextLine();
        float number = Float.parseFloat(stringNumber);

        while (number < min || number > max) {

            if (number < min || number > max) { //if input is less than min or greater than max...
                System.out.println("Please input the correct number");
                stringNumber = myScanner.nextLine();
                number = Float.parseFloat(stringNumber);
            }
        }
        return number;
    }
//int input
    @Override
    public int readInt(String prompt) {
        System.out.println(prompt);
        String intput = myScanner.nextLine();
        int intVal = Integer.parseInt(intput);

        return intVal;
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        System.out.println(prompt);
        String stringNumber = myScanner.nextLine();
        int number = Integer.parseInt(stringNumber);

        while (number < min || number > max) {

            if (number < min || number > max) { //if input is less than min or greater than max...
                System.out.println("Please input the correct number");
                stringNumber = myScanner.nextLine();
                number = Integer.parseInt(stringNumber);
            }
        }
        return number;
    }
//long input
    @Override
    public long readLong(String prompt) {
        System.out.println(prompt);
        String intput = myScanner.nextLine();
        long longVal = Long.parseLong(intput); 

        return longVal;
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        System.out.println(prompt);
        String stringNumber = myScanner.nextLine();
        long number = Long.parseLong(stringNumber);

        while (number < min || number > max) {

            if (number < min || number > max) { //if input is less than min or greater than max...
                System.out.println("Please input the correct number");
                stringNumber = myScanner.nextLine();
                number = Long.parseLong(stringNumber);
            }
        }
        return number;
    }
//String input
    @Override
    public String readString(String prompt) {
        System.out.println(prompt);
        String input = myScanner.nextLine();

        return input;
    }
    
}
