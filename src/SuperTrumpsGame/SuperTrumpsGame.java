package SuperTrumpsGame;

import java.util.Random;

/**
 * Created by Amos on 24-Sep-16.
 */
public class SuperTrumpsGame {
    int numPlayers, dealerId;

    public SuperTrumpsGame(int numPlayers) {
        this.numPlayers = numPlayers;
    }

    public void selectDealer(){
        Random rand = new Random();
        dealerId = rand.nextInt(numPlayers) + 1;
    }


}
