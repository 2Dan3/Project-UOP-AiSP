package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class LoginGUI extends JFrame {

	public LoginGUI () {
			
		setTitle("Prijava na sistem");
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		setSize(screenHeight/4, screenWidth/4);
		
		setLocationRelativeTo (null);
		
	/*	DISPOSE_ON_CLOSE: 	Close the window, if only window - exit.
		EXIT_ON_CLOSE: 		Close the window, exit.
		HIDE_ON_CLOSE: 		Hide the window.
		DO_NOTHING_ON_CLOSE:	Does nothing. Coder writes own on-click logic.
	*/
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable (false);
		
		initGUI();
	}


	public void initGUI() {
		JButton	btnOk = new JButton("OK");
		add (btnOk, BorderLayout.SOUTH);
		btnOk.setSize(200, 100);
		
		//
		String roleChoice = (String) JOptionPane.showInputDialog (null, "Prijavite se s ulogom >>", "Uloga u sistemu",
				JOptionPane.PLAIN_MESSAGE, null, new String[]{"Mušterija", "Vozaè", "Dispeèer"}, "Mušterija");
		
		
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			System.out.println("CLICKED");
			}
		});
	}
}