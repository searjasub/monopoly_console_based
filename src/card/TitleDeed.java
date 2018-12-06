package card;

import enumeration.TitleColor;

public class TitleDeed extends Property {
	
	public TitleColor color;
	public int totalBuildings = 0;

	/**
	 * 
	 * @param name
	 * @param cost
	 * @param color
	 * @param buildingCost
	 * @param location
	 * @param rent
	 * @param rentWithOne
	 * @param rentWithTwo
	 * @param rentWithThree
	 * @param rentWithFour
	 * @param rentWithHotel
	 */
	public TitleDeed(String name, int cost, TitleColor color, int buildingCost, int location, int rent, int rentWithOne, int rentWithTwo, int rentWithThree, int rentWithFour, int rentWithHotel) {
		super(name, cost, buildingCost, location, rent, rentWithOne, rentWithTwo, rentWithThree, rentWithFour, rentWithHotel);
		this.color = color;
		
	}
	
	

}
