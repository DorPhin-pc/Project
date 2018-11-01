package etc;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Icon;

public class GUI_Setting extends JFrame {

	private JPanel contentPane;
	public JTextField textField;
	public JTextField textField_1;
	String Job;
	String Location;
	
	public GUI_Setting() {
		setTitle("\uC124\uC815 \uCC3D");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\uC9C0\uC5ED\uBA85 : ");
		label.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå ExtraBold", Font.PLAIN, 20));
		label.setBounds(58, 45, 77, 29);
		contentPane.add(label);
		
		JLabel lblNewLabel = new JLabel("\uC54C\uBC14 \uAC80\uC0C9 : ");
		lblNewLabel.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå ExtraBold", Font.PLAIN, 20));
		lblNewLabel.setBounds(35, 94, 94, 43);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(138, 102, 159, 29);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(138, 46, 159, 29);
		contentPane.add(textField_1);
		
		JButton btnNewButton = new JButton((Icon) null);
		btnNewButton.setText("\uCC3E\uAE30");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Location = textField_1.getText();
				Job = textField.getText();
				GUI_List_Mon F = new GUI_List_Mon(Location,Job);
				F.setVisible(true);
				F.setLocation(100, 350);
				
			}
		});
		btnNewButton.setBounds(138, 151, 101, 29);
		contentPane.add(btnNewButton);
	}
	
	public String ReturnData() {
		return Job;
	}
}
