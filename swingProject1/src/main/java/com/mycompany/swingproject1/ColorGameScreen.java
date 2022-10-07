/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.swingproject1;

import static com.mycompany.swingproject1.PlayScreen.currentScore;
import static com.mycompany.swingproject1.PlayScreen.end;
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
import javax.swing.event.MouseInputAdapter;

/**
 *
 * @author other
 */
public class ColorGameScreen extends JPanel implements ActionListener{
    public CardLayout cardLO;
    public JPanel panel;
    private String currentDate = "";
    private String currentTime = "";
    private JLabel dateAndTimeDisplayer;
    private JPanel topContainer;
    private int randomNum;
    private JLabel colorTarget;
    private Font targetFont;
    private JPanel colorContainer;
    
    //grid layout
    private JPanel gridPanel;
    private JButton topLeft;    
    private JButton topMiddle;
    private JButton topRight;
    private JButton middleLeft;
    private JButton middleMiddle;
    private JButton middleRight;
    private JButton bottomLeft;
    private JButton bottomMiddle;
    private JButton bottomRight;
    private Icon redButton;    
    private Icon yellowButton;
    private Icon greenButton;
    private Icon blueButton;
    private Icon purpleButton;
    private Icon redHighlighted;    
    private Icon yellowHighlighted;
    private Icon greenHighlighted;
    private Icon blueHighlighted;
    private Icon purpleHighlighted;
    private boolean redPlaced;    
    private boolean yellowPlaced;
    private boolean greenPlaced;
    private boolean bluePlaced;
    private boolean purplePlaced;
    private int emptyButtons;
    private String targetColor;
    private int round;


    


    //triggers the timer to update every second
    private Timer timeAndDateTimer = new Timer(1000, this);
    
    public ColorGameScreen(CardLayout c, JPanel p){
        setSize(600, 400);
        setVisible(true);
        setEnabled(true);
        cardLO = c;
        panel = p;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        //top section with word and time/date
        timeAndDateTimer.start();
        timeAndDateTimer.setRepeats(true);
        colorTarget = new JLabel();        
        dateAndTimeDisplayer = new JLabel();
        topContainer = new JPanel(); 
        topContainer.setLayout(new FlowLayout());
        add(topContainer);
        
        
        //grid section with buttons
        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(3,3));
        
        //set images for color buttons
        redButton = new ImageIcon("src\\redbutton.png");        
        yellowButton = new ImageIcon("src\\yellowbutton.png");
        greenButton = new ImageIcon("src\\greenbutton.png");
        blueButton = new ImageIcon("src\\bluebutton.png");
        purpleButton = new ImageIcon("src\\purplebutton.png");
        redHighlighted = new ImageIcon("src\\redhighlighted.png");    
        yellowHighlighted = new ImageIcon("src\\yellowhighlighted.png");
        greenHighlighted = new ImageIcon("src\\greenhighlighted.png");
        blueHighlighted = new ImageIcon("src\\bluehighlighted.png");
        purpleHighlighted = new ImageIcon("src\\purplehighlighted.png");
        
        //grid buttons
        topLeft = new JButton();
        topMiddle = new JButton();
        topRight = new JButton();
        middleLeft = new JButton();
        middleMiddle = new JButton();
        middleRight = new JButton();
        bottomLeft = new JButton();
        bottomMiddle = new JButton();
        bottomRight = new JButton();
        
        //set button backgrounds and borders to be invisibe
        //so only the icons show
        topLeft.setContentAreaFilled(false);        
        topMiddle.setContentAreaFilled(false);
        topRight.setContentAreaFilled(false);
        middleLeft.setContentAreaFilled(false);
        middleMiddle.setContentAreaFilled(false);
        middleRight.setContentAreaFilled(false);
        bottomLeft.setContentAreaFilled(false);
        bottomMiddle.setContentAreaFilled(false);
        bottomRight.setContentAreaFilled(false);
        topLeft.setBorderPainted(false);        
        topMiddle.setBorderPainted(false);
        topRight.setBorderPainted(false);
        middleLeft.setBorderPainted(false);
        middleMiddle.setBorderPainted(false);
        middleRight.setBorderPainted(false);
        bottomLeft.setBorderPainted(false);
        bottomMiddle.setBorderPainted(false);
        bottomRight.setBorderPainted(false);
        
        //set action command text for each button
        topLeft.setActionCommand("top left");        
        topMiddle.setActionCommand("top middle");
        topRight.setActionCommand("top right");
        middleLeft.setActionCommand("middle left");
        middleMiddle.setActionCommand("middle middle");
        middleRight.setActionCommand("middle right");
        bottomLeft.setActionCommand("bottom left");
        bottomMiddle.setActionCommand("bottom middle");
        bottomRight.setActionCommand("bottom right");
        
        //add action listener for each button
        topLeft.addActionListener(this);
        topMiddle.addActionListener(this);
        topRight.addActionListener(this);
        middleLeft.addActionListener(this);
        middleMiddle.addActionListener(this);
        middleRight.addActionListener(this);
        bottomLeft.addActionListener(this);
        bottomMiddle.addActionListener(this);
        bottomRight.addActionListener(this);
        
        //add mouse listener to each button
        addMouseListeners(topLeft);        
        addMouseListeners(topMiddle);
        addMouseListeners(topRight);
        addMouseListeners(middleLeft);
        addMouseListeners(middleMiddle);
        addMouseListeners(middleRight);
        addMouseListeners(bottomLeft);
        addMouseListeners(bottomMiddle);
        addMouseListeners(bottomRight);


        
        //reset flags and button icons
        round = 0;
        resetBoard();
        generateTarget();
        targetFont = new Font("Consolas",Font.BOLD,25);
        colorTarget.setFont(targetFont);
        topContainer.add(colorTarget);
        topContainer.add(dateAndTimeDisplayer);

        //add buttons to grid layout panel
        gridPanel.add(topLeft);        
        gridPanel.add(topMiddle);
        gridPanel.add(topRight);
        gridPanel.add(middleLeft);
        gridPanel.add(middleMiddle);
        gridPanel.add(middleRight);
        gridPanel.add(bottomLeft);
        gridPanel.add(bottomMiddle);
        gridPanel.add(bottomRight);
        
        //add grid layout panel to box layout panel
        add(gridPanel);

    }
    
    //checks if the chosen button is the same color as
    //the text of the target word
    public void checkAnswer(JButton button){
        String icon = "";
        icon = button.getIcon().toString();
        switch(targetColor){
            case "Red":
                if(icon == "src\\redbutton.png" || icon == "src\\redhighlighted.png"){
                    currentScore += 100;
                }
                break;
            case "Yellow":
                if(icon == "src\\yellowbutton.png" || icon == "src\\yellowhighlighted.png"){
                    currentScore += 100;
                }
                break;
            case "Green":
                if(icon == "src\\greenbutton.png" || icon == "src\\greenhighlighted.png"){
                    currentScore += 100;
                }
                break;
            case "Blue":
                if(icon == "src\\bluebutton.png" || icon == "src\\bluehighlighted.png"){
                    currentScore += 100;
                }
                break;
            case "Purple":
                if(icon == "src\\purplebutton.png" || icon == "src\\purplehighlighted.png"){
                    currentScore += 100;
                }
                break;
            default:
                break;
        }
        //reset board and word for next round
        //after round 5 go to end screen
        round++;
        if (round > 4){
            resetBoard();
            generateTarget();
            round = 0;
            cardLO.show(panel, "Sudoku");
        }
        resetBoard();
        generateTarget();
    }
    
    //re-randomize the game board
    public void resetBoard(){
        //reset flags
        redPlaced = false;        
        yellowPlaced = false;
        greenPlaced = false;
        bluePlaced = false;
        purplePlaced = false;
        emptyButtons = 0;
        targetColor = "";

        
        //reset icons
        topLeft.setIcon(null);        
        topMiddle.setIcon(null); 
        topRight.setIcon(null); 
        middleLeft.setIcon(null); 
        middleMiddle.setIcon(null); 
        middleRight.setIcon(null); 
        bottomLeft.setIcon(null); 
        bottomMiddle.setIcon(null); 
        bottomRight.setIcon(null);
        
        //reset tool tips
        topLeft.setToolTipText("");        
        topMiddle.setToolTipText(""); 
        topRight.setToolTipText(""); 
        middleLeft.setToolTipText(""); 
        middleMiddle.setToolTipText(""); 
        middleRight.setToolTipText(""); 
        bottomLeft.setToolTipText(""); 
        bottomMiddle.setToolTipText(""); 
        bottomRight.setToolTipText(""); 
        
        //reassign buttons
        assignButton(topLeft);        
        assignButton(topMiddle);
        assignButton(topRight);
        assignButton(middleLeft);
        assignButton(middleMiddle);
        assignButton(middleRight);
        assignButton(bottomLeft);
        assignButton(bottomMiddle);
        assignButton(bottomRight);
    }
    
    //randomly assign whether a button has an icon 
    //if it does, assign which color it has
    //if a color has already been chosen,
    //generate again until something different is chosen
    //if a button
    public void assignButton(JButton button){
      boolean buttonAssigned = false;
      while (buttonAssigned == false){
        randomNum = (int) (Math.random() * 6);
        switch (randomNum){
            case 0:
                if(redPlaced == false){
                    button.setIcon(redButton);
                    redPlaced = true;
                    buttonAssigned = true;
                    button.setToolTipText("Select red");        
                    break;
                }
                break;
            case 1:
                if(yellowPlaced == false){
                    button.setIcon(yellowButton);
                    yellowPlaced = true;
                    buttonAssigned = true;
                    button.setToolTipText("Select yellow");        
                    break;
                }
                break;
            case 2:
                if(greenPlaced == false){
                    button.setIcon(greenButton);
                    greenPlaced = true;
                    buttonAssigned = true;
                    button.setToolTipText("Select green");        
                    break;
                }
                break;
            case 3:
                if(bluePlaced == false){
                    button.setIcon(blueButton);
                    bluePlaced = true;
                    buttonAssigned = true;
                    button.setToolTipText("Select blue");        
                    break;
                }
                break;
            case 4:
                if(purplePlaced == false){
                    button.setIcon(purpleButton);
                    purplePlaced = true;
                    buttonAssigned = true;
                    button.setToolTipText("Select purple");        
                    break;
                }
                break;
            case 5:
                if(emptyButtons < 4){
                    emptyButtons++;
                    buttonAssigned = true;
                    button.setToolTipText("");        
                    break;
                }
                break;
            default:
                break;
        }//switch (randomNum){
      }//while (buttonAssigned == false){
    }//public void assignButton(JButton button){
    
    //generate random word and text color from list
    public void generateTarget(){
        //generate random word from list
        randomNum = (int) (Math.random() * 5);
        switch(randomNum){
            case 0:
                colorTarget.setText("Red");
                break;
            case 1:
                colorTarget.setText("Yellow");
                break;
            case 2:
                colorTarget.setText("Green");
                break;
            case 3:
                colorTarget.setText("Blue");
                break;
            case 4:
                colorTarget.setText("Purple");
                break;
            default:
                colorTarget.setText("Red");
                break;
        }
        //assign random color from list to word
        randomNum = (int) (Math.random() * 5);
        switch(randomNum){
            case 0:
                colorTarget.setForeground(Color.red);
                targetColor = "Red";
                break;
            case 1:
                colorTarget.setForeground(Color.yellow);
                targetColor = "Yellow";
                break;
            case 2:
                colorTarget.setForeground(Color.green);
                targetColor = "Green";
                break;
            case 3:
                colorTarget.setForeground(Color.blue);
                targetColor = "Blue";
                break;
            case 4:
                colorTarget.setForeground(Color.magenta);
                targetColor = "Purple";
                break;
            default:
                colorTarget.setForeground(Color.red);
                break;
        }
    }
    
    //adds a mouse listener to a button
    public void addMouseListeners(JButton button){
        button.addMouseListener(new MouseAdapter(){
                public void mouseEntered(MouseEvent e){
                    setHighlighted(button);
                }
                public void mouseExited(MouseEvent e){
                    setUnhighlighted(button);
                }
            });
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
        //for when a button is clicked,
        //checks which button was clicked
        if(e.getActionCommand() != null){
            switch(e.getActionCommand()){
                case "top left":
                    checkAnswer(topLeft);
                    break;
                case "top middle":
                    checkAnswer(topMiddle);
                    break;
                case "top right":
                    checkAnswer(topRight);
                    break;
                case "middle left":
                    checkAnswer(middleLeft);
                    break;
                case "middle middle":
                    checkAnswer(middleMiddle);
                    break;
                case "middle right":
                    checkAnswer(middleRight);
                    break;
                case "bottom left":
                    checkAnswer(bottomLeft);
                    break;
                case "bottom middle":
                    checkAnswer(bottomMiddle);
                    break;
                case "bottom right":
                    checkAnswer(bottomRight);
                    break;
                default:
                    break;
            }//switch(e.getActionCommand()){
        }//if(e.getActionCommand() != null){
    }//public void actionPerformed(ActionEvent e){
    
    //highlight button icon when moused over
    //(switches normal icon for highlighted icon)
    public void setHighlighted(JButton button){
        String icon = "";
        if(button.getIcon() != null){
            icon = button.getIcon().toString();
        }
        switch(icon){
            case "src\\redbutton.png":
                button.setIcon(redHighlighted);
                break;
            case "src\\yellowbutton.png":
                button.setIcon(yellowHighlighted);
                break;
            case "src\\greenbutton.png":
                button.setIcon(greenHighlighted);
                break;
            case "src\\bluebutton.png":
                button.setIcon(blueHighlighted);
                break;
            case "src\\purplebutton.png":
                button.setIcon(purpleHighlighted);
                break;
            default:
                break;
        }
    }
    
    //unhighlight button icon when not moused over
    //(switches highlighted icon for normal icon)
       public void setUnhighlighted(JButton button){
        String icon = "";
        if(button.getIcon() != null){
            icon = button.getIcon().toString();
        }
        switch(icon){
            case "src\\redhighlighted.png":
                button.setIcon(redButton);
                break;
            case "src\\yellowhighlighted.png":
                button.setIcon(yellowButton);
                break;
            case "src\\greenhighlighted.png":
                button.setIcon(greenButton);
                break;
            case "src\\bluehighlighted.png":
                button.setIcon(blueButton);
                break;
            case "src\\purplehighlighted.png":
                button.setIcon(purpleButton);
                break;
            default:
                break;
        }
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
