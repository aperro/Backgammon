import java.util.ArrayList;
import java.util.List;

public class Board {
	// Le plateau possède 26 cases différentes avec leur place respective
	private List<Box> boxList = new ArrayList<Box>();
	
	public void ChangeStoneFromABoxToAnother(int indexNewBox) {
		
	}
	
	// On peut ajouter un paramètre string owner
	public void PossibleMove(int indexBoxSelected, int dice1_Score, int dice2_Score, Boolean diceOneEqualDiceTwo) {
		// Si les dés ne sont pas égaux
		if(!diceOneEqualDiceTwo) {
			// On regarde si la stone a bouger est celle du joueur 1
			if(boxList.get(indexBoxSelected).getOwner().equalsIgnoreCase("Rouge")) {
				// On va dans le sens 1-25
				DesactiveAllPossibleMove();
				// Premier dé
				if((indexBoxSelected + dice1_Score) <= 24) {
					boxList.get((indexBoxSelected + dice1_Score)).setIsAPossibleMove(true);
				}
				// Deuxième dé
				if((indexBoxSelected + dice2_Score) <= 24) {
					boxList.get((indexBoxSelected + dice2_Score)).setIsAPossibleMove(true);
				}
				// Les deux dés
				if((indexBoxSelected + dice1_Score + dice2_Score) <= 24) {
					boxList.get((indexBoxSelected + dice1_Score + dice2_Score)).setIsAPossibleMove(true);
				}
			}
			
			if(boxList.get(indexBoxSelected).getOwner().equalsIgnoreCase("Blanc")) {
				// On va dans le sens 25-1
				
			}
		}
	}
	
	private void DesactiveAllPossibleMove() {
		// TODO Auto-generated method stub
		for(int i=0; i < boxList.size(); i++) {
			Box currentBox = boxList.get(i);
			currentBox.setIsAPossibleMove(false);
		}
	}
	
	private void DesactiveAllSelected() {
		// TODO Auto-generated method stub
		for(int i=0; i < boxList.size(); i++) {
			Box currentBox = boxList.get(i);
			currentBox.setBoxSelected(false);
		}
	}

	public Boolean CheckIsEmpty(int indexBox) {
		return boxList.get(indexBox).getIsEmpty();
	}
	
	public void BoxSelected(int indexBoxSelected) {
		for(int i=0; i < boxList.size(); i++) {
			Box currentBox = boxList.get(i);
			currentBox.setBoxSelected(false);
			if(currentBox.getIndexBox() == indexBoxSelected) {
				currentBox.setBoxSelected(true);
			}
		}
	}

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
			if(j >= 10 && j < 15) {
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
			if(i >= 8 && i <= 12) {
				boxList.get(13).PileStone((new Stone(player2.getName(), false, false)), player2.getName());
			}
			//..
			if(i >= 13 && i < 15) {
				boxList.get(24).PileStone((new Stone(player1.getName(), false, false)), player2.getName());
			}
		}
	}
	
	public List<Box> getBoxList() {
		return boxList;
	}

	public void setBoxList(List<Box> boxList) {
		this.boxList = boxList;
	}
}
