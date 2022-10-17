/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.mycompany.swingproject1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


/**
 *
 * @author other
 */
public class SwingProject1 {

    public static void main(String[] args) {
        JPanel cards = new JPanel(new CardLayout());
        CardLayout cardLO = (CardLayout) (cards.getLayout());
        JPanel title = new TitleScreen(cardLO, cards);        
        JPanel menu = new MenuScreen(cardLO, cards);
        JPanel play = new PlayScreen(cardLO, cards);        
        JPanel colorGame = new ColorGameScreen(cardLO, cards);
        JPanel sudoku = new SudokuScreen(cardLO, cards);                
        JPanel highScores = new HighScoresScreen(cardLO, cards);
        JPanel credits = new CreditsScreen(cardLO, cards);        
        JPanel gameOver = new GameOverScreen(cardLO, cards);
        cards.add(title, "Title");        
        cards.add(menu, "Menu");
        cards.add(play, "Play");        
        cards.add(colorGame, "Color Game");
        cards.add(sudoku, "Sudoku");
        cards.add(highScores, "High Scores");
        cards.add(credits, "Credits");        
        cards.add(gameOver, "Game Over");
        JFrame frame = new JFrame("Swing Project 1.0");
        Container pane = frame.getContentPane();
        pane.add(cards);
        cardLO.show(cards, "Title");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack(); //update JFrame to show new additions/info
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null); //centers window
        frame.setVisible(true);
        Action closeWindow = new AbstractAction(){
        public void actionPerformed(ActionEvent e){
            frame.dispose();
            }
        };
        cards.getInputMap().put(KeyStroke.getKeyStroke("ESCAPE"), "closeWindow");
        cards.getActionMap().put("closeWindow", closeWindow);
        Action creditsDialog = new AbstractAction(){
        public void actionPerformed(ActionEvent e){
                JOptionPane.showMessageDialog(frame, 
                        "Miguel Valmonte 014714427\n" +
                        "Lindsey Pera 015215889\n" +
                        "CS2450 Swing Project 1.2\n" + 
                        "Fall 2022", 
                        "Credits Display", 
                        JOptionPane.PLAIN_MESSAGE);
            }
        };
        cards.getInputMap().put(KeyStroke.getKeyStroke("F1"), "creditsDialog");
        cards.getActionMap().put("creditsDialog", creditsDialog);
    }
    
     
}