package PracticeGUI;


import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by jc299390 on 23/10/16.
 */
public class GameLogic {
    public JFrame frame;
    public boolean firstTurn = true, hasValidCards, keepPlaying = true;
    public String dealerString;
    public  static final int NUMBER_CARDS_TO_DEAL = 8;
    public int numPlayers, dealerId, playerTurn, playerPassed, lastPlayer;
    public  ArrayList<Player> players;
    public  ArrayList<Integer> winners = new ArrayList<>();
    public  Deck superTrumpDeck = new Deck();
    public String currentCategory;
    Card currentCard = null;

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
        Card playedCard = players.get(0).playCard(cardNo);
        if (playedCard.isTrump){
            String changeCategory = playedCard.getTrumpType();
            if (changeCategory.equals("Any Category")) {
                chooseRandCat();
            }
            else {
                currentCategory = changeCategory;
            }
        }
    }

    public static void main(String[] args){
        new GameLogic(3);
    }


    GameLogic(int numOfPlayers){
        selectDealer(numOfPlayers);
        dealRandomCards(numOfPlayers);
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



    public void makeAllValidCards(){
        //find valid cards and separate them;
        for (Card unauditedCard : players.get(playerTurn).playersCards) {
            unauditedCard.isValid = true;
            hasValidCards = true;
        }
    }

    public void validateCards(){
        hasValidCards = false;
        //find valid cards and separate them;
        players.get(playerTurn).setPassed(true);

        for (Card unauditedCard : players.get(playerTurn).playersCards) {
            unauditedCard.isValid = currentCard == null || ((isHigher(currentCard, unauditedCard)) || unauditedCard.isTrump || currentCard.isTrump);
            if (unauditedCard.isValid) {
                players.get(playerTurn).setPassed(false);
                hasValidCards = true;
            }
        }
    }


    public Card pickRandomValidCard(){
        for (Card unauditedCard : players.get(playerTurn).playersCards) {
            if(unauditedCard.isValid){
                currentCard = unauditedCard;
                players.get(playerTurn).playersCards.remove(currentCard);
                trumpTest(currentCard);
                return currentCard;
            }
        }
        return null;
    }

    public void trumpTest(Card card){
        if(card.isTrump){
            String changeOfCategory = card.getTrumpType();
            if (changeOfCategory.equals("Any Category")) {
                chooseRandCat();
            }
            else {
                currentCategory = changeOfCategory;
            }
        }

    }

    public void nextPlayer(){

        // If game hasn't finished
        if (winners.size() + 1 < numPlayers){
            // If 2 players haven't passed
             if((playerPassed + 1) < numPlayers) {
                 // Look for new player
                 playerTurn = (playerTurn + 1) % numPlayers;
                 while ((players.get(playerTurn).passed) || (players.get(playerTurn).outOfCards )){
                     playerTurn = (playerTurn + 1) % numPlayers;
                 }
             }
             else {
                 // Set up new game
                 for (Player player : players) {
                     player.passed = false;
                     firstTurn = true;
                     while ((players.get(playerTurn).passed) || (players.get(playerTurn).outOfCards)) {
                         playerTurn = (playerTurn + 1) % numPlayers;
                     }
                 }
             }
        }
        else{
            keepPlaying = false;
        }
    }




    boolean isHigher(Card lastCard, Card potentialCard){
        return lastCard.getAttributeValue(currentCategory) < potentialCard.getAttributeValue(currentCategory);

    }

    public void chooseRandCat() {
        Random rand = new Random();
        int selection = rand.nextInt(5) + 1;
        pickCat(selection);
    }

    public String pickCat(int selection){
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
        return currentCategory;
    }







}
