import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.*;
import java.sql.*;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;

public class AddRadiologistPanel {
	private static Container mainContent;
	private static Connection conn;
	private static String lastSearch;
	/* Search Field */
	private static JTextField search;
    /* Text Areas */
	private static JTextArea diagnosis;
	/* Drop Downs */
	private static JComboBox<String> cb_image;
	private static DefaultComboBoxModel<String> cb_image_options = new DefaultComboBoxModel<String>();
	/* Dynamic Labels */
	private static JLabel out_tName, out_tSex, out_tBirth, out_tSSN; // `lbl_imgPreview` removed in favor of pop-out image preview
	private static JTextArea out_observations;
	/* All Components: Keep updated list at end of createPanel() This is used to clearForm()*/
	private static ArrayList<JLabel> allLabels = new ArrayList<>();
	private static ArrayList<JTextArea> allTextAreas = new ArrayList<>();
	private static ArrayList<JComboBox<String>> allDropDowns = new ArrayList<JComboBox<String>>();
	/*Drop-Down Values: myCBox.setModel(new DefaultComboBoxModel<String>(myArr));*/
	private static ArrayList<Path> urlList = new ArrayList<>();
	/* Holder for Technician Notes */
	private static ArrayList<String> imgIDList = new ArrayList<>(), observationList = new ArrayList<>(),modalityList = new ArrayList<>(),
			poiList = new ArrayList<>(),conditionList = new ArrayList<>(),imgDateList = new ArrayList<>(), diagList = new ArrayList<>();
	private static ArrayList<ArrayList<String>> allData = new ArrayList<>();
	
	private static JTextField preparerName;
	
	/* WindowBuilder Requires a class constructor to work correctly */
	public AddRadiologistPanel() {
		createPnlRadiologist(mainContent,conn);}
	
	public static JPanel createPnlRadiologist(Container param_contentPane, Connection param_conn) {
		mainContent = param_contentPane;
		conn = param_conn;
		JPanel pnl_technician = new JPanel();
		GridBagConstraints gbc_pnl_registration = new GridBagConstraints();
		gbc_pnl_registration.fill = GridBagConstraints.BOTH;
		gbc_pnl_registration.anchor = GridBagConstraints.NORTH;
		gbc_pnl_registration.gridx = 0;
		gbc_pnl_registration.gridy = 0;
		mainContent.add(pnl_technician, gbc_pnl_registration);
		GridBagLayout gbl_pnl_technician = new GridBagLayout();
		gbl_pnl_technician.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0};
		gbl_pnl_technician.columnWidths = new int[] {0};
		gbl_pnl_technician.rowHeights = new int[] {0};
		gbl_pnl_technician.columnWeights = new double[]{1.0};
		pnl_technician.setLayout(gbl_pnl_technician);

		JPanel row0 = new JPanel();
		GridBagConstraints gbc_row0 = new GridBagConstraints();
		gbc_row0.anchor = GridBagConstraints.NORTH;
		gbc_row0.fill = GridBagConstraints.HORIZONTAL;
		gbc_row0.insets = new Insets(0, 0, 5, 0);
		gbc_row0.gridx = 0;
		gbc_row0.gridy = 0;
		pnl_technician.add(row0, gbc_row0);
		GridBagLayout gbl_row0 = new GridBagLayout();
		gbl_row0.columnWidths = new int[] {0};
		gbl_row0.rowHeights = new int[] {0};
		gbl_row0.columnWeights = new double[]{0.0};
		gbl_row0.rowWeights = new double[]{0.0};
		row0.setLayout(gbl_row0);

		JLabel lbl_Title = new JLabel("Radiologist: Review Image");
		lbl_Title.setVerticalAlignment(SwingConstants.TOP);
		lbl_Title.setHorizontalAlignment(SwingConstants.LEFT);
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
		pnl_technician.add(row1, gbc_row1);
		GridBagLayout gbl_row1 = new GridBagLayout();
		gbl_row1.columnWidths = new int[] {0};
		gbl_row1.rowHeights = new int[] {0};
		gbl_row1.columnWeights = new double[]{0.0, 1.0};
		gbl_row1.rowWeights = new double[]{0.0};
		row1.setLayout(gbl_row1);

		JLabel lbl_search = new JLabel("Review images of Patient ID#:");
		lbl_search.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lbl_search = new GridBagConstraints();
		gbc_lbl_search.insets = new Insets(0, 5, 5, 5);
		gbc_lbl_search.gridx = 0;
		gbc_lbl_search.gridy = 0;
		row1.add(lbl_search, gbc_lbl_search);

		search = new JTextField();
		search.setFont(new Font("Tahoma", Font.PLAIN, 14));
		search.addActionListener( new viewPatientListener() );
		search.addFocusListener( new viewPatientListener() );
		GridBagConstraints gbc_search = new GridBagConstraints();
		gbc_search.insets = new Insets(0, 0, 0, 5);
		gbc_search.fill = GridBagConstraints.HORIZONTAL;
		gbc_search.gridx = 1;
		gbc_search.gridy = 0;
		row1.add(search, gbc_search);
		search.setColumns(10);

		JPanel row2 = new JPanel();
		GridBagConstraints gbc_row2 = new GridBagConstraints();
		gbc_row2.fill = GridBagConstraints.HORIZONTAL;
		gbc_row2.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc_row2.insets = new Insets(0, 0, 5, 0);
		gbc_row2.gridx = 0;
		gbc_row2.gridy = 2;
		pnl_technician.add(row2, gbc_row2);
		GridBagLayout gbl_row2 = new GridBagLayout();
		gbl_row2.columnWidths = new int[] {0};
		gbl_row2.rowHeights = new int[] {0};
		gbl_row2.rowWeights = new double[]{0.0};
		row2.setLayout(gbl_row2);

		JLabel lbl_tName = new JLabel("Patient: ");
		lbl_tName.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_tName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lbl_tName = new GridBagConstraints();
		gbc_lbl_tName.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc_lbl_tName.insets = new Insets(0, 5, 5, 5);
		gbc_lbl_tName.gridx = 0;
		gbc_lbl_tName.gridy = 0;
		row2.add(lbl_tName, gbc_lbl_tName);

		out_tName = new JLabel("_");
		GridBagConstraints gbc_out_tName = new GridBagConstraints();
		gbc_out_tName.anchor = GridBagConstraints.WEST;
		gbc_out_tName.weightx = 1.0;
		gbc_out_tName.insets = new Insets(0, 0, 5, 5);
		gbc_out_tName.gridx = 1;
		gbc_out_tName.gridy = 0;
		row2.add(out_tName, gbc_out_tName);
		JLabel lbl_tSex = new JLabel("Sex: ");
		lbl_tSex.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lbl_tSex = new GridBagConstraints();
		gbc_lbl_tSex.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_tSex.gridx = 2;
		gbc_lbl_tSex.gridy = 0;
		row2.add(lbl_tSex, gbc_lbl_tSex);
		
		out_tSex = new JLabel("_");
		GridBagConstraints gbc_out_tSex = new GridBagConstraints();
		gbc_out_tSex.anchor = GridBagConstraints.WEST;
		gbc_out_tSex.weightx = 1.0;
		gbc_out_tSex.insets = new Insets(0, 0, 5, 5);
		gbc_out_tSex.gridx = 3;
		gbc_out_tSex.gridy = 0;
		row2.add(out_tSex, gbc_out_tSex);

		JLabel lbl_tBirth = new JLabel("Birthdate: ");
		lbl_tBirth.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lbl_tBirth = new GridBagConstraints();
		gbc_lbl_tBirth.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_tBirth.gridx = 4;
		gbc_lbl_tBirth.gridy = 0;
		row2.add(lbl_tBirth, gbc_lbl_tBirth);
		
		out_tBirth = new JLabel("_");
		GridBagConstraints gbc_out_tBirth = new GridBagConstraints();
		gbc_out_tBirth.anchor = GridBagConstraints.WEST;
		gbc_out_tBirth.weightx = 1.0;
		gbc_out_tBirth.insets = new Insets(0, 0, 5, 5);
		gbc_out_tBirth.gridx = 5;
		gbc_out_tBirth.gridy = 0;
		row2.add(out_tBirth, gbc_out_tBirth);

		JLabel lbl_tSSN = new JLabel("SSN: ");
		lbl_tSSN.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lbl_tSSN = new GridBagConstraints();
		gbc_lbl_tSSN.insets = new Insets(0, 0, 5, 0);
		gbc_lbl_tSSN.gridx = 6;
		gbc_lbl_tSSN.gridy = 0;
		row2.add(lbl_tSSN, gbc_lbl_tSSN);
		
		out_tSSN = new JLabel("_");
		GridBagConstraints gbc_out_tSSN = new GridBagConstraints();
		gbc_out_tSSN.anchor = GridBagConstraints.WEST;
		gbc_out_tSSN.weightx = 1.0;
		gbc_out_tSSN.insets = new Insets(0, 0, 0, 5);
		gbc_out_tSSN.gridx = 7;
		gbc_out_tSSN.gridy = 0;
		row2.add(out_tSSN, gbc_out_tSSN);

		JPanel row3 = new JPanel();
		GridBagConstraints gbc_row3 = new GridBagConstraints();
		gbc_row3.weighty = 1.0;
		gbc_row3.anchor = GridBagConstraints.NORTH;
		gbc_row3.fill = GridBagConstraints.BOTH;
		gbc_row3.insets = new Insets(0, 0, 5, 0);
		gbc_row3.gridx = 0;
		gbc_row3.gridy = 3;
		pnl_technician.add(row3, gbc_row3);
		GridBagLayout gbl_row3 = new GridBagLayout();
		gbl_row3.columnWidths = new int[] {0};
		gbl_row3.rowHeights = new int[] {0};
		gbl_row3.columnWeights = new double[]{1.0, 1.0};
		gbl_row3.rowWeights = new double[]{1.0};
		row3.setLayout(gbl_row3);
		
		JPanel img_pnl = new JPanel();
		GridBagConstraints gbc_img_pnl = new GridBagConstraints();
		gbc_img_pnl.fill = GridBagConstraints.BOTH;
		gbc_img_pnl.weighty = 0.0;
		gbc_img_pnl.insets = new Insets(0, 0, 5, 0);
		gbc_img_pnl.gridx = 0;
		gbc_img_pnl.gridy = 0;
		row3.add(img_pnl, gbc_img_pnl);
		GridBagLayout gbl_img_pnl = new GridBagLayout();
		gbl_img_pnl.columnWidths = new int[] {0};
		gbl_img_pnl.rowHeights = new int[] {0};
		gbl_img_pnl.columnWeights = new double[]{1.0, 0.0};
		gbl_img_pnl.rowWeights = new double[]{0.0, 0.0, 1.0};
		img_pnl.setLayout(gbl_img_pnl);
		
/*		Removed in favor of pop-out window version
		lbl_imgPreview = new JLabel("Image Preview");
		lbl_imgPreview.setFont(new Font("Tahoma", Font.ITALIC, 10));
		GridBagConstraints gbc_lbl_imgPreview = new GridBagConstraints();
		gbc_lbl_imgPreview.gridwidth = 2;
		gbc_lbl_imgPreview.insets = new Insets(0, 0, 5, 0);
		gbc_lbl_imgPreview.weighty = 1.0;
		gbc_lbl_imgPreview.gridx = 0;
		gbc_lbl_imgPreview.gridy = 3;
		img_pnl.add(lbl_imgPreview, gbc_lbl_imgPreview);
		lbl_imgPreview.setHorizontalTextPosition(JLabel.CENTER);
		lbl_imgPreview.setVerticalTextPosition(JLabel.BOTTOM);
*/
		cb_image = new JComboBox<String>();
		cb_image.setToolTipText("Select a valid patient first");
		cb_image.setEnabled(false);
		cb_image.addItemListener( new loadTechNotes() );
		GridBagConstraints gbc_cb_image = new GridBagConstraints();
		gbc_cb_image.anchor = GridBagConstraints.NORTH;
		gbc_cb_image.insets = new Insets(0, 0, 5, 5);
		gbc_cb_image.fill = GridBagConstraints.HORIZONTAL;
		gbc_cb_image.gridx = 0;
		gbc_cb_image.gridy = 0;
		img_pnl.add(cb_image, gbc_cb_image);
		
		JButton btn_viewImg = new JButton("View Image");
		btn_viewImg.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn_viewImg.addActionListener( new viewImageListener() );
		GridBagConstraints gbc_btn_viewImg = new GridBagConstraints();
		gbc_btn_viewImg.anchor = GridBagConstraints.NORTH;
		gbc_btn_viewImg.insets = new Insets(0, 0, 5, 0);
		gbc_btn_viewImg.gridx = 1;
		gbc_btn_viewImg.gridy = 0;
		img_pnl.add(btn_viewImg, gbc_btn_viewImg);
		
		JLabel lbl_tObvs = new JLabel("Technician's Observations");
		GridBagConstraints gbc_lbl_tObvs = new GridBagConstraints();
		gbc_lbl_tObvs.insets = new Insets(0, 0, 5, 0);
		gbc_lbl_tObvs.anchor = GridBagConstraints.NORTH;
		gbc_lbl_tObvs.gridwidth = 2;
		gbc_lbl_tObvs.gridx = 0;
		gbc_lbl_tObvs.gridy = 1;
		img_pnl.add(lbl_tObvs, gbc_lbl_tObvs);
		
		out_observations = new JTextArea("-");
		out_observations.setEditable(false);
		GridBagConstraints gbc_out_observations = new GridBagConstraints();
		gbc_out_observations.weightx = 1.0;
		gbc_out_observations.fill = GridBagConstraints.BOTH;
		gbc_out_observations.gridwidth = 2;
		gbc_out_observations.insets = new Insets(0, 0, 0, 5);
		gbc_out_observations.gridx = 0;
		gbc_out_observations.gridy = 2;
		img_pnl.add(out_observations, gbc_out_observations);
		
		JPanel note_pnl = new JPanel();
		GridBagConstraints gbc_note_pnl = new GridBagConstraints();
		gbc_note_pnl.weighty = 1.0;
		gbc_note_pnl.fill = GridBagConstraints.BOTH;
		gbc_note_pnl.gridx = 1;
		gbc_note_pnl.gridy = 0;
		row3.add(note_pnl, gbc_note_pnl);
		GridBagLayout gbl_note_pnl = new GridBagLayout();
		gbl_note_pnl.columnWidths = new int[]{0, 0};
		gbl_note_pnl.rowHeights = new int[]{0, 0, 0};
		gbl_note_pnl.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_note_pnl.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		note_pnl.setLayout(gbl_note_pnl);
		
		JLabel lbl_Diagnosis = new JLabel("Diagnosis");
		lbl_Diagnosis.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lbl_Diagnosis = new GridBagConstraints();
		gbc_lbl_Diagnosis.insets = new Insets(0, 0, 5, 0);
		gbc_lbl_Diagnosis.gridx = 0;
		gbc_lbl_Diagnosis.gridy = 0;
		note_pnl.add(lbl_Diagnosis, gbc_lbl_Diagnosis);
		
		diagnosis = new JTextArea();
		GridBagConstraints gbc_diagnosis = new GridBagConstraints();
		gbc_diagnosis.insets = new Insets(0, 0, 0, 5);
		gbc_diagnosis.weighty = 1.0;
		gbc_diagnosis.fill = GridBagConstraints.BOTH;
		gbc_diagnosis.gridx = 0;
		gbc_diagnosis.gridy = 1;
		note_pnl.add(diagnosis, gbc_diagnosis);
		
		JPanel row5 = new JPanel();
		GridBagConstraints gbc_row5 = new GridBagConstraints();
		gbc_row5.insets = new Insets(0, 0, 5, 0);
		gbc_row5.anchor = GridBagConstraints.SOUTH;
		gbc_row5.fill = GridBagConstraints.HORIZONTAL;
		gbc_row5.gridx = 0;
		gbc_row5.gridy = 4;
		pnl_technician.add(row5, gbc_row5);
		GridBagLayout gbl_row5 = new GridBagLayout();
		gbl_row5.columnWidths = new int[] {0};
		gbl_row5.rowHeights = new int[] {0};
		gbl_row5.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0};
		gbl_row5.rowWeights = new double[]{0.0};
		row5.setLayout(gbl_row5);
		
		JButton btn_saveDiagnosis = new JButton("Save Diagnosis");
		btn_saveDiagnosis.addActionListener(new saveDiagnosisListener() );
		
		JLabel lbl_techName = new JLabel("Enter your name:");
		lbl_techName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lbl_techName = new GridBagConstraints();
		gbc_lbl_techName.anchor = GridBagConstraints.EAST;
		gbc_lbl_techName.insets = new Insets(0, 5, 0, 5);
		gbc_lbl_techName.gridx = 0;
		gbc_lbl_techName.gridy = 0;
		row5.add(lbl_techName, gbc_lbl_techName);
		
		preparerName = new JTextField();
		preparerName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		preparerName.setColumns(10);
		GridBagConstraints gbc_in_name = new GridBagConstraints();
		gbc_in_name.insets = new Insets(0, 0, 0, 5);
		gbc_in_name.fill = GridBagConstraints.HORIZONTAL;
		gbc_in_name.gridx = 1;
		gbc_in_name.gridy = 0;
		row5.add(preparerName, gbc_in_name);
		btn_saveDiagnosis.setFont(new Font("Tahoma", Font.PLAIN, 14));
		//btn_submitPatient.addActionListener(new addPatientListener() );
		GridBagConstraints gbc_btn_saveDiagnosis = new GridBagConstraints();
		gbc_btn_saveDiagnosis.insets = new Insets(0, 5, 0, 5);
		gbc_btn_saveDiagnosis.gridx = 2;
		gbc_btn_saveDiagnosis.gridy = 0;
		row5.add(btn_saveDiagnosis, gbc_btn_saveDiagnosis);
		
		JButton btnResetForm = new JButton("Reset Form");
		btnResetForm.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent clearFormButtonPressed) { search.setText(null); clearForm(); } });
		btnResetForm.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_btnResetForm = new GridBagConstraints();
		gbc_btnResetForm.insets = new Insets(0, 0, 0, 5);
		gbc_btnResetForm.gridx = 3;
		gbc_btnResetForm.gridy = 0;
		row5.add(btnResetForm, gbc_btnResetForm);
		
		JButton btnReturnToMain = new JButton("Return to Main Menu");
		btnReturnToMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				switchPanel(pnl_technician, PatientPortal.pnl_mainmenu);
			}
		});
		btnReturnToMain.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_btnReturnToMain = new GridBagConstraints();
		gbc_btnReturnToMain.insets = new Insets(0, 0, 0, 5);
		gbc_btnReturnToMain.gridx = 4;
		gbc_btnReturnToMain.gridy = 0;
		row5.add(btnReturnToMain, gbc_btnReturnToMain);

		allLabels.addAll( Arrays.asList(out_tName,out_tSex,out_tBirth,out_tSSN) );
		allTextAreas.addAll( Arrays.asList(diagnosis, out_observations) );
		allData.addAll( Arrays.asList(imgIDList,observationList,modalityList,poiList,conditionList,conditionList,imgDateList,diagList) );

		return pnl_technician;
	} // initialize()


	private static class viewPatientListener implements ActionListener, FocusListener { 
		public void focusGained(FocusEvent e) { lastSearch = search.getText(); }
		public void focusLost(FocusEvent e) { if(!lastSearch.isBlank() || !search.getText().isBlank() ) searchPatient(); }
		public void actionPerformed(ActionEvent event) { searchPatient(); }
	}//viewPatientListener

	private static void searchPatient() {
		if (search.getText().isBlank() ) { 
		JOptionPane.showMessageDialog(mainContent,
			    "The Search Box is Empty",
			    "Warning",
			    JOptionPane.WARNING_MESSAGE);
				clearForm();
				search.setText(null);
		}else{
			PreparedStatement cli = null;
			String sql = "SELECT * FROM `ris`.`patient` WHERE `id`=?";

			// Try-Catch for SQLException
			try {
				// Notify the database of our intended statement
				cli = conn.prepareStatement(sql);
				// Load up the ?s in the statement

				String patientID = search.getText().strip().replaceFirst("^0+(?!$)", ""); // Remove whitespace and leading 0's
				cli.setString(1, patientID);
				ResultSet results = cli.executeQuery();

				if(! results.isBeforeFirst() ) { // No Results Found
					JOptionPane.showMessageDialog(mainContent,
						    "No Patient was found with that ID.",
						    "Warning",
						    JOptionPane.WARNING_MESSAGE);
					clearForm();
				}else { // Results found, Load them up!
					//clearForm();
					results.first();
					out_tName.setText( results.getString("last_name") +", "+ results.getString("first_name"));
					out_tBirth.setText( results.getString("birthdate") );
					out_tSex.setText( results.getString("sex") );
					out_tSSN.setText( results.getString("ssn") );
					
					// Check images and load them into the `cb_image` dropdown
					sql="SELECT * FROM `ris`.`image` WHERE `patient_id`=?";
					cli = conn.prepareStatement(sql);
					cli.setString(1, patientID);
					results = cli.executeQuery();
					
					
					if(! results.isBeforeFirst() ) { // No images found for patient
						cb_image.setEnabled(false);
						cb_image_options.removeAllElements();
						cb_image_options.addElement("No Images Available For Patient");
						cb_image.setModel( cb_image_options );
					}else {// Patient Images found, load them up!
						
						cb_image_options.removeAllElements();
						cb_image_options.addElement("Select Image");
						cb_image.setToolTipText("Select an image to view");
						for(ArrayList<String> dataList : allData) dataList.clear();
						urlList.clear();
						while( results.next() ) { // For each image this patient has
							urlList.add( Paths.get(results.getString("url")) ); // Save the absolute paths
							imgIDList.add( (results.getString("id").isBlank()) ? "" : results.getString("id") );
/**/						observationList.add( (results.getString("observations").isBlank()) ? "" : results.getString("observations") );
							modalityList.add( (results.getString("modality").isBlank()) ? "" : results.getString("modality") );
							poiList.add( (results.getString("poi").isBlank()) ? "" : results.getString("poi") );
							conditionList.add( (results.getString("condition").isBlank()) ? "" : results.getString("condition") );
							imgDateList.add( (results.getString("last_modified").isBlank()) ? "" : results.getString("last_modified") );
							diagList.add( (results.getString("observations").isBlank()) ? "" : results.getString("diagnosis") );
/**/
						}
						for(Path filename : urlList) cb_image_options.addElement( filename.getFileName().toString() ); // post just the filenames
						cb_image.setModel( cb_image_options );
						cb_image.setEnabled(true);
						
					}
					
				}
			} catch (SQLException e) { e.printStackTrace();}
		}
	}/*actionPerformed()*/
	
	// Helper Functions
	private static void clearForm(){
		for(JLabel lbl : allLabels) lbl.setText(null);
		for(JTextArea lbl : allTextAreas) lbl.setText(null);
		for(JComboBox<?> d : allDropDowns ) d.setSelectedIndex(0);
//		lbl_imgPreview.setIcon(null);
//		lbl_imgPreview.setText("Image Preview");
		cb_image.setEnabled(false);
		cb_image.setToolTipText("Select a valid patient first");
		cb_image_options.removeAllElements();
	}
	
	// LISTENERS
/*  Removed in favor of pop-out image vieweer version rather than in-panel preview version
 	public static class ImageSelectorListener implements ItemListener{
		@Override
	    public void itemStateChanged(ItemEvent event) {
	       if (event.getStateChange() == ItemEvent.SELECTED) {
	    	   if(cb_image.getSelectedIndex()==0) {// if first value (null) selected 
	    			lbl_imgPreview.setIcon(null);
	    			lbl_imgPreview.setText("Image Preview");
	    	   }else {// else an image was selected, load it up
	    		   lbl_imgPreview.setIcon(new ImageIcon(new ImageIcon( urlList.get(cb_image.getSelectedIndex()-1).toString() ).getImage().getScaledInstance(250, -1, Image.SCALE_DEFAULT)));
	    		   lbl_imgPreview.setText(cb_image.getSelectedItem().toString());
	    	   }
	    	   
	       }
		}//itemStateChabged
	}//ImageSelectorListener
*/
	public static class viewImageListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			BufferedImage image;
			try {
				int imgIdx = cb_image.getSelectedIndex()-1;
				if(imgIdx>-1) {
					image = ImageIO.read(new File(urlList.get( imgIdx ).toString()));
					JLabel picLabel = new JLabel(new ImageIcon(image));
					JOptionPane.showMessageDialog(null, picLabel, cb_image.getSelectedItem().toString(), JOptionPane.PLAIN_MESSAGE, null);
				}else {
					JOptionPane.showMessageDialog(mainContent,
						    "Please choose a patient to view their images.",
						    "Error: No Patient Selected",
						    JOptionPane.ERROR_MESSAGE);
				}
			} catch (IOException e1) {e1.printStackTrace();}
		}
	}

	public static class loadTechNotes implements ItemListener{
		@Override
	    public void itemStateChanged(ItemEvent event) {
	       if (event.getStateChange() == ItemEvent.SELECTED) {
	    	   if(cb_image.getSelectedIndex()<1) {// if first value (null) selected 
	    			out_observations.setText("-");
	    	   }else {// else an image was selected, load it up
	    		   int usrSelection = cb_image.getSelectedIndex()-1;
	    		   String output = "Focus: "+poiList.get(usrSelection)+"\r\n"
	    				   + "Image Date: "+imgDateList.get(usrSelection)+"\r\n"
	    				   + "Modality: "+modalityList.get(usrSelection)+"\r\n"
	    				   + "Condition: "+conditionList.get(usrSelection)+"\r\n"
	    				   + "Technician's Observation:\n"+observationList.get(usrSelection);
	    		  // lbl_imgPreview.setText(cb_image.getSelectedItem().toString());
	    		   out_observations.setText(output);
	    		   diagnosis.setText(diagList.get(usrSelection));
	    	   }
	    	   
	       }
		}//itemStateChabged
	}//ImageSelectorListener

	public static class saveDiagnosisListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			int selectedIdx= cb_image.getSelectedIndex();
			
			if(selectedIdx>0) {
				PreparedStatement cli = null;
				String sql ="UPDATE `ris`.`image` SET `diagnosis`=? WHERE `id`=?";
				int qCount=1;
				
				try {
					cli = conn.prepareStatement(sql);
					cli.setString(qCount++, diagnosis.getText() );
					cli.setString(qCount++, ""+ imgIDList.get(selectedIdx-1) );
					cli.executeUpdate(); // commit insert/update
					
					JOptionPane.showMessageDialog(mainContent, "Diagnosis successfully updated.");
					searchPatient(); // Refreshes the cached image data
					diagnosis.setText(""); // Clear old diagnosis data out
				} catch (SQLException e1) { e1.printStackTrace(); }
			}else {
				JOptionPane.showMessageDialog(mainContent,
					    "Please choose an image to diagnose.",
					    "Error: No Image Selected",
					    JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}
	
	public static void switchPanel(JPanel prevScreen, JPanel nextScreen) { prevScreen.setVisible(false); nextScreen.setVisible(true); }
	
	// Shortcut to print to System.out.println()
	public static void log(Object o) {System.out.println(o);}
}
