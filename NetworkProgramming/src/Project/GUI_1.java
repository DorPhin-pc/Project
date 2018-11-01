package Project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.Component;
import java.awt.Cursor;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class GUI_1 extends JFrame {

	private DefaultListModel model = new DefaultListModel();
	private JPanel contentPane;
	boolean enable = false;
	JLabel wind = new JLabel("");
	JLabel sp = new JLabel("");
	JLabel Dust = new JLabel("");
	JLabel Tem = new JLabel("");
	JLabel wea = new JLabel("");
	JLabel sp_L = new JLabel("\uC2B5\uB3C4");
	JLabel Dust_L = new JLabel("\uBBF8\uC138\uBA3C\uC9C0");
	JLabel wind_L = new JLabel("\uD48D\uC18D");
	private final JButton button = new JButton("\uAD00\uAD11\uC9C0 \uBCF4\uAE30");
	String loc;
	String loc_2;
	JComboBox comboBox_2;
	JComboBox comboBox_1;
	private final JLabel lblNewLabel = new JLabel("");
	public static void main(String[] args) {
		GUI_1 frame = new GUI_1();
		frame.setVisible(true);
	}

	public GUI_1() {
		setTitle("\uC9C0\uC5ED\uBCC4 \uB0A0\uC528 \uBCF4\uAE30");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 413, 435);
		contentPane = new JPanel();
		contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		contentPane.setAlignmentX(Component.RIGHT_ALIGNMENT);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		
		comboBox.setToolTipText("\uC2DC/\uB3C4");
		comboBox.setBounds(23, 35, 101, 32);
		contentPane.add(comboBox);
		
		for(int i=0;i<DBConnect.Location().size();i++) {
			comboBox.addItem(DBConnect.Location().get(i));
		}
		
		Tem.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå ExtraBold", Font.PLAIN, 30));
		Tem.setBounds(239, 121, 92, 86);
		contentPane.add(Tem);
		
		sp_L.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå ExtraBold", Font.PLAIN, 15));
		sp_L.setHorizontalAlignment(SwingConstants.CENTER);
		sp_L.setBounds(161, 270, 83, 15);
		contentPane.add(sp_L);
		
		Dust_L.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå ExtraBold", Font.PLAIN, 15));
		Dust_L.setHorizontalAlignment(SwingConstants.CENTER);
		Dust_L.setBounds(23, 270, 101, 15);
		contentPane.add(Dust_L);
		
		wind_L.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå ExtraBold", Font.PLAIN, 15));
		wind_L.setHorizontalAlignment(SwingConstants.CENTER);
		wind_L.setBounds(283, 270, 82, 15);
		contentPane.add(wind_L);
		
		Dust.setHorizontalAlignment(SwingConstants.CENTER);
		Dust.setFont(new Font("³ª´®½ºÄù¾î ExtraBold", Font.PLAIN, 15));
		Dust.setBounds(23, 308, 101, 15);
		contentPane.add(Dust);
		
		sp.setHorizontalAlignment(SwingConstants.CENTER);
		sp.setFont(new Font("³ª´®½ºÄù¾î ExtraBold", Font.PLAIN, 15));
		sp.setBounds(161, 308, 83, 15);
		contentPane.add(sp);
		
		wind.setHorizontalAlignment(SwingConstants.CENTER);
		wind.setFont(new Font("³ª´®½ºÄù¾î ExtraBold", Font.PLAIN, 15));
		wind.setBounds(283, 308, 82, 15);
		contentPane.add(wind);
	
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(198, 35, 125, 32);
		contentPane.add(comboBox_2);
		
		JButton button_1 = new JButton("\uC124\uC815");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loc = comboBox.getSelectedItem().toString();
				contentPane.add(comboBox_2);
				for(int i=0;i<DBConnect.Location_2(loc).size();i++) {
					comboBox_2.addItem(DBConnect.Location_2(loc).get(i));
				}
			}
		});
		
		button_1.setFont(new Font("³ª´®°íµñ ExtraBold", Font.PLAIN, 15));
		button_1.setBounds(122, 35, 64, 32);
		contentPane.add(button_1);

		JButton btnNewButton = new JButton("\uAC80\uC0C9");
		btnNewButton.setFont(new Font("³ª´®°íµñ ExtraBold", Font.PLAIN, 15));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				loc_2 = comboBox_2.getSelectedItem().toString();
				enable = true;
				Tem.setText(Parser.goodsSearch(loc,loc_2,"","","weather").get(0).getKeyword());
				wind.setText(Parser.goodsSearch(loc,loc_2,"","","weather").get(1).getKeyword());
				sp.setText(Parser.goodsSearch(loc,loc_2,"","","weather").get(2).getKeyword());
				Dust.setText(Parser.goodsSearch(loc,loc_2,"","","weather").get(3).getKeyword());
				wea.setText(Parser.goodsSearch(loc, loc_2, "", "", "weather").get(4).getKeyword());
				String weather = Parser.goodsSearch(loc, loc_2, "", "", "weather").get(4).getKeyword();
				wea.setVisible(enable);
				sp_L.setVisible(enable);
				Dust_L.setVisible(enable);
				wind_L.setVisible(enable);
				button.setVisible(enable);
				lblNewLabel.setVisible(enable);
				if(weather.equals("¸¼À½") || weather.equals("¸Å¿ì¸¼À½"))
					lblNewLabel.setIcon(new ImageIcon("C:\\Users\\PCY\\Desktop\\¸¼À½.png"));
				else if(weather.equals("Èå¸²") || weather.equals("Á¶±ÝÈå¸²") || weather.equals("¸Å¿ìÈå¸²"))
					lblNewLabel.setIcon(new ImageIcon("C:\\Users\\PCY\\Desktop\\Èå¸².png"));
				else if(weather.equals("ºñ") || weather.equals("¼Ò³ª±â") || weather.equals("Èå¸®°í"))
					lblNewLabel.setIcon(new ImageIcon("C:\\Users\\PCY\\Desktop\\ºñ.png"));
				else
					lblNewLabel.setIcon(new ImageIcon("C:\\Users\\PCY\\Desktop\\³ª¸ÓÁö.png"));

			}
		});
		btnNewButton.setBounds(321, 35, 64, 32);
		contentPane.add(btnNewButton);
		
		button.setFont(new Font("³ª´®¹Ù¸¥°íµñ Light", Font.PLAIN, 13));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUI_2 frame = new GUI_2(loc,loc_2);
				frame.setVisible(true);
				frame.setLocation(550, 100);
			}
		});
		button.setBounds(260, 355, 125, 32);
		
		contentPane.add(button);
		wea.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå ExtraBold", Font.PLAIN, 15));
		
		wea.setBounds(249, 185, 92, 25);
		contentPane.add(wea);
		
		lblNewLabel.setBounds(80, 104, 147, 141);
		
		contentPane.add(lblNewLabel);
		lblNewLabel.setVisible(enable);
		wea.setVisible(enable);
		sp_L.setVisible(enable);
		Dust_L.setVisible(enable);
		wind_L.setVisible(enable);
		button.setVisible(enable);
		
	}
}
