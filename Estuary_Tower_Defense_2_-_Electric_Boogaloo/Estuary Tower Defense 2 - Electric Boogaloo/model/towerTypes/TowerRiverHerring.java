package towerTypes;

import main.TowerModel;

/**
 * Makes a TowerRiverHerring as one of the Towers in the game that is esturary related. It sets
 * all positions needed for the game for movement and heath along with a fun fact to make 
 * it educational. There are more attributes that are defined in the extended class to give 
 * a full range of information. 
 * 
 * @author Ryan Barbera, Aaron George, Nick Norton, Thomas Pennington, Grant Zhao
 *
 */
public class TowerRiverHerring extends TowerModel{
	/**
	 * Constructor that makes a TowerRiverHerring object and sets the necessary parameters up
	 * to work with the game. This will be used in the game later to control all aspects about 
	 * what a TowerRiverHerring is able to do.
	 */
	public TowerRiverHerring(){
		setName("River Herring");
		setFactDescription("Iridescent fish with shades of Green and Violet, it's lucky to be here, less than 1 percent survive the journey to salt water!");
		setGameDescription("Look out for his attack on small fish!");
		setReloadCount(0);
		setReloadTime(3);
		setDamage(45);
		setPrice(90);
		setRange(286);
		setRangeMultiplier(.45);
		setUpgrade(null);
	}
}
