package boardMenu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

//TODO take out end round, the round shouldn't end on a button, it should end when time is up

/*
 * TODO grey out buttons if the player does not have enough money to buy the units
 * TODO trash button to take a unit off the cursor
 * 
 */

/**
 * Creates the entire side panel of the game. Includes the scorepanel,
 * timerpanel, buttonpanels, and the information panel. This is the main control
 * center while the game is running to select different options.
 * 
 * @author Ryan Barbera, Aaron George, Nick Norton, Thomas Pennington, Grant Zhao
 *
 */

public class BoardMenuSideGamePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JPanel middlePanel = new JPanel();
	private JPanel creaturePanel = new JPanel();
	
	private JLabel creatureLabel = new JLabel("Select from available creatures below:");

	private JButton genericBirdButton = new JButton();
	private JButton ospreyButton = new JButton();
	private JButton ruddyTurnstoneButton = new JButton(); 
	private JButton sanderlingButton = new JButton();
	private JButton genericFishButton = new JButton(); 
	private JButton blueFishButton = new JButton();
	private JButton riverHerringButton = new JButton();
	private JButton summerFlounderButton = new JButton();
	private JButton genericShellfishButton = new JButton();
	private JButton blueCrabButton = new JButton();
	private JButton easternOysterButton = new JButton();
	private JButton horseShoeCrabButton = new JButton();

	private JButton GAME_BUTTONS[] = { genericShellfishButton, blueCrabButton, easternOysterButton,
			horseShoeCrabButton, genericFishButton, riverHerringButton, blueFishButton, summerFlounderButton,
			genericBirdButton, ruddyTurnstoneButton, sanderlingButton, ospreyButton };
	private JButton SHELLFISH_BUTTONS[] = { easternOysterButton, blueCrabButton, horseShoeCrabButton };
	private JButton FISH_BUTTONS[] = { riverHerringButton, blueFishButton, summerFlounderButton };
	private JButton BIRD_BUTTONS[] = { ruddyTurnstoneButton, sanderlingButton, ospreyButton };
	
	private BufferedImage[] creatureImg;

	private GridLayout creatureLayout = new GridLayout(3, 4); // creates the grid for the creature buttons

	private BoardMenuScorePanel scorePanel; // creates new score panel for new game
	private BoardMenuTimerPanel timerPanel; // creates the timerPanel
	private BoardMenuInfoPanel infoPanel; // creates the information panel

	/**
	 * Constructor that creates a BoardMenuSideGamePanel object that takes in an
	 * array of creatures that will be later used for icons of the towers. Adds all
	 * necessary panels and components to the screen to make the side panel.
	 * 
	 * @param creatures an array of creature images used for icons
	 */
	public BoardMenuSideGamePanel(BufferedImage[] creatures) {
		this.creatureImg = creatures;
		scorePanel = new BoardMenuScorePanel();
		timerPanel = new BoardMenuTimerPanel();
		infoPanel = new BoardMenuInfoPanel();

		// sets up everything
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(Color.WHITE); // sets the color of the background for the screen
		setPreferredSize(new Dimension(260, 900));
		add(scorePanel); // adds the score panel to the screen on East Side
		add(timerPanel); // adds the timer panel to the screen on East Side
		addLabelMiddleTop(); // adds a label to identify creature section
		addMiddlePanel(); // adds the middle panel for the screen
		add(infoPanel); // adds the information panel to the screen
	}

	/**
	 * Adds the label that gives information about how to select creatures from
	 * the button panel in the game. Is necessary for user interaction so they
	 * know what to do when they start to play the game.
	 */
	private void addLabelMiddleTop() {
		add(creaturePanel);
		creaturePanel.add(creatureLabel);
		creaturePanel.setBackground(Color.YELLOW);
		creaturePanel.setPreferredSize(new Dimension(260, 25));
	}

	/**
	 * Adds the button panel to the side panel of the game. There are 12 total
	 * buttons that are added to the panel using a method call. This is where
	 * all of the interaction takes place to add creatures to the centerpanel as
	 * necessary throughout the game.
	 */
	private void addMiddlePanel() {
		middlePanel.setPreferredSize(new Dimension(260, 260));
		middlePanel.setBackground(Color.LIGHT_GRAY);
		middlePanel.setLayout(creatureLayout);

		createButtons(); // creates the set of buttons from next method below
		placeImageOnButtonsCreature(GAME_BUTTONS); // places the images onto the
													// buttons
		add(middlePanel);
	}

	/**
	 * Creates the buttons that are added to the center panel in the game side
	 * panel. There are a total of 12 buttons that are created with this method and are 
	 * added to the gridLayout of the middle panel.
	 */
	private void createButtons() {
		for (int i = 0; i < GAME_BUTTONS.length; i++) {
			middlePanel.add(GAME_BUTTONS[i]);
		}
	}

	/**
	 * Adds images to the buttons that are used in the game. It takes in an
	 * array of buttons and cycles through them and adds the appropriate image
	 * to them. If there is not an equal amount of buttons and images, then this
	 * method will not run.
	 * 
	 * @param listButtons
	 *            the array of buttons that will get images added to the buttons
	 */
	private void placeImageOnButtonsCreature(JButton listButtons[]) {
		for (int i = 0; i < GAME_BUTTONS.length && i < listButtons.length; i++) {
			GAME_BUTTONS[i].setSize(48, 65);
			Image buttonImage = creatureImg[i].getScaledInstance(GAME_BUTTONS[i].getWidth(),
					GAME_BUTTONS[i].getHeight(), Image.SCALE_SMOOTH);
			ImageIcon imgIcon = new ImageIcon(buttonImage);
			GAME_BUTTONS[i].setIcon(imgIcon);
		}
	}

	public BoardMenuScorePanel getScorePanel() {
		return scorePanel;
	}

	public void setScorePanel(int playerHealth, int playerMoney, int playerScore) {
		scorePanel.setHealth(playerHealth);
		scorePanel.setSandDollars(playerMoney);
		scorePanel.setScore(playerScore);
	}

	public BoardMenuTimerPanel getTimerPanel() {
		return timerPanel;
	}

	public void setTimerPanel(long roundTime) {
		timerPanel.setTime(roundTime);
	}

	public BoardMenuInfoPanel getInfoPanel() {
		return infoPanel;
	}

	public JButton[] getGameButtons() {
		return GAME_BUTTONS;
	}
	
	public JButton[] getShellfishButtons() {
		return SHELLFISH_BUTTONS;
	}
	
	public JButton[] getFishButtons() {
		return FISH_BUTTONS;
	}
	
	public JButton[] getBirdButtons() {
		return BIRD_BUTTONS;
	}
	
	public JButton getIndexedGameButton(int index) {
		return GAME_BUTTONS[index];
	}
}