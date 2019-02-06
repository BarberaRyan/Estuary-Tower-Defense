package boardMenu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Sets up the button panel that is seen on the main menu of the game. There is also
 * the commands for the different buttons to control different aspects of the game in
 * the different states it can be in. 
 * 
 * @author Ryan Barbera, Aaron George, Nick Norton, Thomas Pennington, Grant Zhao
 *
 */

public class BoardMenuButtonPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	final int GAP_SIZE_WIDTH = 1000; // sets the width of the JButtons
	final int GAP_SIZE_HEIGHT = 10; // sets the height of the JButtons

	private JPanel buttonPanel = new JPanel();
	
	JButton quitButton = new JButton("Return to Main Menu");
	JButton startButton = new JButton("Start Round"); // creates the start round button	
	JButton cancelButton = new JButton("Remove Image on Cursor");

	private Dimension buttonSize = quitButton.getPreferredSize(); // sets the preferred size of buttons
	
	/**
	 * Sets up the button panel on the screen for the main menu and returns the button panel to be added 
	 * to the JFrame. The placement of the button panel is at the PAGE_END section of the JFrame.
	 */
	public BoardMenuButtonPanel(){
		buttonPanel.setLayout(new GridLayout(1, 4)); // sets the layout of the button panel
		buttonPanel.setPreferredSize(new Dimension((int) (buttonSize.getWidth()) + GAP_SIZE_WIDTH,
				(int) (buttonSize.getHeight()) + GAP_SIZE_HEIGHT)); // sets the preferred size of the button panel
		buttonPanel.setBackground(Color.RED); // sets the background color of the button panel
		setBackground(Color.RED); // sets the background of the main panel for the button panel
		
		buttonPanel.add(startButton); // adds the start button to the panel
		buttonPanel.add(cancelButton);
		buttonPanel.add(quitButton); // adds the exit button to the panel
				
		add(buttonPanel);
	}
	
	/**
	 * Returns back the preferred size of the JComponent and sets the preferred size of the 
	 * JPanel to fit onto the JFrame of the board menu.
	 * 
	 * @return the preferred size of the JComponent 
	 */
	public Dimension getPreferredSize(){
		Dimension size = new Dimension(0, 48);
		return size;
	}
	
	public JButton getStartButton() {
		return startButton;
	}
}