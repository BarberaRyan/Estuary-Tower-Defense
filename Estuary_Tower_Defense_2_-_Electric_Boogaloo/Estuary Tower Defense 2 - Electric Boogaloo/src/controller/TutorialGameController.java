package controller;

import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import main.GameModel;
import main.GameView;
import main.Posn;
import main.TowerModel;

/**
 * Creates a tutorial controller that is responsible for running the tutorial in
 * the game that passes information from this class to the game model and tutorial view. 
 * 
 * @author Ryan Barbera, Aaron George, Nick Norton, Thomas Pennington, Grant Zhao
 *
 */

public class TutorialGameController implements Runnable {
	GameModel model;
	GameView view;
	GameController controller;
	
	Thread timerThread;
	double timeRatio;

	/**
	 * Constructor that creates a TutorialGameController object and initializes the 
	 * game model and game view to setup the tutorial. Calls modelInit to set information
	 * for the player that is playing the game. 
	 * 
	 * @param controller: the game controller to run methods for the tutorial
	 */
	public TutorialGameController(GameController controller) {
		this.controller = controller;
		model = new GameModel();
		modelInit();
		view = new GameView(controller, this);
	}
	
	/**
	 * Initializes the player model and will allow the view to update to set this to the 
	 * different components of the Score Panel and Timer Panel.
	 */
	public void modelInit() {
		model.getPlayer().setHealth(30);
		model.getPlayer().setMoney(1000);
		model.getPlayer().setScore(0);
		model.getPlayer().setRoundTime(1320);
		model.getPlayer().setTimeLeft(1320);
	}
	
	/**
	 * Called when the tutorial button is clicked from the MainMenu of the game. 
	 * Gets the player model information and puts this into the view to update the values
	 * for game play. Calls checkAfforability to set the game buttons to either enabled or 
	 * disabled depending on how much SandDollars that the player has in the game. 
	 */
	public void tutorialButtonClicked(){
		String message = "Indicated on the Score Panel is your total health, total sanddollars, and total score" +
	"\n" + "Indicated on the Information Panel is information reguarding the cost and educational fact about estuaries" +
		"\n" + "Indicated on the button panel is all creatures that are available for placement on the game board";
		int playerHealth = model.getPlayer().getHealth();
		int playerMoney = model.getPlayer().getMoney();
		int playerScore = model.getPlayer().getScore();
		long roundTime = model.getPlayer().getRoundTime();
		long timeLeft = model.getPlayer().getTimeLeft();
		
		view.makeTutorial(playerHealth, playerMoney, playerScore, roundTime, timeLeft);
		JOptionPane.showMessageDialog(view.getTutorialView().getCenterPanel(), message);
		checkAffordability();
	}
	
	/**
	 * Goes through each of the tower units and checks to see if the player has enough money 
	 * to purchase towers when the round is not active. If there is enough money left for the 
	 * player then the button is enabled, else it is disabled until a player has enough money 
	 * accumulated up. 
	 */
	public void checkAffordability() {
		if (isAffordable(0)) {
			view.getTutorialView().getSidePanel().getCreatureButton().setEnabled(true);
		} else {
			view.getTutorialView().getSidePanel().getCreatureButton().setEnabled(false);
		}
	}
	
	/**
	 * Checks the price of each of the towers buttons that are on the game board. If there is 
	 * sufficient money left for a player then a true value is returned, else false if a player cannot.
	 * 
	 * @param towerIndex the index of each of the towers that are in the game to get its price
	 * @return true or false value if the player has enough money to purchase a tower or not
	 */
	public boolean isAffordable(int towerIndex) {
		if (model.getPlayer().getMoney() >= model.getIndexedTowerModel(towerIndex).getPrice()) {
			return true;
		} else {
			JOptionPane.showMessageDialog(view.getTutorialView().getCenterPanel(), "You are unable to afford this creature due to insufficient sand dollars");
			return false;
		}
	}
	
	/**
	 * Called when the start button is clicked on the button panel. Will disable the start button 
	 * and all tower buttons when the start round button is clicked. Also
	 * will start the timer for the current round when this button is clicked. 
	 */
	public void startRoundButtonTutorial(){
		String message = "When you select the start button, the crab to the right is not"
				+ "\n" +"active for the duration of the round until the timer has run out";
		view.getTutorialView().getSidePanel().getCreatureButton().setEnabled(false);
		view.getTutorialView().getButtonPanel().getStartButton().setEnabled(false);
		JOptionPane.showMessageDialog(view.getTutorialView().getCenterPanel(), message);
		startTimer();
	}
	
	/**
	 * Starts the timer in the game by making a new thread for the game and starts the thread. 
	 * Terminates any old threads that have been created by making a call to stopTimer().
	 */
	public void startTimer() {
		stopTimer();
		timerThread = new Thread(this);
		timerThread.start();
	}
	
	/**
	 * Interrupts the current thread that the timer is running on. Will set the current  
	 * thread to null if a thread is running.  
	 */
	public void stopTimer() {
		if (timerThread != null) {
			timerThread.interrupt();
			timerThread = null;
		}
	}
	
	/**
	 * Makes a new instance of the tower that is passed into the method and puts it into 
	 * the tower arrayList. Sets the position of the tower to the current mouse position.
	 * Subtracts the price of the tower from the player money that is left 
	 * and updates the view. Checks to make sure that a player has enough money to purchase 
	 * a tower unit by calling checkAfforability. 
	 * 
	 * @param e a mouse event that has taken place on the screen 
	 * @param towerName a string of a tower that is in the game play
	 */
	public void addTowerToList(MouseEvent e, String towerName) {
		TowerModel tmpTower = model.getFactory().makeStringTower(towerName);
		Posn tmpPosn = new Posn(e.getX(), e.getY());
		tmpTower.setPosn(tmpPosn);
		addTowerToModel(tmpTower, tmpPosn);
		view.getBoardMenu().getSidePanel().getScorePanel().setSandDollars(model.getPlayer().getMoney());
		checkAffordability();
	}
	
	/**
	 * Reduces the player money by the cost of the tower that is passed in. Adds the tower 
	 * created to the array list and does not return anything. 
	 * 
	 * @param tm the towerModel that will be added to the array list 
	 * @param posn the position of the mouse on the screen of the towerModel 
	 */
	public void addTowerToModel(TowerModel tm, Posn posn){
		model.reducePlayerMoney(tm.getPrice());
		model.getTowerList().add(tm);
	}

	/**
	 * Is the main controller for the game that is run on the thread through the use of the 
	 * Runnable interface. Keeps track of the time left in the game and is making updates 
	 * throughout the tutorial. When the round is over a player will be asked if they would 
	 * like to play again.  
	 */
	@Override
	public void run() {
		if(view.getTutorialView().getMouseTower().getTowerLabel() != null) {
			view.getTutorialView().removeLabelOnMouse();
		}
		while(model.getPlayer().getTimeLeft() > 0) {
			try {
				Thread.sleep(33);
			} catch (InterruptedException e) {
				return;
			}
			model.getPlayer().setTimeLeft(model.getPlayer().getTimeLeft() - 1);
			view.getTutorialView().getSidePanel().getTimerPanel().setTime(model.getPlayer().getTimeLeft());
			timeRatio = (double) model.getPlayer().getTimeLeft()/model.getPlayer().getRoundTime();
			view.getTutorialView().getSidePanel().getTimerPanel().burnTimerBar(1, timeRatio);
			
			view.getTutorialView().getSidePanel().getTimerPanel().repaint();
		}
		timesUp();
	}
	
	/**
	 * Is called when the time has run out for the round. Resets the timer panel to the 
	 * original player model time. Goes through each of the towers and enables any towers 
	 * that the player is able to purchase based on the tower price and player money left 
	 * from the end of the round. 
	 */
	public void timesUp() {
		stopTimer();
		JOptionPane.showMessageDialog(null, "This round has ended. Start the next round when you're ready!");
		model.getPlayer().setTimeLeft(model.getPlayer().getRoundTime());
		view.getTutorialView().getButtonPanel().getStartButton().setEnabled(true);
		checkAffordability();
	}
	
	/**
	 * Sets the information panel with the tower name, tower fact description, tower price, and tower 
	 * damage based on the button that was clicked on the side panel. 
	 * 
	 * @param i the index of the tower model to get factual information from about estuaries
	 */
	public void towerButtonIClicked(int i) {
		view.getTutorialView().getSidePanel().getInfoPanel().setCreatureText(model.getIndexedTowerModel(i).getName());
		view.getTutorialView().getSidePanel().getInfoPanel().setFactText(model.getIndexedTowerModel(i).getFactDescription());
		view.getTutorialView().getSidePanel().getInfoPanel().setCost(model.getIndexedTowerModel(i).getPrice());
		view.getTutorialView().getSidePanel().getInfoPanel().setDamage(model.getIndexedTowerModel(i).getDamage());
	}
	
	/**
	 * Gets the smaller of the two dimensions of the screen size after taking a percentage 
	 * of the total size. Uses this information for scaling in the game for the size of objects 
	 * created.
	 * 
	 * @return an integer representing the small of the two sizes of the screen used for 
	 * resizing the tower labels and components in the game.
	 */
	public int getScreenImageSize() {
		Double d1 = view.getTutorialView().SCREEN_SIZE_WIDTH * .05;
		Integer i1 = d1.intValue();

		Double d2 = view.getTutorialView().SCREEN_SIZE_HEIGHT * .07;
		Integer i2 = d2.intValue();

		if (i1 > i2) {
			return i2;
		} else {
			return i1;
		}
	}
}
