package entities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Driver extends Employee {
	
	static ArrayList<Driver> allDrivers = new ArrayList<Driver>();
	private String membershipCardNum;
    private Vehicle vehicle;
    private DriverStatus driverStatus;
    
    public Driver() {
    	super();
    	this.membershipCardNum = "";
		this.vehicle = null;
		this.driverStatus = DriverStatus.NONACTIVE;
    }
    
    public Driver(String username, String password, String name, String lastName, String jmbg, Gender gender,
			String phoneNum, String address, String membershipCardNum, Vehicle vehicle, DriverStatus driverStatus, double salary, boolean deleted) {
		super(username, password, name, lastName, jmbg, gender,
				 phoneNum, address, salary, deleted);

		this.membershipCardNum = membershipCardNum;
		this.vehicle = vehicle;
		this.driverStatus = driverStatus;
	}
    
    // GETTERS & SETTERS
    
    public ArrayList<Driver> getAllDrivers() {
		return allDrivers;
	}

    
	public String getMembershipCardNum() {
		return membershipCardNum;
	}

	public void setMembershipCardNum(String membershipCardNum) {
		this.membershipCardNum = membershipCardNum;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public DriverStatus getDriverStatus() {
		return driverStatus;
	}

	public void setDriverStatus(DriverStatus driverStatus) {
		this.driverStatus = driverStatus;
	}
	

	@Override
	public String toString() {
		return "Driver [brojClanskeKarte=" + membershipCardNum + ", vozilo=" + vehicle + ", statusVozaca="
				+ driverStatus + ", plata=" + salary + ", korisnickoIme=" + username + ", lozinka=" + password + ", ime="
				+ name + ", prezime=" + lastName + ", jmbg=" + jmbg + ", pol=" + gender + ", telefon=" + phoneNum
				+ ", adresa=" + address + "]";
	}


	// Driver-related f-je
    /*
    private void assignVehicleToDriver() {
		// TODO Auto-generated method stub	
	}
    

    private void previousRides() {
        // TODO implement here
        return;
    }

    private void showAppRequests() {
        // TODO implement here
        return;
    }

    private void finishRide() {
        // TODO implement here
        return;
    }

    private void ridesSummary() {
        // TODO implement here
        return;
    }

    private void applyToAuction() {
        // TODO implement here
        return;
    }
*/    
    public static void showAllDrivers() {
    	
    	for (Driver dr : allDrivers) {
    		System.out.println(dr);
    	}
    	
    }
    
    private Vehicle findVehicle(int vin) {

    	for(Vehicle v: Vehicle.allVehicles) {
    		if(v.getVINNum() == vin)
    			return v;
    	}return null;
	}
    
    public static void addNewDriver() {
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
		    	String jmbg = sc.nextLine();
	    	System.out.println("Pol [0 - ZENSKI   1 - MUSKI] >> ");
		    	int g = Integer.parseInt(sc.nextLine());
		    	Gender gender = Gender.values()[g];
	    	System.out.println("Telefon >> ");
		    	String phone = sc.nextLine();
	    	System.out.println("Adresa >> ");
		    	String address = sc.nextLine();
	    	System.out.println("Plata >> ");
	    		double salary = Double.parseDouble(sc.nextLine());
			System.out.println("Broj clanske karte >> ");
				String membershipCardNum = sc.nextLine();
// **za sad bez CRUD za automobile**			System.out.println("Dodeliti automobil odmah?  [0 - NE    1 - DA] >> ");
//				int confirmation = Integer.parseInt(sc.nextLine());
//				if (confirmation == 1) {
//					assignVehicleToDriver();
//				}
			Vehicle vehicle = null;
			System.out.println("Status vozaca  [0 - Neaktivan    1 - Aktivan] >> ");
				DriverStatus status = DriverStatus.values()[Integer.parseInt(sc.nextLine())];
			boolean deleted = false;
    	sc.close();
    	
    	allDrivers.add(new Driver(username, password, name, lastName, jmbg, gender, phone, address, membershipCardNum, vehicle, status, salary, deleted));
    	saveDrivers("Drivers.csv");
    	
    }
    
    public static void editDriver() {
		System.out.println("Izmeniti vozaca [broj clanske karte] >> ");
		
		Scanner sc = new Scanner(System.in);
		String memberCardNum = sc.nextLine();
		
    	for (int i = 0; i < allDrivers.size(); i++) {
			if (allDrivers.get(i) != null && allDrivers.get(i).getMembershipCardNum().equals(memberCardNum) && !allDrivers.get(i).isDeleted() ) {
				
				Driver thisDriver = allDrivers.get(i);
				
				System.out.println("Postavite korisnicko ime >> ");
					thisDriver.setUsername(sc.nextLine());
		    	System.out.println("Postavite lozinku >> ");
		    		thisDriver.setPassword(sc.nextLine());
		    	System.out.println("Ime >> ");
		    		thisDriver.setName(sc.nextLine());
		    	System.out.println("Prezime >> ");
		    		thisDriver.setLastName(sc.nextLine());
		    	System.out.println("Pol [0 - ZENSKI   1 - MUSKI] >> ");
		    		thisDriver.setGender(Gender.values()[Integer.parseInt(sc.nextLine())]);
		    	System.out.println("Telefon >> ");
		    		thisDriver.setPhoneNum(sc.nextLine());
		    	System.out.println("Adresa >> ");
		    		thisDriver.setAddress(sc.nextLine());
		    	System.out.println("Plata >> ");
		    		thisDriver.setSalary(Double.parseDouble(sc.nextLine()));
		    	Vehicle.showAll();
				System.out.println("Unesite VIN [broj sasije] novog automobila >> ");
					int vinNum = Integer.parseInt(sc.nextLine());
					
					for (Vehicle v : Vehicle.getAllVehicles()) {
						if ( !v.isDeleted() && v.getHasDriver()==false && v.getVINNum() == vinNum ) {
							thisDriver.setVehicle(v);
							v.setHasDriver(true);
							break;
						}
					}
					
				System.out.println("Status vozaca  [0 - Neaktivan    1 - Aktivan] >> ");
				thisDriver.setDriverStatus(DriverStatus.values()[Integer.parseInt(sc.nextLine())]);
				sc.close();
				saveDrivers("Drivers.csv");
				break;
			}
		}	
	}
    public static void deleteDriver() {

    	System.out.println("Obrisati vozaca [broj clanske karte] >> ");
		
		Scanner sc = new Scanner(System.in);
		String memberCardNum = sc.nextLine();
		
    	for (int i = 0; i < allDrivers.size(); i++) {
			if (allDrivers.get(i) != null && allDrivers.get(i).getMembershipCardNum().equals(memberCardNum) && !allDrivers.get(i).isDeleted() ) {
				allDrivers.get(i).setDeleted(true);
				saveDrivers("Drivers.csv");
				sc.close();
				break;
			}
		}
	}
    
    public static void driverCRUDMenu() {
    	
    	int cmd = -1;
    	while(cmd != 0) {
    		
    		System.out.println("\n---------------------------------");
    		System.out.println("1) Izmeni postojeceg vozaca");
    		System.out.println("2) Dodaj novog vozaca");
    		System.out.println("3) Obrisi vozaca");
    		System.out.println("0) Nazad \n");
    		
    		cmd = PomocnaKlasa.ocitajCeoBroj();
    		
    		switch (cmd) {
    			
	    		case 1: 
	    			Driver.showAllDrivers();
	    			Driver.editDriver();
	    			break;
	    		case 2: 
	    			Driver.addNewDriver();
	    			break;
	    		case 3: 
	    			Driver.showAllDrivers();
	    			Driver.deleteDriver();
	    			break;
	    		
	    		default:
	    			System.out.println("Gre\u0161ka, nepoznata komanda: " +cmd);
    		}
    		    		
    	}
    }
    
    // // //
    public static void showDriverMenu() {
    	
    	Scanner input = new Scanner(System.in);
    	int cmd = -1;
    	do {
    		System.out.println("\n---------------------------------");
    		System.out.println("1) Moje vo\u017Enje");
    		System.out.println("2) Dodeljene vo\u017Enje");
    		System.out.println("3) Na\u0111i novu vo\u017Enju");
    		System.out.println("4) Moja statistika");
    		System.out.println("0) Izlaz \n");
    		
    		cmd = Integer.parseInt(input.nextLine());
    		
    		switch (cmd) {
	    		case 0: 
	    			System.out.println("Dovi\u0111enja...");
	    			break;
	    		case 1: 
	    			
	    			break;
	    		case 2: 
	    			
	    			break;
	    		case 3: 
	    			System.out.println("Nije u funkciji za kt2");
	    			break;
	    		case 4: 
	    			System.out.println("Nije u funkciji za kt2");
	    			break;
	    		
	    		default:
	    			System.out.println("Gre\u0161ka, nepoznata komanda: " +cmd);
    		}
    		    		
    	} while(cmd != 0);
    	input.close();
    	
    		
    }
    
    
    // FILE IO
    
    
    

	public void loadInDrivers(String filename) {
    	
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
				String jmbg = split[4];
				Gender gender = Gender.values()[Integer.parseInt(split[5])];
				String phoneNum = split[6];
				String address = split[7];
				double salary = Double.parseDouble(split[8]);
				String membershipCardNum = split[9];
/*	*/				Vehicle vehicle = findVehicle(Integer.parseInt(split[10]));
				DriverStatus driverStatus = DriverStatus.values()[Integer.parseInt(split[11])];
				boolean deleted = Boolean.parseBoolean(split[12]);
				
				Driver driver = new Driver(username, password, name, lastName, jmbg, gender, phoneNum, address, membershipCardNum, vehicle, driverStatus, salary, deleted);
				allDrivers.add(driver);
				
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom citanja podataka o vozacima.");
			e.printStackTrace();
		}
	}
    


	

	public static void saveDrivers(String filename) {
    	
    	String sp = System.getProperty("file.separator");
    	
		try {
			File file = new File("src" + sp + "dataFiles" + sp + filename);
			String content = "";
			for (Driver driver: allDrivers) {
				content += driver.getUsername() + "|" + driver.getPassword() + "|"
						+ driver.getName() + "|" + driver.getLastName()+ "|" + driver.getJmbg() + "|"
						+ driver.getGender().ordinal() + "|" + driver.getPhoneNum() + "|" + driver.getAddress() + "|"
						+ driver.getSalary() + "|" + driver.getMembershipCardNum() + "|" + driver.getVehicle().getVINNum() + "|"
						+ driver.getDriverStatus().ordinal() + "|" + driver.isDeleted() +"\n";
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(content);
			writer.close();
			
		} catch (IOException e) {
			System.out.println("Greska prilikom upisivanja vozaca.");
		}
	}

}