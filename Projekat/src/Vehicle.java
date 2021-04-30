
import java.util.*;

public class Vehicle {

	
    public Vehicle() {
    	super();
		this.make = "";
		this.model = "";
		this.yearOfMake = 0;
		this.registrationNum = "";
		this.taxiVehicleNum = "";
		this.type = VehicleType.CAR;
		this.driver = new Driver();
		this.VINNum = 0;
    }
    

    public Vehicle(String make, String model, int yearOfMake, String registrationNum, String taxiVehicleNum,
			VehicleType type, Driver driver, int VINNum) {
		super();
		this.make = make;
		this.model = model;
		this.yearOfMake = yearOfMake;
		this.registrationNum = registrationNum;
		this.taxiVehicleNum = taxiVehicleNum;
		this.type = type;
		this.driver = driver;
		this.VINNum = VINNum;
	}
    
    // GETTERS & SETTERS

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


	public Driver getDriver() {
		return driver;
	}


	public void setDriver(Driver driver) {
		this.driver = driver;
	}


	public int getVINNum() {
		return VINNum;
	}


	public void setVINNum(int vINNum) {
		VINNum = vINNum;
	}
	


	
	@Override
	public String toString() {
		return "Vehicle [make=" + make + ", model=" + model + ", yearOfMake=" + yearOfMake + ", registrationNum="
				+ registrationNum + ", taxiVehicleNum=" + taxiVehicleNum + ", type=" + type + ", driver=" + driver
				+ ", VINNum=" + VINNum + "]";
	}




	private String make;

    private String model;

    private int yearOfMake;

    private String registrationNum;

    private String taxiVehicleNum;

    private VehicleType type;

    private Driver driver;

    private int VINNum;

    //public Driver vehicle;


}