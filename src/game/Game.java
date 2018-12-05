<<<<<<< HEAD
package game;

import java.io.IOException;
import card.Card;
import card.RailRoad;
import dependancy.ConsoleUI;
import dependancy.menu;
import enumeration.CardType;
import enumeration.Token;

public class Game {

	// Class level variables
	public int turn, countPlayers, roundCount, countOfDoublesRolled = 0;
	public Player[] players;
	Die die = new Die();
	Board board = new Board();
	private boolean mortgaged;

	/**
	 * Initialize the game by assigning names, tokens and initial balance.
	 * 
	 * @param totalPlayers insert the number of player
	 */
	private void init(int totalPlayers) throws IOException {

		for (Token t : Token.values()) {
			menu.tokenArray.add(t);
		}

		players = new Player[totalPlayers];
		for (int i = 0; i < players.length; i++) {
			String playerName = ConsoleUI.promptForInput("\nEnter player " + (i + 1) + "'s name", false);

			System.out.println("\nOk, " + playerName + " it is time to choose your token.");
			Token selection = menu.chooseYourToken();

			System.out.println("Time to roll the dice to see who starts.");
			int total = rollForOrder();

			Player newPlayer = new Player(playerName, selection, 1500, total, 0);
			newPlayer.jailCardOwned[0] = board.chance.get(0);
			players[i] = newPlayer;

			// Finish interaction with players[i]
			if (players.length == totalPlayers) {
				System.out.println("Thank you " + players[i].getName() + ".");
			} else {
				System.out.println("Thank you " + players[i].getName() + ". Now let me ask your friend.\n");
			}

		}
		checkForTie();
		sort();
	}

	/**
	 * Method that lets the user roll
	 * 
	 * @return the total number the player rolled
	 */
	private int rollForOrder() throws IOException {
		String[] options = new String[1];
		options[0] = "Let's roll those dice!";
		int rollOptions = ConsoleUI.promptForMenuSelection(options);
		if (rollOptions == 0) {
			die.roll();
			whatYouRolled();
		}
		return die.getTotal();
	}

	/**
	 * Tiny method that will print each die you rolled and the total as well
	 */
	private void whatYouRolled() {
		System.out.println("\nYou have rolled " + die.getDieOne() + " and " + die.getDieTwo());
		System.out.println("Your total is: " + die.getTotal());
	}

	/**
	 * Checks for tie when players are rolling to see who's starts (CURRENTLY NEEDS
	 * FIX)
	 */
	private void checkForTie() throws IOException {
		for (int i = 2; i <= 12; i++) {
			int count = 0;
			for (int j = 0; j < players.length; j++) {
				if (i == players[j].getTurn()) {
					count++;
				}
			}
			if (count > 1) {
				for (int j2 = 0; j2 < players.length; j2++) {
					if (i == players[j2].getTurn()) {
						System.out.println("\nThere is a tie!");
						System.out.println("\n" + players[j2].getName() + ", you can roll again");
						players[j2].setTurn(rollForOrder());
						count = 0;
					}
				}
			}
		}
	}

	/**
	 * This method will sort the player in descending order
	 */
	public void sort() {
		for (int i = 0; i < players.length - 1; i++) {
			for (int j = 0; j < players.length - i - 1; j++) {
				if (players[j].getTurn() < players[j + 1].getTurn()) {
					Player temp = players[j];
					players[j] = players[j + 1];
					players[j + 1] = temp;
				}
			}
		}
	}

	/**
	 * Without a while loop to only run the game once. Later on we can comment them
	 * back to let the game keep running. Might need a little fix after one game is
	 * played
	 * 
	 * @throws InterruptedException
	 */
	public void run() throws IOException, InterruptedException {
		// boolean keepRunning = true;
		// while (keepRunning) {

		board.printWelcome();
		int action = menu.printMainMenu();
		takeAction(action);

		// keepRunning = takeAction(action);
		// }
	}

	/**
	 * Handles the selection from the user
	 * 
	 * @param action the selection from user of the main menu
	 * @return if the game needs to keepRunning or not
	 */
	private boolean takeAction(int action) throws IOException {
		switch (action) {
		case 0:
			speedDieRules();
			break;
		case 1:
			classicMonopolyRules();
			break;
		case 2:
			return false;
		default:
			throw new IllegalArgumentException("Invalid action " + action);
		}
		return true;
	}

	/**
	 * After selection this method through the main menu, it will play this version
	 * of monopoly
	 */
	private void classicMonopolyRules() throws IOException {
		boolean gameOver = false;
		System.out.println("Welcome to Monopoly\nClassic Rules");
		int howManyPlayers = ConsoleUI.promptForInt("Let's get started by having a count of the players.\n"
				+ "Remember that the minimum is 2 and maximum is 8", 2, 8);
		init(howManyPlayers);
		while (!gameOver) {
			// handle turns
			for (int i = 0; i < players.length; i++) {
				turn(players[i]);
			}
			roundCount++;
		}
	}

	/**
	 * This method will handle the first time a player takes the turn
	 * 
	 * @param p the player taking the turn
	 */
	public void turn(Player currentPlayer) throws IOException {

		if (currentPlayer.isInJail == true) {
			System.out.println("\n\n\nOk " + currentPlayer.getName() + ", let's get you out of jail.");
			handleJail(currentPlayer);
		} else {
			boolean isYourTurn = true;
			System.out.println("\nAlright player, " + currentPlayer.getToken() + " you're up.");

			while (isYourTurn) {
				board.printBoard(currentPlayer);
				int action = menu.printTurnMenu();
				switch (action) {
				case 0:
					isYourTurn = regularTurn(currentPlayer);
					break;
				case 1:
					showBalance(currentPlayer);
					break;
				case 2:
					showProperties(currentPlayer);
					break;
				case 3:
					buyHouse();
					break;
				case 4:
					tradeCards();
					break;
				default:
					throw new IllegalArgumentException("Invalid action " + action);
				}
			}
		}
	}

	/**
	 * This method will handle each option of the menu to help the currentPlayer to
	 * get out of jail.
	 * 
	 * @param currentPlayer who's turn is it.
	 */
	private void handleJail(Player currentPlayer) throws IOException {
		int action = menu.printJailMenu();
		switch (action) {
		case 0:

			System.out.println("You have 3 chances to get doubles and get out of jail this turn");

			int selection = ConsoleUI.promptForInt("[0]\tRoll dice", 0, 0);
			if (selection == 0) {
				die.roll();
				whatYouRolled();
				if (die.getDieOne() == die.getDieTwo()) {
					breakOutOfJail(currentPlayer);
					movePlayer(die.getTotal(), currentPlayer);
					board.printBoard(currentPlayer);
					currentPlayer.setTurnInJail(0);
					// handle what happens when you lay on a property
				} else {
					if (currentPlayer.getTurnInJail() > 1) {
						System.out.println(
								"\nSince you have rolled 3 times and you didn't rolled double, the bank took $50 off of your balance.");
						breakOutOfJail(currentPlayer);
						currentPlayer.setBalance(-50);
						break;
					}
					currentPlayer.setTurnInJail(currentPlayer.getTurnInJail() + 1);
					turnAfterRoll(currentPlayer);
					break;
				}
			}
			breakOutOfJail(currentPlayer);
			break;
		case 1:
			if (currentPlayer.jailCardOwned[1] != null) {

				if (currentPlayer.jailCardOwned[1].cardType == CardType.CHANCE) {
					board.chance.add(currentPlayer.jailCardOwned[1]);
				} else {
					board.communityChest.add(currentPlayer.jailCardOwned[1]);
				}
				currentPlayer.jailCardOwned[1] = null;
				breakOutOfJail(currentPlayer);
				turn(currentPlayer);
			} else if (currentPlayer.jailCardOwned[0] != null) {
				if (currentPlayer.jailCardOwned[0].cardType == CardType.CHANCE) {
					board.chance.add(currentPlayer.jailCardOwned[0]);
				} else {
					board.communityChest.add(currentPlayer.jailCardOwned[0]);
				}
				currentPlayer.jailCardOwned[0] = null;
				breakOutOfJail(currentPlayer);
				turn(currentPlayer);

			} else {
				System.out.println("You don't have any \"Get out of Jail\" card");
			}

			break;
		case 2:
			System.out.println("" + "*********************************************************************" + "\n\nOk "
					+ currentPlayer.getName() + ", you are free now.");
			currentPlayer.setBalance(-50);
			breakOutOfJail(currentPlayer);
			turn(currentPlayer);
			break;
		default:
			throw new IllegalArgumentException("Invalid action " + action);
		}

	}

	/**
	 * Reset the flag isInJail of the currentPlayer to false
	 * 
	 * @param currentPlayer player currently taking the turn
	 * @return false to break out of the loop
	 */
	private boolean breakOutOfJail(Player currentPlayer) {
		currentPlayer.isInJail(false);
		return false;
	}

	/**
	 * Method that will let you either take a regular turn if you rolled doubles or
	 * your end of the turn options if you did not rolled doubles
	 * 
	 * @param currentPlayer who's turn it.
	 * @return if the player ended his turn or not.
	 */
	private boolean regularTurn(Player currentPlayer) throws IOException {
		die.roll();
		whatYouRolled();
		movePlayer(die.getTotal(), currentPlayer);

		System.out.println("\n*************************************\n" + "You landed on: "
				+ board.squares[currentPlayer.getLocation()].getName() + "\n"
				+ "*************************************");
		landOnProperty(currentPlayer, die.getTotal());

		if (die.getDieOne() == die.getDieTwo()) {
			countOfDoublesRolled++;
			if (countOfDoublesRolled == 3) {
				System.out.println(""
						+ "\n*************************************************************************************\n"
						+ currentPlayer.name
						+ " you have rolled 3 doubles. You will not be visiting jail this time; you will be going to jail.\n"
						+ "You also lose your turn. Better luck next time!\n Have fun pumping iron.\n"
						+ "*************************************************************************************");
				sendToJail(currentPlayer, 10);
				currentPlayer.isInJail(true);
				countOfDoublesRolled = 0;
				return false;
			}
			turn(currentPlayer);
			return false;
		}
		turnAfterRoll(currentPlayer);
		return false;
	}

	/**
	 * Method that will handle the options available when the player lands on a
	 * property
	 * 
	 * @param currentPlayer taking the turn
	 */
	private void landOnProperty(Player currentPlayer, int location) throws IOException {

		// MEDITERRANEAN AVENUE

		// COMMUNITY CHEST
		if (currentPlayer.getLocation() == 2) {
			handleSpecialCard(currentPlayer);
		}
		// BALTIC AVENUE
		if (currentPlayer.getLocation() == 3) {
			if (board.ownsDeed(1, currentPlayer)) {
				propertyMenuSelection(currentPlayer, 1, -60);
			} else {
				payRent(currentPlayer, 4, 1);
			}
		}
		// INCOME TAX
		if (currentPlayer.getLocation() == 4) {
			handleIncomeTax(currentPlayer);
		}

		// READING RAILROAD
		if (currentPlayer.getLocation() == 5) {
			if (board.ownsDeed(2, currentPlayer)) {
				propertyMenuSelection(currentPlayer, 2, -200);
			} else {
				railRoadRent(currentPlayer, 2);
			}
		}

		// ORIENTAL AVENUE
		if (currentPlayer.getLocation() == 6) {
			if (board.ownsDeed(3, currentPlayer)) {
				propertyMenuSelection(currentPlayer, 3, -100);
			} else {
				payRent(currentPlayer, 6, 3);
			}
		}
		// CHANCE
		if (currentPlayer.getLocation() == 7) {
			handleSpecialCard(currentPlayer);
		}

		// VERMONT AVENUE
		if (currentPlayer.getLocation() == 8) {
			if (board.ownsDeed(4, currentPlayer)) {
				propertyMenuSelection(currentPlayer, 4, -board.deeds[location].getCost());
			} else {
				payRent(currentPlayer, 6, 4);
			}
		}
		// CONNECTICUT AVENUE
		if (currentPlayer.getLocation() == 9) {
			if (board.ownsDeed(5, currentPlayer)) {
				propertyMenuSelection(currentPlayer, 5, -120);
			} else {
				payRent(currentPlayer, 8, 5);
			}
		}
		// ST. CHARLES PLACE
		if (currentPlayer.getLocation() == 11) {
			if (board.ownsDeed(6, currentPlayer)) {
				propertyMenuSelection(currentPlayer, 6, -140);
			} else {
				payRent(currentPlayer, 10, 6);
			}
		}
		// ELECTRIC COMPANY
		if (currentPlayer.getLocation() == 12) {
			if (board.ownsDeed(7, currentPlayer)) {
				propertyMenuSelection(currentPlayer, 7, -150);
			} else {
				utilityRent(currentPlayer, 7);
			}
		}
		// STATES AVENUE
		if (currentPlayer.getLocation() == 13) {
			if (board.ownsDeed(8, currentPlayer)) {
				propertyMenuSelection(currentPlayer, 8, -140);
			} else {
				payRent(currentPlayer, 10, 8);
			}
		}
		// VIRGINIA AVENUE
		if (currentPlayer.getLocation() == 14) {
			if (board.ownsDeed(9, currentPlayer)) {
				propertyMenuSelection(currentPlayer, 9, -160);
			} else {
				payRent(currentPlayer, 12, 9);
			}
		}
		// PENNSYLVANIA RAILROAD
		if (currentPlayer.getLocation() == 15) {
			if (board.ownsDeed(10, currentPlayer)) {
				propertyMenuSelection(currentPlayer, 10, -200);
			} else {
				railRoadRent(currentPlayer, 10);
			}
		}
		// ST. JAMES PLACE
		if (currentPlayer.getLocation() == 16) {
			if (board.ownsDeed(11, currentPlayer)) {
				propertyMenuSelection(currentPlayer, 11, -180);
			} else {
				payRent(currentPlayer, 14, 11);
			}
		}
		// COMMUNITY CHEST
		if (currentPlayer.getLocation() == 17) {
			handleSpecialCard(currentPlayer);
		}
		// TENNESSE AVENUE
		if (currentPlayer.getLocation() == 18) {
			if (board.ownsDeed(12, currentPlayer)) {
				propertyMenuSelection(currentPlayer, 12, -180);
			} else {
				payRent(currentPlayer, 14, 12);
			}
		}
		// NEW YORK AVENUE
		if (currentPlayer.getLocation() == 19) {
			if (board.ownsDeed(13, currentPlayer)) {
				propertyMenuSelection(currentPlayer, 13, -200);
			} else {
				payRent(currentPlayer, 16, 13);
			}
		}
		// KENTUCKY AVENUE
		if (currentPlayer.getLocation() == 21) {
			if (board.ownsDeed(14, currentPlayer)) {
				propertyMenuSelection(currentPlayer, 14, -220);
			} else {
				payRent(currentPlayer, 18, 14);
			}
		}
		// CHANCE
		if (currentPlayer.getLocation() == 22) {
			handleSpecialCard(currentPlayer);
		}

		// INDIANA AVENUE
		if (currentPlayer.getLocation() == 23) {
			if (board.ownsDeed(15, currentPlayer)) {
				propertyMenuSelection(currentPlayer, 15, -220);
			} else {
				payRent(currentPlayer, 18, 15);
			}
		}
		// ILLINOIS AVENUE
		if (currentPlayer.getLocation() == 24) {
			if (board.ownsDeed(16, currentPlayer)) {
				propertyMenuSelection(currentPlayer, 16, -240);
			} else {
				payRent(currentPlayer, 20, 16);
			}
		}
		// B. & O. RAILROAD
		if (currentPlayer.getLocation() == 25) {
			if (board.ownsDeed(17, currentPlayer)) {
				propertyMenuSelection(currentPlayer, 17, -200);
			} else {
				railRoadRent(currentPlayer, 17);
			}
		}
		// ATLANTIC AVENUE
		if (currentPlayer.getLocation() == 26) {
			if (board.ownsDeed(18, currentPlayer)) {
				propertyMenuSelection(currentPlayer, 18, -260);
			} else {
				payRent(currentPlayer, 22, 18);
			}
		}
		// VENTNOR AVENUE
		if (currentPlayer.getLocation() == 27) {
			if (board.ownsDeed(19, currentPlayer)) {
				propertyMenuSelection(currentPlayer, 19, -260);
			} else {
				payRent(currentPlayer, 22, 19);
			}
		}
		// WATER WORKS
		if (currentPlayer.getLocation() == 28) {
			if (board.ownsDeed(20, currentPlayer)) {
				propertyMenuSelection(currentPlayer, 20, -150);
			} else {
				utilityRent(currentPlayer, 20);
			}
		}
		// MARVIN GARDENS
		if (currentPlayer.getLocation() == 29) {
			if (board.ownsDeed(21, currentPlayer)) {
				propertyMenuSelection(currentPlayer, 21, -280);
			} else {
				payRent(currentPlayer, 24, 21);
			}
		}
		// GO TO JAIL
		if (currentPlayer.getLocation() == 30) {
			currentPlayer.setLocation(10);
			currentPlayer.isInJail = true;
		}

		// PACIFIC AVENUE
		if (currentPlayer.getLocation() == 31) {
			if (board.ownsDeed(22, currentPlayer)) {
				propertyMenuSelection(currentPlayer, 22, -300);
			} else {
				payRent(currentPlayer, 26, 22);
			}
		}
		// NORTH CAROLINA AVENUE
		if (currentPlayer.getLocation() == 32) {
			if (board.ownsDeed(23, currentPlayer)) {
				propertyMenuSelection(currentPlayer, 23, -300);
			} else {
				payRent(currentPlayer, 26, 23);
			}
		}
		// COMMUNITY CHEST
		if (currentPlayer.getLocation() == 33) {
			handleSpecialCard(currentPlayer);
		}
		// PENNSYLVANIA AVENUE
		if (currentPlayer.getLocation() == 34) {
			if (board.ownsDeed(24, currentPlayer)) {
				propertyMenuSelection(currentPlayer, 24, -320);
			} else {
				payRent(currentPlayer, 28, 24);
			}
		}
		// SHORT LINE
		if (currentPlayer.getLocation() == 35) {
			if (board.ownsDeed(25, currentPlayer)) {
				propertyMenuSelection(currentPlayer, 25, -200);
			} else {
				railRoadRent(currentPlayer, 25);
			}
		}
		// CHANCE
		if (currentPlayer.getLocation() == 36) {
			handleSpecialCard(currentPlayer);
		}
		// PARK PLACE
		if (currentPlayer.getLocation() == 37) {
			if (board.ownsDeed(26, currentPlayer)) {
				propertyMenuSelection(currentPlayer, 26, -350);
			} else {
				payRent(currentPlayer, 35, 26);
			}
		}
		// LUXURY TAX
		if (currentPlayer.getLocation() == 38) {
			System.out.println("\nThe bank took $100 off of your balance");
			currentPlayer.setBalance(-100);
		}

		// BOARDWALK
		if (currentPlayer.getLocation() == 39) {
			if (board.ownsDeed(27, currentPlayer)) {
				propertyMenuSelection(currentPlayer, 27, -400);
			} else {
				payRent(currentPlayer, 50, 27);
			}
		}
	}

	private void handleSpecialCard(Player currentPlayer) {
		Card topcard = board.chance.get(0);
		switch (topcard.cardName) {
		case JAIL_FREE:
			if (currentPlayer.jailCardOwned[0] == null) {
				currentPlayer.jailCardOwned[0] = topcard;
				board.chance.remove(0);
			} else {
				currentPlayer.jailCardOwned[1] = topcard;
				board.chance.remove(0);
			}
			break;
		case MOVEMENT:
			break;
		case PAY_BUILDING_TAX:
			break;
		case PAY_MONEY:
			break;
		case PAY_OR_RECEIVE_PLAYERS:
			break;
		case RECEIVE_MONEY:
			break;
		default:
			break;

		}

	}

	private void handleIncomeTax(Player currentPlayer) {

		int totalToPay = 0;
		for (card.Property cards : currentPlayer.propertiesOwned) {
			totalToPay += cards.getCost() * 0.1;
		}
		totalToPay += currentPlayer.getBalance() * 0.1;
		// HOUSES

		System.out.println("10% of your income is: " + totalToPay);
		currentPlayer.setBalance(totalToPay);

	}

	private void utilityRent(Player currentPlayer, int deedLocation) throws IOException {
		int totalOwed = 0;
		int selection = menu.printPayRentMenu();
		if (selection == 0) {
			System.out.println("\nYou will now roll the dice to see how much you will have to pay rent.");
			int selection2 = menu.rollDiceMenu();
			if (selection2 == 0) {
				die.roll();
				whatYouRolled();
				for (Player playerOwner : players) {
					if (playerOwner.propertiesOwned.contains(board.deeds[deedLocation])) {
						if (playerOwner.propertiesOwned.contains(board.deeds[7])
								&& playerOwner.propertiesOwned.contains(board.deeds[20])) {
							totalOwed = 10 * die.getTotal();
							System.out.println("Since you rolled " + die.getTotal() + ", and " + playerOwner.getName()
									+ " owns 2 property\n" + "You are paying $" + totalOwed);
						} else if (playerOwner.propertiesOwned.contains(board.deeds[7])
								|| playerOwner.propertiesOwned.contains(board.deeds[20])) {
							totalOwed = 4 * die.getTotal();
							System.out.println("Since you rolled " + die.getTotal() + ", and " + playerOwner.getName()
									+ " owns 1 property\n" + "You are paying $" + totalOwed);
						}
						playerOwner.setBalance(totalOwed);
						currentPlayer.setBalance(-totalOwed);
					}
				}
			}

		}
	}

	private void railRoadRent(Player currentPlayer, int deedLocation) throws IOException {
		int totalOwed = 0;
		int selection = menu.printPayRentMenu();
		if (selection == 0) {
			for (Player playerOwner : players) {
				if (playerOwner.propertiesOwned.contains(board.deeds[deedLocation])) {
					int counter = 0;
					for (card.Property titledeed : playerOwner.propertiesOwned) {
						if (titledeed instanceof RailRoad) {
							counter++;
						}
					}
					switch (counter) {
					case 1:
						totalOwed = 25;
						break;
					case 2:
						totalOwed = 50;
						break;
					case 3:
						totalOwed = 100;
						break;
					case 4:
						totalOwed = 200;
						break;
					default:
						break;
					}
					playerOwner.setBalance(totalOwed);
					currentPlayer.setBalance(-totalOwed);
				}
			}
		}

	}

	private void propertyMenuSelection(Player currentPlayer, int location, int cost) throws IOException {
		int selection = menu.printBuyPropertiesMenu();
		switch (selection) {
		case 0:
			System.out.println("\nYou now own this deed!");
			currentPlayer.propertiesOwned.add(board.deeds[location]);
			currentPlayer.setBalance(cost);
			break;
		case 1:
			System.out.println("\n\nSince you decided not to buy it, the bank will auction this property.");
			// HANDLE AUCTIONING
			break;
		default:
			break;
		}
	}

	private void payRent(Player currentPlayer, int regularRent, int deedLocation) throws IOException {
		int selection = menu.printPayRentMenu();
		if (selection == 0) {
			// CHECK IF OWNER HAS ALL GROUP PROPERTIES
			// DOUBLE RENT
			// CHECK IF THERE IS HOUSES/HOTELS
			// ELSE
			currentPlayer.setBalance(-regularRent);

			for (Player player : players) {
				if (player.propertiesOwned.contains(board.deeds[deedLocation])) {
					player.setBalance(regularRent);
					System.out.println("\n" + player.getName() + " says thanks for the money!");
				}
			}
		}
	}

	/**
	 * Method that will let finish your turn (does not have a roll dice option)
	 * 
	 * @param currentPlayer who's turn is it
	 */
	private void turnAfterRoll(Player currentPlayer) throws IOException {
		boolean isYourTurnAfterRoll = true;
		while (isYourTurnAfterRoll) {
			board.printBoard(currentPlayer);
			int action = menu.printMenuAfterRoll();
			switch (action) {
			case 0:
				showBalance(currentPlayer);
				break;
			case 1:
				showProperties(currentPlayer);
				break;
			case 2:
				buyHouse();
				break;
			case 3:
				tradeCards();
				break;
			case 4:
				isYourTurnAfterRoll = false;
				break;
			default:
				throw new IllegalArgumentException("Invalid action " + action);
			}
		}
	}

	/**
	 * Method to print what the balance is.
	 * 
	 * @param currentPlayer who's turn is it.
	 */
	private void showBalance(Player currentPlayer) {
		System.out.println("\nYour balance is: " + currentPlayer.getBalance() + "\n");
	}

	/**
	 * Method to print/show the properties the current player owns.
	 * 
	 * @param currentPlayer who's turn is it
	 */
	private void showProperties(Player currentPlayer) {
		if (currentPlayer.getPropertiesOwned().isEmpty()) {
			System.out.println("\n\nSorry, you don't own any properties.\nKeep playing to see if get better luck!");
		} else {
			System.out.print("\nThe properties you own are:\n");

			for (int i = 0; i < currentPlayer.getPropertiesOwned().size(); i++) {
				System.out.print("[");
				if (i == currentPlayer.getPropertiesOwned().size() - 1) {
					System.out.print(currentPlayer.propertiesOwned.get(i).getPropertyName());
				} else {
					System.out.print(currentPlayer.propertiesOwned.get(i).getPropertyName() + "]");
				}
			}
			System.out.print("]");

			System.out.print("\nThe cost of buying a house is is:\n");

			for (int i = 0; i < currentPlayer.getPropertiesOwned().size(); i++) {
				System.out.print("[");
				if (i == currentPlayer.getPropertiesOwned().size() - 1) {
					if (currentPlayer.propertiesOwned.get(i).getBuildingCost() == 0) {
						System.out.print("Not allowed]");
					} else {
						System.out.print(currentPlayer.propertiesOwned.get(i).getBuildingCost() + "]");
					}
				} else {
					if (currentPlayer.propertiesOwned.get(i).getBuildingCost() == 0) {
						System.out.print("Not allowed]");
					} else {
						System.out.print(currentPlayer.propertiesOwned.get(i).getBuildingCost() + "]");
					}
				}
			}

			// SHOW RENT

		}
	}

	// UNDER CONSTRUCTION - PLEASE ADD SOME CODE HERE
	private void buyHouse() {

	}

	// UNDER CONSTRUCTION - PLEASE ADD SOME CODE HERE
	private void tradeCards() {

	}

	// tells whether or not property is mortgaged
	public boolean isMortgaged() {
		return mortgaged;
	}

	// unmortgage property
	public void setMortgage(boolean bool) {
		mortgaged = bool;
	}

	public String toString() {
		String status = "";

		if (isMortgaged())
			// will show if property is mortgaged
			status += " (Mortgaged)";

		return super.toString() + status;
	}

	/**
	 * Method that will move the player base on the total number they rolled
	 * 
	 * @param num the total number that the dice got
	 * @param p   the player who's turn is it
	 */
	private void movePlayer(int totalDie, Player currentPlayer) {
		while (totalDie > 0) {
			totalDie--;
			currentPlayer.addLocation(1);
			if (currentPlayer.getLocation() == 40) {
				currentPlayer.setLocation(0);
			}
			if (currentPlayer.getLocation() == 0) {
				currentPlayer.setBalance(200);
			}

		}
	}

	/**
	 * Method that will turn on the isInJail flag of a player and update location of
	 * the player if is sent to jail.
	 * 
	 * @param currentPlayer
	 * @param jailLocation
	 */
	private void sendToJail(Player currentPlayer, int jailLocation) {
		currentPlayer.setLocation(jailLocation);
		currentPlayer.isInJail(true);
	}

	// UNDER CONSTRUCTION - PLEASE ADD SOME CODE HERE
	private void speedDieRules() {
		System.out.println("Please read rules inside box.");
	}
}
=======
package game;

import java.io.IOException;
import card.Card;
import card.RailRoad;
import dependancy.ConsoleUI;
import dependancy.menu;
import enumeration.CardType;
import enumeration.Token;

public class Game {

	// Class level variables
	public int turn, countPlayers, roundCount, countOfDoublesRolled = 0;
	public Player[] players;
	Die die = new Die();
	Board board = new Board();
	private boolean mortgaged;

	/**
	 * Initialize the game by assigning names, tokens and initial balance.
	 * 
	 * @param totalPlayers insert the number of player
	 */
	private void init(int totalPlayers) throws IOException {

		for (Token t : Token.values()) {
			menu.tokenArray.add(t);
		}

		players = new Player[totalPlayers];
		for (int i = 0; i < players.length; i++) {
			String playerName = ConsoleUI.promptForInput("\nEnter player " + (i + 1) + "'s name", false);

			System.out.println("\nOk, " + playerName + " it is time to choose your token.");
			Token selection = menu.chooseYourToken();

			System.out.println("Time to roll the dice to see who starts.");
			int total = rollForOrder();

			Player newPlayer = new Player(playerName, selection, 1500, total, 0);
			newPlayer.jailCardOwned[0] = board.chance.get(0);
			players[i] = newPlayer;

			// Finish interaction with players[i]
			if (players.length == totalPlayers) {
				System.out.println("Thank you " + players[i].getName() + ".");
			} else {
				System.out.println("Thank you " + players[i].getName() + ". Now let me ask your friend.\n");
			}

		}
		checkForTie();
		sort();
	}

	/**
	 * Method that lets the user roll
	 * 
	 * @return the total number the player rolled
	 */
	private int rollForOrder() throws IOException {
		String[] options = new String[1];
		options[0] = "Let's roll those dice!";
		int rollOptions = ConsoleUI.promptForMenuSelection(options);
		if (rollOptions == 0) {
			die.roll();
			whatYouRolled();
		}
		return die.getTotal();
	}

	/**
	 * Tiny method that will print each die you rolled and the total as well
	 */
	private void whatYouRolled() {
		System.out.println("\nYou have rolled " + die.getDieOne() + " and " + die.getDieTwo());
		System.out.println("Your total is: " + die.getTotal());
	}

	/**
	 * Checks for tie when players are rolling to see who's starts (CURRENTLY NEEDS
	 * FIX)
	 */
	private void checkForTie() throws IOException {
		for (int i = 2; i <= 12; i++) {
			int count = 0;
			for (int j = 0; j < players.length; j++) {
				if (i == players[j].getTurn()) {
					count++;
				}
			}
			if (count > 1) {
				for (int j2 = 0; j2 < players.length; j2++) {
					if (i == players[j2].getTurn()) {
						System.out.println("\nThere is a tie!");
						System.out.println("\n" + players[j2].getName() + ", you can roll again");
						players[j2].setTurn(rollForOrder());
						count = 0;
					}
				}
			}
		}
	}

	/**
	 * This method will sort the player in descending order
	 */
	public void sort() {
		for (int i = 0; i < players.length - 1; i++) {
			for (int j = 0; j < players.length - i - 1; j++) {
				if (players[j].getTurn() < players[j + 1].getTurn()) {
					Player temp = players[j];
					players[j] = players[j + 1];
					players[j + 1] = temp;
				}
			}
		}
	}

	/**
	 * Without a while loop to only run the game once. Later on we can comment them
	 * back to let the game keep running. Might need a little fix after one game is
	 * played
	 * 
	 * @throws InterruptedException
	 */
	public void run() throws IOException, InterruptedException {
		// boolean keepRunning = true;
		// while (keepRunning) {

		board.printWelcome();
		int action = menu.printMainMenu();
		takeAction(action);

		// keepRunning = takeAction(action);
		// }
	}

	/**
	 * Handles the selection from the user
	 * 
	 * @param action the selection from user of the main menu
	 * @return if the game needs to keepRunning or not
	 */
	private boolean takeAction(int action) throws IOException {
		switch (action) {
		case 0:
			speedDieRules();
			break;
		case 1:
			classicMonopolyRules();
			break;
		case 2:
			return false;
		default:
			throw new IllegalArgumentException("Invalid action " + action);
		}
		return true;
	}

	/**
	 * After selection this method through the main menu, it will play this version
	 * of monopoly
	 */
	private void classicMonopolyRules() throws IOException {
		boolean gameOver = false;
		System.out.println("Welcome to Monopoly\nClassic Rules");
		int howManyPlayers = ConsoleUI.promptForInt("Let's get started by having a count of the players.\n"
				+ "Remember that the minimum is 2 and maximum is 8", 2, 8);
		init(howManyPlayers);
		while (!gameOver) {
			// handle turns
			for (int i = 0; i < players.length; i++) {
				turn(players[i]);
			}
			roundCount++;
		}
	}

	/**
	 * This method will handle the first time a player takes the turn
	 * 
	 * @param p the player taking the turn
	 */
	public void turn(Player currentPlayer) throws IOException {

		if (currentPlayer.isInJail == true) {
			System.out.println("\n\n\nOk " + currentPlayer.getName() + ", let's get you out of jail.");
			handleJail(currentPlayer);
		} else {
			boolean isYourTurn = true;
			System.out.println("\nAlright player, " + currentPlayer.getToken() + " you're up.");

			while (isYourTurn) {
				board.printBoard(currentPlayer);
				int action = menu.printTurnMenu();
				switch (action) {
				case 0:
					isYourTurn = regularTurn(currentPlayer);
					break;
				case 1:
					showBalance(currentPlayer);
					break;
				case 2:
					showProperties(currentPlayer);
					break;
				case 3:
					buyHouse();
					break;
				case 4:
					tradeCards();
					break;
				default:
					throw new IllegalArgumentException("Invalid action " + action);
				}
			}
		}
	}

	/**
	 * This method will handle each option of the menu to help the currentPlayer to
	 * get out of jail.
	 * 
	 * @param currentPlayer who's turn is it.
	 */
	private void handleJail(Player currentPlayer) throws IOException {
		int action = menu.printJailMenu();
		switch (action) {
		case 0:

			System.out.println("You have 3 chances to get doubles and get out of jail this turn");

			int selection = ConsoleUI.promptForInt("[0]\tRoll dice", 0, 0);
			if (selection == 0) {
				die.roll();
				whatYouRolled();
				if (die.getDieOne() == die.getDieTwo()) {
					breakOutOfJail(currentPlayer);
					movePlayer(die.getTotal(), currentPlayer);
					board.printBoard(currentPlayer);
					currentPlayer.setTurnInJail(0);
					// handle what happens when you lay on a property
				} else {
					if (currentPlayer.getTurnInJail() > 1) {
						System.out.println(
								"\nSince you have rolled 3 times and you didn't rolled double, the bank took $50 off of your balance.");
						breakOutOfJail(currentPlayer);
						currentPlayer.setBalance(-50);
						break;
					}
					currentPlayer.setTurnInJail(currentPlayer.getTurnInJail() + 1);
					turnAfterRoll(currentPlayer);
					break;
				}
			}
			breakOutOfJail(currentPlayer);
			break;
		case 1:
			if (currentPlayer.jailCardOwned[1] != null) {

				if (currentPlayer.jailCardOwned[1].cardType == CardType.CHANCE) {
					board.chance.add(currentPlayer.jailCardOwned[1]);
				} else {
					board.communityChest.add(currentPlayer.jailCardOwned[1]);
				}
				currentPlayer.jailCardOwned[1] = null;
				breakOutOfJail(currentPlayer);
				turn(currentPlayer);
			} else if (currentPlayer.jailCardOwned[0] != null) {
				if (currentPlayer.jailCardOwned[0].cardType == CardType.CHANCE) {
					board.chance.add(currentPlayer.jailCardOwned[0]);
				} else {
					board.communityChest.add(currentPlayer.jailCardOwned[0]);
				}
				currentPlayer.jailCardOwned[0] = null;
				breakOutOfJail(currentPlayer);
				turn(currentPlayer);

			} else {
				System.out.println("You don't have any \"Get out of Jail\" card");
			}

			break;
		case 2:
			System.out.println("" + "*********************************************************************" + "\n\nOk "
					+ currentPlayer.getName() + ", you are free now.");
			currentPlayer.setBalance(-50);
			breakOutOfJail(currentPlayer);
			turn(currentPlayer);
			break;
		default:
			throw new IllegalArgumentException("Invalid action " + action);
		}

	}

	/**
	 * Reset the flag isInJail of the currentPlayer to false
	 * 
	 * @param currentPlayer player currently taking the turn
	 * @return false to break out of the loop
	 */
	private boolean breakOutOfJail(Player currentPlayer) {
		currentPlayer.isInJail(false);
		return false;
	}

	/**
	 * Method that will let you either take a regular turn if you rolled doubles or
	 * your end of the turn options if you did not rolled doubles
	 * 
	 * @param currentPlayer who's turn it.
	 * @return if the player ended his turn or not.
	 */
	private boolean regularTurn(Player currentPlayer) throws IOException {
		die.roll();
		whatYouRolled();
		movePlayer(die.getTotal(), currentPlayer);

		System.out.println("\n*************************************\n" + "You landed on: "
				+ board.squares[currentPlayer.getLocation()].getName() + "\n"
				+ "*************************************");
		landOnProperty(currentPlayer, die.getTotal());

		if (die.getDieOne() == die.getDieTwo()) {
			countOfDoublesRolled++;
			if (countOfDoublesRolled == 3) {
				System.out.println(""
						+ "\n*************************************************************************************\n"
						+ currentPlayer.name
						+ " you have rolled 3 doubles. You will not be visiting jail this time; you will be going to jail.\n"
						+ "You also lose your turn. Better luck next time!\n Have fun pumping iron.\n"
						+ "*************************************************************************************");
				sendToJail(currentPlayer, 10);
				currentPlayer.isInJail(true);
				countOfDoublesRolled = 0;
				return false;
			}
			turn(currentPlayer);
			return false;
		}
		turnAfterRoll(currentPlayer);
		return false;
	}

	/**
	 * Method that will handle the options available when the player lands on a
	 * property
	 * 
	 * @param currentPlayer taking the turn
	 */
	private void landOnProperty(Player currentPlayer, int location) throws IOException {

		// MEDITERRANEAN AVENUE

		// COMMUNITY CHEST
		if (currentPlayer.getLocation() == 2) {
			handleSpecialCard(currentPlayer);
		}
		// BALTIC AVENUE
		if (currentPlayer.getLocation() == 3) {
			if (board.ownsDeed(1, currentPlayer)) {
				propertyMenuSelection(currentPlayer, 1, -60);
			} else {
				payRent(currentPlayer, 4, 1);
			}
		}
		// INCOME TAX
		if (currentPlayer.getLocation() == 4) {
			handleIncomeTax(currentPlayer);
		}

		// READING RAILROAD
		if (currentPlayer.getLocation() == 5) {
			if (board.ownsDeed(2, currentPlayer)) {
				propertyMenuSelection(currentPlayer, 2, -200);
			} else {
				railRoadRent(currentPlayer, 2);
			}
		}

		// ORIENTAL AVENUE
		if (currentPlayer.getLocation() == 6) {
			if (board.ownsDeed(3, currentPlayer)) {
				propertyMenuSelection(currentPlayer, 3, -100);
			} else {
				payRent(currentPlayer, 6, 3);
			}
		}
		// CHANCE
		if (currentPlayer.getLocation() == 7) {
			handleSpecialCard(currentPlayer);
		}

		// VERMONT AVENUE
		if (currentPlayer.getLocation() == 8) {
			if (board.ownsDeed(4, currentPlayer)) {
				propertyMenuSelection(currentPlayer, 4, -board.deeds[location].getCost());
			} else {
				payRent(currentPlayer, 6, 4);
			}
		}
		// CONNECTICUT AVENUE
		if (currentPlayer.getLocation() == 9) {
			if (board.ownsDeed(5, currentPlayer)) {
				propertyMenuSelection(currentPlayer, 5, -120);
			} else {
				payRent(currentPlayer, 8, 5);
			}
		}
		// ST. CHARLES PLACE
		if (currentPlayer.getLocation() == 11) {
			if (board.ownsDeed(6, currentPlayer)) {
				propertyMenuSelection(currentPlayer, 6, -140);
			} else {
				payRent(currentPlayer, 10, 6);
			}
		}
		// ELECTRIC COMPANY
		if (currentPlayer.getLocation() == 12) {
			if (board.ownsDeed(7, currentPlayer)) {
				propertyMenuSelection(currentPlayer, 7, -150);
			} else {
				utilityRent(currentPlayer, 7);
			}
		}
		// STATES AVENUE
		if (currentPlayer.getLocation() == 13) {
			if (board.ownsDeed(8, currentPlayer)) {
				propertyMenuSelection(currentPlayer, 8, -140);
			} else {
				payRent(currentPlayer, 10, 8);
			}
		}
		// VIRGINIA AVENUE
		if (currentPlayer.getLocation() == 14) {
			if (board.ownsDeed(9, currentPlayer)) {
				propertyMenuSelection(currentPlayer, 9, -160);
			} else {
				payRent(currentPlayer, 12, 9);
			}
		}
		// PENNSYLVANIA RAILROAD
		if (currentPlayer.getLocation() == 15) {
			if (board.ownsDeed(10, currentPlayer)) {
				propertyMenuSelection(currentPlayer, 10, -200);
			} else {
				railRoadRent(currentPlayer, 10);
			}
		}
		// ST. JAMES PLACE
		if (currentPlayer.getLocation() == 16) {
			if (board.ownsDeed(11, currentPlayer)) {
				propertyMenuSelection(currentPlayer, 11, -180);
			} else {
				payRent(currentPlayer, 14, 11);
			}
		}
		// COMMUNITY CHEST
		if (currentPlayer.getLocation() == 17) {
			handleSpecialCard(currentPlayer);
		}
		// TENNESSE AVENUE
		if (currentPlayer.getLocation() == 18) {
			if (board.ownsDeed(12, currentPlayer)) {
				propertyMenuSelection(currentPlayer, 12, -180);
			} else {
				payRent(currentPlayer, 14, 12);
			}
		}
		// NEW YORK AVENUE
		if (currentPlayer.getLocation() == 19) {
			if (board.ownsDeed(13, currentPlayer)) {
				propertyMenuSelection(currentPlayer, 13, -200);
			} else {
				payRent(currentPlayer, 16, 13);
			}
		}
		// KENTUCKY AVENUE
		if (currentPlayer.getLocation() == 21) {
			if (board.ownsDeed(14, currentPlayer)) {
				propertyMenuSelection(currentPlayer, 14, -220);
			} else {
				payRent(currentPlayer, 18, 14);
			}
		}
		// CHANCE
		if (currentPlayer.getLocation() == 22) {
			handleSpecialCard(currentPlayer);
		}

		// INDIANA AVENUE
		if (currentPlayer.getLocation() == 23) {
			if (board.ownsDeed(15, currentPlayer)) {
				propertyMenuSelection(currentPlayer, 15, -220);
			} else {
				payRent(currentPlayer, 18, 15);
			}
		}
		// ILLINOIS AVENUE
		if (currentPlayer.getLocation() == 24) {
			if (board.ownsDeed(16, currentPlayer)) {
				propertyMenuSelection(currentPlayer, 16, -240);
			} else {
				payRent(currentPlayer, 20, 16);
			}
		}
		// B. & O. RAILROAD
		if (currentPlayer.getLocation() == 25) {
			if (board.ownsDeed(17, currentPlayer)) {
				propertyMenuSelection(currentPlayer, 17, -200);
			} else {
				railRoadRent(currentPlayer, 17);
			}
		}
		// ATLANTIC AVENUE
		if (currentPlayer.getLocation() == 26) {
			if (board.ownsDeed(18, currentPlayer)) {
				propertyMenuSelection(currentPlayer, 18, -260);
			} else {
				payRent(currentPlayer, 22, 18);
			}
		}
		// VENTNOR AVENUE
		if (currentPlayer.getLocation() == 27) {
			if (board.ownsDeed(19, currentPlayer)) {
				propertyMenuSelection(currentPlayer, 19, -260);
			} else {
				payRent(currentPlayer, 22, 19);
			}
		}
		// WATER WORKS
		if (currentPlayer.getLocation() == 28) {
			if (board.ownsDeed(20, currentPlayer)) {
				propertyMenuSelection(currentPlayer, 20, -150);
			} else {
				utilityRent(currentPlayer, 20);
			}
		}
		// MARVIN GARDENS
		if (currentPlayer.getLocation() == 29) {
			if (board.ownsDeed(21, currentPlayer)) {
				propertyMenuSelection(currentPlayer, 21, -280);
			} else {
				payRent(currentPlayer, 24, 21);
			}
		}
		// GO TO JAIL
		if (currentPlayer.getLocation() == 30) {
			currentPlayer.setLocation(10);
			currentPlayer.isInJail = true;
		}

		// PACIFIC AVENUE
		if (currentPlayer.getLocation() == 31) {
			if (board.ownsDeed(22, currentPlayer)) {
				propertyMenuSelection(currentPlayer, 22, -300);
			} else {
				payRent(currentPlayer, 26, 22);
			}
		}
		// NORTH CAROLINA AVENUE
		if (currentPlayer.getLocation() == 32) {
			if (board.ownsDeed(23, currentPlayer)) {
				propertyMenuSelection(currentPlayer, 23, -300);
			} else {
				payRent(currentPlayer, 26, 23);
			}
		}
		// COMMUNITY CHEST
		if (currentPlayer.getLocation() == 33) {
			handleSpecialCard(currentPlayer);
		}
		// PENNSYLVANIA AVENUE
		if (currentPlayer.getLocation() == 34) {
			if (board.ownsDeed(24, currentPlayer)) {
				propertyMenuSelection(currentPlayer, 24, -320);
			} else {
				payRent(currentPlayer, 28, 24);
			}
		}
		// SHORT LINE
		if (currentPlayer.getLocation() == 35) {
			if (board.ownsDeed(25, currentPlayer)) {
				propertyMenuSelection(currentPlayer, 25, -200);
			} else {
				railRoadRent(currentPlayer, 25);
			}
		}
		// CHANCE
		if (currentPlayer.getLocation() == 36) {
			handleSpecialCard(currentPlayer);
		}
		// PARK PLACE
		if (currentPlayer.getLocation() == 37) {
			if (board.ownsDeed(26, currentPlayer)) {
				propertyMenuSelection(currentPlayer, 26, -350);
			} else {
				payRent(currentPlayer, 35, 26);
			}
		}
		// LUXURY TAX
		if (currentPlayer.getLocation() == 38) {
			System.out.println("\nThe bank took $100 off of your balance");
			currentPlayer.setBalance(-100);
		}

		// BOARDWALK
		if (currentPlayer.getLocation() == 39) {
			if (board.ownsDeed(27, currentPlayer)) {
				propertyMenuSelection(currentPlayer, 27, -400);
			} else {
				payRent(currentPlayer, 50, 27);
			}
		}
	}

	private void handleSpecialCard(Player currentPlayer) {
		Card topcard = board.chance.get(0);
		switch (topcard.cardName) {
		case JAIL_FREE:
			if (currentPlayer.jailCardOwned[0] == null) {
				currentPlayer.jailCardOwned[0] = topcard;
				board.chance.remove(0);
			} else {
				currentPlayer.jailCardOwned[1] = topcard;
				board.chance.remove(0);
			}
			break;
		case MOVEMENT:
			break;
		case PAY_BUILDING_TAX:
			break;
		case PAY_MONEY:
			break;
		case PAY_OR_RECEIVE_PLAYERS:
			break;
		case RECEIVE_MONEY:
			break;
		default:
			break;

		}

	}

	private void handleIncomeTax(Player currentPlayer) {

		int totalToPay = 0;
		for (card.Property cards : currentPlayer.propertiesOwned) {
			totalToPay += cards.getCost() * 0.1;
		}
		totalToPay += currentPlayer.getBalance() * 0.1;
		// HOUSES

		System.out.println("10% of your income is: " + totalToPay);
		currentPlayer.setBalance(totalToPay);

	}

	private void utilityRent(Player currentPlayer, int deedLocation) throws IOException {
		int totalOwed = 0;
		int selection = menu.printPayRentMenu();
		if (selection == 0) {
			System.out.println("\nYou will now roll the dice to see how much you will have to pay rent.");
			int selection2 = menu.rollDiceMenu();
			if (selection2 == 0) {
				die.roll();
				whatYouRolled();
				for (Player playerOwner : players) {
					if (playerOwner.propertiesOwned.contains(board.deeds[deedLocation])) {
						if (playerOwner.propertiesOwned.contains(board.deeds[7])
								&& playerOwner.propertiesOwned.contains(board.deeds[20])) {
							totalOwed = 10 * die.getTotal();
							System.out.println("Since you rolled " + die.getTotal() + ", and " + playerOwner.getName()
									+ " owns 2 property\n" + "You are paying $" + totalOwed);
						} else if (playerOwner.propertiesOwned.contains(board.deeds[7])
								|| playerOwner.propertiesOwned.contains(board.deeds[20])) {
							totalOwed = 4 * die.getTotal();
							System.out.println("Since you rolled " + die.getTotal() + ", and " + playerOwner.getName()
									+ " owns 1 property\n" + "You are paying $" + totalOwed);
						}
						playerOwner.setBalance(totalOwed);
						currentPlayer.setBalance(-totalOwed);
					}
				}
			}

		}
	}

	private void railRoadRent(Player currentPlayer, int deedLocation) throws IOException {
		int totalOwed = 0;
		int selection = menu.printPayRentMenu();
		if (selection == 0) {
			for (Player playerOwner : players) {
				if (playerOwner.propertiesOwned.contains(board.deeds[deedLocation])) {
					int counter = 0;
					for (card.Property titledeed : playerOwner.propertiesOwned) {
						if (titledeed instanceof RailRoad) {
							counter++;
						}
					}
					switch (counter) {
					case 1:
						totalOwed = 25;
						break;
					case 2:
						totalOwed = 50;
						break;
					case 3:
						totalOwed = 100;
						break;
					case 4:
						totalOwed = 200;
						break;
					default:
						break;
					}
					playerOwner.setBalance(totalOwed);
					currentPlayer.setBalance(-totalOwed);
				}
			}
		}

	}

	private void propertyMenuSelection(Player currentPlayer, int location, int cost) throws IOException {
		int selection = menu.printBuyPropertiesMenu();
		switch (selection) {
		case 0:
			System.out.println("\nYou now own this deed!");
			currentPlayer.propertiesOwned.add(board.deeds[location]);
			currentPlayer.setBalance(cost);
			break;
		case 1:
			System.out.println("\n\nSince you decided not to buy it, the bank will auction this property.");
			// HANDLE AUCTIONING
			break;
		default:
			break;
		}
	}

	private void payRent(Player currentPlayer, int regularRent, int deedLocation) throws IOException {
		int selection = menu.printPayRentMenu();
		if (selection == 0) {
			// CHECK IF OWNER HAS ALL GROUP PROPERTIES
			// DOUBLE RENT
			// CHECK IF THERE IS HOUSES/HOTELS
			// ELSE
			currentPlayer.setBalance(-regularRent);

			for (Player player : players) {
				if (player.propertiesOwned.contains(board.deeds[deedLocation])) {
					player.setBalance(regularRent);
					System.out.println("\n" + player.getName() + " says thanks for the money!");
				}
			}
		}
	}

	/**
	 * Method that will let finish your turn (does not have a roll dice option)
	 * 
	 * @param currentPlayer who's turn is it
	 */
	private void turnAfterRoll(Player currentPlayer) throws IOException {
		boolean isYourTurnAfterRoll = true;
		while (isYourTurnAfterRoll) {
			board.printBoard(currentPlayer);
			int action = menu.printMenuAfterRoll();
			switch (action) {
			case 0:
				showBalance(currentPlayer);
				break;
			case 1:
				showProperties(currentPlayer);
				break;
			case 2:
				buyHouse();
				break;
			case 3:
				tradeCards();
				break;
			case 4:
				isYourTurnAfterRoll = false;
				break;
			default:
				throw new IllegalArgumentException("Invalid action " + action);
			}
		}
	}

	/**
	 * Method to print what the balance is.
	 * 
	 * @param currentPlayer who's turn is it.
	 */
	private void showBalance(Player currentPlayer) {
		System.out.println("\nYour balance is: " + currentPlayer.getBalance() + "\n");
	}

	/**
	 * Method to print/show the properties the current player owns.
	 * 
	 * @param currentPlayer who's turn is it
	 */
	private void showProperties(Player currentPlayer) {
		if (currentPlayer.getPropertiesOwned().isEmpty()) {
			System.out.println("\n\nSorry, you don't own any properties.\nKeep playing to see if get better luck!");
		} else {
			System.out.print("\nThe properties you own are:\n");

			for (int i = 0; i < currentPlayer.getPropertiesOwned().size(); i++) {
				System.out.print("[");
				if (i == currentPlayer.getPropertiesOwned().size() - 1) {
					System.out.print(currentPlayer.propertiesOwned.get(i).getPropertyName());
				} else {
					System.out.print(currentPlayer.propertiesOwned.get(i).getPropertyName() + "]");
				}
			}
			System.out.print("]");

			System.out.print("\nThe cost of buying a house is is:\n");

			for (int i = 0; i < currentPlayer.getPropertiesOwned().size(); i++) {
				System.out.print("[");
				if (i == currentPlayer.getPropertiesOwned().size() - 1) {
					if (currentPlayer.propertiesOwned.get(i).getBuildingCost() == 0) {
						System.out.print("Not allowed]");
					} else {
						System.out.print(currentPlayer.propertiesOwned.get(i).getBuildingCost() + "]");
					}
				} else {
					if (currentPlayer.propertiesOwned.get(i).getBuildingCost() == 0) {
						System.out.print("Not allowed]");
					} else {
						System.out.print(currentPlayer.propertiesOwned.get(i).getBuildingCost() + "]");
					}
				}
			}

			// SHOW RENT

		}
	}

	// UNDER CONSTRUCTION - PLEASE ADD SOME CODE HERE
	private void buyHouse() {

	}

	// UNDER CONSTRUCTION - PLEASE ADD SOME CODE HERE
	private void tradeCards() {

	}

	// tells whether or not property is mortgaged
	public boolean isMortgaged() {
		return mortgaged;
	}

	// unmortgage property
	public void setMortgage(boolean bool) {
		mortgaged = bool;
	}

	public String toString() {
		String status = "";

		if (isMortgaged())
			// will show if property is mortgaged
			status += " (Mortgaged)";

		return super.toString() + status;
	}

	/**
	 * Method that will move the player base on the total number they rolled
	 * 
	 * @param num the total number that the dice got
	 * @param p   the player who's turn is it
	 */
	private void movePlayer(int totalDie, Player currentPlayer) {
		while (totalDie > 0) {
			totalDie--;
			currentPlayer.addLocation(1);
			if (currentPlayer.getLocation() == 40) {
				currentPlayer.setLocation(0);
			}
			if (currentPlayer.getLocation() == 0) {
				currentPlayer.setBalance(200);
			}

		}
	}

	/**
	 * Method that will turn on the isInJail flag of a player and update location of
	 * the player if is sent to jail.
	 * 
	 * @param currentPlayer
	 * @param jailLocation
	 */
	private void sendToJail(Player currentPlayer, int jailLocation) {
		currentPlayer.setLocation(jailLocation);
		currentPlayer.isInJail(true);
	}

	// UNDER CONSTRUCTION - PLEASE ADD SOME CODE HERE
	private void speedDieRules() {
		System.out.println("Please read rules inside box.");
	}
}
>>>>>>> 32c323257d64e13ceecca02ffed594b31aad73d0
