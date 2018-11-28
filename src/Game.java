import java.io.IOException;

import enumeration.Token;

public class Game {

	public int turn;
	public int countPlayers;
	public Player[] players;

	public void run() throws IOException {
		//boolean keepRunning = true;
		//while (keepRunning) {
			System.out.println("Welcome to Monopoly!");
			int action = printMainMenu();
			takeAction(action);
			
		//	keepRunning = takeAction(action);
		//}

	}

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

	private void classicMonopolyRules() throws IOException {

		System.out.println("Welcome to Monopoly\nClassic Rules");
		int howManyPlayers = ConsoleUI.promptForInt("First, let's get started by having a count of the players.\n"
				+ "Remember that the minimun is 2 and maximun is  6", 2, 6);
		init(howManyPlayers);
	}

	private void speedDieRules() {
		System.out.println("inside speed die rules");

	}

	private int printMainMenu() throws IOException {
		String[] menuOptions = new String[3];
		menuOptions[0] = "Speed Die Rules";
		menuOptions[1] = "Classic Monopoly Rules";
		menuOptions[2] = "Exit";
		return ConsoleUI.promptForMenuSelection(menuOptions);
	}

	private void init(int totalPlayers) throws IOException {
		players = new Player[totalPlayers];
		for (int i = 0; i < players.length; i++) {
			String playerName = ConsoleUI.promptForInput("Enter player " + (i + 1) + "'s name", false);
			Player player = new Player();
			player.name = playerName;
			players[i] = player;
			System.out.println("\nOk " + players[i].name + ", is time to choose your token.");
			enumeration.Token selection = chooseYourToken();
			if(players.length == totalPlayers) {
				System.out.println("Thank you " + players[i].name + ".");
			}
			else {
				System.out.println("Thank you " + players[i].name + ". Now let me ask your friend.");
			}
			
			player.token = selection;
		}
	}

	private Token chooseYourToken() throws IOException {
		Token selection = null;
		int desireToken = printTokenSelection();
		switch (desireToken) {
		case 0:
			selection = Token.BATTLESHIP;
			break;
		case 1:
			selection = Token.CAR;
			break;
		case 2:
			selection = Token.CAT;
			break;
		case 3:
			selection = Token.DOG;
			break;
		case 4:
			selection = Token.HAT;
			break;
		case 5:
			selection = Token.SHOE;
			break;
		case 6:
			selection = Token.THIMBLE;
			break;
		case 7:
			selection = Token.WHEELBARROW;
			break;
		default:
			throw new IllegalArgumentException("Invalid selection " + desireToken);
		}
		return selection;
	}

	private int printTokenSelection() throws IOException {
		String[] options = new String[8];
		options[0] = Token.BATTLESHIP.toString();
		options[1] = Token.CAR.toString();
		options[2] = Token.CAT.toString();
		options[3] = Token.DOG.toString();
		options[4] = Token.HAT.toString();
		options[5] = Token.SHOE.toString();
		options[6] = Token.THIMBLE.toString();
		options[7] = Token.WHEELBARROW.toString();
		return ConsoleUI.promptForMenuSelection(options);
	}

}
