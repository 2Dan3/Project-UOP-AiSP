package entities;

import java.util.*;

public class Vehicle implements Comparable <Vehicle> {

	static ArrayList<Vehicle> allVehicles = new ArrayList<Vehicle>();
	private String make;
    private String model;
    private int yearOfMake;
    private String registrationNum;
    private String taxiVehicleNum;
    private VehicleType type;
    private boolean hasDriver;
    private String VINNum;
    private boolean deleted;
    private long carID;
    private String engineNum;
    private String lastRegistrationDate;
    private InsuranceType insurance;
    private int seats;
    
	
    public Vehicle() {
    	super();
		this.make = "";
		this.model = "";
		this.yearOfMake = 0;
		this.registrationNum = "";
		this.taxiVehicleNum = "";
		this.type = VehicleType.values()[0];
		this.hasDriver = false;
		this.VINNum = "";
		this.deleted = false;
		this.carID = 0;
		this.engineNum = "";
	    this.lastRegistrationDate = "";
	    this.insurance = InsuranceType.values()[0];
	    this.seats = 4;
    }
    

    public Vehicle(String make, String model, int yearOfMake, String registrationNum, String taxiVehicleNum,
			VehicleType type, boolean hasDriver, String VINNum, boolean deleted, long carID, String engineNum, String lastRegistrationDate, InsuranceType insurance, int seats) {
		super();
		this.make = make;
		this.model = model;
		this.yearOfMake = yearOfMake;
		this.registrationNum = registrationNum;
		this.taxiVehicleNum = taxiVehicleNum;
		this.type = type;
		this.hasDriver = hasDriver;
		this.VINNum = VINNum;
		this.deleted = deleted;
		this.carID = carID;
		this.engineNum = engineNum;
	    this.lastRegistrationDate = lastRegistrationDate;
	    this.insurance = insurance;
	    this.seats = seats;
	}
    
    // GETTERS & SETTERS
    
    	
	public String getEngineNum() {
		return engineNum;
	}


	public void setEngineNum(String engineNum) {
		this.engineNum = engineNum;
	}


	public String getLastRegistrationDate() {
		return lastRegistrationDate;
	}


	public void setLastRegistrationDate(String lastRegistrationDate) {
		this.lastRegistrationDate = lastRegistrationDate;
	}


	public InsuranceType getInsurance() {
		return insurance;
	}


	public void setInsurance(InsuranceType insurance) {
		this.insurance = insurance;
	}


	public int getSeats() {
		return seats;
	}


	public void setSeats(int seats) {
		this.seats = seats;
	}


	public boolean isDeleted() {
		return deleted;
	}
	
	public void setDeleted(boolean b) {
		this.deleted = b;
	}
	
	
	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}


	public String getModel() {
		return model;
	}


	public void setModel(String model) {
		this.model = model;
	}


	public int getYearOfMake() {
		return yearOfMake;
	}


	public void setYearOfMake(int yearOfMake) {
		this.yearOfMake = yearOfMake;
	}


	public String getRegistrationNum() {
		return registrationNum;
	}


	public void setRegistrationNum(String registrationNum) {
		this.registrationNum = registrationNum;
	}


	public String getTaxiVehicleNum() {
		return taxiVehicleNum;
	}


	public void setTaxiVehicleNum(String taxiVehicleNum) {
		this.taxiVehicleNum = taxiVehicleNum;
	}


	public VehicleType getType() {
		return type;
	}


	public void setType(VehicleType type) {
		this.type = type;
	}


	public boolean getHasDriver() {
		return hasDriver;
	}


	public void setHasDriver(boolean hasDriver) {
		this.hasDriver = hasDriver;
	}


	public String getVINNum() {
		return VINNum;
	}


	public void setVINNum(String vINNum) {
		VINNum = vINNum;
	}
	
	public long getCarID() {
		return carID;
	}
	
	public void setCarID(long carID) {
		this.carID = carID;
	}
	


	
	@Override
	public String toString() {
		return "Vehicle [make=" + make + ", model=" + model + ", yearOfMake=" + yearOfMake + ", registrationNum="
				+ registrationNum + ", taxiVehicleNum=" + taxiVehicleNum + ", type=" + type + ", hasDriver=" + hasDriver
				+ ", VINNum=" + VINNum + "]";
	}

	@Override
	public int compareTo(Vehicle v) {
		return Long.compare(this.getCarID(), v.getCarID());
	}
    
    public static void showAll() {
    	for (Vehicle v : allVehicles) {
    		System.out.println(v);
    	}
    }

    
    // FILE IO
    		
}