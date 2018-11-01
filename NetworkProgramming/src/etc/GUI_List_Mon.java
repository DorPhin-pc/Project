package etc;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Parser;

import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JLayeredPane;
import javax.swing.JDesktopPane;
import java.awt.List;
import java.awt.Canvas;
import java.awt.Desktop;

import javax.swing.JList;
import javax.swing.JScrollBar;
import java.awt.Button;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Font;

public class GUI_List_Mon extends JFrame {
	private JPanel contentPane;
	JList List = new JList(new DefaultListModel());
	JList list = new JList(new DefaultListModel());
	
	DefaultListModel DLM_Job = new DefaultListModel();
	DefaultListModel DLM_Link = new DefaultListModel();
	DefaultListModel DLM_Loc = new DefaultListModel();
	
	public GUI_List_Mon(String Loc, String Job) {
		setTitle("\uC120\uD0DD\uC9C0\uC5ED \uC54C\uBC14\uBAA9\uB85D");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		List.setBounds(107, 10, 459, 296);
		contentPane.add(List);
		
		JButton btnNewButton = new JButton("\uC54C\uBC14\r \n\uC815\uBCF4");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == btnNewButton) {
					String URL = (String) DLM_Link.getElementAt(List.getSelectedIndex());
					try {
						Desktop.getDesktop().browse(new URI(URL));
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (URISyntaxException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnNewButton.setBounds(578, 176, 94, 43);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\uCC44\uD305 \uBA74\uC811");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == btnNewButton_1)
					new MultiChatClient();
			}
		});
		btnNewButton_1.setBounds(578, 263, 94, 43);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_3 = new JButton("1");
		btnNewButton_3.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå Bold", Font.PLAIN, 12));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == btnNewButton_3) {
					DLM_Job.removeAllElements();
					DLM_Loc.removeAllElements();
					for(int i = 0; i < Parser.goodsSearch(Loc,Job,1).size();i++) {
							DLM_Loc.addElement(Parser.goodsSearch(Loc,Job,1).get(i).getLoc());
							DLM_Job.addElement(Parser.goodsSearch(Loc,Job,1).get(i).getKeyword());
							DLM_Link.addElement(Parser.goodsSearch(Loc,Job,1).get(i).getLink());
					}
				}
			}
		});
		btnNewButton_3.setBounds(12, 316, 41, 36);
		contentPane.add(btnNewButton_3);
		
		JButton button = new JButton("2");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == button) {
					DLM_Job.removeAllElements();
					DLM_Loc.removeAllElements();
					for(int i = 0; i < Parser.goodsSearch(Loc,Job,2 ).size();i++) {
							DLM_Loc.addElement(Parser.goodsSearch(Loc,Job,2 ).get(i).getLoc());
							DLM_Job.addElement(Parser.goodsSearch(Loc,Job,2 ).get(i).getKeyword());
							DLM_Link.addElement(Parser.goodsSearch(Loc,Job,2 ).get(i).getLink());
					}
				}
			}
		});
		button.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå Bold", Font.PLAIN, 12));
		button.setBounds(65, 316, 41, 36);
		contentPane.add(button);
		
		JButton button_1 = new JButton("3");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == button_1) {
					DLM_Job.removeAllElements();
					DLM_Loc.removeAllElements();
					for(int i = 0; i < Parser.goodsSearch(Loc,Job,3 ).size();i++) {
							DLM_Loc.addElement(Parser.goodsSearch(Loc,Job,3 ).get(i).getLoc());
							DLM_Job.addElement(Parser.goodsSearch(Loc,Job,3 ).get(i).getKeyword());
							DLM_Link.addElement(Parser.goodsSearch(Loc,Job,3 ).get(i).getLink());
					}
				}
			}
		});
		button_1.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå Bold", Font.PLAIN, 12));
		button_1.setBounds(118, 316, 41, 36);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("4");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == button_2) {
					DLM_Job.removeAllElements();
					DLM_Loc.removeAllElements();
					for(int i = 0; i < Parser.goodsSearch(Loc,Job,4 ).size();i++) {
							DLM_Loc.addElement(Parser.goodsSearch(Loc,Job,4 ).get(i).getLoc());
							DLM_Job.addElement(Parser.goodsSearch(Loc,Job,4 ).get(i).getKeyword());
							DLM_Link.addElement(Parser.goodsSearch(Loc,Job,4 ).get(i).getLink());
					}
				}
			}
		});
		button_2.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå Bold", Font.PLAIN, 12));
		button_2.setBounds(171, 316, 41, 36);
		contentPane.add(button_2);
		
		JButton btnP = new JButton("5");
		btnP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == btnP) {
					DLM_Job.removeAllElements();
					DLM_Loc.removeAllElements();
					for(int i = 0; i < Parser.goodsSearch(Loc,Job,5 ).size();i++) {
							DLM_Loc.addElement(Parser.goodsSearch(Loc,Job,5 ).get(i).getLoc());
							DLM_Job.addElement(Parser.goodsSearch(Loc,Job,5 ).get(i).getKeyword());
							DLM_Link.addElement(Parser.goodsSearch(Loc,Job,5 ).get(i).getLink());
					}
				}
			}
		});
		btnP.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå Bold", Font.PLAIN, 12));
		btnP.setBounds(224, 316, 41, 36);
		contentPane.add(btnP);
		
		JButton button_4 = new JButton("6");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == button_4) {
					DLM_Job.removeAllElements();
					DLM_Loc.removeAllElements();
					for(int i = 0; i < Parser.goodsSearch(Loc,Job,6 ).size();i++) {
							DLM_Loc.addElement(Parser.goodsSearch(Loc,Job,6 ).get(i).getLoc());
							DLM_Job.addElement(Parser.goodsSearch(Loc,Job,6 ).get(i).getKeyword());
							DLM_Link.addElement(Parser.goodsSearch(Loc,Job,6 ).get(i).getLink());
					}
				}
			}
		});
		button_4.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå Bold", Font.PLAIN, 12));
		button_4.setBounds(277, 316, 41, 36);
		contentPane.add(button_4);
		
		JButton button_5 = new JButton("7");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == button_5) {
					DLM_Job.removeAllElements();
					DLM_Loc.removeAllElements();
					for(int i = 0; i < Parser.goodsSearch(Loc,Job,7 ).size();i++) {
							DLM_Loc.addElement(Parser.goodsSearch(Loc,Job, 7 ).get(i).getLoc());
							DLM_Job.addElement(Parser.goodsSearch(Loc,Job,7 ).get(i).getKeyword());
							DLM_Link.addElement(Parser.goodsSearch(Loc,Job,7 ).get(i).getLink());
					}
				}
			}
		});
		button_5.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå Bold", Font.PLAIN, 12));
		button_5.setBounds(330, 316, 41, 36);
		contentPane.add(button_5);
		
		JButton button_3 = new JButton("8");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == button_3) {
					DLM_Job.removeAllElements();
					DLM_Loc.removeAllElements();
					for(int i = 0; i < Parser.goodsSearch(Loc,Job,8 ).size();i++) {
							DLM_Loc.addElement(Parser.goodsSearch(Loc,Job,8 ).get(i).getLoc());
							DLM_Job.addElement(Parser.goodsSearch(Loc,Job,8 ).get(i).getKeyword());
							DLM_Link.addElement(Parser.goodsSearch(Loc,Job,8 ).get(i).getLink());
					}
				}
			}
		});
		button_3.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå Bold", Font.PLAIN, 12));
		button_3.setBounds(383, 316, 41, 36);
		contentPane.add(button_3);
		
		JButton button_6 = new JButton("9");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == button_6) {
					DLM_Job.removeAllElements();
					DLM_Loc.removeAllElements();
					for(int i = 0; i < Parser.goodsSearch(Loc,Job,9 ).size();i++) {
							DLM_Loc.addElement(Parser.goodsSearch(Loc,Job,9 ).get(i).getLoc());
							DLM_Job.addElement(Parser.goodsSearch(Loc,Job,9 ).get(i).getKeyword());
							DLM_Link.addElement(Parser.goodsSearch(Loc,Job,9 ).get(i).getLink());
					}
				}
			}
		});
		button_6.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå Bold", Font.PLAIN, 12));
		button_6.setBounds(436, 316, 41, 36);
		contentPane.add(button_6);
		
		JButton button_7 = new JButton("10");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == button_7) {
					DLM_Job.removeAllElements();
					for(int i = 0; i < Parser.goodsSearch(Loc,Job,10).size();i++) {
							DLM_Loc.addElement(Parser.goodsSearch(Loc,Job,10).get(i).getLoc());
							DLM_Job.addElement(Parser.goodsSearch(Loc,Job,10).get(i).getKeyword());
							DLM_Link.addElement(Parser.goodsSearch(Loc,Job,10).get(i).getLink());
					}
				}
			}
		});
		button_7.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå Bold", Font.PLAIN, 12));
		button_7.setBounds(489, 316, 54, 36);
		contentPane.add(button_7);
		
		list.setBounds(12, 10, 83, 296);
		contentPane.add(list);
		
		DLM_Loc = (DefaultListModel)list.getModel();
		DLM_Job = (DefaultListModel)List.getModel();
	}
}
