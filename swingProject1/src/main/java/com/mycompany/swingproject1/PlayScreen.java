/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.swingproject1;

import java.awt.Button;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author PK_Flunr
 */
class PlayScreen extends JPanel implements ActionListener{
    private JButton backButton;
    private String currentDate = "";
    private String currentTime = "";
    //triggers the timer to update every second
    private Timer timeAndDateTimer = new Timer(1000, this);
    private String targetWord = chooseWord();
    //errors = incorrect guesses
    private static int errors = 0;
    private JButton skipButton;
    private JButton restartButton;
    
    private JLabel hangmanImageDisplayer;
    
    private JPanel upperContainer;
    private JLabel dateAndTimeDisplayer;
    private JLabel testAnswerDisplayer;
    
    private JLabel gameplayLabel;
    private String gameplayLabelString;
    
    private JLabel gameplayUnderline;
    
    private LetterUI keyboard;
    
    public static int currentScore;
    
    public static boolean end = false;
    
    
    private static BufferedImage hangmanImage = null;
    public CardLayout cardLO;
    public JPanel panel;
    
    public JLabel wrongLetterAlert;
    
    public PlayScreen(CardLayout c, JPanel p) {
        setSize(600, 400);
        setVisible(true);
        setEnabled(true);
        cardLO = c;
        panel = p;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        timeAndDateTimer.start();
        timeAndDateTimer.setRepeats(true);
        errors = 0;
        
        
        upperContainer = new JPanel();
        upperContainer.setVisible(true);
        upperContainer.setLayout(new FlowLayout());
        add(upperContainer);
        
        
        //back button
        backButton = new JButton("back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                errors = 0;
                currentScore = 100;
                targetWord = chooseWord();
                restart();
                repaint();
                setEnabled(false);
                cardLO.show(panel, "Menu");
            }
        });
        backButton.setToolTipText("Return to menu screen");
        upperContainer.add(backButton);

        //skip button
        skipButton = new JButton("Skip");
        skipButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                restart();
                currentScore = 0;
                endGame(0);
            }
        });
        skipButton.setToolTipText("Skip to next game");
        upperContainer.add(skipButton);
        
        //restart button
        restartButton = new JButton("Next");
        restartButton.setVisible(false);
        restartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int score = currentScore;
                restart();
                keyboard.enableAll();
                setScore(score);
                endGame(score);
                restartButton.setVisible(false);
            }
        });
        upperContainer.add(restartButton);
        
        testAnswerDisplayer = new JLabel();
        upperContainer.add(testAnswerDisplayer);
        
        dateAndTimeDisplayer = new JLabel();
        getCurrentDateAndTime();
        upperContainer.add(dateAndTimeDisplayer);
        
        wrongLetterAlert = new JLabel();
        wrongLetterAlert.setText("Letter not found!");
        wrongLetterAlert.setForeground(Color.red);
        wrongLetterAlert.setVisible(false);
        upperContainer.add(wrongLetterAlert);
        
        hangmanImageDisplayer = new JLabel();
        add(hangmanImageDisplayer);
        
        add(Box.createVerticalGlue());
        
        Font gameplayFont = new Font("Consolas",Font.BOLD,40);
        
        gameplayLabel = new JLabel();
        
        gameplayLabel.setAlignmentY(BOTTOM_ALIGNMENT);
        
        gameplayLabel.setFont(gameplayFont);
        add(gameplayLabel);
        
        gameplayUnderline = new JLabel();
        
        gameplayLabel.setAlignmentY(TOP_ALIGNMENT);
        
        gameplayUnderline.setFont(gameplayFont);
        add(gameplayUnderline);

        add(Box.createRigidArea(new Dimension(0,5)));
        
        keyboard = new LetterUI(this);
        add(keyboard);
        Dimension size = keyboard.getPreferredSize();
        restart();
        
        repaint();

    }
    
    //resets game screen and variables between resets
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(end == true){
        wrongLetterAlert.setVisible(false);
        currentScore = 100;
        errors = 0;
        keyboard.enableAll();
        skipButton.setEnabled(true);        
        skipButton.setVisible(true);
        end = false;
        }
    }
   
    //resets game screen and variables
    private void restart() {
        end = false;
        wrongLetterAlert.setVisible(false);
        targetWord = chooseWord();
        
        errors = 0;
        chooseHangmanImage();
        
        gameplayLabelString = "";
        for(int i = 0; i < targetWord.length(); i++){
            gameplayLabelString += " ";
        }
        gameplayLabel.setText(gameplayLabelString);
        
        String underline = "";
        for(int i = 0; i < targetWord.length(); i++){
            underline += "-";
        }
        gameplayUnderline.setText(underline);
        
        keyboard.enableAll();
        
        currentScore = 100;
    }
    
    //ends game, shows game over screen, resets game screen
    private void endGame(int score) {
        int savedScore = score;
        errors = 0;
        wrongLetterAlert.setVisible(false);
        targetWord = chooseWord();
        setEnabled(false);
        System.out.println("current score: " + currentScore);
        setScore(currentScore);
        //cardLO.show(panel, "Game Over");        
        cardLO.show(panel, "Color Game");
        restart();
        end = true;
        repaint();
        setScore(savedScore);
    }
    
    //sets value of current score
    public void setScore(int score){
        currentScore = score;
    }
    
    // returns one of the specified words.
    private String chooseWord() {
    //generate random number between 0-4
    //to randomly choose one of the five words
    int randomNum = (int) (Math.random() * 5);
    //randomly choose word
    switch (randomNum) {
        case 0:
            return "abstract";
        case 1:
            return "cemetery";
        case 2:
            return "nurse";
        case 3:
            return "pharmacy";
        case 4:
            return "climbing";
        default:
            return "abstract";
    }
}
    //choose hangman image based on # of errors
    private void chooseHangmanImage(){
            switch(errors){
            case 0:
                currentScore = 100;
                try{
                    hangmanImage = ImageIO.read(new File("src\\hangman_00.png"));
                }catch(IOException e){}
                break;
            case 1:
                currentScore = 90;
                try{
                    hangmanImage = ImageIO.read(new File("src\\hangman_01.png"));
                }catch(IOException e){}            
                break;
            case 2:
                currentScore = 80;
                try{
                    hangmanImage = ImageIO.read(new File("src\\hangman_02.png"));
                }catch(IOException e){}            
                break;
            case 3:
                currentScore = 70;
                try{
                    hangmanImage = ImageIO.read(new File("src\\hangman_03.png"));
                }catch(IOException e){}            
                break;
            case 4:
                currentScore = 60;
                try{
                    hangmanImage = ImageIO.read(new File("src\\hangman_04.png"));
                }catch(IOException e){}            
                break;
            case 5:
                currentScore = 50;
                try{
                    hangmanImage = ImageIO.read(new File("src\\hangman_05.png"));
                }catch(IOException e){}            
                break;
            case 6:
                restart();
                keyboard.enableAll();
                currentScore = 40;
                try{
                    hangmanImage = ImageIO.read(new File("src\\hangman_06.png"));
                }catch(IOException e){}        
                endGame(currentScore);
                break;
            default:
                try{
                    hangmanImage = ImageIO.read(new File("src\\hangman_00.png"));
                }catch(IOException e){}        
            }
            // displays image using hangmanImageDisplayer
            hangmanImageDisplayer.setIcon(new ImageIcon(hangmanImage));
            repaint();
    }
    
    private void tryLetter(char letter) {
        boolean success = false;
        char[] gameplayStringArray = gameplayLabelString.toCharArray();
        
        for (int i = 0; i < targetWord.length(); i++) {
            if(targetWord.charAt(i) == letter) {
                gameplayStringArray[i] = letter;
                success = true;
                wrongLetterAlert.setVisible(false);
            }
        }
        
        gameplayLabelString = String.valueOf(gameplayStringArray);
        
        if(!success) {
            errors++;
            chooseHangmanImage();
            wrongLetterAlert.setVisible(true);
        }
        
        gameplayLabel.setText(gameplayLabelString);
        
        if(gameplayLabelString.equals(targetWord)) {
            win();
        }
        
        if(errors >= 6) {
            lose();
        }
    }
    
    private void win() {
        restartButton.setVisible(true);
        skipButton.setVisible(false);
        keyboard.disableAll();
    }
    
    private void lose() {
        restartButton.setVisible(true);
        skipButton.setVisible(false);
        keyboard.disableAll();
    }
    
    //action performed every second
    //redraws timer with current date and time
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() != null && "LetterButton".equals(e.getActionCommand().substring(1))) {
            char letter = e.getActionCommand().charAt(0);
            tryLetter(letter);
            keyboard.getButton(letter).setEnabled(false);
        }
        getCurrentDateAndTime();
        repaint();
    }
    
    public void getCurrentDateAndTime(){
        Date date = new Date();
        Instant now = Instant.now();
            //date
            SimpleDateFormat formatter = new SimpleDateFormat("MMMM dd yyyy");
            currentDate = formatter.format(date);
            //time
            DateTimeFormatter time = DateTimeFormatter.ofPattern("HH:mm:ss");
            LocalTime localTime = LocalTime.ofInstant(now, ZoneId.systemDefault());
            currentTime = time.format(localTime);
            
            dateAndTimeDisplayer.setText(currentDate.substring(0, currentDate.length() - 5) + "," + 
                currentDate.substring(currentDate.length() - 5, currentDate.length())
                + " " + currentTime);
    }
            
            
    
}

class LetterUI extends JPanel {
    private JButton[] buttonArray;
    
    public LetterUI(ActionListener parent) {
        buttonArray = new JButton[123]; // this way, we can use chars to find individual buttons
        
        setSize(600,150);
        // using GridLayout
        setLayout(new GridLayout(2,13));
        
        for(char i = 'a'; i <= 'z'; i++) {
            JButton newButton = new JButton("" + i);
            newButton.setActionCommand("" + i + "LetterButton");
            newButton.addActionListener(parent);
            newButton.setToolTipText("Guess this letter");
            add(newButton);
            
            buttonArray[i] = newButton;
        }
    }
    
    public JButton getButton(char letter) {
        return buttonArray[letter];
    }
    
    public void disableAll() {
        for(char i = 'a'; i <= 'z'; i++) {
            getButton(i).setEnabled(false);
        }
    }
    
    public void enableAll() {
        for(char i = 'a'; i <= 'z'; i++) {
            getButton(i).setEnabled(true);
        }
    }
}