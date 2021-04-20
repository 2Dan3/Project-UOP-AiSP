package vehicle;

import users.Driver;

public class Vehicle {
	
	String model;
	String make;
	int yearManufactured;
	
	String registrationNum; // Broj Registarske Oznake ?
	String vehicleNum; // int ?
	String vehicleType;
	Driver carAsignee;
	
	
	public Vehicle() {
		model = "";
		make = "";
		yearManufactured = 0;
		registrationNum = "";
		vehicleNum = "";
		vehicleType = "";
		// carAssignee = 
	}

	public Vehicle(String model, String make, int yearManufactured, String registrationNum, String vehicleNum,
			String vehicleType, Driver driver) {
		super();
		this.model = model;
		this.make = make;
		this.yearManufactured = yearManufactured;
		this.registrationNum = registrationNum;
		this.vehicleNum = vehicleNum;
		this.vehicleType = vehicleType;
		// this.carAssignee = driver;
	}
	
	
}
