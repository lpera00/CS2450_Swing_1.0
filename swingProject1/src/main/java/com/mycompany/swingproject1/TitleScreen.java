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

/**
 *
 * @author PK_Flunr
 */
class TitleScreen extends JPanel implements ActionListener {

    private Timer screenTimer = new Timer(3000, this);

    public TitleScreen() {
        //start 3 second timer
        screenTimer.start();
        screenTimer.setRepeats(false);
    }

    public void actionPerformed(ActionEvent e) {
        screenTimer.stop();
        repaint();
        setEnabled(false);
        add(new MenuScreen());
    }

    public void paintComponent(Graphics g) { //most important method for custom graphics
        super.paintComponent(g);
        if (screenTimer.isRunning() == true) {
            g.drawString("title", 275, 175);
        } else {
            g.dispose();
        }
    }

}