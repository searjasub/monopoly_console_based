package game;

import java.io.IOException;
import java.util.ArrayList;

import enumeration.Token;
import dependancy.*;

public class Game {

	public int turn;
	public int countPlayers;
	public Player[] players;
	ArrayList<Token> tokenArray = new ArrayList<Token>();
	Die die = new Die();

	/**
	 * Initialize the game by assigning names, tokens and initial balance.
	 * 
	 * @param totalPlayers insert the number of player
	 */
	private void init(int totalPlayers) throws IOException {
		
		for(Token t: Token.values()) {
			tokenArray.add(t);
		}
		
		players = new Player[totalPlayers];
		for (int i = 0; i < players.length; i++) {
			String playerName = ConsoleUI.promptForInput("Enter player " + (i + 1) + "'s name", false);

			System.out.println("\nOk " + playerName + ", is time to choose your token.");
			Token selection = chooseYourToken();
			
			Player newPlayer = new Player(playerName, selection, 1500);
			players[i] = newPlayer;

			// Finish interaction with players[i]
			if (players.length == totalPlayers) {
				System.out.println("Thank you " + players[i].getName() + ".");
			} else {
				System.out.println("Thank you " + players[i].getName() + ". Now let me ask your friend.");
			}
		}
		
		//For testing purposes
		System.out.println(players[1].getName() + " " + players[1].getBalance() + " " + players[1].getToken());
		System.out.println(players[0].getName() + " " + players[0].getBalance() + " " + players[0].getToken());
	}

	/**
	 * Without a while loop to only run the game once. Later on we can comment them
	 * back to let the game keep running. Might need a little fix after one game is
	 * played
	 */
	public void run() throws IOException {
		// boolean keepRunning = true;
		// while (keepRunning) {
		
		//printWelcome();
		System.out.println("Welcome to Monopoly!");
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
		
		int howManyPlayers = ConsoleUI.promptForInt("First, let's get started by having a count of the players.\n"
				+ "Remember that the minimun is 2 and maximun is  6", 2, 6);
		init(howManyPlayers);
		

		
		while (!gameOver) {
			// handle turns
			for(int i=0; i < players.length; i++) {
				turn(players[i]);
			}
			System.out.println("\n\nThis round has ended! Let's keep going");
		}
	}

	/**
	 * 
	 * @param p the player taking the turn
	 * @throws IOException
	 */
	public void turn(Player p) throws IOException {
		boolean isYourTurn = true;
		System.out.println("\nAlright " + p.getToken() + ", you;re up.");
		while (isYourTurn) {

			int action = printTurnMenu();
			switch (action) {
			case 0:
				die.roll();
				System.out.println("\nYou have rolled " + die.getDieOne() + " and " + die.getDieTwo());
				//movePlayer(die.getTotal(), p);
				
				isYourTurn = false;
				break;
			case 1:
				break;
			default:
				throw new IllegalArgumentException("Invalid action " + action);
			}
		}
	}

	/**
	 * 
	 * @param num the total number that was rolled by dice
	 * @param p   the player who's turn is it
	 */
	private void movePlayer(int num, Player p) {

		
		
		checkForGo(p);
	}

	/**
	 * Checks if the player passed for GO and add $200
	 * 
	 * @param p the player who's turn is it
	 */
	private void checkForGo(Player p) {
		if (p.location == 0) {
			p.addMoney(200);
		}
	}

	private void speedDieRules() {
		System.out.println("inside speed die rules");
	}

	/**
	 * By using switch to let user choose which token they want.
	 * It removes the token from tokensAvailable
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
	 * Helper method to remove to token that user selected from the tokensAvailable ArrayList
	 * @param selection
	 */
	private void removeToken(Token selection) {
		tokenArray.remove(selection);
	}

	/**
	 * Prints the current list of tokens available
	 * @param tokensAvailable ArrayList with tokens available
	 * @return the token selected
	 */
	private Token printTokenSelection(ArrayList<Token> tokensAvailable) throws IOException {
		String[] options = new String[tokensAvailable.size()];
		for(int i=0; i<options.length; i++) {
			options[i] = tokensAvailable.get(i).toString();
		}		
		int selection = ConsoleUI.promptForMenuSelection(options);
		return tokenArray.get(selection);
	}

	/**
	 * Prints the options for the main menu
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
	 * @return
	 * @throws IOException
	 */
	private int printTurnMenu() throws IOException {
		String[] menuOptions = new String[2];
		menuOptions[0] = "Roll dice";
		menuOptions[1] = "See balance";
		return ConsoleUI.promptForMenuSelection(menuOptions);

	}
}