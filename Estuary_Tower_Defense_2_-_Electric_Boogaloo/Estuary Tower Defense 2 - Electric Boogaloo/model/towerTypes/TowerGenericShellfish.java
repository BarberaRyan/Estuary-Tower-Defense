package towerTypes;

import main.TowerModel;

/**
 * Makes a TowerGenericShellFish as one of the Towers in the game that is esturary related. It sets
 * all positions needed for the game for movement and heath along with a fun fact to make 
 * it educational. There are more attributes that are defined in the extended class to give 
 * a full range of information. 
 * 
 * @author Ryan Barbera, Aaron George, Nick Norton, Thomas Pennington, Grant Zhao
 *
 */
public class TowerGenericShellfish extends TowerModel {
	
	/**
	 * Constructor that makes a TowerGenericShellFish object and sets the necessary parameters up
	 * to work with the game. This will be used in the game later to control all aspects about 
	 * what a TowerGenericShellFish is able to do.
	 */
	public TowerGenericShellfish() {
		setName("Generic Shellfish");
		setFactDescription("Shellfish are indigenous to the region");
		setGameDescription("Look out for his big meaty claws!");
		setReloadCount(0);
		setReloadTime(2);
		setDamage(50);
		setPrice(100);
		setRange(223);
		setRangeMultiplier(.35);
		setUpgrade(null);
	}
}
