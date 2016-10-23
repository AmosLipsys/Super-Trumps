package PracticeGUI;

import SuperTrumpsGame.Card;
import SuperTrumpsGame.Deck;
import SuperTrumpsGame.Player;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by jc299390 on 23/10/16.
 */
public class GameLogic {
    JFrame frame;
    boolean firstTurn = true;
    String dealerString;
    private static final int NUMBER_CARDS_TO_DEAL = 8;
    int numPlayers, dealerId, playerTurn, playerPassed, lastPlayer;
    private ArrayList<Player> players;
    private ArrayList<Integer> winners = new ArrayList<>();
    private Deck superTrumpDeck = new Deck();
    String currentCategory;
    Card currentCard;

    GameLogic(){
    }

    public void NewGame(){
        selectDealer(numPlayers);
        dealRandomCards(numPlayers);

        System.out.print(players);
//        showAllUserCards();
//        round();

    }

    public void userPlays(int cardNo){
        players.get(0).playCard(cardNo);

    }


    public static void main(String[] args){
        new GameLogic(3);
    }


    GameLogic(int numOfPlayers){
        selectDealer(numOfPlayers);
        dealRandomCards(numOfPlayers);


        System.out.print(players);
//        showAllUserCards();
//        round();

    }

    public int getPlayerTurn(){
        return this.playerTurn;
    }

    ArrayList<Card> getCards(int playerNo){
        return players.get(playerNo).playersCards;
    }


    public String getDealerString() {
        return dealerString;
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

    public void validateCards(){
        //find valid cards and separate them;
        for (Card unauditedCard : players.get(playerTurn).playersCards) {
            unauditedCard.isValid = ((isHigher(currentCard, unauditedCard)) || unauditedCard.isTrump || currentCard.isTrump) || currentCard == null;
        }
    }

    boolean isHigher(Card lastCard, Card potentialCard){
        return lastCard.getAttributeValue(currentCategory) < potentialCard.getAttributeValue(currentCategory);

    }

    private void chooseRandCat() {
        Random rand = new Random();
        int selection = rand.nextInt(5) + 1;
        pickCat(selection);
    }

    private void pickCat(int selection){
        switch (selection) {
            case 1:
                currentCategory = "Hardness";
                break;
            case 2:
                currentCategory = "Specific Gravity";
                break;
            case 3:
                currentCategory = "Cleavage";
                break;
            case 4:
                currentCategory = "Crustal Abundance";
                break;
            case 5:
                currentCategory = "Economic Value";
                break;
        }
    }


}
