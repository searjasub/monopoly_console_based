package card;

import game.Player;

public abstract class Property {
	
	int cost, buildingCost, location, rent;
	String name;
	Player owner;
	
	public Property(String name, int cost, int buildingCost, int location, int rent) {
		this.name = name;
		this.cost = cost;
		this.buildingCost = buildingCost;
		this.location = location;
		this.rent = rent;
	}
	
	public Property(String name, int location, int cost) {
		this.name = name;
		this.location =location;
		this.cost = cost;
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
	
	public int getRent() {
		return this.rent;
	}
}
