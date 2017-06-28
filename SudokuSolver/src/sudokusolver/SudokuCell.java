/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudokusolver;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.accessibility.AccessibleContext;
import javax.swing.JTextField;
import javax.swing.event.EventListenerList;
import javax.swing.plaf.ComponentUI;

/**
 *
 * @author DINESH
 */
public class SudokuCell extends JTextField {

    private int row;
    private int col;
    private int number;
    private boolean isBlocked;

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean getIsBlocked() {
        return isBlocked;
    }

    public void setIsBlocked(boolean isBlocked) {
        this.isBlocked = isBlocked;
    }

    public ComponentUI getUi() {
        return ui;
    }

    public void setUi(ComponentUI ui) {
        this.ui = ui;
    }

    public EventListenerList getListenerList() {
        return listenerList;
    }

    public void setListenerList(EventListenerList listenerList) {
        this.listenerList = listenerList;
    }

    public AccessibleContext getAccessibleContext() {
        return accessibleContext;
    }

    public void setAccessibleContext(AccessibleContext accessibleContext) {
        this.accessibleContext = accessibleContext;
    }

    public SudokuCell(final int row, final int col, boolean isBlocked, int num) {
        super();
        this.row = row;
        this.col = col;
        this.number = num;
        this.isBlocked = isBlocked;

    }

    public void setActionListener() {
        if (!isBlocked) {
            JTextField a = this;
            a.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {

                }

                @Override
                public void keyPressed(KeyEvent e) {
                    //System.out.println(a.getText());
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    if(!a.getText().trim().isEmpty()){
                    number = Integer.parseInt(a.getText());
                    if (number != SudokuGUI.solution[row][col]) {
                        a.setForeground(Color.red);
                    } else {
                        a.setForeground(Color.GREEN);
                    }
                }
                }
            });
            a.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    a.setBackground(Color.YELLOW);
                    a.setCaretColor(Color.YELLOW);
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    

                }

                @Override
                public void mouseReleased(MouseEvent e) {
                                   }

                @Override
                public void mouseEntered(MouseEvent e) {
                    //  a.setBackground(Color.);

                }

                @Override
                public void mouseExited(MouseEvent e) {
                    a.setBackground(Color.darkGray);
                    a.setCaretColor(Color.darkGray);
                }

            });
            a.addActionListener(
                    new ActionListener() {
                @Override

                public void actionPerformed(ActionEvent e) {

                    number = Integer.parseInt(a.getText());
                    if (number != SudokuGUI.solution[row][col]) {
                        a.setForeground(Color.red);
                    } else {
                        a.setForeground(Color.GREEN);
                    }

                    System.out.println("Number set to " + number);
                }
            }
            );
        }

    }

}
