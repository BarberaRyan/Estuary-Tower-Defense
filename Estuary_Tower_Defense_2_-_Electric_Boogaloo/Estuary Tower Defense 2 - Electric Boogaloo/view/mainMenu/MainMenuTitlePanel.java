package mainMenu;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;

/**
 * Creates the title of the panel that runs across the top of the screen. It takes in a String 
 * and sets the title of the panel to that particular string. 
 * 
 * @author Ryan Barbera, Aaron George, Nick Norton, Thomas Pennington, Grant Zhao
 *
 */

public class MainMenuTitlePanel extends JLabel{

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor that sets up the title panel based upon the string title and creates a new 
	 * MainMenuTitlePanel object. Are able to change to color of the text and panel along with 
	 * the font.
	 * 
	 * @param titleText the String that will be used to set the title of the screen
	 */
	public MainMenuTitlePanel(String titleText){
		Font titleFont = new Font(Font.DIALOG, Font.BOLD, 32); // creates the font
		setFont(titleFont); // sets the font
		setBackground(Color.YELLOW); // sets the background color
		setForeground(Color.RED); // sets the color of the letters in writing
		setOpaque(true); // allows the colors to sit on top of each other
		setHorizontalAlignment(JLabel.CENTER); // aligns to the center of the screen
		setText(titleText); // sets the text for the tile based on given constructor input
	}
	
	
	/**
	 * Returns back the preferred size of the JComponent. Sets the preferred size of the 
	 * JPanel to fit onto the JFrame of the main menu.
	 * 
	 * @return the preferred size of the JComponent 
	 */
	public Dimension getPreferredSize(){
		Dimension size = new Dimension(0, 47);
		return size;
	}
}
