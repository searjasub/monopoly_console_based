
public enum MoneyValue {
	one(1),five(5),ten(10),twenty(20),fifty(50),hundred(100),fiveHundred(500);
	
	int value;
	
	private MoneyValue(int value) {
		this.value = value;
	}
	
}
