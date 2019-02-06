package enemyTypes;

import main.EnemyModel;

/**
 * Creates one of the enemies in the game ice. Values are set for the different 
 * setters that the enemy algae has.
 * 
 * @author Ryan Barbera, Aaron George, Nick Norton, Thomas Pennington, Grant Zhao
 *
 */
public class EnemyIce extends EnemyModel {
	
	/**
	 * Constructor that makes an EnemyIce instance that sets the values of different 
	 * parameters for this object.
	 */
	public EnemyIce(){
		setName("EnemyIce");
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
