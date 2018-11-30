package square;

import card.TitleDeed;

public class Property extends Square {
	TitleDeed card;

	public Property(String name, TitleDeed card) {
		super(name);
		this.card = card;

	}

}
