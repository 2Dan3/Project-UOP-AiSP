package entities;

import java.util.*;

public abstract class User implements Comparable <User> {

    protected String username;

    protected String password;

    protected String name;

    protected String lastName;

    protected long jmbg;

    protected Gender gender;

    protected String phoneNum;

    protected String address;
    
    protected boolean deleted;
    
    
    public User() {
    	super();
		this.username = "";
		this.password = "";
		this.name = "";
		this.lastName = "";
		this.jmbg = Long.valueOf("1000000000000");
		this.gender = Gender.values()[0];
		this.phoneNum = "";
		this.address = "";
		this.deleted = false;
    }
    
    public User(String username, String password, String name, String lastName, long jmbg, Gender gender,
			String phoneNum, String address, boolean deleted) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.lastName = lastName;
		this.jmbg = jmbg;
		this.gender = gender;
		this.phoneNum = phoneNum;
		this.address = address;
		this.deleted = deleted;
	}


    //GETTERS & SETTERS
    

	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public long getJmbg() {
		return jmbg;
	}



	public void setJmbg(long jmbg) {
		this.jmbg = jmbg;
	}



	public Gender getGender() {
		return gender;
	}



	public void setGender(Gender gender) {
		this.gender = gender;
	}



	public String getPhoneNum() {
		return phoneNum;
	}



	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}
	
	public boolean isDeleted() {
		return deleted;
	}
	
	public void setDeleted(boolean b) {
		this.deleted = b;
	}


	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", name=" + name + ", lastName=" + lastName
				+ ", jmbg=" + jmbg + ", gender=" + gender + ", phoneNum=" + phoneNum + ", address=" + address + "]";
	}

	
	@Override
	public int compareTo(User user) {
		return Long.compare(this.getJmbg(), user.getJmbg());
	}

}