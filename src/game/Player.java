package game;

import java.util.ArrayList;

import card.Card;
import card.Property;
import enumeration.Token;

public class Player {
	

	int balance;
	int location;
	final Token token;
	final String name;
	int turn;
	Card[] jailCardOwned = new Card[2]; //Array to hold the jail free cards
	ArrayList<Property> propertiesOwned = new ArrayList<Property>();  //Array to hold the property cards
	
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
	
	public void setBalance(int balance) {
		this.balance += balance;
	}
	
	public int getLocation() {
		return this.location;
	}
	
	public void addLocation(int location) {
		this.location += location;
	}
	
	public void setLocation(int location) {
		this.location = 0;
	}
	
	
	public int getTurn() {
		return this.turn;
	}
	
	public void setTurn(int turn) {
		this.turn = turn;
	}
	
	public ArrayList<Property> getPropertiesOwned() {
		return this.propertiesOwned;
	}
	
	public String toString() {
		return "turn = " + this.turn +
		"\nlocation =  " + this.location;
	}
	
	
}
