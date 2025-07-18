# mysql

```sql
-- 접속
mysql -u [유저명] -h[접속_아이피_주소] -p
[비밀번호]

-- db 생성
CREATE DATABASE mydb;
USE mydb;

-- 유저 생성
CREATE USER 'username'@'localhost' IDENTIFIED BY '비밀번호'; --로컬에서만 접속
CREATE USER 'username'@'%' IDENTIFIED BY '비밀번호'; -- 원격 접속 허용(% 혹은 특정IP)

-- 권한 부여
GRANT ALL PRIVILEGES ON mydb.* TO 'username'@'localhost' with grant option; -- mydb의 모든테이블(*)에 대해 모든 권한 부여(ALL)
GRANT SELECT, INSERT, UPDATE ON mydb.* TO 'username'@'localhost'; -- 특정 권한만 부여

FLUSH PRIVILEGES; -- 권한 적용
SHOW GRANTS FOR 'username'@'loaclhost'; -- 권한 확인

-- 테이블 생성
CREATE TABLE category(
    category_id INT AUTO_INCREMENT PRIMARY KEY COMMENT '카테고리ID' -- pk값 자동 증가
    , category_name VARCHAR(25) UNIQUE COMMENT '카테고리명'
)
AUTO_INCREMENT=1001;

CREATE TABLE product (    
    product_id INT AUTO_INCREMENT PRIMARY KEY COMMENT '상품ID' 
    , product_code INT UNIQUE COMMENT '상품코드' -- null 값 및 중복값 허용하지 않음
    , product_name VARCHAR(25) UNIQUE COMMENT '상품명' 
    , product_description VARCHAR(100) NOT NULL COMMENT '상품설명'
    , product_price INT NOT NULL COMMENT '상품가격'
    , target_stock INT NOT NULL COMMENT '안전재고량'
    , product_regdate TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '등록일자'
    , product_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일자'

    , category_id INT NOT NULL COMMENT '상품코드' -- null 값 허용하지 않음
    , company_id INT NOT NULL COMMENT '거래처ID'
    , unit_id INT NOT NULL COMMENT '단위ID'
    , location_id INT NOT NULL COMMENT '창고위치ID'
    , image_id INT NOT NULL COMMENT '이미지ID'

    , FOREIGN KEY(category_id) REFERENCES category(category_id) -- category_id는 외래키, category테이블의 pk값을 참조함
    , FOREIGN KEY(company_id) REFERENCES company(company_id)
    , FOREIGN KEY(unit_id) REFERENCES unit(unit_id)
    , FOREIGN KEY(location_id) REFERENCES location(location_id)
    , FOREIGN KEY(image_id) REFERENCES image(image_id)
);

-- 데이터 삽입
INSERT INTO category(category_name) VALUES('디지털');

INSERT INTO product(category_id, product_code, product_name, product_description, product_price, target_stock, company_id, unit_id, location_id, image_id)
VALUES(1001, 10001, 'A4 복사용지', '80g 500매', 20000, 60, 1, 1, 1, 1);

-- 특정 pk값의 데이터 변경
UPDATE product 
SET product_price = 18500, product_description = '1주년 할인 행사' 
WHERE product_id = 1;

-- 특정 pk값의 데이터 삭제
DELETE FROM product
WHERE product_id = 1;

-- 데이터 조회
SELECT * FROM product WHERE category_id = 3;

-- 테이블 복사
CREATE TABLE staff
AS SELECT empno AS no, ename AS name, sal, deptno FROM emp;

-- 같은 mysql에 여러 명의 동시접속자가 있는 경우, pk값 가져올 때 다른 사람이 insert 혹은 delete해서 내가 고른 것이 달라질 수도 있음. 이를 방지하기 위한 방법 (Session에서...)
SELECT last_insert_id() AS staff_id;

-- 계정
-- 시스템관리자 / 일반 사용자 / 승인 권한 사용자 / 창고 관리자
-- UI는 총 3가지 만들기 (시스템관리자 전용 / 사용자 전용 / 창고 관리자 전용)

-- 입고요청 → 입고대기(자동전환) → 검수요청(수동입력) → 입고완료(자동전환)
-- 출고요청 → 출고대기(자동전환) → 검수요청(수동입력) → 출고완료
-- 일반사원 → 팀장급 사원(컨펌) → 총무팀장(컨펌) → 완료

-- 조회카테고리 대분류 : 출고번호별 조회, 거래처별 조회, 품목별 조회, 일자별 조회
-- 조회카테고리 필터 : 기간, 출고번호, 거래처, 품목, 일자 (대분류 선택시 필터 하나만 빼기)

-- 입출고 등록 테이블
-- No, 선택박스, 입출고번호, 입출고일자, 거래처, 부서, 사원, 관리항목, 공급가합계액, 총합계액

-- 입고제품 상세보기 테이블
-- No, 상품코드, 상품명, 단위, 납기일자, 재고수량, 단가, 공급가액, 부가세, 합계금액, 상품설명











# oracle