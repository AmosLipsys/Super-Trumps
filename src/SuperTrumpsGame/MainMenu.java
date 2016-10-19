package SuperTrumpsGame;

import java.util.Scanner;

public class MainMenu {
    public static void main(String args[]){

        showWelcome();
        int userChoice = 0;
        String userChoiceStr;

        while (userChoice != 3) {
            showMenu();
            userChoiceStr = getUserChoice();
            if (choiceIsInt(userChoiceStr)) {
                userChoice = Integer.parseInt(userChoiceStr);
            }
            if (userChoice == 1) {
                startNewGame();
            }
            else if (userChoice == 2){
                displayInstructions();
            }
            else if (userChoice == 3)
                displayGoodbye();
        }
    }

    // Display Welcome Text
    private static void showWelcome(){
        System.out.println("Super Trumps, A game of fun, rage and intellect");
    }

    // Display Menu Choice
    private static void showMenu(){
        System.out.println(System.getProperty("line.separator") + "Choose an option from the following:");
        String[] menuItems = {"Start Game","InstructionsGUI","Quit"};
        for (int itemIndex = 0; itemIndex < menuItems.length; itemIndex++){
            System.out.println(String.format("%2s. %s",itemIndex + 1, menuItems[itemIndex]));
        }
        System.out.print(">> ");
    }

    // Print InstructionsGUI
    private  static void displayInstructions(){
        System.out.println("InstructionsGUI to play Super Trumps:");
        System.out.println("To Be Written. Sorry this game is still in Alpha :(");
        pressEnterToContinue();

    }

    // Display Goodbye Message
    private static void displayGoodbye(){
        System.out.print("Thanks for playing Super Trumps. See you next time");
    }


    // Get UserChoice For Main Menu
    private static String getUserChoice(){
        Scanner scan = new Scanner(System.in);
        return scan.next();
    }

    // New Game Selected
    public static void startNewGame(){
        int numPlayers = getNumPlayers();
        SuperTrumpsGame game = new SuperTrumpsGame(numPlayers);
        game.selectDealer();
        game.displayDealer();
        pressEnterToContinue();
        game.dealRandomCards();
        game.showAllUserCards();
        pressEnterToContinue();
        game.round();
    }

    // Get number of players from user min = 3, max = 5
    private static int getNumPlayers(){
        int numPlayers = -1, min = 1, max = 5;
        while (numPlayers < min || numPlayers > max) {
            System.out.println("\n\nChoose how many players are allowed to play:");
            Scanner scan = new Scanner(System.in);
            String userChoice = scan.next();
            if(choiceIsInt(userChoice)){
                numPlayers = Integer.parseInt(userChoice);
            }

                if(numPlayers < min || numPlayers > max ){
                    System.out.println("\n\nMake sure you type a value between " + min + " and " + max);
                }
        }
        return numPlayers;
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
