package tutorial;

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

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import boardMenu.TowerView;
import controller.TutorialGameController;
import main.GameView;

/**
* Sets up the overall tutorial menu that displays everything to interact with the game. Will
* place the proper panels in the right spot so that it sets up correctly in the different 
* locations.
* 
* @author Ryan Barbera, Aaron George, Nick Norton, Thomas Pennington, Grant Zhao
*
*/

public class TutorialView extends JFrame implements MouseListener, MouseMotionListener{

	private static final long serialVersionUID = 1L;
	
	private Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
	public int SCREEN_SIZE_WIDTH = SCREEN_SIZE.width;
	public int SCREEN_SIZE_HEIGHT = SCREEN_SIZE.height-20;
	
	private String creatureString = "images/Turrets/Shellfish/generic_shellfish.png";
	private BufferedImage creatureImage;
		
	private TutorialButtonPanel buttonPanel;
	private TutorialSidePanel sidePanel;
	private TutorialCenterPanel centerPanel;
	private TutorialPicturePanel picPanel;
	
	JButton quitButton = new JButton("Return to Main Menu");
	JButton startButton = new JButton("Start Round"); // creates the start round button	
	JButton cancelButton = new JButton("Remove Image on Cursor");

	private GameView view;
	private TutorialGameController tutorialController;
	
	TowerView mouseTower;
	
	/**
	 * Constructor that makes a TutorialView and creates instances of the classes needed to make 
	 * the board menu view. The actions listeners and mouse listeners are called here for interactive 
	 * game play. Will also print out the screen size for reference to make sure that the entire game 
	 * view will fit into the window. 
	 * 
	 * @param gv the current instance of the gameView 
	 * @param tutorialController the current instance of the gameController 
	 */
	public TutorialView(GameView gv, TutorialGameController tutorialController){
		this.view = gv;
		this.tutorialController = tutorialController;
		fillCreatureImg();
		
		buttonPanel = new TutorialButtonPanel();
		sidePanel = new TutorialSidePanel(creatureImage);
		centerPanel = new TutorialCenterPanel(tutorialController, this.getPreferredSize(), buttonPanel.getPreferredSize(), sidePanel.getPreferredSize());
		picPanel = new TutorialPicturePanel(centerPanel.getPreferredSize());
		
		mouseTower = new TowerView();
		
		initBoardMenuButtonActionListeners();
		initSidePanelActionListeners(this);

		initGUI();
		makeView();
	}
	
	/**
	 * Sets up all of the panels so they are placed in the correct spot in the JFrame 
	 * on the screen. There are a total of 3 panels that are placed in their respective 
	 * locations on the screen. 
	 */
	private void initGUI(){
		add(buttonPanel, BorderLayout.PAGE_END);
		add(sidePanel, BorderLayout.EAST);
		centerPanel.layeredPane.add(picPanel, JLayeredPane.PALETTE_LAYER);
		picPanel.setBounds(centerPanel.getInsets().left, centerPanel.getInsets().top, centerPanel.layeredPane.getPreferredSize().width, centerPanel.layeredPane.getPreferredSize().height - 40);
		add(centerPanel, BorderLayout.CENTER);
	}
	
	/**
	 * Makes the entire frame that the components of the board menu view will sit on. Sets 
	 * up all of the properties needed to make the view work correctly when it loads and is 
	 * exited.
	 */
	private void makeView(){
		setTitle("Estuary Tower Defense Tutorial");
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
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
		Dimension size = new Dimension(SCREEN_SIZE_WIDTH, SCREEN_SIZE_HEIGHT);
		return size;
	}
	
	/**
	 * Reads in each of the images contained in the creatureImg array and puts
	 * them into a buffered image array to use for other functions of the game.
	 * Will throw an IOException if one of the images is not able to be read.
	 */
	private void fillCreatureImg() {
		try {
			creatureImage = ImageIO.read(new File(creatureString));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setSidePanel(int playerHealth, int playerMoney, int playerScore, long roundTime, long timeLeft) {
		sidePanel.setScorePanel(playerHealth, playerMoney, playerScore);
		sidePanel.setTimerPanel(roundTime);
	}
	
	/**
	 * Initializes the actionListeners on the 3 buttons that are located on the button panel
	 * of the TutorialView. They call methods to their respective locations in the game to 
	 * either start, stop, or cancel mouse events. 
	 */
	private void initBoardMenuButtonActionListeners() {
		// adds action listener on the button click for the start button 
		buttonPanel.getStartButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tutorialController.startRoundButtonTutorial();
				//TODO implement pause button
				buttonPanel.getStartButton().setEnabled(false);
					}
				});
		buttonPanel.getCancelButton().addActionListener(new ActionListener() {
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
		buttonPanel.getQuitButton().addActionListener(new ActionListener() {
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
	 * @param bmv an instance of the TutorialView to control the game 
	 */
	private void initSidePanelActionListeners(TutorialView bmv) {
		sidePanel.getCreatureButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String message = "Clicking on the crab button will allow you to start" +
						" \n" + "placing turrets onto the screen";
				
				JOptionPane.showMessageDialog(centerPanel, message);
				message = "Please select a spot on the game board to place a crab by clicking the mouse"+"\n"+
				"and of course not on the path";
				JOptionPane.showMessageDialog(centerPanel, message);
				
				tutorialController.towerButtonIClicked(0);
				
				if(mouseTower.getTowerLabel() != null) {
					removeLabelOnMouse();
				}
				mouseTower.setTowerLabel(new JLabel(new ImageIcon(creatureImage.getScaledInstance(tutorialController.getScreenImageSize(), tutorialController.getScreenImageSize(), Image.SCALE_SMOOTH))));
				mouseTower.setTowerName("Generic Shellfish");
				mouseTower.getTowerLabel().setVisible(false);
				picPanel.add(mouseTower.getTowerLabel(), JLayeredPane.DRAG_LAYER);
				
				centerPanel.addMouseMotionListener(bmv);
				centerPanel.addMouseListener(bmv);
				picPanel.revalidate();
			}
		});
	}
	
	public TutorialCenterPanel getCenterPanel() {
		return centerPanel;
	}

	public TutorialButtonPanel getButtonPanel() {
		return buttonPanel;
	}

	public TutorialSidePanel getSidePanel() {
		return sidePanel;
	}
	
	public TowerView getMouseTower() {
		return mouseTower;
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

	@Override
	public void mouseDragged(MouseEvent e) {		
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
			tutorialController.addTowerToList(e, mouseTower.getTowerName());
			removeLabelOnMouse();
			JOptionPane.showMessageDialog(centerPanel, "You have sucessfully placed a crab on the board" + "\n" +
			"You can place as many creatures on the board as you would like until your sanddollars reaches 0" +"\n" +
					"Watch out for the price of each of the creatures that are able to be priced");
			JOptionPane.showMessageDialog(centerPanel, "Now select the start button located below");
		} else {
			centerPanel.removeRangeLabels();
		}
		centerPanel.repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {		
	}
	@Override
	public void mouseReleased(MouseEvent e) {		
	}
	@Override
	public void mouseEntered(MouseEvent e) {		
	}
	@Override
	public void mouseExited(MouseEvent e) {		
	}
}
