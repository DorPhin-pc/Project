package etc;
import java.io.BufferedReader;

import java.io.IOException;

import java.io.InputStreamReader;

import java.io.PrintWriter;

import java.net.InetAddress;

import java.net.Socket;

// 키보드로 전송문자열 입력받아 서버로 전송하는 스레드

class WriteThread{
       Socket socket;
       ClientFrame cf;
       String str;
       String id;
       public WriteThread(ClientFrame cf) {
             this.cf  = cf;
             this.socket= cf.socket;
       }
       public void sendMsg() {
             BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
             PrintWriter pw=null;
             try {
                    pw=new PrintWriter(socket.getOutputStream(),true);
                    if(cf.isFirst==true){
                           InetAddress iaddr=socket.getLocalAddress();                      
                           String ip = iaddr.getHostAddress();                        
                           getId();
                           System.out.println("ip:"+ip+"id:"+id);
                           str = "["+id+"] 님 로그인 ("+ip+")";
                    }else  str= "["+id+"] "+cf.txtF.getText();
                    pw.println(str);
             }catch(IOException ie){
                    System.out.println(ie.getMessage());
             }finally{
                    try{
                           if(br!=null) br.close();
                    }catch(IOException ie){
                           System.out.println(ie.getMessage());
                    }
             }
       }     
       public void getId(){            
             id = Id.getId();
       }
}
class ReadThread extends Thread{
       Socket socket;
       ClientFrame cf;
       public ReadThread(Socket socket, ClientFrame cf) {
             this.cf = cf;
             this.socket=socket;
       }
       public void run() {
             BufferedReader br=null;
             try{
                    br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    while(true){
                           String str=br.readLine();
                           if(str==null){
                                 System.out.println("접속이 끊겼음");
                                 break;
                           }
                           cf.txtA.append(str+"\n");
                    }
             }catch(IOException ie){
                    System.out.println(ie.getMessage());
             }finally{
                    try{
                           if(br!=null) br.close();
                           if(socket!=null) socket.close();
                    }catch(IOException ie){}
             }
       }
}

public class MultiChatClient {
       public static void main(String[] args) {
    	   new MultiChatClient();
       }
       MultiChatClient(){
    	   Socket socket=null;
           ClientFrame cf;
           try{
                  socket=new Socket("59.152.145.138",3000);
                  System.out.println("연결성공!");
                  cf = new ClientFrame(socket);
                  new ReadThread(socket, cf).start();
           }catch(IOException ie){
                  System.out.println(ie.getMessage());
           }
       }
}

