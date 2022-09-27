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
import java.Collections.*;

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
         
        //back button
            backButton = new JButton("End");
            backButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    backButton.setEnabled(false);
                    backButton.setVisible(false);
                    setEnabled(false);
                    cardLO.show(panel, "Menu");
                }
            });
            add(backButton);
        repaint();
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