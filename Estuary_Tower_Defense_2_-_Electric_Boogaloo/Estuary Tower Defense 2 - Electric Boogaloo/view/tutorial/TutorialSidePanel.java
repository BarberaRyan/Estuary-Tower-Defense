package tutorial;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import boardMenu.BoardMenuInfoPanel;
import boardMenu.BoardMenuScorePanel;
import boardMenu.BoardMenuTimerPanel;

/**
* Creates the entire side panel of the game. Includes the scorepanel,
* timerpanel, buttonpanels, and the information panel. This is the main control
* center while the game is running to select different options.
* 
* @author Ryan Barbera, Aaron George, Nick Norton, Thomas Pennington, Grant Zhao
*
*/

public class TutorialSidePanel extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private JPanel creaturePanel = new JPanel();
	private JPanel middlePanel = new JPanel();
	
	private JLabel creatureLabel = new JLabel("Select from available creatures below:");
	
	private JButton creatureButton = new JButton();
	
	private BufferedImage creatureImg;
	
	private TutorialScorePanel scorePanel;
	private TutorialTimerPanel timerPanel;
	private TutorialInfoPanel infoPanel; // creates the information panel

	/**
	 * Constructor that creates a TutorialSidePanel object that takes in an
	 * image of a creature that will be later used for icons of the towers. Adds all
	 * necessary panels and components to the screen to make the side panel.
	 * 
	 * @param creatureImage an array of creature images used for icons
	 */
	public TutorialSidePanel(BufferedImage creatureImage){
		this.creatureImg = creatureImage;
		
		scorePanel = new TutorialScorePanel();
		timerPanel = new TutorialTimerPanel();
		infoPanel = new TutorialInfoPanel();
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(260, 900));
		
		add(scorePanel);
		add(timerPanel);
		addLabelMiddleTop(); // adds a label to identify creature section
		addMiddlePanel(); // adds the middle panel for the screen
		add(infoPanel);
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
	 * Adds the button panel to the side panel of the game. There is 1 
	 * button that is added to the panel. This is where all of the interaction takes place to add 
	 * creatures to the centerpanel as necessary throughout the game.
	 */
	private void addMiddlePanel() {
		middlePanel.setPreferredSize(new Dimension(260, 260));
		middlePanel.setBackground(Color.LIGHT_GRAY);
		creatureButton.setPreferredSize(new Dimension(100,100));
		creatureButton.setIcon(new ImageIcon(creatureImg.getScaledInstance(75, 75, Image.SCALE_SMOOTH)));
		middlePanel.add(creatureButton);
		add(middlePanel);
	}
	
	public TutorialScorePanel getScorePanel() {
		return scorePanel;
	}

	public void setScorePanel(int playerHealth, int playerMoney, int playerScore) {
		scorePanel.setHealth(playerHealth);
		scorePanel.setSandDollars(playerMoney);
		scorePanel.setScore(playerScore);
	}

	public TutorialTimerPanel getTimerPanel() {
		return timerPanel;
	}

	public void setTimerPanel(long roundTime) {
		timerPanel.setTime(roundTime);
	}

	public TutorialInfoPanel getInfoPanel() {
		return infoPanel;
	}

	public JButton getCreatureButton(){
		return creatureButton;
	}
}
