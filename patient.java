import java.io.File;
import java.util.Scanner;
public class patient extends personnel
{
	private String patientID;
	private String firstName;
	private String middleInitial;
	private String lastName;
	private String birthMonth;
	private String birthYear;
	private String birthDay;
	private String sex;
	private String streetAddress;
	private String city;
	private String state;
	private String zipCode;
	private String workPhoneNumber;
	private String homePhoneNumber;
	private String socialSecurityNumber;
	private String employerName;
	private String employerStreetAddress;
	private String employerCity;
	private String employerState;
	private String employerZipCode;
	private String responsibleName;
	private boolean different;
	private String responsibleRelationship;
	private String responsibleStreetAddress;
	private String responsibleCity;
	private String responsibleState;
	private String responsibleZipCode;
	private String responsibleWorkPhoneNumber;
	private String responsibleHomePhoneNumber;
	private String responsibleSocialSecurityNumber;
	private File files;
		public patient(String patientID, String firstName, String middleInitial, String lastName, String birthMonth,
				String birthYear, String birthDay, String sex, String streetAddress, String city, String state,
				String zipCode, String workPhoneNumber, String homePhoneNumber, String socialSecurityNumber,
				String employerName, String employerStreetAddress, String employerCity, String employerState,
				String employerZipCode, String responsibleName, boolean different, String responsibleRelationship,
				String responsibleStreetAddress, String responsibleCity, String responsibleState,
				String responsibleZipCode, String responsibleWorkPhoneNumber, String responsibleHomePhoneNumber,
				String responsibleSocialSecurityNumber, File files)
		{
			super();
			this.patientID = patientID;
			this.firstName = firstName;
			this.middleInitial = middleInitial;
			this.lastName = lastName;
			this.birthMonth = birthMonth;
			this.birthYear = birthYear;
			this.birthDay = birthDay;
			this.sex = sex;
			this.streetAddress = streetAddress;
			this.city = city;
			this.state = state;
			this.zipCode = zipCode;
			this.workPhoneNumber = workPhoneNumber;
			this.homePhoneNumber = homePhoneNumber;
			this.socialSecurityNumber = socialSecurityNumber;
			this.employerName = employerName;
			this.employerStreetAddress = employerStreetAddress;
			this.employerCity = employerCity;
			this.employerState = employerState;
			this.employerZipCode = employerZipCode;
			this.responsibleName = responsibleName;
			this.different = different;
			this.responsibleRelationship = responsibleRelationship;
			this.responsibleStreetAddress = responsibleStreetAddress;
			this.responsibleCity = responsibleCity;
			this.responsibleState = responsibleState;
			this.responsibleZipCode = responsibleZipCode;
			this.responsibleWorkPhoneNumber = responsibleWorkPhoneNumber;
			this.responsibleHomePhoneNumber = responsibleHomePhoneNumber;
			this.responsibleSocialSecurityNumber = responsibleSocialSecurityNumber;
			this.files = files;
		}
		public void setPatientID(String patientID) {
			this.patientID = patientID;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public void setMiddleInitial(String middleInitial) {
			this.middleInitial = middleInitial;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public void setBirthMonth(String birthMonth) {
			this.birthMonth = birthMonth;
		}
		public void setBirthYear(String birthYear) {
			this.birthYear = birthYear;
		}
		public void setBirthDay(String birthDay) {
			this.birthDay = birthDay;
		}
		public void setSex(String sex) {
			this.sex = sex;
		}
		public void setStreetAddress(String streetAddress) {
			this.streetAddress = streetAddress;
		}
		public void setCity(String city) {
			this.city = city;
		}
		public void setState(String state) {
			this.state = state;
		}
		public void setZipCode(String zipCode) {
			this.zipCode = zipCode;
		}
		public void setWorkPhoneNumber(String workPhoneNumber) {
			this.workPhoneNumber = workPhoneNumber;
		}
		public void setHomePhoneNumber(String homePhoneNumber) {
			this.homePhoneNumber = homePhoneNumber;
		}
		public void setSocialSecurityNumber(String socialSecurityNumber) {
			this.socialSecurityNumber = socialSecurityNumber;
		}
		public void setEmployerName(String employerName) {
			this.employerName = employerName;
		}
		public void setEmployerStreetAddress(String employerStreetAddress) {
			this.employerStreetAddress = employerStreetAddress;
		}
		public void setEmployerCity(String employerCity) {
			this.employerCity = employerCity;
		}
		public void setEmployerState(String employerState) {
			this.employerState = employerState;
		}
		public void setEmployerZipCode(String employerZipCode) {
			this.employerZipCode = employerZipCode;
		}
		public void setResponsibleName(String responsibleName) {
			this.responsibleName = responsibleName;
		}
		public void setDifferent(boolean different) {
			this.different = different;
		}
		public void setResponsibleRelationship(String responsibleRelationship) {
			this.responsibleRelationship = responsibleRelationship;
		}
		public void setResponsibleStreetAddress(String responsibleStreetAddress) {
			this.responsibleStreetAddress = responsibleStreetAddress;
		}
		public void setResponsibleCity(String responsibleCity) {
			this.responsibleCity = responsibleCity;
		}
		public void setResponsibleState(String responsibleState) {
			this.responsibleState = responsibleState;
		}
		public void setResponsibleZipCode(String responsibleZipCode) {
			this.responsibleZipCode = responsibleZipCode;
		}
		public void setResponsibleWorkPhoneNumber(String responsibleWorkPhoneNumber) {
			this.responsibleWorkPhoneNumber = responsibleWorkPhoneNumber;
		}
		public void setResponsibleHomePhoneNumber(String responsibleHomePhoneNumber) {
			this.responsibleHomePhoneNumber = responsibleHomePhoneNumber;
		}
		public void setResponsibleSocialSecurityNumber(String responsibleSocialSecurityNumber) {
			this.responsibleSocialSecurityNumber = responsibleSocialSecurityNumber;
		}
		public void setFiles(File files) {
			this.files = files;
		}
		public String getPatientID() {
			return patientID;
		}
		public String getFirstName() {
			return firstName;
		}
		public String getMiddleInitial() {
			return middleInitial;
		}
		public String getLastName() {
			return lastName;
		}
		public String getBirthMonth() {
			return birthMonth;
		}
		public String getBirthYear() {
			return birthYear;
		}
		public String getBirthDay() {
			return birthDay;
		}
		public String getSex() {
			return sex;
		}
		public String getStreetAddress() {
			return streetAddress;
		}
		public String getCity() {
			return city;
		}
		public String getState() {
			return state;
		}
		public String getZipCode() {
			return zipCode;
		}
		public String getWorkPhoneNumber() {
			return workPhoneNumber;
		}
		public String getHomePhoneNumber() {
			return homePhoneNumber;
		}
		public String getSocialSecurityNumber() {
			return socialSecurityNumber;
		}
		public String getEmployerName() {
			return employerName;
		}
		public String getEmployerStreetAddress() {
			return employerStreetAddress;
		}
		public String getEmployerCity() {
			return employerCity;
		}
		public String getEmployerState() {
			return employerState;
		}
		public String getEmployerZipCode() {
			return employerZipCode;
		}
		public String getResponsibleName() {
			return responsibleName;
		}
		public boolean getDifferent() {
			return different;
		}
		public String getResponsibleRelationship() {
			return responsibleRelationship;
		}
		public String getResponsibleStreetAddress() {
			return responsibleStreetAddress;
		}
		public String getResponsibleCity() {
			return responsibleCity;
		}
		public String getResponsibleState() {
			return responsibleState;
		}
		public String getResponsibleZipCode() {
			return responsibleZipCode;
		}
		public String getResponsibleWorkPhoneNumber() {
			return responsibleWorkPhoneNumber;
		}
		public String getResponsibleHomePhoneNumber() {
			return responsibleHomePhoneNumber;
		}
		public String getResponsibleSocialSecurityNumber() {
			return responsibleSocialSecurityNumber;
		}
		public File getFiles() {
			return files;
		}	
}
