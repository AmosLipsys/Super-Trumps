package PracticeGUI;

import SuperTrumpsGame.Card;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class MainMenuGUI extends JPanel implements ActionListener {
    //Set Buttons and Labels
    final int MIN_PLAYERS = 3;
    final int MAX_PLAYERS = 5;
    int numPlayer;
    GridLayout mainMenuLayout = new GridLayout(5, 1);
    JLabel titleLable = new JLabel("Super Trumps!", SwingConstants.CENTER);
    JComboBox numPlayerSeletor = getNumPlayers(MIN_PLAYERS, MAX_PLAYERS);
    JButton newGameButt = new JButton("Start A New Game!");
    JButton instructionsButt = new JButton("Need Help Scrub?");
    JButton quitButt = new JButton("Crash To Desktop");

    //Set Font
    Font titleFont = new Font("Verdana", Font.BOLD, 80);
    Font buttFont = new Font("Verdana", Font.BOLD, 30);


    MainMenuGUI(CardLayout cl, JPanel panelCont, GameLogic game, JPanel gamePanel) {
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
        numPlayerSeletor.setFont(buttFont);
        setLayout(mainMenuLayout);
        add(titleLable);
        add(newGameButt);
        add(numPlayerSeletor);


        add(instructionsButt);
        add(quitButt);
        setSize(1800, 1000);
        setVisible(true);

        newGameButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // Checks Player Number Selector and Saves Value
                numPlayer = Integer.parseInt(String.format("%s",numPlayerSeletor.getSelectedItem()).split(" ")[0]);
                game.numPlayers = numPlayer;
                game.selectDealer(numPlayer);
                game.displayDealer();
                game.dealRandomCards(numPlayer);

                cl.show(panelCont, "3");
            }
        });





        instructionsButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                cl.show(panelCont, "2");
            }
        });


        quitButt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });


    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    JComboBox getNumPlayers(int MIN_PLAYERS, int MAX_PLAYERS) {
        java.util.List<String> playerList = new ArrayList<>();
        for (int i = MIN_PLAYERS; i <= MAX_PLAYERS; i++) {
            String numString = Integer.toString(i);
            playerList.add(numString + " Players");
        }

        JComboBox playerSelector = new JComboBox(playerList.toArray());
        playerSelector.setSelectedIndex(0);
        playerSelector.addActionListener(this);
        return playerSelector;
    }
}

