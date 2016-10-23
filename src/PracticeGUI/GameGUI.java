package PracticeGUI;

import SuperTrumpsGame.Card;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Amos on 23-Oct-16.
 */
public class GameGUI extends JPanel implements ActionListener {
    //Set Buttons and Labels

    GridBagLayout gameLayout = new GridBagLayout();

    JLabel titleLable = new JLabel("Super Trumps!", SwingConstants.CENTER);
    JLabel category = new JLabel("Category: None Selected Yet", SwingConstants.CENTER);
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


    //Set Font
    Font titleFont = new Font("Verdana", Font.BOLD, 80);
    Font buttFont = new Font("Verdana", Font.BOLD, 30);


    GameGUI(CardLayout cl, JPanel panelCont, GameLogic game){
        //Set Layout, size ect
        setLayout(gameLayout);
        setSize(1800, 1000);
        setVisible(true);



        // Set Background Colour
        setBackground(Color.darkGray);
        // Set Title Font And Colour
        titleLable.setFont(titleFont);
        titleLable.setForeground(Color.white);
        // Set Button Font
        category.setFont(buttFont);

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

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        // Make Bottom Button Panel
        buttonPanel.add(passButt);
        buttonPanel.add(finishButt);

        // Fill Panels with Features
        add(titleLable, titleConstraints);
        add(category, categoryConstraints);
        add(cardLabel, cardPickConstraints);
        add(cardSelector, cardSelectorConstraints);


        add(buttonPanel, buttonPanelConstraints);




        passButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                passButt.setEnabled(false);
                if (cardsDealt) {
                    cardButtPanel.add(createCardButt(ThreadLocalRandom.current().nextInt(1, 54 + 1)));
                    cardButtPanel.getComponents();
                    revalidate();
                    repaint();
                }
                else{
                    setCards(game.getCards(0));
                    revalidate();
                    repaint();
                    cardsDealt = true;
                    passButt.setText("Pass");
                }
            }
        });
    }

    public void setCards(ArrayList<Card> playersCards){
        for(Card card:playersCards) {
            cardButtPanel.add(createCardButt(card.getCardNo()));
        }
    }



    JButton createCardButt (int cardNo) {
        ImageIcon cardIcon = getImage(cardNo);
        JButton cardButt = new JButton(cardIcon);
        cardButt.setEnabled(true);
        cardButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                cardLabel.setIcon(getImage(cardNo));
                cardButtPanel.remove(cardButt);
                revalidate();
                repaint();
            }
        });
        return cardButt;
    }





    JButton createInvalidCardButt(int cardNo){
        JButton invalidCardButt = createCardButt(cardNo);
        invalidCardButt.setEnabled(false);
        revalidate();
        repaint();
        return invalidCardButt;
    }

    public void setCategory(String categoryText) {
        category.setText(categoryText);
    }

    public void setPassButtText(String passText){
        passButt.setText(passText);
    }

    ImageIcon getImage(int imageNum){

        String filePath = "C:\\Users\\jc299390\\Desktop\\Super-Trumps\\images\\Slide" +
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




