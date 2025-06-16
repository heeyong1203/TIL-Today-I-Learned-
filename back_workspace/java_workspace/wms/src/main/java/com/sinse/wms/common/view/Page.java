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
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Flow;

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

	public JPanel createGlobal() {
		// ===== 생성 =====
		JPanel panel = new JPanel();
		JPanel empty1 = new JPanel();
		JPanel empty2 = new JPanel();
		JPanel[] panels = new JPanel[9];

		JLabel global_img0 = new JLabel(new ImageIcon(imageUtil.getImage("user_info.png", 50, 50)));
		JLabel global_img1 = new JLabel(new ImageIcon(imageUtil.getImage("message.png", 50, 50)));
		JLabel global_img2 = new JLabel(new ImageIcon(imageUtil.getImage("search.png", 50, 50)));
		JLabel global_img7 = new JLabel(new ImageIcon(imageUtil.getImage("help.png", 50, 50)));
		JLabel global_img8 = new JLabel(new ImageIcon(imageUtil.getImage("settings.png", 50, 50)));

		// ===== 스타일 =====
		panel.setPreferredSize(new Dimension(100, 960));
		panel.setOpaque(false);

		empty1.setPreferredSize(new Dimension(100, 20));
		empty2.setPreferredSize(new Dimension(100, 20));
		empty1.setOpaque(false);
		empty2.setOpaque(false);

		for (int i = 0; i < panels.length; i++) {
			panels[i] = new JPanel();
			panels[i].setPreferredSize(new Dimension(90, 90));
			panels[i].setOpaque(false);
			panels[i].setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
		}

		// ===== 조립 =====
		panels[0].add(global_img0);
		panels[1].add(global_img1);
		panels[2].add(global_img2);
		panels[7].add(global_img7);
		panels[8].add(global_img8);

		panel.add(empty1);
		for (JPanel p : panels) {
			panel.add(p);
		}
		panel.add(empty2);

		return panel;
	}

	public JPanel createLocal() {
		// ===== 로컬 타이틀 =====
		// ===== 생성 =====
		JPanel p_container = new JPanel(); // localPanel
		JLabel la_title = new JLabel("관리자 페이지");
		JPanel[] mainPanels = new JPanel[localMenus.length];
		JPanel[] subPanels = new JPanel[localMenus.length];

		// ===== 스타일 =====
		p_container.setPreferredSize(new Dimension(349, 960));
		la_title.setPreferredSize(new Dimension(349, 200));
		la_title.setFont(new Font("inter", Font.BOLD, 45));
		la_title.setForeground(new Color(38, 124, 181));
		la_title.setHorizontalAlignment(SwingConstants.CENTER);
		la_title.setVerticalAlignment(SwingConstants.CENTER);

		// ===== 조립 =====
		p_container.add(la_title);

		// ===== 메인 메뉴 =====
		for (int i = 0; i < localMenus.length; i++) {
			// ===== 생성 =====
			mainPanels[i] = new JPanel();
			subPanels[i] = new JPanel();
			JPanel p_img = new JPanel();
			JLabel img = new JLabel(new ImageIcon(imageUtil.getImage(localMenus[i][0], 75, 75)));
			JLabel la_main = new JLabel(localMenus[i][1]);

			// ===== 스타일 =====
			p_img.setPreferredSize(new Dimension(100, 100));
			p_img.setLayout(new FlowLayout(FlowLayout.CENTER, 12, 12));
			p_img.setOpaque(false);
			la_main.setPreferredSize(new Dimension(224, 100));
			la_main.setFont(new Font("inter", Font.BOLD, 30));
			la_main.setForeground(new Color(138, 138, 138));
			mainPanels[i].setLayout(new FlowLayout(FlowLayout.LEFT, 25, 0));
			mainPanels[i].setBackground(Color.BLACK);
			mainPanels[i].setOpaque(false);

			// ===== 조립 =====
			p_img.add(img);
			mainPanels[i].add(p_img);
			mainPanels[i].add(la_main);
			p_container.add(mainPanels[i]);

			// 클릭 이벤트 부착
			final int index_main = i;
			mainPanels[index_main].addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					// 모든 패널의 배경색 초기화
					for (int j = 0; j < mainPanels.length; j++) {
						mainPanels[j].setOpaque(false);
					}
					
					// 클릭한 패널만 색상 지정
					mainPanels[index_main].setBackground(new Color(149, 199, 233));
					mainPanels[index_main].setOpaque(true);
					p_container.updateUI();
				}
			});
			
			// ===== 서브 메뉴 =====
			if (localSubMenus[i] != null) {
				for (int j = 0; j < localSubMenus[i].length; j++) {
					// ===== 생성 =====
					JPanel p_blank = new JPanel();
					JPanel p_sub = new JPanel();
					JLabel la_sub = new JLabel(localSubMenus[i][j]);

					// ===== 스타일 =====
					p_blank.setPreferredSize(new Dimension(40, 30));
					p_blank.setOpaque(false);
					p_sub.setPreferredSize(new Dimension(274,30));
					p_sub.setOpaque(false);
					la_sub.setPreferredSize(new Dimension(274, 30));
					la_sub.setBackground(Color.red);
					la_sub.setFont(new Font("inter", Font.BOLD, 20));
					la_sub.setForeground(new Color(138, 138, 138));

					// ===== 조립 =====
					p_sub.add(la_sub);
					p_container.add(p_blank);
					p_container.add(p_sub);
					
					final int index_sub = j;
				}
			} 
		} return p_container;
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
		JLabel la_blank = new JLabel();
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
