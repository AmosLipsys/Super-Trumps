package PracticeGUI;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Scoreboard extends JPanel implements ActionListener {
    BorderLayout scoreboardLayout = new BorderLayout();
    JLabel scoreboardLabel = new JLabel("Scoreboard");
    JButton backtoMenuButt = new JButton("Back To Menu");
    JTextArea scoreArea = new JTextArea();
    String winnerString;
    ArrayList<Integer> listOfInts = new ArrayList<Integer>();
    //Set Font
    Font font = new Font("Verdana", Font.BOLD, 30);

    Scoreboard(CardLayout cl, JPanel panelCont, GameLogic game) {
        listOfInts.add(3);
        listOfInts.add(0);
        listOfInts.add(1);
        listOfInts.add(2);
        setBackground(Color.red);

        // Set Background Colour
        setBackground(Color.darkGray);
        // Set Title Font And Colour
        scoreboardLabel.setFont(font);
        scoreboardLabel.setForeground(Color.white);
        // Set Button Font
        backtoMenuButt.setFont(font);
        scoreArea.setFont(font);
        scoreArea.setText("");
        winnerString = "";
        int count = 1;
        for(Integer winner : listOfInts) {
            if (winner.equals(0)) {
                winnerString = winnerString + "\n" + count + ") Human Player 1";
            } else {
                winnerString = winnerString + "\n" + count + ") AI Player Number " + winner;
            }
            count++;
        }
        scoreArea.append(winnerString);
        game = new GameLogic();

        setLayout(scoreboardLayout);
        add(scoreboardLabel, BorderLayout.NORTH);
        add(scoreArea, BorderLayout.CENTER);
        add(backtoMenuButt, BorderLayout.SOUTH);


        setSize(1800, 1000);
        setVisible(true);


        backtoMenuButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                cl.show(panelCont, "1");

            }
        });

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

