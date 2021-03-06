package game;

import java.util.ArrayList;
import java.util.Collections;

import card.Card;
import card.Property;
import card.RailRoad;
import card.TitleDeed;
import card.Utility;
import enumeration.CardCategory;
import enumeration.CardType;
import enumeration.TitleColor;
import square.*;

public class Board {

	ArrayList<Card> communityChest = new ArrayList<Card>();
	ArrayList<Card> chance = new ArrayList<Card>();
	Property[] deeds = new Property[28];
	Square[] squares = new Square[40];
	Property deed;

	/**
	 * Constructor
	 */
	public Board() {
		initCards();
		initDeeds();
		initSquare();
	}

	public int getSizeOfBoard() {
		return squares.length;
	}

	public boolean ownsDeed(int location, Player currentPlayer) {
		boolean deedOwned = false;

		if (deeds[location].getOwner() == null) {
			deeds[location].setOwner(currentPlayer);
			deedOwned = true;
		}
		return deedOwned;
	}

	/**
	 * Method to initialize every card in the game.
	 */
	public void initCards() {
		chance.add(new Card("\t\tChance\n", "\tGET OUT OF JAIL FREE\nThis card may be kept until needed or traded.\n",
				CardType.CHANCE, CardCategory.JAIL_FREE, 1));
		communityChest.add(new Card("\t\tCommunity Chest\n",
				"\tGET OUT OF JAIL FREE\nThis card may be kept until needed or traded.\n", CardType.COMMUNITY_CHEST,
				CardCategory.JAIL_FREE, 2));
		communityChest.add(new Card("\t\tCommunity Chest\n", "\tAdvance to GO. (Collect $200)",
				CardType.COMMUNITY_CHEST, CardCategory.MOVEMENT, 3));
		chance.add(new Card("\t\tChance\n", "\tGo back three spaces.\n", CardType.CHANCE, CardCategory.MOVEMENT, 4));
		chance.add(new Card("\t\t\tChance\n",
				"\t\tADVANCE TO THE NEAREST RAILROAD.\n\tIf UNOWNED, you may buy it from the bank.\n If OWNED, pay owner twice the rental to which they are otherwise entitled.\n",
				CardType.CHANCE, CardCategory.MOVEMENT, 5));
		chance.add(new Card("\t\t\tChance\n",
				"\t\tADVANCE TO THE NEAREST UTILITY.\n \tIf UNOWNED, you may buy it from the bank.\n If OWNED, throw dice and pay owner ten times the amount thrown.\n",
				CardType.CHANCE, CardCategory.MOVEMENT, 6));
		chance.add(new Card("\t\t\tChance\n",
				"\t\tADVANCE TO THE NEAREST RAILROAD.\n \tIf UNOWNED, you may buy it from the bank.\n If OWNED, pay owner twice the rental to which they are otherwise entitled.\n",
				CardType.CHANCE, CardCategory.MOVEMENT, 7));
		chance.add(
				new Card("\tChance\n", "\tAdvance to GO. (Collect $200)\n", CardType.CHANCE, CardCategory.MOVEMENT, 8));
		chance.add(new Card("\tChance\n", "Advance to Illinois Avenue.\nIf you pass GO, collect $200.\n",
				CardType.CHANCE, CardCategory.MOVEMENT, 9));
		chance.add(new Card("\tChance\n", "Take a trip to Reading Railroad.\nIf you pass GO, collect $200.\n",
				CardType.CHANCE, CardCategory.MOVEMENT, 10));
		chance.add(new Card("\tChance\n", "Advance to St. Charles Place.\nIf you pass GO, collect $200.\n",
				CardType.CHANCE, CardCategory.MOVEMENT, 11));
		chance.add(new Card("\t\t  Chance\n",
				"\t\tGO TO JAIL.\nGO DIRECTLY TO JAIL, DO NOT PASS GO, DO NOT COLLECT $200.\n", CardType.CHANCE,
				CardCategory.MOVEMENT, 12));
		communityChest.add(new Card("\t\tCommunity Chest\n",
				"\t\t  GO TO JAIL.\nGO DIRECTLY TO JAIL, DO NOT PASS GO, DO NOT COLLECT $200.\n",
				CardType.COMMUNITY_CHEST, CardCategory.MOVEMENT, 13));
		chance.add(new Card("\tChance\n", "Advance to Boardwalk.\n", CardType.CHANCE, CardCategory.MOVEMENT, 14));
		chance.add(new Card("\tChance\n", "  Speeding fine $15.\n", CardType.CHANCE, CardCategory.PAY_MONEY, 15, 15));
		communityChest.add(new Card("\t   Community Chest\n", "\tDoctor's fees. Pay $50.\n", CardType.COMMUNITY_CHEST,
				CardCategory.PAY_MONEY, 50, 16));
		communityChest.add(new Card("\t  Community Chest\n", "\tSchool fees. Pay $50.\n", CardType.COMMUNITY_CHEST,
				CardCategory.PAY_MONEY, 50, 17));
		communityChest.add(new Card("\t   Community Chest\n", "\tHospital fees. Pay $100.\n", CardType.COMMUNITY_CHEST,
				CardCategory.PAY_MONEY, 100, 18));
		// Can't add this two cards since the building portion is not working.
		// communityChest.add(new Card("\t Community Chest\n", " You are assessed for
		// street repairs: \nPay $40 per house and $115 per hotel you own.\n",
		// CardType.COMMUNITY_CHEST, CardCategory.PAY_BUILDING_TAX,19));
		// chance.add(new Card("\t\tChance\n", " Make general repairs on all your
		// property: \nFor each house pay $25, for each hotel pay $100.\n",
		// CardType.CHANCE, CardCategory.PAY_BUILDING_TAX,20));
		communityChest.add(new Card("\t\tCommunity Chest\n", "It is your birthday. Collect $10 from every player.\n",
				CardType.COMMUNITY_CHEST, CardCategory.PAY_OR_RECEIVE_PLAYERS, 21));
		chance.add(new Card("     Chance\n", "You have been elected chairman of the board.\n \tPay each player $50.\n",
				CardType.CHANCE, CardCategory.PAY_OR_RECEIVE_PLAYERS, 22));
		chance.add(new Card("\t\tChance\n", "Your building loan matures. Collect $150.\n", CardType.CHANCE,
				CardCategory.RECEIVE_MONEY, 150, 23));
		chance.add(new Card("\t  Chance\n", "Bank pays you dividend of $50.\n", CardType.CHANCE,
				CardCategory.RECEIVE_MONEY, 50, 24));
		communityChest.add(new Card("\t  Community Chest\n", "Bank error in your favor. Collect $200.\n",
				CardType.COMMUNITY_CHEST, CardCategory.RECEIVE_MONEY, 200, 25));
		communityChest.add(new Card("\tCommunity Chest\n", "Life insurance matures. Collect $100.\n",
				CardType.COMMUNITY_CHEST, CardCategory.RECEIVE_MONEY, 100, 26));
		communityChest.add(new Card("\tCommunity Chest\n", "Income tax refund. Collect $20.\n",
				CardType.COMMUNITY_CHEST, CardCategory.RECEIVE_MONEY, 20, 27));
		communityChest.add(new Card("\tCommunity Chest\n", "  Receive $25 consultancy fee.\n", CardType.COMMUNITY_CHEST,
				CardCategory.RECEIVE_MONEY, 25, 28));
		communityChest
				.add(new Card("\t\t Community Chest\n", "You have won second prize in a beauty contest. Collect $10.\n",
						CardType.COMMUNITY_CHEST, CardCategory.RECEIVE_MONEY, 10, 29));
		communityChest.add(new Card("\tCommunity Chest\n", "From sale of stock you get $50.\n",
				CardType.COMMUNITY_CHEST, CardCategory.RECEIVE_MONEY, 50, 30));
		communityChest.add(new Card("\t Community Chest\n", "\tYou inherit $100.\n", CardType.COMMUNITY_CHEST,
				CardCategory.RECEIVE_MONEY, 100, 31));
		communityChest.add(new Card("\tCommunity Chest\n", "Holiday fund matures. Receive $100.\n",
				CardType.COMMUNITY_CHEST, CardCategory.RECEIVE_MONEY, 100, 32));

		Collections.shuffle(chance);
		Collections.shuffle(communityChest);
	}

	/**
	 * Method to initialize every property in the game. public TitleDeed(String name, int cost, TitleColor color, int buildingCost, int location, rentWithOne, rentWithTwo, rentWithThree, rentWithFour, rentWithHotel)
	 */
	public void initDeeds() {
		deeds[0] = new TitleDeed("Mediterranean Avenue", 60, enumeration.TitleColor.BROWN, 50, 1, 2, 10, 30, 90, 160,250);
		deeds[1] = new TitleDeed("Baltic Avenue", 60, enumeration.TitleColor.BROWN, 50, 3, 4, 20, 60, 180, 320, 450);
		deeds[2] = new RailRoad("Reading Railroad", 5, 200);
		deeds[3] = new TitleDeed("Oriental Avenue", 100, enumeration.TitleColor.LIGHTBLUE, 50, 6, 6, 30, 90, 270, 400,550);
		deeds[4] = new TitleDeed("Vermont Avenue", 100, enumeration.TitleColor.LIGHTBLUE, 50, 8, 6, 30, 90, 270, 400, 550);
		deeds[5] = new TitleDeed("Connecticut Avenue", 120, enumeration.TitleColor.LIGHTBLUE, 50, 9, 8, 40, 100, 300, 450, 600);
		deeds[6] = new TitleDeed("St. Charles Place", 140, enumeration.TitleColor.PINK, 100, 11, 10, 50, 150, 450, 625, 750);
		deeds[7] = new Utility("Electric Company", 12, 150);
		deeds[8] = new TitleDeed("States Avenue", 140, enumeration.TitleColor.PINK, 100, 13, 10, 50, 150, 450, 625, 750);
		deeds[9] = new TitleDeed("Virginia Avenue", 160, enumeration.TitleColor.PINK, 100, 14, 12, 60, 180, 500, 700, 900);
		deeds[10] = new RailRoad("Pennsylvania Railroad", 15, 200);
		deeds[11] = new TitleDeed("St. James Place", 180, enumeration.TitleColor.ORANGE, 100, 16, 14, 70, 200, 550, 750,950);
		deeds[12] = new TitleDeed("Tennessee Avenue", 180, enumeration.TitleColor.ORANGE, 100, 18, 14, 70, 200, 550,750, 950);
		deeds[13] = new TitleDeed("New York Avenue", 200, enumeration.TitleColor.ORANGE, 100, 19, 16, 80, 220, 600, 800, 1000);
		deeds[14] = new TitleDeed("Kentucky Avenue", 220, enumeration.TitleColor.RED, 150, 21, 18, 90, 250, 700, 875, 1050);
		deeds[15] = new TitleDeed("Indiana Avenue", 220, enumeration.TitleColor.RED, 150, 23, 18, 90, 250, 700, 875, 1050);
		deeds[16] = new TitleDeed("Illinois Avenue", 240, enumeration.TitleColor.RED, 150, 24, 20, 100, 300, 750, 925, 1100);
		deeds[17] = new RailRoad("B. & O. Railroad", 25, 200);
		deeds[18] = new TitleDeed("Atlantic Avenue", 260, enumeration.TitleColor.YELLOW, 150, 26, 22, 110, 330, 800,975, 1150);
		deeds[19] = new TitleDeed("Ventnor Avenue", 260, enumeration.TitleColor.YELLOW, 150, 27, 22, 110, 330, 800, 975, 1150);
		deeds[20] = new Utility("Water Works", 28, 150);
		deeds[21] = new TitleDeed("Marvin Gardens", 280, enumeration.TitleColor.YELLOW, 150, 29, 24, 120, 360, 850,1025, 1200);
		deeds[22] = new TitleDeed("Pacific Avenue", 300, enumeration.TitleColor.GREEN, 200, 31, 26, 130, 390, 900, 1100, 1275);
		deeds[23] = new TitleDeed("North Carolina Avenue", 300, enumeration.TitleColor.GREEN, 200, 32, 26, 130, 390, 900, 1100, 1275);
		deeds[24] = new TitleDeed("Pennsylvania Avenue", 320, enumeration.TitleColor.GREEN, 200, 34, 28, 150, 450, 1000, 1200, 1400);
		deeds[25] = new RailRoad("Short Line", 35, 200);
		deeds[26] = new TitleDeed("Park Place", 350, enumeration.TitleColor.BLUE, 200, 37, 35, 175, 500, 1100, 1300, 1500);
		deeds[27] = new TitleDeed("Boardwalk", 400, enumeration.TitleColor.BLUE, 200, 39, 50, 200, 600, 1400, 1700, 2000);

	}

	/**
	 * Method to initialize every single square in the board
	 */
	public void initSquare() {
		squares[0] = new Corner("GO");
		squares[1] = new square.Property("Mediterranean Avenue", (TitleDeed) deeds[0]);
		squares[2] = new Special("Community Chest", CardType.COMMUNITY_CHEST);
		squares[3] = new square.Property("Baltic Avenue", (TitleDeed) deeds[1]);
		squares[4] = new Special("Income Tax");
		squares[5] = new square.RailRoad("Reading Railroad", (RailRoad) deeds[2]);
		squares[6] = new square.Property("Oriental Avenue", (TitleDeed) deeds[3]);
		squares[7] = new Special("Chance", CardType.CHANCE);
		squares[8] = new square.Property("Vermont Avenue", (TitleDeed) deeds[4]);
		squares[9] = new square.Property("Connecticut Avenue", (TitleDeed) deeds[5]);
		squares[10] = new Corner("Jail/Just Visiting");
		squares[11] = new square.Property("St. Charles Place", (TitleDeed) deeds[6]);
		squares[12] = new square.Utility("Electric Company", (Utility) deeds[7]);
		squares[13] = new square.Property("States Avenue", (TitleDeed) deeds[8]);
		squares[14] = new square.Property("Virginia Avenue", (TitleDeed) deeds[9]);
		squares[15] = new square.RailRoad("Pennsylvania Railroad", (RailRoad) deeds[10]);
		squares[16] = new square.Property("St. James Place", (TitleDeed) deeds[11]);
		squares[17] = new Special("Community Chest", CardType.COMMUNITY_CHEST);
		squares[18] = new square.Property("Tennessee Avenue", (TitleDeed) deeds[12]);
		squares[19] = new square.Property("New York Avenue", (TitleDeed) deeds[13]);
		squares[20] = new Corner("Free Parking");
		squares[21] = new square.Property("Kentucky Avenue", (TitleDeed) deeds[14]);
		squares[22] = new Special("Chance", CardType.CHANCE);
		squares[23] = new square.Property("Indiana Avenue", (TitleDeed) deeds[15]);
		squares[24] = new square.Property("Illinois Avenue", (TitleDeed) deeds[16]);
		squares[25] = new square.RailRoad("B. & O. Railroad", (RailRoad) deeds[17]);
		squares[26] = new square.Property("Atlantic Avenue", (TitleDeed) deeds[18]);
		squares[27] = new square.Property("Ventnor Avenue", (TitleDeed) deeds[19]);
		squares[28] = new square.Utility("Water Works", (Utility) deeds[20]);
		squares[29] = new square.Property("Marvin Gardens", (TitleDeed) deeds[21]);
		squares[30] = new Corner("Go To Jail");
		squares[31] = new square.Property("Pacific Avenue", (TitleDeed) deeds[22]);
		squares[32] = new square.Property("North Carolina Avenue", (TitleDeed) deeds[23]);
		squares[33] = new Special("Community Chest", CardType.COMMUNITY_CHEST);
		squares[34] = new square.Property("Pennsylvania Avenue", (TitleDeed) deeds[24]);
		squares[35] = new square.RailRoad("Short Line", (RailRoad) deeds[25]);
		squares[36] = new Special("Chance", CardType.CHANCE);
		squares[37] = new square.Property("Park Place", (TitleDeed) deeds[26]);
		squares[38] = new Special("Luxury Tax");
		squares[39] = new square.Property("Boardwalk", (TitleDeed) deeds[27]);

	}

	/**
	 * Print the board in the console
	 * 
	 * @param player adding information of the current player to the right side of
	 *               the board
	 */
	public void printNonColorBoard(Player player) {

		System.out.println("\n"
				+ " ______________________________________________________________________________________________________\n"
				+ "|          |        |        |        |        |        |        |        |        |        |          |\n"
				+ "|   FREE   |        |        |        |        |        |        |        |        |        |          |\n"
				+ "|  PARKING |   21   |   22   |   23   |   24   |   25   |   26   |   27   |   28   |   29   |    30    |\n"
				+ "|          |        |        |        |        |        |        |        |        |        |          |\n"
				+ "|__________|________|________|________|________|________|________|________|________|________|__________|\n"
				+ "|          |                                                                                |          |\n"
				+ "|    19    |                                                                                |    31    |\n"
				+ "|__________|                            _____________________                               |__________|\n"
				+ "|          |                           |                     |                              |          |\n"
				+ "|    18    |                           |                     |                              |    32    |\n"
				+ "|__________|                           |       CHANCE        |                              |__________|\n"
				+ "|          |                           |                     |                              |          |\n"
				+ "|    17    |                           |_____________________|                              |    33    |      Player:\t\t "
				+ player.getName() + "\n"
				+ "|__________|                                                                                |__________|      Token:\t\t "
				+ player.getToken() + "\n"
				+ "|          |                                                                                |          |      Location:\t\t "
				+ player.getLocation() + "\n"
				+ "|    16    |                                                                                |    34    |      Description:\t "
				+ squares[player.getLocation()].getName() + "\n"
				+ "|__________|                                                                                |__________|\n"
				+ "|          |                                                                                |          |\n"
				+ "|    15    |                               M O N O P O L Y                                  |    35    |\n"
				+ "|__________|                                                                                |__________|\n"
				+ "|          |                                                                                |          |\n"
				+ "|    14    |                                                                                |    36    |\n"
				+ "|__________|                            _____________________                               |__________|\n"
				+ "|          |                           |                     |                              |          |\n"
				+ "|    13    |                           |      COMMUNITY      |                              |    37    |\n"
				+ "|__________|                           |        CHEST        |                              |__________|\n"
				+ "|          |                           |                     |                              |          |\n"
				+ "|    12    |                           |_____________________|                              |    38    |\n"
				+ "|__________|                                                                                |__________|\n"
				+ "|          |                                                                                |          |\n"
				+ "|    11    |                                                                                |    39    |\n"
				+ "|__________|________________________________________________________________________________|__________|\n"
				+ "|          |        |        |        |        |        |        |        |        |        |          |\n"
				+ "|          |        |        |        |        |        |        |        |        |        |          |\n"
				+ "|   JAIL   |   9    |   8    |   7    |   6    |   5    |   4    |   3    |   2    |    1   |    GO    |\n"
				+ "|          |        |        |        |        |        |        |        |        |        |          |\n"
				+ "|__________|________|________|________|________|________|________|________|________|________|__________|\n");

	}

	/**
	 * Print the board in the console
	 * 
	 * @param player adding information of the current player to the right side of
	 *               the board
	 */
	public void printBoard(Player player) {

		System.out.println("\n"
				+ " ______________________________________________________________________________________________________\n"
				+ "|          |        |        |        |        |        |        |        |        |        |          |\n"
				+ "|   FREE   |        |        |        |        |        |        |        |        |        |          |\n"
				+ "|  PARKING |   21   |   22   |   23   |   24   |   25   |   26   |   27   |   28   |   29   |    30    |\n"
				+ "|          |        |        |        |        |        |        |        |        |        |          |\n"
				+ "|__________|" + TitleColor.RED + "########" + TitleColor.RESET + "|________|" + TitleColor.RED
				+ "########" + TitleColor.RESET + "|" + TitleColor.RED + "########" + TitleColor.RESET + "|________|"
				+ TitleColor.YELLOW + "########" + TitleColor.RESET + "|" + TitleColor.YELLOW + "########"
				+ TitleColor.RESET + "|________|" + TitleColor.YELLOW + "########" + TitleColor.RESET + "|__________|\n"
				+ "|         " + TitleColor.ORANGE + "#" + TitleColor.RESET
				+ "|                                                                                |"
				+ TitleColor.GREEN + "#" + TitleColor.RESET + "         |\n" + "|    19   " + TitleColor.ORANGE + "#"
				+ TitleColor.RESET
				+ "|                                                                                |"
				+ TitleColor.GREEN + "#" + TitleColor.RESET + "   31    |\n" + "|_________" + TitleColor.ORANGE + "#"
				+ TitleColor.RESET
				+ "|                            _____________________                               |"
				+ TitleColor.GREEN + "#" + TitleColor.RESET + "_________|\n" + "|         " + TitleColor.ORANGE + "#"
				+ TitleColor.RESET + "|                           |" + TitleColor.ORANGE + "#####################"
				+ TitleColor.RESET + "|                              |" + TitleColor.GREEN + "#" + TitleColor.RESET
				+ "         |\n" + "|    18   " + TitleColor.ORANGE + "#" + TitleColor.RESET
				+ "|                           |" + TitleColor.ORANGE + "####             ####" + TitleColor.RESET
				+ "|                              |" + TitleColor.GREEN + "#" + TitleColor.RESET + "   32    |\n"
				+ "|_________" + TitleColor.ORANGE + "#" + TitleColor.RESET + "|                           |"
				+ TitleColor.ORANGE + "####" + TitleColor.RESET + "   CHANCE    " + TitleColor.ORANGE + "####"
				+ TitleColor.RESET + "|                              |" + TitleColor.GREEN + "#" + TitleColor.RESET
				+ "_________|\n" + "|          |                           |" + TitleColor.ORANGE
				+ "####             ####" + TitleColor.RESET + "|                              |          |\n"
				+ "|    17    |                           |" + TitleColor.ORANGE + "#####################"
				+ TitleColor.RESET + "|                              |    33    |      Player:\t\t " + player.getName()
				+ "\n"
				+ "|__________|                                                                                |__________|      Token:\t\t "
				+ player.getToken() + "\n" + "|         " + TitleColor.ORANGE + "#" + TitleColor.RESET
				+ "|                                                                                |"
				+ TitleColor.GREEN + "#" + TitleColor.RESET + "         |      Location:\t\t " + player.getLocation()
				+ "\n" + "|    16   " + TitleColor.ORANGE + "#" + TitleColor.RESET
				+ "|                                                                                |"
				+ TitleColor.GREEN + "#" + TitleColor.RESET + "    34   |      Description:\t "
				+ squares[player.getLocation()].getName() + "" + "\n" + "|_________" + TitleColor.ORANGE + "#"
				+ TitleColor.RESET
				+ "|                                                                                |"
				+ TitleColor.GREEN + "#" + TitleColor.RESET + "_________|\n"
				+ "|          |                                                                                |          |\n"
				+ "|    15    |                               M O N O P O L Y                                  |    35    |\n"
				+ "|__________|                                                                                |__________|\n"
				+ "|         " + TitleColor.PINK + "#" + TitleColor.RESET
				+ "|                                                                                |          |\n"
				+ "|    14   " + TitleColor.PINK + "#" + TitleColor.RESET
				+ "|                                                                                |    36    |\n"
				+ "|_________" + TitleColor.PINK + "#" + TitleColor.RESET
				+ "|                            _____________________                               |__________|\n"
				+ "|         " + TitleColor.PINK + "#" + TitleColor.RESET + "|                           |"
				+ TitleColor.COMMUNITY_CHEST + "#####################" + TitleColor.RESET
				+ "|                              |" + TitleColor.BLUE + "#" + TitleColor.RESET + "         |\n"
				+ "|    13   " + TitleColor.PINK + "#" + TitleColor.RESET + "|                           |"
				+ TitleColor.COMMUNITY_CHEST + "###" + TitleColor.RESET + "   COMMUNITY   " + TitleColor.COMMUNITY_CHEST
				+ "###" + TitleColor.RESET + "|                              |" + TitleColor.BLUE + "#"
				+ TitleColor.RESET + "   37    |\n" + "|_________" + TitleColor.PINK + "#" + TitleColor.RESET
				+ "|                           |" + TitleColor.COMMUNITY_CHEST + "####" + TitleColor.RESET
				+ "    CHEST    " + TitleColor.COMMUNITY_CHEST + "####" + TitleColor.RESET
				+ "|                              |" + TitleColor.BLUE + "#" + TitleColor.RESET + "_________|\n"
				+ "|          |                           |" + TitleColor.COMMUNITY_CHEST + "######         ######"
				+ TitleColor.RESET + "|                              |          |\n"
				+ "|    12    |                           |" + TitleColor.COMMUNITY_CHEST + "#####################"
				+ TitleColor.RESET + "|                              |    38    |\n"
				+ "|__________|                                                                                |__________|\n"
				+ "|         " + TitleColor.PINK + "#" + TitleColor.RESET
				+ "|                                                                                |" + TitleColor.BLUE
				+ "#" + TitleColor.RESET + "         |\n" + "|    11   " + TitleColor.PINK + "#" + TitleColor.RESET
				+ "|                                                                                |" + TitleColor.BLUE
				+ "#" + TitleColor.RESET + "   39    |\n" + "|_________" + TitleColor.PINK + "#" + TitleColor.RESET
				+ "|________________________________________________________________________________|" + TitleColor.BLUE
				+ "#" + TitleColor.RESET + "_________|\n" + "|          |" + TitleColor.LIGHTBLUE + "########"
				+ TitleColor.RESET + "|" + TitleColor.LIGHTBLUE + "########" + TitleColor.RESET + "|        |"
				+ TitleColor.LIGHTBLUE + "########" + TitleColor.RESET + "|        |        |" + TitleColor.BROWN
				+ "########" + TitleColor.RESET + "|        |" + TitleColor.BROWN + "########" + TitleColor.RESET
				+ "|          |\n"
				+ "|          |        |        |        |        |        |        |        |        |        |          |\n"
				+ "|   JAIL   |   9    |   8    |   7    |   6    |   5    |   4    |   3    |   2    |    1   |    GO    |\n"
				+ "|          |        |        |        |        |        |        |        |        |        |          |\n"
				+ "|__________|________|________|________|________|________|________|________|________|________|__________|\n");

	}

	/**
	 * prints the welcome to the monopoly game
	 */
	public void printWelcome() {
		System.out.println("                                  -+ooo:       ++//++                                    \n"
				+ "                               -sy+-`dMN-     :+y++so                                    \n"
				+ "                             -hN:    -NMd`    +:y.                                       \n"
				+ "                            oNMMN:   .sNdy`   .y:y:                                      \n"
				+ "                           +mMMMMN+++-`-mMh.-h+`o++s`                                    \n"
				+ "                           +s+NMMhs. `.-+ydmm-   -s:s:                                   \n"
				+ "                   -:       :homshmysy+/:-`.ss`   `/o+s`                                 \n"
				+ "                  osh        :dsdso+:o+`    `:y     `s+sos+-                             \n"
				+ "              ./syh`h`       .d++y-  - `    :/o/      oy+s:/o`                           \n"
				+ "           .+ys:.`o :mo+/.   h/sh//   -s  `/  -y`.   `y-so` :+                           \n"
				+ "          oso++   ` +osMMNh+`d:Ny:.      `.do+/ds+    s`o:o/`o                           \n"
				+ "         .y-/o++oooy+yMMMMMMNddm:   `  -o/``  .-y-   :yh+yysyy+                          \n"
				+ "          `soo/-`` `:dMMMMMMMMMNhy+o`so++  `o/:+s-   .md+hss/.yyy.                       \n"
				+ "                    `./smMMMMMMMNdMd:++//os/.y/    :mMMNooh`  /yy+                       \n"
				+ "                         -yMMMMMMMMMhs+-.`.+mdo++smMMMMMMNo    `oys-                     \n"
				+ "                           -dMMMMMMMd ++++:ohMMMMMMMMMMMMh.      -yy+`                   \n"
				+ "                            `dMMMMMMM/sssosodMMMMMMMMMMm/         `+ys-                  \n"
				+ "                             :MMMMMMMN/`  `:MMMMMMMMMd+`            .s:                  \n"
				+ "                             /MNhdhdMM/    sMMMMMNho-                                    \n" + "   "
				+ TitleColor.GREEN
				+ "Y8b Y8b Y888P 888'Y88 888       e88'Y88   e88 88e       e   e     888'Y88             \n"
				+ "    Y8b Y8b Y8P  888 ,'Y 888      d888  'Y  d888 888b     d8b d8b    888 ,'Y             \n"
				+ "     Y8b Y8b Y   888C8   888     C8888     C8888 8888D   e Y8b Y8b   888C8               \n"
				+ "      Y8b Y8b    888 \",d 888  ,d  Y888  ,d  Y888 888P   d8b Y8b Y8b  888 \",d           \n"
				+ "       Y8P Y     888,d88 888,d88   \"88,d88   \"88 88\"   d888b Y8b Y8b 888,d88" + TitleColor.RESET
				+ "          \n"
				+ "                                                                                         \n"
				+ "                               88P'888'Y88   e88 88e                                     \n"
				+ "                               P'  888  'Y  d888 888b                                    \n"
				+ "                                   888     C8888 8888D                                   \n"
				+ "                                   888      Y888 888P                                    \n"
				+ "                                   888       \"88 88\"                                   \n"
				+ "                                                                                         \n" 
				+ TitleColor.RED
				+ "    e   e       e88 88e   Y88b Y88   e88 88e   888 88e    e88 88e   888     Y88b Y8P     \n"
				+ "   d8b d8b     d888 888b   Y88b Y8  d888 888b  888 888D  d888 888b  888      Y88b Y      \n"
				+ "  e Y8b Y8b   C8888 8888D b Y88b Y C8888 8888D 888 88\"  c8888 8888D 888       Y88b       \n"
				+ " d8b Y8b Y8b   Y888 888P  8b Y88b   Y888 888P  888       Y888 888P  888  ,d    888       \n"
				+ "d888b Y8b Y8b   \"88 88\"  88b Y88b    \"88 88\"   888        \"88 88\"   888,d88    888"
				+ TitleColor.RESET + " 	  \n");
	}

	/**
	 * Prints out the monopoly board in 500 characters
	 * 
	 * @throws InterruptedException
	 */
	public void printLargeBoard() throws InterruptedException {
		Thread.sleep(200);
		System.out.println(
				"`````````````````````````````````````````````````````````````````M+``````````````````````````````````````.M/``````````````````````````````````````:M:``````````````````````````````````````/M.``````````````````````````````````````+M````````````````````````````````````````M+``````````````````````````````````````:M-``````````````````````````````````````/M```````````````````````````````````````+M.``````````````````````````````````````sN`````````````````````````````````````````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"```````````````````````````````````````````````````````````````` M+```````````````````````````````````````M/``````````````````````````````````````:M:``````````````````````````````````````/M```````````````````````````````````````+M `````````````````````````````````````` M+``````````````````````````````````````:M-``````````````````````````````````````/M```````````````````````````````````````+M```````````````````````````````````````sN ````````````````````````````````````````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"`````````````````````````````````````````````````````````````````M+``````````````:`--.--.`.```````````````M/`````````````````.--.`````````````````:M:`````````````.-.--.-..`..`````````````/M.`````````````.-`.`.-...`.`````````````+M``````````````..`...-..``.``````````````M+````````````.:`.:.--.`...`````````````:M-`````````````--`:..-...`.`````````````/M``````````````.:`.:`.`..`.`````````````+M.`````````````:-`:.--...`.`````````````sN`````````````````````````````````````````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"```````````````````````````````````````````````````````````````` M+```````````` s`o+o.o+sshy-`````````````M/```````````````:yyssyy/```````````````:M:````````````.+:/o+:s:yyyh`````````````/M`````````````.+-+ho:s:hyyh`````````````+M ````````````.o:++//s/yshh-```````````` M+````````````s`os/oo+oshy:`````````````:M-````````````-+:o/s/o:hyyh`````````````/M````````````` s`os/`y.yhss`````````````+M`````````````/-+/+o/s-hyyh`````````````sN ````````````````````````````````````````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"````````````````````````````:``````````````````````````````````` M+`````````````//:++-++`/`+``````````````M/``````````````.m++++++d:``````````````:M:`````````````+/-++://:-./`````````````/M``````````````+/.+`://:--:`````````````+M `````````````+/-+/:/+./.+````````````` M+````````````//:./.++`+`+``````````````:M-`````````````++.::://:--:`````````````/M``````````````//:-o o.+`/``````````````+M`````````````.++-+///:+.:-```````````` sN `````````````````````````````````````````````-``````````````````");
		Thread.sleep(200);
		System.out.println(
				"``````````````````````````:`/h/````````````````````````````````` M+```````````````````````````````````````M/``````````````.m++++++d:``````````````:M:``````````````````````````````````````/M```````````````````````````````````````+M `````````````````````````````````````` M+``````````````````````````````````````:M-``````````````````````````````````````/M```````````````````````````````````````+M`````````````````````````````````````` sN `````````````````````````````````````````````-h-````````````````");
		Thread.sleep(200);
		System.out.println(
				"````````````````````````` y:s/ss```````````````````````````````` M+```````````````````````````````````````M/```````````````/yyssyy+```````````````:M:``````````````````````````````````````/M```````````````````````````````````````+M `````````````````````````````````````` M+``````````````````````````````````````:M-``````````````````````````````````````/M````......`````````````````````````````+M`````````````````````````````````````` sN ````````````````````````````````````````````:s+o+:``````````````");
		Thread.sleep(200);
		System.out.println(
				"``````````````````````:o/ssd.:-````````````````````````````````` M+```````````````````````````````````````M/````````````````.-//-.````````````````:M:``````````````````````````````````````/M```````````````````````````````````````+M ````````:/:-`````````.///-```://///-`` M+``````````````````````````````````````:M-``````````````````````````````````````/M```/d++/yy ````````````````````````````+M`````````````````````````````````````` sN ````````````````````-:``````````````..`````:+oo-os `````````````");
		Thread.sleep(200);
		System.out.println(
				"````````````````````.:+ho-./-``````````````````````````````````` M+```````````````````````````````````````M/`````````````````oyssso:``````````````:M:``````````````````````````````````````/M```````````````````````````````````````+M ``````+mNMMNh.``````sNMMMNy`.NMMMNo``` M+``````````````````````````````````````:M-``````````````````````````````````````/M```/h   +h ````````````````````````````+M`````````````````````````````````````` sN ``````````````````:s+m ```````.mhhhhhdh/.````-`-dso+o:``````````");
		Thread.sleep(200);
		System.out.println(
				"```````````````````:s//y+y.`````-+o:```````````````````````````` M+```````````````````````````````````````M/````````````````-d++++osy.````````````:M:``````````````````````````````````````/M```````````````````````````````````````+M ``````MMMMMMMy `````MMMMMMM:hMMMh-```` M+``````````````````````````````````````:M-``````````````````````````````````````/M```:h   +h ````````````````````````````+M`````````````````````````````````````` sN ````````````````:oo`/myoosoos+ymyyyyyyyhdo-````--.:o:::`````````");
		Thread.sleep(200);
		System.out.println(
				"````````````````.o++yyo:-.`````+h-/hh-``--`````````````````````` M+```````````````````````````````````````M/``````````````` dysss+++od````````````:M:``````````````````````````````````````/M```````````````````````````````````````+M ````..mMMMMMMo....--hMMMMMm+MMMs`````` M+``````````````````````````````````````:M-``````````````````````````````````````/M```:m   /m ````````....``````````://-``+M`````````````````````````````````````` sN ``````````````-s+-o- -y-``.h+`/dyyyyyyyyyhds-````-/.````````````");
		Thread.sleep(200);
		System.out.println(
				"```````````````/++o::o..```````.y+``:hsydmh/```````````````````` M+```````````````````````````````````````M/```````````````.s-..hs+++N````````````:M:``````````````````````````````````````/M```````````````````````````````````````+M ``` ymNMMMMMMmmmmmNNNMMMMMNMMMMNy.```` M+``````````````````````````````````````:M-``````````````````````````````````````/M```:M   `m:```````:yyyy:````````y//-oo +M`````````````````````````````````````` sN `````````````.m:+/-`      `m. hhyyyyyyyyyyyhmy-`````````````````");
		Thread.sleep(200);
		System.out.println(
				"`````````````:+:+y+//.```````````:s+-ymhyyhmh-`````````````````` M+```````````````````````````````````````M/``````````````````./ho++sy````````````:M:``````````````````````````````````````/M```````````````````````````````````````+M `````oMMMMMMMMMMMMMMMMMMMMMMMMMMMy ``` M+``````````````````````````````````````:M-``````````````````````````````````````/M````M`   .ssoo+oosy/:::sh++++++yys/s d-+M`````````````````````````````````````` sN `````````:///:/oo.`    `.-sN-:Nyyyyyyyyyyyyyyhmy:```````````````");
		Thread.sleep(200);
		System.out.println(
				"``````````-//+ss:--``````````````-omhhyyyyyyNh ````````````````` M+```````````````````````````````````````M/````````````````-+ss++oys`````````````:M:``````````````````````````````````````/M```````````````````````````````````````+M ````` dMMMMMMMMMMMMMMMMMMMMMMMMMMy ``` M+``````````````````````````````````````:M-``````````````````````````````````````/M``` hs      ``````.    -.........d-y.so+M`````````````````````````````````````` sN ````````+h+/////++++++o+/--oshyysydmmmmmmmmmyyyhmy:`````````````");
		Thread.sleep(200);
		System.out.println(
				"`````````/s:-:h./```````````.+syydhyyyyyyyhms.`````````````````` M+```````````````````````````````````````M/``````````````:syo++osy:``````````````:M:``````````````````````````````````````/M```````````````````````````````````````+M ````` hMMMMMMMMMMMMMMMMMMMMMMMMMd-```` M+``````````````````````````````````````:M-``````````````````````````````````````/M`````os:.`      ``    ``         m.h.oo+M`````````````````````````````````````` sN `````````````````````.````ss:h+/+:smyNhyhhhMyyyyyhdy:```````````");
		Thread.sleep(200);
		System.out.println(
				"`````````.+/-/o`````--.`````mdhddhhhddhyyymm+-`````````````````` M+```````````````````````````````````````M/````````````:yyo++oyy/````````````````:M:``````````````````````````````````````/M```````````````````````````````````````+M `````.NMMMMMs+ooooooooooo+mMMm//`````` M+``````````````````````````````````````:M-``````````````````````````````````````/M``````./++oooooohh++oohh+ooooos+oo:y d:+M`````````````````````````````````````` sN ``````````````````````````-+hmd+-syNdmmyyyyMhyyyyyyhmy``````````");
		Thread.sleep(200);
		System.out.println(
				"````````````:-````.yyoyo.``+NyNy-`.``/Nhyyyhdmh+.``````````````` M+```````````````````````````````````````M/``````````-yy+++oys:``````..``````````:M:``````````````````````````````````````/M```````````````````````````````````````+M `````dMMMMMMN-```````````:MMMM:``````` M+``````````````````````````````````````:M-``````````````````````````````````````/M```````````````-mm////md``````o+  /`/y +M`````````````````````````````````````` sN ``````````````````````-+ss++dyo+osdds.NyyyhNyyyyyyyhh+``````````");
		Thread.sleep(200);
		System.out.println(
				"``````````````````.hy.`/dsymhhM` o/y` yNyyyyyyhdms-````````````` M+```````````......```..`.`.`.```````````M/`````````/ho++oyo-`````.+yyh-`````````:M:```````````.`..`.`.`..`..``.``````````/M````````````.`..`.`.`..`..``.``````````+M ```-dMMMMMMMMh ``````````mMMMMm``````` M+``````````..`..````..`.`.`.```````````:M-```````````.`..`.`.`..`..``.``````````/M````````````````:so::oo-``````.h/--oy``+M`````````..`.`.`.`..`...``....```````` sN ``````````````````````d-.::/:    ` `d Myyydmyyyyyhdo````````````");
		Thread.sleep(200);
		System.out.println(
				"````````````````````/ys-omhyyhM: -:: `dmyydmhyyyyhmo```````````` M+``````````.oyo.ss/o:+y.s/ooo```````````M/````````:h+++ss-``````shs++od.````````:M:``````````:y/y-+s/y.os-s/so/``````````/M```````````:s/s:/s/y-o+:s:so/``````````+M ``/NMMMMMMMMMo``````````oMMMMMMo`````` M+`````````.oyo-y/o:+/d`o+ooo```````````:M-``````````:y:y-+s/y.oo-s/so/``````````/M`````````````..``.ysho``..``````://-```+M`````````+o.h:s-y+o//sy-oo:h/o```````` sN ````````````````````.`o/:o+        `N MyyyNdyyhhh+.`````````````");
		Thread.sleep(200);
		System.out.println(
				"``````````````````````yNdyyyyyhms/:/oddyhms:smhyyydm ``````````` M+``````````.:+/`++`+-://`+`+````````````M/``````` ho++oy`````````+h+++sy ```````:M:``````````-/-+`/+`o.:/+`+-/```````````/M```````````-/-/.:+`o./:/`/-/```````````+M ``ohhhhhhhhy/`````````` dMMMMMMm`````` M+``````````:++`+:-:::o:.:.+````````````:M-``````````-+-+`/+`o.:/+`+-/```````````/M````````````s++yoohosyoso+y````````````+M`````````-+-+.+-/-//:/+`-/`://```````` sN ```````````````````+doy+h-y      `.`y MyyyNyyyo:````````````````");
		Thread.sleep(200);
		System.out.println(
				"```````````````````` yNyyyyyyyyyhdddhydms.   +MdyyN+```````````` M+`````````+`/:-:/.//`/ +`/-/://`````````M/``````` d+++s+````````` d+++om ```````:M:`````````/../+`//`-/-//:/`+:``````````/M```````````+/-:/:+`/`//-+-+-+``````````+M ````````````````````````.++++++.`````` M+````````:/-/--:.-/:`/:o //../`````````:M-````````-:+/:/-/`+ +.:`//+`-:`````````/M``````````` h/:hsyh+oysh/:h.```````````+M```````````/`+--.:-:++`-+-+.-````````` sN ````````````````..:sNoh.        `.sh:-Nydd:`````````````````````");
		Thread.sleep(200);
		System.out.println(
				"``````````````````````omhyyyhmdyyyyydms`  `/hmhyyNh ```````````` M+````````/o.+y.`o+:/-s`h/y.+/oy`````````M/````````ss+++d.````````+h+++ss ```````:M:``````````yy`y/s.ys-+o.+s/ho``````````/M```````````/o/o/`+-s/ss +.:/y``````````+M `````````````````````````````````````` M+`````````.oo//o:s:oh. s`y`yy``````````:M-````````-oss`.os/h`y-o+s.s.oo.````````/M`````````````--```/dd.``.-`````````````+M`````````` s/h//o+/oy.ys`h-h`````````` sN ``````````````yddddhydNo  ./-  oy`s-:hhs/```````````````````````");
		Thread.sleep(200);
		System.out.println(
				"```````````````````````.oyy+::NdyyyhMo   /dmhyyhNo`````````````` M+````````-`-...:.....:.-`..:...`````````M/`````````do++oyo-````:yy+++od.````````:M:`````````````-`.`.``.`:.-`..``````````/M```````````--...:.`-`.-`.`..-``````````+M `````````````````````````````````````` M+````````::`--:.``.`.``-.-.````````````:M-`````````-.`:-`-`.--..`.--.``.````````/M```````````````````````````````````````+M```````````-`..-.`--.`.``````````````` sN ````:++.``````.smdyyyyhm: `. -+s:s. m-``````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"``````````````````````````````-mdyyyhmdommhyyydm:``````o-``````` M+```````````````````````````````````````M/`````````.ys++++syssys++++sh.`````````:M:``````````````````````````````````````/M```````````````````````````````````````+M `````````````````````````````````````` M+``````````````````````````````````````:M-``````````````````````````````````````/M```````````````````````````````````````+M`````````````````````````````````````` sN `` y:``o+````````+hmhyyymy`   -:-` .d-``````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"````````````````````````````````hdyyyyhdyyyhmmo``````+`.ds-````` M+```````````````````````````````````````M/```````````:syo++++++++oyy/```````````:M:``````````````````````````````````````/M```````````````````````````````````````+M `````````````````````````````````````` M+``````````````````````````````````````:M-``````````````````````````````````````/M```````````````````````````````````````+M`````````````````````````````````````` sN ```o+..s/-+````````.+hmdydm:   .-/ss.```````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"`````````````````````````````````smyyyyhdmNy/``````++sh+`oo````` MhsssssssssssssssssssssssssssssssssssssssM/`````````````./osssssoo/.`````````````:MysssssssssssssssssssssssssssssssssssssshMssssssssssssssssssssssssssssssssssssssshM `````````-..``.::`...-..-``-`.```````` MhsssssssssssssssssssssssssssssssssssssshMysssssssssssssssssssssssssssssssssssssshM````````````.-.`..`.`-.````````````````+MsssssssssssssssssssssssssssssssssssssssdN ````.:y:s+`````````````:sdmN-sy+-.``````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"``````````````````````````````````+yyhyo/-``````.oss.y/y+``````` MdhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhM/``````````````````````````````````````:MhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhdMhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhdM ````````y.so+ss``s+s-.oo/oy.ys ``````` Ms::::::::::::::::::::::::::::::::::::::+M+::::::::::::::::::::::::::::::::::::::oM``````````` s+-os:oos.:++oy.```````````+M:::::::::::::::::::::::::::::::::::::::yN ```````s/``:o++-``````````.+y:``````````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"``````````````````````````````````````````````.oo.:oos`````````` MdyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyM/`````````.-.--`.`.-``-.`.-:```````````:MhyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyhMyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyydM ````````/:/`+`://::+.`// /.`/+```````` Mo....................................../M:......................................+M````````````:+::/://+:/::o:+```````````+M-......................................yN ``````````-y```h.```````````````````````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"``````````````````````````````````````````````+.sy+.o.`````````` MdyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyM/``````````/y`.oy/y:os-y:s`.o``````````:MhyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyhMyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyydM ````````````-/:/-`/++`.-/+```````````` Mo....................................../M:......................................+M`````````````++.:/:-+-+./+`````````````+M-......................................yN ```````````o+/+s+++.````````````````````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"````````````````````````````````````````````````.+o.```````````` MdyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyM/`````````.:+:/-/`o :-`+`+:/-``````````:MhyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyhMyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyydM ````````````.s.:+`-ys``-yh ``````````` Mo....................................../M:......................................+M```````````` yd.oso/:y:oooo````````````+M-......................................yN ``````````````:-o:`d ```````````````````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"`````````````````````````````````````````````````.`````````````` MdyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyM/``````````````````````````````````````:MhyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyhMyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyydM ``````````````-.```-.```..```````````` Mo....................................../M:......................................+M`````````````..`....`.`....````````````+M-......................................yN ``````````````.o//o+````````````````````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"```````````````````````````````````````````````````````````````` MdyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyM/``````````````````````````````````````:MhyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyhMyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyydM `````````````````````````````````````` Mo....................................../M:......................................+M```````````````````````````````````````+M-......................................yN ````````````````..``````````````````````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"```````````````````````````````````````````````````````````````` MdyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyM/ `````````````````````````````````````:MhyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyhMyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyydM `````````````````````````````````````` Mo....................................../M:......................................+M`````````````````````````````````````` +M-......................................sN ````````````````````````````````````````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyymhhhhhhhhhhhhhhMmhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhdhyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyhddhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhddhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhddyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyydhyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyhdhyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyhdyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyhdhyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyydMhdddddddddddddmyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy");
		Thread.sleep(200);
		System.out.println(
				".................................................-Mo/////////////M+`............................................................................................................................................................................................................................................................................................................................................................................`+MssssssssssssshM``................................................");
		Thread.sleep(200);
		System.out.println(
				"`````````````````````````````````````````````````.Mo////////////:M+``````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````+MosssssssssssohM `````````````````````````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"`````````````````````````````````````````````````.Mo////////////:M+``````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````+MosssssssssssohM `````````````````````````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"`````````````````````````````````````````````````.Mo////////////:M+``````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````+MosssssssssssohM `````````````````````````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"````````````````````````````````````````/+o.`````.Mo////////////:M+``````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````+MosssssssssssohM `````````````````````````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"```````````````````````````````````/:.``+s+:`````.Mo////////////:M+``````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````+MosssssssssssohM ``````````:`.:```````````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"``````.-.``````````````````````````-ho-`o+-+`````.Mo////////////:M+``````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````+MosssssssssssohM `````+..o ooos `````````````````````````...``````");
		Thread.sleep(200);
		System.out.println(
				"`````.yd:``````````````````````````o+/-`:++:`````.Mo////////////:M+```````````````````````````````````````````````````````````````````````````````.yh+```````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````+MosssssssssssohM `````/+++`-::o`````````````````````````-+:+-`````");
		Thread.sleep(200);
		System.out.println(
				"`````-oh/``````````````````````````/++:`+s+:`````.Mo////////////:M+`````````````````````````````````````````````````````````````````````````````--:mNh ..````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````+MosssssssssssohM `````o:+-`/os+`````````````````````````.o+o.`````");
		Thread.sleep(200);
		System.out.println(
				"`````/+++.`````````````````````````o/-/```:-`````.Mo////////////:M+````````````````````````````````````````````````````````````````````````````.::-..`-/+````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````+MosssssssssssohM `````///+`-o+:`````````````````````````.///-`````");
		Thread.sleep(200);
		System.out.println(
				"`````://s``````````````````````````:so.`:o+-`````.Mo////////////:M+`````````````````````````````````````````````````````````````````````````.- ho ..``. /y.``````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````+MosssssssssssohM `````o-.y o:/o`````````````````````````-+/+:`````");
		Thread.sleep(200);
		System.out.println(
				"`````-://.`````````````````````````+o+:`+:o/`````.Mo////////////:M+`````````````````````````````````````````````````````````````````````````//-``.:/:.```-`/s ```````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````+MosssssssssssohM ``````+y:`/o/:``````````````````````````:d+.`````");
		Thread.sleep(200);
		System.out.println(
				"`````//-s.`````````````````````````s//-`s.:+`````.Mo////////////:M+`````````````````````````````````````````````````````````````````````.-`d+`..:/:-:/:.``-`-s-``````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````+MosssssssssssohM `````-oo/`-/o/``````````````````````````/dy.`````");
		Thread.sleep(200);
		System.out.println(
				"``````.-.``````````````````````````oo:/`/h+/`````.Mo////////////:M+`````````````````````````````````````````````````````````````````````/+.``.:/:.``.-:/:.```/.:s ```````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````+MosssssssssssohM `````o/s:`-oy:```````````````````````````...`````");
		Thread.sleep(200);
		System.out.println(
				"```````````````````````````````````:.`-`s+s-`````.Mo////////////:M+`````````````````````````````````````````````````````````````````..`d+`..:/:.`......-::-.``-.`o-``````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````+MosssssssssssohM ````````````::```````````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"````````````````````````````````````````///-`````.Mo////////////:M+`````````````````````````````````````````````````````````````````/s```.:/:.`..........-::-```.+-`o-```````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````+MosssssssssssohM `````````````````````````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"`````````````````````````````````````````````````.Mo////////////:M+`````````````````````````````````````````````````````````````...d/ ..:/:.`.`............-::-```-:.:-``````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````+MosssssssssssohM `````````````````````````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"`````````````````````````````````````````````````.Mo////////////:M+`````````````````````````````````````````````````````````````+s`.`.:/:.`..................--:-````s+ /:```````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````+MosssssssssssohM `````````````````````````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"`````````````````````````````````````````````````.Mo////////////:M+```````````````````````````````````````````````````````````-d:`..:/:.`.`....................----```.::-.``````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````+MosssssssssssshM `````````````````````````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"`````````````````````````````````````````````````.Mo////////////:M+`````````````````````````````````````````````````````````+s`-`.:/:.```......................-----.````sy -:```````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````+MosssssssssssshM``````````````````````````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyhMdddddddddddddhM+```````````````````````````````````````````````````````-y-`-.:/:.``........................--------.````::-```````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````+MmmmmmmmmmmmmmNMyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy");
		Thread.sleep(200);
		System.out.println(
				"`````````````````````````````````````````````````.Mo////////////:M+````````````````````````````````````````````````````./o`:`.:/:-...........................-----------.````+h`--```````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````+MosssssssssssshM`````````````````````````````````````````````````.");
		Thread.sleep(200);
		System.out.println(
				"`````````````````````````````````````````````````.Mo////////////:M+```````````````````````````````````````````````````-s-`-.:/:-.``......................-----------------.````::/ ``````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````+MosssssssssssohM `````````````````````````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"`````````````````````````````````````````````````.Mo////////////:M+````````````````````````````````````````````````./+`/`.:/:-.`..............//oo:......------------------..````:d-.-```````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````+MosssssssssssohM `````````````````````````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"````````````````````````````````````````...-`````.Mo////////////:M+```````````````````````````````````````````````-s..-.:/:-.``...........:/+o-.+./+/.----------------------...```..-+```````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````+MosssssssssssohM `````````````-```````````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"````````````````````````````````````````++++`````.Mo////////////:M+````````````````````````````````````````````.++./`.-/:-............-////.-:++oo:::o/------------------::::-...``.`-h:`.```````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````+MosssssssssssohM ``````````.oy.```````````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"```````````````````````````````````//-``o/./`````.Mo////////////:M+```````````````````````````````````````````-o..-`-//-.``.......-://:---+o+o++++oy.-:o/-------------:::::::::-..````-.-+```````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````+MosssssssssssohM `````....`-:oo +.-+``````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"``````-.```````````````````````````:so-`/+o-`````.Mo////////////:M+````````````````````````````````````````.++-o``-//-.``......::/--:-/o+o++o++ooo/:.::`/o/---------:::::::::::::-..```-..s/`````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````+MosssssssssssohM `````-/+-`-os:`/++o`````````````````````---``````");
		Thread.sleep(200);
		System.out.println(
				"`````.od/``````````````````````````+o/.`os+:`````.Mo////////////:M+```````````````````````````````````````-o..-`-//-..``....+//../:++oo++++s:.o----/+oos:/h:------:::::::::::::::::-..````/-.+-```````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````--.````````````````````````````````````````````````````````````````````````````````````````````````+MosssssssssssohM `````+++/`:+/+`-::o````````````````````-+:+-`````");
		Thread.sleep(200);
		System.out.println(
				"`````.sh/``````````````````````````/++:`:oo.`````.Mo////////////:M+````````````````````````````````````-//-o.`-//-.``.....`y//: /so++++++o+s.-/:oso+ooos:+/------::::::::::::::::://:-..```.-.:o+.`````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````.`:sy/:```````````````````````````````````````````````````````````````````````````````````````````````+MosssssssssssohM `````o:::`-::s /oy/````````````````````-o/o-`````");
		Thread.sleep(200);
		System.out.println(
				"`````.+++``````````````````````````o/-/`++/:`````.Mo////////////:M+```````````````````````````````````-+`:.`-//-.``.......+/..--.-+oo+oo+::-/o+o./sosdh/+:----:::::::::::::::::///////:.````. hMN+```````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````.+my//o+-//`````````````````````````````````````````````````````````````````````````````````````````````+MosssssssssssohM `````+oy+`/+/o`-o+/````````````````````.//+.`````");
		Thread.sleep(200);
		System.out.println(
				"`````:/++``````````````````````````:so-`s/:+`````.Mo////////////:M+````````````````````````````````-/-:s``-//-.`.........-s`:o-.-:`-++--:/+ooo////hmMh:+-----:::::::::::::::::///////:-.````.+:::``````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````.+mMmNNy-.oso/+```````````````````````````````````````````````````````````````````````````````````````````+MosssssssssssohM `````+:-o +-.s o:/o ```````````````````-//o-`````");
		Thread.sleep(200);
		System.out.println(
				"`````-+++``````````````````````````+++:`+/:+`````.Mo////////////:M+```````````````````````````````./`/.`-:/-.``..........s`:o+oo-`-:: .so++ooo+//-sms//---::::::::::::::::::///////:-.````:h.--``````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````.+dMmy+sdNNy//+y+.-`````````````````````````````````````````````````````````````````````````````````````````+MosssssssssssohM `````///o`/sy+ /o+:`````````````````````:ds.`````");
		Thread.sleep(200);
		System.out.println(
				"`````-::/``````````````````````````s//-`s///`````.Mo////////////:M+````````````````````````````-/-/y``-:/:.`............/--o++++o+:.-:.-so+++-//:+o++:---:::::::::::::::::////////-.````./--```````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````./dMmyo:::+ydNNs:/-sy.-```````````````````````````````````````````````````````````````````````````````````````+MosssssssssssohM `````-/s:`::oo`-/o/`````````````````````/hs.`````");
		Thread.sleep(200);
		System.out.println(
				"```````````````````````````````````oo:/`ss//`````.Mo////////////:M+```````````````````````````.:`/.`-:/:.`.............-+ /o++osooo+--::/+:./yo:oh/s:--::::::::::::::::::///////-.````/y.::```````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````/dMmho:://+++oydNNs./+:s--.````````````````````````````````````````````````````````````````````````````````````+MosssssssssssohM `````-o/:`.+h.`-oy-``````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"```````````````````````````````````-``.`+/./`````.Mo////////////:M+````````````````````````-/.+y``-:/:.``..............s`:o++os:+ss+os/o: .  sooyho/s-::::::::::::::::////////-.````.:-:````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````/dMmho::/+oossssosymNNs-.so/+.```````````````````````````````````````````````````````````````````````````````````+MosssssssssssohM ``````````+``s ``-:``````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"``````````````````````````````````````` yo:o`````.Mo////////////:M+```````````````````````.:`/.`.:/:.``...............o+.s+++y+:+ss+oo/.:    /so+o:/+/::::::::::::::////////-.````/s`//```````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````/dMmho/:/+ossyyyyyyssyymMNs:+:+.```````````````````````````````````````````````````````````````````````````````````+MosssssssssssohM ``````````-:::```````````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"`````````````````````````````````````````````````.Mo////////////:M+````````````````````./`oh``.:/:.`..................-oo.:ooo+s/s+oo/`     `.h+o+/ /s::::::::::::////////-.````-:-:````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````:dMNho/:/+ossyyyyyyyyyyyyyhmMNo.`````````````````````````````````````````````````````````````````````````````````````+MosssssssssssohM `````````````````````````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"`````````````````````````````````````````````````.Mo////////////:M+````````````````````:`+.`.:/:.``.....................-os.:osso+oo//- -../o/::+oo.:/+::::::::::///////-.````/s`//```````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````:hMNho/:/+osyyyso/::yyyyyyyyyyymMNo.```````````````````````````````````````````````````````````````````````````````````+MosssssssssssohM `````````````````````````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"`````````````````````````````````````````````````.Mo////////////:M+````````````````./`sh `.:/:.`........................---o+-/s+oo/`    .y+.:-/+o++ /s:::::::////////:.````:.-:````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````:hMNho/:/+ossyo/-.`` `+yyyyyyyyyyyhmMNo``````````````````````````````````````````````````````````````````````````````````+MosssssssssssohM `````````````````````````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"-------------------------------------------------:Ms++++++++++++/M+```````````````-/`+``.:/:.``........................------o/-/o/Md://++h.--+o+++o.-/o::::////////:.````/o`//```````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````-hMNho/:/+ossyyhm-`     `syyyyyyyyyyyyhmMNo````````````````````````````````````````````````````````````````````````````````+MsssssssssssssdM.-------------------------------------------------");
		Thread.sleep(200);
		System.out.println(
				"yyyysyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyysM+``````````````NMM:``.//:.`.......................----------oms:y/:+o+++s/ooo++++o+ /s::////////:.````:.-/````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````-hMNdo/:/+ossyyyyyMd.     `-yyyyyyyyyyyyyyhmMN+``````````````````````````````````````````````````````````````````````````````+Myyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyysyyys");
		Thread.sleep(200);
		System.out.println(
				"```````````````````````````````````````````````````````````````` M+``````````````/o+`.:``-//-......................-----------s.-./o++o+++++++++++o+:.+o+///////:.````/+ ++```````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````-hMNdo/:/+ossyyyyyyyhMy.     `:yyyyyyyyyyyyyyyhmMN+````````````````````````````````````````````````````````````````````````````+M ````````````````````````````````````````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"```````````````.------::-``````````````````````````````````````` M+`````````````````s/`:.``-::-..................--------------dooh+o++o++++++o+o/.-/os///////:.````:`:/````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````-hMNdo/:/+ossyyyyyyyyyymM+.     .+yyyyyyyyyyyyyyyyhmMN+``````````````````````````````````````````````````````````````````````````+M ````````````````````````````:::+/.``````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"``````````````.o///:..:/:+``````````````````````````````-:.````` M+```````````````````.s: -``-::-.............-----------------so+s:h+++++++++o/.-/oo://////:.````:/.++```````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````-yMNds/:/+ossyyyyyyyyyyyyyNN:`     .syyyyyyyyyyyyyyyyyhmMm+````````````````````````````````````````````````````````````````````````+M `````-`````````````````````-/.++oo//-```````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"``````````````o`+++s./+oo/`````````````````````````````+-`o-```` M+`````````````````````o+.-```-::-..........------------------:d++s-o+++o+o+:.-oo+///////:.````:`/+````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````-yMNds/:/+ossyooosssyyyyyyyyyMd:`    `:yyyyyyyyyyyyyyyyyyyhmMm+``````````````````````````````````````````````````````````````````````+M `````+o:.``````````````````::`o+/-+oos:-````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"`````````````/::o++/`so/M/-````````````````````````````-//+.```` M+````````````````````````y+`.``-::-....--------------------::-y:+/:o+++o+--:+o////////:..```::.+/```````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````-yNNds/:/+ossyyy.````....-:://+sh+:     ./yyyyyyyyyyyyyyyyyyyyhmMm/````````````````````````````````````````````````````````````````````+M ````.s-:.``````````````````/--o+++sos:/s/-:--+://```````````````");
		Thread.sleep(200);
		System.out.println(
				"`````````````o.o+s.:+::+Ns/````````````````````````````//:o.```` M+`````````````````````````//-````-:--------------------------::y++y:os+.-/++////////:.````-`++````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````-sNNds/:/+ossyyyys-               ```      -oyyyyyyyyyyyyyyyyyyyyyhNMd/``````````````````````````````````````````````````````````````````+M ````.+//.``````````````````+.-oo++o+o-d:o+oo+++/./``````````````");
		Thread.sleep(200);
		System.out.println(
				"````````````+`+o+o/-+/:::s/:-`````````````````````-/:/`.so/````` M+``````````````````````````- yy ```.---------------------::::::yoos/o.-//+////////:.````:--//```````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````.sNNds/::+ossyyyyyys:                        `-syyyyyyyyyyyyyyyyyyyyyyhNMd/````````````````````````````````````````````````````````````````+M ````./o+`.o::.`````````````/.:++o+++o-/-++s/+o+o:-/`````````````");
		Thread.sleep(200);
		System.out.println(
				"```````````-:-o++o-:oo/:++yo:::..`````````````````//:o-.+s:````` M+`````````````````````````````:::````.-----------------:::::::::s-//:+++/://////:..```-`o+`.``````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````.sNNds/:/+ossyyyyyyyys///:::--.``````````       ./yyyyyyyyyyyyyyyyyyyyyyhyhNMd/``````````````````````````````````````````````````````````````+M ````.sss-./:/-`````````````+./+oo//s:` ./sos+/s+o:-/````````````");
		Thread.sleep(200);
		System.out.println(
				"```````````+`o++++ o+o:-/+s/o/---:--``````````````.:y:.-so:````` M+`````````````````````````````.: +h ```.-------------:-::::::::://+o+/////////:..```--::/```````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````.sNNds+::+ossyyyyyyyyyyydmmmmmmmmmmmdhhhysso+`     -+yyyyyyyyyyyyyyyyyyyyhhhyymMNo`````````````````````````````````````````````````````````````+M ````.:::/`o/-/```````````` o.+++o/.-/`  ./o+++++++-:/```````````");
		Thread.sleep(200);
		System.out.println(
				"``````````//:-.:..--:-/` `-o:-o+/:-:+ ````````````-/o+`.oo-````` M+````````````````````````````````.-::.```.---------::::::::::::::::::///////:..```. s+`.``````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````.oNNdy+::/ossyy+syyyyyyyyyyyyyyyyhhhhhhdddmmmNN:`     :syyyyyyyyyyyyyyyyyyhhhyhNMh:``````````````````````````````````````````````````````````````+M `````/so.`+o/:`````````````s`//+o/-:o   ./:-.:..-..:o.``````````");
		Thread.sleep(200);
		System.out.println(
				"```````````o.-:/::/:+o/`  .+../o+o+-o ````````````/-++`:+//.```` M+``````````````````````````````````+.:h.```.-----:::::::::::::::::////////:-.```-.:-/```````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````.omNmy+::/ossyys:``/syyyyyyyyyyyyyyyyyyyyyyyyyyyNm.     `/yyyyyyyyyyyyyyyyhhhyhNMh:````````````````````````````````````````````````````````````````+M `````-oo../y/.`````````````.:-:.-//s+o:.-:o//`++///`+```````````");
		Thread.sleep(200);
		System.out.println(
				"```````````.+-+++s/os++-```+/-++oo:.+`````````````/:/+.:o+o.```` M+````````````````````````````````````.----```.---::::::::::::::::///////:-.``.. y+`.``````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````.omNmy+::/oosyyyo:`   ./syyyyyyyyyyyyyyyyyyyyyyyyyhMy.     -+yyyyyyyyyyyyyhhhyhNMh:``````````````````````````````````````````````````````````````````+M `````/so-.+o/-````````````````.-::-/+y/o-/oo::o++o-:-```````````");
		Thread.sleep(200);
		System.out.println(
				"````````````.:-++o+++so/---oo+o+oo-.+`````````````.+/s`./y/````` M+``````````````````````````````````````o:.s-```..--::::::::::::///////:-.```-./-:```````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````.omMmy+::/oosyyyy+-`     `./syyyyyyyyyyyyyyyyyyyyyyyydMo.     :oyyyyyyyyyyhhhyhmMd/````````````````````````````````````````````````````````````````````+M `````-oo..+.:/```````````````````..:o++/.++o`+++++`+````````````");
		Thread.sleep(200);
		System.out.println(
				"`````````````.:-o+o+oo+o/h:++++++o../````````````````.`./:/.```` M+````````````````````````````````````````.+-.-```..-::::::::////////:-.`````h+`-````````````````````````````````````````````````````````````````````````````````````````-.-```````````````````````````````````````````.+mMmy+::/+osyyyyyydy-`      `-/syyyyyyyyyyyyyyyyyyyyyyymN/`    `/syyyyyyyhhhyhmMd/``````````````````````````````````````````````````````````````````````+M `````o+o-`...````````````````````````/yh/-//.-o+o.+`````````````");
		Thread.sleep(200);
		System.out.println(
				"``````````````-///////:+/+:soo+++o`::``````````````````.//s.```` M+``````````````````````````````````````````o:`/-```..-:::::///////:-.```..+--``````````````````````````````````````````````````````````````````````````````````````````:-:/-````````````````````````````````````````.+mMmy+::/+osyyyyyyyyhNNs-`      `-+syyyyyyyyyyyyyyyyyys:/yNm:` `./syyyyyyhhhyhmMd/````````````````````````````````````````````````````````````````````````+M ````.o.:/````````````````````````````:oN++o`//oo:::`````````````");
		Thread.sleep(200);
		System.out.println(
				"```````````````````````./+oooo:++s o.``````````````````.-:o.```` M+`````````````````````````````````````````````o/.-```..-:://////:-.`````h+.-```````````````````````````````````````````````````````````````````````````````````````````.::/```````````````````````````````````````.+dMmy+::/+osyyyyyyyyyyyyhNNs-`      `-+syyyyyyyyyyyyyys:. .ohMy::smNmyyyyhhhyhmMd/``````````````````````````````````````````````````````````````````````````+M ````.+-:/````````````````````````````./yo+:.s+++`o``````````````");
		Thread.sleep(200);
		System.out.println(
				"``````````````````````````-/o//o+/.+````````````````````-::````` M+``````````````````````````````````````````````+/.--```..-:///:-.```..+.:``````````````````````````````````````````````````````````````````````````````````````````````-.yo``````````````````````````````````````+dMmy+::/+osyyyyysssosssssyyhNNs-`      `:+yyyyyyyyyyys/.`  `:ydMdNNdhyyyhhhyhmMm+````````````````````````````````````````````````````````````````````````````+M `````:/:.`````````````````````````````+/++.:oo/:+.``````````````");
		Thread.sleep(200);
		System.out.println(
				"`````````````````````````````-:o+::/```````````````````````````` M+```````````````````````````````````````````````.`so`..```.-:-.`````h/.-`````````````````````````````````````````````````````````````````````````````````````````````````mh ```````````````````````````````````/dMmh+/:/+osyyso/-..``   ``.-:/ohNNs-`      .:oyyyyyyys/-`     -+ymdhyyyyhhhyhmMm+``````````````````````````````````````````````````````````````````````````````+M ``````````````````````````````````````-::-:::::::```````````````");
		Thread.sleep(200);
		System.out.println(
				"````````````````````````````````.-`````````````````````````````` M+``````````````````````````````````````````````````::-..`````````./.-```````````````````````````````````````````````````````````````````...``````````````````````````````os.-//::````````````````````````````/dMmho/:/+osys+-`                `.:smNs-`      ./oyyys+-`      `:syyyyyyhhhyhmMm+````````````````````````````````````````````````````````````````````````````````+M ````````````````````````````````````````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"::::/::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::M+```````````````````````````````````````````````````- oh```````.h/.:`````````````````````````````````````````````````````````````````:sdNNd/```````````````````````````-.////+oo/``````````````````````````/dMmho::/+osys/.                      `.omNs-`      ./so:.      `:hMNyyyyhhhyhmMm+``````````````````````````````````````````````````````````````````````````````````+M::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::/:::/");
		Thread.sleep(200);
		System.out.println(
				"+++++++++++++++++++++++++++++++++++++++++++++++++oMhssyyyyyyyyyysM+``````````````````````````````````````````````````````.+:...-/.-``````````````````````````````````````````````````````````````````+dNNNNNdNh-`````````````````````````:/-     .`````````````````````````/dMNho/:/+osyy+.`       ``.:/+/:..`       `-sNNs-`      -.      `:hMNdyyyyhhyymMmo````````````````````````````````````````````````````````````````````````````````````+MhhhhhhhhhhhhhmM++++++++++++++++++++++++++++++++++++++++++++++++++");
		Thread.sleep(200);
		System.out.println(
				"`````````````````````````````````````````````````.Mo////////////:M+```````````````````````````````````````````````````````:`/mNy-:`````````````````````````````````````````````````````````````````/mNNNNNNysdNNy:`.ohmdo`````````````````:.`..   ```````````````````````:dMNho/:/+osyyy:`      `.+hmMMMNNMMmho.`      ./dNNs-`          .:hMNdyyyhhhhymMNo``````````````````````````````````````````````````````````````````````````````````````+MosssssssssssshM `````````````````````````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"`````````````````````````````````````````````````.Mo////////////:M+`````````````````````````````````````````````````````````-yh+`````````````````````````````````````````````````````````````````.hMNNNNNNdhysydNNmNMMMMMs``````````````.`.---.` ``````````````````````:hMNho/:/+ossyyy:`      .sNNmdhyyyyyyhdNNs-`     ./ydNNs:`      .:hMNdyyyhhhyydMNo.```````````````````````````````````````````````````````````````````````````````````````+MosssssssssssohM `````./o:````````````````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"`````````````````````````````````````````````````.Mo////////////:M+`````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````:NNNNNNNNmNmdhysyhmNMMMMMm``````````````...`-..-`````````````````````:hMNho/:/+ossyyyyo`     `-mNdyyyyyyyyyyyyyhmN:`     -syydNNs:`  ./hMNdyyyhhhhydMNo.`````````````````````````````````````````````````````````````````````````````````````````+MosssssssssssohM `````./o/````````````````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"`````````````````````````````````````````````````.Mo////////////:M+````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````/MNNNNNNNNMMNNmdhyyydmNMMM+....```````/dy/`..-...```````````````````-hMNho/:/+ossyyyyyy-`     .mmyyyyyyyyyyyyyyyyydN-`    `/hyyydMms-:yMNdyyyyhhhydMNs.```````````````````````````````````````````````````````````````````````````````````````````+MosssssssssssohM `````/oo+````````````````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"```````````````````````````````````````.+//+`````.Mo////////////:M+```````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````:MMMNNNNNMMMMMMNNmddddhhmMN/:..--..-`+dmdddNh:.d-``````````````````-hMNdo/:/+ossyyyyyyyh.     `oNyyyyyyyyyyyyyyyyyyyds.     -myyyyydMmNMdyyyhhhhydMMs.`````````````````````````````````````````````````````````````````````````````````````````````+MosssssssssssohM `````-ss:````````````````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"````````````````````````````````````````+/-/`````.Mo////////////:M+`````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````` dMMNNNNNNNNMMMMMNNNNNmmhNMd+..------:NmddmmNM+ h+````````````````-hMNds/:/+ossyyyyyyyyyd.     .sdyyyyyyyyyyyyyyyyyyyyh.     -NyyyyyyyddyyyyhhhydMMs.```````````````````````````````````````````````````````````````````````````````````````````````+MosssssssssssohM ``````:+/`s:/y ``````````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"`````./+-``````````````````````````/y//`:..:`````.Mo////////////:M+`````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````` mMMMMMNNNNNNNNNNNMMMMMNNMNo/---/s:.--dNNNNMd-``oy``````````````-yMNds/:/+ossyyyyyyyyyyym.     `/dyyyyyyyyyyyyyyyyyyyyo.    `:MyyyyyyyyyyhhhhydNMs.`````````````````````````````````````````````````````````````````````````````````````````````````+MosssssssssssohM `````:+o:`:+++`````````````````````````.:::.`````");
		Thread.sleep(200);
		System.out.println(
				"`````.sd-``````````````````````````+/o-`-````````.Mo////////////:M+````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````:+ymMMMMMNNNNNNMMMMMMMNmo/:...yy:...-oMMMo````:d ```````````-yMNds/:/+ossyyyyyyyyyyyyyd+`     -syyyyyyyyyyyyyyyyyyys:     .oNyyyyyyyyyhhhydNMy.```````````````````````````````````````````````````````````````````````````````````````````````````+MosssssssssssohM `````.os:`://+`````````````````````````.o/+-`````");
		Thread.sleep(200);
		System.out.println(
				"`````./+/``````````````````````````o-```so:-`````.Mo////////////:M+````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````.+yNMMMNNNNMMMMMMNh///-......``.:`yM:``````N``````````-yNNds/:/+ossyyyysooooosyyyyhm-`     -+yyyyyyyyyyyyyyyyyo:`    `:NdyyyyyyyhhhydNMy.`````````````````````````````````````````````````````````````````````````````````````````````````````+MosssssssssssohM `````:::y :+y:`````````````````````````-ooo:`````");
		Thread.sleep(200);
		System.out.println(
				"`````.s/o``````````````````````````:yo.`:yo.`````.Mo////////////:M+```````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````.mNMMMMMMMMMNdo//:--.``..``.:- /h ``````h-```````.yNNds/:/+ossyys+:.````````-/oymh-`     ./oyyyyyyyyyyyyys/-     `:dNyyyyyyhhhydNMy-```````````````````````````````````````````````````````````````````````````````````````````````````````+MosssssssssssohM `````:o/:`/+/+`````````````````````````-+/+-`````");
		Thread.sleep(200);
		System.out.println(
				"`````//so.`````````````````````````o//:`++/``````.Mo////////////:M+```````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````oMMMMMMMMNdy++/+--..-.``..```  om```````o+`````-sNNds/:/+ossyyo-`             `./md:.      .:/osssyssss+:-`     ./dNyyyyyhhhydNMy-`````````````````````````````````````````````````````````````````````````````````````````````````````````+MosssssssssssohM `````+-// o/+s ````````````````````````./o/.`````");
		Thread.sleep(200);
		System.out.println(
				"`````://o.`````````````````````````o::+`os+``````.Mo////////////:M+`````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````` yMMMNNmhy++//:hd:-.../:--`   `:NMs``````:s```.sNNds/:/+ossyyo-`                 `-hNo-`       `..----.``      .:sNNyyyyhhhydNMy-```````````````````````````````````````````````````````````````````````````````````````````````````````````+MosssssssssssohM `````+o:s`-+s-``````````````````````````:mo.`````");
		Thread.sleep(200);
		System.out.println(
				"`````.::/``````````````````````````+/./`yo+:`````.Mo////////////:M+````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````:++o++///+/:-/-.``..```  `.-/dMMN/``````y .sNNds+:/+ossyyo:`       ./syyo/``     .sNmo:.`                 `./smNdyyyhhhyhNMh-`````````````````````````````````````````````````````````````````````````````````````````````````````````````+MosssssssssssohM `````-+o-`:/+/``````````````````````````-s/.`````");
		Thread.sleep(200);
		System.out.println(
				"````````````````````````````````````````o::/`````.Mo////////////:M+``````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````-+//////::-....--    `/y+:-mNMNN.`````-sNNds+::/ossyyo:`      `-yNNNmmNNm/`    `-ydNNho/.```       ``.-/odNNmhyyhhhyhNMh:```````````````````````````````````````````````````````````````````````````````````````````````````````````````+MosssssssssssohM `````:oo+`./s-```````````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"````````````````````````````````````````o+-/`````.Mo////////////:M+``````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````/+/////://::::-...``.+s/:--yNMNMy```.omNdy+://ossyyy:.       `/NNdyyyyyydN:`    -oyyhmNNmdyso+++++osydmNNmdhyyhhhyhNMh:`````````````````````````````````````````````````````````````````````````````````````````````````````````````````+MosssssssssssohM `````-os:````.```````````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"`````````````````````````````````````````````````.Mo////////////:M+```````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````.--.:/+osy.```.-//////::/:-mMNNM-.omNmy+::/ossyyyyhd/`       `:oyyyyyyyyh/.    .oyyyyyhdmmNNNNNNNNNmmdhyyyyhhhyhNMh:```````````````````````````````````````````````````````````````````````````````````````````````````````````````````+MosssssssssssohM `````+::o````````````````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"`````````````````````````````````````````````````.Mo////////////:M+`````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````-hdd/.....-+so+sy::  oNNNMdmNmy+::/+ssyyyyyyymNd/`       .:syyyyyyo:     -hyyyyyyyyyyyyhhhyyyyyyyyyhhhyhmMd:`````````````````````````````````````````````````````````````````````````````````````````````````````````````````````+MosssssssssssohM `````+o+/````````````````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"`````````````````````````````````````````````````.Mo////////////:M+`````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````-MMMNs-----:Nmmmmy/-``NMMMMmy+::/+osyyyyyyyyyyhmNd/`      `-/syys+-`    -omyyyyyyyyyyyyyyyyyyyyyyhhhyhmMd/```````````````````````````````````````````````````````````````````````````````````````````````````````````````````````+MosssssssssssshM `````++s:````````````````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"`````````````````````````````````````````````````.Mo////////////:M+`````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````:MMNNNy.::--hNNNNNNNdsNMMmy+::/+osyyyyyyyyyyyyyyhmNh/.      `-++:`    `-oNyyyyyyyyyyyyyyyyyyyyyhhhyhmMd/`````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````+MosssssssssssohM `````````````````````````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"+++++++++++++++++++++++++++++++++++++++++++++++++oMhssysysysyssssM+`````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````.MMMNo:.-...-:+hmNNNNMMmy+::/+osyyyss+//:--::/+osshmNh/.      `.     ./hNhyyyyyyyyyyyyyyyyyyyhhhyhmMd/```````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````+MhhhhhhhhhhhhhmM++++++++++++++++++++++++++++++++++++++++++++++++++");
		Thread.sleep(200);
		System.out.println(
				"////////////////////////////////////////////////////////////////:M+````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````` mMMo--........`-/ydNmy+::/+osyso/-.`           `.:/ymNh/.         ./yNNhyyyyyyyyyyyyyyyyyyhhhyhmMd/`````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````+M:////////////////////////////////////////////////////////////////");
		Thread.sleep(200);
		System.out.println(
				"```````````````.```````````````````````````````````````````````` M+``````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````:NM///..........```-:::/+osys/.                    `-omNh/.       ./ddyyyyyyyyyyyyyyyyyyhhhyhmMm+```````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````+M `````````````````````````...````````````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"`````````````` do.``````....```````````````````````````./+o````` M+```````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````/Nso+--..----...`````:osys/.          ````          `-smNh/.      `./syyyyyyyyyyyyyyyhhhyhmMm+`````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````+M `````````````````````````yNms-.``````````.``````````````````````");
		Thread.sleep(200);
		System.out.println(
				"`````````````` MMNy:`.sdmMNmy-``````...````````````````.:ss````` M+````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````:dsh/:::::::::...```.:oo.`       .-oyhdddhs+-`       `:hmNh/.      `-+syyyyyyyyyyyyhhyhmMm+```````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````+M ```````````````````````` hMMMMds/-....-+dM+.--.`````````````````");
		Thread.sleep(200);
		System.out.println(
				"`````````````` MMMMMddMMMMMMMM..:+yhmMMd````````````..`:-//.```` M+``````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````/sho//:::::::---.-:..-      `-smMNmdddddmNMdo.`     `-shmNh/.      .:oyyyyyyyyhhhyhmMm+.````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````+M ```````````````````````` hMMMMMMMMNmmNMMMMmMMMNh.```````````````");
		Thread.sleep(200);
		System.out.println(
				"```````````````:+sdNNMMMMMMMMMmMMMMMMMMM``````````-oss`.+y+````` M+`````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````+mdo/::::::::::-`.:     `oNNdhyyyyyyyyyhdNm+.     `:yyhmNh/-    .+myyyyyyhhhhymMNo.``````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````+M ```````````````````````` hMMMMMMMMMMMMMMMMMMMMMMy ``````````````");
		Thread.sleep(200);
		System.out.println(
				"````````````````+yhhydMMMMMMMMdNNMMMMMMM `````````-+/-`.soo.```` M+```````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````:hMNds++oo++///::-:::    `yNdyyyyyyyyyyyyyyyhNo.     .syyyhNNh/.`:hMdyyyyyhhhydNNo.````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````+M ````..```````````````````sMMMMMMMNMMMMMMMMMMMMMMo```````````````");
		Thread.sleep(200);
		System.out.println(
				"`````.+s/````` dMMMMMMMMMMMMMM: .:+shmmo``````````.o//``/o/````` M+````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````//++`````:hMNho/:/+osyyhyyyso::    `+MyyyyyyyyyyyyyyyyyyyN:`    `/hyyyyhNMhoNNhyyyyhhyydMNo.``````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````+M ````:o::.`````````````````+yddho:/MMMMMMMMhyddy:``````./:/.`````");
		Thread.sleep(200);
		System.out.println(
				"`````.sd:````` NMMMMMMMMMMMMMM:```````````````````-+++./:oo.```` M+````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````//+/```:hMNho/:/+ossyyyyyyyyy-     .hdyyyyyyyyyyyyyyyyyyyds.     -myyyyyyhNMmyyyhhhyydMNo.````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````+M ````-yys-::+::```````````````````+MMMMMMMMo```````````./o/.`````");
		Thread.sleep(200);
		System.out.println(
				"`````:os/``````-hNMMddMMMMMMMM:```````````````````/:..`:/:o.```` M+````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````.oh``````-hMNho/:/+ossysyyyyyyyyh.     .yhyyyyyyyyyyyyyyyyyyyho.    `:NyyyyyyyyhyyhhhyydMNs.``````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````+M ````.+:+.-oos.```````````````````+MMMMMMMMo```````````:s:s:`````");
		Thread.sleep(200);
		System.out.println(
				"`````:+o+.`````````` yMMMMMMMM:```````````````````-syh``:o/````` M+``````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````-ooyds-``-hMNdo/:/+osyyy+..+yyyyyym.     `/hyyyyyyyyyyyyyyyyyyyy:`    .omyyyyyyyyyyhhhydMMs.````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````+M ````:+.+:`oy/.```````````````````+MMMMMMMMo ``````````-/o+-`````");
		Thread.sleep(200);
		System.out.println(
				"`````/+:y.```````````yMMMMMMMM:```````````````````-+:+`/+:+.```` M+````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````-syo/o```-hMNds/:/+ossyyy/.  `.+yyyydo.     ./yyyyyyyyyyyyyyyyyyy/.     :ddyyyyyyyyhhhydMMs.``````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````+M ````./y/..::/.```````````````````+MMMMMMMMdmMMMh-`````.+y+-`````");
		Thread.sleep(200);
		System.out.println(
				"`````-://.`````````` sMMMMMMMM:```````````````````//:o.`+s:````` M+``````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````-ooso::``-yMNds/:/+ossyyys:.     `.+yyyN:`     ./syyyyyyyyyyyyyyys/.     -sNyyyyyyyhhhydNMs.````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````+M ````.:o:..::+-```````````````````+MMMMMMMMMMMMMMm `````:N+``````");
		Thread.sleep(200);
		System.out.println(
				"`````-::/```````/yddhhMMMMMMMM//sdmdh+````````````.oo/`.+/+.```` M+```````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````+o.:ysh.``-yMNds/:/+ossyyyyydy-`      `-+ydm:.     `-/osyyyyyyyyyso/-      -sMhyyyyyhhhydNMy.``````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````+M ````:+oo:```````````````sNNhs+:. /MMMMMMMMMMMMMMy `````/y:.`````");
		Thread.sleep(200);
		System.out.println(
				"```````````````yMMMMMMMMMMMMMMNMMMMMMMo```````````-s+-`.+/-````` M+``````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````oo-od/:``-yNNds/:/+ossyyyyyyyhNNs-`      `-+dNo.`      .-:/+ooo+/:-`      .:hMdyyyyhhhydNMy-````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````+M ```````````````````````.MMMMMMMNNmMMMMMMMMhyhhy/````````````````");
		Thread.sleep(200);
		System.out.println(
				"`````````````` dMMMMMMMMMMMMMMMMMMMMMMy```````````:+-s`.+s/````` M+````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````-`.ooo+``-sNNds/:/+ossyyyyyyyyyyydNNs-`      `-sNd+.`                     .:sNNhyyyhhhydNMy-``````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````+M ```````````````````````.MMMMMMMMNmMMMMMMMMMNNds+:```````````````");
		Thread.sleep(200);
		System.out.println(
				"```````````````-hNMMNmMMMMNmmNMMMMMMMMy ```````````..``.yss.```` M+`````````````````````````````````````````````````````````````````````````````````````````````````````````````````````` o+h:````.sNNds/:/+ossyyyyyyyyyyyyyyydNNs-`      .:yNmo:.`               .-+yNNmyyyhhhydNMy-````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````+M ````````````````````````hMNdhs+-..MMMMMMMMhdMMMMN ``````````````");
		Thread.sleep(200);
		System.out.println(
				"`````````````````..-.oNh/.....-/sdMMMMy ```````````````-o/:````` M+``````````````````````````````````````````````````````````````````````````````````````````````````````````````````.`.ooy++oo .sNNds/:/+ossyyyyyyyyyyyyyyyyyyydNNs:`      .:yNNmyo/-..````...:+shmNNdhyyhhhydNMh-``````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````+M `````````````````````````..```````:ymmNmh+``:yNMN ``````````````");
		Thread.sleep(200);
		System.out.println(
				"`````````````````````..```````````-smNs````````````````.++-````` M+`````````````````````````````````````````````````````````````````````````````````````````````````````````````````-o/+y+::-:-sNNds+::+ossyyyyyyyyyyyyyyyyyyyyyyydNNs:.      ./sdmNNNmmdddddmNNNNmdhyyyhhhyhNMh:````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````+M ````````````````````````````````````....``````.od ``````````````");
		Thread.sleep(200);
		System.out.println(
				"````````````````````````````````````...````````````````````````` M+`````````````````````````````````````````````````````````````````````````````````````````````````````````````````osyss:``.omNdy+::/ossssssssssssssssssssssssssssshdh+:.      ./syhhdddmmmmddhhyyyyyhhhyhNMh:``````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````+M ````````````````````````````````````````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"////////////////////////////////////////////////////////////////:M+``````````````````````````````````````````````````````````````````````````````````````````````````````````````./:h:oo``.omNmy+::/ossyyy/..```..........................`      `-/syyyyyyyyyyyyyyyhhhyhNMh:````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````+M:////////////////////////////////////////////////////////////////");
		Thread.sleep(200);
		System.out.println(
				"+++++++++++++++++++++++++++++++++++++++++++++++++oMdhhhhhhhhhhhhhM+```````````````````````````````````````````````````````````````````````````````````````````````````````````-.+/ysh-.`.omNmy+::/oosyyyydNd/`                                     `-/syyyyyyyyyyyhhhyhNMh:``````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````+M+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		Thread.sleep(200);
		System.out.println(
				"`````````````````````````````````````````````````.MyoooooooooooooM+`````````````````````````````````````````````````````````````````````````````````````````````````````````-sy-h/:/-`.+mNmy+::/oosyyyyyyyhmNd/`                         ````````````.:+syyyyyyyhhhyhmMd/````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````+M ````````````````````````````````````````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"`````````````````````````````````````````````````.MyoooooooooooooM+````````````````````````````````````````````````````````````````````````````````````````````````````````.-./y/:``.+mMmy+::/+osyyyyyyyyyyyhmNd:`       ``+syyyyyyyyyhhhhhhhhhhhhhdddddhyyyyyhhhyhmMm/``````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````+M ````````````````````````````````````````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"`````````````````````````````````````````````````.MyoooooooooooooM+```````````````````````````````````````````````````````````````````````````````````````````````````````./+``.-``/dMmyo/:/+osyyyyyyyyssyyyyyhmNh:`       .+dmmmddddddddddddddddddddddddhyyhhhyhmMm+.```````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````+M ````````````````````````````````````````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"`````````````````````````````````````````````````.MyoooooooooooooM+````````````````````````````````````````````````````````````````````````````````````````````````````.s+/+d-```/dMmho/:/+osyyyso/:--....--:/oshmNh:`      `./syyyyyyyyyyyyyyyyyyyyyyyyyyhhhyhmMm+.`````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````+M ``````````````````````.....`````````````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"```````````````````````````````````.````..--`````.MyoooooooooooooM+```````````````````````````````````````````````````````````````````````````````````````````````````-y/s+:.+`/dMmho/:/+osyso:.`              `-/smNy:.      `-+syyyyyyyyyyyyyyyyyyyyyyhhhyhmMmo.```````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````+M `````:-.-````````.-+sooooooo+-.`````````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"```````````````````````````````````:s/``:o+.`````.MyoooooooooooooM+`````````````````````````````````````````````````````````````````````````````````````````````````:ooy+`.``/dMNho/:/+osys/.`                    `-omNy:.      `-+syyyyyyyyyyyyyyyyyyhhhyhmMmo.`````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````+M ````.++/:``````.+ss+/+/++///+os+.```````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"`````.ym/``````````````````````````:s+.`syy/`````.MyoooooooooooooM+`````````````````````````````````````````````````````````````````````````````````````````````.yo/d--``:hMNho/:/+ossys:`       .:shmmNNmdhs:.`      ./hNNy:.      .:oyyyyyyyyyyyyhhhydNNo.`````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````+M ````.+ys`````.d/+++y/.````./ss++/oh/`````..````````:oss+:```````");
		Thread.sleep(200);
		System.out.println(
				"`````.+s+``````````````````````````oo:+`o::/`````.MyoooooooooooooM+```````````````````````````````````````````````````````````````````````````````````````````:ooysy/``:hMNho/:/+ossyyy-`      .smMNddhhhhhdmNms.`     `:yhNNy:.      -:yyyyyyyyhhhyydNNs.```````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````+M `````+s/.````yo/+/d.`````````/ho++/sy-```+msoo/.`.hs+//+sy``````");
		Thread.sleep(200);
		System.out.println(
				"`````.///``````````````````````````o//:`+o./`````.MyoooooooooooooM+`````````````````````````````````````````````````````````````````````````````````````````.ss+/h :.-hMNho/:/+ossyyyy:`     `/mNdyyyyyyyyyyyyhNN/`     .+yydNNy/.   `/mhyyyyyyhhhydNNs.`````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````+M `````/++.``` d//+os````````````+h++/+so.``yo/+om oy/++++/d:`````");
		Thread.sleep(200);
		System.out.println(
				"`````-os/.`````````````````````````/yo-`sss:`````.MyoooooooooooooM+``````````````````````````````````````````````````````````````````````````````````````osyoy/.-``-hMNho/:/+ossyyyyys.     `:MdyyyyyyyyyyyyyyyyhN/`     -hyyydNNy/.-sMmyyyyhhhhydNNs.```````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````+M `````:oo-````so///d.````````````.sy++/+ysss/+++h -d+++++oh``````");
		Thread.sleep(200);
		System.out.println(
				"`````.+//-`````````````````````````+//-`-s+.`````.MyoooooooooooooM+``````````````````````````````````````````````````````````````````````````````````````-sm-`:``-hMNds/:/+ossyyyyyyy+`     .mdyyyyyyyyyyyyyyyyyyhm-     .yyyyyydNNydMdyyyyhhhydNMs.`````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````+M `````-y-`````.d/+++yo-```.oh/`````-ss+++/+++/od.``.+osso/```````");
		Thread.sleep(200);
		System.out.println(
				"`````.::/.`````````````````````````/::-`o//-`````.MyoooooooooooooM+```````````````````````````````````````````````````````````````````````````````````.sy.`-/``-yMNds/:/+ossyyyyyyyyh+`     .Myyyyyyyyyyyyyyyyyyyym:     .shyyyyyydNNhyyyhhhydNMy.```````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````+M ````-+-:/`````:d+/+/+ssosy+/ys``````.+oysssys+``````````````````");
		Thread.sleep(200);
		System.out.println(
				"`````````````````````````````````` yo:o`-ho.`````.MyoooooooooooooM+`````````````````````````````````````````````````````````````````````````````````.+oos+y``.yMNds/:/+ossyyyyyyyyyyho`     .myyyyyyyyyyyyyyyyyyyyd:     .ydyyyyyyyyyyyhhhydNMy.`````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````+M `````+//-``````.sho/+++++++//ho`````````.--`````````````````````");
		Thread.sleep(200);
		System.out.println(
				"````````````````````````````````````````/:```````.MyoooooooooooooM+``````````````````````````````````````````````````````````````````````````````:/:d:o+.+:.yMNds/:/+ossds.+yyyyyyyyyh.     .oyyyyyyyyyyyyyyyyyyyy+-     :mhyyyyyyyyyhhhydNMy.```````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````+M ``````````````````/ossyyyyso+.``````````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"`````````````````````````````````````````````````.MyoooooooooooooM+`````````````````````````````````````````````````````````````````````````````.ossso-``.yMNds/:/+ossyydM:``/syyyyyyN:`     -+yyyyyyyyyyyyyyyyyyo:     .+MyyyyyyyyhhhydNMy-`````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````+M ````````````````````````````````````````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"`````````````````````````````````````````````````.MyoooooooooooooM+``````````````````````````````````````````````````````````````````````````` :yhss-``.sMNds/:/+ossyyyyyMh`  `-+yyyyhm-`     ./syyyyyyyyyyyyyys/-     ./NdyyyyyyhhhydNMh-```````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````+M ````````````````````````````````````````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"`````````````````````````````````````````````````.MyoooooooooooooM+``````````````````````````````````````````````````````````````````````````oy/oo+``.sNNds/:/+ossyyyyyyymM-`   `./syydm:.     `-:osyyyyyyyyso/-`     .+NmyyyyyhhhyhNMh-````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````ohy.`````````````````````````````````````````````````````````+M ````````````````````````````````````````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"`````````````````````````````````````````````````.MyoooooooooooooM+``````````````````````````````````````````````````````````````````````/oooyh+/``.sNNds+::/ossyyyyyyyyyyMy`      .:oydNo-`      `.-::///::.`      `:sNmyyyyhhhydNMh-```````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````.:.dNm-.-```````````````````````````````````````````````````````+M ````````````````````````````````````````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"+++++++++++++++++++++++++++++++++++++++++++++++++oMdhhhhhhhhhhhhhM+``````````````````````````````````````````````````````````````````````sy-oo```.sNNdy+:/+oosyyyyyyyyyyyymM-`       `-+yNd+-`                    .:smNdyyyhhhyhNMh:`````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````:`/..`.:+```````````````````````````````````````````````````````+M+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		Thread.sleep(200);
		System.out.println(
				"::://::::::::::::::::::::::::::::::::::::::::::::/MhyyyyyyyyyyyyyM+```````````````````````````````````````````````````````````````````/:.o-so``.oNNmy+:/+ossyyyyyyyyyyyyyyhMs.         `.:yNms+-.`            `.-oyNNmhyyhhhyhNMh:```````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````.:`+h``..```.d+`-```````````````````````````````````````````````````+MhddddddddddddmM:::::::::::::::::::::::::::::::::::::::::::::/:::/");
		Thread.sleep(200);
		System.out.println(
				"`````````````````````````````````````````````````.MyoooooooooooooM+`````````````````````````````````````````````````````````````````.:oy+/```.oNMmy+://+ssyyyyyyyyyyyyyyyyyNN-            .:sdNNhso/:--..-::+oydNNNdyyyhhhyhNMd:`````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````:./.``-//:.``..-/-``````````````````````````````````````````````````+MyyyyyyyyyyyyydM `````````````````````````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"`````````````````````````````````````````````````.MyoooooooooooooM+```````````````````````````````````````````````````````````````./+sss-.`.omMmy+::/+osyyyyyyyyyyyyyyyyyyyhMo.    ``       `-/ydNNNNNNNNNNNNNNmdhyyyhhhyhmMd/```````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````.-`oh``.-//++//:.`..`s+`.```````````````````````````````````````````````+MyyyyyyyyyyyyydM `````````````````````````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"`````````````````````````````````````````````````.MyoooooooooooooM+````````````````````````````````````````````````````````````/s.+yyo:.`.omMmy+::/+osyyyyyyyyyyyyyyyyyyyyyyNm-     `+-`      `.:oshhhhddhhhhyyyyyyhhhyhmMd/`````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````:./..`-//++++++//:.``-..//``````````````````````````````````````````````+MyyyyyyyyyyyyydM `````````````````````````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"`````````````````````````````````````````````````.MyoooooooooooooM+````````````````````````````````````````````````````````````/yh.++-`.+mMmy+::/+osyyyyyyyyyyyyyyyyyyyyyyyydM+.    `sNh/.`      `-/syyyyyyyyyyyyhhhyhmMd/```````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````.-`sy`..-//++++++++++//:.``-.+o`````````````````````````````````````````````+MyyyyyyyyyyyyydM `````````````````````````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"```````````````````````````````````-`````````````.MyoooooooooooooM+`````````````````````````````````````````````````````````--...//``.+mMmy+::/+ossoossyyyyyyyyyyyyyyyyyyyyyyNd-    `-hmNdo:``     `./syyyyyyyyhhhyhmMd/`````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````::/..`-//++++++++++++++//:.``:/`/+``````````````````````````````````````````+MyyyyyyyyyyyyydM `````````````````````````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"```````````````````````````````````-y+.`-.-.`````.MyoooooooooooooM+```````````````````````````````````````````````````````` oh+/```.+mMmy+::/+osyyhs-..--:/+osssyyyyyyyyyyyyymM/`    .oyhdNNh+-`   `.smyyyyyyhhhyhmMd+.``````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````..`ss .`-//++++++++++++++++++//:.``:.:/`````````````````````````````````````````+MyyyyyyyyyyyyydM `````````````````````````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"`````.+s-``````````````````````````+//-`o+-+`````.MyoooooooooooooM+`````````````````````````````````````````````````````````-...``/dMmyo/:/+osyyyyhmNo.    ```.-::/+osssyyyyyyMh:    `:yyyyhmNds/../dNdyyyyhhhyhmMm+.````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````:/-..`-:/++++++++++++++++++++++//-.``:s`:+``````````````````````````````````````+MyyyyyyyyyyyyydM `````--.-`o:/o ````````````````````````./:/.`````");
		Thread.sleep(200);
		System.out.println(
				"`````.yd:``````````````````````````:oo-`o//+`````.MyoooooooooooooM+`````````````````````````````````````````````````````````````/dMmho/:/+ossyyyyyyydNd:`          ```.-://+osdm/`    -syyyyyhdNNhsNNhyyyhhhyhmMm+.```````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````. so`.`-//++++++++++++++++++++++++++//:```:.:-`````````````````````````````````````+MyyyyyyyyyyyyydM `````-sso`o--s`````````````````````````-//+.`````");
		Thread.sleep(200);
		System.out.println(
				"`````./+o``````````````````````````o+-+`-y+.`````.MyoooooooooooooM+`````````````````````````````````````````````````````````````+mMmh+///oosyyyyyyyyyymNs.`                ``..-:-    ./yyyyyyyyhmNdyyyhhhyhmMm+.````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````//--.`-:/+++++++++++++++++++++++++++++++/-```-h.-/``````````````````````````````````+MyyyyyyyyyyyyydM `````:+o+`-/+o`````````````````````````-o++-`````");
		Thread.sleep(200);
		System.out.println(
				"``````+::``````````````````````````++o-`+/::`````.MyoooooooooooooM+``````````````````````````````````````````````````````````````.omMmyo+ossyyyyyyyyyyyhNm/`       `                   :syyyyyyyyyhyyhhhyhmMmo.```````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````.`s+`-`-:/+++++++++++++++++++++++++++++++++++/-```-::.`````````````````````````````````+MyyyyyyyyyyyyydM `````./oo`-oy-`````````````````````````-//+-`````");
		Thread.sleep(200);
		System.out.println(
				"`````.ho/``````````````````````````/s/-`///+`````.MyoooooooooooooM+````````````````````````````````````````````````````````````````.omMmysosyyyyyyyyyyyyymMy-`     ``:+/--..`          -+yyyyyyyyyyhhhyhmMmo.````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````/+.--`.://+++++++++++++++++++++++++++++++++++//+/-```.d/`/``````````````````````````````+MyyyyyyyyyyyyydM `````.oh-``.:y `````````````````````````:s+.`````");
		Thread.sleep(200);
		System.out.println(
				"`````/::+-`````````````````````````+::- s+:+`````.MyoooooooooooooM+``````````````````````````````````````````````````````````````````.oNMmyssyyyyyyyyyyyyyhNm+.      `/dNNmdhhso+:--.```/yyyyyyyyhhhyhdNNo.```````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````.`s/`-`.:/+++++++++++++++++++++++++++++++++++++++++++/-`` .:/.`````````````````````````````+MyyyyyyyyyyyyydM `````/+s/`:+/:``````````````````````````:No.`````");
		Thread.sleep(200);
		System.out.println(
				"``````---``````````````````````````o++:`o+:+`````.MyoooooooooooooM+````````````````````````````````````````````````````````````````````.oNMmyyyyyyyyyyyyyyyymMy:`     `-+yyhhddmNNNmddhyosyyyyyyhhyydNNo.``````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````` /+`:-`.://++++++++++++++++++++++++++++++++++++++//////++/-`.``ho`-``````````````````````````+MyyyyyyyyyyyyydM ``````...`+/o:``````````````````````````-o-``````");
		Thread.sleep(200);
		System.out.println(
				"```````````````````````````````````o/.+`..```````.MyoooooooooooooM+``````````````````````````````````````````````````````````````````````.sNMmyyyyyyyyyyyyyyyhNNo-`     .:syyyyyyyyhhddmdyyyyhhhyydNNs.`````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````o/`:`.://++++++++++++++++++++++++++++++++++++++++////////++/-``.`-+:`````````````````````````+MyyyyyyyyyyyyydM `````````````````````````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"`````````````````````````````````````````````````.MyoooooooooooooM+````````````````````````````````````````````````````````````````````````.sNMdyyyyyyyyyyyyyyydMh:.     `-+yyyyyyyyyyyyyyyyhhhydNNs.````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````++`/-`.://+++++++++++++++++++++++++++++++++++++///////////////++/-`.. ss````````````````````````+MyyyyyyyyyyyyydM `````````````````````````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"`````````````````````````````````````````````````.MyoooooooooooooM+``````````````````````````````````````````````````````````````````````````.sNMdyyyyyyyyyyyyyyhNNo-`     ./syyyyyyyyyyyyhhhydNNs.`````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````o:.:`.://+++++++++++++++++++++++++++++++++++++++/////////////////++/-``..-s/`````````````````````+MyyyyyyyyyyyyydM `````````````````````````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"`````````````````````````````````````````````````.MyoooooooooooooM+````````````````````````````````````````````````````````````````````````````.sMMdyyyyyyyyyyyyyydMd/.     `:oyyyyyyyyyhhyydNMy.````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````++`+-`.://++++++++ssooooso+++++++++++++++++++++++/////////////////////++/-``. /s````````````````````+MyyyyyyyyyyyyydM `````````````````````````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"`````````````````````````````````````````````````.MyoooooooooooooM+``````````````````````````````````````````````````````````````````````````````.yMMdyyyyyyyyyyyyyyNNs:`   ``/syyyyyhhhhydNMy.`````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````+-.:`.://+o+++++oo/.      ./+s+++++++++++++++++//////////////////////////+++-``-:`/o`````````````````+MyyyyyyyyyyyyydM `````````````````````````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"`````````````````````````````````````````````````.MyoooooooooooooM+````````````````````````````````````````````````````````````````````````````````.yMMdyyyyyyyyyyyyydMm+-./yNMdyyyyhhhydNMy.````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````oo`o:`.://++++++++y-  .++ooo+:  `s+++++++++++++++/////////////////////////////+++-``:..oo-``````````````+MyyyyyyyyyyyyydM `````````````````````````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"yyysyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyMmdddddddddddddM+``````````````````````````````````````````````````````````````````````````````````.yMMdyyyyyyyyyyyyymMhNMmhyyyyhhhydNMy-`````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````+..-`.://+++++++++h`  oo+++++++s`  y+++++++++++++////////////////////////////////+o+```+MMd `````````````+MmmmmmmmmmmmmmNMsyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyysyyyyy");
		Thread.sleep(200);
		System.out.println(
				"----------------------------------------------------------------.M+````````````````````````````````````````````````````````````````````````````````````-yMNdyyyyyyyyyyyydmdyyyyyhhhydNMy-````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````+/`o:`.://++++++++++so  +o+++++++++y  :s++++++++//////////////////////////////////+++:``.+ /:```````````````+M.----------------------------------------------------------------");
		Thread.sleep(200);
		System.out.println(
				"```````````````````````````````````````````````````````````````` M+``````````````````````````````````````````````````````````````````````````````````````-yMNdyyyyyyyyyyyyyyyyhhhydNMh-`````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````/.:-`.://++++++++++++y/  oo+++++++++h` .h/+++++++////////////////////////////////+++:```d+`/.````````````````+M ````````````````````````````````````````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"```````````````````````````````````````````````````````````````` M+````````````````````````````````````````````````````````````````````````````````````````-hMNdyyyyyyyyyyyyhhhyhNMh-````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````//.s:`.://+++++++++++++++y  `so+++++++/h. .h/++++/////////////////////////////////+++:``.+`-````````````````````+M ````````````````````````````````````````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"`````````````````````````:``````m.``` h:```````````````````````` M+``````````````````````````````````````````````````````````````````````````````````````````-hMNdyyyyyyyhhhhyhNMh:`````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````:.:.`.://+++++++++++++++++oo` `:ooy/+++/d` /y//+//////////////////////////////////++:``.h/./.````````````````````+M `````````````````````````````````````.:/:/++:```````````````````");
		Thread.sleep(200);
		System.out.println(
				"`````````````````````````+h.``` m+```.h````h-``````:-:`````````` M+````````````````````````````````````````````````````````````````````````````````````````````:hMNdyyhhhhhyhNMh:````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````/:-s-`.://+++++++++++++++++++++s/.  `d/+++/d  /o//////////////////////////////////+++:.`-/.-````````````````````````+M ````.+++-``````````````````````````.+s/-/ossos+````````.:.``````");
		Thread.sleep(200);
		System.out.println(
				"```````````````````` so.``/d.```.````.-.``:+``````/--/--://````` M+``````````````````````````````````````````````````````````````````````````````````````````````:hMNdyhhyhNMh:`````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````:./.``-//+++++++++++++++++++++++++ososs++++/h  o+/+///////////////////////////////++/.`.h:-/`````````````````````````+M ````.+o::+````````````````````.`-`:ss+o++sNMNyos``````-+:+-`````");
		Thread.sleep(200);
		System.out.println(
				"`````````````````````.od-``.``.-:+osssoshho-``````:/-o.//oo.```` M+````````````````````````````````````````````````````````````````````````````````````````````````:hMNhhNMd:```````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````.:--y-`.-//++++++++++++++++++++++++++++++++++++/y  s+//oh///////////////////////////++/.`-:`:.```````````````````````````+M ````-s+o+/````````````````````+:+/os.s:y+/sN+/-oo`````.+++.`````");
		Thread.sleep(200);
		System.out.println(
				"`````.oh:``````````````--:/oyyyso/:-....-:omo`````:+-+`//--````` M+``````````````````````````````````````````````````````````````````````````````````````````````````/dMMd/`````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````:./.``-//++++++++++++++++++++++++++++++++++++++/y` .oo++:h////////////////////////++/.`.y-:/.````````````````````````````+M ````:o/y+/``+:.:+.``````````//.-:ys//dy:h``s+...d-````.+/+.`````");
		Thread.sleep(200);
		System.out.println(
				"`````-yd:`````.+/ohshddyso+:--.-:/++oo++o:./No````.os/`//o+````` M+````````````````````````````````````````````````````````````````````````````````````````````````````//````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````:-:h-`.-//++++++++++++++++++++++++++++++++++++++///+s-  `  `so/////////////////////++/.`--./.```````````````````````````````+M ````-+++++``:sos:````````````.-:y/+o.MN:o```d-..+y ````+//.`````");
		Thread.sleep(200);
		System.out.println(
				"`````./+o`````omNNNNMMd+sso++ohysssyyo+oo:..sN ```.os/`:/:o.```` M+````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````:/:/.``-//++++++++++++++++++++++++++++++++++++++++////+oo+/+oo+/oo/+oo/////////////++/.`.s-//.````````````````````````````````+M ````.---:s `:.`:s-````````.++/`+/+-s.Md/+```+o...N`````/m+``````");
		Thread.sleep(200);
		System.out.println(
				"`````:-/+````:NoMNmhMMoossossyhhds+++s+/:...+M ```-+os`:-.+.```` M+```````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````sMMs .`://++++++++++++++++++++++++++++++++++++/////////////+////o+    +o//////////++/.`--.+.```````````````````````````````````+M ````:o::++``-sos-``````````.--`oo:o+oMd:+```.d...N-````/d+.`````");
		Thread.sleep(200);
		System.out.println(
				"`````-+o/`````-dMhhddmy+/:---...-:/+o+oshh:.oh ```.+s/`.::s.```` M+```````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````-oo-.-.`.://++++++++++++++++++++++++++++++++++//////////////////+o`  `oo////////++/.`.o.++`````````````````````````````````````+M ````.+syo.``/-/s/``````````/:-:-yddosyh.s````d...m:```.-:.``````");
		Thread.sleep(200);
		System.out.println(
				"`````/::+.`````.:.::..:/syyyyyo+/:--.-:///:+h.````-s//`-oos````` M+``````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````:/.::``.://++++++++++++++++++++++++++++++++///////////////////++ooo+///////++/-`-.-+.```````````````````````````````````````+M ````:o/:++` m+++s.```````````o/`+ys.`yo/y```:y...N.```.+++.`````");
		Thread.sleep(200);
		System.out.println(
				"``````--.`````````````-````.::+oyhhyysssssho.`````./s/`-+o+.```` M+`````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````+o.-.`-://+++++++++++++++++++++++++///////////////////////////////////++/-`.o`++`````````````````````````````````````````+M ````.::::y `:````````````````.`.-.yo.y-y/+``y:..:m ````/o/``````");
		Thread.sleep(200);
		System.out.println(
				"````````````````````:ys```.````````.--::--`.``````./:+./-.+-```` M+``````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````-/..-``-///+++++++++++++++++++++++/////////////////////////////////++/-`-.-o.```````````````````````````````````````````+M ````-+++/+````````````````````````-s+o-++s:+m+s:y/`````oo/.`````");
		Thread.sleep(200);
		System.out.println(
				"```````````````````-y-```oh ```-h ``-s.````hs`````.-++`````````` M+```````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````.`+h`..`-//+++++++++++++++++++++////////////////////////////////+++-`./`o+`````````````````````````````````````````````+M `````....h ````````````````````````.s/..oosNMNsos ````-/+:.`````");
		Thread.sleep(200);
		System.out.println(
				"````````````````````````:d-``` sh ```Ns ```.s:`````````````````` M+``````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````.+-..``-//+++++++++++++++//////////////////////////////////+++-`-.-s.```````````````````````````````````````````````+M ````-////:```````````````````````````+o:-+osy+o+````````````````");
		Thread.sleep(200);
		System.out.println(
				"```````````````````````````````:-````:-````````````````````````` M+```````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````-`/d.```-//++++++++++++/////////////////////////////////+++-`.:`o+`````````````````````````````````````````````````+M ``````````````````````````````````````./+o+o/:``````````````````");
		Thread.sleep(200);
		System.out.println(
				"```````````````````````````````````````````````````````````````` M+`````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````` +:-```-//++++++++/////////////////////////////////+++-`-`:s.```````````````````````````````````````````````````+M ````````````````````````````````````````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"`````````````````````````````````````````````````````````````````M+```````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````-.-d:```-/++++++////////////////////////////////+++-`.-`s+ ````````````````````````````````````````````````````+M````````````````````````````````````````````````````````````````.");
		Thread.sleep(200);
		System.out.println(
				"yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyhhhhhhhhhhhhhhhhM+``````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````.::-```-++++///////////////////////////////+++-`-`/y.```````````````````````````````````````````````````````+Mhhhhhhhhhhhhhhhyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyhyyyy");
		Thread.sleep(200);
		System.out.println(
				"....-............................................-MyssssssssssssoM+```````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````:.`y+```:+++////////////////////////////+++-``.`s+ ``````````````````````````````````````./+oossso/````````+MhhhhhhhhhhhhhdM.............................................-...-");
		Thread.sleep(200);
		System.out.println(
				"`````````````````````````````````````````````````.MyoooooooooooooM+``````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````--::```:++/////////////////////////+++:`-`+h```````````````````````````````````````.+oyyyhhdhhyoy```````+MyyyyyyyyyyyyydM `````````````````````````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"`````````````````````````````````````````````````.MyoooooooooooooM+```````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````/: o+```:+++////////////////////+++:```.o+``````````````````````````````````````:oyyhyyyyh/o`ods+``````+MyyyyyyyyyyyyydM `````````````````````````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"````````````````````````````````````````/-/:`````.MyoooooooooooooM+``````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````::-:.``:+++////////////////+++:`- od`.`````````````````````````````````````-syyyyyyyy/s oo+hhs `````+MyyyyyyyyyyyyydM `````:+/+````````````````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"````````````````````````````````````````+/.+`````.MyoooooooooooooM+```````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````//`:+```:+++////////////+++:```-+/``````````````````````````````````````.oyysssssss-/yyoshyd+ `````+MyyyyyyyyyyyyydM `````.::s````````````````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"````````````````````````````````````````/--/`````.MyoooooooooooooM+``````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````:+`..`.:+++////////+++:`. sh .``````````````````````````````````````:yysss/`:o+`y-:yyyyym-``````+MyyyyyyyyyyyyydM `````-/+y````````````````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"```````````````````````````````````:::-`.:-``````.MyoooooooooooooM+```````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````.s-.:``.:+++////+++:```-//````````````````````````````````````````+hyssos/++./:ysss+/::y```````+MyyyyyyyyyyyyydM `````.od.````````````````````````````````-```````");
		Thread.sleep(200);
		System.out.println(
				"`````.sd:``````````````````````````-oo/`s`-o`````.MyoooooooooooooM+``````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````:s.`-`./++/+++:`. yy -.```````````````````````````````````````+hsssooo`s///oos+//+o+s ``````+MyyyyyyyyyyyyydM `````//++``````````````````````````````-o:o-`````");
		Thread.sleep(200);
		System.out.println(
				"`````.sm:``````````````````````````s--.`/+::`````.MyoooooooooooooM+````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````s/`-``./++:.``/-:`````````````````````````````````````````./sysso/o:+os os+/:o/ys++```````+MyyyyyyyyyyyyydM `````:ss/``````````````````````````````./+/.`````");
		Thread.sleep(200);
		System.out.println(
				"`````.//o``````````````````````````/s/.`+o:-`````.MyoooooooooooooM+````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````.`-h-`.`..``yy`:.```````````````````````````````````````::y+.+s+-:o-/s//o:::osyy:.````````+MyyyyyyyyyyyyydM `````:+//``````````````````````````````./:o.`````");
		Thread.sleep(200);
		System.out.println(
				"``````+/-``````````````````````````+s/``:ho.`````.MyoooooooooooooM+````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````o/.`.././.`````````````````````````````````````````/o`:yy-.+o-oyooo//:oosyh:``````````+MyyyyyyyyyyyyydM `````/o/y```````````````````````````````/os.`````");
		Thread.sleep(200);
		System.out.println(
				"`````.yo+``````````````````````````+..+`oo:-`````.MyoooooooooooooM+````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````.``mNm-:.``````````````````````````````````````````.-s-`-ss/+osss:o/+ssyys.```````````+MyyyyyyyyyyyyydM `````//s/```````````````````````````````:d+.`````");
		Thread.sleep(200);
		System.out.println(
				"`````-/:+-`````````````````````````so:+`+o+:`````.MyoooooooooooooM+```````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````shs``````````````````````````````````````````````ooos-`+d`do+::oysyys/`````````````+MyyyyyyyyyyyyydM `````./o+```````````````````````````````:mo.`````");
		Thread.sleep(200);
		System.out.println(
				"``````...``````````````````````````-.`. s:/-`````.MyoooooooooooooM+```````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````/+shhhsyh//++-+syys+.``````````````+MyyyyyyyyyyyyydM ``````+y/```````````````````````````````.:.``````");
		Thread.sleep(200);
		System.out.println(
				"``````````````````````````````````````` yo//`````.MyoooooooooooooM+````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````.ydhhhhhhysyyyss+-````````````````+MyyyyyyyyyyyyydM `````+-.o````````````````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"````````````````````````````````````````+-:/`````.MyoooooooooooooM+````::-`---.-/:-:..:-.--:--:`--.-.-.-:---:--:.-..-.--...-...:.--..`.:-`.--.-...-.-`.-.---...-.--..-.-.--..----`.--`-.-......--.....-.--..`:.-`--+`.---::.::--:`:..-..-`.-....--..-..-.`.....-----`-.-`:`.:.-..```````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````-yyhhhhhyssso/.``````````````````+MyyyyyyyyyyyyydM `````-o+o````````````````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"````````````````````````````````````````+/./`````.MyoooooooooooooM+````-//---:::/:-:--:--::/--/.//::--::::-:--:/-:--+::/--:/:/./:o:-/.://.::/:::::::/.//://+:-:-:-/::+//-/::/:/:/-:-/--/::--:/-::/.:://::::/`/-/.::/.--::/:-:-::/-:-:+::/-:/-/-/://:/:-/:--/:--/:-//-+:/:/-+::::/`..`````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````.+oooooo+:.`````````````````````+MyyyyyyyyyyyyydM `````//ss````````````````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"`````````````````````````````````````````````````.MyoooooooooooooM+```.//////.:/+-:-o:+/+:+:-/+-//+./+/+:/:+///-+++:/:+://o:-+/`+:+`+:+/o-+/+:o:-//:///////-+//.+/-///+/+//--::o/o://:++//+`+:++.++//+.///:+/+:/::o`.:.:-///-`+/::/-:+/+///:`+/-+/+///-/+-/+::+:/-`:/:://+//+:/+:/o+````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````...``````````````````````````+MyyyyyyyyyyyyydM ``````...````````````````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"`````````````````````````````````````````````````.MyoooooooooooooM+````.`````.``````````.``.`.``.`.`.``.```.``.`.`..``...```.```.```````````.````.``````.`.``.`````.``..```.```.````.`..````.``.`..``````.``.```.``````````..`.```.`````.`````````.``.`````.````.```..``.````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````+MyyyyyyyyyyyyydM `````````````````````````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"````.````````````````````````````````````````````-MyooooooooooosoM+``````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````+MyyyyyyyyyyyyydM````````````......................................");
		Thread.sleep(200);
		System.out.println(
				"yyyyyyyyyyyyyyyyydhhhhhhhhhhhhhhhhhhhhhhyhhhhhhhhNddddddddddddddMdyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyydhyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyydyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyhdyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyhdyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyydhyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyydhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhdyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyhdhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhmMdddddddddddddmmyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy");
		Thread.sleep(200);
		System.out.println(
				"``````````````````Mh/////////////////////++/////////////////////:M+.......................................M/......................................:M:``````````````````````````````````````/M.......................................oM````````````````````````````````````````M+``````````````````````````````````````:MdyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyydM:``````````````````````````````````````/MyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyydM`````````````````````````````````````````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"````````````````` Mh:////////////////////hmh////////////////////:M+``````````````````````````````````````.M/``````````````````````````````````````:M:``````````````````````````````````````/M.``````````````````````````````````````+M `````````````````````````````````````` M+``````````````````````````````````````:MdyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyydM:``````````````````````````````````````/MyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyydM ```````````````````````````.````````````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"````````````````` Mh://////////////////oy/`.sy/////////+y///////:M+``````````````````````````````````````.M/``````````````````````````````````````:M:``````````.-..`.`.`.`..`-`..``````````/M.``````````````````````````````````````+M `````````..`..`.``.``..`.`.-`````````` M+`````-.-``..`.--``--.`````.`---```````:MdyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyydM:``````..`..`````````.`..`.``.`````````/MyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyydM ````````````````.o+``````..s-```````````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"````````````````` Mh:////////////////sy:     -dy/////+yosm//////:M+``````````````````````````````````````.M/``````````````````````````````````````:M:`````````-o./s:o-y:++++o.`d/``````````/M.``````````````````````````````````````+M `````````ys-h/`so`y:/ss/s//+/````````` M+`````y:hs-/o++--/o--o+/h`.m`h:-```````:MdyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyydM:`````+:-s-:+s-+/s-o:y ss/s:+h-oo-`````/MyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyydM ```````````````o:`:o.```++o/y```````````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"````````````````` Mh://////////////sh:      -o/:ys///+/y/d/yo///:M+``````````````````````````````````````.M/``````````````````````````````````````:M:``````````/::-`/+.//`:-/:.o:``````````/M.``````````````````````````````````````+M `````````+:-+:/-::o/.//`+./:/````````` M+`````y-d`+h+s:``o/``:sh:yy++h/:```````:MdyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyydM:`````:/:///:++-o/+-++/:+`o-:+`:-``````/MyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyydM ````````````.o.o//+```:.:so:.```````````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"````````````````` Mh:////////////sh/      .o:    -syo///:hs/////:M+``````````````````````````````````````.M/``````````````````````````````````````:M:``````````````````````````````````````/M.``````````````````````````````````````+M ````````//`./`+/`.+:://:`+`/::```````` M+`````:./``.:`://`://:`+`-``////.``````:MhyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyydM:````````````:/-/`+-/-+/.o-````````````/MyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyydM ``````````.../y:/`````oy+/-``.h//```````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"````````````````` Mh://///////:sh:      .o/       `yhh//////////:Mo--------------------------------------:Mo--------------------------------------/M:``````````````````````````````````````/M:--------------------------------------oM ``````` ss.yo/yo.-sos``ss+os.s```````` M+````````````-++:`-``/.`/``````````````:MdhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhydM:````````````s``y:h/s./o.s ````````````/MhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhydM ````````.-`/+:+:```/+`-s```.y/+-+:``.```````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"````````````````` Mh:///////:oh:      :s/       .o+` -sy////////:MhsssssssssssssssssssssssssssssssssssssssMhssssssssssssssssssssssssssssssssssssssyM:`````````````./osyyyyso/``````````````/MssssssssssssssssssssssssssssssssssssssshM ````````.`-``-..-...`::..`..-````````` M+`````````````:s`yy-`.yy-``````````````:MhsssssssssssssssssssssssssssssssssssssshM:`````````````:-.`.`-.-:`.`````````````/MssssssssssssssssssssssssssssssssssssssshM ``````.//y+:/-```+ss/o/``.o//-+-``.sMm/ ````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"`````/o////`````` Mh://////sy-.so/:::my-+/syyo/od:     .yhs/////:M+```````````````````````````````````````M/``````````````````````````````````````:M:```````````+hhyssssssssydy:```````````/M```````````````````````````````````````+M `````````````````````````````````````` M+`````````````:yy//y-:s+o``````````````:M/``````````````````````````````````````/M:``````````````````````````````````````/M```````````````````````````````````````+M ````.-y` +/-``-y/:h````o/+oo.:``.sMMMMMm/ ``````````````````````");
		Thread.sleep(200);
		System.out.println(
				"``````/o+o+`````` Mh/////sy:/yhssh+dsm+Nds- .o+ohNd:   .ooyh+://:M+```````````````````````````````````````M/``````````````````````````````````````:M:`````````-dyssssyhyyhhsssshy``````````/M```````````````````````````````````````+M `````````````````````````````````````` M+``````````````-.```--``.``````````````:M/``````````````````````````````````````/M-``````````````````````````````````````/M```````````````````````````````````````+M ````y`.+/:``//.--o-``/`.+y`:``.sNMMMNMMMMd/`````````````````````");
		Thread.sleep(200);
		System.out.println(
				"`````+/`````````` Mh:+/oo. yNyyyy/hdhhNy /o+o/-symdNy`o+`  .ydo::M+`````:..:-`.`-.`.`--`:----`-:.`-.-.````M/````````-`-.:.-.`````-:..`..--````````:M:````````.myssshy:````-ohsssyd`````````/M``````````::`.-`--:.``--:`.`..`````````+M `````````````````````````````````````` M+``````````````````````````````````````:M/````````````--``.`-`:--`::````````````/M```````````````````````````````````````/M``````````````.-`-``.-..-`-````````````+M ````////``-//`o.```-:.+/`.``.oNMMMNs.-hMMMMd:```````````````````");
		Thread.sleep(200);
		System.out.println(
				"``````o+os+`````` Mh:dds  ssoyoysdhd:Ns .ssh:+ho``o+o+`      -dd/M+``` y`-o`:/y/ss/s-s.s``.++o-`o`s`y`````M/````````//+s:`hs:s:y+-`oo+o/o`````````:M:``````` yysssd/````````.dsssho````````/M```````` s.`ss+-ss:-y:s:/:d:/:`````````+M `````````````````````````````````````` M+``````````````````````````````````````:M/``````````` hs.yy`s //yo.`````````````/M```````````````````````````````````````/M````````` y-o:y/:++/+s.y/:s+-.`````````sM ````````.y./o//``///o+//``.oNMMMNs.````:hMMMMh:`````````````````");
		Thread.sleep(200);
		System.out.println(
				"`````/+.y-y ````` Mh://oyss :Nhomo-:s::oy-ss+yo++`++s.      -so/:M+````/:-/:/././+`/.+:::-`::./:-/+ +`````M/`````````+`//.+/+./:-/:/:`:-/`````````:M:``````` mssssh `````````ohsssh ```````/M`````````:/::+--///-:./.:/`/:/-````````+M ``````.++++oo.```````````````````````` M+``````````````````````````````````````:M/````````````o+/-`/+:--+-/:````````````/M```````````````````````````````````````/M``````````/+.++::+/-:/`o/::/.``````````sM ```````y/s+:/```+s..y`````yMMMMM+````````:hMMMNh:```````````````");
		Thread.sleep(200);
		System.out.println(
				"``````++.`s`````` Mh/////shysy+ys+sy+ms+oo..:yh+s/.yy     .oo///:M+```````````::/`/:/.o.+:./.+-```````````M/```````````+.+`+/:.o`// +-+-``````````:M:``````` hysssd/`````````hyssyy ```````/M````````````+.+`/+:-+`++ //:.``````````+M ````` mMMMMMMd ``````````/yhhhhhhhho`` M+``````````````````````````````````````:M/```````````+`+`++:.+`++ +/:.``````````/M```````````````````````````````````````/M``````````/:-`+`::.:+:`-/`+.+``````````sM `````:sy-y/```//y-:-.`.:```:hMMMMd:````````:dMMMNy-`````````````");
		Thread.sleep(200);
		System.out.println(
				"`````.+++/h ````` Mh:////+syd-  ``:yN+hhy+ so/so+shmo   .+s/////:M+``````````:oo/s:+o-y/y/:s-y/```````````M/``````````+os:s-so.y/s+-y/y:``````````:M:````````-msssydo `````.yysssd-````````/M```````````+os/s.y+-o/ss.ooo-``````````+M ``````oMMMMMMo``````````oMMMMMMMMMN/`` M+``````````````````````````````````````:M/``````````oos:s.yo.o/ss.ss+.``````````/M``````````````````-///-:-:::-::----.```/M``````````so:ooo+/o/y+:so:s:h `````````sM ````yyd::.`.s::``/``.oNNh-```:hMMMMh:```````-dMMMMm-````````````");
		Thread.sleep(200);
		System.out.println(
				"``````````:`````` Mh:///////+ys--/hsdd/:h-:/ss:yo.``` -oo///////:M+``````````.``..``.`.`.`..`..```````````M/``````````.``..`..`.`.`..`.```````````:M:`````````-hhy+.`````-shyssyd/`````````/M```````````.```.`..```......```````````+M ```````dMMMMm`````````` yMMMMMMMMd-``` M+``````````````````````````````````````:M/``````````.`.`.`..`.`......```````````/M`````````````````::.+////::::-::::-/-``/M``````````.`.-..---...-.`:---``````````sM ````:o.``./:/-o-``.omMMMNs`````/dMMMNh:```-sNMMMNo.`````````````");
		Thread.sleep(200);
		System.out.println(
				"````````````````` Mh/////o////omh.-h+sNm+oyhs- `    :so/////////:M+```````````````````````````````````````M/``````````````````````````````````````:M:``````````..``````:yhyssyhy-``````````/M```````````````````````````````````````+M ```````-MMMM:```````````-mMMMMMMd````` M+````````````````./y/.`````````````````:M-``````````````````````````````````````/M````````````````/:.+o++o++oo+ooo/oo+-``/M```````````:h-o/:y-:o:+s ss:```````````sM ``````````so:/.`.omMMMNy-````-```/dMMMNh:sNMMMNs.```````````````");
		Thread.sleep(200);
		System.out.println(
				"````````````````` Mh:o+oy+/+////+yh:://o+/:s`     -so///////////:M+```````````````````````````````````````M/``````````````````````````````````````:M:````````````````/yhyssyhy:````````````/M```````````````````````````````````````+M ``````//dMMm+ooooooooooo+sMMMMMN.````` M+````````````````osho/`````````````````:M-``````````````````````````````````````/M```````````````/-.+//::/+++o++o/-/s-```/M``````````.+.+.+ s/::::+:/++.``````````sM ``````````./``.+mMMMNy-````:hNy-```/dMMMMMMMNs.`````````````````");
		Thread.sleep(200);
		System.out.println(
				"````````````````` Mh:+osshyh//////+s/-//.  /h` `:so/////////////:M+```````````````````````````````````````M/``````````````````````````````````````:M:``````````````:yysssyhs:``````````````/M```````````````````````````````````````+M ````.dMMMMMMMMMMMMMMMMMMMMMMMMMh ````` M+`````````````````-/.``````````````````:M-``````````````````````````````````````/M``````````````+/:++/.:-:o++++ooooo.````/M```````````````````````````````````````sM `````````````+mMMMMy-````:hNMMMNy-```/dMMMNs.```````````````````");
		Thread.sleep(200);
		System.out.println(
				"````````````````` Mh///+/+d/oyo+////dho`    d`/yo///////////////:M+```````````````````````````````````````M/``````````````````````````````````````:M:`````````````shsssyy+-````````````````/M```````````````````````````````````````+M ``` yMMMMMMMMMMMMMMMMMMMMMMMMMMd`````` M+``````````````````````````````````````:M-``````````````````````````````````````/M`````.....:://oyooo-..:`.:+++/+o+``````/M```````````````````````````````````````sM `````````````sNMMMMs.``:hNMMMmMMMNs-```/dy-`````````````````````");
		Thread.sleep(200);
		System.out.println(
				"````````````````` Mh//////sysyo/////+/oy+` .Nyo/////////////////:M+```````````````````````````````````````M/``````````````````````````````````````:M:````````````yyssyd/.``````````````````/M```````````````````````````````````````+M ``` yMMMMMMMMMMMMMMMMMMMMMMMMMMMs````` M+``````````````````````````````````````:M-``````````````````````````````````````/M````/::+syhds///o+` `  `-`so/++/```````/M```````````````````````````````````````sM ``````````````-sNMMMmo./dMMh:.+mMMMNs-``````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"````````````````` Mh:///////ho//////////sh+so///////////////////:M+```````````````````````````````````````M/``````````````````````````````````````:M:````````````Nssshh..-s.```````````````/M```````````````````````````````````````+M ````.ymMMMMNMMMMMmmmmmmmmMMMMMMNmy```` M+``````````````````````````````````````:M-``````````````````````````````````````/M````o`++++s-:-+//:/./-//oooo++.````````/M```````````````````````````````````````sM ````````````````-yNMMMmo-+/````+MMMMNy``````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"````````````````` Mh/////////o///////////sy+/////////////////////M+```````````````````````````````````````M/``````````````````````````````````````:M:````````````mysssyyyhh ```````````````/M```````````````````````````````````````+M ``````sMMM+dMMMMMh......oMMMMMMm.-```` M+``````````````````````````````````````:M-``````````````````````````````````````/M````o.-:--::/:s+oo/:.ooss+os/+`````````/M```````````````````````````````````````sN ``````````````````-yNMMMmo.``/dMMMMh:```````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"````````````````` ddhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyM+```````````````````````````````````````M/``````````````````````````````````````:M:````````````-hhyssssm-````````````````/M```````````````````````````````````````+M ````-dMMMh:MMMMMMM``````sMMMMMMM`````` M+``````````````````````````````````````:M-``````````````````````````````````````/M````+.+oo+::-//:::.-`+++/+oo/+`````````/M`````````````````````````````````````` sN ````````````````````-yNMMMmodMMMMh:`````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"```````````````````````````````````````````````````````````````` M+```````````````````````````````````````M/``````````````````````````````````````:M:``````````````:osyyh+`````````````````/M```````````````````````````````````````+M ``.oNMMMN.`yNMMMNs``````.hNNNNm+`````` M+``````````````````````````````````````:M-``````````````````````````````````````/M````/://+++so++ooo/.`o+ooo++:o`````````/M`````````````````````````````````````` sN ``````````````````````-yNMMMMMMd:```````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"```````````````````````````````````````````````````````````````` M+```````````````````````````````````````M/``````````````````````````````````````:M:````````````````.-//-.````````````````/M```````````````````````````````````````+M ``-/////:```-//:-`````````-::-```````` M+``````````````````````````````````````:M-``````````````````````````````````````/M````.::/::/://+++o+.`/++//-:::`````````/M`````````````````````````````````````` sN `````````````.:/````````:hNMMd/```````````````.........`````````");
		Thread.sleep(200);
		System.out.println(
				"```````````````````````````````````````````````````````````````` M+```````````````````````````````````````M/``````````````````````````````````````:M:```````````````+hhyyhh/```````````````/M```````````````````````````````````````+M `````````````````````````````````````` M+``````````````````````````````````````:M-``````````````````````````````````````/M`````````.--::/:::-.---/-..````````````/M`````````````````````````````````````` sN ``````````./yhdh`........`/h+..............`:yhhhhhhhdy-````````");
		Thread.sleep(200);
		System.out.println(
				"````````````````````````````:-``+/`///.+/++-+--``o`:///````````` M+```````````````````````````````````````M/``````````````````````````````````````:M:``````````````/mssssssN.``````````````/M```````````````````````````````````````+M `````````````````````````````````````` M+``````````````````````````````````````:M-``````````````````````````````````````/M````````````````---/:--````````````````/M`````````````````````````````````````` sN ```````:shdhyyydyhhhhhhhhhhhhhhhhhhhhhhhhhhhdyyyyyyhh/``````````");
		Thread.sleep(200);
		System.out.println(
				"`````````````````````````````h.y-h.y/--y h`-y/ho.m/o`:+````````` M+``````````````/`/.o`++-/+``````````````M/`````````````:-.:-+./+-/+`````````````:M:``````````````/mssssssm.``````````````/M``````````````:.-:-+://::/`````````````+M `````````````/`/`++-++./+````````````` M+```````:/:-/`/:.`/`/`++-++./+`````````:M-``````````````--./.+`//:``````````````/M```````````````````````````````````````/M```````````````/`:../`//.``````````````sN ``````-oyhhyyyyhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyyyyyyymy.``````````");
		Thread.sleep(200);
		System.out.println(
				"`````````````````````````````.d: y./-d.y h`-y/+`+m-s:-d.```````` M+```````````` syhh.y`oo+.s``````````````M/`````````````hyhh`s/-o+.o`````````````:M:```````````````/hhyhhy-```````````````/M``````````````hhyy yo`so`s`````````````+M ````````````.hhys+o/:++-+.```````````` M+```````+/+so:++ -hhys+o/:++-+.````````:M-``````````````hyhhs:os`s``````````````/M```````````````````````````````````````/M``````````````.hhyys:o+.+````````````` sN `````````./ohhdd::::::::::::::::::::::::::::sdhhhhhhdmo`````````");
		Thread.sleep(200);
		System.out.println(
				"``````````````````````````````.``.`.:...`.``...``.``-:.````````` M+`````````````-`....`--`:.``````````````M/`````````````-`.-`.`-.`:.`````````````:M:`````````````````.--.`````````````````/M.`````````````-.`.`.`:.`:.`````````````+M ````````````...`.--`--`--``````````````M+```````.`-``..``...`---.--`--`````````:M-``````````````.`..`:`.:```````````````/M```````````````````````````````````````+M.``````````````..`-./`-:```````````````sN `````````````-++`````````````````````````````.:::::::::-````````");
		Thread.sleep(200);
		System.out.println(
				"```````````````````````````````````````````````````````````````` M+```````````````````````````````````````M/``````````````````````````````````````:M:``````````````````````````````````````/M```````````````````````````````````````+M `````````````````````````````````````` M+``````````````````````````````````````:M-``````````````````````````````````````/M```````````````````````````````````````+M`````````````````````````````````````` sN ````````````````````````````````````````````````````````````````");
		Thread.sleep(200);
		System.out.println(
				"```````````````````````````````````````````````````````````````` M+```````````````````````````````````````M/``````````````````````````````````````:M:``````````````````````````````````````/M```````````````````````````````````````+M `````````````````````````````````````` M+``````````````````````````````````````:M-``````````````````````````````````````/M```````````````````````````````````````+M`````````````````````````````````````` sN ````````````````````````````````````````````````````````````````");
	}
}