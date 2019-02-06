package boardMenu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import controller.GameController;
import main.EnemyModel;
import main.GameView;
import main.Posn;

/**
 * Sets up the overall board menu that displays everything to interact with the game. Will
 * place the proper panels in the right spot so that it sets up correctly in the different 
 * locations.
 * 
 * @author Ryan Barbera, Aaron George, Nick Norton, Thomas Pennington, Grant Zhao
 *
 */

public class BoardMenuView extends JFrame implements MouseListener, MouseMotionListener {

	private static final long serialVersionUID = 1L;
	
	private Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
	
	public final int WIDTH_OF_SCREEN = SCREEN_SIZE.width; // sets the width of screen from getPreferredSize method
	public final int HEIGHT_OF_SCREEN = SCREEN_SIZE.height-20; // set the height of screen from getPreferredSize method

	private BoardMenuSideGamePanel sidePanel;
	private BoardMenuCenterPanel centerPanel;
	private BoardMenuButtonPanel buttonPanel;
	private BoardMenuPicturePanel picPanel;
	
	private GameView view;
	private GameController controller;
	
	private ArrayList<JLabel> enemyLabels;
	
	TowerView mouseTower;
	
	public List<String> creatureList = Arrays.asList("images/Turrets/Shellfish/generic_shellfish.png",
			"images/Turrets/Shellfish/eastern_oyster.jpeg", "images/Turrets/Shellfish/blue_crab.png",
			"images/Turrets/Shellfish/horseshoe_crab.gif", "images/Turrets/Fish/generic_fish.png",
			"images/Turrets/Fish/river_herring.png", "images/Turrets/Fish/blue_fish.png",
			"images/Turrets/Fish/summer_flounder.png", "images/Turrets/Birds/generic_bird.png",
			"images/Turrets/Birds/ruddy_turnstone.jpg", "images/Turrets/Birds/sanderling.jpg",
			"images/Turrets/Birds/osprey.png"
	);

	public List<String> enemyImgList = Arrays.asList("images/Enemies/animal_poop.png", "images/Enemies/litter.png",
			"images/Enemies/pathogens.png", "images/Enemies/chemical_waste.png", "images/Enemies/algae_overgrowth.png",
			"images/Enemies/ice.png");
	
	private BufferedImage[] creatureImg = new BufferedImage[creatureList.size()];
	private BufferedImage[] enemyImg = new BufferedImage[enemyImgList.size()];
	
	/**
	 * Constructor that makes a BoardMenuView and creates instances of the classes needed to make 
	 * the board menu view. The actions listeners and mouse listeners are called here for interactive 
	 * game play. Will also print out the screen size for reference to make sure that the entire game 
	 * view will fit into the window. 
	 * 
	 * @param gv the current instance of the gameView 
	 * @param gc the current instance of the gameController 
	 */
	public BoardMenuView(GameView gv, GameController gc) {
		this.view = gv;
		this.controller = gc;
		
		enemyLabels = new ArrayList<JLabel>(8);
		
		fillImgLists();
		
		buttonPanel = new BoardMenuButtonPanel();
		sidePanel = new BoardMenuSideGamePanel(creatureImg);
		centerPanel = new BoardMenuCenterPanel(controller, this.getPreferredSize(), buttonPanel.getPreferredSize(), sidePanel.getPreferredSize());
		picPanel = new BoardMenuPicturePanel(centerPanel.getPreferredSize());
		
		mouseTower = new TowerView();
		
		initBoardMenuButtonActionListeners();
		initGUI(); // calls the method to setup the rest of the screen
		setMainScreen(); // creates the base of the game board
		initSidePanelActionListeners(this);
		
		System.out.println(WIDTH_OF_SCREEN + " " + HEIGHT_OF_SCREEN);
	}

	/**
	 * Sets up all of the panels so they are placed in the correct spot in the JFrame 
	 * on the screen. There are a total of 3 panels that are placed in their respective 
	 * locations on the screen. 
	 */
	private void initGUI() {;
		add(sidePanel, BorderLayout.EAST);
		add(buttonPanel, BorderLayout.PAGE_END);
		centerPanel.layeredPane.add(picPanel, JLayeredPane.PALETTE_LAYER);
		picPanel.setBounds(centerPanel.getInsets().left, centerPanel.getInsets().top, centerPanel.layeredPane.getPreferredSize().width, centerPanel.layeredPane.getPreferredSize().height - 40);
		add(centerPanel, BorderLayout.CENTER);
	}
	
	/**
	 * Makes the entire frame that the components of the board menu view will sit on. Sets 
	 * up all of the properties needed to make the view work correctly when it loads and is 
	 * exited.
	 */
	private void setMainScreen() {
		setTitle("Estuary Tower Defense"); // sets the title of the screen
		setResizable(false); // disables the ability to make the screen bigger or smaller
		setLocationRelativeTo(null); // centers the screen on the display
		setDefaultCloseOperation(EXIT_ON_CLOSE); // closes the screen if the user exits from window
		setVisible(true);
		pack();
	}
	
	/**
	 * Returns the preferred size of the board menu based on the size of the screen that the game 
	 * is being played on. 
	 * 
	 * @return size the preferred size of the BoardMenuView 
	 */
	public Dimension getPreferredSize(){
		Dimension size = new Dimension(WIDTH_OF_SCREEN, HEIGHT_OF_SCREEN);
		return size;
	}
	
	/**
	 * Initializes the actionListeners on the 3 buttons that are located on the button panel
	 * of the BoardMenuView. They call methods to their respective locations in the game to 
	 * either start, stop, or cancel mouse events. 
	 */
	private void initBoardMenuButtonActionListeners() {
		// adds action listener on the button click for the start button 
		buttonPanel.startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.startRoundButtonClicked();
				//TODO implement pause button
				buttonPanel.startButton.setEnabled(false);
					}
				});
		buttonPanel.cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(mouseTower.getTowerLabel() != null) {
					removeLabelOnMouse();
				} else {
					JOptionPane.showMessageDialog(null, "You don't have anything on the cursor!");;
				}
			}
		});
		
		// adds action listener on the button click for the quit button 
		buttonPanel.quitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				view.boardMenuQuitButtonClicked();
				dispose();
			}
		});
	}
	
	/**
	 * Adds action listeners to all of the game buttons that are on the side panel of the JFrame.
	 * Information will be displayed to update the view based on the button that was clicked and 
	 * a label will be created on the center panel that attaches to the location of the mouse.
	 * 
	 * @param bmv an instance of the BoardMenuView to control the game 
	 */
	private void initSidePanelActionListeners(BoardMenuView bmv) {

		sidePanel.getIndexedGameButton(0).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.towerButtonIClicked(0);
				
				if(mouseTower.getTowerLabel() != null) {
					removeLabelOnMouse();
				}
				
				paintMouseLabel(bmv, 0);
			}
		});
		
		sidePanel.getIndexedGameButton(1).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.towerButtonIClicked(1);
				
				if(mouseTower.getTowerLabel() != null) {
					removeLabelOnMouse();
				}
				
				paintMouseLabel(bmv, 1);
			}
		});
		
		sidePanel.getIndexedGameButton(2).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.towerButtonIClicked(2);
				
				if(mouseTower.getTowerLabel() != null) {
					removeLabelOnMouse();
				}
				
				paintMouseLabel(bmv, 2);
			}
		});
		
		sidePanel.getIndexedGameButton(3).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.towerButtonIClicked(3);
				
				if(mouseTower.getTowerLabel() != null) {
					removeLabelOnMouse();
				}
				
				paintMouseLabel(bmv, 3);
			}
		});

		sidePanel.getIndexedGameButton(4).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.towerButtonIClicked(4);
				
				if(mouseTower.getTowerLabel() != null) {
					removeLabelOnMouse();
				}
				
				paintMouseLabel(bmv, 4);
			}
		});
		
		sidePanel.getIndexedGameButton(5).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.towerButtonIClicked(5);
				
				if(mouseTower.getTowerLabel() != null) {
					removeLabelOnMouse();
				}
				
				paintMouseLabel(bmv, 5);
			}
		});
		
		sidePanel.getIndexedGameButton(6).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.towerButtonIClicked(6);
				
				if(mouseTower.getTowerLabel() != null) {
					removeLabelOnMouse();
				}
				
				paintMouseLabel(bmv, 6);
			}
		});
		
		sidePanel.getIndexedGameButton(7).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.towerButtonIClicked(7);
				
				if(mouseTower.getTowerLabel() != null) {
					removeLabelOnMouse();
				}
				
				paintMouseLabel(bmv, 7);
			}
		});
		
		sidePanel.getIndexedGameButton(8).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.towerButtonIClicked(8);
				
				if(mouseTower.getTowerLabel() != null) {
					removeLabelOnMouse();
				}
				
				paintMouseLabel(bmv, 8);
			}
		});
		
		sidePanel.getIndexedGameButton(9).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.towerButtonIClicked(9);
				
				if(mouseTower.getTowerLabel() != null) {
					removeLabelOnMouse();
				}
				
				paintMouseLabel(bmv, 9);
			}
		});
		
		sidePanel.getIndexedGameButton(10).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.towerButtonIClicked(10);
				
				if(mouseTower.getTowerLabel() != null) {
					removeLabelOnMouse();
				}
				
				paintMouseLabel(bmv, 10);
			}
		});
		
		sidePanel.getIndexedGameButton(11).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.towerButtonIClicked(11);
				
				if(mouseTower.getTowerLabel() != null) {
					removeLabelOnMouse();
				}
				
				paintMouseLabel(bmv, 11);
			}
		});
	}
	
	/**
	 * Adds the mouseLabels to the screen based upon what image was selected. The Tower class is used to set 
	 * the names and labels then adds the label to the layeredPane. MotionListeners are added to the center panel
	 * and the pic panel will be revalidated. 
	 * 
	 * @param bmv the current instance of the BoardMenuView 
	 * @param counter an index to get the correct images for each of the labels that are created
	 */
	private void paintMouseLabel(BoardMenuView bmv, int counter) {
		mouseTower.setTowerLabel(new JLabel(new ImageIcon(creatureImg[counter].getScaledInstance(controller.getScreenImageSize(), controller.getScreenImageSize(), Image.SCALE_SMOOTH))));
		mouseTower.getTowerLabel().setVisible(false);
		
		switch(counter) {
		case 0:
			mouseTower.setTowerName("Generic Shellfish");
		case 1:
			mouseTower.setTowerName("Eastern Oyster");
		case 2:
			mouseTower.setTowerName("Blue Crab");
		case 3:
			mouseTower.setTowerName("Horseshoe Crab");
		case 4:
			mouseTower.setTowerName("Generic Fish");
		case 5:
			mouseTower.setTowerName("River Herring");
		case 6:
			mouseTower.setTowerName("Blue Fish");
		case 7:
			mouseTower.setTowerName("Summer Flounder");
		case 8:
			mouseTower.setTowerName("Generic Bird");
		case 9:
			mouseTower.setTowerName("Ruddy Turnstone");
		case 10:
			mouseTower.setTowerName("Sanderling");
		case 11:
			mouseTower.setTowerName("Osprey");
		}
		
		picPanel.add(mouseTower.getTowerLabel(), JLayeredPane.DRAG_LAYER);
		
		centerPanel.addMouseMotionListener(bmv);
		centerPanel.addMouseListener(bmv);
		picPanel.revalidate();
		
	}
	
	/**
	 * Reads in each of the images contained in the creatureImg array and puts
	 * them into a buffered image array to use for other functions of the game.
	 * Will throw an IOException if one of the images is not able to be read.
	 */
	private void fillImgLists() {
		try {
			for (int x = 0; x < creatureList.size(); x++) {
				creatureImg[x] = ImageIO.read(new File(creatureList.get(x)));
			}
			for (int x = 0; x < enemyImgList.size(); x++) {
				enemyImg[x] = ImageIO.read(new File(enemyImgList.get(x)));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Removes the label on the mouse once a tower is placed on the center panel. This allows a 
	 * user to only place one unit at a time on the screen. 
	 */
	public void removeLabelOnMouse() {
		picPanel.remove(mouseTower.getTowerLabel());
		mouseTower.setTowerLabel(null);
		picPanel.revalidate();
		picPanel.repaint();
	}
	
	/**
	 * Controls what happens when the mouse is moved around on the screen. In this case it 
	 * places a label with the tower to the mouse cursor and follows it in the center of it. 
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		if(mouseTower.getTowerLabel() != null) {
			mouseTower.getTowerLabel().setVisible(true);
			mouseTower.getTowerLabel().setLocation(e.getX() - mouseTower.getTowerLabel().getWidth() / 2, e.getY() - mouseTower.getTowerLabel().getHeight()/2);
		}
	}

	/**
	 * Controls what happens when the mouse is clicked on the screen. This places the towers onto the center panel
	 * and repaints the panel after it is placed on the screen. Then it removes the label off of the cursor until 
	 * another button is clicked.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		if(mouseTower.getTowerLabel() != null) {
			centerPanel.addTmpLabel(e, mouseTower);
			controller.addTowerToList(e, mouseTower.getTowerName());
			removeLabelOnMouse();
		} else {
			centerPanel.removeRangeLabels();
		}
		centerPanel.repaint();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public TowerView getMouseTower() {
		return mouseTower;
	}
	
	public void setMouseTower(TowerView tv) {
		this.mouseTower = tv;
	}
	
	public BoardMenuPicturePanel getPicPanel() {
		return picPanel;
	}

	/**
	 * Adds an enemy with a label, position, and the index of the image of the enemy list that 
	 * was created to the screen when called. 
	 * 
	 * @param tmpEnemy an enemy that will be placed on the screen
	 * @param start the location where the enemy will start on the screen
	 */
	public void addEnemy(EnemyModel tmpEnemy, Posn start) {
		JLabel tmpLabel = null;
		switch(tmpEnemy.getName()) {
		case "EnemyPoo" :
			addEnemyWithInt(tmpLabel, start, 0);
			break;
		case "EnemyLitter" :
			addEnemyWithInt(tmpLabel, start, 1);
			break;
		case "EnemyPathogen" :
			addEnemyWithInt(tmpLabel, start, 2);
			break;
		case "EnemyChemical" :
			addEnemyWithInt(tmpLabel, start, 3);
			break;
		case "EnemyAlgae" :
			addEnemyWithInt(tmpLabel, start, 4);
			break;
		case "EnemyIce" :
			addEnemyWithInt(tmpLabel, start, 5);
			break;
		}
	}
	
	/**
	 * Creates the images with a particular label, x and y position, and image of the enemy. 
	 * It will add the enemy to the center panel on the layeredPane sized accordingly to what 
	 * the screen size is.
	 * 
	 * @param label the JLabel that will be created for the enemy on the screen
	 * @param posn the position of the coordinates of the enemies that are placed on the screen 
	 * @param index the index of the enemy image in the arrayList 
	 */
	public void addEnemyWithInt(JLabel label, Posn posn, int index) {
		label = new JLabel(new ImageIcon(enemyImg[index].getScaledInstance(controller.getScreenImageSize(), controller.getScreenImageSize(), Image.SCALE_SMOOTH)));
		label.setLocation(posn.getXCor() - label.getIcon().getIconWidth()/2, posn.getYCor() - label.getIcon().getIconHeight()/2);
		label.setSize(new Dimension(controller.getScreenImageSize(), controller.getScreenImageSize()));
		label.setBounds(posn.getXCor() - getWidth()/2, posn.getYCor() - getHeight()/2, getWidth(), getHeight());
		enemyLabels.add(label);
		centerPanel.getLayeredPane().add(label, JLayeredPane.DRAG_LAYER);
	}
	
	public ArrayList<JLabel> getEnemyLabels() {
		return enemyLabels;
	}
	public BoardMenuCenterPanel getCenterPanel() {
		return centerPanel;
	}

	public BoardMenuButtonPanel getButtonPanel() {
		return buttonPanel;
	}

	public BoardMenuSideGamePanel getSidePanel() {
		return sidePanel;
	}
	
	public void setSidePanel(int playerHealth, int playerMoney, int playerScore, long roundTime, long timeLeft) {
		sidePanel.setScorePanel(playerHealth, playerMoney, playerScore);
		sidePanel.setTimerPanel(roundTime);
	}
}