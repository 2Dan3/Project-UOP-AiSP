package uiMainWindows;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import entities.Driver;
import entities.RequestStatus;
import entities.RequestType;
import entities.Ride;
import entities.TaxiService;

public class SumReportGenerator extends JFrame{
	
	private TaxiService taxiSvc;
	
	private JLabel lblDateFrom = new JLabel("Datum od: ");
	private JTextField txtDateFrom = new JTextField();
	
	private JLabel lblDateTo = new JLabel("Datum do: ");
	private JTextField txtDateTo = new JTextField();
	
	private JLabel lblTotalRides = new JLabel("Ukupno realizovanih vo\u017Enji >> ");
	private JTextField txtTotalRides = new JTextField();
	
	private JLabel lblAppRides = new JLabel("Vo\u017E poru\u010Dene aplikacijom >> ");
	private JTextField txtAppRides = new JTextField();
	
	private JLabel lblPhoneRides = new JLabel("Vo\u017E poru\u010Dene telefonom >> ");
	private JTextField txtPhoneRides = new JTextField();
	
	private JLabel lblActiveDrivers = new JLabel("Aktivnih voza\u010Da >> ");
	private JTextField txtActiveDrivers = new JTextField();
	
	private JLabel lblAvgDuration = new JLabel("Prose\u010Dno trajanje vo\u017Enje >> ");
	private JTextField txtAvgDuration = new JTextField();
	
	private JLabel lblAvgDistance = new JLabel("Prose\u010Dan broj pre\u0111enih kilometara >> ");
	private JTextField txtAvgDistance = new JTextField();
	
	private JLabel lblTotalEarns = new JLabel("Ukupna zarada u periodu >> ");
	private JTextField txtTotalEarns = new JTextField();
	
	private JLabel lblAvgEarns = new JLabel("Prose\u010Dna zarada u periodu >> ");
	private JTextField txtAvgEarns = new JTextField();
	
	private JButton generateReport = new JButton("Generi\u0161i izve\u0161taj");
	

	public SumReportGenerator(TaxiService taxiSvc) {
		
		this.taxiSvc = taxiSvc;
		this.setTitle("Sumirani Izve\u0161taj :: " /* TODO+ date1st +" - "+ dateLast*/);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize((int)(screenSize.width / 3.5), screenSize.height / 2);

		setAlwaysOnTop(true);
		setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setResizable(false);
		
		initGUI();
		initEvents();
	}
	
	
	
	private void initGUI() {
		// TODO Auto-generated method stub
		getRootPane().setDefaultButton(generateReport);
		
		GridLayout layout = new GridLayout(11, 2, 4, 4);
		setLayout(layout);
		
		txtTotalRides.setEnabled(false);
		txtAppRides.setEnabled(false);
		txtPhoneRides.setEnabled(false);
		txtAvgDistance.setEnabled(false);
		txtAvgDuration.setEnabled(false);
		txtAvgEarns.setEnabled(false);
		txtTotalEarns.setEnabled(false);
		txtActiveDrivers.setEnabled(false);
		
		add(lblDateFrom);
		add(txtDateFrom);
		add(lblDateTo);
		add(txtDateTo);
		add(lblTotalRides);
		add(txtTotalRides);
		add(lblAppRides);
		add(txtAppRides);
		add(lblPhoneRides);
		add(txtPhoneRides);
		add(lblActiveDrivers);
		add(txtActiveDrivers);
		add(lblAvgDuration);
		add(txtAvgDuration);
		add(lblAvgDistance);
		add(txtAvgDistance);
		add(lblTotalEarns);
		add(txtTotalEarns);
		add(lblAvgEarns);
		add(txtAvgEarns);
		add(new JLabel());
		add(generateReport);
		
		
		
		
		
		
		
	}



	private void initEvents() {
		
		generateReport.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				LocalDate dateFrom = LocalDate.parse(txtDateFrom.getText());
				LocalDate dateTo = LocalDate.parse(txtDateTo.getText());
				
				String[] data = generateReport (dateFrom, dateTo);
				
				txtTotalRides.setText(data[0]);
				txtAppRides.setText(data[1]);
				txtPhoneRides.setText(data[2]);
				txtActiveDrivers.setText(data[3]);
				txtAvgDuration.setText(data[4]);
				txtAvgDistance.setText(data[5]);
				txtAvgEarns.setText(data[6]);
				txtTotalEarns.setText(data[7]);	
			}
		});
		
	}

	
	private String[] generateReport(LocalDate dateFrom, LocalDate dateTo) {

		int totalRides;
		int appRides = 0;
		int phoneRides = 0;
		
		ArrayList<Driver> activeDrivers = new ArrayList<Driver>();
		int activeDriversTotal;
		
		double totalDuration = 0;
		double avgDuration;
		
		double totalDistance = 0;
		double avgDistance;
		
		double totalEarns = 0;
		double avgEarns;
		
		boolean alreadyCounted;
		
		for(Ride ride : taxiSvc.getAllRides()) {
			
			LocalDate rideDate = LocalDate.parse(ride.getRequestDateTime());
			
			if( (rideDate.isAfter(dateFrom) || rideDate.isEqual(dateFrom)) && (rideDate.isBefore(dateTo) || rideDate.isEqual(dateTo))) {
				
				if(ride.getStatus()==RequestStatus.values()[5]) {
									
					if(ride.getRequestType() == RequestType.values()[0]) phoneRides += 1;

					else if(ride.getRequestType() == RequestType.values()[1])	appRides += 1;
					
					totalDuration += ride.getDuration();
					
					totalDistance += ride.getDistanceTraveled();
					
					totalEarns += ride.getPricePerKm() * ride.getDistanceTraveled() + ride.getStartingPrice();
					
					alreadyCounted = false;
					
					for(Driver driver : activeDrivers) {
						if (ride.getDriver().equals(driver) ) {
							alreadyCounted = true;
							break;
						}
					}
					if(!alreadyCounted)    activeDrivers.add(ride.getDriver());
				}
			}
		}
		
		totalRides = appRides + phoneRides;
		
		activeDriversTotal = activeDrivers.size();
		
		avgDuration = totalDuration / totalRides;
		
		avgDistance = totalDistance / totalRides;
		
		avgEarns = totalEarns / totalRides;
		
		String[] data = new String[] {String.valueOf(totalRides), String.valueOf(appRides), String.valueOf(phoneRides), String.valueOf(activeDriversTotal), String.valueOf(avgDuration), String.valueOf(avgDistance), String.valueOf(avgEarns), String.valueOf(totalEarns)};
		return data;
	}

//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		new SumReportWindow().setVisible(true);
//	}

}
