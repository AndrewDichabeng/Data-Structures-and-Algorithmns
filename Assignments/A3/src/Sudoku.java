import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * A Sudoku solver that uses a back-tracking algorithm.
 * @author  :   Andrew Dichabeng
 * @version :   1.0
 */
public class Sudoku {

    /* constant */
    private static final int PUZZLE_SIZE = 9;

    /**
     * The main method.
     * @param args  -   Array of String command line arguments.
     * @throws IOException  -   An exception thrown when there is an Input/Output exception
     */
    public static void main(String[] args) throws IOException {

        int numOfEntries, row, col, number, test;
        String dir = "";

        /* test selection */
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter a test number between 1 and 5 inclusive : ");
        test = keyboard.nextInt();

        switch (test)   {
            case 1: dir = "tests/in1.txt";  // has solution
                break;
            case 2: dir = "tests/in2.txt";  // has solution
                break;
            case 3: dir = "tests/in3.txt";  // has solution
                break;
            case 4: dir = "tests/in4.txt";  // no solution
                break;
            case 5: dir = "tests/in5.txt";  // has solution
                break;
            default: System.out.println("Invalid Test");
                break;
        }

        /* reading in puzzle */
        Scanner sc = new Scanner(new FileReader(dir));
        int[][] puzzle = new int[PUZZLE_SIZE][PUZZLE_SIZE];
        numOfEntries = sc.nextInt();

        for (int i = 0 ; i < numOfEntries ; i++) {
            row = sc.nextInt();
            col = sc.nextInt();
            number = sc.nextInt();
            puzzle[row][col] = number;
        }

        /* console output */
        System.out.println("Original Puzzle : \n");
        printPuzzle(puzzle);
        System.out.println("\nSolution to Puzzle : \n");

        if (solve(0,0,puzzle)) {    // solves in place
            printPuzzle(puzzle);
        }

        else {
            System.out.println("NO SOLUTION.");
        }
        sc.close();
        keyboard.close();
    }

    /**
     * Method to print out the Sudoku puzzle to the console.
     * @param array -   The 2-dimensional array that represents the puzzle.
     */
    private static void printPuzzle(int[][] array) {

        for (int i = 0 ; i < array.length ; i++) {
            for(int j = 0 ; j < array.length ; j++) {
                System.out.printf("[%d] ", array[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * Method to check if a block of a Sudoku puzzle has a legal solution.
     * @param i - The x-index of the 2-dimensional array.
     * @param j - The y-index of the 2-dimensional array.
     * @param cells -   The 2-dimensional array that represents the Sudoku puzzle.
     * @return  -   Returns true if the puzzle has solution.
     */
    private static boolean legal(int i, int j, int val, int[][] cells) {

        for (int k = 0; k < 9; ++k) {   // row
            if (val == cells[k][j]) {
                return false;
            }
        }

        for (int k = 0; k < 9; ++k) {   // col
            if (val == cells[i][k]) {
                return false;
            }
        }

        int boxRowOffset = (i / 3) * 3;
        int boxColOffset = (j / 3) * 3;

        for (int k = 0; k < 3; ++k)  {  // box
            for (int m = 0; m < 3; ++m) {
                if (val == cells[boxRowOffset + k][boxColOffset + m]) {
                    return false;
                }
            }
        }
        return true;    // no violations, so it's legal
    }

    /**
     * Method to solve a Sudoku puzzle.
     * @param i - The x-index of the 2-dimensional array.
     * @param j - The y-index of the 2-dimensional array.
     * @param cells -   The 2-dimensional array that represents the Sudoku puzzle.
     * @return  -   Returns true if the puzzle has solution.
     */
    private static boolean solve(int i, int j, int[][] cells) {

        if (i == 9) {
            i = 0;
            if (++j == 9) {
                return true;
            }
        }

        if (cells[i][j] != 0) {     // skip filled cells
            return solve(i+1,j,cells);
        }

        for (int val = 1; val <= 9; ++val) {
            if (legal(i,j,val,cells)) {
                cells[i][j] = val;
                if (solve(i+1,j,cells)) {
                    return true;
                }
            }
        }
        cells[i][j] = 0;    // reset on backtrack
        return false;
    }

}