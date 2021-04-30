
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Driver extends Employee {
	
	ArrayList<Driver> allDrivers = new ArrayList<Driver>();
	
    public Driver() {
    	super();
    	this.membershipCardNum = "";
		this.vehicle = new Vehicle();
		this.driverStatus = DriverStatus.NONACTIVE;
    }
    
    public Driver(String membershipCardNum, Vehicle vehicle, DriverStatus driverStatus) {
		super();
		this.membershipCardNum = membershipCardNum;
		this.vehicle = vehicle;
		this.driverStatus = driverStatus;
	}
    
    // GETTERS & SETTERS

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
		return "Driver [membershipCardNum=" + membershipCardNum + ", vehicle=" + vehicle + ", driverStatus="
				+ driverStatus + ", salary=" + salary + ", username=" + username + ", password=" + password + ", name="
				+ name + ", lastName=" + lastName + ", jmbg=" + jmbg + ", gender=" + gender + ", phoneNum=" + phoneNum
				+ ", address=" + address + "]";
	}




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
    
    
    // FILE IO
    
    
    public void loadInDrivers(String filename) {
    	
    	String sp = System.getProperty(File.separator);
    	
		try {
			File file = new File("src" + sp + "fajlovi" + sp + "Drivers.csv");
			BufferedReader reader = new BufferedReader(new FileReader(file));
			
			String row;
			while ((row = reader.readLine()) != null) {
				
				String[] split = row.split("\\|");
				
				
				String membershipCardNum = split[0];
				Vehicle vehicle = split[1];
				DriverStatus driverStatus = DriverStatus[split[2]];
				double salary = Double.parseDouble(split[3]);
				String username = split[4];
				String password = split[5];
				String name= split[6];
				String lastName= split[7];
				int jmbg = Integer.parseInt(split[8]);
				Gender gender = split[9];
				String phoneNum = split[10];
				String address = split[11];
				
				Driver driver = new Driver(membershipCardNum, vehicle, driverStatus);
				allDrivers.add(driver);
				
				// za proveru
				System.out.println(allDrivers);
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
			File file = new File("src" + sp + "fajlovi" + sp + "Drivers.csv");
			String content = "";
			for (Driver driver: allDrivers) {
				content += driver.getMembershipCardNum() + "|" + driver.getVehicle() + "|"
						+ driver.getDriverStatus() + "|" + driver.getSalary() + "|"
						+ driver.getUsername() + "|" + driver.getPassword() + "|"
						+ driver.getName() + "|" + driver.getLastName()+ "|" + driver.getJmbg() + "|"
						+ driver.getGender() + "|" + driver.getPhoneNum() + "|" + driver.getAddress() +"\n";
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(content);
			writer.close();
			
		} catch (IOException e) {
			System.out.println("Greska prilikom upisivanja vozaca.");
		}
	}

}