package uiDataInputForms;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import entities.Department;
import entities.Dispatcher;
import entities.Driver;
import entities.Gender;
import entities.RequestStatus;
import entities.Ride;

public class RideDataForm extends JFrame {
	
	private Ride ride;
	
	JTable driverTable;
	DefaultTableModel tableModel;
	private JButton btnCancel = new JButton("Odustani");
	private JButton btnAssign = new JButton("Dodeli voza\u010Da");


	// TODO Fields init - taxiSvc ? ? ?
	
	
	public RideDataForm(Ride ride) {
		this.ride = ride;
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
	// TODO Auto-generated method stub
	/*if(dispatcher != null) {*/
		
	/*}*/
		ArrayList<Driver> nonBusyDrivers = TaxiService.getNonBusyDrivers();
		
		String[] tableHeader = new String[] {"Korisni\u010Dko ime","Lozinka","Ime","Prezime",
				"JMBG","Pol","Telefon","Adresa","Br.Tel. linije","Odeljenje","Plata"};
		
		Object[][] tableData = new Object[nonBusyDrivers.size()][tableHeader.length];
		
		for (int i = 0; i < nonBusyDrivers.size(); i++) {
			Driver dr = nonBusyDrivers.get(i);
			tableData[i][0] = dr.getUsername();
			tableData[i][1] = dr.getName();
			tableData[i][2] = dr.getLastName();
			tableData[i][3] = dr.getJmbg();
			tableData[i][4] = dr.getMembershipCardNum();
			tableData[i][5] = dr.getVehicle().getModel(); // Get make + get model concat ?
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
	
	GridLayout layout = new GridLayout(2, 2, 8, 8);
	setLayout(layout);
	
	getRootPane().setDefaultButton(btnAssign);
	
	add(driverTable);
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
						JOptionPane.showMessageDialog(null, "Molimo, pre dodele ozna\u010Dite voza\u010Da u tabeli!", "Pa\u017Enja", JOptionPane.WARNING_MESSAGE);
					}else {
						
						long jmbg = (long) (tableModel.getValueAt(row, 3));
						ride.setDriver(findDriver(jmbg));
						ride.setStatus(RequestStatus.ASSIGNED);
						JOptionPane.showMessageDialog(null, "Voza\u010D je uspe\u0161no dodeljen za vo\017Enju - \'" + ride.getRideID() + "\'.", "Voza\u010D :: DODELJEN", JOptionPane.INFORMATION_MESSAGE);
					//TODO uncomment: taxiSvc.saveDispatchers(ProdavnicaMain.PRODAVCI_FAJL);
						
						RideDataForm.this.dispose();
						RideDataForm.this.setVisible(false);
					}
			}
		});
	}
}