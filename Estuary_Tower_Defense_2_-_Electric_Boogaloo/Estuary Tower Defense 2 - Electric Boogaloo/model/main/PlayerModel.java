package main;

/**
 * A container for the attributes of the game that are relevant and related to
 * the player. This is created when the tutorial or board menus are created to 
 * set the score, money, health, and time left in the round. These attributes will
 * be used to update the view when a player is attacking the enemies on the game 
 * board. 
 * 
 * @author Ryan Barbera, Aaron George, Nick Norton, Thomas Pennington, Grant Zhao
 */

public class PlayerModel {
	int money;
	int health;
	int score;
	long roundTime;
	static long timeLeft;
	
	public int getMoney() {
		return money;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}

	public long getRoundTime() {
		return roundTime;
	}

	public void setRoundTime(long roundTime) {
		this.roundTime = roundTime;
	}

	public long getTimeLeft() {
		return timeLeft;
	}

	public void setTimeLeft(long updatedTimeLeft) {
		timeLeft = updatedTimeLeft;
	}

	public void setMoney(int money) {
		this.money = money;
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	
}