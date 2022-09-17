/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.swingproject1;

import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author PK_Flunr
 */
class PlayScreen extends JPanel implements ActionListener{
    private Button backButton;
    private String currentDate = "";
    private String currentTime = "";
    //triggers the timer to update every second
    private Timer timeAndDateTimer = new Timer(1000, this);
    private String targetWord = "";
    //errors = incorrect guesses
    private static int errors = 0;
    private Button testCorrectButton;
    private Button testIncorrectButton;
    private static BufferedImage hangmanImage = null;

    
    public PlayScreen(){
        setSize(600,400);
        setVisible(true);
        timeAndDateTimer.start();
        timeAndDateTimer.setRepeats(true);
        errors = 0;
        chooseHangmanImage();
        getCurrentDateAndTime();
        
        //back button
        backButton = new Button("back");
            backButton.setBounds(475, 325, 100, 20);
            backButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    errors = 0;
                    backButton.setEnabled(false);
                    backButton.setVisible(false);
                    testIncorrectButton.setEnabled(false);
                    testIncorrectButton.setVisible(false);
                    repaint();
                    setEnabled(false);
                    add(new MenuScreen());
                    timeAndDateTimer.stop();
                }
            });
            add(backButton);
            
          //back button
            testIncorrectButton = new Button("test incorrect");
            testIncorrectButton.setBounds(75, 325, 100, 20);
            testIncorrectButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    errors++;
                    chooseHangmanImage();
                }
            });
            add(testIncorrectButton);
            
            targetWord = chooseWord();
            
            
            
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
    
    public void chooseHangmanImage(){
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
                    testIncorrectButton.setEnabled(false);
                    testIncorrectButton.setVisible(false);
                    repaint();
                    setEnabled(false);
                    add(new GameOverScreen());
                    timeAndDateTimer.stop();          
                break;
            default:
                try{
                    hangmanImage = ImageIO.read(new File("src\\hangman_00.png"));
                }catch(IOException e){}        
            }
            repaint();
    }
    
    //action performed every second
    //redraws timer w/ current date and time
    public void actionPerformed(ActionEvent e){
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
    }
            
            
    
    public void paintComponent(Graphics g){
        //display hangman image
        g.drawImage(hangmanImage, 180, 30, 150, 150, this);            
        //display current date w/ a comma after the day
        g.setColor(Color.white);
        g.fillRect(400, 3, 180, 20);
        g.setColor(Color.black);
        if(currentDate.length() > 0 && currentTime.length() > 0){
        g.drawString(currentDate.substring(0, currentDate.length() - 5) + "," + 
                currentDate.substring(currentDate.length() - 5, currentDate.length())
                + " " + currentTime, 410, 15);
        }
        g.drawString("target word: " + targetWord, 10, 20);
        int offset = 40;
        for(int i = 0; i < targetWord.length(); i++){
            g.fillRect(160 + (i*offset), 200, 30, 3);
        }
    }

}