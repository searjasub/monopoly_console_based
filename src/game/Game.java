package game;

import java.io.IOException;
import java.util.ArrayList;

import enumeration.Token;
import square.Property;
import dependancy.*;

public class Game {

	// Class level variables
	public int turn, countPlayers, roundCount, countOfDoublesRolled = 0;
	public Player[] players;
	ArrayList<Token> tokenArray = new ArrayList<Token>();
	Die die = new Die();
	Board board = new Board();

	/**
	 * Initialize the game by assigning names, tokens and initial balance.
	 * 
	 * @param totalPlayers insert the number of player
	 */
	private void init(int totalPlayers) throws IOException {

		for (Token t : Token.values()) {
			tokenArray.add(t);
		}

		players = new Player[totalPlayers];
		for (int i = 0; i < players.length; i++) {
			String playerName = ConsoleUI.promptForInput("\nEnter player " + (i + 1) + "'s name", false);

			System.out.println("\nOk " + playerName + ", is time to choose your token.");
			Token selection = chooseYourToken();

			System.out.println("Time to roll dice to see who starts");
			int total = rollForOrder();

			Player newPlayer = new Player(playerName, selection, 1500, total);
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
		options[0] = "Let's roll those dice";
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
		int action = printMainMenu();
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
		int howManyPlayers = ConsoleUI
				.promptForInt("Now that is your turn, let's get started by having a count of the players.\n"
						+ "Remember that the minimum is 2 and maximum is  8", 2, 8);
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
			System.out.println("\n\n\nOk " + currentPlayer.getName() + ", let's get out of jail.");
			handleJail(currentPlayer);
		} else {
			boolean isYourTurn = true;
			System.out.println("\nAlright player," + currentPlayer.getToken() + " you're up.");

			while (isYourTurn) {
				board.printBoard(currentPlayer);
				int action = printTurnMenu();
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
		boolean ableToGetOut = false;
		while (!ableToGetOut) {
			int action = PrintJailMenu();
			switch (action) {
			case 0:
				System.out.println("You have 3 chances to get doubles and get out of jail this turn");
				boolean rolledDoubles = false;
				while (!rolledDoubles) {
					int selection = ConsoleUI.promptForInt("[0]\tRoll dice", 0, 0);
					if (selection == 0) {
						die.roll();
						whatYouRolled();
						if (die.getDieOne() == die.getDieTwo()) {
							breakOutOfJail(currentPlayer);
							movePlayer(die.getTotal(), currentPlayer);
							board.printBoard(currentPlayer);
							// handle what happens when you lay on a property
							rolledDoubles = true;
						}
					}
				}
				ableToGetOut = true;
				break;
			case 1:
				if (currentPlayer.jailCardOwned != null) {

					ableToGetOut = !breakOutOfJail(currentPlayer);
				}

				break;
			case 2:
				System.out.println("" + "*********************************************************************"
						+ "\n\nOk " + currentPlayer.getName() + ", you are free now.");
				currentPlayer.setBalance(-50);
				ableToGetOut = !breakOutOfJail(currentPlayer);
				turn(currentPlayer);
				break;
			default:
				throw new IllegalArgumentException("Invalid action " + action);
			}
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
		landOnProperty(currentPlayer);

		if (die.getDieOne() == die.getDieTwo()) {
			countOfDoublesRolled++;
			if (countOfDoublesRolled == 3) {
				System.out.println(""
						+ "\n*************************************************************************************\n"
						+ currentPlayer.name + " have rolled 3 doubles. You will not be visiting jail this time.\n"
						+ "You also loose your turn. Better luck next time!\n"
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
	private void landOnProperty(Player currentPlayer) throws IOException {

		if (currentPlayer.getLocation() == 1) {
			if (board.ownsDeed(1, currentPlayer)) {
				propertyMenuSelection(currentPlayer, 0, -60);
			} else {
				payRent(currentPlayer, 2, 0);
			}
		}
		if (currentPlayer.getLocation() == 3) {
			if (board.ownsDeed(3, currentPlayer)) {
				propertyMenuSelection(currentPlayer, 1, -60);
			} else {
				payRent(currentPlayer, 4, 1);
			}
		}
		if (currentPlayer.getLocation() == 6) {
			if (board.ownsDeed(6, currentPlayer)) {
				propertyMenuSelection(currentPlayer, 2, -100);
			} else {
				payRent(currentPlayer, 6, 2);
			}
		}
		if (currentPlayer.getLocation() == 8) {
			if (board.ownsDeed(8, currentPlayer)) {
				propertyMenuSelection(currentPlayer, 3, -100);
			} else {
				payRent(currentPlayer, 6, 3);
			}
		}
		if (currentPlayer.getLocation() == 9) {
			if (board.ownsDeed(9, currentPlayer)) {
				propertyMenuSelection(currentPlayer, 4, -120);
			} else {
				payRent(currentPlayer, 8, 4);
			}
		}
		if (currentPlayer.getLocation() == 11) {
			if (board.ownsDeed(11, currentPlayer)) {
				propertyMenuSelection(currentPlayer, 5, -140);
			} else {
				payRent(currentPlayer, 10, 5);
			}
		}
		if (currentPlayer.getLocation() == 13) {
			if (board.ownsDeed(13, currentPlayer)) {
				propertyMenuSelection(currentPlayer, 6, -140);
			} else {
				payRent(currentPlayer, 10, 6);
			}
		}
		if (currentPlayer.getLocation() == 14) {
			if (board.ownsDeed(14, currentPlayer)) {
				propertyMenuSelection(currentPlayer, 7, -160);
			} else {
				payRent(currentPlayer, 12, 7);
			}
		}
		if (currentPlayer.getLocation() == 16) {
			if (board.ownsDeed(16, currentPlayer)) {
				propertyMenuSelection(currentPlayer, 8, -180);
			} else {
				payRent(currentPlayer, 14, 8);
			}
		}
		if (currentPlayer.getLocation() == 18) {
			if (board.ownsDeed(18, currentPlayer)) {
				propertyMenuSelection(currentPlayer, 9, -180);
			} else {
				payRent(currentPlayer, 14, 9);
			}
		}
		if (currentPlayer.getLocation() == 19) {
			if (board.ownsDeed(19, currentPlayer)) {
				propertyMenuSelection(currentPlayer, 10, -200);
			} else {
				payRent(currentPlayer, 16, 10);
			}
		}
		if (currentPlayer.getLocation() == 21) {
			if (board.ownsDeed(21, currentPlayer)) {
				propertyMenuSelection(currentPlayer, 11, -220);
			} else {
				payRent(currentPlayer, 18, 11);
			}
		}
		if (currentPlayer.getLocation() == 23) {
			if (board.ownsDeed(23, currentPlayer)) {
				propertyMenuSelection(currentPlayer, 12, -220);
			} else {
				payRent(currentPlayer, 18, 12);
			}
			;
		}
		if (currentPlayer.getLocation() == 24) {
			if (board.ownsDeed(24, currentPlayer)) {
				propertyMenuSelection(currentPlayer, 13, -240);
			} else {
				payRent(currentPlayer, 20, 13);
			}
		}
		if (currentPlayer.getLocation() == 26) {
			if (board.ownsDeed(26, currentPlayer)) {
				propertyMenuSelection(currentPlayer, 14, -260);
			} else {
				payRent(currentPlayer, 22, 14);
			}
		}
		if (currentPlayer.getLocation() == 27) {
			if (board.ownsDeed(27, currentPlayer)) {
				propertyMenuSelection(currentPlayer, 15, -260);
			} else {
				payRent(currentPlayer, 22, 15);
			}
		}
		if (currentPlayer.getLocation() == 29) {
			if (board.ownsDeed(29, currentPlayer)) {
				propertyMenuSelection(currentPlayer, 16, -280);
			} else {
				payRent(currentPlayer, 22, 16);
			}
		}
		if (currentPlayer.getLocation() == 31) {
			if (board.ownsDeed(31, currentPlayer)) {
				propertyMenuSelection(currentPlayer, 17, -300);
			} else {
				payRent(currentPlayer, 26, 17);
			}
		}
		if (currentPlayer.getLocation() == 32) {
			if (board.ownsDeed(32, currentPlayer)) {
				propertyMenuSelection(currentPlayer, 18, -300);
			} else {
				payRent(currentPlayer, 26, 18);
			}
		}
		if (currentPlayer.getLocation() == 34) {
			if (board.ownsDeed(34, currentPlayer)) {
				propertyMenuSelection(currentPlayer, 19, -320);
			} else {
				payRent(currentPlayer, 28, 19);
			}
		}
		if (currentPlayer.getLocation() == 37) {
			if (board.ownsDeed(1, currentPlayer)) {
				propertyMenuSelection(currentPlayer, 20, -350);
			} else {
				payRent(currentPlayer, 35, 20);
			}
		}
		if (currentPlayer.getLocation() == 39) {
			if (board.ownsDeed(1, currentPlayer)) {
				propertyMenuSelection(currentPlayer, 21, -400);
			} else {
				payRent(currentPlayer, 50, 21);
			}
		}
	}

	
	private void propertyMenuSelection(Player currentPlayer, int location, int balance) throws IOException {
		int selection = printBuyPropertiesMenu();
		switch (selection) {
		case 0:
			System.out.println("\nYou now own this deed!");
			currentPlayer.propertiesOwned.add(board.deeds[location]);
			currentPlayer.setBalance(balance);
			break;
		case 1:
			System.out.println("\n\nSince you decide it not to buy it, the bank will auction this property");
			// HANDLE AUCTIONING
			break;
		default:
			break;
		}
	}

	private void payRent(Player currentPlayer, int regularRent, int deedLocation) throws IOException {
		int selection = printPayRentMenu();
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
			int action = printMenuAfterRoll();
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
		if (currentPlayer.getPropertiesOwned().size() == 0) {
			System.out.println("\n\nSorry, you don't own any properties.\nKeep playing to see if get better luck!");
		} else {
			System.out.print("\nThe properties you own are:\n[");

			for (int i = 0; i < currentPlayer.getPropertiesOwned().size(); i++) {
				if (i == currentPlayer.getPropertiesOwned().size() - 1) {
					System.out.print(currentPlayer.propertiesOwned.get(i).getPropertyName());
				} else {
					System.out.print(currentPlayer.propertiesOwned.get(i).getPropertyName() + ",");
				}
			}
			System.out.print("]");

			System.out.print("\nThe cost of the building is:\n[");

			for (int i = 0; i < currentPlayer.getPropertiesOwned().size(); i++) {
				if (i == currentPlayer.getPropertiesOwned().size() - 1) {
					System.out.print(currentPlayer.propertiesOwned.get(i).getBuildingCost());
				} else {
					System.out.print(currentPlayer.propertiesOwned.get(i).getBuildingCost() + ",");
				}
			}
			System.out.print("]");
		}
	}

	// UNDER CONSTRUCTION - PLEASE ADD SOME CODE HERE
	private void buyHouse() {

	}

	// UNDER CONSTRUCTION - PLEASE ADD SOME CODE HERE
	private void tradeCards() {

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

	/**
	 * By using switch to let user choose which token they want. It removes the
	 * token from tokensAvailable
	 * 
	 * @return what token user wants to get;
	 */
	private Token chooseYourToken() throws IOException {
		Token selection = null;
		Token desireToken = printTokenSelection(tokenArray);
		switch (desireToken) {
		case SHOE:
			selection = Token.SHOE;
			removeToken(selection);
			break;
		case HAT:
			selection = Token.HAT;
			removeToken(selection);
			break;
		case CAR:
			selection = Token.CAR;
			removeToken(selection);
			break;
		case CAT:
			selection = Token.CAT;
			removeToken(selection);
			break;
		case DOG:
			selection = Token.DOG;
			removeToken(selection);
			break;
		case THIMBLE:
			selection = Token.THIMBLE;
			removeToken(selection);
			break;
		case BATTLESHIP:
			selection = Token.BATTLESHIP;
			removeToken(selection);
			break;
		case WHEELBARROW:
			selection = Token.WHEELBARROW;
			removeToken(selection);
			break;
		default:
			throw new IllegalArgumentException("Invalid selection " + desireToken);
		}
		return selection;
	}

	/**
	 * Helper method to remove to token that user selected from the tokensAvailable
	 * ArrayList
	 * 
	 * @param selection
	 */
	private void removeToken(Token selection) {
		tokenArray.remove(selection);
	}

	/**
	 * Prints the current list of tokens available
	 * 
	 * @param tokensAvailable ArrayList with tokens available
	 * @return the token selected
	 */
	private Token printTokenSelection(ArrayList<Token> tokensAvailable) throws IOException {
		String[] options = new String[tokensAvailable.size()];
		for (int i = 0; i < options.length; i++) {
			options[i] = tokensAvailable.get(i).toString();
		}
		int selection = ConsoleUI.promptForMenuSelection(options);
		return tokenArray.get(selection);
	}

	/**
	 * Prints the options for the main menu
	 * 
	 * @return
	 */
	private int printMainMenu() throws IOException {
		String[] menuOptions = new String[3];
		menuOptions[0] = "Speed Die Rules";
		menuOptions[1] = "Classic Monopoly Rules";
		menuOptions[2] = "Exit";
		return ConsoleUI.promptForMenuSelection(menuOptions);
	}

	/**
	 * Prints the options for the turn menu
	 * 
	 * @return
	 * @throws IOException
	 */
	private int printTurnMenu() throws IOException {
		String[] menuOptions = new String[5];
		menuOptions[0] = "Roll dice";
		menuOptions[1] = "See balance";
		menuOptions[2] = "See your properties";
		menuOptions[3] = "Buy house/hotel";
		menuOptions[4] = "Trade cards";
		return ConsoleUI.promptForMenuSelection(menuOptions);
	}

	/**
	 * Print the options after they roll for first time
	 * 
	 * @return the selection
	 */
	private int printMenuAfterRoll() throws IOException {
		String[] menuOptions = new String[5];
		menuOptions[0] = "See balance";
		menuOptions[1] = "See your properties";
		menuOptions[2] = "Buy house/hotel";
		menuOptions[3] = "Trade cards";
		menuOptions[4] = "End turn";
		return ConsoleUI.promptForMenuSelection(menuOptions);
	}

	/**
	 * Prints the options for the menu when you need to get out of jail.
	 * 
	 * @return the user's selection
	 */
	private int PrintJailMenu() throws IOException {
		String[] menuOptions = new String[3];
		menuOptions[0] = "Try to roll doubles";
		menuOptions[1] = "Use your card";
		menuOptions[2] = "Pay $50";
		return ConsoleUI.promptForMenuSelection(menuOptions);

	}

	private int printBuyPropertiesMenu() throws IOException {
		String[] menuOptions = new String[2];
		menuOptions[0] = "Buy property";
		menuOptions[1] = "Not buy it";
		return ConsoleUI.promptForMenuSelection(menuOptions);
	}

	private int printPayRentMenu() throws IOException {
		String[] menuOptions = new String[1];
		menuOptions[0] = "Pay Rent";
		return ConsoleUI.promptForMenuSelection(menuOptions);
	}

}
