package towerTypes;

import main.TowerModel;

/**
 * Makes a TowerGenericBird as one of the Towers in the game that is esturary related. It sets
 * all positions needed for the game for movement and heath along with a fun fact to make 
 * it educational. There are more attributes that are defined in the extended class to give 
 * a full range of information. 
 * 
 * @author Ryan Barbera, Aaron George, Nick Norton, Thomas Pennington, Grant Zhao
 *
 */
public class TowerGenericBird extends TowerModel{

	/**
	 * Constructor that makes a TowerGenericBird object and sets the necessary parameters up
	 * to work with the game. This will be used in the game later to control all aspects about 
	 * what a TowerGenericBird is able to do.
	 */
	public TowerGenericBird(){
		setName("Generic Bird");
		setFactDescription("Birds eat the small fish in the estuaries");
		setGameDescription("Look out for his attack on small fish!");
		setReloadCount(0);
		setReloadTime(3);
		setDamage(30);
		setPrice(110);
		setRange(477);
		setRangeMultiplier(.75);
		setUpgrade(null);
	}
}
