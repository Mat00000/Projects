
public enum Karta {

	dwa(2),
	trzy(3),
	cztery(4),
	piêæ(5),
	szeœæ(6),
	siedem(7),
	osiem(8),
	dziewiêæ(9),
	dziesiêæ(10),
	walet(11),
	królowa(12),
	król(13),
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
