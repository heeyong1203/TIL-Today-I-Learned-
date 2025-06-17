/* �Ʒ��� Ŭ������ ���� �����ø����̼� ���������� �ؼ� �� ����� �� �ִ� javaEE ����� Ŭ������ �����Ѵ�...
     �̷��� Ŭ������ ������ �����(Sevlet)�̶� �Ѵ�...
*/
package mall.product;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;

import java.io.PrintWriter;
import java.io.IOException;

public class ProductController extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 클라이언트의 웹브라우저에 출력될 문자열을 스트림으로 준비해보자
		PrintWriter out = response.getWriter();
		out.print("my mall application!!");
	}
}