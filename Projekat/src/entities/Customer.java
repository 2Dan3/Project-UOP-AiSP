package entities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Customer extends User {

	ArrayList<Customer> allCustomers = new ArrayList<Customer>();
	
    public Customer() {
    	super();
    }
    
    public Customer(String username, String password, String name, String lastName, int jmbg, Gender gender,
			String phoneNum, String address, boolean deleted) {
    	super( username, password, name, lastName, jmbg, gender,
    		 phoneNum, address, deleted);
    }
    

    
    
    @Override
	public String toString() {
		return "Customer [korisnickoIme=" + username + ", lozinka=" + password + ", ime=" + name + ", prezime=" + lastName
				+ ", jmbg=" + jmbg + ", pol=" + gender + ", telefon=" + phoneNum + ", adresa=" + address + "]";
	}


	private boolean requestRide(String start, String destination, RequestType requestType, String additionalNotice) {
        // TODO implement here
        return false;
    }

    private void requestRecords() {
        // TODO implement here
        return;
    }
    
    
    
    
    
    public void showAllCustomers() {
    	for (Customer c : allCustomers) {
    		System.out.println(c);
    	}
    }
    
    public void showCustomerMenu() {
    	
    	Scanner input = new Scanner(System.in);
    	int cmd = -1;
    	do {
    		System.out.println("\n---------------------------------");
    		System.out.println("1) Naruci voznju telefonom");
    		System.out.println("2) Naruci voznju aplikacijom");
    		System.out.println("0) Izlaz \n");
    		
    		cmd = Integer.parseInt(input.nextLine());
    		
    		switch (cmd) {
	    		case 0: 
	    			System.out.println("Dovi\u0111enja...");
	    			break;
	    		case 1: 
	    			
	    			break;
	    		case 2:
	    			System.out.println("Nije u funkciji za kt2");
	    			break;
	    			
	    		default:
	    			System.out.println("Gre\u0161ka, nepoznata komanda: " +cmd);
    		}
    		    		
    	} while(cmd != 0);
    	input.close();
    	
    		
    }
    
    
    
    // GETTERS & SETTERS
    
    public ArrayList<Customer> getAllCustomers() {
		return allCustomers;
	}

    
	
    // FILE IO
    
    
    public void loadInCustomers(String filename) {
    	
    	String sp = System.getProperty("file.separator");
    	
		try {
			File file = new File("src" + sp + "dataFiles" + sp + filename);

			BufferedReader reader = new BufferedReader(new FileReader(file));
			
			String row;
			while ((row = reader.readLine()) != null) {
				
				String[] split = row.split("\\|");
				
				
				String username = split[0];
				String password = split[1];
				String name= split[2];
				String lastName= split[3];
				int jmbg = Integer.parseInt(split[4]);
				Gender gender = Gender.values()[Integer.parseInt(split[5])];
				String phoneNum = split[6];
				String address = split[7];
				boolean deleted = Boolean.parseBoolean(split[8]);
				
				Customer customer = new Customer(username, password, name, lastName, jmbg, gender, phoneNum, address, deleted);
				allCustomers.add(customer);

			}
			reader.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom citanja podataka o klijentima.");
			e.printStackTrace();
		}
	}


	public void saveCustomers(String filename) {
    	
    	String sp = System.getProperty("file.separator");
    	
		try {
			File file = new File("src" + sp + "dataFiles" + sp + filename);
		
			String content = "";
			for (Customer customer: allCustomers) {
				content +=
						  customer.getUsername() + "|" + customer.getPassword() + "|"
						+ customer.getName() + "|" + customer.getLastName()+ "|" + customer.getJmbg() + "|"
						+ customer.getGender().ordinal() + "|" + customer.getPhoneNum() + "|" + customer.getAddress() + "|" + customer.isDeleted() +"\n";
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(content);
			writer.close();
			
		} catch (IOException e) {
			System.out.println("Greska prilikom upisivanja klijenata.");
		}
	}

}