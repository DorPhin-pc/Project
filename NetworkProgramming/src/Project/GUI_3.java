package Project;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Canvas;
import javax.swing.ImageIcon;

public class GUI_3 extends JFrame {

	private JPanel contentPane;
	static JTextArea textArea = new JTextArea();
	private JTextField textField;
	String start;

	public GUI_3(String End) {
		setTitle("\uAD00\uAD11\uC9C0 \uC815\uBCF4");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 676, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		textArea.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå ExtraBold", Font.PLAIN, 15));
		textArea.setEditable(false);
		textArea.setBounds(222, 8, 430, 240);
		contentPane.add(textArea);
		
		textField = new JTextField();
		textField.setBounds(312, 255, 141, 47);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("\uAC00\uB294 \uAE38 \uCC3E\uAE30");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource() == btnNewButton) {
					start = textField.getText();
				}
				String url_road = "https://www.google.com/maps/dir/" + start + "/" + End;
				
				String URL = url_road;
				try {
					Desktop.getDesktop().browse(new URI(URL));
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(465, 255, 187, 47);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(222, 10, 430, 238);
		contentPane.add(scrollPane);
		
		JLabel lblNewLabel = new JLabel("\uCD9C\uBC1C\uC9C0 :");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(232, 255, 85, 47);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\PCY\\Desktop\\KakaoTalk_20180610_220734912.png"));
		lblNewLabel_1.setBounds(12, 10, 198, 238);
		contentPane.add(lblNewLabel_1);
		
		
	}
}
