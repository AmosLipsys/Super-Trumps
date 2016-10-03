package SuperTrumpsGame;

import java.util.ArrayList;

/**
 * Created by Amos on 03-Oct-16.
 */
public class Player {
    boolean passed, outOfCards;
    ArrayList<Card> playersCards = new ArrayList<Card>();
    ArrayList<Card> validPlayersCards = new ArrayList<Card>();
    public void Player() {
        passed = false;
        outOfCards = false;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    public void getNewCard(Deck stDeck){
        // Give player 1 card
        playersCards.add(stDeck.dealCard());
    }

    public void setCards(Deck stDeck, int numCards) {
        for (int i = 0; i < numCards ; i++) {
            getNewCard(stDeck);
        }
    }

    public int noCards(){
        return validPlayersCards.size();
    }

    public Card playCard(int cardNo){
        Card selectedCard = validPlayersCards.get(cardNo);
        validPlayersCards.remove(selectedCard);
        playersCards.remove(selectedCard);
        return selectedCard;
    }

    public void displayCards(){
        int cardNumber = 1;
        for (Card chosenCard:validPlayersCards) {
            System.out.println("Card Number " + cardNumber);
            chosenCard.display();
            cardNumber++;
        }
    }

    public void displayCardsSimple(String currentCategory, int playerTurn) {
        int cardNumber = 1;
        for (Card chosenCard:validPlayersCards) {
            System.out.println("Card Number " + cardNumber);
            chosenCard.displayNameCatVal(currentCategory, playerTurn);
            cardNumber++;
        }

    }
}
