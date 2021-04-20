package users;

import java.util.Scanner;

public abstract class User {
	protected String username;
	protected String password;
	protected String name;
	protected String lastName;
	protected int jmbg;
	protected String address;
	protected Gender gender;
	protected int phone;
	
	enum Gender {
		ZENSKI,
		MUSKI
	}

	public void setGender() {
		
		boolean error = true;
		do {
			
			System.out.println("1) Zenski\n2)Muski");
			
			Scanner input = new Scanner(System.in);
			try {
			System.out.println("Molimo izaberite pol osobe [1 / 2] >> ");
			
			int value = input.nextInt();
			
			input.close();
			
			switch(value) {
			case 1:
			  this.gender = Gender.ZENSKI;
			  break;
			case 2:
			   this.gender = Gender.MUSKI;
			  break;
			//default:
				//System.out.println("");
				//this.setGender();
			    //break;
						};
						
			}catch(NumberFormatException exception) {
				error = false;
				throw new NumberFormatException("Uneli ste pogresan tip vrednosti.\nPokusajte ponovo:");
			}
			
		}while (error == false);
	}
	
	
}