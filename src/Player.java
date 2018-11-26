
public class Player {	

	Money personalBalance = new Money();
	int totalMoney;


	
	public Player(String name,Token token) {
		personalBalance.one = 5;
		personalBalance.five = 5;
		
		for(int i= 0; i< personalBalance.one;i++) {
			totalMoney += MoneyValue.one.value;
			Board.bank.one -= MoneyValue.one.value;
		}
		
		for(int i= 0; i< personalBalance.five;i++) {
			totalMoney += MoneyValue.five.value;
		}
		
		
//		fives = MoneyValue.five.value;
//		fives += MoneyValue.five.value;
	}
	
}
