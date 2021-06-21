package entities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class TaxiService {

    private static int TAXIDNUM;

    private static String LOCATION;

    private static String NAME;

    private static double STARTINGPRICE;

    private static double PRICEPERKM;
 
    private ArrayList <Dispatcher> allDispatchers;
    
    private ArrayList <Driver> allDrivers;
    
    private ArrayList <Customer> allCustomers;
   
    private ArrayList <Ride> allRides;
    
    private ArrayList <Vehicle> allVehicles;
    
    
    public TaxiService() {
    	this.allDispatchers = new ArrayList<Dispatcher>();
    	this.allDrivers = new ArrayList<Driver>();
    	this.allCustomers = new ArrayList<Customer>();
    	this.allRides = new ArrayList<Ride>();
    	this.allVehicles = new ArrayList<Vehicle>();
    }
    
 // GETTERS & SETTERS
    //TODO
    public ArrayList<Dispatcher> getAllDispatchers() {
    	return allDispatchers;
	}
    public ArrayList<Driver> getAllDrivers() {
    	return allDrivers;
    }
    
    
    
    
    
    
    
    
    public ArrayList<Ride> getAllRides() {
		return allRides;
	}
    
    
    
    // DISPATCHERS >> 
    
    
	public Dispatcher findDispatcher(long jmbG) {

		for(Dispatcher dp : allDispatchers) {
			if (dp.getJmbg() == jmbG) {
				return dp;
			}
		}
		return null;
	}

	public void addNew(Dispatcher dp) {
    	allDispatchers.add(dp);
    }
    
    /*public ArrayList<Dispatcher> listActiveDispatchers() {
    	
		ArrayList<Dispatcher> dispatcherList = new ArrayList<Dispatcher>();
    	return dispatcherList;
    }*/
    
    public ArrayList<Dispatcher> getNonDeletedDispatchers() {
    	
		ArrayList<Dispatcher> nonDeletedDpList = new ArrayList<Dispatcher>();
		
		loadInDispatchers("Dispatchers.csv");

		for(Dispatcher dp : allDispatchers) {

		    if (!dp.isDeleted())
		        nonDeletedDpList.add(dp);
		}
		return nonDeletedDpList;
	}    
    
    
    // FILE IO
    
    public void loadInDispatchers(String filename) {
    	allDispatchers.clear();
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
				long jmbg = Long.parseLong(split[4]);
				Gender gender = Gender.values()[Integer.parseInt(split[5])];
				String phoneNum = split[6];
				String address = split[7];
				double salary = Double.parseDouble(split[8]);
				String phoneLineNum = split[9];
				Department dept = Department.values()[Integer.parseInt(split[10])];
				boolean deleted = Boolean.parseBoolean(split[11]);
				
				Dispatcher dispatcher = new Dispatcher(username, password, name, lastName, jmbg, gender, phoneNum, address, salary, phoneLineNum, dept, deleted);
				allDispatchers.add(dispatcher);
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("Gre\u0161ka prilikom \u010Ditanja podataka o dispe\u010Derima.");
			e.printStackTrace();
		}
	}
    
    
    public void saveDispatchers(String filename) {
    	
    	String sp = System.getProperty("file.separator");
    	
		try {
			File file = new File("src" + sp + "dataFiles" + sp + filename);

			String content = "";
			for (Dispatcher dp: allDispatchers) {
				content += dp.getUsername() + "|" + dp.getPassword() + "|" + dp.getName() + "|" + dp.getLastName()+ "|" + dp.getJmbg() + "|"
						+ dp.getGender().ordinal() + "|" + dp.getPhoneNum() + "|" + dp.getAddress() + "|" + dp.getSalary() + "|" + dp.getPhoneLineNum() + "|" + dp.getDept().ordinal() + "|" + dp.isDeleted() +"\n";
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(content);
			writer.close();
			
		} catch (IOException e) {
			System.out.println("Gre\u0161ka prilikom upisivanja dispe\u010Dera.");
		}
	}
    
    
    
    // DRIVERS >> 
    
    public ArrayList<Driver> searchMultiCriteria(String name, String lastName, String salaryMin, String salaryMax, long carID) {
    	loadInDrivers("Drivers.csv");
    	ArrayList<Driver> matchingDrivers = new ArrayList<Driver>();
    	
    	Vehicle matchingVehicle = findVehicle(carID);
    	
    	double salaryMAX = Double.valueOf(salaryMax);
    	double salaryMIN = 0;
    	
    	if(!salaryMin.isBlank() ) {
    		salaryMIN = Double.valueOf(salaryMin);
    	}
    	
    	for (Driver dr : allDrivers) {
    		
    		if( (name.equals(dr.getName()) || name.isBlank()) && (lastName.equals(dr.getLastName()) || lastName.isBlank()) && 
    			(salaryMIN <= dr.getSalary() && salaryMAX >= dr.getSalary()) && (dr.getVehicle().equals(matchingVehicle) || matchingVehicle.equals(null)) ) {
    			
    			matchingDrivers.add(dr);
    		}
    	}
    	return matchingDrivers;
    }
    
    
    private Vehicle findVehicle(long carID) {
		loadInVehicles("Vehicles.csv");
		
		for (Vehicle v : allVehicles) {
			if (carID == v.getCarID()) {
				return v;
			}
		}
		return null;
	}
    

	public Driver findDriver(long jmbG) {

		for(Driver dr : allDrivers) {
			if (dr.getJmbg() == jmbG) {
				return dr;
			}
		}
		return null;
	}

	public void addNew(Driver dr) {
    	allDrivers.add(dr);
    }
    
    public ArrayList<Driver> getNonDeletedDrivers() {
    	
		ArrayList<Driver> nonDeletedDrList = new ArrayList<Driver>();
		
		loadInDrivers("Drivers.csv");

		for(Driver dr : allDrivers) {

		    if (!dr.isDeleted())
		    	nonDeletedDrList.add(dr);
		}
		return nonDeletedDrList;
	}
    
    public ArrayList<Driver> getNonBusyDrivers() {
    	ArrayList<Driver> nonBusyDr = new ArrayList<Driver>();
    	
    	loadInDrivers("Drivers.csv");
    	
    	for(Driver dr : allDrivers) {
    		if (!dr.isDeleted() && dr.getDriverStatus() == DriverStatus.values()[1]) {
    			nonBusyDr.add(dr);
    		}
    	}
		return nonBusyDr;
    }
    
    
    
    // FILE IO
    
    public void loadInDrivers(String filename) {
    	allDrivers.clear();
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
				long jmbg = Long.parseLong(split[4]);
				Gender gender = Gender.values()[Integer.parseInt(split[5])];
				String phoneNum = split[6];
				String address = split[7];
				double salary = Double.parseDouble(split[8]);
				String membershipCardNum = split[9];
// TODO				Vehicle vehicle = findVehicle(Integer.parseInt(split[10]));
				Vehicle vehicle = null;
				DriverStatus driverStatus = DriverStatus.values()[Integer.parseInt(split[11])];
				boolean deleted = Boolean.parseBoolean(split[12]);
				
				Driver driver = new Driver(username, password, name, lastName, jmbg, gender, phoneNum, address, membershipCardNum, vehicle, driverStatus, salary, deleted);
				allDrivers.add(driver);
				
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("Gre\u0161ka prilikom \u016Ditanja podataka o voza\u016Dima.");
			e.printStackTrace();
		}
	}
    


	

	public void saveDrivers(String filename) {
    	
    	String sp = System.getProperty("file.separator");
    	
		try {
			File file = new File("src" + sp + "dataFiles" + sp + filename);
			String content = "";
			for (Driver driver: allDrivers) {
				content += driver.getUsername() + "|" + driver.getPassword() + "|"
						+ driver.getName() + "|" + driver.getLastName()+ "|" + driver.getJmbg() + "|"
						+ driver.getGender().ordinal() + "|" + driver.getPhoneNum() + "|" + driver.getAddress() + "|"
						+ driver.getSalary() + "|" + driver.getMembershipCardNum() + "|" + null + "|"
						+ driver.getDriverStatus().ordinal() + "|" + driver.isDeleted() +"\n";
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(content);
			writer.close();
			
		} catch (IOException e) {
			System.out.println("Gre\u0161ka prilikom upisivanja voza\u016Da.");
		}
	}
	
	
	
	
	// RIDES
	
	
	public Ride findRide(long rideID) {
		for (Ride ride : allRides) {
			if (rideID == ride.getRideID() ) {
				return ride;
			}
		}
		return null;
	}
	
	public void addNew(Ride ride) {
    	allRides.add(ride);
    }
	
	
	
	public void loadInRides(String filename) {
    	allRides.clear();
    	String sp = System.getProperty("file.separator");
    	
		try {
			File file = new File("src" + sp + "dataFiles" + sp + filename);
			BufferedReader reader = new BufferedReader(new FileReader(file));
			
			String row;
			while ((row = reader.readLine()) != null) {
				
				String[] split = row.split("\\|");
				
				
				String requestDateTime = split[0];
				String startingAddress = split[1];
				String destinationAddress = split[2];
				Customer customer = null;
				Driver driver = null;
				// TODO Customer customer = findCustomer(split[3]);
				//TODO Driver driver = findDriver(split[4]);
				double distanceTraveled = Double.parseDouble(split[5]);
				double duration= Double.parseDouble(split[6]);
				RequestStatus status= RequestStatus.values()[Integer.parseInt(split[7])];
				RequestType requestType = RequestType.values()[Integer.parseInt(split[8])];
				double startingPrice = Double.parseDouble(split[9]);
				double pricePerKm = Double.parseDouble(split[10]);
				long rideID = Long.valueOf(split[11]);
				
				Ride ride = new Ride(requestDateTime, startingAddress, destinationAddress, customer,
						driver, distanceTraveled, duration, status, requestType,
						startingPrice, pricePerKm, rideID);
				allRides.add(ride);
				
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("Gre\u0161ka prilikom \u010Ditanja podataka o vo\u017Enjama.");
			e.printStackTrace();
		}
	}
    
    
    public void saveRides(String filename) {
    	
    	String sp = System.getProperty("file.separator");
    	
		try {
			File file = new File("src" + sp + "dataFiles" + sp + filename);
			String content = "";
			for (Ride ride: allRides) {
				content += (String)ride.getRequestDateTime() + "|" + ride.getStartingAddress() + "|"
						+ ride.getDestinationAddress() + "|" + ride.getCustomer().getJmbg() + "|"
						+ ride.getDriver().getJmbg() + "|" + ride.getDistanceTraveled() + "|"
						+ ride.getDuration() + "|" + ride.getStatus().ordinal() + "|" + ride.getRequestType().ordinal() + "|"
						+ ride.getStartingPrice() + "|" + ride.getPricePerKm() + "|" + ride.getRideID() +"\n";
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(content);
			writer.close();
			
		} catch (IOException e) {
			System.out.println("Gre\u0161ka prilikom upisivanja vo\u017Enji.");
		}
	}
	
}