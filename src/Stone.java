import java.awt.Point;

public class Stone {
	
	private static final int stoneWidth = 50;
	private static final int stoneHeight = 50;
	
	private String name;
	
	private Boolean isPlayable_;
	private Boolean isSelected_;
	
	public Stone(String name, Boolean isPlayable, Boolean isSelected) {
		this.name = name;
		this.isPlayable_ = isPlayable;
		this.isSelected_ = isSelected;
	}
	
}
