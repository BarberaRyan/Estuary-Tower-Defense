package towerTypes;

import main.TowerModel;

/**
 * Makes a TowerSanderling as one of the Towers in the game that is esturary related. It sets
 * all positions needed for the game for movement and heath along with a fun fact to make 
 * it educational. There are more attributes that are defined in the extended class to give 
 * a full range of information. 
 * 
 * @author Ryan Barbera, Aaron George, Nick Norton, Thomas Pennington, Grant Zhao
 *
 */
public class TowerSanderling extends TowerModel{

	/**
	 * Constructor that makes a TowerSanderling object and sets the necessary parameters up
	 * to work with the game. This will be used in the game later to control all aspects about 
	 * what a TowerSanderling is able to do.
	 */
	public TowerSanderling(){
		setName("Sanderling");
		setFactDescription("Small plump birds that feed by sticking their bills deep in the sand, ");
		setGameDescription("Look out for his attack on small fish!");
		setReloadCount(0);
		setReloadTime(3);
		setDamage(30);
		setPrice(110);
		setRange(445);
		setRangeMultiplier(.7);
		setUpgrade(null);
	}
}
