public class Card implements Comparable<Card>
{
	Karta name;
	Kolor color;
  
	public Card(Karta n, Kolor c)
	{
		name = n;
		color = c;
	}
  
	public Karta getKarta()
    {
		return name;
    }
  
	public Kolor getKolor()
	{
		return color;
	}
  
	public String properties()
	{
   String z = name.toString()+" "+color.toString();
   return z;
	}

@Override

	public int compareTo(Card arg0) 
	{
	int currentVal = this.getKarta().Getvalue() * 10 + this.getKolor().Getvalue();
	int nexVal = arg0.getKarta().Getvalue() * 10 + arg0.getKolor().Getvalue();
	return currentVal - nexVal;
	}
}