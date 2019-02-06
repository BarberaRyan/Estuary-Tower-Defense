package main;

import java.util.ArrayList;

/**
 * Controls the path of the enemies using a system of coordinate pairs on each of the paths
 * corners. This will become important when the map resizes to update the system of coordinates 
 * that are used. 
 * 
 * @author Ryan Barbera, Aaron George, Nick Norton, Thomas Pennington, Grant Zhao
 *
 */

public class PathModel {
	private ArrayList<Posn> posnArr;
	private int size;
	
	/**
	 * Creates a PathModel object that initializes an arrayList of positions and sets the 
	 * size to 40.
	 */
	public PathModel() {
		this.posnArr = new ArrayList<Posn>();
		this.size = 40;
	}
	
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public ArrayList<Posn> getPosnArr() {
		return posnArr;
	}
	public void setPosnArr(ArrayList<Posn> posnArr) {
		this.posnArr = posnArr;
	}
	
	/**
	 * Access the arrayList of posns given an index, and Returns that posn 
	 * at the particular index that is requested. 
	 * 
	 * @param ind the index of the posn that is requested from the arrayList
	 * @return the posn at the particular index in the arrayList of posns
	 */
	public Posn getPosnAtIndex(int ind){
		return posnArr.get(ind);
	}
}
