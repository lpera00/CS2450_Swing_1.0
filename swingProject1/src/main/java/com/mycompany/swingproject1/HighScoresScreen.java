/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.swingproject1;

import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

/**
 *
 * @author PK_Flunr
 */
class HighScoresScreen extends JPanel {
    private Button backButton;
    public CardLayout cardLO;
    public JPanel panel;
    public int offset = 15;

    public HighScoresScreen(CardLayout c, JPanel p){
        setSize(600,400);
        setVisible(true);
        setEnabled(true);
        setLayout(null);
        cardLO = c;
        panel = p;
        //back button
            backButton = new Button("back");
            backButton.setBounds(475, 325, 100, 20);
            backButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    backButton.setEnabled(false);
                    backButton.setVisible(false);
                    setEnabled(false);
                    cardLO.show(panel, "Menu");
                }
            });
            add(backButton);
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawString("High Scores", 250, 25); 
        for(int i = 0; i < 5; i++){
         g.drawString("ABC.....00000", 250, 50 + offset*i);
        }
        backButton.setEnabled(true);
        backButton.setVisible(true);
    }

}

class HighScore implements java.io.Serializable, Comparable<HighScore> {
    final private String initials;
    final private Integer score;
    
    public HighScore(String initials, int score) {
        this.initials = initials;
        this.score = score;
    }
    
    public int getScore() {
        return score;
    }
    
    public String getInitials() {
        return initials;
    }
    
    @Override public int compareTo(HighScore other) {
        return score.compareTo(other.score);
    }
}