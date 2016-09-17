import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author  :   Andrew Dichabeng
 * file     :   ChangeMain.java
 * description  :   A main program to demonstrate the Change class using input from a user.
 */

public class ChangeMain {

    public static void main(String[] args) {

        int inputAmount;
        Scanner sc = new Scanner(System.in);    //  the Scanner object to read in user input.
        boolean quit = false;                   //  boolean sentinel variable.

        while (!quit) {
            System.out.println("\nEnter the amount(of type integer) to be changed in cents (e.g - $ 1.25 = 125 cents) : ");
            System.out.println("Enter Zero(0) to quit.");
            try {
                inputAmount = sc.nextInt();
                if (inputAmount < 0) {
                    System.out.println("Invalid Input : Amount must be greater than zero (0).");
                }
                else if (inputAmount == 0) {
                    System.out.println("Program is quiting....");
                    quit = true;
                }
                else {
                    Change newChange = new Change(inputAmount);
                    System.out.printf("Change for %d cents = %d", newChange.getAmount(), newChange.getCounter());
                }
            }
            catch (InputMismatchException ime){
                quit = true;
                ime.printStackTrace();
                System.exit(1);
            }
        }
        sc.close();     // close the scanner.
    }
}
