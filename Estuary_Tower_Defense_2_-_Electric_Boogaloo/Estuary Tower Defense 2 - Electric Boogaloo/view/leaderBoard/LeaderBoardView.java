package leaderBoard;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import mainMenu.MainMenuTitlePanel;

/**
 * Creates the leaderboard for the game and does the initial setup for the leaderboard 
 * making the view.  
 * 
 * @author Ryan Barbera, Aaron George, Nick Norton, Thomas Pennington, Grant Zhao
 *
 */

public class LeaderBoardView extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	private final int LEADER_BOARD_WIDTH = 700;
	private final int LEADER_BOARD_HEIGHT = 500;
	
	private MainMenuTitlePanel title;
	public LeaderBoardCenterPanel leaderBoardCenterPanel;
	
	private JPanel buttonPanel = new JPanel();
	
	private JButton exitButton = new JButton("Exit");

	/**
	 * Constructor that makes a LeaderBoardView object and creates instances of the classes needed to make 
	 * the leaderboard view. Makes 2 calls to separate methods to initialize the panels 
	 * and to make the JFrame.
	 * 
	 * @param score the current score of the player 
	 */
	public LeaderBoardView(int score){
		title = new MainMenuTitlePanel("Estuary Tower Defense LeaderBoard");
		leaderBoardCenterPanel = new LeaderBoardCenterPanel(score);
		
		initGUI();
		setupLeaderBoard();
	}
	
	/**
	 * Sets up all of the panels so they are placed in the correct spot in the JFrame 
	 * on the screen. There are a total of 3 panels that are placed in their respective 
	 * locations on the screen. Sets up the action listeners for the LeaderBoardView to
	 * control what happens when you close the leader board.
	 */
	private void initGUI(){
		// adds the title to the screen
		add(title, BorderLayout.PAGE_START);
		
		// adds the centerpanel to the screen
		add(leaderBoardCenterPanel, BorderLayout.CENTER);
		
		// adds the buttonPanel to the screen
		buttonPanel.setSize(new Dimension(0,48));
		buttonPanel.setBackground(Color.RED);
		
		exitButton.setPreferredSize(new Dimension(100,35));
		
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				dispose();
			}
		});
		
		buttonPanel.add(exitButton);
		add(buttonPanel, BorderLayout.PAGE_END);
	}
	
	/**
	 * Makes the entire frame that the components of the leaderboard view will sit on. Sets 
	 * up all of the properties needed to make the view work correctly when it loads.
	 */
	private void setupLeaderBoard(){
		setTitle("LeaderBoard");
		setSize(LEADER_BOARD_WIDTH, LEADER_BOARD_HEIGHT);
		setResizable(false);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}