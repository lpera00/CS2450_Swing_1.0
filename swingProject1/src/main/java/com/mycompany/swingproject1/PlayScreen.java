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
    private String targetWord = "";
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
    
    private int currentScore;
    
    
    private static BufferedImage hangmanImage = null;

    
    public PlayScreen() {
        setSize(600, 400);
        setVisible(true);
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
        //backButton.setBounds(475, 325, 100, 20);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                errors = 0;
                backButton.setEnabled(false);
                backButton.setVisible(false);
                restartButton.setEnabled(false);
                restartButton.setVisible(false);
                removeAll();
                setLayout(new FlowLayout());
                repaint();
                setEnabled(false);
                add(new MenuScreen());
                timeAndDateTimer.stop();
            }
        });
        upperContainer.add(backButton);

        //skip button
        skipButton = new JButton("Skip");
        skipButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                endGame(0);
            }
        });
        upperContainer.add(skipButton);
        
        //restart button
        restartButton = new JButton("Next");
        restartButton.setVisible(false);
        //testIncorrectButton.setBounds(75, 325, 100, 20);
        restartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //restart();
                endGame(currentScore);
                restartButton.setVisible(false);
            }
        });
        upperContainer.add(restartButton);
        
        testAnswerDisplayer = new JLabel();
        upperContainer.add(testAnswerDisplayer);
        
        dateAndTimeDisplayer = new JLabel();
        getCurrentDateAndTime();
        upperContainer.add(dateAndTimeDisplayer);
        

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
        //keyboard.setBounds(-600,300,size.width,size.height);
        restart();
        
        repaint();

    }
    
    private void restart() {
        targetWord = chooseWord();
        testAnswerDisplayer.setText("target word: " + targetWord);
        
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
    
    private void endGame(int score) {
        errors = 0;
        backButton.setEnabled(false);
        backButton.setVisible(false);
        restartButton.setEnabled(false);
        restartButton.setVisible(false);
        setEnabled(false);
        removeAll();
        setLayout(new FlowLayout());
        add(new GameOverScreen(score));
        timeAndDateTimer.stop();
        repaint();
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
    
    private void chooseHangmanImage(){
    //choose hangman image based on # of errors
            switch(errors){
            case 0:
                try{
                    hangmanImage = ImageIO.read(new File("src\\hangman_00.png"));
                }catch(IOException e){}
                break;
            case 1:
                try{
                    hangmanImage = ImageIO.read(new File("src\\hangman_01.png"));
                }catch(IOException e){}            
                break;
            case 2:
                try{
                    hangmanImage = ImageIO.read(new File("src\\hangman_02.png"));
                }catch(IOException e){}            
                break;
            case 3:
                try{
                    hangmanImage = ImageIO.read(new File("src\\hangman_03.png"));
                }catch(IOException e){}            
                break;
            case 4:
                try{
                    hangmanImage = ImageIO.read(new File("src\\hangman_04.png"));
                }catch(IOException e){}            
                break;
            case 5:
                try{
                    hangmanImage = ImageIO.read(new File("src\\hangman_05.png"));
                }catch(IOException e){}            
                break;
            case 6:
                try{
                    hangmanImage = ImageIO.read(new File("src\\hangman_06.png"));
                }catch(IOException e){}            
                break;
            case 7:
                    errors = 0;
                    backButton.setEnabled(false);
                    backButton.setVisible(false);
                    restartButton.setEnabled(false);
                    restartButton.setVisible(false);
                    repaint();
                    setEnabled(false);
                    add(new GameOverScreen(0));
                    timeAndDateTimer.stop();          
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
            }
        }
        
        gameplayLabelString = String.valueOf(gameplayStringArray);
        
        if(!success) {
            errors++;
            chooseHangmanImage();
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
    //redraws timer w/ current date and time
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