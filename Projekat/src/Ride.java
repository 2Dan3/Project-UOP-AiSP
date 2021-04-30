
import java.util.*;

public class Ride {

    public Ride() {
    	super();
		this.requestDateTime = "";
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
		RideID = 0;
    }

    
    public Ride(String requestDateTime, String startingAddress, String destinationAddress, Customer customer,
			Driver driver, double distanceTraveled, double duration, RequestStatus status, RequestType requestType,
			TaxiService startingPrice, TaxiService pricePerKm, int rideID) {
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


	public TaxiService getStartingPrice() {
		return startingPrice;
	}


	public void setStartingPrice(TaxiService startingPrice) {
		this.startingPrice = startingPrice;
	}


	public TaxiService getPricePerKm() {
		return pricePerKm;
	}


	public void setPricePerKm(TaxiService pricePerKm) {
		this.pricePerKm = pricePerKm;
	}


	public int getRideID() {
		return RideID;
	}


	public void setRideID(int rideID) {
		RideID = rideID;
	}
	

	@Override
	public String toString() {
		return "Ride [requestDateTime=" + requestDateTime + ", startingAddress=" + startingAddress
				+ ", destinationAddress=" + destinationAddress + ", customer=" + customer + ", driver=" + driver
				+ ", distanceTraveled=" + distanceTraveled + ", duration=" + duration + ", status=" + status
				+ ", requestType=" + requestType + ", startingPrice=" + startingPrice + ", pricePerKm=" + pricePerKm
				+ ", RideID=" + RideID + "]";
	}



	private String requestDateTime;

    private String startingAddress;

    private String destinationAddress;

    private Customer customer;

    private Driver driver;

    private double distanceTraveled;

    private double duration;

    private RequestStatus status;

    private RequestType requestType;

    private TaxiService startingPrice;

    private TaxiService pricePerKm;

    private int RideID;

    
    
    
}