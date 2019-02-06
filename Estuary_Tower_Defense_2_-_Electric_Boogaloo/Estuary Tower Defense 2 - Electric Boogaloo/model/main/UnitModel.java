package main;

/**
 * Houses the attributes that both Towers and Enemies share in common such as the name,
 * posn coordinates, gameDescriptions and the fact descriptions that makes this educational.
 * 
 * @author Ryan Barbera, Aaron George, Nick Norton, Thomas Pennington, Grant Zhao
 * 
 */

public abstract class UnitModel {
	
	String name;
	Posn posn;
	String gameDescription;
	String factDescription;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Posn getPosn() {
		return posn;
	}

	public void setPosn(Posn posn) {
		this.posn = posn;
	}

	public String getGameDescription() {
		return gameDescription;
	}

	public void setGameDescription(String gameDescription) {
		this.gameDescription = gameDescription;
	}

	public String getFactDescription() {
		return factDescription;
	}

	public void setFactDescription(String factDescription) {
		this.factDescription = factDescription;
	}
}
