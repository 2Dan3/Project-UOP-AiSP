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

import entities.TaxiService;
//import main.TaxiService;
import uiDataInputForms.DispatcherDataForm;
import uiDataInputForms.RideDataForm;
import entities.Dispatcher;
import entities.Ride;

	public class RideMainWindow extends JFrame {
		
		private TaxiService taxiSvc;
		private JButton addBtn = new JButton();
		private JButton editBtn = new JButton();
		private JButton dltBtn = new JButton();
		private JButton searchBtn = new JButton();
		
		private JMenuBar mainMenu = new JMenuBar();
		
		private JMenu personMenu = new JMenu("Korisnici");
		private JMenu carMenu = new JMenu("Vozni park");
		private JMenu rideMenu = new JMenu("Vo\u017Enje");
		
		private JMenuItem dispatchersItem = new JMenuItem("Dispe\u010Deri");
		private JMenuItem driversItem = new JMenuItem("Voza\u010Di");
		private JMenuItem carsItem = new JMenuItem("Automobili");
		private JMenuItem ridesItem = new JMenuItem("Vo\u017Enje");

	//JToolBar mainToolbar = new JToolBar();
		JTable table;
		DefaultTableModel tableModel;
		
								//TaxiService taxiSvc
		public RideMainWindow() {
			
			//this.taxiSvc = taxiSvc;
			
			this.setTitle("Vo\u017Enje :: Telefon");
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
			/*mainToolbar.setFloatable(false);
			mainToolbar.add(addBtn);
			mainToolbar.add(editBtn);
			mainToolbar.add(dltBtn);
			
			//TODO mainToolbar.setBounds(0, 600, 1200, 150);*/
			this.add(buttonBox, BorderLayout.WEST);

			//TODO taxiSvc.getAllDispatchers();
			ArrayList<Ride> rides = Ride.getAllRides();
			
			String[] tableHeader = new String[] {"ID Vo\017Enje","Status","Vreme zahteva","Po\010Detak","Destinacija",
					"Trajanje","Kilometra\u017Ea","Mu\u0161terija","Voza\u010D"};
			
			Object[][] tableData = new Object[rides.size()][tableHeader.length];
			
			for (int i = 0; i < rides.size(); i++) {
				Ride ride = rides.get(i);
				tableData[i][0] = ride.getRideID();
				tableData[i][1] = ride.getStatus();
				tableData[i][2] = ride.getRequestDateTime().toString();
				tableData[i][3] = ride.getStartingAddress();
				tableData[i][4] = ride.getDestinationAddress();
				tableData[i][5] = ride.getDuration();
				tableData[i][6] = ride.getDistanceTraveled();
				tableData[i][7] = ride.getCustomer();
				tableData[i][8] = ride.getDriver();
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
						String rideID = tableModel.getValueAt(row, 0).toString();
						//TODO Ride ride = findRide(rideID);
						/*if(ride == null) {
							JOptionPane.showMessageDialog(null, "Vo\u017Enja sa tim ID ne postoji ili je obrisana iz sistema.", "Nepostoje\u0107a vo\u017Enja", JOptionPane.INFORMATION_MESSAGE);
						}else {
							*/
							RideDataForm rdf = new RideDataForm(ride);
							rdf.setVisible(true);
						}
					}
				}
			);
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
		
		
		public static void main(String[] args) {
			RideMainWindow rideWin = new RideMainWindow();
			rideWin.setVisible(true);
		}
	}