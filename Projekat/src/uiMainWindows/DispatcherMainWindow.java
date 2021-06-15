package uiMainWindows;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.AbstractButton;
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
import entities.Dispatcher;

public class DispatcherMainWindow extends JFrame {
	
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

//JToolBar mainToolbar = new JToolBar(); //TODO made JTable & DefaultTableModel private
	private JTable table;
	private DefaultTableModel tableModel;
	
							//TaxiService taxiSvc
	public DispatcherMainWindow(Dispatcher currentDispatcher) {
		
		//this.taxiSvc = taxiSvc;
		this.currentDispatcher = currentDispatcher;
		this.setTitle("Dispe\u010Der :: " + currentDispatcher.getUsername());// + current_user/dispatch
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

		//TODO taxiSvc.getAllDispatchers();
		ArrayList<Dispatcher> ldispatchers = Dispatcher.getNonDeletedDispatchers();
		
		String[] tableHeader = new String[] {"Korisni\u010Dko ime","Lozinka","Ime","Prezime",
				"JMBG","Pol","Telefon","Adresa","Br.Tel. linije","Odeljenje","Plata"};
		
		Object[][] tableData = new Object[ldispatchers.size()][tableHeader.length];
		
		for (int i = 0; i < ldispatchers.size(); i++) {
			//System.out.println(i);
			//System.out.println(ldispatchers.size());
			Dispatcher dp = ldispatchers.get(i);
			
			tableData[i][0] = dp.getUsername();
			tableData[i][1] = dp.getPassword();
			tableData[i][2] = dp.getName();
			tableData[i][3] = dp.getLastName();
			tableData[i][4] = dp.getJmbg();
			tableData[i][5] = dp.getGender().toString();
			tableData[i][6] = dp.getPhoneNum();
			tableData[i][7] = dp.getAddress();
			tableData[i][8] = dp.getPhoneLineNum();
			tableData[i][9] = dp.getDept().toString();
			tableData[i][10] = dp.getSalary();
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
				DispatcherDataForm ddf = new DispatcherDataForm(null);
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
					Dispatcher dispatcher = TaxiService.findDispatcher(jmbg);
					if(dispatcher == null) {
						JOptionPane.showMessageDialog(null, "Dispe\u010Der sa tim JMBG nije prona\u0111en.", "Gre\u0161ka", JOptionPane.INFORMATION_MESSAGE);
					}else {
						DispatcherDataForm ddf = new DispatcherDataForm(dispatcher);
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
					Dispatcher dispatcher = TaxiService.findDispatcher(jmbg);
					if(dispatcher == null) {
						JOptionPane.showMessageDialog(null, "Dispe\u016Der ne postoji ili je obrisan iz sistema.", "Nepostoje\u0107i dispe\u016Der", JOptionPane.INFORMATION_MESSAGE);
					}
					else {	
						int confirmation = JOptionPane.showConfirmDialog(null, 
								"Da li ste sigurni da \u017Eelite da obri\u0161ete dispe\u010Dera?", 
								username + " - Potvrda brisanja", JOptionPane.YES_NO_OPTION);
						
						if(confirmation == JOptionPane.YES_OPTION) {
							dispatcher.setDeleted(true);
							//TODO da se iz fajla ne brise korisnik koji je logicki obrisan pri ponovnom snimanju iz liste tableModel.removeRow(row);
							Dispatcher.saveDispatchers("Dispatchers.csv");
							refreshWindow();
						}
					}
				}
			}
		});
		refreshBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DispatcherMainWindow dpNewWin = new DispatcherMainWindow(currentDispatcher);
				dpNewWin.setVisible(true);
				//TODO check and move around if not working
				DispatcherMainWindow.this.dispose();
				DispatcherMainWindow.this.setVisible(false);
				
			}
		});
	}
	
	public JButton getRefreshBtn() {
		return refreshBtn;
	}
	public void refreshWindow() {
		refreshBtn.doClick(330);
	}
	
	
	/*public static void main(String[] args) {
		DispatcherMainWindow dpWin = new DispatcherMainWindow();
		dpWin.setVisible(true);
	}*/
}
