import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.Icon;
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
	private BufferedImage bg;
	private JLabel backgroundImage;
	private JLabel DiceImage;
	
	private BufferedImage buttonDiceImage;
	private BufferedImage dice_1;
	private BufferedImage dice_2;
	private BufferedImage dice_3;
	private BufferedImage dice_4;
	private BufferedImage dice_5;
	private BufferedImage dice_6;
	
	private JLabel diceOne;
	private JLabel diceTwo;
	
	//création du bouton
	JButton roolDice;
	
	GameManager gameManager_;
	
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
		
		/*
		 * Initialize image for bg and dice
		 */
		File fileBackground = new File(urlBackground.getPath());
		bg = null;
		try {
			bg = ImageIO.read(fileBackground);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		File fileButtonDiceImage = new File(urlDiceImage.getPath());
		buttonDiceImage = null;
		try {
			buttonDiceImage = ImageIO.read(fileButtonDiceImage);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ImageIcon water = new ImageIcon(urlDiceImage.getPath());
		roolDice = new JButton(water);
		
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
		Show();
		
		//Ajout de l'action listener
		roolDice.addActionListener(new ActionListener()
		{
		    @Override
		    public void actionPerformed(ActionEvent e)
		    {
		    	gameManager_.RollDices();
		    	System.out.println(gameManager_.getDiceOne().GetNumber());
		    	System.out.println(gameManager_.getDiceTwo().GetNumber());
		    	
		    	lp.removeAll();//or remove(JComponent)
		        //Dice 1
		        switch (gameManager_.getDiceOne().GetNumber())
				{
				case 1:
					diceOne = new JLabel(new ImageIcon(dice_1));
					diceOne.setBounds(0, 0 , 100, 100);
					lp.add(diceOne, new Integer(2));
					break;
				case 2:
					diceOne = new JLabel(new ImageIcon(dice_2));
					diceOne.setBounds(0, 0 , 100, 100);
					lp.add(diceOne, new Integer(2));
					break;
				case 3:
					diceOne = new JLabel(new ImageIcon(dice_3));
					diceOne.setBounds(0, 0 , 100, 100);
					lp.add(diceOne, new Integer(2));
					break;
				case 4:
					diceOne = new JLabel(new ImageIcon(dice_4));
					diceOne.setBounds(0, 0 , 100, 100);
					lp.add(diceOne, new Integer(2));
					break;
				case 5:
					diceOne = new JLabel(new ImageIcon(dice_5));
					diceOne.setBounds(0, 0 , 100, 100);
					lp.add(diceOne, new Integer(2));
					break;
				case 6:
					diceOne = new JLabel(new ImageIcon(dice_6));
					diceOne.setBounds(0, 0 , 100, 100);
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
					diceTwo.setBounds(500, 500 , 600, 600);
					lp.add(diceTwo, new Integer(2));
					break;
				case 2:
					diceTwo = new JLabel(new ImageIcon(dice_2));
					diceTwo.setBounds(500, 500 , 600, 600);
					lp.add(diceTwo, new Integer(2));
					break;
				case 3:
					diceTwo = new JLabel(new ImageIcon(dice_3));
					diceTwo.setBounds(500, 500 , 600, 600);
					lp.add(diceTwo, new Integer(2));
					break;
				case 4:
					diceTwo = new JLabel(new ImageIcon(dice_4));
					diceTwo.setBounds(500, 500 , 600, 600);
					lp.add(diceTwo, new Integer(2));
					break;
				case 5:
					diceTwo = new JLabel(new ImageIcon(dice_5));
					diceTwo.setBounds(500, 500 , 600, 600);

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
			
			// Background
			backgroundImage = new JLabel(new ImageIcon(bg));
			backgroundImage.setBounds(0, 0, width, heigth);
			lp.add(backgroundImage, new Integer(1));
			
			roolDice.setBounds(100, 100, 200, 120);
			lp.add(roolDice, new Integer(2));
			
			lp.repaint();
		}

	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
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