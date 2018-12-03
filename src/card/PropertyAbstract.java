package card;

import game.Player;

public abstract class PropertyAbstract {
	
	String name;
	Player owner;
	
	public PropertyAbstract(String name) {
		this.name = name;
	}
	
	public String getPropertyName() {
		return this.name;
	}
	
	public Player getOwner() {
		return this.owner;
	}
	
	public void setOwner(Player owner) {
		this.owner = owner;
	}
}
