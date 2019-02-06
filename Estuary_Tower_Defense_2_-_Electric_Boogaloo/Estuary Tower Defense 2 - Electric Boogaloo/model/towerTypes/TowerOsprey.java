package towerTypes;

import main.TowerModel;


/**
 * Makes a TowerOsprey as one of the Towers in the game that is esturary related. It sets
 * all positions needed for the game for movement and heath along with a fun fact to make 
 * it educational. There are more attributes that are defined in the extended class to give 
 * a full range of information. 
 * 
 * @author Ryan Barbera, Aaron George, Nick Norton, Thomas Pennington, Grant Zhao
 *
 */
public class TowerOsprey extends TowerModel{

	/**
	 * Constructor that makes a TowerOsprey object and sets the necessary parameters up
	 * to work with the game. This will be used in the game later to control all aspects about 
	 * what a TowerOsprey is able to do.
	 */
	public TowerOsprey(){
		setName("Osprey");
		setFactDescription("Slender sea hawk that eats fish and nests around saltmarshes, river, and ponds. They have no teeth!");
		setGameDescription("Look out for his attack on small fish!");
		setReloadCount(0);
		setReloadTime(3);
		setDamage(30);
		setPrice(110);
		setRange(636);
		setRangeMultiplier(1);
		setUpgrade(null);
	}
}
