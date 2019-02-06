package mainMenu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import controller.GameController;
import controller.TutorialGameController;
import main.GameView;

/**
 * Creates the main menu for the game. It adds the title panel, image panel, and game panel to the 
 * screen. This is where all of the action of the game takes place from this menu. A player can 
 * go right into the game, start the tutorial, view the current high scores on the leader board, or 
 * quit the game all together. 
 * 
 * @author  Ryan Barbera, Aaron George, Nick Norton, Thomas Pennington, Grant Zhao
 *
 */

public class MainMenuView extends JFrame {

	private static final long serialVersionUID = 1L;
	
	final static int SCREEN_WIDTH = 750; // sets up the sizing of the screen for width
	final static int SCREEN_HEIGHT = 500; // sets up the sizing of the screen for height
	
	MainMenuTitlePanel titlePanel;
	MainMenuImagePanel imagePanel;
	MainMenuButtonPanel gamePanel;
	
	GameView view;
	GameController controller;
	TutorialGameController tutorialController;
	
	/**
	 * Makes a new MainMenuView object for the game. It sets up the entire JFrame that the 
	 * player sees. Passes in current instances of the GameController, GameView, and the 
	 * TutorialGameController and makes a series of method calls to build the view. 
	 * 
	 * @param gv the current instance of the gameView 
	 * @param gc the current instance of the gameController 
	 * @param tutorialController the current instance of the tutorialController
	 */
	public MainMenuView(GameView gv, GameController gc, TutorialGameController tutorialController) {
		this.view = gv;
		this.controller = gc;
		this.tutorialController = tutorialController;
		initGUI(); // makes GUI screen
		initActionListeners();

		setMainGraphicsUp(); // creates the main panel for viewing 
		pack();
	}

	/**
	 * Sets up all panels to create the JFrame for the main menu. It contains the 
	 * MainMenuTitlePanel, MainMenuImagePanel and the MainMenuButtonPanel and adds 
	 * them in their respective locations on the JFrame. 
	 */
	private void initGUI() {

		// creates the Title Panel that goes across the top of the screen
		titlePanel = new MainMenuTitlePanel("Estuary Tower Defense");
		add(titlePanel, BorderLayout.PAGE_START);
		
		// creates the center panel with a picture of an estuary 
		imagePanel = new MainMenuImagePanel();
		add(imagePanel, BorderLayout.CENTER);
		
		// creates the lower screen with the buttons attached
		gamePanel = new MainMenuButtonPanel();
		
		add(gamePanel, BorderLayout.PAGE_END);
	}
	
	/**
	 * Adds the action listeners to each of the 4 buttons that are on the main menu. Based on 
	 * what button was clicked makes a call to the controller to start whatever part of the game 
	 * that the button is responsible for. 
	 */
	private void initActionListeners() {
		// Action Listeners for Mouse Events Clicked
		// Action performed when the start button is clicked 
		gamePanel.startButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.mainMenuStartButtonClicked();
				dispose();
			}
		});
		
		// design and call the method that will make this work
		gamePanel.tutorialButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tutorialController.tutorialButtonClicked();
				dispose();
			}
		});
		
		// adds action listener on the button click for the save button
		gamePanel.leaderBoardButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				view.leaderBoardButtonClicked();
			}
		});
		
		// Action performed when the quit button is clicked 
		gamePanel.quitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				System.exit(0);
			}
		});	
	}
	
	
	/**
	 * Returns back the preferred size of the JComponent. Sets the preferred size of the 
	 * JPanel to fit onto the JFrame of the main menu.
	 * 
	 * @return the preferred size of the JComponent 
	 */
	public Dimension getPreferredSize() {
		Dimension size = new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT);
		return size;
	}
	
	/**
	 * Creates the JFrame characteristics needed to display the main menu and 
	 * what happens when the user terminates the program. 
	 */
	private void setMainGraphicsUp(){
		setTitle("Main Menu"); // sets the title of the screen
		setSize(SCREEN_WIDTH, SCREEN_HEIGHT); // sets the size of the screen
		setLocationRelativeTo(null); // sets in the middle of the screen
		setResizable(false); // unable to resize
		setVisible(true); // makes the GUI visible
		setDefaultCloseOperation(EXIT_ON_CLOSE); // closes on operation
	}
}