package SuperTrumpsGame;

import com.sun.glass.ui.Size;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by jc299390 on 17/10/16.
 */
public class STGui extends JFrame implements ActionListener {
    JFrame SuperTumpsApp = new JFrame();
    GridLayout mainMenuLayout = new GridLayout(4, 1);
    JPanel mainMenuPannel = new JPanel(mainMenuLayout);
    JLabel titleLable = new JLabel("Super Trumps!", SwingConstants.CENTER);
    JButton newGameButt = new JButton("Start A New Game!");
    JButton instructionsButt = new JButton("Need Help Scrub?");
    JButton quitButt = new JButton("Crash To Desktop");

    Container con = getContentPane();
    Font titleFont = new Font("Verdana", Font.BOLD, 80);
    Font buttFont = new Font("Verdana", Font.BOLD, 30);

    public static void main(String[] args){

        // Set Look and Feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        STGui frame = new STGui();
    }



    STGui(){
        // Set Background Colour
        con.setBackground(Color.darkGray);
        // Set Title Font And Colour
        titleLable.setFont(titleFont);
        titleLable.setForeground(Color.white);
        // Set Button Font
        newGameButt.setFont(buttFont);
        instructionsButt.setFont(buttFont);
        quitButt.setFont(buttFont);

        con.setLayout(mainMenuLayout);
        con.add(titleLable);
        con.add(newGameButt);
        con.add(instructionsButt);
        con.add(quitButt);
        setSize(800, 900);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {


    }
}
