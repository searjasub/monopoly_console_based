package game;

public class Die {

	private int dieOne;
	private int dieTwo;

	/**
	 * Constructor that rolls the dice. Calls the roll() method to roll the dice
	 */
	public Die() {
		roll();
	}

	/**
	 * Rolls the dice by setting each of the dice to be a random number between 1
	 * and 6
	 */
	public void roll() {
		dieOne = (int) (Math.random() * 6) + 1;
		dieTwo = (int) (Math.random() * 6) + 1;
	}

	/**
	 * @return the number showing on the first die
	 */
	public int getDieOne() {
		return dieOne;
	}

	/*
	 * @return the number showing on the second die
	 */
	public int getDieTwo() {
		return dieTwo;
	}

	/**
	 * @return the total value of dieOne + dieTwo
	 */
	public int getTotal() {
		return dieOne + dieTwo;
	}

}
