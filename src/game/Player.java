package game;

import enumeration.Token;

public class Player {
	

	int balance;
	final Token token;
	final String name;
	
	public Player(String name, Token token){
		this.name = name;
		this.token = token;
	}	

}