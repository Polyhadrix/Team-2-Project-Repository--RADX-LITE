package ris; // Double-check this

import java.awt.*;
//import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class PatientPortal {
	/*Open Database Connection (only once)*/
	private static Connection conn=openConnection();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater (new Runnable() { public void run() { initialize(); } });
	}//End main()

	/**
	 * Create the application.
	 */
	public PatientPortal() { initialize(); }

	/**
	 * Initialize the contents of the frame.
	 */
	private static void initialize() {
		JFrame frm_PatientPortal = new JFrame();
		frm_PatientPortal.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 14));
		frm_PatientPortal.setTitle("Patient Portal");
		frm_PatientPortal.setIconImage(Toolkit.getDefaultToolkit().getImage("src\\ung.gif"));
		frm_PatientPortal.setBounds(100, 100, 920, 499);
		frm_PatientPortal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {0};
		gridBagLayout.rowHeights = new int[] {0};
		gridBagLayout.columnWeights = new double[]{1.0};
		gridBagLayout.rowWeights = new double[]{1.0};
		frm_PatientPortal.getContentPane().setLayout(gridBagLayout);
		new AddRegistrationPanel(frm_PatientPortal,conn);
		
		//Display the window.
		//frmPatientPortal.pack();
		frm_PatientPortal.setVisible(true);
	}


	//Database Functions
	/* Open a comm-link to the database */
	public static Connection openConnection(){
		// Note the parameter "allowMultiQueries=true" this allows multiple SQL commands to be .execute()'d
		try { return DriverManager.getConnection("jdbc:mysql://localhost:3306/?allowMultiQueries=true", "root", ""); }
		catch (SQLException e) { System.out.println("XAMPP is required to be installed and running for this demonstration."); e.printStackTrace(); }
		return null;
	}

	/* Creates SQL Server Command Line Object */
	public static Statement createCLO(Connection conn){
		try { return conn.createStatement(); } 
		catch (SQLException e) { System.out.println("Error: Could not create SQL CLI Statement Object."); e.printStackTrace(); }
		return null;
	}//End: createCLO(Connection)

	public static ResultSet resultsFor(String sql) {
		try{
			Statement s = null;
			String query= sql;
			s=conn.createStatement();
			return s.executeQuery(query);
		} catch (SQLException e) { System.out.print("Database Error: \n"); e.printStackTrace(); return null;}
	}

	// Shortcut to print to System.out.println()
	public static void log(Object o) {System.out.println(o);}

}
