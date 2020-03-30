import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.Choice;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JTabbedPane;
import java.awt.Canvas;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLayeredPane;

public class Radiologist {

	private JFrame frame;
	private JTextField patientIDInput;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Radiologist window = new Radiologist();
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
	public Radiologist() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 956, 633);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextPane txtpnWelcomeBack = new JTextPane();
		txtpnWelcomeBack.setEditable(false);
		txtpnWelcomeBack.setText("Welcome Back, Doctor");
		txtpnWelcomeBack.setBounds(10, 10, 160, 19);
		frame.getContentPane().add(txtpnWelcomeBack);
		
		JTextPane txtpnDoctor = new JTextPane();
		txtpnDoctor.setEditable(false);
		txtpnDoctor.setBounds(160, 10, 77, 19);
		frame.getContentPane().add(txtpnDoctor);
		
		JLabel lblPatientIDSearch = new JLabel("Search by Patient ID:");
		lblPatientIDSearch.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPatientIDSearch.setBounds(10, 54, 152, 27);
		frame.getContentPane().add(lblPatientIDSearch);
		
		JLabel lblFileViewSelection = new JLabel("File View Selection:");
		lblFileViewSelection.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFileViewSelection.setBounds(10, 92, 160, 27);
		frame.getContentPane().add(lblFileViewSelection);
		
		patientIDInput = new JTextField();
		patientIDInput.setBounds(163, 60, 66, 20);
		frame.getContentPane().add(patientIDInput);
		patientIDInput.setColumns(10);
		
		JLabel lblComments = new JLabel("Diagnosis and Analysis:");
		lblComments.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblComments.setBounds(435, 142, 160, 30);
		frame.getContentPane().add(lblComments);
		
		JTextPane commentInput = new JTextPane();
		commentInput.setBounds(435, 171, 438, 296);
		frame.getContentPane().add(commentInput);
		
		JComboBox imageSelectDropdown = new JComboBox();
		imageSelectDropdown.setBounds(146, 97, 160, 21);
		frame.getContentPane().add(imageSelectDropdown);
		
		JButton btnNewButton = new JButton("Submit and Send");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(715, 477, 160, 30);
		frame.getContentPane().add(btnNewButton);
		
		JLabel label = new JLabel("");
		label.setBounds(10, 146, 400, 400);
		frame.getContentPane().add(label);
		
		JLabel lblPhysicianName = new JLabel("Physician's Name:");
		lblPhysicianName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPhysicianName.setBounds(386, 92, 126, 30);
		frame.getContentPane().add(lblPhysicianName);
		
		JTextPane physicianNameOutput = new JTextPane();
		physicianNameOutput.setBounds(509, 100, 228, 19);
		frame.getContentPane().add(physicianNameOutput);
		
		JButton btnSearchByPatientID = new JButton("Search");
		btnSearchByPatientID.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnSearchByPatientID.setBounds(239, 59, 66, 20);
		frame.getContentPane().add(btnSearchByPatientID);
		
		JLabel lblSuccess = new JLabel("Success!");
		lblSuccess.setForeground(new Color(34, 139, 34));
		lblSuccess.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSuccess.setBounds(809, 10, 66, 27);
		frame.getContentPane().add(lblSuccess);
		
		JLabel lblFailure = new JLabel("Failure--Please search again");
		lblFailure.setForeground(new Color(165, 42, 42));
		lblFailure.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFailure.setBounds(576, 11, 223, 27);
		frame.getContentPane().add(lblFailure);
		
		JPanel confirmCancelPanel = new JPanel();
		confirmCancelPanel.setBounds(435, 506, 270, 30);
		frame.getContentPane().add(confirmCancelPanel);
		confirmCancelPanel.setLayout(null);
		confirmCancelPanel.setVisible(false);
		
		JLabel lblPleaseConfirm = new JLabel("Please Confirm:");
		lblPleaseConfirm.setForeground(Color.RED);
		lblPleaseConfirm.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPleaseConfirm.setBounds(33, 4, 95, 17);
		confirmCancelPanel.add(lblPleaseConfirm);
		
		JButton btnYes = new JButton("YES");
		btnYes.setEnabled(false);
		btnYes.setBounds(126, 3, 59, 21);
		confirmCancelPanel.add(btnYes);
		
		JButton btnNo = new JButton("NO");
		btnNo.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent CancelCancellation)
			{
				btnYes.setEnabled(false);
				btnNo.setEnabled(false);
				confirmCancelPanel.setVisible(false);
			}
		});
		btnNo.setEnabled(false);
		btnNo.setBounds(195, 3, 65, 21);
		confirmCancelPanel.add(btnNo);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent Cancelled)
			{
				btnYes.setEnabled(true);
				btnNo.setEnabled(true);
				confirmCancelPanel.setVisible(true);
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCancel.setBounds(435, 478, 84, 27);
		frame.getContentPane().add(btnCancel);
		
		JLabel lblPhysicianName_1 = new JLabel("Physician's Name:");
		lblPhysicianName_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPhysicianName_1.setBounds(386, 51, 126, 30);
		frame.getContentPane().add(lblPhysicianName_1);
		
		JTextPane physicianNameOutput_1 = new JTextPane();
		physicianNameOutput_1.setBounds(509, 59, 228, 19);
		frame.getContentPane().add(physicianNameOutput_1);
	}
}
