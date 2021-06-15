package uiDataInputForms;

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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import entities.Department;
import entities.Dispatcher;
import entities.Gender;
import entities.ValidatorClass;


public class DispatcherDataForm extends JFrame {

	private JLabel lblUsername = new JLabel("  Korisni\u010Dko ime >>");
		private JTextField txtUsername= new JTextField(20);
	private JLabel lblPass = new JLabel("  Lozinka >>");
		private JPasswordField txtPass = new JPasswordField(20);
	private JLabel lblName = new JLabel("  Ime >> ");
		private JTextField txtName = new JTextField(20);
	private JLabel lblLastName = new JLabel("  Prezime >> ");
		private JTextField txtLastName = new JTextField(20);
	private JLabel lblJmbg = new JLabel("  JMBG >> ");
//TODO make read-only txtJMBG
		private JTextField txtJmbg = new JTextField(13);
	private JLabel lblGender = new JLabel("  Pol >> ");
		private JComboBox<Gender> genderCBox = new JComboBox<Gender>(Gender.values());
	private JLabel lblPhone = new JLabel("  Telefon >> ");
		private JTextField txtPhone = new JTextField(13);
	private JLabel lblAddress = new JLabel("  Adresa >> ");
		private JTextField txtAddress = new JTextField();
	private JLabel lblPhoneLineNum = new JLabel("  Br.Tel. linije >> ");
		private JTextField txtPhoneLineNum = new JTextField();
	private JLabel lblDepartment = new JLabel("  Odeljenje >> ");
		private JComboBox<Department> departmentCBox = new JComboBox<Department>(Department.values());
	private JLabel lblSalary = new JLabel("  Plata >> ");
		private JTextField txtSalary = new JTextField();
		
	private JButton btnSave = new JButton("Sa\u010Duvaj");
	private JButton btnCancel = new JButton("Odustani");
	
	//TODO private TaxiService taxiSvc;
	//TODO 
	private Dispatcher dispatcher;
	
					// TaxiService taxiSvc, Dispatcher dispatcher
	public DispatcherDataForm(Dispatcher dispatcher) {
//		this.taxiSvc = taxiSvc;
		this.dispatcher = dispatcher;
		if(dispatcher == null) {
			setTitle("Dodavanje dispe\u010Dera");
		}else {
			setTitle("Izmena podataka - " + dispatcher.getUsername());
		}
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize.width / 5, (int)(screenSize.height / 2.5));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setAlwaysOnTop(true);
		setLocationRelativeTo(null);
		
		initGUI();
		initEvents();
//		pack();
	}

	private void initGUI() {
		// TODO Auto-generated method stub
		if(dispatcher != null) {
			fillInTextFieldValues();
			txtJmbg.setBackground(Color.LIGHT_GRAY);
			txtJmbg.setDisabledTextColor(Color.black);
			//TODO remove this custom jmbg
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
		add(lblPhoneLineNum);
		add(txtPhoneLineNum);
		add(lblSalary);
		add(txtSalary);
		add(lblDepartment);
		add(departmentCBox);
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
				
				//TODO pozivi funkcije PomocnaKlasa.capitalize();
					String username = txtUsername.getText().trim();
					String pass = new String(txtPass.getPassword());
					String name = ValidatorClass.capitalize( txtName.getText().trim() );
					String lastName = ValidatorClass.capitalize( txtLastName.getText().trim() );
					long jmbg = Long.parseLong(txtJmbg.getText().trim());
					Gender gen = (Gender)genderCBox.getSelectedItem();
					String ph = txtPhone.getText().trim();
					String address = ValidatorClass.capitalize( txtAddress.getText().trim() );
					String phLnNum = txtPhoneLineNum.getText().trim();
					double salary = Double.parseDouble(txtSalary.getText().trim());
					Department dept = (Department)departmentCBox.getSelectedItem();
					
					if (ValidatorClass.validateFields(username, pass, name, lastName, jmbg, ph, address) == 0) {
						
						if(dispatcher != null) { // EDITING obj:
						dispatcher.setUsername(username);
						dispatcher.setPassword(pass);
						dispatcher.setName(name);
						dispatcher.setLastName(lastName);
						dispatcher.setJmbg(jmbg);
						dispatcher.setGender(gen);
						dispatcher.setPhoneNum(ph);
						dispatcher.setAddress(address);
						dispatcher.setPhoneLineNum(phLnNum);
						dispatcher.setSalary(salary);
						dispatcher.setDept(dept);
					}else { // ADDING obj:
						Dispatcher newDisp = new Dispatcher(username, pass, name, lastName, jmbg, gen, ph, address, salary, phLnNum, dept, false);
						//TODO uncomment: 
						Dispatcher.addNew(newDisp);
					}
					
					//TODO uncomment: 
					Dispatcher.saveDispatchers("Dispatchers.csv");
					DispatcherDataForm.this.dispose();
					DispatcherDataForm.this.setVisible(false);
					//TODO uncomment kad nadjem nacin da radi odavde: refreshWindow();
			}		}
		});
		
		
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DispatcherDataForm.this.dispose();
				DispatcherDataForm.this.setVisible(false);
			}
		});
		
	}
	
	
	private void fillInTextFieldValues() {
			txtUsername.setText(dispatcher.getUsername());
			txtPass.setText(dispatcher.getPassword());
			txtName.setText(dispatcher.getName());
			txtLastName.setText(dispatcher.getLastName());
			txtJmbg.setText(String.valueOf(dispatcher.getJmbg()));
			genderCBox.setSelectedItem(dispatcher.getGender());
			txtPhone.setText(dispatcher.getPhoneNum());
			txtAddress.setText(dispatcher.getAddress());
			txtPhoneLineNum.setText(dispatcher.getPhoneLineNum());
			txtSalary.setText(String.valueOf(dispatcher.getSalary()));
			departmentCBox.setSelectedItem(dispatcher.getDept());
		}
	
}
