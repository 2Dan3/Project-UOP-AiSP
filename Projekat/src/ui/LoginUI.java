package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import entities.Dispatcher;
import entities.Driver;
import uiMainWindows.DispatcherMainWindow;

//import entities.Roles;

public class LoginUI extends JFrame {
	
	private JLabel userLabel = new JLabel("Korisni\u010Dko ime:");
	private JTextField userText = new JTextField();
	private JLabel passwordLabel = new JLabel("Lozinka:");
	private JPasswordField passwordText = new JPasswordField();
	private JButton loginButton = new JButton("Prijavi me");
	private String[] niz = new String[] {"Dimi","Dan"};
	private JComboBox<Roles> rolesComboBox = new JComboBox<Roles>(Roles.values());
	private JLabel success = new JLabel();
	
	
	public LoginUI () {
		
	
		
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
						
	
		}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		LoginUI loginUI = new LoginUI();
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
					  
					if(rolesComboBox.getSelectedItem() == Roles.DISPEÈER) {//TODO videti zasto je error kod poziva Dispatcher.getNonDeletedDispatchers() ! ! ! 
						for (Dispatcher user : Dispatcher.getNonDeletedDispatchers()) {
							if(username.equals(user.getUsername()) && pass.equals(user.getPassword())) {
								found = true;
								DispatcherMainWindow dpMainWin = new DispatcherMainWindow(user);
								dpMainWin.setVisible(true);
								LoginUI.this.dispose();
								LoginUI.this.setVisible(false);
								break;
							}
						}
						System.out.println(Dispatcher.getNonDeletedDispatchers());
						System.out.println(Dispatcher.getAllDispatchers());
					}/*else if(rolesComboBox.getSelectedItem() == Roles.VOZAÈ) {
						for (Driver user : getAllDrivers()) {
							if(user.equals(user.getUsername()) && pass.equals(user.getPassword())) {
								found = true;
								DriverMainWindow dpMainWin = new DriverMainWindow();
							}
						}
					}else if(rolesComboBox.getSelectedItem() == Roles.MUŠTERIJA) {
						for (Customer user : getAllCustomers()) {
							if(user.equals(user.getUsername()) && pass.equals(user.getPassword())) {
								found = true;
								CustomerMainWindow dpMainWin = new CustomerMainWindow();
							}
						}
					}*/
					
					
					
					/*for (String l : niz) {
						if (username.equals(l) && pass.equals(l)) {
							
							success.setText("Prijava :: uspe\u0161na !");
//							frame.setVisible(false);
							//Driver.showDriverMenu();
//							Dispatcher.showDispatcherMenu(frame);
							// Customer.showCustomerMenu();
//							frame.setVisible(false);
							found = true;
							
							break;
						}
					}
					*/		
					if (found == false) 
							JOptionPane.showMessageDialog (null, "Neispravno korisni\u010Dko ime ili lozinka!", "Gre\u0161ka", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
	};

}
