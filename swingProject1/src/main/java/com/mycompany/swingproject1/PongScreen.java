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
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *
 * @author other
 */
public class PongScreen extends JPanel implements ActionListener{
    public CardLayout cardLO;
    public JPanel panel;
    private JPanel pongPanel;
    private JPanel p1Paddle;
    private JPanel p2Paddle;
    private String currentDate = "";
    private String currentTime = "";
    private JLabel dateAndTimeDisplayer;
    private JLabel title;
    private JLabel p1Label;
    private JLabel p2Label;
    private JLabel p1ScoreLabel;
    private JLabel p2ScoreLabel;
    private int p1Score;
    private int p2Score;
    private JButton quit;
    private boolean acceptInput;

    private Timer timeAndDateTimer;

    
    public PongScreen(CardLayout c, JPanel p){
        setLayout(null);
        setSize(600, 400);
        setVisible(true);
        setEnabled(true);
        cardLO = c;
        panel = p;
        acceptInput = false;
        
        //time and date
        dateAndTimeDisplayer = new JLabel();
        dateAndTimeDisplayer.setBounds(420, 5, 200, 20);
        dateAndTimeDisplayer.setVisible(true);
        timeAndDateTimer = new Timer(1000, this);
        timeAndDateTimer.start();
        timeAndDateTimer.setRepeats(true);  
        add(dateAndTimeDisplayer);
        
        //title
        title = new JLabel();
        title.setBounds(10, 5, 200, 20);
        title.setVisible(true);
        title.setText("Pong");
        add(title);
        
        //player 1 labels
        p1Label = new JLabel();
        p1Label.setBounds(15, 30, 200, 20);
        p1Label.setVisible(true);
        p1Label.setText("Player 1 Score:");
        add(p1Label);
        
        p1ScoreLabel = new JLabel();
        p1ScoreLabel.setBounds(55, 50, 200, 20);
        p1ScoreLabel.setVisible(true);
        p1ScoreLabel.setText(Integer.toString(p1Score));
        add(p1ScoreLabel);
        
        
        //player 2 labels
        p2Label = new JLabel();
        p2Label.setBounds(475, 30, 200, 20);
        p2Label.setVisible(true);
        p2Label.setText("Player 2 Score:");
        add(p2Label);
        
        p2ScoreLabel = new JLabel();
        p2ScoreLabel.setBounds(515, 50, 200, 20);
        p2ScoreLabel.setVisible(true);
        p2ScoreLabel.setText(Integer.toString(p2Score));
        add(p2ScoreLabel);
        
        //pong panel
        pongPanel = new JPanel();
        pongPanel.setLayout(null);
        pongPanel.setBounds(135, 30, 300, 275);
        pongPanel.setVisible(true);
        pongPanel.setEnabled(true);
        pongPanel.setBackground(Color.black);
        add(pongPanel);
        
        //player 1 paddle
        p1Paddle = new JPanel();
        p1Paddle.setLayout(null);
        p1Paddle.setBounds(40, 100, 10, 50);
        p1Paddle.setVisible(true);
        p1Paddle.setEnabled(true);
        p1Paddle.setBackground(Color.white);
        pongPanel.add(p1Paddle);
        
        //player 2 paddle
        p2Paddle = new JPanel();
        p2Paddle.setLayout(null);
        p2Paddle.setBounds(250, 100, 10, 50);
        p2Paddle.setVisible(true);
        p2Paddle.setEnabled(true);
        p2Paddle.setBackground(Color.white);
        pongPanel.add(p2Paddle);
        
        
        //quit button
        quit = new JButton("quit");
        quit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetBoard();
                acceptInput = false;
                repaint();
                setEnabled(false);
                cardLO.show(panel, "Menu");
            }
        });
        quit.setBounds(475, 325, 100, 20);
        quit.setToolTipText("Return to menu screen");
        add(quit);
        
        //controls for pong
        //reset board and toggle accepting input
        Action reset = new AbstractAction(){
        public void actionPerformed(ActionEvent e){
            System.out.println("reset");
            resetBoard();
          }
        };
        panel.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "reset");
        panel.getActionMap().put("reset", reset);

        
        //player 1
        Action moveP1Up = new AbstractAction(){
        public void actionPerformed(ActionEvent e){
            System.out.println("moveP1Up");
            if(acceptInput == true){
            movePlayer1(-5);
            }
           }
        };
        panel.getInputMap().put(KeyStroke.getKeyStroke('w'), "moveP1Up");
        panel.getActionMap().put("moveP1Up", moveP1Up);
        Action moveP1Down = new AbstractAction(){
        public void actionPerformed(ActionEvent e){
            System.out.println("moveP1Down");
            if(acceptInput == true){
            movePlayer1(5);
            }
           }
        };
        panel.getInputMap().put(KeyStroke.getKeyStroke('s'), "moveP1Down");
        panel.getActionMap().put("moveP1Down", moveP1Down);
        //player 2
        Action moveP2Up = new AbstractAction(){
        public void actionPerformed(ActionEvent e){
            System.out.println("moveP2Up");
            if(acceptInput == true){
            movePlayer2(-5);
            }
           }
        };
        panel.getInputMap().put(KeyStroke.getKeyStroke("UP"), "moveP2Up");
        panel.getActionMap().put("moveP2Up", moveP2Up);
        Action moveP2Down = new AbstractAction(){
        public void actionPerformed(ActionEvent e){
            System.out.println("moveP2Down");
            if(acceptInput == true){
            movePlayer2(5);
            }
           }
        };
        panel.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "moveP2Down");
        panel.getActionMap().put("moveP2Down", moveP2Down);
        
        
    }
    
    public void movePlayer1(int direction){
        remove(pongPanel);
        pongPanel.remove(p1Paddle);
        int y = p1Paddle.getY() + direction;
        //prevent paddle from going off screen
        if(y < 0){
         y = 0;
        }
        else if(y > 225){
         y = 225;
        }
        System.out.println(y);
        p1Paddle = new JPanel();
        p1Paddle.setLayout(null);
        p1Paddle.setBounds(40, y, 10, 50);
        p1Paddle.setVisible(true);
        p1Paddle.setEnabled(true);
        p1Paddle.setBackground(Color.white);
        pongPanel.add(p1Paddle);
        add(pongPanel);
        repaint();
    }
    
        public void movePlayer2(int direction){
        remove(pongPanel);
        pongPanel.remove(p2Paddle);
        int y = p2Paddle.getY() + direction;
        //prevent paddle from going off screen
        if(y < 0){
         y = 0;
        }
        else if(y > 225){
         y = 225;
        }
        System.out.println(y);
        p2Paddle = new JPanel();
        p2Paddle.setLayout(null);
        p2Paddle.setBounds(250, y, 10, 50);
        p2Paddle.setVisible(true);
        p2Paddle.setEnabled(true);
        p2Paddle.setBackground(Color.white);
        pongPanel.add(p2Paddle);
        add(pongPanel);
        repaint();
    }
        
        public void resetBoard(){
        remove(pongPanel);
        pongPanel.remove(p1Paddle);
        pongPanel.remove(p2Paddle);
        p1Paddle = new JPanel();        
        p2Paddle = new JPanel();
        p1Paddle.setLayout(null);        
        p2Paddle.setLayout(null);
        p1Paddle.setBounds(40, 100, 10, 50);        
        p2Paddle.setBounds(250, 100, 10, 50);
        p1Paddle.setVisible(true);        
        p2Paddle.setVisible(true);
        p1Paddle.setEnabled(true);        
        p2Paddle.setEnabled(true);
        p1Paddle.setBackground(Color.white);        
        p2Paddle.setBackground(Color.white);
        pongPanel.add(p1Paddle);        
        pongPanel.add(p2Paddle);
        add(pongPanel);
        repaint();
        if(acceptInput == false){
            acceptInput = true;
        }
    }
    
    
    public void actionPerformed(ActionEvent e){
        //for update time/date
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
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

}
