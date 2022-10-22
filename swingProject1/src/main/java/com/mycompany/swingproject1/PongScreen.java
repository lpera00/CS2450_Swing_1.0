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
    private Ball pongBall;
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
        
        // pong ball
        pongBall = new Ball();
        pongBall.setBackground(Color.white);
        pongPanel.add(pongBall);
        pongBall.recenter();
        
        pongBall.setP1Hitbox(p1Paddle.getBounds());
        pongBall.setP2Hitbox(p2Paddle.getBounds());
        
        //quit button
        quit = new JButton("quit");
        quit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetBoard();
                pongBall.recenter();
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
        pongBall.setP1Hitbox(p1Paddle.getBounds());
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
        pongBall.setP2Hitbox(p2Paddle.getBounds());
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
        // temp..?
        pongBall.recenter();
        pongBall.start();
        pongBall.setP1Hitbox(p1Paddle.getBounds());
        pongBall.setP2Hitbox(p2Paddle.getBounds());
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

// fuck it we ball
class Ball extends JPanel implements ActionListener {
    public static final int GAME_WINDOW_WIDTH = 300;
    public static final int GAME_WINDOW_HEIGHT = 275;
    
    private static final int BALL_SIDE_LENGTH = 10; //"ball" is square with this side length
    
    private static final int FRAMES_PER_SECOND = 40;
    private static final int MILLISECONDS_PER_SECOND = 1000; // avoid magic numbers
    private int delta;
    private Timer gameLoopTimer;
    
    private Rectangle p1Hitbox;
    private Rectangle p2Hitbox;
    
    private Boolean active;
    
    private int current_x;
    private int current_y;
    
    private static final double SPEED = 90; // in pixels/sec
    // vertical and horizontal components of normalized direction vector
    private double current_direction_x;
    private double current_direction_y;
    
    public Ball(){
        delta = MILLISECONDS_PER_SECOND / FRAMES_PER_SECOND;
        gameLoopTimer = new Timer(delta, this);
        gameLoopTimer.setActionCommand("gameTick");
        gameLoopTimer.start();
        gameLoopTimer.setRepeats(true);
        
        active = false;
    }
    
    // implementation of simple int clamp for keeping ball in bounds
    public static int clamp(int minim, int value, int maxim) {
        return Math.max(minim,Math.min(maxim, value));
    }
    
    public void recenter() {
        // ball will not move
        active = false;
        // set start position
        current_x = GAME_WINDOW_WIDTH / 2 - BALL_SIDE_LENGTH / 2;
        current_y = GAME_WINDOW_HEIGHT / 2 - BALL_SIDE_LENGTH / 2;
        // place ball
        setBounds(current_x,current_y,BALL_SIDE_LENGTH,BALL_SIDE_LENGTH);
    }
    
    public void start() {
        // this gross line of code gets a random angle in rad between -pi and pi
        double randomAngle = ((Math.random() * (2 * Math.PI)) - Math.PI);
        // then set direction vectors to their new value
        current_direction_x = Math.cos(randomAngle);
        current_direction_y = Math.sin(randomAngle);
        
        // free the ball
        active = true;
    }
    
    public void setP1Hitbox(Rectangle hitbox) {
        p1Hitbox = hitbox;
    }
    
    public void setP2Hitbox(Rectangle hitbox) {
        p2Hitbox = hitbox;
    }
    
    // called once per frame
    private void gameLoop() {
        // ball movement handled here
        if(active) {
            // oh god oh fuck
            // these next 2 lines of code are compact but really unreadable
            // for each of the x and y components, the position is moved along the movement vector
            // the speed is given in pixels/second, so frames/second value is used to
            // convert that value into pixels/frame, as this function is called once/frame
            current_x = clamp(0,(int)(current_x + current_direction_x * SPEED / FRAMES_PER_SECOND),GAME_WINDOW_WIDTH - BALL_SIDE_LENGTH);
            current_y = clamp(0,(int)(current_y + current_direction_y * SPEED / FRAMES_PER_SECOND),GAME_WINDOW_HEIGHT - BALL_SIDE_LENGTH);
            // then the new x and y values are applied to the actual component
            setBounds(current_x,current_y,BALL_SIDE_LENGTH,BALL_SIDE_LENGTH);
            
            //bouncing
            //vertical bounce
            if(current_y == 0 || current_y == GAME_WINDOW_HEIGHT - BALL_SIDE_LENGTH) {
                current_direction_y *= -1;
            }
            //p1 horizontal bounce
            if(p1Hitbox != null && getBounds().intersects(p1Hitbox.getBounds())) {
                current_direction_x = Math.abs(current_direction_x);
            }
            //p2 horizontal bounce
            if(p2Hitbox != null && getBounds().intersects(p2Hitbox.getBounds())) {
                current_direction_x = -1 * Math.abs(current_direction_x);
            }
        }
    }
    
    @Override public void actionPerformed(ActionEvent evt) {
        if (evt.getActionCommand() != null) {
            if (evt.getActionCommand().equals("gameTick")) {
                this.gameLoop();
            }
        }
    }
}