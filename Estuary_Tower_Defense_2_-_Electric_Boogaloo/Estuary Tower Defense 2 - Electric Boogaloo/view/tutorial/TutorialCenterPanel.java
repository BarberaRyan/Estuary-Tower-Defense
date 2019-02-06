package tutorial;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import boardMenu.TowerView;
import controller.TutorialGameController;

/**
 * Creates the center panel class to put the main graphics of the game on. It creates the track images 
 * and paints it on the screen. Everything in this class is set on a layered pane so different objects 
 * are able to be put on different layers for simplicity of the game. 
 * 
 * @author Ryan Barbera, Aaron George, Nick Norton, Thomas Pennington, Grant Zhao
 *
 */

public class TutorialCenterPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	// Bloons TD 2 TracksEdit. 25 Oct. 2017. Citation for the image used in
	// panel

	JLayeredPane layeredPane;
	private Insets centerPanelInsets;
	private JLabel rangeLabel;
	
	private List<String> rangeStringList = Arrays.asList(
			"range.png");
	private ArrayList<Icon> rangeIconList;
	
	private TutorialGameController tutorialController;

	/**
	 * Creates a TutorialCenterPanel object to setup the center panel. It loads in the images of the range
	 * images that are added to the tower labels to show the range that the tower can shoot. Creates a layered pane
	 * to put over top of the center panel for easy tracking of the different JComponets that are used in the game. 
	 * Sets up all boundaries that each of the components need to have proper display to the user.  
	 * 
	 * @param gc the current tutorial game controller of the game
	 * @param boardMenuSize the preferred size Dimension of the BoardMenu 
	 * @param buttonPanelSize the preferred size Dimension of the buttonPanel
	 * @param sidePanelSize the preferred size Dimension of the sidePanel
	 */
	public TutorialCenterPanel(TutorialGameController gc, Dimension boardMenuSize, Dimension buttonPanelSize, Dimension sidePanelSize) {
		this.tutorialController = gc;
		
		setBackground(Color.GREEN);
		
		layeredPane = new JLayeredPane();
		layeredPane.setLocation(0, 0);
		layeredPane.setPreferredSize(new Dimension(boardMenuSize.width - sidePanelSize.width, boardMenuSize.height - buttonPanelSize.height));
		layeredPane.setLayout(null);
		
		centerPanelInsets = layeredPane.getInsets();
		
		add(layeredPane);
		
		rangeIconList = new ArrayList<Icon>(2);
		
		for(String s: rangeStringList) {
			Image tmpImg = null;
			try {
				tmpImg = ImageIO.read(new File (s));
			} catch (IOException e) {
				e.printStackTrace();
			}
			rangeIconList.add(new ImageIcon(tmpImg));
		}
	
	}
	
	/**
	 * Adds a JLabel to the mouse when a button is clicked from the side panel and will follow the 
	 * mouse around on the center panel. Also adds a JButton to the back of the JLabel to click on 
	 * to find the range of the tower. Creates the range label to attach to the center panel as well 
	 * centered on the JLabel that was created in the center panel. Sets the appropriate sizing of each of 
	 * the JLabels and JButtons based on the dimensions of the screen. 
	 * 
	 * @param e the mouse event that has taken place on the center panel
	 * @param tv the TowerView objects that create a name and image label to add to JLabels
	 */
	public void addTmpLabel(MouseEvent e, TowerView tv) {
		JLabel tmpLabel = new JLabel(tv.getTowerLabel().getIcon());
		JButton tmpTowerButton = new JButton();
		
		tmpLabel.setLocation(e.getPoint());
		tmpLabel.setSize(new Dimension(tutorialController.getScreenImageSize(), tutorialController.getScreenImageSize()));
		tmpLabel.setBounds(centerPanelInsets.left + e.getX() - tmpLabel.getWidth()/2, centerPanelInsets.top + e.getY() - tmpLabel.getHeight()/2, tutorialController.getScreenImageSize(), tutorialController.getScreenImageSize());
		
		tmpTowerButton.setLocation(e.getPoint());
		tmpTowerButton.setSize(new Dimension(tutorialController.getScreenImageSize(), tutorialController.getScreenImageSize()));
		tmpTowerButton.setBounds(centerPanelInsets.left + e.getX() - tmpLabel.getWidth()/2, centerPanelInsets.top + e.getY() - tmpLabel.getHeight()/2, tutorialController.getScreenImageSize(), tutorialController.getScreenImageSize());
		
		switch(tv.getTowerName()) {
		case "Generic Shellfish":
			tmpTowerButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent ae) {
					if (rangeLabel != null) {
						layeredPane.remove(rangeLabel);
						layeredPane.repaint();
					}
					rangeLabel = new JLabel(rangeIconList.get(0));
					rangeLabel.setOpaque(false);
					rangeLabel.setLocation(tmpTowerButton.getLocation());
					rangeLabel.setSize(250, 250);
					rangeLabel.setBounds(
							centerPanelInsets.left + tmpTowerButton.getLocation().x - rangeLabel.getWidth() / 4 - 15,
							centerPanelInsets.top + tmpTowerButton.getLocation().y - rangeLabel.getHeight() / 4, 250,
							250);
					layeredPane.add(rangeLabel, JLayeredPane.DRAG_LAYER);
				}
			});
		}
		
		layeredPane.add(tmpLabel, JLayeredPane.DRAG_LAYER);
		layeredPane.add(tmpTowerButton, JLayeredPane.DEFAULT_LAYER);	
	}
	
	/**
	 * Removes a rangeLabel that was created from the layeredPane on the center panel
	 * and revalidates the layeredPane after this method is called. 
	 */
	public void removeRangeLabels() {
		if(rangeLabel != null) {
			layeredPane.remove(rangeLabel);
			rangeLabel = null;
			layeredPane.revalidate();
		}
	}
	
	public Insets getCenterPanelInsets() {
		return centerPanelInsets;
	}

}