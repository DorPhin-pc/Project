package test;

import javax.swing.ImageIcon;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.*;

public class data {
	private String keyword,link,Loc;
	private ImageIcon image;
	public data(String keyword) {
		this.keyword = keyword;
	}
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public boolean equals(Object O) {
		data DA = (data) O;
		return DA.getKeyword().equals(keyword) && DA.getLink().equals(link);
	}

	public String toString() {
		return String.format("검색: %s, 링크 :%s",keyword,link);
	}


	
}
