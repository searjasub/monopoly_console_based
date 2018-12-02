package demo;

import enumeration.TitleColor;
import game.Game;

public class Demo{


    public Demo(){}

    public static void main(String[] args) throws Exception {
        System.out.println(TitleColor.BLUE + "Look at me I'm Blue!" + TitleColor.RESET);
        Game game = new Game();
        game.run();
    }


    public void Go(){
        // collect go
    }

    public void buyingProperty(){
        // buying property
        // removes money 
    }

    public void landOnRent(){
        // paying rent
        // checking for full set
    }

    public void chanceAndCommunityChest(){
        // follow what the card category says
    }

    public void taxes(){
        // pay 75$ tax
        // ask to choose 200 or calculate 10% of net worth
    }

    public void jail(){
        // 3 doubles
        // land on square
        // community chest

        // try three times
        // have to pay after 3 times
        // or buy out before then
        // get out of jail free card
    }

    public void housesBuyAndSell(){
        // do it evenly
        // need a full color group
        // sell evenly
    }

    public void auctioning(){
        // :(
    }

    public void sellingAndTradingProperties(){
        // with other players
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