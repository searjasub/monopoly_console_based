
public class Board {

	Square[] s = new Square[20];
	
	
	
	
	static Money bank = new Money();
	public Board() {
		for(int i = 0; i < s.length; i++) {
			s[i] = new RailRoad();
		}
		
		
		bank.one = 100;
		bank.fifty = 200;
		
	}
}
