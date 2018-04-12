import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

public class Interface extends JFrame implements MouseListener, ActionListener {

	private static final long serialVersionUID = 1L;
	private final int width = 1080;
	private final int heigth = 926;

	private JLayeredPane lp = new JLayeredPane();

	private JLabel backgroundImage;
	private JLabel diceImage;
	private JLabel redStoneLabel = new JLabel();
	private JLabel whiteStoneLabel = new JLabel();
	private JLabel selectedStoneLabel = new JLabel();

	private JLabel validateBoxLabel = new JLabel();

	// Les images du bouton et des diffèrentes faces des dés
	private BufferedImage bg;
	private BufferedImage buttonDiceImage;
	private BufferedImage dice_1;
	private BufferedImage dice_2;
	private BufferedImage dice_3;
	private BufferedImage dice_4;
	private BufferedImage dice_5;
	private BufferedImage dice_6;
	private BufferedImage redStoneImage;
	private BufferedImage whiteStoneImage;
	private BufferedImage stoneSelectedImage;

	private BufferedImage validateBoxTopImage;
	private BufferedImage validateBoxBotImage;

	private JLabel diceOne;
	private JLabel diceTwo;

	//création du bouton
	JButton rollDice;

	GameManager gameManager_;
	private Boolean OneStoneIsSelected = false;

	// Test 
	int dice1 = 1;
	int dice2 = 5;

	public Interface() {
		//Display the window.
		this.setVisible(true);
		this.setSize(1100, 970);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Backgammon");
		addMouseListener(this);
		add(lp);

		// CREATE THE GAME MANAGER (will manage all the calculations)
		gameManager_ = new GameManager();

		// Get the path for pictures
		URL urlBackground = getClass().getResource("Board.jpg");
		URL urlDiceImage = getClass().getResource("lucky_dice.png");
		URL urlDice_1 = getClass().getResource("Dice_1.png");
		URL urlDice_2 = getClass().getResource("Dice_2.png");
		URL urlDice_3 = getClass().getResource("Dice_3.png");
		URL urlDice_4 = getClass().getResource("Dice_4.png");
		URL urlDice_5 = getClass().getResource("Dice_5.png");
		URL urlDice_6 = getClass().getResource("Dice_6.png");
		URL urlRedStone = getClass().getResource("RedStone.png");
		URL urlWhiteStone = getClass().getResource("WhiteStone.png");
		URL urlSelectedStone = getClass().getResource("StoneSelected.png");

		URL urlSelectedBoxTop = getClass().getResource("ValidateBoxTop.png");
		URL urlSelectedBoxBot = getClass().getResource("ValidateBoxBot.png");

		//Initialize image for bg and dice
		File fileBackground = new File(urlBackground.getPath());
		bg = null;
		try {
			bg = ImageIO.read(fileBackground);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Get dice images
		File fileButtonDiceImage = new File(urlDiceImage.getPath());
		buttonDiceImage = null;
		try {
			buttonDiceImage = ImageIO.read(fileButtonDiceImage);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ImageIcon water = new ImageIcon(urlDiceImage.getPath());
		rollDice = new JButton(water);

		File fileDice_1 = new File(urlDice_1.getPath());
		dice_1 = null;
		try {
			dice_1 = ImageIO.read(fileDice_1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		File fileDice_2 = new File(urlDice_2.getPath());
		dice_2 = null;
		try {
			dice_2 = ImageIO.read(fileDice_2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		File fileDice_3 = new File(urlDice_3.getPath());
		dice_3 = null;
		try {
			dice_3 = ImageIO.read(fileDice_3);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		File fileDice_4 = new File(urlDice_4.getPath());
		dice_4 = null;
		try {
			dice_4 = ImageIO.read(fileDice_4);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		File fileDice_5 = new File(urlDice_5.getPath());
		dice_5 = null;
		try {
			dice_5 = ImageIO.read(fileDice_5);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		File fileDice_6 = new File(urlDice_6.getPath());
		dice_6 = null;
		try {
			dice_6 = ImageIO.read(fileDice_6);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// get stone images

		File fileRedStone = new File(urlRedStone.getPath());
		redStoneImage = null;
		try {
			redStoneImage = ImageIO.read(fileRedStone);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		File fileWhiteStone = new File(urlWhiteStone.getPath());
		whiteStoneImage = null;
		try {
			whiteStoneImage = ImageIO.read(fileWhiteStone);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		File fileSelectedStone = new File(urlSelectedStone.getPath());
		stoneSelectedImage = null;
		try {
			stoneSelectedImage = ImageIO.read(fileSelectedStone);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Get BoxSelected Image
		File fileSelectedBoxTop = new File(urlSelectedBoxTop.getPath());
		validateBoxTopImage = null;
		try {
			validateBoxTopImage = ImageIO.read(fileSelectedBoxTop);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		File fileSelectedBoxBot = new File(urlSelectedBoxBot.getPath());
		validateBoxBotImage = null;
		try {
			validateBoxBotImage = ImageIO.read(fileSelectedBoxBot);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Show();

		//Ajout de l'action listener
		rollDice.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				gameManager_.RollDices();
				//System.out.println(gameManager_.getDiceOne().GetNumber());
				//System.out.println(gameManager_.getDiceTwo().GetNumber());

				lp.removeAll();//or remove(JComponent)
				//Dice 1
				switch (gameManager_.getDiceOne().GetNumber())
				{
				case 1:
					diceOne = new JLabel(new ImageIcon(dice_1));
					diceOne.setBounds(130, 415, 100, 100);
					lp.add(diceOne, new Integer(2));
					break;
				case 2:
					diceOne = new JLabel(new ImageIcon(dice_2));
					diceOne.setBounds(130, 415 , 100, 100);
					lp.add(diceOne, new Integer(2));
					break;
				case 3:
					diceOne = new JLabel(new ImageIcon(dice_3));
					diceOne.setBounds(130, 415, 100, 100);
					lp.add(diceOne, new Integer(2));
					break;
				case 4:
					diceOne = new JLabel(new ImageIcon(dice_4));
					diceOne.setBounds(130, 415, 100, 100);
					lp.add(diceOne, new Integer(2));
					break;
				case 5:
					diceOne = new JLabel(new ImageIcon(dice_5));
					diceOne.setBounds(130, 415, 100, 100);
					lp.add(diceOne, new Integer(2));
					break;
				case 6:
					diceOne = new JLabel(new ImageIcon(dice_6));
					diceOne.setBounds(130, 415, 100, 100);
					lp.add(diceOne, new Integer(2));
					break;
				default:
					break;
				}

				// Dice 2
				switch (gameManager_.getDiceTwo().GetNumber())
				{
				case 1:
					diceTwo = new JLabel(new ImageIcon(dice_1));
					diceTwo.setBounds(280, 415, 100, 100);
					lp.add(diceTwo, new Integer(2));
					break;
				case 2:
					diceTwo = new JLabel(new ImageIcon(dice_2));
					diceTwo.setBounds(280, 415, 100, 100);
					lp.add(diceTwo, new Integer(2));
					break;
				case 3:
					diceTwo = new JLabel(new ImageIcon(dice_3));
					diceTwo.setBounds(280, 415, 100, 100);
					lp.add(diceTwo, new Integer(2));
					break;
				case 4:
					diceTwo = new JLabel(new ImageIcon(dice_4));
					diceTwo.setBounds(280, 415, 100, 100);
					lp.add(diceTwo, new Integer(2));
					break;
				case 5:
					diceTwo = new JLabel(new ImageIcon(dice_5));
					diceTwo.setBounds(280, 415, 100, 100);

					System.out.println("Je passe ici");
					lp.add(diceTwo, new Integer(2));
					break;
				default:
					break;
				}

				Show();
			}
		});

	}

	// Show all food
	public void Show()
	{
		lp.removeAll();
		// Background
		backgroundImage = new JLabel(new ImageIcon(bg));
		backgroundImage.setBounds(0, 0, width, heigth);
		lp.add(backgroundImage, new Integer(1));

		/*validateBoxLabel = new JLabel(new ImageIcon(validateBoxTopImage));
		validateBoxLabel.setBounds(500, 500, 68, 381);
		lp.add(validateBoxLabel, new Integer(2));*/

		rollDice.setBounds(430, 405, 130, 130);
		lp.add(rollDice, new Integer(2));

		// Créer les stones
		ShowStones();

		if(OneStoneIsSelected) {
			System.out.print("ici");
			ShowPossibleMove();
		}

		lp.repaint();
	}

	private void ShowStones() {
		// TODO Auto-generated method stub

		//redStoneLabel = new JLabel(new ImageIcon(redStoneImage));
		//whiteStoneLabel = new JLabel(new ImageIcon(whiteStoneImage));

		for(int i = 0; i < gameManager_.getBoard().getBoxList().size(); i++){
			if(!gameManager_.getBoard().getBoxList().get(i).getIsEmpty()) {
				Box currentBox = gameManager_.getBoard().getBoxList().get(i);
				if(currentBox.getOwner().equalsIgnoreCase("Rouge")) {

					// TODO Faire apparaitre les stones rouge dans une case.
					for(int j = 0; j < currentBox.getStonesInside().size(); j++) {
						JLabel redStoneLabel = new JLabel(new ImageIcon(redStoneImage));
						// Si la case est actuellement selectionnée par le joueur alors afficher une pièce verte
						if(j == currentBox.getStonesInside().size() - 1 && currentBox.getBoxSelected()) {
							redStoneLabel = new JLabel(new ImageIcon(stoneSelectedImage));
						}

						if(currentBox.getIsTop()) {
							redStoneLabel.setBounds(currentBox.getBoxStartPosition().x, currentBox.getBoxStartPosition().y + 40*j, 50, 50);
						}else {
							redStoneLabel.setBounds(currentBox.getBoxStartPosition().x , currentBox.getBoxStartPosition().y - 40*j, 50, 50);
						}
						lp.add(redStoneLabel, new Integer(2));
					}
				}
				if(currentBox.getOwner().equalsIgnoreCase("Blanc")) {

					// TODO Faire apparaitre les stones blanches dans une case.
					for(int j = 0; j < currentBox.getStonesInside().size(); j++) {
						JLabel whiteStoneLabel = new JLabel(new ImageIcon(whiteStoneImage));
						// Si la case est actuellement selectionnée par le joueur alors afficher une pièce verte
						if(j == currentBox.getStonesInside().size() - 1 && currentBox.getBoxSelected()) {
							whiteStoneLabel = new JLabel(new ImageIcon(stoneSelectedImage));
						}

						if(currentBox.getIsTop()) {
							whiteStoneLabel.setBounds(currentBox.getBoxStartPosition().x, currentBox.getBoxStartPosition().y + 40*j, 50, 50);
						}else {
							whiteStoneLabel.setBounds(currentBox.getBoxStartPosition().x, currentBox.getBoxStartPosition().y - 40*j, 50, 50);
						}
						lp.add(whiteStoneLabel, new Integer(2));
					}
				}
			}
		}
	}

	private void ShowPossibleMove() {
		// TODO Auto-generated method stub
		for(int i = 0; i < gameManager_.getBoard().getBoxList().size(); i++){

			Box currentBox = gameManager_.getBoard().getBoxList().get(i);
			
			if(currentBox.getIsAPossibleMove()) {

				if(currentBox.getIsTop()) {
					validateBoxLabel = new JLabel(new ImageIcon(validateBoxTopImage));
					validateBoxLabel.setBounds(currentBox.getBoxStartPosition().x - 7, currentBox.getBoxStartPosition().y -2, 68, 381);
					lp.add(validateBoxLabel, new Integer(2));
				}else
				{
					validateBoxLabel = new JLabel(new ImageIcon(validateBoxBotImage));
					validateBoxLabel.setBounds(currentBox.getBoxStartPosition().x - 7, currentBox.getBoxStartPosition().y - 330, 68, 381);
					lp.add(validateBoxLabel, new Integer(2));
				}
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int x = e.getX() - 9;
		int y = e.getY() - 40;
		System.out.println(x + " ; " + y + " \n ");

		// Check if something is selected
		int notInBox = 0;
		int BoxNotEmpty = 0;
		for(int i = 0; i < gameManager_.getBoard().getBoxList().size(); i++){
			// case non vide OU une pièce selectionnée
			if(!gameManager_.getBoard().getBoxList().get(i).getIsEmpty() || OneStoneIsSelected) {
				BoxNotEmpty++;
				Box currentBox = gameManager_.getBoard().getBoxList().get(i);
				
				System.out.println(currentBox.getBoxStartPosition().x + " "+ currentBox.getBoxEndPosition().x  + " "+  currentBox.getBoxStartPosition().y + " "+ currentBox.getBoxEndPosition().y + " \n");
				
				// On est dans la partie haut du terrain
				if(currentBox.getIsTop()) {
					if(x >= currentBox.getBoxStartPosition().x && x <= currentBox.getBoxEndPosition().x && y >= currentBox.getBoxStartPosition().y && y <= currentBox.getBoxEndPosition().y) {
						// La case cliquée est-t-elle vide ? Si oui alors nouvelle selection
						if(!currentBox.getIsEmpty() && !currentBox.getIsAPossibleMove()) {
							gameManager_.getBoard().BoxSelected(i);
							OneStoneIsSelected = true;
							System.out.print("Selectionné");
							// On affiche les mouvements possibles
							gameManager_.getBoard().PossibleMove(i, dice1, dice2, false);
						}
						
						int oldIndex = gameManager_.getBoard().FindIndexBoxSelected();
						Box oldBox = gameManager_.getBoard().getBoxList().get(oldIndex);
						// La case cliqué est-il une case où le joueur peut poser sa stone? Si oui alors on change la stone de place
						if(oldIndex != 0 &&(currentBox.getIsEmpty() || oldBox.getOwner() == currentBox.getOwner()) && currentBox.getIsAPossibleMove()) {
							System.out.print("Hi");
							// faire un if
							gameManager_.getBoard().ChangeStoneFromABoxToAnother(i, oldIndex, oldBox.getOwner());
							gameManager_.getBoard().DesactiveAllSelected();
							OneStoneIsSelected = false;
						}
					}
					else {
						notInBox++;
					}
				}
				// On est dans la partie basse du terrain
				else {
					if(x >= currentBox.getBoxStartPosition().x && x <= currentBox.getBoxEndPosition().x && y <= currentBox.getBoxStartPosition().y && y >= currentBox.getBoxEndPosition().y) {
						// La case cliquée est-t-elle vide ? Si oui alors nouvelle selection
						if(!currentBox.getIsEmpty() && !currentBox.getIsAPossibleMove()) {
							gameManager_.getBoard().BoxSelected(i);
							OneStoneIsSelected = true;
							System.out.print("Selectionné");
							// On affiche les mouvements possibles
							gameManager_.getBoard().PossibleMove(i, dice1, dice2, false);
						}
						
						int oldIndex = gameManager_.getBoard().FindIndexBoxSelected();
						Box oldBox = gameManager_.getBoard().getBoxList().get(oldIndex);
						// La case cliqué est-il une case où le joueur peut poser sa stone? Si oui alors on change la stone de place
						if(oldIndex != 0 &&(currentBox.getIsEmpty() || oldBox.getOwner() == currentBox.getOwner()) && currentBox.getIsAPossibleMove()) {
							System.out.print("Hi");
							// faire un if
							gameManager_.getBoard().ChangeStoneFromABoxToAnother(i, oldIndex, oldBox.getOwner());
							gameManager_.getBoard().DesactiveAllSelected();
							OneStoneIsSelected = false;
						}
					}else {
						notInBox++;
					}
				}
			}
		}
		if(notInBox == BoxNotEmpty) {
			OneStoneIsSelected = false;
			gameManager_.getBoard().DesactiveAllSelected();
		}
		Show();
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		Interface frame = new Interface();
	}
}