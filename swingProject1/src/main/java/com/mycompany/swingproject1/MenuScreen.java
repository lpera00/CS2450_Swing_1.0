/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.swingproject1;

import java.awt.Button;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

/**
 *
 * @author PK_Flunr
 */
class MenuScreen extends JPanel {

    private Button playButton;
    private Button highScoresButton;
    private Button creditsButton;
    private String screenName = "";

    public MenuScreen() {
        setSize(600, 400);
        setVisible(true);
        //play button
        playButton = new Button("Play");
        playButton.setBounds(475, 275, 100, 20);
        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                screenName = "Play";
                disableButtons();
                repaint();
                setEnabled(false);
                add(new PlayScreen());
            }
        });
        add(playButton);
        //HS button
        highScoresButton = new Button("High Scores");
        highScoresButton.setBounds(475, 300, 100, 20);
        highScoresButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                screenName = "High Scores";
                disableButtons();
                repaint();
                setEnabled(false);
                add(new HighScoresScreen());
            }
        });
        add(highScoresButton);
        //credits button
        creditsButton = new Button("Credits");
        creditsButton.setBounds(475, 325, 100, 20);
        creditsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                screenName = "Credits";
                disableButtons();
                repaint();
                setEnabled(false);
                add(new CreditsScreen());
            }
        });
        add(creditsButton);
    }

    public void disableButtons() {
        playButton.setVisible(false);
        playButton.setEnabled(false);
        highScoresButton.setVisible(false);
        highScoresButton.setEnabled(false);
        creditsButton.setVisible(false);
        creditsButton.setEnabled(false);
    }

    public void paintComponent(Graphics g) {
        g.drawString("menu", 265, 175);
    }
}
