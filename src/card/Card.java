package card;

import enumeration.CardCategory;
import enumeration.CardType;

public class Card {
	String name, desc;
	public CardType cardType;
	public CardCategory cardName;
	int cost, id;

	public Card(String name, String desc, CardType cardType, CardCategory cardName, int cost, int id) {
		this.name = name;
		this.desc = desc;
		this.cardType = cardType;
		this.cardName = cardName;
		this.cost = cost;
		this.id = id;
	}

	public Card(String name, String desc, CardType cardType, CardCategory cardName, int id) {
		//this(name, desc, cardType, cardName, 0, 0);
		this.name = name;
		this.desc = desc;
		this.cardType = cardType;
		this.cardName = cardName;
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getDesc() {
		return this.desc;
	}
	
	
	
	
}

