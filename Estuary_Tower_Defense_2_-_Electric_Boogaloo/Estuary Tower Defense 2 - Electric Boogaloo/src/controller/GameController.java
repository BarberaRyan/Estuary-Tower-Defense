package controller;

import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import main.EnemyModel;
import main.GameModel;
import main.GameView;
import main.Posn;
import main.TowerModel;

/**
 * The GameController for the main game when it is running. Houses methods that control
 * different aspects of the game when events happen. Implements runnable to control enemy
 * movement of the screen. 
 * 
 * @author Ryan Barbera, Aaron George, Nick Norton, Thomas Pennington, Grant Zhao
 * 
 */

public class GameController implements Runnable {
	
	GameModel model;
	GameView view;
	TutorialGameController tutorialController;
	
	double timeRatio;
	Thread timerThread;
	
	/**
	 * Creates a new GameController object that initializes the GameModel, TutorialController,
	 * and the GameView. Sets up the player model for the current state of the game when it is 
	 * running. 
	 */
	public GameController() {
		model = new GameModel();
		tutorialController = new TutorialGameController(this);
		modelInit();
		view = new GameView(this, tutorialController);
	}
	
	/**
	 * Initializes the player model in the game by setting each attribute that a 
	 * player has. Uses this information to setup the view when this method is 
	 * called. 
	 */
	public void modelInit() {
		model.getPlayer().setHealth(30);
		model.getPlayer().setMoney(250);
		model.getPlayer().setScore(0);
		model.getPlayer().setRoundTime(1320);
		model.getPlayer().setTimeLeft(1320);
	}
	
	/**
	 * Called when the start round button is clicked in the game from the button panel.
	 * Disables the start button and all tower buttons when the round is running so a 
	 * purchase is not able to take place. Starts the timer to begin the game. 
	 */
	public void startRoundButtonClicked() {
		for(int counter = 0; counter < view.getBoardMenu().getSidePanel().getGameButtons().length; counter++) {
			view.getBoardMenu().getSidePanel().getGameButtons()[counter].setEnabled(false);
		}
		view.getBoardMenu().getButtonPanel().getStartButton().setEnabled(false);
		startTimer();

		//TODO starts the round
		//TODO implement pause button over this button?
		
	} 

	/**
	 * Called when the quit button is pressed from the button panel. This stops the current 
	 * thread that is running and reinitializes the player model for the next player that 
	 * plays the game. 
	 */
	public void boardMenuQuitButtonClicked() {
		stopTimer();
		modelInit();
		//TODO resets round multiplier for enemy spawning, maybe implement a round counter (if so, reset round counter)
		
	}

	/**
	 * Called when the main menu start button is clicked from the button panel. Creates a new BoardMenuView
	 * with all of the player model information updated in the view. Gets the path ready for the game 
	 * based on the screen size that this game is played on. Disables any tower unit that is not able 
	 * to be purchased from insufficient money for a player. 
	 */
	public void mainMenuStartButtonClicked() {
		int playerHealth = model.getPlayer().getHealth();
		int playerMoney = model.getPlayer().getMoney();
		int playerScore = model.getPlayer().getScore();
		long roundTime = model.getPlayer().getRoundTime();
		long timeLeft = model.getPlayer().getTimeLeft();
		
		view.makeBoardMenu(playerHealth, playerMoney, playerScore, roundTime, timeLeft);
		checkAffordability();
		model.getPath().setPosnArr(view.getBoardMenu().getPicPanel().getPosns());
		model.getPath().setSize(model.getPath().getSize());
	}

	/**
	 * Controls what happens with every game tick of the timer that is running for the game and 
	 * updates the BoardMenuView accordingly based on events that are taking place. 
	 * Spawns enemies to follow the path at set intervals based on the current time remaining.
	 */
	@Override
	public void run() {
		if(view.getBoardMenu().getMouseTower().getTowerLabel() != null) {
			view.getBoardMenu().removeLabelOnMouse();
		}
		while(model.getPlayer().getTimeLeft() > 0) {
			try {
				Thread.sleep(33);
			} catch (InterruptedException e) {
				return;
			}
			
			if(model.getPlayer().getTimeLeft() % 165 == 0 || model.getPlayer().getTimeLeft() == 1287) {
				EnemyModel tmpEnemy = model.spawnEnemy();
				view.getBoardMenu().addEnemy(tmpEnemy, model.getPath().getPosnAtIndex(0));
			}
			
			EnemyIterator ti = new EnemyIterator(model.getEnemyList());
			
			while(ti.hasNext()) {
				EnemyModel currentEnemy = ti.next();
				JLabel enemyLabel = null;
				
				model.moveEnemy(currentEnemy);
//				System.out.println(currentEnemy.getPosn().getXCor() + ", " + currentEnemy.getPosn().getYCor());
				
				enemyLabel = view.getBoardMenu().getEnemyLabels().get(ti.getIndex());
				enemyLabel.setLocation(currentEnemy.getPosn().getXCor(), currentEnemy.getPosn().getYCor());
				enemyLabel.setBounds(enemyLabel.getLocation().x - enemyLabel.getWidth() / 2,
						enemyLabel.getLocation().y - enemyLabel.getHeight() / 2, enemyLabel.getWidth(),
						enemyLabel.getHeight());
				
				if(model.isOutOfBounds(currentEnemy)) {
					model.reducePlayerHealth(1);
					view.getBoardMenu().getSidePanel().getScorePanel().setHealth(model.getPlayer().getHealth());
					
					if (model.isPlayerDead()) {
					
					}
					ti.remove();
					view.getBoardMenu().getCenterPanel().getLayeredPane().remove(enemyLabel);
				} else {
					for (int towerCounter = 0; towerCounter < model.getTowerList().size(); towerCounter++) {
						if(model.isReloading(towerCounter)) {
							continue;
						} else {
							if(model.isInRange(towerCounter, currentEnemy)) {
								if(model.damageEnemy(towerCounter, currentEnemy)) {
									model.getPlayer().setScore(model.getPlayer().getScore() + currentEnemy.getScoreValue());
									model.increasePlayerMoney(currentEnemy.getBounty());
									
									view.getBoardMenu().getCenterPanel().getLayeredPane().remove(enemyLabel);
									ti.remove();
									
									view.getBoardMenu().getSidePanel().getScorePanel().setScore(model.getPlayer().getScore());
									view.getBoardMenu().getSidePanel().getScorePanel().setSandDollars(model.getPlayer().getMoney());
									break;
								} else {
									continue;
								}
							} else {
								continue;
							}
						}
					}
				}
				model.setEnemyList(ti.getIterEnemyList());
			}
			
			model.getPlayer().setTimeLeft(model.getPlayer().getTimeLeft() - 1);
			view.getBoardMenu().getSidePanel().getTimerPanel().setTime(model.getPlayer().getTimeLeft());
			timeRatio = (double) model.getPlayer().getTimeLeft()/model.getPlayer().getRoundTime();
			view.getBoardMenu().getSidePanel().getTimerPanel().burnTimerBar(1, timeRatio);
			
			view.getBoardMenu().getSidePanel().getTimerPanel().repaint();
			view.getBoardMenu().getSidePanel().getTimerPanel().repaint();
		}
		timesUp();

	}
	
	/**
	 * Called when there is no time remaining on the clock. Asks the player if they would like to 
	 * play again. Updates the time in the game to the player model time that was passed in from the controller. 
	 * Enables any of the towers that are able to be purchased based on the total money that a player has. 
	 * Enables the start round button so that a player can play another round. 
	 */
	public void timesUp() {
		stopTimer();
		JOptionPane.showMessageDialog(null, "This round has ended. Start the next round when you're ready!");
		model.getPlayer().setTimeLeft(model.getPlayer().getRoundTime());
		view.getBoardMenu().getButtonPanel().getStartButton().setEnabled(true);
		checkAffordability();
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
			return false;
		}
	}

	/**
	 * Sets the information panel with the tower name, tower fact description, tower price, and tower 
	 * damage based on the button that was clicked on the side panel. 
	 * 
	 * @param i the index of the tower model to get factual information from about estuaries
	 */
	public void towerButtonIClicked(int i) {
		view.getBoardMenu().getSidePanel().getInfoPanel().setCreatureText(model.getIndexedTowerModel(i).getName());
		view.getBoardMenu().getSidePanel().getInfoPanel().setFactText(model.getIndexedTowerModel(i).getFactDescription());
		view.getBoardMenu().getSidePanel().getInfoPanel().setCost(model.getIndexedTowerModel(i).getPrice());
		view.getBoardMenu().getSidePanel().getInfoPanel().setDamage(model.getIndexedTowerModel(i).getDamage());
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
	 * Goes through each of the tower units and checks to see if the player has enough money 
	 * to purchase towers when the round is not active. If there is enough money left for the 
	 * player then the button is enabled, else it is disabled until a player has enough money 
	 * accumulated up. 
	 */
	public void checkAffordability() {
		for (int counter = 0; counter < 12; counter++) {
			switch(counter) {
			case 0: case 1: case 2: case 3: case 4: case 5: case 6: case 7: case 8: case 9: case 10: case 11:
				if(isAffordable(counter)) {
					view.getBoardMenu().getSidePanel().getGameButtons()[counter].setEnabled(true);
				} else {
					view.getBoardMenu().getSidePanel().getGameButtons()[counter].setEnabled(false);
				}
			}
		}
	}
	
	public GameModel getModel() {
		return model;
	}
	
	public void setModel(GameModel model){
		this.model = model;
	}
	
	public GameView getView() {
		return view;
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
		Double d1 = view.getBoardMenu().WIDTH_OF_SCREEN * .05;
		Integer i1 = d1.intValue();

		Double d2 = view.getBoardMenu().HEIGHT_OF_SCREEN * .07;
		Integer i2 = d2.intValue();

		if (i1 > i2) {
			return i2;
		} else {
			return i1;
		}
	}
}
