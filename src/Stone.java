import java.awt.Point;

public class Stone {
	
	private Box box_; // if box = null : stone is eaten
	
	public Stone() {
	}
	
	public Box GetBox() {
		return box_;
	}

	public void SetBox(Box box)
	{
		this.box_ = box;
	}
	
}
