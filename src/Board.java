import java.util.ArrayList;
import java.util.List;

public class Board {
	// Le plateau possède 26 cases différentes avec leur place respective
	private List<Box> boxList = new ArrayList<Box>();
	private int selectedBox_ = -1;

	public Board(Player player1, Player player2)
	{
		// To-do Créer l'ensemble des box
		for(int i = 0; i < 28; i++) {
			boxList.add(new Box(i));
		}

		// Player 1 position de départ
		for(int j = 0; j < player1.getStoneList().size(); j++) {
			// Les deux premières stones vont sur la case 1
			if(j < 2) {
				player1.getStoneList().get(j).SetBox(boxList.get(1));
			}
			//..
			if(j >= 2 && j < 7) {
				player1.getStoneList().get(j).SetBox(boxList.get(12));
			}
			//..
			if(j >= 7 && j< 10) {
				player1.getStoneList().get(j).SetBox(boxList.get(17));
			}
			//..
			if(j >= 10 && j < 15) {
				player1.getStoneList().get(j).SetBox(boxList.get(19));
			}
			
		}

		// Player 2 position de départ
		for(int i = 0; i < player2.getStoneList().size(); i++) {
			// Les deux premières stones vont sur la case 1
			if(i < 5) {
				player2.getStoneList().get(i).SetBox(boxList.get(6));
			}
			//..
			if(i >= 5 && i < 8) {
				player2.getStoneList().get(i).SetBox(boxList.get(8));
			}
			//..
			if(i >= 8 && i <= 12) {
				player2.getStoneList().get(i).SetBox(boxList.get(13));
			}
			//..
			if(i >= 13 && i < 15) {
				player2.getStoneList().get(i).SetBox(boxList.get(24));
			}
		}
	}
	
	public void TakeStoneAtBox(int boxIndex)
	{
		Box box = boxList.get(boxIndex);
		if (box.getStonesInside().size() == 1 && !box.getOwner().isPlaying())
		{
			Stone stoneToTake;
			if (box.getOwner().getName().equalsIgnoreCase("Rouge"))
			{
				stoneToTake = box.getStonesInside().get(0);
				stoneToTake.SetBox(boxList.get(26)); // Red prison
			}
			else
			{
				stoneToTake = box.getStonesInside().get(0);
				stoneToTake.SetBox(boxList.get(27)); // White prison
			}
		}
	}
	
	public void DesactiveAllPossibleMove() {
		for(int i=0; i < boxList.size(); i++) {
			boxList.get(i).setIsAPossibleMove(false);
		}
	}
	
	// Returns the number of box with isAPossibleMove = true between fromInd (inclusive) and toInd (inclusive)
	public int NumberOfPossibleMoves(int fromInd, int toInd)
	{
		int num = 0;
		for (Box box : boxList.subList(fromInd, toInd+1))
		{
			if (box.getIsAPossibleMove())
				num++;
		}
		return num;
	}

	public Boolean CheckIsEmpty(int indexBox) {
		return boxList.get(indexBox).isEmpty();
	}

	public void SetSelectedBox(int indexBoxSelected) {
		selectedBox_ = indexBoxSelected;
	}

	public int GetSelectedBox() {
		return selectedBox_;
	}

	public List<Box> getBoxList() {
		return boxList;
	}

	public void setBoxList(List<Box> boxList) {
		this.boxList = boxList;
	}
}
