package gui.layout;
/* 
GUI 프로그래밍을 윈도우 프로그래밍이라고도 함
모든 UI 컴포넌트는 윈도우 안에서 구현되므로..

컴포넌트란? 재사용 가능한 객체단위(예- gui분야에서는 버튼,체크박스 등..)
[java 컴포넌트의 유형]
- 남을 포함할 수 있는 유형(컨테이너형)
  예) Frame
   특징) 남을 포함하려다 보니, 어떻게 배치할지를 고민을 함...
		  따라서 컨테이너 형에는 배치관리자(Layout Manager)가 지원됨		
		  컨테이너 유형은 개발자가 배치관리자를 지정하지 않아도, 시스템에서 기본으로
		  적용되는 배치관리자가 있음..예) Frame의 경우는 BorderLayout이 적용
		  
   배치관리자 종류) 
	  1) BorderLayout(동,서,남,북,센터의 방향을 갖는 배치)
	      각 방향에 들어가는 컴포넌트는 자신의 크기를 잃어버리고, 확장해버림.
		  따라서 대왕버튼이 만들어졌음..
		  
	  2) FlowLayout(위치가 결정되지 않고 컨테이너에 따라 흘러다님)
	       단 방향성이 있어서 수평방향의 흐름 또는 수직방향의 흐름 둘중 하나임
	  
	  3) GridLayout(행과 열의 배치방식)
	       각 행,열에 배치되는 즉 셀에 들어가는 컴포넌트가 자신의 크기를 잃어버리고
	       확장해버림
	  4) CardLayout(마치 포커의 카드처럼 오직 하나의 카드만 보여지는 배치방식)
	      화면 전환 시 사용되는데, 사실..직접 만들어 구현해도 되기때문에 몰라도 됨
	  
- 남에게 포함 당할 수 있는 유형(비주얼 컴포넌트형)
  예) 버튼, 체크박스, 초이스, 윈도우 안에 포함되는 모든것들..
*/
import java.awt.Frame;

public class LayoutTest{
	public static void main(String[] args) {
		//윈도우 생성 
		Frame frame=new Frame();
	}
}
