/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudokusolver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author DINESH
 */
//Input is a string that 
public class SudokuSolver {

    int[][] matrix;

    /**
     * @param args the command line arguments
     */
    public SudokuSolver(int size,String[] input) {
        matrix = new int[size][size];
        load(input);
        solvePuzzle();
    }

    private void solvePuzzle(/*String[] input*/) {
        //load(input);
        solve(0, 0);

       // displayGrid();

    }

    private boolean solve(int i, int j) {
        if (j == matrix.length) {
            j = 0;
            i++;
            if (i == (matrix.length)) {
                return true;
            } else {
                return solve(i, j);
            }
        } else {
            if (matrix[i][j] == 0) {

                for (int num = 1; num < 10; num++) {
                    if (isLegal(i, j, num)) {
                        this.matrix[i][j] = num;
                        if (!solve(i, j + 1)) {
                            matrix[i][j] = 0;
                        } else {
                            return true;
                        }
                    }
                }
                return false;
            } else {
                return solve(i, j + 1);
            }
        }
        //return true;
    }

    private boolean isLegal(int row, int col, int element) {

        ArrayList b = new ArrayList();
        ArrayList k = new ArrayList();

        for (int i = 0; i < matrix.length; i++) {
            k.add(matrix[row][i]);
            b.add(matrix[i][col]);
        }
        boolean first = k.contains(element);
        boolean second = b.contains(element);
        boolean third = checkBox(row, col, element);
        // boolean fourth = (matrix[row][col] != 0);
        return !(first || second || third);

    }

    private boolean checkBox(int row, int col, int element) {
        int row_offset = 3 * (row / 3);
        int col_offset = 3 * (col / 3);
        ArrayList a = new ArrayList();
        for (int i = row_offset; i < (row_offset + 3); i++) {
            for (int j = col_offset; j < (col_offset + 3); j++) {
                a.add(matrix[i][j]);
            }
        }
        return a.contains(element);
    }

    private void load(String[] input) {
        for (int i = 0; i < input.length; i++) {
            int row = Integer.parseInt(input[i].substring(0, 1));
            int col = Integer.parseInt(input[i].substring(1, 2));
            matrix[row][col] = Integer.parseInt(input[i].substring(2, 3));
        }

    }

    private void displayNumber() {
        System.out.print("  ");
        for (int k = 0; k < matrix.length; k++) {
            System.out.printf("%d ", k);
        }
        System.out.println();
    }

    private void displayGrid() {
        for (int i = 0; i < matrix.length; i++) {
            if (i == 0) {
                displayNumber();
                drawLine(false);
            }
            if (i % 3 == 0) {
                drawLine(false);
            }
            System.out.print(i);
            drawLine(true);
           // drawLine(true);
            for (int j = 0; j < matrix.length; j++) {
                drawLine(true);
                System.out.print(matrix[i][j]);
                //drawLine(true);
            }
            drawLine(true);
            System.out.println();
          //  drawLine(false);

        }
        drawLine(false);
    }

    private void drawLine(boolean vertical) {
        if (vertical) {
            System.out.print('|');
        } else {
            System.out.print(" ");
            for (int i = 0; i < 2 * matrix.length - 1; i++) {

                System.out.print('-');
            }
            System.out.println();
        }
    }

    
}
