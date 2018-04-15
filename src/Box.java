import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Box {
	private int indexBox; // 24 cases, 0 et 25 seront les buts, 26 et 27 sont les pions pris rouge(1) et blanc(2)

	private Point boxStartPosition = new Point();
	private Point boxEndPosition = new Point();
	private Player owner;

	private static final int boxWidth = 55;
	private static final int boxHeight = 360;
	
	private Boolean isAPossibleMove;
	private Boolean isTop;

	private List<Stone> StonesInside = new ArrayList<Stone>();

	public Box(int indexBox) {
		this.indexBox = indexBox;
		this.isAPossibleMove = false;
		this.owner = null;
		
		// Savoir si box du haut ou du bas
		if(this.getIndexBox() < 13) {
			this.setIsTop(true);
		}else {
			this.setIsTop(false);
		}
		
		GeneratePositionThisBox();
	}

	public void DepileStone() {
		if(this.StonesInside.size() > 0) {
			StonesInside.remove(StonesInside.size()-1);
		}
	}
	
	public void Clear()
	{
		this.StonesInside.clear();
	}
	
	public void PileStone(Player player, Stone stone) {
		this.StonesInside.add(stone);
		this.owner = player;
		// TO-DO Placer les pions dans les box
	}

	private void GeneratePositionThisBox() {
		// TODO Auto-generated method stub
		
		// Pour l'affichage et la hitbox seulement
		
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
		
		boxEndPosition.x = boxStartPosition.x + boxWidth;
		boxEndPosition.y = boxStartPosition.y + boxHeight;
		break;
		
		case 2: tableCoin = 2; // En haut à gauche
		boxStartPosition.x = 40 + (12 - indexBox) *66;
		boxStartPosition.y = 35;
		boxEndPosition.x = boxStartPosition.x + boxWidth;
		boxEndPosition.y = boxStartPosition.y + boxHeight;
		break;

		case 3: tableCoin = 3; // En bas à gauche
		boxStartPosition.x = 40 + (indexBox -13) *66;
		boxStartPosition.y = 890;
		boxEndPosition.x = boxStartPosition.x + boxWidth;
		boxEndPosition.y = boxStartPosition.y - boxHeight;
		break;
		
		case 4: tableCoin = 4; // En bas à droite
		boxStartPosition.x = 900 - (24 - indexBox) *66;
		boxStartPosition.y = 890;
		boxEndPosition.x = boxStartPosition.x + boxWidth;
		boxEndPosition.y = boxStartPosition.y - boxHeight;
		break;
		default:// End boxes
			boxStartPosition.x = 1000;
			boxEndPosition.x = boxStartPosition.x + boxWidth;
			if (indexBox == 0) // FAIRE LES 2 POSITION DE UNE FOIS MANGé
			{
				boxStartPosition.y = 35;
				boxEndPosition.y = boxStartPosition.y + boxHeight;
			} else
			{
				boxStartPosition.y = 890;
				boxEndPosition.y = boxStartPosition.y - boxHeight;
			}
			break;
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

	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	public Boolean isEmpty() {
		return (this.StonesInside.size() == 0);
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
