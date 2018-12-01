package game;

import card.Card;
import card.Property;
import card.RailRoad;
import card.TitleDeed;
import card.Utility;
import enumeration.CardCategory;
import enumeration.CardType;
import square.*;

public class Board {
	
	Card[] cards = new Card[32];
	Property[] deeds = new Property[28];
	Square[] squares = new Square[40];
	
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
		deeds[0] = new TitleDeed("Mediterranean Avenue", 60, enumeration.TitleColor.BROWN, 50, 1);
		deeds[1] = new TitleDeed("Baltic Avenue", 60, enumeration.TitleColor.BROWN, 50, 3);
		deeds[2] = new TitleDeed("Oriental Avenue", 100, enumeration.TitleColor.LIGHTBLUE, 50, 6);
		deeds[3] = new TitleDeed("Vermont Avenue", 100, enumeration.TitleColor.LIGHTBLUE, 50, 8);
		deeds[4] = new TitleDeed("Connecticut Avenue", 120, enumeration.TitleColor.LIGHTBLUE, 50, 9);
		deeds[5] = new TitleDeed("St. Charles Place", 140, enumeration.TitleColor.PINK, 100, 11);
		deeds[6] = new TitleDeed("States Avenue", 140, enumeration.TitleColor.PINK, 100, 13);
		deeds[7] = new TitleDeed("Virginia Avenue", 160, enumeration.TitleColor.PINK, 100, 14);
		deeds[8] = new TitleDeed("St. James Place", 180, enumeration.TitleColor.ORANGE, 100, 16);
		deeds[9] = new TitleDeed("Tennessee Avenue", 180, enumeration.TitleColor.ORANGE, 100, 18);
		deeds[10] = new TitleDeed("New York Avenue", 200, enumeration.TitleColor.ORANGE, 100, 19);
		deeds[11] = new TitleDeed("Kentucky Avenue", 220, enumeration.TitleColor.RED, 150, 21);
		deeds[12] = new TitleDeed("Indiana Avenue", 220, enumeration.TitleColor.RED, 150, 23);
		deeds[13] = new TitleDeed("Illinois Avenue", 240, enumeration.TitleColor.RED, 150, 24);
		deeds[14] = new TitleDeed("Atlantic Avenue", 260, enumeration.TitleColor.YELLOW, 150, 26);
		deeds[15] = new TitleDeed("Ventnor Avenue", 260, enumeration.TitleColor.YELLOW, 150, 27);
		deeds[16] = new TitleDeed("Marvin Gardens", 280, enumeration.TitleColor.YELLOW, 150, 29);
		deeds[17] = new TitleDeed("Pacific Avenue", 300, enumeration.TitleColor.GREEN, 200, 31);
		deeds[18] = new TitleDeed("North Carolina Avenue", 300, enumeration.TitleColor.GREEN, 200, 32);
		deeds[19] = new TitleDeed("Pennsylvania Avenue", 320, enumeration.TitleColor.GREEN, 200, 34);
		deeds[20] = new TitleDeed("Park Place", 350, enumeration.TitleColor.BLUE, 200, 37);
		deeds[21] = new TitleDeed("Boardwalk", 400, enumeration.TitleColor.BLUE, 200, 39);
		deeds[22] = new Utility("Electric Company", 12);
		deeds[23] = new Utility("Water Works", 28);
		deeds[24] = new RailRoad("Reading Railroad", 5);
		deeds[25] = new RailRoad("Pennsylvania Railroad", 15);
		deeds[26] = new RailRoad("B. & O. Railroad", 25);
		deeds[27] = new RailRoad("Short Line", 35);
	}
	
	public void initSquare() {
		squares[0] = new Corner("Collect $200 salary as you pass GO");
		squares[1] = new square.Property("Mediterranean Avenue", (TitleDeed) deeds[0]);
		squares[2] = new Special("Community Chest", CardType.COMMUNITY_CHEST);
		squares[3] = new square.Property("Baltic Avenue", (TitleDeed) deeds[1]);
		squares[4] = new Special("Income Tax");
		squares[5] = new square.RailRoad("Reading Railroad", (RailRoad) deeds[24]);
		squares[6] = new square.Property("Oriental Avenue", (TitleDeed) deeds[2]);
		squares[7] = new Special("Chance", CardType.CHANCE);
		squares[8] = new square.Property("Vermont Avenue", (TitleDeed) deeds[3]);
		squares[9] = new square.Property("Connecticut Avenue", (TitleDeed) deeds[4]);
		squares[10] = new Corner("Just Visiting");
		squares[11] = new square.Property("St. Charles Place", (TitleDeed) deeds[5]);
		squares[12] = new square.Utility("Electric Company", (Utility) deeds[22]);
		squares[13] = new square.Property("States Avenue", (TitleDeed) deeds[6]);
		squares[14] = new square.Property("Virginia Avenue", (TitleDeed) deeds[7]);
		squares[15] = new square.RailRoad("Pennsylvania Railroad", (RailRoad) deeds[25]);
		squares[16] = new square.Property("St. James Place", (TitleDeed) deeds[8]);
		squares[17] = new Special("Community Chest", CardType.COMMUNITY_CHEST);
		squares[18] = new square.Property("Tennessee Avenue", (TitleDeed) deeds[9]);
		squares[19] = new square.Property("New York Avenue", (TitleDeed) deeds[10]);
		squares[20] = new Corner("Free Parking");
		squares[21] = new square.Property("Kentucky Avenue", (TitleDeed) deeds[11]);
		squares[22] = new Special("Chance", CardType.CHANCE);
		squares[23] = new square.Property("Indiana Avenue", (TitleDeed) deeds[12]);
		squares[24] = new square.Property("Illinois Avenue", (TitleDeed) deeds[13]);
		squares[25] = new square.RailRoad("B. & O. Railroad", (RailRoad) deeds[26]);
		squares[26] = new square.Property("Atlantic Avenue", (TitleDeed) deeds[14]);
		squares[27] = new square.Property("Ventnor Avenue", (TitleDeed) deeds[15]);
		squares[28] = new square.Utility("Water Works", (Utility) deeds[23]);
		squares[29] = new square.Property("Marvin Gardens", (TitleDeed) deeds[16]);
		squares[30] = new Corner("Go To Jail");
		squares[31] = new square.Property("Pacific Avenue", (TitleDeed) deeds[17]);
		squares[32] = new square.Property("North Carolina Avenue", (TitleDeed) deeds[18]);
		squares[33] = new Special("Community Chest", CardType.COMMUNITY_CHEST);
		squares[34] = new square.Property("Pennsylvania Avenue", (TitleDeed) deeds[19]);
		squares[35] = new square.RailRoad("Short Line", (RailRoad) deeds[27]);
		squares[36] = new Special("Chance", CardType.CHANCE);
		squares[37] = new square.Property("Park Place", (TitleDeed) deeds[20]);
		squares[38] = new Special("Luxury Tax");
		squares[39] = new square.Property("Boardwalk", (TitleDeed) deeds[21]);
	}
	
	public void pintBoard(Player player) {
		System.out.println(" _______________________________________________________________________________________________");
		System.out.println("|			|		|		|		|		|		|		|		|		|		|			|");
		System.out.println("|	FREE	|		|		|		|		|		|		|		|		|		|	GO TO	|");
		System.out.println("|  PARKING	|	21	|	22	|	23	|   24  |	25	|	26	|	27	|	28	|	29	|	JAIL	|");
		System.out.println("|			|		|		|		|		|		|		|		|		|		|			|");
		System.out.println("|___________|_______|_______|_______|_______|_______|_______|_______|_______|_______|___________|");
		System.out.println("|			|																		|			|");
		System.out.println("|	 19		|																		|	 31		|");
		System.out.println("|___________|							________________							|___________|");
		System.out.println("|			|						   |				|  							|			|");
		System.out.println("|	 18		|						   |	 CHANCE		|							|	 32		|");
		System.out.println("|___________|						   |				|							|___________|");
		System.out.println("|			|						   |________________|							|			|");
		System.out.println("|	 17		|								               							|	 33		|		Current player: " + player.getToken() );
		System.out.println("|___________|																		|___________|		Location: " + player.location);
		System.out.println("|			|																		|			|");
		System.out.println("|	 16		|																		|	 34		|");
		System.out.println("|___________|																		|___________|");
		System.out.println("|			|							M O N O P O L Y								|			|");
		System.out.println("|	 15		|																		|	 35		|");
		System.out.println("|___________|																		|___________|");
		System.out.println("|			|																		|			|");
		System.out.println("|	 14		|							________________							|	 36		|");
		System.out.println("|___________|						   |				|							|___________|");
		System.out.println("|			|						   |   COMMUNITY	|							|			|");
		System.out.println("|	 13		|					 	   |	 CHEST		|							|	 37		|");
		System.out.println("|___________|						   |________________|							|___________|");
		System.out.println("|			|																		|			|");
		System.out.println("|	 12		|																		|	 38		|");
		System.out.println("|___________|																		|___________|");
		System.out.println("|			|																		|			|");
		System.out.println("|	 11		|																		|	 39		|");
		System.out.println("|___________|_______________________________________________________________________|___________|");
		System.out.println("|			|		|		|		|		|		|		|		|		|		|			|");
		System.out.println("|			|		|		|		|		|		|		|		|		|		|			|");
		System.out.println("|	JAIL	|	9	|	8	|	7	|	6	|	5	|	4	|	3	|	2	|	1	|	 GO		|");
		System.out.println("|			|		|		|		|		|		|		|		|		|		|			|");
		System.out.println("|___________|_______|_______|_______|_______|_______|_______|_______|_______|_______|___________|");
		
	}
	
	
}