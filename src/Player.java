import java.util.ArrayList;
import java.util.List;

public class Player {

	private String name;
	private List<Stone> stoneList = new ArrayList<Stone>();
	private int remainingStone;
	
	public Player(String name) {
		this.setName(name);
		this.remainingStone = 15;
		for(int i = 0; i < 15; i++) {
			stoneList.add(new Stone(this.getName(), false, false));
		}
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
	
}
