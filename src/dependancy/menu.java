package dependancy;

import java.io.IOException;
import java.util.ArrayList;

import enumeration.Token;

public class menu {
	public static ArrayList<Token> tokenArray = new ArrayList<Token>();
	
	/**
	 * By using switch to let user choose which token they want. It removes the
	 * token from tokensAvailable
	 * 
	 * @return what token user wants to get;
	 */
	public static Token chooseYourToken() throws IOException {
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
	public static void removeToken(Token selection) {
		tokenArray.remove(selection);
	}
	
	/**
	 * Prints the current list of tokens available
	 * 
	 * @param tokensAvailable ArrayList with tokens available
	 * @return the token selected
	 */
	public static Token printTokenSelection(ArrayList<Token> tokensAvailable) throws IOException {
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
	public static int printMainMenu() throws IOException {
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
	public static int printTurnMenu() throws IOException {
		String[] menuOptions = new String[5];
		menuOptions[0] = "Roll Dice";
		menuOptions[1] = "See Balance";
		menuOptions[2] = "See Your Properties";
		menuOptions[3] = "Sell Properties Or \"Get Out Of Jail\" Card";
		menuOptions[4] = "Buy Properties Or \"Get Out Of Jail\" Card";
		return ConsoleUI.promptForMenuSelection(menuOptions);
	}
	
//	public static int printSellBuyMenu() throws IOException{
//		String[]strings menuOptions = new String[2];
//	}

	/**
	 * Print the options after they roll for first time
	 * 
	 * @return the selection
	 */
	public static int printMenuAfterRoll() throws IOException {
		String[] menuOptions = new String[5];
		menuOptions[0] = "See Balance";
		menuOptions[1] = "See Your Properties";
		menuOptions[2] = "Sell Properties Or \"Get Out Of Jail\" Card";
		menuOptions[3] = "Buy Properties Or \"Get Out Of Jail\" Card";
		menuOptions[4] = "End Turn";
		return ConsoleUI.promptForMenuSelection(menuOptions);
	}

	/**
	 * Prints the options for the menu when you need to get out of jail.
	 * 
	 * @return the user's selection
	 */
	public static int printJailMenu() throws IOException {
		String[] menuOptions = new String[3];
		menuOptions[0] = "Try To Roll Doubles";
		menuOptions[1] = "Use Your 'Get Out Of Jail Free' Card";
		menuOptions[2] = "Pay $50";
		return ConsoleUI.promptForMenuSelection(menuOptions);

	}

	public static int printBuyPropertiesMenu() throws IOException {
		String[] menuOptions = new String[2];
		menuOptions[0] = "Buy Property";
		menuOptions[1] = "Auction Off Property";
		return ConsoleUI.promptForMenuSelection(menuOptions);
	}

	public static int printPayRentMenu() throws IOException {
		String[] menuOptions = new String[1];
		menuOptions[0] = "Pay Rent";
		return ConsoleUI.promptForMenuSelection(menuOptions);
	}
	public static int printBuyBuildingMenu() throws IOException {
		String[] menuOptions = new String[2];
		menuOptions[0] = "Buy House";
		menuOptions[1] = "Buy Hotel";
		return ConsoleUI.promptForMenuSelection(menuOptions);
	}
	
	public static int rollDiceMenu()throws IOException {
		String[] menuOptions = new String[1];
		menuOptions[0] = "Roll Dice";
		return ConsoleUI.promptForMenuSelection(menuOptions);
	}
	public static int payLuxuryTaxMenu() throws IOException {
		String[] menuOptions = new String[2];
		menuOptions[0] = "Pay $200.";
		menuOptions[1] = "Pay 10% Of Your Assets";
		return ConsoleUI.promptForMenuSelection(menuOptions);
	}
	
	public static int printAcceptMenu() throws IOException{
		String[] menuOption = new String[2];
		menuOption[0] = "Accept";
		menuOption[1] = "Decline";
		return ConsoleUI.promptForMenuSelection(menuOption);
	}
	
	public static int printSellBuyMainMenu() throws IOException{
		String[] menuOption = new String[3];
		menuOption[0] = "Property";
		menuOption[1] = "\"Get Out Of Jail\"";
		menuOption[2] = "Abandon transaction";
		return ConsoleUI.promptForMenuSelection(menuOption);
	}



}