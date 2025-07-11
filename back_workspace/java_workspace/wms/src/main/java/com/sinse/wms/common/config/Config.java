package com.sinse.wms.common.config;

// 물류관리시스템 에서 사용되는 모든 상수를 관리하는 클래스 
public class Config {
	/*--------------------------------------------------------
	    페이지 정의
	 --------------------------------------------------------*/
	public static final int SIGNIN_PAGE=0;
	public static final int MAIN_PAGE=1;
	public static final int SIGNUP_PAGE=2;
	
	
	/*--------------------------------------------------------
	    페이지 레이아웃 수치
	 --------------------------------------------------------*/
	public static final int p_center_width = 1440;
	public static final int p_center_height = 960;
	public static final int p_west_width = 450;
	public static final int p_west_height = p_center_height;
	public static final int p_right_width = p_center_width - p_west_width;
	public static final int p_right_height = p_center_height;
	public static final int p_top_weight = p_right_width;
	public static final int p_top_height = 100;
	public static final int p_bottom_weight = p_right_width;
	public static final int p_bottom_height = p_right_height-100;
	
	public static final int p_globalNavi_width = 100;
	public static final int p_globalNavi_height = p_west_height;
	public static final int p_localNavi_width = p_west_width-p_globalNavi_width;
	public static final int p_localNavi_height = p_west_height;
	
	
	public static final int P_WRAPPER_WIDTH = 1440;
	public static final int P_WRAPPER_HEIGHT = 960;
	public static final int P_WEST_WIDTH = 450;
	public static final int P_WEST_HEIGHT = P_WRAPPER_HEIGHT;
	public static final int P_CONTAINER_WIDTH = P_WRAPPER_WIDTH-P_WEST_WIDTH;
	public static final int P_CONTAINER_HEIGHT = P_WRAPPER_HEIGHT;
	public static final int P_NORTH_WIDTH = P_CONTAINER_WIDTH;
	public static final int P_NORTH_HEIGHT = 100;
	public static final int P_CENTER_WIDTH = P_CONTAINER_WIDTH;
	public static final int P_CENTER_HEIGHT = P_CONTAINER_HEIGHT-P_NORTH_HEIGHT;
	
	public static final int P_GLOBAL_NAVI_WIDTH = 100;
	public static final int P_GLOBAL_NAVI_HEIGHT = P_WEST_HEIGHT;
	public static final int P_LOCAL_NAVI_WIDTH = P_WEST_WIDTH-P_GLOBAL_NAVI_WIDTH;
	public static final int P_LOCAL_NAVI_HEIGHT = P_WEST_HEIGHT;
	
	public static final int LOCAL_MENU_WIDTH = 350;
	public static final int LOCAL_MENU_HEIGHT = 150;
	public static final int LOCAL_DETAIL_WIDTH = 300;
	public static final int LOCAL_DETAIL_HEIGHT = 30;	
}
