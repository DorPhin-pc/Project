package Project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import java.awt.List;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;

public class GUI_2 extends JFrame {

	private JPanel contentPane;
	String Tour_name;
	String End;
	GUI_3 fr = new GUI_3(End);
	public GUI_2(String loc1,String loc2) {
		setTitle("\uAD00\uAD11\uC9C0");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 216, 321);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		List list = new List();
		list.setBackground(Color.WHITE);
		list.setBounds(10, 10, 180, 229);
		contentPane.add(list);
		for(int i=0;i<DBConnect.Tour(loc1,loc2).size();i++) {
			list.add(DBConnect.Tour(loc1,loc2).get(i));
		}
		
		JButton btnNewButton = new JButton("\uC815\uBCF4 \uBCF4\uAE30");
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String End = list.getSelectedItem();
				Tour_name = list.getSelectedItem().toString();
				fr.textArea.setText("");
				for(int i = 0; i < Parser.goodsSearch(loc1,loc2,Tour_name,"","tour").size();i++) 
					fr.textArea.append(Parser.goodsSearch(loc1,loc2,Tour_name,"","tour").get(i).getKeyword() + "\n");
				GUI_3 frame = new GUI_3(End);
				frame.setVisible(true);
				frame.setLocation(550, 420);
			}
		});
		btnNewButton.setBounds(20, 245, 153, 33);
		contentPane.add(btnNewButton);
	}
}
