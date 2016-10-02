package SuperTrumpsGame;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Amos on 24-Sep-16.
 */
public class SuperTrumpsGame {
    int numPlayers, dealerId, playerTurn;
    private ArrayList<Player> players;
    private Deck superTrumpDeck = new Deck();
    String currentCategory;
    Card currentCard;

    public SuperTrumpsGame(int numPlayers) {
        this.numPlayers = numPlayers;
    }

    public void selectDealer(){
        Random rand = new Random();

        dealerId = rand.nextInt(numPlayers);
        playerTurn = (dealerId + 1) % numPlayers;
    }

    public void firstTurn(){
        if(playerTurn != 0){
            int selectedCard = 0;

        }
    }


    public void nextTurn(){
        playerTurn = (playerTurn + 1) % numPlayers;
    }

    void displayDealer(){
        if (dealerId == 0){
            System.out.println("You are the dealer!");
        }
        else {
            System.out.println("Dealer is Player " + dealerId);
        }
    }

    void dealRandomCards() {
        players = new ArrayList<Player>();
        for (int i = 0; i < numPlayers ; i++) {
            players.add(new Player());
        }

        for (Player player: players){
            player.setCards(superTrumpDeck);
        }

    }

    public void showUserCards() {
        System.out.println("The following are your cards:");
        players.get(0).displayCards();
    }
}
