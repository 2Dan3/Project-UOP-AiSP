package uiMainWindows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import entities.Driver;
import entities.DriverStatus;
import entities.RequestStatus;
import entities.Ride;
import entities.TaxiService;

@SuppressWarnings("serial")
public class DriverMainWindow extends JFrame {
	
	private TaxiService taxiSvc;
	private Driver currentDriver;
	
	private JButton editBtn = new JButton();
	private JButton refreshBtn = new JButton();
	
	private JMenuBar mainMenu = new JMenuBar();
	
	private JMenu carMenu = new JMenu("Automobili");
	private JMenu rideMenu = new JMenu("Vo\u017Enje");
	
	private JMenuItem carsItem = new JMenuItem("Moj automobil");
	private JMenuItem ridesItem1 = new JMenuItem("Dodeljene vo\u017Enje");
	private JMenuItem ridesItem2 = new JMenuItem("Na\u0111i novu vo\u017Enju");

	private JTable table;
	private DefaultTableModel tableModel;
	

	public DriverMainWindow(TaxiService taxiSvc, Driver currentDriver) {
		
		this.taxiSvc = taxiSvc;
		this.currentDriver = currentDriver;
		
		this.setTitle("Voza\u010D :: " + currentDriver.getName() + " " + currentDriver.getLastName());
		this.setSize(1100, 300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setResizable(false);
		
		initGUI();
		initEvents();	
	}
	
	private void initGUI() {
		
		setJMenuBar(mainMenu);
		//entityMenu.setMnemonic('P');
		
		mainMenu.add(carMenu);
		mainMenu.add(rideMenu);
		
		carsItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK));
		carMenu.add(carsItem);
		
		ridesItem1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_DOWN_MASK));
		rideMenu.add(ridesItem1);
		
		ridesItem2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
		rideMenu.add(ridesItem2);
		
		
		// TODO ako ima dodeljenu voznju: editBtn.setBackground(Color.green);
		ImageIcon refreshIcon = new ImageIcon(getClass().getResource("/icons/refresh.gif"));
		refreshBtn.setIcon(refreshIcon);
		
		ImageIcon editIcon = new ImageIcon(getClass().getResource("/icons/edit.gif"));
		editBtn.setIcon(editIcon);

		
		//TODO
		/*addBtn.setSize(60, 20);
		editBtn.setSize(60, 20);
		dltBtn.setSize(60, 20);*/
		
		Box buttonBox = Box.createVerticalBox();
		buttonBox.add(editBtn);
		buttonBox.add(Box.createVerticalStrut(3));
		buttonBox.add(refreshBtn);
		
		/*mainToolbar.setFloatable(false);
		mainToolbar.add(addBtn);
		mainToolbar.add(editBtn);
		mainToolbar.add(dltBtn);
		//TODO mainToolbar.setBounds(0, 600, 1200, 150);*/
		this.add(buttonBox, BorderLayout.WEST);
		taxiSvc.loadInCustomers("Customers.csv");
		taxiSvc.loadInVehicles("Vehicles.csv");
		taxiSvc.loadInRides("Rides.csv");
		ArrayList<Ride> rides = taxiSvc.getAllCurrentDriverRides(currentDriver);
		
		String[] tableHeader = new String[] {"ID vo\u017Enje","Status vo\u017Enje","Vreme zahteva","Po\u010Detna",
				"Destinacija","Trajanje","Kilometra\u017E","Mu\u0161terija","Voza\u010D"};
		
		Object[][] tableData = new Object[rides.size()][tableHeader.length];
		
		for (int i = 0; i < rides.size(); i++) {
			Ride ride = rides.get(i);
			tableData[i][0] = ride.getRideID();
			tableData[i][1] = ride.getStatus();
			tableData[i][2] = ride.getRequestDateTime();
			tableData[i][3] = ride.getStartingAddress();
			tableData[i][4] = ride.getDestinationAddress();
			tableData[i][5] = ride.getDuration();
			tableData[i][6] = ride.getDistanceTraveled();
//			tableData[i][7] = null;
//			tableData[i][8] = null;
			if(ride.getCustomer()!= null) tableData[i][7] = ride.getCustomer().getLastName() +" "+ ride.getCustomer().getName();
			else tableData[i][7] = "/";
			
			if (ride.getDriver()!=null) tableData[i][8] = ride.getDriver().getLastName()+" "+ride.getDriver().getName();
			else tableData[i][8] = "/";
		}
		
		tableModel = new DefaultTableModel(tableData, tableHeader);
		table = new JTable(tableModel);
		
		table.setGridColor(Color.blue);
		table.setRowSelectionAllowed(true);
		table.setColumnSelectionAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setDefaultEditor(Object.class, null);
		
		table.getTableHeader().setReorderingAllowed(false);
	
		DefaultTableCellRenderer centerRend = new DefaultTableCellRenderer();
		centerRend.setHorizontalAlignment(SwingConstants.CENTER);
		
		for (int i = 0; i < tableHeader.length; i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(centerRend);
		}
		
		JScrollPane scroller = new JScrollPane(table);
		this.add(scroller);
		
		for (Ride ride : taxiSvc.getAllRides()) {
			
			if (currentDriver.equals(ride.getDriver() ) && ride.getStatus() == RequestStatus.values()[2]) {
				String roleChoice = (String) JOptionPane.showInputDialog (DriverMainWindow.this, "Preuzeti dodeljenu vo\u017Enju?\nSa adrese: "+ride.getStartingAddress(), "Imate novu vo\u017Enju >>",
					JOptionPane.INFORMATION_MESSAGE, null, new String[]{"Prihvati", "Odbij"}, "Prihvati");
				
				if(roleChoice == "Prihvati") {
					
					currentDriver.setDriverStatus(DriverStatus.values()[0]);
					ride.setStatus(RequestStatus.values()[4]);
					
				}else if(roleChoice == "Odbij") {
					
					ride.setStatus(RequestStatus.values()[3]);
					ride.setDriver(null);
				}
				
				if(roleChoice != null) {

					taxiSvc.saveDrivers("Drivers.csv");
					taxiSvc.saveRides("Rides.csv");
				}
				break;
			}
		}
		
	}
	
	//	TODO
	private void initEvents() {
		refreshBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DriverMainWindow drNewWin = new DriverMainWindow (taxiSvc, currentDriver);
				drNewWin.setVisible(true);

				DriverMainWindow.this.dispose();
				DriverMainWindow.this.setVisible(false);
				
			}
		});
		editBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int row = table.getSelectedRow();
				if(row == -1) {
					JOptionPane.showMessageDialog(null, "Molimo, pre zavr\u0161avanja vo\u017Enje ozna\u010Dite red u tabeli!", "Pa\u017Enja", JOptionPane.WARNING_MESSAGE);
				}else {
					
					long rideId = (long)(tableModel.getValueAt(row, 0));
					Ride ride = taxiSvc.findRide(rideId);
					
					if(ride.getStatus() != RequestStatus.values()[4]) {
						JOptionPane.showMessageDialog(null, "Ne mo\u017Eete zavr\u0161iti vo\u017Enju koja nije u toku.", "Gre\u0161ka", JOptionPane.WARNING_MESSAGE);
					}else {
						RideFinishForm rff = new RideFinishForm(taxiSvc, ride);
						rff.setVisible(true);
					}
				}
				
			};
		
		});
	
	/*public static void main(String[] args) {
		DriverMainWindow drWin = new DriverMainWindow();
		drWin.setVisible(true);
	}*/
	}
}
