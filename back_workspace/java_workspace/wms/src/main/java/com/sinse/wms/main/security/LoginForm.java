package com.sinse.wms.main.security;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.sinse.wms.AppMain;
import com.sinse.wms.common.config.Config;
import com.sinse.wms.common.view.Page;

public class LoginForm extends Page {
	JPanel p_west;
	JPanel p_center;
	JLabel la_signIn;
	JLabel la_id;
	JLabel la_pwd;
	JLabel la_welcome;
	JLabel la_welcome2;
	JLabel la_question;
	
	JTextField t_id;
	JPasswordField t_pwd;
	
	JButton bt_signIn;
	JButton bt_signUp;
	
	public LoginForm(AppMain appMain) {
		// 생성
		super(appMain);
		p_west = new JPanel();
		p_center = new JPanel();
		la_signIn = new JLabel("SIGN IN");
		la_id = new JLabel("아이디 찾기");
		la_pwd = new JLabel("비밀번호 찾기");
		la_welcome = new JLabel("Welcome Back !!");
		la_welcome2 = new JLabel("오늘도 안정적인 물류 운영을 도와드릴게요.");
		la_question = new JLabel("아직 계정이 없으신가요?");
		t_id = new JTextField("ID");
		t_pwd = new JPasswordField("PASSWORD");
		bt_signIn = new JButton("SIGN IN");
		bt_signUp = new JButton("SIGN UP");
		
		// 스타일
		Dimension d = new Dimension(300, 60);
		Dimension d2 = new Dimension(120, 60);
		p_west.setPreferredSize(new Dimension(500, 600));
		p_center.setPreferredSize(new Dimension(500, 600));
		t_id.setPreferredSize(d);
		t_pwd.setPreferredSize(d);
		bt_signIn.setPreferredSize(d2);
		bt_signUp.setPreferredSize(d2);
		
		
		// 조립
		p_west.setLayout(new BoxLayout(p_west, BoxLayout.Y_AXIS));
		p_west.add(la_signIn);
		p_west.add(t_id);
		p_west.add(t_pwd);
		p_west.add(la_id);
		p_west.add(la_pwd);
		p_west.add(bt_signIn);
		p_center.setLayout(new BoxLayout(p_center, BoxLayout.Y_AXIS));
		p_center.add(la_welcome);
		p_center.add(la_welcome2);
		p_center.add(la_question);
		p_west.add(bt_signUp);
		add(p_west, BorderLayout.WEST);
		add(p_center, BorderLayout.CENTER);
		
		bt_signIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// appMain.showPage(Config.MAIN_PAGE);
			}
		});
		
		setPreferredSize(new Dimension(Config.P_WRAPPER_WIDTH, Config.P_WRAPPER_HEIGHT));
	}
	
}
