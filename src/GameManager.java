
public class GameManager {

	private Dice diceOne;
	private Dice diceTwo;
	
	// Chaque joueur possède 15 Stones
	private Player player1, player2;
	private Board board;
	private boolean diceLaunched = false;
	
	public GameManager() {
		player1 = new Player("Rouge");
		player2 = new Player("Blanc");
		diceOne = new Dice();
		diceTwo = new Dice();
		
		board = new Board(player1, player2);
		FillBox();
		
		player1.setPlaying(true);
	}
	
	public void RollDices() {
		diceOne.Roll();
		diceTwo.Roll();
		if (diceOne.Value() == diceTwo.Value())
		{
			diceOne.setRemainingUse(2);
			diceTwo.setRemainingUse(2);
		}
		else
		{
			diceOne.setRemainingUse(1);
			diceTwo.setRemainingUse(1);
		}
		diceLaunched = true;
		System.out.println("RollDices : " + diceOne.Value() + "; " + diceTwo.Value());
	}

	public void FillBox()
	{
		for(Box box : board.getBoxList())
		{
			box.Clear();
			box.setOwner(null);
		}
		
		for(Stone stoneJ1 : player1.getStoneList()){ // player 1 stones 
			int stoneBoxIndex = stoneJ1.GetBox().getIndexBox();
			board.getBoxList().get(stoneBoxIndex).PileStone(player1, stoneJ1);
		}
		for(Stone stoneJ2 : player2.getStoneList()){ // player 2 stones 
			int stoneBoxIndex = stoneJ2.GetBox().getIndexBox();
			board.getBoxList().get(stoneBoxIndex).PileStone(player2, stoneJ2);
		}
					
	}
	

	// Cette méthode change de place une stone
	public void MoveStoneFromTo(int indexOldBox, int indexNewBox) {
		// Depile one stone from the odlIndexBox
		Box currentBox = board.getBoxList().get(indexOldBox);
		Box nextBox = board.getBoxList().get(indexNewBox);
		if (!currentBox.getStonesInside().isEmpty())
		{
			currentBox.getStonesInside().get(0).SetBox(nextBox);
		}

		// décroît le nombre d'utilisations restantes du dé utilisé
		
		// si on a joué le dé n°1
		if (diceOne.Value() == Math.abs(indexOldBox - indexNewBox) && diceOne.getRemainingUse() > 0)
		{
			diceOne.DecreaseRemainingUse();
		} else
		{
			if (diceTwo.Value() == Math.abs(indexOldBox - indexNewBox) && diceTwo.getRemainingUse() > 0)
			{
				diceTwo.DecreaseRemainingUse();
			} 
			else // si on peut sortir et que les dés sont tout les deux trop grands
			{
				if (GetPlaying().CanEnd())
				{
					if (diceOne.Value() > diceTwo.Value())
					{
						if (diceOne.getRemainingUse() > 0)
						{
							diceOne.DecreaseRemainingUse();
						} else
						{
							diceTwo.DecreaseRemainingUse();
						}
					} else
					{
						if (diceTwo.getRemainingUse() > 0)
						{
							diceTwo.DecreaseRemainingUse();
						}
					}
				}
			}
		}
		
		// TODO : gérer si il y a un pion adverse sur cette case
		
		FillBox();
		if (diceOne.getRemainingUse() == 0 && diceTwo.getRemainingUse() == 0)
		{
			EndOfTurn();
		}
		
	}
	
	public boolean SelectBox(int boxId)
	{
		boolean canMove = false;
		Box selectedBox = board.getBoxList().get(boxId);
		if (selectedBox.getOwner() != null && selectedBox.getOwner().isPlaying() && boxId < 25 && boxId > 0)
		{
			PossibleMove(boxId, selectedBox.getOwner());
			board.SetSelectedBox(boxId);
			canMove = true;
		}
		return canMove;
	}
	
	public boolean MoveToBox(int boxId)
	{
		boolean canMove = false;
		
		int oldBox = board.GetSelectedBox();
		// Si on clique sur la même case
		if (boxId == oldBox)
		{
			board.SetSelectedBox(-1);
			return true;
		}
		if (oldBox != -1 && board.getBoxList().get(boxId).getIsAPossibleMove())
		{
			// if we can move from oldBox to boxId
			MoveStoneFromTo(oldBox, boxId);
			board.SetSelectedBox(-1);
			canMove = true;
		}
		
		return canMove;
	}
	

	// Cette méthode regarde par rapport au score des dés quelles sont les positions atteignables
	public void PossibleMove(int indexBoxSelected, Player owner) 
	{
		int dice1 = diceOne.Value(), dice2 = diceTwo.Value();
		System.out.println(dice1 + " and " + dice2);
		// On reset les possible move bool de toutes les cases
		board.DesactiveAllPossibleMove();


		// On regarde si la stone a bouger est celle du joueur 1
		if(owner.equals(player1)) {
			// On va dans le sens 1-25
			// Premier dé
			if(diceOne.getRemainingUse() > 0)
			{
				if((indexBoxSelected + dice1) <= 24)
				{
					Box boxAfterDice1 = board.getBoxList().get(indexBoxSelected+ dice1);
					if (boxAfterDice1.getOwner() == null || boxAfterDice1.getOwner().equals(player1))
					{
						boxAfterDice1.setIsAPossibleMove(true);
					} else
					{
						// Si il y a un seul pion adverse
						if (boxAfterDice1.getStonesInside().size() == 1)
						{
							boxAfterDice1.setIsAPossibleMove(true);
						}
					}
				} else
				{
					if (!player1.CanEnd())
					{
						if ((indexBoxSelected + dice1) == 25)
						{
							board.getBoxList().get(25).setIsAPossibleMove(true);
						} else 
						{
							// Si le nombre est plus grand que le nombre de cases pour finir
							// il faut regarder qu'il n'y ai pas de pions avant
							boolean canExit = true;
							for (int i = 19; i < indexBoxSelected; i++)
							{
								if (!board.CheckIsEmpty(i))
								{
									canExit = false;
								}
							}
							if (canExit)
							{
								board.getBoxList().get(25).setIsAPossibleMove(true);
							}
						}
					}
				}
			}
			// Deuxième dé
			if(diceTwo.getRemainingUse() > 0)
			{
				if((indexBoxSelected + dice2) <= 24)
				{
					Box boxAfterDice2 = board.getBoxList().get(indexBoxSelected+ dice2);
					if (boxAfterDice2.getOwner() == null || boxAfterDice2.getOwner().equals(player1))
					{
						boxAfterDice2.setIsAPossibleMove(true);
					} else
					{
						// Si il y a un seul pion adverse
						if (boxAfterDice2.getStonesInside().size() == 1)
						{
							boxAfterDice2.setIsAPossibleMove(true);
						}
					}
				} else
				{
					if (!player1.CanEnd())
					{
						if ((indexBoxSelected + dice2) == 25)
						{
							board.getBoxList().get(25).setIsAPossibleMove(true);
						} else 
						{
							// Si le nombre est plus grand que le nombre de cases pour finir
							// il faut regarder qu'il n'y ai pas de pions avant
							boolean canExit = true;
							for (int i = 19; i < indexBoxSelected; i++)
							{
								if (!board.CheckIsEmpty(i))
								{
									canExit = false;
								}
							}
							if (canExit)
							{
								board.getBoxList().get(25).setIsAPossibleMove(true);
							}
						}
					}
				}	
			}
		} 
		else // Joueur 2
		{
			// On va dans le sens 25-1
			// Premier dé
			if(diceOne.getRemainingUse() > 0)
			{
				if ((indexBoxSelected - dice1) >= 1)
				{
					Box boxAfterDice1 = board.getBoxList().get(indexBoxSelected- dice1);
					if (boxAfterDice1.getOwner() == null || boxAfterDice1.getOwner().equals(player2))
					{
						boxAfterDice1.setIsAPossibleMove(true);
					} else
					{
						// Si il y a un seul pion adverse
						if (boxAfterDice1.getStonesInside().size() == 1)
						{
							boxAfterDice1.setIsAPossibleMove(true);
						}
					}
				
				} else
				{
					if (!player2.CanEnd())
					{
						if ((indexBoxSelected - dice1) == 0)
						{
							board.getBoxList().get(0).setIsAPossibleMove(true);
						} else 
						{
							// Si le nombre est plus grand que le nombre de cases pour finir
							// il faut regarder qu'il n'y ai pas de pions avant
							boolean canExit = true;
							for (int i = 6; i > indexBoxSelected; i--)
							{
								if (!board.CheckIsEmpty(i))
								{
									canExit = false;
								}
							}
							if (canExit)
							{
								board.getBoxList().get(0).setIsAPossibleMove(true);
							}
						}
					}
				}
			}
			// Deuxième dé
			if(diceTwo.getRemainingUse() > 0)
			{
				if ((indexBoxSelected - dice2) >= 1)
				{
					Box boxAfterDice2 = board.getBoxList().get(indexBoxSelected- dice2);
					if (boxAfterDice2.getOwner() == null || boxAfterDice2.getOwner().equals(player2))
					{
						boxAfterDice2.setIsAPossibleMove(true);
					} else
					{
						// Si il y a un seul pion adverse
						if (boxAfterDice2.getStonesInside().size() == 1)
						{
							boxAfterDice2.setIsAPossibleMove(true);
						}
					}
				
				} else
				{
					if (!	player2.CanEnd())
					{
						if ((indexBoxSelected - dice2) == 0)
						{
							board.getBoxList().get(0).setIsAPossibleMove(true);
						} else 
						{
							// Si le nombre est plus grand que le nombre de cases pour finir
							// il faut regarder qu'il n'y ai pas de pions avant
							boolean canExit = true;
							for (int i = 6; i > indexBoxSelected; i--)
							{
								if (!board.CheckIsEmpty(i))
								{
									canExit = false;
								}
							}
							if (canExit)
							{
								board.getBoxList().get(0).setIsAPossibleMove(true);
							}
						}
					}
				}
			}
		}
		
	}
	
	public Player GetPlaying()
	{
		if (player1.isPlaying())
		{
			return player1;
		} else
		{
			return player2;
		}
	}
	
	public void EndOfTurn()
	{
		player1.setPlaying(!player1.isPlaying());
		player2.setPlaying(!player2.isPlaying());
		diceLaunched = false;
	}	
	
	public boolean isDiceLaunched() {
		return diceLaunched;
	}

	public void setDiceLaunched(boolean diceLaunched) {
		this.diceLaunched = diceLaunched;
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

	public Player getPlayer1() {
		return player1;
	}

	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}


}
