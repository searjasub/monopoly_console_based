package game;

import card.Card;
import card.TitleDeed;
import enumeration.CardCategory;
import enumeration.CardType;

public class Board {
	
	Card[] cards = new Card[32];
	TitleDeed[] deeds = new TitleDeed[22];
	
	public Board() {
		initCards();	
		initDeeds();
	}
	
	public void initCards() {
		cards[0] = new Card("Chance", "GET OUT OF JAIL FREE This card may be kept until needed or traded.", CardType.CHANCE, CardCategory.JAIL_FREE);
		cards[1] = new Card("Community Chest", "GET OUT OF JAIL FREE This card may be kept until needed or traded.", CardType.COMMUNITY_CHEST, CardCategory.JAIL_FREE);
		cards[2] = new Card("Community Chest", "Advance to GO. (Collect $200)", CardType.COMMUNITY_CHEST, CardCategory.MOVEMENT);
		cards[3] = new Card("Chance", "Go back three spaces.", CardType.CHANCE, CardCategory.MOVEMENT);
		cards[4] = new Card("Chance", "ADVANCE TO THE NEAREST RAILROAD. If UNOWNED, you may buy it from the bank. If OWNED, pay owner twice the rental to which they are otherwise entitled.", CardType.CHANCE, CardCategory.MOVEMENT);
		cards[5] = new Card("Chance", "ADVANCE TO THE NEAREST UTILITY. If UNOWNED, you may buy it from the bank. If OWNED, throw dice and pay owner ten times the amount thrown.", CardType.CHANCE, CardCategory.MOVEMENT);
		cards[6] = new Card("Chance", "ADVANCE TO THE NEAREST RAILROAD. If UNOWNED, you may buy it from the bank. If OWNED, pay owner twice the rental to which they are otherwise entitled.", CardType.CHANCE, CardCategory.MOVEMENT);
		cards[7] = new Card("Chance", "Advance to GO. (Collect $200)", CardType.CHANCE, CardCategory.MOVEMENT);
		cards[8] = new Card("Chance", "Advance to Illinois Avenue. If you pass GO, collect $200.", CardType.CHANCE, CardCategory.MOVEMENT);
		cards[9] = new Card("Chance", "Take a trip to Reading Railroad. If you pass GO, collect $200.", CardType.CHANCE, CardCategory.MOVEMENT);
		cards[10] = new Card("Chance", "Advance to ST. Charles Place. If you pass GO, collect $200.", CardType.CHANCE, CardCategory.MOVEMENT);
		cards[11] = new Card("Chance", "GO TO JAIL. GO DIRECTLY TO JAIL, DO NOT PASS GO, DO NOT COLLECT $200.", CardType.CHANCE, CardCategory.MOVEMENT);
		cards[12] = new Card("Community Chest", "GO TO JAIL. GO DIRECTLY TO JAIL, DO NOT PASS GO, DO NOT COLLECT $200.", CardType.COMMUNITY_CHEST,CardCategory.MOVEMENT);
		cards[13] = new Card("Chance", "Advance to Boardwalk.", CardType.CHANCE, CardCategory.MOVEMENT);
		cards[14] = new Card("Chance", "Speeding fine $15.", CardType.CHANCE, CardCategory.PAY_MONEY, 15);
		cards[15] = new Card("Community Chest", "Doctor's fees. Pay $50.", CardType.COMMUNITY_CHEST, CardCategory.PAY_MONEY, 50);
		cards[16] = new Card("Community Chest", "School fees. Pay $50.", CardType.COMMUNITY_CHEST, CardCategory.PAY_MONEY, 50);
		cards[17] = new Card("Community Chest", "Hospital fees. Pay $100.", CardType.COMMUNITY_CHEST, CardCategory.PAY_MONEY, 100);
		cards[18] = new Card("Community Chest", "You are assessed for street repairs: pay $40 per house and $115 per hotel you own.", CardType.COMMUNITY_CHEST, CardCategory.PAY_BUILDING_TAX);
		cards[19] = new Card("Chance", "Make general repairs on all your property: for each house pay $25, for each hotel pay $100.", CardType.CHANCE, CardCategory.PAY_BUILDING_TAX);
		cards[20] = new Card("Community Chest", "It is your birthday. COllect $10 from every player.", CardType.COMMUNITY_CHEST, CardCategory.PAY_OR_RECEIVE_PLAYERS);
		cards[21] = new Card("Chance", "You have been elected chairman of the board. Pay each player $50.",CardType.CHANCE, CardCategory.PAY_OR_RECEIVE_PLAYERS);
		cards[22] = new Card("Chance", "Your building loan matures. Collect $150.", CardType.CHANCE, CardCategory.RECEIVE_MONEY, 150);
		cards[23] = new Card("Chance", "Bank pays you dividend of $50.", CardType.CHANCE, CardCategory.RECEIVE_MONEY, 50);
		cards[24] = new Card("Community Chest", "Bank error in your favor. Collect $200.", CardType.COMMUNITY_CHEST, CardCategory.RECEIVE_MONEY, 200);
		cards[25] = new Card("Community Chest", "Life insurance matures. Collect $100.", CardType.COMMUNITY_CHEST, CardCategory.RECEIVE_MONEY, 100);
		cards[26] = new Card("Community Chest", "Income tax refund. Collect $20.", CardType.COMMUNITY_CHEST, CardCategory.RECEIVE_MONEY, 20);
		cards[27] = new Card("Community Chest", "Receive $25 consultancy fee.", CardType.COMMUNITY_CHEST, CardCategory.RECEIVE_MONEY, 25);
		cards[28] = new Card("Community Chest", "You have won second prize in a beauty contest. Collect $10.", CardType.COMMUNITY_CHEST, CardCategory.RECEIVE_MONEY, 10);
		cards[29] = new Card("Community Chest", "From sale of stock you get $50.", CardType.COMMUNITY_CHEST, CardCategory.RECEIVE_MONEY, 50);
		cards[30] = new Card("Community Chest", "You inherit $100.", CardType.COMMUNITY_CHEST, CardCategory.RECEIVE_MONEY, 100);
		cards[31] = new Card("Community Chest", "Holiday fund matures. Receive $100.", CardType.COMMUNITY_CHEST, CardCategory.RECEIVE_MONEY, 100);
	}
	
	public void initDeeds() {
		deeds[0] = new TitleDeed("Mediterranean Avenue", 60, enumeration.TitleColor.BROWN, 50);
		deeds[1] = new TitleDeed("Baltic Avenue", 60, enumeration.TitleColor.BROWN, 50);
		deeds[2] = new TitleDeed("Oriental Avenue", 100, enumeration.TitleColor.LIGHTBLUE, 50);
		deeds[3] = new TitleDeed("Vermont Avenue", 100, enumeration.TitleColor.LIGHTBLUE, 50);
		deeds[4] = new TitleDeed("Connecticut Avenue", 120, enumeration.TitleColor.LIGHTBLUE, 50);
		deeds[5] = new TitleDeed("St. Charles Place", 140, enumeration.TitleColor.PINK, 100);
		deeds[6] = new TitleDeed("States Avenue", 140, enumeration.TitleColor.PINK, 100);
		deeds[7] = new TitleDeed("Virginia Avenue", 160, enumeration.TitleColor.PINK, 100);
		deeds[8] = new TitleDeed("St. James Place", 180, enumeration.TitleColor.ORANGE, 100);
		deeds[9] = new TitleDeed("Tennessee Avenue", 180, enumeration.TitleColor.ORANGE, 100);
		deeds[10] = new TitleDeed("New York Avenue", 200, enumeration.TitleColor.ORANGE, 100);
		deeds[11] = new TitleDeed("Kentucky Avenue", 220, enumeration.TitleColor.RED, 150);
		deeds[12] = new TitleDeed("Indiana Avenue", 220, enumeration.TitleColor.RED, 150);
		deeds[13] = new TitleDeed("Illinois Avenue", 240, enumeration.TitleColor.RED, 150);
		deeds[14] = new TitleDeed("Atlantic Avenue", 260, enumeration.TitleColor.YELLOW, 150);
		deeds[15] = new TitleDeed("Ventnor Avenue", 260, enumeration.TitleColor.YELLOW, 150);
		deeds[16] = new TitleDeed("Marvin Gardens", 280, enumeration.TitleColor.YELLOW, 150);
		deeds[17] = new TitleDeed("Pacific Avenue", 300, enumeration.TitleColor.GREEN, 200);
		deeds[18] = new TitleDeed("North Carolina Avenue", 300, enumeration.TitleColor.GREEN, 200);
		deeds[19] = new TitleDeed("Pennsylvania Avenue", 320, enumeration.TitleColor.GREEN, 200);
		deeds[20] = new TitleDeed("Park Place", 350, enumeration.TitleColor.BLUE, 200);
		deeds[21] = new TitleDeed("Boardwalk", 400, enumeration.TitleColor.BLUE, 200);
	}
	
	
}