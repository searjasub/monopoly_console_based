
public class Board {

	Card[] cards = new Card[32];

	public Board() {
		cards[0] = new Card("name", "desc", CardCategory.JAIL_FREE);
		cards[1] = new Card("name", "desc", CardCategory.JAIL_FREE);
		cards[2] = new Card("name", "desc", CardCategory.MOVEMENT);
		cards[3] = new Card("name", "desc", CardCategory.MOVEMENT);
		cards[4] = new Card("name", "desc", CardCategory.MOVEMENT);
		cards[5] = new Card("name", "desc", CardCategory.MOVEMENT);
		cards[6] = new Card("name", "desc", CardCategory.MOVEMENT);
		cards[7] = new Card("name", "desc", CardCategory.MOVEMENT);
		cards[8] = new Card("name", "desc", CardCategory.MOVEMENT);
		cards[9] = new Card("name", "desc", CardCategory.MOVEMENT);
		cards[10] = new Card("name", "desc", CardCategory.MOVEMENT);
		cards[11] = new Card("name", "desc", CardCategory.MOVEMENT);
		cards[12] = new Card("name", "desc", CardCategory.MOVEMENT);
		cards[13] = new Card("name", "desc", CardCategory.MOVEMENT);
		cards[14] = new Card("name", "desc", CardCategory.PAY_BUILDING_TAX);
		cards[15] = new Card("name", "desc", CardCategory.PAY_BUILDING_TAX);
		cards[16] = new Card("name", "desc", CardCategory.PAY_MONEY);
		cards[17] = new Card("name", "desc", CardCategory.PAY_MONEY);
		cards[18] = new Card("name", "desc", CardCategory.PAY_MONEY);
		cards[19] = new Card("name", "desc", CardCategory.PAY_MONEY);
		cards[20] = new Card("name", "desc", CardCategory.PAY_OR_RECEIVE_PLAYERS);
		cards[21] = new Card("name", "desc", CardCategory.PAY_OR_RECEIVE_PLAYERS);
		cards[22] = new Card("name", "desc", CardCategory.RECEIVE_MONEY);
		cards[23] = new Card("name", "desc", CardCategory.RECEIVE_MONEY);
		cards[24] = new Card("name", "desc", CardCategory.RECEIVE_MONEY);
		cards[25] = new Card("name", "desc", CardCategory.RECEIVE_MONEY);
		cards[26] = new Card("name", "desc", CardCategory.RECEIVE_MONEY);
		cards[27] = new Card("name", "desc", CardCategory.RECEIVE_MONEY);
		cards[28] = new Card("name", "desc", CardCategory.RECEIVE_MONEY);
		cards[29] = new Card("name", "desc", CardCategory.RECEIVE_MONEY);
		cards[30] = new Card("name", "desc", CardCategory.RECEIVE_MONEY);
		cards[31] = new Card("name", "desc", CardCategory.RECEIVE_MONEY);
	}

}
