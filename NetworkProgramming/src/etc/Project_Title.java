package etc;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JDesktopPane;
import javax.swing.SwingConstants;
import javax.swing.Icon;
import javax.swing.JLabel;
import java.awt.Font;

public class Project_Title extends JFrame {
	private JPanel contentPane;
	public static void main(String[] args) {
		Project_Title frame = new Project_Title();
		frame.setVisible(true);
	}

	public Project_Title() {
		setTitle("\uC54C\uBC14 \uCC44\uD305 \uD504\uB85C\uADF8\uB7A8");
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(392,220);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnNewButton = new JButton(new ImageIcon("C:\\Users\\PCY\\Desktop\\NetworkProject\\ablamonlogo.jpg"));
		btnNewButton.setBounds(106, 59, 154, 100);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == btnNewButton) {
					GUI_Setting F = new GUI_Setting();
					F.setVisible(true);
					F.setLocation(500,100);
				}
			}
		});
		
		contentPane.setLayout(null);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("\uC54C\uBC14\uBAAC");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå Bold", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(106, 29, 154, 20);
		contentPane.add(lblNewLabel_1);
	}
}
