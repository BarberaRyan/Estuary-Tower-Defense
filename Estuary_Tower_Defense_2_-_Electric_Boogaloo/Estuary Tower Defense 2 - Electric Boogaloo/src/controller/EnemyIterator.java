package controller;

import java.util.ArrayList;
import java.util.Iterator;

import main.EnemyModel;

/**
 * A custom iterator class to iterate through all enemies on the game path and 
 * adds and removes them accordingly.
 * 
 * @author Ryan Barbera, Aaron George, Nick Norton, Thomas Pennington, Grant Zhao
 *
 */

public class EnemyIterator implements Iterator <EnemyModel> {

	private ArrayList<EnemyModel> enemyList;
	private int size;
	private int index;
	private EnemyModel currentEnemy;
	
	/**
	 * Creates the EnemyIterator and initializes the enemy arrayList and size to be used 
	 * with the implemented methods below. 
	 * 
	 * @param enemyList the arrayList of enemies that are on the game path 
	 */
	public EnemyIterator(ArrayList<EnemyModel> enemyList) {
		this.enemyList = enemyList;
		this.size = enemyList.size();
		index = -1;
	}
	
	/**
	 * Returns true or false while the iterator is iterating over the entire arrayList of 
	 * enemies to determine if there are more elements. If there are more enemies in the 
	 * list then it returns true, else false if there are no more enemies in the list. 
	 * 
	 * @return true or false depending if the enemy arrayList has more elements in it. 
	 */
	@Override
	public boolean hasNext() {
		if(index < size - 1)
			return true;
		else
			return false;
	}

	/**
	 * Returns the next element in the arrayList of enemy models and increments the index 
	 * of the arrayList to the next enemy model. 
	 * 
	 * @return the enemy model for each enemy object that was placed into the arrayList 
	 */
	@Override
	public EnemyModel next() {
		currentEnemy = enemyList.get(index + 1);
		index++;
		return currentEnemy;
	}
	
	/**
	 * Removes an enemy model from the arrayList of enemies and decrements the arrayList size 
	 * and index. 
	 */
	@Override
	public void remove() {
		enemyList.remove(index);
		index--;
		size--;
	}
	
	public ArrayList<EnemyModel> getIterEnemyList() {
		return enemyList;
	}
	
	public int getIndex() {
		return index;
	}

	public EnemyModel getCurrentEnemy() {
		return currentEnemy;
	}

}
