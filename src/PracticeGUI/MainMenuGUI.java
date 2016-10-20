package PracticeGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuGUI extends JPanel implements ActionListener {
    //Set Buttons and Labels

    GridLayout mainMenuLayout = new GridLayout(4, 1);
    JLabel titleLable = new JLabel("Super Trumps!", SwingConstants.CENTER);
    JButton newGameButt = new JButton("Start A New Game!");
    JButton instructionsButt = new JButton("Need Help Scrub?");
    JButton quitButt = new JButton("Crash To Desktop");

    //Set Font
    Font titleFont = new Font("Verdana", Font.BOLD, 80);
    Font buttFont = new Font("Verdana", Font.BOLD, 30);

    MainMenuGUI(){
        setBackground(Color.red);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
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
        add(instructionsButt);
        add(quitButt);
        setSize(1800, 1000);
        setVisible(true);


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
