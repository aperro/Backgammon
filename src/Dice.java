
public class Dice {
	
	private int number = 1;

	public Dice(){
	}

	public void Roll(){
		number = 1 + (int) ( Math.random() * 5);
	}

	public int GetNumber(){
		return number;
	}
}
