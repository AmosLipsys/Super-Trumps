package PracticeGUI;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class selectCategory extends JPanel implements ActionListener {
    GridLayout mainMenuLayout = new GridLayout(6, 1);
    JLabel pickACategoryLabel = new JLabel("Pick A Category");
    JButton hardnessButt = new JButton("Hardness");
    JButton specificGravityButt = new JButton("Specific Gravity");
    JButton cleavageButt = new JButton("Cleavage");
    JButton crustalAbundanceButt = new JButton("Crustal Abundance");
    JButton economicValueButt = new JButton("Economic Value");

    //Set Font
    Font font = new Font("Verdana", Font.BOLD, 30);


    selectCategory(CardLayout cl, JPanel panelCont, GameLogic game) {
        setBackground(Color.red);

        // Set Background Colour
        setBackground(Color.darkGray);
        // Set Title Font And Colour
        pickACategoryLabel.setFont(font);
        pickACategoryLabel.setForeground(Color.white);
        // Set Button Font
        hardnessButt.setFont(font);
        specificGravityButt.setFont(font);
        cleavageButt.setFont(font);
        crustalAbundanceButt.setFont(font);
        economicValueButt.setFont(font);

        setLayout(mainMenuLayout);

        add(pickACategoryLabel);
        add(hardnessButt);
        add(specificGravityButt);
        add(cleavageButt);
        add(crustalAbundanceButt);
        add(economicValueButt);

        setSize(1800, 1000);
        setVisible(true);

        hardnessButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                game.pickCat(1);
                cl.show(panelCont, "3");

            }
        });
        specificGravityButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                game.pickCat(2);
                cl.show(panelCont, "3");

            }
        });
        cleavageButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                game.pickCat(3);
                cl.show(panelCont, "3");

            }
        });
        crustalAbundanceButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                game.pickCat(4);
                cl.show(panelCont, "3");

            }
        });
        economicValueButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                game.pickCat(5);
                cl.show(panelCont, "3");

            }
        });

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

