package entities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import javax.swing.JFrame;

public class Dispatcher extends Employee {

	static ArrayList<Dispatcher> allDispatchers = new ArrayList<Dispatcher>();
	private int phoneLineNum;
    private Department dept;
    
    public Dispatcher() {
    	super();
    	
		this.phoneLineNum = 0;
		this.dept = Department.REQUESTS;
    }
    
    
    public Dispatcher(String username, String password, String name, String lastName, int jmbg, Gender gender,
    		String phone, String address, double salary, int phoneLineNum, Department department, boolean deleted) {
		super(username, password, name, lastName, jmbg, gender, phone, address, salary, deleted);

		this.phoneLineNum = phoneLineNum;
		this.dept = department;
	}
    
    
    
    public static void showAllDispatchers() {
    	for (Dispatcher d : allDispatchers) {
    		System.out.println(d);
    	}
    }
    
    public static void addNewDispatcher() {
    	Scanner sc = new Scanner(System.in);
    	
	    	System.out.println("Postavite korisnicko ime >> ");
		    	String username = sc.nextLine();
	    	System.out.println("Postavite lozinku >> ");
		    	String password = sc.nextLine();
	    	System.out.println("Ime >> ");
		    	String name = sc.nextLine();
	    	System.out.println("Prezime >> ");
		    	String lastName = sc.nextLine();
	    	System.out.println("JMBG >> ");
		    	int jmbg = Integer.parseInt(sc.nextLine());
	    	System.out.println("Pol [0 - ZENSKI   1 - MUSKI] >> ");
		    	int g = Integer.parseInt(sc.nextLine());
		    	Gender gender = Gender.values()[g];
	    	System.out.println("Telefon >> ");
		    	String phone = sc.nextLine();
	    	System.out.println("Adresa >> ");
		    	String address = sc.nextLine();
	    	System.out.println("Plata >> ");
	    		double salary = Double.parseDouble(sc.nextLine());
			System.out.println("Broj telefonske linije >> ");
				int phoneLineNum = Integer.parseInt(sc.nextLine());
			System.out.println("Odeljenje na kom radi [0 - REKLAMACIJE    1 - PORUCIVANJE VOZNJI] >> ");
				int d = Integer.parseInt(sc.nextLine());
				Department department = Department.values()[d];
			boolean deleted = false;
    	sc.close();
    	
    	allDispatchers.add(new Dispatcher(username, password, name, lastName, jmbg, gender, phone, address, salary, phoneLineNum, department, deleted));
    	
    }
    
    public static void editDispatcher() {
		System.out.println("Izmeniti dispecera [JMBG] >> ");
		
		Scanner sc = new Scanner(System.in);
		int jmbg = Integer.parseInt(sc.nextLine());
		
    	for (int i = 0; i < allDispatchers.size(); i++) {
			if (allDispatchers.get(i).getJmbg() == jmbg && !allDispatchers.get(i).isDeleted() ) {
				
				Dispatcher thisDispatcher = allDispatchers.get(i);
				
				System.out.println("Postavite korisnicko ime >> ");
					thisDispatcher.setUsername(sc.nextLine());
		    	System.out.println("Postavite lozinku >> ");
		    		thisDispatcher.setPassword(sc.nextLine());
		    	System.out.println("Ime >> ");
		    		thisDispatcher.setName(sc.nextLine());
		    	System.out.println("Prezime >> ");
		    		thisDispatcher.setLastName(sc.nextLine());
		    	System.out.println("Pol [0 - ZENSKI   1 - MUSKI] >> ");
		    		thisDispatcher.setGender(Gender.values()[Integer.parseInt(sc.nextLine())]);
		    	System.out.println("Telefon >> ");
		    		thisDispatcher.setPhoneNum(sc.nextLine());
		    	System.out.println("Adresa >> ");
		    		thisDispatcher.setAddress(sc.nextLine());
		    	System.out.println("Plata >> ");
		    		thisDispatcher.setSalary(Double.parseDouble(sc.nextLine()));
		    	System.out.println("Broj telefonske linije >> ");
		    		thisDispatcher.setPhoneLineNum(Integer.parseInt(sc.nextLine()));
		    	System.out.println("Odeljenje na kom radi [0 - REKLAMACIJE    1 - PORUCIVANJE VOZNJI] >> ");
		    		thisDispatcher.setDept(Department.values()[Integer.parseInt(sc.nextLine())]);
				
				sc.close();
				saveDispatchers("Dispatchers.csv");
				break;
			}
		}	
	}
    public static void deleteDispatcher() {

    	System.out.println("Obrisati dispecera [JMBG] >> ");
		
		Scanner sc = new Scanner(System.in);
		int jmbg = Integer.parseInt(sc.nextLine());
		
    	for (int i = 0; i < allDispatchers.size(); i++) {
			if (allDispatchers.get(i).getJmbg() == jmbg && !allDispatchers.get(i).isDeleted() ) {
				allDispatchers.get(i).setDeleted(true);
				saveDispatchers("Dispatchers.csv");
				break;
			}
		}
	}
    


	public static void dispatcherCRUDMenu() {
		
    	Scanner input = new Scanner(System.in);
    	int cmd = -1;
    	while(cmd != 0) {
    		System.out.println("\n---------------------------------");
    		System.out.println("1) Izmeni postojeceg dispecera");
    		System.out.println("2) Dodaj novog dispecera");
    		System.out.println("3) Obrisi dispecera");
    		System.out.println("0) Nazad \n");
    		
    		cmd = PomocnaKlasa.ocitajCeoBroj();
    		
    		switch (cmd) {
	    		case 0: 
	    			System.out.println("Dovi\u0111enja...");
	    			break;
	    		case 1: 
	    			showAllDispatchers();
	    			editDispatcher();
	    			break;
	    		case 2: 
	    			addNewDispatcher();
	    			break;
	    		case 3:
	    			showAllDispatchers();
	    			deleteDispatcher();
	    			break;
	    		
	    		default:
	    			System.out.println("Gre\u0161ka, nepoznata komanda: " +cmd);
    		}
    		    		
    	}
    	input.close();
    }
    
    public static void showDispatcherMenu(JFrame frame) {
    	
    	int cmd = -1;
    	while (cmd != 0) {
    		
    		System.out.println("\n---------------------------------");
    		System.out.println("1) Taxi sluzba - prikaz i izmena");
    		System.out.println("2) Pretraga");
    		System.out.println("3) Automobili");
    		System.out.println("4) Vozaci");
    		System.out.println("5) Voznje");
    		System.out.println("6) Izvestaji");
    		System.out.println("7) Dispeceri");
    		System.out.println("0) Izlaz \n");
    		
    		cmd = PomocnaKlasa.ocitajCeoBroj();
    		
    		switch (cmd) {
	    		case 0: 
	    			System.out.println("Dovi\u0111enja...");
	    			frame.dispose();
	    			break;
	    		case 1: 
	    			System.out.println("Nije u funkciji za kt2");
	    			break;
	    		case 2: 
	    			System.out.println("Nije u funkciji za kt2");
	    			break;
	    		case 3: 
	    			System.out.println("Nije u funkciji za kt2");
	    			break;
	    		case 4: 
	    			Driver.driverCRUDMenu();
	    			break;
	    		case 5:
	    			
	    			break;
	    		case 6:
	    			System.out.println("Nije u funkciji za kt2");
	    			break;
	    		case 7:
	    			dispatcherCRUDMenu();
	    			break;
	    		
	    		default:
	    			System.out.println("Gre\u0161ka, nepoznata komanda: " +cmd);
    		}
    		    		
    	}
    }
    
    


	// GETTERS & SETTERS
    
    public ArrayList<Dispatcher> getAllDispatchers() {
		return allDispatchers;
	}

    
    
	public int getPhoneLineNum() {
		return phoneLineNum;
	}


	public void setPhoneLineNum(int phoneLineNum) {
		this.phoneLineNum = phoneLineNum;
	}


	public Department getDept() {
		return dept;
	}


	public void setDept(Department dept) {
		this.dept = dept;
	}


	@Override
	public String toString() {
		return "Dispatcher [phoneLineNum=" + phoneLineNum + ", dept=" + dept + ", salary=" + salary + ", username="
				+ username + ", password=" + password + ", name=" + name + ", lastName=" + lastName + ", jmbg=" + jmbg
				+ ", gender=" + gender + ", phoneNum=" + phoneNum + ", address=" + address + "]";
	}

	
/*
    private void addDriver() {
        // TODO implement here
        return;
    }

    private void updateDriver() {
        // TODO implement here
        return;
    }

    private void addVehicle() {
        // TODO implement here
        return;
    }

    private void updateVehicle() {
        // TODO implement here
        return;
    }

    private void showTaxiService() {
        // TODO implement here
        return;
    }

    private void updateTaxiService() {
        // TODO implement here
        return;
    }

    private void searchDriver() {
        // TODO implement here
        return;
    }

    private void searchVehicle() {
        // TODO implement here
        return;
    }

    private void showRides() {
        // TODO implement here
        return;
    }

    private void assignToDriver() {
        // TODO implement here
        return;
    }

    private void showDriverReport() {
        // TODO implement here
        return;
    }

    private void showSumReport() {
        // TODO implement here
        return;
    }
*/    
    
    
    
    // FILE IO
    
    public void loadInDispatchers(String filename) {
    	
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
				double salary = Double.parseDouble(split[8]);
				int phoneLineNum = Integer.parseInt(split[9]);
				Department dept = Department.values()[Integer.parseInt(split[10])];
				boolean deleted = Boolean.parseBoolean(split[11]);
				
				Dispatcher dispatcher = new Dispatcher(username, password, name, lastName, jmbg, gender, phoneNum, address, salary, phoneLineNum, dept, deleted);
				allDispatchers.add(dispatcher);
				
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom citanja podataka o dispecerima.");
			e.printStackTrace();
		}
	}
    
    
    public static void saveDispatchers(String filename) {
    	
    	String sp = System.getProperty("file.separator");
    	
		try {
			File file = new File("src" + sp + "dataFiles" + sp + filename);

			String content = "";
			for (Dispatcher dp: allDispatchers) {
				content += dp.getUsername() + "|" + dp.getPassword() + "|" + dp.getName() + "|" + dp.getLastName()+ "|" + dp.getJmbg() + "|"
						+ dp.getGender() + "|" + dp.getPhoneNum() + "|" + dp.getAddress() + "|" + dp.getSalary() + "|" + dp.getPhoneLineNum() + "|" + dp.getDept() + "|" + dp.isDeleted() +"\n";
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(content);
			writer.close();
			
		} catch (IOException e) {
			System.out.println("Greska prilikom upisivanja dispecera.");
		}
	}

}