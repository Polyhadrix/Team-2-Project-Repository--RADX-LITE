import java.awt.*;
//import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PatientPortal {
	/* Declare Objects Here for Uniform Access*/
	public static JLayeredPane layeredContentPane;
	public static JPanel pnl_mainmenu, pnl_registration, pnl_technician, pnl_radiologist;
	public static ArrayList<JPanel> allPanels = new ArrayList<>();

	/*Open Database Connection (only once)*/
	private static Connection conn=openConnection();

	/* Launches application */
	public static void main(String[] args) { javax.swing.SwingUtilities.invokeLater (new Runnable() { public void run() { initialize(); } }); }

	/* Initialize the frame and initialize each panel. */
	private static void initialize() {
		if(!verifyDatabase()) {//if database does NOT yet exist
			initDatabase(); // initialize it
		}
		
		/* Set Up Primary Frame */
		JFrame appWindow = new JFrame();
		appWindow.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 14));
		appWindow.setTitle("Patient Portal");
		appWindow.setIconImage(Toolkit.getDefaultToolkit().getImage("src\\ung.gif")); // The icon in the Title bar
		appWindow.setBounds(100, 100, 920, 510);
		appWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/* Using a JLayeredPane with gridBagLayout as the primary content pane */
		layeredContentPane = new JLayeredPane();
		layeredContentPane.setOpaque(true); // precautionary, some reported problems without
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {0}; // single-col, auto-width
		gridBagLayout.rowHeights = new int[] {0}; // single-row, auto-width
		gridBagLayout.columnWeights = new double[]{1.0}; // expand col to fill window
		gridBagLayout.rowWeights = new double[]{1.0}; // expand row to fill window
		layeredContentPane.setLayout(gridBagLayout);
		appWindow.setContentPane(layeredContentPane);

		/* Run each Panels initialization process */
		pnl_mainmenu = createPnlMainMenu();
		pnl_registration = AddRegistrationPanel.createPnlRegistration(layeredContentPane,conn);
		pnl_technician = AddTechnicianPanel.createPnlTechnician(layeredContentPane, conn);
		pnl_radiologist = AddRadiologistPanel.createPnlRadiologist(layeredContentPane, conn);
		
		// Hide all the other panels except the Main Menu (PatientPortal.java) panel
		allPanels.addAll( Arrays.asList(pnl_registration,pnl_technician,pnl_radiologist) ); // Note: DON'T include the main menu panel
		for(JPanel pnl : allPanels) { pnl.setVisible(false); }

		//Display the window
		// appWindow.pack(); Don't Pack, because the hidden windows won't be used in calculation
		appWindow.setVisible(true);
	}

	/* Initialize and populate Main Menu panel */
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
	
	/* Additional Helper Functions Below */
	
	/**Hides the first panel, shows the next panel.
	 * @param prevScreen The JPanel to hide
	 * @param nextScreen The JPanel to show
	 */
	public static void switchPanel(JPanel prevScreen, JPanel nextScreen) { prevScreen.setVisible(false); nextScreen.setVisible(true); }

	
	/**
	 * Opens a connection to the database for communications
	 * 
	 * Note: The connection parameter "allowMultiQueries=true" allows multiple SQL commands to be .execute()'d
	 * @return the dB Connection object
	 */
	public static Connection openConnection(){ 
		try { return DriverManager.getConnection("jdbc:mysql://localhost:3306/?allowMultiQueries=true", "root", ""); }
		catch (SQLException e) { System.out.println("XAMPP is required to be installed and running for this demonstration."); e.printStackTrace(); }
		return null;
	}

	// Shortcode: "System.out.println()" can be written as "log()"
	public static void log(Object o) {System.out.println(o);}
	
	/* Polls the server to see if the `gamenight` database exists */
	public static boolean verifyDatabase(){
		try{			
			DatabaseMetaData dbmd = conn.getMetaData();
			ResultSet rs = dbmd.getCatalogs();//capture String array of Database names

			//Scan database array for matching database name
			while(rs.next()) {
				if( rs.getString(1).contentEquals("ris") ) {
					rs.close();
					return true;
				}
			}
			rs.close();
			return false;
		} catch (Exception e) {	log("Error: verifyDatabase() function failed."); e.printStackTrace(); }//End try-catch
		return false;
	}//End: hasDB(Connection,String)
	
	// Server Initializatioin //
	public static boolean initDatabase() {
		log("Initializing `ris` Database...");
		String sql = "CREATE DATABASE IF NOT EXISTS `ris` DEFAULT CHARACTER SET utf8mb4 ;" + 
			"USE `ris` ;" + 
			"" + 
			"CREATE TABLE IF NOT EXISTS `ris`.`employer` (" + 
			"  `id` INT NOT NULL AUTO_INCREMENT," + 
			"  `name` VARCHAR(45) NOT NULL," + 
			"  `street` VARCHAR(45) NULL," + 
			"  `city` VARCHAR(45) NULL," + 
			"  `state` VARCHAR(45) NULL," + 
			"  `zip` VARCHAR(45) NULL," + 
			"  PRIMARY KEY (`id`))" + 
			"ENGINE = InnoDB;" + 
			"CREATE TABLE IF NOT EXISTS `ris`.`steward` (" + 
			"  `id` INT NOT NULL AUTO_INCREMENT," + 
			"  `ssn` VARCHAR(45) NULL," + 
			"  `name` VARCHAR(45) NULL," + 
			"  `relation` VARCHAR(45) NULL," + 
			"  `home_phone` VARCHAR(45) NULL," + 
			"  `cell_phone` VARCHAR(45) NULL," + 
			"  `work_phone` VARCHAR(45) NULL," + 
			"  `street` VARCHAR(45) NULL," + 
			"  `city` VARCHAR(45) NULL," + 
			"  `state` VARCHAR(45) NULL," + 
			"  `zip` VARCHAR(45) NULL," + 
			"  PRIMARY KEY (`id`))" + 
			"ENGINE = InnoDB;" + 
			"CREATE TABLE IF NOT EXISTS `ris`.`patient` (" + 
			"  `id` INT NOT NULL AUTO_INCREMENT," + 
			"  `ssn` VARCHAR(20) NOT NULL," + 
			"  `first_name` VARCHAR(45) NOT NULL," + 
			"  `middle_initial` VARCHAR(45) NULL," + 
			"  `last_name` VARCHAR(45) NOT NULL," + 
			"  `birthdate` VARCHAR(45) NOT NULL," + 
			"  `sex` VARCHAR(45) NOT NULL," + 
			"  `home_phone` VARCHAR(45) NULL," + 
			"  `cell_phone` VARCHAR(45) NULL," + 
			"  `work_phone` VARCHAR(45) NULL," + 
			"  `street` VARCHAR(100) NOT NULL," + 
			"  `city` VARCHAR(45) NOT NULL," + 
			"  `state` VARCHAR(45) NOT NULL," + 
			"  `zip` VARCHAR(20) NOT NULL," + 
			"  `employer_id` INT NULL," + 
			"  `steward_id` INT NULL," + 
			"  PRIMARY KEY (`id`)," + 
			"  INDEX `FK_Patient_Employer_idx` (`employer_id` ASC)," + 
			"  INDEX `FK_Patient_Steward_idx` (`steward_id` ASC)," + 
			"  CONSTRAINT `FK_Patient_Employer`" + 
			"    FOREIGN KEY (`employer_id`)" + 
			"    REFERENCES `ris`.`employer` (`id`)" + 
			"    ON DELETE NO ACTION" + 
			"    ON UPDATE CASCADE," + 
			"  CONSTRAINT `FK_Patient_Steward`" + 
			"    FOREIGN KEY (`steward_id`)" + 
			"    REFERENCES `ris`.`steward` (`id`)" + 
			"    ON DELETE NO ACTION" + 
			"    ON UPDATE CASCADE)" + 
			"ENGINE = InnoDB;" + 
			"CREATE TABLE IF NOT EXISTS `ris`.`image` (" + 
			"  `id` INT NOT NULL AUTO_INCREMENT," + 
			"  `patient_id` INT NOT NULL," + 
			"  `last_modified` VARCHAR(45) NULL," + 
			"  `poi` VARCHAR(45) NULL," + 
			"  `condition` VARCHAR(45) NULL," + 
			"  `modality` VARCHAR(45) NULL," + 
			"  `observations` VARCHAR(1000) NULL," + 
			"  `diagnosis` VARCHAR(1000) NULL," + 
			"  `url` VARCHAR(255) NOT NULL," + 
			"  PRIMARY KEY (`id`)," + 
			"  INDEX `FK_Image_Patient_idx` (`patient_id` ASC)," + 
			"  CONSTRAINT `FK_Image_Patient`" + 
			"    FOREIGN KEY (`patient_id`)" + 
			"    REFERENCES `ris`.`patient` (`id`)" + 
			"    ON DELETE NO ACTION" + 
			"    ON UPDATE CASCADE)" + 
			"ENGINE = InnoDB;";

		try { 
			PreparedStatement cli = conn.prepareStatement(sql);
			cli.executeUpdate(sql); log("Database Initiliazation: Successful."); 
			return true;
		}catch(SQLException e){log("Error: Creating Database and Tables"); e.printStackTrace();}
		return false;
	}
	
}
