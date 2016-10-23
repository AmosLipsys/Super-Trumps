package PracticeGUI;

import SuperTrumpsGame.Card;
import SuperTrumpsGame.Deck;
import SuperTrumpsGame.Player;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by jc299390 on 23/10/16.
 */
public class gameLogic{
    JFrame frame;
    String dealerString;
    private static final int NUMBER_CARDS_TO_DEAL = 8;
    int numPlayers, dealerId, playerTurn, playerPassed, lastPlayer;
    private ArrayList<Player> players;
    private ArrayList<Integer> winners = new ArrayList<>();
    private Deck superTrumpDeck = new Deck();
    String currentCategory;
    Card currentCard;


    public static void main(String[] args){
        new gameLogic(3);
    }


    gameLogic(int numOfPlayers){
        selectDealer(numOfPlayers);
        displayDealer();
        dealRandomCards(numOfPlayers);
        System.out.print(players);
//        showAllUserCards();
//        round();

    }

    private void displayDealer() {
        JOptionPane.showMessageDialog(frame,dealerString,
                "The Dealer",
                JOptionPane.PLAIN_MESSAGE);
    }


    public void dealRandomCards(int numPlayers) {
        players = new ArrayList<Player>();
        for (int i = 0; i < numPlayers ; i++) {
            players.add(new Player());
        }

        for (Player player: players){
            player.setCards(superTrumpDeck, NUMBER_CARDS_TO_DEAL);
        }
    }

    public void selectDealer(int numPlayers){


        // Pick a random int to find dealer
        Random rand = new Random();
        dealerId = rand.nextInt(numPlayers);
        if (dealerId == 0){
            dealerString = "You are the Dealer :D";
        }
        else {
            dealerString = "The dealer is A.I. Number " + dealerId;
        }

        // First players turn is left of dealer (this may wrap to first user)
        playerTurn = (dealerId + 1) % numPlayers;
    }

}
