package game;

import java.util.Random;

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
		Random random = new Random();
//		dieOne = random.nextInt(6) + 1;
//		dieTwo = random.nextInt(6) + 1;
		dieOne = 1;
		dieTwo = 6;
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

	/**
	 * @return
	 */
	public boolean diceEqualsDoble() {
		return dieOne == dieTwo;

	}

	public int returnOne(){
		dieOne = -1;
		dieTwo = -2;
		return 1;
	}
	public int returnTwo(){
		dieOne = -1;
		dieTwo = -2;
		return 2;
	}

	public int returnThree(){
		dieOne = -1;
		dieTwo = -2;
		return 3;
	}

	public int returnFour(){
		dieOne = -1;
		dieTwo = -2;
		return 4;
	}
	public int returnFive(){
		dieOne = -1;
		dieTwo = -2;
		return 5;
	}
	public int returnSix(){
		dieOne = -1;
		dieTwo = -2;
		return 6;
	}
	public int returnSeven(){
		dieOne = -1;
		dieTwo = -2;
		return 7;
	}
	public int returnEight(){
		dieOne = -1;
		dieTwo = -2;
		return 8;
	}
	public int returnNine(){
		dieOne = -1;
		dieTwo = -2;
		return 9;
	}
	public int returnTen(){
		dieOne = -1;
		dieTwo = -2;
		return 10;
	}
	
	public int returnEleven(){
		dieOne = -1;
		dieTwo = -2;
		return 11;
	}
	public int returnTwelve(){
		dieOne = -1;
		dieTwo = -2;
		return 12;
	}
}