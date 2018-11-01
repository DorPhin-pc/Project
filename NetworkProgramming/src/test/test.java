package test;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class test {
	public static void main(String[] args) {
		String word = "서울+편의점";
		try {
		System.out.println("utf-8 -> euc-kr        : " + new String(word.getBytes("utf-8"), "euc-kr"));
		System.out.println("utf-8 -> ksc5601       : " + new String(word.getBytes("utf-8"), "ksc5601"));
		System.out.println("utf-8 -> x-windows-949 : " + new String(word.getBytes("utf-8"), "x-windows-949"));
		System.out.println("utf-8 -> iso-8859-1    : " + new String(word.getBytes("utf-8"), "iso-8859-1"));

		System.out.println("iso-8859-1 -> euc-kr        : " + new String(word.getBytes("iso-8859-1"), "euc-kr"));
		System.out.println("iso-8859-1 -> ksc5601       : " + new String(word.getBytes("iso-8859-1"), "ksc5601"));
		System.out.println("iso-8859-1 -> x-windows-949 : " + new String(word.getBytes("iso-8859-1"), "x-windows-949"));
		System.out.println("iso-8859-1 -> utf-8         : " + new String(word.getBytes("iso-8859-1"), "utf-8"));

		System.out.println("euc-kr -> utf-8         : " + new String(word.getBytes("euc-kr"), "utf-8"));
		System.out.println("euc-kr -> ksc5601       : " + new String(word.getBytes("euc-kr"), "ksc5601"));
		System.out.println("euc-kr -> x-windows-949 : " + new String(word.getBytes("euc-kr"), "x-windows-949"));
		System.out.println("euc-kr -> iso-8859-1    : " + new String(word.getBytes("euc-kr"), "iso-8859-1"));

		System.out.println("ksc5601 -> euc-kr        : " + new String(word.getBytes("ksc5601"), "euc-kr"));
		System.out.println("ksc5601 -> utf-8         : " + new String(word.getBytes("ksc5601"), "utf-8"));
		System.out.println("ksc5601 -> x-windows-949 : " + new String(word.getBytes("ksc5601"), "x-windows-949"));
		System.out.println("ksc5601 -> iso-8859-1    : " + new String(word.getBytes("ksc5601"), "iso-8859-1"));

		System.out.println("x-windows-949 -> euc-kr     : " + new String(word.getBytes("x-windows-949"), "euc-kr"));
		System.out.println("x-windows-949 -> utf-8      : " + new String(word.getBytes("x-windows-949"), "utf-8"));
		System.out.println("x-windows-949 -> ksc5601    : " + new String(word.getBytes("x-windows-949"), "ksc5601"));
		System.out.println("x-windows-949 -> iso-8859-1 : " + new String(word.getBytes("x-windows-949"), "iso-8859-1"));
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		String str_kr = "서울 강동구 날씨";
		 
		 String charset[] = {"euc-kr", "ksc5601", "iso-8859-1", "8859_1", "ascii", "UTF-8"}; 
		 
		 for(int i=0; i<charset.length ; i++){
		     try {
				System.out.println(charset[i] + " URLEncoder : " + URLEncoder.encode(str_kr, charset[i]));
				System.out.println(charset[i] + " URLDecoder : " + URLDecoder.decode(str_kr, charset[i]));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		     
		  }



}
}
