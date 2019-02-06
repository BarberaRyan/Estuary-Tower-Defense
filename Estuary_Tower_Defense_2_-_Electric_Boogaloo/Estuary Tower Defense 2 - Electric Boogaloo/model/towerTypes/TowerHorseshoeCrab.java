package towerTypes;

import main.TowerModel;

/**
 * Makes a TowerHorseshoeCrab as one of the Towers in the game that is esturary related. It sets
 * all positions needed for the game for movement and heath along with a fun fact to make 
 * it educational. There are more attributes that are defined in the extended class to give 
 * a full range of information. 
 * 
 * @author Ryan Barbera, Aaron George, Nick Norton, Thomas Pennington, Grant Zhao
 *
 */
public class TowerHorseshoeCrab extends TowerModel{

	/**
	 * Constructor that makes a TowerHorseshoeCrab object and sets the necessary parameters up
	 * to work with the game. This will be used in the game later to control all aspects about 
	 * what a TowerHorseshoeCrab is able to do.
	 */
	public TowerHorseshoeCrab(){
		setName("Horseshoe Crab");
		setFactDescription("Survivalist Crab! Can go a year without eating and endure extreme environmental condiditions like heat and salinity");
		setGameDescription("Look out for his attack on small fish!");
		setReloadCount(0);
		setReloadTime(2);
		setDamage(50);
		setPrice(100);
		setRange(267);
		setRangeMultiplier(.42);
		setUpgrade(null);
	
	}
}

