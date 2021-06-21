package uiOperatingWindows;

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
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import entities.Dispatcher;
import entities.Driver;
import entities.TaxiService;
import uiDataInputForms.DriverDataForm;
import uiMainWindows.DispatcherMainWindow;
import uiMainWindows.RideMainWindow;

public class DriverOperativeWindow extends JFrame {
		
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
		
								//TaxiService taxiSvc
		public DriverOperativeWindow(TaxiService taxiSvc, Dispatcher currentDispatcher) {
			
			this.taxiSvc = taxiSvc;
			this.currentDispatcher = currentDispatcher;
			this.setTitle("Dispe\u010Der :: " + currentDispatcher.getUsername());
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

			ArrayList<Driver> nonDeletedDrivers = taxiSvc.getNonDeletedDrivers();
			
			String[] tableHeader = new String[] {"Korisni\u010Dko ime","Lozinka","Ime","Prezime",
					"JMBG","Pol","Telefon","Adresa","Vozilo","Br.\u010Dlanske karte","Status","Plata"};
			
			Object[][] tableData = new Object[nonDeletedDrivers.size()][tableHeader.length];
			
			for (int i = 0; i < nonDeletedDrivers.size(); i++) {
				//System.out.println(i);
				//System.out.println(ldispatchers.size());
				Driver dr = nonDeletedDrivers.get(i);
				
				tableData[i][0] = dr.getUsername();
				tableData[i][1] = dr.getPassword();
				tableData[i][2] = dr.getName();
				tableData[i][3] = dr.getLastName();
				tableData[i][4] = dr.getJmbg();
				tableData[i][5] = dr.getGender().toString();
				tableData[i][6] = dr.getPhoneNum();
				tableData[i][7] = dr.getAddress();
				tableData[i][8] = null;
				// TODO tableData[i][8] = dr.getVehicle().getModel();
				tableData[i][9] = dr.getMembershipCardNum();
				tableData[i][10] = dr.getDriverStatus().toString();
				tableData[i][11] = dr.getSalary();
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
			
			addBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					DriverDataForm ddf = new DriverDataForm(taxiSvc, null);
					ddf.setVisible(true);
					
				}
			});
			editBtn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					int row = table.getSelectedRow();
					if(row == -1) {
						JOptionPane.showMessageDialog(null, "Molimo, pre izmene ozna\u010Dite red u tabeli!", "Pa\u017Enja", JOptionPane.WARNING_MESSAGE);
					}else {
						long jmbg = (long) tableModel.getValueAt(row, 4);
						Driver driver = taxiSvc.findDriver(jmbg);
						if(driver == null) {
							JOptionPane.showMessageDialog(null, "Voza\u010D sa tim JMBG nije prona\u0111en.", "Gre\u0161ka", JOptionPane.INFORMATION_MESSAGE);
						}else {
							DriverDataForm ddf = new DriverDataForm(taxiSvc, driver);
							ddf.setVisible(true);
						}
					}
				}
			});
			dltBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int row = table.getSelectedRow();
					if(row == -1) {
						JOptionPane.showMessageDialog(null, "Molimo, ozna\u010Dite red u tabeli!", "Gre\u0161ka", JOptionPane.WARNING_MESSAGE);
					}else {
						long jmbg = (long) tableModel.getValueAt(row, 4);
						String username = tableModel.getValueAt(row, 0).toString();
						Driver driver = taxiSvc.findDriver(jmbg);
						if(driver == null) {
							JOptionPane.showMessageDialog(null, "Voza\u0161 ne postoji ili je obrisan iz sistema.", "Nepostoje\u0107i voza\u0161", JOptionPane.INFORMATION_MESSAGE);
						}
						else {	
							int confirmation = JOptionPane.showConfirmDialog(null, 
									"Da li ste sigurni da \u017Eelite da obri\u0161ete voza\u010Da?", 
									username + " ¤ Potvrda brisanja", JOptionPane.YES_NO_OPTION);
							
							if(confirmation == JOptionPane.YES_OPTION) {
								driver.setDeleted(true);
								//TODO da se iz fajla ne brise korisnik koji je logicki obrisan pri ponovnom snimanju iz liste tableModel.removeRow(row);
								taxiSvc.saveDrivers("Drivers.csv");
								refreshWindow();
							}
						}
					}
				}
			});
			refreshBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					DriverOperativeWindow dpNewWin = new DriverOperativeWindow(taxiSvc, currentDispatcher);
					dpNewWin.setVisible(true);

					DriverOperativeWindow.this.dispose();
					DriverOperativeWindow.this.setVisible(false);
					
				}
			});
			dispatchersItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					DispatcherMainWindow dpNewWin = new DispatcherMainWindow(taxiSvc, currentDispatcher);
					dpNewWin.setVisible(true);
					
					DriverOperativeWindow.this.dispose();
					DriverOperativeWindow.this.setVisible(false);
				}
			});
			ridesItem.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					DriverOperativeWindow.this.dispose();
					DriverOperativeWindow.this.setVisible(false);
					RideMainWindow rideNewWin = new RideMainWindow(taxiSvc, currentDispatcher);
					rideNewWin.setVisible(true);
				}
			});
			
		}
		

		public void refreshWindow() {
			refreshBtn.doClick(350);
		}
		
		
		/*public static void main(String[] args) {
			DispatcherMainWindow dpWin = new DispatcherMainWindow();
			dpWin.setVisible(true);
		}*/
}