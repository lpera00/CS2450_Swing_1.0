/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.swingproject1;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.*;


/**
 *
 * @author PK_Flunr
 */
class TitleScreen extends JPanel implements ActionListener {

    private Timer screenTimer = new Timer(3000, this);
    public CardLayout cardLO;
    public JPanel panel;

    public TitleScreen(CardLayout c, JPanel p) {
        //start 3 second timer
        screenTimer.start();
        screenTimer.setRepeats(false);
        cardLO = c;
        panel = p;
    }
    
    //switch to menu screen after 3 seconds
    public void actionPerformed(ActionEvent e) {
        screenTimer.stop();
        removeAll();
        setEnabled(false);
        switchScreens(cardLO, panel);
    }
    
    public void switchScreens(CardLayout c, JPanel p){
        c.show(p, "Menu");
    }

    public void paintComponent(Graphics g) { //most important method for custom graphics
        super.paintComponent(g);
        if (screenTimer.isRunning() == true) {
            g.drawString("CS2450 Swing Project 1.0", 220, 75);
            g.drawString("By: Team ValPera", 245, 275);
        } else {
            g.dispose();
        }
    }

}