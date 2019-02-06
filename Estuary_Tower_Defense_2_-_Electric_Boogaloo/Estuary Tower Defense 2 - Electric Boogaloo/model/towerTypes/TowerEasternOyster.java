package towerTypes;

import main.TowerModel;
/**
 * Makes a TowerEasternOyster as one of the Towers in the game that is esturary related. It sets
 * all positions needed for the game for movement and heath along with a fun fact to make 
 * it educational. There are more attributes that are defined in the extended class to give 
 * a full range of information. 
 * 
 * @author Ryan Barbera, Aaron George, Nick Norton, Thomas Pennington, Grant Zhao
 *
 */
public class TowerEasternOyster extends TowerModel{

	/**
	 * Constructor that makes a TowerEasternOyster object and sets the necessary parameters up
	 * to work with the game. This will be used in the game later to control all aspects about 
	 * what a TowerEasternOyster is able to do.
	 */
	public TowerEasternOyster(){
		setName("Eastern Oyster");
		setFactDescription("A natural sponge! They absorb pollutants from their surrondings");
		setGameDescription("Look out for his attack on small fish!");
		setReloadCount(0);
		setReloadTime(2);
		setDamage(50);
		setPrice(100);
		setRange(191);
		setRangeMultiplier(.3);
		setUpgrade(null);
	
	}
}
