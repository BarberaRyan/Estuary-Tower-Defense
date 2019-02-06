package main;

import javax.swing.SwingUtilities;

import controller.GameController;
/**
 * Starts the game by calling a new instance of the GameController. 
 * 
 * @author Ryan Barbera, Aaron George, Nick Norton, Thomas Pennington, Grant Zhao
 *
 */
public class Main {
	public static void main(String kms []) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new GameController();				
			}
		});
	}
}
