package PracticeGUI;

import javax.swing.*;
import java.awt.*;

public class CardLayoutTutorial {
	GameLogic game = new GameLogic();
	JFrame frame = new JFrame("Super Trumps");
	JPanel panelCont = new JPanel();
	JPanel mainMenuPanel = new JPanel();
	JPanel instructionsPanel = new JPanel();
	JPanel gamePanel = new JPanel();
	CardLayout cl = new CardLayout();
	JTextArea statusScreen = new JTextArea(2,20);

	public CardLayoutTutorial() {

		// Set look and feel to system default
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

		//Make Holding Panel For the Frame
		panelCont.setLayout(cl);

		// Add Panels to the card layout list and set number
		gamePanel.add(new GameGUI(cl, panelCont, game, statusScreen) );
		mainMenuPanel.add(new MainMenuGUI(cl, panelCont, game, statusScreen));
		instructionsPanel.add(new InstructionsGUI(cl, panelCont) );

		panelCont.add(mainMenuPanel, "1");
		panelCont.add(instructionsPanel, "2");
		panelCont.add(gamePanel,"3");

		// Show default as Main Menu
		cl.show(panelCont, "1");

		// Put JPanels in JFrame and add defaults
		frame.add(panelCont);
		frame.setSize(1800, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new CardLayoutTutorial();
			}
		});
	}

}
