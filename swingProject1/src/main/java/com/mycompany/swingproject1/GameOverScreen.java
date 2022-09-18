/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.swingproject1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author PK_Flunr
 */
class GameOverScreen extends JPanel {
    private JButton backButton;
    private JLabel scoreLabel;

    public GameOverScreen(int score){
        setSize(600,400);
        setVisible(true);
        setLayout(new FlowLayout());
        
        scoreLabel = new JLabel();
        scoreLabel.setText("Score: " + Integer.toString(score));
        add(scoreLabel);
         
        //back button
            backButton = new JButton("End");
            backButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    backButton.setEnabled(false);
                    backButton.setVisible(false);
                    repaint();
                    setEnabled(false);
                    add(new MenuScreen());
                }
            });
            add(backButton);
        repaint();
    }

}