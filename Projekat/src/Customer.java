
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Customer extends User {

	ArrayList<Customer> allCustomers = new ArrayList<Customer>();
	
    public Customer() {
    	super();
    }
    

    @Override
	public String toString() {
		return "Customer [username=" + username + ", password=" + password + ", name=" + name + ", lastName=" + lastName
				+ ", jmbg=" + jmbg + ", gender=" + gender + ", phoneNum=" + phoneNum + ", address=" + address + "]";
	}


	private boolean requestRide(String start, String destination, RequestType requestType, String additionalNotice) {
        // TODO implement here
        return false;
    }

    private void requestRecords() {
        // TODO implement here
        return;
    }
    
    
    // FILE IO
    
    
    public void loadInCustomers() {
    	
    	String sp = System.getProperty(File.separator);
    	
		try {
			File file = new File("src" + sp + "fajlovi" + sp + "Customers.csv");

			BufferedReader reader = new BufferedReader(new FileReader(file));
			
			String row;
			while ((row = reader.readLine()) != null) {
				
				String[] split = row.split("\\|");
				
				
				String username = split[0];
				String password = split[1];
				String name= split[2];
				String lastName= split[3];
				int jmbg = Integer.parseInt(split[4]);
				Gender gender = split[5];
				String phoneNum = split[6];
				String address = split[7];
				
				Customer customer = new Customer();
				allCustomers.add(customer);
				
				// za proveru
				System.out.println(allCustomers);
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom citanja podataka o klijentima.");
			e.printStackTrace();
		}
	}
    
    
    public void saveCustomers() {
    	
    	String sp = System.getProperty(File.separator);
    	
		try {
			File file = new File("src" + sp + "fajlovi" + sp + "Customers.csv");
		
			String content = "";
			for (Customer customer: allCustomers) {
				content +=
						  customer.getUsername() + "|" + customer.getPassword() + "|"
						+ customer.getName() + "|" + customer.getLastName()+ "|" + customer.getJmbg() + "|"
						+ customer.getGender() + "|" + customer.getPhoneNum() + "|" + customer.getAddress() +"\n";
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(content);
			writer.close();
			
		} catch (IOException e) {
			System.out.println("Greska prilikom upisivanja klijenata.");
		}
	}

}