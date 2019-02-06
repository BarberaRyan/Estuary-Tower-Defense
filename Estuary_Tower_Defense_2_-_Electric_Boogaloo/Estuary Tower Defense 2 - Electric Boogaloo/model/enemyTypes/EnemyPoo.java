package enemyTypes;

import main.EnemyModel;

/**
 * Creates one of the enemies in the game poo. Values are set for the different 
 * setters that the enemy algae has.
 * 
 * @author Ryan Barbera, Aaron George, Nick Norton, Thomas Pennington, Grant Zhao
 *
 */
public class EnemyPoo extends EnemyModel {

	/**
	 * Constructor that makes an EnemyPoo instance that sets the values of different 
	 * parameters for this object.
	 */
	public EnemyPoo(){
		setName("EnemyPoo");
		setBounty(10);
		setMaxHealth(150);
		setCurrentHealth(150);
		setSpeed(20);
		setScoreValue(10);
		setPositionIndex(0);
		setXDirection(0);
		setYDirection(1);
	}
}
