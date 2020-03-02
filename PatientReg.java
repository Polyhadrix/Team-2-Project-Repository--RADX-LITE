import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
	private JComboBox stateDropdown;
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
	private JComboBox employerStateDropdown;
	private JLabel lblEmployerZipCode;
	private JTextField employerZipInput;
	private JLabel lblDateOfBirth;
	private JPanel referringPhysicianMainMenu;
	private JPanel confirmCancelPanel;
	private JButton btnNo;
	private JLabel lblPleaseConfirm;
	private JTextPane txtpnWelcomeBack;
	private JTextPane txtpnDoctor;
	private JButton btnRegisterNewPatient;
	private JButton btnEditPatientInfo;
	private JButton btnSendImagingRequest;
	private JLabel lblPatient;
	private JTextField textField;
	private JLabel lblCondition;
	private JComboBox conditionDropDown;
	private JPanel editPatientInfoPanel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() 
			{
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
		
		editPatientInfoPanel = new JPanel();
		layeredPane.setLayer(editPatientInfoPanel, -2);
		editPatientInfoPanel.setBounds(0, 0, 826, 423);
		layeredPane.add(editPatientInfoPanel);
		
		JPanel sendImagingRequest = new JPanel();
		layeredPane.setLayer(sendImagingRequest, -1);
		sendImagingRequest.setBounds(0, 0, 826, 427);
		layeredPane.add(sendImagingRequest);
		sendImagingRequest.setLayout(null);
		
		lblPatient = new JLabel("Patient:");
		lblPatient.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPatient.setBounds(10, 10, 62, 15);
		sendImagingRequest.add(lblPatient);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(569, 9, 96, 19);
		sendImagingRequest.add(textField);
		
		JComboBox patientDropDown = new JComboBox();
		patientDropDown.setFont(new Font("Tahoma", Font.PLAIN, 12));
		patientDropDown.setToolTipText("");
		patientDropDown.setBounds(58, 7, 109, 21);
		sendImagingRequest.add(patientDropDown);
		patientDropDown.addItem("SELECT");
		
		lblCondition = new JLabel("Condition:");
		lblCondition.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCondition.setBounds(177, 10, 62, 15);
		sendImagingRequest.add(lblCondition);
		
		conditionDropDown = new JComboBox();
		conditionDropDown.setToolTipText("");
		conditionDropDown.setFont(new Font("Tahoma", Font.PLAIN, 12));
		conditionDropDown.setBounds(240, 7, 127, 21);
		sendImagingRequest.add(conditionDropDown);
		conditionDropDown.addItem("SELECT");
		
		
		
		JPanel registerPatientPanel = new JPanel();
		layeredPane.setLayer(registerPatientPanel, 0);
		registerPatientPanel.setBounds(0, 0, 824, 427);
		layeredPane.add(registerPatientPanel);
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
		
		JComboBox sexDropdown = new JComboBox();
		sexDropdown.setBounds(680, 11, 85, 19);
		registerPatientPanel.add(sexDropdown);
		sexDropdown.addItem("SELECT");
		sexDropdown.addItem("Male");
		sexDropdown.addItem("Female");
		
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
		
		stateDropdown = new JComboBox();
		stateDropdown.setBounds(391, 35, 99, 19);
		registerPatientPanel.add(stateDropdown);
		stateDropdown.addItem("SELECT");
		stateDropdown.addItem("Alabama");
		stateDropdown.addItem("Alaska");
		stateDropdown.addItem("Arkansas");
		stateDropdown.addItem("California");
		stateDropdown.addItem("Colorado");
		stateDropdown.addItem("Connecticut");
		stateDropdown.addItem("Delaware");
		stateDropdown.addItem("Florida");
		stateDropdown.addItem("Georgia");
		stateDropdown.addItem("Hawaii");
		stateDropdown.addItem("Idaho");
		stateDropdown.addItem("Illinois");
		stateDropdown.addItem("Indiana");
		stateDropdown.addItem("Iowa");
		stateDropdown.addItem("Kansas");
		stateDropdown.addItem("Kentucky");
		stateDropdown.addItem("Louisiana");
		stateDropdown.addItem("Maine");
		stateDropdown.addItem("Maryland");
		stateDropdown.addItem("Massachusetts");
		stateDropdown.addItem("Michigan");
		stateDropdown.addItem("Minnesota");
		stateDropdown.addItem("Mississippi");
		stateDropdown.addItem("Missouri");
		stateDropdown.addItem("Montana");
		stateDropdown.addItem("Nebraska");
		stateDropdown.addItem("Nevada");
		stateDropdown.addItem("New Hampshire");
		stateDropdown.addItem("New Jersey");
		stateDropdown.addItem("New Mexico");
		stateDropdown.addItem("New York");
		stateDropdown.addItem("North Carolina");
		stateDropdown.addItem("North Dakota");
		stateDropdown.addItem("Ohio");
		stateDropdown.addItem("Oklahoma");
		stateDropdown.addItem("Oregon");
		stateDropdown.addItem("Pennsylvania");
		stateDropdown.addItem("Rhode Island");
		stateDropdown.addItem("South Carolina");
		stateDropdown.addItem("South Dakota");
		stateDropdown.addItem("Tennessee");
		stateDropdown.addItem("Texas");
		stateDropdown.addItem("Utah");
		stateDropdown.addItem("Vermont");
		stateDropdown.addItem("Virginia");
		stateDropdown.addItem("Washington");
		stateDropdown.addItem("West Virginia");
		stateDropdown.addItem("Wisconsin");
		stateDropdown.addItem("Wyoming");
		
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
		
		employerStateDropdown = new JComboBox();
		employerStateDropdown.setBounds(476, 97, 99, 19);
		registerPatientPanel.add(employerStateDropdown);
		employerStateDropdown.addItem("SELECT");
		employerStateDropdown.addItem("Alabama");
		employerStateDropdown.addItem("Alaska");
		employerStateDropdown.addItem("Arkansas");
		employerStateDropdown.addItem("California");
		employerStateDropdown.addItem("Colorado");
		employerStateDropdown.addItem("Connecticut");
		employerStateDropdown.addItem("Delaware");
		employerStateDropdown.addItem("Florida");
		employerStateDropdown.addItem("Georgia");
		employerStateDropdown.addItem("Hawaii");
		employerStateDropdown.addItem("Idaho");
		employerStateDropdown.addItem("Illinois");
		employerStateDropdown.addItem("Indiana");
		employerStateDropdown.addItem("Iowa");
		employerStateDropdown.addItem("Kansas");
		employerStateDropdown.addItem("Kentucky");
		employerStateDropdown.addItem("Louisiana");
		employerStateDropdown.addItem("Maine");
		employerStateDropdown.addItem("Maryland");
		employerStateDropdown.addItem("Massachusetts");
		employerStateDropdown.addItem("Michigan");
		employerStateDropdown.addItem("Minnesota");
		employerStateDropdown.addItem("Mississippi");
		employerStateDropdown.addItem("Missouri");
		employerStateDropdown.addItem("Montana");
		employerStateDropdown.addItem("Nebraska");
		employerStateDropdown.addItem("Nevada");
		employerStateDropdown.addItem("New Hampshire");
		employerStateDropdown.addItem("New Jersey");
		employerStateDropdown.addItem("New Mexico");
		employerStateDropdown.addItem("New York");
		employerStateDropdown.addItem("North Carolina");
		employerStateDropdown.addItem("North Dakota");
		employerStateDropdown.addItem("Ohio");
		employerStateDropdown.addItem("Oklahoma");
		employerStateDropdown.addItem("Oregon");
		employerStateDropdown.addItem("Pennsylvania");
		employerStateDropdown.addItem("Rhode Island");
		employerStateDropdown.addItem("South Carolina");
		employerStateDropdown.addItem("South Dakota");
		employerStateDropdown.addItem("Tennessee");
		employerStateDropdown.addItem("Texas");
		employerStateDropdown.addItem("Utah");
		employerStateDropdown.addItem("Vermont");
		employerStateDropdown.addItem("Virginia");
		employerStateDropdown.addItem("Washington");
		employerStateDropdown.addItem("West Virginia");
		employerStateDropdown.addItem("Wisconsin");
		employerStateDropdown.addItem("Wyoming");	
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
		
		JCheckBox chckbxDifferentThanPatient = new JCheckBox("Different than Patient?");
		chckbxDifferentThanPatient.setBounds(294, 123, 148, 21);
		registerPatientPanel.add(chckbxDifferentThanPatient);
		chckbxDifferentThanPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent checkboxClicked) 
			{
				if (chckbxDifferentThanPatient.isSelected())
				{
					responsiblePanel.setVisible(true);
				}
				if (chckbxDifferentThanPatient.isSelected()==false)
				{
					responsiblePanel.setVisible(false);
				}
			}
		});
		chckbxDifferentThanPatient.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		
		
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
		
		JComboBox responsibleStateDropdown = new JComboBox();
		responsibleStateDropdown.setBounds(418, 41, 99, 19);
		responsiblePanel.add(responsibleStateDropdown);
		responsibleStateDropdown.addItem("SELECT");
		responsibleStateDropdown.addItem("Alabama");
		responsibleStateDropdown.addItem("Alaska");
		responsibleStateDropdown.addItem("Arkansas");
		responsibleStateDropdown.addItem("California");
		responsibleStateDropdown.addItem("Colorado");
		responsibleStateDropdown.addItem("Connecticut");
		responsibleStateDropdown.addItem("Delaware");
		responsibleStateDropdown.addItem("Florida");
		responsibleStateDropdown.addItem("Georgia");
		responsibleStateDropdown.addItem("Hawaii");
		responsibleStateDropdown.addItem("Idaho");
		responsibleStateDropdown.addItem("Illinois");
		responsibleStateDropdown.addItem("Indiana");
		responsibleStateDropdown.addItem("Iowa");
		responsibleStateDropdown.addItem("Kansas");
		responsibleStateDropdown.addItem("Kentucky");
		responsibleStateDropdown.addItem("Louisiana");
		responsibleStateDropdown.addItem("Maine");
		responsibleStateDropdown.addItem("Maryland");
		responsibleStateDropdown.addItem("Massachusetts");
		responsibleStateDropdown.addItem("Michigan");
		responsibleStateDropdown.addItem("Minnesota");
		responsibleStateDropdown.addItem("Mississippi");
		responsibleStateDropdown.addItem("Missouri");
		responsibleStateDropdown.addItem("Montana");
		responsibleStateDropdown.addItem("Nebraska");
		responsibleStateDropdown.addItem("Nevada");
		responsibleStateDropdown.addItem("New Hampshire");
		responsibleStateDropdown.addItem("New Jersey");
		responsibleStateDropdown.addItem("New Mexico");
		responsibleStateDropdown.addItem("New York");
		responsibleStateDropdown.addItem("North Carolina");
		responsibleStateDropdown.addItem("North Dakota");
		responsibleStateDropdown.addItem("Ohio");
		responsibleStateDropdown.addItem("Oklahoma");
		responsibleStateDropdown.addItem("Oregon");
		responsibleStateDropdown.addItem("Pennsylvania");
		responsibleStateDropdown.addItem("Rhode Island");
		responsibleStateDropdown.addItem("South Carolina");
		responsibleStateDropdown.addItem("South Dakota");
		responsibleStateDropdown.addItem("Tennessee");
		responsibleStateDropdown.addItem("Texas");
		responsibleStateDropdown.addItem("Utah");
		responsibleStateDropdown.addItem("Vermont");
		responsibleStateDropdown.addItem("Virginia");
		responsibleStateDropdown.addItem("Washington");
		responsibleStateDropdown.addItem("West Virginia");
		responsibleStateDropdown.addItem("Wisconsin");
		responsibleStateDropdown.addItem("Wyoming");
		
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
		
		JButton btnRegisterPatient = new JButton("Register Patient");
		btnRegisterPatient.setBounds(599, 272, 140, 21);
		registerPatientPanel.add(btnRegisterPatient);
		btnRegisterPatient.setVisible(false);
		btnRegisterPatient.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent buttonPressed) 
			{
				Connection conn=null;

				try {
					conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", ""); //This part here created the connection to My SQL
					if(conn != null) 
					System.out.println("Connection Established.");
					
					
					String firstName = firstNameInput.getText();
					String middleInitial = middleInitialInput.getText();
					String lastName = lastNameInput.getText();
					String sex = (String) sexDropdown.getSelectedItem();
					String birthMonth = birthMonthInput.getText();
					String birthYear = birthYearInput.getText();
					String birthDay = birthDayInput.getText();
					String City = cityInput.getText();
					String address = addressInput.getText();
					String state = (String) stateDropdown.getSelectedItem();
					String zipCode = zipCodeInput.getText();
					String employerName = employerNameInput.getText();
					String workPhoneNumber = workPhoneNumberInput.getText();
					String homePhoneNumber = homePhoneNumberInput.getText();
					String socSecNum = socSecNumInput.getText();
					String ResponsibleName = responsibleNameInput.getText();
					String responsibleRelationship = responsibleRelationshipInput.getText();
					String responsibleAddress = responsibleAddressInput.getText();
					String responsibleCity = responsibleCityInput.getText();
					String responsibleState = (String) responsibleStateDropdown.getSelectedItem();
					String responsibleZipCode = responsibleZipCodeInput.getText();
					String responsibleWorkNumber = responsibleWorkNumberInput.getText();
					String responsibleHomeNumber = responsibleHomeNumberInput.getText();
					String responsibleSSN = responsibleSSNInput.getText();
					String employerAddress = employerAddressInput.getText();
					String employerCity = employerCityInput.getText();
					String employerState = (String) employerStateDropdown.getSelectedItem();
					String employerZip = employerZipInput.getText();
					
					String query = "INSERT INTO patient.patient_information (First_Name, Middle_initial, Last_Name) VALUES (?,?,?)";
					PreparedStatement stmt = conn.prepareStatement(query);
					
					stmt.setString( 1, firstName);
					stmt.setString( 2, middleInitial);
					stmt.setString( 3, lastName);
					
					stmt.execute();
				}
				 catch (SQLException e) {
						// TODO Auto-generated catch block
						System.out.println("Connection Failure.");
						e.printStackTrace();
					}
			}
		});
		
		
		JCheckBox chckbxConfirmWithPatient = new JCheckBox("CONFIRM WITH PATIENT");
		chckbxConfirmWithPatient.setBounds(425, 270, 170, 21);
		registerPatientPanel.add(chckbxConfirmWithPatient);
		chckbxConfirmWithPatient.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		chckbxConfirmWithPatient.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent confirmed) 
			{
				if (chckbxConfirmWithPatient.isSelected())
				{
					btnRegisterPatient.setVisible(true);
				}
				if (chckbxConfirmWithPatient.isSelected() == false)
				{
					btnRegisterPatient.setVisible(false);
				}
			}
		});
		
		confirmCancelPanel = new JPanel();
		confirmCancelPanel.setBounds(106, 258, 249, 35);
		registerPatientPanel.add(confirmCancelPanel);
		confirmCancelPanel.setLayout(null);
		confirmCancelPanel.setVisible(false);
		
		JButton btnYes = new JButton("YES");
		btnYes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent confirmedCancelSequence) 
			{
				firstNameInput.setText(null);
				middleInitialInput.setText(null);
				lastNameInput.setText(null);
				birthMonthInput.setText(null);
				birthDayInput.setText(null);
				birthYearInput.setText(null);
				sexDropdown.setSelectedIndex(0);
				addressInput.setText(null);
				cityInput.setText(null);
				stateDropdown.setSelectedIndex(0);
				zipCodeInput.setText(null);
				workPhoneNumberInput.setText(null);
				homePhoneNumberInput.setText(null);
				socSecNumInput.setText(null);
				employerNameInput.setText(null);
				employerAddressInput.setText(null);
				employerCityInput.setText(null);
				employerStateDropdown.setSelectedIndex(0);
				employerZipInput.setText(null);
				responsibleNameInput.setText(null);
				responsibleRelationshipInput.setText(null);
				responsibleAddressInput.setText(null);
				responsibleCityInput.setText(null);
				responsibleStateDropdown.setSelectedIndex(0);
				responsibleZipCodeInput.setText(null);
				responsibleWorkNumberInput.setText(null);
				responsibleHomeNumberInput.setText(null);
				responsibleSSNInput.setText(null);
				layeredPane.setLayer(registerPatientPanel, 0);
				layeredPane.setLayer(referringPhysicianMainMenu, 1);
			}
		});
		btnYes.setBounds(105, 9, 59, 21);
		confirmCancelPanel.add(btnYes);
		
		btnNo = new JButton("NO");
		btnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent cancelledCancelSequence) 
			{
				confirmCancelPanel.setVisible(false);
			}
		});
		btnNo.setBounds(174, 9, 65, 21);
		confirmCancelPanel.add(btnNo);
		
		lblPleaseConfirm = new JLabel("Please Confirm:");
		lblPleaseConfirm.setForeground(Color.RED);
		lblPleaseConfirm.setBounds(10, 10, 95, 17);
		confirmCancelPanel.add(lblPleaseConfirm);
		lblPleaseConfirm.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent cancelButtonClicked) 
			{
				confirmCancelPanel.setVisible(true);
			}
		});
		btnCancel.setBounds(10, 265, 85, 21);
		registerPatientPanel.add(btnCancel);
		
		referringPhysicianMainMenu = new JPanel();
		layeredPane.setLayer(referringPhysicianMainMenu, 1);
		referringPhysicianMainMenu.setBounds(0, 0, 824, 423);
		layeredPane.add(referringPhysicianMainMenu);
		referringPhysicianMainMenu.setLayout(null);
		
		btnEditPatientInfo = new JButton("Edit an Existing Patient's Profile");
		btnEditPatientInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent goToEditRegistrationInfo) 
			{
				
			}
		});
		btnEditPatientInfo.setToolTipText("");
		btnEditPatientInfo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnEditPatientInfo.setBounds(579, 181, 235, 50);
		referringPhysicianMainMenu.add(btnEditPatientInfo);
		
		btnSendImagingRequest = new JButton("Send an Imaging Request");
		btnSendImagingRequest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent goToImagingRequest) 
			{
				layeredPane.setLayer(sendImagingRequest, 1);
				layeredPane.setLayer(referringPhysicianMainMenu, 0);
				layeredPane.setLayer(registerPatientPanel, -1);
			}
		});
		btnSendImagingRequest.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSendImagingRequest.setBounds(296, 181, 235, 50);
		referringPhysicianMainMenu.add(btnSendImagingRequest);
		
		txtpnWelcomeBack = new JTextPane();
		txtpnWelcomeBack.setBounds(10, 10, 111, 19);
		txtpnWelcomeBack.setText("Welcome Back, Doctor");
		referringPhysicianMainMenu.add(txtpnWelcomeBack);
		
		txtpnDoctor = new JTextPane();
		txtpnDoctor.setBounds(114, 10, 77, 19);
		referringPhysicianMainMenu.add(txtpnDoctor);
		
		btnRegisterNewPatient = new JButton("Register a New Patient");
		btnRegisterNewPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent goToRegisterPatientPanel) 
			{
				layeredPane.setLayer(referringPhysicianMainMenu, 0);
				layeredPane.setLayer(registerPatientPanel, 1);
			}
		});
		btnRegisterNewPatient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnRegisterNewPatient.setBounds(10, 181, 235, 50);
		referringPhysicianMainMenu.add(btnRegisterNewPatient);
	}
}
