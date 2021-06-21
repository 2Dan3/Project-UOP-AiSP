package uiDataInputForms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import entities.AdditionalRequestNotes;
import entities.Customer;
import entities.RequestType;
import entities.Ride;
import entities.TaxiService;

public class RideCreationForm extends JFrame {

	private TaxiService taxiSvc;
	private Customer currentCustomer;
	private Ride selectedRide;
	
	private JComboBox<AdditionalRequestNotes> additionalNoteCBox = new JComboBox<AdditionalRequestNotes>(AdditionalRequestNotes.values());
	
	
	private JButton confirmBtn = new JButton("Potvrdi");
	private JButton cancelBtn = new JButton("Odustani");
	
	
	public RideCreationForm(TaxiService taxiSvc, Customer currentCustomer, Ride selectedRide) {
		
		this.taxiSvc = taxiSvc;
		this.currentCustomer = currentCustomer;
		this.selectedRide = selectedRide;
		
		
		
		initGUI();
		initEvents();
	}


	private void initGUI() {
		
		if (selectedRide != null) {
			if (selectedRide.getRequestType() == RequestType.values()[1]) {
				
			additionalNoteCBox.setEnabled(false);
			}
			
		}
		else {
			
		
		}
		
		
		
		
	}


	private void initEvents() {
		confirmBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO
				
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
}
