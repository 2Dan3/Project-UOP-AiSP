package uiMainWindows;

import javax.swing.JFrame;

import entities.Dispatcher;

public class DispatcherMainWindow extends JFrame {
	private JMenuBar mainMenu = new JMenuBar();
	private JMenu dispatcherMenu = new JMenu("Dispeceri");
	private JMenu driverMenu = new JMenu("Vozaci");
	private JMenu customerMenu = new JMenu("Klijenti");
	private JMenu ridesMenu = new JMenu("Voznje");
	private JMenu carsMenu = new JMenu("Automobili");
	private JMenuItem carItem = new JMenuItem("Prodavci");
	private JMenuItem rideItem = new JMenuItem("Prodavci");
	private Dispatcher currentUser;
	
	public  DispatcherMainWindow(Dispatcher currentUser) {
		this.currentUser = currentUser;
		setTitle("Dispecer >> " + currentUser.getusername());
		setSize(500, 500);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initMenus();
		initActions();
	}
	
	private void initMenus() {
		setJMenuBar(mainMenu);
		
	}
	
	private void initActions() {
		
	}
}
