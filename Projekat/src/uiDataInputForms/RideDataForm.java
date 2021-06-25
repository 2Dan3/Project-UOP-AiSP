package uiDataInputForms;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
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
		
		String[] tableHeader = new String[] {"Korisni\u010Dko ime","Ime","Prezime",
				"JMBG","Br.\u010Dlanske karte","Vozilo"};
		
		Object[][] tableData = new Object[nonBusyDrivers.size()][tableHeader.length];
		
		for (int i = 0; i < nonBusyDrivers.size(); i++) {
			Driver dr = nonBusyDrivers.get(i);
			tableData[i][0] = dr.getUsername();
			tableData[i][1] = dr.getName();
			tableData[i][2] = dr.getLastName();
			tableData[i][3] = dr.getJmbg();
			tableData[i][4] = dr.getMembershipCardNum();
//			
			if(dr.getVehicle()!=null)	tableData[i][5] = dr.getVehicle().getMake() + " " + dr.getVehicle().getModel();
			else tableData[i][5] = "/";
		}
		
		tableModel = new DefaultTableModel(tableData, tableHeader);
		driverTable = new JTable(tableModel);
		
		driverTable.setGridColor(Color.blue);
		driverTable.setRowSelectionAllowed(true);
		driverTable.setColumnSelectionAllowed(false);
		driverTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		driverTable.setDefaultEditor(Object.class, null);
		
		driverTable.getTableHeader().setReorderingAllowed(false);
	
		DefaultTableCellRenderer centerRend = new DefaultTableCellRenderer();
		centerRend.setHorizontalAlignment(SwingConstants.CENTER);
		
		for (int i = 0; i < tableHeader.length; i++) {
			driverTable.getColumnModel().getColumn(i).setCellRenderer(centerRend);
		}
		
		JScrollPane scroller = new JScrollPane(driverTable);
		this.add(scroller);	
	
//	GridLayout layout = new GridLayout(2, 1, 5, 5);
//	setLayout(layout);
	
		
	getRootPane().setDefaultButton(btnAssign);
	
//	btnAssign.setSize(70, 50);
//	btnCancel.setSize(70, 50);
	
//	add(driverTable);
//	add(new JLabel());
	add(btnAssign, BorderLayout.SOUTH);
//	add(btnCancel, BorderLayout.SOUTH);
	
	
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
						
						String jmbg = (String)(tableModel.getValueAt(row, 3));
						Driver driver = taxiSvc.findDriver(jmbg);
						
						if(driver.getVehicle().equals(null)) {
							JOptionPane.showMessageDialog(RideDataForm.this, "Odabranom voza\u010Du je potrebno dodeliti automobil, pre dodele vo\u017Enje!", "Gre\u0161ka", JOptionPane.WARNING_MESSAGE);
						}
						else {
							ride.setDriver(driver);
							ride.setStatus(RequestStatus.ASSIGNED);
						
							JOptionPane.showMessageDialog(RideDataForm.this, "Voza\u010D je uspe\u0161no dodeljen za vo\u017Enju - \'" + ride.getRideID() + "\'.", "Voza\u010D :: DODELJEN", JOptionPane.INFORMATION_MESSAGE);
							taxiSvc.saveRides("Rides.csv");
							taxiSvc.saveDrivers("Drivers.csv");
							
							RideDataForm.this.dispose();
							RideDataForm.this.setVisible(false);
						}
						
						
						
					}
			}
		});
	}
}