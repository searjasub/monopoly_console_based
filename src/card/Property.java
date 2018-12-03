package card;

public abstract class Property {
	String name;
	
	public Property(String name) {
		this.name = name;
	}
	
	public String getPropertyName() {
		return this.name;
	}

}
