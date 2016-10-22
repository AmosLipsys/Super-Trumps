package PracticeGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Amos on 23-Oct-16.
 */
public class GameGUI extends JPanel implements ActionListener {
    //Set Buttons and Labels

    GridLayout mainMenuLayout = new GridLayout(4, 1);
    JLabel titleLable = new JLabel("Super Trumps!", SwingConstants.CENTER);
    JTextField randomtext = new JTextField("Random");
    JButton newGameButt = new JButton("Start A New Game!");
    JButton instructionsButt = new JButton("Need Help Scrub?");
    JButton quitButt = new JButton("Crash To Desktop");

    //Set Font
    Font titleFont = new Font("Verdana", Font.BOLD, 80);
    Font buttFont = new Font("Verdana", Font.BOLD, 30);


    GameGUI(CardLayout cl, JPanel panelCont){
        setBackground(Color.red);

        // Set Background Colour
        setBackground(Color.darkGray);
        // Set Title Font And Colour
        titleLable.setFont(titleFont);
        titleLable.setForeground(Color.white);
        // Set Button Font
        newGameButt.setFont(buttFont);
        instructionsButt.setFont(buttFont);
        quitButt.setFont(buttFont);

        setLayout(mainMenuLayout);
        add(titleLable);
        add(newGameButt);
        add(randomtext);
        add(instructionsButt);
        add(quitButt);
        setSize(1800, 1000);
        setVisible(true);

        instructionsButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                cl.show(panelCont, "2");
            }
        });


        quitButt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        });



    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}




