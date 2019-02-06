package boardMenu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import controller.GameController;

/**
 * Creates the center panel class to put the main graphics of the game on. It creates the track images 
 * and paints it on the screen. It creates the action listeners for the center panel to keep track of 
 * movements when JLabels are added to the screen for different towers. Everything in this class is set on 
 * a layered pane so different objects are able to be put on different layers for simplicity of the game. 
 * 
 * @author Ryan Barbera, Aaron George, Nick Norton, Thomas Pennington, Grant Zhao
 */
public class BoardMenuCenterPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	JLayeredPane layeredPane;
	private Insets centerPanelInsets;
	private JLabel rangeLabel;
	
	private String rangeString = "range.png";
	private BufferedImage rangeImage;
	private GameController controller;

/**
 * Creates a BoardMenuCenterPanel object to setup the center panel. It loads in the images of the range
 * images that are added to the tower labels to show the range that the tower can shoot. Creates a layered pane
 * to put over top of the center panel for easy tracking of the different JComponets that are used in the game. 
 * Sets up all boundaries that each of the components need to have proper display to the user. 
 * 
 * @param gc the current game controller of the game
 * @param boardMenuSize the preferred size Dimension of the BoardMenu 
 * @param buttonPanelSize the preferred size Dimension of the buttonPanel
 * @param sidePanelSize the preferred size Dimension of the sidePanel
 */
	public BoardMenuCenterPanel(GameController gc, Dimension boardMenuSize, Dimension buttonPanelSize, Dimension sidePanelSize) {
		this.controller = gc;
		
		setBackground(Color.GREEN);
		
		layeredPane = new JLayeredPane();
		layeredPane.setLocation(0, 0);
		layeredPane.setPreferredSize(new Dimension(boardMenuSize.width - sidePanelSize.width, boardMenuSize.height - buttonPanelSize.height));
		layeredPane.setLayout(null);
		
		centerPanelInsets = layeredPane.getInsets();
		
		add(layeredPane);

		rangeImage = null;
		try {
			 rangeImage = ImageIO.read(new File(rangeString));
		} catch (IOException e) {
			e.printStackTrace();
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
		tmpLabel.setSize(new Dimension(controller.getScreenImageSize(), controller.getScreenImageSize()));
		tmpLabel.setBounds(centerPanelInsets.left + e.getX() - tmpLabel.getWidth()/2, centerPanelInsets.top + e.getY() - tmpLabel.getHeight()/2, controller.getScreenImageSize(), controller.getScreenImageSize());
		
		tmpTowerButton.setLocation(e.getPoint());
		tmpTowerButton.setSize(new Dimension(controller.getScreenImageSize(), controller.getScreenImageSize()));
		tmpTowerButton.setBounds(centerPanelInsets.left + e.getX() - tmpLabel.getWidth()/2, centerPanelInsets.top + e.getY() - tmpLabel.getHeight()/2, controller.getScreenImageSize(), controller.getScreenImageSize());
		
		// switches on the TowerView name for action listeners 
		switch(tv.getTowerName()) {
		case "Generic Shellfish":
			tmpTowerButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent ae) {
					if (rangeLabel != null) {
						layeredPane.remove(rangeLabel);
						layeredPane.repaint();
					}
					
					Double doubleSize = controller.getModel().getIndexedTowerModel(0).getRangeMultiplier() * 900;
					Integer rangeSize = doubleSize.intValue();
					
					rangeLabel = new JLabel(new ImageIcon(rangeImage.getScaledInstance(rangeSize, rangeSize, Image.SCALE_SMOOTH)));
					
					rangeLabel.setOpaque(false);
					rangeLabel.setLocation(tmpTowerButton.getLocation());
					rangeLabel.setSize(250, 250);
					rangeLabel.setBounds(
							centerPanelInsets.left + tmpTowerButton.getLocation().x - rangeLabel.getWidth() / 4 - 15,
							centerPanelInsets.top + tmpTowerButton.getLocation().y - rangeLabel.getHeight() / 4, 250,
							250);
					layeredPane.add(rangeLabel, JLayeredPane.DRAG_LAYER);
					rangeLabel.setSize(rangeSize, rangeSize);
					rangeLabel.setBounds(tmpTowerButton.getLocation().x  + tmpTowerButton.getWidth()/2 - rangeLabel.getWidth() / 2,
							tmpTowerButton.getLocation().y + tmpTowerButton.getHeight()/2 - rangeLabel.getHeight() / 2, rangeSize,
							rangeSize);
					layeredPane.add(rangeLabel, JLayeredPane.DRAG_LAYER);		
				}
			});
			
		case "Eastern Oyster":
			tmpTowerButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent ae) {
					if (rangeLabel != null) {
						layeredPane.remove(rangeLabel);
						layeredPane.repaint();
					}
					
					Double doubleSize = controller.getModel().getIndexedTowerModel(1).getRangeMultiplier() * 900;
					Integer rangeSize = doubleSize.intValue();
					
					rangeLabel = new JLabel(new ImageIcon(rangeImage.getScaledInstance(rangeSize, rangeSize, Image.SCALE_SMOOTH)));
					
					rangeLabel.setOpaque(false);
					rangeLabel.setSize(rangeSize, rangeSize);
					rangeLabel.setBounds(tmpTowerButton.getLocation().x  + tmpTowerButton.getWidth()/2 - rangeLabel.getWidth() / 2,
							tmpTowerButton.getLocation().y + tmpTowerButton.getHeight()/2 - rangeLabel.getHeight() / 2, rangeSize,
							rangeSize);
					layeredPane.add(rangeLabel, JLayeredPane.DRAG_LAYER);		
				}
			});

		case "Blue Crab":
			tmpTowerButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent ae) {
					if (rangeLabel != null) {
						layeredPane.remove(rangeLabel);
						layeredPane.repaint();
					}
					
					Double doubleSize = controller.getModel().getIndexedTowerModel(2).getRangeMultiplier() * 900;
					Integer rangeSize = doubleSize.intValue();
					
					rangeLabel = new JLabel(new ImageIcon(rangeImage.getScaledInstance(rangeSize, rangeSize, Image.SCALE_SMOOTH)));
					
					rangeLabel.setOpaque(false);
					rangeLabel.setSize(rangeSize, rangeSize);
					rangeLabel.setBounds(tmpTowerButton.getLocation().x  + tmpTowerButton.getWidth()/2 - rangeLabel.getWidth() / 2,
							tmpTowerButton.getLocation().y + tmpTowerButton.getHeight()/2 - rangeLabel.getHeight() / 2, rangeSize,
							rangeSize);
					layeredPane.add(rangeLabel, JLayeredPane.DRAG_LAYER);		
				}
			});
			
		case "Horseshoe Crab":
			tmpTowerButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent ae) {
					if (rangeLabel != null) {
						layeredPane.remove(rangeLabel);
						layeredPane.repaint();
					}
					
					Double doubleSize = controller.getModel().getIndexedTowerModel(3).getRangeMultiplier() * 900;
					Integer rangeSize = doubleSize.intValue();
					
					rangeLabel = new JLabel(new ImageIcon(rangeImage.getScaledInstance(rangeSize, rangeSize, Image.SCALE_SMOOTH)));
					
					rangeLabel.setOpaque(false);
					rangeLabel.setSize(rangeSize, rangeSize);
					rangeLabel.setBounds(tmpTowerButton.getLocation().x  + tmpTowerButton.getWidth()/2 - rangeLabel.getWidth() / 2,
							tmpTowerButton.getLocation().y + tmpTowerButton.getHeight()/2 - rangeLabel.getHeight() / 2, rangeSize,
							rangeSize);
					layeredPane.add(rangeLabel, JLayeredPane.DRAG_LAYER);		
				}
			});
			
		case "Generic Fish":
			tmpTowerButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent ae) {
					if (rangeLabel != null) {
						layeredPane.remove(rangeLabel);
						layeredPane.repaint();
					}
					
					Double doubleSize = controller.getModel().getIndexedTowerModel(4).getRangeMultiplier() * 900;
					Integer rangeSize = doubleSize.intValue();
					
					rangeLabel = new JLabel(new ImageIcon(rangeImage.getScaledInstance(rangeSize, rangeSize, Image.SCALE_SMOOTH)));
					
					rangeLabel.setOpaque(false);
					rangeLabel.setLocation(tmpTowerButton.getLocation());
					
					rangeLabel.setSize(rangeSize, rangeSize);

					rangeLabel.setBounds(tmpTowerButton.getLocation().x  + tmpTowerButton.getWidth()/2 - rangeLabel.getWidth() / 2,
							tmpTowerButton.getLocation().y + tmpTowerButton.getHeight()/2 - rangeLabel.getHeight() / 2, rangeSize,
							rangeSize);
					layeredPane.add(rangeLabel, JLayeredPane.DRAG_LAYER);	
				}
			});
		
		case "River Herring" :
			tmpTowerButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent ae) {
					if (rangeLabel != null) {
						layeredPane.remove(rangeLabel);
						layeredPane.repaint();
					}
					
					Double doubleSize = controller.getModel().getIndexedTowerModel(5).getRangeMultiplier() * 900;
					Integer rangeSize = doubleSize.intValue();
					
					rangeLabel = new JLabel(new ImageIcon(rangeImage.getScaledInstance(rangeSize, rangeSize, Image.SCALE_SMOOTH)));
					
					rangeLabel.setOpaque(false);
					rangeLabel.setLocation(tmpTowerButton.getLocation());
					
					rangeLabel.setSize(rangeSize, rangeSize);

					rangeLabel.setBounds(tmpTowerButton.getLocation().x  + tmpTowerButton.getWidth()/2 - rangeLabel.getWidth() / 2,
							tmpTowerButton.getLocation().y + tmpTowerButton.getHeight()/2 - rangeLabel.getHeight() / 2, rangeSize,
							rangeSize);
					layeredPane.add(rangeLabel, JLayeredPane.DRAG_LAYER);	
				}
			});
		
		case "Blue Fish" :
			tmpTowerButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent ae) {
					if (rangeLabel != null) {
						layeredPane.remove(rangeLabel);
						layeredPane.repaint();
					}
					
					Double doubleSize = controller.getModel().getIndexedTowerModel(6).getRangeMultiplier() * 900;
					Integer rangeSize = doubleSize.intValue();
					
					rangeLabel = new JLabel(new ImageIcon(rangeImage.getScaledInstance(rangeSize, rangeSize, Image.SCALE_SMOOTH)));
					
					rangeLabel.setOpaque(false);
					rangeLabel.setLocation(tmpTowerButton.getLocation());
					
					rangeLabel.setSize(rangeSize, rangeSize);

					rangeLabel.setBounds(tmpTowerButton.getLocation().x  + tmpTowerButton.getWidth()/2 - rangeLabel.getWidth() / 2,
							tmpTowerButton.getLocation().y + tmpTowerButton.getHeight()/2 - rangeLabel.getHeight() / 2, rangeSize,
							rangeSize);
					layeredPane.add(rangeLabel, JLayeredPane.DRAG_LAYER);	
				}
			});
			
		case "Summer Flounder" :
			tmpTowerButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent ae) {
					if (rangeLabel != null) {
						layeredPane.remove(rangeLabel);
						layeredPane.repaint();
					}
					
					Double doubleSize = controller.getModel().getIndexedTowerModel(7).getRangeMultiplier() * 900;
					Integer rangeSize = doubleSize.intValue();
					
					rangeLabel = new JLabel(new ImageIcon(rangeImage.getScaledInstance(rangeSize, rangeSize, Image.SCALE_SMOOTH)));
					
					rangeLabel.setOpaque(false);
					rangeLabel.setLocation(tmpTowerButton.getLocation());
					
					rangeLabel.setSize(rangeSize, rangeSize);

					rangeLabel.setBounds(tmpTowerButton.getLocation().x  + tmpTowerButton.getWidth()/2 - rangeLabel.getWidth() / 2,
							tmpTowerButton.getLocation().y + tmpTowerButton.getHeight()/2 - rangeLabel.getHeight() / 2, rangeSize,
							rangeSize);
					layeredPane.add(rangeLabel, JLayeredPane.DRAG_LAYER);
				}
			});
			
		case "Generic Bird":
			tmpTowerButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent ae) {
					if (rangeLabel != null) {
						layeredPane.remove(rangeLabel);
						layeredPane.repaint();
					}
					
					Double doubleSize = controller.getModel().getIndexedTowerModel(8).getRangeMultiplier() * 900;
					Integer rangeSize = doubleSize.intValue();
					
					rangeLabel = new JLabel(new ImageIcon(rangeImage.getScaledInstance(rangeSize, rangeSize, Image.SCALE_SMOOTH)));
					
					rangeLabel.setOpaque(false);
					rangeLabel.setLocation(tmpTowerButton.getLocation());
					rangeLabel.setSize(rangeSize, rangeSize);
					rangeLabel.setBounds(tmpTowerButton.getLocation().x  + tmpTowerButton.getWidth()/2 - rangeLabel.getWidth() / 2,
							tmpTowerButton.getLocation().y + tmpTowerButton.getHeight()/2 - rangeLabel.getHeight() / 2, rangeSize,
							rangeSize);
					layeredPane.add(rangeLabel, JLayeredPane.DRAG_LAYER);
				}
			});
			
		case "Ruddy Turnstone" :
			tmpTowerButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent ae) {
					if (rangeLabel != null) {
						layeredPane.remove(rangeLabel);
						layeredPane.repaint();
					}
					
					Double doubleSize = controller.getModel().getIndexedTowerModel(9).getRangeMultiplier() * 900;
					Integer rangeSize = doubleSize.intValue();
					
					rangeLabel = new JLabel(new ImageIcon(rangeImage.getScaledInstance(rangeSize, rangeSize, Image.SCALE_SMOOTH)));
					
					rangeLabel.setOpaque(false);
					rangeLabel.setLocation(tmpTowerButton.getLocation());
					
					rangeLabel.setSize(rangeSize, rangeSize);

					rangeLabel.setBounds(tmpTowerButton.getLocation().x  + tmpTowerButton.getWidth()/2 - rangeLabel.getWidth() / 2,
							tmpTowerButton.getLocation().y + tmpTowerButton.getHeight()/2 - rangeLabel.getHeight() / 2, rangeSize,
							rangeSize);
					layeredPane.add(rangeLabel, JLayeredPane.DRAG_LAYER);	
				}
			});
			
		case "Sanderling" :
			tmpTowerButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent ae) {
					if (rangeLabel != null) {
						layeredPane.remove(rangeLabel);
						layeredPane.repaint();
					}
					
					Double doubleSize = controller.getModel().getIndexedTowerModel(10).getRangeMultiplier() * 900;
					Integer rangeSize = doubleSize.intValue();
					
					rangeLabel = new JLabel(new ImageIcon(rangeImage.getScaledInstance(rangeSize, rangeSize, Image.SCALE_SMOOTH)));
					
					rangeLabel.setOpaque(false);
					rangeLabel.setLocation(tmpTowerButton.getLocation());
					
					rangeLabel.setSize(rangeSize, rangeSize);

					rangeLabel.setBounds(tmpTowerButton.getLocation().x  + tmpTowerButton.getWidth()/2 - rangeLabel.getWidth() / 2,
							tmpTowerButton.getLocation().y + tmpTowerButton.getHeight()/2 - rangeLabel.getHeight() / 2, rangeSize,
							rangeSize);
					layeredPane.add(rangeLabel, JLayeredPane.DRAG_LAYER);	
				}
			});
			
		case "Osprey" :
			tmpTowerButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent ae) {
					if (rangeLabel != null) {
						layeredPane.remove(rangeLabel);
						layeredPane.repaint();
					}
					
					Double doubleSize = controller.getModel().getIndexedTowerModel(11).getRangeMultiplier() * 900;
					Integer rangeSize = doubleSize.intValue();
					
					rangeLabel = new JLabel(new ImageIcon(rangeImage.getScaledInstance(rangeSize, rangeSize, Image.SCALE_SMOOTH)));
					
					rangeLabel.setOpaque(false);
					rangeLabel.setLocation(tmpTowerButton.getLocation());
					
					rangeLabel.setSize(rangeSize, rangeSize);

					rangeLabel.setBounds(tmpTowerButton.getLocation().x  + tmpTowerButton.getWidth()/2 - rangeLabel.getWidth() / 2,
							tmpTowerButton.getLocation().y + tmpTowerButton.getHeight()/2 - rangeLabel.getHeight() / 2, rangeSize,
							rangeSize);
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
	

	public JLayeredPane getLayeredPane() {
		return layeredPane;
	}
}
