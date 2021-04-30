
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Dispatcher extends Employee {

	ArrayList<Dispatcher> allDispatchers = new ArrayList<Dispatcher>();
	
    public Dispatcher() {
    	super();
		this.phoneLineNum = 0;
		this.dept = Department.REQUESTS;
    }
    
    
    public Dispatcher(int phoneLineNum, Department dept) {
		super();
		this.phoneLineNum = phoneLineNum;
		this.dept = dept;
	}
    
    // GETTERS & SETTERS

	public int getPhoneLineNum() {
		return phoneLineNum;
	}


	public void setPhoneLineNum(int phoneLineNum) {
		this.phoneLineNum = phoneLineNum;
	}


	public Department getDept() {
		return dept;
	}


	public void setDept(Department dept) {
		this.dept = dept;
	}


	@Override
	public String toString() {
		return "Dispatcher [phoneLineNum=" + phoneLineNum + ", dept=" + dept + ", salary=" + salary + ", username="
				+ username + ", password=" + password + ", name=" + name + ", lastName=" + lastName + ", jmbg=" + jmbg
				+ ", gender=" + gender + ", phoneNum=" + phoneNum + ", address=" + address + "]";
	}



	private int phoneLineNum;

    private Department dept;

    private void addDriver() {
        // TODO implement here
        return;
    }

    private void updateDriver() {
        // TODO implement here
        return;
    }

    private void addVehicle() {
        // TODO implement here
        return;
    }

    private void updateVehicle() {
        // TODO implement here
        return;
    }

    private void showTaxiService() {
        // TODO implement here
        return;
    }

    private void updateTaxiService() {
        // TODO implement here
        return;
    }

    private void searchDriver() {
        // TODO implement here
        return;
    }

    private void searchVehicle() {
        // TODO implement here
        return;
    }

    private void showRides() {
        // TODO implement here
        return;
    }

    private void assignToDriver() {
        // TODO implement here
        return;
    }

    private void showDriverReport() {
        // TODO implement here
        return;
    }

    private void showSumReport() {
        // TODO implement here
        return;
    }
    
    
    // FILE IO
    
    public void loadInDispatchers(String filename) {
		try {
			File file = new File("src/fajlovi/" + filename);
			BufferedReader reader = new BufferedReader(new FileReader(file));
			
			String row;
			while ((row = reader.readLine()) != null) {
				
				String[] split = row.split("\\|");
				
				
				int phoneLineNum = Integer.parseInt(split[0]);
				Department dept = split[1];
				double salary = Double.parseDouble(split[2]);
				String username = split[3];
				String password = split[4];
				String name= split[5];
				String lastName= split[6];
				int jmbg = Integer.parseInt(split[7]);
				Gender gender = split[8];
				String phoneNum = split[9];
				String address = split[10];
				
				Dispatcher dispatcher = new Dispatcher(phoneLineNum, dept);
				allDispatchers.add(dispatcher);
				
				// za proveru
				System.out.println(allDispatchers);
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom citanja podataka o dispecerima.");
			e.printStackTrace();
		}
	}
    
    
    public void saveDispatchers(String filename) {
		try {
			File file = new File("src/fajlovi/" + filename);
			String content = "";
			for (Dispatcher dp: allDispatchers) {
				content += dp.getPhoneLineNum() + "|" + dp.getDept() + "|" + dp.getSalary() + "|"
						+ dp.getUsername() + "|" + dp.getPassword() + "|"
						+ dp.getName() + "|" + dp.getLastName()+ "|" + dp.getJmbg() + "|"
						+ dp.getGender() + "|" + dp.getPhoneNum() + "|" + dp.getAddress() +"\n";
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(content);
			writer.close();
			
		} catch (IOException e) {
			System.out.println("Greska prilikom upisivanja dispecera.");
		}
	}

}