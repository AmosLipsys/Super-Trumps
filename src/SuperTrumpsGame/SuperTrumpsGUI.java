package SuperTrumpsGame;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Amos on 16-Oct-16.
 */

public class SuperTrumpsGUI extends JFrame {

    JButton PlayButt = new JButton("Play a new game!");
    JButton InstructionsButt = new JButton("Need some help?");
    JButton QuitButt = new JButton("Crash to Desktop!");
    JTextField textField = new JTextField();
    SuperTrumpsGUI(){
        super("Super Trump");
        setSize(400, 400);
        setResizable(true);
        setVisible(true);
        setLayout(new GridLayout(4,1));
        add(PlayButt);
        add(InstructionsButt);
        add(QuitButt);


        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        add(textField);

        /*
        Make Screen Full Size
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        */
    }

}
