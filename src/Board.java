import java.util.ArrayList;
import java.util.List;

public class Board {
	// public int[] board = new int [26]; // 24 cases, 0 et 25 seront les buts

	// Le plateau possède 26 cases différentes avec leur place respective
	private List<Box> boxList = new ArrayList<Box>();

	public Board(Player player1, Player player2)
	{
		// To-do Créer l'ensemble des box
		for(int i = 0; i < 26; i++) {
				boxList.add(new Box(i));
		}

		// Player 1 position de départ
		for(int j = 0; j < player1.getStoneList().size(); j++) {
			// Les deux premières stones vont sur la case 1
			if(j < 2) {
				boxList.get(1).PileStone((new Stone(player1.getName(), false, false)), player1.getName());
			}
			//..
			if(j >= 2 && j < 7) {
				boxList.get(12).PileStone((new Stone(player1.getName(), false, false)), player1.getName());
			}
			//..
			if(j >= 7 && j< 10) {
				boxList.get(17).PileStone((new Stone(player1.getName(), false, false)), player1.getName());
			}
			//..
			if(j > 7 && j < 15) {
				boxList.get(19).PileStone((new Stone(player1.getName(), false, false)), player1.getName());
			}
		}

		// Player 2 position de départ
		for(int i = 0; i < player2.getStoneList().size(); i++) {
			// Les deux premières stones vont sur la case 1
			if(i < 5) {
				boxList.get(6).PileStone((new Stone(player2.getName(), false, false)), player2.getName());
			}
			//..
			if(i >= 5 && i < 8) {
				boxList.get(8).PileStone((new Stone(player2.getName(), false, false)), player2.getName());
			}
			//..
			if(i >= 8 && i < 13) {
				boxList.get(13).PileStone((new Stone(player2.getName(), false, false)), player2.getName());
			}
			//..
			if(i > 13 && i < 15) {
				boxList.get(24).PileStone((new Stone(player1.getName(), false, false)), player2.getName());
			}
			/*
			board[1] = 2;
			board[6] = -5;
			board[8] = -3;
			board[12] = 5;
			board[13] = -5;
			board[16] = 3;
			board[18] = 5;
			board[24] = -2;*/
		}
	}
	
	public List<Box> getBoxList() {
		return boxList;
	}

	public void setBoxList(List<Box> boxList) {
		this.boxList = boxList;
	}
}
