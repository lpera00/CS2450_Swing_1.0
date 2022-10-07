/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.swingproject1;

import java.awt.CardLayout;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 *
 * @author other
 */
public class SudokuScreen extends JPanel{
    private JButton quit;
    public CardLayout cardLO;
    public JPanel panel;
    private JFormattedTextField inputs[];

    
    public SudokuScreen(CardLayout c, JPanel p){
        setLayout(null);
        setSize(600, 400);
        setVisible(true);
        setEnabled(true);
        cardLO = c;
        panel = p;
        //initialize solution array
        int soluiton[] = {3,5,9,2,2,9,6,8,5,7,3,1,4,7,2,9,3,8,6,1,4,2,1,2,3,
        6,8,5,4,9,7,5,9,6,6,7,8,1,3,4,9,8,3,4,5,2,7,6,7,4,6,8,1};
        //create grid of text fields
        inputs = new JFormattedTextField[81];
        
        //quit button
        quit = new JButton("quit");
        quit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setEnabled(false);
                cardLO.show(panel, "Game Over");
            }
        });
        quit.setBounds(475, 325, 100, 20);
        add(quit);
        repaint();
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawString("Sudoku screen", 250, 100);
    }
}
