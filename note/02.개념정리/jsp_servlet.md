# javaEE / 웹프로그램 / 웹애플리케이션 형태

## 38일차 수업(25.06.16.)
- Tomcat 설치 
- JAVA_HOME 환경변수 설정
- Tomcat bin 디렉토리에서 cmd 실행 후 dir 선택하여 startup 실행 (서버... 닫지 말 것)
- 서버 포트 변경 후 접속 ( http://localhost:8080 )
- 기본 localhost 충돌 시 localhost 주소 수정 (Tomcat > conf > server 메모장 혹은 vscode로 수정)

- http://IP주소:localhost주소/실행할html명


java 프로젝트 → 애플리케이션 생성

jsp 프로젝트 → 웹사이트 생성

mysite > WEB-INF/classes/src

자바의 클래스 중, 오직 java EE 기반의 서버에서만 해석 및 실행될 수 있는 클래스를 가리켜 **서블릿(servlet)**이라 함..

서블릿으로 정의하기 위해서는 서블릿을 상속 받아야 함
```
Database Driver와 비슷하게 (jdbc...) java의 기본 구현 틀을 따라주어야 함

API 구현체(TomCat의 jar 등..) - HttpServlet... 
doGet()메서드..
```

서블릿은 밴더사에서 java의 기준에 맞춰 javaEE 구현체로 만든다.

서블릿 jar은 WEB-INF/lib 디렉토리에 존재해야 함

---

## 40일차 수업(25.06.18.)
server 내 webapp 디렉토리 밑에 html 파일을 보관할 디렉토리를 생성한다.

유저가 작성한 폼 양식을 자바 기반의 서버로 전송해야 함
왜 js는 직접 mysql에 대해 insert하지 않는가?
보안상 js의 코드는 html과 함께 작성되므로 안전하지 않기 때문임
따라서 직접 insert가 불가하므로, 원격지인 서버에 간접적으로 부탁만 할 수 있음

이렇게 웹기반 즉 http 프로토콜 기반에서, 클라이언트인 브라우저 측이 
서버 측에 무언가를 부탁하는 통신 행위를 가리켜 요청(request)라고 함

서버 측으로 전송할 파라미터들을 묶어서 보낼 때 사용하는 태그 => form
html의 입력양식을 이루는 컴포넌트를 대상으로 어디부터, 어디까지를 전송할 지 결정

form 태그의 action: 어느 서버에 전송할지 결정짓는 주소
ex) <form action="http://localhost:8282/member">
※ 클래스는 직접적으로 주소로 참조될 수 없으므로 mapping이 필요함
WEB-INF 하위에 있는 web.xml에 mapping 해야함

클라이언트가 서버에게 전송하고 싶은 것을 표현 : post
ex) <form action="http://localhost:8282/member" method="post">

action에서 http://localhost:8282 생략 가능
이 때 action에 남은 /의 의미 : 이 html을 배포한 원천 서버(origin)의 주소 루트
위 예시의 경우 http://localhost:8282/를 의미함


웹브라우저인 클라이언트가, POST로 전송을 하면, 서버는 반드시 doPost() 메서드로 요청을 처리해야 함
네트워크를 타고 전송된 파라미터와 그 값들을 처리하고 싶을 때 사용되는 객체는 '요청' 정보를 갖는 request 객체임
```java
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
```
웹은 서버와 연동되는 상태가 유지되지 않음(stateless)

## 41일차 수업(25.06.19)
web.xml은 서버 가동 시 읽어들임 : 따라서 서버 가동 이후 그 코드가 변경되었다면 서버 재가동 해야 함
servlet : 서버가 실행 시 서블릿이 이미 반영되어 있음. 이후 서블릿이 수정되면 서버를 재가동해야 수정된 서블릿이 반영됨
tomcat server 재가동 시점 : 

## 42일차 수업(25.06.20)
### JSP란? Java Server Page! 즉, 자바기술로 만든 서버 측에서 실행되는 페이지

서블릿(Servlet)과 목적이 같다.

서블릿 : 디자인을 표현 가능. 
		그러나 html 문서를 모두 문자열로 처리해야 하나, 효율성이 극도로 떨어짐..
		
따라서, 서블릿과 jsp 중 누구를 사용해야 하느냐가 고민된다면 판단 기준은? \
현재 처리하려는 코드에서 디자인이 포함되어 있는지 판단하면 됨

```
JSP는 서블릿이다 (O)
서블릿은 JSP이다 (X)

JSP를 서블릿으로 변경하는 주체는 톰캣과 같은 웹컨테이너이다.

JSP가 서블릿으로 변경되는 시점은 최초의 클라이언트의 요청이 있을때 인스턴스가 생성되며, 인스턴스 생성(init) 이후부터는 요청이 있을 때마다 쓰레드가 생성되어 요청을 처리함

서블릿 인스턴스가 메모리에 올라가는 시점은 컴파일 후, 최초로 클라이언트의 요청이 있을 때이다.

init()메서드는 서블릿의 생성자가 아니다.
생성자가 먼저 호출되고, init()이 호출됨
```

주요 경쟁자) ASP,PHP와 같은 서버측에서 실행되는 스크립트 언어와 경쟁...

java
```java
<body>
	for(int i=0; i<30 >;i++){
		x
	}
	// 불가능한 표현 방법 --> jsp 이용
</body>

```
```java
<body>
	<% for(int i=0; i<30; i++){ %>
	<%="나는 jsp예요 <br>" %> // <% %> 스크립틀릿 영역으로 감싸 script 표현 가능
	<% } %> 
</body>
```

```java
JSP 코드를 작성할 수 있는 영역

1) <%@ page %> 지시 역역 - 현재 페이지에 대한 인코딩 등 페이지 자체에 대한 설정 정보 작성
						ex) 인코딩 다국어: utf-8 / mimeType : text/html, imae/jpeg... 등
2) <%! %> 선언부 - 멤버 변수와 멤버 메서드를 작성할 수 있는 영역 
3) <% %> 스크립틀릿 영역 - 개발자가 로직을 작성할 수 있는 메서드 영역
4) <%=데이터(문자, 숫자, 논리값, 객체)%> out.print(); 의 줄임 표현 

```

### JSP의 실행 위치 - 오직 서버에서만 실행된다..
- 따라서 jsp, asp, php와 같은 언어를 가리켜 Server Side Script 언어라고 함 \
- job korea - 서버 개발자 모집: jsp기반 개발자
						
### javascript의 실행 위치 - 클라이언트의 웹브라우저에서 실행됨 
- Client Side Script 언어
- 클라이언트에게 노출 목적의 언어이므로 보안과 관련된 정보는 절대로 JavaScript에 넣지 않음

전송 파라미터 : `?=변수`

```
서블릿의 인스턴스는 클라이언트의 요청이 있을 때마다 메모리에 생성되어지지 않음! 
요청마다 쓰레드가 생성됨! → 서비스를 실행함
```

---
## 개발
```
서버용 OS - 리눅스 (무료)
개발 서버 - tomcat/Resin(유료)
개발 DB - MySQL 
개발 언어 - jsp, java, php
(중소형)
```
```
서버용 OS - 유닉스
개발 서버 - WAS(WebLogic/JBoss)
개발 DB - Oracle

(대형)
```

## 43일차 수업(25.06.23)
### HTTP
- 서버 전송 방법 : GET / POST

GET : 데이터가 HEAD에서 노출됨 // BODY 필요 없음

POST : BODY에 데이터가 저장됨 // BODY가 필요함


HTTP에서 웹브라우저(클라이언트)가 서버에 원하는 내용을 요청하면, 서버는 이에 대한 결과를 적절한 형식으로 응답해줌 → HTML, XML, Image 등 웹브라우저가 해석 및 실행 가능한 형식이라면 모두 가능
```
- HTML: 디자인 표현 중시
- XML: 데이터 구조화 중시

※ if, 서버의 응답형식을 웹브라우저가 해석 또는 실행가능한 형식이 아닌 경우, 해당 파일을 다운로드 함
→ 응답형식을 .zip으로 설정하여 자료실로 만들기도 함
→ 그래도 출력스트림을 이용하는게;;
```

정적 자원 : 웹서버 상 이미지, html, css, js 코드를 올려 웹브라우저로 이 자원을 요청하게 될 경우, 웹서버는 이 자원들을 해석 및 실행하는 것이 아닌 파일로 저장하여 전송해줌. 이 때의 자원을 정적자원이라고 함 (웹브라우저가 실행함)

동적 자원 : jsp, servlet

404에러(전세계 표준) : 서버에 해당 자원이 없음을 표현함

소켓통신 - stateful : 항상 연결되어 있음
http통신 - stateless : 보내주고 서버 단절됨

websocket? 
```
http 통신 + socket 통신
http통신은 끊어지지만 코드를 통한 socket통신으로 통신이 이어짐
```

### 서블릿
Servlet(인터페이스) > GenericServlet(클래스) > HttpServlet(클래스)

```
Servlet
init, service, destroy 메서드 보유

HttpServlet
doXXX 메서드를 보유
```

분기???? service() → doXXX...

HttpServletRequest : Servlet의 요청객체
request.getParameter의 request: JSP의 요청객체

결국 JSP에서의 request = servlet에서의 HttpServlet과 같음

HttpServletRequest, HttpServletResonse 객체는 인터페이스이므로 프로그래머가 new 할 수 없으며
요청이 있을 때마다 컨테이너에 의해 메모리에 올라간다.

Sevlet은 웹용 API가 아님.. HttpSevlet이 웹용 API..

name → 변수역할 해줌... 다만 변수가 아니라 파라미터라고 부름

## 48일차 수업(25.07.01)
### Web Dynamic Project 생성
#### 환경준비
- Eclipse IDE for Enterprise Java & Web Developers 설치 (JavaEE 환경 준비)

- Java JDK 1.8 이상 설치

- Apache Tomcat 또는 원하는 애플리케이션 서버 설치

- 서버를 Eclipse에 등록

#### 프로젝트 생성
- File → New → Dynamic Web Project

- Project Name: 예) board

- Project > Properties > Project Facetes > Runtimes > 서버 등록된 TomCat 버전 선택

- Dynamic Web module version: 보통 3.0 또는 3.1

- Java : Tomcat version과 맞게 설정 (ex. Tomcat 8.5 & Java 1.8)

- Configuration: Java EE 옵션 선택

#### 프로젝트 구조 구성
```
YourProject/
├── src/main/java ← Java 클래스
├── / webapp/     ← 웹 리소스 (JSP, 이미지 등)
│   ├── META-INF
│   ├── notice/     
│   │   ├── list.jsp            
│   │   └── write.jsp
│   └── WEB-INF/
│       ├── lib/      ← 외부 라이브러리 (필요 시)
│       └── web.xml   ← Deployment Descriptor      
```

#### web.xml 설정
```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://xmlns.jcp.org/xml/ns/javaee" xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd" id="WebApp_ID" version="3.1">
  <display-name>board</display-name>
  <servlet> 
  	  <servlet-name>registServlet</servlet-name> 
  	  <servlet-class>com.sinse.board.RegistServlet</servlet-class>
  </servlet>
  <servlet-mapping> 
      <servlet-name>registServlet</servlet-name>
      <url-pattern>/notice/regist</url-pattern>
  </servlet-mapping> 
</web-app>
```
1) `<servlet>`: 브라우저에서 요청하는 매핑 이름에 대해 실제로 실행하는 서블릿 클래스 설정
    - `<servlet-name>`: <servlet>과 <servlet-mapping>을 연결
    - `<servlet-class>`: 브라우저에 요청하는 매핑 이름에 대해 실제로 기능을 수행하는 서블릿 클래스

2) `<servlet-mapping>`: 브라우저에서 요청하는 논리적인 서블릿 설정
    - `<url-pattern>`: 클라이언트가 요청하는 논리적인 서블릿 이름

### Library용 jar가 아닌 실행용 jar
- java -jar [lombok.jar]

### summernote
- 자바스크립트 객체 json // json 문자열 아님
- attribute가 많을 땐 json 형식으로 간다.

### HTTP 통신
- HEAD / BODY
- HEAD로 주고받는 형식 : GET 방식 (메타 정보 보유)
- BODY로 주고받는 형식 : POST 방식 (자세하고 다양한 내용 보유)

### 커넥션 풀링
- DB연동을 위해 DriverManager.getConnection(Connection) 필요
- 서버에 클라이언트가 요청할 때마다 Connection 호출, 요청 종료 시마다 Connection.close() 반복하면 비용적, 시간적 손실 발생
- 그래서 사용자가 없는 Connection 다수를 미리 준비함. 이는 close하지 않으며, 클라이언트 요청 시 대여해 줌
- 이 Connection들은 List로 미리 준비해두며, 이를 커넥션풀링이라고 함 (like 여름철 튜브 장사, 튜브들 미리 바람 넣어두기)

### 게시판 (7~10일)
- 일반 게시판
- 코멘트 게시판
- 비동기 게시판
- 갤러리
    → DB 커넥션 풀링
    → HIbernate (JPA)
    → Mybatis

###
- 웹소켓
- oauth


## 49일차 수업(25.07.02)
### 엔터프라이즈 개발자
```
표현계층 (Presentation Layer) : html, js, css...
→ Business Layer : DAO...
→ Persistence Layer : Mybatis...
```

## 50일차 수업(25.07.03)
- include?
- mybatis 
    - collection 1:多 / association 1:1
    - jtbc commection → 커넥션풀 유틸 클래스를 만들었으나 서버에서 지원함
    - weblogic, tomcat 등 웹컨테이너는 자체 커넥션풀을 보유하고 있음
    
- WAS(Web Application Server : Web Container + Web Server + 추가적인 비즈니스 로직)
    - Web Container: JavaEE 기술을 이해하는 애플리케이션. 서블릿이나 jsp(java server page)를 실행함
    - Web Server: 정적 자원(html, css, image)을 전담하는 서버 IIS, Apache 이메일 서버, FTP, DNS Server


## 51일차 수업(25.07.04)
- tomcat/server.xml: GlobalNamingResources에 커넥션풀 추가
- Java Naming and Directory Interface
    - 자원 이름 기반 접근: DB 커넥션, EJB, JMS 등 다양한 자원을 이름으로 조회 가능
    - WAS 중심 설정: DB 연결 정보 등을 WAS(Web Application Server)에 설정하고, 애플리케이션은 JNDI로 조회만 함
    - 코드와 설정 분리: DB 정보가 코드에 하드코딩되지 않아 유지보수 용이
    - 표준화된 접근 방식: 다양한 네이밍/디렉터리 서비스(LDAP 등)와 연동 가능

    * 장점
    - 중앙 집중형 자원 관리: 여러 애플리케이션이 동일한 설정을 공유 가능
    - 유지보수 용이: DB 정보 변경 시 코드 수정 없이 WAS 설정만 변경하면 됨
    - 보안성 향상: 민감한 정보(DB URL, 계정 등)를 코드에 노출하지 않음
    - 커넥션 풀 활용: 효율적인 DB 커넥션 관리 가능

    * 단점
    - 설정 복잡성: WAS 설정 파일(server.xml, context.xml 등)을 잘 알아야 함
    - 초기 학습 난이도: JNDI lookup, InitialContext 등 개념이 생소할 수 있음
    - 보안 이슈 가능성: 잘못된 설정이나 외부 입력을 통한 JNDI 인젝션 위험 (예: log4j 취약점)

[정식 운영 환경에서 JNDI 활용하기]
server.xml 편집 → GlobalNamingResource 내부에 Resource태그 추가
context.xml 편집

[테스트 개발 환경에서 JNDI 활용하기]
server.xml 편집 → GlobalNamingResource

웹컨테이너(Tomcat)가 제공하는 Connection Pool 사용해보기
```java
<Resource
  		name="jndi/mysql"                           // Java Naming Directory Interface를 이용하겠다. mysql을
  		auth="Container"                            // webContainer가
  		type="javax.sql.DataSource"                 // type은 javax.sql의 DataSource로 설정
  		driverClassName="com.mysql.cj.jdbc.Driver"  // Class.forName에서 사용하던 MySQL driver (maven을 통한 mysql.jar의 해당 위치에 드라이버가 있음)
  		url="jdbc:mysql://localhost:3306/******"    // DriverManager 객체를 통해 getConnection 메소드에 이용될 url (jdbc:mysql://ip주소:포트번호/데이터베이스명)
  		username="****"                             // getConnection 메소드에 이용될 username (MySQL 데이터베이스 내의 이용할 유저명)
  		password="****"                             // getConnection 메소드에 이용될 password (해당 username에 설정된 password)
  		maxTotal="20"                               // 최대 커넥션풀 개수
  		maxIdle="10"                                // 유휴 상태로 유지할 수 있는 최대 커넥션 수 (사용중이지 않을 때 닫아둘 수 있는 최대 커넥션 수로 낭비를 방지하기 위함)
  		maxWaitMilis="-1"                           // 커넥션이 모두 사용 중일 때 대기할 최대 시간(ms): -1이면 무한 대기
  	/>
```





### 로그 레벨
```
자바의 로그 레벨 trace < debug < info < warn < error < fatal

```

### 세션
- 세션ID, 쿠키, 흔적...

## 52일차 수업(25.07.07)
### 비동기 방식의 Hibernate 연동!
- 새로고침 없는 애플리케이션


### SPA (Single Page Application) 실습
- tomcat을 통해 select 박스 내 들어갈 아이템 전달받기
- 그 동안의 방식을 통해 받게 되면 html 파일로 받기에 새로고침이 일어남
- 새로고침이 아니라 아이템만 받고 싶다. 즉, 데이터로 받아보자

```java
response.setContentType("text/html; charset=utf-8"); // html 새로고침 발생 (X)
response.setContentType("application/json; charset=utf-8"); // 데이터만 변경 (O)

```

#### 기본 세팅(maven 환경 설정)
- lombok 설치 (getter, setter)
- mysql jdbc 설치 (db 연동)
- logback 설치 (로그 출력)
- hibernate-core 5.6.15.Final (hibernate 연동)

#### 공통 코드 설계 - DB 연결 및 인코딩 처리
| 항목 | 설명 |
|------|------|
| `hibernate.cfg.xml` | DB 연결 정보 설정 (JDBC URL, 드라이버, 사용자명 등) |
| `HibernateConfig` | SessionFactory를 관리하는 싱글톤 객체 |
| `CharacterEncodingFilter` | 클라이언트 요청/응답에 UTF-8 인코딩 적용 |
---
#### 음식 타입 구현 (선행 데이터)
**model**  
  `FoodType.java` → `@Entity`, `@Table(name="food_type")`

**repository**  
  `FoodTypeDAO.java` → `selectAll()` 작성

**servlet**  
  `FoodTypeList.java`  
  `GET /foodtype/list` 요청에 대해 JSON 배열 응답

**js/HTML**  
  셀렉트 박스를 화면에 구성하고, Ajax를 통해 리스트를 받아 출력

> ✅ `FoodType`은 등록용 폼에서 반드시 먼저 필요하므로, 1순위로 개발됩니다.
---

#### 상점 등록 기능
**model**  
  `Store.java` → `@Entity`, 음식 분류는 `@ManyToOne`

**repository**  
  `StoreDAO.java` → `insert()` 메서드 작성

**servlet**  
  `StoreRegist.java`  
  `POST /store/regist` 처리 → `201 Created` 또는 JSON 메시지 응답

**js/HTML**  
  등록 버튼 클릭 시 폼 데이터를 Ajax로 서버에 전송 → 성공 시 목록 다시 가져오기

---

#### 상점 목록 조회
**servlet**  
  `StoreList.java`  
  `GET /store/list` → JSON 배열 반환

**js/HTML**  
  테이블 출력 함수를 구현하고, 등록 성공 또는 초기 페이지 로딩 시 목록 가져오기

---

#### 상세보기 기능 

**servlet**  
  `StoreDetail.java`  
  `GET /store/detail?store_id=1` 요청 처리

**js**  
  상호명을 클릭하면 해당 `store_id`로 요청 → 상세정보 표시

---

#### 공통 예외처리 및 응답 메시지

- **exception**  
  `StoreException`, `FoodTypeException`

- **util**  
  `Message.java`  
  → 응답 형태를 통일된 구조(JSON)로 보낼 수 있도록 객체 구성

---

#### 공통 예외처리 및 응답 메시지

- **exception**  
  `StoreException`, `FoodTypeException`

- **util**  
  `Message.java`  
  → 응답 형태를 통일된 구조(JSON)로 보낼 수 있도록 객체 구성

---

## 53일차 수업(25.07.08)
### SPA (맛집 검색 페이지) 예제 실습
#### 1단계: 기획 및 데이터 모델 설계
- 기능 
  - 음식 유형별 음식점 등록
  - 등록된 음식점 리스트 출력
  - 리스트에서 음식점 선택하여 상세 내용 확인 
  - 상세 내용에서 수정, 삭제 기능

- ERD 설계
  - FoodType 테이블 + Store 테이블 (Food_type_id=fk)
  - 1:多 관계: 한 음식 유형에 대해 여러 가게가 있음

#### 2단계 : Hibernate 설정 및 Entity 정의(모델 설정)
``` java
@Data // Lombok.. getter/setter 세팅용
@Entity // 엔터티 선언을 해주어야 함
@Table (name="store") // 테이블명 지정
public class Store {
  @Id // primary key를 지정함
  private int store_id;
  private String store_name;
  private String tel;

  @ManyToOne // 현재 클래스(many) 기준 ManyToOne 지정; 여러 가게가 한 유형에 소속
  @JoinColumn(name="food_type_id") // DB테이블의 foreign key 컬럼명 지정
  private FoodType foodType; // food_type_id가 아닌 FoodType객체를 보유함
}
```
| 항목 | 설명 |
|------|------|
| `hibernate.cfg.xml` | DB 연결 정보 설정 (JDBC URL, 드라이버, 사용자명 등) |
| `HibernateConfig` | SessionFactory를 관리하는 싱글톤 객체 |


#### 3단계 : DAO 개발
- hibernate에서 제공하는 Transaction객체 사용
- session을 try-with-resources 방식을 적용하여 사용
- try문 내에서 `session.beginTransaction()`으로 시작하여 `tx.commit()`으로 마무리

```java
# selectAll(){} 
  1) TypedQuery query = session.createQuery("from Store", Store.class);
  2) List list = query.getResultList();
  3) return list;

# select(int store_id){}
  1) 매개변수로 모델pk값 받아오기
  2) 해당 pk값의 모델 조회하기
     Store store = session.get(Store.class, store_id); 
  3) return store;
  
# insert(Store store){}
  1) 매개변수로 모델 받아오기
  2) session.save(store)

# update(Store store){}
  1) 매개변수로 모델 받아오기
  2) 선택한 모델 수정하기
     session.update(store)

# delete(int store_id){}
  1) 매개변수로 모델pk값 받아오기
  2) 모델 pk값으로 모델 생성하기
     Store store = session.get(Store.class, store_id);
  3) 선택한 모델 삭제하기 
     session.delete(store)

# 공통
catch문 이하에선 rollback 및 throw StoreException();
```

### 4단계: 서블릿 구성
- 클라이언트의 파라미터 받기(request 객체 활용)
- DAO를 통해 쿼리문 수행(CRUD)
- 응답은 JSON 형식으로 함
```java
String store_id = request.getParameter("store_id");
...
Store store = new Store();
store.setStore_id(Integer.parseInt(store_id));

// 응답 정보 생성
response.setContentType("application/json"); // html이 아닌 json 형태로! 새로고침 없는 SPA 구성을 위함
Message message = new Message(); // 응답 정보를 담을 객체(클래스 생성)
Gson gson = new Gson();
PrintWriter out = response.getWriter();

storeDAO.insert(store);     // C(insert)인 경우
storeDAO.select(store_id);  // R(select)인 경우
storeDAO.update(store);     // U(update)인 경우
storeDAO.delete(store_id);  // D(delete)인 경우

message.setResult("fail");  // 성공인 경우 success
message.setMsg(e.getMessage()); // 성공인 경우 CRUD 성공
String jsonStr = gson.toJson(message); // 문자열로 변환: toJson이 핵심!
out.print(jsonStr);
```

### 5단계: 클라이언트 구현 (index.html + jquery + ajax)
- ajax 사용 환경 세팅
```js
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
```

### ajax 기능 추가 사용
- POST방식 (url, type, data, success, error)
```js
$.ajax({
  url:"/store/edit",  // servlet 호출 url (web.xml 매핑!) 
  type: "POST",       // 데이터 전송 방식 
  data: {             // POST 방식인 경우, 보낼 data를 정의해야 함 (뭘 보낼건데?)
    food_type_id: $("#aside_detail select").val(),
    store_id: $("#aside_detail input[name='store_id']").val(),
    store_name: $("#aside_detail input[name='store_name']").val(),
    tel: $("#aside_detail input[name='tel']").val()
  },
  /* 성공 시 출력할 내용 */
  success:function(result, status, xhr){
    console.log("result=",result),  // result: 서버에서 응답한 JSON 데이터
    console.log("status=",status),  // status: "success" 문자열 (응답 상태)
    console.log("xhr=",xhr)         // xhr: XMLHttpRequest 객체 (HTTP 상태코드 확인 가능)
    
    if(xhr.status==204){
      getStoreList();
    }
  },
  /* 에러 발생 시 출력할 내용 */
  error:function(xhr, status, err){
    // xhr.status → HTTP 상태 코드 (예: 400, 500)
    // status → "error"
    // err → 브라우저가 제공하는 에러 메시지
  }
});
```

- GET방식 (url, type, success, error)
```js
$.ajax({
      /* GET 방식: 데이터를 URL 쿼리스트링을 통해 전송, 서버는 이를 request.getParameter()로 처리 */
      url:"/store/detail?store_id="+store_id, // servlet 호출 url
			type:"GET",                             // 데이터 전송 방식 
			success:function(result, status, xhr){
				$("#aside_detail select").val(result.foodType.food_type_id);
				$("#aside_detail input[name='store_id']").val(result.store_id);
				$("#aside_detail input[name='store_name']").val(result.store_name);
				$("#aside_detail input[name='tel']").val(result.tel);
			},
			error:function(xht, status, err){
				
			}
		});
```