package com.sinse.shop.product.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.sinse.shop.AppMain;
import com.sinse.shop.common.config.Config;
import com.sinse.shop.common.view.Page;
import com.sinse.shop.home.MainPage;

public class ProductDetailPage extends Page {
	JPanel p_wrapper; // 실제적 컨텐츠 크기
	JPanel p_west;
	JPanel p_east;
	JScrollPane scroll; // this를 scroll에 넣는 것은 위반됨
	
	JPanel p_img; // 좌측 큰 이미지 및 썸네일을 감쌀 패널
	JPanel p_content; // 상품정보 감쌀 패널
	JPanel p_big; // 큰 이미지 패널
	
	MainPage mainPage;
	
	public ProductDetailPage(AppMain appMain) { // 모든 페이지들은 AppMain을 통해 내용을 공유할 수 있음...
		super(appMain); 
		
		mainPage = (MainPage)appMain.pages[Config.MAIN_PAGE]; // Page를 MainPage로 다운캐스팅..
		
		//생성
		p_wrapper = new JPanel();
		p_west = new JPanel();
		p_east = new JPanel();
		p_img = new JPanel();
		p_content = new JPanel();
		p_big = new JPanel() {
			protected void paintComponent(Graphics g) {
				/* g.setColor(Color.RED); g.fillRect(0, 0,
				 * this.getPreferredSize().getSize().width,
				 * this.getPreferredSize().getSize().height); */
				Image image = null; // try문 밖에서 image가 쓰여야하므로 변수를 try문 밖으로 빼자
				try {
					image = ImageIO.read(new File("c:/public/"+mainPage.product.getFilenameList().get(0)));
				} catch (IOException e) {
					e.printStackTrace();
				}
				g.drawImage(image, 0, 0, this.getPreferredSize().getSize().width, this.getPreferredSize().getSize().height, this);
			}
		};
		
		// 스타일
		p_wrapper.setBackground(Color.BLUE);
		p_wrapper.setPreferredSize(new Dimension(Config.SHOPMAIN_WIDTH-300, Config.SHOPMAIN_HEIGHT-Config.UTIL_HEIGHT-Config.NAVI_HEIGHT));
		p_west.setPreferredSize(new Dimension(150, Config.SHOPMAIN_HEIGHT-Config.UTIL_HEIGHT-Config.NAVI_HEIGHT));
		p_east.setPreferredSize(new Dimension(150, Config.SHOPMAIN_HEIGHT-Config.UTIL_HEIGHT-Config.NAVI_HEIGHT));
		p_img.setPreferredSize(new Dimension(p_wrapper.getPreferredSize().getSize().width/2, 450));
		p_img.setBackground(Color.CYAN);
		p_content.setPreferredSize(new Dimension(p_wrapper.getPreferredSize().getSize().width/2, 450));
		p_content.setBackground(Color.MAGENTA);
		p_big.setPreferredSize(new Dimension(p_img.getPreferredSize().getSize().width-10, p_img.getPreferredSize().getSize().height-70));
		
		// 조립
		setLayout(new BorderLayout());
		p_wrapper.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		p_img.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5)); // thumbnail 세로 55 
		add(p_west, BorderLayout.WEST);
		add(p_east, BorderLayout.EAST);
		p_img.add(p_big);
		p_wrapper.add(p_img);
		p_wrapper.add(p_content);
		add(p_wrapper);
		
		
		setPreferredSize(new Dimension(Config.SHOPMAIN_WIDTH, Config.SHOPMAIN_HEIGHT-Config.UTIL_HEIGHT-Config.NAVI_HEIGHT));
		setVisible(true);
	}
}
