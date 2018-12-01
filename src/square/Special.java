package square;
import enumeration.CardType;

public class Special extends Square {
	CardType cardType;

	public Special(String name, CardType cardType) {
		super(name);
		this.cardType = cardType;
	}

	public Special(String name) {
		this(name, null);
	}


}
