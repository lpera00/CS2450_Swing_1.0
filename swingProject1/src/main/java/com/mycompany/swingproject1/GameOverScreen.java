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
import javax.swing.text.*;
import java.util.ArrayList;
import java.util.*;
import java.util.Collection;
import java.util.Collections;
//import java.Collections.*;
import java.io.*;

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
    public String initials;
    private boolean validInitials;

    private ArrayList<HighScore> highScoreList;

    public GameOverScreen(CardLayout c, JPanel p) {
        highScoreList = HighScore.getHighScores();

        setSize(600, 400);
        setVisible(true);
        setLayout(new FlowLayout());
        setEnabled(true);
        cardLO = c;
        panel = p;
        displayScore = PlayScreen.currentScore;
        System.out.println("current score: " + currentScore);
        scoreLabel = new JLabel();
        scoreLabel.setText("Score: " + Integer.toString(displayScore));
        add(scoreLabel);
        initials = "";
        validInitials = false;

        //back button
        backButton = new JButton("End");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                backButton.setEnabled(false);
                backButton.setVisible(false);
                initialsPanel.setVisible(false);
                initialsPanel.setEnabled(false);
                inputInitials.setEnabled(true);
                inputInitials.setText("");
                initialsLabel.setText("new high score! enter your initials to save:");
                setEnabled(false);
                // set for next gameplay
                
                initials = "";
                validInitials = false;
                cardLO.show(panel, "Menu");
            }
        });
        backButton.setToolTipText("Return to menu screen");
        add(backButton);

        //components for inputting initials
        initialsPanel = new JPanel(new FlowLayout());
        try {
        inputInitials = new JFormattedTextField(new MaskFormatter("UUU"));
        } catch(java.text.ParseException e) {
            System.out.println("fuck it we ball");
        }
        inputInitials.setEnabled(true);
        //inputInitials.setText("");
        initialsLabel = new JLabel();
        initialsLabel.setText("new high score! enter your initials to save:");
        inputInitials.setColumns(3);
        inputInitials.setToolTipText("input initials in the format \"ABC\"");
        
        initialsPanel.add(initialsLabel);
        initialsPanel.add(inputInitials);
        initialsPanel.setVisible(false);
        initialsPanel.setEnabled(false);
        
        
        inputInitials.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                initials = inputInitials.getText();
                System.out.println(initials);
                inputInitials.setEnabled(false);
                initialsLabel.setText("Saved!");
                //save high score
                highScoreList.add(new HighScore(initials,displayScore));
                Collections.sort(highScoreList,Collections.reverseOrder());
                if(highScoreList.size() > 5) {
                    highScoreList.remove(5);
                }
                HighScore.setHighScores(highScoreList);
            }
        });
        //add(test);
        add(initialsPanel);
    }
    
    
    public void checkIfHighScore() {
        // to determine if our score is a high score, we either check if
        // the high score list is smaller than 5, or if it's 5, check the
        // 5th best score and check if it's larger
        if(highScoreList.size() < 5) {
            makeHighScoreSectionVisible();
        }
        else {
            int leastBestScore = highScoreList.get(4).getScore();
            if(leastBestScore < displayScore) {
                makeHighScoreSectionVisible();
            }
            else {
                makeHighScoreSectionInvisible();
            }
        }
    }
    
    private void makeHighScoreSectionVisible() {
        initialsPanel.setVisible(true);
        initialsPanel.setEnabled(true);
    }

    private void makeHighScoreSectionInvisible() {
        initialsPanel.setVisible(false);
        initialsPanel.setEnabled(false);
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        displayScore = PlayScreen.currentScore;
        System.out.println("displayscore:" + displayScore);
        scoreLabel.setText("Score: " + Integer.toString(displayScore));
        backButton.setEnabled(true);
        backButton.setVisible(true);
        
        checkIfHighScore();
    }

}
