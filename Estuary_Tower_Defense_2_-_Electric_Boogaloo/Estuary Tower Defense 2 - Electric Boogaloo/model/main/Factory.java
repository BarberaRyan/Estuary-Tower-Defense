package main;

import enemyTypes.*;
import towerTypes.*;

/**
 * A utilization of the factory design pattern to generate gameplay objects
 * such as turrets and enemies.
 * 
 * @author Ryan Barbera, Aaron George, Nick Norton, Thomas Pennington, Grant Zhao
 *
 */
public class Factory {
	/**
	 * Returns an instance of an enemy corresponding to the passed in string. This object
	 * will be placed in an ArrayList of enemies to be interacted with in the model logic.
	 * @param	enemyInt	a int describing the enemy to be generated
	 * @return	EnemyModel	an instance of the enemy corresponding to the passed in string 
	 */
	public EnemyModel makeIntEnemy(int enemyInt) {
		EnemyModel newEnemy = null;
		
		switch(enemyInt) {
			case 0:
				newEnemy = new EnemyPoo();
				break;
			case 1:
				newEnemy = new EnemyLitter();
				break;
			case 2:
				newEnemy = new EnemyPathogens();
				break;
			case 3:
				newEnemy = new EnemyChemical();
				break;
			case 4:
				newEnemy = new EnemyAlgae();
				break;
			case 5:
				newEnemy = new EnemyIce();
				break;
			default:
				break;
		}
		
		return newEnemy;
	}
	
	/**
	 * Returns an instance of a tower corresponding to the passed in string. This object
	 * will be placed in an ArrayList of towers to be interacted with in the model logic.
	 * @param	towerName	a string describing the tower to be generated
	 * @return	TowreModel	an instance of the tower corresponding to the passed in string
	 */
	public TowerModel makeStringTower(String towerName) {
		TowerModel newTower = null;
		
		switch(towerName) {
			case "Generic Shellfish" :
				newTower = new TowerGenericShellfish();
				break;
			case "Eastern Oyster" :
				newTower = new TowerEasternOyster();
				break;
			case "Blue Crab" :
				newTower = new TowerBlueCrab();
				break;
			case "Horseshoe Crab" :
				newTower = new TowerHorseshoeCrab();
				break;
			case "Generic Fish" :
				newTower = new TowerGenericFish();
				break;
			case "River Herring" :
				newTower = new TowerRiverHerring();
				break;
			case "Blue Fish" :
				newTower = new TowerBlueFish();
				break;
			case "Summer Flounder" :
				newTower = new TowerSummerFlounder();
				break;
			case "Generic Bird" :
				newTower = new TowerGenericBird();
				break;
			case "Ruddy Turnstone" :
				newTower = new TowerRuddyTurnstone();
				break;
			case "Sanderling" :
				newTower = new TowerSanderling();
				break;
			case "Osprey" :
				newTower = new TowerOsprey();
				break;
			default:
				break;
		}
		return newTower;
	}

	/**
	 * Returns an instance of a tower corresponding to the passed in int. This object
	 * will be placed in an ArrayList of towers to be interacted with in the model logic.
	 * @param	i	a int describing the tower to be generated
	 * @return	TowreModel	an instance of the tower corresponding to the passed in string
	 */
	public TowerModel makeIntTower(int i) {
		TowerModel newTower = null;
		
		switch(i) {
			case 0 :
				newTower = new TowerGenericShellfish();
				break;
			case 1 :
				newTower = new TowerEasternOyster();
				break;
			case 2 :
				newTower = new TowerBlueCrab();
				break;
			case 3 :
				newTower = new TowerHorseshoeCrab();
				break;
			case 4 :
				newTower = new TowerGenericFish();
				break;
			case 5 :
				newTower = new TowerRiverHerring();
				break;
			case 6 :
				newTower = new TowerBlueFish();
				break;
			case 7 :
				newTower = new TowerSummerFlounder();
				break;
			case 8 :
				newTower = new TowerGenericBird();
				break;
			case 9 :
				newTower = new TowerRuddyTurnstone();
				break;
			case 10:
				newTower = new TowerSanderling();
				break;
			case 11 :
				newTower = new TowerOsprey();
				break;
			default:
				break;
		}
		return newTower;
	}
}
