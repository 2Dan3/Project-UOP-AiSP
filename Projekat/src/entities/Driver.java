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
    private long drivingLicence;
    
    public Driver() {
    	super();
    	this.membershipCardNum = "";
		this.vehicle = null;
		this.driverStatus = DriverStatus.values()[1];
		this.drivingLicence = 0;
    }
    
    public Driver(String username, String password, String name, String lastName, long jmbg, Gender gender,
			String phoneNum, String address, String membershipCardNum, Vehicle vehicle, DriverStatus driverStatus, double salary, boolean deleted, long drivingLicence) {
		super(username, password, name, lastName, jmbg, gender,
				 phoneNum, address, salary, deleted);

		this.membershipCardNum = membershipCardNum;
		this.vehicle = vehicle;
		this.driverStatus = driverStatus;
		this.drivingLicence = drivingLicence;
	}
    
    // GETTERS & SETTERS
    
    public long getDrivingLicence() {
		return drivingLicence;
	}

	public void setDrivingLicence(long drivingLicence) {
		this.drivingLicence = drivingLicence;
	}

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

    
   /* public static void driverCRUDMenu() {
    	
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
    }*/
    
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
    
    
    

	

}