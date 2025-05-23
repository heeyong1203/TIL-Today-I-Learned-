/*
Java에서는 비슷한 자료형 간에는 서로 형변환이 가능함
*/

class DataType2{
	public static void main(String[] args){
		/*
		문자 : char (한 문자를 담을 수 있음; char x = 'a';) / 컴퓨터는 어차피 숫자로 변환하여 받아들임
		숫자 : 정수 byte < short < int < long
						 1			  2		 4			8
				 실수						  float < double
												 4			8
		논리값 : boolean (true와 false를 숫자로 인식하지 않음)
						1
		*/
		byte b=5; // 메모리 1칸(byte는 1byte)
		short s =7; // 메모리 2칸(short는 2byte)
		byte x=8; 
		
		// b=x;  b에 x를 대입하라. 자료형이 같기 때문에 오류 없음
				 // 대입연산 뿐만 아니라, 일반적으로 연산자가 동작하기 위해서는 연산 대상이 되는 데이터의 자료형이 동일해야 함
		s=b; // short의 공간이 byte보다 크기 때문에 문제가 발생하지 않음 / short가 byte로 간다고 하면 두 칸의 데이터 중 한 칸의 데이터는 소실될 수 있음
				 // 이 코드는 연산 대상이 되는 피연산자들이 동일하지 않기 때문에 java 컴파일러가 자료형을 동일하게 변환시킨다.
				 // 즉, 개발자가 처리한 것이 아니고, 컴파일러에 의해 형변환이 발생한다. 이를 "자동 형변환"이라고 한다.
				 // 자동형변환이 되는 이유 : 1) 같은 숫자라는 가족(같은 자료형) / 2) 작은 범위의 자료형을 큰 범위의 자료형에 대입하기 때문에 데이터의 손실이 발생하지 않음

		b=(byte)s; // 개발자가 데이터의 손실을 감안해서라도, 원하는 같은 종류의 자료형으로 변환 하는 것을 '강제 형변환'이라고 함
							// 이 때 사용되는 (소괄호)를 "cast 연산자"라고 한다.
							// 주의) 형변환은 언제나 같은 종류끼리만 지원함 - "같은 숫자(실수, 정수 상관무)끼리"
							
		char c='B';	// 컴파일 타임에, java 컴파일러가 귀찮음을 감수하고 유니코드를 찾아봄
		char d='한';	
		char k=96;	// 인간인 개발자가 유니코드를 찾아서 직접 넣어준 것으로 생각하여 고마워함
		//char m=-1;	// char형은 사실상 숫자형은 맞으나, 여기에 담을 수 있는 수는 ASCII를 포함한 유니코드를 담기위한 용도임
							// 따라서, 키코드 체계에서는 음수를 지원하지 않기 때문에 에러 발생
		
		s=(short)k;
	}
}