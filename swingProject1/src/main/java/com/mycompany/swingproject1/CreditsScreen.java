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
class CreditsScreen extends JPanel {
    private Button backButton;

    public CreditsScreen(){
        setSize(600,400);
        setVisible(true);
        //back button
            backButton = new Button("back");
            backButton.setBounds(475, 325, 100, 20);
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
    }
    
    public void paintComponent(Graphics g){
        g.drawString("credits screen", 250, 175);
    }

}