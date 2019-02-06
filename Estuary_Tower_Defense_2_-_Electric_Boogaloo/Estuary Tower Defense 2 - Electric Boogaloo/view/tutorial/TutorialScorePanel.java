package tutorial;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Makes the score panel that is located on the side menu panel to give information about 
 * current score, health left, and sand dollars that are left. Will make updates during the 
 * game to keep the information current for the user playing the game. 
 * 
 * @author Ryan Barbera, Aaron George, Nick Norton, Thomas Pennington, Grant Zhao
 *
 */

public class TutorialScorePanel extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private Font firstLabelFont = new Font(Font.DIALOG, Font.PLAIN, 16); // sets font size for head label
	private Font secondLabelFont = new Font(Font.DIALOG, Font.PLAIN, 16); // sets font for label
	
	public static int score; 
	private static int sandDollars;
	private static int health; 
		
	private JLabel scoreTotalLabel = new JLabel("0"); // label for score total
	private JLabel sandDollarsLabel = new JLabel("0"); // label for sand dollars
	private JLabel healthLeftLabel = new JLabel("0"); // label for health left 

	/**
	 * Constructor that makes a TutorialScorePanel object and sets the grid layout of 
	 * all JComponents needed to display correctly to the user.
	 *   
	 */
	public TutorialScorePanel(){
		setPreferredSize(new Dimension(260, 90)); // sets the size of the panel
		setBackground(Color.WHITE);
		setLayout(new GridLayout(3,2)); // sets a new grid layout 
		
		// Score Label
		JLabel scoreLabel = new JLabel("Score: ");
		scoreLabel.setFont(firstLabelFont);
		add(scoreLabel);
		
		// Total Score Label
		scoreTotalLabel.setFont(secondLabelFont);
		add(scoreTotalLabel);
		
		// Remaining SandDollars
		JLabel sandDollars = new JLabel("SandDollars: ");
		sandDollars.setFont(firstLabelFont);
		add(sandDollars);
		
		// Total Remaining SandDollars that are left
		sandDollarsLabel.setFont(secondLabelFont);
		sandDollarsLabel.setAlignmentY(RIGHT_ALIGNMENT);
		add(sandDollarsLabel);
		
		// Health Left
		JLabel healthLabel = new JLabel("Health Left: ");
		healthLabel.setFont(firstLabelFont);
		add(healthLabel);
		
		// Total Health that is left 
		healthLeftLabel.setFont(secondLabelFont);
		add(healthLeftLabel);
	}
	
	public void setScore(int playerScore) {
		score = playerScore;
		scoreTotalLabel.setText("" + score);
	}

	public void setSandDollars(int playerMoney) {
		sandDollars = playerMoney;
		sandDollarsLabel.setText("" + sandDollars);
	}

	public void setHealth(int playerHealth) {
		health = playerHealth;
		healthLeftLabel.setText("" + health);
	}
}
