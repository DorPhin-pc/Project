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

public class Chatting_Owner extends JFrame {

	static String SendMessage = ".";
	private JPanel contentPane;
	private JTextField Text;
	TextArea Chat = new TextArea();
	static PrintWriter pw = null;
	static boolean endflag = true;
	static String Input="";
	public static void main(String[] args) {
		Chatting_Owner frame = new Chatting_Owner();
		frame.setVisible(endflag);
		Socket sock = null;
		BufferedReader br = null;
		
		
		try{
			sock = new Socket("localhost",8008);
			pw = new PrintWriter(new OutputStreamWriter(sock.getOutputStream()));
			br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			
			BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
			
			pw.println("사장님");
			pw.flush();
			System.out.println("접속완료 ID : 사장님");
			
			
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
	public Chatting_Owner() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\PCY\\Desktop\\albamon_title.jpg"));
		setTitle("채팅 면접(사장님)");
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
		
		Chat.setEnabled(false);
		Chat.setBounds(10, 10, 350, 215);
		contentPane.add(Chat);
		Chat.setText("접속완료 ID : 사장님\n");
		Chat.append(Input);
		
	}
	public void input(String in) {
		Input = in;
	}
}



