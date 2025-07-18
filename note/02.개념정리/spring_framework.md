### jsp&Servlet → MVC패턴 기반 프레임워크 스프링제작 → Spring4 framework
- JSP & Spring
- Spring 4
- 8월 초 프로젝트 발표
- 8월 초 프로젝트 발표
---


## 54일차 수업(25.07.09)

### 🛠️ Spring Framework를 직접 만들어보며 배우는 MVC 구조

#### 📌 What to do가 아닌 How to do!
- *무엇을 만들지*보다 *어떻게 만들지*를 고민하는 개발자로 성장하기 위한 시작
- 실무에서 주로 사용하는 **디자인 패턴**과 **웹 애플리케이션 구조**를 직접 설계하고 구현해보는 과정
- 특히 **Spring 프레임워크의 구조를 직접 구현**해보며 핵심 원리를 체득하는 것이 목표

---

### 🏢 SI업체와 정부 프레임워크

#### ✅ SI업체
- 회사마다 자체 프레임워크를 보유하거나 커스터마이징하여 사용
- 유지보수성과 일관성 확보 목적
- 공공기관에서 업체를 바꾸면 유지보수에 문제가 생겨 비용 증가 발생이 빈번하였음

#### ✅ 정부
- 위의 고민으로 표준 프레임워크를 제시하여 SI사들이 기준에 맞추는 효과를 얻음
- 대표적 예: **한국전자정부프레임워크**
- Spring 기반으로 구성
- MVC 기반의 표준 구조와 공통 모듈 제공
- 다양한 공공 프로젝트에 적용됨

---

### 🧩 MVC 패턴이란?

#### ✔️ 핵심 개념
| 역할 | 설명 |
|------|------|
| Model | 데이터 처리 로직 (DTO, DAO, Manager 등), 비즈니스 로직 포함 |
| View | 사용자에게 보여지는 화면 (JSP 등) |
| Controller | 요청 분석, 처리 흐름 제어, View 선택 |

> 👉 디자인과 비즈니스 로직을 구분하여 **관심사의 분리(SoC)** 실현

---

#### ⚖️ 막개발 vs MVC 구조

| 구분 | 막개발 | MVC 패턴 |
|------|--------|----------|
| 장점 | 빠른 개발 가능 | 유지보수, 협업, 재사용 용이 |
| 단점 | 유지보수 어려움, 로직 중복 | 컨트롤러/매핑이 많아질 수 있음 |
| 구조 | JSP에서 DB까지 모두 처리 | Controller + Model + View 명확 분리 |

---

### 🧠 MVC 구조의 발전 방향

#### ❗ MVC의 단점
- 기능이 많아질수록 `Controller` 클래스 수가 급증
- 요청별로 일일이 매핑해야 하므로 구조가 비대해짐

#### 💡 해결 방법
- **Front Controller**: 모든 요청을 하나의 서블릿이 받고, 내부적으로 분기 처리
- **Command Pattern**: 기능별 1:1 대응하여 클래스를 만들어 위임하고, 공통 인터페이스로 처리 (다형성 활용) 

---

### ⚙️ Command Pattern

#### 📌 필요성
- Controller가 모든 요청을 if문으로 처리하면 유지보수 어려움
- → 기능별로 클래스를 분리하고, 공통된 `execute()` 메서드로 실행

#### ✅ 요청 처리 5단계 흐름

1. 요청을 받는다 (`DispatcherServlet`)
2. 요청을 분석한다 (`URI`, 파라미터 등)
3. 일을 시킨다 (Model 호출 → DAO 또는 Manager)
4. 결과를 저장한다 (`request.setAttribute()`)
5. 알맞는 View를 선택한다 (`getViewPage()` → JSP로 forward)

---

### 🧪 직접 만든 구조 요약

#### 🏁 DispatcherServlet
- 모든 요청의 진입점
- `web.xml`에 등록, `init()` 시 `Properties` 파일 로딩
- `doGet/doPost → doRequest()`로 일원화 처리
- `Class.forName()`으로 Command 객체 동적 생성

#### 🧱 Controller 인터페이스
- 모든 컨트롤러는 `execute(request, response)` 구현
- `getViewPage()`로 뷰 경로 반환

#### 🧾 Properties 매핑파일
```properties
/memberJoin.do=com.mvc.controller.MemberJoinController
/memberList.do=com.mvc.controller.MemberListController
```

### 🧰 Filter와 Encoding 처리
#### 🔠 CharacterEncodingFilter

```java
request.setCharacterEncoding("UTF-8");
chain.doFilter(request, response);
```
#### 🔍 chain.doFilter() 위치에 따른 의미
| 위치 | 처리 시점   | 설명              |
| -- | ------- | --------------- |
| 이전 | 요청 전 처리 | 한글 인코딩, 인증 검사 등 |
| 이후 | 응답 후 처리 | 로그 기록, 리소스 정리 등 |

#### ☀️ 비유: 편광 필터
- 필터를 통과하기 전 → 원본 광선

- 필터를 통과한 후 → 편광 처리된 결과

- chain.doFilter()는 이 경계선에 해당

### 🆚 Model1 vs Model2(MVC)
| 구분   | Model1        | Model2 (MVC)        |
| ---- | ------------- | ------------------- |
| 구조   | JSP가 View+Controller 수행 | Model + Controller + JSP(View) 분리 |
| 확장성  | 낮음            | 높음                  |
| 사용 예 | 단순한 게시판       | 대규모 웹 프로젝트          |

### 📘 결론
이 구조는 단순한 웹 프로젝트를 넘어서,
Spring MVC의 핵심 원리를 직접 구현해보는 과정이다.

"Spring을 만든다"는 관점에서,
단순 사용자가 아닌 설계자 시야로 패턴과 구조를 익힐 수 있다.

## 55일차 수업(25.07.10)
- Web이 아닌 Swing으로 MVC 패턴을 이용해보자
- Model의 능력: web에서도 Swing에서도 모델은 그대로 사용 가능하다?!!

### 🖥️ 1. Swing 기반 MVC 패턴

- **🔄 Model 재사용성**
  - Web 애플리케이션용으로 작성된 **DAO · Service · DTO** 등의 비즈니스 로직을 그대로 Swing 애플리케이션에 재사용
  - Swing에서는 **View**(`JFrame`/`JPanel`)와 **Controller**(`ActionListener`)만 새로 구현하면 됩니다

```java
// 예시: View
public class BloodView extends JFrame {
    // 테이블, 버튼 등 컴포넌트 정의
    public BloodView() {
        setTitle("혈액 정보 관리");
        // 레이아웃 설정, 컴포넌트 배치
    }
}

// 예시: Controller
public class BloodController {
    private BloodModel model;
    private BloodView view;

    public BloodController(BloodModel model, BloodView view) {
        this.model = model;
        this.view = view;
        view.getBtnLoad().addActionListener(e -> loadData());
    }

    private void loadData() {
        List<Blood> list = model.fetchAll();
        view.updateTable(list);
    }
}
```

### 🌐 2. Web MVC 복습

### 🚦 Front Controller vs 세부 Controller 구조

#### 🧭 Front Controller (DispatcherServlet)

- 모든 클라이언트 요청을 최초로 수신하는 메인 컨트롤러

- 요청 URI에 따라 어떤 기능(세부 컨트롤러)을 수행할지 결정

- 매핑 파일 (.properties)에서 URI에 해당하는 클래스명(String) 을 조회

- 해당 클래스명을 기반으로:

```Java
Class.forName(className) // JVM 메소드 영역에 클래스 로드

Controller controller = (Controller)clazz.newInstance(); // 힙 메모리에 인스턴스 생성
``` 
- 생성된 세부 컨트롤러 인스턴스를 통해 **`execute(request, response)`** 메서드 실행

#### ⚙️ 세부 Controller (ex. ListController, RegistController)

- Front Controller가 실행한 실제 비즈니스 로직 담당 컨트롤러

- 공통 인터페이스(예: Controller)의 `execute()` 메서드를 구현

- 내부적으로 Model 호출, 결과 저장, View 경로 반환 처리

#### 📂 구조화된 흐름 요약
```
ServletContext context = config.getServletContext();

String realPath = context.getRealPath(config.getInitParameter("contextConfigLocation"));

요청 URI → DispatcherServlet → 매핑 파일 확인
            ↓
    Class.forName(className) (메소드 영역)
            ↓
    clazz.newInstance() (힙 영역)
            ↓
    controller.execute(request, response)
            ↓
    Model 호출 → 결과 처리 → View 포워딩
```

✅ 이 구조는 다양한 컨트롤러를 동적으로 처리할 수 있어 유지보수성과 확장성이 뛰어남

#### 🔄 Forward vs Redirect

|구분|Forward|Redirect|
|-|-|-|
|요청 횟수|1회 (서버 내부 이동)|2회 (`302 응답` + 클라이언트 재요청)|
|URL 변화|**유지**|**변경**|
|데이터 전달|같은 request 공유 <br> `request.setAttribute()` 사용|새 request 생성 <br> → `session.setAttribute()` 또는 URL 파라미터|

```java
// Forward 예시
dispatcher.forward(request, response);
// Redirect 예시
response.sendRedirect(request.getContextPath() + "/list.do");
```

#### 🗂️ request.setAttribute vs session.setAttribute

- `Forward` 시: 한 페이지 렌더링용 일회성 데이터 전달 → `request.setAttribute()` 권장

- `sendRedirect` 시: 새 요청이므로 request 사라짐 → 다음 요청에도 유지할 데이터는 `session.setAttribute()` 사용

### 🔧 3. Filter와 인코딩 처리

#### 📝 Filter 인터페이스 메서드

```java
init(FilterConfig filterConfig)

doFilter(ServletRequest, ServletResponse, FilterChain) (반드시 구현하고 chain.doFilter() 호출)

destroy()
```

#### 🔠 인코딩 설정 위치

```java
public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
    // 1) 요청 파라미터 해석 전
    request.setCharacterEncoding("UTF-8");
    // 2) 응답 메시지 출력 전
    response.setCharacterEncoding("UTF-8");
    // 3) 다음 필터/서블릿 호출
    chain.doFilter(request, response);
    // 4) (Optional) 후처리 로직
}
```
- `체인 이전`에 인코딩 설정 필수 → 요청 파라미터 및 응답 메시지 모두 올바른 문자셋으로 처리

- `체인 이후`에 설정 시 이미 파라미터 해석·출력이 끝나 효과 없음

#### 🔗 Filter 체인과 실행 순서

- 여러 Filter 클래스가 등록된 순서대로 `doFilter()` 한 번씩 호출

- 순서 제어

    - `전통 Servlet`: web.xml `<filter-mapping>` 내 순서

    - `Spring Boot`: @Order 또는 `FilterRegistrationBean.setOrder()`

### ✨ 핵심 요약

- Swing 프로젝트에도 MVC 패턴 적용 → `Model 재사용`

- `Web MVC`: Forward/Redirect 차이와 request·session 속성 활용 방법

- `Filter`: 한 클래스의 doFilter() 한 번 구현, 인코딩 설정은 체인 이전에


## ✅ 56일차 수업 요약 (2025.07.11)

### 📚 학습 목표

- MVC 패턴 구조 이해 및 적용
- DispatcherServlet 기반 Command Pattern 프레임워크 설계
- JSON 기반 설정 파일을 통한 동적 매핑
- Controller 인스턴스 재사용 구조 구현
- Service 계층 도입 및 트랜잭션 관리
- MyBatis의 `<selectKey>` 동작 원리 이해

---

### 🧠 1. 기존 Command Pattern 구조의 문제점

#### 🔧 구조
기존에는 요청이 들어올 때마다 `properties` 파일을 읽어
- key = URI
- value = Controller 클래스 이름

이 값을 기준으로 매번 `new Controller()` 인스턴스를 생성함.

#### ❗ 단점
- 매 요청마다 파일 I/O + new 연산 → **비효율적**
- 상태 유지 불가 (동일한 컨트롤러라도 매번 새 객체)
- GC 부담 증가

> 🧠 **비유**  
> 손님이 올 때마다 알바를 새로 뽑는 식. 효율도 떨어지고, 일도 못 맡김.

---

### 🔄 2. Controller 인스턴스 미리 생성 & 저장

#### ✅ 해결 방법
DispatcherServlet이 초기화될 때 미리 JSON 설정 파일을 읽어  
→ 모든 Controller를 **한 번만 생성하여 Map에 저장**

```json
{
  "mappingType": "myframework.web.handler.SimpleUrlHandlerMapping",
  "controllerMappings": {
    "/admin/notice/list": "myframework....ListController",
    "/admin/notice/regist": "myframework...RegistController"
  },
  "viewMappings": {
    "/admin/notice/list/view": "/secure/notice/list.jsp",
    "/admin/notice/regist/view": "/admin/notice/list"
  }
}
```

#### 🔎 매핑 로딩 과정
```java
JsonObject root = JsonParser.parseReader(new FileReader(...)).getAsJsonObject();
String mappingType = root.get("mappingType").getAsString();
JsonObject ctrlMap = root.getAsJsonObject("controllerMappings");

Map<String, Controller> handlerMap = new HashMap<>();
for (Entry<String, JsonElement> e : ctrlMap.entrySet()) {
  String uri = e.getKey();
  String className = e.getValue().getAsString();
  Controller ctrl = (Controller) Class.forName(className).newInstance();
  handlerMap.put(uri, ctrl);
}
```
💡 즉, 손님이 오기 전에 직원 전원을 미리 고용해놓는 방식!

### 🚪 3. DispatcherServlet의 역할
#### Servlet 설정 예시 (web.xml)
```
<servlet>
  <servlet-name>adminDispatcher</servlet-name>
  <servlet-class>DispatcherServlet</servlet-class>
  <init-param>
    <param-name>contextConfigLocation</param-name>
    <param-value>/WEB-INF/admin-servlet.json</param-value>
  </init-param>
</servlet>

<servlet-mapping>
  <servlet-name>adminDispatcher</servlet-name>
  <url-pattern>/admin/*</url-pattern>
</servlet-mapping>
```

#### 📌 주의할 점
- JSP 파일이 /admin/ 아래에 있으면 DispatcherServlet이 잡아서 오류

- 따라서 JSP는 /secure/ 아래에 둬야 뷰만 정상적으로 호출됨

### 🧩 4. Spring처럼 HandlerMapping 객체로 분리
- SimpleUrlHandlerMapping → URI와 Controller 매핑

- viewMappings 도 같은 방식으로 처리

#### 🧠 비유
- Map은 사물함, URI는 열쇠, Controller는 내용물

- 열쇠만 있으면 꺼내 쓸 수 있음!

### 🔄 5. Controller → Service → DAO 구조
#### 문제 상황
- Bio와 Staff를 한 번에 insert 해야 함 (트랜잭션)
- DAO에서 각각 commit() 하면 같은 트랜잭션이라 볼 수 없음

#### 잘못된 방식 ❌
- Controller가 SqlSession을 직접 생성 → 역할 침범

#### ✅ 해결 방법: Service 도입
```java
public class StaffService {
  public void regist(Bio bio) {
    SqlSession session = MybatisConfig.getSession();
    try {
      staffDAO.insert(session, bio.getStaff());  // 사원 insert
      bioDAO.insert(session, bio);               // bio insert
      session.commit();
    } catch {
      session.rollback();
    } finally {
      session.close();
    }
  }
}
```
#### 🧠 비유
- DAO: 실무 사원

- Service: 부장님 → 사원에게 일 시키고 성과 판단(트랜잭션)

- Controller: 고객 담당 팀장 → 부장에게 업무 지시만 함

### 🧬 6. MyBatis <selectKey> 동작 원리
```
<insert id="insert" parameterType="Staff">
  insert into staff(name, sal, email) values(#{name}, #{sal}, #{email})
  <selectKey keyColumn="staff_id" resultType="int" keyProperty="staff_id" order="AFTER">
    select last_insert_id() as staff_id
  </selectKey>
</insert>
```

#### 💡 핵심
- insert 후 → DB에서 자동 생성된 PK 값을
→ 동일 객체의 staff_id 필드에 자동 세팅됨

#### 🧠 이게 가능한 이유?
- Java에서 staff 객체는 Heap에 존재

- bio.setStaff(staff) 했을 때, bio는 staff 객체의 주소를 저장함

- 이후 staffDAO.insert()로 staff_id 값이 채워지면,

    bio에서 참조하고 있던 그 staff도 동일한 인스턴스 → 값 자동 반영

<br>
    📦 비유
    
    staff = 직원
    
    bio = 직원 정보를 담은 봉투
    
    봉투에 넣은 직원이 사번을 나중에 발급받아도, 봉투에 들은 내용이 바뀜

#### 🧭 전체 구조 흐름 요약
```
요청 URI
   ↓
DispatcherServlet (프론트 컨트롤러)
   ↓
HandlerMapping (Map으로 Controller 인스턴스 찾아줌)
   ↓
Controller (요청 수신)
   ↓
Service (트랜잭션 주도)
   ↓
DAO (DB 처리)
   ↓
ViewResolver → JSP 뷰 응답
```

#### 📌 결론 
✔️ Controller 매번 생성 → Map에 미리 생성 구조로 개선

✔️ service 도입으로 트랜잭션 처리 책임 분리

✔️ view/controller 매핑 JSON화로 구조적 명확성 확보

✔️ selectKey + 참조 관계로 insert 후 키 값 연동까지 해결

# 📘 57일차 수업 정리 (2025.07.15)

## 🌱 스프링 탄생 배경

* 로드 존슨이 기존 EJB(Enterprise Java Beans) 기반 Java 애플리케이션의 복잡성을 비판하며, POJO 기반의 단순한 객체 설계를 제안
* 이 아이디어에서 출발해 **Spring Framework**가 등장
* 현재는 XML 설정을 넘어, Java 기반 설정(@Configuration 등)이 일반적

---

## 🧩 스프링의 두 축: DI & AOP

### ✅ 1. DI (Dependency Injection) - 의존성 주입

#### 📌 개념

* 객체 간의 \*\*의존성(Dependency)\*\*을 직접 new로 생성하지 않고, 외부에서 주입받는 방식
* Spring이 객체를 생성하고 조립한 뒤 주입

#### 📌 목적

* 객체 간 결합도 낮추기
* 유지보수성과 테스트 용이성 확보
* 유연한 설계 가능

#### 📌 비유

* `FryPan`(X), `Induction`(X), `Pan`(O) : 특정 구현체가 아닌 상위 개념에 의존해야 변경에 유연
* → 인터페이스/추상클래스 등으로 선언 + 구현체는 외부에서 주입

#### 📌 예제 코드 (Java Config 방식)

```java
@Configuration
public class AppConfig {
    @Bean
    public FryPan fryPan() {
        return new FryPan();
    }

    @Bean
    public Induction induction() {
        return new Induction();
    }

    @Bean
    public Cook cook() {
        return new Cook(induction()); // 생성자 주입
    }
}
```

* `@Configuration`: 이 클래스는 스프링 설정 클래스임을 명시
* `@Bean`: 메서드가 반환하는 객체를 Spring 컨테이너가 관리하는 Bean으로 등록
* `Cook` 객체는 `Induction`을 생성자로 주입받음. 즉, 의존성을 외부에서 주입받는 형태

#### 📌 실행 예시

```java
public class AppMain {
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		Cook cook = context.getBean(Cook.class);
		cook.cooking();
	}
}
```

* `ApplicationContext`: 스프링 컨테이너. Bean을 생성하고 보관하는 역할
* `context.getBean(...)`: 객체를 꺼내서 사용. 이 시점에 이미 모든 의존성은 주입 완료됨

#### 📌 XML 방식 예시

```xml
<bean class="com.sinse.springapp.cook.FryPan" id="fryPan"/>
<bean class="com.sinse.springapp.cook.Induction" id="induction"/>
<bean class="com.sinse.springapp.cook.Cook" id="cook">
    <constructor-arg ref="fryPan"/>
</bean>
```

* XML 방식으로 의존성 주입을 설정하는 전통적 방법
* 현재는 어노테이션 기반 Java 설정이 더 많이 사용됨

---

### ✅ 2. AOP (Aspect Oriented Programming) - 관점 지향 프로그래밍

#### 📌 개념

* 핵심 로직과 \*\*공통 코드(횡단 관심사)\*\*를 분리하는 프로그래밍 방식
* 공통 기능(로깅, 트랜잭션, 보안 등)을 비즈니스 로직과 분리하여 모듈화

#### 📌 동작 흐름

* 특정 메서드 호출 전/후/예외 발생 시 등 원하는 시점에 Aspect 코드 삽입

#### 📌 사용 어노테이션

| 어노테이션                     | 설명                    |
| ------------------------- | --------------------- |
| `@Aspect`                 | AOP를 위한 클래스 정의        |
| `@Component`              | 빈 등록 (자동 탐색 대상)       |
| `@Autowired`              | 스프링이 Bean 자동 주입       |
| `@Before(...)`            | 대상 메서드 실행 전에 공통 코드 실행 |
| `@EnableAspectJAutoProxy` | AOP 기능 활성화 (프록시 사용)   |
| `@ComponentScan`          | 해당 패키지에서 Bean을 자동 탐색  |

#### 📌 예제 코드 (BellAspect)

```java
@Aspect
@Component
public class BellAspect {
    @Autowired
    private Bell bell;

    @Before("execution(* com.sinse.springapp.school.Student.*(..))")
    public void ringBell() {
        bell.sound();
    }
}
```

* `@Aspect`: 이 클래스가 공통 기능을 가진 클래스임을 지정
* `@Before`: Student 클래스의 모든 메서드 실행 전에 `ringBell()`이 실행됨
* `bell.sound()`는 공통 기능 (예: 알림음)을 수행하는 코드

#### 📌 AOP 설정 클래스 예시

```java
@Configuration
@EnableAspectJAutoProxy
@ComponentScan("com.sinse.springapp.school")
public class AppConfig {
    @Bean
    public Bell bell() { return new Bell(); }

    @Bean
    public Student student() { return new Student(); }
}
```

#### 📌 실행 예시

```java
public class AppMain {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Student student = context.getBean(Student.class);
        student.getUp();     // 호출 전 bell.sound() 실행됨
        student.goToSchool();
        student.study();
        student.rest();
        student.haveLunch();
        student.goHome();
    }
}
```

* `Student`의 메서드를 호출할 때마다 `BellAspect`가 작동하여 공통 로직이 수행됨

#### 🖥 GUI에도 적용되는 DI

```java
@Configuration
@EnableAspectJAutoProxy
@ComponentScan("com.sinse.springapp.gui")
public class AppConfig {
    @Bean
    public JTextField name() { return new JTextField(15); }

    @Bean
    public JTextField email() { return new JTextField(15); }

    @Bean
    public JButton bt() { return new JButton("버튼"); }

    @Bean
    public MyWin myWin() {
        return new MyWin(name(), email(), bt());
    }
}
```

```java
public class MyWin extends JFrame {
    public MyWin(JComponent name, JComponent email, JComponent bt) {
        setLayout(new FlowLayout());
        add(name); add(email); add(bt);
        setSize(200, 150);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        context.getBean(MyWin.class); // GUI 실행
    }
}
```

* 스프링은 웹 뿐 아니라 데스크탑, 모바일 환경에서도 DI 구조 사용 가능

---

## 🧭 Spring MVC 구조 정리

### 📌 DispatcherServlet 설정 (web.xml)

```xml
<servlet>
    <servlet-name>userDispatcher</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <init-param>
        <param-name>contextClass</param-name>
        <param-value>org.springframework.web.context.support.AnnotationConfigWebApplicationContext</param-value>
    </init-param>
    <init-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>com.sinse.springmvc.spring.config.WebConfig</param-value>
    </init-param>
</servlet>
<servlet-mapping>
    <servlet-name>userDispatcher</servlet-name>
    <url-pattern>/shop/*</url-pattern>
</servlet-mapping>
```

### 📌 Java Config 방식 설정 (WebConfig)

```java
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.sinse.springmvc.spring.controller"})
public class WebConfig {
    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
}
```

### 📌 컨트롤러 예제 (NoticeController)

```java
@Controller
public class NoticeController {
    Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping("/notice/list")
    public ModelAndView selectAll() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("list", "게시물 목록");
        mav.setViewName("notice/list");
        return mav;
    }

    @RequestMapping("/notice/registform")
    public String registForm() {
        return "notice/write";
    }

    @RequestMapping(value="/notice/regist", method=RequestMethod.POST)
    public String regist() {
        logger.debug("글쓰기 요청 받음");
        return "redirect:/shop/notice/list";
    }

    @RequestMapping("/notice/detail")
    public ModelAndView getDetail() {
        logger.debug("상세보기 요청 받음");
        return null;
    }

    @RequestMapping(value="/notice/edit", method=RequestMethod.GET)
    public String update() {
        logger.debug("수정 요청 받음");
        return "redirect:/shop/notice/detail?notice_id=33";
    }

    @RequestMapping(value="/notice/del", method=RequestMethod.GET)
    public String delete() {
        logger.debug("삭제 요청 받음");
        return "redirect:/shop/notice/list";
    }
}
```

---

## ✅ 오늘의 키워드 총정리

| 개념                          | 설명                                           |
| --------------------------- | -------------------------------------------- |
| **DI**                      | 의존성 주입: 객체를 외부에서 주입 (new X)                  |
| **AOP**                     | 공통 기능 분리 (횡단 관심사, Aspect)                    |
| **@Bean**                   | 개발자가 직접 등록하는 스프링 Bean                        |
| **@ComponentScan**          | 특정 패키지 내 Bean 자동 탐색                          |
| **@EnableWebMvc**           | Spring MVC 설정 활성화                            |
| **@EnableAspectJAutoProxy** | AOP 기능 활성화                                   |
| **@Controller**             | Spring MVC의 Controller 역할 클래스                |
| **DispatcherServlet**       | 모든 요청을 받아 처리하는 프론트 컨트롤러                      |
| **ViewResolver**            | JSP 파일의 경로와 확장자 조합기 설정                       |
| **ApplicationContext**      | 스프링 컨테이너 (Bean 생성 및 관리 담당)                   |
| **ModelAndView**            | Controller가 Model과 View를 함께 전달할 때 사용         |
| **redirect**                | insert/update/delete 이후 새로고침 시 중복 방지용 재요청 방식 |

---

## 📌 Spring MVC 흐름 요약

```plaintext
1. 사용자 요청: /shop/notice/list
↓
2. DispatcherServlet이 요청 받음
↓
3. @RequestMapping("/notice/list") 메서드 실행
↓
4. ModelAndView 객체 생성 및 반환
↓
5. ViewResolver가 뷰 이름(예: notice/list)에 prefix/suffix 붙여서 JSP 경로 조합 → /WEB-INF/views/notice/list.jsp
↓
6. JSP 출력 (forward 방식)
```

---

## 🔁 추가 정리: redirect, forwarding, URL 구조

* `return "redirect:/shop/notice/list"`는 **GET 요청**으로 새로 리다이렉트
* `ModelAndView`는 View와 데이터를 같이 담을 때 사용 (주로 `select` 결과 보여줄 때)
* `String return`은 View만 반환할 때 (주로 `insert`, `update`, `delete` 처리 후)
* redirect는 보통 **DB에 영향을 주는 요청 처리 후** 새로고침 문제를 방지하기 위해 사용 (PRG 패턴)
* 뷰 파일은 `/WEB-INF/views/notice/xxx.jsp`에 위치해야 클라이언트가 직접 접근 못하고 Dispatcher가 제어 가능
* `ViewResolver`가 prefix/suffix 설정을 했기 때문에 `notice/list`처럼 뷰 이름만 넘겨도 경로 조합됨
* `@RequestMapping("/notice/list")`는 `/shop` 하위 URL 패턴에서 실행되므로 최종 주소는 `/shop/notice/list`
* `redirect:` 시에는 다시 DispatcherServlet 경유하므로 `/shop/notice/~` 구조로 지정해야 함

> ✔ 이 구조는 **Spring Web MVC** 패턴의 대표적인 흐름이며, 웹에서 이루어지는 HTTP 요청 기반 개발 구조입니다.

# 📘 58일차 수업 정리 (2025.07.16)

### ✅ @Autowired
- 스프링 컨테이너로부터 인스턴스를 주입받는 어노테이션.
- 결국 우리가 어제 배운 **DI(Dependency Injection, 의존성 주입)**을 어노테이션으로 간단하게 구현한 형태.
- 클래스 간의 결합도를 낮추고 유지보수를 용이하게 하기 위한 설계 패턴이다.

---

### ✅ @RequestMapping
- 기존 `controllerMapping.properties` 파일처럼 **요청 URL을 컨트롤러 메서드와 연결해주는 역할**.
- web.xml에 DispatcherServlet이 `/admin/*` 경로에 매핑되어 있으므로,  
  `@RequestMapping("/notice/list")`는 클라이언트 기준 `/admin/notice/list`를 의미함.

---

### ✅ ModelAndView
- Model: view로 전달할 데이터를 저장하는 객체 (기존 request.setAttribute와 유사).
- View: DispatcherServlet → ViewResolver가 참조하는 JSP 페이지의 논리적 이름.

```java
ModelAndView mav = new ModelAndView();
mav.addObject("noticeList", list); // 데이터 저장
mav.setViewName("secure/notice/list"); // 보여줄 JSP
```

* forwarding 방식: setViewName("...")

* redirect 방식: setViewName("redirect:/admin/notice/list")

---

### ✅ redirect와 forwarding 차이

* redirect: 클라이언트에게 다시 요청하라는 명령. → URL이 변경됨

    - 반드시 DispatcherServlet을 다시 통과해야 하므로 /admin/... 경로 명시해야 함.

* forwarding: 서버 내부에서 해당 JSP로 이동. → URL이 변경되지 않음

    - ViewResolver가 경로 + 확장자 붙여서 JSP 찾아줌.

---

### ✅ AdminWebConfig (자바 기반 설정 클래스)

* Spring의 XML 설정을 대신함.

* 주요 어노테이션:

    - @Configuration: 설정 파일임을 명시

    - @EnableWebMvc: Spring MVC 기능 사용 가능

    - @EnableTransactionManagement: 트랜잭션 사용을 위한 설정 활성화

    - @ComponentScan: 지정된 패키지 내 컴포넌트 자동 스캔

* ViewResolver 등록:
```java
@Bean
public InternalResourceViewResolver resolver() {
    InternalResourceViewResolver resolver = new InternalResourceViewResolver();
    resolver.setPrefix("/WEB-INF/views/");
    resolver.setSuffix(".jsp");
    return resolver;
}
```

---

### ✅ JNDI (Java Naming and Directory Interface)

* 커넥션 풀을 직접 관리하지 않고 톰캣 등 컨테이너에서 제공하는 커넥션을 사용.

* AdminWebConfig에서 아래처럼 작성:
```java
JndiTemplate template = new JndiTemplate();
DataSource ds = template.lookup("java:comp/env/jndi/mysql", DataSource.class);
```
* DataSource.class는 타입 캐스팅을 위한 것 (형변환)

---

### ✅ MyBatis와 Hibernate 설정

* 둘 다 트랜잭션이 필요함 (기본 auto-commit이 아님)

* 그래서 transactionManager가 반드시 설정되어야 함.

* transactionManager 설정 후 @EnableTransactionManagement 활성화 필요

---

### ✅ WEB-INF 폴더의 JSP는 직접 접근 불가

* 보안을 위해 웹 브라우저가 직접 접근하지 못함.

* 따라서 Controller를 통해 forwarding 방식으로만 접근 가능.

* DispatcherServlet + ViewResolver 조합으로 .jsp가 보여지는 구조.

---

### 💬 기억할 것들

* redirect:는 클라이언트에게 다시 요청하라는 의미이므로 DispatcherServlet 설정에 맞게 /admin/... 경로를 명시해야 한다.

* forwarding은 viewName을 단순 문자열로 넘기면 됨 (ViewResolver가 prefix/suffix 붙여줌).

* ViewResolver, JNDI, 트랜잭션 설정은 대부분 AdminWebConfig에서 처리.

* Spring MVC는 역할이 명확하게 나뉘어 있으므로 흐름을 구조적으로 이해하자.


# 📘 58일차 수업 정리 (2025.07.16)
keyword만 나열하면 gpt가 정리해주겠지? 난 믿는다

@autowired: 스프링컨테이너로부터 인스턴스 주입받기 (injection) 어제 배운 DI??
DI 상기시켜보자 DI가 뭐였지?

@requestMapping: 결국 .text파일로 매핑했던 controllerMapping과 veiwMapping 중 controllerMapping을 담당함, 다만 DispathcerServlet이 가이드를 하고 있는데, web.xml에서 dispatcherServlet을 거치려면 즉, 1차 Controller 관문에 이르기 위해서는 /admin/*로 접속하라는 url-pattern을 구축해두었으므로 @requestMapping("/notice/list")는 /admin/notice/list를 의미함 여기서 의문은 url-pattern에 /*로 되어 있으면 /admin//notice/list로 인식하는 건 아닌가 하는점

그럼 ViewMapping역할을 하는 것도 있어야 할 거 아니야? > 그래서 내가 있잖아! ModelAndView!: Model과 View를 한번에 처리한다. 여기서의 Model은 MVC에서 일처리를 담당하는 M이 아니라 테이블모델에서의 모델을 의미하는 듯함 (껍데기) 즉, view로 가져갈 데이터는 ModelAndView의 Model에 mav.setObject("noticeList", noticeList);로 저장하고, 보여줄 페이지는 mav.setViewName("secure/notice/list")으로 처리
다만 이때 setViewName은 포워딩 방식이든, redirect방식이든 처리하는데, forwarding 경우에는 사용할 세부 컨트롤러의 requestMapping url주소를 정확히 입력해주어야 한다.(그러나, DispatcherServlet을 통과한 시점이므로 /admin은 제외하고...) redirect인 경우에는 redirect:"실제경로"를 적는 것이 아니고 접두어, 접미어가 짤려나간 형식의 이름을 적는다. 왜? InternalResourceViewResolver를 우리는 사용했으니까. (이하 viewResolver) viewResolver는 실제 경로를 적게 하되, 접두어, 접미어를 선택할 수 있게 한다. 접두어는 경로, 접미어는 .jsp 이때 .html도 있는데 왜 접미어를 .jsp로 한정지을까 의문이었지만 서버가 처리할 수 있는 것은 즉, 웹컨테이너가 직접 관여할 수 있는 것은 서블릿과 jsp인데, 서블릿은 로직을 담당하고 view 지금 view 얘기중이다. view는 jsp가 담당하므로, 결국 viewName은 .jsp인 jsp들로 한정되어 있을 것이기 때문이다.

### AdminWebConfig...
spring의 고전적 설정을 담당하는 xml을 대신하는 .java파일이다.
@Configuration : 왜 사용하더라?
@EnableWebMvc : web에서 MVC 패턴을 이용하기 위해 필수적인 어노테이션
@EnableTransactionManagement : mybatis, hibernate 등 sql 쿼리문을 담당할 때 트랜잭션이 필요하기 때문에 달아주는 어노테이션이다.
@ComponentScan : 모든 컨트롤러, 모든 서비스 객체, 모든 DAO들을 직접 실행하는건 비효율적, 그래서 특정 패키지 내에 있는 컨트롤러, 서비스, dAO를 모두 실행시키기 위한 어노테이션이라고 보면 될 것 같다.

아까 말했듯이 controller가 3. 일시킨다, 4. 저장한다를 마치고 직접 view를 보여주지 않는데, 이것은 AdminWebConfig가 한다고 보면 될듯 하다. 여기서 InternalResourceViewResolver가 사용되었거든...
viewResolver만 있는 것이 아니고, resolver는 여러 형태가 있는데 내가 배운 건 이것뿐... 그리고 이 Resolver는 접두어, 접미어 세팅이 가능하다.(prefix, suffix)

이 때, viewResolver는 스프링컨테이너의 관리 대상으로 올리기 위해 @Bean 어노테이션을 썼다. (맞나?)

우리는 이제 jdbc Connection Pool을 직접 싱글톤으로 작성한 session들을 여럿 미리 만들어 두어 관리하고 싶지 않다. 이 역할 마저 맡기고 싶어. 그래서 사용한 것이 JNDI!
JNDI 세팅법은 후에 기술하기로 하고, 일단 JNDI 사용 즉, database 선택을 위한 datasource 세팅도 역시나 AdminWebConfig에서 담당한다.

DataSource 형으로 반환 계획... JndiTemplate이라는 객체를 사용. jndi.lookup(사용 db glomalNaming을 불러온다.) 
ex) jndi.lookup("java:comp/env/jndi/mysql", DataSource.class) {component의 environment 중 jndi 사용할 건데 mysql, DataSource.class는 무슨 의미인지 모르겠다. 여튼 mysql 사용 선언 같은 느낌인듯}
이건 @Bean으로 관리 안하나부다. 왜??


### mybatis, hibernate
둘 다 쿼리문 사용 시 트랜잭션이 강요되었던 걸로 기억한다.
일반 mysql은 기본 auto commit이 true인 반면에 mybatis와 hibernate는 commit을 해줘야했던걸로 기억!
그래서 transactionManager가 필수다. 이 세팅이 끝나면 결국 AdminWebConfig한테 아까말했듯이 EnableTrasactionManagement 어노테이션을 줘야하는거지...

일단 여기까지 정리해볼까?
틀린 내용이나 수정 필요한 내용 있나?


# 59일차 수업 정리(2025.07.17)

### ✅ 1. 한글 깨짐 문제와 CharacterEncodingFilter
🔧 왜 깨지냐?

* 요청/응답 시 인코딩 설정이 명확하지 않으면 ISO-8859-1로 처리되어 깨짐 발생

해결 방법:

(1) web.xml 방식:

```xml
<filter>
  <filter-name>encodingFilter</filter-name>
  <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
  <init-param>
    <param-name>encoding</param-name>
    <param-value>UTF-8</param-value>
  </init-param>
</filter>
<filter-mapping>
  <filter-name>encodingFilter</filter-name>
  <url-pattern>/admin/*</url-pattern>
</filter-mapping>
```

(2) Java Config 방식 (WebConfig.java 등):

```java
@Bean
public FilterRegistrationBean<CharacterEncodingFilter> characterEncodingFilter() {
    CharacterEncodingFilter filter = new CharacterEncodingFilter();
    filter.setEncoding("UTF-8");
    filter.setForceEncoding(true);
    
    FilterRegistrationBean<CharacterEncodingFilter> registration = new FilterRegistrationBean<>();
    registration.setFilter(filter);
    registration.addUrlPatterns("/*");
    return registration;
}
```

<br>

### ✅ 2. Spring Bean 등록 개념 정리
| 어노테이션                                    | 대상  | 역할                              |
| ---------------------------------------- | --- | ------------------------------- |
| `@Component`                             | 클래스 | Spring이 관리하는 일반 빈               |
| `@Controller`, `@Service`, `@Repository` | 클래스 | `@Component`의 세부 역할 분리          |
| `@Bean`                                  | 메서드 | Java Config에서 명시적으로 객체 등록       |
| `@ComponentScan`                         | 패키지 | 해당 경로 내의 `@Component` 계열 클래스 스캔 |

<br>

> ✔️ @Bean은 클래스 X, 메서드에 붙여서 직접 객체를 반환 <br>
> ✔️ @Component는 클래스에 붙여서 자동 등록

<br>

### ✅ 3. DispatcherServlet & ApplicationContext 관계

핵심:
* DispatcherServlet 하나당 ApplicationContext 하나 <br>
→ adminDispatcher, userDispatcher가 따로 있으면 각기 다른 컨테이너가 생성됨.

흐름:
1. web.xml에서 DispatcherServlet 등록 (admin/user 구분)

2. 각각 AdminWebConfig, UserWebConfig로 초기화

3. 내부적으로 AnnotationConfigWebApplicationContext가 각 설정 클래스를 바탕으로 @Bean, @Component 등을 스캔 및 등록

4. Controller 등 싱글톤 빈들이 메모리에 올라감

<br>

### ✅ 4. HandlerMapping, ViewResolver의 흐름
요청 흐름 요약:
1. 요청 진입 → DispatcherServlet

2. HandlerMapping이 어떤 Controller로 요청을 보낼지 결정

3. HandlerAdapter가 실제 메서드 실행

4. 결과는 ModelAndView 형태로 반환

5. ViewResolver가 뷰 이름을 .jsp 등의 실제 파일로 변환

6. DispatcherServlet이 View를 forward

```java
// InternalResourceViewResolver 설정
@Bean
public ViewResolver viewResolver() {
    InternalResourceViewResolver resolver = new InternalResourceViewResolver();
    resolver.setPrefix("/WEB-INF/views/");
    resolver.setSuffix(".jsp");
    return resolver;
}
```
<br>

### ✅ 5. WebConfig 중복 문제 (admin/user)
현재 문제:
* AdminWebConfig에만 DataSource, Hibernate 설정 있음

* 하지만 클라이언트는 userDispatcher 먼저 실행

    → 즉, userDispatcher만 실행되면 DB 관련 Bean이 없어 NullPointerException 등 발생 가능

<br>

### ✅ 6. DispatcherServlet 간의 Bean 공유?
답: 불가능
* DispatcherServlet마다 독립적인 ApplicationContext를 가짐.

* 하나의 DispatcherServlet이 만든 Bean은 다른 DispatcherServlet에서는 접근 불가

<br>

### ✅ 7. ServletContext vs Session vs Request
| 범위                                                          | 생존 범위        | 주요 메서드                               |
| ----------------------------------------------------------- | ------------ | ------------------------------------ |
| **Request**                                                 | 요청 1번        | `request.setAttribute()`             |
| **Session**                                                 | 로그인\~브라우저 종료 | `session.setAttribute()`             |
| **ServletContext**                                          | 서버 실행\~종료    | `getServletContext().setAttribute()` |
| ⇒ **ServletContext는 가장 상위 범위이며, DispatcherServlet 간 공유 가능** |

<br>

### ✅ 8. MVC 패턴 5단계
Spring MVC에서 DispatcherServlet이 요청을 처리하는 5단계 구조:

1. HandlerMapping: 요청 받기

2. HandlerAdapter: 요청 분석하기 (실제 컨트롤러 메서드 호출 방식 결정)

3. Controller (Handler): 일 시킨다. (DAO가 실질적으로 로직을 수행)

4. Controller : 저장 하기 (View로 보여줄 데이터 및 view에 대한 정보 저장)

5. ViewResolver: 어떤 뷰를 반환할지 결정(jsp, html 등)하고 반환

💡 핵심: DispatcherServlet이 이 전체 흐름을 통제하는 중앙 제어자

<br>

### ✅ 9. ApplicationContext(SpringContainer) 구조
Spring Web 프로젝트에서는 ApplicationContext가 계층적으로 구성됨.

* Root ApplicationContext (공통 Bean 등록)

* DB, Service, DAO 등 공통 Bean

* ContextLoaderListener가 초기화 담당

Servlet ApplicationContext (각 DispatcherServlet마다 별도)

* Controller, ViewResolver 등 Web 관련 Bean

* ex) userWebConfig, adminWebConfig 등

> 💡 따라서 총 3개의 ApplicationContext가 있을 수 있음:
> Root + user용 ServletContext + admin용 ServletContext

### ✅ 10. ContextLoaderListener의 역할과 ServletContext 생성 시점
* web.xml에서 <listener>로 등록한 ContextLoaderListener가 서버 시작과 동시에 동작

* 이 리스너가 **RootConfig(XML 또는 JavaConfig)**를 참조해서 Root ApplicationContext 생성

* 이후 <servlet> 태그로 등록된 각각의 DispatcherServlet이 자신의 WebConfig 기반으로 별도 ApplicationContext 생성

> ❗ 순서와 구조는 아래와 같음:
```text
Tomcat (서버 시작)
  → ServletContext 생성
    → ContextLoaderListener 초기화
      → Root ApplicationContext 생성
        → DispatcherServlet 초기화
          → Servlet-specific ApplicationContext 생성
```

> 💡 web.xml에서 순서는 아래처럼 보여도 실제 실행 순서는 ContextLoaderListener가 먼저 실행됨 (서버 부팅 시 가장 먼저 실행되도록 설계됨).

```xml
<!-- 실제 web.xml 구성 예 -->
<context-param>
  <param-name>contextConfigLocation</param-name>
  <param-value>/WEB-INF/spring/root-context.xml</param-value>
</context-param>

<listener>
  <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
</listener>

<servlet>
  <servlet-name>user</servlet-name>
  <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
  <init-param>
    <param-name>contextConfigLocation</param-name>
    <param-value>/WEB-INF/spring/user-web-context.xml</param-value>
  </init-param>
</servlet>
```
<br>

### ✅ 11. 정리된 구조도
```
[ServletContext]
│
├── ContextLoaderListener
│   └── Root ApplicationContext
│       ├── 공통 Bean (Service, Repository 등)
│
├── DispatcherServlet (user)
│   └── userWebConfig → Servlet ApplicationContext (Controller, ViewResolver)
│
└── DispatcherServlet (admin)
    └── adminWebConfig → Servlet ApplicationContext (Controller, ViewResolver)
```




이전에 spring 만들어볼땐 handler 만들었었는데, 이번엔 굳이 안 만들었다? defaultHandlerMapping이 작동하는건가?
@Bean <bean class="InternalResourceViewResolver" id="viewResolver"></bean>
@Bean <bean id="handlerMapping"></bean>

HandlerMapping을 지나면 스프링컨테이너가 만들어진다? 스프링컨테이너??? (applicationContext??) 하위는 AnnotationConfigWebApplicationContext?? 이게 injection의 주체? 
AnnotationConfigWebApplicationContext는 자루를 가지고 있다. @annotation들을 모두 가지고 있다? 얘를 만나면 일단 모든 annotation이 메모리에 올라가있는다는건가?

스프링컨테이너의 소유자는 누구인가? DispatcherServlet? 지금 adminDispatcher, userDispatcher 모두 web.xml에서 동일 클래스로 지정되어있고 name만 다른데 괜찮은가?
'org.springframework.web.servlet.DispatcherServlet'

@RequestMapping 등의 모든 매핑작업도 ApplicationContext가 직접 하는건가?

4. 저장하기를 실행한다면 이건 무조건 forwarding이다?
ModelAndView에 저장을했지? view는 접두어와 접미어를 뺀 일부만 저장, 해석은 DispatcherServlet이 직접 안해. 누가 한다? ViewResolver! (InternalResourceViewResolver)
view 정보를 해석받으면 DispatcherServlet이 응답받을 view 내용을 톰캣한테 보내준다?

admin, user 각각 Dispatcher config역할을 하는 webConfig.java가 있는데, bean들과 component 설정들을 다 지정하고, mybatis, hibernate들에 대한 정보들도 동일하게 등록할수밖에 없다. 이러면 ApplicationContext는 중복 내용을 갖네? 물론 ApplicationContext가 서로 다른 2개가 있겠지만 동일 내용의 모델 등 굳이 메모리 낭비가 필요한가?
하나로 만들려면? 하나 이미 등록했으면 다음 webConfig에선 중복되는 건 지워라, View만 남겨라? .... 이해 안감..
현재 데이터소스 및 db 설정정보는 admin에만 있다. 클라이언트가 admin이 아니라 shoppingmall(user) 먼저 들어오면?? 데이터소스, db 없잖아? viewResolver만 있네.
admin을 부랴부랴 켰다. handler, spring컨테이너 생성됐는데, userDispather에서 adminDispatcher의 spring컨테이너가 갖고있는 어노테이션 빈들 접근 가능? 불가...
서블릿 간 공유할 기술이 필요하겠네?

상위 인터페이스? 상위 클래스? 필요한건가? (강사님께 아직 답 받기 전)

JavaEE 요청 처리 흐름에서 무언가를 저장할 수 있는 용도의 객체? Session? request? (request < Session < ??: session보다 더 오래 살아갈 무언가가 있다?
지난 수업 들 중 배웠던 ServletContext.. getRealPath로 사용했었지...)

Request.. response 클라이언트에게 전달 시 사라짐..
Session.. cookie 없어지면 무용지물... (창 닫거나, 일정시간 미사용 등등)
ServletContext..

