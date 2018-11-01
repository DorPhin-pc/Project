package Project;

import javax.swing.ImageIcon;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.*;

public class Data {
	private String keyword,link,Loc;
	private ImageIcon image;
	public Data(String keyword) {
		this.keyword = keyword;
	}
	public Data(byte[]image) {
		if(image!=null) {
			this.image = new ImageIcon(image);
			Image img = this.image.getImage();
			BufferedImage bi = new BufferedImage(img.getWidth(null),img.getHeight(null),BufferedImage.TYPE_INT_ARGB);
			Graphics g = bi.createGraphics();
			g.drawImage(img, 0, 0, 150, 150, null);
			this.image = new ImageIcon(bi);
		}
		else
			this.image = new ImageIcon();
	}
	public ImageIcon getImage() {
		return image;
	}
	public void setImage(ImageIcon image) {
		this.image = image;
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
		Data DA = (Data) O;
		return DA.getKeyword().equals(keyword) && DA.getLink().equals(link);
	}

	public String toString() {
		return String.format("검색: %s, 링크 :%s",keyword,link);
	}


	
}
