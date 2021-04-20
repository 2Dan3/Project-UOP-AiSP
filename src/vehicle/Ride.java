package vehicle;

import java.time.LocalDateTime; //

import users.Customer;
import users.Driver;

public class Ride {

	LocalDateTime requestDatetime; //
	Customer customer;
	Driver driver;
	String startingAddress;
	
	String destinationAddress;
	double kmTravelled;
	double duration;
	String status;
	// :Kreirana  Kreirana-na cekanju  Dodeljena  Odbijena  Prihvacena  Zavrsena
	
	
	public Ride() {
		// requestDatetime = ;
		// customer = new Customer;
		// driver = new Driver;
		startingAddress = "";
		destinationAddress = "";
		kmTravelled = 0;
		duration = 0;
		// status = "Created";
	}
	
	
}
