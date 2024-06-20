package chapter5;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Page;

@WebServlet(urlPatterns={"/chapter5/greeting2"})
public class Greeting2 extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out=resp.getWriter();


		// getParameterメソッド...リクエストパラメータからデータを取得
		// GETの場合はURLの「?user=xxx」のこと
		// クエリ文字列という↑
		req.setCharacterEncoding("UTF-8");
		String user=req.getParameter("user");

		Page.header(out);
		out.println("<p>こんにちは、"+user+"さん！</p>");
		Page.footer(out);
	}

}
