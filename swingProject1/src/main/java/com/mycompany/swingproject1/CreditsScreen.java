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
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author PK_Flunr
 */
class CreditsScreen extends JPanel {
    private JButton backButton;
    
    private JLabel creditsLabel;
    public CardLayout cardLO;
    public JPanel panel;

    public CreditsScreen(CardLayout c, JPanel p){
        setSize(600,400);
        setVisible(true);
        setEnabled(true);
        cardLO = c;
        panel = p;
        // label holding credits
        var creditsText = "<html>Credits:<br/>Miguel Valmonte 014714427<br/>Lindsey Pera 015215889</html>";
        creditsLabel = new JLabel(creditsText);
        Dimension creditsDimension = creditsLabel.getPreferredSize();
        creditsLabel.setBounds(250,175,creditsDimension.width,creditsDimension.height);
        add(creditsLabel);
        
        //back button
            backButton = new JButton("back");
            backButton.setBounds(475, 325, 100, 20);
            backButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    backButton.setEnabled(false);
                    backButton.setVisible(false);
                    //repaint();
                    //removeAll();
                    setEnabled(false);
                    cardLO.show(panel, "Menu");
                    //add(new MenuScreen());
                }
            });
            backButton.setToolTipText("Return to menu screen");
            add(backButton);
    }
    
     public void paintComponent(Graphics g) {
        super.paintComponent(g);
        backButton.setEnabled(true);
        backButton.setVisible(true);
    }

}