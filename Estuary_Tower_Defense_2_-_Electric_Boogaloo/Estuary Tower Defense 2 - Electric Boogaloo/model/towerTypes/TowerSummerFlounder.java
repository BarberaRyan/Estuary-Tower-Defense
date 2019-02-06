package towerTypes;

import main.TowerModel;


/**
 * Makes a TowerSummerFlounder as one of the Towers in the game that is esturary related. It sets
 * all positions needed for the game for movement and heath along with a fun fact to make 
 * it educational. There are more attributes that are defined in the extended class to give 
 * a full range of information. 
 * 
 * @author Ryan Barbera, Aaron George, Nick Norton, Thomas Pennington, Grant Zhao
 *
 */
public class TowerSummerFlounder extends TowerModel{

	/**
	 * Constructor that makes a TowerSummerFlounder object and sets the necessary parameters up
	 * to work with the game. This will be used in the game later to control all aspects about 
	 * what a TowerSummerFlounder is able to do.
	 */
	public TowerSummerFlounder(){
		setName("Summer Flounder");
		setFactDescription("Left side flatfish that has both of it's eyes on the left side of it's head, it hides low");
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
