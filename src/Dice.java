public class Dice {
	
	private int number = 1;
	private int remainingUse = 0;

	public Dice(){
		
	}

	public void Roll(){
		number = 1 + (int) ( Math.random() * 6);
	}

	public int Value(){
		return number;
	}

	public int getRemainingUse() {
		return remainingUse;
	}

	public void setRemainingUse(int remainingUse) {
		this.remainingUse = remainingUse;
	}
	
	public void DecreaseRemainingUse()
	{
		if (remainingUse > 0)
		{
			remainingUse --;
		}
	}
}
