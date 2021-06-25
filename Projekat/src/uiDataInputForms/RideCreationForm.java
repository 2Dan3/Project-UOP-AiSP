package uiDataInputForms;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import entities.AdditionalRequestNotes;
import entities.Customer;
import entities.Driver;
import entities.RequestStatus;
import entities.RequestType;
import entities.Ride;
import entities.TaxiService;
import entities.ValidatorClass;

public class RideCreationForm extends JFrame {

	private TaxiService taxiSvc;
	private Customer currentCustomer;
	private Ride selectedRide;
	
/* ? -*/	private JLabel lblRideID = new JLabel("  ID vo\u017Enje >> ");
	private JTextField txtRideID = new JTextField();
	
	private JLabel lblDate = new JLabel("  Datum >> ");
	private JTextField txtDate = new JTextField();
	private JLabel lblStartingAddress = new JLabel("  Po\u010Detna >> ");
	private JTextField txtStartingAddress = new JTextField();
	private JLabel lblDestination = new JLabel("  Destinacija >> ");
	private JTextField txtDestination = new JTextField();
	private JLabel lblDuration = new JLabel("  Trajanje >> ");
	private JTextField txtDuration = new JTextField();
	private JLabel lblDistance = new JLabel("  Kilometra\u017Ea >> ");
	private JTextField txtDistance = new JTextField();
	private JLabel lblDriver = new JLabel("  Voza\u010D >> ");
	private JTextField txtDriver = new JTextField();
	private JLabel lblRating = new JLabel("  Ocena >> ");
	private JTextField txtRating = new JTextField();
	
	private JButton confirmBtn = new JButton("Potvrdi");
	private JButton cancelBtn = new JButton("Odustani");
	
	
	public RideCreationForm(TaxiService taxiSvc, Customer currentCustomer, Ride selectedRide) {
		
		this.taxiSvc = taxiSvc;
		this.currentCustomer = currentCustomer;
		this.selectedRide = selectedRide;
		
		if (selectedRide.equals(null)) {
			setTitle("Kreiranje zahteva :: "+currentCustomer.getName() +" "+ currentCustomer.getLastName());
		}else {
			setTitle("Ocenjivanje voza\u010Da :: "+selectedRide.getDriver().getLastName() +" "+ selectedRide.getDriver().getName());
		}
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize.width / 5, (int)(screenSize.height / 2.5));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setAlwaysOnTop(true);
		setLocationRelativeTo(null);
		
		initGUI();
		initEvents();
	}


	private void initGUI() {
		
		getRootPane().setDefaultButton(confirmBtn);
		GridLayout layout = new GridLayout(8, 2, 5, 5);
		setLayout(layout);
		
		add(lblDate);
		add(txtDate);
		add(lblDistance);
		add(txtDistance);
		add(lblDuration);
		add(txtDuration);
		add(lblDriver);
		add(txtDriver);
		add(lblRating);
		add(txtRating);
		add(lblStartingAddress);
		add(txtStartingAddress);
		add(lblDestination);
		add(txtDestination);
		add(confirmBtn);
		add(cancelBtn);
		
		txtDate.setEnabled(false);
		txtDistance.setEnabled(false);
		txtDuration.setEnabled(false);
		txtDriver.setEnabled(false);
		
		if (selectedRide != null) {
			
			fillInTextFieldValues();
			
			txtStartingAddress.setEnabled(false);
			txtDestination.setEnabled(false);
			
		}else {
			txtRating.setEnabled(false);
			txtDate.setText(new SimpleDateFormat("dd.MM.yyyy").format(new GregorianCalendar().getTime()));
		}
	}



	private void initEvents() {
		confirmBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 
				String warningMsg;
				if(selectedRide.equals(null)) {
					
					String startingAddress = txtStartingAddress.getText();
					String destination = txtDestination.getText();
					
					if(startingAddress.isBlank() || destination.isBlank() ) {
						JOptionPane.showMessageDialog(RideCreationForm.this, "Molimo, unesite sve podatke!", "Pa\u017Enja", JOptionPane.WARNING_MESSAGE);
					}else {
						Ride newRide = new Ride(new SimpleDateFormat("dd.MM.yyyy").format(new GregorianCalendar().getTime()), startingAddress, destination, currentCustomer,
								null, 0, 0, RequestStatus.values()[0], RequestType.values()[0],
// TODO
						TaxiService.STARTINGPRICE, TaxiService.PRICEPERKM, taxiSvc.generateNew("rideID");
						taxiSvc.addNew(newRide);
					}
				}
				
				else {
					String rating = txtRating.getText();
					
					if (rating!="1" && rating!="2" && rating!="3" && rating!="4" && rating!="5")
						JOptionPane.showMessageDialog(RideCreationForm.this, "Molimo, ocenite vo\u017Enju ocenom 1-5!", "Pa\u017Enja", JOptionPane.WARNING_MESSAGE);
					else {
						selectedRide.setRating(Integer.parseInt(rating));
						selectedRide.getDriver().calculateAvgRating();
						taxiSvc.saveDrivers("Drivers.csv");
					}
				}
				
				taxiSvc.saveRides("Rides.csv");
				RideCreationForm.this.dispose();
				RideCreationForm.this.setVisible(false);
			}
		});
		cancelBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RideCreationForm.this.dispose();
				RideCreationForm.this.setVisible(false);
			}
		});
		
	}
	
	
	
	private void fillInTextFieldValues() {
		
		txtDate.setText(selectedRide.getRequestDateTime());
		txtDistance.setText(String.valueOf(selectedRide.getDistanceTraveled()));
		txtDuration.setText(String.valueOf(selectedRide.getDuration()));
		txtDriver.setText(selectedRide.getDriver().getName() +" "+ selectedRide.getDriver().getLastName());
		txtStartingAddress.setText(selectedRide.getStartingAddress());
		txtDestination.setText(selectedRide.getDestinationAddress());
	}
}
