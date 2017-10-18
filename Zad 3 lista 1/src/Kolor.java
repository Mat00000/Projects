
public enum Kolor {
		
	Pik(4),
	Trefl(1),
	Karo(2),
	Kier(3);
	
	private final int value;
	
	Kolor(int value)
	{
		this.value = value;
	}
	
	public int Getvalue()
	{
		return value;
	}

}
