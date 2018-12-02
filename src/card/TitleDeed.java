package card;

import enumeration.TitleColor;

public class TitleDeed extends Property {
	String name;
	int cost, buildingCost, location;
	TitleColor color;

	public TitleDeed(String name, int cost, TitleColor color, int buildingCost, int location) {
		super(name);
		this.cost = cost;
		this.buildingCost = buildingCost;
		this.location = location;
		this.color = color;
	}
}
