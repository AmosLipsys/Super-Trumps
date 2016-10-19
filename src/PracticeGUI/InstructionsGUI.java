package PracticeGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;



public class InstructionsGUI extends JPanel implements ActionListener {

//    //Set Buttons and Labels
    Font titleFont = new Font("Verdana", Font.BOLD, 40);
    Font buttFont = new Font("Verdana", Font.BOLD, 30);


//
//    GridLayout InstructionsLayout = new GridLayout(2, 1);
//    JLabel titleLable = new JLabel("Super Trumps!", SwingConstants.CENTER);
//    JButton newGameButt = new JButton("Start A New Game!");
//    JButton instructionsButt = new JButton("Need Help Scrub?");
//    JButton quitButt = new JButton("Crash To Desktop");
//
//    //Set Font
//    Font titleFont = new Font("Verdana", Font.BOLD, 80);
//    Font buttFont = new Font("Verdana", Font.BOLD, 30);


    InstructionsGUI(){
        JLabel title = new JLabel("Instructions", SwingConstants.CENTER);
        title.setFont(titleFont);

        add(title, BorderLayout.NORTH);

        ImageIcon instructionsIcon = getImage(61);

        JLabel picLabel = new JLabel(instructionsIcon);
        add(picLabel);

        JPanel buttonPanel = new JPanel(new GridLayout());
        buttonPanel.add(new JButton("Page 1"));
        buttonPanel.add(new JButton("Page 2"));
        buttonPanel.add(new JButton("Page 3"));
        buttonPanel.add(new JButton("Page 4"));
        buttonPanel.add(new JButton("Back to Main Menu"));
        add(buttonPanel, BorderLayout.SOUTH);
        setVisible(true);
        setBounds(0, 0, 1800, 1000);


    }


    ImageIcon getImage(int imageNum){
        String filePath = "C:\\Users\\jc299390\\Desktop\\SuperTrump\\Super-Trumps\\images\\Slide" + Integer.toString(imageNum) + ".jpg"  ;
        BufferedImage myPicture = null;
        try {
            myPicture = ImageIO.read(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ImageIcon instructionsIcon = new ImageIcon(myPicture);
        Image image = instructionsIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(675, 825,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        return new ImageIcon(newimg);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
