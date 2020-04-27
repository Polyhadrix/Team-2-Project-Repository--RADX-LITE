import java.awt.*;
//import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PatientPortal {
	private static JLayeredPane layeredContentPane;
	
	public static JPanel pnl_mainmenu, pnl_registration, pnl_technician, pnl_radiologist;
	public static ArrayList<JPanel> allPanels = new ArrayList<>();
	
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
		JFrame appWindow = new JFrame();
		appWindow.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 14));
		appWindow.setTitle("Patient Portal");
		appWindow.setIconImage(Toolkit.getDefaultToolkit().getImage("src\\ung.gif"));
		appWindow.setBounds(100, 100, 920, 499);
		appWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		layeredContentPane = new JLayeredPane();
		layeredContentPane.setOpaque(true);
/**/	GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {0};
		gridBagLayout.rowHeights = new int[] {0};
		gridBagLayout.columnWeights = new double[]{1.0};
		gridBagLayout.rowWeights = new double[]{1.0};
		appWindow.setContentPane(layeredContentPane);
		layeredContentPane.setLayout(gridBagLayout);
/**/	
		pnl_mainmenu = createPnlMainMenu();
		pnl_registration = AddRegistrationPanel.createPnlRegistration(layeredContentPane,conn);
		pnl_technician = AddTechnicianPanel.createPnlTechnician(layeredContentPane, conn);
		pnl_radiologist = AddRadiologistPanel.createPnlRadiologist(layeredContentPane, conn);
		
		// Size up the main frame, and hide the other panels
		allPanels.addAll( Arrays.asList(pnl_registration,pnl_technician) ); // Note: DON'T include the main menu panel
		int largestHeight=0, largestWidth=0;
		for(JPanel pnl : allPanels) {
			largestHeight = Math.max(largestHeight, pnl.getHeight() );
			largestWidth = Math.max(largestWidth, pnl.getWidth() );
			pnl.setVisible(false);
		}
		layeredContentPane.setPreferredSize(new Dimension( largestWidth, largestHeight ));


		//Display the window
		//appWindow.pack();
		appWindow.setVisible(true);
	}

	public static JPanel createPnlMainMenu() {
		JPanel pnl_mainmenu = new JPanel();
		GridBagConstraints gbc_pnl_registration = new GridBagConstraints();
		gbc_pnl_registration.fill = GridBagConstraints.BOTH;
		gbc_pnl_registration.anchor = GridBagConstraints.NORTH;
		gbc_pnl_registration.gridx = 0;
		gbc_pnl_registration.gridy = 0;
		gbc_pnl_registration.weighty = 1.0;
		layeredContentPane.add(pnl_mainmenu, gbc_pnl_registration);
		GridBagLayout gbl_pnl_mainmenu = new GridBagLayout();
		gbl_pnl_mainmenu.columnWidths = new int[]{0, 0};
		gbl_pnl_mainmenu.rowHeights = new int[] {0, 0, 0, 0};
		gbl_pnl_mainmenu.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_pnl_mainmenu.rowWeights = new double[]{0.0, 1.0, 1.0, 1.0};
		pnl_mainmenu.setLayout(gbl_pnl_mainmenu);
		
		JPanel row0 = new JPanel();
		GridBagConstraints gbc_row0 = new GridBagConstraints();
		gbc_row0.anchor = GridBagConstraints.NORTH;
		gbc_row0.fill = GridBagConstraints.HORIZONTAL;
		gbc_row0.insets = new Insets(0, 0, 5, 0);
		gbc_row0.gridx = 0;
		gbc_row0.gridy = 0;
		pnl_mainmenu.add(row0, gbc_row0);
		GridBagLayout gbl_row0 = new GridBagLayout();
		gbl_row0.columnWidths = new int[]{189, 0};
		gbl_row0.rowHeights = new int[]{22, 0};
		gbl_row0.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_row0.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		row0.setLayout(gbl_row0);
		
		JLabel lbl_Title = new JLabel("RIS Main Menu");
		lbl_Title.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_Title.setVerticalAlignment(SwingConstants.TOP);
		lbl_Title.setFont(new Font("Tahoma", Font.BOLD, 18));
		GridBagConstraints gbc_lbl_Title = new GridBagConstraints();
		gbc_lbl_Title.insets = new Insets(5, 5, 0, 0);
		gbc_lbl_Title.weightx = 1.0;
		gbc_lbl_Title.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbl_Title.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc_lbl_Title.gridx = 0;
		gbc_lbl_Title.gridy = 0;
		row0.add(lbl_Title, gbc_lbl_Title);
		
		JPanel row1 = new JPanel();
		GridBagConstraints gbc_row1 = new GridBagConstraints();
		gbc_row1.anchor = GridBagConstraints.SOUTH;
		gbc_row1.insets = new Insets(0, 0, 5, 0);
		gbc_row1.fill = GridBagConstraints.HORIZONTAL;
		gbc_row1.gridx = 0;
		gbc_row1.gridy = 1;
		pnl_mainmenu.add(row1, gbc_row1);
		GridBagLayout gbl_row1 = new GridBagLayout();
		gbl_row1.columnWidths = new int[] {0};
		gbl_row1.rowHeights = new int[] {0};
		gbl_row1.columnWeights = new double[]{1.0};
		gbl_row1.rowWeights = new double[]{0.0};
		row1.setLayout(gbl_row1);
		
		JButton btn_Patient = new JButton("Patient Information System");
		btn_Patient.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { switchPanel(pnl_mainmenu, pnl_registration); } });
		btn_Patient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_btn_Patient = new GridBagConstraints();
		gbc_btn_Patient.insets = new Insets(0, 0, 5, 0);
		gbc_btn_Patient.gridx = 0;
		gbc_btn_Patient.gridy = 0;
		row1.add(btn_Patient, gbc_btn_Patient);
		
		JPanel row2 = new JPanel();
		GridBagConstraints gbc_row2 = new GridBagConstraints();
		gbc_row2.insets = new Insets(0, 0, 5, 0);
		gbc_row2.fill = GridBagConstraints.BOTH;
		gbc_row2.gridx = 0;
		gbc_row2.gridy = 2;
		pnl_mainmenu.add(row2, gbc_row2);
		GridBagLayout gbl_row2 = new GridBagLayout();
		gbl_row2.columnWidths = new int[] {0};
		gbl_row2.rowHeights = new int[] {0};
		gbl_row2.columnWeights = new double[]{1.0};
		gbl_row2.rowWeights = new double[]{0.0};
		row2.setLayout(gbl_row2);
		
		JButton btn_Technician = new JButton("Technician Image Uploader");
		btn_Technician.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { switchPanel(pnl_mainmenu, pnl_technician); } });
		btn_Technician.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_btn_Technician = new GridBagConstraints();
		gbc_btn_Technician.insets = new Insets(0, 0, 5, 0);
		gbc_btn_Technician.gridx = 0;
		gbc_btn_Technician.gridy = 0;
		row2.add(btn_Technician, gbc_btn_Technician);
		
		JPanel row3 = new JPanel();
		GridBagConstraints gbc_row3 = new GridBagConstraints();
		gbc_row3.anchor = GridBagConstraints.NORTH;
		gbc_row3.fill = GridBagConstraints.HORIZONTAL;
		gbc_row3.gridx = 0;
		gbc_row3.gridy = 3;
		pnl_mainmenu.add(row3, gbc_row3);
		GridBagLayout gbl_row3 = new GridBagLayout();
		gbl_row3.columnWidths = new int[] {0};
		gbl_row3.rowHeights = new int[] {0};
		gbl_row3.columnWeights = new double[]{1.0};
		gbl_row3.rowWeights = new double[]{0.0};
		row3.setLayout(gbl_row3);
		
		JButton btn_Radiologist = new JButton("Radiologist Report Manager");
		btn_Radiologist.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { switchPanel(pnl_mainmenu, pnl_radiologist); } });
		btn_Radiologist.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_btn_Radiologist = new GridBagConstraints();
		gbc_btn_Radiologist.insets = new Insets(0, 0, 5, 0);
		gbc_btn_Radiologist.gridx = 0;
		gbc_btn_Radiologist.gridy = 0;
		row3.add(btn_Radiologist, gbc_btn_Radiologist);
		return pnl_mainmenu;
		
	} // initialize()
	
	/**Hides the first panel, shows the next panel.
	 * @param prevScreen The JPanel to hide
	 * @param nextScreen The JPanel to show
	 */
	public static void switchPanel(JPanel prevScreen, JPanel nextScreen) { prevScreen.setVisible(false); nextScreen.setVisible(true); }

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
