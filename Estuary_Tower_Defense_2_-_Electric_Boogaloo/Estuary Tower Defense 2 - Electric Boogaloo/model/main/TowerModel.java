package main;

/**
 * Houses the attributes for the Towers that are created. This is how the enemies will know 
 * when to shoot after a delay that takes place, damage that they have on the enemies,
 * ugrades that a possible tower is able to have once the base unit is purchased,and 
 * the range multiplier that the tower is able to shoot from. 
 * 
 * @author Ryan Barbera, Aaron George, Nick Norton, Thomas Pennington, Grant Zhao
 *
 */

public abstract class TowerModel extends UnitModel{
	int range;
	double rangeMultiplier;
	int damage;
	int price;
	
	//TODO Turrets should have attack methods at this level
	
	//HOW THIS WORKS reloadTime is how long it takes for something to fire
	//reloadCount is the current reload counter, every time tick it is reduced by 1
	//When reloadCount is 0, the turret can fire again
	int reloadTime;
	int reloadCount;
	
	//The possible turrets it can upgrade into
	String[] upgrade;
	
	
	//TODO TARGET ACQUISTION
	//Return an array of the indexes of all enemies it'll damage
	//If it returns null nothing is in range
	//Takes as in input an array of all enemy xPositions, and all enemy yPostions
	
	//public abstract int[] targetAcquisition(int[] enemyXPos, int[] enemyYPos );
	
	
	public int getRange() {
		return range;
	}
	public void setRange(int range) {
		this.range = range;
	}
	public double getRangeMultiplier() {
		return rangeMultiplier;
	}
	public void setRangeMultiplier(double rangeMultiplier) {
		this.rangeMultiplier = rangeMultiplier;
	}
	public int getDamage() {
		return damage;
	}
	public void setDamage(int damage) {
		this.damage = damage;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getReloadTime() {
		return reloadTime;
	}
	public void setReloadTime(int reloadTime) {
		this.reloadTime = reloadTime;
	}
	public int getReloadCount() {
		return reloadCount;
	}
	public void setReloadCount(int reloadCount) {
		this.reloadCount = reloadCount;
	}
	public String[] getUpgrade() {
		return upgrade;
	}
	public void setUpgrade(String[] upgrade) {
		this.upgrade = upgrade;
	}
}