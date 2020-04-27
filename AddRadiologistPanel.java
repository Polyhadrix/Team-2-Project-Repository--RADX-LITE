import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;

public class AddRadiologistPanel {
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
	private static JLabel lbl_imgPreview, out_rName, out_rSex, out_rBirth, out_rSSN;
	/* List of Labels (Keep updated in body) */
	private static ArrayList<JLabel> allLabels = new ArrayList<>();
	/* Drop-Downs */
	private static JComboBox<String> condition, poi, modality;
	/* All Components: Keep updated list at end of createPanel() */
	private static ArrayList<JTextField> allTextFields = new ArrayList<>();
	private static ArrayList<JComboBox<String>> allDropDowns = new ArrayList<JComboBox<String>>();
	/*Drop-Down Values: myCBox.setModel(new DefaultComboBoxModel<String>(myArr));*/
	private static String[] conditions = {"SELECT","Fracture","Hernia","Embolism","Cancer", "Arterial/Vascular Obstruction"};
	private static String[] pois = {"SELECT","head","neck","chest","upper back","lower back","abdomen","arm - right","arm - left","leg - right","leg - right","foot - right","foot - left"};
	private static String[] modalities = {"SELECT","X-Ray","Magnetic Resonance Imaging","Computed Tomography","Ultrasound","Fluoroscopy","Mammography"};
	private static JTextField textField;
	
	/* WindowBuilder Requires a class constructor to work correctly */
	public AddRadiologistPanel() {
		createPnlRadiologist(mainContent,conn);}
	
	public static JPanel createPnlRadiologist(Container param_contentPane, Connection param_conn) {
		mainContent = param_contentPane;
		conn = param_conn;
		JPanel pnl_radiologist = new JPanel();
		GridBagConstraints gbc_pnl_registration = new GridBagConstraints();
		gbc_pnl_registration.fill = GridBagConstraints.BOTH;
		gbc_pnl_registration.anchor = GridBagConstraints.NORTH;
		gbc_pnl_registration.gridx = 0;
		gbc_pnl_registration.gridy = 0;
		mainContent.add(pnl_radiologist, gbc_pnl_registration);
		GridBagLayout gbl_pnl_technician = new GridBagLayout();
		gbl_pnl_technician.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0};
		gbl_pnl_technician.columnWidths = new int[] {0};
		gbl_pnl_technician.rowHeights = new int[] {0, 0, 0, 260, 0, 0};
		gbl_pnl_technician.columnWeights = new double[]{1.0};
		pnl_radiologist.setLayout(gbl_pnl_technician);

		JPanel row0 = new JPanel();
		GridBagConstraints gbc_row0 = new GridBagConstraints();
		gbc_row0.anchor = GridBagConstraints.NORTH;
		gbc_row0.fill = GridBagConstraints.HORIZONTAL;
		gbc_row0.insets = new Insets(0, 0, 5, 0);
		gbc_row0.gridx = 0;
		gbc_row0.gridy = 0;
		pnl_radiologist.add(row0, gbc_row0);
		GridBagLayout gbl_row0 = new GridBagLayout();
		gbl_row0.columnWidths = new int[] {0};
		gbl_row0.rowHeights = new int[] {0};
		gbl_row0.columnWeights = new double[]{0.0};
		gbl_row0.rowWeights = new double[]{0.0};
		row0.setLayout(gbl_row0);

		JLabel lbl_Title = new JLabel("Radiologist: Upload Image");
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
		pnl_radiologist.add(row1, gbc_row1);
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
		pnl_radiologist.add(row2, gbc_row2);
		GridBagLayout gbl_row2 = new GridBagLayout();
		gbl_row2.columnWidths = new int[] {0};
		gbl_row2.rowHeights = new int[] {0};
		gbl_row2.rowWeights = new double[]{0.0};
		row2.setLayout(gbl_row2);

		JLabel lbl_rName = new JLabel("Patient: ");
		lbl_rName.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_rName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lbl_rName = new GridBagConstraints();
		gbc_lbl_rName.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc_lbl_rName.insets = new Insets(0, 5, 5, 5);
		gbc_lbl_rName.gridx = 0;
		gbc_lbl_rName.gridy = 0;
		row2.add(lbl_rName, gbc_lbl_rName);

		out_rName = new JLabel("_");
		GridBagConstraints gbc_out_rName = new GridBagConstraints();
		gbc_out_rName.anchor = GridBagConstraints.WEST;
		gbc_out_rName.weightx = 1.0;
		gbc_out_rName.insets = new Insets(0, 0, 5, 5);
		gbc_out_rName.gridx = 1;
		gbc_out_rName.gridy = 0;
		row2.add(out_rName, gbc_out_rName);
		JLabel lbl_rSex = new JLabel("Sex: ");
		lbl_rSex.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lbl_rSex = new GridBagConstraints();
		gbc_lbl_rSex.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_rSex.gridx = 2;
		gbc_lbl_rSex.gridy = 0;
		row2.add(lbl_rSex, gbc_lbl_rSex);
		
		out_rSex = new JLabel("_");
		GridBagConstraints gbc_out_rSex = new GridBagConstraints();
		gbc_out_rSex.anchor = GridBagConstraints.WEST;
		gbc_out_rSex.weightx = 1.0;
		gbc_out_rSex.insets = new Insets(0, 0, 5, 5);
		gbc_out_rSex.gridx = 3;
		gbc_out_rSex.gridy = 0;
		row2.add(out_rSex, gbc_out_rSex);

		JLabel lbl_rBirth = new JLabel("Birthdate: ");
		lbl_rBirth.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lbl_rBirth = new GridBagConstraints();
		gbc_lbl_rBirth.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_rBirth.gridx = 4;
		gbc_lbl_rBirth.gridy = 0;
		row2.add(lbl_rBirth, gbc_lbl_rBirth);
		
		out_rBirth = new JLabel("_");
		GridBagConstraints gbc_out_rBirth = new GridBagConstraints();
		gbc_out_rBirth.anchor = GridBagConstraints.WEST;
		gbc_out_rBirth.weightx = 1.0;
		gbc_out_rBirth.insets = new Insets(0, 0, 5, 5);
		gbc_out_rBirth.gridx = 5;
		gbc_out_rBirth.gridy = 0;
		row2.add(out_rBirth, gbc_out_rBirth);

		JLabel lbl_rSSN = new JLabel("SSN: ");
		lbl_rSSN.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lbl_rSSN = new GridBagConstraints();
		gbc_lbl_rSSN.insets = new Insets(0, 0, 5, 0);
		gbc_lbl_rSSN.gridx = 6;
		gbc_lbl_rSSN.gridy = 0;
		row2.add(lbl_rSSN, gbc_lbl_rSSN);
		
		out_rSSN = new JLabel("_");
		GridBagConstraints gbc_out_rSSN = new GridBagConstraints();
		gbc_out_rSSN.anchor = GridBagConstraints.WEST;
		gbc_out_rSSN.weightx = 1.0;
		gbc_out_rSSN.insets = new Insets(0, 0, 0, 5);
		gbc_out_rSSN.gridx = 7;
		gbc_out_rSSN.gridy = 0;
		row2.add(out_rSSN, gbc_out_rSSN);

		JPanel row3 = new JPanel();
		GridBagConstraints gbc_row3 = new GridBagConstraints();
		gbc_row3.weighty = 1.0;
		gbc_row3.anchor = GridBagConstraints.NORTH;
		gbc_row3.fill = GridBagConstraints.BOTH;
		gbc_row3.insets = new Insets(0, 0, 5, 0);
		gbc_row3.gridx = 0;
		gbc_row3.gridy = 3;
		pnl_radiologist.add(row3, gbc_row3);
		GridBagLayout gbl_row3 = new GridBagLayout();
		gbl_row3.columnWidths = new int[] {0, 0, 629};
		gbl_row3.rowHeights = new int[] {0};
		gbl_row3.columnWeights = new double[]{1.0, 1.0, 0.0};
		gbl_row3.rowWeights = new double[]{1.0};
		row3.setLayout(gbl_row3);
		
		JPanel img_pnl = new JPanel();
		GridBagConstraints gbc_img_pnl = new GridBagConstraints();
		gbc_img_pnl.fill = GridBagConstraints.VERTICAL;
		gbc_img_pnl.weighty = 0.0;
		gbc_img_pnl.insets = new Insets(0, 0, 0, 5);
		gbc_img_pnl.gridx = 0;
		gbc_img_pnl.gridy = 0;
		row3.add(img_pnl, gbc_img_pnl);
		GridBagLayout gbl_img_pnl = new GridBagLayout();
		gbl_img_pnl.columnWidths = new int[]{126, 0};
		gbl_img_pnl.rowHeights = new int[]{0, 0, 0, 0};
		gbl_img_pnl.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_img_pnl.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		img_pnl.setLayout(gbl_img_pnl);
		
		JButton btn_ViewImage = new JButton("View Image");
		GridBagConstraints gbc_btn_ViewImage = new GridBagConstraints();
		gbc_btn_ViewImage.insets = new Insets(0, 0, 5, 0);
		gbc_btn_ViewImage.gridx = 0;
		gbc_btn_ViewImage.gridy = 2;
		img_pnl.add(btn_ViewImage, gbc_btn_ViewImage);
		btn_ViewImage.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		lbl_imgPreview = new JLabel("Image Preview");
		lbl_imgPreview.setFont(new Font("Tahoma", Font.ITALIC, 10));
		GridBagConstraints gbc_lbl_imgPreview = new GridBagConstraints();
		gbc_lbl_imgPreview.insets = new Insets(0, 0, 5, 0);
		gbc_lbl_imgPreview.fill = GridBagConstraints.VERTICAL;
		gbc_lbl_imgPreview.weighty = 1.0;
		gbc_lbl_imgPreview.gridx = 0;
		gbc_lbl_imgPreview.gridy = 1;
		img_pnl.add(lbl_imgPreview, gbc_lbl_imgPreview);
		lbl_imgPreview.setHorizontalTextPosition(JLabel.CENTER);
		lbl_imgPreview.setVerticalTextPosition(JLabel.BOTTOM);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 0;
		gbc_comboBox.gridy = 0;
		img_pnl.add(comboBox, gbc_comboBox);
		comboBox.addItem("SELECT");
		
		JPanel note_pnl = new JPanel();
		GridBagConstraints gbc_note_pnl = new GridBagConstraints();
		gbc_note_pnl.insets = new Insets(0, 0, 0, 5);
		gbc_note_pnl.weighty = 1.0;
		gbc_note_pnl.fill = GridBagConstraints.BOTH;
		gbc_note_pnl.gridx = 1;
		gbc_note_pnl.gridy = 0;
		row3.add(note_pnl, gbc_note_pnl);
		GridBagLayout gbl_note_pnl = new GridBagLayout();
		gbl_note_pnl.columnWidths = new int[]{0, 0};
		gbl_note_pnl.rowHeights = new int[]{0, 0, 0, 0};
		gbl_note_pnl.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_note_pnl.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		note_pnl.setLayout(gbl_note_pnl);
		
		JLabel lbl_Observations = new JLabel("Technician's Observations");
		lbl_Observations.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lbl_Observations = new GridBagConstraints();
		gbc_lbl_Observations.insets = new Insets(0, 0, 5, 0);
		gbc_lbl_Observations.gridx = 0;
		gbc_lbl_Observations.gridy = 0;
		note_pnl.add(lbl_Observations, gbc_lbl_Observations);
		
		observations = new JTextArea();
		GridBagConstraints gbc_observations = new GridBagConstraints();
		gbc_observations.insets = new Insets(0, 0, 5, 0);
		gbc_observations.weighty = 1.0;
		gbc_observations.fill = GridBagConstraints.BOTH;
		gbc_observations.gridx = 0;
		gbc_observations.gridy = 1;
		note_pnl.add(observations, gbc_observations);
		
		JPanel note_pnl_1 = new JPanel();
		GridBagConstraints gbc_note_pnl_1 = new GridBagConstraints();
		gbc_note_pnl_1.fill = GridBagConstraints.BOTH;
		gbc_note_pnl_1.gridx = 2;
		gbc_note_pnl_1.gridy = 0;
		row3.add(note_pnl_1, gbc_note_pnl_1);
		GridBagLayout gbl_note_pnl_1 = new GridBagLayout();
		gbl_note_pnl_1.columnWidths = new int[]{0, 0};
		gbl_note_pnl_1.rowHeights = new int[]{0, 0, 0, 0};
		gbl_note_pnl_1.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_note_pnl_1.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		note_pnl_1.setLayout(gbl_note_pnl_1);
		
		JLabel lbl_r_Observations = new JLabel("Radiologist's Observations");
		lbl_r_Observations.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lbl_r_Observations = new GridBagConstraints();
		gbc_lbl_r_Observations.insets = new Insets(0, 0, 5, 0);
		gbc_lbl_r_Observations.gridx = 0;
		gbc_lbl_r_Observations.gridy = 0;
		note_pnl_1.add(lbl_r_Observations, gbc_lbl_r_Observations);
		
		JTextArea observations_1 = new JTextArea();
		GridBagConstraints gbc_observations_1 = new GridBagConstraints();
		gbc_observations_1.weighty = 1.0;
		gbc_observations_1.fill = GridBagConstraints.BOTH;
		gbc_observations_1.insets = new Insets(0, 0, 5, 0);
		gbc_observations_1.gridx = 0;
		gbc_observations_1.gridy = 1;
		note_pnl_1.add(observations_1, gbc_observations_1);
		btn_ViewImage.addActionListener( new chooseImageListener() );

		JPanel row4 = new JPanel();
		GridBagConstraints gbc_row4 = new GridBagConstraints();
		gbc_row4.fill = GridBagConstraints.HORIZONTAL;
		gbc_row4.anchor = GridBagConstraints.SOUTH;
		gbc_row4.gridx = 0;
		gbc_row4.gridy = 4;
		pnl_radiologist.add(row4, gbc_row4);
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
		pnl_radiologist.add(row5, gbc_row5);
		GridBagLayout gbl_row5 = new GridBagLayout();
		gbl_row5.columnWidths = new int[] {0, 0, 0};
		gbl_row5.rowHeights = new int[] {0};
		gbl_row5.columnWeights = new double[]{1.0, 1.0, 0.0};
		gbl_row5.rowWeights = new double[]{0.0};
		row5.setLayout(gbl_row5);
		
		JButton btn_uploadImage = new JButton("Upload Image");
		btn_uploadImage.setFont(new Font("Tahoma", Font.PLAIN, 14));
		//btn_submitPatient.addActionListener(new addPatientListener() );
		GridBagConstraints gbc_btn_uploadImage = new GridBagConstraints();
		gbc_btn_uploadImage.insets = new Insets(0, 5, 0, 5);
		gbc_btn_uploadImage.gridx = 0;
		gbc_btn_uploadImage.gridy = 0;
		row5.add(btn_uploadImage, gbc_btn_uploadImage);
		
		JButton btnClearForm = new JButton("Clear Form");
		btnClearForm.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent clearFormButtonPressed) { clearForm(); } });
		btnClearForm.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_btnClearForm = new GridBagConstraints();
		gbc_btnClearForm.anchor = GridBagConstraints.WEST;
		gbc_btnClearForm.insets = new Insets(0, 50, 0, 5);
		gbc_btnClearForm.gridx = 1;
		gbc_btnClearForm.gridy = 0;
		row5.add(btnClearForm, gbc_btnClearForm);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 0;
		row5.add(textField, gbc_textField);
		textField.setColumns(10);

		allLabels.addAll( Arrays.asList(out_rName,out_rSex,out_rBirth,out_rSSN) );

		return pnl_radiologist;
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
					clearForm();
					results.first();
					out_rName.setText( results.getString("last_name") +", "+ results.getString("first_name"));
					out_rBirth.setText( results.getString("birthdate") );
					out_rSex.setText( results.getString("sex") );
					out_rSSN.setText( results.getString("ssn") );
				}
			} catch (SQLException e) { e.printStackTrace();}
		}

	}/*actionPerformed()*/
	
	// Helper Functions
	private static void clearForm(){ for(JLabel lbl : allLabels) lbl.setText(null); }
	
	private static void copyImgToAppFolder() throws IOException {
		
	    Path src = selectedFile.toPath();
	    Path dst = Paths.get("src/img/");
	    try
	    { 
	       Files.copy(src, dst.resolve(selectedFile.getName()), StandardCopyOption.REPLACE_EXISTING);
	       log("File Copied");
	    }
	    catch(IOException e){ e.printStackTrace(); }
	}
	
	// Shortcut to print to System.out.println()
		public static void log(Object o) {System.out.println(o);}
}
