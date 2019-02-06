package main;

import java.util.ArrayList;
import java.util.Random;

import enemyTypes.*;
import towerTypes.*;

/**
 * Contains the logic of the game used to update information during the rounds. This 
 * is able to track the movement of objects that are on the screen. Also, this is where 
 * the Towers check to see if they are in range to shoot at the enemies on the game board. 
 * 
 * @author Ryan Barbera, Aaron George, Nick Norton, Thomas Pennington, Grant Zhao
 *
 */

public class GameModel {

	private ArrayList<EnemyModel> enemyList;
	private ArrayList<TowerModel> towerList;
	private ArrayList<EnemyModel> enemyModelList;
	private ArrayList<TowerModel> towerModelList;
	private PathModel path;
	private Factory factory;
	private PlayerModel player;

	/**
	 * Creates an GameModel object and instantiates arrayLists that will keep track of 
	 * enemies and towers when the are needed in the game.
	 */
	public GameModel() {
		enemyList = new ArrayList<EnemyModel>();
		towerList = new ArrayList<TowerModel>();
		enemyModelList = new ArrayList<EnemyModel>(7);
		towerModelList = new ArrayList<TowerModel>(12);
		fillTowerModelList();
		fillEnenmyModelList();
		path = new PathModel();
		factory = new Factory();
		player = new PlayerModel();		
	}
	
	/**
	 * fills the enemyModelArrayList with every enemy that is able to be made in the game
	 */
	private void fillEnenmyModelList() {
		for(int counter = 0; counter < 6; counter++) {
			switch(counter) {
				case 0:
					enemyModelList.add(new EnemyPoo());
					break;
				case 1: 
					enemyModelList.add(new EnemyLitter());
					break;
				case 2:
					enemyModelList.add(new EnemyPathogens());
					break;
				case 3:
					enemyModelList.add(new EnemyChemical());
					break;
				case 4:
					enemyModelList.add(new EnemyAlgae());
					break;
				case 5:
					enemyModelList.add(new EnemyIce());
					break;
				default:
					break;	
			}
		}	
	}

	/**
	 * Fills the towerModelArrayList with every tower that is able to be made in the game.
	 */
	private void fillTowerModelList() {
		for (int counter = 0; counter < 12; counter++) {
			switch (counter) {
			case 0:
				towerModelList.add(new TowerGenericShellfish());
				break;
			case 1:
				towerModelList.add(new TowerEasternOyster());
				break;
			case 2:
				towerModelList.add(new TowerBlueCrab());
				break;
			case 3:
				towerModelList.add(new TowerHorseshoeCrab());
				break;
			case 8:
				towerModelList.add(new TowerGenericBird());
				break;
			case 9:
				towerModelList.add(new TowerRuddyTurnstone());
				break;
			case 10:
				towerModelList.add(new TowerSanderling());
				break;
			case 11:
				towerModelList.add(new TowerOsprey());
				break;
			case 4:
				towerModelList.add(new TowerGenericFish());
				break;
			case 5:
				towerModelList.add(new TowerRiverHerring());
				break;
			case 6:
				towerModelList.add(new TowerBlueFish());
				break;
			case 7:
				towerModelList.add(new TowerSummerFlounder());
				break;
			default:
				break;
			}
		}
	}

	/**
	 * Checks to see if the tower in the arrayList is reloading when firing at an enemy on the 
	 * game board. It will reduce the reload count by 1 if it has not reached zero at which point 
	 * it is able to fire at the enemy when it gets to zero. 
	 * 
	 * @param towerInd the index of the tower to check the reload count from 
	 * @return true if the reload count is 0 for firing or false to reduce the reload count by 1
	 */
	public boolean isReloading(int towerInd) {
		if (towerList.get(towerInd).getReloadCount() == 0)
			return false;
		else {
			towerList.get(towerInd).setReloadCount(towerList.get(towerInd).getReloadCount() - 1);
			return true;
		}
	}

	/**
	 * Resets the reload time of the tower that is in the tower arrayList. 
	 * 
	 * @param towerInd the index of the Tower that you want to reset the reload time for
	 */
	public void resetReload(int towerInd) {
		towerList.get(towerInd).setReloadCount(towerList.get(towerInd).getReloadTime());
	}

	/**
	 * Checks the range of the tower versus the enemy, if it is range it returns true
	 * If it is not in range it returns false. This will determine if an enemy is able to
	 * shoot towards the enemy during the game.
	 *
	 * @param towerInd the tower that you need to get the positions from 
	 * @param em the enemyModel that you need to get the position from 
	 * @return true if the Tower is in range of the of the enemy or false if the Tower is not in the range of the enemy 
	 */
	public boolean isInRange(int towerInd, EnemyModel em) {
		int xDis = towerList.get(towerInd).getPosn().getXCor() - em.getPosn().getXCor();
		int yDis = towerList.get(towerInd).getPosn().getYCor() - em.getPosn().getYCor();
		double Dis = Math.sqrt(Math.pow(xDis, 2) + Math.pow(yDis, 2));

		if (Dis <= towerList.get(towerInd).getRange())
			return true;
		else
			return false;
	}

	/**
	 * Adds damage to the current object that is on the game given a Tower index from the arrayList of 
	 * Towers and an EnemyModel.
	 * 
	 * @param towerInd the index of the tower in the arrayList to access the total damage that Tower has
	 * @param em the enemyModel that you need to get the current health of 
	 * @return true if the current health is 0 or false if the health is not equal to 0
	 */
	public boolean damageEnemy(int towerInd, EnemyModel em) {
		em.setCurrentHealth(em.getCurrentHealth() - towerList.get(towerInd).getDamage());

		if (0 >= em.getCurrentHealth())
			return true;
		else
			return false;
	}

	
	/**
	 * Moves the enemy at the given index in this game's enemyList toward the next destination in the path,
	 * And, if the enemy reaches the destination, returns true if the enemy falls out of bounds,
	 * And returns false, while updating its direction, if it does not
	 * 
	 * @param enemyUnit an EnemyModel that is able to be spawned in the game
	 */
	public void moveEnemy(EnemyModel enemyUnit) {
        Posn destination = path.getPosnAtIndex(enemyUnit.getPositionIndex() + 1);      
        if(enemyUnit.moveToward(destination)) {                                        
        }
        else {
        	 destination = path.getPosnAtIndex(enemyUnit.getPositionIndex() + 1);
        	 enemyUnit.updateDirection(destination);
        }
    }
	
	/**
	 * Gets the position index of an EnemyModel and returns true if the enemy at the given 
	 * index in this game's enemyList has reached the end of the path, or returns false otherwise
	 * 
	 * @param enemyUnit an enemy unit that is able to be spawned
	 * @return true if the enemy is out of bounds or false value if the enemy is not out of bounds 
	 */
	public boolean isOutOfBounds(EnemyModel enemyUnit) {
		if (enemyUnit.getPositionIndex() >= path.getSize())
			return true;
		else
			return false;
	}

	/**
	 * Randomly spawns an enemy and places the enemy at the top opening of the path. 
	 * Then it is added into the arrayList of enemies and returns the EnemyModel that it 
	 * created.
	 * 
	 * @return an EmemyModel that was spawned randomly
	 */
	public EnemyModel spawnEnemy() {
		Random rn = new Random();
		int rand1 = rn.nextInt((5 - 0) + 1) + 0;
		
		EnemyModel tmpEnemy = factory.makeIntEnemy(rand1);
		tmpEnemy.setPosn(path.getPosnAtIndex(0));
		enemyList.add(tmpEnemy);
		
		return tmpEnemy;
	}

	/**
	 * Reduces the players health when an enemy makes it out of the Estuary by 
	 * making it to the end of the path. The view will be updated accordingly when 
	 * this takes place in the game. 
	 * 
	 * @param i an integer that reduces the players health 
	 */
	public void reducePlayerHealth(int i) {
		player.setHealth(player.getHealth() - i);
	}

	/**
	 * Reduces the players money when a Tower is purchased in the game. This 
	 * will be updated in the view when the player's money goes down. 
	 * 
	 * @param i an integer that will decrease the players money by set amount
	 */
	public void reducePlayerMoney(int i) {
		player.setMoney(player.getMoney() - i);
	}

	/**
	 * Increases the players money by a given amount during game play when the Towers 
	 * are attacking the enemies. The view will be updated by this change when there 
	 * is an increase in the player's money. 
	 * 
	 * @param i an integer that increases the players money 
	 */
	public void increasePlayerMoney(int i) {
		player.setMoney(player.getMoney() + i);
	}

	/**
	 * Increases the player's money by the enemy bounty when an enemy is killed by the 
	 * Tower. The view will be updated to reflect this change.
	 * 
	 * @param enemyInd the index of the enemy list to access the bounty of a particular enemy
	 */
	public void gainBounty(int enemyInd) {
		increasePlayerMoney(enemyList.get(enemyInd).getBounty());
	}

	/**
	 * Checks to see if the player is dead when enemies get past the estuary at the end 
	 * of the path. If the players health is above 0 then it returns false and true if the 
	 * health is 0.
	 * 
	 * @return true or false depending if the players health has reached 0
	 */
	public boolean isPlayerDead() {
		if (player.getHealth() > 0) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Increases the players score when Tower attacks an enemy on the game board. The players score 
	 * will be updated in the view accordingly when the score increases. 
	 * 
	 * @param inc the amount that the score increases 
	 */
	public void increaseScore(int inc) {
		player.setScore(player.getScore() + inc);
	}

	public PathModel getPath() {
		return path;
	}
	public Factory getFactory() {
		return factory;
	}

	public PlayerModel getPlayer() {
		return player;
	}
	
	/**
	 * Returns the tower model given an index to locate a particular tower that 
	 * has been created. 
	 * 
	 * @param towerIndex the index of the Tower that is needed from the arrayList
	 * @return the tower model at the specific index that is requested. 
	 */
	public TowerModel getIndexedTowerModel(int towerIndex) {
		return towerModelList.get(towerIndex);
	}
	
	public ArrayList<TowerModel> getTowerList() {
		return towerList;
	}
	
	public ArrayList<EnemyModel> getEnemyList() {
		return enemyList;
	}
	
	public void setTowerList(ArrayList<TowerModel> iterTowerList){
		towerList = iterTowerList;
		//Used for testing purposes
	}

	public void setEnemyList(ArrayList<EnemyModel> iterEnemyList) {
		enemyList = iterEnemyList;
	}

	public void setPath(ArrayList<Posn> posnArr, int size) {
		PathModel newPath = new PathModel();
		newPath.setPosnArr(posnArr);
		newPath.setSize(size);
		path = newPath;
	}
	
	public void setPlayer(PlayerModel player){
		this.player = player;
	}
}