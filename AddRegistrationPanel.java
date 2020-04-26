package ris;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class AddRegistrationPanel {
	private static Container mainContent;
	private static Connection conn;
	/*Patient Fields*/
	private static JTextField search, pFirst, pMiddle, pLast, pBirth, pHome, pCell, pWork, pSSN, pStreet, pCity, pZip;
    /*Employer Fields*/
	private static JTextField eCompany, eStreet, eCity, eZip;
    /*Steward Fields*/
	private static JTextField sName, sRelation, sSSN, sHome, sCell, sWork, sStreet, sCity, sZip;
	/* CheckBoxes */
	private static JCheckBox isDependent;	
	/* Drop-Downs */
	private static JComboBox<String> pSex, pState, eState, sState;
	/*Drop-Down Values*/
	private static String[] states = {"","Alabama","Alaska","Arkansas","California","Colorado","Connecticut","Delaware",
			"Florida","Georgia","Hawaii","Idaho","Illinois","Indiana","Iowa","Kansas","Kentucky","Louisiana",
			"Maine","Maryland","Massachusetts","Michigan","Minnesota","Mississippi","Missouri","Montana",
			"Nebraska","Nevada","New Hampshire","New Jersey","New Mexico","New York","North Carolina",
			"North Dakota","Ohio","Oklahoma","Oregon","Pennsylvania","Rhode Island","South Carolina",
			"South Dakota","Tennessee","Texas","Utah","Vermont","Virginia","Washington",
			"West Virginia","Wisconsin","Wyoming"};
	
	/* Panels */
	private static JPanel pnl_steward;
	
	public AddRegistrationPanel(JFrame param_window, Connection param_conn) {
		mainContent = param_window.getContentPane();
		conn = param_conn;
		createPnlRegistration();
	}
	
	public static void createPnlRegistration() {
		JPanel pnl_registration = new JPanel();
		GridBagConstraints gbc_pnl_registration = new GridBagConstraints();
		gbc_pnl_registration.fill = GridBagConstraints.HORIZONTAL;
		gbc_pnl_registration.anchor = GridBagConstraints.NORTH;
		gbc_pnl_registration.gridx = 0;
		gbc_pnl_registration.gridy = 0;
		mainContent.add(pnl_registration, gbc_pnl_registration);
		GridBagLayout gbl_pnl_registration = new GridBagLayout();
		gbl_pnl_registration.columnWidths = new int[]{0, 0};
		gbl_pnl_registration.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_pnl_registration.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_pnl_registration.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		pnl_registration.setLayout(gbl_pnl_registration);
		
		JPanel row0 = new JPanel();
		GridBagConstraints gbc_row0 = new GridBagConstraints();
		gbc_row0.anchor = GridBagConstraints.NORTH;
		gbc_row0.fill = GridBagConstraints.HORIZONTAL;
		gbc_row0.insets = new Insets(0, 0, 5, 0);
		gbc_row0.gridx = 0;
		gbc_row0.gridy = 0;
		pnl_registration.add(row0, gbc_row0);
		GridBagLayout gbl_row0 = new GridBagLayout();
		gbl_row0.columnWidths = new int[]{189, 0};
		gbl_row0.rowHeights = new int[]{22, 0};
		gbl_row0.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_row0.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		row0.setLayout(gbl_row0);
		
		JLabel lbl_Title = new JLabel("Patient Registration");
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
		gbc_row1.anchor = GridBagConstraints.NORTH;
		gbc_row1.fill = GridBagConstraints.HORIZONTAL;
		gbc_row1.insets = new Insets(0, 0, 5, 0);
		gbc_row1.gridx = 0;
		gbc_row1.gridy = 1;
		pnl_registration.add(row1, gbc_row1);
		GridBagLayout gbl_row1 = new GridBagLayout();
		gbl_row1.columnWidths = new int[] {0};
		gbl_row1.rowHeights = new int[] {0};
		gbl_row1.columnWeights = new double[]{0.0, 1.0};
		gbl_row1.rowWeights = new double[]{0.0};
		row1.setLayout(gbl_row1);
		
		JLabel lbl_search = new JLabel("To View/ Edit Existing Patient, enter their Patient ID# here and hit 'Enter': ");
		lbl_search.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lbl_search = new GridBagConstraints();
		gbc_lbl_search.insets = new Insets(0, 5, 5, 5);
		gbc_lbl_search.gridx = 0;
		gbc_lbl_search.gridy = 0;
		row1.add(lbl_search, gbc_lbl_search);
		
		search = new JTextField();
		search.setFont(new Font("Tahoma", Font.PLAIN, 14));
		search.addActionListener( new viewPatientListener() );
		GridBagConstraints gbc_search = new GridBagConstraints();
		gbc_search.fill = GridBagConstraints.HORIZONTAL;
		gbc_search.gridx = 1;
		gbc_search.gridy = 0;
		row1.add(search, gbc_search);
		search.setColumns(10);
		
		JPanel row2 = new JPanel();
		GridBagConstraints gbc_row2 = new GridBagConstraints();
		gbc_row2.anchor = GridBagConstraints.NORTH;
		gbc_row2.fill = GridBagConstraints.HORIZONTAL;
		gbc_row2.insets = new Insets(0, 0, 5, 0);
		gbc_row2.gridx = 0;
		gbc_row2.gridy = 2;
		pnl_registration.add(row2, gbc_row2);
		GridBagLayout gbl_row2 = new GridBagLayout();
		gbl_row2.columnWidths = new int[] {0};
		gbl_row2.rowHeights = new int[] {0};
		gbl_row2.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0};
		gbl_row2.rowWeights = new double[]{0.0};
		row2.setLayout(gbl_row2);
		
		JLabel lbl_pFirst = new JLabel("First Name: ");
		lbl_pFirst.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_pFirst.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lbl_pFirst = new GridBagConstraints();
		gbc_lbl_pFirst.anchor = GridBagConstraints.LINE_START;
		gbc_lbl_pFirst.insets = new Insets(0, 5, 0, 5);
		gbc_lbl_pFirst.gridx = 0;
		gbc_lbl_pFirst.gridy = 0;
		row2.add(lbl_pFirst, gbc_lbl_pFirst);
		
		pFirst = new JTextField();
		GridBagConstraints gbc_pFirst = new GridBagConstraints();
		gbc_pFirst.insets = new Insets(0, 0, 0, 5);
		gbc_pFirst.weightx = 3.0;
		gbc_pFirst.fill = GridBagConstraints.HORIZONTAL;
		gbc_pFirst.gridx = 1;
		gbc_pFirst.gridy = 0;
		row2.add(pFirst, gbc_pFirst);
		pFirst.setColumns(10);
		
		JLabel lbl_pMiddle = new JLabel(" MI");
		lbl_pMiddle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lbl_pMiddle = new GridBagConstraints();
		gbc_lbl_pMiddle.anchor = GridBagConstraints.EAST;
		gbc_lbl_pMiddle.insets = new Insets(0, 0, 0, 5);
		gbc_lbl_pMiddle.gridx = 2;
		gbc_lbl_pMiddle.gridy = 0;
		row2.add(lbl_pMiddle, gbc_lbl_pMiddle);
		
		pMiddle = new JTextField();
		GridBagConstraints gbc_pMiddle = new GridBagConstraints();
		gbc_pMiddle.weightx = 1.0;
		gbc_pMiddle.insets = new Insets(0, 0, 0, 5);
		gbc_pMiddle.fill = GridBagConstraints.HORIZONTAL;
		gbc_pMiddle.gridx = 3;
		gbc_pMiddle.gridy = 0;
		row2.add(pMiddle, gbc_pMiddle);
		pMiddle.setColumns(10);
		
		JLabel lbl_pLast = new JLabel("Last: ");
		lbl_pLast.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lbl_pLast = new GridBagConstraints();
		gbc_lbl_pLast.insets = new Insets(0, 0, 0, 5);
		gbc_lbl_pLast.gridx = 4;
		gbc_lbl_pLast.gridy = 0;
		row2.add(lbl_pLast, gbc_lbl_pLast);
		
		pLast = new JTextField();
		GridBagConstraints gbc_pLast = new GridBagConstraints();
		gbc_pLast.weightx = 3.0;
		gbc_pLast.insets = new Insets(0, 0, 0, 5);
		gbc_pLast.fill = GridBagConstraints.HORIZONTAL;
		gbc_pLast.gridx = 5;
		gbc_pLast.gridy = 0;
		row2.add(pLast, gbc_pLast);
		pLast.setColumns(10);
		
		JLabel lbl_pBirth = new JLabel("Birthdate: ");
		lbl_pBirth.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lbl_pBirth = new GridBagConstraints();
		gbc_lbl_pBirth.insets = new Insets(0, 0, 0, 5);
		gbc_lbl_pBirth.gridx = 6;
		gbc_lbl_pBirth.gridy = 0;
		row2.add(lbl_pBirth, gbc_lbl_pBirth);
		
		pBirth = new JTextField();
		GridBagConstraints gbc_pBirth = new GridBagConstraints();
		gbc_pBirth.weightx = 3.0;
		gbc_pBirth.insets = new Insets(0, 0, 0, 5);
		gbc_pBirth.fill = GridBagConstraints.HORIZONTAL;
		gbc_pBirth.gridx = 7;
		gbc_pBirth.gridy = 0;
		row2.add(pBirth, gbc_pBirth);
		pBirth.setColumns(10);
		
		JLabel lbl_pSex = new JLabel("Sex: ");
		lbl_pSex.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lbl_pSex = new GridBagConstraints();
		gbc_lbl_pSex.insets = new Insets(0, 0, 0, 5);
		gbc_lbl_pSex.gridx = 8;
		gbc_lbl_pSex.gridy = 0;
		row2.add(lbl_pSex, gbc_lbl_pSex);
		
		pSex = new JComboBox<String>();
		pSex.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pSex.setModel(new DefaultComboBoxModel<String>(new String[] {"", "Male", "Female", "Intersex"}));
		GridBagConstraints gbc_pSex = new GridBagConstraints();
		gbc_pSex.weightx = 1.0;
		gbc_pSex.insets = new Insets(0, 0, 0, 5);
		gbc_pSex.fill = GridBagConstraints.HORIZONTAL;
		gbc_pSex.gridx = 9;
		gbc_pSex.gridy = 0;
		row2.add(pSex, gbc_pSex);
		
		JPanel row3 = new JPanel();
		GridBagConstraints gbc_row3 = new GridBagConstraints();
		gbc_row3.anchor = GridBagConstraints.NORTH;
		gbc_row3.fill = GridBagConstraints.HORIZONTAL;
		gbc_row3.insets = new Insets(0, 0, 5, 0);
		gbc_row3.gridx = 0;
		gbc_row3.gridy = 3;
		pnl_registration.add(row3, gbc_row3);
		GridBagLayout gbl_row3 = new GridBagLayout();
		gbl_row3.columnWidths = new int[] {0};
		gbl_row3.rowHeights = new int[] {0};
		gbl_row3.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0};
		gbl_row3.rowWeights = new double[]{0.0};
		row3.setLayout(gbl_row3);
		
		JLabel lbl_pHome = new JLabel("Home Phone: ");
		lbl_pHome.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_pHome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lbl_pHome = new GridBagConstraints();
		gbc_lbl_pHome.anchor = GridBagConstraints.LINE_START;
		gbc_lbl_pHome.insets = new Insets(0, 5, 0, 5);
		gbc_lbl_pHome.gridx = 0;
		gbc_lbl_pHome.gridy = 0;
		row3.add(lbl_pHome, gbc_lbl_pHome);
		
		pHome = new JTextField();
		pHome.setColumns(10);
		GridBagConstraints gbc_pHome = new GridBagConstraints();
		gbc_pHome.weightx = 3.0;
		gbc_pHome.fill = GridBagConstraints.HORIZONTAL;
		gbc_pHome.insets = new Insets(0, 0, 0, 5);
		gbc_pHome.gridx = 1;
		gbc_pHome.gridy = 0;
		row3.add(pHome, gbc_pHome);
		
		JLabel lbl_pCell = new JLabel("Cell Phone: ");
		lbl_pCell.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lbl_pCell = new GridBagConstraints();
		gbc_lbl_pCell.anchor = GridBagConstraints.EAST;
		gbc_lbl_pCell.insets = new Insets(0, 0, 0, 5);
		gbc_lbl_pCell.gridx = 2;
		gbc_lbl_pCell.gridy = 0;
		row3.add(lbl_pCell, gbc_lbl_pCell);
		
		pCell = new JTextField();
		pCell.setColumns(10);
		GridBagConstraints gbc_pCell = new GridBagConstraints();
		gbc_pCell.weightx = 3.0;
		gbc_pCell.fill = GridBagConstraints.HORIZONTAL;
		gbc_pCell.insets = new Insets(0, 0, 0, 5);
		gbc_pCell.gridx = 3;
		gbc_pCell.gridy = 0;
		row3.add(pCell, gbc_pCell);
		
		JLabel lbl_pWork = new JLabel("Work Phone:  ");
		lbl_pWork.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lbl_pWork = new GridBagConstraints();
		gbc_lbl_pWork.insets = new Insets(0, 0, 0, 5);
		gbc_lbl_pWork.gridx = 4;
		gbc_lbl_pWork.gridy = 0;
		row3.add(lbl_pWork, gbc_lbl_pWork);
		
		pWork = new JTextField();
		pWork.setColumns(10);
		GridBagConstraints gbc_pWork = new GridBagConstraints();
		gbc_pWork.weightx = 3.0;
		gbc_pWork.fill = GridBagConstraints.HORIZONTAL;
		gbc_pWork.insets = new Insets(0, 0, 0, 5);
		gbc_pWork.gridx = 5;
		gbc_pWork.gridy = 0;
		row3.add(pWork, gbc_pWork);
		
		JLabel lbl_pSSN = new JLabel("SSN: ");
		lbl_pSSN.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lbl_pSSN = new GridBagConstraints();
		gbc_lbl_pSSN.insets = new Insets(0, 0, 0, 5);
		gbc_lbl_pSSN.gridx = 6;
		gbc_lbl_pSSN.gridy = 0;
		row3.add(lbl_pSSN, gbc_lbl_pSSN);
		
		pSSN = new JTextField();
		pSSN.setColumns(10);
		GridBagConstraints gbc_pSSN = new GridBagConstraints();
		gbc_pSSN.weightx = 3.0;
		gbc_pSSN.fill = GridBagConstraints.HORIZONTAL;
		gbc_pSSN.insets = new Insets(0, 0, 0, 5);
		gbc_pSSN.gridx = 7;
		gbc_pSSN.gridy = 0;
		row3.add(pSSN, gbc_pSSN);
		
		JPanel row4 = new JPanel();
		GridBagConstraints gbc_row4 = new GridBagConstraints();
		gbc_row4.anchor = GridBagConstraints.NORTH;
		gbc_row4.fill = GridBagConstraints.HORIZONTAL;
		gbc_row4.insets = new Insets(0, 0, 5, 0);
		gbc_row4.gridx = 0;
		gbc_row4.gridy = 4;
		pnl_registration.add(row4, gbc_row4);
		GridBagLayout gbl_row4 = new GridBagLayout();
		gbl_row4.columnWidths = new int[] {0};
		gbl_row4.rowHeights = new int[] {0};
		gbl_row4.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0};
		gbl_row4.rowWeights = new double[]{0.0};
		row4.setLayout(gbl_row4);
		
		JLabel lbl_pStreet = new JLabel("Street: ");
		lbl_pStreet.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_pStreet.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lbl_pStreet = new GridBagConstraints();
		gbc_lbl_pStreet.insets = new Insets(0, 5, 0, 5);
		gbc_lbl_pStreet.gridx = 0;
		gbc_lbl_pStreet.gridy = 0;
		row4.add(lbl_pStreet, gbc_lbl_pStreet);
		
		pStreet = new JTextField();
		pStreet.setColumns(10);
		GridBagConstraints gbc_pStreet = new GridBagConstraints();
		gbc_pStreet.weightx = 3.0;
		gbc_pStreet.fill = GridBagConstraints.HORIZONTAL;
		gbc_pStreet.insets = new Insets(0, 0, 0, 5);
		gbc_pStreet.gridx = 1;
		gbc_pStreet.gridy = 0;
		row4.add(pStreet, gbc_pStreet);
		
		JLabel lbl_pCity = new JLabel("City: ");
		lbl_pCity.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lbl_pCity = new GridBagConstraints();
		gbc_lbl_pCity.insets = new Insets(0, 0, 0, 5);
		gbc_lbl_pCity.gridx = 2;
		gbc_lbl_pCity.gridy = 0;
		row4.add(lbl_pCity, gbc_lbl_pCity);
		
		pCity = new JTextField();
		pCity.setColumns(10);
		GridBagConstraints gbc_pCity = new GridBagConstraints();
		gbc_pCity.weightx = 1.0;
		gbc_pCity.fill = GridBagConstraints.HORIZONTAL;
		gbc_pCity.insets = new Insets(0, 0, 0, 5);
		gbc_pCity.gridx = 3;
		gbc_pCity.gridy = 0;
		row4.add(pCity, gbc_pCity);
		
		JLabel lbl_pState = new JLabel("State: ");
		lbl_pState.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lbl_pState = new GridBagConstraints();
		gbc_lbl_pState.insets = new Insets(0, 0, 0, 5);
		gbc_lbl_pState.gridx = 4;
		gbc_lbl_pState.gridy = 0;
		row4.add(lbl_pState, gbc_lbl_pState);
		
		pState = new JComboBox<String>();
		pState.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pState.setModel(new DefaultComboBoxModel<String>(states));
		GridBagConstraints gbc_pState = new GridBagConstraints();
		gbc_pState.insets = new Insets(0, 0, 0, 5);
		gbc_pState.weightx = 1.0;
		gbc_pState.fill = GridBagConstraints.HORIZONTAL;
		gbc_pState.gridx = 5;
		gbc_pState.gridy = 0;
		row4.add(pState, gbc_pState);
		
		JLabel lbl_pZip = new JLabel("ZIP: ");
		lbl_pZip.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lbl_pZip = new GridBagConstraints();
		gbc_lbl_pZip.insets = new Insets(0, 0, 0, 5);
		gbc_lbl_pZip.gridx = 6;
		gbc_lbl_pZip.gridy = 0;
		row4.add(lbl_pZip, gbc_lbl_pZip);
		
		pZip = new JTextField();
		pZip.setColumns(10);
		GridBagConstraints gbc_pZip = new GridBagConstraints();
		gbc_pZip.weightx = 3.0;
		gbc_pZip.fill = GridBagConstraints.HORIZONTAL;
		gbc_pZip.insets = new Insets(0, 0, 0, 5);
		gbc_pZip.gridx = 7;
		gbc_pZip.gridy = 0;
		row4.add(pZip, gbc_pZip);
		
		JPanel row5 = new JPanel();
		GridBagConstraints gbc_row5 = new GridBagConstraints();
		gbc_row5.fill = GridBagConstraints.BOTH;
		gbc_row5.insets = new Insets(0, 0, 5, 0);
		gbc_row5.gridx = 0;
		gbc_row5.gridy = 5;
		pnl_registration.add(row5, gbc_row5);
		GridBagLayout gbl_row5 = new GridBagLayout();
		gbl_row5.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_row5.rowHeights = new int[]{0, 0};
		gbl_row5.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_row5.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		row5.setLayout(gbl_row5);
		
		JLabel lbl_eCompany = new JLabel("Employer Company: ");
		lbl_eCompany.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_eCompany.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lbl_eCompany = new GridBagConstraints();
		gbc_lbl_eCompany.insets = new Insets(0, 5, 0, 5);
		gbc_lbl_eCompany.gridx = 0;
		gbc_lbl_eCompany.gridy = 0;
		row5.add(lbl_eCompany, gbc_lbl_eCompany);
		
		eCompany = new JTextField();
		eCompany.setColumns(10);
		GridBagConstraints gbc_eCompany = new GridBagConstraints();
		gbc_eCompany.weightx = 3.0;
		gbc_eCompany.fill = GridBagConstraints.HORIZONTAL;
		gbc_eCompany.insets = new Insets(0, 0, 0, 5);
		gbc_eCompany.gridx = 1;
		gbc_eCompany.gridy = 0;
		row5.add(eCompany, gbc_eCompany);
		
		JPanel row6 = new JPanel();
		GridBagConstraints gbc_row6 = new GridBagConstraints();
		gbc_row6.fill = GridBagConstraints.BOTH;
		gbc_row6.insets = new Insets(0, 0, 5, 0);
		gbc_row6.gridx = 0;
		gbc_row6.gridy = 6;
		pnl_registration.add(row6, gbc_row6);
		GridBagLayout gbl_row6 = new GridBagLayout();
		gbl_row6.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_row6.rowHeights = new int[]{0, 0};
		gbl_row6.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_row6.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		row6.setLayout(gbl_row6);
		
		JLabel lbl_eStreet = new JLabel("Street: ");
		lbl_eStreet.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_eStreet.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lbl_eStreet = new GridBagConstraints();
		gbc_lbl_eStreet.insets = new Insets(0, 5, 0, 5);
		gbc_lbl_eStreet.gridx = 0;
		gbc_lbl_eStreet.gridy = 0;
		row6.add(lbl_eStreet, gbc_lbl_eStreet);
		
		eStreet = new JTextField();
		eStreet.setColumns(10);
		GridBagConstraints gbc_eStreet = new GridBagConstraints();
		gbc_eStreet.anchor = GridBagConstraints.NORTH;
		gbc_eStreet.weightx = 3.0;
		gbc_eStreet.fill = GridBagConstraints.HORIZONTAL;
		gbc_eStreet.insets = new Insets(0, 0, 0, 5);
		gbc_eStreet.gridx = 1;
		gbc_eStreet.gridy = 0;
		row6.add(eStreet, gbc_eStreet);
		
		JLabel lbl_eCity = new JLabel("City: ");
		lbl_eCity.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lbl_eCity = new GridBagConstraints();
		gbc_lbl_eCity.insets = new Insets(0, 0, 0, 5);
		gbc_lbl_eCity.gridx = 2;
		gbc_lbl_eCity.gridy = 0;
		row6.add(lbl_eCity, gbc_lbl_eCity);
		
		eCity = new JTextField();
		eCity.setColumns(10);
		GridBagConstraints gbc_eCity = new GridBagConstraints();
		gbc_eCity.weightx = 1.0;
		gbc_eCity.fill = GridBagConstraints.HORIZONTAL;
		gbc_eCity.insets = new Insets(0, 0, 0, 5);
		gbc_eCity.gridx = 3;
		gbc_eCity.gridy = 0;
		row6.add(eCity, gbc_eCity);
		
		JLabel lbl_eState = new JLabel("State: ");
		lbl_eState.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lbl_eState = new GridBagConstraints();
		gbc_lbl_eState.insets = new Insets(0, 0, 0, 5);
		gbc_lbl_eState.gridx = 4;
		gbc_lbl_eState.gridy = 0;
		row6.add(lbl_eState, gbc_lbl_eState);
		
		eState = new JComboBox<String>();
		eState.setFont(new Font("Tahoma", Font.PLAIN, 14));
		eState.setModel(new DefaultComboBoxModel<String>(states));
		GridBagConstraints gbc_eState = new GridBagConstraints();
		gbc_eState.weightx = 1.0;
		gbc_eState.fill = GridBagConstraints.HORIZONTAL;
		gbc_eState.insets = new Insets(0, 0, 0, 5);
		gbc_eState.gridx = 5;
		gbc_eState.gridy = 0;
		row6.add(eState, gbc_eState);
		
		JLabel lbl_eZip = new JLabel("ZIP: ");
		lbl_eZip.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lbl_eZip = new GridBagConstraints();
		gbc_lbl_eZip.insets = new Insets(0, 0, 0, 5);
		gbc_lbl_eZip.gridx = 6;
		gbc_lbl_eZip.gridy = 0;
		row6.add(lbl_eZip, gbc_lbl_eZip);
		
		eZip = new JTextField();
		eZip.setColumns(10);
		GridBagConstraints gbc_eZip = new GridBagConstraints();
		gbc_eZip.weightx = 3.0;
		gbc_eZip.insets = new Insets(0, 0, 0, 5);
		gbc_eZip.fill = GridBagConstraints.HORIZONTAL;
		gbc_eZip.gridx = 7;
		gbc_eZip.gridy = 0;
		row6.add(eZip, gbc_eZip);
		
		JPanel row7 = new JPanel();
		GridBagConstraints gbc_row7 = new GridBagConstraints();
		gbc_row7.fill = GridBagConstraints.BOTH;
		gbc_row7.insets = new Insets(0, 0, 5, 0);
		gbc_row7.gridx = 0;
		gbc_row7.gridy = 7;
		pnl_registration.add(row7, gbc_row7);
		GridBagLayout gbl_row7 = new GridBagLayout();
		gbl_row7.columnWidths = new int[] {0, 0};
		gbl_row7.rowHeights = new int[] {0};
		gbl_row7.columnWeights = new double[]{0.0, 1.0};
		gbl_row7.rowWeights = new double[]{0.0};
		row7.setLayout(gbl_row7);
		
		isDependent = new JCheckBox("is a dependent");
		isDependent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnl_steward.setVisible( isDependent.isSelected() );
			}
		});
		isDependent.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_isDependent = new GridBagConstraints();
		gbc_isDependent.insets = new Insets(0, 0, 0, 5);
		gbc_isDependent.gridx = 0;
		gbc_isDependent.gridy = 0;
		row7.add(isDependent, gbc_isDependent);
		
		pnl_steward = new JPanel();
		GridBagConstraints gbc_pnl_steward = new GridBagConstraints();
		gbc_pnl_steward.fill = GridBagConstraints.BOTH;
		gbc_pnl_steward.insets = new Insets(0, 0, 5, 0);
		gbc_pnl_steward.gridx = 0;
		gbc_pnl_steward.gridy = 8;
		pnl_registration.add(pnl_steward, gbc_pnl_steward);
		pnl_steward.setVisible(false);
		GridBagLayout gbl_pnl_steward = new GridBagLayout();
		gbl_pnl_steward.columnWidths = new int[]{0, 0};
		gbl_pnl_steward.rowHeights = new int[]{0, 0, 0, 0};
		gbl_pnl_steward.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_pnl_steward.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		pnl_steward.setLayout(gbl_pnl_steward);
		
		JPanel row8_0 = new JPanel();
		GridBagConstraints gbc_row8_0 = new GridBagConstraints();
		gbc_row8_0.fill = GridBagConstraints.BOTH;
		gbc_row8_0.insets = new Insets(0, 0, 5, 0);
		gbc_row8_0.gridx = 0;
		gbc_row8_0.gridy = 0;
		pnl_steward.add(row8_0, gbc_row8_0);
		GridBagLayout gbl_row8_0 = new GridBagLayout();
		gbl_row8_0.columnWidths = new int[] {0};
		gbl_row8_0.rowHeights = new int[] {0};
		gbl_row8_0.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, 1.0};
		gbl_row8_0.rowWeights = new double[]{0.0};
		row8_0.setLayout(gbl_row8_0);
		
		JLabel lbl_sName = new JLabel("Steward's Name: ");
		lbl_sName.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_sName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lbl_sName = new GridBagConstraints();
		gbc_lbl_sName.insets = new Insets(0, 5, 0, 5);
		gbc_lbl_sName.gridx = 0;
		gbc_lbl_sName.gridy = 0;
		row8_0.add(lbl_sName, gbc_lbl_sName);
		
		sName = new JTextField();
		sName.setColumns(10);
		GridBagConstraints gbc_sName = new GridBagConstraints();
		gbc_sName.weightx = 3.0;
		gbc_sName.fill = GridBagConstraints.HORIZONTAL;
		gbc_sName.insets = new Insets(0, 0, 0, 5);
		gbc_sName.gridx = 1;
		gbc_sName.gridy = 0;
		row8_0.add(sName, gbc_sName);
		
		JLabel lbl_sRelation = new JLabel("Relationship: ");
		lbl_sRelation.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lbl_sRelation = new GridBagConstraints();
		gbc_lbl_sRelation.insets = new Insets(0, 0, 0, 5);
		gbc_lbl_sRelation.gridx = 2;
		gbc_lbl_sRelation.gridy = 0;
		row8_0.add(lbl_sRelation, gbc_lbl_sRelation);
		
		sRelation = new JTextField();
		sRelation.setColumns(10);
		GridBagConstraints gbc_sRelation = new GridBagConstraints();
		gbc_sRelation.weightx = 1.0;
		gbc_sRelation.fill = GridBagConstraints.HORIZONTAL;
		gbc_sRelation.insets = new Insets(0, 0, 0, 5);
		gbc_sRelation.gridx = 3;
		gbc_sRelation.gridy = 0;
		row8_0.add(sRelation, gbc_sRelation);
		
		JLabel lbl_sSSN = new JLabel("Steward SSN: ");
		lbl_sSSN.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lbl_sSSN = new GridBagConstraints();
		gbc_lbl_sSSN.anchor = GridBagConstraints.EAST;
		gbc_lbl_sSSN.insets = new Insets(0, 0, 0, 5);
		gbc_lbl_sSSN.gridx = 4;
		gbc_lbl_sSSN.gridy = 0;
		row8_0.add(lbl_sSSN, gbc_lbl_sSSN);
		
		sSSN = new JTextField();
		sSSN.setColumns(10);
		GridBagConstraints gbc_sSSN = new GridBagConstraints();
		gbc_sSSN.insets = new Insets(0, 0, 0, 5);
		gbc_sSSN.fill = GridBagConstraints.HORIZONTAL;
		gbc_sSSN.gridx = 5;
		gbc_sSSN.gridy = 0;
		row8_0.add(sSSN, gbc_sSSN);
		
		JPanel row8_1 = new JPanel();
		GridBagConstraints gbc_row8_1 = new GridBagConstraints();
		gbc_row8_1.fill = GridBagConstraints.BOTH;
		gbc_row8_1.insets = new Insets(0, 0, 5, 0);
		gbc_row8_1.gridx = 0;
		gbc_row8_1.gridy = 1;
		pnl_steward.add(row8_1, gbc_row8_1);
		GridBagLayout gbl_row8_1 = new GridBagLayout();
		gbl_row8_1.columnWidths = new int[] {0};
		gbl_row8_1.rowHeights = new int[] {0};
		gbl_row8_1.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, 1.0};
		gbl_row8_1.rowWeights = new double[]{1.0};
		row8_1.setLayout(gbl_row8_1);
		
		JLabel lbl_sHome = new JLabel("Home Phone: ");
		lbl_sHome.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_sHome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lbl_sHome = new GridBagConstraints();
		gbc_lbl_sHome.anchor = GridBagConstraints.LINE_START;
		gbc_lbl_sHome.insets = new Insets(0, 5, 0, 5);
		gbc_lbl_sHome.gridx = 0;
		gbc_lbl_sHome.gridy = 0;
		row8_1.add(lbl_sHome, gbc_lbl_sHome);
		
		sHome = new JTextField();
		sHome.setColumns(10);
		GridBagConstraints gbc_sHome = new GridBagConstraints();
		gbc_sHome.weightx = 3.0;
		gbc_sHome.fill = GridBagConstraints.BOTH;
		gbc_sHome.insets = new Insets(0, 0, 0, 5);
		gbc_sHome.gridx = 1;
		gbc_sHome.gridy = 0;
		row8_1.add(sHome, gbc_sHome);
		
		JLabel lbl_sCell = new JLabel("Cell Phone: ");
		lbl_sCell.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lbl_sCell = new GridBagConstraints();
		gbc_lbl_sCell.anchor = GridBagConstraints.EAST;
		gbc_lbl_sCell.insets = new Insets(0, 0, 0, 5);
		gbc_lbl_sCell.gridx = 2;
		gbc_lbl_sCell.gridy = 0;
		row8_1.add(lbl_sCell, gbc_lbl_sCell);
		
		sCell = new JTextField();
		sCell.setColumns(10);
		GridBagConstraints gbc_sCell = new GridBagConstraints();
		gbc_sCell.weightx = 3.0;
		gbc_sCell.fill = GridBagConstraints.HORIZONTAL;
		gbc_sCell.insets = new Insets(0, 0, 0, 5);
		gbc_sCell.gridx = 3;
		gbc_sCell.gridy = 0;
		row8_1.add(sCell, gbc_sCell);
		
		JLabel lbl_sWork = new JLabel("Work Phone:  ");
		lbl_sWork.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lbl_sWork = new GridBagConstraints();
		gbc_lbl_sWork.insets = new Insets(0, 0, 0, 5);
		gbc_lbl_sWork.gridx = 4;
		gbc_lbl_sWork.gridy = 0;
		row8_1.add(lbl_sWork, gbc_lbl_sWork);
		
		sWork = new JTextField();
		sWork.setColumns(10);
		GridBagConstraints gbc_sWork = new GridBagConstraints();
		gbc_sWork.weightx = 3.0;
		gbc_sWork.fill = GridBagConstraints.HORIZONTAL;
		gbc_sWork.insets = new Insets(0, 0, 0, 5);
		gbc_sWork.gridx = 5;
		gbc_sWork.gridy = 0;
		row8_1.add(sWork, gbc_sWork);
		
		JPanel row8_2 = new JPanel();
		GridBagConstraints gbc_row8_2 = new GridBagConstraints();
		gbc_row8_2.fill = GridBagConstraints.BOTH;
		gbc_row8_2.gridx = 0;
		gbc_row8_2.gridy = 2;
		pnl_steward.add(row8_2, gbc_row8_2);
		GridBagLayout gbl_row8_2 = new GridBagLayout();
		gbl_row8_2.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_row8_2.rowHeights = new int[]{0, 0};
		gbl_row8_2.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_row8_2.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		row8_2.setLayout(gbl_row8_2);
		
		JLabel lbl_sStreet = new JLabel("Street: ");
		lbl_sStreet.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_sStreet.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lbl_sStreet = new GridBagConstraints();
		gbc_lbl_sStreet.insets = new Insets(0, 5, 0, 5);
		gbc_lbl_sStreet.gridx = 0;
		gbc_lbl_sStreet.gridy = 0;
		row8_2.add(lbl_sStreet, gbc_lbl_sStreet);
		
		sStreet = new JTextField();
		sStreet.setColumns(10);
		GridBagConstraints gbc_sStreet = new GridBagConstraints();
		gbc_sStreet.weightx = 3.0;
		gbc_sStreet.fill = GridBagConstraints.HORIZONTAL;
		gbc_sStreet.insets = new Insets(0, 0, 0, 5);
		gbc_sStreet.gridx = 1;
		gbc_sStreet.gridy = 0;
		row8_2.add(sStreet, gbc_sStreet);
		
		JLabel lbl_sCity = new JLabel("City: ");
		lbl_sCity.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lbl_sCity = new GridBagConstraints();
		gbc_lbl_sCity.insets = new Insets(0, 0, 0, 5);
		gbc_lbl_sCity.gridx = 2;
		gbc_lbl_sCity.gridy = 0;
		row8_2.add(lbl_sCity, gbc_lbl_sCity);
		
		sCity = new JTextField();
		sCity.setColumns(10);
		GridBagConstraints gbc_sCity = new GridBagConstraints();
		gbc_sCity.weightx = 1.0;
		gbc_sCity.fill = GridBagConstraints.HORIZONTAL;
		gbc_sCity.insets = new Insets(0, 0, 0, 5);
		gbc_sCity.gridx = 3;
		gbc_sCity.gridy = 0;
		row8_2.add(sCity, gbc_sCity);
		
		JLabel lbl_sState = new JLabel("State: ");
		lbl_sState.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lbl_sState = new GridBagConstraints();
		gbc_lbl_sState.insets = new Insets(0, 0, 0, 5);
		gbc_lbl_sState.gridx = 4;
		gbc_lbl_sState.gridy = 0;
		row8_2.add(lbl_sState, gbc_lbl_sState);
		
		sState = new JComboBox<String>();
		sState.setFont(new Font("Tahoma", Font.PLAIN, 14));
		sState.setModel(new DefaultComboBoxModel<String>(states));
		GridBagConstraints gbc_sState = new GridBagConstraints();
		gbc_sState.weightx = 1.0;
		gbc_sState.fill = GridBagConstraints.HORIZONTAL;
		gbc_sState.insets = new Insets(0, 0, 0, 5);
		gbc_sState.gridx = 5;
		gbc_sState.gridy = 0;
		row8_2.add(sState, gbc_sState);
		
		JLabel lbl_sZip = new JLabel("ZIP: ");
		lbl_sZip.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lbl_sZip = new GridBagConstraints();
		gbc_lbl_sZip.insets = new Insets(0, 0, 0, 5);
		gbc_lbl_sZip.gridx = 6;
		gbc_lbl_sZip.gridy = 0;
		row8_2.add(lbl_sZip, gbc_lbl_sZip);
		
		sZip = new JTextField();
		sZip.setColumns(10);
		GridBagConstraints gbc_sZip = new GridBagConstraints();
		gbc_sZip.weightx = 3.0;
		gbc_sZip.insets = new Insets(0, 0, 0, 5);
		gbc_sZip.fill = GridBagConstraints.HORIZONTAL;
		gbc_sZip.gridx = 7;
		gbc_sZip.gridy = 0;
		row8_2.add(sZip, gbc_sZip);
		
		JPanel row9 = new JPanel();
		GridBagConstraints gbc_row9 = new GridBagConstraints();
		gbc_row9.anchor = GridBagConstraints.NORTH;
		gbc_row9.fill = GridBagConstraints.HORIZONTAL;
		gbc_row9.gridx = 0;
		gbc_row9.gridy = 9;
		pnl_registration.add(row9, gbc_row9);
		GridBagLayout gbl_row9 = new GridBagLayout();
		gbl_row9.columnWidths = new int[]{0, 0, 0};
		gbl_row9.rowHeights = new int[]{0, 0};
		gbl_row9.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_row9.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		row9.setLayout(gbl_row9);
		
		JButton btn_submitPatient = new JButton("Register Patient");
		btn_submitPatient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn_submitPatient.addActionListener(new addPatientListener() );
		GridBagConstraints gbc_btn_submitPatient = new GridBagConstraints();
		gbc_btn_submitPatient.insets = new Insets(0, 5, 0, 5);
		gbc_btn_submitPatient.gridx = 0;
		gbc_btn_submitPatient.gridy = 0;
		row9.add(btn_submitPatient, gbc_btn_submitPatient);
		
	} // initialize()
	
	/* Listeners */
	private static class addPatientListener implements ActionListener { public void actionPerformed(ActionEvent event) {
		
		if (pFirst.getText() == null || pFirst.getText().strip().isEmpty() ) { 
		JOptionPane.showMessageDialog(mainContent,
			    "A required field is empty",
			    "Warning",
			    JOptionPane.WARNING_MESSAGE);
		}else{ 
			PreparedStatement cli = null;
			String sql = "INSERT INTO `ris`.`patient`"
				 + " (`ssn`,`first_name`,`middle_initial`,`last_name`,`birthdate`,`sex`,`home_phone`,`cell_phone`,`work_phone`,`street`,`city`,`state`,`zip`) VALUES"
				 + " (?,?,?,?,?,?,?,?,?,?,?,?,?)";
			int qCount = 1; // Because counting question marks is annoying...

			// Try-Catch for SQLException
			try {
				// Notify the database of our intended statement
				cli = conn.prepareStatement(sql);
				// Load up the ?s in the statement
				cli.setString(qCount++, pSSN.getText());
				cli.setString(qCount++, pFirst.getText());
				cli.setString(qCount++, pMiddle.getText());
				cli.setString(qCount++, pLast.getText());
				cli.setString(qCount++, pBirth.getText());
				cli.setString(qCount++, pSex.getSelectedItem().toString() ); // ComboBoxes work different
				cli.setString(qCount++, pHome.getText());
				cli.setString(qCount++, pCell.getText());
				cli.setString(qCount++, pWork.getText());
				cli.setString(qCount++, pStreet.getText());
				cli.setString(qCount++, pCity.getText());
				cli.setString(qCount++, pState.getSelectedItem().toString() ); // ComboBoxes work different
				cli.setString(qCount++, pZip.getText());
				
				cli.executeUpdate(); // commit insert/update
				
			} catch (SQLException e) { e.printStackTrace();}
			
			if (eCompany.getText() != null && !( eCompany.getText().strip().isEmpty() ) ) { // If employer info filled
				
				cli = null;
				sql = "INSERT INTO `ris`.`employer`"
						+ " (`name`,`street`,`city`,`state`,`zip`) VALUES"
						 + " (?,?,?,?,?)";
				qCount = 1; // Because counting question marks is annoying...
				
				// Try-Catch for SQLException
				try {
					// Notify the database of our intended statement
					cli = conn.prepareStatement(sql);
					// Load up the ?s in the statement
					cli.setString(qCount++, eCompany.getText());
					cli.setString(qCount++, eStreet.getText());
					cli.setString(qCount++, eCity.getText());
					cli.setString(qCount++, eState.getSelectedItem().toString() ); // ComboBoxes work different
					cli.setString(qCount++, eZip.getText());
					
					cli.executeUpdate(); // commit insert/update
					 ResultSet rs = cli.executeQuery("SELECT MAX(id) AS `id` FROM `ris`.`employer`");
					 rs.first();
					int lastid = rs.getInt("id");
					cli.executeUpdate("UPDATE `ris`.`patient` SET `employer_id`="+lastid+" WHERE `employer_id` IS NULL ORDER BY `id` DESC LIMIT 1");
				} catch (SQLException e) { e.printStackTrace();}

			}
			
			if (sName.getText() != null && !( sName.getText().strip().isEmpty() ) ) {
				cli = null;
				sql = "INSERT INTO `ris`.`steward`"
						+ " (`ssn`,`name`,`relation`,`home_phone`,`cell_phone`,`work_phone`,`street`,`city`,`state`,`zip`) VALUES"
						+ " (?,?,?,?,?,?,?,?,?,?)";
				qCount = 1; // Because counting question marks is annoying...
				
				// Try-Catch for SQLException
				try {
					// Notify the database of our intended statement
					cli = conn.prepareStatement(sql);
					// Load up the ?s in the statement
					cli.setString(qCount++, sSSN.getText());
					cli.setString(qCount++, sName.getText());
					cli.setString(qCount++, sRelation.getText());
					cli.setString(qCount++, sHome.getText());
					cli.setString(qCount++, sCell.getText());
					cli.setString(qCount++, sWork.getText());
					cli.setString(qCount++, sStreet.getText());
					cli.setString(qCount++, sCity.getText());
					cli.setString(qCount++, sState.getSelectedItem().toString() ); // ComboBoxes work different
					cli.setString(qCount++, sZip.getText());

					cli.executeUpdate(); // commit insert/update
					ResultSet rs = cli.executeQuery("SELECT MAX(id) AS `id` FROM `ris`.`steward`");
					 rs.first();
					int lastid = rs.getInt("id");
					cli.executeUpdate("UPDATE `ris`.`patient` SET `steward_id`="+lastid+" WHERE `steward_id` IS NULL ORDER BY `id` DESC LIMIT 1");
					
				} catch (SQLException e) { e.printStackTrace();}
			}
			
		}
		
	}/*actionPerformed()*/}//addpatientListener
	
	private static class viewPatientListener implements ActionListener { public void actionPerformed(ActionEvent event) {
		
		if (search.getText() == null || search.getText().strip().isEmpty() ) { 
		JOptionPane.showMessageDialog(mainContent,
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
					JOptionPane.showMessageDialog(mainContent,
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
					String steward_id = results.getString("steward_id");
					
					if(employer_id!=null && !employer_id.isEmpty()) {
						sql="SELECT * FROM `ris`.`employer` WHERE `id`="+employer_id;
						results = sentry.executeQuery(sql); results.first();
						eCompany.setText( results.getString("name") );
						eStreet.setText( results.getString("street") );
						eCity.setText( results.getString("city") );
						eState.setSelectedItem( results.getString("state") );
						eZip.setText( results.getString("zip") );
					}

					if(steward_id!=null && !steward_id.isEmpty()) {
						isDependent.doClick();
						sql="SELECT * FROM `ris`.`steward` WHERE `id`="+steward_id;
						results = sentry.executeQuery(sql); results.first();
						sName.setText( results.getString("name") );
						sSSN.setText( results.getString("ssn") );
						sRelation.setText( results.getString("relation") );
						sHome.setText( results.getString("home_phone") );
						sCell.setText( results.getString("cell_phone") );
						sWork.setText( results.getString("work_phone") );
						sStreet.setText( results.getString("street") );
						sCity.setText( results.getString("city") );
						sState.setSelectedItem( results.getString("state") );
						sZip.setText( results.getString("zip") );
					}

				}
			} catch (SQLException e) { e.printStackTrace();}
		}
		
	}/*actionPerformed()*/}//viewPatientListener
}
