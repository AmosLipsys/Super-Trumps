package SuperTrumpsGame;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Amos on 24-Sep-16.
 */


public class SuperTrumpsGame {
    private static final int NUMBER_CARDS_TO_DEAL = 2;
    int numPlayers, dealerId, playerTurn, playerPassed, lastPlayer;
    private ArrayList<Player> players;
    private ArrayList<Integer> winners = new ArrayList<>();
    private Deck superTrumpDeck = new Deck();
    String currentCategory;
    Card currentCard;

    // Starts game with certain num of players
    public SuperTrumpsGame(int numPlayers) {
        this.numPlayers = numPlayers;
    }

    // Randomly Selects Dealer
    public void selectDealer(){
        // Pick a random int to find dealer
        Random rand = new Random();
        dealerId = rand.nextInt(numPlayers);
        // First players turn is left of dealer (this may wrap to first user)
        playerTurn = (dealerId + 1) % numPlayers;
    }

    // How one round goes
    public void round(){
        int round_no = 1;
        while ((winners.size()+1 < players.size())){
            System.out.println("\n\nRound: " + round_no);
            round_no++;
            firstTurn();
            nextTurns();
        }
        displayScoreboard();
    }

    // First Turn at the start of each round
    public void firstTurn(){
        System.out.println("Turn: 1");
        // Skip players without any cards
        while (players.get(playerTurn).outOfCards){
            playerTurn = (playerTurn + 1) % numPlayers;
        }

        // Make all cards valid
        for (Card unauditedCard: players.get(playerTurn).playersCards){
            players.get(playerTurn).validPlayersCards.add(unauditedCard);
        }

        // AI First Turn
        //----------------------------------------------------------------
        int cardSelection = 0;
        if(playerTurn != 0) {
            // AI selects random Category
            chooseRandCat();
            System.out.println("AI Plays First");
            System.out.println("AI Chooses Category " + currentCategory);

            // AI Selects first card in hand
            currentCard = players.get(playerTurn).playCard(cardSelection);
            lastPlayer = playerTurn;

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
            resetDeck();
        }

        // User First Turn
        //----------------------------------------------------------------
        else {
            //Show users cards
            lastPlayer = playerTurn;
            players.get(0).displayCards();
            // Select a card from range
            cardSelection = selectCard(players.get(0).noCards());
            cardSelection--;
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
        int i = 2;

        while (numPlayers - 1 > playerPassed && winners.size() +1 < players.size() ){

            if (!(players.get(playerTurn).outOfCards)){
                if((!(players.get(playerTurn).passed))){
                    System.out.println("Turn: " + i);

                    if (playerTurn == 0) {
                        System.out.println("Users Turn:");
                        userTurn();
                    }
                    else {
                        System.out.println("AI's Turn:");
                        aiTurn();
                    }

                    i++;
                    if(players.get(playerTurn).playersCards.isEmpty()){
                        winners.add(playerTurn + 1);
                        players.get(playerTurn).outOfCards = true;
                        System.out.println("Player " + playerTurn + "is out of cards!");
                    }
                }
                else if(!(players.get(playerTurn).passed)){
                    System.out.println("Player " + (playerTurn + 1) + " Passes");
                }
                pressEnterToContinue();
            }


            nextPlayer();

        }
        System.out.println("Player " + (lastPlayer + 1)  + " wins the round!");
        playerTurn = lastPlayer;

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
            System.out.println("AI Player searches his deck but can't find anything");
            playerPassed++;
        }
        else{
            // AI Selects first card in hand
            int cardSelection = 0;
            currentCard = players.get(playerTurn).playCard(cardSelection);
            lastPlayer = playerTurn;

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

            }
            currentCard.displayNameCatVal(currentCategory, playerTurn);
        }
        resetDeck();
    }

    // Normal User Turn
    void userTurn() {

        validateCards();
        System.out.println("\u001B[33m" + "User has " + players.get(0).validPlayersCards.size() + " valid cards out of " +
                (players.get(0).playersCards.size() + players.get(0).validPlayersCards.size()) + " cards \n" + "\u001B[0m") ;
        // If play has no valid card pass
        if (players.get(0).validPlayersCards.size() == 0) {
            players.get(0).passed = true;
            players.get(playerTurn).getNewCard(superTrumpDeck);
            playerPassed++;
            System.out.println("User is forced to pass as they have no valid card :(");
        }

        else {
            int cardSelection = 0;
            //Show users cards
            players.get(0).displayCardsSimple(currentCategory, playerTurn);
            // Select a card from range
            cardSelection = selectCard(players.get(0).noCards());

            // If the player passes

            if (cardSelection == 0){
                System.out.println("User Chooses to Pass");
                playerPassed++;
                players.get(0).passed = true;
                players.get(playerTurn).getNewCard(superTrumpDeck);
            }
            else {
                cardSelection --;
                // Remove card from users deck and store card into var current card
                currentCard = players.get(playerTurn).playCard(cardSelection);
                // If it is a trump card played
                if (currentCard.isTrump) {
                    // If the geologist card is picked up
                    if (currentCard.getTrumpType().equals("Any Category")) {
                        displayCatOptions();
                        pickCat(userInputOneToFive());
                    }
                    // If a non geologist trump card is picked up
                    else {
                        currentCategory = currentCard.getTrumpType();
                    }
                }
                // No trump card means player needs to select a category

                currentCard.displayNameCatVal(currentCategory, playerTurn);
                lastPlayer = playerTurn;
                resetDeck();
            }
        }


    }



    private void validateCards(){
        //find valid cards and separate them;
        for (Card unauditedCard : players.get(playerTurn).playersCards) {
            if((isHigher(currentCard, unauditedCard)) || unauditedCard.isTrump || currentCard.isTrump){
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
        while(cardSelection < 0 || cardSelection > numCards) {
            System.out.println("\u001B[34m" + "Pick a card, Press 0 to Pass:" + "\u001B[0m");
            String userChoice = scan.next();
            if (choiceIsInt(userChoice)) {
                cardSelection = Integer.parseInt(userChoice);
            } else {
                System.out.println("Input not an Integer");
            }

            if (cardSelection < 0 || cardSelection > numCards) {
                System.out.println("Card not in range :(");
            }
        }
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
        System.out.println("Above are your cards:");
    }

    public void showAllUserCards() {
        System.out.println("The following are your cards:");
        players.get(0).displayAllCards();
        System.out.println("Above are your cards:");
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

    private void displayScoreboard(){
        for (int i = 0; i < players.size(); i++){
            if (!players.get(i).outOfCards){
                winners.add(i+1);
            }
        }


        int tally = 0;
        for (int winner:winners) {
            tally++;
            System.out.println(tally + ") Player " + winner);
        }

    }

    private static void pressEnterToContinue()
    {
        System.out.println("\u001B[36m" + "Press " + "ENTER"+ " to continue..." + "\u001B[0m");
        try
        {
            System.in.read();
        }
        catch(Exception e)
        {}
    }
}
