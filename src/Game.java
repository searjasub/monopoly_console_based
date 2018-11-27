import java.io.IOException;

public class Game {

	public int turn;
	public int countPlayers;
	public Player[] players;

	public void init() {

		System.out.println("Welcome to Monopoly!");

	}

	public void run() throws IOException {
		boolean keepRunning = true;
		while (keepRunning) {
			int action = printMainMenu();
			keepRunning = takeAction(action);
		}

	}

	private boolean takeAction(int action) {
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

	private void classicMonopolyRules() {

		System.out.println("inside classic monopoly rules");
		
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

}
