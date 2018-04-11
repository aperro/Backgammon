import java.util.ArrayList;
import java.util.List;

public class GameManager {

	private Dice diceOne = new Dice();
	private Dice diceTwo = new Dice();
	private boolean diceOneEqualDiceTwo;
	
	// Chaque joueur possède 15 Stones
	private Player player1, player2;
	
	private Board board;
	
	public GameManager() {
		player1 = new Player("Rouge");
		player2 = new Player("Blanc");
		
		board = new Board(player1, player2);
	}
	
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
	
	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}


}
