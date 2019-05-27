public class HammingCode {
	public int errorCorrection(int x) {
		String binary = "";
		int numberOfZeros = 0;
		int numberOfOnes = 0;
		int temp;
		if(x == 0 || x == 1) {
			return 1;
		}
		while(x > 0) {
			temp = x % 2;
			if(temp == 1) {
				numberOfOnes++;
			}
			else {
				numberOfZeros++;
			}
			
			binary = binary + "" + temp;
			x = x / 2;
		}
		if(numberOfOnes >= numberOfZeros) {
			return numberOfOnes;
		}
		else {
			return numberOfZeros;
		}
	}
}