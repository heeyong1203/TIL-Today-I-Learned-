[절차지향 언어-C언어]
1. 변수&자료형
2. 연산자
3. 제어문
4. 함수
5. 배열
-----------------------------
[객체지향 언어]

Java script > \
Java > \
JSP > \
MVC > \
spring (legacy한 시스템) > \
-------------------------- 이클립스 활용 \
springboot > \
msa > \
시큐리티 \
-------------------------- 인텔리제이 활용

5월이 굉장히 중요함
-------------------------------------------------

[화살 날리기]
span 이용(inline)
inline-block 활용

변수와 함수 -> 하나의 움직이는 물체 생성 가능
여러 개의 물체가 각기 다른 위치에서 각기 다른 속도로 움직이고 싶다면?
→ class의 탄생

oop : Object-Oriented Programming

클래스 내부에서는 함수가 사물이 움직이는 방식, 방법이라는 표현으로 변경되어야 함
이에 따라 function이라는 키워드를 제거해야 함

거푸집은 형상을 나타내주지 못함 -> new 연산자 필요

클래스를 구성하는 요소는 변수와 함수
하지만 명칭을 변수-> property / 함수-> method라고 바꿔 불러야 함 (혼동 방지)

js파일명은 반드시 대문자로 시작

함수를 하나로 줄여쓰는 방법 → 화살표 함수
; 

JavaScript 언어는 함수를 1급시민(first-class citizen) 취급
컴퓨터 공학에서 일급시민이란 어떤 대상을 일반 데이터처럼 취급할 수 있는 특징을 말함
즉, 자바스크립트는 함수를 변수에 담거나, 함수를 매개변수로서 전달하거나,
함수를 리턴할 수도 있음

* 표현식에 의한 함수 정의
function init(){

} 

* 함수 선언문
let init=function(){

}

* 화살표 함수 \
  /* \
  화살표 함수는 this를 보유할 수 없다. \
  따라서 화살표 함수영역에서 사용되는 this는 나를 가리키는 것이 아니라, \
  상위 스코프를 가리킨다. \
   */ 

let init=()=>{

}