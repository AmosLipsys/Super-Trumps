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

    public SuperTrumpsGame(int numPlayers) {
        this.numPlayers = numPlayers;
    }

    public void selectDealer(){
        Random rand = new Random();

        dealerId = rand.nextInt(numPlayers);
        playerTurn = (dealerId + 1) % numPlayers;
    }

    public void nextTurn(){
        playerTurn = (playerTurn + 1) % numPlayers;
    }

    void displayDealer(){
        System.out.println("Dealer is Player " +dealerId);
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
}
