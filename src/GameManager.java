import javax.swing.JOptionPane;

public class GameManager {

	private Dice diceOne;
	private Dice diceTwo;
	
	// Chaque joueur poss�de 15 Stones
	private Player player1, player2;
	private Board board;
	private boolean diceLaunched = false;
	private boolean canPlay = true;
	
	public GameManager() {
		player1 = new Player("Rouge");
		player2 = new Player("Blanc");
		diceOne = new Dice();
		diceTwo = new Dice();
		
		board = new Board(player1, player2);
		FillBox();
		
		player1.setPlaying(true);
		player2.setPlaying(false);
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
		System.out.println("RollDices : " + diceOne.Value() + " & " + diceTwo.Value());
		
		if (!IsAllowedToPlay())
		{
			EndOfTurn();
		}
	}
	
	public boolean IsAllowedToPlay()
	{
		if (player1.isPlaying())
		{
			if (!board.getBoxList().get(26).isEmpty())
			{
				PossibleMove(26, player1);
				if (board.NumberOfPossibleMoves(1, 6) == 0)
				{
					System.out.println("Pas de coup possible pour le prisonnier");
					canPlay = false;
					return false;
				}
			} else
			{
				if (CheckAllBoardMove(player1) == 0)
				{
					System.out.println("Pas de coup possible");
					canPlay = false;
					return false;
				}
			}
		} else
		{
			if (!board.getBoxList().get(27).isEmpty())
			{
				PossibleMove(27, player2);
				if (board.NumberOfPossibleMoves(19, 24) == 0)
				{
					System.out.println("Pas de coup possible pour le prisonnier");
					canPlay = false;
					return false;
				}
			} else
			{
				if (CheckAllBoardMove(player2) == 0)
				{
					System.out.println("Pas de coup possible");
					canPlay = false;
					return false;
				}
			}
		}
		return true;
	}
	
	public int CheckAllBoardMove(Player player)
	{
		int totalMove = 0;
		for (int i = 0; i < 26; i++)
		{
			Box box = board.getBoxList().get(i); 
			if (box.getOwner() != null && box.getOwner().equals(player))
			{
				PossibleMove(i, player);
				totalMove += board.NumberOfPossibleMoves(Math.max(0, i-6), Math.min(i+6, 25));
			}
		}
		return totalMove;
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
	

	// Cette m�thode change de place une stone
	public void MoveStoneFromTo(int indexOldBox, int indexNewBox) {
		// Depile one stone from the odlIndexBox
		Box currentBox = board.getBoxList().get(indexOldBox);
		Box nextBox = board.getBoxList().get(indexNewBox);
		if (!currentBox.getStonesInside().isEmpty())
		{
			currentBox.getStonesInside().get(0).SetBox(nextBox);
		}

		// d�cro�t le nombre d'utilisations restantes du d� utilis�
		if (indexOldBox == 26) // prison joueur 1
		{
			if (diceOne.Value() == indexNewBox && diceOne.getRemainingUse() > 0)
			{
				diceOne.DecreaseRemainingUse();
			} else
			{
				if (diceTwo.Value() == indexNewBox && diceTwo.getRemainingUse() > 0)
				{
					diceTwo.DecreaseRemainingUse();
				}
				else
				{
					System.out.println("error determining which dice was played");
					return;
				}
			}
		}
		
		if (indexOldBox == 27) // prison joueur 2
		{
			if (diceOne.Value() == 25-indexNewBox && diceOne.getRemainingUse() > 0)
			{
				diceOne.DecreaseRemainingUse();
			} else
			{
				if (diceTwo.Value() == 25-indexNewBox && diceTwo.getRemainingUse() > 0)
				{
					diceTwo.DecreaseRemainingUse();
				}
				else
				{
					System.out.println("error determining which dice was played");
					return;
				}
			}
		}
		
		// si on a jou� le d� n�1 <|�_�|>
		if (diceOne.Value() == Math.abs(indexOldBox - indexNewBox) && diceOne.getRemainingUse() > 0)
		{
			diceOne.DecreaseRemainingUse();
		} else
		{
			// si on a jou� le d� n�2 <|�_�|>
			if (diceTwo.Value() == Math.abs(indexOldBox - indexNewBox) && diceTwo.getRemainingUse() > 0)
			{
				diceTwo.DecreaseRemainingUse();
			} 
			else // si on peut sortir et que les d�s sont tous les deux trop grands
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
		
		// g�re si il y a un pion adverse sur cette case
		if (nextBox.getOwner() != null && !nextBox.getOwner().equals(currentBox.getOwner()) && nextBox.getStonesInside().size() == 1)
		{
			board.TakeStoneAtBox(indexNewBox);
		}
		
		FillBox();
		if (diceOne.getRemainingUse() == 0 && diceTwo.getRemainingUse() == 0 || !this.IsAllowedToPlay())
		{
			EndOfTurn();
		}
		
	}
	
	public boolean SelectBox(int boxId)
	{
		boolean canMove = false;
		Box selectedBox = board.getBoxList().get(boxId);
		System.out.println(selectedBox.getOwner() != null);
		if (selectedBox.getOwner() != null && selectedBox.getOwner().isPlaying() && boxId > 0)
		{
			System.out.println(player1.isPlaying() + " - " + player2.isPlaying() + " - " + ((boxId < 25 && board.getBoxList().get(26).isEmpty()) || (boxId == 26 && !board.getBoxList().get(26).isEmpty())));
			if ( player1.isPlaying() )
			{
				if ( (boxId < 25 && board.getBoxList().get(26).isEmpty()) || (boxId == 26 && !board.getBoxList().get(26).isEmpty()) )
				{
					PossibleMove(boxId, selectedBox.getOwner());
					board.SetSelectedBox(boxId);
					canMove = true;
				}				
			}
			else
			{
				if ( (boxId < 25 && board.getBoxList().get(27).isEmpty()) || (boxId == 27 && !board.getBoxList().get(27).isEmpty()) )
				{
					PossibleMove(boxId, selectedBox.getOwner());
					board.SetSelectedBox(boxId);
					canMove = true;
				}
			}
		}
		return canMove;
	}
	
	public boolean MoveToBox(int boxId)
	{
		boolean canMove = false;
		int oldBox = board.GetSelectedBox();
		// Si on clique sur la m�me case
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
	

	// Cette m�thode regarde par rapport au score des d�s quelles sont les positions atteignables
	public void PossibleMove(int indexBoxSelected, Player owner) 
	{
		int dice1 = diceOne.Value(), dice2 = diceTwo.Value();
		// On reset les possible move bool de toutes les cases
		board.DesactiveAllPossibleMove();
		
		if (indexBoxSelected == 26) // un pion rouge en prison
		{
			Box boxAfterDice1 = board.getBoxList().get(dice1);
			Box boxAfterDice2 = board.getBoxList().get(dice2);
			
			if (diceOne.getRemainingUse() > 0)
			{
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
			}
			if (diceTwo.getRemainingUse() > 0)
			{
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
			}
			return;
		}
		
		if (indexBoxSelected == 27) // un pion blanc en prison
		{
			Box boxAfterDice1 = board.getBoxList().get(25 - dice1);
			Box boxAfterDice2 = board.getBoxList().get(25 - dice2);
			
			if (diceOne.getRemainingUse() > 0)
			{
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
			}
			if (diceTwo.getRemainingUse() > 0)
			{
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
			}
			return;
		}
		
		// On regarde si la stone a bouger est celle du joueur 1
		if(owner.equals(player1)) {
			// On va dans le sens 1-25
			// Premier d�
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
					if (player1.CanEnd())
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
			// Deuxi�me d�
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
					if (player1.CanEnd())
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
			// Premier d�
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
					if (player2.CanEnd())
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
			// Deuxi�me d�
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
					if (player2.CanEnd())
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
		boolean endOfGame = true;
		for (Stone stone : GetPlaying().getStoneList())
		{
			if (stone.GetBox().getIndexBox() != 0 && stone.GetBox().getIndexBox() != 25)
			{
				endOfGame = false;
				break;
			}
		}
		if (endOfGame)
		{
			EndOfGame();
			return;
		}
		player1.setPlaying(!player1.isPlaying());
		player2.setPlaying(!player2.isPlaying());
		
		diceLaunched = false;
	}	
	
	public void EndOfGame()
	{
		GetPlaying().addToScore(GetResult());
		player1.setPlaying(!player1.isPlaying());
		player2.setPlaying(!player2.isPlaying());
		diceLaunched = false;
		board.getBoxList().clear();
		board = new Board(player1, player2);
		FillBox();
		
		if (player1.getScore() >= 3)
		{
			System.out.println("Red wins ! ");
			JOptionPane winner = new JOptionPane();
			winner.showConfirmDialog(null, "Rouge gagne la partie ! F�licitations", "Rouge gagne !", JOptionPane.INFORMATION_MESSAGE);
			
		} else
		{
			if (player2.getScore() >= 3)
			{
				System.out.println("White wins ! ");
				JOptionPane winner = new JOptionPane();
				winner.showConfirmDialog(null, "Blanc gagne la partie ! F�licitations", "Blanc gagne !", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	
	private int GetResult()
	{
		int score = 1;
		if (GetPlaying().equals(player1)) // player1 wins
		{
			for (Stone stone : player2.getStoneList())
			{
				if (stone.GetBox().getIndexBox() < 19 && stone.GetBox().getIndexBox() > 6)
					score = Math.max(score, 2);
				if (stone.GetBox().getIndexBox() > 19)
					score = 3;
			}
			return score;
		} else								// player2 wins
		{
			for (Stone stone : player1.getStoneList())
			{
				if (stone.GetBox().getIndexBox() < 19 && stone.GetBox().getIndexBox() > 6)
					score = Math.max(score, 2);
				if (stone.GetBox().getIndexBox() < 6 || stone.GetBox().getIndexBox() == 26)
					score = 3;
			}
			return score;
		}
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

	public boolean canPlay() {
		return canPlay;
	}

	public void setCanPlay(boolean canPlay) {
		this.canPlay = canPlay;
	}


}
