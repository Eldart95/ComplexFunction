package Ex1;

import java.util.Comparator;

public class Monom_Comperator implements Comparator<Monom> {

	public Monom_Comperator() {
		
	}
	public int compare(Monom o1, Monom o2) {
		int dp = o2.get_power() - o1.get_power();
		if(dp == 0) {
			return 1;
		}
		else {
			return 0;
		}
		
	}

	// ******** add your code below *********

}
