/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.swingproject1;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author other
 */
public class SwingProject1 {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                createAndShowGUI();
            }
        });    
    }
    
    private static void createAndShowGUI(){
    JFrame frame = new JFrame("Swing Project 1.0");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(new Screen());
    frame.pack(); //update JFrame to show new additions/info
    frame.setSize(600,400);
    frame.setLocationRelativeTo(null); //centers window
    frame.setVisible(true);
    }
    
}

 class Screen extends JPanel implements ActionListener{
     private Timer screenTimer = new Timer(3000, this);
     
        public Screen(){
            //start 3 second timer
            screenTimer.start();
            screenTimer.setRepeats(false);
        }
        
        public void actionPerformed(ActionEvent e){
            screenTimer.stop();
            repaint();
            setEnabled(false);
            add(new MenuScreen());
        }
        
        public void paintComponent(Graphics g){ //most important method for custom graphics
            super.paintComponent(g);
            if(screenTimer.isRunning() == true){
            g.drawString("title", 275, 175);
            }else{
                g.dispose();
            }
        }
    
    }

class MenuScreen extends JPanel {
    private Button playButton;
    private Button highScoresButton;
    private Button creditsButton;
    private String screenName = "";
    
    public MenuScreen(){
        setSize(600,400);
        setVisible(true);
        //play button
            playButton = new Button("Play");
            playButton.setBounds(475, 275, 100, 20);
            playButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    screenName = "Play";
                    disableButtons();
                    repaint();
                    setEnabled(false);
                    add(new PlayScreen());
                }
            });
            add(playButton);
        //HS button
            highScoresButton = new Button("High Scores");
            highScoresButton.setBounds(475, 300, 100, 20);
            highScoresButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    screenName = "High Scores";
                    disableButtons();
                    repaint();
                    setEnabled(false);
                    add(new HighScoresScreen());
                }
            });
            add(highScoresButton);
        //credits button
            creditsButton = new Button("Credits");
            creditsButton.setBounds(475, 325, 100, 20);
            creditsButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    screenName = "Credits";
                    disableButtons();
                    repaint();
                    setEnabled(false);
                    add(new CreditsScreen());
                }
            });
            add(creditsButton);
    }
    
    public void disableButtons(){
        playButton.setVisible(false);
        playButton.setEnabled(false);
        highScoresButton.setVisible(false);
        highScoresButton.setEnabled(false);
        creditsButton.setVisible(false);
        creditsButton.setEnabled(false);
    }
    
    public void paintComponent(Graphics g){
        g.drawString("menu", 265, 175);
    }
}

class PlayScreen extends JPanel implements ActionListener{
    private Button backButton;
    private String currentDate = "";
    private String currentTime = "";
    //triggers the timer to update every second
    private Timer timeAndDateTimer = new Timer(1000, this);
    //generate random number between 0-4
    //to randomly choose one of the five words
    private int randomNum = (int)(Math.random() * 5);
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
            
            
            //randomly choose word
            switch(randomNum){
                case 0:
                    targetWord = "abstract";
                break;
                case 1:
                    targetWord = "cemetery";
                break;
                case 2:
                    targetWord = "nurse";
                break;
                case 3:
                    targetWord = "pharmacy";
                break;
                case 4:
                    targetWord = "climbing";
                break;
                default:
                    targetWord = "abstract";
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

class HighScoresScreen extends JPanel {
    private Button backButton;

    public HighScoresScreen(){
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
        g.drawString("HS screen", 250, 175);
    }

}

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

class GameOverScreen extends JPanel {
    private Button backButton;

    public GameOverScreen(){
        setSize(600,400);
        setVisible(true);
        //back button
            backButton = new Button("end");
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
        g.drawString("game over screen", 250, 175);
    }

}
