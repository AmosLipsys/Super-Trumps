package PracticeGUI;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class TestLayout {

    protected void initUI() {
        //Set Font
        Font titleFont = new Font("Verdana", Font.BOLD, 40);
        Font buttFont = new Font("Verdana", Font.BOLD, 30);


        final JFrame frame = new JFrame(TestLayout.class.getSimpleName());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JLabel title = new JLabel("Instructions", SwingConstants.CENTER);
        title.setFont(titleFont);

        frame.add(title, BorderLayout.NORTH);

        ImageIcon instructionsIcon = getImage(61);

        JLabel picLabel = new JLabel(instructionsIcon);
        frame.add(picLabel);


        JPanel buttonPanel = new JPanel(new GridLayout());
        buttonPanel.add(new JButton("Page 1"));
        buttonPanel.add(new JButton("Page 2"));
        buttonPanel.add(new JButton("Page 3"));
        buttonPanel.add(new JButton("Page 4"));
        buttonPanel.add(new JButton("Back to Main Menu"));
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
        frame.setBounds(0, 0, 1800, 1000);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {


            @Override
            public void run() {
                new TestLayout().initUI();
            }
        });
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

}