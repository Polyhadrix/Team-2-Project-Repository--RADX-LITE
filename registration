// package ris;
// Note: Work in progress. Converting from static layout to elastic layout.
// Up next: Improved database functions

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;

public class registration {
	/* Declare Form Elements */
	private static JLabel label; // Label can be re-usable (no dependencies)
	/*Declare Buttons*/
	//private static JButton button, btn_cancel, btn_submit, btn_yes, btn_no;
    /*Patient Fields*/
	private static JTextField pFirst, pMiddle, pLast, pBirth, pHome, pCell, pWork, pSSN, pStreet, pCity, pZip;
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
	private static JPanel rowPanel, stewardPanel;
	
	/*Open Database Connection (only once)*/
	private static Connection conn=openConnection();
	
	
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
        frame.setPreferredSize(new Dimension(940, 315));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add content to the pane.
        addComponentsToPane(frame.getContentPane());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
	
	public static void addComponentsToPane(Container pane) {

	/* Set stage and apply defaults */
		pane.setLayout(new GridBagLayout()); // GridBagLayout for elastic layouts
		rowPanel = new JPanel(new GridBagLayout());

	/* Make Form */
		JLabel title = new JLabel("Patient Registration");
		title.setFont(DEFLT_TITLE);
		GridBagConstraints c = new GridBagConstraints();
		c.gridx=0; c.gridy=currentRow; currentRow++;
		// c.weighty=0.1;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		pane.add(title,c);
		
		addLabel("First Name:",3,pFirst,pane); pFirst=addField(pFirst,3,pane);
		addLabel("MI:",1,pMiddle,pane);        pMiddle=addField(pMiddle,1,pane);
		addLabel("Last Name:",3,pLast,pane);   pLast=addField(pLast,3,pane);
		addLabel("Birthdate:",3,pBirth,pane);  pBirth=addField(pBirth,3,pane);
		addLabel("Sex:",2,pSex,pane);          pSex=addDropdown(pSex,sexes,2,pane);
		
		addLabel("Home Phone:",3,pHome,pane);  pHome=addField(pHome,3,pane);
		addLabel("Cell Phone:",3,pCell,pane);  pCell=addField(pCell,3,pane);
		addLabel("Work Phone:",3,pWork,pane);  pWork=addField(pWork,3,pane);
		addLabel("SSN:",3,pSSN,pane);          pSSN=addField(pSSN,3,pane);

		addLabel("Street:",2,pStreet,pane);    pStreet=addField(pStreet,9,pane);
		addLabel("City:",2,pCity,pane);        pCity=addField(pCity,4,pane);
		addLabel("State:",2,pState,pane);      pState=addDropdown(pState,states,1,pane);
		addLabel("ZIP:",2,pZip,pane);          pZip=addField(pZip,2,pane);

		addLabel("Employer Company:",5,eCompany,pane);   eCompany=addField(eCompany,3,pane); endLine(pane);

		addLabel("Street:",2,eStreet,pane);    eStreet=addField(eStreet,9,pane);
		addLabel("City:",2,eCity,pane);        eCity=addField(eCity,4,pane);
		addLabel("State:",2,eState,pane);      eState=addDropdown(eState,states,1,pane);
		addLabel("ZIP:",2,eZip,pane);          eZip=addField(eZip,2,pane);
		
		isDependent=addCheckBox(isDependent,"is a dependent",5,pane); endLine(pane);// to add final row
		
		// Hidden Steward's Section //
		stewardPanel = new JPanel(new GridBagLayout());
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
		
		pane.add(stewardPanel, c);
		
		isDependent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent checkboxClicked) 
			{
				if (isDependent.isSelected()) stewardPanel.setVisible(true);
				else stewardPanel.setVisible(false);
			}
		});
		
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
	
	/*Listeners*/
	public void actionPerformed(ActionEvent checkboxClicked) 
	{
		if (isDependent.isSelected()) stewardPanel.setVisible(true);
		else stewardPanel.setVisible(false);
	}
	
	//Database Functions
	/* Open a comm-link to the database */
	public static Connection openConnection(){
		try {
			Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", ""); //This part here created the connection to MySQL
			if(conn != null) System.out.println("Connection Established.");
			return conn;
		} catch (SQLException e) { System.out.println("Connection Failure: XAMPP is currently turned off. "); /*e.printStackTrace();*/ }
		return null;
	}
	
	public static ResultSet resultsFor(String sql) {
		try{
			Statement s = null;
			String query= sql;
			s=conn.createStatement();
			return s.executeQuery(query);
		} catch (SQLException e) { System.out.print("Database Error: \n"); e.printStackTrace(); return null;}
	}

}
