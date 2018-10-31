package 임베디드;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import org.ws4d.coap.Constants;
import org.ws4d.coap.connection.BasicCoapChannelManager;
import org.ws4d.coap.connection.BasicCoapClientChannel;
import org.ws4d.coap.interfaces.CoapChannelManager;
import org.ws4d.coap.interfaces.CoapClient;
import org.ws4d.coap.interfaces.CoapClientChannel;
import org.ws4d.coap.interfaces.CoapRequest;
import org.ws4d.coap.interfaces.CoapResponse;
import org.ws4d.coap.messages.CoapRequestCode;
public class Client_UI extends JFrame implements ActionListener,CoapClient{
	private String SERVER_ADDRESS;
	private int PORT; 
	
	static double Warning_Temperature=100;
	
	static int counter = 0;
	private CoapChannelManager channelManager = null;
	private BasicCoapClientChannel clientChannel = null;
	private Random tokenGen = null;
	
	static String responseData;
	static String First_Minute;
	static String Second_Minute;
	static String Third_Minute;
	
	static String Warning_Temp;
	//--메인 프레임
	public static JLabel ONOFF = new JLabel("OFF");
	
	public static JLabel Set_Label_1 = new JLabel("1 단계");
	public static JLabel Minute_Label_1 = new JLabel("분");
	public static JTextField Text_1 = new JTextField("0");
	public static JLabel Power1 = new JLabel("불 온도(℃)");
	public static JTextField power_Text1 = new JTextField("0");
	
	public static JLabel Set_Label_2 = new JLabel("2 단계");
	public static JTextField Text_2 = new JTextField("0");
	public static JLabel Minute_Label_2 = new JLabel("분");
	public static JLabel Power2 = new JLabel("불 온도(℃)");
	public static JTextField power_Text2 = new JTextField("0");
	
	public static JLabel Set_Label_3 = new JLabel("3 단계");
	public static JTextField Text_3 = new JTextField("0");
	public static JLabel Minute_Label_3 = new JLabel("분");
	public static JLabel Power3 = new JLabel("불 온도(℃)");
	public static JTextField power_Text3 = new JTextField("0");
	
	public static JButton Setting_Button = new JButton("설정");
	public static JButton Start_Button = new JButton("시작"); 
	public static JButton turn_OFF = new JButton("OFF");
	public static JButton Reset_button = new JButton("RESET");
	
	public static JTextArea response_Text_main = new JTextArea("Connect Fail");
	public static JLabel Start1 = new JLabel("1단계 : 0 초");
	public static JLabel Start2 = new JLabel("2단계 : 0 초");
	public static JLabel Start3 = new JLabel("3단계 : 0 초");
	public static JLabel Total_timer = new JLabel("total : 0 초");
	public static double Total_Second;
	public static JLabel Warning_Label = new JLabel("위험 온도 : ");
	public static JTextField Warning_Text = new JTextField("100");
	//--서브 프레임
	JFrame F = new JFrame("Induction");
	JLabel grade1 = new JLabel("- 1 -");
	JButton Exitbutton = new JButton(new ImageIcon("Exit.png"));

	public static JLabel Firsttime_Label = new JLabel();
	public static JLabel Secondtime_Label = new JLabel();
	public static JLabel Thirdtime_Label = new JLabel();
	public static JLabel timer =null;
	public static JLabel sub_Total_time = new JLabel("0");
	public static JLabel response_Text_sub = new JLabel("0");
	ImageIcon i1 = new ImageIcon("Induction.png");
	Image image1 = i1.getImage();
	
	public static String FirstTime_String = "1";
	public static String SecondTime_String = "1";
	public static String ThirdTime_String = "1";
	public static boolean ToF=false;
	public static boolean ToF_1 = false;
	public static double FirstTime,SecondTime,ThirdTime;
	public static int First_fire,Second_fire,Third_fire;
	public static double sub_Total_Second;
	
	//완료 프레임
	JFrame Complete_F = new JFrame();
	JLabel Complete_L = new JLabel("요리가 완료되었습니다.");
	JButton Complete_B = new JButton("확인");
	static double FirstMinute=0;
	static double SecondMinute=0;
	static double ThirdMinute=0;
	
	public Client_UI(String server_address,int port){
		super();
		this.SERVER_ADDRESS = server_address;
		this.PORT = port;
		this.channelManager = BasicCoapChannelManager.getInstance();
		this.tokenGen = new Random();
	}
	
	public boolean connect(){
		try {
			clientChannel = (BasicCoapClientChannel) channelManager.connect(this, InetAddress.getByName(SERVER_ADDRESS), PORT);
		} catch( UnknownHostException e ){
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
	public CoapRequest createRequest( boolean reliable, CoapRequestCode reqCode ) {
		return clientChannel.createRequest( reliable, reqCode );
	}
	
	public byte[] generateRequestToken(int tokenLength ){
		byte[] token = new byte[tokenLength];
		tokenGen.nextBytes(token);
		return token;
	}
	
	public void Complete_Frame(){
		Complete_F.setLocation(300, 300);
		Complete_F.setSize(300, 150);
		Complete_F.setResizable(false);
		Complete_F.setLayout(null);
		Complete_F.setVisible(ToF_1);
		ToF_1 = false;
		
		Complete_L.setFont(new Font("Serif",Font.BOLD,20));
		Complete_L.setBounds(20,30,250,30);
		Complete_F.add(Complete_L);
		
		Complete_B.setBounds(90,70,100,30);
		Complete_F.add(Complete_B);
		Complete_B.addActionListener(this);
	}
	
	public void UI_Sub_Frame(){
		
		class MyPanel extends JPanel{
			public void paintComponent(Graphics g){
				super.paintComponent(g);
				g.drawImage(image1, 0, 0,getWidth(),getHeight(), F);
			}
		}
		MyPanel panel = new MyPanel();
		panel.setLayout(null);
		F.add(panel);
		F.setVisible(true);
		F.setSize(500, 400);
		F.setLocation(700, 200);
		F.setResizable(false);
		grade1.setForeground(Color.WHITE);
		grade1.setFont(new Font("Serif",Font.BOLD,30));
		grade1.setBounds(390,30,70,30);
		panel.add(grade1);
		timer = new JLabel(""+FirstTime + "초");
		timer.setForeground(Color.WHITE);
		timer.setFont(new Font("Serif",Font.PLAIN,25));
		timer.setBounds(380, 70, 100, 30);
		panel.add(timer);

		sub_Total_Second = FirstTime + SecondTime + ThirdTime;
		sub_Total_time.setText("" + sub_Total_Second + " 초");
		sub_Total_time.setForeground(Color.WHITE);
		sub_Total_time.setFont(new Font("Serif",Font.BOLD,25));
		sub_Total_time.setBounds(125, 280, 120, 40);
		panel.add(sub_Total_time);
		
		response_Text_sub.setForeground(Color.white);
		response_Text_sub.setFont(new Font("Serif",Font.BOLD,25));
		response_Text_sub.setBounds(340, 280, 100, 40);
		panel.add(response_Text_sub);
		
		Exitbutton.setBounds(20, 20, 50,58);
		Exitbutton.addActionListener(this);
		Exitbutton.setBackground(Color.black);
		Exitbutton.setBorderPainted(false);
		panel.add(Exitbutton);
		
		for(int F_S_T=1;F_S_T<=3;F_S_T++){
			switch(F_S_T){
			case 1:
				try {
					clientChannel = (BasicCoapClientChannel) channelManager.connect(this, InetAddress.getByName(SERVER_ADDRESS), PORT);
					CoapRequest coapRequest = clientChannel.createRequest(true, CoapRequestCode.POST);
					byte [] token = generateRequestToken(3);
					coapRequest.setUriPath("temperature");
					coapRequest.setToken(token);
					coapRequest.setPayload(power_Text1.getText().getBytes());
					clientChannel.sendMessage(coapRequest);
				}catch(UnknownHostException e1){
					e1.printStackTrace();
				}
				while(true){
					if(FirstTime!=0){
						sub_Total_Second--;
						sub_Total_time.setText(sub_Total_Second+" 초");
						FirstTime--;
						timer.setText(FirstTime + "초");
					}else{
						timer.setText(SecondTime + "초");
						grade1.setText("- 2 -");
						break;
					}
					try{
						Thread.sleep(1000);
					}catch(InterruptedException e){
						e.printStackTrace();
					}
				}
				break;
			case 2:
				try {
					clientChannel = (BasicCoapClientChannel) channelManager.connect(this, InetAddress.getByName(SERVER_ADDRESS), PORT);
					CoapRequest coapRequest = clientChannel.createRequest(true, CoapRequestCode.POST);
					byte [] token = generateRequestToken(3);
					coapRequest.setUriPath("temperature");
					coapRequest.setToken(token);
					coapRequest.setPayload(power_Text2.getText().getBytes());
					clientChannel.sendMessage(coapRequest);
				}catch(UnknownHostException e1){
					e1.printStackTrace();
				}
				while(true){
					if(SecondTime!=0){
						sub_Total_Second--;
						sub_Total_time.setText(sub_Total_Second+" 초");
						SecondTime--;
						timer.setText(SecondTime + "초");
					}else{
						timer.setText(ThirdTime + "초");
						grade1.setText("- 3 -");
						break;
					}
					try{
						Thread.sleep(1000);
					}catch(InterruptedException e){
						e.printStackTrace();
					}
				}
				break;
			case 3:
				try {
					clientChannel = (BasicCoapClientChannel) channelManager.connect(this, InetAddress.getByName(SERVER_ADDRESS), PORT);
					CoapRequest coapRequest = clientChannel.createRequest(true, CoapRequestCode.POST);
					byte [] token = generateRequestToken(3);
					coapRequest.setUriPath("temperature");
					coapRequest.setToken(token);
					coapRequest.setPayload(power_Text3.getText().getBytes());
					clientChannel.sendMessage(coapRequest);
				}catch(UnknownHostException e1){
					e1.printStackTrace();
				}
				while(true){
					if(ThirdTime!=0){
						sub_Total_Second--;
						sub_Total_time.setText(sub_Total_Second+" 초");
						ThirdTime--;
						timer.setText(ThirdTime + "초");
					}else{
						timer.setText("완료");
						break;
					}
					try{
						Thread.sleep(1000);
					}catch(InterruptedException e){
						e.printStackTrace();
					}
				}
				ToF_1=true;
				F.setVisible(false);
				ToF=false;
				ONOFF.setText("OFF");
				ONOFF.setForeground(Color.RED);
				try {
					clientChannel = (BasicCoapClientChannel) channelManager.connect(this, InetAddress.getByName(SERVER_ADDRESS), PORT);
					CoapRequest coapRequest = clientChannel.createRequest(true, CoapRequestCode.POST);
					byte [] token = generateRequestToken(3);
					coapRequest.setUriPath("temperature");
					coapRequest.setToken(token);
					coapRequest.setPayload("0".getBytes());
					clientChannel.sendMessage(coapRequest);
				}catch(UnknownHostException e1){
					e1.printStackTrace();
				}
				timer.setText("");
				break;
			}
		}
	}
	
	public void Client_UI_Frame(){
		setTitle("Induction Setting");
		setSize(400,400);
		setLocation(200,200);
		setLayout(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		Set_Label_1.setBounds(10, 20, 100, 30);
		add(Set_Label_1);
		Text_1.setBounds(70, 20, 50, 30);
		add(Text_1);
		Minute_Label_1.setBounds(130, 20, 100, 30);
		add(Minute_Label_1);
		power_Text1.setBounds(150,20,30,30);
		add(power_Text1);
		Power1.setBounds(180,20,100,30);
		add(Power1);
		
		Set_Label_2.setBounds(10, 70, 100, 30);
		add(Set_Label_2);
		Text_2.setBounds(70, 70, 50, 30);
		add(Text_2);
		Minute_Label_2.setBounds(130, 70, 100, 30);
		add(Minute_Label_2);
		power_Text2.setBounds(150,70,30,30);
		add(power_Text2);
		Power2.setBounds(180,70,100,30);
		add(Power2);
		
		Set_Label_3.setBounds(10, 120, 100, 30);
		add(Set_Label_3);
		Text_3.setBounds(70, 120, 50, 30);
		add(Text_3);
		Minute_Label_3.setBounds(130, 120, 100, 30);
		add(Minute_Label_3);
		power_Text3.setBounds(150,120,30,30);
		add(power_Text3);
		Power3.setBounds(180,120,100,30);
		add(Power3);
		
		Warning_Label.setBounds(10,160,100,30);
		add(Warning_Label);
		Warning_Text.setBounds(70, 160, 50, 30);
		add(Warning_Text);
		
		Setting_Button.setBounds(10, 190, 110, 40);
		add(Setting_Button);
		Start_Button.setBounds(260,190,110,40);
		add(Start_Button);
		
		response_Text_main.setBounds(10, 260, 240, 55);
		response_Text_main.setFont(new Font("Serif",Font.BOLD,20));
		response_Text_main.setForeground(Color.RED);
		add(response_Text_main);
		
		Start1.setBounds(300,20,100,30);
		Start2.setBounds(300,70,100,30);
		Start3.setBounds(300,120,100,30);
		Total_timer.setBounds(300, 155, 120, 30);
		Total_timer.setFont(new Font("Serif",Font.BOLD,15));
		Total_timer.setForeground(Color.RED);
		add(Start1);
		add(Start2);
		add(Start3);
		add(Total_timer);
		
		ONOFF.setBounds(300,230,100,30);
		ONOFF.setFont(new Font("Serif",Font.PLAIN,20));
		ONOFF.setForeground(Color.RED);
		
		add(ONOFF);
		
		turn_OFF.setBounds(150, 190, 70, 15);
		turn_OFF.setFont(new Font("Serif",Font.PLAIN,10));
		add(turn_OFF);
		Reset_button.setBounds(150,210,70,15);
		Reset_button.setFont(new Font("Serif",Font.PLAIN,10));
		add(Reset_button);
		
		Reset_button.addActionListener(this);
		turn_OFF.addActionListener(this);
		Setting_Button.addActionListener(this);
		Start_Button.addActionListener(this);
		try {
			clientChannel = (BasicCoapClientChannel) channelManager.connect(this, InetAddress.getByName(SERVER_ADDRESS), PORT);
			CoapRequest coapRequest = clientChannel.createRequest(true, CoapRequestCode.GET);
			byte [] token = generateRequestToken(3);
			coapRequest.setUriPath("temperature");
			coapRequest.setToken(token);
			coapRequest.setObserveOption(1);
			clientChannel.sendMessage(coapRequest);

		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}
	}
	
	public static void main(String args[]) throws IOException{
		String Server_address = "raspberrypi.mshome.net";
		Client_UI Client = new Client_UI(Server_address,Constants.COAP_DEFAULT_PORT);
		Client.channelManager = BasicCoapChannelManager.getInstance();
		Client.Client_UI_Frame();
		while(true){
			if(ToF == true){
				Client.UI_Sub_Frame();
			}
			if(ToF_1 == true){
				Client.Complete_Frame();
			}
			if(responseData!=null && responseData != "success"){
				Warning_Temp = responseData;
				if(Double.parseDouble(Warning_Temp)>=Warning_Temperature){
					try {
						Client.clientChannel = (BasicCoapClientChannel) Client.channelManager.connect(Client, InetAddress.getByName(Client.SERVER_ADDRESS), Client.PORT);
						CoapRequest coapRequest = Client.clientChannel.createRequest(true, CoapRequestCode.POST);
						byte [] token = Client.generateRequestToken(3);
						coapRequest.setUriPath("temperature");
						coapRequest.setToken(token);
						coapRequest.setPayload("0".getBytes());
						Client.clientChannel.sendMessage(coapRequest);
					}catch(UnknownHostException e1){
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "온도가 높습니다!", "경고!", JOptionPane.WARNING_MESSAGE);
					try{
						Thread.sleep(5000);
					}catch(InterruptedException e1){
						e1.printStackTrace();
					}
				}
			}
		}	
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == Setting_Button){
			FirstMinute = FirstTime = Double.parseDouble(Text_1.getText())*60;
			Start1.setText("1단계 : "+FirstMinute + " 초");
			SecondMinute = SecondTime = Double.parseDouble(Text_2.getText())*60;
			Start2.setText("2단계 : "+SecondMinute + " 초");
			ThirdMinute = ThirdTime = Double.parseDouble(Text_3.getText())*60;
			Start3.setText("3단계 : "+ThirdMinute + " 초");
			Total_Second = FirstTime + SecondTime + ThirdTime;
			Total_timer.setText("total : " + Total_Second + " 초");
			
			Warning_Temperature = Double.parseDouble(Warning_Text.getText());
		}
		if(e.getSource() == Start_Button){
			if(response_Text_main.getText() != "Connect Fail"){
				ONOFF.setText("ON");
				ONOFF.setForeground(Color.GREEN);
				ToF = true;
				F.setVisible(true);
			}
			else{}
		}
		if(e.getSource() == turn_OFF){
			ONOFF.setText("OFF");
			ONOFF.setForeground(Color.RED);
			ToF=false;
			F.setVisible(false);
			try {
				clientChannel = (BasicCoapClientChannel) channelManager.connect(this, InetAddress.getByName(SERVER_ADDRESS), PORT);
				CoapRequest coapRequest = clientChannel.createRequest(true, CoapRequestCode.POST);
				byte [] token = generateRequestToken(3);
				coapRequest.setUriPath("temperature");
				coapRequest.setToken(token);
				coapRequest.setPayload("0".getBytes());
				clientChannel.sendMessage(coapRequest);
			}catch(UnknownHostException e1){
				e1.printStackTrace();
			}
		}
		if(e.getSource() == Exitbutton){
			ONOFF.setText("OFF");
			ONOFF.setForeground(Color.RED);
			ToF=false;
			F.setVisible(false);
			try {
				clientChannel = (BasicCoapClientChannel) channelManager.connect(this, InetAddress.getByName(SERVER_ADDRESS), PORT);
				CoapRequest coapRequest = clientChannel.createRequest(true, CoapRequestCode.POST);
				byte [] token = generateRequestToken(3);
				coapRequest.setUriPath("temperature");
				coapRequest.setToken(token);
				coapRequest.setPayload("0".getBytes());
				clientChannel.sendMessage(coapRequest);
			}catch(UnknownHostException e1){
				e1.printStackTrace();
			}
		}
		if(e.getSource() == Reset_button){
			Text_1.setText("0"); 	Text_2.setText("0"); 	Text_3.setText("0");
			power_Text1.setText("0"); 	power_Text2.setText("0"); 	power_Text3.setText("0");
			Start1.setText("1단계 : 0 초"); 	Start2.setText("2단계 : 0 초"); 	Start3.setText("3단계 : 0 초");
			Total_timer.setText("total : 0 초");
			try {
				clientChannel = (BasicCoapClientChannel) channelManager.connect(this, InetAddress.getByName(SERVER_ADDRESS), PORT);
				CoapRequest coapRequest = clientChannel.createRequest(true, CoapRequestCode.POST);
				byte [] token = generateRequestToken(3);
				coapRequest.setUriPath("temperature");
				coapRequest.setToken(token);
				coapRequest.setPayload("0".getBytes());
				clientChannel.sendMessage(coapRequest);
			}catch(UnknownHostException e1){
				e1.printStackTrace();
			}
		}
		if(e.getSource() == Complete_B){
			Complete_F.setVisible(false);
			ToF_1 = false;
		}
	}

	@Override
	public void onResponse(CoapClientChannel channel, CoapResponse response) {
		if(response.getPayload() != null){
			responseData = new String(response.getPayload());
			response_Text_main.setText("Connect Success,\nInduction Temp : " + responseData);
			response_Text_main.setForeground(Color.GREEN);
			response_Text_sub.setText(responseData + "℃");
		}else{
			response_Text_main.setText("response null\n");
			response_Text_sub.setText("response null\n");
		}
		
	}

	@Override
	public void onMCResponse(CoapClientChannel channel, CoapResponse response, InetAddress srcAddress, int srcPort) {
		System.out.println("onMCResponse");
	}

	@Override
	public void onConnectionFailed(CoapClientChannel channel, boolean notReachable, boolean resetByServer) {
		System.out.println("연결에 실패했습니다.");
	}
	
}