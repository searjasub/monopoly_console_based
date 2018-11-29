package game;

import enumeration.Token;

public class Player {
	

	int balance;
	int location;
	final Token token;
	final String name;
	
	/**
	 * Constructor for the player class
	 * @param name of the player
	 * @param token selected by the player
	 */
	public Player(String name, Token token){
		this.name = name;
		this.token = token;
	}
	
	/**
	 * @param amount money added to the balance
	 */
	public void addMoney(int amount) {
		this.balance += amount;
	}
	
	public Token getToken() {
		return this.token;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getBalance() {
		return this.balance;
	}
	
	public int getLocation() {
		return this.location;
	}
	
	
}