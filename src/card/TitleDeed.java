package card;

import enumeration.TitleColor;

public class TitleDeed extends Property {
	
	TitleColor color;

	public TitleDeed(String name, int cost, TitleColor color, int buildingCost, int location, int rent, int rentWithOne, int rentWithTwo, int rentWithThree, int rentWithFour, int rentWithHotel) {
		super(name, cost, buildingCost, location, rent, rentWithOne, rentWithTwo, rentWithThree, rentWithFour, rentWithHotel);
		
	}
	
	

}
