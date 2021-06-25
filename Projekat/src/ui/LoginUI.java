package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import entities.Customer;
import entities.Dispatcher;
import entities.Driver;
import entities.TaxiService;
import uiMainWindows.CustomerMainWindow;
import uiMainWindows.DispatcherMainWindow;
import uiMainWindows.DriverMainWindow;

//import entities.Roles;

public class LoginUI extends JFrame {
	
	private JLabel userLabel = new JLabel("Korisni\u010Dko ime:");
	private JTextField userText = new JTextField();
	private JLabel passwordLabel = new JLabel("Lozinka:");
	private JPasswordField passwordText = new JPasswordField();
	private JButton loginButton = new JButton("Prijavi me");
	private JComboBox<Roles> rolesComboBox = new JComboBox<Roles>(Roles.values());
	private JLabel success = new JLabel();
	
	private TaxiService taxiSvc;
	
	
	public LoginUI (TaxiService taxiSvc) {
		
		this.taxiSvc = taxiSvc;
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		
		setSize(screenSize.width / 4, screenSize.height / 5);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Prijava na sistem");
		setLocationRelativeTo (null);
		setResizable(false);	
		setLayout(null);
		
		initGUI();
		initActions();
		
		
	}
		
		public void initGUI() {
			
			success.setBounds(10,140,300,25);
			add(success);
			
			userLabel.setBounds(15, 20, 90, 25);
			add(userLabel);
			
			userText.setBounds(130, 20, 165, 25);
			userText.setBackground(Color.lightGray);
			add(userText);
			
			passwordLabel.setBounds(15, 60, 90, 25);
			add(passwordLabel);
			
			passwordText.setBounds(130, 60, 165, 25);
			passwordText.setBackground(Color.lightGray);
			add(passwordText);
			
			rolesComboBox.setBounds(15, 95, 100, 25);
			add(rolesComboBox);
			
			loginButton.setBounds(130, 95, 100, 25);
			loginButton.setBackground(Color.blue);
			loginButton.setForeground(Color.white);
			loginButton.setFont(new Font("", Font.BOLD, 12));
			add(loginButton);
			
			getRootPane().setDefaultButton(loginButton);
			
			// Testni
			userText.setText("katarinaV");
			passwordText.setText("lozinka099");
						
	
		}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TaxiService taxiSvc = new TaxiService();
		LoginUI loginUI = new LoginUI(taxiSvc);
		loginUI.setVisible(true);
		
		
		
	}
	
	public void initActions() {
	
		loginButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String username = userText.getText().trim();
				String pass = new String(passwordText.getPassword());
				
				boolean found = false;
				
				if (username.equals("") || pass.equals(""))
					JOptionPane.showMessageDialog (null, "Molimo, unesite sve podatke!", "Pa\u017Enja", JOptionPane.WARNING_MESSAGE);
				else {
					//TODO Uprostiti, staviti u Login funkciju u drugi fajl, zatim pozvati odavde :
					  
					if(rolesComboBox.getSelectedItem() == Roles.values()[2]) {//TODO videti zasto je error kod poziva Dispatcher.getNonDeletedDispatchers() ! ! ! 
						for (Dispatcher user : taxiSvc.getNonDeletedDispatchers()) {
							if(username.equals(user.getUsername()) && pass.equals(user.getPassword())) {
								found = true;
								DispatcherMainWindow dpMainWin = new DispatcherMainWindow(taxiSvc, user);
								dpMainWin.setVisible(true);
								LoginUI.this.dispose();
								LoginUI.this.setVisible(false);
								break;
							}
						}
//						System.out.println("undel" +taxiSvc.getNonDeletedDispatchers());
//						System.out.println("  all" +taxiSvc.getAllDispatchers());
						
					}else if(rolesComboBox.getSelectedItem() == Roles.values()[1]) {
						for (Driver user : taxiSvc.getNonDeletedDrivers()) {
							if(username.equals(user.getUsername()) && pass.equals(user.getPassword())) {
								found = true;
								DriverMainWindow drMainWin = new DriverMainWindow(taxiSvc, user);
								drMainWin.setVisible(true);
								LoginUI.this.dispose();
								LoginUI.this.setVisible(false);
								break;
							}
						}
					}else if(rolesComboBox.getSelectedItem() == Roles.values()[0]) {
						for (Customer user : taxiSvc.getNonDeletedCustomers()) {
							if(username.equals(user.getUsername()) && pass.equals(user.getPassword())) {
								found = true;
								CustomerMainWindow customerMainWin = new CustomerMainWindow(taxiSvc, user);
								customerMainWin.setVisible(true);
								LoginUI.this.dispose();
								LoginUI.this.setVisible(false);
								break;
							}
						}
					}
							
					if (found == false) 
							JOptionPane.showMessageDialog (null, "Neispravno korisni\u010Dko ime ili lozinka!", "Gre\u0161ka", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
	};

}
