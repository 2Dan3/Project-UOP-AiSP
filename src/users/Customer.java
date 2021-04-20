package users;

import java.util.Scanner;

public class Customer extends User {

	void orderViaPhone() {
		// the request inputs
	}
	
	void rateTheDriver(Driver currentDriver) {
		
		Scanner starsInput = new Scanner(System.in);
		int starsGiven = starsInput.nextInt();
		starsInput.close();
		
		calcNewAvg(starsGiven, currentDriver); // Poziv f-je iz 1 od menija u Program.java ?
	}
	
	
}
