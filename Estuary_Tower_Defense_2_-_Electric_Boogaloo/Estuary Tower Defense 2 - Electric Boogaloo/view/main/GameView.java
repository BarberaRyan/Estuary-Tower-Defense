package main;

import boardMenu.BoardMenuView;
import controller.GameController;
import controller.TutorialGameController;
import leaderBoard.LeaderBoardView;
import mainMenu.MainMenuView;
import tutorial.TutorialView;

/**
 * Controls what happens when a button is clicked on the main menu and what is the 
 * next action that the needs to happen in the game. It either makes a new instance of 
 * a view or controls what happens when a certain button is clicked on the BoardMenu or
 * the LeaderBoard. 
 * 
 * @author Ryan Barbera, Aaron George, Nick Norton, Thomas Pennington, Grant Zhao
 *
 */

public class GameView {
	//TODO these should be private with getters and setters
	MainMenuView mainMenu;
	BoardMenuView boardMenu;
	LeaderBoardView leaderBoard;
	TutorialView tutorial;
	GameController controller;
	TutorialGameController tutorialController;
	
	/**
	 * Creates a GameView object that initializes each of the controllers that are needed in the game 
	 * and starts up the main menu view to begin playing the game and selecting an option.
	 * 
	 * @param gc the current instance of the game controller class 
	 * @param tutorialController the current instance of the tutorialController class 
	 */
	public GameView(GameController gc, TutorialGameController tutorialController) {
		this.controller = gc;
		this.tutorialController = tutorialController;
		mainMenu = new MainMenuView(this, controller, tutorialController);
	}
	
	/**
	 * Makes a new BoardMenuView that initializes the score panel to the player health, score, and 
	 * money. Then it sets the timer panel to the amount of seconds that the rounds are going to 
	 * last for. 
	 * 
	 * @param playerHealth what the players health in the game is initialized to
	 * @param playerMoney what the players money in the game is initialized to
	 * @param playerScore what the players score in the game is initialized to
	 * @param roundTime what the round time is set to in the game
	 * @param timeLeft how much time is remaining in the current round 
	 */
	public void makeBoardMenu(int playerHealth, int playerMoney, int playerScore, long roundTime, long timeLeft) {
		boardMenu = new BoardMenuView(this, controller);
		boardMenu.setSidePanel(playerHealth, playerMoney, playerScore, roundTime, timeLeft);
	}

	/**
	 * Makes a new TutorialView when the tutorial button is clicked from the main menu view. 
	 * It initializes the score panel to the player health, score, and money. Then it sets the 
	 * timer panel to the amount of seconds that the rounds are going to last for. 
	 * 
	 @param playerHealth what the players health in the game is initialized to
	 * @param playerMoney what the players money in the game is initialized to
	 * @param playerScore what the players score in the game is initialized to
	 * @param roundTime what the round time is set to in the game
	 * @param timeLeft how much time is remaining in the current round 
	 */
	public void makeTutorial(int playerHealth, int playerMoney, int playerScore, long roundTime, long timeLeft){
		tutorial = new TutorialView(this, tutorialController);
		tutorial.setSidePanel(playerHealth, playerMoney, playerScore, roundTime, timeLeft);
	}

	/**
	 * Makes a new LeaderBoardView when the leader board button from the main menu view is clicked. 
	 * It takes in the current score of the round and checks it against the other high scores that 
	 * have been previously set. If the score is in the top 10, then the score is added to the leaderboard.
	 */
	public void leaderBoardButtonClicked() {
		leaderBoard = new LeaderBoardView(controller.getModel().getPlayer().getScore());
		// TODO displays the leader board, maybe pauses the game(?)
		
	}

	/**
	 * Exits the board menu and returns back to the main menu to select another option 
	 * in the game. 
	 */
	public void boardMenuQuitButtonClicked() {
		controller.boardMenuQuitButtonClicked();
		mainMenu = new MainMenuView(this, controller, tutorialController);
		// TODO close boardMenu, tell controller, controller deletes and closes everything
	}

	/**
	 * Exits the leader board and disposes of the current JFrame and starts back up the 
	 * main menu view to select another option in the game.
	 */
	public void leaderBoardExitButtonClicked() {
		mainMenu = new MainMenuView(this,controller, tutorialController);
	}
	
	public BoardMenuView getBoardMenu() {
		return boardMenu;
	}
	
	public TutorialView getTutorialView(){
		return tutorial;
	}
}
