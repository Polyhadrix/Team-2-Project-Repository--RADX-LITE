package ris;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import java.util.Date;

import javax.swing.*;

public class AddTechnicianPanel {
	private static Container mainContent;
	private static Connection conn;
	private static File selectedFile;
	private static JFileChooser fc;
	private static String lastSearch;
	/* Search Field */
	private static JTextField search;
    /* Text Areas */
	private static JTextArea observations;
	/* Dynamic Labels */
	private static JLabel lbl_imgPreview, out_tName, out_tSex, out_tBirth, out_tSSN;
	/* Drop-Downs */
	private static JComboBox<String> condition, poi, modality;
	/* All Components: Keep updated list at end of createPanel() This is used to clearForm()*/
	private static ArrayList<JLabel> allLabels = new ArrayList<>();
	private static ArrayList<JTextArea> allTextAreas = new ArrayList<>();
	private static ArrayList<JComboBox<String>> allDropDowns = new ArrayList<JComboBox<String>>();
	/*Drop-Down Values: myCBox.setModel(new DefaultComboBoxModel<String>(myArr));*/
	private static String[] conditions = {"","Fracture","Hernia","Embolism","Cancer", "Arterial/Vascular Obstruction"};
	private static String[] pois = {"","head","neck","chest","upper back","lower back","abdomen","arm - right","arm - left","leg - right","leg - right","foot - right","foot - left"};
	private static String[] modalities = {"","X-Ray","Magnetic Resonance Imaging","Computed Tomography","Ultrasound","Fluoroscopy","Mammography"};
	
	/* WindowBuilder Requires a class constructor to work correctly */
	public AddTechnicianPanel() {
		createPnlTechnician(mainContent,conn);}
	
	public static JPanel createPnlTechnician(Container param_contentPane, Connection param_conn) {
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
		gbl_pnl_technician.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0};
		gbl_pnl_technician.columnWidths = new int[] {0};
		gbl_pnl_technician.rowHeights = new int[] {0, 0, 0, 0, 0, 0};
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

		JLabel lbl_Title = new JLabel("Technician: Upload Image");
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
		pnl_technician.add(row1, gbc_row1);
		GridBagLayout gbl_row1 = new GridBagLayout();
		gbl_row1.columnWidths = new int[] {0};
		gbl_row1.rowHeights = new int[] {0};
		gbl_row1.columnWeights = new double[]{0.0, 1.0};
		gbl_row1.rowWeights = new double[]{0.0};
		row1.setLayout(gbl_row1);

		JLabel lbl_search = new JLabel("To add an Image to a Patient: enter their Patient ID# here: ");
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
		gbc_img_pnl.fill = GridBagConstraints.VERTICAL;
		gbc_img_pnl.weighty = 0.0;
		gbc_img_pnl.insets = new Insets(0, 0, 5, 0);
		gbc_img_pnl.gridx = 0;
		gbc_img_pnl.gridy = 0;
		row3.add(img_pnl, gbc_img_pnl);
		GridBagLayout gbl_img_pnl = new GridBagLayout();
		gbl_img_pnl.columnWidths = new int[]{0, 0};
		gbl_img_pnl.rowHeights = new int[]{0, 0, 0};
		gbl_img_pnl.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_img_pnl.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		img_pnl.setLayout(gbl_img_pnl);
		
		JButton btn_ChooseImage = new JButton("Choose Image");
		GridBagConstraints gbc_btn_ChooseImage = new GridBagConstraints();
		gbc_btn_ChooseImage.insets = new Insets(0, 0, 5, 0);
		gbc_btn_ChooseImage.gridx = 0;
		gbc_btn_ChooseImage.gridy = 0;
		img_pnl.add(btn_ChooseImage, gbc_btn_ChooseImage);
		btn_ChooseImage.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		lbl_imgPreview = new JLabel("Image Preview");
		lbl_imgPreview.setFont(new Font("Tahoma", Font.ITALIC, 10));
		GridBagConstraints gbc_lbl_imgPreview = new GridBagConstraints();
		gbc_lbl_imgPreview.weighty = 1.0;
		gbc_lbl_imgPreview.gridx = 0;
		gbc_lbl_imgPreview.gridy = 1;
		img_pnl.add(lbl_imgPreview, gbc_lbl_imgPreview);
		lbl_imgPreview.setHorizontalTextPosition(JLabel.CENTER);
		lbl_imgPreview.setVerticalTextPosition(JLabel.BOTTOM);
		
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
		
		JLabel lbl_Observations = new JLabel("Observations");
		lbl_Observations.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lbl_Observations = new GridBagConstraints();
		gbc_lbl_Observations.insets = new Insets(0, 0, 5, 0);
		gbc_lbl_Observations.gridx = 0;
		gbc_lbl_Observations.gridy = 0;
		note_pnl.add(lbl_Observations, gbc_lbl_Observations);
		
		observations = new JTextArea();
		GridBagConstraints gbc_observations = new GridBagConstraints();
		gbc_observations.weighty = 1.0;
		gbc_observations.fill = GridBagConstraints.BOTH;
		gbc_observations.gridx = 0;
		gbc_observations.gridy = 1;
		note_pnl.add(observations, gbc_observations);
		btn_ChooseImage.addActionListener( new chooseImageListener() );

		JPanel row4 = new JPanel();
		GridBagConstraints gbc_row4 = new GridBagConstraints();
		gbc_row4.fill = GridBagConstraints.HORIZONTAL;
		gbc_row4.anchor = GridBagConstraints.SOUTH;
		gbc_row4.gridx = 0;
		gbc_row4.gridy = 4;
		pnl_technician.add(row4, gbc_row4);
		GridBagLayout gbl_row4 = new GridBagLayout();
		gbl_row4.columnWidths = new int[] {0};
		gbl_row4.rowHeights = new int[] {0};
		gbl_row4.columnWeights = new double[]{1.0, 1.0, 1.0};
		gbl_row4.rowWeights = new double[]{0.0, 0.0};
		row4.setLayout(gbl_row4);
		
		JLabel lbl_modality = new JLabel("Modality");
		lbl_modality.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lbl_modality = new GridBagConstraints();
		gbc_lbl_modality.gridx = 0;
		gbc_lbl_modality.gridy = 0;
		row4.add(lbl_modality, gbc_lbl_modality);
		
		JLabel lbl_poi = new JLabel("Part of Body");
		lbl_poi.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lbl_poi = new GridBagConstraints();
		gbc_lbl_poi.gridx = 1;
		gbc_lbl_poi.gridy = 0;
		row4.add(lbl_poi, gbc_lbl_poi);
		
		JLabel lbl_condition = new JLabel("Condition");
		lbl_condition.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lbl_condition = new GridBagConstraints();
		gbc_lbl_condition.gridx = 2;
		gbc_lbl_condition.gridy = 0;
		row4.add(lbl_condition, gbc_lbl_condition);
		
		modality = new JComboBox<String>();
		modality.setModel( new DefaultComboBoxModel<String>(modalities) );
		GridBagConstraints gbc_modality = new GridBagConstraints();
		gbc_modality.fill = GridBagConstraints.HORIZONTAL;
		gbc_modality.insets = new Insets(0, 0, 5, 5);
		gbc_modality.gridx = 0;
		gbc_modality.gridy = 1;
		row4.add(modality, gbc_modality);
		
		poi = new JComboBox<String>();
		poi.setModel( new DefaultComboBoxModel<String>(pois) );
		GridBagConstraints gbc_poi = new GridBagConstraints();
		gbc_poi.insets = new Insets(0, 0, 5, 5);
		gbc_poi.fill = GridBagConstraints.HORIZONTAL;
		gbc_poi.gridx = 1;
		gbc_poi.gridy = 1;
		row4.add(poi, gbc_poi);
		
		condition = new JComboBox<String>();
		condition.setModel( new DefaultComboBoxModel<String>(conditions) );
		GridBagConstraints gbc_condition = new GridBagConstraints();
		gbc_condition.insets = new Insets(0, 0, 5, 5);
		gbc_condition.fill = GridBagConstraints.HORIZONTAL;
		gbc_condition.gridx = 2;
		gbc_condition.gridy = 1;
		row4.add(condition, gbc_condition);
		
		JPanel row5 = new JPanel();
		GridBagConstraints gbc_row5 = new GridBagConstraints();
		gbc_row5.insets = new Insets(0, 0, 5, 0);
		gbc_row5.anchor = GridBagConstraints.SOUTH;
		gbc_row5.fill = GridBagConstraints.HORIZONTAL;
		gbc_row5.gridx = 0;
		gbc_row5.gridy = 5;
		pnl_technician.add(row5, gbc_row5);
		GridBagLayout gbl_row5 = new GridBagLayout();
		gbl_row5.columnWidths = new int[] {0};
		gbl_row5.rowHeights = new int[] {0};
		gbl_row5.columnWeights = new double[]{0.0, 1.0};
		gbl_row5.rowWeights = new double[]{0.0};
		row5.setLayout(gbl_row5);
		
		JButton btn_uploadImage = new JButton("Upload Image");
		btn_uploadImage.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { confirmUpload(); } });
		btn_uploadImage.setFont(new Font("Tahoma", Font.PLAIN, 14));
		//btn_submitPatient.addActionListener(new addPatientListener() );
		GridBagConstraints gbc_btn_uploadImage = new GridBagConstraints();
		gbc_btn_uploadImage.insets = new Insets(0, 5, 0, 5);
		gbc_btn_uploadImage.gridx = 0;
		gbc_btn_uploadImage.gridy = 0;
		row5.add(btn_uploadImage, gbc_btn_uploadImage);
		
		JButton btnClearForm = new JButton("Clear Form");
		btnClearForm.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent clearFormButtonPressed) { search.setText(null); clearForm(); } });
		btnClearForm.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_btnClearForm = new GridBagConstraints();
		gbc_btnClearForm.anchor = GridBagConstraints.WEST;
		gbc_btnClearForm.insets = new Insets(0, 50, 0, 5);
		gbc_btnClearForm.gridx = 1;
		gbc_btnClearForm.gridy = 0;
		row5.add(btnClearForm, gbc_btnClearForm);

		allLabels.addAll( Arrays.asList(out_tName,out_tSex,out_tBirth,out_tSSN) );
		allTextAreas.addAll( Arrays.asList(observations) );
		allDropDowns.addAll( Arrays.asList(modality, condition, poi) );

		return pnl_technician;
	} // initialize()
	
	/* Listeners */
	private static class chooseImageListener  implements ActionListener { 
		public void actionPerformed(ActionEvent event) {
			//Set up the file chooser.
	        if (fc == null) {
	            fc = new JFileChooser();

		    //Add a custom file filter and disable the default
		    //(Accept All) file filter.
	            fc.addChoosableFileFilter(new ImageFilter());
	            fc.setAcceptAllFileFilterUsed(false);

		    //Add custom icons for file types.
	            fc.setFileView(new ImageFileView());

		    //Add the preview pane.
	            fc.setAccessory(new ImagePreview(fc));
	        }

	        //Show it.
	        int returnVal = fc.showDialog(mainContent, "Return Image Path");

	        //Process the results.
	        if (returnVal == JFileChooser.APPROVE_OPTION) {
	            selectedFile = fc.getSelectedFile();
	            lbl_imgPreview.setIcon(new ImageIcon(new ImageIcon( selectedFile.getPath() ).getImage().getScaledInstance(250, -1, Image.SCALE_DEFAULT)));
	            lbl_imgPreview.setText( selectedFile.getPath() );
	        } else {
	            //log.append("File Explorer closed by user." + newline);
	        }
	        //log.setCaretPosition(log.getDocument().getLength());

	        //Reset the file chooser for the next time it's shown.
	        fc.setSelectedFile(null);
		} 

	}
	
	
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
					clearForm();
				}else { // Results found, Load them up!
					//clearForm();
					results.first();
					out_tName.setText( results.getString("last_name") +", "+ results.getString("first_name"));
					out_tBirth.setText( results.getString("birthdate") );
					out_tSex.setText( results.getString("sex") );
					out_tSSN.setText( results.getString("ssn") );
				}
			} catch (SQLException e) { e.printStackTrace();}
		}

	}/*actionPerformed()*/
	
	// Helper Functions
	private static void clearForm(){
		for(JLabel lbl : allLabels) lbl.setText(null);
		for(JTextArea lbl : allTextAreas) lbl.setText(null);
		for(JComboBox<?> d : allDropDowns ) d.setSelectedIndex(0);
	}
	
	private static void confirmUpload() {
		if(! search.getText().isBlank() ) {
			int response = JOptionPane.showConfirmDialog(mainContent, // Display pop-up Confirmation to this frame
				    "You are about to apply this image to Patient:\nPID#"+search.getText()+" : "+out_tName.getText(), // The message
				    "Confirm Upload", // The Title Bar
				    JOptionPane.OK_CANCEL_OPTION // Options
				    );
			if( response == JOptionPane.YES_OPTION) { uploadImg(); }
		}else{// They didn't select a patient
			
			JOptionPane.showMessageDialog(mainContent,
			    "You must select a patient to apply the image too.",
			    "Select a Patient",
			    JOptionPane.ERROR_MESSAGE);
			
		}
	}
	
	private static void uploadImg() {
		// String imgPath = copyImg().toString; // Copies file to a local project folder
		String imgPath = selectedFile.toPath().toString(); // For interest of time, we'll just point to the native image folder
		PreparedStatement cli = null;
		String patientID = search.getText().strip().replaceFirst("^0+(?!$)", ""); // Remove whitespace and leading 0's
		// Prepare Datetime
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String datetime = dateFormat.format( new Date() );
		String insertCmd="";

		
		String dupeChk = "SELECT * FROM `ris`.`image` WHERE `url`=?";
		try {
			cli = conn.prepareStatement(dupeChk);
			cli.setString(1, imgPath);
			ResultSet results = cli.executeQuery();
			
			if( results.isBeforeFirst() ) { // Results Found

				 // Display pop-up Confirmation to this frame
				int response = JOptionPane.showConfirmDialog(mainContent,
					    "This image seems to already be in the database.\n"
					    + "Did you want to update its information?",
					    "Update Image Information?",
					    JOptionPane.YES_NO_OPTION);
				if( response == JOptionPane.YES_OPTION) { /* Duplicate Found: UPDATE Image */
					insertCmd = "UPDATE `ris`.`image` "
							+ "SET `patient_id`=?,`modality`=?,`poi`=?,`condition`=?,`observations`=?,`last_modified`=?"
							+ " WHERE `url`=?";
					int qCount = 1; // Because counting question marks is annoying...
					
					cli = conn.prepareStatement(insertCmd);
					cli.setString(qCount++, patientID);
					cli.setString(qCount++, modality.getSelectedItem().toString() );
					cli.setString(qCount++, poi.getSelectedItem().toString() );
					cli.setString(qCount++, condition.getSelectedItem().toString() );
					cli.setString(qCount++, observations.getText() );
					cli.setString(qCount++, datetime);
					cli.executeUpdate(); // commit insert/update
					
					JOptionPane.showMessageDialog(mainContent,
						    "Image successfully updated.");

				}
			}else { // No duplicates found, INSERT Image

				insertCmd = "INSERT INTO `ris`.`image`"
						 + " (`patient_id`,`url`,`modality`,`poi`,`condition`,`observations`,`last_modified`) VALUES"
						 + " (?,?,?,?,?,?,?)";
					int qCount = 1; // Because counting question marks is annoying...
					
						// Notify the database of our intended statement
						cli = conn.prepareStatement(insertCmd);
						// Load up the ?s in the statement
						cli.setString(qCount++, patientID);
						cli.setString(qCount++, imgPath);
						cli.setString(qCount++, modality.getSelectedItem().toString() );
						cli.setString(qCount++, poi.getSelectedItem().toString() );
						cli.setString(qCount++, condition.getSelectedItem().toString() );
						cli.setString(qCount++, observations.getText() );
						cli.setString(qCount++, datetime);
						cli.executeUpdate(); // commit insert/update
						
						JOptionPane.showMessageDialog(mainContent,
							    "Image successfully uploaded.");
				
			} // Ends Else(No dupes found, INSERT Image) block
		} catch (SQLException e1) {e1.printStackTrace();}

	}// uploadImage()
	
	private static Path copyImg() {
		Path src = selectedFile.toPath(); Path dst = Paths.get("src/img/");
	    try{ return Files.copy(src, dst.resolve(selectedFile.getName()), StandardCopyOption.REPLACE_EXISTING); }
	    catch(IOException e){ log("Unexpected Upload Error"); e.printStackTrace(); } return null;
	}
	
	// Shortcut to print to System.out.println()
		public static void log(Object o) {System.out.println(o);}
}
