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
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import java.io.*;
import javax.imageio.*;
import javax.swing.KeyStroke;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author PK_Flunr
 */
class MenuScreen extends JPanel {

    private JButton playButton;
    private JButton highScoresButton;
    private JButton creditsButton;
    private JButton pongButton;
    private String screenName = "";
    public CardLayout cardLO;
    public JPanel panel;
    private BufferedImage pic = null;


    public MenuScreen(CardLayout c, JPanel p) {
        repaint();
        setLayout(null);
        setSize(600, 400);
        setVisible(true);
        setEnabled(true);
        cardLO = c;
        panel = p;
        //play button
        pongButton = new JButton("Play Pong");
        pongButton.setToolTipText("Play pong");
        pongButton.setBounds(450, 250, 120, 20);
        pongButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                screenName = "Pong";
                disableButtons();
                setEnabled(false);
                cardLO.show(panel, "Pong");
            }
        });
        add(pongButton);
        //play button
        playButton = new JButton("Play");
        playButton.setToolTipText("Play games");
        playButton.setBounds(450, 275, 120, 20);
        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                screenName = "Play";
                disableButtons();
                setEnabled(false);
                cardLO.show(panel, "Play");
            }
        });
        add(playButton);
        //high scores button
        highScoresButton = new JButton("High Scores");
        highScoresButton.setToolTipText("View high scores screen");
        highScoresButton.setBounds(450, 300, 120, 20);
        highScoresButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                screenName = "High Scores";
                disableButtons();
                setEnabled(false);
                cardLO.show(panel, "High Scores");
            }
        });
        add(highScoresButton);
        //credits button
        creditsButton = new JButton("Credits");
        creditsButton.setToolTipText("View credits screen");
        creditsButton.setBounds(450, 325, 120, 20);
        creditsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                screenName = "Credits";
                disableButtons();
                setEnabled(false);
                cardLO.show(panel, "Credits");
            }
        });
        add(creditsButton);
        repaint();
    }

    public void disableButtons() {
        playButton.setVisible(false);
        playButton.setEnabled(false);
        highScoresButton.setVisible(false);
        highScoresButton.setEnabled(false);
        creditsButton.setVisible(false);
        creditsButton.setEnabled(false);
    }
    
      public void enableButtons() {
        playButton.setVisible(true);
        playButton.setEnabled(true);
        highScoresButton.setVisible(true);
        highScoresButton.setEnabled(true);
        creditsButton.setVisible(true);
        creditsButton.setEnabled(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        try{
            pic = ImageIO.read(new File("src\\hangman_06.png")); //in documents
        }catch(IOException e){}
        g.drawImage(pic, 50, 50, 150, 150, this);
        enableButtons();
    }
}
