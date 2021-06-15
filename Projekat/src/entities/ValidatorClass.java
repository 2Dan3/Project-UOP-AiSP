package entities;

import java.util.Scanner;

import javax.swing.JOptionPane;

public class ValidatorClass {

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
	
	// split polja po 'space', 'for' petlja kroz duzinu nastalog niza i za svakoj reci je uvecano 1. slovo, kom se zatim dodaje i ostatak reci
	public static String capitalize(String s) {
		
		String[] singleWords = s.split(" ");
		String capitalized = "";
		
		for (int i = 0; i < singleWords.length; i++) {
			
			String newWord = singleWords[i].substring(0, 1).toUpperCase() + singleWords[i].substring(1, singleWords[i].length()).toLowerCase();
			capitalized += " " + newWord;
		}
		return capitalized.trim();
	}
	
	public static int validateFields(String username, String pass, String name, String lastName, long jmbg, String ph, String address) {
		
		String warningMsg = "";
		int errors = 0;
		
		if(name.isBlank()) {
			warningMsg += "- Unesite ime\n";
			errors++;
		}
		if(lastName.isBlank()) {
			warningMsg += "- Unesite prezime\n";
			errors++;
		}
		if(username.isBlank()) {
			warningMsg += "- Unesite korisni\u016Dko ime\n";
			errors++;
		}/*else if(prodavac == null){
			String korisnickoIme = txtKorisnickoIme.getText().trim();
			Prodavac pronadjeni = prodavnica.nadjiProdavca(korisnickoIme);
			if(pronadjeni != null) {
				warningMsg += "- Prodavac sa tim korisnickim imenom vec postoji\n";
				errors++;
			}
		}*/
		
		if(pass.isBlank()) {
			warningMsg += "- Unesite lozinku\n";
			errors++;
		}//TODO Change from 10 to 13
		if(String.valueOf(jmbg).length() != 10) {
			warningMsg += "- JMBG mora imati 10 cifara\n";
			errors++;
		}
		if(ph.isBlank()) {
			warningMsg += "- Unesite broj telefona\n";
		}else {
			int maxDigits = 10;
			
			if(ph.charAt(0) == '+') {
				maxDigits = 13;
			}
			if(ph.length() > maxDigits || ph.length() < 6) {
				warningMsg += "- Preduga\u016Dak ili prekratak broj\n";
				errors++;
			}
		}
		if(!Character.isDigit(address.charAt(address.length()-1))) {
			warningMsg += "- Dodajte broj na unetu adresu\n";
			errors++;
		}
		
		if(errors > 0) {
			String warningTitle = "Prona\u0111eno gre\u0161aka: " + errors;
			warningMsg += "Potrebno je da ispravite gre\u0161ke:\n";
			JOptionPane.showMessageDialog(null, warningMsg, warningTitle, JOptionPane.WARNING_MESSAGE);
		}
		
		return errors;
	}
	

}
