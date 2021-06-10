package entities;

import java.util.*;

public abstract class Employee extends User {

    protected double salary;
    
    public Employee() {
    	super();
		this.salary = 0;
    }
    
	public Employee(String username, String password, String name, String lastName, String jmbg, Gender gender,
			String phoneNum, String address, double salary, boolean deleted) {
		super(username, password, name, lastName, jmbg, gender, phoneNum, address, deleted);
		this.salary = salary;
	}


	// GETTERS & SETTERS
	
	
	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	
	@Override
	public String toString() {
		return "Employee [salary=" + salary + ", username=" + username + ", password=" + password + ", name=" + name
				+ ", lastName=" + lastName + ", jmbg=" + jmbg + ", gender=" + gender + ", phoneNum=" + phoneNum
				+ ", address=" + address + "]";
	}
    
}