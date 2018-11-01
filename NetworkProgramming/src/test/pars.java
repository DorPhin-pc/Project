package test;


import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import Project.Data;
import Project.URLManager;


public class pars {
	public static ArrayList<Data>goodsSearch(String F_Category,String S_Category) {
		String url = "https://search.daum.net/search?w=tot&DA=YZR&t__nil_searchbox=btn&sug=&sugo=&q=";
		String Weather = "날씨";
		
		String keyword = F_Category + " " + S_Category + " " + Weather;
		try {
			keyword = URLEncoder.encode(keyword,"UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		String result = url + keyword;
		ArrayList<Data> ITEM = new ArrayList<>();
		
		InputStream in = URLManager.getURLInputStream(result,URLManager.USER_AGENT_PC);
		
		try{
				Document doc = Jsoup.parse(in,URLManager.ENCODING_UTF8,"");
				
				Elements root = doc.select("div[class=cont_today]");
				Elements temperature = root.select("div[class=info_temp]");
				temperature = temperature.select("div[class=info_weather]");
				temperature = temperature.select("span[class=wrap_desc]");
				temperature = temperature.select("span[class=desc_temp]");
				temperature = temperature.select("strong[class=txt_temp]");		//온도
				
				Elements Dust = root.select("div[class=info_another]");
				Dust = Dust.select("dl[class=dl_weather]");
				Dust = Dust.select("dd");		//미세먼지
			
			for(int i=0; i<temperature.size();i++) {
				Data D = new Data(temperature.get(i).text());
				ITEM.add(D);
			}
			
		}catch(IOException e){
			e.printStackTrace();
		}
		
		return ITEM;
	}
}