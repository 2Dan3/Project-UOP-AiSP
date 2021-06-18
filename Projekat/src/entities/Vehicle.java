package entities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Vehicle {

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
    }
    

    public Vehicle(String make, String model, int yearOfMake, String registrationNum, String taxiVehicleNum,
			VehicleType type, boolean hasDriver, String VINNum, boolean deleted, long carID) {
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
	}
    
    // GETTERS & SETTERS
    
    public static ArrayList<Vehicle> getAllVehicles() {
		return allVehicles;
	}
    

	public void setAllVehicles(ArrayList<Vehicle> allVehicles) {
		this.allVehicles = allVehicles;
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

    
    
    public static void showAll() {
    	for (Vehicle v : allVehicles) {
    		System.out.println(v);
    	}
    }

    
    // FILE IO
    
    
    public void loadInVehicles(String filename) {
    	
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
				
				Vehicle vehicle1 = new Vehicle(make, model, yearOfMake, registrationNum, taxiVehicleNum,
					 type, hasDriver, VINNum, deleted, carID);
				allVehicles.add(vehicle1);

			}
			reader.close();
			
		} catch (IOException e) {
			System.out.println("Greska prilikom citanja podataka o vozilima.");
			e.printStackTrace();
		}
	}
    
    
    public void saveVehicles(String filename) {
    	
    	String sp = System.getProperty("file.separator");
    	
		try {
			File file = new File("src" + sp + "dataFiles" + sp + filename);
		
			String content = "";
			for (Vehicle vehicle: allVehicles) {
				content += vehicle.getMake() + "|" + vehicle.getModel() + "|"
						+ vehicle.getYearOfMake() + "|" + vehicle.getRegistrationNum() + "|"
						+ vehicle.getTaxiVehicleNum() + "|" + vehicle.getType() + "|"
						+ vehicle.getHasDriver() + "|" + vehicle.getVINNum() + "|" + vehicle.isDeleted() + "|" + vehicle.carID +"\n";
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(content);
			writer.close();
			
		} catch (IOException e) {
			System.out.println("Greska prilikom upisivanja vozila.");
		}
	}

}