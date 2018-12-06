package demo;

import dependancy.ConsoleUI;
import enumeration.Token;
import game.Game;
import game.Player;
import java.util.Random;
import card.Card;
import enumeration.*;
public class Demo{
    Game game = new Game();


    public Demo(){
        game.players = new Player[3];
        game.players[0] = new Player("Sear", Token.HAT, 1300, 0, 0);
        game.players[1] = new Player("Brooke", Token.DOG, 1300, 0, 0);
        game.players[2] = new Player("Spencer", Token.CAT, 1300, 0, 0);
    }
        
    public static void main(String[] args) throws Exception {
        Demo d = new Demo();
        boolean loop = true;
        while(loop){
            int menu = ConsoleUI.promptForMenuSelection(new String[] {"exit", "Land on things","taxy bois", "jail :("});
            switch(menu){
                case 0:
                    loop = false;
                    break;
                case 1:
                    d.landOnProperty();
                    break;
                case 2:
                    d.taxes();
                    break;
                case 4:
                    d.jail();
                    break;
                default:
                    break;
            }
        }
        d.game.board.printLargeBoard();
    }


    public void Go() throws Exception{
        game.players[1].setLocation(39);
        game.turn(game.players[1]);
        // collect go
    }

    public void landOnProperty() throws Exception{
    for(int i = 0; i < 6; i++){
        game.turn(game.players[i]);
    }
        // buy property
        // removes money 
        // pay rent
        // checking for full set
    }

    public void taxes() throws Exception{
        game.turn(game.players[0]);
        game.players[0].setLocation(38);
        game.turn(game.players[0]);
        game.players[0].setLocation(4);
        game.turn(game.players[0]);
        // pay 75$ tax
        // ask to choose 200 or calculate 10% of net worth
    }

    public void jail() throws Exception{
        for(int i = 0; i < 3; i++){
            game.players[i].setLocation(10);
            game.players[i].isInJail(true);
            game.players[i].jailCardOwned[0] = new Card("\t\tChance\n", "\tGET OUT OF JAIL FREE\nThis card may be kept until needed or traded.\n", CardType.CHANCE, CardCategory.JAIL_FREE,1);
        }
        game.turn(game.players[new Random().nextInt(3)]);
    }

    public void housesBuyAndSell(){
        // do it evenly
        // need a full color group
        // sell evenly :(
    }


    public void mortgages(){
        // do that thing
        // 10% when received if not immediate
        // 10% plus the mortagage
    }

    public void bankruptcy(){
    // make sure player cant play anymore
    // if to the bank must be auctioned
    // un mortgaged by bank
    }

    public void buyingBuildings(){
        // do so evenly
    }

    // Go
    // Buying Property
    // Landing on rent
    // Chance / Community Chest
    // Taxes
    // Jail
    // Houses (buy/sell)
    // AUCTIONING
    // (selling/Trading) properties
    // Mortgages
    // bankruptcy
}