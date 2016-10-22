package PracticeGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CardLayoutTutorial {
	JFrame frame = new JFrame("Super Trumps");
	JPanel panelCont = new JPanel();
	JPanel mainMenuPanel = new JPanel();
	JPanel instructionsPanel = new JPanel();
	CardLayout cl = new CardLayout();

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
		mainMenuPanel.add(new MainMenuGUI(cl, panelCont));
		instructionsPanel.add(new InstructionsGUI(cl, panelCont) );
		panelCont.add(mainMenuPanel, "1");
		panelCont.add(instructionsPanel, "2");

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
