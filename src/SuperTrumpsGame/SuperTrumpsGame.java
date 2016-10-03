package SuperTrumpsGame;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

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
        // AI Turn
        int cardSelection = 0;
        if(playerTurn != 0) {
            chooseRandCat();
        }
        else {
            // User Turn
            currentCategory = "Hardness";
            players.get(0).displayCards();
            Scanner scan = new Scanner(System.in);
            cardSelection = -1;
            while(cardSelection <= 0 || cardSelection > players.get(0).noCards()) {
                System.out.println("Pick a card:");
                String userChoice = scan.next();
                if (choiceIsInt(userChoice)) {
                    cardSelection = Integer.parseInt(userChoice);
                } else {
                    System.out.println("Input not an Integer");
                }

                if (cardSelection <= 0 || cardSelection > players.get(0).noCards()) {
                    System.out.println("Card not in range :(");
                }
                }


                cardSelection--;

            }
        currentCard = players.get(playerTurn).playCard(cardSelection);
        currentCard.displayNameCatVal(currentCategory);
        nextTurn();
    }



    private static boolean choiceIsInt(String userInput){
        try {
            Integer.parseInt(userInput);
            return true;
        }
        catch (Exception e){
            System.out.println("Error! Make sure you type in a integer!");
            return false;
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
            System.out.println("Dealer is Player " + (dealerId + 1));
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

    private void chooseRandCat(){
        Random rand = new Random();
        int selection = rand.nextInt(5) + 1;

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
