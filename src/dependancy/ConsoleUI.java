package dependancy;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleUI {
	public static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

	/**
	 * Generates a console-based menu using the Strings in options as the menu
	 * items. Reserves the number 0 for the "quit" option when withQuit is true.
	 *
	 * @param options - Strings representing the menu options
	 * @return the int of the selection made by the user
	 */
	public static int promptForMenuSelection(String[] options) throws IOException {

		boolean isValid = false;
		int selection = 0;

		while (!isValid) {
			System.out.println("Please choose one");

			for (int i = 0; i < options.length; i++) {
				System.out.println("[" + i + "]\t" + options[i]);
			}
			selection = promptForInt("[Please enter the number of the option]", 0, options.length - 1);
			isValid = true;

		}
		return selection;
	}

	/**
	 * Generates a prompt that expects the user to enter one of two responses that
	 * will equate to a boolean value. The trueString represents the case
	 * insensitive response that will equate to true. The falseString acts
	 * similarly, but for a false boolean value. Example: Assume this method is
	 * called with a trueString argument of "yes" and a falseString argument of
	 * "no". If the enters "YES", the method returns true. If the user enters "no",
	 * the method returns false. All other inputs are considered invalid, the user
	 * will be informed, and the prompt will repeat.
	 *
	 * @param prompt      - the prompt to be displayed to the user
	 * @param trueString  - the case insensitive value that will evaluate to true
	 * @param falseString - the case insensitive value that will evaluate to false
	 * @return the boolean value
	 */
	public static boolean promptForBool(String prompt, String trueString, String falseString) throws IOException {

		validatePrompt(prompt);

		boolean isValid = false;
		boolean whatTheyWant = false;
		String userInput = null;

		do {
			System.out.println(prompt);
			System.out.println("Enter [" + trueString + "] or [" + falseString + "]");
			try {
				userInput = in.readLine();
			} catch (IOException ex) {
				System.out.println(userInput + " is not a valid answer. Needs to be either [" + trueString + "] or ["
						+ falseString + "]");
			}
			if (userInput == null
					|| !userInput.equalsIgnoreCase(trueString) && !userInput.equalsIgnoreCase(falseString)) {
				System.out.println("Your input is invalid. Enter [" + trueString + "] or [" + falseString + "]");
			} else {
				isValid = true;
			}
		} while (!isValid);

		if (userInput.equalsIgnoreCase(trueString)) {
			whatTheyWant = true;
		}
		return whatTheyWant;
	}

	/**
	 * Generates a prompt that expects a numeric input representing a byte value.
	 * This method loops until valid input is given.
	 *
	 * @param prompt - the prompt to be displayed to the user
	 * @param min    - the inclusive minimum boundary
	 * @param max    - the inclusive maximum boundary
	 * @return the byte value
	 */
	public static byte promptForByte(String prompt, byte min, byte max) throws IOException {
		return (byte) promptForLong(prompt, min, max);
	}

	/**
	 * Generates a prompt that expects a numeric input representing a short value.
	 * This method loops until valid input is given.
	 *
	 * @param prompt - the prompt to be displayed to the user
	 * @param min    - the inclusive minimum boundary
	 * @param max    - the inclusive maximum boundary
	 * @return the short value
	 */
	public static short promptForShort(String prompt, short min, short max) throws IOException {
		return (short) promptForLong(prompt, min, max);
	}

	/**
	 * Generates a prompt that expects a numeric input representing an int value.
	 * This method loops until valid input is given.
	 *
	 * @param prompt - the prompt to be displayed to the user
	 * @param min    - the inclusive minimum boundary
	 * @param max    - the inclusive maximum boundary
	 * @return the int value
	 */
	public static int promptForInt(String prompt, int min, int max) throws IOException {
		return (int) promptForLong(prompt, min, max);
	}

	/**
	 * Generates a prompt that expects a numeric input representing a long value.
	 * This method loops until valid input is given.
	 *
	 * @param prompt - the prompt to be displayed to the user
	 * @param min    - the inclusive minimum boundary
	 * @param max    - the inclusive maximum boundary
	 * @return the long value
	 */
	public static long promptForLong(String prompt, long min, long max) throws IOException {
		boolean isValid = false;
		double response = 0;

		while (!isValid) {
			isValid = true;
			response = promptForDouble(prompt, min, max);

			String strResponse = "" + response;

			int dotIndex = strResponse.indexOf('.');
			if (dotIndex != -1) { // there is a dot in the string
				String secondHalf = strResponse.substring(dotIndex + 1);
				int secondHalfInt = Integer.parseInt(secondHalf);
				if (secondHalfInt != 0) { // there were digits after the '.' that were not just 0
					isValid = false;
					System.out.println("You must enter a whole number");
				}
			}
		}
		return (long) response;
	}

	/**
	 * Generates a prompt that expects a numeric input representing a float value.
	 * This method loops until valid input is given.
	 *
	 * @param prompt - the prompt to be displayed to the user
	 * @param min    - the inclusive minimum boundary
	 * @param max    - the inclusive maximum boundary
	 * @return the float value
	 */
	public static float promptForFloat(String prompt, float min, float max) throws IOException {
		return (float) promptForDouble(prompt, min, max);
	}

	/**
	 * Generates a prompt that expects a numeric input representing a double value.
	 * This method loops until valid input is given.
	 *
	 * @param prompt - the prompt to be displayed to the user
	 * @param min    - the inclusive minimum boundary
	 * @param max    - the inclusive maximum boundary
	 * @return the double value
	 */
	public static double promptForDouble(String prompt, double min, double max) throws IOException {
		validatePrompt(prompt);
		validateMinMax(min, max);

		boolean isValid = false;
		double response = 0;
		while (!isValid) {
			String rawInput = promptForInput(prompt, false);
			try {
				response = Double.parseDouble(rawInput);
				if (min > response || response > max) {
					throw new IndexOutOfBoundsException();
				}
				isValid = true;
			} catch (NumberFormatException ex) {
				System.out.println("That doesn't look like a valid number.");
			} catch (IndexOutOfBoundsException ex) {
				System.out.println("You entered " + response + ", which is not between " + min + " and " + max);
			}
		}
		return response;
	}

	/**
	 * Generates a prompt that allows the user to enter any response and returns the
	 * String. When allowEmpty is true, empty responses are valid. When false,
	 * responses must contain at least one character (including whitespace).
	 *
	 * @param prompt     - the prompt to be displayed to the user.
	 * @param allowEmpty - when true, makes empty responses valid
	 * @return the input from the user as a String
	 */
	public static String promptForInput(String prompt, boolean allowEmpty) throws IOException {

		validatePrompt(prompt);

		boolean isValid = false;
		String input = null;

		while (!isValid) {

			System.out.println(prompt);
			try {
				input = in.readLine();
				if (!allowEmpty && input.isEmpty()) {
					throw new IndexOutOfBoundsException();
				} else {
					isValid = true;
				}
			} catch (IndexOutOfBoundsException ex) {
				System.out.println("Answer cannot be empty");
				isValid = false;
			}
		}
		return input;
	}

	/**
	 * Generates a prompt that expects a single character input representing a char
	 * value. This method loops until valid input is given.
	 *
	 * @param prompt - the prompt to be displayed to the user
	 * @param min    - the inclusive minimum boundary
	 * @param max    - the inclusive maximum boundary
	 * @return the char value
	 */
	public static char promptForChar(String prompt, char min, char max) throws IOException {
		validatePrompt(prompt);

		if (min > max) {
			throw new IllegalArgumentException("Min cannot be greater than max");
		}

		boolean isValid = false;
		char input = ' ';
		String rawInput;

		while (!isValid) {
			System.out.println(prompt);
			try {
				rawInput = in.readLine();

				if (rawInput.length() > 1) {
					System.out.println("It can not be more than 1 character");
					continue;
				}
				input = rawInput.charAt(0);

				if (input > max) {
					System.out.println("Needs to be smaller than " + max);
					isValid = false;
					input = ' ';
				} else if (input < min) {
					System.out.println("Needs to be bigger than " + min);
					isValid = false;
					input = ' ';
				} else {
					isValid = true;
				}

			} catch (IndexOutOfBoundsException ex) {
				System.out.println("" + input + " is not a valid option from " + min + " and " + max);
				isValid = false;
			}
		}
		return input;
	}

	private static void validatePrompt(String prompt) {
		if (prompt == null) {
			throw new IllegalArgumentException("Prompt cannot be null");
		}
	}

	private static void validateMinMax(double min, double max) {
		if (max < min) {
			throw new IllegalArgumentException("Max cannot be less than min");
		}
	}
}
