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
	private JLabel redStoneGoalLabel = new JLabel();
	private JLabel whiteStoneGoalLabel = new JLabel();
	private JLabel selectedStoneLabel = new JLabel();

	private JLabel validateBoxLabel = new JLabel();
	private JLabel validateBoxGoalLabel = new JLabel();

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
	private BufferedImage redStoneGoalImage;
	private BufferedImage whiteStoneGoalImage;

	private BufferedImage stoneSelectedImage;

	private BufferedImage validateBoxTopImage;
	private BufferedImage validateBoxBotImage;
	private BufferedImage validateBoxGoalImage;

	private JLabel diceOne;
	private JLabel diceTwo;

	//création du bouton
	JButton rollDice;

	GameManager gameManager_;
	private boolean OneStoneIsSelected = false;

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
		URL urlRedGoalStone = getClass().getResource("RedStoneGoal.png");
		URL urlWhiteGoalStone = getClass().getResource("WhiteStoneGoal.png");
		URL urlSelectedStone = getClass().getResource("StoneSelected.png");

		URL urlSelectedBoxTop = getClass().getResource("ValidateBoxTop.png");
		URL urlSelectedBoxBot = getClass().getResource("ValidateBoxBot.png");
		URL urlSelectedBoxGoal = getClass().getResource("ValidateBoxGoal.png");

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

		// get stone Goal images

		File fileRedGoalStone = new File(urlRedGoalStone.getPath());
		redStoneGoalImage = null;
		try {
			redStoneGoalImage = ImageIO.read(fileRedGoalStone);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		File fileWhiteGoalStone= new File(urlWhiteGoalStone.getPath());
		whiteStoneGoalImage = null;
		try {
			whiteStoneGoalImage = ImageIO.read(fileWhiteGoalStone);
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

		File fileSelectedBoxGoal = new File(urlSelectedBoxGoal.getPath());
		validateBoxGoalImage = null;
		try {
			validateBoxGoalImage = ImageIO.read(fileSelectedBoxGoal);
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
		// Si on a lancé les dés, on affiche le résultat, sinon on affiche le bouton de lancement des dés
		if (!gameManager_.isDiceLaunched())
		{	
			rollDice.setBounds(705, 405, 130, 130);
			lp.add(rollDice, new Integer(2));
		} else
		{
			//Dice 1
			switch (gameManager_.getDiceOne().Value())
			{
			case 1:
				diceOne = new JLabel(new ImageIcon(dice_1));
				break;
			case 2:
				diceOne = new JLabel(new ImageIcon(dice_2));
				break;
			case 3:
				diceOne = new JLabel(new ImageIcon(dice_3));
				break;
			case 4:
				diceOne = new JLabel(new ImageIcon(dice_4));
				break;
			case 5:
				diceOne = new JLabel(new ImageIcon(dice_5));
				break;
			case 6:
				diceOne = new JLabel(new ImageIcon(dice_6));
				break;
			default:
				break;
			}

			// Dice 2
			switch (gameManager_.getDiceTwo().Value())
			{
			case 1:
				diceTwo = new JLabel(new ImageIcon(dice_1));
				break;
			case 2:
				diceTwo = new JLabel(new ImageIcon(dice_2));
				break;
			case 3:
				diceTwo = new JLabel(new ImageIcon(dice_3));
				break;
			case 4:
				diceTwo = new JLabel(new ImageIcon(dice_4));
				break;
			case 5:
				diceTwo = new JLabel(new ImageIcon(dice_5));
				break;
			case 6:
				diceTwo = new JLabel(new ImageIcon(dice_6));
				break;
			default:
				break;
			}

			diceOne.setBounds(130, 415, 100, 100);
			diceTwo.setBounds(280, 415, 100, 100);
			if (gameManager_.getDiceOne().getRemainingUse() > 0)
				lp.add(diceOne, new Integer(2));
			if (gameManager_.getDiceTwo().getRemainingUse() > 0)
				lp.add(diceTwo, new Integer(2));
		}

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

		for(Box currentBox : gameManager_.getBoard().getBoxList()){
			if(!currentBox.isEmpty()) {

				if(currentBox.getOwner() != null && currentBox.getOwner().getName().equalsIgnoreCase("Rouge")) {
					// TODO Faire apparaitre les stones rouge dans une case.
					for(int j = 0; j < currentBox.getStonesInside().size(); j++) {
						JLabel redStoneLabel = new JLabel(new ImageIcon(redStoneImage));
						// Si la case est actuellement selectionnée par le joueur alors afficher une pièce verte
						if(j == currentBox.getStonesInside().size() - 1 && currentBox.getIndexBox() == gameManager_.getBoard().GetSelectedBox()) {
							redStoneLabel = new JLabel(new ImageIcon(stoneSelectedImage));
						}
						if (currentBox.getIndexBox() == 25) // player 1 goal
						{
							System.out.println("oki");
							JLabel redStoneGoalLabel = new JLabel(new ImageIcon(redStoneGoalImage));
							redStoneGoalLabel.setBounds(currentBox.getBoxStartPosition().x, currentBox.getBoxStartPosition().y - 26*j, 66, 26);
							System.out.println("oki");
						}

						if (currentBox.getIndexBox() == 26) // player 1 prison
							redStoneLabel.setBounds(currentBox.getBoxStartPosition().x, currentBox.getBoxStartPosition().y - 30*j, 50, 50);

						if(currentBox.getIsTop()) {
							redStoneLabel.setBounds(currentBox.getBoxStartPosition().x, currentBox.getBoxStartPosition().y + 40*j, 50, 50);
						}else {
							redStoneLabel.setBounds(currentBox.getBoxStartPosition().x , currentBox.getBoxStartPosition().y - 40*j - 50, 50, 50);
						}
						lp.add(redStoneLabel, new Integer(2));
					}
				}
				if(currentBox.getOwner() != null && currentBox.getOwner().getName().equalsIgnoreCase("Blanc")) {

					// TODO Faire apparaitre les stones blanches dans une case.
					for(int j = 0; j < currentBox.getStonesInside().size(); j++) {
						JLabel whiteStoneLabel = new JLabel(new ImageIcon(whiteStoneImage));
						// Si la case est actuellement selectionnée par le joueur alors afficher une pièce verte
						System.out.println(gameManager_.getBoard().GetSelectedBox());
						if(j == currentBox.getStonesInside().size() - 1 && currentBox.getIndexBox() == gameManager_.getBoard().GetSelectedBox()) {
							whiteStoneLabel = new JLabel(new ImageIcon(stoneSelectedImage));
						}
						if (currentBox.getIndexBox() == 0) // player 2 goal
							whiteStoneLabel.setBounds(currentBox.getBoxStartPosition().x, currentBox.getBoxStartPosition().y + 30*j, 50, 50);

						if(currentBox.getIsTop()) {
							whiteStoneLabel.setBounds(currentBox.getBoxStartPosition().x, currentBox.getBoxStartPosition().y + 40*j, 50, 50);
						}else {
							whiteStoneLabel.setBounds(currentBox.getBoxStartPosition().x, currentBox.getBoxStartPosition().y - 40*j - 50, 50, 50);
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
					validateBoxLabel.setBounds(currentBox.getBoxStartPosition().x - 7, currentBox.getBoxStartPosition().y -2, 68, 380);
					lp.add(validateBoxLabel, new Integer(2));
				}else
				{
					validateBoxLabel = new JLabel(new ImageIcon(validateBoxBotImage));
					validateBoxLabel.setBounds(currentBox.getBoxStartPosition().x-9, currentBox.getBoxStartPosition().y - 378, 68, 380);
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
		int clickedBox = -1;
		boolean BoxNotEmpty = false;

		for(Box box : gameManager_.getBoard().getBoxList())
		{
			// On est dans la partie haut du terrain
			if(box.getIsTop()) {
				// Si on a cliqué dans cette case
				if(x >= box.getBoxStartPosition().x && x <= box.getBoxEndPosition().x && y >= box.getBoxStartPosition().y && y <= box.getBoxEndPosition().y) 
				{
					clickedBox = box.getIndexBox();
				}
			}
			// On est dans la partie basse du terrain
			else 
			{
				if(x >= box.getBoxStartPosition().x && x <= box.getBoxEndPosition().x && y <= box.getBoxStartPosition().y && y >= box.getBoxEndPosition().y) 
				{
					clickedBox = box.getIndexBox();
				}
			}
		}

		if (clickedBox > 0 && clickedBox < 26)
		{
			// Si c'est le premier clique valide du joueur : selection d'un pion
			if (!OneStoneIsSelected)
			{
				if (gameManager_.SelectBox(clickedBox))
				{
					OneStoneIsSelected = true;
				}
			}
			else
			{
				if (gameManager_.MoveToBox(clickedBox))
				{
					OneStoneIsSelected = false;
				}
			}
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