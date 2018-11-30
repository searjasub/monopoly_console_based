package card;

import enumeration.CardCategory;
import enumeration.CardType;

public class Card {
	String name, desc;
	CardType cardType;
	CardCategory cardName;
	int cost;

	public Card(String name, String desc, CardType cardType, CardCategory cardName, int cost) {
		this.name = name;
		this.desc = desc;
		this.cardType = cardType;
		this.cardName = cardName;
		this.cost = cost;
	}

	public Card(String name, String desc, CardType cardType, CardCategory cardName) {
		this(name, desc, cardType, cardName, 0);
	}
}

