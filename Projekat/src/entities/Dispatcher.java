package entities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Dispatcher extends Employee implements CRUDOps {

	ArrayList<Dispatcher> allDispatchers = new ArrayList<Dispatcher>();
	
    public Dispatcher() {
    	super();
    	this.id = createNewID();
		this.phoneLineNum = 0;
		this.dept = Department.REQUESTS;
    }
    
    
    public Dispatcher(int id, String username, String password, String name, String lastName, int jmbg, Gender gender,
    		String phone, String address, double salary, int phoneLineNum, Department department) {
		super(username, password, name, lastName, jmbg, gender, phone, address, salary);
		this.id = id;
		this.phoneLineNum = phoneLineNum;
		this.dept = department;
	}
    
    // GETTERS & SETTERS
    
    public ArrayList<Dispatcher> getAllDispatchers() {
		return allDispatchers;
	}

	public void setAllDispatchers(ArrayList<Dispatcher> allDispatchers) {
		this.allDispatchers = allDispatchers;
	}
	
    public int getId() {
    	return id;
    }
    
    public void setId(int id) {
    	this.id = id;
    }
    
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

	private int id;

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
    
    
    public void showAll() {
    	for (Dispatcher d : allDispatchers) {
    		System.out.println(d);
    	}
    }
    public void addNew() {
    	Scanner sc = new Scanner(System.in);
	    	System.out.println("Postavite korisnicko ime >> ");
		    	String username = sc.nextLine();
	    	System.out.println("Postavite lozinku >> ");
		    	String password = sc.nextLine();
	    	System.out.println("Ime >> ");
		    	String name = sc.nextLine();
	    	System.out.println("Prezime >> ");
		    	String lastName = sc.nextLine();
	    	System.out.println("JMBG >> ");
		    	int jmbg = Integer.parseInt(sc.nextLine());
	    	System.out.println("Pol [0 - ZENSKI   1 - MUSKI] >> ");
		    	int g = Integer.parseInt(sc.nextLine());
		    	Gender gender = Gender.values()[g];
	    	System.out.println("Telefon >> ");
		    	String phone = sc.nextLine();
	    	System.out.println("Adresa >> ");
		    	String address = sc.nextLine();
	    	System.out.println("Plata >> ");
	    		double salary = Double.parseDouble(sc.nextLine());
			System.out.println("Broj telefonske linije >> ");
				int phoneLineNum = Integer.parseInt(sc.nextLine());
			System.out.println("Odeljenje na kom radi [0 - ZALBE    1 - PORUCIVANJE VOZNJI >> ");
				int d = Integer.parseInt(sc.nextLine());
				Department department = Department.values()[d];
    	sc.close();
    	
    	allDispatchers.add(new Dispatcher(id, username, password, name, lastName, jmbg, gender, phone, address, salary, phoneLineNum, department));
    	
    }
    
    
    // FILE IO
    
    public void loadInDispatchers(String filename) {
    	
    	String sp = System.getProperty(File.separator);
    	
		try {
			File file = new File("src" + sp + "dataFiles" + sp + filename);
		
			BufferedReader reader = new BufferedReader(new FileReader(file));
			
			String row;
			while ((row = reader.readLine()) != null) {
				
				String[] split = row.split("\\|");
				
				int id = Integer.parseInt(split[0]);
				String username = split[1];
				String password = split[2];
				String name= split[3];
				String lastName= split[4];
				int jmbg = Integer.parseInt(split[5]);
				Gender gender = Gender.values()[Integer.parseInt(split[6])];
				String phoneNum = split[7];
				String address = split[8];
				double salary = Double.parseDouble(split[9]);
				int phoneLineNum = Integer.parseInt(split[10]);
				Department dept = Department.values()[Integer.parseInt(split[11])];
				
				Dispatcher dispatcher = new Dispatcher(id, username, password, name, lastName, jmbg, gender, phoneNum, address, salary, phoneLineNum, dept);
				allDispatchers.add(dispatcher);
				
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom citanja podataka o dispecerima.");
			e.printStackTrace();
		}
	}
    
    
    public void saveDispatchers(String filename) {
    	
    	String sp = System.getProperty(File.separator);
    	
		try {
			File file = new File("src" + sp + "dataFiles" + sp + filename);

			String content = "";
			for (Dispatcher dp: allDispatchers) {
				content += dp.getId() + "|" + dp.getUsername() + "|" + dp.getPassword() + "|" + dp.getName() + "|" + dp.getLastName()+ "|" + dp.getJmbg() + "|"
						+ dp.getGender() + "|" + dp.getPhoneNum() + "|" + dp.getAddress() + "|" + dp.getSalary() + "|" + dp.getPhoneLineNum() + "|" + dp.getDept() +"\n";
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(content);
			writer.close();
			
		} catch (IOException e) {
			System.out.println("Greska prilikom upisivanja dispecera.");
		}
	}

}