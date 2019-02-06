package mainMenu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Displays the button panel at the bottom of the main menu screen. Allows you to select 
 * an option to enter into a different part of the game. You are able to select from 4 
 * different options depending on what a player wants to do.
 * 
 * @author Ryan Barbera, Aaron George, Nick Norton, Thomas Pennington, Grant Zhao
 *
 */

public class MainMenuButtonPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	final int GAP_SIZE_WIDTH = 500; // sets the width of the JButtons
	final int GAP_SIZE_HEIGHT = 10; // sets the height of the JButtons 
		
	JPanel buttonPanel = new JPanel(); // panel for the buttons 
	GridLayout buttonLayout = new GridLayout(1,4);
	
	JButton startButton = new JButton("Start Game"); // creates the start button
	JButton tutorialButton = new JButton("Tutorial"); // creates the tutorial button
	JButton leaderBoardButton = new JButton("Show Scores"); // saves the stuff to a leaderBoard
	JButton quitButton = new JButton("Quit"); // creates the quit button
	
	Dimension buttonSize =  startButton.getPreferredSize(); // sets the preferred size of buttons
	
	/**
	 * The constructor that creates an MainMenuButtonPanel object of the button panel. 
	 * Sets up the button panel with a GridLayout that will contain the buttons used
	 * to start the game. Adds three buttons to the panel with action listeners to 
	 * control what happens when a button is clicked. Then adds the buttonPanel to the 
	 * PAGE.END position on the JFrame.
	 */
	public MainMenuButtonPanel(){
		buttonPanel.setPreferredSize(new Dimension((int) (buttonSize.getWidth()) + GAP_SIZE_WIDTH,
				(int)(buttonSize.getHeight()) + GAP_SIZE_HEIGHT));
		buttonPanel.setLayout(buttonLayout);
		buttonPanel.add(startButton);
		buttonPanel.add(tutorialButton);
		buttonPanel.add(leaderBoardButton); // adds the leaderBoard button to the panel;
		buttonPanel.add(quitButton);
		buttonPanel.setBackground(Color.BLUE);
		setBackground(Color.BLUE);
		add(buttonPanel);	
	}
	
	/**
	 * Returns the overridden size of the JComponent. Sets the preferred size of the
	 * JPanel for the button panel to sit on. 
	 * 
	 * @return the preferred size of the JComponent 
	 */
	public Dimension getPreferredSize(){
		Dimension size = new Dimension(0, 48);
		return size;
	}
}
