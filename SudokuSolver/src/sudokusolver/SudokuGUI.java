/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudokusolver;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author DINESH
 */
public class SudokuGUI extends JFrame {

    JPanel gridPanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    SudokuCell[][] matrix;
    static int[][] solution;
    final JButton submit = new JButton("solve");
    final JButton clear = new JButton("clear");

    public SudokuGUI(int size, String[] input) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
       

        matrix = new SudokuCell[size][size];
        SudokuSolver s = new SudokuSolver(size, input);
        this.solution = s.matrix;

        this.setSize(400, 400);
        this.setLayout(new BorderLayout());
        Font f = new Font("Courier New", Font.PLAIN, 22);

        gridPanel.setLayout(new GridLayout(matrix.length + 1, matrix.length));
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                final SudokuCell cell = new SudokuCell(i, j, false, -1);
                matrix[i][j] = cell;
                matrix[i][j].setText("");
                matrix[i][j].setHorizontalAlignment(JTextField.CENTER);
                matrix[i][j].setBackground(Color.darkGray);
                gridPanel.add(matrix[i][j]);
                matrix[i][j].setFont(f);
                matrix[i][j].setCaretColor(Color.darkGray);

            }
        }

        load(input);
        JLabel l = new JLabel("SUDOKU");
        l.setHorizontalAlignment(  SwingConstants.CENTER);
        l.setFont(f);
        this.getContentPane().add(l,BorderLayout.NORTH);
        this.getContentPane().add(gridPanel, BorderLayout.CENTER);
        // this.setLayout();
        displaySudoku();
        //submit.setSize(80, 20);
        // submit.setText("submit");
        buttonPanel.setLayout(new BorderLayout());
        // submit.setLocation(20,0);
        buttonPanel.add(submit, BorderLayout.EAST);
        //this.getContentPane().add(submit);
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayFullGrid();
            }
        });
        //clear.setSize(80, 20);
        //clear.setLocation(100,0);
        buttonPanel.add(clear, BorderLayout.WEST);
        this.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displaySudoku();

            }

        });
        addActionListeners();
    }

    public void addActionListeners() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[i][j].setActionListener();
            }
        }
    }

    private void load(String[] input) {
        for (int i = 0; i < input.length; i++) {
            int row = Integer.parseInt(input[i].substring(0, 1));
            int col = Integer.parseInt(input[i].substring(1, 2));
            matrix[row][col].setNumber(Integer.parseInt(input[i].substring(2, 3)));
            matrix[row][col].setIsBlocked(true);
            matrix[row][col].setFont(new Font("Courier New", Font.BOLD, 22));
            matrix[row][col].setText(((Integer) (matrix[row][col].getNumber())).toString());

            matrix[row][col].setEnabled(false);
            matrix[row][col].setForeground(Color.PINK);

        }
    }

    private void displaySudoku() {

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (!matrix[i][j].getIsBlocked()) {
                    matrix[i][j].setText("");
                }
            }
        }

    }

    private void displayFullGrid() {


        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[i][j].setForeground(Color.GREEN);
                matrix[i][j].setText(((Integer) solution[i][j]).toString());
            }
        }

    }

    public static void main(String[] args) {
        // TODO code application logic here
        //SudokuSolver s = new SudokuSolver(9);
        //s.solvePuzzle(args);
        String [] a = {"008","123", "136", "217", "249", "262", "315", "357", "444", "455", "467",  "531", "573", "621", "676" ,"688", "728",
            "735", "771", "819" ,"864"};
        SudokuGUI g = new SudokuGUI(9, a);
        //g.load(args,)
        g.displaySudoku();
        g.setVisible(true);
    }

}
