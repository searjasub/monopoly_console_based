package enumeration;

/**
 * <pre>to use the color, in the String call <code>TitleColor.COLOR</code><br>
 * make sure to call <code>TitleColor.RESET</code> to clear the color that was set <br>
 * <b>In Example: </b> <code> System.out.println(TitleColor.RED + "This Color is Red!" + TitleColor.RESET);</code></pre>
 */
public enum TitleColor {
	BROWN("\u001B[38;5;94m"),
	LIGHTBLUE("\u001B[38;5;14m"),
	PINK("\u001B[38;5;13m"),
	ORANGE("\u001B[38;5;202m"),
	RED("\u001B[38;5;196m"),
	YELLOW("\u001B[38;5;11m"),
	GREEN("\u001B[38;5;10m"),
	BLUE("\u001B[38;5;21m"),
	RESET("\u001B[0m");

	public String colorTag;
	private TitleColor(String color){
		this.colorTag = color;
	}
	@Override
	public String toString() {
		return colorTag;
	}
}