package game;

import enumeration.Token;

public class Player {
	

	int balance;
	int location;
	final Token token;
	final String name;
	int turn;
	/**
	 * Constructor for the player class
	 * @param name of the player
	 * @param token selected by the player
	 */
	public Player(String name, Token token, int balance, int turn){
		this.name = name;
		this.token = token;
		this.balance += balance;
		this.turn = turn;
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
	
	public void setTurn(int turn) {
		this.turn = turn;
	}
	
	public int getTurn() {
		return this.turn;
	}
	
	public String toString() {
		return "\n" + this.name + " the " + this.token + " rolled " + this.turn + ".";
		
	}
	
	
}
