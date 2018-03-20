import java.util.ArrayList;
import java.util.List;

public class GameManager {

	private Dice diceOne = new Dice();
	private Dice diceTwo = new Dice();
	private boolean diceOneEqualDiceTwo;
	
	private List<Stone> stoneList = new ArrayList<Stone>();
	
	public void RollDices() {
		diceOne.Roll();
		diceTwo.Roll();
		if(diceOne == diceTwo){
			diceOneEqualDiceTwo = true;
		}else{
			diceOneEqualDiceTwo = false;
		}
	}

	public Dice getDiceOne() {
		return diceOne;
	}

	public void setDiceOne(Dice diceOne) {
		this.diceOne = diceOne;
	}

	public Dice getDiceTwo() {
		return diceTwo;
	}

	public void setDiceTwo(Dice diceTwo) {
		this.diceTwo = diceTwo;
	}

	public List<Stone> getStoneList() {
		return stoneList;
	}

	public void setStoneList(List<Stone> stoneList) {
		this.stoneList = stoneList;
	}
	
}
