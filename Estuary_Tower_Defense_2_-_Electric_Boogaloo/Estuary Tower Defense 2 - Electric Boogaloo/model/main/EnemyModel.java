package main;

/**
 * The super class for all enemy types. It contains the attributes 
 * that all enemies should have and inherits from UnitModel. This will control the 
 * path movement of the enemies that are on the screen. 
 * 
 * @author Ryan Barbera, Aaron George, Nick Norton, Thomas Pennington, Grant Zhao
 * 
 */

public abstract class EnemyModel extends UnitModel {

	int maxHealth;
	int currentHealth;
	int bounty;
	int speed;
	int scoreValue;
	int positionIndex;
	int xDirection;
	int yDirection;
	
	/**
	 * Moves this enemy toward its current direction, and returns true if this enemy reaches the given destination
	 * and false otherwise. If the corner of the image has not reached the destination, then it keeps moving in the 
	 * current direction and will add either x or y values.
	 * 
	 * @param destination the x coordinate of the next destination direction 
	 * @return true or false depending if the enemy has reached its destination 
	 */
	public boolean moveToward(Posn destination) {
		if(xDirection != 0) {															  //If this enemy is moving in the x direction,
			int nextXCor = posn.getXCor() + (xDirection * speed);                         //Configure data on next perceived x location based on speed,
			boolean reachLeftDest = xDirection < 0 && nextXCor <= destination.getXCor();  //Whether the next path location is reached to the left,
			boolean reachRightDest = xDirection > 0 && nextXCor >= destination.getXCor(); //And whether the next path location is reached on the right.
			
			if(reachLeftDest || reachRightDest) {                                         //If the next path location is reached, 
				posn.setXCor(destination.getXCor());                                      //Set this enemy's position to the next path location,
				positionIndex++;														  //Increment its position along the path,
				return true;                                                              //And report the destination was reached.
			}
			else{
				posn.setXCor(nextXCor);                                                   //Otherwise move this enemy to the next x location based on speed,
				return false;                                                             //And report the destination was not reached.
			}
		}
		if(yDirection != 0) {															  //If this enemy is moving in the y direction,
			int nextYCor = posn.getYCor() + (yDirection * speed);							  //Do the same for finding the next y location.
			boolean reachLeftDest = yDirection < 0 && nextYCor <= destination.getYCor();
			boolean reachRightDest = yDirection > 0 && nextYCor >= destination.getYCor();
			if(reachLeftDest || reachRightDest) {
				posn.setYCor(destination.getYCor());
				positionIndex++;
				return true;
			}
			else{
				posn.setYCor(nextYCor);
				return false;
			}
		}
		return false;
	}
	
	/** 
	 * Sets this enemy's direction(s) of motion based on the given destination from the posns
	 * that were created. The directions that a particular path could be taking is north, south, 
	 * east, and west. It increments one of the directions based on the x and y positions of each
	 * enemy that is one the board.
	 * 
	 * @param destination the current x, y position on the game map that the enemies are traveling 
	 * 					  on
	 */
	public void updateDirection(Posn destination) {
		int xDifference = destination.getXCor() - posn.getXCor();	//Calculate the distance and direction to the destination in each dimension
		int yDifference = destination.getYCor() - posn.getYCor();
		if(xDifference > 0 && yDifference > 0) {
			xDirection = 1;
			yDirection = 1;
		} else if (xDifference > 0 && yDifference < 0) {
			xDirection = 1;
			yDirection = -1;
		} else if (xDifference < 0 && yDifference < 0) {
			xDirection = -1;
			yDirection = -1;
		} else if (xDifference < 0 && yDifference > 0) {
			xDirection = -1;
			yDirection = 1;
		} else if (xDifference == 0 && yDifference < 0) {
			xDirection = 0;
			yDirection = -1;
		} else if (xDifference == 0 && yDifference > 0) {
			xDirection = 0;
			yDirection = 1;
		} else if (xDifference < 0 && yDifference == 0) {
			xDirection = -1;
			yDirection = 0;
		} else {
			xDirection = 1;
			yDirection = 0;
		}
	}
	
	public int getBounty() {
		return bounty;
	}
	public void setBounty(int bounty) {
		this.bounty = bounty;
	}
	public int getMaxHealth() {
		return maxHealth;
	}
	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}
	public int getCurrentHealth() {
		return currentHealth;
	}
	public void setCurrentHealth(int currentHealth) {
		this.currentHealth = currentHealth;
	}
	
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getScoreValue() {
		return scoreValue;
	}
	public void setScoreValue(int scoreValue) {
		this.scoreValue = scoreValue;
	}
	
	public int getPositionIndex() {
		return positionIndex;
	}
	public void setPositionIndex(int positionIndex) {
		this.positionIndex = positionIndex;
	}
	
	public int getXDirection() {
		return xDirection;
	}
	public void setXDirection(int xDirection) {
		this.xDirection = xDirection;
	}
	
	public int getYDirection() {
		return yDirection;
	}
	public void setYDirection(int yDirection) {
		this.yDirection = yDirection;
	}
	
}
