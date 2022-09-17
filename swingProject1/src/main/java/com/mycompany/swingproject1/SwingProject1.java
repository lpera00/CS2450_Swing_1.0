/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.mycompany.swingproject1;

import javax.swing.*;

/**
 *
 * @author other
 */
public class SwingProject1 {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Swing Project 1.0");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new TitleScreen());
        frame.pack(); //update JFrame to show new additions/info
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null); //centers window
        frame.setVisible(true);
    }

}
