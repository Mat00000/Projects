
public enum Karta {

	dwa(2),
	trzy(3),
	cztery(4),
	pi��(5),
	sze��(6),
	siedem(7),
	osiem(8),
	dziewi��(9),
	dziesi��(10),
	walet(11),
	kr�lowa(12),
	kr�l(13),
	as(14);
	
	private final int value;
	
	Karta(int value)
	{
		this.value = value;
	}
	
	public int Getvalue()
	{
		return value;
	}
	
}
