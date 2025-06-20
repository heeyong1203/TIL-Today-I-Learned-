package com.sinse.wms.common.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import com.sinse.wms.AppMain;
import com.sinse.wms.common.config.ImageUtil;

public class Page extends JPanel {
	private JPanel p_navi, p_global, p_local;
	private JPanel p_section, p_north, p_center;
	String[][] localMenus = { { "inbound.png", "입고" }, { "outbound.png", "출고" }, { "inventory.png", "재고 현황" },
			{ "statistics.png", "통계 및 보고서" }, { "userManaging.png", "사용자 관리" } };
	String[][] localSubMenus = { { "입고 현황", "입고 요청", "입고 검수" }, { "출고 현황", "출고 요청", "출고 검수" }, {}, // 재고 현황에는 서브메뉴 없음
			{ "입/출고 현황 통계", "입/출고 현황 보고서" }, {} // 사용자 관리에는 서브메뉴 없음
	};

	AppMain appMain;
	ImageUtil imageUtil;
	
	private List<JPanel> mainPanels = new ArrayList();
	private List<JPanel> subPanels = new ArrayList();

	public Page(AppMain appMain) {
		// 생성
		this.imageUtil = new ImageUtil();
		p_navi = new JPanel();
		p_global = new JPanel();
		p_local = new JPanel();

		p_section = new JPanel();
		p_north = new JPanel();
		p_center = new JPanel();

		// 스타일
		appMain.setBackground(new Color(227, 227, 227));
		p_navi.setPreferredSize(new Dimension(450, 960));
		p_global.setPreferredSize(new Dimension(100, 960));
		p_global.setBackground(new Color(38, 124, 181));
		p_local.setPreferredSize(new Dimension(350, 960));
		p_local.setBorder(new MatteBorder(0, 0, 0, 1, Color.BLACK));

		p_section.setPreferredSize(new Dimension(990, 960));
		p_north.setPreferredSize(new Dimension(990, 100));
		p_center.setPreferredSize(new Dimension(990, 860));

		// 조립
		appMain.setLayout(new BorderLayout());
		p_navi.setLayout(new BorderLayout());
		p_section.setLayout(new BorderLayout());
		p_north.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
		p_center.setLayout(new FlowLayout(FlowLayout.CENTER, 45, 60));

		p_north.add(createCurrentUser());
		p_center.add(createContent());

		p_global.add(createGlobal());
		p_local.add(createLocal());

		p_section.add(p_north, BorderLayout.NORTH);
		p_section.add(p_center, BorderLayout.CENTER);
		p_navi.add(p_global, BorderLayout.WEST);
		p_navi.add(p_local, BorderLayout.CENTER);

		appMain.add(p_navi, BorderLayout.WEST);
		appMain.add(p_section, BorderLayout.CENTER);
		
	}

	public Box createGlobal() {
		// 생성
		Box box = Box.createVerticalBox();
		JLabel global_img1 = new JLabel(new ImageIcon(imageUtil.getImage("user_info.png", 50, 50)));
		JLabel global_img2 = new JLabel(new ImageIcon(imageUtil.getImage("message.png", 50, 50)));
		JLabel global_img3 = new JLabel(new ImageIcon(imageUtil.getImage("search.png", 50, 50)));
		JLabel global_img4 = new JLabel(new ImageIcon(imageUtil.getImage("help.png", 50, 50)));
		JLabel global_img5 = new JLabel(new ImageIcon(imageUtil.getImage("settings.png", 50, 50)));

		// 스타일
		box.add(box.createVerticalStrut(55));
		box.add(global_img1);
		box.add(box.createVerticalStrut(50));
		box.add(global_img2);
		box.add(box.createVerticalStrut(50));
		box.add(global_img3);
		box.add(box.createVerticalStrut(400));
		box.add(global_img4);
		box.add(box.createVerticalStrut(50));
		box.add(global_img5);

		// 조립
		return box;
	}

	public JPanel createLocal() {
		// 생성
		JPanel p_title = new JPanel();
		JLabel la_localTitle = new JLabel("관리자 페이지"); // local_navi title
		// 스타일
		p_title.setPreferredSize(new Dimension(350, 960));
		la_localTitle.setPreferredSize(new Dimension(350, 140));
		la_localTitle.setFont(new Font("inter", Font.BOLD, 45));
		la_localTitle.setForeground(new Color(38, 124, 181));
		la_localTitle.setHorizontalAlignment(SwingConstants.CENTER);
		p_title.setOpaque(false);
		// 조합
		p_title.add(la_localTitle);
		
		// 로컬 메뉴 생성
		for (int i = 0; i < localMenus.length; i++) {
			// 생성
			JPanel p_container = new JPanel();
			JPanel p_main = new JPanel();
			JPanel p_sub = new JPanel();
			JLabel la_img = new JLabel(new ImageIcon(imageUtil.getImage(localMenus[i][0], 75, 75)));
			JLabel la_text = new JLabel(localMenus[i][1]);
			// 스타일
			p_container.setPreferredSize(new Dimension(348, 80 + (localSubMenus[i].length*30)));
			p_main.setPreferredSize(new Dimension(348, 80)); 
			p_sub.setPreferredSize(new Dimension(330, (localSubMenus[i].length*30))); 
			la_text.setPreferredSize(new Dimension(220, 75));
			la_text.setFont(new Font("inter", Font.BOLD, 30)); 
			la_text.setForeground(new Color(138, 138, 138));
			p_container.setOpaque(false);
			p_main.setOpaque(true);
			p_sub.setOpaque(false);
			//p_sub.setVisible(false);
			// 조립
			p_main.add(la_img); 
			p_main.add(la_text);
			p_container.add(p_main);
			p_container.add(p_sub);
			
			mainPanels.add(p_main);
			subPanels.add(p_sub);
			
			// 로컬 하위 메뉴 생성
			if (localSubMenus[i] != null && localSubMenus[i].length > 0) {
				for (int j = 0; j < localSubMenus[i].length; j++) {
					// 생성
					JLabel la_sub = new JLabel(localSubMenus[i][j]);
					JPanel p_blank = new JPanel();
					// 스타일
					p_blank.setPreferredSize(new Dimension(20, 20));					
					la_sub.setFont(new Font("inter", Font.BOLD, 20));
					la_sub.setForeground(new Color(138, 138, 138));
					la_sub.setPreferredSize(new Dimension(300, 20));
					p_blank.setOpaque(false);
					// 조립
					p_sub.add(p_blank);
					p_sub.add(la_sub);
				}
			}
			
			// 클릭 이벤트 부착
			final int index = i; // 익명 클래스 내부에서 사용하기 위한 final index
			p_main.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					// 1. 모든 mainPanel 색상 초기화 및 모든 subPanel 숨김 처리
					for(int j = 0; j < mainPanels.size(); j++) {
						JPanel mp = mainPanels.get(j);
						JPanel sp = subPanels.get(j);
						mp.setBackground(null);
						sp.setVisible(false);
					}
					// 2. 현재 클릭한 패널만 강조 색상 및 하위 메뉴 보이기
					p_main.setBackground(new Color(149, 199, 233));
					p_sub.setVisible(true);
					p_local.revalidate();
					p_local.repaint();
					p_local.updateUI();
				}
			});
			
			
			
			p_title.add(p_container);
		}
		return p_title;
	}

	// 상단 유저 현황 패널
	public JPanel createCurrentUser() {
		// 생성
		JPanel p_top = new JPanel() { // 내부 익명 클래스 오버라이딩
			protected void paintComponent(Graphics g) {
				java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
				g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING,
						java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setColor(Color.WHITE); // 내부 배경색
				g2.fillRoundRect(0, 0, 850, 60, 45, 45);
				g2.dispose();
				super.paintComponent(g);
			}

			protected void paintBorder(Graphics g) {
				java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
				g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING,
						java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setColor(Color.BLACK);
				g2.drawRoundRect(0, 0, 849, 59, 45, 45);
				g2.dispose();
				super.paintBorder(g);
			}
		};
		JPanel p_blank = new JPanel();
		JPanel p_userIcon = new JPanel();
		JPanel p_user = new JPanel();
		JLabel la_blank =new JLabel();
		JLabel la_userIcon = new JLabel(new ImageIcon(imageUtil.getImage("user_info.png", 40, 40)));
		JLabel la_user = new JLabel("<html><div style='text-align: center;'>마케팅 1팀<br>김범수 님</div></html>");

		// 스타일
		p_top.setPreferredSize(new Dimension(850, 60));
		p_blank.setPreferredSize(new Dimension(660, 50));
		p_userIcon.setPreferredSize(new Dimension(50, 50));
		p_user.setPreferredSize(new Dimension(120, 50));
		la_blank.setPreferredSize(new Dimension(650, 40));
		la_userIcon.setPreferredSize(new Dimension(40, 40));
		la_user.setPreferredSize(new Dimension(110, 40));
		la_user.setFont(new Font("inter", Font.BOLD, 16));
		la_user.setHorizontalAlignment(SwingConstants.CENTER);
		p_blank.setOpaque(false);
		p_userIcon.setOpaque(false);
		p_user.setOpaque(false);
		p_top.setOpaque(false);

		// 조립
		p_top.setLayout(new FlowLayout());
		p_blank.add(la_blank);
		p_userIcon.add(la_userIcon);
		p_user.add(la_user);
		p_top.add(p_blank);
		p_top.add(p_userIcon);
		p_top.add(p_user);
		return p_top;
	}

	// 컨텐츠 패널
	public JPanel createContent() {
		// 생성
		JPanel panel = new JPanel() { // 내부 익명 클래스 오버라이딩
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
				g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING,
						java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setColor(Color.WHITE); // 내부 배경색
				g2.fillRoundRect(0, 0, 900, 700, 50, 50);
				g2.dispose();
			}

			protected void paintBorder(Graphics g) {
				super.paintBorder(g);
				java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
				g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING,
						java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setColor(Color.BLACK);
				g2.drawRoundRect(0, 0, 899, 699, 50, 50);
				g2.dispose();
			}
		};

		// 스타일
		panel.setPreferredSize(new Dimension(900, 700));
		panel.setMaximumSize(new Dimension(900, 700));
		panel.setLayout(new BorderLayout());

		return panel;
	}
}
