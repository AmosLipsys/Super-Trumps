package PracticeGUI;

import java.util.ArrayList;

/**
 * Created by Amos on 03-Oct-16.
 */
public class Player {
    boolean passed, outOfCards;
    public ArrayList<Card> playersCards = new ArrayList<Card>();
    public void Player() {
        passed = false;
        outOfCards = false;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    public Card getNewCard(Deck stDeck){
        // Give player 1 card
        Card card = stDeck.dealCard();
        playersCards.add(card);
        return card;
    }

    public void setCards(Deck stDeck, int numCards) {
        for (int i = 0; i < numCards ; i++) {
            getNewCard(stDeck);
        }
    }


    public Card playCard(int cardNo){
        Card selectedCard = new Card();
        for(Card card: playersCards){
            if (card.cardNo==cardNo){
                selectedCard = card;
            }
        }
        playersCards.remove(selectedCard);
        return selectedCard;
    }

}
