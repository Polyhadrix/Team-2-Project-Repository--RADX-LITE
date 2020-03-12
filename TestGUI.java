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

public class TestGUI {

	private JFrame frame;
	private JTextField firstNameInput;
	private JTextField middleInitialInput;
	private JLabel lblLastName;
	private JTextField lastNameInput;
	private JLabel lblSex;
	private JLabel lblDateOfBirth;
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
	private JTextField ResponsibleNameInput;
	private JLabel lblResponsibleRelationship;
	private JTextField responsibleRelationshipInput;
	private JLabel lblAddressIfDifferent;
	private JTextField responsibleAddress;
	private JTextField responsibleCityInput;
	private JTextField responsibleZipCodeInput;
	private JTextField responsibleWorkNumberInput;
	private JTextField responsibleHomeNumberInput;
	private JTextField responsibleSSNInput;
	private JLabel lblEmployerAddress;
	private JTextField employerAddressInput;
	private JLabel lblCity_1;
	private JTextField employerCityInput;
	private JLabel lblEmployerState;
	private JComboBox employerStateDropdown;
	private JLabel lblEmployerZipCode;
	private JTextField employerZipInput;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestGUI window = new TestGUI();
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
	public TestGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 754, 398);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFirstName.setBounds(10, 6, 65, 24);
		frame.getContentPane().add(lblFirstName);
		
		firstNameInput = new JTextField();
		firstNameInput.setBounds(75, 10, 96, 19);
		frame.getContentPane().add(firstNameInput);
		firstNameInput.setColumns(10);
		
		JLabel lblMiddleInitial = new JLabel("Middle Initial:");
		lblMiddleInitial.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMiddleInitial.setBounds(181, 6, 79, 24);
		frame.getContentPane().add(lblMiddleInitial);
		
		middleInitialInput = new JTextField();
		middleInitialInput.setColumns(10);
		middleInitialInput.setBounds(258, 10, 27, 19);
		frame.getContentPane().add(middleInitialInput);
		
		lblLastName = new JLabel("Last Name:");
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblLastName.setBounds(297, 6, 65, 24);
		frame.getContentPane().add(lblLastName);
		
		lastNameInput = new JTextField();
		lastNameInput.setColumns(10);
		lastNameInput.setBounds(363, 10, 96, 19);
		frame.getContentPane().add(lastNameInput);
		
		lblSex = new JLabel("Sex:");
		lblSex.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSex.setBounds(10, 36, 27, 24);
		frame.getContentPane().add(lblSex);
		
		JComboBox sexDropdown = new JComboBox();
		sexDropdown.setBounds(40, 39, 79, 21);
		frame.getContentPane().add(sexDropdown);
		sexDropdown.addItem("SELECT");
		sexDropdown.addItem("Male");
		sexDropdown.addItem("Female");
		
		lblDateOfBirth = new JLabel("Date of Birth:");
		lblDateOfBirth.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDateOfBirth.setBounds(469, 6, 71, 24);
		frame.getContentPane().add(lblDateOfBirth);
		
		birthMonthInput = new JTextField();
		birthMonthInput.setColumns(10);
		birthMonthInput.setBounds(546, 10, 27, 19);
		frame.getContentPane().add(birthMonthInput);
		
		lblSlashDelimiter = new JLabel("/");
		lblSlashDelimiter.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSlashDelimiter.setBounds(578, 6, 11, 24);
		frame.getContentPane().add(lblSlashDelimiter);
		
		birthDayInput = new JTextField();
		birthDayInput.setColumns(10);
		birthDayInput.setBounds(586, 10, 27, 19);
		frame.getContentPane().add(birthDayInput);
		
		lblSlashDelimiter1 = new JLabel("/");
		lblSlashDelimiter1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSlashDelimiter1.setBounds(616, 6, 11, 24);
		frame.getContentPane().add(lblSlashDelimiter1);
		
		birthYearInput = new JTextField();
		birthYearInput.setColumns(10);
		birthYearInput.setBounds(626, 10, 37, 19);
		frame.getContentPane().add(birthYearInput);
		
		lblStreetAddress = new JLabel("Address:");
		lblStreetAddress.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblStreetAddress.setBounds(129, 36, 46, 24);
		frame.getContentPane().add(lblStreetAddress);
		
		addressInput = new JTextField();
		addressInput.setColumns(10);
		addressInput.setBounds(181, 40, 140, 19);
		frame.getContentPane().add(addressInput);
		
		lblCity = new JLabel("City:");
		lblCity.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCity.setBounds(331, 36, 27, 24);
		frame.getContentPane().add(lblCity);
		
		cityInput = new JTextField();
		cityInput.setColumns(10);
		cityInput.setBounds(363, 40, 140, 19);
		frame.getContentPane().add(cityInput);
		
		lblState = new JLabel("State:");
		lblState.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblState.setBounds(513, 36, 32, 24);
		frame.getContentPane().add(lblState);
		
		stateDropdown = new JComboBox();
		stateDropdown.setBounds(551, 39, 112, 21);
		frame.getContentPane().add(stateDropdown);
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
		lblZipCode.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblZipCode.setBounds(666, 36, 27, 24);
		frame.getContentPane().add(lblZipCode);
		
		zipCodeInput = new JTextField();
		zipCodeInput.setColumns(10);
		zipCodeInput.setBounds(690, 40, 40, 19);
		frame.getContentPane().add(zipCodeInput);
		
		lblWorkPhone = new JLabel("Work Phone Number:");
		lblWorkPhone.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblWorkPhone.setBounds(10, 70, 119, 24);
		frame.getContentPane().add(lblWorkPhone);
		
		workPhoneNumberInput = new JTextField();
		workPhoneNumberInput.setColumns(10);
		workPhoneNumberInput.setBounds(134, 72, 79, 19);
		frame.getContentPane().add(workPhoneNumberInput);
		
		JLabel lblHomePhone = new JLabel("Home Phone Number:");
		lblHomePhone.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblHomePhone.setBounds(226, 70, 119, 24);
		frame.getContentPane().add(lblHomePhone);
		
		homePhoneNumberInput = new JTextField();
		homePhoneNumberInput.setColumns(10);
		homePhoneNumberInput.setBounds(352, 72, 79, 19);
		frame.getContentPane().add(homePhoneNumberInput);
		
		lblSSN = new JLabel("Social Security Number:");
		lblSSN.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSSN.setBounds(443, 70, 131, 24);
		frame.getContentPane().add(lblSSN);
		
		socSecNumInput = new JTextField();
		socSecNumInput.setColumns(10);
		socSecNumInput.setBounds(577, 72, 65, 19);
		frame.getContentPane().add(socSecNumInput);
		
		lblEmployerName = new JLabel("Name of Employer:");
		lblEmployerName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEmployerName.setBounds(10, 104, 109, 24);
		frame.getContentPane().add(lblEmployerName);
		
		employerNameInput = new JTextField();
		employerNameInput.setColumns(10);
		employerNameInput.setBounds(116, 108, 180, 19);
		frame.getContentPane().add(employerNameInput);
		
		lblEmployerAddress = new JLabel("Street Address of Workplace:");
		lblEmployerAddress.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEmployerAddress.setBounds(306, 104, 160, 24);
		frame.getContentPane().add(lblEmployerAddress);
		
		employerAddressInput = new JTextField();
		employerAddressInput.setColumns(10);
		employerAddressInput.setBounds(469, 108, 180, 19);
		frame.getContentPane().add(employerAddressInput);
		
		lblCity_1 = new JLabel("City:");
		lblCity_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCity_1.setBounds(10, 138, 27, 24);
		frame.getContentPane().add(lblCity_1);
		
		employerCityInput = new JTextField();
		employerCityInput.setColumns(10);
		employerCityInput.setBounds(40, 142, 140, 19);
		frame.getContentPane().add(employerCityInput);
		
		lblEmployerState = new JLabel("State:");
		lblEmployerState.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEmployerState.setBounds(190, 137, 32, 24);
		frame.getContentPane().add(lblEmployerState);
		
		employerStateDropdown = new JComboBox();
		employerStateDropdown.setBounds(226, 141, 112, 21);
		frame.getContentPane().add(employerStateDropdown);
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
		lblEmployerZipCode.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEmployerZipCode.setBounds(352, 138, 27, 24);
		frame.getContentPane().add(lblEmployerZipCode);
		
		employerZipInput = new JTextField();
		employerZipInput.setColumns(10);
		employerZipInput.setBounds(380, 142, 40, 19);
		frame.getContentPane().add(employerZipInput);
		
		lblResponsibleName = new JLabel("Person Responsible for Balance:");
		lblResponsibleName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblResponsibleName.setBounds(10, 172, 184, 24);
		frame.getContentPane().add(lblResponsibleName);
		
		ResponsibleNameInput = new JTextField();
		ResponsibleNameInput.setColumns(10);
		ResponsibleNameInput.setBounds(191, 176, 171, 19);
		frame.getContentPane().add(ResponsibleNameInput);
		
		JPanel responsiblePanel = new JPanel();
		responsiblePanel.setBounds(0, 206, 740, 104);
		frame.getContentPane().add(responsiblePanel);
		responsiblePanel.setLayout(null);
		responsiblePanel.setVisible(false);
		
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
		
		responsibleAddress = new JTextField();
		responsibleAddress.setBounds(139, 41, 96, 19);
		responsiblePanel.add(responsibleAddress);
		responsibleAddress.setColumns(10);
		
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
		
		JCheckBox chckbxDifferentThanPatient = new JCheckBox("Different than Patient?");
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
		chckbxDifferentThanPatient.setBounds(372, 175, 148, 21);
		frame.getContentPane().add(chckbxDifferentThanPatient);
		
		JButton btnRegisterPatient = new JButton("Register Patient");
		btnRegisterPatient.setVisible(false);
		btnRegisterPatient.addActionListener(new ActionListener() {
			
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
					String City = cityInput.getText();
					String address = addressInput.getText();
					String zipCode = zipCodeInput.getText();
					String employerName = employerNameInput.getText();
					String workPhoneNumber = workPhoneNumberInput.getText();
					String homePhoneNumber = homePhoneNumberInput.getText();
					String socSecNum = socSecNumInput.getText();
					String employerAddress = employerAddressInput.getText();
					String employerCity = employerCityInput.getText();
					String employerZip = employerZipInput.getText();
					
					String query = "INSERT INTO hospitalris.patient_information (First_Name, Middle_initial, Last_Name, birthMonth, birthYear,birthDay,City,address,zipcode,employerName,workPhoneNumber,homePhoneNumber,socSecNum, employerAddress, employerCity, employerZip) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
					PreparedStatement stmt = conn.prepareStatement(query);
				
					stmt.setString( 1, firstName);
					stmt.setString( 2, middleInitial);
					stmt.setString( 3, lastName);
					stmt.setString( 4, birthMonth);
					stmt.setString( 5, birthYear);
					stmt.setString(6, birthDay);
					stmt.setString( 7, City);
					stmt.setString( 8, address);
					stmt.setString( 9, zipCode);
					stmt.setString( 10, employerName);
					stmt.setString( 11, workPhoneNumber);
					stmt.setString( 12, homePhoneNumber);
					stmt.setString(13, socSecNum);
					stmt.setString(14, employerAddress);
					stmt.setString(15, employerCity);
					stmt.setString(16, employerZip);
					stmt.execute();
					System.out.println("Patient information successfully saved!");
				} catch(SQLException e) {
					System.out.println("Something is wrong with the patient data");
					e.printStackTrace();
				}
				
				//this should only push the responsible user information if the checkbox is selected.
				//done to prevent errors on eclipse side.
				if (chckbxDifferentThanPatient.isSelected()) {
				try {
					String ResponsibleName = ResponsibleNameInput.getText();
					String responsibleRelationship = responsibleRelationshipInput.getText();
					String responsibleAddressInput = responsibleAddress.getText();
					String responsibleCity = responsibleCityInput.getText();
					String responsibleZipCode = responsibleZipCodeInput.getText();
					String responsibleWorkNumber = responsibleWorkNumberInput.getText();
					String responsibleHomeNumber = responsibleHomeNumberInput.getText();
					String responsibleSSN = responsibleSSNInput.getText();
					String socSecNum = socSecNumInput.getText();
					
					String query2 = "INSERT INTO hospitalris.responsible_information (ResponsibleName, responsibleRelationship, responsibleAddressInput,responsibleCity,responsibleZipCode,responsibleWorkNumber,responsibleHomeNumber,responsibleSSN, dependentSSN) VALUES (?,?,?,?,?,?,?,?,?)";
					PreparedStatement stmt2 = conn.prepareStatement(query2);
					stmt2.setString( 1, ResponsibleName);
					stmt2.setString( 2, responsibleRelationship);
					stmt2.setString( 3, responsibleAddressInput);
					stmt2.setString( 4, responsibleCity);
					stmt2.setString( 5, responsibleZipCode);
					stmt2.setString( 6, responsibleWorkNumber);
					stmt2.setString( 7, responsibleHomeNumber);
					stmt2.setString( 8, responsibleSSN);
					stmt2.setString( 9, socSecNum);
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
		btnRegisterPatient.setBounds(590, 330, 140, 21);
		frame.getContentPane().add(btnRegisterPatient);
		
		JCheckBox chckbxConfirmWithPatient = new JCheckBox("CONFIRM WITH PATIENT");
		chckbxConfirmWithPatient.addActionListener(new ActionListener() {
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
		chckbxConfirmWithPatient.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chckbxConfirmWithPatient.setBounds(403, 329, 170, 21);
		frame.getContentPane().add(chckbxConfirmWithPatient);
	}
}
