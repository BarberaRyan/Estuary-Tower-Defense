package boardMenu;

import javax.swing.JLabel;

/**
 * Creates JLabels when placing objects on the center panel for easy placement.
 * 
 * @author Ryan Barbera, Aaron George, Nick Norton, Thomas Pennington, Grant Zhao
 *
 */

public class TowerView {
	private JLabel towerLabel;
	private String towerName;

	/**
	 * Creates a TowerView object with a JLabel and a tower name. This is used when the towers 
	 * are created with the clicking of buttons on the BoardMenuGameSidePanel. 
	 */
	public TowerView() {
		this.towerLabel = null;
		this.towerName = "";
	}

	public JLabel getTowerLabel() {
		return towerLabel;
	}

	public void setTowerLabel(JLabel towerLabel) {
		this.towerLabel = towerLabel;
	}

	public String getTowerName() {
		return towerName;
	}

	public void setTowerName(String towerName) {
		this.towerName = towerName;
	}
}