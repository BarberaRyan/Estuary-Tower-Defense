package towerTypes;

import main.TowerModel;

/**
 * Makes a TowerBlueFish as one of the Towers in the game that is esturary related. It sets
 * all positions needed for the game for movement and heath along with a fun fact to make 
 * it educational. There are more attributes that are defined in the extended class to give 
 * a full range of information. 
 * 
 * @author Ryan Barbera, Aaron George, Nick Norton, Thomas Pennington, Grant Zhao
 *
 */
public class TowerBlueFish extends TowerModel{

	/**
	 * Constructor that makes a TowerBlueFish object and sets the necessary parameters up
	 * to work with the game. This will be used in the game later to control all aspects about 
	 * what a TowerBlueFish is able to do.
	 */
	public TowerBlueFish(){
		setName("Blue Fish");
		setFactDescription("Fierce fish with sharp teeth for feeding, he gets a mean reputation");
		setGameDescription("Look out for his attack on small fish!");
		setReloadCount(0);
		setReloadTime(3);
		setDamage(45);
		setPrice(90);
		setRange(350);
		setRangeMultiplier(.55);
		setUpgrade(null);
	}
}
