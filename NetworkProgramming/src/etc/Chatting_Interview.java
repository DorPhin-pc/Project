package Project;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.TextArea;

public class Chatting_Interview extends JFrame {

	static String SendMessage = ".";
	private JPanel contentPane;
	private JTextField Text;
	TextArea Chat = new TextArea();
	static PrintWriter pw = null;
	static boolean endflag = true;
	static String Input="";
	public static void main(String[] args) {
		Chatting_Interview frame = new Chatting_Interview();
		frame.setVisible(endflag);
		Socket sock = null;
		BufferedReader br = null;
		
		
		try{
			sock = new Socket("localhost",8008);
			pw = new PrintWriter(new OutputStreamWriter(sock.getOutputStream()));
			br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			
			BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
			
			pw.println("알바면접");
			pw.flush();
			System.out.println("접속완료 ID : 알바면접생");
			
			
			InputThread it = new InputThread(sock,br);
			it.start();
			while(SendMessage != null) {}

			System.out.println("클라이언트의 접속을 종료합니다.");
		}catch(Exception ex){
			if(endflag)
				System.out.println(ex);
		}finally{
			try{
				if(pw != null)
					pw.close();
			}catch(Exception ex){}
			try{
				if(sock != null)
					sock.close();
			}catch(Exception ex){}
		}
	}
	public Chatting_Interview() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\PCY\\Desktop\\albamon_title.jpg"));
		setTitle("채팅 면접");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Text = new JTextField();
		Text.setBounds(12, 231, 348, 21);
		contentPane.add(Text);
		Text.setColumns(10);
		
		JButton btnNewButton = new JButton("전송");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == btnNewButton) {
					SendMessage = Text.getText();
					Chat.append("나 : " + SendMessage + "\n");
					pw.println(SendMessage);
					pw.flush();
					if(SendMessage.equals("/quit")){
						endflag = false;
					}
					Text.setText("");
				}
			}
		});
		btnNewButton.setBounds(367, 230, 67, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("RE");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == btnNewButton_1) {
					Chat.append(Input + "\n");
				}
			}
		});
		btnNewButton_1.setBounds(367, 101, 67, 49);
		contentPane.add(btnNewButton_1);
		
		Chat.setEnabled(true);
		Chat.setEditable(false);
		Chat.setBounds(10, 10, 350, 215);
		contentPane.add(Chat);
		Chat.setText("접속완료 ID : 알바면접생\n");
	}
	
	public void input(String in) {
		Input = in;
	}
}

class InputThread extends Thread{
	private Socket sock = null;
	private static BufferedReader br = null;
	Chatting_Interview C = new Chatting_Interview();
	public InputThread(Socket sock,BufferedReader br){
		this.sock = sock;
		this.br = br;
	}
	public void run(){
		try{
			String line = null;
			while((line = br.readLine()) != null){
				C.input(line);
				C.Chat.append(line);
			}
		}catch(Exception e){
		}finally{
			try{
				if(br!=null){
					br.close();
				}
			}catch(Exception ex){}
			try{
				if(sock != null){
					sock.close();
				}
			}catch(Exception ex){}
		}
	}
}
