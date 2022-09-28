/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.swingproject1;

import static com.mycompany.swingproject1.PlayScreen.currentScore;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.ArrayList;
//import java.Collections.*;
import java.io.*;
import java.util.*;

/**
 *
 * @author PK_Flunr
 */
class GameOverScreen extends JPanel {
    private JButton backButton;
    private JLabel scoreLabel;
    public CardLayout cardLO;
    public JPanel panel;
    public int displayScore;
    private JPanel initialsPanel;
    private JLabel initialsLabel;
    private JFormattedTextField inputInitials;
    private JButton test;
    public String initials;
    private boolean validInitials;
    
    private ArrayList<HighScore> highScoreList;

    public GameOverScreen(int score, CardLayout c, JPanel p){
        highScoreList = HighScore.getHighScores();
        
        setSize(600,400);
        setVisible(true);
        setLayout(new FlowLayout());
        setEnabled(true);
        cardLO = c;
        panel = p;
        displayScore = PlayScreen.currentScore;
        System.out.println("current score: " + currentScore);
        score = PlayScreen.currentScore;
        scoreLabel = new JLabel();
        scoreLabel.setText("Score: " + Integer.toString(score));
        add(scoreLabel);
        initials = "";
        validInitials = false;
         
        //back button
            backButton = new JButton("End");
            backButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    backButton.setEnabled(false);
                    backButton.setVisible(false);
                    initialsPanel.setVisible(false);
                    initialsPanel.setEnabled(false);
                    test.setVisible(true);                    
                    test.setEnabled(true);
                    inputInitials.setEnabled(true);
                    inputInitials.setText("");
                    initialsLabel.setText("new high score! enter your initials to save:");
                    setEnabled(false);
                    initials = "";
                    validInitials = false;
                    cardLO.show(panel, "Menu");
                }
            });
            add(backButton);
            
            //components for inputting initials
            initialsPanel = new JPanel(new FlowLayout());
            inputInitials = new JFormattedTextField();
            inputInitials.setEnabled(true);
            inputInitials.setText("");
            initialsLabel = new JLabel();
            initialsLabel.setText("new high score! enter your initials to save:");
            inputInitials.setColumns(3);
            initialsPanel.add(initialsLabel);
            initialsPanel.add(inputInitials);
            initialsPanel.setVisible(false);
            initialsPanel.setEnabled(false);
            //test button to make high score section appear
            //will be removed once reading saved scores is implemented
            test = new JButton("test new high score");
            test.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    initialsPanel.setVisible(true);
                    initialsPanel.setEnabled(true);
                    test.setVisible(false);                    
                    test.setEnabled(false);
                    repaint();                
                    }
                });
            inputInitials.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    initials = inputInitials.getText();
                    validInitials = checkInitials(initials);
                    if(validInitials == true){
                        initials = initials.toUpperCase();
                        //saving score/initials to file
                        //to be implemented here
                        initialsLabel.setText("score saved");
                        inputInitials.setEnabled(false);
                    }
                    else{
                        inputInitials.setText("");
                        initialsLabel.setText("invalid input, try again:");
                    }
                }
            });
            add(test);
            add(initialsPanel);
    }
    
    public boolean checkInitials(String input){
        //checks if each char is not an upper or lower case letter
        for(int i = 0; i < input.length(); i++){
            if((int)input.charAt(i) < 65){
                return false;
            }
            else if((int)input.charAt(i) > 90 && (int)input.charAt(i) < 97){
                return false;
            }
            else if((int)input.charAt(i) > 122){
                return false;
            }
        }
        return true;
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        displayScore = PlayScreen.currentScore;
        System.out.println("displayscore:"+displayScore);
        scoreLabel.setText("Score: " + Integer.toString(displayScore));
        backButton.setEnabled(true);
        backButton.setVisible(true);
    }

}