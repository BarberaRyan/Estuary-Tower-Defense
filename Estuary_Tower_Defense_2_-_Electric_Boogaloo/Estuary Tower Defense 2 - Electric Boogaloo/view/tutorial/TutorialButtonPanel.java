package tutorial;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Displays the button panel at the bottom of the tutorial menu screen. This has 3 buttons that 
 * are able to control the game action when it is displayed in the tutorial. 
 * 
 * @author  Ryan Barbera, Aaron George, Nick Norton, Thomas Pennington, Grant Zhao
 *
 */
public class TutorialButtonPanel extends JPanel{

	private static final long serialVersionUID = 1L;

	final int GAP_SIZE_WIDTH = 1000; // sets the width of the JButtons
	final int GAP_SIZE_HEIGHT = 10; // sets the height of the JButtons
	
	private JPanel buttonPanel;
	private JButton quitButton = new JButton("Return to Main Menu");
	private JButton startButton = new JButton("Start Round"); // creates the start round button	
	JButton cancelButton = new JButton("Remove Image on Cursor");
	private Dimension buttonSize = quitButton.getPreferredSize(); // sets the preferred size of buttons

	/**
	 * Creates a new TutorialButtonPanel object and sets the parameters of the button panel.
	 * It adds three buttons to the panel and places the panel at the BorderLayout.PAGE_END. 
	 */
	public TutorialButtonPanel(){
		buttonPanel = new JPanel();
		
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
	 * Returns the overridden size of the JComponent. Sets the preferred size of the
	 * JPanel for the button panel to sit on. 
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
	
	public JButton getQuitButton() {
		return quitButton;
	}
	public JButton getCancelButton() {
		return cancelButton;
	}
}
