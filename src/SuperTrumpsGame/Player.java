package SuperTrumpsGame;

import java.util.ArrayList;

/**
 * Created by Amos on 03-Oct-16.
 */
public class Player {
    boolean finished;
    ArrayList<Card> playersCards = new ArrayList<Card>();
    public void Player() {
        finished = false;
    }

    public void getNewCard(Deck stDeck){
        // Give player 1 card
        playersCards.add(stDeck.dealCard());
    }

    public void setCards(Deck stDeck) {
        for (int i = 0; i < 6 ; i++) {
            getNewCard(stDeck);
        }
    }
}
