package PracticeGUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Amos on 23-Oct-16.
 */
public class GameGUI extends JPanel implements ActionListener {
    //Set Buttons and Labels

    GridBagLayout gameLayout = new GridBagLayout();

    JLabel titleLable = new JLabel("Super Trumps!", SwingConstants.CENTER);
    JLabel categoryLable = new JLabel("Category: None Selected Yet", SwingConstants.CENTER);
    JLabel cardLabel;
    JPanel cardButtPanel;
    JPanel buttonPanel = new JPanel(new GridLayout());
    boolean cardsDealt = false;

    JButton passButt = new JButton("Get Cards");
    JButton finishButt = new JButton("Finish The Game!");

    GridBagConstraints titleConstraints = new GridBagConstraints();
    GridBagConstraints categoryConstraints = new GridBagConstraints();
    GridBagConstraints cardPickConstraints = new GridBagConstraints();
    GridBagConstraints cardSelectorConstraints = new GridBagConstraints();
    GridBagConstraints buttonPanelConstraints = new GridBagConstraints();
    GridBagConstraints statusScreenConstraints = new GridBagConstraints();



    //Set Font
    Font titleFont = new Font("Verdana", Font.BOLD, 80);
    Font buttFont = new Font("Verdana", Font.BOLD, 30);


    GameGUI(CardLayout cl, JPanel panelCont, GameLogic game, JTextArea statusScreen){

        //Set Layout, size ect
        setLayout(gameLayout);
        setSize(1800, 1000);
        setVisible(true);



        // Set Background Colour
        setBackground(Color.darkGray);
        // Set Title Font And Colour
        titleLable.setFont(titleFont);
        titleLable.setForeground(Color.white);
        statusScreen.setFont(buttFont);
        statusScreen.setMaximumSize(new Dimension(300, 200));
        statusScreen.setText(game.getDealerString());
        statusScreen.setForeground(Color.white);
        statusScreen.setWrapStyleWord(true);
        statusScreen.setLineWrap(true);
        statusScreen.setOpaque(false);
        statusScreen.setEditable(false);
        statusScreen.setFocusable(false);
        // Set Button Font
        categoryLable.setFont(buttFont);

        // Get Default Card
        ImageIcon cardIcon = getImage(65);
        cardLabel = new JLabel(cardIcon);


        cardButtPanel = new JPanel();
        JScrollPane cardSelector = new JScrollPane(cardButtPanel);
        cardSelector.setPreferredSize(new Dimension(600,340));
        cardSelector.createHorizontalScrollBar();

        // ~~~~~~~~~Set Constraints
        // Title
        titleConstraints.gridx = 0;
        titleConstraints.gridy = 0;
        // Category
        categoryConstraints.gridx = 0;
        categoryConstraints.gridy = 1;
        // Card Pic
        cardPickConstraints.gridx = 0;
        cardPickConstraints.gridy = 3;
        // Card Selector
        cardSelectorConstraints.gridx = 0;
        cardSelectorConstraints.gridy = 4;
        // Pass Button
        buttonPanelConstraints.gridx = 0;
        buttonPanelConstraints.gridy = 5;
        buttonPanelConstraints.fill = GridBagConstraints.HORIZONTAL;
        passButt.setFont(buttFont);
        // Status Screen
        statusScreenConstraints.gridx = 0;
        statusScreenConstraints.gridy = 6;

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        // Make Bottom Button Panel
        buttonPanel.add(passButt);
        buttonPanel.add(finishButt);

        // Fill Panels with Features
        add(titleLable, titleConstraints);
        add(categoryLable, categoryConstraints);
        add(cardLabel, cardPickConstraints);
        add(cardSelector, cardSelectorConstraints);
        add(statusScreen, statusScreenConstraints);


        add(buttonPanel, buttonPanelConstraints);


        finishButt.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent arg0) {
                 for (Component button : cardButtPanel.getComponents()) {
                     button.setEnabled(true);
                 }
                 cl.show(panelCont, "5");
                 validate();
                 repaint();
             }
         });



        passButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {

                // Pause Button
                passButt.setEnabled(false);

                // If no card have been dealt so far
                if (!cardsDealt) {
                    setInvalidCards(game.getCards(0), game, statusScreen);
                    cardsDealt = true;
                }

                if (game.firstTurn){
                    game.firstTurn = false;
                    if(!(game.playerTurn == 0)){
                        game.chooseRandCat();
                        categoryLable.setText(game.currentCategory);
                    }
                    else{
                        cl.show(panelCont, "4");
                        game.currentCategory = game.pickCat(1);
                        categoryLable.setText(game.currentCategory);
                    }
                    validate();
                    repaint();
                    game.makeAllValidCards();
                }
                else {
                    game.validateCards();
                }

                // If AI Turn
                if(!(game.playerTurn == 0)){
                    passButt.setText(String.format("Press to Continue", game.playerTurn));
                    statusScreen.setText(String.format("A.I. No:%s took it's turn", game.playerTurn));

                    if(game.hasValidCards){
                        game.pickRandomValidCard();
                        cardLabel.setIcon(getImage(game.currentCard.cardNo));
                        validate();
                        repaint();
                    }
                    else{
                        // AI PASS
                    }
                }
                // If it's a Player turn
                // Player Passes
                else if(passButt.getText().equals("Pass")){
                    Card newCard = game.players.get(0).getNewCard(game.superTrumpDeck);
                    cardButtPanel.add(createCardButt(newCard.cardNo, game, statusScreen));
                    validate();
                    repaint();
                }
                else{
                    passButt.setText("Pass");
                    statusScreen.setText("It's Your Turn");
                    setValidAndInvalidCards(game);
                }
                categoryLable.setText(game.currentCategory);
                game.nextPlayer();

                // Reset Screen
                revalidate();
                repaint();
                passButt.setEnabled(true);

                if (!(game.keepPlaying)){
                    cl.show(panelCont, "5");
                }
            }
        });
    }


    public void setValidAndInvalidCards(GameLogic game){
        int counter = 0;
        for (Component button : cardButtPanel.getComponents()) {

            if(game.players.get(0).playersCards.get(counter).isValid){
                button.setEnabled(true);
            }
            else{
                button.setEnabled(false);
            }
            counter++;

        }



    }

    public void setAllCardsValid(){
        for (Component button : cardButtPanel.getComponents()) {
            button.setEnabled(true);
        }
        validate();
        repaint();
    }

    public void setAllCardsInvalid(){
        for (Component button : cardButtPanel.getComponents()) {
            button.setEnabled(false);
        }
        validate();
        repaint();
    }





    public void setValidCards(ArrayList<Card> playersCards, GameLogic game,JTextArea statusScreen){

        for(Card card:playersCards) {
            cardButtPanel.add(createCardButt(card.getCardNo(), game, statusScreen));
        }
    }

    public void setInvalidCards(ArrayList<Card> playersCards, GameLogic game, JTextArea statusScreen){
        for(Card card:playersCards) {
            cardButtPanel.add(createInvalidCardButt(card.getCardNo(), game, statusScreen));
        }
    }

    public JButton createCardButt (int cardNo, GameLogic game, JTextArea statusScreen) {
        ImageIcon cardIcon = getImage(cardNo);
        JButton cardButt = new JButton(cardIcon);
        cardButt.setEnabled(true);
        cardButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {

                game.userPlays(cardNo);
                cardLabel.setIcon(getImage(cardNo));
                cardButtPanel.remove(cardButt);


                game.nextPlayer();

                passButt.setText("Continue");
                statusScreen.setText("You play a card");
                setAllCardsInvalid();
                revalidate();
                repaint();
            }
        });
        return cardButt;
    }

    JButton createInvalidCardButt(int cardNo, GameLogic game, JTextArea status){
        JButton invalidCardButt = createCardButt(cardNo, game, status);
        invalidCardButt.setEnabled(false);
        revalidate();
        repaint();
        return invalidCardButt;
    }


    public void setCategoryLable(String categoryText) {
        categoryLable.setText(categoryText);
    }

    public void setPassButtText(String passText){
        passButt.setText(passText);
    }

    public void selectACategory(CardLayout cl, JPanel panelCont){
        cl.show(panelCont, "4");
    }


    ImageIcon getImage(int imageNum){

        String filePath = "C:\\Users\\Amos\\Desktop\\Super Trumps\\images\\Slide" +
                String.format("%02d",imageNum) + ".jpg" ;

        BufferedImage myPicture = null;
        try {
            myPicture = ImageIO.read(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ImageIcon instructionsIcon = new ImageIcon(myPicture);
        Image image = instructionsIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(200, 300,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        return new ImageIcon(newimg);
    }

    @Override
    public void actionPerformed(ActionEvent e) {


    }
}




