package com.sinse.wms.inbound.model;

//아래의 클래스는 로직을 담기 위한 객체가 아니라, 단지 데이터베이스의 TopCategory 테이블의 
//레코드 1건을 담기 위한 모델 객체이다.
//또한 이 객체는 DB의 레코드를 담고 있기 때문에...보안상 중요하다..따라서 은닉화시켜 정의해야 함 
public class Product {
	private int product_id;
	private String cateogry;
	private String product_name;
	private String unit_price;
	private String quantity;
	private String unit;
	
	
}





