package db;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TableTest extends JFrame{
	// 프레임에 JTable 만들기 (w500 h550)
	JTable table;
	JScrollPane scroll;
	
	// 데이터에 사용될 이차원 배열을 만들어보자
	String[][] data = {
			{"java","python","c#","javascript", "R"},
			{"jsp","servlet","React","Vue", "Node.js"},
			{"Oracle","MySQL","MariaDB","DB2", "MsSQL"}
	};
	
	// 컬럼명에 사용될 일차원 배열을 만들어보자
	String[] columns= {"과목1","과목2","과목3","과목4","과목5"};
	
	public TableTest() {
//		table=new JTable(14, 7); // row, column
		table=new JTable(data, columns); // rowData, columnNames
		scroll=new JScrollPane(table);
		
		// BorderLayout 영역에 붙이면, 테이블의 컬럼명이 안나옴... 
		setLayout(new FlowLayout());
		
		add(scroll);

		setSize(500, 550);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new TableTest();
	}
	
}
