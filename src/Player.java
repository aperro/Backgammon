import java.util.ArrayList;
import java.util.List;

public class Player {

	private String name;
	private boolean isPlaying = false;
	
	private List<Stone> stoneList = new ArrayList<Stone>();
	public Player(String name) {
		this.setName(name);
		for(int i = 0; i < 15; i++) {
			stoneList.add(new Stone());
		}
	}
	
	public boolean CanEnd()
	{
		for (Stone stone : stoneList)
		{
			if (name.equalsIgnoreCase("Rouge")) // player1
			{
				if (stone.GetBox() == null || stone.GetBox().getIndexBox() < 19)
				{
					return false;
				}
			} else	// Player 2
			{
				if (stone.GetBox() == null || stone.GetBox().getIndexBox() > 6)
				{
					return false;
				}
			}
		}
		return true;
	}

	public List<Stone> getStoneList() {
		return stoneList;
	}

	public void setStoneList(List<Stone> stoneList) {
		this.stoneList = stoneList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isPlaying() {
		return isPlaying;
	}

	public void setPlaying(boolean isPlaying) {
		this.isPlaying = isPlaying;
	}	
	
}
