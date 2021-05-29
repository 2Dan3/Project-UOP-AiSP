package main;

import java.util.ArrayList;
import java.util.Scanner;

public class Program {
	
	public static Object currentUser = null;
	
	private static Object login() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Ja sam: ");
		System.out.println("1) Klijent \n2) Vozac \n3) Dispecer");
		int roleNum = sc.nextInt();
		sc.nextLine();
		
/*	*/		String[] roles = new String[] {"Customer.csv","Driver.csv","Dispatcher.csv"};
		
		
		System.out.println("Korisnicko ime >> ");
		String typedUsername = sc.nextLine();
		
		System.out.println("Lozinka >> ");
		String typedPassword = sc.nextLine();
		
/*	*/		ArrayList <Object> usersList = loadInRoleUsers(roles[roleNum - 1] );
		
		for (Object user : usersList) {
			if (user.getUsername == typedUsername && user.getPassword() == typedPassword) {
				
				System.out.println("Uspesno ste se prijavili na sistem...");
				return user;
			}
/*	*/		}System.out.println("Login podaci nisu validni!");
	}
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
/*	*/		if ( (user = login()) instanceof Driver) {
		Driver dr = (Driver)user;
}else if (user instanceof Dispatcher) {
		Dispatcher disp = (Dispatcher)user;
}else {
	Customer cust = (Customer)user;
}
		user.showMainMenu();

	}

}
