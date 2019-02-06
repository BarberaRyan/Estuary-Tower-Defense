package towerTypes;

import main.TowerModel;

/**
 * Makes a TowerBlueCrab as one of the Towers in the game that is esturary related. It sets
 * all positions needed for the game for movement and heath along with a fun fact to make 
 * it educational. There are more attributes that are defined in the extended class to give 
 * a full range of information. 
 * 
 * @author Ryan Barbera, Aaron George, Nick Norton, Thomas Pennington, Grant Zhao
 *
 */

public class TowerBlueCrab extends TowerModel{

	/**
	 * Constructor that makes a TowerBlueCrab object and sets the necessary parameters up
	 * to work with the game. This will be used in the game later to control all aspects about 
	 * what a TowerBlueCrab is able to do.
	 */
	public TowerBlueCrab(){
		setName("Blue Crab");
		setFactDescription("It's scientific name translates to 'Savory Beautiful Swimmer'");
		setGameDescription("Look out for his attack on small fish!");
		setReloadCount(0);
		setReloadTime(2);
		setDamage(50);
		setPrice(100);
		setRange(255);
		setRangeMultiplier(.4);
		setUpgrade(null);
	}
}
