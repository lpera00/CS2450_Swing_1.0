/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.swingproject1;

import static com.mycompany.swingproject1.PlayScreen.currentScore;
import static com.mycompany.swingproject1.PlayScreen.end;
import java.awt.CardLayout;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.swing.text.MaskFormatter;



/**
 *
 * @author other
 */
public class SudokuScreen extends JPanel implements ActionListener{
    private JButton quit;
    private JButton submit;
    private JLabel feedbackLabel;
    public CardLayout cardLO;
    public JPanel panel;
    private JFormattedTextField inputs[];
    private SudokuGrid gridPanel;
    private JFormattedTextField gridSquare;
    private String currentDate = "";
    private String currentTime = "";
    private JLabel dateAndTimeDisplayer;
    private Timer timeAndDateTimer;

    // solution of the puzzle we are asked to implement
    private static final int SOLUTION[][] = {
        {8, 3, 5, 4, 1, 6, 9, 2, 7},
        {2, 9, 6, 8, 5, 7, 4, 3, 1},
        {4, 1, 7, 2, 9, 3, 6, 5, 8},
        {5, 6, 9, 1, 3, 4, 7, 8, 2},
        {1, 2, 3, 6, 7, 8, 5, 4, 9},
        {7, 4, 8, 5, 2, 9, 1, 6, 3},
        {6, 5, 2, 7, 8, 1, 3, 9, 4},
        {9, 8, 1, 3, 4, 5, 2, 7, 6},
        {3, 7, 4, 9, 6, 2, 8, 1, 5},
    };
    // array of x,y positions of numbers to be given at beginning of puzzle
    private static final int GIVEN_POSITIONS[][] = {
        {0,0},{0,3},{0,5},{0,8},
        {1,6},
        {2,1},{2,6},{2,7},
        {3,0},{3,2},{3,4},{3,6},{3,7},
        {4,4},
        {5,1},{5,2},{5,4},{5,6},{5,8},
        {6,1},{6,2},{6,7},
        {7,2},
        {8,0},{8,3},{8,5},{8,8},
    };
    
    public SudokuScreen(CardLayout c, JPanel p){
        setLayout(null);
        setSize(600, 400);
        setVisible(true);
        setEnabled(true);
        cardLO = c;
        panel = p;
        
        //time and date
        dateAndTimeDisplayer = new JLabel();
        dateAndTimeDisplayer.setBounds(420, 5, 200, 20);
        dateAndTimeDisplayer.setVisible(true);
        timeAndDateTimer = new Timer(1000, this);
        timeAndDateTimer.start();
        timeAndDateTimer.setRepeats(true);  
        add(dateAndTimeDisplayer);
        
        
        /*
        //create grid of text fields
        inputs = new JFormattedTextField[81];
        gridPanel = new JPanel(new GridLayout(9,9));
        for (int i = 0; i < 81; i++){
            //set textfileds to only accept 1 digit
            try {
            gridSquare = new JFormattedTextField(new MaskFormatter("#"));
            } catch(java.text.ParseException e) {
            System.out.println("sudoku textfield");
            }
            
            //set already placed numbers to be uneditable
            gridSquare.setDisabledTextColor(Color.black);
            gridSquare.setEnabled(false);
            switch(i){
                case 0:
                    gridSquare.setText("8");
                    break;
                case 3:
                    gridSquare.setText("4");
                    break;
                case 5:
                    gridSquare.setText("6");
                    break;
                case 8:
                    gridSquare.setText("7");
                    break;
                case 15:
                    gridSquare.setText("4");
                    break;
                case 19:
                    gridSquare.setText("1");
                    break;
                case 24:
                    gridSquare.setText("6");
                    break;
                case 25:
                    gridSquare.setText("5");
                    break;
                case 27:
                    gridSquare.setText("5");
                    break;
                case 29:
                    gridSquare.setText("9");
                    break;
                case 31:
                    gridSquare.setText("3");
                    break;
                case 33:
                    gridSquare.setText("7");
                    break;
                case 34:
                    gridSquare.setText("8");
                    break;
                case 40:
                    gridSquare.setText("7");
                    break;
                case 46:
                    gridSquare.setText("4");
                    break;
                case 47:
                    gridSquare.setText("8");
                    break;
                case 49:
                    gridSquare.setText("2");
                    break;
                case 51:
                    gridSquare.setText("1");
                    break;
                case 53:
                    gridSquare.setText("3");
                    break;
                case 55:
                    gridSquare.setText("5");
                    break;
                case 56:
                    gridSquare.setText("2");
                    break;
                case 61:
                    gridSquare.setText("9");
                    break;
                case 65:
                    gridSquare.setText("1");
                    break;
                case 72:
                    gridSquare.setText("3");
                    break;
                case 75:
                    gridSquare.setText("9");
                    break;
                case 77:
                    gridSquare.setText("2");
                    break;
                case 80:
                    gridSquare.setText("5");
                    break;
                default:
                    //set all other textfields to be editable
                    gridSquare.setToolTipText("Enter a number between 1-9");
                    gridSquare.setEnabled(true);
                    break;
            }
            inputs[i] = gridSquare;
            gridPanel.add(inputs[i]);
        }
        //gridPanel is the panel displaying the textfields
        //basically acts as the sudoku grid
        */
        gridPanel = new SudokuGrid(SOLUTION,GIVEN_POSITIONS);
        gridPanel.setBounds(140, 20, 300, 340);
        gridPanel.setVisible(true);
        gridPanel.setEnabled(true);
        add(gridPanel);
        
        feedbackLabel = new JLabel();
        feedbackLabel.setBounds(25, 250, 100, 100);
        feedbackLabel.setVisible(true);
        add(feedbackLabel);
        
        //quit button
        quit = new JButton("quit");
        quit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                remove(gridPanel);
                gridPanel = new SudokuGrid(SOLUTION,GIVEN_POSITIONS);
                gridPanel.setBounds(140, 20, 300, 340);
                gridPanel.setVisible(true);
                gridPanel.setEnabled(true);
                add(gridPanel);
                //gridPanel.initializeGame();
                repaint();
                setEnabled(false);
                cardLO.show(panel, "Game Over");
            }
        });
        quit.setBounds(475, 325, 100, 20);
        quit.setToolTipText("Skip game");
        add(quit);
        
        //submit button
        submit = new JButton("submit");
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int verified = gridPanel.verify();
                switch (verified) {
                    case 1: // correct answer
                        int sudokuScore = 540 - gridPanel.getNumberOfFails() * 10;
                        currentScore += sudokuScore;
                        // reset game
                        remove(gridPanel);
                        gridPanel = new SudokuGrid(SOLUTION,GIVEN_POSITIONS);
                        gridPanel.setBounds(140, 20, 300, 340);
                        gridPanel.setVisible(true);
                        gridPanel.setEnabled(true);
                        add(gridPanel);
                        //gridPanel.initializeGame();
                        repaint();
                        // scene transition
                        setEnabled(false);
                        cardLO.show(panel, "Game Over");
                        break;
                    case 0: // incorrect answer
                        // scoring feedback handled elsewhere
                        feedbackLabel.setText("Try again!");
                        break;
                    default: // invalid answer
                        feedbackLabel.setText("Invalid input!");
                }
            }
        });
        submit.setBounds(15, 325, 100, 20);
        submit.setToolTipText("Check your solution");
        add(submit);

        Action cheat = new AbstractAction(){
        public void actionPerformed(ActionEvent e){
            gridPanel.cheat();
           }
        };
        panel.getInputMap().put(KeyStroke.getKeyStroke('c'),"ISuckAtSudoku");
        panel.getActionMap().put("ISuckAtSudoku", cheat);
        
        repaint();
    }
    
    public void actionPerformed(ActionEvent e){
        //for update time/date
        Date date = new Date();
        Instant now = Instant.now();
            //date
            SimpleDateFormat formatter = new SimpleDateFormat("MMMM dd yyyy");
            currentDate = formatter.format(date);
            //time
            DateTimeFormatter time = DateTimeFormatter.ofPattern("HH:mm:ss");
            LocalTime localTime = LocalTime.ofInstant(now, ZoneId.systemDefault());
            currentTime = time.format(localTime);
            
            dateAndTimeDisplayer.setText(currentDate.substring(0, currentDate.length() - 5) + "," + 
                currentDate.substring(currentDate.length() - 5, currentDate.length())
                + " " + currentTime);
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}

class SudokuGrid extends JPanel {
    private int[][] solutionArray; // holds this SudokuGrid's solution
    private int[][] givenArray; // holds what this SudokuGrid will initially display
    private JFormattedTextField[][] inputFields; // array of the JTextFields forming this grid
    private Boolean[][] markedWrong; // array of booleans that are true if the grid at x,y has attempted to have been submitted with a wrong answer
    
    public SudokuGrid(int[][]solutionArray,int[][]givenArray){
        // copy solution arrays into this class' fields
        this.solutionArray = solutionArray;
        this.givenArray = givenArray;
        
        // set up layout and the array for the fields to be the right length
        inputFields = new JFormattedTextField[solutionArray.length][solutionArray[0].length];
        this.setLayout(new GridLayout(solutionArray.length,solutionArray[0].length));
        
        for(int row = 0; row < inputFields.length; row++) {
            for(int col = 0; col < inputFields[0].length; col++) {
                JFormattedTextField gridSquare;
                try {
                    // assumes all grid squares are on unless turned off in next for loop
                    gridSquare = new JFormattedTextField(new MaskFormatter("#"));
                    gridSquare.setToolTipText("Enter a number between 1-9");
                    gridSquare.setEnabled(true);
                    gridSquare.setDisabledTextColor(Color.black);
                    
                    this.add(gridSquare);
                    inputFields[row][col] = gridSquare;
                    
                } catch (java.text.ParseException e) {
                    System.out.println("sudoku textfield error");
                }
            }
        }
        // now we have a grid of blank text fields
        // this next for-each loop will place the given numbers on the grid (and disable editing them)
        for(int[] coord: this.givenArray) {
            // readability assignments
            int x = coord[0];
            int y = coord[1];
            
            JFormattedTextField targetGridSquare = inputFields[x][y];
            assert(targetGridSquare != null);
            targetGridSquare.setEnabled(false);
            targetGridSquare.setToolTipText(null);
            targetGridSquare.setText("" + solutionArray[x][y]);
        }
        // assume answers are right until marked wrong
        // initialize markedWrong array and fill with falses
        markedWrong = new Boolean[inputFields.length][inputFields[0].length];
        for(int row = 0; row < markedWrong.length; row++) {
            for(int col = 0; col < markedWrong[row].length; col++) {
                markedWrong[row][col] = false;
            }
        }
        
        
        
        //initializeGame();
    }
    
    // public method to check if sudoku is solved; returns 1 if solved, 0 if not solved, and -1 for invalid input
    public int verify() {
        Boolean failed = false;
        Boolean[][]originalWrongArray = markedWrong;
        for(int row = 0; row < inputFields.length; row++) {
            for(int col = 0; col < inputFields[0].length; col++) {
                try {
                    int input = Integer.parseInt(inputFields[row][col].getText());
                    if (input < 1 || input > 9) {
                        markedWrong = originalWrongArray;
                        return -1; // ensure input is a number betwen 1 and 9; if not, input invalid
                    }
                    // wrong answer
                    else if (input != solutionArray[row][col]) {
                        failed = true;
                        markedWrong[row][col] = true;
                    }
                } catch (NumberFormatException n) {
                    markedWrong = originalWrongArray;
                    return -1; // if any inputs don't have a number, return invalid input
                }
            }
        }
        if(failed) return 0;
        else return 1;
    }
    
    public void cheat() {
        for(int row = 0; row < inputFields.length; row++) {
            for(int col = 0; col < inputFields[0].length; col++) {
                inputFields[row][col].setText("" + solutionArray[row][col]);
            }
        }
    }
    
    public int getNumberOfFails() {
        int counter = 0;
        for(Boolean[]row:markedWrong) {
            for(Boolean wrong:row) {
                if(wrong) counter++;
            }
        }
        return counter;
    }
    
    public void initializeGame() {
        for(int row = 0; row < inputFields.length; row++) {
            for(int col = 0; col < inputFields[0].length; col++) {
                JFormattedTextField gridSquare;
                try {
                    // assumes all grid squares are on unless turned off in next for loop
                    gridSquare = new JFormattedTextField(new MaskFormatter("#"));
                    gridSquare.setToolTipText("Enter a number between 1-9");
                    gridSquare.setEnabled(true);
                    gridSquare.setDisabledTextColor(Color.black);
                    
                    this.add(gridSquare);
                    inputFields[row][col] = gridSquare;
                    
                } catch (java.text.ParseException e) {
                    System.out.println("sudoku textfield error");
                }
            }
        }
        // now we have a grid of blank text fields
        // this next for-each loop will place the given numbers on the grid (and disable editing them)
        for(int[] coord: this.givenArray) {
            // readability assignments
            int x = coord[0];
            int y = coord[1];
            
            JFormattedTextField targetGridSquare = inputFields[x][y];
            assert(targetGridSquare != null);
            targetGridSquare.setEnabled(false);
            targetGridSquare.setToolTipText(null);
            targetGridSquare.setText("" + solutionArray[x][y]);
        }
        // assume answers are right until marked wrong
        // initialize markedWrong array and fill with falses
        markedWrong = new Boolean[inputFields.length][inputFields[0].length];
        for(int row = 0; row < markedWrong.length; row++) {
            for(int col = 0; col < markedWrong[row].length; col++) {
                markedWrong[row][col] = false;
            }
        }
    }
}