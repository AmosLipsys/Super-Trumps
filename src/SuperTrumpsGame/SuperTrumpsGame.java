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
            currentCard = players.get(playerTurn).playCard(cardSelection);

            if (currentCard.isTrump) {
                if (currentCard.getTrumpType().equals("Any Category")) {
                    chooseRandCat();
                } else {
                    currentCategory = currentCard.getTrumpType();
                }
            }
            currentCard.displayNameCatVal(currentCategory);
        }
        // User Turn
        else {
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
            currentCard = players.get(playerTurn).playCard(cardSelection);

            if(currentCard.isTrump){
                if (currentCard.getTrumpType().equals("Any Category")) {
                    displayCatOptions();
                    pickCat(userInputOneToFive());
                }
                else{
                    currentCategory = currentCard.getTrumpType();
                }
            }
            else{
                displayCatOptions();
                pickCat(userInputOneToFive());
            }
            currentCard.displayNameCatVal(currentCategory);
            }

        nextTurn();
    }

    private static void displayCatOptions(){
        System.out.println("\nChoose one of the following options:\n"+
                "1) Hardness\n" +
                "2) Specific Gravity\n" +
                "3) Cleavage\n" +
                "4) Crustal Abundance\n" +
                "5) Economic Value");
    }

    private static int userInputOneToFive(){
        Scanner scan = new Scanner(System.in);
        int selection = -1;
        while(selection <= 0 || selection > 5) {
            System.out.println("Pick a value 1 - 5:");
            String userChoice = scan.next();
            if (choiceIsInt(userChoice)) {
                selection = Integer.parseInt(userChoice);
            } else {
                System.out.println("Input not an Integer");
            }

            if (selection <= 0 || selection > 5) {
                System.out.println("Input number 1 - 5");
            }
        }
        return selection;

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
