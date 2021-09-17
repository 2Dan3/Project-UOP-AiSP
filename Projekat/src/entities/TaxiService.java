package entities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import dataStructures.MyArrayList;

public class TaxiService {

    public static String TAXIDNUM;

    public static String LOCATION;

    public static String NAME;

    public static double STARTINGPRICE;

    public static double PRICEPERKM;
 
    private ArrayList <Dispatcher> allDispatchers;
    
    private ArrayList <Driver> allDrivers;
    
    private ArrayList <Customer> allCustomers;
   
    private ArrayList <Ride> allRides;
    
    private ArrayList <Vehicle> allVehicles;
    
    private MyArrayList auctionApplications = new MyArrayList();
    
    
    public TaxiService() {
    	this.allDispatchers = new ArrayList<Dispatcher>();
    	this.allDrivers = new ArrayList<Driver>();
    	this.allCustomers = new ArrayList<Customer>();
    	this.allRides = new ArrayList<Ride>();
    	this.allVehicles = new ArrayList<Vehicle>();
    	
    	loadInTaxiSvc("TaxiService.csv");
    	System.out.println(TAXIDNUM+ LOCATION+ NAME+ STARTINGPRICE+ PRICEPERKM);
    }
    
 // GETTERS

    public ArrayList<Dispatcher> getAllDispatchers() {
    	return allDispatchers;
	}
    public ArrayList<Driver> getAllDrivers() {
    	return allDrivers;
    }
    
    public ArrayList<Customer> getAllCustomers() {
    	return allCustomers;
    }
    
    public ArrayList<Vehicle> getAllVehicles() {
		return allVehicles;
	}
    
    public ArrayList<Ride> getAllRides() {
		return allRides;
	}
    
    public MyArrayList getAuctionApplications() {
    	return auctionApplications;
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
    	
    	Collections.sort(allDispatchers);
    	
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
    
// OLD FUNCTION
	public Driver UNUSEDfindDriver(String JMBG) {
		
		if(JMBG.trim().isBlank()) return null;
		
		long jmbg = Long.valueOf(JMBG);
		
		for(Driver dr : allDrivers) {
			if (dr.getJmbg() == jmbg) {
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
				Vehicle vehicle = findVehicle(Long.parseLong(split[10]));
//				Vehicle vehicle = null;
				DriverStatus driverStatus = DriverStatus.values()[Integer.parseInt(split[11])];
				boolean deleted = Boolean.parseBoolean(split[12]);
				long drivingLicence = Long.valueOf(split[13]);
				
				Driver driver = new Driver(username, password, name, lastName, jmbg, gender, phoneNum, address, membershipCardNum, vehicle, driverStatus, salary, deleted, drivingLicence);
				allDrivers.add(driver);
				
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("Gre\u0161ka prilikom \u016Ditanja podataka o voza\u016Dima.");
			e.printStackTrace();
		}
	}
    


	

	public void saveDrivers(String filename) {
    	
		Collections.sort(allDrivers);
		
    	String sp = System.getProperty("file.separator");
    	
		try {
			File file = new File("src" + sp + "dataFiles" + sp + filename);
			String content = "";
			for (Driver driver: allDrivers) {
				String carId = "0";
				if(driver.getVehicle() != null) carId = String.valueOf(driver.getVehicle().getCarID());
					
				content += driver.getUsername() + "|" + driver.getPassword() + "|"
						+ driver.getName() + "|" + driver.getLastName()+ "|" + driver.getJmbg() + "|"
						+ driver.getGender().ordinal() + "|" + driver.getPhoneNum() + "|" + driver.getAddress() + "|"
						+ driver.getSalary() + "|" + driver.getMembershipCardNum() + "|" + carId + "|"
						+ driver.getDriverStatus().ordinal() + "|" + driver.isDeleted() + "|" + driver.getDrivingLicence() +"\n";
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
//				Customer customer = null;
//				Driver driver = null;
				Customer customer = findCustomer(split[3]);
				Driver driver = findDriver(split[4]);
				double distanceTraveled = Double.parseDouble(split[5]);
				double duration= Double.parseDouble(split[6]);
				RequestStatus status= RequestStatus.values()[Integer.parseInt(split[7])];
				RequestType requestType = RequestType.values()[Integer.parseInt(split[8])];
				double startingPrice = Double.parseDouble(split[9]);
				double pricePerKm = Double.parseDouble(split[10]);
				long rideID = Long.valueOf(split[11]);
				int rating = Integer.valueOf(split[12]);
				
				Ride ride = new Ride(requestDateTime, startingAddress, destinationAddress, customer,
						driver, distanceTraveled, duration, status, requestType,
						startingPrice, pricePerKm, rideID, rating);
				allRides.add(ride);
				
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("Gre\u0161ka prilikom \u010Ditanja podataka o vo\u017Enjama.");
			e.printStackTrace();
		}
	}
    
    
    

	public void saveRides(String filename) {
    	
		Collections.sort(allRides);
		
    	String sp = System.getProperty("file.separator");
    	String driverJmbg;
    	
		try {
			File file = new File("src" + sp + "dataFiles" + sp + filename);
			String content = "";
			for (Ride ride: allRides) {
				
				driverJmbg = "";
				if(ride.getDriver() != null)	driverJmbg = String.valueOf(ride.getDriver().getJmbg());
					
				
				content += (String)ride.getRequestDateTime() + "|" + ride.getStartingAddress() + "|"
						+ ride.getDestinationAddress() + "|" + ride.getCustomer().getJmbg() + "|"
//						+ ride.getDestinationAddress() + "|" + null + "|"
						+ driverJmbg + "|" + ride.getDistanceTraveled() + "|"
//						+ null + "|" + ride.getDistanceTraveled() + "|"
						+ ride.getDuration() + "|" + ride.getStatus().ordinal() + "|" + ride.getRequestType().ordinal() + "|"
						+ ride.getStartingPrice() + "|" + ride.getPricePerKm() + "|" + ride.getRideID() + "|" + ride.getRating() + "\n";
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(content);
			writer.close();
			
		} catch (IOException e) {
			System.out.println("Gre\u0161ka prilikom upisivanja vo\u017Enji.");
		}
	}
    
    
    
    // VEHICLES
    
    public ArrayList<Vehicle> searchMultiCriteria(String make, String model, int yearOfMake, String registrationNum, String taxiVehicleNum) {
    	loadInVehicles("Vehicles.csv");
    	ArrayList<Vehicle> matchingVehicles = new ArrayList<Vehicle>();
    	
    	for (Vehicle v : allVehicles) {
    		
    		if( (make.equals(v.getMake()) || make.isBlank()) && (model.equals(v.getModel()) || model.isBlank()) && 
    			(yearOfMake == v.getYearOfMake() || yearOfMake == 0) && (registrationNum.equals(v.getRegistrationNum()) || registrationNum.isBlank()) && (taxiVehicleNum.equals(v.getTaxiVehicleNum()) || taxiVehicleNum.isBlank())) {
    			
    			matchingVehicles.add(v);
    		}
    	}
    	return matchingVehicles;
    }
    
    
    // FILE IO
    
    public void loadInVehicles(String filename) {
    	allVehicles.clear();
    	String sp = System.getProperty("file.separator");
    	
		try {
			File file = new File("src" + sp + "dataFiles" + sp + filename);
		
			BufferedReader reader = new BufferedReader(new FileReader(file));
			
			String row;
			while ((row = reader.readLine()) != null) {
				
				String[] split = row.split("\\|");
				
				
				String make = split[0];
				String model = split[1];
				int yearOfMake = Integer.parseInt(split[2]);
				String registrationNum = split[3];
				String taxiVehicleNum = split[4];
				VehicleType type = VehicleType.values()[Integer.parseInt(split[5])];
				boolean hasDriver = Boolean.parseBoolean(split[6]);
				String VINNum= split[7];
				boolean deleted = Boolean.parseBoolean(split[8]);
				long carID = Long.valueOf(split[9]);
				String engineNum = split[10];
				String lastRegistrationDate = split[11];
				InsuranceType insurance = InsuranceType.values()[Integer.parseInt(split[12])];
				int seats = Integer.parseInt(split[13]);
				
				Vehicle vehicle1 = new Vehicle(make, model, yearOfMake, registrationNum, taxiVehicleNum,
					 type, hasDriver, VINNum, deleted, carID, engineNum, lastRegistrationDate, insurance, seats);
				allVehicles.add(vehicle1);

			}
			reader.close();
			
		} catch (IOException e) {
			System.out.println("Gre\u0161ka prilikom \u010Ditanja podataka o vozilima.");
			e.printStackTrace();
		}
	}
    
    
    public void saveVehicles(String filename) {
    	
    	Collections.sort(allVehicles);
    	
    	String sp = System.getProperty("file.separator");
    	
		try {
			File file = new File("src" + sp + "dataFiles" + sp + filename);
		
			String content = "";
			for (Vehicle vehicle: allVehicles) {
				content += vehicle.getMake() + "|" + vehicle.getModel() + "|"
						+ vehicle.getYearOfMake() + "|" + vehicle.getRegistrationNum() + "|"
						+ vehicle.getTaxiVehicleNum() + "|" + vehicle.getType().ordinal() + "|"
						+ vehicle.getHasDriver() + "|" + vehicle.getVINNum() + "|" + vehicle.isDeleted()
						+ "|" + vehicle.getCarID() + "|" + vehicle.getEngineNum() + "|" 
						+ vehicle.getLastRegistrationDate() + "|" + vehicle.getInsurance().ordinal() +"|" + vehicle.getSeats() +"\n";
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(content);
			writer.close();
			
		} catch (IOException e) {
			System.out.println("Gre\u0161ka prilikom upisivanja vozila.");
		}
	}
    
    
    
    // CUSTOMERS
    
	public ArrayList<Customer> getNonDeletedCustomers() {
		ArrayList<Customer> nonDeletedCustomerList = new ArrayList<Customer>();
		
		loadInCustomers("Customers.csv");

		for(Customer c : allCustomers) {

		    if (!c.isDeleted())
		    	nonDeletedCustomerList.add(c);
		}
		return nonDeletedCustomerList;
	}
	
	
	private Customer findCustomer(String JMBG) {
    	if(JMBG.trim().isBlank()) return null;
		
    	long jmbg = Long.valueOf(JMBG);
    	
    	for(Customer customer : allCustomers) {
    		if (customer.getJmbg() == jmbg) {
    			return customer;
    		}
    	}
		return null;
	}
	
	
	
	// FILE IO
	
	public void loadInCustomers(String filename) {
    	allCustomers.clear();
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
				boolean deleted = Boolean.parseBoolean(split[8]);
				
				Customer customer = new Customer(username, password, name, lastName, jmbg, gender, phoneNum, address, deleted);
				allCustomers.add(customer);

			}
			reader.close();
		} catch (IOException e) {
			System.out.println("Gre\u0161ka prilikom \u010Ditanja podataka o klijentima.");
			e.printStackTrace();
		}
	}


	public void saveCustomers(String filename) {
    	
		Collections.sort(allCustomers);
		
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
			System.out.println("Gre\u0161ka prilikom pisanja podataka o taksi slu\u017Ebi.");
		}
	}
	
	
	
	
	// TAXi SERViCE
	
	 public static void loadInTaxiSvc(String filename) {
	    	
	    	String sp = System.getProperty("file.separator");
	    	
			try {
				File file = new File("src" + sp + "dataFiles" + sp + filename);
			
				BufferedReader reader = new BufferedReader(new FileReader(file));
				
				String row;
				while ((row = reader.readLine()) != null) {
					
					String[] split = row.split("\\|");
					
					TAXIDNUM = split[0];
					LOCATION = split[1];
					NAME = split[2];
					STARTINGPRICE = Double.parseDouble(split[3]);
					PRICEPERKM = Double.parseDouble(split[4]);
					
					
				}
				reader.close();
			} catch (IOException e) {
				System.out.println("Gre\u0161ka prilikom \u010Ditanja podataka o taksi slu\u017Ebi.");
				e.printStackTrace();
			}
		}

	public ArrayList<Ride> getOngoingRides() {
		loadInRides("Rides.csv");
		
		ArrayList<Ride> rides = new ArrayList<Ride>();
		for(Ride ride : allRides) {
			if(ride.getStatus() != RequestStatus.values()[5]) {
				rides.add(ride);
			}
		}
		return rides;
	}

	public ArrayList<Ride> getAllCurrentDriverRides(Driver currentDriver) {
		
		loadInRides("Rides.csv");
		ArrayList<Ride> rides = new ArrayList<Ride>();
		
		for(Ride ride : allRides) {
			if(ride.getDriver()==currentDriver) {
				rides.add(ride);
			}
		}
		return rides;
	}
	
	
	
	//
	
	public void loadInDriverAuctionApplications(String filename) {
		String sp = System.getProperty("file.separator");
    	
		try {
			File file = new File("src" + sp + "dataFiles" + sp + filename);
		
			BufferedReader reader = new BufferedReader(new FileReader(file));
			
			String row;
			while ((row = reader.readLine()) != null) {
				
				String[] split = row.split("\\|");
				
				Driver driver = findDriver(split[0]);
				long rideID = Long.parseLong(split[1]);
				int arrivalTime = Integer.parseInt(split[2]);
				
				AuctionApplication newAuctionApplication = new AuctionApplication(driver, rideID, arrivalTime);
				
				auctionApplications.add(newAuctionApplication);
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("Gre\u0161ka prilikom \u010Ditanja prijava za aukciju.");
			e.printStackTrace();
		}
		
	}
	
	public void saveDriverAuctionApplications(String filename) throws Exception {
    	
//		Collections.sort(auctionApplications);
		
    	String sp = System.getProperty("file.separator");
    	
		try {
			File file = new File("src" + sp + "dataFiles" + sp + filename);
		
			String content = "";
			for (int i = 0; i < auctionApplications.length(); i++) {
				AuctionApplication a = (AuctionApplication)auctionApplications.get(i);
				content +=
						  a.getDriver() + "|" + a.getRideID() + "|" + a.getArrivalTimeInMins() +"\n";
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(content);
			writer.close();
			
		} catch (IOException e) {
			System.out.println("Gre\u0161ka prilikom pisanja prijava za aukciju.");
		}
	}
	
	
	
	
	
	
	public Driver findDriver(String ID) {
		
		if(ID.trim().isBlank()) return null;
		
		long id = Long.valueOf(ID);
		
		int firstEl = 0;
		int lastEl = allDrivers.size() -1;
		int midEl;
		
		while(lastEl >= firstEl) {
			
			
			midEl = (firstEl + lastEl) / 2;
			
			Driver middleUser = allDrivers.get(midEl);
			
			if(middleUser.getJmbg() == id)	return middleUser;
			
			if(middleUser.getJmbg() < id)	firstEl = midEl +1;
			
			else 							lastEl = midEl -1;
		}
		
		return null;
	}
	
	
	
	public long generateNewRideID() {
    	
    	long newRideID = allRides.get(0).getRideID() +1;
		return newRideID;
	}
	
	public double calculateAvgRating(Driver currentDriver) {
		
		int ridesTotal = 0;
		double ratingSum = 0;
		int rate;
		
		for(Ride r : allRides) {
			if(r.getDriver().equals(currentDriver) && (rate = r.getRating()) != 0) {
				ridesTotal += 1;
				ratingSum += rate;
			}
		}return ratingSum / ridesTotal;
	}

	
}