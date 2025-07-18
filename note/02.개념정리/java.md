# 데스크탑 기반 애플리케이션

## JVM 3단 구조 (Method - Stack - Heap)
### Method
- .java 실행 시 .class 파일이 처음 참조될 때 JVM 로딩됨
- 로드된 .class 파일의 전체 메소드들이 Method 영역에 올라온다. 
- static 변수(클래스변수), 상수, 인터페이스 정보 등이 올라감
- 공유 영역으로 클래스 당 1번만 로딩됨
- 메모리를 차지함 (영구적으로 유지되지는 않음, GC 대상이 될 수 있음)

🔹 느낌: “코드 설계도와 static 공간”

### Stack
- 각 스레드마다 독립적으로 생성되는 메모리 영역
- 메서드가 호출될 때마다 스택 프레임 생성
- 프레임 안에는 this, 매개변수, 지역 변수명이 들어감
- 변수명은 있지만 값은 Heap 또는 상수풀을 참조 (스택 안에는 실제 객체가 없음)

🔹 느낌: “실행 순서를 위한 작업 공간”

### Heap
- new 키워드 등으로 생성된 객체가 저장되는 공간
- 인스턴스 변수 값이 실제로 존재
- Heap에 객체 생성이 완료되면, Stack의 참조변수가 해당 객체 주소를 가리킴
- GC(가비지 컬렉터)의 관리 대상

🔹 느낌: “실제 데이터를 보관하는 객체 저장소”

### String Constant Pool (상수풀)
- "korea" 같은 문자열 리터럴은 중복 없이 하나만 상수풀에 저장됨
- String str = "korea" 는 상수풀의 주소를 참조하지만 new String("korea") 는 힙에 새로운 객체를 만들어 메모리 낭비 가능성 있음
- Stack의 str 변수는 상수풀의 주소값을 직접 참조

🔹 느낌: “문자열 재사용을 위한 최적화 풀”

### 한 줄 요약
- JVM은 클래스를 로딩해 메소드 영역에 올리고, 실행 시 메서드는 스택에 쌓여 흐름을 제어하며, 객체는 힙에 생성되어 스택에서 참조하고, 문자열 리터럴은 상수풀을 통해 중복 없이 관리된다.


## 접근 제한자와 리턴 타입<context-param>
  		<param-name>contextClass</param-name>
  		<param-value>org.springframework.web.context.support.AnnotationConfigWebApplicationContext</param-value>
  	</context-param>
  	<context-param>
  		<param-name>contextConfigLocation</param-name>
  		<param-value>com.sinse.mall.spring.config.RootConfig</param-value>
  	</context-param>

AnnotationConfigWebApplicationContext가 기본적으로 servletContext를 보유하고 있고, 서버 작동과 동시에 Listener를 통해 annotation들을 감지하고 있어서, 서버 시작하면 서버내에서 사용될 어노테이션들을 전부 로드한다고 생각했는데 그것이 아니고 rootConfig에 있는 어노테이션들만 로드시켜두는건가?<context-param>
  		<param-name>contextClass</param-name>
  		<param-value>org.springframework.web.context.support.AnnotationConfigWebApplicationContext</param-value>
  	</context-param>
  	<context-param>
  		<param-name>contextConfigLocation</param-name>
  		<param-value>com.sinse.mall.spring.config.RootConfig</param-value>
  	</context-param>

AnnotationConfigWebApplicationContext가 기본적으로 servletContext를 보유하고 있고, 서버 작동과 동시에 Listener를 통해 annotation들을 감지하고 있어서, 서버 시작하면 서버내에서 사용될 어노테이션들을 전부 로드한다고 생각했는데 그것이 아니고 rootConfig에 있는 어노테이션들만 로드시켜두는건가?1
### 접근 제한자
| 접근제한자       | 클래스 내부 | 같은 패키지 | 다른 패키지 (상속X) | 다른 패키지 (상속O) |
| ----------- | ------ | ------ | ------------ | ------------ |
| `public`    | ✅      | ✅      | ✅            | ✅            |
| `protected` | ✅      | ✅      | ❌            | ✅            |
| (default)   | ✅      | ✅      | ❌            | ❌            |
| `private`   | ✅      | ❌      | ❌            | ❌            |

- public: 어디서든 접근 가능 (가장 개방적)

- protected: 같은 패키지 + 상속 받은 외부 클래스에서 접근 가능

- (default): 접근제한자를 아예 안 붙였을 때. 같은 패키지까지만 허용

- private: 오직 해당 클래스 내부에서만 접근 가능 (가장 폐쇄적)

### 리턴 타입

