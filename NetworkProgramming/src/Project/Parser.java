package Project;


import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Parser {
	
	public static ArrayList<Data>goodsSearch(String F_Category,String S_Category,String Tour,String Start_Tour,String IDX) {
		String url_weather = "https://search.daum.net/search?w=tot&DA=YZR&t__nil_searchbox=btn&sug=&sugo=&q=";	//³¯¾¾ ÆÄ½Ì
		String Weather = "³¯¾¾";
		String url_tour = "https://search.daum.net/search?nil_suggest=btn&nil_ch=&rtupcoll=&w=tot&m=&f=&lpp=&DA=SBC&sug=&sq=&o=&sugo=&q=";	//°ü±¤Áö ÆÄ½Ì
		
		
		String key_weather = F_Category + " " + S_Category + " " + Weather;	//³¯¾¾
		
		try {
			key_weather = URLEncoder.encode(key_weather,"UTF-8");
			Tour = URLEncoder.encode(Tour,"UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		String result_weather = url_weather + key_weather;
		String result_tour = url_tour + Tour;
		
		ArrayList<Data> ITEM = new ArrayList<>();
		
		InputStream in_weather = URLManager.getURLInputStream(result_weather,URLManager.USER_AGENT_PC);
		InputStream in_tour = URLManager.getURLInputStream(result_tour,URLManager.USER_AGENT_PC);
		
		try{
				Document doc_weather = Jsoup.parse(in_weather,URLManager.ENCODING_UTF8,"");
				Document doc_tour = Jsoup.parse(in_tour,URLManager.ENCODING_UTF8,"");
				
				Elements temperature = doc_weather.select("div[class=info_temp]");
				temperature = temperature.select("div[class=info_weather]");
			//	temperature = temperature.select("span[class=wrap_desc night]");
				temperature = temperature.select("span[class=desc_temp]");
				temperature = temperature.select("strong[class=txt_temp]");		//¿Âµµ
				
				Elements Dust = doc_weather.select("div[class=info_another]");
				Dust = Dust.select("dl[class=dl_weather]");
				Dust = Dust.select("dd");		//¹Ì¼¼¸ÕÁö(0=Ç³¼Ó,1=½Àµµ,2=¹Ì¼¼¸ÕÁö)
			
				Elements Wea = doc_weather.select("div[class=info_temp]");
				Wea = Wea.select("div[class=info_weather]");
			//	Wea = Wea.select("span[class=wrap_desc night]");
				Wea = Wea.select("span[class=desc_temp]");
				Wea = Wea.select("span[class=txt_weather]");
				
				Elements tour = doc_tour.select("div[class=wrap_basicinfo list_local");
				Elements tour_name = tour.select("div[class=wrap_tit]");
				tour_name = tour_name.select("a");	//°ü±¤Áö ÀÌ¸§
				
				Elements tour_S_name = tour.select("div[class=wrap_tit]");
				tour_S_name = tour_S_name.select("span[class=f_nb]");  //°ü±¤Áö Æ¯Â¡(?)
				
				Elements tour_etc = tour.select("div[class=info_place]");
				tour_etc = tour_etc.select("dl[class=dl_comm]");	//ÁÖ¼Ò,ÈÞ¹«ÀÏ,±âÅ¸
				
				Data D;
				if(IDX == "weather") {
					D = new Data(temperature.get(0).text());
					ITEM.add(D);
					for(int i=0;i<Dust.size();i++) {
						D = new Data(Dust.get(i).text());
						ITEM.add(D);
					}
					String wea[] = Wea.get(0).text().split(" ");
					D = new Data(wea[2]);
					ITEM.add(D);
				}
				if(IDX == "tour") {
					D = new Data(tour_name.get(0).text());
					ITEM.add(D);
					D = new Data(tour_S_name.get(0).text());
					ITEM.add(D);
					for(int i=0;i<tour_etc.size();i++) {
						D = new Data(tour_etc.get(i).text());
						ITEM.add(D);
					}
				}
		}catch(IOException e){
			e.printStackTrace();
		}
		
		return ITEM;
	}
}