package entities;

import java.util.ArrayList;


public class Auction {

	
	private ArrayList<Driver> driversApplied = new ArrayList<Driver>();
	
	private int occupants;
	private Customer customer;
	private String startingAddress;
	private String destination;
	private boolean petFriendly;
	private boolean smokingAllowed;
	
	public Auction(int occupants, Customer customer, String startingAddress, String destination, boolean petFriendly, boolean smokingAllowed, ArrayList<Driver> driversApplied) {
		
		this.driversApplied = driversApplied;
		
		this.occupants = occupants;
		this.customer = customer;
		this.startingAddress = startingAddress;
		this.destination = destination;
		this.petFriendly = petFriendly;
		this.smokingAllowed = smokingAllowed;
		
	}
	
	
	private Driver beginAuction () {
		
		
		
		return driver;
	}
}
