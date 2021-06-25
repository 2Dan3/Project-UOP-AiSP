package uiMainWindows;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Calendar;

import entities.TaxiService;
import uiDataInputForms.DispatcherDataForm;
import uiDataInputForms.RideDataForm;
import uiOperatingWindows.DriverOperativeWindow;
import entities.Dispatcher;
import entities.RequestStatus;
import entities.Ride;

	public class RideMainWindow extends JFrame {
		
		private Dispatcher currentDispatcher;
		private TaxiService taxiSvc;
		private JButton addBtn = new JButton();
		private JButton editBtn = new JButton();
		private JButton dltBtn = new JButton();
		private JButton searchBtn = new JButton();
		private JButton refreshBtn = new JButton();
		
		private JMenuBar mainMenu = new JMenuBar();
		
		private JMenu personMenu = new JMenu("Korisnici");
		private JMenu carMenu = new JMenu("Vozni park");
		private JMenu rideMenu = new JMenu("Vo\u017Enje");
		
		private JMenuItem dispatchersItem = new JMenuItem("Dispe\u010Deri");
		private JMenuItem driversItem = new JMenuItem("Voza\u010Di");
		private JMenuItem carsItem = new JMenuItem("Automobili");
		private JMenuItem ridesItem = new JMenuItem("Vo\u017Enje");

		private JTable table;
		private DefaultTableModel tableModel;
		
		public RideMainWindow(TaxiService taxiSvc, Dispatcher currentDispatcher) {
			
			this.currentDispatcher = currentDispatcher;
			this.taxiSvc = taxiSvc;
			
			this.setTitle("Dispe\u010Der :: " + currentDispatcher.getUsername() );
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
			
			mainMenu.add(personMenu);
			mainMenu.add(carMenu);
			mainMenu.add(rideMenu);
			
			dispatchersItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_DOWN_MASK));
			personMenu.add(dispatchersItem);
			personMenu.addSeparator();
			driversItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK));
			personMenu.add(driversItem);
			
			carsItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK));
			carMenu.add(carsItem);
			ridesItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
			rideMenu.add(ridesItem);
			
			ImageIcon addIcon = new ImageIcon(getClass().getResource("/icons/add.gif"));
			addBtn.setIcon(addIcon);
			
			ImageIcon editIcon = new ImageIcon(getClass().getResource("/icons/edit.gif"));
			editBtn.setIcon(editIcon);
			
			ImageIcon dltIcon = new ImageIcon(getClass().getResource("/icons/remove.gif"));
			dltBtn.setIcon(dltIcon);
			
			ImageIcon searchIcon = new ImageIcon(getClass().getResource("/icons/search.gif"));
			searchBtn.setIcon(searchIcon);
			
			ImageIcon refreshIcon = new ImageIcon(getClass().getResource("/icons/refresh.gif"));
			refreshBtn.setIcon(refreshIcon);
			
			//TODO
			/*addBtn.setSize(60, 20);
			editBtn.setSize(60, 20);
			dltBtn.setSize(60, 20);*/
			Box buttonBox = Box.createVerticalBox();
			buttonBox.add(addBtn);
			buttonBox.add(Box.createVerticalStrut(3));
			buttonBox.add(editBtn);
			buttonBox.add(Box.createVerticalStrut(3));
			buttonBox.add(dltBtn);
			buttonBox.add(Box.createVerticalStrut(3));
			buttonBox.add(searchBtn);
			buttonBox.add(Box.createVerticalStrut(3));
			buttonBox.add(refreshBtn);
			/*mainToolbar.setFloatable(false);
			mainToolbar.add(addBtn);
			mainToolbar.add(editBtn);
			mainToolbar.add(dltBtn);
			
			//TODO mainToolbar.setBounds(0, 600, 1200, 150);*/
			this.add(buttonBox, BorderLayout.WEST);
			taxiSvc.loadInCustomers("Customers.csv");
			taxiSvc.loadInDrivers("Drivers.csv");
			taxiSvc.loadInRides("Rides.csv");
			ArrayList<Ride> rides = taxiSvc.getAllRides();
			
			String[] tableHeader = new String[] {"ID Vo\u017Enje","Status","Vreme zahteva","Po\u010Detak","Destinacija",
					"Trajanje","Kilometra\u017Ea","Mu\u0161terija","Voza\u010D"};
			
			Object[][] tableData = new Object[rides.size()][tableHeader.length];
			
///* Testni Datum */			Calendar today = Calendar.getInstance();
//			today.set(Calendar.HOUR_OF_DAY, 0);
			System.out.println(rides);
			for (int i = 0; i < rides.size(); i++) {
				Ride ride = rides.get(i);
				tableData[i][0] = ride.getRideID();
				tableData[i][1] = ride.getStatus();
//				tableData[i][2] = null;
				tableData[i][2] = ride.getRequestDateTime().toString();
				tableData[i][3] = ride.getStartingAddress();
				tableData[i][4] = ride.getDestinationAddress();
				tableData[i][5] = ride.getDuration();
				tableData[i][6] = ride.getDistanceTraveled();
//				tableData[i][7] = null;
//				tableData[i][8] = null;
				if(ride.getCustomer() != null) tableData[i][7] = ride.getCustomer().getLastName() + " "+ride.getCustomer().getName();
				else tableData[i][7] = "/";
				if(ride.getDriver() != null) tableData[i][8] = ride.getDriver().getLastName() + " "+ ride.getDriver().getName();
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
			
		}
		
		private void initEvents() {
			
			editBtn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					int row = table.getSelectedRow();
					if(row == -1) {
						JOptionPane.showMessageDialog(null, "Molimo, pre izmene ozna\u010Dite red u tabeli!", "Pa\u017Enja", JOptionPane.WARNING_MESSAGE);
					}else {
						long rideID = Long.valueOf(tableModel.getValueAt(row, 0).toString());
						Ride ride = taxiSvc.findRide(rideID);
						if (ride == null || ride.getDriver() != null /*|| ride.getStatus()!= RequestStatus.values()[0] || ride.getStatus()!=RequestStatus.values()[3]*/) {
							JOptionPane.showMessageDialog(null, "Vo\u017Enja sa tim ID je ve\u0107 dodeljena ili ne postoji.", "Nepostoje\u0107a vo\u017Enja", JOptionPane.INFORMATION_MESSAGE);
						}else {
							
							RideDataForm rdf = new RideDataForm(taxiSvc, ride);
							rdf.setVisible(true);
						}
					}
				}
			});
			dispatchersItem.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					RideMainWindow.this.dispose();
					RideMainWindow.this.setVisible(false);
					DispatcherMainWindow dpNewWin = new DispatcherMainWindow(taxiSvc, currentDispatcher);
					dpNewWin.setVisible(true);
				}
			});
			driversItem.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					RideMainWindow.this.dispose();
					RideMainWindow.this.setVisible(false);
					DriverOperativeWindow drNewWin = new DriverOperativeWindow(taxiSvc, currentDispatcher);
					drNewWin.setVisible(true);
				}
			});
			refreshBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					RideMainWindow rideNewWin = new RideMainWindow (taxiSvc, currentDispatcher);
					rideNewWin.setVisible(true);

					RideMainWindow.this.dispose();
					RideMainWindow.this.setVisible(false);
					
				}
			});
			/*dltBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int row = table.getSelectedRow();
					if(row == -1) {
						JOptionPane.showMessageDialog(null, "Molimo, ozna\u010Dite red u tabeli!", "Gre\u0161ka", JOptionPane.INFORMATION_MESSAGE);
					}else {
						String jmbg = tableModel.getValueAt(row, 5).toString();
						String username = tableModel.getValueAt(row, 1).toString();
						Dispatcher dispatcher = taxiSvc.findDispatcher(jmbg);
						
						int confirmation = JOptionPane.showConfirmDialog(null, 
								"Da li ste sigurni da \u017Eelite da obri\u0161ete dispe\u010Dera?", 
								username + " - Porvrda brisanja", JOptionPane.YES_NO_OPTION);
						
						if(confirmation == JOptionPane.YES_OPTION) {
							dispatcher.setDeleted(true);
							tableModel.removeRow(row);
							taxiSvc.saveDispatchers(TaxiService.PRODAVCI_FAJL);
						}
					}
				}
			});*/
		}
	}