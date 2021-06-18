package uiMainWindows;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import entities.Dispatcher;
import entities.Driver;
import entities.Ride;
import entities.TaxiService;

public class DriverMainWindow extends JFrame {
	
	private TaxiService taxiSvc;
	private Driver currentDriver;
	
	private JButton addBtn = new JButton();
	private JButton editBtn = new JButton();
	
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
		// TODO initEvents();	
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
		
		ImageIcon addIcon = new ImageIcon(getClass().getResource("/icons/add.gif"));
		addBtn.setIcon(addIcon);
		
		ImageIcon editIcon = new ImageIcon(getClass().getResource("/icons/edit.gif"));
		editBtn.setIcon(editIcon);
		// TODO ako ima dodeljenu voznju: editBtn.setBackground(Color.green);

		
		//TODO
		/*addBtn.setSize(60, 20);
		editBtn.setSize(60, 20);
		dltBtn.setSize(60, 20);*/
		
		Box buttonBox = Box.createVerticalBox();
		buttonBox.add(addBtn);
		buttonBox.add(Box.createVerticalStrut(3));
		buttonBox.add(editBtn);
		
		/*mainToolbar.setFloatable(false);
		mainToolbar.add(addBtn);
		mainToolbar.add(editBtn);
		mainToolbar.add(dltBtn);
		//TODO mainToolbar.setBounds(0, 600, 1200, 150);*/
		this.add(buttonBox, BorderLayout.WEST);
		
		ArrayList<Ride> rides = taxiSvc.getAllRides();
		
		String[] tableHeader = new String[] {"Korisni\u010Dko ime","Lozinka","Ime","Prezime",
				"JMBG","Pol","Telefon","Adresa","Br.Tel. linije","Odeljenje","Plata"};
		
		Object[][] tableData = new Object[rides.size()][tableHeader.length];
		
		for (int i = 0; i < rides.size(); i++) {
			Ride ride = rides.get(i);
			tableData[i][0] = ride.getUsername();
			tableData[i][1] = ride.getPassword();
			tableData[i][2] = ride.getName();
			tableData[i][3] = ride.getLastName();
			tableData[i][4] = ride.getJmbg();
			tableData[i][5] = ride.getGender().toString();
			tableData[i][6] = ride.getPhoneNum();
			tableData[i][7] = ride.getAddress();
			tableData[i][8] = ride.getPhoneLineNum();
			tableData[i][9] = ride.getDept().toString();
			tableData[i][10] = ride.getSalary();
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
	
	/*public static void main(String[] args) {
		DriverMainWindow drWin = new DriverMainWindow();
		drWin.setVisible(true);
	}*/
}
