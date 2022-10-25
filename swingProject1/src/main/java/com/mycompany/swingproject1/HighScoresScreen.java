/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.swingproject1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author PK_Flunr
 */
class HighScoresScreen extends JPanel {
    private JButton backButton;
    public CardLayout cardLO;
    public JPanel panel;
    public int offset = 15;
    
    private ArrayList<HighScore> scoreList;
    
    private JLabel[] scoreLabels;

    public HighScoresScreen(CardLayout c, JPanel p){
        scoreList = HighScore.getHighScores();
        
        setSize(600,400);
        setVisible(true);
        setEnabled(true);
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        cardLO = c;
        panel = p;
        //back button
            backButton = new JButton("back");
            Dimension buttonSize = backButton.getPreferredSize();
            backButton.setBounds(475, 325, buttonSize.width, buttonSize.height);
            backButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    backButton.setEnabled(false);
                    backButton.setVisible(false);
                    setEnabled(false);
                    cardLO.show(panel, "Menu");
                }
            });
            backButton.setHorizontalAlignment(SwingConstants.CENTER);
            backButton.setToolTipText("Return to menu screen");
            add(backButton);
        
        // score labels
        // empty array
        scoreLabels = new JLabel[5];
        for(int i = 0; i < 5; i++) {
            // create a new label
            scoreLabels[i] = new JLabel();
            // empty score
            if(scoreList.size() < i + 1) {
                scoreLabels[i].setText("Name: N/A Score: 000");
            }
            // if the score exists...
            else {
                HighScore targetScore = scoreList.get(i);
                assert(targetScore != null);
                scoreLabels[i].setText("Name: " + targetScore.getInitials() + " Score: " + targetScore.getScore());
            }
            scoreLabels[i].setHorizontalAlignment(SwingConstants.CENTER);
            add(scoreLabels[i]);
        }
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        backButton.setEnabled(true);
        backButton.setVisible(true);
        
        scoreList = HighScore.getHighScores();
        for(int i = 0; i < 5; i++) {
            if(scoreList.size() < i + 1) {
                scoreLabels[i].setText("Name: N/A Score: 000");
            }
            // if the score exists...
            else {
                HighScore targetScore = scoreList.get(i);
                assert(targetScore != null);
                scoreLabels[i].setText("Name: " + targetScore.getInitials() + " Score: " + targetScore.getScore());
            }
            scoreLabels[i].setEnabled(true);
            scoreLabels[i].setVisible(true);
        }
    }

}

// a data class that holds a high score
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
    
    public static ArrayList<HighScore> getHighScores() {
        // load the file containing the list; if it doesn't exist, create an empty highscore arraylist and serialize it into the file
        File highScoreFile = new File("highscore.ser");
        if(!highScoreFile.exists()) {
            ArrayList<HighScore> emptyList = new ArrayList<HighScore>();
            
            setHighScores(emptyList);
            System.out.println("new high score list created!");
        }
        
        // deserialize high score file
        
        ArrayList<HighScore> outList = new ArrayList<HighScore>();
        
        try {
            FileInputStream fileIn = new FileInputStream(highScoreFile);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            outList = (ArrayList<HighScore>) objectIn.readObject();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            c.printStackTrace();
        }
        
        return outList;
    }
    
    public static void setHighScores(ArrayList<HighScore> list) {
        try {
                FileOutputStream fileOut = new FileOutputStream("highscore.ser");
                ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
                objectOut.writeObject(list);
                
                objectOut.close();
                fileOut.close();
            }
            catch (IOException i) {
                i.printStackTrace();
            }
    }
}