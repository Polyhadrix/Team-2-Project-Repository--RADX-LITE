package ris;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class registration {
	/* Declare Form Elements */
	private static Container stage;
	/*Declare Labels*/
	private static JLabel label; // Label can be re-usable (no dependencies)
	/*Declare Buttons*/
	// private static JButton button, btn_cancel, btn_submit, btn_yes, btn_no;
    /*Patient Fields*/
	private static JTextField search, pFirst, pMiddle, pLast, pBirth, pHome, pCell, pWork, pSSN, pStreet, pCity, pZip;
    /*Employer Fields*/
	private static JTextField eCompany, eStreet, eCity, eZip;
    /*Steward Fields*/
	private static JTextField sName, sRelation, sSSN, sHome, sCell, sWork, sStreet, sCity, sZip;
	/* Drop-Downs */
	private static JComboBox<String> pSex, pState, eState, sState;
	/* CheckBoxes */
	private static JCheckBox isDependent;
	
	/* Drop-Down Values */
	private static String[] states = {"SELECT","Alabama","Alaska","Arkansas","California","Colorado","Connecticut","Delaware",
			"Florida","Georgia","Hawaii","Idaho","Illinois","Indiana","Iowa","Kansas","Kentucky","Louisiana",
			"Maine","Maryland","Massachusetts","Michigan","Minnesota","Mississippi","Missouri","Montana",
			"Nebraska","Nevada","New Hampshire","New Jersey","New Mexico","New York","North Carolina",
			"North Dakota","Ohio","Oklahoma","Oregon","Pennsylvania","Rhode Island","South Carolina",
			"South Dakota","Tennessee","Texas","Utah","Vermont","Virginia","Washington",
			"West Virginia","Wisconsin","Wyoming"};
	private static String[] sexes = {"SELECT","Male","Female","Intersex"};
	
	/* Set Default Styles */
	private final static Font DEFLT_TITLE = new Font("Tahoma", Font.BOLD, 18);
	private final static Font DEFLT_FONT = new Font("Tahoma", Font.PLAIN, 12);
	private final static Insets LBL_PADDING = new Insets(6,2,6,2); // Padding around JLabels
	private final static Insets FLD_PADDING = new Insets(6,0,6,6); // Padding around JTextFields, JComboBoxes, etc.
	
	/* Track Content Pointer */
	private final static int COL_MAX = 24;
	private static int currentCol = 0;
	private static int currentRow = 0;
	private static JPanel rowPanel, regPanel;
	
	/*Open Database Connection (only once)*/
	private static Connection conn=openConnection();
	// private static Statement cli = createCLO(conn); // Create the SQL Server Command Line Object;
	
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater (new Runnable() { public void run() { createAndShowGUI(); } });
	}//End main()
	
	private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Patient Registration");
        UIManager.put("Label.font", DEFLT_FONT);
        UIManager.put("ComboBox.font", DEFLT_FONT);
        UIManager.put("CheckBox.font", DEFLT_FONT);
        ImageIcon ico = new ImageIcon("src/ung.gif");
        frame.setIconImage(ico.getImage());
        frame.setPreferredSize(new Dimension(940, 350));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        stage = frame.getContentPane();

        //Add content to the pane.
        regPanel = createRegistrationPanel();
        stage.add(regPanel);
        // stage.add( createPatientInfoPanel() );

        
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
	
	public static JPanel createRegistrationPanel() {

		JPanel pnlREG = new JPanel();
		
	/* Set stage and apply defaults */
		pnlREG.setLayout(new GridBagLayout()); // GridBagLayout for elastic layouts
		rowPanel = new JPanel(new GridBagLayout());

	/* Make Form */
		JLabel title = new JLabel("Patient Registration");
		title.setFont(DEFLT_TITLE);
		GridBagConstraints c = new GridBagConstraints();
		c.gridx=0; c.gridy=currentRow; currentRow++;
		// c.weighty=0.1;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		pnlREG.add(title,c);
		
		addLabel("To View/Edit an Existing Patient, enter their Patient ID# here and hit Enter: ",12,search,pnlREG); search=addField(search,12,pnlREG);
		search.addActionListener( new viewPatientListener() );
		
		addLabel("First Name:",3,pFirst,pnlREG); pFirst=addField(pFirst,3,pnlREG);
		addLabel("MI:",1,pMiddle,pnlREG);        pMiddle=addField(pMiddle,1,pnlREG);
		addLabel("Last Name:",3,pLast,pnlREG);   pLast=addField(pLast,3,pnlREG);
		addLabel("Birthdate:",3,pBirth,pnlREG);  pBirth=addField(pBirth,3,pnlREG);
		addLabel("Sex:",2,pSex,pnlREG);          pSex=addDropdown(pSex,sexes,2,pnlREG);
		
		addLabel("Home Phone:",3,pHome,pnlREG);  pHome=addField(pHome,3,pnlREG);
		addLabel("Cell Phone:",3,pCell,pnlREG);  pCell=addField(pCell,3,pnlREG);
		addLabel("Work Phone:",3,pWork,pnlREG);  pWork=addField(pWork,3,pnlREG);
		addLabel("SSN:",3,pSSN,pnlREG);          pSSN=addField(pSSN,3,pnlREG);

		addLabel("Street:",2,pStreet,pnlREG);    pStreet=addField(pStreet,9,pnlREG);
		addLabel("City:",2,pCity,pnlREG);        pCity=addField(pCity,4,pnlREG);
		addLabel("State:",2,pState,pnlREG);      pState=addDropdown(pState,states,1,pnlREG);
		addLabel("ZIP:",2,pZip,pnlREG);          pZip=addField(pZip,2,pnlREG);

		addLabel("Employer Company:",5,eCompany,pnlREG);   eCompany=addField(eCompany,3,pnlREG); endLine(pnlREG);

		addLabel("Street:",2,eStreet,pnlREG);    eStreet=addField(eStreet,9,pnlREG);
		addLabel("City:",2,eCity,pnlREG);        eCity=addField(eCity,4,pnlREG);
		addLabel("State:",2,eState,pnlREG);      eState=addDropdown(eState,states,1,pnlREG);
		addLabel("ZIP:",2,eZip,pnlREG);          eZip=addField(eZip,2,pnlREG);
		
		isDependent=addCheckBox(isDependent,"is a dependent",5,pnlREG); endLine(pnlREG);// to add final row
		
		// Hidden Steward's Section //
		JPanel stewardPanel = new JPanel(new GridBagLayout());
		stewardPanel.setVisible(false);
		c = new GridBagConstraints();
		c.gridx=0;c.gridy=currentRow; c.fill = GridBagConstraints.HORIZONTAL;
		
		addLabel("Steward's Name:",3,sName,stewardPanel);   sName=addField(sName,3,stewardPanel);
		addLabel("Relationship:",3,sRelation,stewardPanel); sRelation=addField(sRelation,3,stewardPanel);
		addLabel("Steward's SSN:",3,sSSN,stewardPanel);     sSSN=addField(sSSN,3,stewardPanel);
		
		addLabel("Home Phone:",3,sHome,stewardPanel);  sHome=addField(sHome,3,stewardPanel);
		addLabel("Cell Phone:",3,sCell,stewardPanel);  sCell=addField(sCell,3,stewardPanel);
		addLabel("Work Phone:",3,sWork,stewardPanel);  sWork=addField(sWork,3,stewardPanel);
		
		addLabel("Street:",2,sStreet,stewardPanel);    sStreet=addField(sStreet,9,stewardPanel);
		addLabel("City:",2,sCity,stewardPanel);        sCity=addField(sCity,4,stewardPanel);
		addLabel("State:",2,sState,stewardPanel);      sState=addDropdown(sState,states,1,stewardPanel);
		addLabel("ZIP:",2,sZip,stewardPanel);          sZip=addField(sZip,2,stewardPanel);
		
		endLine(stewardPanel);// to add final row
		
		isDependent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent checkboxClicked) { stewardPanel.setVisible( isDependent.isSelected() ); }
		});
		
		pnlREG.add(stewardPanel, c);

		addSubmit(pnlREG);
		
		return pnlREG;
		
    }//createRegistrationPane()
	
	
	public static JPanel createInformationPanel() {

		JPanel pnlREG = new JPanel();
		
	/* Set stage and apply defaults */
		pnlREG.setLayout(new GridBagLayout()); // GridBagLayout for elastic layouts
		rowPanel = new JPanel(new GridBagLayout());

	/* Make Form */
		JLabel title = new JLabel("Patient Information");
		title.setFont(DEFLT_TITLE);
		GridBagConstraints c = new GridBagConstraints();
		c.gridx=0; c.gridy=currentRow; currentRow++;
		// c.weighty=0.1;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		pnlREG.add(title,c);
		
		addLabel("First Name:",3,pFirst,pnlREG); pFirst=addField(pFirst,3,pnlREG);
		addLabel("MI:",1,pMiddle,pnlREG);        pMiddle=addField(pMiddle,1,pnlREG);
		addLabel("Last Name:",3,pLast,pnlREG);   pLast=addField(pLast,3,pnlREG);
		addLabel("Birthdate:",3,pBirth,pnlREG);  pBirth=addField(pBirth,3,pnlREG);
		addLabel("Sex:",2,pSex,pnlREG);          pSex=addDropdown(pSex,sexes,2,pnlREG);
		
		addLabel("Home Phone:",3,pHome,pnlREG);  pHome=addField(pHome,3,pnlREG);
		addLabel("Cell Phone:",3,pCell,pnlREG);  pCell=addField(pCell,3,pnlREG);
		addLabel("Work Phone:",3,pWork,pnlREG);  pWork=addField(pWork,3,pnlREG);
		addLabel("SSN:",3,pSSN,pnlREG);          pSSN=addField(pSSN,3,pnlREG);

		addLabel("Street:",2,pStreet,pnlREG);    pStreet=addField(pStreet,9,pnlREG);
		addLabel("City:",2,pCity,pnlREG);        pCity=addField(pCity,4,pnlREG);
		addLabel("State:",2,pState,pnlREG);      pState=addDropdown(pState,states,1,pnlREG);
		addLabel("ZIP:",2,pZip,pnlREG);          pZip=addField(pZip,2,pnlREG);

		addLabel("Employer Company:",5,eCompany,pnlREG);   eCompany=addField(eCompany,3,pnlREG); endLine(pnlREG);

		addLabel("Street:",2,eStreet,pnlREG);    eStreet=addField(eStreet,9,pnlREG);
		addLabel("City:",2,eCity,pnlREG);        eCity=addField(eCity,4,pnlREG);
		addLabel("State:",2,eState,pnlREG);      eState=addDropdown(eState,states,1,pnlREG);
		addLabel("ZIP:",2,eZip,pnlREG);          eZip=addField(eZip,2,pnlREG);
		
		isDependent=addCheckBox(isDependent,"is a dependent",5,pnlREG); endLine(pnlREG);// to add final row
		
		// Hidden Steward's Section //
		JPanel stewardPanel = new JPanel(new GridBagLayout());
		stewardPanel.setVisible(false);
		c = new GridBagConstraints();
		c.gridx=0;c.gridy=currentRow; c.fill = GridBagConstraints.HORIZONTAL;
		
		addLabel("Steward's Name:",3,sName,stewardPanel);   sName=addField(sName,3,stewardPanel);
		addLabel("Relationship:",3,sRelation,stewardPanel); sRelation=addField(sRelation,3,stewardPanel);
		addLabel("Steward's SSN:",3,sSSN,stewardPanel);     sSSN=addField(sSSN,3,stewardPanel);
		
		addLabel("Home Phone:",3,sHome,stewardPanel);  sHome=addField(sHome,3,stewardPanel);
		addLabel("Cell Phone:",3,sCell,stewardPanel);  sCell=addField(sCell,3,stewardPanel);
		addLabel("Work Phone:",3,sWork,stewardPanel);  sWork=addField(sWork,3,stewardPanel);
		
		addLabel("Street:",2,sStreet,stewardPanel);    sStreet=addField(sStreet,9,stewardPanel);
		addLabel("City:",2,sCity,stewardPanel);        sCity=addField(sCity,4,stewardPanel);
		addLabel("State:",2,sState,stewardPanel);      sState=addDropdown(sState,states,1,stewardPanel);
		addLabel("ZIP:",2,sZip,stewardPanel);          sZip=addField(sZip,2,stewardPanel);
		
		endLine(stewardPanel);// to add final row
		
		isDependent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent checkboxClicked) { stewardPanel.setVisible( isDependent.isSelected() ); }
		});
		
		pnlREG.add(stewardPanel, c);

		addSubmit(pnlREG);
		
		return pnlREG;
		
    }//addComponentsToPane()
	

	/**
	 * addLabel: Adds an elastic JLabel to a specified pane
	 * @param txt = The visible text to the side of a TextField
	 * @param colWidth = The amount of grid columns this label should span (of COL_MAX)
	 * @param target = The field the label is intended for
	 * @param pane = the pane to append this element to
	 */
	public static JLabel addLabel(String txt, int colWidth, Component target, Container pane) {
		label = new JLabel(txt);
		label.setLabelFor(target);
		if( currentCol + colWidth > COL_MAX) endLine(pane);
		GridBagConstraints c = new GridBagConstraints();
		c.gridx=currentCol; c.gridwidth=colWidth;
		c.insets = LBL_PADDING;
		currentCol+=colWidth;
		rowPanel.add(label,c);
		return label;
	}

	/**
	 * addField: Adds an elastic JTextField to a specified pane
	 * @param fld = The instance identifier of the particular JTextField
	 * @param colWidth = The amount of grid columns this label should span (of COL_MAX)
	 * @param pane = the pane to append this element to
	 */
	public static JTextField addField(JTextField fld, int colWidth, Container pane) {
		fld = new JTextField();
		if( currentCol + colWidth > COL_MAX) endLine(pane);
		GridBagConstraints c = new GridBagConstraints();
		c.gridx=currentCol; c.gridwidth=colWidth; c.weightx = colWidth/24.0;
		c.fill=GridBagConstraints.HORIZONTAL; c.insets = FLD_PADDING;
		currentCol+=colWidth;
		rowPanel.add(fld,c);
		return fld;
	}

	/**
	 * addDropdown: Adds an elastic JComboBox to a specified pane
	 * @param dDown = The instance identifier of the particular JComboBox
	 * @param values = An array of values in the dropdown box
	 * @param colWidth = The amount of grid columns this label should span (of COL_MAX)
	 * @param pane = the pane to append this element to
	 */
	public static JComboBox<String> addDropdown(JComboBox<String> dDown, String[] values, int colWidth, Container pane) {
		dDown = new JComboBox<String>();
		if( currentCol + colWidth > COL_MAX) endLine(pane);
		GridBagConstraints c = new GridBagConstraints();
		c.gridx=currentCol;  c.gridwidth=colWidth; c.weightx = colWidth/24.0;
		c.fill=GridBagConstraints.HORIZONTAL; c.insets = FLD_PADDING;
		currentCol+=colWidth;
		rowPanel.add(dDown,c);
		for(String val : values) dDown.addItem(val);
		return dDown;
	}
	
	public static JCheckBox addCheckBox(JCheckBox cBox, String txt, int colWidth, Container pane) {
		cBox = new JCheckBox(txt);
		if( currentCol + colWidth > COL_MAX) endLine(pane);
		GridBagConstraints c = new GridBagConstraints();
		c.gridx=0; currentRow++; c.weightx=1.0; c.weighty=1.0;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill=GridBagConstraints.NONE; c.insets = FLD_PADDING;
		currentCol+=colWidth;
		rowPanel.add(cBox,c);
		return cBox;
	}
	
	public static void addSubmit(Container pane) {
		JButton button = new JButton("Register Patient");
		button.addActionListener(new addPatientListener() );
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridx=0; currentRow++; c.weightx=1.0; c.weighty=1.0;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill=GridBagConstraints.NONE; c.insets = FLD_PADDING;
		pane.add(button,c);
	}

	// Ends a form line
	public static void endLine(Container pane) { 
		GridBagConstraints c = new GridBagConstraints();
		c.gridy=currentRow; c.weightx=1.0; c.fill=GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.weighty=0.1;
		pane.add(rowPanel,c);
		rowPanel = new JPanel(new GridBagLayout());
		currentRow++; currentCol=0;
	}
	
	/* Listeners */
	private static class addPatientListener implements ActionListener { public void actionPerformed(ActionEvent event) {
		
		if (pFirst.getText() == null || pFirst.getText().strip().isEmpty() ) { 
		JOptionPane.showMessageDialog(stage,
			    "A required field is empty",
			    "Warning",
			    JOptionPane.WARNING_MESSAGE);
		}else{ 
			PreparedStatement sentry = null;
			String sql = "INSERT INTO `ris`.`patient`"
				 + " (`ssn`,`first_name`,`middle_initial`,`last_name`,`birthdate`,`sex`,`home_phone`,`cell_phone`,`work_phone`,`street`,`city`,`state`,`zip`) VALUES"
				 + " (?,?,?,?,?,?,?,?,?,?,?,?,?)";
			int qCount = 1; // Because counting question marks is annoying...

			// Try-Catch for SQLException
			try {
				// Notify the database of our intended statement
				sentry = conn.prepareStatement(sql);
				// Load up the ?s in the statement
				sentry.setString(qCount++, pSSN.getText());
				sentry.setString(qCount++, pFirst.getText());
				sentry.setString(qCount++, pMiddle.getText());
				sentry.setString(qCount++, pLast.getText());
				sentry.setString(qCount++, pBirth.getText());
				sentry.setString(qCount++, pSex.getSelectedItem().toString() ); // ComboBoxes work different
				sentry.setString(qCount++, pHome.getText());
				sentry.setString(qCount++, pCell.getText());
				sentry.setString(qCount++, pWork.getText());
				sentry.setString(qCount++, pStreet.getText());
				sentry.setString(qCount++, pCity.getText());
				sentry.setString(qCount++, pState.getSelectedItem().toString() ); // ComboBoxes work different
				sentry.setString(qCount++, pZip.getText());
				
				sentry.executeUpdate(); // commit insert/update
				
			} catch (SQLException e) { e.printStackTrace();}
			
			if (eCompany.getText() != null && !( eCompany.getText().strip().isEmpty() ) ) { // If employer info filled
				
				sentry = null;
				sql = "INSERT INTO `ris`.`employer`"
						+ " (`name`,`street`,`city`,`state`,`zip`) VALUES"
						 + " (?,?,?,?,?)";
				qCount = 1; // Because counting question marks is annoying...
				
				// Try-Catch for SQLException
				try {
					// Notify the database of our intended statement
					sentry = conn.prepareStatement(sql);
					// Load up the ?s in the statement
					sentry.setString(qCount++, eCompany.getText());
					sentry.setString(qCount++, eStreet.getText());
					sentry.setString(qCount++, eCity.getText());
					sentry.setString(qCount++, eState.getSelectedItem().toString() ); // ComboBoxes work different
					sentry.setString(qCount++, eZip.getText());
					
					sentry.executeUpdate(); // commit insert/update
					 ResultSet rs = sentry.executeQuery("SELECT MAX(id) AS `id` FROM `ris`.`employer`");
					 rs.first();
					int lastid = rs.getInt("id");
					sentry.executeUpdate("UPDATE `ris`.`patient` SET `employer_id`="+lastid+" WHERE `employer_id` IS NULL ORDER BY `id` DESC LIMIT 1");
				} catch (SQLException e) { e.printStackTrace();}

			}
			
			if (sName.getText() != null && !( sName.getText().strip().isEmpty() ) ) {
				sentry = null;
				sql = "INSERT INTO `ris`.`steward`"
						+ " (`ssn`,`name`,`relation`,`home_phone`,`cell_phone`,`work_phone`,`street`,`city`,`state`,`zip`) VALUES"
						+ " (?,?,?,?,?,?,?,?,?,?)";
				qCount = 1; // Because counting question marks is annoying...
				
				// Try-Catch for SQLException
				try {
					// Notify the database of our intended statement
					sentry = conn.prepareStatement(sql);
					// Load up the ?s in the statement
					sentry.setString(qCount++, sSSN.getText());
					sentry.setString(qCount++, sName.getText());
					sentry.setString(qCount++, sRelation.getText());
					sentry.setString(qCount++, sHome.getText());
					sentry.setString(qCount++, sCell.getText());
					sentry.setString(qCount++, sWork.getText());
					sentry.setString(qCount++, sStreet.getText());
					sentry.setString(qCount++, sCity.getText());
					sentry.setString(qCount++, sState.getSelectedItem().toString() ); // ComboBoxes work different
					sentry.setString(qCount++, sZip.getText());
					
					sentry.executeUpdate(); // commit insert/update
					
				} catch (SQLException e) { e.printStackTrace();}
			}
			
		}
		
	}/*actionPerformed()*/}//addpatientListener
	
private static class viewPatientListener implements ActionListener { public void actionPerformed(ActionEvent event) {
		
		if (search.getText() == null || search.getText().strip().isEmpty() ) { 
		JOptionPane.showMessageDialog(stage,
			    "The Search Box is Empty",
			    "Warning",
			    JOptionPane.WARNING_MESSAGE);
		}else{ 
			PreparedStatement sentry = null;
			String sql = "SELECT * FROM `ris`.`patient` WHERE `id`=?";

			// Try-Catch for SQLException
			try {
				// Notify the database of our intended statement
				sentry = conn.prepareStatement(sql);
				// Load up the ?s in the statement
				
				String searchID = search.getText().strip().replaceFirst("^0+(?!$)", ""); // Remove whitespace and leading 0's
				sentry.setString(1, searchID);
				ResultSet results = sentry.executeQuery();
				
				if(! results.isBeforeFirst() ) { // No Results Found
					JOptionPane.showMessageDialog(stage,
						    "No Patient was found with that ID.",
						    "Warning",
						    JOptionPane.WARNING_MESSAGE);
				}else { // Results found, Load them up!
					results.first();
					pFirst.setText( results.getString("first_name") );
					pMiddle.setText( results.getString("middle_initial") );
					pLast.setText( results.getString("last_name") );
					pBirth.setText( results.getString("birthdate") );
					pSex.setSelectedItem( results.getString("sex") );
					pHome.setText( results.getString("home_phone") );
					pCell.setText( results.getString("cell_phone") );
					pWork.setText( results.getString("work_phone") );
					pStreet.setText( results.getString("street") );
					pCity.setText( results.getString("city") );
					pState.setSelectedItem( results.getString("state") );
					pZip.setText( results.getString("zip") );
					String employer_id = results.getString("employer_id");
					
					if(! employer_id.isEmpty()) {
						sql="SELECT * FROM `ris`.`employer` WHERE `id`="+results.getString("employer_id");
						results = sentry.executeQuery(sql); results.first();
						eCompany.setText( results.getString("name") );
						eStreet.setText( results.getString("street") );
						eCity.setText( results.getString("city") );
						eState.setSelectedItem( results.getString("state") );
						eZip.setText( results.getString("zip") );
					}
					

				}
			} catch (SQLException e) { e.printStackTrace();}
		}
		
	}/*actionPerformed()*/}//viewPatientListener
	
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
