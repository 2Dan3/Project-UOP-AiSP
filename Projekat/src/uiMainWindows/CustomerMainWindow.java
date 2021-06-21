package uiMainWindows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import entities.Customer;
import entities.Dispatcher;
import entities.Ride;
import entities.TaxiService;
import uiDataInputForms.DispatcherDataForm;
import uiDataInputForms.RideCreationForm;

public class CustomerMainWindow extends JFrame {
	
	private Customer currentCustomer;
	private TaxiService taxiSvc;
	
	private JButton newAppRideBtn = new JButton();
	private JButton newPhoneRideBtn = new JButton();
	private JButton refreshBtn = new JButton();
	
	private JMenuBar mainMenu = new JMenuBar();
	
	private JMenu rideMenu = new JMenu("Moje vo\u017Enje");
	
	private JMenuItem viaAppItem = new JMenuItem("Aplikacijom");
	private JMenuItem viaPhoneItem = new JMenuItem("Telefonom");

	private JTable table;
	private DefaultTableModel tableModel;
	
	public CustomerMainWindow(TaxiService taxiSvc, Customer currentCustomer) {
		
		this.taxiSvc = taxiSvc;
		this.currentCustomer = currentCustomer;
		this.setTitle("Korisnik :: " + currentCustomer.getName() +" "+ currentCustomer.getLastName());
		this.setSize(1100, 300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setResizable(false);
		
		initGUI();
		initEvents();	
	}
	
	private void initGUI() {
		
		setJMenuBar(mainMenu);
		
		rideMenu.setMnemonic('V');
		
		mainMenu.add(rideMenu);
		
		viaAppItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK));
		rideMenu.add(viaAppItem);
		rideMenu.addSeparator();
		viaPhoneItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.CTRL_DOWN_MASK));
		rideMenu.add(viaPhoneItem);
		
		/*ImageIcon addIcon = new ImageIcon(getClass().getResource("/icons/add.gif"));
		addBtn.setIcon(addIcon);
		
		ImageIcon editIcon = new ImageIcon(getClass().getResource("/icons/edit.gif"));
		editBtn.setIcon(editIcon);
		
		ImageIcon dltIcon = new ImageIcon(getClass().getResource("/icons/remove.gif"));
		dltBtn.setIcon(dltIcon);
		
		ImageIcon searchIcon = new ImageIcon(getClass().getResource("/icons/search.gif"));
		searchBtn.setIcon(searchIcon);
		
		ImageIcon refreshIcon = new ImageIcon(getClass().getResource("/icons/refresh.gif"));
		refreshBtn.setIcon(refreshIcon);*/
		
		//TODO
		/*addBtn.setSize(60, 20);
		editBtn.setSize(60, 20);
		dltBtn.setSize(60, 20);*/
		Box buttonBox = Box.createVerticalBox();
		buttonBox.add(newAppRideBtn);
		buttonBox.add(Box.createVerticalStrut(3));
		buttonBox.add(newPhoneRideBtn);
		buttonBox.add(Box.createVerticalStrut(3));
		buttonBox.add(refreshBtn);
		
		this.add(buttonBox, BorderLayout.WEST);

		ArrayList<Ride> rides = taxiSvc.getOngoingRides();
		
		String[] tableHeader = new String[] {"ID Vo\u017Enje","Status","Vreme zahteva","Po\u010Detak","Destinacija",
				"Trajanje","Kilometra\u017Ea","Mu\u0161terija","Voza\u010D"};
		
		Object[][] tableData = new Object[rides.size()][tableHeader.length];
		
/* Testni Datum */			Calendar today = Calendar.getInstance();
		today.set(Calendar.HOUR_OF_DAY, 0);
		
		for (int i = 0; i < rides.size(); i++) {
			Ride ride = rides.get(i);
			tableData[i][0] = ride.getRideID();
			tableData[i][1] = ride.getStatus();
			tableData[i][2] = null;
			//TODO tableData[i][2] = ride.getRequestDateTime().toString();
			tableData[i][3] = ride.getStartingAddress();
			tableData[i][4] = ride.getDestinationAddress();
			tableData[i][5] = ride.getDuration();
			tableData[i][6] = ride.getDistanceTraveled();
			tableData[i][7] = null;
			tableData[i][8] = null;
			//TODO tableData[i][7] = ride.getCustomer();
			//TODO tableData[i][8] = ride.getDriver();
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
		
	}
	
	
	private void initEvents() {
		
		newAppRideBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RideCreationForm rcf = new RideCreationForm(taxiSvc, currentCustomer, null);
				rcf.setVisible(true);
				
			}
		});
	}
}
