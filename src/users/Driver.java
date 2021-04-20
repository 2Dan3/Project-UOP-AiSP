package users;

import vehicle.Vehicle;

public class Driver extends User {
	
	private double salary;
	private int membershipCardNum; 
	Vehicle car;
	// double avgRating;
	private int timesRated;
	
	
	public Driver() {
		super();
		salary = 0;
		membershipCardNum = 000000; // added value auto-increments as an ID
		// car = 
		// avgRating = 0.0;
		// timesRated = 0;
	}
	
	public Driver(double salary, int membershipCardNum, Vehicle car, double avgRating, int timesRated) {
		super();
		this.salary = salary;
		this.membershipCardNum = membershipCardNum;
		this.car = car;
		// this.avgRating = avgRating;
		//this.timesRated = timesRated;
	}
	
	// Mozda treba u Program.java da stoji def. f-je
	void calcNewAvg(int newRating, Driver currentDriver) {
		currentDriver.avgRating = (currentDriver.avgRating * currentDriver.timesRated + newRating) 
				/ ++currentDriver.timesRated; // zbog ++ odmah ide i 'save' novog 'timesRated' u file
	}
	
	
	// GETTERS & SETTERS
	
	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public int getMembershipCardNum() {
		return membershipCardNum;
	}

	public void setMembershipCardNum(int membershipCardNum) {
		this.membershipCardNum = membershipCardNum;
	}

	public Vehicle getCar() {
		return car;
	}

	public void setCar(Vehicle car) {
		this.car = car;
	}

	public int getTimesRated() {
		return timesRated;
	}

	public void setTimesRated(int timesRated) {
		this.timesRated = timesRated;
	}
	
	
	
}