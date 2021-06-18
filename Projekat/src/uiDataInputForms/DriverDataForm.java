package uiDataInputForms;

import entities.Driver;
import entities.DriverStatus;
import entities.TaxiService;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
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

import entities.Department;
import entities.Dispatcher;
import entities.Gender;
import entities.TaxiService;
import entities.ValidatorClass;

public class DriverDataForm extends JFrame{
	
	private TaxiService taxiSvc;
	private Driver driver;
	
	private JLabel lblUsername = new JLabel("  Korisni\u010Dko ime >>");
		private JTextField txtUsername= new JTextField(20);
	private JLabel lblPass = new JLabel("  Lozinka >>");
		private JPasswordField txtPass = new JPasswordField(20);
	private JLabel lblName = new JLabel("  Ime >> ");
		private JTextField txtName = new JTextField(20);
	private JLabel lblLastName = new JLabel("  Prezime >> ");
		private JTextField txtLastName = new JTextField(20);
	private JLabel lblJmbg = new JLabel("  JMBG >> ");
		private JTextField txtJmbg = new JTextField(13);
	private JLabel lblGender = new JLabel("  Pol >> ");
		private JComboBox<Gender> genderCBox = new JComboBox<Gender>(Gender.values());
	private JLabel lblPhone = new JLabel("  Telefon >> ");
		private JTextField txtPhone = new JTextField(13);
	private JLabel lblAddress = new JLabel("  Adresa >> ");
		private JTextField txtAddress = new JTextField();
	private JButton assignVehicleBtn = new JButton("  Automobil >> ");// TODO umesto texta, ImageIcon crvenog/zutog autica
	
	private JLabel lblMembershipCardNum = new JLabel("  Br.\u010Dlanske karte >>");
		private JTextField txtMembershipCardNum = new JTextField();
	private JLabel lblSalary = new JLabel("  Plata >> ");
		private JTextField txtSalary = new JTextField();
	
	private JButton btnSave = new JButton("Sa\u010Duvaj");
	private JButton btnCancel = new JButton("Odustani");

	
		
	public DriverDataForm(TaxiService taxiSvc, Driver driver) {
		this.taxiSvc = taxiSvc;
		this.driver = driver;
		
		if(driver == null) {
			setTitle("Dodavanje voza\u010Da");
		}else {
			setTitle("Izmena podataka - " + driver.getUsername());
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

		if(driver != null) {
			fillInTextFieldValues();
			txtJmbg.setBackground(Color.LIGHT_GRAY);
			txtJmbg.setDisabledTextColor(Color.black);
			txtJmbg.setEnabled(false);
		}
		
		GridLayout layout = new GridLayout(13, 2, 5, 5);
		setLayout(layout);
		
		txtJmbg.setFont(new Font("", Font.ITALIC, 12));
		
		getRootPane().setDefaultButton(btnSave);
		
		add(lblUsername);
		add(txtUsername);
		add(lblPass);
		add(txtPass);
		add(lblName);
		add(txtName);
		add(lblLastName);
		add(txtLastName);
		add(lblJmbg);
		add(txtJmbg);
		add(lblGender);
		add(genderCBox);
		add(lblPhone);
		add(txtPhone);
		add(lblAddress);
		add(txtAddress);
		add(new JLabel());
		add(assignVehicleBtn);
		add(lblMembershipCardNum);
		add(txtMembershipCardNum);
		add(lblSalary);
		add(txtSalary);
		add(new JLabel());
		add(btnSave);
		add(new JLabel());
		add(btnCancel);
		
	}

	private void initEvents() {
		// TODO Auto-generated method stub
		btnSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				/* TODO if (ValidatorClass.validateFields()) {*/
				
					String username = txtUsername.getText().trim();
					String pass = new String(txtPass.getPassword());
					String name = txtName.getText().trim();
					String lastName = txtLastName.getText().trim();
					String jmbgInput = txtJmbg.getText().trim();
					Gender gen = (Gender)genderCBox.getSelectedItem();
					String ph = txtPhone.getText().trim();
					String address = txtAddress.getText().trim();
//TODO funkcija koja vraca Vehicle obj iz reda izabranog u tabeli automobila, prikazanoj klikom na dugme "Automobili"			Vehicle vehicle = txtPhoneLineNum.getText().trim();
					String membershipCardNum = txtMembershipCardNum.getText().trim();
					String salaryInput = txtSalary.getText().trim();
					
					String warningMsg;
					if ( !( warningMsg = ValidatorClass.validateFields(username, pass, name, lastName, jmbgInput, ph, address, salaryInput) ).isBlank() ) {
						
						JOptionPane.showMessageDialog(DriverDataForm.this, warningMsg, "Pa\u017Enja, potrebno je da ispravite gre\u0161ke: \n", JOptionPane.WARNING_MESSAGE);
					}
					else{
						name = ValidatorClass.capitalize( name );
						lastName = ValidatorClass.capitalize( lastName );
						address = ValidatorClass.capitalize( address );
						long jmbg = Long.valueOf(jmbgInput);
						double salary = Double.parseDouble(salaryInput);
						
						if(driver != null) { // EDITING obj:
							driver.setUsername(username);
							driver.setPassword(pass);
							driver.setName(name);
							driver.setLastName(lastName);
							driver.setJmbg(jmbg);
							driver.setGender(gen);
							driver.setPhoneNum(ph);
							driver.setAddress(address);
							//TODO	driver.setVehicle(vehicle);
							driver.setMembershipCardNum(membershipCardNum);
							driver.setSalary(salary);
							
						}else { // ADDING obj:
							
							Driver newDriver = new Driver(username, pass, name, lastName, jmbg, gen, ph, address, membershipCardNum, null, DriverStatus.values()[1], salary, false);
							taxiSvc.addNew(newDriver);
					}
					
					taxiSvc.saveDrivers("Drivers.csv");
					DriverDataForm.this.dispose();
					DriverDataForm.this.setVisible(false);
					//TODO uncomment kad nadjem nacin da radi odavde: refreshWindow();
			}		}
		});
		
		
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DriverDataForm.this.dispose();
				DriverDataForm.this.setVisible(false);
			}
		});
		
	}
	
	
	private void fillInTextFieldValues() {
			txtUsername.setText(driver.getUsername());
			txtPass.setText(driver.getPassword());
			txtName.setText(driver.getName());
			txtLastName.setText(driver.getLastName());
			txtJmbg.setText(String.valueOf(driver.getJmbg()));
			genderCBox.setSelectedItem(driver.getGender());
			txtPhone.setText(driver.getPhoneNum());
			txtAddress.setText(driver.getAddress());
			txtMembershipCardNum.setText(driver.getMembershipCardNum());
			txtSalary.setText(String.valueOf(driver.getSalary()));
		}
	
}
