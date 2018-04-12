import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Box {
	private int indexBox; // 24 cases, 0 et 25 seront les buts

	private Point boxStartPosition = new Point();
	private Point boxEndPosition = new Point();
	private String owner;
	private Boolean isEmpty;
	private int currentNumberOfStones;
	
	private static final int stepBetweenBox = 40;
	
	private Boolean boxSelected;
	private Boolean isAPossibleMove;
	private Boolean isTop;

	private List<Stone> StonesInside = new ArrayList<Stone>();

	public Box(int indexBox) {
		this.indexBox = indexBox;
		this.isEmpty = true;
		this.currentNumberOfStones = 0;
		this.boxSelected = false;
		this.isAPossibleMove = false;
		
		if(this.getIndexBox() < 13) {
			this.setIsTop(true);
		}else {
			this.setIsTop(false);
		}
		
		
		GeneratePositionThisBox();
	}

	public void PileStone(String player) {
		this.StonesInside.add(new Stone(player, false, false));
		owner = player;
		// TO-DO Placer les pions dans les box
		this.currentNumberOfStones++;
		this.isEmpty = false;
		
		if(this.getIsTop()) {
			this.boxEndPosition.y = boxEndPosition.y + stepBetweenBox;
		}
		if(!this.getIsTop()) {
			this.boxEndPosition.y = boxEndPosition.y - stepBetweenBox;
		}
	}

	private void GeneratePositionThisBox() {
		// TODO Auto-generated method stub
		int tableCoin = 0;
		if(indexBox > 0 && indexBox <= 6) {
			tableCoin = 1;
		}
		if(indexBox >= 7 && indexBox <= 12) {
			tableCoin = 2;
		}
		if(indexBox >= 13 && indexBox <= 18) {
			tableCoin = 3;
		}
		if(indexBox >= 19 && indexBox <= 24) {
			tableCoin = 4;
		}

		switch(tableCoin) {
		case 1: tableCoin = 1; // En haut à droite
		boxStartPosition.x = 900 - ( indexBox -1) *66;
		boxStartPosition.y = 35;
		boxEndPosition.x = boxStartPosition.x + stepBetweenBox;
		boxEndPosition.y = boxStartPosition.y + stepBetweenBox;
		break;
		
		case 2: tableCoin = 2; // En haut à droite
		boxStartPosition.x = 40 + (12 - indexBox) *66;
		boxStartPosition.y = 35;
		boxEndPosition.x = boxStartPosition.x + stepBetweenBox;
		boxEndPosition.y = boxStartPosition.y + stepBetweenBox;
		break;

		case 3: tableCoin = 3; // En bas à gauche
		boxStartPosition.x = 40 + (indexBox -13) *66;
		boxStartPosition.y = 840;
		boxEndPosition.x = boxStartPosition.x + stepBetweenBox;
		boxEndPosition.y = boxStartPosition.y - stepBetweenBox;
		break;
		
		case 4: tableCoin = 4; // En bas à droite
		boxStartPosition.x = 900 - (24 - indexBox) *66;
		boxStartPosition.y = 840;
		boxEndPosition.x = boxStartPosition.x + stepBetweenBox;
		boxEndPosition.y = boxStartPosition.y - stepBetweenBox;
		break;
		default: System.out.print("Bug");
		break;
		}
	}

	public void DepileStone() {
		Stone lastStone = StonesInside.get(StonesInside.size()-1);
		// TO-DO

		if(this.currentNumberOfStones == 0) {
			this.isEmpty = true;
		}
	}

	public int getIndexBox() {
		return indexBox;
	}

	public void setIndexBox(int indexBox) {
		this.indexBox = indexBox;
	}

	public Point getBoxStartPosition() {
		return boxStartPosition;
	}

	public void setBoxStartPosition(Point boxStart) {
		this.boxStartPosition = boxStart;
	}

	public Point getBoxEndPosition() {
		return boxEndPosition;
	}

	public void setBoxEndPosition(Point boxEnd) {
		this.boxEndPosition = boxEnd;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public Boolean getIsEmpty() {
		return isEmpty;
	}

	public void setIsEmpty(Boolean isEmpty) {
		this.isEmpty = isEmpty;
	}

	public List<Stone> getStonesInside() {
		return StonesInside;
	}

	public void setStonesInside(List<Stone> stonesInside) {
		StonesInside = stonesInside;
	}

	public Box(List<Stone> StonesInside){
		this.StonesInside = StonesInside;
	}

	public Boolean getBoxSelected() {
		return boxSelected;
	}

	public void setBoxSelected(Boolean boxSelected) {
		this.boxSelected = boxSelected;
	}

	public Boolean getIsAPossibleMove() {
		return isAPossibleMove;
	}

	public void setIsAPossibleMove(Boolean isAPossibleMove) {
		this.isAPossibleMove = isAPossibleMove;
	}

	public Boolean getIsTop() {
		return isTop;
	}

	public void setIsTop(Boolean isTop) {
		this.isTop = isTop;
	}

}
