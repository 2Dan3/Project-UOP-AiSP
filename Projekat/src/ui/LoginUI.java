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

//import entities.Roles;

public class LoginUI extends JFrame {
	
	private JLabel userLabel = new JLabel("Korisnièko ime:");
	private JTextField userText = new JTextField();
	private JLabel passwordLabel = new JLabel("Lozinka:");
	private JPasswordField passwordText = new JPasswordField();
	private JButton loginButton = new JButton("Prijavi me");
	private JLabel success;
	private String[] niz = new String[] {"Dimi","Dan"};
	private JComboBox<Roles> rolesComboBox = new JComboBox<Roles>(Roles.values());
	
	JPanel panel;
	JFrame frame;
	
	
	public LoginUI () {
		
		panel = new JPanel();
		
		frame = new JFrame();
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		
		frame.setSize(screenSize.width / 4, screenSize.height / 5);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.add(panel);
		frame.setTitle("Prijava na sistem");
		frame.setLocationRelativeTo (null);
		frame.setResizable (false);
		
		panel.setLayout(null);
		success = new JLabel();
		success.setBounds(10,110,300,25);
		panel.add(success);
		
		initGUI();
		initActions();
		
		
	}
		
		public void initGUI() {
		
			userLabel.setBounds(15, 20, 90, 25);
			panel.add(userLabel);
			
			userText.setBounds(130, 20, 165, 25);
			userText.setBackground(Color.lightGray);
			panel.add(userText);
			
			passwordLabel.setBounds(15, 60, 90, 25);
			panel.add(passwordLabel);
			
			passwordText.setBounds(130, 60, 165, 25);
			passwordText.setBackground(Color.lightGray);
			panel.add(passwordText);
			
			rolesComboBox.setBounds(15, 95, 100, 25);
			panel.add(rolesComboBox);
			
			loginButton.setBounds(130, 95, 100, 25);
			loginButton.setBackground(Color.blue);
			loginButton.setForeground(Color.white);
			loginButton.setFont(new Font("", Font.BOLD, 12));
			panel.add(loginButton);			
						
	
		}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		LoginUI loginUI = new LoginUI();
		loginUI.frame.setVisible(true);
		
		
		
	}
	
	public void initActions() {
	
		loginButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String user = userText.getText();
				String pass = passwordText.getText();
				boolean found = false;
				
				if (user.equals("") || pass.equals(""))
					JOptionPane.showMessageDialog (null, "Molimo, unesite sve podatke!", "Pažnja", JOptionPane.WARNING_MESSAGE);
				else {
					for (String l : niz) {
						if (user.equals(l) && pass.equals(l)) {
							
							success.setText("Login successful !");
							frame.setVisible(false);
							//Driver.showDriverMenu();
							Dispatcher.showDispatcherMenu(frame);
							// Customer.showCustomerMenu();
							frame.setVisible(false);
							found = true;
							
							break;
						}
					}
							
					if (found == false) 
							JOptionPane.showMessageDialog (null, "Neispravno korisnièko ime ili lozinka!", "Greška", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
	};

}
