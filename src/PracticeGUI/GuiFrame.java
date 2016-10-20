package PracticeGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by jc299390 on 19/10/16.
 */
public class GuiFrame extends JFrame implements ActionListener {
    GuiFrame(){
        setTitle("Super Trumps");
        setBounds(0, 0, 1800, 1000);
        setVisible(true);

        Container superTrumpPane = getContentPane();


        MainMenuGUI MainMenu = new MainMenuGUI();
        InstructionsGUI Instructions = new InstructionsGUI();
        Instruct instruct = new Instruct();

        JPanel cards = new JPanel(new CardLayout());
        cards.add(MainMenu);
        cards.add(Instructions);
        cards.add(instruct);


        superTrumpPane.add(cards);

//        add(new InstructionsGUI());

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        revalidate();
        repaint();
    }

    public static void main(String[] args){
        GuiFrame STFrame = new GuiFrame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
