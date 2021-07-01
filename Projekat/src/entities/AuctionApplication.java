package entities;

import java.util.ArrayList;


public class AuctionApplication implements Comparable <AuctionApplication> {

	
	private ArrayList<Driver> driversApplied = new ArrayList<Driver>();
	
//	private int occupants;
//	private Customer customer;
//	private String startingAddress;
//	private String destination;
//	private boolean petFriendly;
//	private boolean smokingAllowed;
	private Driver driver;
	private long rideID;
	private int arrivalTimeInMins;
	
	public AuctionApplication(Driver driver, long rideID, int arrivalTimeInMins/*int occupants, Customer customer, String startingAddress, String destination, boolean petFriendly, boolean smokingAllowed, ArrayList<Driver> driversApplied*/) {
		
		this.driver = driver;
		this.rideID = rideID;
		this.arrivalTimeInMins = arrivalTimeInMins;
//		this.driversApplied = driversApplied;
//		
//		this.occupants = occupants;
//		this.customer = customer;
//		this.startingAddress = startingAddress;
//		this.destination = destination;
//		this.petFriendly = petFriendly;
//		this.smokingAllowed = smokingAllowed;
		
//		TODO Premestiti poziv this.beginAuction();
		
	}
	
	
	public Driver getDriver() {
		return driver;
	}


	public void setDriver(Driver driver) {
		this.driver = driver;
	}


	public long getRideID() {
		return rideID;
	}


	public void setRideID(long rideID) {
		this.rideID = rideID;
	}


	public int getArrivalTimeInMins() {
		return arrivalTimeInMins;
	}


	public void setArrivalTimeInMins(int arrivalTimeInMins) {
		this.arrivalTimeInMins = arrivalTimeInMins;
	}


	private Driver beginAuction () {
		
//		TODO Premestiti instancu objekta Auction auction = new Auction(occupants, customer, start, destination, petFriendly, smokingAllowed, appliedDrivers)
		
		int 
		
		if(driver.getVehicle().getSeats() < occupants || (petFriendly==true && driver.getVehicle().isPetFriendly()==false) || (smokingAllowed==true && driver.getVehicle().isSmokingAllowed==false)) {
			continue;
		}
//		if(driver.getAvgRating() )
		
		for (Driver driver : appliedDrivers) {
			
			if (driver.)
		}
		
		
		return driver;
	}
	
	
	
	
	@Override
	public int compareTo(AuctionApplication a) {
		return Long.compare(this.getArrivalTimeInMins(), a.getArrivalTimeInMins());
	}
}
