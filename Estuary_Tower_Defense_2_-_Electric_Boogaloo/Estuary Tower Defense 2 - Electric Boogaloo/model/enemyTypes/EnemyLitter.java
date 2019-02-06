package enemyTypes;

import main.EnemyModel;

/**
 * Creates one of the enemies in the game litter. Values are set for the different 
 * setters that the enemy algae has.
 * 
 * @author Ryan Barbera, Aaron George, Nick Norton, Thomas Pennington, Grant Zhao
 *
 */
public class EnemyLitter extends EnemyModel {
	
	/**
	 * Constructor that makes an EnemyLitter instance that sets the values of different 
	 * parameters for this object.
	 */
	public EnemyLitter(){
		setName("EnemyLitter");
		setBounty(15);
		setMaxHealth(175);
		setCurrentHealth(175);
		setSpeed(15);
		setScoreValue(15);
		setPositionIndex(0);
		setXDirection(0);
		setYDirection(1);
	}
}
