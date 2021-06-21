package uiDataInputForms;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import entities.Driver;
import entities.DriverStatus;
import entities.RequestStatus;
import entities.Ride;
import entities.TaxiService;

public class RideDataForm extends JFrame {
	
	private Ride ride;
	private TaxiService taxiSvc;
	
	private JTable driverTable;
	private DefaultTableModel tableModel;
	private JButton btnCancel = new JButton("Odustani");
	private JButton btnAssign = new JButton("Dodeli voza\u010Da");

	
	
	public RideDataForm(TaxiService taxiSvc, Ride ride) {
		
		this.taxiSvc = taxiSvc;
		this.ride = ride;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize( (int)(screenSize.width / 2.5), (int)(screenSize.height / 2.5) );
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setAlwaysOnTop(true);
		setLocationRelativeTo(null);
		
		initGUI();
		initEvents();
}

	private void initGUI() {
	// TODO Auto-generated method stub
	/*if(dispatcher != null) {*/
		
	/*}*/
		ArrayList<Driver> nonBusyDrivers = taxiSvc.getNonBusyDrivers();
		
		String[] tableHeaderr = new String[] {"Korisni\u010Dko ime","Ime","Prezime",
				"JMBG","Br.\u010Dlanske karte","Vozilo"};
		
		Object[][] tableData = new Object[nonBusyDrivers.size()][tableHeaderr.length];
		
		for (int i = 0; i < nonBusyDrivers.size(); i++) {
			Driver dr = nonBusyDrivers.get(i);
			tableData[i][0] = dr.getUsername();
			tableData[i][1] = dr.getName();
			tableData[i][2] = dr.getLastName();
			tableData[i][3] = dr.getJmbg();
			tableData[i][4] = dr.getMembershipCardNum();
			tableData[i][5] = null;
			//TODO tableData[i][5] = dr.getVehicle().getModel() + " " + dr.getVehicle().getMake();
		}
		
		tableModel = new DefaultTableModel(tableData, tableHeaderr);
		driverTable = new JTable(tableModel);
		
		driverTable.setGridColor(Color.blue);
		driverTable.setRowSelectionAllowed(true);
		driverTable.setColumnSelectionAllowed(false);
		driverTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		driverTable.setDefaultEditor(Object.class, null);
		
		driverTable.getTableHeader().setReorderingAllowed(false);
	
		DefaultTableCellRenderer centerRend = new DefaultTableCellRenderer();
		centerRend.setHorizontalAlignment(SwingConstants.CENTER);
		
		for (int i = 0; i < tableHeaderr.length; i++) {
			driverTable.getColumnModel().getColumn(i).setCellRenderer(centerRend);
		}
		
		JScrollPane scroller = new JScrollPane(driverTable);
		this.add(scroller);	
	
	BorderLayout layout = new BorderLayout();
	setLayout(layout);
	
	getRootPane().setDefaultButton(btnAssign);
	
	btnAssign.setSize(70, 50);
	btnCancel.setSize(70, 50);
	
	add(driverTable, BorderLayout.NORTH);
//	add(new JLabel());
	add(btnAssign);
	add(btnCancel);
	
	
	}
	
	
	private void initEvents() {
		btnCancel .addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RideDataForm.this.dispose();
				RideDataForm.this.setVisible(false);
			}
		});
		
		btnAssign.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					
					int row = driverTable.getSelectedRow();
					if(row == -1) {
						JOptionPane.showMessageDialog(RideDataForm.this, "Molimo, pre dodele ozna\u010Dite voza\u010Da u tabeli!", "Pa\u017Enja", JOptionPane.WARNING_MESSAGE);
					}else {
						
						long jmbg = (long) (tableModel.getValueAt(row, 3));
						Driver driver = taxiSvc.findDriver(jmbg);
						
						ride.setDriver(driver);
						ride.setStatus(RequestStatus.ASSIGNED);
						driver.setDriverStatus(DriverStatus.values()[0]);
						
						JOptionPane.showMessageDialog(RideDataForm.this, "Voza\u010D je uspe\u0161no dodeljen za vo\u017Enju - \'" + ride.getRideID() + "\'.", "Voza\u010D :: DODELJEN", JOptionPane.INFORMATION_MESSAGE);
					//TODO uncomment: 
						taxiSvc.saveRides("Rides.csv");
						taxiSvc.saveDrivers("Drivers.csv");
						
						RideDataForm.this.dispose();
						RideDataForm.this.setVisible(false);
					}
			}
		});
	}
}