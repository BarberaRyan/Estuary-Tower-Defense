package towerTypes;

import main.TowerModel;

/**
 * Makes a TowerGenericFish as one of the Towers in the game that is esturary related. It sets
 * all positions needed for the game for movement and heath along with a fun fact to make 
 * it educational. There are more attributes that are defined in the extended class to give 
 * a full range of information. 
 * 
 * @author Ryan Barbera, Aaron George, Nick Norton, Thomas Pennington, Grant Zhao
 *
 */
public class TowerGenericFish extends TowerModel{

	/**
	 * Constructor that makes a TowerGenericFish object and sets the necessary parameters up
	 * to work with the game. This will be used in the game later to control all aspects about 
	 * what a TowerGenericFish is able to do.
	 */
	public TowerGenericFish(){
		setName("Generic Fish");
		setFactDescription("White Perch are found in numerous counts in Delaware Estuaries");
		setGameDescription("Look out for me swimming in the Delaware Bay!");
		setReloadCount(0);
		setReloadTime(3);
		setDamage(45);
		setPrice(150);
		setRange(318);
		setRangeMultiplier(.5);
		setUpgrade(null);
	}
}
