package main;

/**
 * Keeps track of each pair of x and y coordinates that make up the positions that 
 * are the boundaries of the path model. 
 * 
 * @author Ryan Barbera, Aaron George, Nick Norton, Thomas Pennington, Grant Zhao
 *
 */

public class Posn {
	int xCor;
	int yCor;
	
	/**
	 * Creates a Posn object that takes in an x, y coordinate to keep track of where 
	 * the bounds are of the track. When the posn is reached the next posn in the arrayList 
	 * will be the next destination of the next corner in the map. 
	 * 
	 * @param xCor the x coordinate of the path  
	 * @param yCor the y coordinate of the path
	 */
	public Posn(int xCor, int yCor) {
		this.xCor = xCor;
		this.yCor = yCor;
	}
	
	public int getXCor() {
		return xCor;
	}
	public void setXCor(int xCor) {
		this.xCor = xCor;
	}
	
	public int getYCor() {
		return yCor;
	}
	public void setYCor(int yCor) {
		this.yCor = yCor;
	}
}
