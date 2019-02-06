package towerTypes;

import main.TowerModel;

/**
 * Makes a TowerRuddyTurnstone as one of the Towers in the game that is esturary related. It sets
 * all positions needed for the game for movement and heath along with a fun fact to make 
 * it educational. There are more attributes that are defined in the extended class to give 
 * a full range of information. 
 * 
 * @author Ryan Barbera, Aaron George, Nick Norton, Thomas Pennington, Grant Zhao
 *
 */
public class TowerRuddyTurnstone extends TowerModel{

	/**
	 * Constructor that makes a TowerRuddyTurnstone object and sets the necessary parameters up
	 * to work with the game. This will be used in the game later to control all aspects about 
	 * what a TowerRuddyTurnstone is able to do.
	 */
	public TowerRuddyTurnstone(){
		setName("Ruddy Turnstone");
		setFactDescription("Calico Colored shorebird that looks for food by flipping rocks and pebbles. They migrate in groups of 10 to over 1000");
		setGameDescription("Look out for his attack on small fish!");
		setReloadCount(0);
		setReloadTime(3);
		setDamage(30);
		setPrice(110);
		setRange(17);
		setRangeMultiplier(414);
		setUpgrade(null);
	}
}
