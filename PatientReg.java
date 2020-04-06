import java.awt.EventQueue;
import java.awt.Font;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLayeredPane;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;



import javax.swing.JList;
import javax.swing.JTabbedPane;

public class PatientReg
{

	private JFrame frame;
	private JTextField firstNameInput;
	private JTextField middleInitialInput;
	private JLabel lblLastName;
	private JTextField lastNameInput;
	private JLabel lblSex;
	private JTextField birthMonthInput;
	private JTextField birthYearInput;
	private JTextField birthDayInput;
	private JLabel lblSlashDelimiter;
	private JLabel lblSlashDelimiter1;
	private JLabel lblStreetAddress;
	private JTextField cityInput;
	private JLabel lblCity;
	private JTextField addressInput;
	private JLabel lblZipCode;
	private JTextField zipCodeInput;
	private JLabel lblState;
	private JComboBox REG_stateDropdown;
	private JLabel lblEmployerName;
	private JTextField employerNameInput;
	private JLabel lblWorkPhone;
	private JTextField workPhoneNumberInput;
	private JTextField homePhoneNumberInput;
	private JLabel lblSSN;
	private JTextField socSecNumInput;
	private JLabel lblResponsibleName;
	private JTextField responsibleNameInput;
	private JLabel lblResponsibleRelationship;
	private JTextField responsibleRelationshipInput;
	private JLabel lblAddressIfDifferent;
	private JTextField responsibleAddressInput;
	private JTextField responsibleCityInput;
	private JTextField responsibleZipCodeInput;
	private JTextField responsibleWorkNumberInput;
	private JTextField responsibleHomeNumberInput;
	private JTextField responsibleSSNInput;
	private JLabel lblEmployerAddress;
	private JTextField employerAddressInput;
	private JLabel lblEmployerCity;
	private JTextField employerCityInput;
	private JLabel lblEmployerState;
	private JComboBox REG_employerStateDropdown;
	private JLabel lblEmployerZipCode;
	private JTextField employerZipInput;
	private JLabel lblDateOfBirth;
	private JPanel referringPhysicianMainMenu;
	private JPanel confirmCancelPanel;
	private JButton REG_btnNo;
	private JLabel lblPleaseConfirm;
	private JTextPane txtpnWelcomeBack;
	private JTextPane txtpnDoctor;
	private JButton MAIN_btnRegisterNewPatient;
	private JButton MAIN_btnEditPatientInfo;
	private JButton MAIN_btnSendImagingRequest;
	private JLabel lblPatient;
	private JLabel lblCondition;
	private JComboBox IMG_conditionDropdown;
	private JPanel editPatientInfoPanel;
	private JLabel lblModality;
	private JComboBox IMG_modalityDropdown;
	private JButton IMG_btnYes2;
	private JButton IMG_btnNo2;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		//getPatientInfo(672);
		EventQueue.invokeLater(new Runnable() {
			public void run() 
			{
				getPatientInfo("321");
				try {
					PatientReg window = new PatientReg();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PatientReg() 
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 840, 460);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 826, 423);
		frame.getContentPane().add(layeredPane);
		
		
		JPanel registerPatientPanel = new JPanel();
		registerPatientPanel.setBounds(0, 0, 821, 396);
		layeredPane.add(registerPatientPanel);
		layeredPane.setLayer(registerPatientPanel, 0);
		registerPatientPanel.setLayout(null);
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setBounds(10, 10, 62, 15);
		registerPatientPanel.add(lblFirstName);
		lblFirstName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		firstNameInput = new JTextField();
		firstNameInput.setBounds(75, 10, 96, 19);
		registerPatientPanel.add(firstNameInput);
		firstNameInput.setColumns(10);
		
		JLabel lblMiddleInitial = new JLabel("Middle Initial:");
		lblMiddleInitial.setBounds(179, 10, 77, 15);
		registerPatientPanel.add(lblMiddleInitial);
		lblMiddleInitial.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		middleInitialInput = new JTextField();
		middleInitialInput.setBounds(259, 9, 23, 19);
		registerPatientPanel.add(middleInitialInput);
		middleInitialInput.setColumns(10);
		
		lblLastName = new JLabel("Last Name:");
		lblLastName.setBounds(292, 11, 65, 15);
		registerPatientPanel.add(lblLastName);
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		lastNameInput = new JTextField();
		lastNameInput.setBounds(356, 10, 96, 19);
		registerPatientPanel.add(lastNameInput);
		lastNameInput.setColumns(10);
		
		lblSex = new JLabel("Sex:");
		lblSex.setBounds(648, 11, 22, 15);
		registerPatientPanel.add(lblSex);
		lblSex.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JComboBox REG_sexDropdown = new JComboBox();
		REG_sexDropdown.setBounds(680, 11, 85, 19);
		registerPatientPanel.add(REG_sexDropdown);
		REG_sexDropdown.addItem("SELECT");
		REG_sexDropdown.addItem("Male");
		REG_sexDropdown.addItem("Female");
		
		lblDateOfBirth = new JLabel("Date of Birth:");
		lblDateOfBirth.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDateOfBirth.setBounds(462, 11, 71, 15);
		registerPatientPanel.add(lblDateOfBirth);
		
		birthMonthInput = new JTextField();
		birthMonthInput.setBounds(543, 11, 22, 19);
		registerPatientPanel.add(birthMonthInput);
		birthMonthInput.setColumns(10);
		
		lblSlashDelimiter = new JLabel("/");
		lblSlashDelimiter.setBounds(569, 12, 5, 15);
		registerPatientPanel.add(lblSlashDelimiter);
		lblSlashDelimiter.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		birthDayInput = new JTextField();
		birthDayInput.setBounds(578, 11, 22, 19);
		registerPatientPanel.add(birthDayInput);
		birthDayInput.setColumns(10);
		
		lblSlashDelimiter1 = new JLabel("/");
		lblSlashDelimiter1.setBounds(603, 12, 5, 15);
		registerPatientPanel.add(lblSlashDelimiter1);
		lblSlashDelimiter1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		birthYearInput = new JTextField();
		birthYearInput.setBounds(610, 10, 35, 19);
		registerPatientPanel.add(birthYearInput);
		birthYearInput.setColumns(10);
		
		lblStreetAddress = new JLabel("Address:");
		lblStreetAddress.setBounds(10, 35, 46, 15);
		registerPatientPanel.add(lblStreetAddress);
		lblStreetAddress.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		addressInput = new JTextField();
		addressInput.setBounds(64, 35, 140, 19);
		registerPatientPanel.add(addressInput);
		addressInput.setColumns(10);
		
		lblCity = new JLabel("City:");
		lblCity.setBounds(214, 36, 23, 15);
		registerPatientPanel.add(lblCity);
		lblCity.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		cityInput = new JTextField();
		cityInput.setBounds(243, 36, 96, 19);
		registerPatientPanel.add(cityInput);
		cityInput.setColumns(10);
		
		lblState = new JLabel("State:");
		lblState.setBounds(349, 36, 31, 15);
		registerPatientPanel.add(lblState);
		lblState.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		REG_stateDropdown = new JComboBox();
		REG_stateDropdown.setBounds(391, 35, 99, 19);
		registerPatientPanel.add(REG_stateDropdown);
		REG_stateDropdown.addItem("SELECT");
		REG_stateDropdown.addItem("Alabama");
		REG_stateDropdown.addItem("Alaska");
		REG_stateDropdown.addItem("Arkansas");
		REG_stateDropdown.addItem("California");
		REG_stateDropdown.addItem("Colorado");
		REG_stateDropdown.addItem("Connecticut");
		REG_stateDropdown.addItem("Delaware");
		REG_stateDropdown.addItem("Florida");
		REG_stateDropdown.addItem("Georgia");
		REG_stateDropdown.addItem("Hawaii");
		REG_stateDropdown.addItem("Idaho");
		REG_stateDropdown.addItem("Illinois");
		REG_stateDropdown.addItem("Indiana");
		REG_stateDropdown.addItem("Iowa");
		REG_stateDropdown.addItem("Kansas");
		REG_stateDropdown.addItem("Kentucky");
		REG_stateDropdown.addItem("Louisiana");
		REG_stateDropdown.addItem("Maine");
		REG_stateDropdown.addItem("Maryland");
		REG_stateDropdown.addItem("Massachusetts");
		REG_stateDropdown.addItem("Michigan");
		REG_stateDropdown.addItem("Minnesota");
		REG_stateDropdown.addItem("Mississippi");
		REG_stateDropdown.addItem("Missouri");
		REG_stateDropdown.addItem("Montana");
		REG_stateDropdown.addItem("Nebraska");
		REG_stateDropdown.addItem("Nevada");
		REG_stateDropdown.addItem("New Hampshire");
		REG_stateDropdown.addItem("New Jersey");
		REG_stateDropdown.addItem("New Mexico");
		REG_stateDropdown.addItem("New York");
		REG_stateDropdown.addItem("North Carolina");
		REG_stateDropdown.addItem("North Dakota");
		REG_stateDropdown.addItem("Ohio");
		REG_stateDropdown.addItem("Oklahoma");
		REG_stateDropdown.addItem("Oregon");
		REG_stateDropdown.addItem("Pennsylvania");
		REG_stateDropdown.addItem("Rhode Island");
		REG_stateDropdown.addItem("South Carolina");
		REG_stateDropdown.addItem("South Dakota");
		REG_stateDropdown.addItem("Tennessee");
		REG_stateDropdown.addItem("Texas");
		REG_stateDropdown.addItem("Utah");
		REG_stateDropdown.addItem("Vermont");
		REG_stateDropdown.addItem("Virginia");
		REG_stateDropdown.addItem("Washington");
		REG_stateDropdown.addItem("West Virginia");
		REG_stateDropdown.addItem("Wisconsin");
		REG_stateDropdown.addItem("Wyoming");
		
		lblZipCode = new JLabel("Zip:");
		lblZipCode.setBounds(497, 36, 19, 15);
		registerPatientPanel.add(lblZipCode);
		lblZipCode.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		zipCodeInput = new JTextField();
		zipCodeInput.setBounds(526, 35, 46, 19);
		registerPatientPanel.add(zipCodeInput);
		zipCodeInput.setColumns(10);
		
		lblWorkPhone = new JLabel("Work Phone Number:");
		lblWorkPhone.setBounds(582, 36, 119, 15);
		registerPatientPanel.add(lblWorkPhone);
		lblWorkPhone.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		workPhoneNumberInput = new JTextField();
		workPhoneNumberInput.setBounds(711, 35, 77, 19);
		registerPatientPanel.add(workPhoneNumberInput);
		workPhoneNumberInput.setColumns(10);
		
		JLabel lblHomePhone = new JLabel("Home Phone Number:");
		lblHomePhone.setBounds(10, 66, 117, 15);
		registerPatientPanel.add(lblHomePhone);
		lblHomePhone.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		homePhoneNumberInput = new JTextField();
		homePhoneNumberInput.setBounds(138, 66, 77, 19);
		registerPatientPanel.add(homePhoneNumberInput);
		homePhoneNumberInput.setColumns(10);
		
		lblSSN = new JLabel("Social Security Number:");
		lblSSN.setBounds(224, 69, 129, 15);
		registerPatientPanel.add(lblSSN);
		lblSSN.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		socSecNumInput = new JTextField();
		socSecNumInput.setBounds(359, 67, 96, 19);
		registerPatientPanel.add(socSecNumInput);
		socSecNumInput.setColumns(10);
		
		lblEmployerName = new JLabel("Name of Employer:");
		lblEmployerName.setBounds(469, 69, 102, 15);
		registerPatientPanel.add(lblEmployerName);
		lblEmployerName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		employerNameInput = new JTextField();
		employerNameInput.setBounds(581, 69, 96, 19);
		registerPatientPanel.add(employerNameInput);
		employerNameInput.setColumns(10);
		
		lblEmployerAddress = new JLabel("Street Address of Workplace:");
		lblEmployerAddress.setBounds(10, 91, 160, 24);
		registerPatientPanel.add(lblEmployerAddress);
		lblEmployerAddress.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		employerAddressInput = new JTextField();
		employerAddressInput.setBounds(172, 96, 119, 19);
		registerPatientPanel.add(employerAddressInput);
		employerAddressInput.setColumns(10);
		
		lblEmployerCity = new JLabel("City:");
		lblEmployerCity.setBounds(300, 95, 31, 19);
		registerPatientPanel.add(lblEmployerCity);
		lblEmployerCity.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		employerCityInput = new JTextField();
		employerCityInput.setBounds(330, 95, 96, 19);
		registerPatientPanel.add(employerCityInput);
		employerCityInput.setColumns(10);
		
		lblEmployerState = new JLabel("State:");
		lblEmployerState.setBounds(437, 98, 31, 15);
		registerPatientPanel.add(lblEmployerState);
		lblEmployerState.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		REG_employerStateDropdown = new JComboBox();
		REG_employerStateDropdown.setBounds(476, 97, 99, 19);
		registerPatientPanel.add(REG_employerStateDropdown);
		REG_employerStateDropdown.addItem("SELECT");
		REG_employerStateDropdown.addItem("Alabama");
		REG_employerStateDropdown.addItem("Alaska");
		REG_employerStateDropdown.addItem("Arkansas");
		REG_employerStateDropdown.addItem("California");
		REG_employerStateDropdown.addItem("Colorado");
		REG_employerStateDropdown.addItem("Connecticut");
		REG_employerStateDropdown.addItem("Delaware");
		REG_employerStateDropdown.addItem("Florida");
		REG_employerStateDropdown.addItem("Georgia");
		REG_employerStateDropdown.addItem("Hawaii");
		REG_employerStateDropdown.addItem("Idaho");
		REG_employerStateDropdown.addItem("Illinois");
		REG_employerStateDropdown.addItem("Indiana");
		REG_employerStateDropdown.addItem("Iowa");
		REG_employerStateDropdown.addItem("Kansas");
		REG_employerStateDropdown.addItem("Kentucky");
		REG_employerStateDropdown.addItem("Louisiana");
		REG_employerStateDropdown.addItem("Maine");
		REG_employerStateDropdown.addItem("Maryland");
		REG_employerStateDropdown.addItem("Massachusetts");
		REG_employerStateDropdown.addItem("Michigan");
		REG_employerStateDropdown.addItem("Minnesota");
		REG_employerStateDropdown.addItem("Mississippi");
		REG_employerStateDropdown.addItem("Missouri");
		REG_employerStateDropdown.addItem("Montana");
		REG_employerStateDropdown.addItem("Nebraska");
		REG_employerStateDropdown.addItem("Nevada");
		REG_employerStateDropdown.addItem("New Hampshire");
		REG_employerStateDropdown.addItem("New Jersey");
		REG_employerStateDropdown.addItem("New Mexico");
		REG_employerStateDropdown.addItem("New York");
		REG_employerStateDropdown.addItem("North Carolina");
		REG_employerStateDropdown.addItem("North Dakota");
		REG_employerStateDropdown.addItem("Ohio");
		REG_employerStateDropdown.addItem("Oklahoma");
		REG_employerStateDropdown.addItem("Oregon");
		REG_employerStateDropdown.addItem("Pennsylvania");
		REG_employerStateDropdown.addItem("Rhode Island");
		REG_employerStateDropdown.addItem("South Carolina");
		REG_employerStateDropdown.addItem("South Dakota");
		REG_employerStateDropdown.addItem("Tennessee");
		REG_employerStateDropdown.addItem("Texas");
		REG_employerStateDropdown.addItem("Utah");
		REG_employerStateDropdown.addItem("Vermont");
		REG_employerStateDropdown.addItem("Virginia");
		REG_employerStateDropdown.addItem("Washington");
		REG_employerStateDropdown.addItem("West Virginia");
		REG_employerStateDropdown.addItem("Wisconsin");
		REG_employerStateDropdown.addItem("Wyoming");	
		lblEmployerZipCode = new JLabel("Zip:");
		lblEmployerZipCode.setBounds(582, 99, 19, 15);
		registerPatientPanel.add(lblEmployerZipCode);
		lblEmployerZipCode.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		employerZipInput = new JTextField();
		employerZipInput.setBounds(612, 98, 96, 19);
		registerPatientPanel.add(employerZipInput);
		employerZipInput.setColumns(10);
		
		lblResponsibleName = new JLabel("Person Responsible for Balance:");
		lblResponsibleName.setBounds(10, 125, 178, 15);
		registerPatientPanel.add(lblResponsibleName);
		lblResponsibleName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		responsibleNameInput = new JTextField();
		responsibleNameInput.setBounds(189, 125, 96, 19);
		registerPatientPanel.add(responsibleNameInput);
		responsibleNameInput.setColumns(10);
		
		JPanel responsiblePanel = new JPanel();
		responsiblePanel.setBounds(10, 151, 740, 104);
		registerPatientPanel.add(responsiblePanel);
		responsiblePanel.setLayout(null);
		responsiblePanel.setVisible(false);
		
		JCheckBox REG_chckbxDifferentThanPatient = new JCheckBox("Different than Patient?");
		REG_chckbxDifferentThanPatient.setBounds(294, 123, 148, 21);
		registerPatientPanel.add(REG_chckbxDifferentThanPatient);
		REG_chckbxDifferentThanPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent checkboxClicked) 
			{
				if (REG_chckbxDifferentThanPatient.isSelected())
				{
					responsiblePanel.setVisible(true);
				}
				if (REG_chckbxDifferentThanPatient.isSelected()==false)
				{
					responsiblePanel.setVisible(false);
				}
			}
		});
		REG_chckbxDifferentThanPatient.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		
		
		lblResponsibleRelationship = new JLabel("Relationship of Individual:");
		lblResponsibleRelationship.setBounds(10, 11, 142, 15);
		responsiblePanel.add(lblResponsibleRelationship);
		lblResponsibleRelationship.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		responsibleRelationshipInput = new JTextField();
		responsibleRelationshipInput.setBounds(162, 10, 96, 19);
		responsiblePanel.add(responsibleRelationshipInput);
		responsibleRelationshipInput.setColumns(10);
		
		lblAddressIfDifferent = new JLabel("Address of Individual:");
		lblAddressIfDifferent.setBounds(10, 42, 124, 15);
		responsiblePanel.add(lblAddressIfDifferent);
		lblAddressIfDifferent.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		responsibleAddressInput = new JTextField();
		responsibleAddressInput.setBounds(139, 41, 96, 19);
		responsiblePanel.add(responsibleAddressInput);
		responsibleAddressInput.setColumns(10);
		
		JLabel lblResponsibleCity = new JLabel("City:");
		lblResponsibleCity.setBounds(246, 42, 23, 15);
		responsiblePanel.add(lblResponsibleCity);
		lblResponsibleCity.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		responsibleCityInput = new JTextField();
		responsibleCityInput.setBounds(279, 41, 96, 19);
		responsiblePanel.add(responsibleCityInput);
		responsibleCityInput.setColumns(10);
		
		JLabel lblResponsibleState = new JLabel("State:");
		lblResponsibleState.setBounds(382, 42, 31, 15);
		responsiblePanel.add(lblResponsibleState);
		lblResponsibleState.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JComboBox REG_responsibleStateDropdown = new JComboBox();
		REG_responsibleStateDropdown.setBounds(418, 41, 99, 19);
		responsiblePanel.add(REG_responsibleStateDropdown);
		REG_responsibleStateDropdown.addItem("SELECT");
		REG_responsibleStateDropdown.addItem("Alabama");
		REG_responsibleStateDropdown.addItem("Alaska");
		REG_responsibleStateDropdown.addItem("Arkansas");
		REG_responsibleStateDropdown.addItem("California");
		REG_responsibleStateDropdown.addItem("Colorado");
		REG_responsibleStateDropdown.addItem("Connecticut");
		REG_responsibleStateDropdown.addItem("Delaware");
		REG_responsibleStateDropdown.addItem("Florida");
		REG_responsibleStateDropdown.addItem("Georgia");
		REG_responsibleStateDropdown.addItem("Hawaii");
		REG_responsibleStateDropdown.addItem("Idaho");
		REG_responsibleStateDropdown.addItem("Illinois");
		REG_responsibleStateDropdown.addItem("Indiana");
		REG_responsibleStateDropdown.addItem("Iowa");
		REG_responsibleStateDropdown.addItem("Kansas");
		REG_responsibleStateDropdown.addItem("Kentucky");
		REG_responsibleStateDropdown.addItem("Louisiana");
		REG_responsibleStateDropdown.addItem("Maine");
		REG_responsibleStateDropdown.addItem("Maryland");
		REG_responsibleStateDropdown.addItem("Massachusetts");
		REG_responsibleStateDropdown.addItem("Michigan");
		REG_responsibleStateDropdown.addItem("Minnesota");
		REG_responsibleStateDropdown.addItem("Mississippi");
		REG_responsibleStateDropdown.addItem("Missouri");
		REG_responsibleStateDropdown.addItem("Montana");
		REG_responsibleStateDropdown.addItem("Nebraska");
		REG_responsibleStateDropdown.addItem("Nevada");
		REG_responsibleStateDropdown.addItem("New Hampshire");
		REG_responsibleStateDropdown.addItem("New Jersey");
		REG_responsibleStateDropdown.addItem("New Mexico");
		REG_responsibleStateDropdown.addItem("New York");
		REG_responsibleStateDropdown.addItem("North Carolina");
		REG_responsibleStateDropdown.addItem("North Dakota");
		REG_responsibleStateDropdown.addItem("Ohio");
		REG_responsibleStateDropdown.addItem("Oklahoma");
		REG_responsibleStateDropdown.addItem("Oregon");
		REG_responsibleStateDropdown.addItem("Pennsylvania");
		REG_responsibleStateDropdown.addItem("Rhode Island");
		REG_responsibleStateDropdown.addItem("South Carolina");
		REG_responsibleStateDropdown.addItem("South Dakota");
		REG_responsibleStateDropdown.addItem("Tennessee");
		REG_responsibleStateDropdown.addItem("Texas");
		REG_responsibleStateDropdown.addItem("Utah");
		REG_responsibleStateDropdown.addItem("Vermont");
		REG_responsibleStateDropdown.addItem("Virginia");
		REG_responsibleStateDropdown.addItem("Washington");
		REG_responsibleStateDropdown.addItem("West Virginia");
		REG_responsibleStateDropdown.addItem("Wisconsin");
		REG_responsibleStateDropdown.addItem("Wyoming");
		
		JLabel lblResponsibleZipCode = new JLabel("Zip:");
		lblResponsibleZipCode.setBounds(527, 43, 19, 15);
		responsiblePanel.add(lblResponsibleZipCode);
		lblResponsibleZipCode.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		responsibleZipCodeInput = new JTextField();
		responsibleZipCodeInput.setBounds(551, 41, 96, 19);
		responsiblePanel.add(responsibleZipCodeInput);
		responsibleZipCodeInput.setColumns(10);
		
		JLabel lblResponsibleWorkPhone = new JLabel("Work Phone Number:");
		lblResponsibleWorkPhone.setBounds(10, 76, 117, 15);
		responsiblePanel.add(lblResponsibleWorkPhone);
		lblResponsibleWorkPhone.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		responsibleWorkNumberInput = new JTextField();
		responsibleWorkNumberInput.setBounds(137, 74, 96, 19);
		responsiblePanel.add(responsibleWorkNumberInput);
		responsibleWorkNumberInput.setColumns(10);
		
		JLabel lblResponsibleHomePhone = new JLabel("Home Phone Number:");
		lblResponsibleHomePhone.setBounds(243, 74, 124, 15);
		responsiblePanel.add(lblResponsibleHomePhone);
		lblResponsibleHomePhone.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		responsibleHomeNumberInput = new JTextField();
		responsibleHomeNumberInput.setBounds(366, 73, 96, 19);
		responsiblePanel.add(responsibleHomeNumberInput);
		responsibleHomeNumberInput.setColumns(10);
		
		JLabel lblResponsibleSSN = new JLabel("Social Security Number:");
		lblResponsibleSSN.setBounds(472, 76, 132, 15);
		responsiblePanel.add(lblResponsibleSSN);
		lblResponsibleSSN.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		responsibleSSNInput = new JTextField();
		responsibleSSNInput.setBounds(614, 75, 96, 19);
		responsiblePanel.add(responsibleSSNInput);
		responsibleSSNInput.setColumns(10);
		
		JButton REG_btnRegisterPatient = new JButton("Register Patient");
		REG_btnRegisterPatient.setBounds(599, 272, 140, 21);
		registerPatientPanel.add(REG_btnRegisterPatient);
		REG_btnRegisterPatient.setVisible(false);
		REG_btnRegisterPatient.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent buttonPressed) 
			{
				Connection conn=null;

				try {
					conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", ""); //This part here created the connection to MySQL
					if(conn != null) 
					System.out.println("Connection Established.");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("Connection Failure.");
					e.printStackTrace();
				}
				
				//if the connection fails, then the information will not be pushed to MySQL
					if(conn != null) {
				try {
					String firstName = firstNameInput.getText();
					String middleInitial = middleInitialInput.getText();
					String lastName = lastNameInput.getText();
					String birthMonth = birthMonthInput.getText();
					String birthYear = birthYearInput.getText();
					String birthDay = birthDayInput.getText();
					String State = (String)REG_stateDropdown.getSelectedItem();
					String City = cityInput.getText();
					String Gender = (String) REG_sexDropdown.getSelectedItem();
					String address = addressInput.getText();
					String zipCode = zipCodeInput.getText();
					String employerName = employerNameInput.getText();
					String workPhoneNumber = workPhoneNumberInput.getText();
					String homePhoneNumber = homePhoneNumberInput.getText();
					String socSecNum = socSecNumInput.getText();
					String employerAddress = employerAddressInput.getText();
					String employerCity = employerCityInput.getText();
					String employerZip = employerZipInput.getText();
					
					String query = "INSERT INTO hospitaliris.patient_information (First_Name, Middle_initial, Last_Name, Gender, birthMonth, birthYear,birthDay, State, City,address,zipcode,employerName,workPhoneNumber,homePhoneNumber,socSecNum,Patient_ID,employerAddress, employerCity, employerZip) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
					PreparedStatement stmt = conn.prepareStatement(query);
				
					stmt.setString( 1, firstName);
					stmt.setString( 2, middleInitial);
					stmt.setString( 3, lastName);
					stmt.setString(4, Gender);
					stmt.setString( 5, birthMonth);
					stmt.setString( 6, birthYear);
					stmt.setString(7, birthDay);
					stmt.setString(8, State);
					stmt.setString( 9, City);
					stmt.setString( 10, address);
					stmt.setString( 11, zipCode);
					stmt.setString( 12, employerName);
					stmt.setString( 13, workPhoneNumber);
					stmt.setString( 14, homePhoneNumber);
					stmt.setString(15, socSecNum);
					stmt.setString(16,"0001");
					stmt.setString(16, employerAddress);
					stmt.setString(17, employerCity);
					stmt.setString(18, employerZip);
					stmt.execute();
					System.out.println("Patient information successfully saved!");
				} catch(SQLException e) {
					System.out.println("Something is wrong with the patient data");
					e.printStackTrace();
				}
				
				//this should only push the responsible user information if the checkbox is selected.
				//done to prevent errors on eclipse side.
				if (REG_chckbxDifferentThanPatient.isSelected()) {
				try {
					String ResponsibleName = responsibleNameInput.getText();
					String responsibleRelationship = responsibleRelationshipInput.getText();
					String responsibleAddress = responsibleAddressInput.getText();
					String responsibleState =(String)REG_responsibleStateDropdown.getSelectedItem();
					String responsibleCity = responsibleCityInput.getText();
					String responsibleZipCode = responsibleZipCodeInput.getText();
					String responsibleWorkNumber = responsibleWorkNumberInput.getText();
					String responsibleHomeNumber = responsibleHomeNumberInput.getText();
					String responsibleSSN = responsibleSSNInput.getText();
					String socSecNum = socSecNumInput.getText();
					
					String query2 = "INSERT INTO hospitaliris.responsible_information (ResponsibleName, responsibleRelationship, responsibleAddressInput,responsibleState,responsibleCity,responsibleZipCode,responsibleWorkNumber,responsibleHomeNumber,responsibleSSN, dependentSSN) VALUES (?,?,?,?,?,?,?,?,?,?)";
					PreparedStatement stmt2 = conn.prepareStatement(query2);
					stmt2.setString( 1, ResponsibleName);
					stmt2.setString( 2, responsibleRelationship);
					stmt2.setString( 3, responsibleAddress);
					stmt2.setString(4, responsibleState);
					stmt2.setString( 5, responsibleCity);
					stmt2.setString( 6, responsibleZipCode);
					stmt2.setString( 7, responsibleWorkNumber);
					stmt2.setString( 8, responsibleHomeNumber);
					stmt2.setString( 9, responsibleSSN);
					stmt2.setString( 10, socSecNum);
					stmt2.execute();
					System.out.println("responsible information succesfully saved!");
				}catch(SQLException e) {
					System.out.println("Something is wrong with the responsible figure data");
					e.printStackTrace();
				}
				
				}
					}
			}
		});
		
		
		JCheckBox REG_chckbxConfirmWithPatient = new JCheckBox("CONFIRM WITH PATIENT");
		REG_chckbxConfirmWithPatient.setBounds(425, 270, 170, 21);
		registerPatientPanel.add(REG_chckbxConfirmWithPatient);
		REG_chckbxConfirmWithPatient.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		REG_chckbxConfirmWithPatient.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent confirmed) 
			{
				if (REG_chckbxConfirmWithPatient.isSelected())
				{
					REG_btnRegisterPatient.setVisible(true);
					REG_btnRegisterPatient.setEnabled(true);
				}
				else
				{
					REG_btnRegisterPatient.setVisible(false);
					REG_btnRegisterPatient.setEnabled(false);
				}
			}
		});
		
		confirmCancelPanel = new JPanel();
		confirmCancelPanel.setBounds(106, 258, 249, 35);
		registerPatientPanel.add(confirmCancelPanel);
		confirmCancelPanel.setLayout(null);
		confirmCancelPanel.setVisible(false);
		
		JButton REG_btnYes = new JButton("YES");
		REG_btnYes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent confirmedCancelSequence) 
			{
				layeredPane.setLayer(registerPatientPanel, 0);
				layeredPane.setLayer(referringPhysicianMainMenu, 1);
				firstNameInput.setText(null);
				middleInitialInput.setText(null);
				lastNameInput.setText(null);
				birthMonthInput.setText(null);
				birthDayInput.setText(null);
				birthYearInput.setText(null);
				REG_sexDropdown.setSelectedIndex(0);
				addressInput.setText(null);
				cityInput.setText(null);
				REG_stateDropdown.setSelectedIndex(0);
				zipCodeInput.setText(null);
				workPhoneNumberInput.setText(null);
				homePhoneNumberInput.setText(null);
				socSecNumInput.setText(null);
				employerNameInput.setText(null);
				employerAddressInput.setText(null);
				employerCityInput.setText(null);
				REG_employerStateDropdown.setSelectedIndex(0);
				employerZipInput.setText(null);
				responsibleNameInput.setText(null);
				responsibleRelationshipInput.setText(null);
				responsibleAddressInput.setText(null);
				responsibleCityInput.setText(null);
				REG_responsibleStateDropdown.setSelectedIndex(0);
				responsibleZipCodeInput.setText(null);
				responsibleWorkNumberInput.setText(null);
				responsibleHomeNumberInput.setText(null);
				responsibleSSNInput.setText(null);
				confirmCancelPanel.setVisible(false);
				REG_chckbxConfirmWithPatient.setSelected(false);
				REG_chckbxDifferentThanPatient.setSelected(false);
				REG_btnRegisterPatient.setVisible(false);
				REG_btnRegisterPatient.setEnabled(false);
//Disable all the interactables in this page before the user can touch them.
				REG_btnRegisterPatient.setEnabled(false);
				REG_sexDropdown.setEnabled(false);
				REG_chckbxConfirmWithPatient.setEnabled(false);
				REG_stateDropdown.setEnabled(false);
				REG_chckbxDifferentThanPatient.setEnabled(false);
				REG_employerStateDropdown.setEnabled(false);
				REG_btnNo.setEnabled(false);
				REG_responsibleStateDropdown.setEnabled(false);
				REG_btnYes.setEnabled(false);
//Re-enable all the interactables in the Main Menu.
				MAIN_btnEditPatientInfo.setEnabled(true);
				MAIN_btnRegisterNewPatient.setEnabled(true);
				MAIN_btnSendImagingRequest.setEnabled(true);
			}
		});
		REG_btnYes.setBounds(105, 9, 59, 21);
		confirmCancelPanel.add(REG_btnYes);
		
		REG_btnNo = new JButton("NO");
		REG_btnNo.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent REG_cancelledCancelSequence) 
			{
				confirmCancelPanel.setVisible(false);
				REG_btnYes.setEnabled(false);
				REG_btnNo.setEnabled(false);
			}
		});
		REG_btnNo.setBounds(174, 9, 65, 21);
		confirmCancelPanel.add(REG_btnNo);
		
		lblPleaseConfirm = new JLabel("Please Confirm:");
		lblPleaseConfirm.setForeground(Color.RED);
		lblPleaseConfirm.setBounds(10, 10, 95, 17);
		confirmCancelPanel.add(lblPleaseConfirm);
		lblPleaseConfirm.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JButton REG_btnCancel = new JButton("Cancel");
		REG_btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent cancelButtonClicked) 
			{
				confirmCancelPanel.setVisible(true);
				REG_btnYes.setEnabled(true);
				REG_btnNo.setEnabled(true);
			}
		});
		REG_btnCancel.setBounds(10, 265, 85, 21);
		registerPatientPanel.add(REG_btnCancel);
		
		JPanel sendImagingRequest = new JPanel();
		layeredPane.setLayer(sendImagingRequest, -1);
		sendImagingRequest.setBounds(0, 0, 826, 427);
		layeredPane.add(sendImagingRequest);
		sendImagingRequest.setLayout(null);
		
		lblPatient = new JLabel("Patient:");
		lblPatient.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPatient.setBounds(10, 10, 62, 15);
		sendImagingRequest.add(lblPatient);
		
			
		
		JLabel lblHypothesisNotes = new JLabel("Hypothesis Notes:");
		lblHypothesisNotes.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblHypothesisNotes.setBounds(10, 54, 95, 15);
		sendImagingRequest.add(lblHypothesisNotes);
		
		JTextArea hypothesisNotesInput = new JTextArea();
		hypothesisNotesInput.setBounds(113, 47, 703, 92);
		sendImagingRequest.add(hypothesisNotesInput);
		
		JTextArea IMG_patientText = new JTextArea();
		IMG_patientText.setFont(new Font("Tahoma", Font.PLAIN, 12));
		IMG_patientText.setToolTipText("");
		IMG_patientText.setBounds(58, 7, 195, 21);
		sendImagingRequest.add(IMG_patientText);
			
		
		
		lblCondition = new JLabel("Condition:");
		lblCondition.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCondition.setBounds(271, 10, 62, 15);
		sendImagingRequest.add(lblCondition);
		
		IMG_conditionDropdown = new JComboBox();
		IMG_conditionDropdown.setToolTipText("");
		IMG_conditionDropdown.setFont(new Font("Tahoma", Font.PLAIN, 12));
		IMG_conditionDropdown.setBounds(334, 7, 158, 21);
		sendImagingRequest.add(IMG_conditionDropdown);
		IMG_conditionDropdown.addItem("SELECT");
		IMG_conditionDropdown.addItem("Fracture");
		IMG_conditionDropdown.addItem("Hernia");
		IMG_conditionDropdown.addItem("Embolism");
		IMG_conditionDropdown.addItem("Cancer Screening");
		IMG_conditionDropdown.addItem("arterial/vascular obstruction"); 
		
		JLabel lblObservations = new JLabel("Observations:");
		lblObservations.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblObservations.setBounds(10, 154, 77, 15);
		sendImagingRequest.add(lblObservations);
		
		JTextArea observationsInput = new JTextArea();
		observationsInput.setBounds(97, 150, 719, 92);
		sendImagingRequest.add(observationsInput);
		
		JLabel lblAreaOfBody = new JLabel("Area of Body:");
		lblAreaOfBody.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAreaOfBody.setBounds(510, 10, 77, 15);
		sendImagingRequest.add(lblAreaOfBody);
		
		JComboBox IMG_bodyAreaDropdown = new JComboBox();
		IMG_bodyAreaDropdown.setToolTipText("");
		IMG_bodyAreaDropdown.setFont(new Font("Tahoma", Font.PLAIN, 12));
		IMG_bodyAreaDropdown.setBounds(597, 7, 127, 21);
		sendImagingRequest.add(IMG_bodyAreaDropdown);
		IMG_bodyAreaDropdown.addItem("SELECT");
		
		lblModality = new JLabel("Modality:");
		lblModality.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblModality.setBounds(10, 272, 62, 15);
		sendImagingRequest.add(lblModality);
		
		IMG_modalityDropdown = new JComboBox();
		IMG_modalityDropdown.setToolTipText("");
		IMG_modalityDropdown.setFont(new Font("Tahoma", Font.PLAIN, 12));
		IMG_modalityDropdown.setBounds(70, 269, 158, 21);
		sendImagingRequest.add(IMG_modalityDropdown);
		IMG_modalityDropdown.addItem("SELECT");
		IMG_modalityDropdown.addItem("Plain X-Ray");
		IMG_modalityDropdown.addItem("Fluoroscopy");
		IMG_modalityDropdown.addItem("Mammography");
		IMG_modalityDropdown.addItem("Ultrasound");
		IMG_modalityDropdown.addItem("Computed Tompgraphy");
		IMG_modalityDropdown.addItem("Magnetic Resonace Imaging");
		
		JButton IMG_btnGoToScheduling = new JButton("Go to Scheduling");
		IMG_btnGoToScheduling.setFont(new Font("Tahoma", Font.PLAIN, 12));
		IMG_btnGoToScheduling.setBounds(635, 270, 158, 21);
		sendImagingRequest.add(IMG_btnGoToScheduling);
		IMG_btnGoToScheduling.setVisible(false);
		
		JCheckBox IMG_chckbxDone = new JCheckBox("Done?");
		IMG_chckbxDone.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent IMG_Confirmation)
			{
				if (IMG_chckbxDone.isSelected())
				{
					IMG_btnGoToScheduling.setEnabled(true);
					IMG_btnGoToScheduling.setVisible(true);
				}
				else
				{
					IMG_btnGoToScheduling.setEnabled(false);
					IMG_btnGoToScheduling.setVisible(false);
				}
			}
		});
		IMG_chckbxDone.setFont(new Font("Tahoma", Font.PLAIN, 12));
		IMG_chckbxDone.setBounds(557, 269, 67, 21);
		sendImagingRequest.add(IMG_chckbxDone);
		
		JPanel confirmCancelPanel1 = new JPanel();
		confirmCancelPanel1.setBounds(106, 317, 267, 48);
		sendImagingRequest.add(confirmCancelPanel1);
		confirmCancelPanel1.setLayout(null);
		confirmCancelPanel1.setVisible(false);
		
		JButton IMG_btnCancel1 = new JButton("Cancel");
		IMG_btnCancel1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent sendImagingRequestCancelButtonPressed) 
			{
				confirmCancelPanel1.setVisible(true);
				IMG_btnYes2.setEnabled(true);
				IMG_btnNo2.setEnabled(true);
			}
		});
			
		IMG_btnYes2 = new JButton("YES");
		IMG_btnYes2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent IMG_yesButtonPressed) 
			{
				layeredPane.setLayer(referringPhysicianMainMenu, 1);
				layeredPane.setLayer(sendImagingRequest, -1);
				hypothesisNotesInput.setText(null);
				observationsInput.setText(null);
				IMG_bodyAreaDropdown.setSelectedIndex(0);
				IMG_chckbxDone.setSelected(false);
				IMG_conditionDropdown.setSelectedIndex(0);
				IMG_modalityDropdown.setSelectedIndex(0);
				IMG_patientText.setText(null);
				IMG_bodyAreaDropdown.setEnabled(false);
				IMG_btnCancel1.setEnabled(false);
				IMG_btnGoToScheduling.setVisible(false);
				IMG_btnGoToScheduling.setEnabled(false);
				IMG_chckbxDone.setEnabled(false);
				IMG_patientText.setEnabled(false);
				IMG_btnYes2.setEnabled(false);
				IMG_btnNo2.setEnabled(false);
				IMG_modalityDropdown.setEnabled(false);
				confirmCancelPanel1.setVisible(false);
				IMG_btnYes2.setEnabled(false);
				IMG_btnNo2.setEnabled(false);
				MAIN_btnEditPatientInfo.setEnabled(true);
				MAIN_btnRegisterNewPatient.setEnabled(true);
				MAIN_btnSendImagingRequest.setEnabled(true);
				
			}
		});
		IMG_btnYes2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		IMG_btnYes2.setBounds(98, 10, 63, 21);
		confirmCancelPanel1.add(IMG_btnYes2);
		
		IMG_btnNo2 = new JButton("NO");
		IMG_btnNo2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent IMG_cancelCancellation)
			{
				confirmCancelPanel1.setVisible(false);
				IMG_btnYes2.setEnabled(false);
				IMG_btnNo2.setEnabled(false);
			}
		});
		IMG_btnNo2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		IMG_btnNo2.setBounds(194, 10, 63, 21);
		confirmCancelPanel1.add(IMG_btnNo2);
		
		JLabel lblPleaseConfirm_1 = new JLabel("Please confirm");
		lblPleaseConfirm_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPleaseConfirm_1.setBounds(10, 13, 92, 15);
		confirmCancelPanel1.add(lblPleaseConfirm_1);
		IMG_btnCancel1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		IMG_btnCancel1.setBounds(10, 326, 85, 21);
		sendImagingRequest.add(IMG_btnCancel1);
		
		referringPhysicianMainMenu = new JPanel();
		layeredPane.setLayer(referringPhysicianMainMenu, 1);
		referringPhysicianMainMenu.setBounds(0, 0, 824, 423);
		layeredPane.add(referringPhysicianMainMenu);
		referringPhysicianMainMenu.setLayout(null);
		
		MAIN_btnEditPatientInfo = new JButton("Edit an Existing Patient's Profile");
		MAIN_btnEditPatientInfo.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent goToEditRegistrationInfo) 
			{
				
			}
		});
		MAIN_btnEditPatientInfo.setToolTipText("");
		MAIN_btnEditPatientInfo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		MAIN_btnEditPatientInfo.setBounds(579, 181, 235, 50);
		referringPhysicianMainMenu.add(MAIN_btnEditPatientInfo);
		
		MAIN_btnSendImagingRequest = new JButton("Send an Imaging Request");
		MAIN_btnSendImagingRequest.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent goToImagingRequest) 
			{
				layeredPane.setLayer(sendImagingRequest, 1);
				layeredPane.setLayer(referringPhysicianMainMenu, 0);
				IMG_bodyAreaDropdown.setEnabled(true);
				IMG_btnCancel1.setEnabled(true);
				IMG_chckbxDone.setEnabled(true);
				IMG_patientText.setEnabled(true);
				IMG_modalityDropdown.setEnabled(true);
				IMG_conditionDropdown.setEnabled(true);
				MAIN_btnEditPatientInfo.setEnabled(false);
				MAIN_btnRegisterNewPatient.setEnabled(false);
				MAIN_btnSendImagingRequest.setEnabled(false);
			}
		});
		MAIN_btnSendImagingRequest.setFont(new Font("Tahoma", Font.PLAIN, 14));
		MAIN_btnSendImagingRequest.setBounds(296, 181, 235, 50);
		referringPhysicianMainMenu.add(MAIN_btnSendImagingRequest);
		
		txtpnWelcomeBack = new JTextPane();
		txtpnWelcomeBack.setEditable(false);
		txtpnWelcomeBack.setBounds(10, 10, 111, 19);
		txtpnWelcomeBack.setText("Welcome Back, Doctor");
		referringPhysicianMainMenu.add(txtpnWelcomeBack);
		
		txtpnDoctor = new JTextPane();
		txtpnDoctor.setEditable(false);
		txtpnDoctor.setBounds(114, 10, 77, 19);
		referringPhysicianMainMenu.add(txtpnDoctor);
		
		MAIN_btnRegisterNewPatient = new JButton("Register a New Patient");
		MAIN_btnRegisterNewPatient.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent goToRegisterPatientPanel) 
			{
				layeredPane.setLayer(referringPhysicianMainMenu, 0);
				layeredPane.setLayer(registerPatientPanel, 1);
				REG_btnCancel.setEnabled(true);
				REG_btnRegisterPatient.setEnabled(true);
				REG_btnYes.setEnabled(true);
				REG_chckbxConfirmWithPatient.setEnabled(true);
				REG_chckbxDifferentThanPatient.setEnabled(true);
				REG_responsibleStateDropdown.setEnabled(true);
				REG_sexDropdown.setEnabled(true);
				REG_btnNo.setEnabled(true);
				REG_employerStateDropdown.setEnabled(true);
				REG_stateDropdown.setEnabled(true);
				MAIN_btnEditPatientInfo.setEnabled(false);
				MAIN_btnRegisterNewPatient.setEnabled(false);
				MAIN_btnSendImagingRequest.setEnabled(false);
			}
		});
		MAIN_btnRegisterNewPatient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		MAIN_btnRegisterNewPatient.setBounds(10, 181, 235, 50);
		referringPhysicianMainMenu.add(MAIN_btnRegisterNewPatient);
		
		editPatientInfoPanel = new JPanel();
		layeredPane.setLayer(editPatientInfoPanel, -2);
		editPatientInfoPanel.setBounds(0, 0, 826, 423);
		layeredPane.add(editPatientInfoPanel);
		
		//IMPORTANT! Make sure that the following 9 lines are at the BOTTOM of the code!
		IMG_bodyAreaDropdown.setEnabled(false);
		IMG_btnCancel1.setEnabled(false);
		IMG_btnGoToScheduling.setEnabled(false);
		IMG_chckbxDone.setEnabled(false);
		IMG_patientText.setEnabled(false);
		IMG_btnYes2.setEnabled(false);
		IMG_btnNo2.setEnabled(false);
		IMG_modalityDropdown.setEnabled(false);
		
		//IMPORTANT! Make sure that the following 10 lines are at the BOTTOM of the code!
		REG_btnCancel.setEnabled(false);
		REG_btnRegisterPatient.setEnabled(false);
		REG_btnYes.setEnabled(false);
		REG_chckbxConfirmWithPatient.setEnabled(false);
		REG_chckbxDifferentThanPatient.setEnabled(false);
		REG_responsibleStateDropdown.setEnabled(false);
		REG_sexDropdown.setEnabled(false);
		REG_btnNo.setEnabled(false);
		REG_employerStateDropdown.setEnabled(false);
		REG_stateDropdown.setEnabled(false);
		
	}
	
	private static List getPatientInfo(String ssn){
		
		List myArray = new List();
		Connection conn=null;
		try {
			conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", ""); //This part here created the connection to MySQL
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Connection Failure.");
			e.printStackTrace();
		}
		
		if(conn != null) {
			try {
				Statement s = null;
				String query= "SELECT First_Name from hospitaliris.patient_information where socsSecNum=" + ssn; //edit this 
				s=conn.createStatement();
				ResultSet rs=s.executeQuery(query);
				 while (rs.next()) {
				      String name = rs.getString("First_Name"); //retrieve the result and save it to name
				      myArray.add(name); //intended to use when populating a dropdown
				      System.out.println(name); //testing out what is saved to name.
				         }
					
			} catch (SQLException e) {

				System.out.print("The following error was produced: "+"\n");
				e.printStackTrace();
					}
		}
		return myArray;
	}
}