package WindowBuilderTesting;

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

public class Radiologist {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	JLabel lblNewLabel_5;

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
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 899, 583);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Patient name: ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 101, 121, 43);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Patient ID: ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(10, 137, 121, 43);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(713, 429, 160, 77);
		frame.getContentPane().add(btnNewButton);
		
		Choice choice = new Choice();
		choice.setBounds(688, 74, 160, 27);
		frame.getContentPane().add(choice);
		
		JLabel lblNewLabel_2 = new JLabel("Physician List");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(688, 41, 160, 27);
		frame.getContentPane().add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(143, 114, 133, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(143, 150, 133, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Welcome Dr.");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(10, 11, 160, 20);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Comment");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(452, 174, 89, 30);
		frame.getContentPane().add(lblNewLabel_4);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(346, 215, 332, 291);
		frame.getContentPane().add(textPane);
		
		lblNewLabel_5 = new JLabel("");
		Image image = new ImageIcon(this.getClass().getResource("/rightlung.PNG")).getImage();
		lblNewLabel_5.setIcon(new ImageIcon(image));
		lblNewLabel_5.setBounds(10, 215, 317, 291);
		frame.getContentPane().add(lblNewLabel_5);
	}
}
