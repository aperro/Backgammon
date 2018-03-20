
public class Stone {
	public enum Color {
		WHITE,
		BLACK,
		NONE
	}
	public static Stone WHITE = new Stone(Color.WHITE);
	public static Stone BLACK = new Stone(Color.BLACK);
	public static Stone NONE = new Stone(Color.NONE);
	
	private Color color;
	
	
	public Stone(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

}
