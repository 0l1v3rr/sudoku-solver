package sudokusolver;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static int size = 9;
    public static int[][] sudoku = new int[size][size];

    public static void main(String[] args) {
        readFile("sudoku.txt");
        if(isValidBoard(sudoku)) {
            System.out.println("The unsolved game: ");
            writeBoard(sudoku);
            System.out.println("\nSolving the sudoku...\n");
            solveSudoku(sudoku);
            writeBoard(sudoku);
        } else {
            System.out.println("The given board is not valid.");
        }
    }

    /** Reading the sudoku from the given file. (txt) */
    public static void readFile(String path) {
        try {
            Scanner sc = new Scanner(new File(path));

            for (int i = 0; i < size; i++)
                for (int j = 0; j < size; j++)
                    sudoku[i][j] = sc.nextInt();
            sc.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }

    /** Checking if the given board is valid. */
    public static boolean isValidBoard(int[][] su) {
        if(su.length == size) {
            for(int[] ints : su) {
                if(ints.length != size) {
                    return false;
                }
            }
            return true;
        }

        return false;
    }

    /** Writing the board. */
    public static void writeBoard(int[][] su) {
        for(int row = 0; row < su.length; row++) {
            if(row != 0 && row % 3 == 0) System.out.println("──────┼───────┼──────");

            for(int col = 0; col < su[0].length; col++) {
                if(col % 3 == 0 && col != 0) System.out.print("│ ");

                if(col == 8) {
                    if(su[row][col] == 0) System.out.println("?");
                    else System.out.println(su[row][col]);
                } else {
                    if(su[row][col] == 0) System.out.print("? ");
                    else System.out.print(su[row][col] + " ");
                }
            }
        }
    }

    /** Finding an empty cell. (empty = 0) */
    public static int[] findEmptyCell(int[][] su) {
        for(int row = 0; row < su.length; row++) {
            for(int col = 0; col < su[0].length; col++) {
                if(su[row][col] == 0) {
                    return new int[]{row, col};
                }
            }
        }

        return null;
    }

    /** Checking if the given number is valid in the given position. */
    public static boolean validNum(int[][] su, int n, int row, int col) {
        for(int i = 0; i < su[0].length; i++)
            if(su[row][i] == n && col != i) return false;

        for(int i = 0; i < su.length; i++)
            if(su[i][col] == n && row != i) return false;

        for(int i = row / 3 * 3; i < row / 3 * 3 + 3; i++)
            for(int j = col / 3 * 3; j < col / 3 * 3 + 3; j++)
                if(su[i][j] == n && (i != row && j != col)) return false;

        return true;
    }

    /** Solving the sudoku.*/
    public static boolean solveSudoku(int[][] su) {
        int[] foundEmpty = findEmptyCell(su);
        if(foundEmpty == null) return true;

        for(int i = 1; i < 10; i++) {
            if(validNum(su, i, foundEmpty[0], foundEmpty[1])) {
                su[foundEmpty[0]][foundEmpty[1]] = i;

                if(solveSudoku(su)) return true;
                else su[foundEmpty[0]][foundEmpty[1]] = 0;
            }
        }

        return false;
    }

}
