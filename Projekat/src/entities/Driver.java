package entities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Driver extends Employee implements CRUDOps {
	
	ArrayList<Driver> allDrivers = new ArrayList<Driver>();
	
    public Driver() {
    	super();
    	this.id = createNewID();
    	this.membershipCardNum = "";
		this.vehicle = new Vehicle();
		this.driverStatus = DriverStatus.NONACTIVE;
    }
    
    public Driver(int id, String username, String password, String name, String lastName, int jmbg, Gender gender,
			String phoneNum, String address, String membershipCardNum, Vehicle vehicle, DriverStatus driverStatus, double salary) {
		super(username, password, name, lastName, jmbg, gender,
				 phoneNum, address, salary);
		this.id = id;
		this.membershipCardNum = membershipCardNum;
		this.vehicle = vehicle;
		this.driverStatus = driverStatus;
	}
    
    // GETTERS & SETTERS
    
    public ArrayList<Driver> getAllDrivers() {
		return allDrivers;
	}

	public void setAllDrivers(ArrayList<Driver> allDrivers) {
		this.allDrivers = allDrivers;
	}
	
    public int getId() {
    	return id;
    }
    
    public void setId(int id) {
    	this.id = id;
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


	private int id;

	private String membershipCardNum;

    private Vehicle vehicle;

    private DriverStatus driverStatus;

    //public Vehicle driver;


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
    
    public void showAll() {
    	
    	for (Driver dr : allDrivers) {
    		System.out.println(dr);
    	}
    	
    }
    
    // // //
    @Override
    public void showUserMenu() {
    	
    	Scanner input = new Scanner(System.in);
    	int cmd = -1;
    	do {
    		System.out.println("\n1) Moje vo\u017Enje");
    		System.out.println("2) Dodeljene vo\u017Enje");
    		System.out.println("3) Na\u0111i novu vo\u017Enju");
    		System.out.println("4) Moja statistika");
    		System.out.println("x) Izlaz");
    		
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
	    			
	    			break;
	    		case 4: 
	    			
	    			break;
	    		
	    		default:
	    			throw new IllegalArgumentException("Gre\u0161ka, nepoznata komanda: " + cmd);
    		}
    		    		
    	} while(cmd != 0);
    	input.close();
    	
    		
    }
    
    
    // FILE IO
    
    
    public void loadInDrivers(String filename) {
    	
    	String sp = System.getProperty(File.separator);
    	
		try {
			File file = new File("src" + sp + "dataFiles" + sp + filename);
			BufferedReader reader = new BufferedReader(new FileReader(file));
			
			String row;
			while ((row = reader.readLine()) != null) {
				
				String[] split = row.split("\\|");
				
				int id = Integer.parseInt(split[0]);
				String username = split[1];
				String password = split[2];
				String name= split[3];
				String lastName= split[4];
				int jmbg = Integer.parseInt(split[5]);
				Gender gender = Gender.values()[Integer.parseInt(split[6])];
				String phoneNum = split[7];
				String address = split[8];
				double salary = Double.parseDouble(split[9]);
				String membershipCardNum = split[10];
/*	*/				Vehicle vehicle = new Vehicle(split[11]);
				DriverStatus driverStatus = DriverStatus.values()[Integer.parseInt(split[12])];
				
				Driver driver = new Driver(id, username, password, name, lastName, jmbg, gender, phoneNum, address, membershipCardNum, vehicle, driverStatus, salary);
				allDrivers.add(driver);
				
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom citanja podataka o vozacima.");
			e.printStackTrace();
		}
	}
    


	public void saveDrivers(String filename) {
    	
    	String sp = System.getProperty(File.separator);
    	
		try {
			File file = new File("src" + sp + "dataFiles" + sp + filename);
			String content = "";
			for (Driver driver: allDrivers) {
				content += driver.getId() + "|"	+ driver.getUsername() + "|" + driver.getPassword() + "|"
						+ driver.getName() + "|" + driver.getLastName()+ "|" + driver.getJmbg() + "|"
						+ driver.getGender().ordinal() + "|" + driver.getPhoneNum() + "|" + driver.getAddress() + "|"
						+ driver.getSalary() + "|" + driver.getMembershipCardNum() + "|" + driver.getVehicle() + "|"
						+ driver.getDriverStatus().ordinal() +"\n";
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(content);
			writer.close();
			
		} catch (IOException e) {
			System.out.println("Greska prilikom upisivanja vozaca.");
		}
	}

}