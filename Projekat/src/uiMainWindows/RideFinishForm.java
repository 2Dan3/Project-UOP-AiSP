package uiMainWindows;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import entities.RequestStatus;
import entities.Ride;
import entities.TaxiService;

public class RideFinishForm extends JFrame {
	
	private TaxiService taxiSvc;
	private Ride selectedRide;
	
	private JLabel lblKm = new JLabel("Unesite kilometra\u017Eu >> ");
	private JTextField txtKm = new JTextField();
	
	private JLabel lblTime = new JLabel("Unesite vreme putovanja >> ");
	private JTextField txtTime = new JTextField();
	
	private JButton finishBtn = new JButton("Zavr\u0161i vo\u017Enju");
	
	
	public RideFinishForm(TaxiService taxiSvc, Ride selectedRide) {
		
		this.taxiSvc = taxiSvc;
		this.selectedRide = selectedRide;
		
		setTitle("Zavr\u0161ite vo\u017Enju :: ");
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize.width / 5, screenSize.height / 7);

		setAlwaysOnTop(true);
		setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setResizable(false);
		
		initGUI();
		initEvents();
	}


	private void initGUI() {
		
		getRootPane().setDefaultButton(finishBtn);
		
		GridLayout layout = new GridLayout(3, 2, 4, 4);
		setLayout(layout);
		
		add(lblKm);
		add(txtKm);
		add(lblTime);
		add(txtTime);
		add(new JLabel());
		add(finishBtn);
		
	}


	private void initEvents() {

		finishBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(txtKm.getText().isBlank() || txtTime.getText().isBlank() ) {
					JOptionPane.showMessageDialog(RideFinishForm.this, "Da biste zavr\u0161ili vo\u017Enju, molimo da unesete tra\u017Eene podatke!", 
						"Gre\u0161ka", JOptionPane.WARNING_MESSAGE);
				}else {
					selectedRide.setDuration(Double.parseDouble(txtTime.getText()));
					selectedRide.setDistanceTraveled(Double.parseDouble(txtKm.getText()));
					selectedRide.setStatus(RequestStatus.values()[5]);
					JOptionPane.showMessageDialog(RideFinishForm.this, "Vo\u017Enja je uspe\u0161no zavr\u0161ena.", 
							"Snimanje :: uspe\\u0161no", JOptionPane.INFORMATION_MESSAGE);
					taxiSvc.saveRides("Rides.csv");
					
					RideFinishForm.this.dispose();
					RideFinishForm.this.setVisible(false);
				}
			}
		});
		
	}
}
