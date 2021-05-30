package entities;

import java.util.Scanner;

public class PomocnaKlasa {

	static Scanner sc = new Scanner(System.in);
	
		
	// int input reading
	public static int ocitajCeoBroj(){
		while (sc.hasNextInt()==false) {
			System.out.println("Gre\u0161ka, nepoznata vrednost");
			sc.nextLine();
		}
		int ceoBroj = sc.nextInt();
		sc.nextLine(); //cisti sve sa ulaza sto nije broj ili ostatak texta posle broja
		return ceoBroj;
	}

}
