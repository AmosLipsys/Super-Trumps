package PracticeGUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by jc299390 on 21/10/16.
 */
public class Instruct extends JPanel implements ActionListener {
    //Set Buttons and Labels
//Set Font
    Font titleFont = new Font("Verdana", Font.BOLD, 40);
    Font buttFont = new Font("Verdana", Font.BOLD, 30);
    JButton page1Butt = new JButton("Page 1");
    JButton page2Butt = new JButton("Page 2");
    JButton page3Butt = new JButton("Page 3");
    JButton page4Butt = new JButton("Page 4");
    JButton backButt = new JButton("Back to Main Menu");

    Instruct(){

        // Set Background Colour
        setBackground(Color.darkGray);
        // Set Title Font And Colour
        // Set layout
        setLayout(new BorderLayout());
        setVisible(true);
        setSize(1800, 1000);

        //Title Top Center with correct font
        JLabel title = new JLabel("Instructions", SwingConstants.CENTER);
        title.setFont(titleFont);
        add(title, BorderLayout.NORTH);

        // Instructions as pic

        ImageIcon instructionsIcon = getImage(61);
        JLabel picLabel = new JLabel(instructionsIcon);
        add(picLabel);

        // Butt Panel
        JPanel buttonPanel = new JPanel(new GridLayout());

        buttonPanel.add(page1Butt);
        buttonPanel.add(page2Butt);
        buttonPanel.add(page3Butt);
        buttonPanel.add(page4Butt);
        buttonPanel.add(backButt);
        add(buttonPanel, BorderLayout.SOUTH);


        setSize(1800, 1000);
        setVisible(true);


        page1Butt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                ImageIcon instructionsIcon = getImage(61);
                picLabel.setIcon(instructionsIcon);
            }
        });

        page2Butt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                ImageIcon instructionsIcon = getImage(62);
                picLabel.setIcon(instructionsIcon);
            }
        });

        page3Butt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                ImageIcon instructionsIcon = getImage(63);
                picLabel.setIcon(instructionsIcon);
            }
        });

        page4Butt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                ImageIcon instructionsIcon = getImage(64);
                picLabel.setIcon(instructionsIcon);
            }
        });

        backButt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                // Change Page
            }
        });



    }

    ImageIcon getImage(int imageNum){
        String filePath = "C:\\Users\\jc299390\\Desktop\\SuperTrump\\Super-Trumps\\images\\Slide" +
                Integer.toString(imageNum) + ".jpg"  ;
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