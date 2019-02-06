package enemyTypes;

import main.EnemyModel;

/**
 * Creates one of the enemies in the game pathogen. Values are set for the different 
 * setters that the enemy algae has.
 * 
 * @author Ryan Barbera, Aaron George, Nick Norton, Thomas Pennington, Grant Zhao
 *
 */
public class EnemyPathogens extends EnemyModel{

	/**
	 * Constructor that makes an EnemyPathogen instance that sets the values of different 
	 * parameters for this object.
	 */
	public EnemyPathogens(){
		setName("EnemyPathogen");
		setBounty(15);
		setMaxHealth(175);
		setCurrentHealth(175);
		setSpeed(30);
		setScoreValue(15);
		setPositionIndex(0);
		setXDirection(0);
		setYDirection(1);
	}
}
