/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.swingproject1;

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
    public CardLayout cardLO;
    public JPanel panel;
    private JFormattedTextField inputs[];
    private JPanel gridPanel;
    private JFormattedTextField gridSquare;
    private String currentDate = "";
    private String currentTime = "";
    private JLabel dateAndTimeDisplayer;
    private Timer timeAndDateTimer;


    
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
        
        //initialize solution array
        int soluiton[] = {3,5,9,2,2,9,6,8,5,7,3,1,4,7,2,9,3,8,6,1,4,2,1,2,3,
        6,8,5,4,9,7,5,9,6,6,7,8,1,3,4,9,8,3,4,5,2,7,6,7,4,6,8,1};
        
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
        gridPanel.setBounds(140, 20, 300, 340);
        gridPanel.setVisible(true);
        gridPanel.setEnabled(true);
        add(gridPanel);
        
        //quit button
        quit = new JButton("quit");
        quit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setEnabled(false);
                cardLO.show(panel, "Game Over");
            }
        });
        quit.setBounds(475, 325, 100, 20);
        quit.setToolTipText("Skip game");
        add(quit);
        
        //submit button
        //doesn't check anything yet
        //just skips to the game over screen
        submit = new JButton("submit");
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setEnabled(false);
                cardLO.show(panel, "Game Over");
            }
        });
        submit.setBounds(15, 325, 100, 20);
        submit.setToolTipText("Check your solution");
        add(submit);

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
