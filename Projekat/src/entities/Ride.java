package entities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Ride {

	ArrayList<Ride> allRides = new ArrayList<Ride>();
	private String requestDateTime;
    private String startingAddress;
    private String destinationAddress;
    private Customer customer;
    private Driver driver;
    private double distanceTraveled;
    private double duration;
    private RequestStatus status;
    private RequestType requestType;
    private double startingPrice;
    private double pricePerKm;
    private long RideID;
	
    public Ride() {
    	super();
		this.requestDateTime = "";
		this.startingAddress = "";
		this.destinationAddress = "";
		this.customer = new Customer();
		this.driver = new Driver();
		this.distanceTraveled = 0;
		this.duration = 0;
		this.status = RequestStatus.CREATED;
		this.requestType = RequestType.values()[1];
		this.startingPrice = 0;
		this.pricePerKm = 0;
		RideID = 0;
    }

    public Ride(String requestDateTime, String startingAddress, String destinationAddress, Customer customer,
			Driver driver, double distanceTraveled, double duration, RequestStatus status, RequestType requestType,
			double startingPrice, double pricePerKm, long rideID) {
		super();
		this.requestDateTime = requestDateTime;
		this.startingAddress = startingAddress;
		this.destinationAddress = destinationAddress;
		this.customer = customer;
		this.driver = driver;
		this.distanceTraveled = distanceTraveled;
		this.duration = duration;
		this.status = status;
		this.requestType = requestType;
		this.startingPrice = startingPrice;
		this.pricePerKm = pricePerKm;
		RideID = rideID;
	}
    
    // GETTERS & SETTERS
    
    
	public String getRequestDateTime() {
		return requestDateTime;
	}


	public void setRequestDateTime(String requestDateTime) {
		this.requestDateTime = requestDateTime;
	}


	public String getStartingAddress() {
		return startingAddress;
	}


	public void setStartingAddress(String startingAddress) {
		this.startingAddress = startingAddress;
	}


	public String getDestinationAddress() {
		return destinationAddress;
	}


	public void setDestinationAddress(String destinationAddress) {
		this.destinationAddress = destinationAddress;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public Driver getDriver() {
		return driver;
	}


	public void setDriver(Driver driver) {
		this.driver = driver;
	}


	public double getDistanceTraveled() {
		return distanceTraveled;
	}


	public void setDistanceTraveled(double distanceTraveled) {
		this.distanceTraveled = distanceTraveled;
	}


	public double getDuration() {
		return duration;
	}


	public void setDuration(double duration) {
		this.duration = duration;
	}


	public RequestStatus getStatus() {
		return status;
	}


	public void setStatus(RequestStatus status) {
		this.status = status;
	}


	public RequestType getRequestType() {
		return requestType;
	}


	public void setRequestType(RequestType requestType) {
		this.requestType = requestType;
	}


	public double getStartingPrice() {
		return startingPrice;
	}


	public void setStartingPrice(double startingPrice) {
		this.startingPrice = startingPrice;
	}


	public double getPricePerKm() {
		return pricePerKm;
	}


	public void setPricePerKm(double pricePerKm) {
		this.pricePerKm = pricePerKm;
	}


	public long getRideID() {
		return RideID;
	}


	public void setRideID(long rideID) {
		this.RideID = rideID;
	}
	

	@Override
	public String toString() {
		return "Ride [requestDateTime=" + requestDateTime + ", startingAddress=" + startingAddress
				+ ", destinationAddress=" + destinationAddress + ", customer=" + customer + ", driver=" + driver
				+ ", distanceTraveled=" + distanceTraveled + ", duration=" + duration + ", status=" + status
				+ ", requestType=" + requestType + ", startingPrice=" + startingPrice + ", pricePerKm=" + pricePerKm
				+ ", RideID=" + RideID + "]";
	}
    
    //FILE IO
    
    
}