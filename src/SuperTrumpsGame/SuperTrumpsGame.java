package SuperTrumpsGame;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Amos on 24-Sep-16.
 */


public class SuperTrumpsGame {
    private static final int NUMBER_CARDS_TO_DEAL = 6 ;
    int numPlayers, dealerId, playerTurn;
    private ArrayList<Player> players;
    private Deck superTrumpDeck = new Deck();
    String currentCategory;
    Card currentCard;
    int playerPassed;

    public SuperTrumpsGame(int numPlayers) {
        this.numPlayers = numPlayers;
    }

    public void selectDealer(){
        // Pick a random int to find dealer
        Random rand = new Random();
        dealerId = rand.nextInt(numPlayers);
        // First players turn is left of dealer (this may wrap to first user)
        playerTurn = (dealerId + 1) % numPlayers;
    }


    // First Turn at the start of each round
    public void firstTurn(){

        // Make all cards valid
        for (Card unauditedCard: players.get(playerTurn).playersCards){
            players.get(playerTurn).validPlayersCards.add(unauditedCard);
//            players.get(playerTurn).playersCards.remove(unauditedCard);
        }



        // AI First Turn
        //----------------------------------------------------------------
        int cardSelection = 0;
        if(playerTurn != 0) {
            // AI selects random Category
            chooseRandCat();

            // AI Selects first card in hand
            currentCard = players.get(playerTurn).playCard(cardSelection);

            // If AI selects trump card
            if (currentCard.isTrump) {
                // AI selects random category if he gets the geologist
                if (currentCard.getTrumpType().equals("Any Category")) {
                    chooseRandCat();
                // Set category to trump card selected
                } else {
                    currentCategory = currentCard.getTrumpType();
                }
            }
            currentCard.displayNameCatVal(currentCategory, playerTurn);
        }

        // User First Turn
        //----------------------------------------------------------------
        else {
            //Show users cards
            players.get(0).displayCards();
            // Select a card from range
            cardSelection = selectCard(players.get(0).noCards());
            // Remove card from users deck and store card into var current card
            currentCard = players.get(playerTurn).playCard(cardSelection);
            // If it is a trump card played
            if(currentCard.isTrump){
                // If the geologist card is picked up
                if (currentCard.getTrumpType().equals("Any Category")) {
                    displayCatOptions();
                    pickCat(userInputOneToFive());
                }
                // If a non geologist trump card is picked up
                else{
                    currentCategory = currentCard.getTrumpType();
                }
            }
            // No trump card means player needs to select a category
            else{
                displayCatOptions();
                pickCat(userInputOneToFive());
            }
            currentCard.displayNameCatVal(currentCategory, playerTurn);
            }
        resetDeck();
        nextPlayer();
    }

    public void nextTurns() {
        resetPass();
        while (playerPassed < numPlayers){
            // If player is AI and hasn't passed
            if(!(playerTurn == 0) && !(players.get(playerTurn).passed)){
                aiTurn();
            }
            else {
                userTurn();
            }
            if(players.get(playerTurn).passed){
                playerPassed = 0;
                for (Player guy: players) {
                    if(guy.passed){
                        playerPassed++;
                    }
                }
                System.out.println("\nPlayer " + playerTurn + " Passed\n");
            }

            nextPlayer();
        }


    }

    private void resetPass(){
        playerPassed = 0;
        for (Player selectedPlayer: players) {
            selectedPlayer.passed = false;
        }
    }

    private void resetDeck(){
        players.get(playerTurn).playersCards.removeAll(players.get(playerTurn).validPlayersCards);
        players.get(playerTurn).playersCards.addAll(players.get(playerTurn).validPlayersCards);
        players.get(playerTurn).validPlayersCards.clear();
    }

    // Normal AI Turn
    private void aiTurn(){
        //find valid cards and separate them;
        validateCards();

        // If no valid cards AI auto passes and picks up one card
        if(players.get(playerTurn).validPlayersCards.isEmpty()){
            players.get(playerTurn).getNewCard(superTrumpDeck);
            players.get(playerTurn).setPassed(true);
        }
        else{
            // AI Selects first card in hand
            int cardSelection = 0;
            currentCard = players.get(playerTurn).playCard(cardSelection);

            // If AI selects trump card
            if (currentCard.isTrump) {
                resetPass();
                // AI selects random category if he gets the geologist
                if (currentCard.getTrumpType().equals("Any Category")) {
                    chooseRandCat();
                    // Set category to trump card selected
                } else {
                    currentCategory = currentCard.getTrumpType();
                }
                currentCard.displayNameCatVal(currentCategory, playerTurn);
            }
        }
        resetDeck();
    }

    // Normal User Turn
    void userTurn(){
        validateCards();
        players.get(0).displayCardsSimple(currentCategory);

        resetDeck();
    }

    private void validateCards(){
        //find valid cards and separate them;
        for (Card unauditedCard : players.get(playerTurn).playersCards) {
            if((isHigher(currentCard, unauditedCard)) || unauditedCard.isTrump){
                players.get(playerTurn).validPlayersCards.add(unauditedCard);
            }
        }
        players.get(playerTurn).playersCards.removeAll(players.get(playerTurn).validPlayersCards);

    }

    boolean isHigher(Card lastCard, Card potentialCard){
        return lastCard.getAttributeValue(currentCategory) < potentialCard.getAttributeValue(currentCategory);

    }


    private static void displayCatOptions(){
        System.out.println("\nChoose one of the following options:\n"+
                "1) Hardness\n" +
                "2) Specific Gravity\n" +
                "3) Cleavage\n" +
                "4) Crustal Abundance\n" +
                "5) Economic Value");
    }

    private static int selectCard(int numCards){
        Scanner scan = new Scanner(System.in);
        int cardSelection = -1;
        while(cardSelection <= 0 || cardSelection > numCards) {
            System.out.println("Pick a card:");
            String userChoice = scan.next();
            if (choiceIsInt(userChoice)) {
                cardSelection = Integer.parseInt(userChoice);
            } else {
                System.out.println("Input not an Integer");
            }

            if (cardSelection <= 0 || cardSelection > numCards) {
                System.out.println("Card not in range :(");
            }
        }
        cardSelection --;
        return cardSelection;
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


    public void nextPlayer(){
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
            player.setCards(superTrumpDeck, NUMBER_CARDS_TO_DEAL);
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
