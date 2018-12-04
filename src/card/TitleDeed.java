package card;

import enumeration.TitleColor;

public class TitleDeed extends Property {
	
	int  buildingCost, location;
	TitleColor color;

	public TitleDeed(String name, int cost, TitleColor color, int buildingCost, int location, int rent) {
		super(name, cost, buildingCost, location, rent);
		
	}
	
	

}
