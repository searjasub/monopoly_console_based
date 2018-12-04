package card;

import game.Player;

public abstract class Property {
	
	int cost, buildingCost, location;
	String name;
	Player owner;
	
	public Property(String name, int cost, int buildingCost, int location, int rent) {
		this.name = name;
		this.cost = cost;
		this.buildingCost = buildingCost;
		this.location = location;
	}
	
	public Property(String name, int location) {
		this.name = name;
		this.location =location;
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
	
	public int getCost() {
		return this.cost;
	}
	
	public int getBuildingCost() {
		return this.buildingCost;
	}
	
	public int getLocation() {
		return this.location;
	}
}
